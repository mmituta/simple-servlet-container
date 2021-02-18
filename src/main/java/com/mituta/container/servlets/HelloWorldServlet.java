package com.mituta.container.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of servlet that always responds with "Hello world".
 */
public class HelloWorldServlet extends HttpServlet
{

    /**
     * Ignores the request and always responds with "Hello world".
     *
     * @param request will be ignored.
     * @param response response that the "Hello world" message will be written to.
     * @throws IOException
     */
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response )
        throws IOException
    {
        PrintWriter out = response.getWriter();
        out.println( "Hello world" );
    }

}
