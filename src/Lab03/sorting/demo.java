package Lab03.sorting;

import geom.Point2D;
import geom.PointComparator;

public class demo {
    public static void main(String[] args) {
        int[] num_segments = {1, 3, 7};
        /*ISort[] algorithms = {
                new StraightInsertionSort<Point2D>(),
                new ShellSort<Point2D>(num_segments),
                new StraightSelectionSort<Point2D>(),
                new BubbleSort<Point2D>()
        };

        for (int aIdx = 0; aIdx < algorithms.length; aIdx++) {
            Point2D[] points = Point2D.generate(50, -20, 20);
            //If you want to sort ...
            System.out.println("done1");
            algorithms[aIdx].sort(points, new PointComparator(), 1); //do sorting
            //If you want to time it ...
            System.out.println("done2");
            Point2D[] time = SortingEval.timeit(algorithms[aIdx], 50, 100, 1);
            //here: more code for other purpose.
            System.out.println("done3");*/
        Point2D[] p = Point2D.generate(20,0,10);
        StraightSelectionSort<Point2D> sss = new StraightSelectionSort<>();
        BubbleSort<Point2D> bs= new BubbleSort<>();
        bs.sort(p,new PointComparator(),1);
        //sss.sort(p,new PointComparator(),1);
        for(int i = 0;i<p.length;i++){
            System.out.println(p[i].getX());
        }

    }

}
