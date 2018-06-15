
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stack;
import static edu.princeton.cs.algs4.MergeX.sort;
import java.util.Arrays;
import java.util.Iterator;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] point ){
        Stack<Point2D> hull = new Stack<Point2D>();
        int N = point.length;
        Point2D origin[] = new Point2D[N];
        for(int i=0;i<N;i++){
            origin[i] = point[i];
        }
        int a=0;
        
//        for(int i=0;i<N;i++){
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.text(point[i].x(), point[i].y()-5, Integer.toString(i));
//        }

        sort(point, Point2D.Y_ORDER);
//        StdDraw.setPenColor(StdDraw.RED);
//        point[0].draw();
        sort(point, point[0].polarOrder());
        
//        for(int i=0;i<N;i++){
//            StdDraw.setPenColor(StdDraw.CYAN);
//            StdDraw.text(origin[i].x(), origin[i].y()-8, Integer.toString(i));
//        }
        
        hull.push(point[0]);
        hull.push(point[1]); 
        
//        Iterator<Point2D> k = hull.iterator();
//        while (k.hasNext()){
//            Point2D T = k.next();
//            System.out.printf(T.toString()+"" "");
//        }
//        System.out.printf(""\n"");
        
        for(int i=2;i<N;i++){
            Point2D top = hull.pop();
            while ( (Point2D.ccw(hull.peek(),top,point[i])) <= 0 ){
                top = hull.pop();
            }
            hull.push(top);
            hull.push(point[i]);
        }
        int[] index = new int[hull.size()];
        Iterator<Point2D> j = hull.iterator();
        while (j.hasNext()){
            Point2D P = j.next();
//            StdDraw.setPenColor(StdDraw.MAGENTA);
//            P.draw();
            for(int i=0;i<N;i++){
                if(origin[i].equals(P)){
                    index[a]=i;
                    a++;
//                    System.out.printf(i+"" "");
                }
            }
        }
        Arrays.sort(index);
        return index;
    }
    
    public static void main(String[] args) {

        int N = 10;
        Point2D[] point = new Point2D[N];
        
        StdDraw.setCanvasSize(650, 650);
        StdDraw.setXscale(-20, 120);
        StdDraw.setYscale(-20, 120);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        
        for(int i=0;i<N;i++){
            double a = StdRandom.uniform(0,100);
            double b = StdRandom.uniform(0,100);
            point[i] = new Point2D(a,b);
            point[i].draw();
            StdDraw.text(a, b-2, ""(""+a+"" , ""+b+"")"");
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.text(a, b-3, Integer.toString(i));
        }
        int[]index = ConvexHullVertex(point);
        System.out.printf(""ConvexHullVertex = "");
        for(int i=0;i<index.length;i++){
            System.out.printf(index[i]+"" "");
        }          
    }  
}
