package com.mituta.container.servlets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import com.mituta.container.configuration.ContainerConfiguration;
import com.mituta.container.configuration.ServletNotFoundException;

/**
 * Is responsible for initializing servlets based on the container configuration and incoming requests.
 */
public class ServletFactory
{
    /**
     * Initialises the servlet instance based on the container configuration and the provided {@link
     * HttpServletRequest}.
     *
     * @param request it will be used to decide which servlet should be initialized.
     * @param config it will provide the servlet class information, necessary for initialization.
     * @return an initialized implementation of {@link HttpServlet} which type depends on the request and
     * configuration.
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws ServletNotFoundException thrown when the servlet with the specified name was not found in the
     * configuration.
     */
    public HttpServlet createServlet( HttpServletRequest request,
                                      ContainerConfiguration config ) throws IllegalAccessException,
        InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException,
        ServletNotFoundException
    {
        Class< HttpServlet > clazz =
            (Class< HttpServlet >)Class.forName( config.getServletClass( request.getServletPath() ) );
        Constructor< HttpServlet > declaredConstructor = clazz.getDeclaredConstructor();
        return declaredConstructor.newInstance();
    }
}
