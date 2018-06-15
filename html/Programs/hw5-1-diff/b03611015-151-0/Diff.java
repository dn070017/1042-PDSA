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
    public void ConvexHullVertix(int N){
        Stack<Point2D> hull = new Stack<>();
        int small = 0;
        
        Point2D[] a = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            a[i] = new Point2D(x, y);
            a[i].draw();
        }
        for(int i = 0; i<N; i++){
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
        int n = 0;        
        for(int i=0;i<N;i++){
        if(hull.pop()==a[i])
            b[n]=a[i];
        n++;
        
        }
    

}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        }
        
        // TODO code application logic here
    }

