

import java.util.Arrays;
import static java.util.Arrays.sort;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
    public int[] ConvexHullVertex(Point2D a[]) {
        int min = 0;
        for (int j = 1; j < a.length; j++) {
            if (a[min].compareTo(a[j]) == +1) {
                min = j;
            }
        }
        int m = 0;      // m 為凸包頂點數目
        int ans[] = null;
        for (int i = 0; i < a.length; i++) {
            while (m >= 2 && Point2D.ccw(a[ans[m - 2]], a[ans[m - 1]], a[i]) < 0) {
                m--;
            }
            // 添加新的點
            ans[m++] = i;
        }
        sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        int N = 10;
        edu.princeton.cs.algs4.StdDraw.setCanvasSize(400, 400);
        edu.princeton.cs.algs4.StdDraw.setXscale(0, 100);
        edu.princeton.cs.algs4.StdDraw.setYscale(0, 100);
        edu.princeton.cs.algs4.StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            int x = edu.princeton.cs.algs4.StdRandom.uniform(100);
            int y = edu.princeton.cs.algs4.StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }
        int min = 0;
        for (int j = 1; j < N; j++) {
            if (points[min].compareTo(points[j]) == +1) {
                min = j;
            }
        }
        edu.princeton.cs.algs4.StdDraw.setPenColor(edu.princeton.cs.algs4.StdDraw.RED);
        edu.princeton.cs.algs4.StdDraw.setPenRadius(.005);
        points[min].draw();
        edu.princeton.cs.algs4.StdDraw.setPenColor(edu.princeton.cs.algs4.StdDraw.BLUE);
        Arrays.sort(points, points[min].polarOrder());
        for (int i = 0; i < N; i++) {
            points[min].drawTo(points[i]);
            edu.princeton.cs.algs4.StdDraw.show(100);
        }

    }


}

