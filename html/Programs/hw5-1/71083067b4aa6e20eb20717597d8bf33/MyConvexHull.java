import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        int zero = 0;
        int zeroaa = 0;
        int zerobb = 0;
        int L = a.length;
        double mmaa = a[0].y();
        double mmbb = a[0].x();
        double ang[] = new double[L];
        int order[] = new int[L];
        double ang_order[] = new double[L];

        for (int i = 0; i < L; i++) {
            if (a[i].y() < mmaa) {
                zero = i;
                mmaa = a[i].y();
            }
        }


  
        for (int i = 0; i < L; i++) {
            double dx = a[i].x() - a[zero].x();
            double dy = a[i].y() - a[zero].y();
            ang[i] = Math.atan2(dy, dx);
            ang_order[i] = ang[i];
        }

        Arrays.sort(ang_order);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (ang[j] == ang_order[i]) {
                    order[i] = j;
                }
            }
        }

        int m = 2;
        Point2D[] ch = new Point2D[L + 1];
        ch[0] = new Point2D(a[order[0]].x(), a[order[0]].y());
        ch[1] = new Point2D(a[order[1]].x(), a[order[1]].y());
        int fir_order[] = new int[L + 1];
        fir_order[0] = order[0];
        fir_order[1] = order[1];
        for (int i = 2; i < L; i++) {
            while (m >= 2 && Point2D.ccw(ch[m - 2], ch[m - 1], a[order[i]]) == -1) {
                m--;
            }
            ch[m] = new Point2D(a[order[i]].x(), a[order[i]].y());
            fir_order[m] = order[i];
            m++;

        }
     
        int lorder[] = new int[m];
        System.arraycopy(fir_order, 0, lorder, 0, m);
        Arrays.sort(lorder);
        return lorder;
    }

    public static void main(String[] args) throws Exception {
}
}
