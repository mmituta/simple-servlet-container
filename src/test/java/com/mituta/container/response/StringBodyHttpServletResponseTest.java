package com.mituta.container.response;

import static org.assertj.core.api.Assertions.*;
import java.io.PrintWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link StringBodyHttpServletResponse} class.
 */
public class StringBodyHttpServletResponseTest
{

    private StringBodyHttpServletResponse response;

    @BeforeEach
    public void setUp()
    {
        response = new StringBodyHttpServletResponse();
    }

    @Test
    public void shouldReturnEmptyStringIfNothingWasWritten()
    {
        assertThat( response.getBody() ).isEmpty();
    }

    @Test
    public void shouldReturnWhatWasWrittenWithPrintLn()
    {
        PrintWriter writer = response.getWriter();
        writer.println( "test" );
        assertThat( response.getBody() ).isEqualTo( "test\r\n" );
    }

    @Test
    public void shouldReturnReturnWhatWasWrittenWithFormat()
    {
        PrintWriter writer = response.getWriter();
        writer.format( "%s test", "body" );
        assertThat( response.getBody() ).isEqualTo( "body test" );
    }

    @Test
    public void shouldReturnWhatWasWrittenWithPrintf()
    {
        PrintWriter writer = response.getWriter();
        writer.printf( "%s %s", "test", "format" );

        assertThat( response.getBody() ).isEqualTo( "test format" );
    }

    @Test
    public void shouldNotReturnWhatWasWrittenWithPrintWithoutFlush()
    {
        PrintWriter writer = response.getWriter();
        writer.print( "no flush" );

        assertThat( response.getBody() ).isEmpty();
    }

    @Test
    public void shouldReturnWhatWasWrittenWithPrintWithFlush()
    {
        PrintWriter writer = response.getWriter();
        writer.print( "flush" );
        writer.flush();

        assertThat( response.getBody() ).isEqualTo( "flush" );
    }
}
