package com.kirillrublevsky.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.kirillrublevsky.service.PointSorter.sort;

@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {

    List<Point> points = new ArrayList<Point>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!points.isEmpty()) {
            points = sort(points);
        }
        String json = new Gson().toJson(points);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        Integer x = gson.fromJson(request.getParameter("x"), Integer.class);
        Integer y = gson.fromJson(request.getParameter("y"), Integer.class);
        points.add(new Point(x, y));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        points.clear();
    }
}
