
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author Po-Lin
 */
public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        double min_y = 10000.0;
        int temp_i = 0;
        for (int i = 0; i < a.length; i++) { //找y最小的點作為頂點
            if (a[i].y() < min_y) {
                min_y = a[i].y();
                temp_i = i;
            }
        }
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(.01);
//        a[temp_i].draw();
        double[] angle = new double[a.length];
        for (int i = 0; i < a.length; i++) { //算角度
            if (i != temp_i) {
                angle[i] = Math.acos((a[i].x() - a[temp_i].x()) / Math.sqrt(Math.pow(a[i].x() - a[temp_i].x(), 2) + Math.pow(a[i].y() - a[temp_i].y(), 2))) * 180 / 3.1415;
            } else {
                angle[i] = 0;
            }
        }

        double[] angle_sort = Arrays.copyOf(angle, a.length);
        Arrays.sort(angle_sort);  //把算出來的角度做sorting

        int[] temp_index = new int[a.length];
        int[] index = new int[a.length]; //將sorting後的點與原array做比較，並將index做sorting
        for (int i = 0; i < angle_sort.length; i++) {
            for (int j = 0; j < angle.length; j++) {
                if (angle_sort[i] == angle[j] && temp_index[j] == 0) {
                    index[i] = j;
                    temp_index[j] = 1;
                    break;
                }
            }
        }
        Stack<Integer> convexhull = new Stack<Integer>();
        convexhull.push(index[0]);
        convexhull.push(index[1]);
        convexhull.push(index[2]);

        int O = 0;
        int A = 0;
        int B = 0;
        for (int i = 3; i < angle_sort.length; i++) { //逆時針去計算外積，若為正值即為外凸點
            B = index[i];
            A = convexhull.pop();
            O = convexhull.pop();
            if ((a[A].x() - a[O].x()) * (a[B].y() - a[O].y()) - (a[A].y() - a[O].y()) * (a[B].x() - a[O].x()) >= 0) {
                convexhull.push(O);
                convexhull.push(A);
                convexhull.push(B);
            } else {
                for (int j = convexhull.size() + 2; j > 2; j--) {
                    A = O;
                    O = convexhull.pop();
                    if ((a[A].x() - a[O].x()) * (a[B].y() - a[O].y()) - (a[A].y() - a[O].y()) * (a[B].x() - a[O].x()) >= 0) {
                        convexhull.push(O);
                        convexhull.push(A);
                        convexhull.push(B);
                        break;
                    }
                }
            }

        }

        int[] Vertex = new int[convexhull.size()];
        for (int i = 0; i < Vertex.length; i++) {
            Vertex[i] = convexhull.pop();
        }
        Arrays.sort(Vertex);   //將結果依照小至大sorting
        return Vertex;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader convexhull_file = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data;
            data = convexhull_file.readLine().split("","");
            if (data != null) {
                double d = Double.parseDouble(data[0]);
                data = convexhull_file.readLine().split("" "");
                int N = Integer.parseInt(data[0]);
                double[][] input_coord = new double[N][2];
                int n = 0;
                while (n < N) //add coord to grid  //讀取input座標
                {
                    data = convexhull_file.readLine().split("" "");
                    input_coord[n][0] = Double.parseDouble(data[0]);
                    input_coord[n][1] = Double.parseDouble(data[1]);
                    //System.out.println(input_coord[n][0] + "" "" + input_coord[n][1]);
                    n++;
                }

                WeightedQuickUnionUF ufstruct = new WeightedQuickUnionUF(N);
                for (int i = 0; i < N; i++) {  //將距離<=d的兩點做相連
                    for (int j = i + 1; j < N; j++) {
                        if (Math.sqrt(Math.pow(input_coord[i][0] - input_coord[j][0], 2) + Math.pow(input_coord[i][1] - input_coord[j][1], 2)) <= d) {
                            ufstruct.union(i, j);
                        }
                    }
                }
                int[] point_find = new int[N]; //將各個點的find存入point_find中
                for (int i = 0; i < N; i++) {
                    point_find[i] = ufstruct.find(i);
                    //System.out.println(point_find[i]);
                }

                int temp = 0;
                int count = 1;
                double[] temp_x = new double[N];
                double[] temp_y = new double[N];
                int NumofConvexhull = 0;

                for (int i = 0; i < N; i++) {
                    temp = point_find[i];
                    if (temp == -1) {
                    } else {
                        point_find[i] = -1;
                        temp_x[0] = input_coord[i][0];
                        temp_y[0] = input_coord[i][1];
                        for (int j = i + 1; j < N; j++) {
                            if (point_find[j] == temp) {
                                point_find[j] = -1;
                                temp_x[count] = input_coord[j][0];
                                temp_y[count] = input_coord[j][1];
                                count++;
                            }
                        }
                        if (count == 3) {
                            NumofConvexhull = NumofConvexhull + 3;
                        } else if (count > 3) {
                            Point2D[] points = new Point2D[count];
                            for (int j = 0; j < count; j++) {
                                double x = temp_x[j];
                                double y = temp_y[j];
                                points[j] = new Point2D(x, y);
                            }
                            int[] result = ConvexHullVertex(points);
                            NumofConvexhull = NumofConvexhull + result.length;
                        }
                    }
                    count=1;
                }
                System.out.println(NumofConvexhull);
            }
        }
    }

//    public static void main(String[] args) {
//        //StdDraw.setCanvasSize(200, 200);
//        //StdDraw.setXscale(0, 5);
//        //StdDraw.setYscale(0, 5);
//        Point2D[] points = new Point2D[10];
//        // TODO code application logic here
//        for (int i = 0; i < 10; i++) {
//            double x = StdRandom.uniform();
//            double y = StdRandom.uniform();
//            //StdDraw.setPenColor(StdDraw.RED);
//            //System.out.println(x+"" ""+y);
//            //StdDraw.setPenRadius(.01);
//            points[i] = new Point2D(x, y);
//            //points[i].draw();
//        }
//
//        int[] result = ConvexHullVertex(points);
////        for (int i = 0; i < result.length; i++) {
////            System.out.println(result[i]);
////        }
//
//    }
}

