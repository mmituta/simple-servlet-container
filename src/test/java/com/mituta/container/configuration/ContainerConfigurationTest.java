package com.mituta.container.configuration;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link ContainerConfiguration} class.
 */
public class ContainerConfigurationTest
{
    private ContainerConfiguration configuration;

    @BeforeEach
    public void setUp()
    {
        configuration = new ContainerConfiguration();
    }

    @Test
    public void shouldGetClassNameForServletName() throws ServletNotFoundException
    {
        configuration.setServlets( asList( servlet( "name", "class" ) ) );

        assertThat( configuration.getServletClass( "name" ) ).isEqualTo( "class" );
    }

    @Test
    public void shouldThrowServletNotFoundExceptionIfNoServletWasRead()
    {
        assertThrows( ServletNotFoundException.class, () -> configuration.getServletClass( "name" ) );
    }

    @Test
    public void shouldThrowServletNotFoundExceptionIfServletWithSuchNameDoesNotExist()
    {
        configuration.setServlets( asList( servlet( "a", "a" ) ) );
        assertThrows( ServletNotFoundException.class, () -> configuration.getServletClass( "b" ) );
    }

    @Test
    public void shouldGetCorrectClassNameWhenMultipleServletsWereRead() throws ServletNotFoundException
    {
        configuration.setServlets( asList( servlet( "a", "a" ), servlet( "b", "b" ) ) );

        assertThat( configuration.getServletClass( "a" ) ).isEqualTo( "a" );
        assertThat( configuration.getServletClass( "b" ) ).isEqualTo( "b" );
    }

    private ServletConfig servlet( String name, String className )
    {
        return new ServletConfig( name, className );
    }
}
