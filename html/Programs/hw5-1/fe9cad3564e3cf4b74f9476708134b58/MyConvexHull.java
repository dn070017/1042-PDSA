import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

/**
.
 */
public class MyConvexHull {
    private static Point2D[] points;
    private static int N, L;
    private static double radius = 0.01;

    public static int[] ConvexHullVertex(Point2D[] a) {

        for (Point2D p:a){System.out.println(""before: ""+p);}
        Arrays.sort(a,a[0].POLAR_ORDER);
        for (Point2D p:a){System.out.println(""after: ""+p);}
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return null;
    }

    public static void main(String[] args) {
        N = 10;//Integer.valueOf(args[0]);
        L = 512;

        // StdDraw setting
        StdDraw.setCanvasSize(L, L);
        StdDraw.setScale(0, L);
        StdDraw.setPenRadius(0.01);


        points = random2DPointArr(10);
        ConvexHullVertex(points);


        // draw line between points
        StdDraw.setPenColor(Color.green);
        StdDraw.setPenRadius();
        for (int i=1; i<points.length; i++)
            points[0].drawTo(points[i]);
        // draw red point
        StdDraw.setPenRadius(radius);
        StdDraw.setPenColor(Color.red);
        points[0].draw();
        // draw points
        StdDraw.setPenColor(Color.black);
        for (int i=1; i<points.length; i++)
            points[i].draw();
        // draw number
        StdDraw.setPenColor(Color.blue);
        for (int i=0; i<points.length; i++) {
            StdDraw.text(points[i].x(), points[i].y() + L*0.025, String.valueOf(i));
        }

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

    }

    public static Point2D[] random2DPointArr(int N) {
        Point2D [] arr = new Point2D[N];
        for (int i=0;i<N;i++) {
            arr[i] = random2DPoint();
            System.out.println(arr[i]);
        }
        return arr;
    }

    public static Point2D random2DPoint() {
        return new Point2D(StdRandom.uniform(),StdRandom.uniform());
    }
}
