package com.mituta.container.response;

/**
 * Is responsible for creating a {@code String} that contains basic HTTP response. The body of a response will
 * be provided by the {@link StringBodyHttpServletResponse#getBody()}  method.
 * </br></br>
 * The resulting HTTP response will be created using this template:
 * <pre>
 *     "HTTP 200 OK
 *
 *     {body}
 * </pre>
 * Where the {body} is the result of {@link StringBodyHttpServletResponse#getBody()}  method.
 * The body needs to be separated from the headers by two CRLF, see <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html">documentation</a>.
 */
public class HttpResponseFactory
{
    /**
     * Creates a basic HTTP response with a HTTP 200 status. The body of the response will be taken from the
     * provided {@link StringBodyHttpServletResponse} instance.
     *
     * @param response will be used to get the body of the response.
     * @return a basic response with HTTP 200 status and text body.
     * @see StringBodyHttpServletResponse#getBody() method.
     */
    public String createOkResponse( StringBodyHttpServletResponse response )
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "HTTP 200 OK" );
        builder.append( "\n" );
        builder.append( "\n" );
        builder.append( response.getBody() );
        return builder.toString();
    }
}
