package com.tessera.intercept.form;

import java.lang.annotation.*;

/**
 * 
 * @author crawford
 *
 */

@Retention (RetentionPolicy.RUNTIME)
public @interface PropertiesField
{
    String keyPattern () default "k%d";
    String valuePattern () default "v%d";
}

// EOF