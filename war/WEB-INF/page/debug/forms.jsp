<%@ page language="java" %> 
<%@ page isELIgnored="false" %> 

<%@ page import="java.io.*" %>
<%@ page import="java.beans.*" %>
<%@ page import="java.lang.reflect.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.weaselworks.util.*" %>
<%@ page import="com.tessera.intercept.form.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>
    HttpServletRequest Forms
</h1>

<%!
	protected
	void dumpForm (final Form form, final String attr, final JspWriter out)
		throws Exception
	{
		out.println ("<h2>FORM [" + attr + "]</h2>");
		
		out.println ("<table class='debug'>");
		
		out.println ("<tr><th colspan='2'>Form  Details</th></tr>");
		out.println ("<tr><td>formType:</td><td>" + form.getClass ().getName () + "</td></tr>"); 
		out.println ("<tr><td>bound:</td><td>" + form.getBound () + "</td></tr>"); 
		out.println ("<tr><td>hasErrors:</td><td>" + form.getHasErrors () + "</td></tr>"); 
		out.println ("<tr><td>throwable:</td><td>" + form.getThrowable () + "</td></tr>"); 
		
		out.println ("<tr><th colspan='2'>Form Fields</tr>"); 
		final Class<?> type = form.getClass (); 
        final BeanInfo beanInfo = Introspector.getBeanInfo (type);
        final PropertyDescriptor [] props = beanInfo.getPropertyDescriptors ();
        boolean first = true;

        final String [] skipFields = { 
        	"errors", "bound", "globalErrors", "hasErrors", "class"
        };
        final List<String> skip = Arrays.asList (skipFields); 
        
        for (int i = 0; i < props.length; i ++) {
            final PropertyDescriptor prop = props [i];
        	final String name = prop.getName (); 
			if (skip.contains (name)) { 
				continue;
            }
            final Method read = prop.getReadMethod ();
            if (read != null) {
                final Object val = read.invoke (form, new Object[0]);
            	out.println ("<tr><td>" + prop.getName () + "</td><td>" + val + "</td></tr>"); 
            }
        }

		
		out.println ("<tr><th colspan='2'>Field Errors</th></tr>");
		final Map<String, String> errs = form.getErrors (); 
		if (errs != null) { 
			final Set<String> fields = form.getErrors().keySet (); 
			for (final String field : fields) { 
				out.println ("<tr><th>" + field + "</th><td>" + form.getError (field) + "</td></tr>"); 
			}			
		} else { 
			out.println ("<tr><td colspan='2'>None found.</td></tr>"); 
		}		
		
		out.println ("<tr><th colspan='2'>Global Errors</th></tr>"); 
		final List<String> gerrs = form.getGlobalErrors();
		if (gerrs != null) { 
			for (int i = 0; i < gerrs.size (); i ++) { 
				final String gerr = gerrs.get (i); 
				out.println ("<tr><td>" + (i + 1) + ".</td><td>" + gerr + "</td></tr>"); 
			}
		} else { 
			out.println ("<tr><td colspan='2'>None found.</td></tr>"); 
		}
		out.println ("</table>"); 
		return; 
	}
%>

<%
	{
	    final Enumeration<String> e = request.getAttributeNames ();
	    final Iterator<String> iter = new CollatingIterator<String> (new EnumerationIterator<String> (e), new StringComparator ()); 
	 
	    int cnt = 0;
	
	    while (iter.hasNext ()) {
	    	final String attr = iter.next (); 
			final Object obj = request.getAttribute (attr); 
			if (obj instanceof Form) {
				final Form form = (Form) obj; 
				dumpForm (form, attr, out); 
				cnt ++; 
			}
	    }
	    
	    if (cnt == 0) { 
	    	out.println ("None found."); 
	    }
	}
%>

<%-- EOF --%> 