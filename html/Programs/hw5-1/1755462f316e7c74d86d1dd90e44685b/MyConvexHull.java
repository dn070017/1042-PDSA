/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class MyConvexHull {

    private static Stack<Point2D> hull = new Stack<Point2D>();

    public static int[] ConvexHullVertex(Point2D[] pts) {

        // defensive copy
        int N = pts.length;
        int[] ans;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = pts[i];
        }
        if (pts.length > 1) {
            int n = pts.length, k = 0;
            Point2D[] back = new Point2D[2 * n];
            Arrays.sort(pts);
            for (int i = 0; i < n; i++) {
                while (k >= 2 && Point2D.ccw(back[k - 2], back[k - 1], pts[k]) <= 0) {
                    k--;
                }
                back[k++] = pts[i];
            }
            for (int i = n - 2, t = k + 1; i >= 0; i--) {
                while (k >= t && Point2D.ccw(back[k - 2], back[k - 1], pts[k]) <= 0) {
                    k--;
                }
                back[k++] = pts[i];
            }
            if (k > 1) {
                back = Arrays.copyOfRange(pts, 0, k - 1);
            }
            ans = new int[back.length];
            //System.out.printf(""size= %f\n"", back[4].y());
            for (int i = 0; i < back.length; i++) {
                for (int j = 0; j < points.length; j++) {
                    if (back[i].y() == points[j].y()) {
                        //System.out.printf(""value=%f  index= %d\n"",back[i].y(),j);
                        ans[i] = j;
                    }
                }
                //System.out.printf(""index= %d\n"",ans[i]);
            }
            
            return ans;
//            for (int i = 0; i < back.length; i++) {
//                System.out.printf(""%f , "", back[i].y());
//            }

        } else if (pts.length <= 1) {
            return null;
        } else {
            return null;
        }
    }

        // preprocess so that points[0] has lowest y-coordinate; break ties by x-coordinate
// points[0] is an extreme point of the convex hull
// (alternatively, could do easily in linear time)
//Arrays.sort(points, Point2D.Y_ORDER);
//                        for (int i = 0; i < N; i++) {
//            System.out.printf(""%f , "" ,points[i].y());
//        }
//hull.push(points[0]);
// sort by polar angle with respect to base point points[0],
// breaking ties by distance to points[0]
//Arrays.sort(points, points[0].POLAR_ORDER);
//        Arrays.sort(points, points[0].POLAR_ORDER);
//        for (int i = 0; i < 10; i++) {
//            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.005);
//            StdDraw.text(points[i].x() - 0.02, points[i].y() + 0.02, Integer.toString(i));
//        }
    /**
     * Unit tests the <tt>ClosestPair</tt> data type. Reads in an integer
     * <tt>N</tt> and <tt>N</tt> points (specified by their <em>x</em>- and
     * <em>y</em>-coordinates) from standard input; computes their convex hull;
.
     */
    public static void main(String[] args) {
//        Point2D[] a = new Point2D[10];
//        a[0] = new Point2D(0.444732, 0.870521);
//        a[1] = new Point2D(0.542629, 0.928699);
//        a[2] = new Point2D(0.437711, 0.518879);
//        a[3] = new Point2D(0.331491, 0.709862);
//        a[4] = new Point2D(0.075244, 0.825623);
//        a[5] = new Point2D(0.078116, 0.931341);
//        a[6] = new Point2D(0.386513, 0.516805);
//        a[7] = new Point2D(0.235063, 0.145878);
//        a[8] = new Point2D(0.126400, 0.103314);
//        a[9] = new Point2D(0.305495, 0.779383);
//        MyConvexHull mch;
//        mch = new MyConvexHull();
//        int[] ans;
//        ans = mch.ConvexHullVertex(a);
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

