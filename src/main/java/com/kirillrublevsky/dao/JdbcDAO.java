package com.kirillrublevsky.dao;

import java.awt.Point;
import java.util.List;

public interface JdbcDAO {

    void addPoint(Point point);

    void deleteAll();

    List<Point> findAll();
}
