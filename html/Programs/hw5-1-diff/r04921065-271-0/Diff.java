/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import edu.princeton.cs.algs4.Insertion;
//import edu.princeton.cs.algs4.Merge;
//import edu.princeton.cs.algs4.Point2D;
//import java.io.IOException;
import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Stack;
import java.io.FileReader;
import java.io.BufferedReader;


/**
 *
 * @author 余軒
 */
//利用StdRandom產生N個2D points((用Point2D的array來儲存))
//再搭配StdDraw把這個N個點畫出來

public class MyConvexHull {
    
  //  private static int N = 16;
    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] points = new Point2D[a.length];   //a 為 原始input
        for(int i=0;i<a.length;i++){
            points[i] = a[i];
        }
        
        Merge.sort(points);
        Arrays.sort(points, points[0].POLAR_ORDER);
        
        Stack<Point2D> vals = new Stack<Point2D>();
        
        
        int N = a.length;
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        //if (k1 == N-1) return;        // all points equal
        vals.push(points[0]);
        vals.push(points[k1]);
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        vals.push(points[k2]);
        
        for (int i = k2; i < N; i++) {
            Point2D top = vals.pop();
            while (Point2D.ccw(vals.peek(), top, points[i]) <= 0) {
                top = vals.pop();
            }
            vals.push(top);
            vals.push(points[i]);
        }
        System.out.println(vals.size());
        
        int number = vals.size();
        Point2D[] vertex = new Point2D[number];
        int[] IDarray = new int[number];
        for(int u = 0;u<number;u++){
            vertex[u] = vals.pop();
        
           int count = 0;
      for (int i= 0;i<N;i++)
        for (int j = 0;j<number;j++)
          {
              if (a[i].equals(vertex[j]))
                  IDarray[count]=i;
              count++;
              
          }
        }
        return IDarray;
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
}
    
    
//    public static void main(String[] args) throws IOException {
//        // TODO code application logic here
//
//    int N = 30;
//
//        StdDraw.setCanvasSize(600, 600);
//        StdDraw.setXscale(-100, 700);
//        StdDraw.setYscale(-100, 700);
//        StdDraw.setPenRadius(.02);
//
//        Point2D[] points = new Point2D[N];
//        for (int i = 0; i < N; i++) {
//
//            int x = StdRandom.uniform(600);
//            int y = StdRandom.uniform(600);
//            points[i] = new Point2D(x, y);
//            StdDraw.point(points[i].x(), points[i].y());
//
//        }
//        Merge.sort(points);
//
//        Arrays.sort(points, points[0].polarOrder());
//        for (int i = 0; i < N; ++i) {
//            StdDraw.text(points[i].x(), points[i].y() + 12, String.valueOf(i));
//        }
//
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(.02);
//        points[0].draw();
//        
//        Stack<Point2D> vals = new Stack<Point2D>();
//        
// 
//        int k1;
//        for (k1 = 1; k1 < N; k1++)
//            if (!points[0].equals(points[k1])) break;
//        if (k1 == N) return;        // all points equal
//        vals.push(points[0]);
//        vals.push(points[k1]);
//        // find index k2 of first point not collinear with points[0] and points[k1]
//        int k2;
//        for (k2 = k1 + 1; k2 < N; k2++)
//            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
//        vals.push(points[k2]);    // points[k2-1] is second extreme point
//
//        // Graham scan; note that points[N-1] is extreme point different from points[0]
//        for (int i = k2; i < N; i++) {
//            Point2D top = vals.pop();
//            while (Point2D.ccw(vals.peek(), top, points[i]) <= 0) {
//                top = vals.pop();
//            }
//            vals.push(top);
//            vals.push(points[i]);
//        }
//        System.out.println(vals.size());
//        
//        StdDraw.setPenColor(StdDraw.GREEN);
//        int number = vals.size();
//        Point2D[] vertex = new Point2D[number];
//        for(int u = 0;u<number;u++){
//            vertex[u] = vals.pop();
//           StdDraw.point(vertex[u].x(), vertex[u].y());
//        }
//        
//        
//        
//        /*
//        System.out.println(vals.size());
//        Iterator<Point2D> c = vals.iterator();
//        while(c.hasNext()){
//            Point2D f = c.next();
//            System.out.println(f);
//        }
//        */
//        
//        
//    }
//    }
}



