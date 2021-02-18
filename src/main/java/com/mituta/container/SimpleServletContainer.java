package com.mituta.container;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import com.mituta.container.configuration.ContainerConfigurationReader;
import com.mituta.container.configuration.ContainerConfiguration;
import com.mituta.container.configuration.ServletNotFoundException;
import com.mituta.container.request.InvalidServletRequestException;
import com.mituta.container.request.HttpRequestReader;
import com.mituta.container.response.HttpResponseFactory;
import com.mituta.container.response.ResponseWriter;
import com.mituta.container.response.StringBodyHttpServletResponse;
import com.mituta.container.servlets.ServletFactory;

/**
 * Entry class of the application. It starts the servlet container and listens for incoming connections.
 */
public class SimpleServletContainer
{

    private final HttpRequestReader requestReader;
    private final ResponseWriter responseWriter;
    private final ServletFactory servletFactory;
    private final ContainerConfigurationReader configReader;
    public static final int PORT_NUMBER = 8080;

    public static void main( String... args ) throws JAXBException, URISyntaxException
    {
        SimpleServletContainer container = new SimpleServletContainer();
        container.start();
    }

    /**
     * Creates a new instance of {@link SimpleServletContainer} and initialized all of the dependencies with
     * default values.
     */
    public SimpleServletContainer()
    {
        configReader = new ContainerConfigurationReader();
        requestReader = new HttpRequestReader();
        responseWriter = new ResponseWriter( new HttpResponseFactory() );
        servletFactory = new ServletFactory();
    }


    /**
     * Starts the container. It reads the container configuration, creates the socket and starts listening for
     * incoming connections.
     *
     * @throws JAXBException
     * @throws URISyntaxException
     */
    public void start() throws JAXBException, URISyntaxException
    {
        startListening( createSocket(), configReader.readFromResources( "web.xml" ) );
    }

    private void startListening( ServerSocket serverSocket, ContainerConfiguration config )
    {
        while( true )
        {
            Socket socket = null;
            try
            {
                socket = serverSocket.accept();
                HttpServletRequest request = requestReader.readRequest( socket.getInputStream() );

                if( isServletRequest( config, request ) )
                {
                    StringBodyHttpServletResponse response = getResponseFromServlet( config, request );
                    responseWriter.writeResponse( socket.getOutputStream(), response );
                }
                else
                {
                    System.err.printf( "Context '%s' is not a servlet context, expected context is '%s'%n",
                                       request.getContextPath(), config.getContext() );
                }
            }
            catch( InvalidServletRequestException e )
            {
                System.err.println( "Incorrect servlet request:" + e.getRequestPath() );
            }
            catch( ServletNotFoundException e )
            {
                System.err.println(
                    "Servlet with the given name does not exist in configuration:" + e.getServletName() );
            }
            catch( IllegalAccessException | InstantiationException | InvocationTargetException | ServletException | ClassNotFoundException | NoSuchMethodException | IOException aE )
            {
                aE.printStackTrace();
            }

            finally
            {
                closeSocket( socket );
            }
        }
    }

    private StringBodyHttpServletResponse getResponseFromServlet( ContainerConfiguration aAConfig,
                                                                  HttpServletRequest aRequest ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ServletException, IOException, ServletNotFoundException
    {
        HttpServlet servlet = servletFactory.createServlet( aRequest, aAConfig );
        StringBodyHttpServletResponse response = new StringBodyHttpServletResponse();
        servlet.service( aRequest, response );
        return response;
    }

    private boolean isServletRequest( ContainerConfiguration aConfig, HttpServletRequest aRequest )
    {
        String requestContextPath = aRequest.getContextPath();
        return requestContextPath.equals( aConfig.getContext() );
    }

    private ServerSocket createSocket()
    {
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket( PORT_NUMBER, 1, InetAddress.getByName( "127.0.0.1" ) );
        }
        catch( IOException e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
        return serverSocket;
    }

    private void closeSocket( Socket aSocket )
    {
        if( aSocket != null )
        {
            try
            {
                aSocket.close();
            }
            catch( IOException aE )
            {
                aE.printStackTrace();
            }
        }
    }


}
