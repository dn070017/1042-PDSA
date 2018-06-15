/*
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
import java.util.Arrays;
import java.util.HashMap;

public class MyConvexHull {

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
        for(int i=0;i<a.length;i++){
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
        
        int[] an=new int[cvh.size()];
        int num=cvh.size();
        for (int i=0;i<num;i++){
            an[i]=map.get(a[cvh.pop()]);
//             StdOut.print(an[i]);
        }
        Arrays.sort(an);
        for (int i=0;i<an.length;i++){
            StdOut.print(an[i]);
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
//            map.put(points[i], i);
//            StdDraw.text(points[i].x(), points[i].y() + 0.03, Integer.toString(i));
        }

        ConvexHullVertex(points);

    }
}

