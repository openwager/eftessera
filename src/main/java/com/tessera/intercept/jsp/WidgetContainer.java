package com.tessera.intercept.jsp;

import java.io.*;
import java.util.*;
import java.util.List;

import javax.servlet.http.*;

/**
 * 
 * @author crawford
 *
 */

public class WidgetContainer
	implements Widget
{
	public
	WidgetContainer ()
	{
		return;
	}
	
	public
	WidgetContainer (final Map<String, String> props)
	{
		String s = props.get (PROP.OPEN);
		if (s != null) {
		    this.open = s;
		}
		s = props.get (PROP.CLOSE);
		if (s != null) {
		    this.close = s;
		}
		s = props.get (PROP.SEPARATOR);
		if (s != null) {
		    this.separator = s;
		}
		return;
	}
	
	public interface PROP
	{
		public static final String OPEN = "prefix";
		public static final String CLOSE = "postfix";
		public static final String SEPARATOR = "separator";
	}
	
	interface DEFAULT
	{
		public String OPEN = "";
		public String CLOSE = "";
		public String SEPARATOR = "";
	}
		
	protected String open = DEFAULT.OPEN;
	protected String close = DEFAULT.CLOSE;
	protected String separator = DEFAULT.SEPARATOR;
	
	public
	void render (final HttpServletRequest req, final HttpServletResponse res, final Writer out)
		throws IOException
	{
		out.write (open);
		for (final Widget widget : getWidgets ()) {
	        widget.render (req, res, out);
	        out.write (separator);
		}
		out.write (close);
		return;
	}
	
	protected List<Widget> widgets = new ArrayList<Widget> ();
	
	public
	List<Widget> getWidgets ()
	{
	return widgets;
	}
	
	public
	void addWidget (final Widget widget)
	{
		widgets.add (widget);
		return;
	}
	
	public
	boolean removeWidget (final Widget widget)
	{
	    return widgets.remove (widget);
	}
	
	public
	int getWidgetCount ()
	{
	    return widgets.size ();
	}
	
	public
	Widget getWidget (final int index)
	{
	    return widgets.get (index);
	}
	
	public
	List<Widget> getWidgetList ()
	{
	    return widgets;
	}
}

//EOF