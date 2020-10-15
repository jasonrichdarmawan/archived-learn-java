package com.nexsoft.controllers;

import com.nexsoft.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeleteController extends HttpServlet {

    public DeleteController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));

        User userModel = new User();

        Map where = new HashMap();
        where.put("id", id);

        try {
            userModel.delete("users", where);

            res.sendRedirect("/tugas/successAction.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
