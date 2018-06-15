
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Steven
 */
public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Stack<Point2D> hull = new Stack<>();

        int N = a.length;
        Point2D[] d = new Point2D[N];

        for (int i = 0; i < N; i++) {
            d[i] = a[i];
        }

        Arrays.sort(a, Point2D.Y_ORDER);
        Arrays.sort(a, a[0].POLAR_ORDER);
        hull.push(a[0]);
        hull.push(a[1]);

        for (int i = 2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, a[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(a[i]);
        }
        int m = hull.size();
        Point2D[] b = new Point2D[m];
        for (int i = 0; i < m; i++) {
            b[i] = hull.pop();
        }
        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < N; j++) {
                if (b[i] == d[j]) {
                    c[i] = j;
                }
            }
        }
        Arrays.sort(c);
        return c;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String data = br.readLine();
            double distance = Double.parseDouble(data);

            data = br.readLine();
            int N = Integer.parseInt(data);

            Point2D[] datapoint = new Point2D[N];
            
           UF UF = new UF(N);

            for (int i = 0; i < N; i++) {
                data = br.readLine();
                String[] position = new String[2];
                position = data.split("" "");
                datapoint[i] = new Point2D(Double.parseDouble(position[0]), Double.parseDouble(position[1]));

            }

            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if (datapoint[i].distanceTo(datapoint[j]) <= distance) {
                        UF.union(i, j);
                    }
                }
            }

            int[] CC = new int[UF.count()];

            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = UF.find(i);
            }

            Arrays.sort(a);
            CC[0] = a[0];

            int[] CCnumber = new int[UF.count()];
            int k = 0;
            int n = 0;
            int size = 1;

            for (int i = 1; i < N; i++) {
                if (a[i] == CC[n]) {
                    size++;

                }
                if (a[i] > a[i - 1]) {
                    if (size >= 3) {
                        CCnumber[k] = a[i - 1];
                        k++;
                    }
                    size = 1;
                    n++;
                    CC[n] = a[i];
                }
            }

            int leng = 0;

            for (int i = 0; i < k; i++) {

                int count = 0;
                for (int j = 0; j < N; j++) {
                    if (CCnumber[i] == UF.find(j)) {
                        count++;
                    }
                }

                Point2D[] convex = new Point2D[count];

                int num = 0;
                for (int j = 0; j < N; j++) {
                    if (CCnumber[i] == UF.find(j)) {
                        convex[num] = datapoint[j];
                        num++;
                    }
                }
                int[] b = ConvexHullVertex(convex);
                leng += b.length;
            }


            System.out.print(leng);

        }
    }
}




