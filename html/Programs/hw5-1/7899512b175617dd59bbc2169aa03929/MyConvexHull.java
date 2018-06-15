
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] point) {
        int N = point.length;
        Point2D[] ww = new Point2D[N];
        Point2D[] cc = new Point2D[N];
        Point2D min = new Point2D(100, 100);
        for (int i = 0; i < point.length; i++) {
            switch (point[i].compareTo(min)) {
                case 1:
                    break;
                case 0:
                    break;
                case -1:
                    min = point[i];
                    break;
            }
        }
        cc = point.clone();
        Point2D p = new Point2D(min.x(), min.y());
        Arrays.sort(point, p.ATAN2_ORDER);
        if (N <= 2) {
            return null;
        }
        ww[0] = point[0];
        ww[1] = point[1];
        ww[0].drawTo(ww[1]);
        int j = 0;
        int i = 0;
        while (i < N - 1) {
            ww[j + 2] = point[i + 1];
            switch (Point2D.ccw(ww[j], ww[j + 1], ww[j + 2])) {
                case -1:
                    j--;
                    break;
                case 0:
                    ww[j + 1].drawTo(ww[j + 2]);
                    j++;
                    i++;
                    break;
                case 1:
                    ww[j + 1].drawTo(ww[j + 2]);
                    j++;
                    i++;
                    break;
            }
        }
        int[] out = new int[j + 1];
        if (j > N / 2) {
            out = new int[j + 1];
            return out;
        }
        int count = 0;
        for (int k = 0; k < N; k++) {
            for (i = 1; i < j + 2; i++) {
                if (cc[k] == ww[i]) {
                    out[count] = k;
                    count++;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        int minpoint = 0;
        int M = 10;
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[M];
        Point2D[] cc = new Point2D[M];
        Point2D[] ww = new Point2D[M];
        Point2D min = new Point2D(100, 100);
        for (int i = 0; i < M; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
            switch (points[i].compareTo(min)) {
                case 1:
                    break;
                case 0:
                    break;
                case -1:
                    min = points[i];
                    minpoint = i;
                    break;
            }
        }
        MyConvexHull t = new MyConvexHull();
        int[] x;
        x = t.ConvexHullVertex(points);
        System.out.println(x);

    }
}

