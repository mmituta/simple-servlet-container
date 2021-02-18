package com.mituta.container.request;

/**
 * Represents an exception where the request URI does not match the expected format.
 *
 * @see HttpRequestReader
 */
public class InvalidServletRequestException extends Exception
{
    private final String request;

    /**
     * Creates a new instance of {@link InvalidServletRequestException}.
     *
     * @param requestPath path that caused the exception.
     */
    public InvalidServletRequestException( String requestPath )
    {
        this.request = requestPath;
    }


    /**
     * Returns the path that caused the exception to be raised.
     *
     * @return path that raised the exception.
     */
    public String getRequestPath()
    {
        return request;
    }
}
