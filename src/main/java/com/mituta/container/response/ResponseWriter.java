package com.mituta.container.response;

import static java.util.Objects.*;
import java.io.OutputStream;
import java.io.PrintWriter;
/**
 * Is responsible for writing the provided {@link StringBodyHttpServletResponse} o a specified output stream.
 */
public class ResponseWriter
{
    private HttpResponseFactory httpResponseFactory;

    /**
     * Creates a new instance of {@link ResponseWriter} that will use the specified {@link
     * HttpResponseFactory} for creating responses.
     *
     * @param httpResponseFactory will be used for creating correct http responses based on the {@link
     * StringBodyHttpServletResponse} instance.
     */
    public ResponseWriter( HttpResponseFactory httpResponseFactory )
    {
        requireNonNull( httpResponseFactory, "Response factory can not be null." );
        this.httpResponseFactory = httpResponseFactory;
    }

    /**
     * Writes a HTTP response to the specified output stream. The provided {@link StringBodyHttpServletResponse}
     * does not contain necessary HTTP header information, it only contains the body of the response. That is
     * why the {@link #httpResponseFactory} will be used for creating a correct HTTP response with the body
     * provided by {@link StringBodyHttpServletResponse}.
     *
     * @param outputStream stream to which the response will be written.
     * @param response response
     */
    public void writeResponse( OutputStream outputStream, StringBodyHttpServletResponse response )
    {
        PrintWriter writer = new PrintWriter( outputStream, true );
        writer.printf( httpResponseFactory.createOkResponse( response ) );
    }
}
