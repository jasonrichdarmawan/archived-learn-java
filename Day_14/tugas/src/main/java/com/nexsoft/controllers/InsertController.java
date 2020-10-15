package com.nexsoft.controllers;

import com.nexsoft.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InsertController extends HttpServlet {

    public InsertController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        RequestDispatcher rd = req.getRequestDispatcher("/insert.jsp");

        rd.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        String status = req.getParameter("status");
        int physics = Integer.parseInt(req.getParameter("physics"));
        int calculus = Integer.parseInt(req.getParameter("calculus"));
        int biologi = Integer.parseInt(req.getParameter("biologi"));

        User userModel = new User();
        Map data = new HashMap();
        data.put("username", username);
        data.put("fullname", fullname);
        data.put("address", address);
        data.put("status", status);
        data.put("physics", physics);
        data.put("calculus", calculus);
        data.put("biologi", biologi);

        try {
            userModel.insert("users", data);

            res.sendRedirect("/tugas/successAction.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}