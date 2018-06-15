
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        int zero = 0;
        int L=a.length;
        double mi = 0;
        double ang[] = new double[L];
        int order[] = new int[L];
        double ang_order[] = new double[L];

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

        int m = 0;
        Point2D[] ch = new Point2D[L + 1];
        ch[0] = new Point2D(a[order[0]].x(), a[order[0]].y());
        ch[1] = new Point2D(a[order[1]].x(), a[order[1]].y());
        int fir_order[] = new int[10 + 1];
        fir_order[0] = order[0];
        fir_order[1] = order[1];
        for (int i = 0; i < L; i++) {
            while (m >= 2 && Point2D.ccw(ch[m - 2], ch[m - 1], a[order[i]]) == -1) {
                m--;
            }
            ch[m] = new Point2D(a[order[i]].x(), a[order[i]].y());
            fir_order[m] = order[i];
            m++;
        }
        Arrays.sort(fir_order);
        return fir_order;
    }

    public static void main(String[] args) {
 }
}
