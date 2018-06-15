/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
    private Stack<Point2D> hull = new Stack<Point2D>();

    /**
.
     *
     * @param  pts the array of points
     * @throws NullPointerException if <tt>points</tt> is <tt>null</tt> or if any
     *         entry in <tt>points[]</tt> is <tt>null</tt>
     */
    public void ConvexHullVertex(Point2D[] pts) {

        // defensive copy
        int N = pts.length;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++)
            points[i] = pts[i];

        // preprocess so that points[0] has lowest y-coordinate; break ties by x-coordinate
        // points[0] is an extreme point of the convex hull
        // (alternatively, could do easily in linear time)
        Arrays.sort(points);

        // sort by polar angle with respect to base point points[0],
        // breaking ties by distance to points[0]
        Arrays.sort(points, points[0].polarOrder());

        hull.push(points[0]);       // p[0] is first extreme point

        // find index k1 of first point not equal to points[0]
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        if (k1 == N) return;        // all points equal

        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        hull.push(points[k2-1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }

        assert isConvex();
        while(!hull.isEmpty())
            for(int i=0;i<N;i++)
               if(hull.peek().y()== pts[i].y())
                   System.out.printf(""%d"" , i);
    }

    /**
.
     *
     * @return the extreme points on the convex hull in counterclockwise order
     */
    public Iterable<Point2D> hull() {
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D p : hull) s.push(p);
        return s;
    }

    // check that boundary of hull is strictly convex
    private boolean isConvex() {
        int N = hull.size();
        if (N <= 2) return true;

        Point2D[] points = new Point2D[N];
        int n = 0;
        for (Point2D p : hull()) {
            points[n++] = p;
        }

        for (int i = 0; i < N; i++) {
            if (Point2D.ccw(points[i], points[(i+1) % N], points[(i+2) % N]) <= 0) {
                return false;
            }
        }
        return true;
    }

   /**
.
     * Reads in an integer <tt>N</tt> and <tt>N</tt> points (specified by
     * their <em>x</em>- and <em>y</em>-coordinates) from standard input;
     * computes their convex hull; and prints out the points on the
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
