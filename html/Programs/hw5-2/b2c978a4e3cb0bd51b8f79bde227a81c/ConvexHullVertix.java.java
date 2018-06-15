
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author S410
 */
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class MyConvexHull {

    static class CC {

        Point2D[] point;
        int index;
        int len = 0;

        void addcc(Point2D a) {
            point[len] = a;
            len++;
        }
    }
    static HashMap<Point2D, Integer> map = new HashMap<Point2D, Integer>();
    static int current;

    public static Point2D[] sortbyY(Point2D[] a) {
        Arrays.sort(a, Point2D.Y_ORDER);
        return a;
    }

    public static Point2D[] sortbyangle(Point2D[] a, int low) {
        Comparator<Point2D> cmp = a[low].ATAN2_ORDER;
        Arrays.sort(a, cmp);
        return a;
    }

    public static boolean ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
        if (area2 < 0) {
            return false; // clockwise
        } else if (area2 > 0) {
            return true; // counter-clockwise
        } else {
            return false; // collinear
        }
    }

    public static int[] ConvexHullVertex(Point2D[] a) {
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        a = sortbyY(a);
        a = sortbyangle(a, 0);

        for (int i = 0; i < a.length; i++) {
//            StdDraw.text(a[i].x(), a[i].y() + 0.03, Integer.toString(i));
        }

        //找covexhull
        Stack<Integer> cvh = new Stack<Integer>();
        cvh.push(0);
        cvh.push(1);
        current = 2;
        while (1 > 0) {
//            StdOut.print(current);
            int p1 = current;
            int p2 = cvh.pop();
            int p3 = cvh.pop();
            if (ccw(a[p3], a[p2], a[p1])) {
                cvh.push(p3);
                cvh.push(p2);
                cvh.push(p1);
                current++;

                //最後一次回到原點且完成
                if (p1 == 0) {
                    cvh.pop();
                    break;
                }
            } else {
                cvh.push(p3);
            }
            if (current == a.length) {
                current = 0;
            }
        }

        int[] an = new int[cvh.size()];
        int num = cvh.size();
        for (int i = 0; i < num; i++) {
            an[i] = map.get(a[cvh.pop()]);
        }
        Arrays.sort(an);
        for (int i = 0; i < an.length; i++) {
//            StdOut.print(an[i]);
//            StdOut.print(an.length);
        }
        return an;

    }

    public static void main(String[] args) throws Exception {

        //讀檔，前兩行
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String maxlength = br.readLine();
        double maxlen = Double.parseDouble(maxlength);
        String number = br.readLine();
        int N = Integer.parseInt(number);

        //讀進所有點，畫出來並標記index
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            String[] coor = br.readLine().split("" "");
            points[i] = new Point2D(Double.parseDouble(coor[0]), Double.parseDouble(coor[1]));
//            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
//            StdDraw.text(points[i].x(), points[i].y() + 0.03, Integer.toString(i));
        }
        //做CC
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        int[] ID = new int[N];
        for (int i = 0; i < N; i++) {
            ID[i] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (points[i].distanceTo(points[j]) <= maxlen) {
                    uf.union(ID[i], ID[j]);
//                    StdDraw.line(points[i].x(), points[i].y(), points[j].x(), points[j].y());
                }
            }
        }

        for (int i = 0; i < N; i++) {
//            StdDraw.text(points[i].x(), points[i].y() + 0.03, Integer.toString(uf.find(ID[i])));
        }

        HashMap<Integer, Integer> mapcc = new HashMap<Integer, Integer>();
        HashMap<Integer, ArrayList<Point2D>> mapccc = new HashMap<Integer, ArrayList<Point2D>>();
        for (int i = 0; i < N; i++) {
            if (mapccc.containsKey(uf.find(ID[i]))) {
                mapccc.get(uf.find(ID[i])).add(points[i]);
//                int a=mapcc.get(uf.find(ID[i]));
//                mapcc.remove(uf.find(ID[i]));
//                mapcc.put(uf.find(ID[i]), ++a);
            } else {
//                mapcc.put(uf.find(ID[i]),1);
                mapccc.put(uf.find(ID[i]), new ArrayList<Point2D>());
                mapccc.get(uf.find(ID[i])).add(points[i]);
            }
        }
        int ans =0;
        for (int a : mapccc.keySet()) {
//            StdOut.print(i);
            if (mapccc.get(a).size() >= 3) {
//                mapccc.remove(mapccc.get(i));
                Point2D[] aa = mapccc.get(a).toArray(new Point2D[mapccc.get(a).size()]);
                ans+=(ConvexHullVertex(aa).length);
            }
        }
        StdOut.print(ans);
        for (int i = 0; i < N; i++) {
//            StdOut.print(st.get(uf.find(i)));
        }

    }
}

