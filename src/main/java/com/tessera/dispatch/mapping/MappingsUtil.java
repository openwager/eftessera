package com.tessera.dispatch.mapping;

import java.io.*;
import java.util.*;

import org.apache.log4j.*;

import com.tessera.dispatch.*;
import com.tessera.dispatch.mapping.xml.*;
import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public class MappingsUtil
{
	protected static final Logger logger = Logger.getLogger (MappingsUtil.class); 
	
	/**
	 * Private constructor to defeat instantiation. 
	 */
	
	private
	MappingsUtil ()
	{
		return; 
	}
		
	/**
	 * 
	 * @param path
	 * @param disp
	 */
	
	public static
	void loadMappings (final String path, final Dispatcher disp)
		throws IOException
	{
//		if (path.startsWith (File.separator + "WEB-INF")) {
		if (path.substring (1, 8).equals ("WEB-INF")) {
			final String realPath = disp.getServletContext ().getRealPath (path); 
			loadMappings (new File (realPath), disp); 
		} else { 
	    	final InputStream is = Thread.currentThread ().getContextClassLoader ().getResourceAsStream (path); 
	        if (is == null) {
	            logger.error ("Resource not found: " + path);           
	        } else { 
	        	loadMappings (is, path, disp);
	        }
		}

        return; 
	}
	
	/**
	 * 
	 * @param path
	 * @param disp
	 * @throws IOException
	 */
	
	public static
	void loadMappings (final File path, final Dispatcher disp)
		throws IOException
	{
		if (! path.exists ()) { 
			logger.error ("Missing resource: " + path);
			return; 
		}
	
		logger.info ("Loading mappings from: " + path.getAbsolutePath ()); 
		
		// If the path is a directory, recursively 
		
		if (path.isDirectory ()) { 
			for (final File file : path.listFiles ()) {
				final String name = file.getName ().toLowerCase (); 
				if (! name.startsWith (".")) { 
					if (file.isDirectory () || name.endsWith (".xml")) {
						loadMappings (file, disp);
					} else { 
						logger.info ("Ignoring file: " + file.getName ()); 
					}
				}
			}    		
		} else { 
			final InputStream is = new FileInputStream (path);
			loadMappings (is, path.toString (), disp); 
		}
		
		return; 

	}

	/** 
	 * A static link to a codec to use until we can get fancy and support multiple codecs. 
	 */
	
	protected static MappingsCodec codec = new XMLMappingsCodec ();  
	
	 /**
	 *
	 * @param is
	 * @param path
	 */
	
	public static 
	void loadMappings (final InputStream is, final String path, final Dispatcher disp)
	{
	    final Map <String, InterceptorChain> map;
	    final DispatchPath dp = disp.getDispatchPath (); 
	    
	    try {
	        map = codec.decode (is, path);
	        logger.info ("Loaded " + map.size () + " mappings from '" + path + "'.");
	        for (final String action : map.keySet ()) { 
	        	final InterceptorChain list = map.get (action);
	        	if (dp.lookup (action) != null) {  
	        		logger.warn ("Action '" + action + "' redefined."); 
	        	}
	        	dp.register (action, list); 
	        	
	        }
	    }
	    catch (final IOException io_e) {        	
	        logger.info ("Mappings load failed: " + path, io_e);
	    }
	    catch (final MappingException m_e) { 
	    	logger.warn ("Mappings load failed: " + m_e); 
	    }
	    
	    return; 
	}
}

// EOF