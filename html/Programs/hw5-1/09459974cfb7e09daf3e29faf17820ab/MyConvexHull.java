
/**
 * ****************************************************************************
 * Compilation: javac GrahamaScan.java Execution: java GrahamScan < input.txt
 * Dependencies: Point2D.java
 *
 * Create points from standard input and compute the convex hull using Graham
.
 *
.
 *
 *****************************************************************************
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

/**
 * The <tt>GrahamScan</tt> data type provides methods for computing the convex
.
 * <p>
 * The implementation uses the Graham-Scan convex hull algorithm. It runs in
 * O(<em>N</em> log <em>N</em>) time in the worst case and uses O(<em>N</em>)
.
 * <p>
 * For additional documentation, see
 * <a href=""http://algs4.cs.princeton.edu/99scientific"">Section 9.9</a> of
.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class MyConvexHull {

    public static Stack<Point2D> hull = new Stack<Point2D>();

    /**
.
     *
     * @param pts the array of points
     * @throws NullPointerException if <tt>points</tt> is <tt>null</tt> or if
     * any entry in <tt>points[]</tt> is <tt>null</tt>
     */
    public int[] ConvexHullVertex(Point2D[] pts) {

        // defensive copy
        int N = pts.length;
        int[] ans;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = pts[i];
        }

        // preprocess so that points[0] has lowest y-coordinate; break ties by x-coordinate
        // points[0] is an extreme point of the convex hull
        // (alternatively, could do easily in linear time)
        Arrays.sort(points);

        // sort by polar angle with respect to base point points[0],
        // breaking ties by distance to points[0]
        Arrays.sort(points, 1, N, points[0].POLAR_ORDER);

        hull.push(points[0]);       // p[0] is first extreme point

        // find index k1 of first point not equal to points[0]
        int k1;
        for (k1 = 1; k1 < N; k1++) {
            if (!points[0].equals(points[k1])) {
                break;
            }
        }
//        if (k1 == N) {
//            return;        // all points equal
//        }
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++) {
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) {
                break;
            }
        }
        hull.push(points[k2 - 1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);

        }
        ans = new int[hull.size()];
        System.out.printf(""hull size=%d\n"", hull.size());
         for (int i = 0; i < ans.length; i++) {
             double check =0;
             check=hull.pop().y();
             //System.out.printf(""check=%f\n"", check);
                for (int j = 0; j < points.length; j++) {
                    if (check == pts[j].y()) {
                        //System.out.printf(""value=%f  index= %d\n"",back[i].y(),j);
                        ans[i] = j;
                    }
                }
                
                System.out.printf(""index= %d\n"",ans[i]);

            }
                         Arrays.sort(ans);
                         for(int i=0;i<ans.length;i++)
                System.out.printf(""\nindex= %d\n"",ans[i]);
                         return ans;
        //assert isConvex();
    }

    /**
.
     *
     * @return the extreme points on the convex hull in counterclockwise order
     */
    public Iterable<Point2D> hull() {
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D p : hull) {
            s.push(p);
        }
        return s;
    }

    // check that boundary of hull is strictly convex
    private boolean isConvex() {
        int N = hull.size();
        if (N <= 2) {
            return true;
        }

        Point2D[] points = new Point2D[N];
        int n = 0;
        for (Point2D p : hull()) {
            points[n++] = p;
        }

        for (int i = 0; i < N; i++) {
            if (Point2D.ccw(points[i], points[(i + 1) % N], points[(i + 2) % N]) <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Unit tests the <tt>ClosestPair</tt> data type. Reads in an integer
     * <tt>N</tt> and <tt>N</tt> points (specified by their <em>x</em>- and
     * <em>y</em>-coordinates) from standard input; computes their convex hull;
.
     */
    public static void main(String[] args) {
//        Point2D[] a = new Point2D[10];
//        a[0] = new Point2D(0.200, 0.250);
//        a[1] = new Point2D(0.147, 0.387);
//        a[2] = new Point2D(0.300, 0.300);
//        a[3] = new Point2D(0.333, 0.213);
//        a[4] = new Point2D(0.353, 0.412);
//        a[5] = new Point2D(0.700, 0.890);
//        a[6] = new Point2D(0.879, 0.700);
//        a[7] = new Point2D(0.867, 0.888);
//        a[8] = new Point2D(0.980, 0.120);
//        a[9] = new Point2D(0.111, 0.932);
//        MyConvexHull graham = new MyConvexHull();
//        graham.ConvexHullVertex(a);
//        for (Point2D p : graham.hull()) {
//            //StdOut.println(p);
//        }
    }

}

