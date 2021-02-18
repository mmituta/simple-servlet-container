package com.mituta.container.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is responsible for parsing HTTP request. Example of a HTTP request:
 * <pre>
 *     GET {URI} HTTP/1.1
 * </pre>
 * where {URI} is the requested path. This reader only reads the URI part of the request, ignoring the rest of
 * information.
 */
public class HttpRequestReader
{
    private static final String PATH_SEPARATOR = "/";
    private static final String WHITE_SPACE = " ";
    private static final int HTTP_REQUEST_LINE_SEGMENTS = 3;

    /**
     * Reads the request from the specified {@link InputStream}. It expects that the request contains a URI in
     * a following format:
     * <pre>
     *      {context}/{servletPath}
     * </pre>
     * If the format of the URI request differs from the specified format, an {@link
     * InvalidServletRequestException} will be thrown.
     *
     * @param inputStream the request will be read from.
     * @return an instance of {@link GetHttpServletRequest} that represents the read request.
     * @throws IOException
     * @throws InvalidServletRequestException will be thrown when the path read from the request does not
     * match the expected format.
     */
    public GetHttpServletRequest readRequest(
        InputStream inputStream ) throws IOException, InvalidServletRequestException
    {
        String[] pathSegments = getPathSegments( inputStream );

        String context = pathSegments[0];
        String servletPath = pathSegments[1];
        GetHttpServletRequest request = new GetHttpServletRequest( context, stripQueryParam( servletPath ) );
        logRequest( request );
        return request;
    }

    private String stripQueryParam( String servletPath )
    {
        return servletPath.replaceAll( "\\?.+", "" );
    }

    private String[] getPathSegments(
        InputStream aInputStream ) throws IOException, InvalidServletRequestException
    {
        String uri = getUriPartOfRequest( readRequestLine( aInputStream ) );
        if( !uri.contains( PATH_SEPARATOR ) )
        {
            throw new InvalidServletRequestException( uri );
        }
        return uri.split( PATH_SEPARATOR, 2 );
    }

    private String getUriPartOfRequest( String line ) throws InvalidServletRequestException
    {
        String[] requestSegments = line.split( WHITE_SPACE );
        if( requestSegments.length < HTTP_REQUEST_LINE_SEGMENTS )
        {
            throw new InvalidServletRequestException( line );
        }
        return trimSlashes( requestSegments[1] );
    }

    private String trimSlashes( String uriSegment )
    {
        return uriSegment.replaceAll( "^/|/$", "" );

    }

    private String readRequestLine( InputStream input ) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( input ) );
        return bufferedReader.readLine();
    }

    private void logRequest( GetHttpServletRequest aRequest )
    {
        System.out.println( "Received request: " );
        System.out.println( "Context: " + aRequest.getContextPath() );
        System.out.println( "Servlet: " + aRequest.getServletPath() );
    }

}
