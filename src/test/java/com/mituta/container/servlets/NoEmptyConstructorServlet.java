package com.mituta.container.servlets;

import javax.servlet.http.HttpServlet;

/**
 * Test implemetation of {@link HttpServlet} that does not have a no-args constructor.
 *
 * @author created:  mmituta on 17.02.2021 09:23
 */
public class NoEmptyConstructorServlet extends HttpServlet
{
    public NoEmptyConstructorServlet(String someArg){

    }
}
