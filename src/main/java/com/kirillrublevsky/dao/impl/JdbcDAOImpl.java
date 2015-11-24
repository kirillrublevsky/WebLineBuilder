package com.kirillrublevsky.dao.impl;

import com.kirillrublevsky.dao.JdbcDAO;

import java.awt.Point;
import java.sql.*;
import java.util.*;

public class JdbcDAOImpl implements JdbcDAO {

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:point_db;DB_CLOSE_DELAY=-1";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS point";
    private static final String CREATE_TABLE = "CREATE TABLE point (x INT NOT NULL, y INT NOT NULL)";

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;

    {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            statement.execute(DROP_TABLE);
            statement.execute(CREATE_TABLE);
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void addPoint(Point point) {
        String sql = "INSERT INTO point(x, y) VALUES (?, ?)";

        try {
            connection = DriverManager.getConnection(URL);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, point.x);
            preparedStatement.setInt(2, point.y);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM point";

        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Point> findAll() {
        String sql = "SELECT * FROM point";
        List<Point> points = new ArrayList<Point>();

        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            Point point = null;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                point = new Point(resultSet.getInt("x"), resultSet.getInt("y"));
                points.add(point);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return points;
    }
}
