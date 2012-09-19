package com.tessera.dispatch.mapping;

import java.io.*;
import java.util.Map;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 * 
 * @author Lee Crawford (lee.crawford@gmail.com) 
 * @copyright Copyright (c) 2009, Weaselworks, Inc. 
 */

public interface MappingsCodec
{
    /**
     *
     * @param map
     * @param os
     * @throws IOException
     */

    public
    void encode (Map<String, InterceptorChain> map, OutputStream os)
        throws IOException;

    /**
     *
     * @param is
     * @return
     * @throws IOException
     */

    public
    Map<String, InterceptorChain> decode (InputStream is, String path)
        throws IOException;
}

// EOF