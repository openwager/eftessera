package com.tessera.intercept.form;

import java.lang.reflect.*;
import java.util.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class BeanBinderUtil
{
	private
	BeanBinderUtil ()
	{
		return; 
	}

    protected static final Map<Class<?>, List<Pair<Field, PropertiesField>>> cache = new HashMap<Class<?>, List<Pair<Field, PropertiesField>>> ();

   /**
    *
    * @param type
    * @return
    */

   public static
   List<Pair<Field, PropertiesField>> getFields (final Class<?> type)
   {
           return getFields (type, true);
   }

   /**
    *
    * @param type
    * @param create
    * @return
    */

   public static
   List<Pair<Field, PropertiesField>> getFields (final Class<?> type, final boolean create)
   {
       List<Pair<Field, PropertiesField>> fields =  cache.get (type);
       if (fields == null) {
           if (create) {
               fields = extractFields (type);
               cache.put (type, fields);
           }
       }
       return fields;
   }

   /**
    * 
    * @param type
    * @return
    */
   
   private static
   List<Pair<Field, PropertiesField>> extractFields (final Class<?> type)
   {
       List<Pair<Field, PropertiesField>> fields = new ArrayList<Pair<Field, PropertiesField>> ();

       for (Class<?> klass = type; klass != null; klass = klass.getSuperclass ()) {
           for (final Field field : klass.getDeclaredFields ()) {
               if (field.isAnnotationPresent (PropertiesField.class)) {
                   if (ClassUtil.implementsInterface (field.getType (), Map.class)) {
                       throw new IllegalArgumentException ("Not a Map: " + field.getType ().getName ());
                   }
                   field.setAccessible (true);
                   final PropertiesField pfield = field.getAnnotation (PropertiesField.class);
                   fields.add (new Pair<Field, PropertiesField> (field, pfield));
               }
           }
       }

       return fields;
   }       
}

// EOF
