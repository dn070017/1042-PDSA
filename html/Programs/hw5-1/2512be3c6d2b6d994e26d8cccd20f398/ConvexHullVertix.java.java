
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
                    temp_index[j]=1;
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
    public static void main(String[] args) {
        //StdDraw.setCanvasSize(200, 200);
        //StdDraw.setXscale(0, 5);
        //StdDraw.setYscale(0, 5);
        Point2D[] points = new Point2D[10];
        // TODO code application logic here
        for (int i = 0; i < 10; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            //StdDraw.setPenColor(StdDraw.RED);
            //System.out.println(x+"" ""+y);
            //StdDraw.setPenRadius(.01);
            points[i] = new Point2D(x, y);
            //points[i].draw();
        }

        int[] result = ConvexHullVertex(points);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }

    }
}

