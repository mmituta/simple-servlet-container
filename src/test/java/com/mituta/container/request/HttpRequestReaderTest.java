package com.mituta.container.request;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link HttpRequestReader} class.
 */
public class HttpRequestReaderTest
{

    private HttpRequestReader reader;

    @BeforeEach
    public void setUp()
    {
        reader = new HttpRequestReader();
    }

    @Test
    public void shouldReadURIFromTheRequest() throws IOException, InvalidServletRequestException
    {
        GetHttpServletRequest request = reader.readRequest( asStream( "GET context/servletPath HTTP/1.1" ) );

        assertThat( request.getContextPath() ).isEqualTo( "context" );
        assertThat( request.getServletPath() ).isEqualTo( "servletPath" );
    }

    @Test
    public void shouldReadURIFromTheRequestIfTheUriStartsWithSlash() throws IOException,
        InvalidServletRequestException
    {
        GetHttpServletRequest request = reader.readRequest( asStream( "GET /first/second HTTP/1.1" ) );

        assertThat( request.getContextPath() ).isEqualTo( "first" );
        assertThat( request.getServletPath() ).isEqualTo( "second" );
    }

    @Test
    public void shouldReadUriFromTheRequestIfTheUriEndsWithSlash() throws IOException,
        InvalidServletRequestException
    {
        GetHttpServletRequest request = reader.readRequest( asStream( "GET ctx/servlet/ HTTP/1.1" ) );

        assertThat( request.getContextPath() ).isEqualTo( "ctx" );
        assertThat( request.getServletPath() ).isEqualTo( "servlet" );
    }

    @Test
    public void shouldThrowInvalidServletRequestExceptionIfThePathIsNotTwoSegments()
    {
        assertThrows( InvalidServletRequestException.class,
                      () -> reader.readRequest( asStream( "GET path HTTP/1.1" ) ) );
    }

    @Test
    public void shouldThrowInvalidServletRequestIfRequestDoesNotMatchHTTPFormat()
    {
        assertThrows( InvalidServletRequestException.class,
                      () -> reader.readRequest( asStream( "GET path" ) ) );
    }

    @Test
    public void shouldReturnMultiSegmentServletPath() throws IOException,
        InvalidServletRequestException
    {
        GetHttpServletRequest request = reader.readRequest( asStream( "GET ctx/servlet/abc HTTP/1.1" ) );
        assertThat( request.getContextPath() ).isEqualTo( "ctx" );
        assertThat( request.getServletPath() ).isEqualTo( "servlet/abc" );
    }

    @Test
    public void shouldIgnoreQueryParameters() throws IOException, InvalidServletRequestException
    {
        GetHttpServletRequest request = reader.readRequest( asStream( "GET ctx/servlet?test=123 HTTP/1.1" ) );
        assertThat( request.getContextPath() ).isEqualTo( "ctx" );
        assertThat( request.getServletPath() ).isEqualTo( "servlet" );
    }

    private InputStream asStream( String request )
    {
        return new ByteArrayInputStream( request.getBytes() );
    }
}
