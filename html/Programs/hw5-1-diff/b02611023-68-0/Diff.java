/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        
        Point2D[] origin = a.clone();
        Arrays.sort(a, Point2D.Y_ORDER);
        Point2D p = new Point2D(a[0].x(), a[0].y());
        
        Arrays.sort(a, p.POLAR_ORDER);

        Point2D A = new Point2D(0.0, 0.0);
        Point2D B = A;
        Point2D C = A;
        int count = 0;
        int n_count = 0;
        int k0 = 0;
        Point2D[] point = new Point2D[a.length+1];
        for (int i = 0; i < a.length; i++) {
            point[i] = a[i];
        }
        point[a.length] = a[0];
        for (int i = 2; i < a.length+1; i++) {
            for (int j = 0; j <= i - 2; j++) {
                for (int k = j + 1; k <= i; k++) {
                    if (point[j] != null) {
                        A = new Point2D(point[j].x(), point[j].y());
                        if (point[k] != null) {
                            if (count <= 0) {
                                B = new Point2D(point[k].x(), point[k].y());
                                count++;
                                k0 = k;
                            } else {
                                C = new Point2D(point[k].x(), point[k].y());
                                count++;
                            }
                        }
                        if (count == 2 && Point2D.ccw(A, B, C) <= 0) {
                            if (j == 0) {
                                point[k] = null;
                                n_count++;
                            } else {
                                point[k0] = null;
                                n_count = n_count + 1;
                            }
                        }
                    }
                }
                count = 0;
            }

        }

        int[] index = new int[a.length - n_count];
        int l = 0;
        for (int m = 0; m < a.length; m++) {
            for (int n = 0; n < a.length; n++) {
                if (point[m] != null) {
                    if (point[m] == origin[n]) {
                        index[l] = n;
                        l++;
                        break;
                    }
                }
                if (l == (index.length - 1)) {
                    break;
                }
            }
        }
        //Arrays.sort(index);
        return index;
    }
        


//    public static void main(String[] args) {
//        int N = 20;
//        Point2D[] points = new Point2D[N];
//        StdDraw.setCanvasSize(600, 600);
//        StdDraw.setXscale(0, 100);
//        StdDraw.setYscale(0, 100);
//        StdDraw.setPenRadius(.01);
//        for (int i = 0; i < N; i++) {
//            int x = StdRandom.uniform(100);
//            int y = StdRandom.uniform(100);
//            points[i] = new Point2D(x, y);
//            points[i].draw();
//            System.out.println(points[i]);
//        }
//        Point2D[] origin = points.clone();
//        Arrays.sort(points, Point2D.Y_ORDER);
//        Point2D p = new Point2D(points[0].x(), points[0].y());
//        StdDraw.setPenColor(StdDraw.RED);
//        p.draw();
//
//        StdDraw.setPenRadius();
//        StdDraw.setPenColor(StdDraw.BLUE);
//        Arrays.sort(points, p.POLAR_ORDER);
//        for (int i = 0; i < N; i++) {
//            p.drawTo(points[i]);
//            StdDraw.show(100);
//        }
//
//        Point2D a = new Point2D(0.0, 0.0);
//        Point2D b = a;
//        Point2D c = a;
//        int count = 0;
//        int n_count = 0;
//        int k0 = 0;
//        Point2D[] point = new Point2D[N+1];
//        for (int i = 0; i < N; i++) {
//            point[i] = points[i];
//        }
//        point[N] = points[0];
//        for (int i = 2; i < N+1; i++) {
//            for (int j = 0; j <= i - 2; j++) {
//                for (int k = j + 1; k <= i; k++) {
//                    if (point[j] != null) {
//                        a = new Point2D(point[j].x(), point[j].y());
//                        if (point[k] != null) {
//                            if (count <= 0) {
//                                b = new Point2D(point[k].x(), point[k].y());
//                                count++;
//                                k0 = k;
//                            } else {
//                                c = new Point2D(point[k].x(), point[k].y());
//                                count++;
//                            }
//                        }
//                        if (count == 2 && Point2D.ccw(a, b, c) <= 0) {
//                            if (j == 0) {
//                                point[k] = null;
//                                n_count++;
//                            } else {
//                                point[k0] = null;
//                                n_count = n_count + 1;
//                            }
//                        }
//                    }
//                }
//                count = 0;
//            }
//
//        }
//
//        int[] index = new int[N - n_count];
//        int l = 0;
//        for (int m = 0; m < N; m++) {
//            for (int n = 0; n < N; n++) {
//                if (point[m] != null) {
//                    if (point[m] == origin[n]) {
//                        index[l] = n;
//                        l++;
//                        break;
//                    }
//                }
//                if (l == (index.length - 1)) {
//                    break;
//                }
//            }
//        }
//        Arrays.sort(index);
//
//        System.out.println();
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(.02);
//        for (int i = 0; i < N; i++) {
//            if (point[i] != null) {
//                point[i].draw();
//                StdDraw.show(100);
//            }
//            System.out.println(point[i]);
//        }
//        System.out.println();
//        for (int i = 0; i < N - n_count; i++) {
//            System.out.println(index[i]);
//        }
//    }
}

