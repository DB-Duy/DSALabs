package Lab04.Question6;

import Lab04.Lists.MyArrayList;
import Lab04.Lists.SLinkedList;
import geom.Point2D;

public class MySLinkedListQuestion4 {
    public static void main(String[] args) {
        float[] time = listTime();
        System.out.printf("%-20s %-20s %-20s %-20s %n","Implementation","add(0, object)","get(0)","add(object)");
        System.out.printf("%-20s %-20s %-20s %-20s %n","Slinked List",""+time[0],""+time[2],""+time[1]);

    }
    public static float[] listTime() {
        float[] time = {0,0,0}; //add(0), get, add in order
        SLinkedList<Point2D> list = new SLinkedList<>();
        for(int i=0;i<3;i++){
            for (int j = 0; j < 100; j++) {
                if(i==0){
                    double start,end;
                    start=System.nanoTime();
                    for (int k = 0; k < 100; k++) {
                        list.add(0,new Point2D(0,0));
                    }
                    end=System.nanoTime();
                    time[i]+=(end-start);
                    list.clear();
                }
                else if(i==1){
                    double start,end;
                    start=System.nanoTime();
                    for (int k = 0; k < 100; k++) {
                        list.add(new Point2D(0,0));
                    }
                    end=System.nanoTime();
                    time[i]+=(end-start);
                    list.clear();
                }
                else{
                    if(list.isEmpty()){
                        for(int k=0;k<100;k++){
                            list.add(new Point2D(k,k));
                        }
                    }
                    double start, end;
                    start=System.nanoTime();
                    for (int k = 0; k < 100; k++) {
                        int rand =(int) Math.random()*100;
                        list.get(rand);
                    }
                    end=System.nanoTime();
                    time[i]+=(end-start);
                }
            }
            time[i]=time[i]/(100*100);
        }
        return time;
    }
}
