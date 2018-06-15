/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author Arthur
 */
//public class MyConvexHull {
//
//    private void ConvexHullVertex(Point2D a[]) {
//        int N = a.length;
//        Point2D[] classpoint = new Point2D[N + 1];
//
//    }
//    void swap(Point2D a, Point2D b)
//    {
//        
//    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        int n = 10;
//        Point2D[] point = new Point2D[n];
//
//        for (int i = 0; i < n; i++) {
//            double x = StdRandom.uniform();
//            double y = StdRandom.uniform();
//            point[i] = new Point2D(x, y);
//            StdDraw.filledCircle(point[i].x(), point[i].y(), 0.005);
//            System.out.printf(""%f  ,   %f\n"", point[i].x(), point[i].y());
//        }
//        double[] num;
//        num = new double[10];
//        for (int i = 0; i < n; i++) {
//            num[i] = point[i].y();
//            System.out.printf(""\n%f"", num[i]);
//
//        }
//        int minindex=-1;
//        double min=1;
//        for(int i=0;i<n;i++)
//        {
//            if(num[i]< min){
//                min=num[i];
//                minindex=i;
//            }
//        }
//        System.out.printf(""\nmin=%f\nminindex=%d"", min,minindex);
//        swap(point[1],point[minindex]);
//        Arrays.sort(point, point[1].polarOrder());
//        // TODO code application logic here
//    }
//
//}
public class MyConvexHull {

    private static Stack<Point2D> hull = new Stack<Point2D>();

    /**
.
     *
     * @param pts the array of points
     * @throws NullPointerException if <tt>points</tt> is <tt>null</tt> or if
     * any entry in <tt>points[]</tt> is <tt>null</tt>
     */
    public static int[] ConvexHullVertex(Point2D[] pts) {
 
        int count = 0;

        // defensive copy
        int N = pts.length;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = pts[i];
        }

        // preprocess so that points[0] has lowest y-coordinate; break ties by x-coordinate
        // points[0] is an extreme point of the convex hull
        // (alternatively, could do easily in linear time)
        Arrays.sort(points, Point2D.Y_ORDER);
        hull.push(points[0]);
        // sort by polar angle with respect to base point points[0],
        // breaking ties by distance to points[0]
        //Arrays.sort(points, points[0].POLAR_ORDER);
        Arrays.sort(points, points[0].POLAR_ORDER);
        hull.push(points[1]);
        hull.push(points[2]);
        for (int i = 3; i < points.length; ++i) {
            Point2D top = hull.peek();
            Point2D nextToTop = hull.get(hull.size() - 2);

            while (Point2D.ccw(points[i], nextToTop, top) >= 0 && hull.size() >= 3) {
                hull.pop();
                top = hull.peek();
                nextToTop = hull.get(hull.size() - 2);
            }

            hull.push(points[i]);
        }
        int[] back = new int[hull.size()];
        double check = 0;
        //assert isConvex();
        while (!hull.isEmpty()) {
            check = hull.pop().y();
            for (int i = 0; i < N; i++) {
                if (check == pts[i].y()) {
                    //System.out.printf(""%d"", i);
                    //back[hull.size()-1-count] = i;
                    count++;
                }
            }

        }
        return back;
    }


    /**
     * Unit tests the <tt>ClosestPair</tt> data type. Reads in an integer
     * <tt>N</tt> and <tt>N</tt> points (specified by their <em>x</em>- and
     * <em>y</em>-coordinates) from standard input; computes their convex hull;
.
     */
    public static void main(String[] args) {
//        int N = StdIn.readInt();
//        Point2D[] points = new Point2D[N];
//        for (int i = 0; i < N; i++) {
//            int x = StdIn.readInt();
//            int y = StdIn.readInt();
//            points[i] = new Point2D(x, y);
//        }
//        MyConvexHull graham = new MyConvexHull(points);
//        for (Point2D p : graham.hull())
//            StdOut.println(p);
    }

}

