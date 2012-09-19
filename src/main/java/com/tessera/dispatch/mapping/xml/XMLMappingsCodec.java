package com.tessera.dispatch.mapping.xml;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.apache.log4j.*;
import org.w3c.dom.*;

import com.tessera.dispatch.mapping.*;
import com.tessera.intercept.*;
import com.tessera.intercept.util.*;
import com.weaselworks.util.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class XMLMappingsCodec
	implements MappingsCodec
{

	private static final Logger logger = Logger.getLogger (XMLMappingsCodec.class.getName ()); 
	
    public
    XMLMappingsCodec ()
    {
        return;
    }

    protected XMLContext context = new XMLContext (); 
    
    public
    XMLContext getXMLContext () 
    {
    	return context; 
    }
    
    /**
     * Interface containing constants used to encode and decode the XML-based
     * representation
     */

    interface XML
    {
        interface M
        {
            public static final String E = "mappings";
            public static final String IGNORE = "ignore";

            interface PREFIX
            {
                public static final String E = "prefix";
                public static final String NAME = "name";
            }

            interface AM
            {
                public static final String E = "action";
                public static final String CLASS = "class";
                public static final String NAME = "name";

                interface I
                {
                    public static final String E = "interceptor";
                    public static final String CLASS = "class";
                    public static final String ENABLED = "enabled";

                    interface CASE
                    {
                        public static final String E = "case";
                        public static final String NAME = "name";
                    }            

                    interface P
                    {
                        public static final String E = "property";
                        public static final String NAME = "name";
                        public static final String VALUE = "value";
                    }
                    interface L
                    {
                        public static final String E = "list";
                        public static final String ENTRY = "entry";
                    }
                }
            }
        }
    }

    /**
     * A utility method used to instantiate a new {@link DocumentBuilder} with
     * the appropriate settings for dispatcher parsing.
     *
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     */

//	    protected static
//	    DocumentBuilder getBuilder ()
//	        throws ParserConfigurationException
//	    {
//	        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
//	        factory.setNamespaceAware (false);
//	        factory.setValidating (false);
//	        return factory.newDocumentBuilder ();
//	    }

    /**
     * @see ActionMappingsCodec#encode(java.util.Map, java.io.OutputStream) <java.lang.String,com.twofish.ludwig.dispatcher.ActionMapping>, java.io.OutputStream)
     */

    public
    void encode (final Map<String, InterceptorChain> map, final OutputStream os)
        throws IOException
    {
        try {
            final DocumentBuilder builder = XMLUtil.getBuilder ();
            final Document doc = builder.newDocument ();

            // Create the top-level mappings element

            Element e = doc.createElement (XML.M.E);
            doc.appendChild (e);

            // Create new elements for each of the actionMappings

            for (final String uri : map.keySet ()) {
                final Interceptor action = map.get (uri);
                final Element child = toElement (doc, uri, action);
                e.appendChild (child);
            }

            // Now dump it to the specified output stream

            final TransformerFactory xfactory = TransformerFactory.newInstance ();
            final Transformer transformer = xfactory.newTransformer ();
            transformer.setOutputProperty("omit-xml-declaration","yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            final Source source = new DOMSource (e);
            final Result result = new StreamResult (os);
            transformer.transform (source, result);
        }
        catch (final Exception e) {
            e.printStackTrace ();
        }

        return;
    }

    /**
     *
     * @param doc
     * @param mapping
     * @return
     */

    protected
    Element toElement (final Document doc, final String uri, final Interceptor action)
    {
        // Create a new actionMapping property

        final Element e = doc.createElement (XML.M.AM.E);
        e.setAttribute (XML.M.AM.NAME, uri);

        // Set the action class and any associated properties

//	        e.setAttribute (XML.M.AM.CLASS, action.getClass ().getName ());
//	        for (final Object obj : action.getProperties ().keySet ()) {
//	            final String key = (String) obj;
//	            if (key.startsWith ("_")) {
//	                continue;
//	            }
//	            e.setAttribute (key, action.getProperty (key));
//	        }

        // Add the interceptors as child elements

//	        for (final InterceptorList.InterceptorEntry ie : action.getInterceptorList ().getUnderlying ()) {
//	            e.appendChild (toElement (doc, ie));
//	        }
        return e;
    }

    /**
     *
     * @param doc
     * @param ie
     * @return
     */

//	    protected
//	    Element toElement (final Document doc, final InterceptorList.InterceptorEntry ie)
//	    {
//	        final Element e = doc.createElement (XML.M.AM.I.E);
//	        e.setAttribute (XML.M.AM.I.ENABLED, "" + ie.getEnabled ());
//	        final Interceptor i = ie.getInterceptor ();
//	        e.setAttribute (XML.M.AM.I.CLASS, i.getClass ().getName ());
//
//	        // Create the properties
//
//	        for (final Object obj : i.getProperties () .keySet ()) {
//	            final String key = (String) obj;
//	            if (key.startsWith ("_")) {
//	                continue;
//	            }
//	            final Element p = doc.createElement (XML.M.AM.I.P.E);
//	            p.setAttribute (XML.M.AM.I.P.NAME, key);
//	            p.setAttribute (XML.M.AM.I.P.VALUE, i.getProperties ().getProperty (key));
//	            e.appendChild (p);
//	        }
//	        return e;
//	    }

    /**
     * @see ActionMappingsCodec#decode(java.io.InputStream, String)
     */

    public
    Map<String, InterceptorChain> decode (final InputStream is, final String path)
        throws IOException
    {
        final Map<String, InterceptorChain> map = new HashMap<String, InterceptorChain> ();

        // Parse the document

        try {
            final Document doc = XMLUtil.parse (is, context);
            final Element elem = doc.getDocumentElement ();
            require (XML.M.E, elem);

            // Parse the action mappings

            final PrefixContext ctxt = new PrefixContext ();
            decodeMappings (elem, map, ctxt);
        }
        catch (final Exception e) {
            e.printStackTrace ();
            return null;
        }

        return map;
    }

    /**
     * Used to decode the top-level mappings document that has been parsed from the input
     * path. Expects the passed Element to contain a single mappings element.
     *
     * @param elem the document element
     * @param map the mappings cache to write entries into
     * @param ctxt the prefix context used to maintain the current implicit prefix for actions
     * @throws IOException
     */

    protected static
    void decodeMappings (final Element elem, final Map<String, InterceptorChain> map, final PrefixContext ctxt)
        throws Exception
    {
        // Loop through all the children, parsing them in turn

        final NodeList nodes = elem.getChildNodes ();

        for (int i = 0; i < nodes.getLength (); i ++) {
            final Node node  = nodes.item (i);
            if (! (node instanceof Element)) {
                continue;
            }

            final Element child = (Element) nodes.item (i);
            final String tag = child.getTagName ();

            if (tag.equals ("prefix")) {
                final String key = child.getAttribute ("name");
                if (StringUtil.isEmpty (key)) {
                    throw new IOException ("Missing prefix attribute: name");
                }
                ctxt.pushPrefix (key);
                decodeMappings (child, map, ctxt);
                ctxt.popPrefix ();
            } else if (tag.equals ("action")) {
                final String key = child.getAttribute ("name");
                if (key == null) {
                    throw new IOException ("Missing action attribute: name");
                }
            	try { 
            		final InterceptorChain action = decodeActionMapping (child, ctxt);
            		map.put (action.getProperty ("_name"), action);
            	}
            	catch (final Exception e) { 
            		logger.error ("Action '" + key + "' failed: " + e.getMessage (), e); 
            	}
            } else {
                throw new IOException ("ERROR: Unrecognized tag: " + tag);
            }
        }

        return;
    }

    /**
     * Used to decode a single action mapping.
     *
     * @param elem the element to be parsed
     * @param ctxt the current prefix context used to maintain the implicit prefix
     * @return the decoded action mapping
     * @throws Exception
     */

    protected static
    InterceptorChain decodeActionMapping (final Element elem, final PrefixContext ctxt)
        throws Exception
    {
        require (XML.M.AM.E, elem);
        final InterceptorChain chain = new InterceptorChain (); 

        // Determine the complete path to the action using the prefix and specified name

        final String name = elem.getAttribute (XML.M.AM.NAME);
        if (StringUtil.isEmpty (name)) {
            if (StringUtil.isEmpty (ctxt.addPrefix (""))) {
                throw new IOException ("ERROR: Cannot have empty name without a prefix.");
            }
        }
        final String fullname = ctxt.addPrefix (name); 
        chain.setProperty ("_name", fullname); 
        
        // Interpret any additional element attributes as properties to be set on the action itself

        final NamedNodeMap nnm = elem.getAttributes ();

        for (int i = 0; i < nnm.getLength (); i ++) {
            final Node n = nnm.item (i);
            final String key = n.getNodeName ();
            if (XML.M.AM.CLASS.equals (key) || XML.M.AM.NAME.equals (key)) {
                continue;
            }
            chain.setProperty (key, n.getNodeValue ());
        }

        // Parse the enclosed interceptor chain

        decodeInterceptors (elem, chain);

        // Initialize the chain before returning the mapping

        chain.init (); 
        return chain;
    }

    /**
     *
     * @param elem
     * @param list
     * @return
     * @throws Exception
     */

    public static
    InterceptorChain decodeInterceptors (final Element elem, InterceptorChain chain)
        throws Exception
    {
        if (chain == null) {
            chain = new InterceptorChain ();
        }
        final NodeList nodes = elem.getChildNodes ();

        for (int i = 0; i < nodes.getLength (); i ++) {
            final Node node = nodes.item (i);
            if (node.getNodeType () != Node.ELEMENT_NODE) {
                continue;
            }
            final Element child = (Element) node;
            if (! child.getTagName ().equals (XML.M.AM.I.E)) {
                continue;
            }
            final Interceptor interceptor = decodeInterceptor (child);
            chain.addInterceptor (interceptor);
            final String enabledStr = child.getAttribute (XML.M.AM.I.ENABLED);
            boolean enabled = StringUtil.isEmpty (enabledStr) || Boolean.parseBoolean (enabledStr);
            interceptor.setEnabled (enabled);
        }

        return chain;
    }

    /**
     *
     * @param elem
     * @return
     * @throws Exception
     */

    public static
    Map<String, String> extractProperties (final Element elem)
        throws Exception
    {
        return extractProperties (elem, new PropertyContext (), false);
    }

    /**
     * A utility method used to extract the properties from an interceptor
     * declaration.
     *
     * @param elem the element containing the properties
     * @return
     */

    public static
    Map<String, String> extractProperties (final Element elem, final PropertyContext ctx, final boolean inList)
        throws Exception
    {
        final NodeList nodes = elem.getChildNodes ();

        for (int i = 0; i < nodes.getLength (); i ++) {
            final Node node = nodes.item (i);
            if (! (node instanceof Element)) {
                continue;
            }
            final Element child = (Element) nodes.item (i);
            final String tag = child.getTagName ();

            if (tag.equals ("list")) {
                final String prefix = child.getAttribute (XML.M.PREFIX.E);
                if (! StringUtil.isEmpty (prefix)) {
                    ctx.pushPrefix (prefix);
                }
                ctx.startList ();
                extractProperties (child, ctx, true);
                ctx.endList ();
                if (! StringUtil.isEmpty (prefix)) {
                    ctx.popPrefix ();
                }
            } else if (tag.equals ("property")) {
                if (inList) {
                    ctx.incrementList ();
                }
                extractProperty (child, ctx);
            } else if (tag.equals ("entry")) {
                ctx.incrementList ();
                extractProperties (child, ctx, false);
            } else {
                continue;
            }
        }

        return ctx.getProperties (); 
    }

    /**
     *
     * @param elem
     * @param ctx
     * @throws Exception
     */

    public static
    void extractProperty (final Element elem, final PropertyContext ctx)
        throws Exception
    {
        require ("property", elem);
        final String key = elem.getAttribute ("name");
        final String value = elem.getAttribute ("value");
        ctx.setProperty (key, value);
        return;
    }

    /**
     *
     * @param elem
     * @param si
     */

    public static
    void decodeCases (final Element elem, final MappedInterceptor si)
        throws Exception
    {
        final NodeList nodes = elem.getChildNodes ();

        for (int i = 0; i < nodes.getLength (); i ++) {
            final Node node  = nodes.item (i);
            if (! (node instanceof Element)) {
                continue;
            }
            final Element child = (Element) node;
            final String tag = child.getTagName ();
            if (tag.equals ("case")) {
                String key = child.getAttribute ("name");
                if (key == null) {
                    key = "true";
                }
                decodeInterceptors (child, si.getCase (key, true));
            } else if (tag.equals ("interceptor")) {
                final Interceptor interceptor = decodeInterceptor (child);
                final InterceptorChain list = si.getCase (Boolean.toString (true), true);
                list.addInterceptor (interceptor); 
                final String enabledStr = child.getAttribute (XML.M.AM.I.ENABLED);
                boolean enabled = StringUtil.isEmpty (enabledStr) || Boolean.parseBoolean (enabledStr);
                interceptor.setEnabled (enabled);
            } else if (tag.equals ("property")) {
                continue;
            } else if (tag.equals ("list")) {
                continue; 
            } else {
                throw new IOException ("ERROR: Unrecognized child tag: " + tag);
            }
        }

        return;
    }

    /**
     * Used to decode an interceptor element completely.
     *
     * @param elem
     * @return
     * @throws IOException
     */

    protected static
    Interceptor decodeInterceptor (final Element elem)
        throws Exception
    {
        require (XML.M.AM.I.E, elem);

        // Instantiate the interceptor with the declared properties

        final String className = elem.getAttribute (XML.M.AM.I.CLASS);
        Interceptor interceptor = null;

        if (StringUtil.isEmpty (className)) { 
        	interceptor = createErrorInterceptor ("MISSING INTERCEPTOR [" + className + "]; MISSING CLASS ATTRIBUTE (?)."); 
        	logger.error ("Missing required class attribute on interceptor (action=?).");
        	interceptor = initInterceptor (interceptor); 
        	return interceptor; 
        }

        try {
            final Map<String, String> props = extractProperties (elem);
            final Class<?> clazz = Thread.currentThread ().getContextClassLoader ().loadClass (className);
            final Constructor<?> cons = clazz.getConstructor (new Class [] { Map.class });
            interceptor = (Interceptor) cons.newInstance (props);
        }
        catch (final ClassNotFoundException cnf_e) {
        	interceptor = createErrorInterceptor ("MISSING INTERCEPTOR [" + className + "] (" + cnf_e.getMessage () + ")."); 
        	logger.error ("Interceptor class not found: " + className + "; replaced by stub."); 
        	
        }
        catch (final InvocationTargetException it_e) {
            throw new IOException ("Cannot instantiate: " + className, it_e);
        }

        // Parse any enclosed interceptors based on the type of the interceptor. Note that
        // we aren't detecting the error case where there are embedded interceptors that
        // go ignored because the interceptor class declared doesn't support cases

        if (interceptor instanceof MappedInterceptor) {
            decodeCases (elem, (MappedInterceptor) interceptor);
        }

        interceptor = initInterceptor (interceptor); 
        return interceptor;
    }

    /**
     * 
     * @param itor
     * @return
     *
     */
    
    protected static
    Interceptor initInterceptor (final Interceptor itor)
    {
    	try { 
    		itor.init (); 
    		return itor; 
    	}
    	catch (final Throwable t) { 
    		final String cname = itor.getClass ().getName (); 
    		logger.error ("Interceptor [" + cname + "] initialization failed. (" + t.getMessage () + ")"); 
    		final Interceptor repl = createErrorInterceptor ("MISSING INTERCEPTOR [" + itor.getClass().getName () + "]; INITIALIZATION FAILED. (" + t.getMessage () + ")");
    		// TODO: Surely there's a cleaner way to do this [crawford]0
    		try { repl.init (); } catch (Throwable t2) {  } 
    		return repl; 
    	}
    	
    	// NOT REACHED
    }
    
    /**
     * 
     * @param msg
     * @return
     */
    
    protected static
    Interceptor createErrorInterceptor (final String msg)
    {
       	final Map<String, String> props = new HashMap<String, String> (); 
    	props.put ("expression", msg); 
    	final Interceptor interceptor = new SystemErrInterceptor (props);
    	return interceptor; 
    }
    
    /**
     *
     */

    static class PrefixContext
    {
        protected List<String> prefixes = new ArrayList<String> ();
        public String popPrefix () { return prefixes.remove (prefixes.size () -1); }
        public void pushPrefix (final String prefix) { prefixes.add (prefix); return; }
        protected char separator = '/';

        public
        String addPrefix (final String key)
        {
            StringBuffer buf = new StringBuffer ();
            for (final String prefix : prefixes) {
                buf.append (prefix);
                buf.append (separator);
            }
            if (StringUtil.isEmpty (key)) {
                buf.setLength (buf.length () - 1);
            } else {
                buf.append (key);
            }
            return buf.toString ();
        }
    }

    /**
     *
     */

    static class PropertyContext
        extends PrefixContext
    {
        public
        PropertyContext ()
        {
            separator = '.';
            return;
        }

        protected Map<String, String> props = new HashMap<String, String> ();
        public Map<String, String> getProperties () { return this.props; }
        public void setProperties (final Map<String, String> props) { this.props = props; return; }
        public void setProperty (final String key, final String value) { props.put (addPrefix (key), value); return; }

        public void startList () {  pushPrefix ("-1"); return; }
        public void endList () {  popPrefix (); return; }
        public void incrementList () { int next = Integer.parseInt (popPrefix ()) + 1; pushPrefix ("" + next); return; }

        protected InterceptorChain ilist;
        public InterceptorChain getInterceptorList () { return this.ilist; }
        public void setInterceptorList (final InterceptorChain ilist) { this.ilist = ilist; return; }
    }

    /**
     * A utility method used to verify that the element is indeed the one that is expected.
     *
     * @param tagname the name of the element that is expected
     * @param elem the element to be verified
     * @throws IOException thrown if there is a mismatch
     */

    protected static
    void require (final String tag, final Element elem)
        throws IOException
    {
        if (! tag.equals (elem.getTagName ())) {
            throw new IOException ("Expected '" + tag + "'; found '" + elem.getTagName () + "'");
        }
        return;
    }
}

// EOF