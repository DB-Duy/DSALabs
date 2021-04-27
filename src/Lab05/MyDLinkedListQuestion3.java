package Lab05;

import geom.Point2D;

import java.util.List;
import java.util.ListIterator;

public class MyDLinkedListQuestion3 {
    public static void main(String[] args) {
        Point2D[] array = Point2D.generate(10, 0, 10);
        Point2D point = new Point2D(0, 0);
        List<Point2D> list = new DLinkedList<>();
        System.out.println("Before remove:");
        for (int i = 0; i < array.length; i++) {  // sets Y coordinate to 0 for easier checking
            array[i].setY(0);
            System.out.println(array[i]);
            list.add(array[i]);
        }

        removeHittedPoints(list, point, 4.0);   // change radius here

        System.out.println();
        System.out.println("After remove: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void removeHittedPoints(List<Point2D> list, Point2D testPoint, double radius) {
        ListIterator<Point2D> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Point2D p = iterator.next();
            double distance = Point2D.distanceAB(p, testPoint);
            if (distance < radius) {
                iterator.remove();
            }
        }
    }
}
