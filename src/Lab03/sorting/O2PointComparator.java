package Lab03.sorting;

import geom.Point2D;

import java.util.Comparator;

public class O2PointComparator implements Comparator<Point2D> {
    @Override
    public int compare(Point2D o1, Point2D o2) {
        Point2D origin = new Point2D(0,0);
        if (Math.abs(Point2D.distanceAB(o1,o2)) < 1e-7) return 0;
        else if (Math.abs(Point2D.distanceAB(o1,origin)) < Math.abs(Point2D.distanceAB(o2,origin))) return -1;
        else return 1;
    }
}