package com.nexsoft.controllers;

import com.nexsoft.models.Authenticator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

public class LoginController extends HttpServlet
{
//    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Authenticator authenticator = new Authenticator();

        if(authenticator.authenticate(username, password))
        {
            ResultSet user = authenticator.getUser();
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            res.sendRedirect("/tugas/success.jsp");
        }
        else
        {
            res.sendRedirect("/tugas/error.jsp");
        }
    }
}
