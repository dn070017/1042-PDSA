import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;


public class MyConvexHull {
    private static int N, L;
    private static double radius = 0.01;
    private static Point2D[] a;
    
    public static String ConvexHullVertex(Point2D[] a) {
        String s = """";        
        // sorting
        Arrays.sort(a);
        Arrays.sort(a, a[0].POLAR_ORDER);
        
        int result, i1 = 0, i2 = 1, i3 = 2;
        LinkedList<Integer> list = new LinkedList<>();
        Point2D[] tmp = new Point2D[a.length + 2];
        for (int i = 0; i < a.length; i++)
            tmp[i] = a[i];
        tmp[tmp.length - 2] = a[0];
        tmp[tmp.length - 1] = a[1];
        while (i3 < tmp.length) {
            result = Point2D.ccw(tmp[i1], tmp[i2], tmp[i3]);
            // ccw
            if (result == 1) {
                if (list.isEmpty()) {
                    list.addLast(i1);
                }
                else {
                    if (list.getLast() != i1) {
                        list.addLast(i1);
                    }                    
                }
                i1 = i2;
                i2 = i3;
                i3++;
            }
            // cw or collinear
            else {
                if (list.isEmpty()) {
                    i2 = i3;
                    i3++;
                }
                else {
                    i2 = i1;
                    i1 = list.removeLast();
                }
            }
        }
        // display
        while (!list.isEmpty()) {
            s += list.removeFirst();
            s += "" "";
        }
        
        return s;
    } 
    
    public static void main(String[] args) {
        N = Integer.valueOf(args[0]);
//        L = Integer.valueOf(args[1]);
        L = 512;
        a = new Point2D[N];
        
        // create window
        StdDraw.setCanvasSize(L, L);
        StdDraw.setScale(0, L);
        StdDraw.setPenRadius(radius);
        
        // create random points
        int x, y;
        for (int i=0; i<N; i++) {
            x = StdRandom.uniform(L);
            y = StdRandom.uniform(L);
            a[i] = new Point2D(x, y);
        }
        
        String s = """";
        // convex hull
        s = ConvexHullVertex(a);
                
        

    }
}
