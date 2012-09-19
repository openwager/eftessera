package com.tessera.intercept;

import javax.servlet.http.*;

import org.apache.commons.beanutils.*;
import org.apache.commons.jexl.*;
import org.apache.log4j.*;

/**
 * 
 * @author crawford
 *
 */

public class JexlExpression<T>
{
	private static final Logger logger = Logger.getLogger (JexlExpression.class); 
	
	public
	JexlExpression (final Class<T> type, final String val)
		throws Exception
	{
		setOriginal (val) ;
		if (val != null) { 	
			if (val.startsWith ("${")) { 
				if (! val.endsWith ("}")) { 
					throw new IllegalArgumentException ("Expected '}'."); 
				}
				final String es = val.substring (2, val.length () - 1);  
				final Expression e = ExpressionFactory.createExpression (es);
//				e.addPostResolver (new CustomResolver ()); 
				setExpression (e); 
			} else { 
				setString (val); 
			}
		}
		setType (type); 
		return; 
	}
	
	protected String original; 
	protected void setOriginal (final String original) { this.original = original; return; } 
	public String getOriginal () { return this.original; } 
	
	protected Expression expression;
	public Expression getExpression () { return this.expression; } 
	public void setExpression (final Expression expression) { this.expression = expression; return; } 
	
	protected String str; 
	public String getString () { return this.str; } 
	public void setString (final String str) { this.str = str; return; } 

	protected Class<T> type; 
	public Class<T> getType () { return this.type; } 
	public void setType (final Class<T> type) { this.type = type; return; } 
	
//	@SuppressWarnings("unchecked")
    public
	T evaluate (final Class<T> type, final HttpServletRequest req)
		throws Exception
	{ 
    	try { 
    		Object o; 
    		if (str != null ) { 
    			o = str; 
    		} else if (expression != null) { 
    			final JexlContext context = JexlUtil.getContext (req); 
    			o = expression.evaluate (context); 
    		} else { 
    			return null; 
    		}
    		if (o != null) { 
    			if (! type.isAssignableFrom (o.getClass ())) { 
    				o = ConvertUtils.convert (o, type);
    			}
    		}
    		return type.cast (o);     		
    	}
    	catch (final Throwable t) 	{
    		logger.error ("Error evaluating '" + getOriginal () + "'.", t) ;
    		if (t instanceof Exception) { 
    			throw (Exception) t; 
    		} else if (t instanceof RuntimeException) { 
    			throw (RuntimeException) t; 
    		}
    	}
    	
    	return null; 
	}
	
    public
	T evaluate (final HttpServletRequest req)
		throws Exception
	{
    	return evaluate (getType (), req); 
	}

    /**
     * 
     * @author crawford
     *
     */
    
    class CustomResolver
    	implements JexlExprResolver
    {
    	public
    	CustomResolver ()
    	{
    		return; 
    	}
    	
	    public
	    Object evaluate (final JexlContext arg0, final String arg1)
        {
	        logger.error ("evaluate (" + arg1 + ")"); 
	        return null;
        }    	
    }
}

// EOF
	