
import java.util.Arrays;
import java.util.Stack;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] b = new Point2D[a.length];
        for (int copy = 0; copy < a.length; copy++) {
            b[copy] = a[copy];
        }

        int i = 0;

        for (int j = 1; j < b.length; j++) {
            if (b[i].compareTo(b[j]) == 1) {
                i = j;
            }
        }

        Point2D min = b[i];
        Arrays.sort(b, min.ATAN2_ORDER);
/*
        StdDraw.setPenColor(StdDraw.RED);
        min.draw();
        StdOut.println(""==="" + min);

        

        for (int h = 0; h < 10; h++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            b[h].draw();
            StdOut.println(b[h]);
            StdDraw.show(100);
        }
        StdDraw.setPenColor(StdDraw.RED);
        min.draw();
 */      
        int cc1,cc2,cc3,num=2;
        Stack<Integer> index1 = new Stack<>();
/*
        StdDraw.setPenRadius();
        b[0].drawTo(b[1]);
        StdDraw.show(1000);
*/

        index1.push(0);
        index1.push(1);


        while (true) {
            index1.push(num);
            cc3=index1.pop();
            cc2=index1.pop();
            cc1=index1.pop();        
            
            if (Point2D.ccw(b[cc1], b[cc2], b[cc3]) != 1) {
                while (Point2D.ccw(b[cc1], b[cc2], b[cc3]) != 1) {
                    if (cc1 == 0) {
                        cc1 = b.length - 1;
                    } else {
                        cc2=cc1;
                        cc1=index1.pop();
                    }
                }
            } else {
/*
                StdDraw.setPenColor(StdDraw.RED);
                b[cc1].drawTo(b[cc2]);
                b[cc2].drawTo(b[cc3]);
                StdDraw.show(1000);
*/
            }
            index1.push(cc1);
            index1.push(cc2);
            index1.push(cc3);
            if(cc3==0) break;
            num++;
            if(num == b.length){
                num=0;
            }
        }
        index1.pop();
        //StdOut.println(index1);

        int count = 0, temp;
        int out[] = new int[index1.size()];
        int size = index1.size();
        
        while (count < size) {
            temp = index1.pop();
            i = 0;
            //StdOut.println(temp);
            while (true) {
                if (a[i].equals(b[temp])) {
                    //StdOut.println(i);
                    //StdOut.println(b[temp]);
                    out[count] = i;
                    break;
                }
                i++;
            }
            count++;
        }

        Arrays.sort(out);
        /*for (int h = 0; h < count; h++) {
            StdOut.println(out[h]);
        }
        */
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return out;
    }

    public static void main(String[] args) {

        int N = 10;
/*
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(.01);
*/
        double x, y;

        Point2D[] points = new Point2D[N];
        for (int i = 0; i < 10; i++) {
            x = StdRandom.uniform();
            y = StdRandom.uniform();
            points[i] = new Point2D(x, y);
            //StdOut.println(points[i]);
        }

        // MyConvexHull mch = new MyConvexHull();
        MyConvexHull.ConvexHullVertex(points);
        /*
            Point2D p = new Point2D(minx, miny);
            StdDraw.setPenColor(StdDraw.RED);

            p.draw();

            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.BLUE);
             Arrays.sort(points, p.atan2Order());
           for (i = 0; i < N; i++) {
                p.drawTo(points[i]);
                StdDraw.show(500);
            }*/

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
    }
}

