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
            for (int i = 0; i < n; ++i) {
                while (k >= 2 && Point2D.ccw(back[k - 2], back[k - 1], pts[i]) <= 0) {
                    k--;
                }
                back[k++] = pts[i];
            }
            for (int i = n - 2, t = k + 1; i >= 0; i--) {
                while (k >= t && Point2D.ccw(back[k - 2], back[k - 1], pts[i]) <= 0) {
                    k--;
                }
                back[k++] = pts[i];
            }
            if (k > 1) {
                back = Arrays.copyOfRange(back, 0, k - 1);
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
                
//                System.out.printf(""index= %d\n"",ans[i]);
            }
            Arrays.sort(ans);
//            System.out.printf(""index= %d\n"",ans[0]);
//            System.out.printf(""index= %d\n"",ans[1]);
//            System.out.printf(""index= %d\n"",ans[2]);
//            System.out.printf(""index= %d\n"",ans[3]);
//            System.out.printf(""index= %d\n"",ans[4]);
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
//        a[0] = new Point2D(0.200,0.250);
//        a[1] = new Point2D(0.147,0.387);
//        a[2] = new Point2D(0.300 ,0.300);
//        a[3] = new Point2D(0.333,0.213);
//        a[4] = new Point2D(0.353,0.412);
//        a[5] = new Point2D(0.700,0.890);
//        a[6] = new Point2D(0.879,0.700);
//        a[7] = new Point2D(0.867,0.888);
//        a[8] = new Point2D(0.980,0.120);
//        a[9] = new Point2D(0.111,0.932);
//        Point2D[] point = new Point2D[10];
//        for (int i = 0; i < 10; i++) {
//            double x = StdRandom.uniform();
//            double y = StdRandom.uniform();
//            point[i] = new Point2D(x, y);
//            StdDraw.filledCircle(point[i].x(), point[i].y(), 0.005);
//            System.out.printf(""%f  ,   %f\n"", point[i].x(), point[i].y());
//        }
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

