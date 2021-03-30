package Lab03.sorting;

import geom.Point2D;

import java.util.Comparator;

public class M2PointComparator implements Comparator<Point2D> {
    private Point2D center;
    public M2PointComparator(Point2D center){
        this.center=center;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    @Override
    public int compare(Point2D o1, Point2D o2) {
        if (Math.abs(Point2D.distanceAB(o1,o2)) < 1e-7) return 0;
        else if (Math.abs(Point2D.distanceAB(o1,center)) < Math.abs(Point2D.distanceAB(o2,center))) return -1;
        else return 1;
    }

}