package com.kirillrublevsky.service.impl;

import com.kirillrublevsky.dao.JdbcDAO;
import com.kirillrublevsky.dao.impl.JdbcDAOImpl;
import com.kirillrublevsky.service.JdbcService;

import java.awt.*;
import java.util.List;

import static com.kirillrublevsky.service.PointSorter.sort;

public class JdbcServiceImpl implements JdbcService {

    private JdbcDAO jdbcDAO = new JdbcDAOImpl();

    @Override
    public void addPoint(Point point) {
        jdbcDAO.addPoint(point);
    }

    @Override
    public void deleteAll() {
        jdbcDAO.deleteAll();
    }

    @Override
    public List<Point> findAll() {
        List<Point> points = jdbcDAO.findAll();
        if (!points.isEmpty()) {
            points = sort(points);
        }
        return points;
    }
}
