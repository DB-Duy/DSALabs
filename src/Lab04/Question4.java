package Lab04;

import geom.Point2D;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Vector;

public class Question4 {
    public static void main(String[] args) {
        float[] time = listTime();
        System.out.printf("%-20s %-20s %-20s %-20s %n","Implementation","add(0, object)","get()","add(object)");
        System.out.printf("%-20s %-20s %-20s %-20s %n","Linked List",""+time[0],""+time[3],""+time[6]);
        System.out.printf("%-20s %-20s %-20s %-20s %n","Array List",""+time[1],""+time[4],""+time[7]);
        System.out.printf("%-20s %-20s %-20s %-20s %n","Vector",""+time[2],""+time[5],""+time[8]);
    }
    public static float[] listTime() {
        float[] time = {0,0,0,0,0,0,0,0,0}; //linked, array, vector, add(0), get, add in order
        LinkedList<Point2D> linked = new LinkedList<Point2D>();
        ArrayList<Point2D> array = new ArrayList<>();
        Vector<Point2D> vector = new Vector<>();
        for(int i=0;i<9;i++){
        for (int j = 0; j < 1000; j++) {
            if(i<3){
            double start,end;
            start=System.nanoTime();
            for (int k = 0; k < 1000; k++) {
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
            time[i]+=(end-start);
            linked.clear();
            array.clear();
            vector.clear();
        }
            else if(i>=6){
                double start,end;
                start=System.nanoTime();
                for (int k = 0; k < 1000; k++) {
                    switch (i) {
                        case 0:
                            linked.add(new Point2D(0, 0));
                            break;
                        case 1:
                            array.add(new Point2D(0,0));
                            break;
                        case 2:
                            vector.add(new Point2D(0,0));
                            break;
                    }
                }
                end=System.nanoTime();
                time[i]+=(end-start);
                linked.clear();
                array.clear();
                vector.clear();
            }
            else{
                if(linked.isEmpty()){
                for(int k=0;k<1000;k++){
                    linked.add(new Point2D(k,k));
                    array.add(new Point2D(k,k));
                    vector.add(new Point2D(k,k));
                }
                }
                double start, end;
                start=System.nanoTime();
                for (int k = 0; k < 1000; k++) {
                    int rand =(int) Math.random()*1000;
                    switch (i) {
                        case 3:
                            linked.get(rand);
                            break;
                        case 4:
                            array.get(rand);
                            break;
                        case 5:
                            vector.get(rand);
                            break;
                    }
                }
                end=System.nanoTime();
                time[i]+=(end-start);
            }
        }
        time[i]=time[i]/(1000*1000);
        }
        return time;
    }
}
