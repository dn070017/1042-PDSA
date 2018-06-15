
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {

//    public static class Point2D {
//
//        public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
//
//        private final double x;
//        private final double y;
//
//        public Point2D(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public static int ccw(Point2D a, Point2D b, Point2D c) {
//            double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
//            if (area2 < 0) {
//                return -1;
//            } else if (area2 > 0) {
//                return +1;
//            } else {
//                return 0;
//            }
//        }
//
//        private class PolarOrder implements Comparator<Point2D> {
//
//            public int compare(Point2D q1, Point2D q2) {
//                double dy1 = q1.y - y;
//                double dy2 = q2.y - y;
//
//                int state = 0;
//
//                if (dy1 == 0 && dy2 == 0) {
//                } else if (dy1 >= 0 && dy2 < 0) {
//                    state = -1;
//                } else if (dy2 <= 0 && dy1 < 0) {
//                    state = +1;
//                } else {
//                    state = -ccw(Point2D.this, q1, q2);
//                }
//                return state;
//            }
//        }
//        
//
//    }

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
            if (last > 9) {
                break;
            }
            int clockwise = Point2D.ccw(newindex[first], newindex[middle], newindex[last]);
            if (clockwise == -1) {          //clockwise                
                middle = first;
                first = convex_index[j-1];
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
        int[] ans = new int[j+2];
        for(int i = 0 ; i < j+2 ; i++){
            for(int k = 0 ; k < 10 ; k++){
                if(newindex[convex_index[i]].x() == a[k].x() && newindex[convex_index[i]].y() == a[k].y())
                    ans[i] = k;
            }
        }
//        for (int i = 0; i < j+2; i++) {
//            System.out.print(ans[i] + "" "");
//        }
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return ans;
    }

    public static void main(String[] args) {
//        StdDraw.setCanvasSize(500, 500);
        //StdDraw.clear(StdDraw.WHITE);
        int N = 10;
        Point2D[] A = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double a = StdRandom.uniform();
            double b = StdRandom.uniform();
            A[i] = new Point2D(a, b);
//            StdDraw.filledCircle(A[i].x(), A[i].y(), 0.01);
//            StdDraw.text(a, b - 0.03, Integer.toString(i));
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(A[i].x() + A[i].y());
//        }
//        System.out.println("" "");
        int[] ans = ConvexHullVertex(A);
        //System.out.println(ans[0] + 1);
        //ans[0] = ConvexHullVertex(A);

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
    }
}

