package com.mituta.container.configuration;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents the Servlet configuration.
 */
public class ServletConfig
{
    private String name;
    private String className;

    /**
     * No arguments constructor is required by the JAXB.
     */
    public ServletConfig()
    {
        this( null, null );
    }

    /**
     * Initializes the {@link ServletConfig} class with the specified servlet name and class name. It is used
     * for test purposes.
     *
     * @param servletName logical name of the servlet.
     * @param className name of the servlet class.
     */
    ServletConfig( String servletName, String className )
    {
        this.name = servletName;
        this.className = className;
    }

    @XmlElement( name = "servlet-name" )
    public String getName()
    {
        return name;
    }

    public void setName( String aName )
    {
        name = aName;
    }

    @XmlElement( name = "servlet-class" )
    public String getClassName()
    {
        return className;
    }

    public void setClassName( String aClassName )
    {
        className = aClassName;
    }

    @Override
    public String toString()
    {
        return "ServletConfig{" +
            "name='" + name + '\'' +
            ", className='" + className + '\'' +
            '}';
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o )
        {
            return true;
        }
        if( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        ServletConfig that = (ServletConfig)o;
        return Objects.equals( name, that.name ) &&
            Objects.equals( className, that.className );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( name, className );
    }
}
