package com.mituta.container.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URISyntaxException;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link ContainerConfigurationReader} class.
 */
public class ContainerConfigurationReaderTest
{

    private ContainerConfigurationReader reader;

    @BeforeEach
    public void setUp()
    {
        reader = new ContainerConfigurationReader();

    }

    @Test
    public void shouldReadConfigurationFromTheFileWithNoServlets() throws JAXBException, URISyntaxException
    {
        ContainerConfiguration config = reader.readFromResources( "no-servlets-web.xml" );

        assertThat( config.getContext() ).isEqualTo( "no-servlets-context" );
    }

    @Test
    public void shouldReadConfigurationThatIncludesServlet() throws JAXBException, URISyntaxException
    {
        ContainerConfiguration config = reader.readFromResources( "single-servlet-web.xml" );

        ServletConfig expectedConfig = new ServletConfig( "servlet-name", "servlet-class" );
        assertThat( config.getServlets() ).contains( expectedConfig );
    }

    @Test
    public void shouldReadConfigurationThatIncludesMultipleServlets() throws JAXBException, URISyntaxException
    {
        ContainerConfiguration config = reader.readFromResources( "multiple-servlets-web.xml" );

        ServletConfig firstConfig = new ServletConfig( "first-servlet-name", "first-servlet-class" );
        ServletConfig second = new ServletConfig( "second-servlet-name", "second-servlet-class" );
        assertThat( config.getServlets() ).contains( firstConfig, second );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfFileDoesNotExist()
    {
        assertThrows( IllegalArgumentException.class, () -> reader.readFromResources( "not-existing" ) );
    }
}
