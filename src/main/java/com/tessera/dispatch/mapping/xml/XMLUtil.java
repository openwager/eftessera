package com.tessera.dispatch.mapping.xml;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.apache.log4j.*;
import org.w3c.dom.*;

import com.weaselworks.util.*;


/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class XMLUtil
{	
	private static final Logger logger = Logger.getLogger (XMLUtil.class); 
	
	private
	XMLUtil ()
	{
		return;
	}

	/**
	 * Convenience method for obtaining a new DocumentBuilder instance. 
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 */
	
    public static
    DocumentBuilder getBuilder ()
        throws ParserConfigurationException
    {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
        factory.setNamespaceAware (false);
        factory.setValidating (false);
        return factory.newDocumentBuilder ();
    }

    /**
     * Utility method for dumping a human-readable version of the specified element
     * to the output stream. 
     * 
     * @param el
     * @param os
     * @throws Exception
     */
	
	public static 
	void dump (final Element el, final OutputStream os)
		throws Exception
	{
        // Now dump it to the specified output stream

        final TransformerFactory xfactory = TransformerFactory.newInstance ();
        final Transformer transformer = xfactory.newTransformer ();
        transformer.setOutputProperty("omit-xml-declaration","yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final Source source = new DOMSource (el);
        final Result result = new StreamResult (os);
        transformer.transform (source, result);
        return; 
	}
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	
	public static
	Document parse (final InputStream is)
		throws Exception
	{
		return parse (is, new XMLContext ()); 
	}
	
	/**
	 * 
	 * @param is
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	
	public static 
	Document parse (final InputStream is, final XMLContext ctx)
		throws Exception
	{
        final DocumentBuilder builder = getBuilder ();
        final Document doc = builder.parse (is);
        ctx.setDocument (doc); 
        
        // Extract any macro definitions and add them to the context (permanently) 
        
        final Element elem = doc.getDocumentElement (); 

        for (final Element e : new ElementIterator (elem)) { 
    		final String name = e.getNodeName (); 
    		if ("define".equals (name)) { 
    			final XMLMacro macro = parseMacro (e); 
    			if (ctx.hasMacro (macro.getName ())) { 
    				logger.warn ("Macro already defined: " + name); 
    			}
    			ctx.setMacro (macro.getName (), macro); 
    			elem.removeChild (e); 
    		}        	
        }

        // Then process the document by expanding any embedded macros
      
        expandMacros (null, elem, ctx); 
        ctx.setDocument (null); 
        return doc; 		
	}

	/**
	 * 
	 * @param parent
	 * @param ctx
	 * @return
	 */
	
	private static
	void expandMacros (final Element parent, final Element child, final XMLContext ctx)
		throws Exception
	{	
		final String name = child.getNodeName ();
		
		if (ctx.hasMacro (name)) { 
			final XMLMacro macro = ctx.getMacro (name); 
			final List<Element> els = expandMacro (child, macro, ctx); 
        	for (final Element el : els) { 
				parent.insertBefore (el, child);					
				expandMacros (parent, el, ctx); 
			}        			        	
        	ctx.popParameters ();
			parent.removeChild (child);         		
		} else { 
			for (final Element e : new ElementIterator2 (child)) { 
				expandMacros (child, e, ctx); 
			}
		}
		return; 
	}
	

	/**
	 * 
	 * @param el
	 * @param macro
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	
	public static
	List<Element> expandMacro (final Element el, final XMLMacro macro, final XMLContext ctx)
		throws Exception
	{
		// Setup the context for expansion
		
		final Map<String, String> params = macro.getDefaults (); 
		for (final Node node : new AttributeIterator (el)) { 
			final String attr = node.getNodeName (); 
			final String val = node.getNodeValue (); 
			params.put (attr, val); 
		}
		ctx.pushParameters (params); 
				
		// Expand the enclosed elements
		
		final List<Element> els = new ArrayList<Element> ();
		for (final Element e : macro.getExpansion ()) {
			final Element copy = clone (e, ctx.getDocument ());
			expand (copy, ctx); 
			els.add (copy); 		
		}
 
		return els; 
	}

	/**
	 * A poor man's clone implementation since the XML parser seems to get very
	 * upset when we insert nodes form one document into another. Just when you 
	 * thought the Java XML API couldn't get any worse, you discover undocumented, 
	 * sementic brokenness. 
	 *  
	 * @param el
	 * @param doc
	 * @return
	 */
	
	public static 
	Element clone (final Element el, final Document doc)
	{
		final Element nel = doc.createElement (el.getNodeName ());
		for (final Node node : new AttributeIterator (el)) { 
			nel.setAttribute (node.getNodeName (), node.getNodeValue ()); 
		}
		for (final Element child : new ElementIterator (el)) { 
			nel.appendChild (clone (child, doc)); 
		}
		return nel; 
	}
	
	/**
	 * Expands any parameter expansions (e.g., "foo %{bar} baz") in the attribute
	 * values of the specified element. 
	 * 
	 * The following are currently unsupported: 
	 * 
	 * <ul>
	 *   <li>Expansion of parameters in attribute names
	 *   <li>Expansion of parameters in CDATA or PCDATA sections
	 * </ul>
	 * 
	 * @param source
	 * @param ctx
	 * @return
	 */
	
	private static
	void expand (final Element el, final XMLContext ctx)
		throws Exception
	{
		// Expand any attribute values
		
		for (final Node node : new AttributeIterator (el)) { 
			final String name = node.getNodeName (); 
			final String value = node.getNodeValue (); 
			if (value.indexOf ("%{") >= 0) {
				final String newValue = ctx.expand (value); 
				el.setAttribute (name, newValue); 
			}			
		}
		for (final Element child : new ElementIterator (el)) { 
			expand (child, ctx); 
		}
		return; 		
	}
	
	
	/**
	 * Parses a macro definition into a usable {@linkplain XMLMacro} object. 
	 * 
	 * @param el the define element to be parsed
	 * @return the complete XMLMacro
	 */
	
	public static
	XMLMacro parseMacro (final Element el)
		throws Exception
	{
		final XMLMacro macro = new XMLMacro ();
		
		// Get the name of the macro 
		
		final String mname = el.getAttribute ("name"); 
		if (StringUtil.isEmpty (mname)) { 
			throw new Exception ("Missing attribute: name"); 			
		}
		macro.setName (mname); 
		
		// Extract the rest of the attributes
		
		for (final Node node : new AttributeIterator (el)) { 
			final String name = node.getNodeName (); 
			if (! "name".equals (name)) {
				macro.setDefault (name, node.getNodeValue ()); 
			}
		}
		
		// Extract the macro expansion
		
		final List<Element> ex = new ArrayList<Element> (); 
		for (final Element e : new ElementIterator (el)) { 
			ex.add (e); 
		}
		macro.setExpansion (ex); 
		return macro;
	}
}

// EOF