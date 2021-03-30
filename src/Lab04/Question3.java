package Lab04;

import geom.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Question3 {
    public static void main(String[] args) {
        Point2D[] array = Point2D.generate(10,0,10);
        Point2D point = new Point2D(0,0);
        for(int i =0;i<array.length;i++){
            array[i].setY(0);
            System.out.println(array[i]);
        }

        List<Point2D> list = new ArrayList<>(Arrays.asList(array));
        removeHittedPoints(list,point,4.0);

        System.out.println();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
    public static void removeHittedPoints(List<Point2D> list,Point2D testPoint,double radius){
        ListIterator<Point2D> iterator = list.listIterator();
        while(iterator.hasNext()){
            Point2D p = iterator.next();
            if(Point2D.distanceAB(testPoint,p)<radius){
                iterator.remove();
            }
        }
    }
}
