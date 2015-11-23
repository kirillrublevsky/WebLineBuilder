package com.kirillrublevsky.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class PointSorter {

    public static List<Point> sort(List<Point> points) {
        List<Point> sortedPoints = new ArrayList<Point>();
        Point pointB = findCenter(points);
        Point pointA = findFirstPoint(pointB, points);

        Point nextPoint;
        int iterations = points.size() - 1;

        for (int i = 0; i < iterations; i++) {
            sortedPoints.add(pointA);
            points.remove(pointA);
            nextPoint = findNextPoint(points, pointA, pointB);
            pointB = pointA;
            pointA = nextPoint;
        }
        sortedPoints.add(points.get(0));
        return sortedPoints;
    }

    private static Point findCenter(List<Point> points) {
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        int currentX;
        int currentY;
        for (Point element : points) {
            currentX = element.x;
            currentY = element.y;
            if (currentX < minX) {
                minX = currentX;
            }
            if (currentX > maxX) {
                maxX = currentX;
            }
            if (currentY < minY) {
                minY = currentY;
            }
            if (currentY > maxY) {
                maxY = currentY;
            }
        }
        return new Point((minX + maxX) / 2, (minY + maxY) / 2);
    }

    private static Point findFirstPoint(Point center, List<Point> points) {
        Point firstPoint = null;
        double maxDistance = 0;
        double currentDistance;

        for (Point point : points) {
            currentDistance = findDistance(point, center);
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                firstPoint = point;
            }
        }
        return firstPoint;
    }

    private static double findDistance(Point one, Point two) {
        int differenceX = one.x - two.x;
        int differenceY = one.y - two.y;
        return sqrt(pow(differenceX, 2) + pow(differenceY, 2));
    }

    private static Point findNextPoint(List<Point> points, Point pointA, Point pointB) {
        Point nextPoint = null;
        double smallestAngle = 360;
        double smallestDistance = Double.POSITIVE_INFINITY;
        double currentAngle;
        double currentDistance;

        for (Point point : points) {
            currentAngle = findOuterAngle(pointA, pointB, point);
            currentDistance = findDistance(pointA, point);
            if (currentAngle < smallestAngle) {
                smallestAngle = currentAngle;
                smallestDistance = currentDistance;
                nextPoint = point;
            } else if (currentAngle == smallestAngle) {
                if (currentDistance < smallestDistance) {
                    smallestDistance = currentDistance;
                    nextPoint = point;
                }
            }
        }
        return nextPoint;
    }

    private static double findOuterAngle(Point pointA, Point pointB, Point pointC) {
        int xAB = pointB.x - pointA.x;
        int yAB = pointB.y - pointA.y;
        int xAC = pointC.x - pointA.x;
        int yAC = pointC.y - pointA.y;

        double scalarMultiplication = xAB * xAC + yAB * yAC;
        double modulusAB = sqrt(pow(xAB, 2) + pow(yAB, 2));
        double modulusAC = sqrt(pow(xAC, 2) + pow(yAC, 2));
        double cos = scalarMultiplication / (modulusAB * modulusAC);
        if (cos < -1) {
            cos = -1;
        } else if (cos > 1) {
            cos = 1;
        }
        return 360 - toDegrees(acos(cos));
    }
}