package com.kirillrublevsky.servlet;

import com.google.gson.Gson;
import com.kirillrublevsky.service.JdbcService;
import com.kirillrublevsky.service.impl.JdbcServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {

    private JdbcService jdbcService = new JdbcServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = new Gson().toJson(jdbcService.findAll());
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        Integer x = gson.fromJson(request.getParameter("x"), Integer.class);
        Integer y = gson.fromJson(request.getParameter("y"), Integer.class);
        jdbcService.addPoint(new Point(x, y));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        jdbcService.deleteAll();
    }
}
