package com.mituta.container.response;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link HttpServletResponse} that  allows to acquire the content written by the servlet in
 * form of a {@code String}.
 * </br>
 * Notice that the {@link #writer} is constructed with autoFlush property equal {@code true}. It means that
 * the information written to it by the servlet will be auto flushed if it was done using following methods:
 * <ul>
 *     <li>println</li>
 *     <li>printf</li>
 *     <li>format</li>
 * </ul>
 * If any other method is used, the servlet needs to be responsible for calling {@link PrintWriter#flush()}.
 */
public class StringBodyHttpServletResponse extends AbstractHttpServletResponse
{
    private final ByteArrayOutputStream bodyOutputStream;
    private PrintWriter writer;

    /**
     * Creates a new instance of {@link StringBodyHttpServletResponse}. It will initialize the {@link
     * ByteArrayOutputStream} and a {@link PrintWriter} that writes to this stream.
     * </br>
     * Initialized {@link PrintWriter} will be returned by the {@link #getWriter()} method for the servlet to
     * write with.
     */
    public StringBodyHttpServletResponse()
    {
        bodyOutputStream = new ByteArrayOutputStream();
        this.writer = new PrintWriter( bodyOutputStream, true );
    }

    /**
     * Returns the body of the response, that was written using the {@link #writer} instance.
     *
     * @return {@code String} that represents the body written using the {@link #writer} instance. If nothing
     * was written, then will return an empty {@code String}.
     */
    public String getBody()
    {
        return bodyOutputStream.toString();
    }


    /**
     * Returns the writer that should be used to writing the response by the servlet.
     *
     * @return print writer instance.
     */
    @Override
    public PrintWriter getWriter()
    {
        return writer;
    }

}
