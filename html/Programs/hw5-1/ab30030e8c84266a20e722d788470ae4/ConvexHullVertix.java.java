
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] point) {
        int N = point.length;
        Point2D[] ww = new Point2D[N];
        Point2D[] cc = new Point2D[N];
        Point2D min = new Point2D(N, N);
        min = point[0];
        for (Point2D point1 : point) {
            switch (point1.compareTo(min)) {
                case 1:
                    break;
                case 0:
                    break;
                case -1:
                    min = point1;
                    break;
            }
        }
        cc = point.clone();
        Point2D p = new Point2D(min.x(), min.y());
        Arrays.sort(point, p.ATAN2_ORDER);
        for (int i = 0; i < N; i++) {
            String word = Integer.toString(i);
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.textLeft(point[i].x(), point[i].y() + 2, word);

        }
        if (N < 2) {
            return null;
        }
        if (point[0].y() == point[1].y() && point[0].x() > point[1].x()) {
            ww[0] = point[1];
            ww[1] = point[0];
//            ww[0].drawTo(ww[1]);
        } else {
            ww[0] = point[0];
            ww[1] = point[1];
//            ww[0].drawTo(ww[1]);
        }
        int j = 2;
        int i = 0;
        if (N == 2) {
            return null;
        }
        while (i < N - 2) {
            ww[j] = point[i + 2];
            switch (Point2D.ccw(ww[j - 2], ww[j - 1], ww[j])) {
                case -1:
                    j--;
                    break;
                case 0:
//                    ww[j - 1].drawTo(ww[j]);
                    j++;
                    i++;
                    break;
                case 1:
//                    ww[j - 1].drawTo(ww[j]);
                    j++;
                    i++;
                    break;
            }
        }
//        ww[j - 1].drawTo(ww[0]);
        int[] out = new int[j];
        int count = 0;
        for (int k = 0; k < N; k++) {
            for (i = 0; i < j; i++) {
                if (cc[k] == ww[i]) {
                    out[count] = k;
                    count++;
                    break;
                }
            }
        }

        return out;

    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                    int fin =0;
            int M = 0;
            double L = 0;
            int count = 0;
            String data;
            Point2D min = new Point2D(1, 1);
            while (count < 2) {
                if (count == 0) {
                    String fund = br.readLine();
                    L = Double.parseDouble(fund);
                    count++;
                }
                if (count == 1) {
                    String fund = br.readLine();
                    M = Integer.parseInt(fund);
//                    StdDraw.setCanvasSize(500, 500);
//                    StdDraw.setXscale(0, 1);
//                    StdDraw.setYscale(0, 1);
//                    StdDraw.setPenRadius(.005);
                    count++;
                }
            }
            Point2D[] points = new Point2D[M];
            count = 0;
            while ((data = br.readLine()) != null) {
                String fund[] = data.split("" "");
                double x = Double.parseDouble(fund[0]);
                double y = Double.parseDouble(fund[1]);
                points[count] = new Point2D(x, y);
                String word = Integer.toString(count);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                points[count].draw();
//                StdDraw.text(x, y - 0.05, word);
                count++;
            }
            UF c = new UF(M);
            for (int i = 0; i < M; i++) {
                for (int j = i; j < M; j++) {
                    if (points[i].distanceSquaredTo(points[j]) <= L * L) {
                        c.union(i, j);
//                        points[i].drawTo(points[j]);
                    }
                }
            }
            MyConvexHull t = new MyConvexHull();
            int[][] cal = new int[M][M];
            int[] where = new int[M];
            int w = 0;
            int j;
            int[] x;
            for (int i = 0; i < M; i++) {
                count = 0;
                for (j = 0; j < M; j++) {
                    if (c.find(j) == i) {
                        cal[i][count] = j;
                        count++;
                    }
                }
                if (count >= 1) {
                    where[w] = i;
                    w++;
                }
                Point2D[] cals = new Point2D[count];
                if (count > 0) {
                    for (int k = 0; k < count; k++) {
                        cals[k] = points[cal[i][k]];
                    }
                    x = t.ConvexHullVertex(cals);
                    if (x == null) {
                    } else {
                        fin=fin+x.length;
                    }
                }
                j = 0;

            }
            System.out.println(fin);
        }
    }
}

