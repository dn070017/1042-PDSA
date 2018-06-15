
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        int zero = 0;
        double mi = 0;
        double ang[] = new double[10];
        int order[] = new int[10];
        double ang_order[] = new double[10];
        for (int i = 0; i < 10; i++) {
            double x = StdRandom.uniform(0, 100);
            double y = StdRandom.uniform(0, 100);

            a[i] = new Point2D(x, y);
            a[i].draw();

            if (i == 0) {
                mi = a[0].y();
            } else if (i > 0 && y < mi) {
                zero = i;
                mi = y;
            }
        }

        for (int i = 0; i < 10; i++) {
            double dx = a[i].x() - a[zero].x();
            double dy = a[i].y() - a[zero].y();
            ang[i] = Math.atan2(dy, dx);
            ang_order[i] = ang[i];
        }

        Arrays.sort(ang_order);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ang[j] == ang_order[i]) {
                    order[i] = j;
                }
            }
        }

        int m = 0;
        Point2D[] ch = new Point2D[10 + 1];
        ch[0] = new Point2D(a[order[0]].x(), a[order[0]].y());
        ch[1] = new Point2D(a[order[1]].x(), a[order[1]].y());
        int fir_order[] = new int[10 + 1];
        fir_order[0] = order[0];
        fir_order[1] = order[1];
        for (int i = 0; i < 10; i++) {
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

