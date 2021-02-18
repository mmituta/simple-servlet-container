package com.mituta.container.configuration;

/**
 * Exception that is thrown when the servlet with specified logical name does not exist in the configuration.
 */
public class ServletNotFoundException extends Exception
{
    private final String servletName;

    /**
     * Creates a new instance of {@link ServletNotFoundException} that was thrown for the specified name.
     *
     * @param servletName name of the servlet that was not found.
     */
    public ServletNotFoundException( String servletName )
    {
        this.servletName = servletName;
    }

    /**
     * Gets the name of the servlet that was not found.
     *
     * @return name of the servlet.
     */
    public String getServletName()
    {
        return servletName;
    }
}
