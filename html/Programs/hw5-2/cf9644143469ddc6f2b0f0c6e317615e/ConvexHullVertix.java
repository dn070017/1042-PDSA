/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author daf
 */
// import edu.princeton.cs.algs4.Point2D;
// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.Stack;
// import edu.princeton.cs.algs4.UF;
// import edu.princeton.cs.algs4.MergeX;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

public class MyConvexHull {

    public static class Custom_Point2D implements Comparable<Custom_Point2D> {

        public static final Comparator<Custom_Point2D> X_ORDER = new XOrder();

        public static final Comparator<Custom_Point2D> Y_ORDER = new YOrder();

        public static final Comparator<Custom_Point2D> R_ORDER = new ROrder();

        public static final Comparator<Custom_Point2D> ccId_ORDER = new ccIdOrder();

        private final double x;    // x coordinate
        private final double y;    // y coordinate
        public final int id;
        public int ccid;

        public Custom_Point2D(double x, double y, int num) {
            if (Double.isInfinite(x) || Double.isInfinite(y)) {
                throw new IllegalArgumentException(""Coordinates must be finite"");
            }
            if (Double.isNaN(x) || Double.isNaN(y)) {
                throw new IllegalArgumentException(""Coordinates cannot be NaN"");
            }
            if (x == 0.0) {
                this.x = 0.0;  // convert -0.0 to +0.0
            } else {
                this.x = x;
            }

            if (y == 0.0) {
                this.y = 0.0;  // convert -0.0 to +0.0
            } else {
                this.y = y;
            }

            this.id = num;
        }

        public double x() {
            return x;
        }

        public double y() {
            return y;
        }

        public double r() {
            return Math.sqrt(x * x + y * y);
        }

        public double theta() {
            return Math.atan2(y, x);
        }

        private double angleTo(Custom_Point2D that) {
            double dx = that.x - this.x;
            double dy = that.y - this.y;
            return Math.atan2(dy, dx);
        }

        public static int ccw(Custom_Point2D a, Custom_Point2D b, Custom_Point2D c) {
            double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            if (area2 < 0) {
                return -1;
            } else if (area2 > 0) {
                return +1;
            } else {
                return 0;
            }
        }

        public static double area2(Custom_Point2D a, Custom_Point2D b, Custom_Point2D c) {
            return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        }

        public double distanceTo(Custom_Point2D that) {
            double dx = this.x - that.x;
            double dy = this.y - that.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        public double distanceSquaredTo(Custom_Point2D that) {
            double dx = this.x - that.x;
            double dy = this.y - that.y;
            return dx * dx + dy * dy;
        }

        public int compareTo(Custom_Point2D that) {
            if (this.y < that.y) {
                return -1;
            }
            if (this.y > that.y) {
                return +1;
            }
            if (this.x < that.x) {
                return -1;
            }
            if (this.x > that.x) {
                return +1;
            }
            return 0;
        }

        public Comparator<Custom_Point2D> polarOrder() {
            return new PolarOrder();
        }

        public Comparator<Custom_Point2D> atan2Order() {
            return new Atan2Order();
        }

        public Comparator<Custom_Point2D> distanceToOrder() {
            return new DistanceToOrder();
        }

        private static class XOrder implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D p, Custom_Point2D q) {
                if (p.x < q.x) {
                    return -1;
                }
                if (p.x > q.x) {
                    return +1;
                }
                return 0;
            }
        }

        private static class YOrder implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D p, Custom_Point2D q) {
                if (p.y < q.y) {
                    return -1;
                }
                if (p.y > q.y) {
                    return +1;
                }
                return 0;
            }
        }

        private static class ROrder implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D p, Custom_Point2D q) {
                double delta = (p.x * p.x + p.y * p.y) - (q.x * q.x + q.y * q.y);
                if (delta < 0) {
                    return -1;
                }
                if (delta > 0) {
                    return +1;
                }
                return 0;
            }
        }

        private static class ccIdOrder implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D p, Custom_Point2D q) {
                double delta = p.ccid - q.ccid;
                if (delta < 0) {
                    return -1;
                }
                if (delta > 0) {
                    return +1;
                }
                return 0;
            }
        }

        private class Atan2Order implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D q1, Custom_Point2D q2) {
                double angle1 = angleTo(q1);
                double angle2 = angleTo(q2);
                if (angle1 < angle2) {
                    return -1;
                } else if (angle1 > angle2) {
                    return +1;
                } else {
                    return 0;
                }
            }
        }

        private class PolarOrder implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D q1, Custom_Point2D q2) {
                double dx1 = q1.x - x;
                double dy1 = q1.y - y;
                double dx2 = q2.x - x;
                double dy2 = q2.y - y;

                if (dy1 >= 0 && dy2 < 0) {
                    return -1;    // q1 above; q2 below
                } else if (dy2 >= 0 && dy1 < 0) {
                    return +1;    // q1 below; q2 above
                } else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                    if (dx1 >= 0 && dx2 < 0) {
                        return -1;
                    } else if (dx2 >= 0 && dx1 < 0) {
                        return +1;
                    } else {
                        return 0;
                    }
                } else {
                    return -ccw(Custom_Point2D.this, q1, q2);     // both above or below
                }
                // Note: ccw() recomputes dx1, dy1, dx2, and dy2
            }
        }

        private class DistanceToOrder implements Comparator<Custom_Point2D> {

            public int compare(Custom_Point2D p, Custom_Point2D q) {
                double dist1 = distanceSquaredTo(p);
                double dist2 = distanceSquaredTo(q);
                if (dist1 < dist2) {
                    return -1;
                } else if (dist1 > dist2) {
                    return +1;
                } else {
                    return 0;
                }
            }
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other == null) {
                return false;
            }
            if (other.getClass() != this.getClass()) {
                return false;
            }
            Custom_Point2D that = (Custom_Point2D) other;
            return this.x == that.x && this.y == that.y;
        }

        @Override
        public String toString() {
            return ""("" + x + "", "" + y + "")"";
        }

        @Override
        public int hashCode() {
            int hashX = ((Double) x).hashCode();
            int hashY = ((Double) y).hashCode();
            return 31 * hashX + hashY;
        }

        public void draw() {
            StdDraw.point(x, y);
        }

        public void drawTo(Custom_Point2D that) {
            StdDraw.line(this.x, this.y, that.x, that.y);
        }
    }

    public static int[] ConvexHullVertex(Custom_Point2D[] a) {

        Custom_Point2D[] points = new Custom_Point2D[a.length];
        for (int i = 0; i < a.length; i++) {
            points[i] = new Custom_Point2D(a[i].x(), a[i].y(), i);
        }

        MergeX.sort(points, Custom_Point2D.Y_ORDER);
        MergeX.sort(points, points[0].polarOrder());

        Stack<Integer> record = new Stack<>();

        int k = 2;
        int j = 1;
        int i = 0;
        while (k < points.length) {
            if (Custom_Point2D.ccw(points[i], points[j], points[k]) == 1) {
                record.push(i);
                //StdOut.println(i);
                i = j;
                j = k;
                k++;
            } else {
                j = i;
                i = (int) record.pop();
            }
        }
        record.push(i);
        record.push(j);

        int[] res = new int[record.size()];
        int l = 0;

        while (!record.isEmpty()) {
            res[res.length - 1 - l] = points[(int) record.pop()].id;
            l++;
        }

//        for(int n=0;n<res.length;n++){
//            StdOut.println(res[n]);
//        }
        return res;
    }

    public static void main(String[] args) {
        ////input////

        BufferedReader br = null;
        String sCurrentLine;
        List<String[]> info = new ArrayList();
        try {
            br = new BufferedReader(new FileReader(args[0]));
            while ((sCurrentLine = br.readLine()) != null) {
                info.add(sCurrentLine.split("" ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double thr = Double.parseDouble(info.get(0)[0]);
        int N =Integer.parseInt(info.get(1)[0]);
        Point2D[] points = new Point2D[N];
//        StdDraw.setScale();
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D(Double.parseDouble(info.get(i+2)[0]),Double.parseDouble(info.get(i+2)[1]));
//            StdOut.println(i + "": "" + points[i].x() + "" "" + points[i].y());
//            StdDraw.text(points[i].x(), points[i].y() + 0.025, String.valueOf(i));
//            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
        }
        ////input////

        ////cc + convexhull////
        Custom_Point2D[] custom_points = new Custom_Point2D[points.length];
        for (int i = 0; i < points.length; i++) {
            custom_points[i] = new Custom_Point2D(points[i].x(), points[i].y(), i);
        }

//        int[] index_res = ConvexHullVertex(custom_points);

//        for (int i = 0; i < index_res.length-1; i++) {
//            StdDraw.setPenColor(StdDraw.GREEN);
//            StdDraw.line(points[index_res[i]].x(), points[index_res[i]].y(), points[index_res[i + 1]].x(), points[index_res[i + 1]].y());
//        }
//        StdDraw.line(points[index_res[index_res.length-1]].x(), points[index_res[index_res.length-1]].y(), points[index_res[0]].x(), points[index_res[0]].y());
        ////////////cc + convexhull/////////////
        MergeX.sort(custom_points, Custom_Point2D.Y_ORDER);

        UF cc = new UF(custom_points.length);
//        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < custom_points.length - 1; i++) {
            for (int j = 1; j < custom_points.length - i; j++) {
                if (custom_points[i].distanceTo(custom_points[i + j]) <= thr) {
                    cc.union(i, i + j);
//                    StdDraw.line(custom_points[i].x(), custom_points[i].y(), custom_points[i + j].x(), custom_points[i + j].y());
//                    custom_points[i].drawTo(custom_points[i + j]);
                } else if (custom_points[i + j].y() - custom_points[i].y() > thr) {
                    break;
                }
            }
        }

        for (int i = 0; i < custom_points.length; i++) {
            custom_points[i].ccid = cc.find(i);
        }

        MergeX.sort(custom_points, Custom_Point2D.Y_ORDER);
        MergeX.sort(custom_points, Custom_Point2D.ccId_ORDER);

//        for (int i = 0; i < custom_points.length; i++) {
//            StdOut.println(custom_points[i].ccid);
//        }
        Stack<Custom_Point2D> points_stack = new Stack();
        int current_id = custom_points[0].ccid;
        Custom_Point2D[] current_points;
        int res = 0;
        for (int i = 0; i < custom_points.length; i++) {
//            StdOut.println(""now:"" + custom_points[i].id);
            if (custom_points[i].ccid == current_id) {
                points_stack.push(custom_points[i]);
                //StdOut.println(current_id);
            }
            if (custom_points[i].ccid != current_id || i == custom_points.length - 1) {
                ////process current cc points////
                if (points_stack.size() >= 3) {
                    current_points = new Custom_Point2D[points_stack.size()];
                    int k = points_stack.size() - 1;
                    //StdOut.println(""size:""+points_stack.size());
                    while (!points_stack.isEmpty()) {
                        current_points[k] = points_stack.pop();
                        //StdOut.println(current_points[k].id);
                        k--;
                    }
                    //StdOut.println("""");
                    MergeX.sort(current_points, current_points[0].polarOrder());
                    //StdOut.println(custom_points[0].id);
                    int[] current_index_res = ConvexHullVertex(current_points);
                    //StdOut.println(current_index_res[0]);
                    res = res + current_index_res.length;
                    //StdOut.println(current_index_res.length +"" ""+ res);
                }
                ////process current cc points////

                while (!points_stack.isEmpty()) {
                    points_stack.pop();
                }
                current_id = custom_points[i].ccid;
                points_stack.push(custom_points[i]);
            }
        }
        StdOut.println(res);
        ////cc + convexhull////

    }

}

