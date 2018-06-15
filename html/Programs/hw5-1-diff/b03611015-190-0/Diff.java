/*
.
 * To change this template file, choose Tools | Templates
.
 */





import java.util.Arrays;

/**
 *
 * @author Steven
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a){
        Stack<Point2D> hull = new Stack<>();
        
        int N = a.length;
        Point2D[] d = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            a[i] = new Point2D(x, y);
            
        }
        for(int i=0; i<N ;i++){
            d[i] = a[i];
        }
        /*for(int i = 0; i<N; i++){
            if(a[small].compareTo(a[i]) == 1)
                small = i;
        }
        Point2D p = a[small];
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.02);
        a[small].draw();
        
         StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);
        Arrays.sort(a, p.atan2Order());
        for (int i = 0; i < N; i++) {
            p.drawTo(a[i]);
            StdDraw.show(1000);
        }
       */
        Arrays.sort(a, Point2D.Y_ORDER);
        Arrays.sort(a, a[0].POLAR_ORDER);
        hull.push(a[0]);
        hull.push(a[1]);
        
        for(int i=2; i<N; i++){
        Point2D top = hull.pop();
        while(Point2D.ccw(hull.peek(),top,a[i])<=0)
            top = hull.pop();
        hull.push(top);
        hull.push(a[i]);
        }
        int m = hull.size();
        Point2D[] b =new Point2D[m];
        for(int i=0;i<m;i++){
        b[i] = hull.pop();
        }
        int[] c = new int[m];
        for(int i=0;i<m;i++){
            for(int j=0;j<N;j++)
            {
                if(b[i]==d[j])
                    c[i] = j;
            }
        }
   return c;
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        }
    }


