package com.mituta.container.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example of servlet that returns the current time.
 */
public class CurrentTimeServlet extends HttpServlet
{

    /**
     * Ignores the request and always responds with current time.
     *
     * @param request will be ignored.
     * @param response response that the current time will be written to.
     * @throws IOException
     */
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response )
        throws IOException
    {
        PrintWriter out = response.getWriter();
        out.println( LocalDateTime.now() );
    }

}
