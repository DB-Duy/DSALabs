package Lab04;

import geom.Point2D;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Vector;

public class Question4 {
    public static void listTime() {
        LinkedList<Point2D> linked = new LinkedList<Point2D>();
        ArrayList<Point2D> array = new ArrayList<>();
        Vector<Point2D> vector = new Vector<>();
        for(int i=0;i<3;i++){
        for (int j = 0; j < 1000; j++) {
            double[] time = {0,0,0}; //linked, array, vector in order
            double start,end;
            start=System.nanoTime();
            for (int k = 0; k < 50000; k++) {
                switch (i) {
                    case 0:
                        linked.add(0, new Point2D(0, 0));
                        break;
                    case 1:
                        array.add(0,new Point2D(0,0));
                        break;
                    case 2:
                        vector.add(0,new Point2D(0,0));
                        break;
                }
            }
            end=System.nanoTime();
            time[i]=start-end;
            start=0;
            end=0;
            linked.clear();
            array.clear();
            vector.clear();
        }}
    }
}
