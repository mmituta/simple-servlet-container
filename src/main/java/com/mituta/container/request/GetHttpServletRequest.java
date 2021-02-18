package com.mituta.container.request;

/**
 * Represents the simplified HTTP servlet request. It assumes that every request will be for a GET method and
 * will contain only the context and servlet paths in the URI.
 */
public class GetHttpServletRequest extends AbstractServletRequestAdapter
{
    private String context;
    private String servletName;

    public GetHttpServletRequest( String context, String servletName )
    {
        this.context = context;
        this.servletName = servletName;
    }

    /**
     * The requested HTTP method. For simplicity it will always return GET.
     *
     * @return the GET HTTP method.
     */

    @Override
    public String getMethod()
    {
        return "GET";
    }

    /**
     * Returns the context segment of the request URI.
     * </br></br>
     * According to the specification the context path should start with '/' character, but for increased
     * readability and to reduce boilerplate this method will return the path without this character at the
     * beginning.
     *
     * @return context of the request, it does not start (or end) with the '/' character.
     */
    @Override
    public String getContextPath()
    {
        return context;
    }

    /**
     * Returns the path of the servlet.
     * </br></br>
     * According to the specification the servlet path should start with '/' character, but for increased
     * readability and to reduce boilerplate this method will return the path without this character at the
     * beginning.
     *
     * @return path of the servlet, it does not start (or end) with the '/' character.
     */
    @Override
    public String getServletPath()
    {
        return servletName;
    }
}
