import java.util.Arrays;
public class MyConvexHull {

    private static int[] con_hull;
    public static int[] ConvexHullVertex(Point2D[] a) {
        int N = a.length;

        double xx = 1;
        double yy = 1;

        double array_y[] = new double[N]; //to find the lowest point, we have to get the minimum y point
        double theta[] = new double[N];
        for (int i = 0; i < N; i++) {

            array_y[i] = a[i].y();
            if (a[i].y() < yy) {
                yy = a[i].y();
                xx = a[i].x();
            }
        }

       
        Arrays.sort(array_y);
        //build an array with theta value
        Point2D miniy = new Point2D(xx, yy);
        for (int i = 0; i < N; i++) {
            double dx = a[i].x() - miniy.x();
            double dy = a[i].y() - miniy.y();
            theta[i] = Math.atan2(dy, dx);
        }
        int theta_int[] = new int[N];
        QuickUnionUF uf = new QuickUnionUF(4000);
        for (int i = 0; i < N; i++) {
            theta_int[i] = (int) (theta[i] * 1000);
            uf.union(theta_int[i], i + 1);
        }
        Arrays.sort(theta_int);
        int sort_number[] = new int[N];
        for (int i = 0; i < N; i++) {
            sort_number[i] = uf.find(theta_int[i]) - 1;
        }
        //5-1 lets see whether its ccw or not
        Stack<Integer> need = new Stack<>();
        if (sort_number.length >= 3) {
            need.push(sort_number[0]);

            int aa = sort_number[0];
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
            need.push(sort_number[N - 1]);
            int num_size = need.size();
            int con_hull[] = new int[num_size];
            for (int i = 0; i < num_size; i++) {
                con_hull[i] = need.pop();
            }
            Arrays.sort(con_hull);

        }
        return con_hull;
    }
}
