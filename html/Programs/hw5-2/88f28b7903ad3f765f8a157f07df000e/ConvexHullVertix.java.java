
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {

        if (a.length == 0) {
            throw new NullPointerException();
        }
        Point2D[] origin = a.clone();
        Arrays.sort(a, Point2D.Y_ORDER);
        Point2D p = new Point2D(a[0].x(), a[0].y());

        Arrays.sort(a, p.POLAR_ORDER);

        Point2D A = new Point2D(0.0, 0.0);
        Point2D B = A;
        Point2D C = A;
        int count = 0;
        int n_count = 0;
        int k0 = 0;
        Point2D[] point = new Point2D[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            point[i] = a[i];
        }
        if (a.length > 2) {
            point[a.length] = a[0];
        }
        for (int i = 2; i < a.length + 1; i++) {
            for (int j = 0; j <= i - 2; j++) {
                for (int k = j + 1; k <= i; k++) {
                    if (point[j] != null) {
                        A = new Point2D(point[j].x(), point[j].y());
                        if (point[k] != null) {
                            if (count <= 0) {
                                B = new Point2D(point[k].x(), point[k].y());
                                count++;
                                k0 = k;
                            } else {
                                C = new Point2D(point[k].x(), point[k].y());
                                count++;
                            }
                        }
                        if (count == 2 && Point2D.ccw(A, B, C) <= 0) {
                            if (j == 0) {
                                point[k] = null;
                                j = 0;
                                n_count++;
                            } else {
                                point[k0] = null;
                                j = 0;
                                n_count = n_count + 1;
                            }
                        }
                    } else {
                        break;
                    }
                }
                count = 0;
            }

        }

        int[] index = new int[a.length - n_count];
        int l = 0;
        for (int m = 0; m < a.length; m++) {
            for (int n = 0; n < a.length; n++) {
                if (point[m] != null) {
                    if (point[m] == origin[n]) {
                        index[l] = n;
                        l++;
                        break;
                    }
                }
            }
            if (l > (index.length - 1)) {
                break;
            }
        }
        Arrays.sort(index);
        return index;
    }

    public static int[] parent;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            double d = Double.parseDouble(br.readLine());
            int N = Integer.parseInt(br.readLine());
            Point2D[] p = new Point2D[N];
            int temp = 0;

            while (br.ready()) {
                String[] data = br.readLine().split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                p[temp] = new Point2D(x, y);
                temp++;
            }

            Arrays.sort(p, Point2D.Y_ORDER);
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    Point2D X = p[i];
                    Point2D Y = p[j];
                    if (Y.distanceTo(X) <= d) {
                        MyConvexHull.union(j, i);
                    }
                }
            }

            int count = 0;
            Stack<Point2D> sp = new Stack<Point2D>();
            Stack<Integer> sn = new Stack<Integer>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (p[i] != null) {
                        if (p[j] != null) {
                            if (MyConvexHull.connected(i, j)) {
                                sp.push(p[j]);
                                p[j] = null;
                                count++;
                            }
                        }
                    } else {
                        break;
                    }
                }

                if (p[i] != null) {
                    sp.push(p[i]);
                    sn.push((count + 1));
                    p[i] = null;
                }
                count = 0;
            }

            int num = 0;
            while (!sp.isEmpty()) {
                int size = sn.pop();
                Point2D[] a = new Point2D[size];
                for (int i = 0; i < size; i++) {
                    a[i] = sp.pop();
                }
                if (a.length >= 3) {
                    num = num + (MyConvexHull.ConvexHullVertex(a).length);
                }
            }
            System.out.println(num);
        }
    }

    public static int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        parent[rootP] = rootQ;
    }

    public static boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}

