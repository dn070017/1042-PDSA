import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class MyConvexHull {

//    public static int[] con_hull;
    public static int[] ConvexHullVertex(Point2D[] a) {
        int N = a.length;

//        Point2D[] a = new Point2D[N];
        double xx = 1;
        double yy = 1;
        for (int i = 0; i < N; i++) {
//            System.out.printf(""%3.34f "", a[i].x());
//            System.out.printf(""%3.3f\n"", a[i].y());
            
            String strval = Integer.toString(i);
            
        }
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(xx, yy, 0.01);

        double array_y[] = new double[N]; //to find the lowest point, we have to get the minimum y point
        double theta[] = new double[N];
        for (int i = 0; i < N; i++) {
            array_y[i] = a[i].y();
            if (a[i].y() < yy) {
                yy = a[i].y();
                xx = a[i].x();
            } else if (a[i].y() == yy) {
                double x_ = a[i].x();
                double y_ = a[i].y() + 0.01;
                a[i] = new Point2D(x_, y_);
            }
        }

        //sort y value
        Arrays.sort(array_y);
        //build an array with theta value
        Point2D miniy = new Point2D(xx, yy);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(xx, yy, 0.01);

        for (int i = 0; i < N; i++) {
            double dx = a[i].x() - miniy.x();
            double dy = a[i].y() - miniy.y();
            theta[i] = Math.atan2(dy, dx);
        }

        int theta_int[] = new int[N];
        QuickUnionUF uf = new QuickUnionUF(4000);
        for (int i = 0; i < N; i++) {
            theta_int[i] = (int) (Math.round(theta[i] * 1000));
            uf.union(theta_int[i], i + 1);
        }

        Arrays.sort(theta_int);
//        for (int i = 0; i < N; i++) {
//            System.out.printf(""%d\n "", theta_int[i]);
//        }
        int sort_number[] = new int[N];
        for (int i = 0; i < N; i++) {
            sort_number[i] = uf.find(theta_int[i]) - 1;
        }
//        for (int i = 0; i < N; i++) {
//            System.out.printf(""%d\n "", sort_number[i]);
//        }

        //5-1 lets see whether its ccw or not
        Stack<Integer> need = new Stack<>();

        need.push(sort_number[0]);

        int aa = sort_number[0];
        if (N > 2) {
            int bb = sort_number[1];
            for (int i = 0; i < N - 2; i++) {

                int cc = sort_number[i + 2];
                while (Point2D.ccw(a[aa], a[bb], a[cc]) != 1) {

                    bb = need.pop();
                    aa = need.peek();

                }
                need.push(bb);
                aa = bb;
                bb = cc;
            }
        }
        need.push(sort_number[N - 1]);
        int num_size = need.size();
        int con_hull[] = new int[num_size];
        for (int i = 0; i < num_size; i++) {
            con_hull[i] = need.pop();
        }
        Arrays.sort(con_hull);
//        for (int i = 0; i < N; i++) {
//            System.out.printf(""%d\n "", con_hull[i]);
//        }

        return con_hull;
    }

    public static void main(String[] args) throws Exception {

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
        try (BufferedReader br = new BufferedReader(new FileReader((args[0])))) {
            String data = br.readLine();
            double d = Double.parseDouble(data);
            String data2 = br.readLine();
            int N = Integer.parseInt(data2);
//            System.out.printf(""%f\n "", d);
//            System.out.printf(""%d\n "", N);
            Point2D[] a = new Point2D[N];
            for (int i = 0; i < N; i++) {
                String[] data3 = br.readLine().split("" "");
                double first = Double.parseDouble(data3[0]);
                double second = Double.parseDouble(data3[1]);
                a[i] = new Point2D(first, second);
//
//                System.out.printf(""%f "", a[i].x());
//                System.out.printf(""%f\n"", a[i].y());
            }
//            calculate the distance and connect
            QuickUnionUF uu = new QuickUnionUF(N);
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (a[i].distanceTo(a[j]) <= d) {
                        uu.union(j, i);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
                String strval = Integer.toString(uu.find(i));
                StdDraw.textLeft(a[i].x(), a[i].y(), strval);

            }
//            System.out.printf(""%d\n "", uu.count());
            int couu[] = new int[N];
            for (int i = 0; i < N; i++) {
                couu[i] = 0;
            }
            for (int i = 0; i < N; i++) {
                couu[uu.find(i)] = couu[uu.find(i)] + 1;
            }
            int total = 0;
            for (int i = 0; i < N; i++) {
                if (couu[i] > 2) {
                    int m = couu[i];
                    Point2D[] b = new Point2D[m];
                    int initial = 0;
                    for (int j = 0; j < N; j++) {
                        if (uu.find(j) == i) {
                            b[initial] = a[j];
                            initial = initial + 1;
                        }

                    }
                    int ans[] = ConvexHullVertex(b);
                    total = total + ans.length;
                }
            }
            System.out.printf(""%d\n "", total);
        }

    }
}

