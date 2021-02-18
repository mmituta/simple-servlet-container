package com.mituta.container.response;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for the {@link ResponseWriter} class.
 */
@ExtendWith( MockitoExtension.class )
public class ResponseWriterTest
{

    @Mock
    private HttpResponseFactory responseFactory;
    private ResponseWriter writer;
    private StringBodyHttpServletResponse request;
    private ByteArrayOutputStream outputStream;


    @BeforeEach
    public void setUpWriter()
    {
        writer = new ResponseWriter( responseFactory );

        outputStream = new ByteArrayOutputStream();
        request = new StringBodyHttpServletResponse();
    }

    @Test
    public void shouldWriteResponseToStream()
    {
        when( responseFactory.createOkResponse( request ) ).thenReturn( "hello world" );
        writer.writeResponse( outputStream, request );

        assertThat( outputStream.toString() ).isEqualTo( "hello world" );
    }

    @Test
    public void shouldThrowNullPointerExceptionIfTheFactoryIsNull()
    {
        assertThrows( NullPointerException.class, () -> new ResponseWriter( null ) );
    }
}
