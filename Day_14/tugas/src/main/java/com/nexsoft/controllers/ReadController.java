package com.nexsoft.controllers;

import com.nexsoft.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

public class ReadController extends HttpServlet {

    public ReadController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        User userModel = new User();

        ResultSet users = userModel.getUsers();

        req.setAttribute("users", users);
        RequestDispatcher rd = req.getRequestDispatcher("/menu.jsp");

        rd.forward(req, res);
    }
}
