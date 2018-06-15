
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import static java.lang.Math.pow;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        int length = a.length;
        Point2D[] newindex = new Point2D[length];
        Point2D[] sortedindex = new Point2D[length];
        int[] convex_index = new int[length];

        //copy
        for (int i = 0; i < length; i++) {
            double newindex_x = a[i].x();
            double newindex_y = a[i].y();
            newindex[i] = new Point2D(newindex_x, newindex_y);
        }

        //find the min_y and its index is min_index
        double min_y = newindex[0].y();
        int min_index = 0;
        for (int i = 0; i < length; i++) {
            if (newindex[i].y() < min_y) {
                min_y = newindex[i].y();
                min_index = i;
            }
        }

        sortedindex[0] = newindex[0];
        newindex[0] = newindex[min_index];
        newindex[min_index] = sortedindex[0];

//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(newindex[0].x, newindex[0].y, 0.01);
        Arrays.sort(newindex, newindex[0].POLAR_ORDER);
//        for (int i = 0; i < length; i++) {
//            System.out.print(i + "":"");
//            System.out.println(newindex[i].x + newindex[i].y);
//            StdDraw.text(newindex[i].x, newindex[i].y + 0.03, Integer.toString(i));
//        }

        int j = 0;
        int first = 0;
        int middle = 1;
        int last = 2;
        int state = 0;
        while (true) {
//            System.out.print(""first middle last進入判斷"");
//            System.out.print(first);
//                System.out.print("" "" + middle);
//                System.out.println("" "" + last);
//                System.out.println("" -------- "");
            if (last > (length - 1)) {
                break;
            }
            int clockwise = Point2D.ccw(newindex[first], newindex[middle], newindex[last]);
            if (clockwise == -1) {          //clockwise                
                middle = first;
                first = convex_index[j - 1];
                last = last;
                state = 0;

//                System.out.print(""first middle last / conter順時針應變："");
//                System.out.print(first);
//                System.out.print("" "" + middle);
//                System.out.print("" "" + last);
//                System.out.println("" -1"");
            } else if (clockwise == 1) {    //counter-clockwise                
                convex_index[j] = first;
                convex_index[j + 1] = middle;
                convex_index[j + 2] = last;

                first = middle;
                middle = last;
                last = last + 1;

                state++;
//                System.out.print(""convex_index：逆時針應變  "");
//                System.out.print(j + ""： "");
//                System.out.print(convex_index[j]);
//                //System.out.print(j+1 + ""： "");        
//                System.out.print("" "" + convex_index[j+1]);
//                //System.out.print(j+2 + ""： "");
//                System.out.println("" "" + convex_index[j+2]);
//                System.out.print(""first middle last / conter"");
//                System.out.print(first);
//                System.out.print("" "" + middle);
//                System.out.print("" "" + last);
//                System.out.println("" 1"");

            } else if (clockwise == 0) {     //collinear
                middle = first;
                first = first - 1;
                last = last;
                state = 0;

//                System.out.print(""first middle last / conter："");
//                System.out.print(first);
//                System.out.print("" "" + middle);
//                System.out.print("" "" + last);
//                System.out.println("" 0"");
            }
            if (state >= 1) {
                j++;
            } else {
                j--;
            }

        }
        //convex_index[9] = 0;
//        System.out.println(""   "");
////        
//        System.out.println("" "");
//        for (int i = 0; i < j+2; i++) {
//            System.out.println(convex_index[i]);
//        }
//        System.out.println(""-------------------------------"");
        int[] ans = new int[j + 2];
        for (int i = 0; i < j + 2; i++) {
            for (int k = 0; k < length; k++) {
                if (newindex[convex_index[i]].x() == a[k].x() && newindex[convex_index[i]].y() == a[k].y()) {
                    ans[i] = k;
                }
            }
        }
//        for (int i = 0; i < j+2; i++) {
//            System.out.print(ans[i] + "" "");
//        }
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return ans;
    }

    public static void main(String[] args) {
////        StdDraw.setCanvasSize(500, 500);
//        //StdDraw.clear(StdDraw.WHITE);
//        int N = 10;
//        Point2D[] A = new Point2D[N];
//        for (int i = 0; i < N; i++) {
//            double a = StdRandom.uniform();
//            double b = StdRandom.uniform();
//            A[i] = new Point2D(a, b);
////            StdDraw.filledCircle(A[i].x(), A[i].y(), 0.01);
////            StdDraw.text(a, b - 0.03, Integer.toString(i));
//        }
//
////        for (int i = 0; i < N; i++) {
////            System.out.println(A[i].x() + A[i].y());
////        }
////        System.out.println("" "");
//        int[] ans = ConvexHullVertex(A);
//        //System.out.println(ans[0] + 1);
//        //ans[0] = ConvexHullVertex(A);
//
//        // 1. read in the file containing N 2-dimentional points
//        // 2. create an edge for each pair of points with a distance <= d
//        // 3. find connected components (CCs) with a size >= 3
//        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
//        // 5. count the number of points in N serving as a convex hull vertex, print it
        double distance = 0;
        int N = 0;
        String[][] points_read;
        double[][] points = null;

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            distance = Double.parseDouble(br.readLine());
            N = Integer.parseInt(br.readLine());
//            System.out.println(distance);
//            System.out.println(N);

            points_read = new String[N][2];

            for (int i = 0; i < N; i++) {
                String[] inputmatrix = br.readLine().split("" "");
                for (int j = 0; j < 2; j++) {
                    points_read[i][j] = inputmatrix[j];
//                    System.out.println(points_read[i][j]);
                }
            }

            points = new double[N][2];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    points[i][j] = Double.parseDouble(points_read[i][j]);
                }
            }

        } catch (IOException ex) {
            System.out.printf(""Failed to open the file"");
        } catch (IllegalArgumentException ex) {
            System.out.printf(""Failed to open the file"");
        }
        Point2D[] point = new Point2D[N];
        for (int i = 0; i < N; i++) {
            point[i] = new Point2D(points[i][0], points[i][1]);
        }
//
////        StdDraw.setCanvasSize(500, 500);
//        for (int i = 0; i < N; i++) {
//            StdDraw.filledCircle(point[i].x(), point[i].y(), 0.01);
//            StdDraw.text(point[i].x(), point[i].y() - 0.03, Integer.toString(i));
//        }

        QuickFindUF uf = new QuickFindUF(N);
        double point_d;

        int i;
        int j;
        for (i = 0; i < N - 1; i++) {
            for (j = i + 1; j < N; j++) {
                point_d = pow((pow((point[i].x() - point[j].x()), 2) + pow((point[i].y() - point[j].y()), 2)), 0.5);
                if (point_d <= distance) {
//                    System.out.printf(""%d %d\n"", i, j);
                    uf.union(i, j);
                }
            }
        }

        //System.out.print(123);
        Stack<Integer> connect = new Stack<Integer>();

        int num = 0;
        int count = 0;
        int[] mark = new int[N];
        for (int a = 0; a < N; a++) {
            mark[a] = 0;
        }
        
        for (int a = 0; a < N - 1; a++) {
            if (mark[a] == 0) {
                //System.out.print(456);
                count=0;
                mark[a] = 1;
                connect.push(a);
                count++;
                //System.out.print(789);
                for (int b = a + 1; b < N; b++) {
                    if (uf.connected(a, b)) {
                        count++;
                        mark[b] = 1;
                        connect.push(b);
                        
//                        System.out.println(""count:"" + count);
                    }
                }

                Point2D[] cc = new Point2D[count];
                int[] ans = new int[count];
                if (count >= 3) {
                    for (int c = 0; c < count; c++) {
                        ans[c] = connect.pop();
//                        System.out.println(""ans:"" + ans[c]);
                    }
                    for (int c = 0; c < count; c++) {
                        cc[c] = new Point2D(point[ans[c]].x(), point[ans[c]].y());
                    }
                    int[] convex = ConvexHullVertex(cc);
//                    System.out.println(convex.length);
                    
                    num += convex.length;
                }
            }else continue;

        }
        System.out.print(num);

    }

}

