import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class TestServlet
	extends HttpServlet
{

	public
	TestServlet ()
	{
		System.err.println ("HELLO"); 
		return; 
	}
	
	@Override
    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
		doPut (req, res); 
		return; 
    }

	@Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
		final OutputStream os = res.getOutputStream ();
		final PrintStream ps = new PrintStream (os); 
		ps.println ("Hello, World!"); 
		res.setContentType ("text/plain"); 
		ps.flush (); 
		ps.close (); 
		return; 
    }
}

// EOF
