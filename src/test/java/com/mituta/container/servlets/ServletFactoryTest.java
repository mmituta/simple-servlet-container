package com.mituta.container.servlets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.mituta.container.configuration.ContainerConfiguration;
import com.mituta.container.configuration.ServletNotFoundException;
import com.mituta.container.request.GetHttpServletRequest;

/**
 * Tests for the {@link ServletFactory}.
 */
@ExtendWith( MockitoExtension.class )
public class ServletFactoryTest
{
    @Mock
    private ContainerConfiguration configuration;
    private ServletFactory factory;

    @BeforeEach
    public void setUpFactory()
    {
        factory = new ServletFactory();
    }

    @Test
    public void shouldCreateServletBasedOnRequest() throws InvocationTargetException,
        ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, ServletNotFoundException
    {
        GetHttpServletRequest request = setUpRequest( "servlet", TestServlet.class );

        assertThat( factory.createServlet( request, configuration ) ).isInstanceOf( TestServlet.class );
    }

    @Test
    public void shouldThrowNoSuchMethodWhenServletDoesNotHaveNoArgsConstructor() throws ServletNotFoundException
    {
        GetHttpServletRequest request = setUpRequest( "servlet", NoEmptyConstructorServlet.class );

        assertThrows( NoSuchMethodException.class, () -> factory.createServlet( request, configuration ) );
    }

    @Test
    public void shouldThrowClassNotFoudExceptionIfTheClassDoesNotExist() throws ServletNotFoundException
    {

        GetHttpServletRequest request = new GetHttpServletRequest( "context", "notExisting" );

        when( configuration.getServletClass( "notExisting" ) ).thenReturn( "NotExistingClass" );
        assertThrows( ClassNotFoundException.class, () -> factory.createServlet( request, configuration ) );

    }

    private GetHttpServletRequest setUpRequest( String servletName,
                                                Class< ? extends HttpServlet > servletClass ) throws ServletNotFoundException
    {
        GetHttpServletRequest request = new GetHttpServletRequest( "context", servletName );

        String className = servletClass.getName();
        when( configuration.getServletClass( servletName ) ).thenReturn( className );
        return request;
    }
}
