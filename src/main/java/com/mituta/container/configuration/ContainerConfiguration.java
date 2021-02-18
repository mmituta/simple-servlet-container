package com.mituta.container.configuration;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.mituta.container.SimpleServletContainer;

/**
 * Represents the configuration of {@link SimpleServletContainer}.
 */
@XmlRootElement( name = "web-app" )
public class ContainerConfiguration
{
    private String context;
    private Collection< ServletConfig > servlets;

    /**
     * Gets the name of the servlet class based on the provided logical name.
     *
     * @param servletLogicalName name of the servlet.
     * @return name of the servlet class.
     */
    public String getServletClass( String servletLogicalName ) throws ServletNotFoundException
    {
        if( servlets == null )
        {
            throw new ServletNotFoundException( servletLogicalName );
        }
        return servlets.stream()
            .filter( servlet -> servlet.getName().equals( servletLogicalName ) )
            .findFirst().map( ServletConfig::getClassName )
            .orElseThrow( () -> new ServletNotFoundException( servletLogicalName ) );
    }

    @XmlElement
    public String getContext()
    {
        return context;
    }

    public void setContext( String aContext )
    {
        context = aContext;
    }

    @XmlElement( name = "servlet" )
    public Collection< ServletConfig > getServlets()
    {
        return servlets;
    }

    public void setServlets( Collection< ServletConfig > aServlets )
    {
        servlets = aServlets;
    }

    @Override
    public String toString()
    {
        return "ContainerConfiguration{" +
            "context='" + context + '\'' +
            ", servlets=" + servlets +
            '}';
    }


}
