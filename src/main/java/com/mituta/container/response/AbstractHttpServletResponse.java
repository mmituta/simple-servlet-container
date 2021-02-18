package com.mituta.container.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * An abstract class implementing {@link HttpServletResponse} interface. It can be used as a base class to
 * improve readability of classes that don't need to implement all of the {@link HttpServletResponse }
 * methods. <br/> Note that the the method implemented by {@link AbstractHttpServletResponse} are stubs that
 * don't return any values. Using those implementation may lead to {@code NullPointerException}.
 */
public abstract class AbstractHttpServletResponse implements HttpServletResponse
{
    @Override
    public void addCookie( Cookie aCookie )
    {

    }

    @Override
    public boolean containsHeader( String aS )
    {
        return false;
    }

    @Override
    public String encodeURL( String aS )
    {
        return null;
    }

    @Override
    public String encodeRedirectURL( String aS )
    {
        return null;
    }

    @Override
    public String encodeUrl( String aS )
    {
        return null;
    }

    @Override
    public String encodeRedirectUrl( String aS )
    {
        return null;
    }

    @Override
    public void sendError( int aI, String aS ) throws IOException
    {

    }

    @Override
    public void sendError( int aI ) throws IOException
    {

    }

    @Override
    public void sendRedirect( String aS ) throws IOException
    {

    }

    @Override
    public void setDateHeader( String aS, long aL )
    {

    }

    @Override
    public void addDateHeader( String aS, long aL )
    {

    }

    @Override
    public void setHeader( String aS, String aS1 )
    {

    }

    @Override
    public void addHeader( String aS, String aS1 )
    {

    }

    @Override
    public void setIntHeader( String aS, int aI )
    {

    }

    @Override
    public void addIntHeader( String aS, int aI )
    {

    }

    @Override
    public void setStatus( int aI )
    {

    }

    @Override
    public void setStatus( int aI, String aS )
    {

    }

    @Override
    public int getStatus()
    {
        return 0;
    }

    @Override
    public String getHeader( String aS )
    {
        return null;
    }

    @Override
    public Collection< String > getHeaders( String aS )
    {
        return null;
    }

    @Override
    public Collection< String > getHeaderNames()
    {
        return null;
    }

    @Override
    public String getCharacterEncoding()
    {
        return null;
    }

    @Override
    public String getContentType()
    {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException
    {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException
    {
        return null;
    }

    @Override
    public void setCharacterEncoding( String aS )
    {

    }

    @Override
    public void setContentLength( int aI )
    {

    }

    @Override
    public void setContentLengthLong( long aL )
    {

    }

    @Override
    public void setContentType( String aS )
    {

    }

    @Override
    public void setBufferSize( int aI )
    {

    }

    @Override
    public int getBufferSize()
    {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException
    {

    }

    @Override
    public void resetBuffer()
    {

    }

    @Override
    public boolean isCommitted()
    {
        return false;
    }

    @Override
    public void reset()
    {

    }

    @Override
    public void setLocale( Locale aLocale )
    {

    }

    @Override
    public Locale getLocale()
    {
        return null;
    }
}
