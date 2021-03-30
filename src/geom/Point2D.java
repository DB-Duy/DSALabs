/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

import java.awt.*;
import java.util.Random;

/**
 * @author LTSACH
 */
public class Point2D extends geomObject {
    static int POINT_HALF_SIZE=2;

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    private double x;
    private double y;

    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void draw(Graphics g, SpaceMapping mapper) {

        Graphics2D g2 = (Graphics2D) g;
         Point2D point = mapper.logic2Device(this.getX(), this.getY() );

         int x = (int)point.getX() - POINT_HALF_SIZE;
         int y = (int)point.getY() - POINT_HALF_SIZE;
         int i=2*POINT_HALF_SIZE+1;
         g2.setColor(this.faceColor);
         //g2.fillRect(x,y,x+i,y+i);
        g2.fillOval(x,y,x+i,y+i);
    }

    public static Point2D[] linear(int N, double a, double b, double xMin,
                                   double xMax) {
        Point2D[] list = new Point2D[N];
        double step = (xMax - xMin) / (N - 1); //xMax inclusive
        double x = xMin;
        double y;
        for (int idx = 0; idx < N; idx++) {
            y=a*x+b;
            x += step;
            list[idx].setX(x);
            list[idx].setY(y);
        }
        return list;
    }


    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D oldPoint) {
        this.x = oldPoint.x;
        this.y = oldPoint.y;
    }

    public Point2D clone() {
        return new Point2D(this.x, this.y);
    }

    public String toString() {
        return String.format("P(%6.2f, %6.2f)", this.x, this.y);
    }

    public static Point2D[] generate(int N, double min, double max) {
        Random generator = new Random();
        Point2D[] list = new Point2D[N];
        for (int idx = 0; idx < N; idx++) {
            double x = min + generator.nextDouble() * (max - min);
            double y = min + generator.nextDouble() * (max - min);
            list[idx] = new Point2D(x, y);
        }
        return list;
    }

    public static double distanceAB(Point2D a, Point2D b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double distanceTo(Point2D point) {
        double dx = this.getX() - point.getX();
        double dy = this.getY() - point.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
