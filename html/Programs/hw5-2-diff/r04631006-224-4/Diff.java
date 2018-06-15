
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        int zero = 0;
        int L = a.length;
        double mmaa = a[0].y();
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
        int fir_order[] = new int[10 + 1];
        fir_order[0] = order[0];
        fir_order[1] = order[1];
        for (int i = 2; i < L; i++) {
            while (m >= 2 && Point2D.ccw(ch[m - 2], ch[m - 1], a[order[i]]) != 1) {
                m--;
            }
            ch[m] = new Point2D(a[order[i]].x(), a[order[i]].y());
            fir_order[m] = order[i];
            m++;

        }    
        int lorder[] = new int[m];
        for (int i = 0; i < m; i++) {
            lorder[i] = fir_order[i];
        }
        return lorder;
    }

    public static void main(String[] args) throws Exception {
try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String Distance = br.readLine();
            double Dis = Double.parseDouble(Distance);
            String Number = br.readLine();
            int Num = Integer.parseInt(Number);
            double point[][] = new double[Num][2];
            Point2D[] poi = new Point2D[Num];
            for (int i = 0; i < Num; i++) {
                String[] data = br.readLine().split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                point[i][0] = x;
                point[i][1] = y;
                poi[i] = new Point2D(x, y);
            }

            int[] cc = new int[Num];
            QuickFindUF qf = new QuickFindUF(Num);
            for (int i = 0; i < Num; i++) {
                for (int j = 0; j < Num; j++) {
                    if (poi[i].distanceTo(poi[j]) <= Dis) {
                        qf.union(j, i);
                    }
                }
            }
            int mm = 0;
            int outt = 0;
            for (int i = 0; i < Num; i++) {
                for (int j = i; j < Num; j++) {
                    if (qf.find(i) == i && qf.connected(i, j)) {
                        cc[i] = cc[i] + 1;
                    }
                }
                if (cc[i] >= 3) {
                    Point2D[] CCpoi = new Point2D[cc[i]];
                    for (int k = 0; k < Num; k++) {
                        if (qf.find(k) == i) {
                            CCpoi[mm] = new Point2D(poi[k].x(), poi[k].y());
                            mm = mm + 1;
                        }
                    }
                    int ooooo[] = ConvexHullVertex(CCpoi);
                    outt = outt + ooooo.length;

                    mm = 0;
                }
            }
            System.out.printf(""%d "", outt);
}
}
}

