package com.mituta.container.response;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for the {@link HttpResponseFactory} class.
 */
@ExtendWith( MockitoExtension.class )
public class HttpResponseFactoryTest
{
    @Mock
    private StringBodyHttpServletResponse response;

    private HttpResponseFactory factory;

    @BeforeEach
    public void setUpFactory()
    {
        factory = new HttpResponseFactory();
    }

    @Test
    public void shouldCreateResponseWithHttp200StatusCode()
    {
        String httpResponse = factory.createOkResponse( response );
        assertThat( httpResponse ).startsWith( "HTTP 200 OK\n" );
    }

    @Test
    public void shouldCreateResponseThatEndsWithTheMessageBody()
    {
        String body = "I am the message's body";
        when( response.getBody() ).thenReturn( body );

        String httpResponse = factory.createOkResponse( response );
        assertThat( httpResponse ).endsWith( body );

    }

    @Test
    public void shouldIncludeTwoNewLinesBeforeTheMessageBody()
    {
        String body = "body";
        when( response.getBody() ).thenReturn( body );
        String httpResponse = factory.createOkResponse( response );
        assertThat( httpResponse ).matches( ".+\n\n" + body );
    }


}
