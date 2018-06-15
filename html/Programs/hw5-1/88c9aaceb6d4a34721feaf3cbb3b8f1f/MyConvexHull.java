
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Frank
 */
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
        ww[0] = p;
        ww[1] = point[1];
        ww[0].drawTo(ww[1]);
        int j = 0;
        int i = 1;
        if (N == 2) {
            return null;
        }
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
        int[] out = new int[j + 2];
        int count = 0;
        for (int k = 0; k < N; k++) {
            for (i = 0; i < j + 2; i++) {
                if (cc[k].compareTo(ww[i])==0) {
                    out[count] = k;
                    count++;           
                    i = 0;
                    break;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {

    }
}

