package com.kirillrublevsky.service;

import java.awt.Point;
import java.util.*;

public interface JdbcService {

    void addPoint(Point point);

    void deleteAll();

    List<Point> findAll();
}
