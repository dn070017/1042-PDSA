/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Yue
 */


import static java.lang.Math.atan;
import java.util.Arrays;
import java.util.HashMap;

public class MyConvexHull {
    Stack<Point2D> hull;
    
    // Point2D[] setRedPoints(Point2D[] p) { 設定 Y 值最小的點，並與首項交換 } (第一次)
    public static Point2D[] setRedPointAndSort(Point2D[] p) {
        // 標記 Y 值最小的點為紅色
        Point2D redPoint = p[0];
        int index = 0;
        for (int i = 1; i < p.length; i++) {
            if (redPoint.y() > p[i].y()) {
                redPoint = p[i];
                index = i;
            }
        }
//        StdDraw.setPenColor(StdDraw.RED);
//        redPoint.draw();
        
        // 將 array 首項與紅點互換
        p[index] = p[0];
        p[0] = redPoint;
        
        // 計算紅點到其它點的角度
        double[] degree = new double[p.length];
        for (int i = 0; i < p.length; i++) {
            degree[i] = atan((p[i].x()-redPoint.x())/(p[i].y()-redPoint.y()));
        }
        
        // 把紅點到其它點角度由小至大排序
        Point2D tempPoint = new Point2D(0, 0);
        double tempDegree = 0;
        for (int i = 1; i < p.length; i++) {
           for (int k = i+1; k < p.length; k++) {
              if (degree[i] < degree[k]) {
                  tempPoint = p[i];
                  p[i] = p[k];
                  p[k] = tempPoint;  

                  tempDegree = degree[i];
                  degree[i] = degree[k];
                  degree[k] = tempDegree; 
               }
           }
        }
        
    return p;
    }
    
    // int ccw(Point2D a, Point2D b, Point2D c) { 判斷向量ab與向量bc角度是否為 counter-clockwise }
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area = (b.x()-a.x())*(c.y()-a.y()) - (b.y()-a.y())*(c.x()-a.x());
        
        if (area < 0)  
            return -1; // clockwise
        else if (area > 0) 
            return +1; // counter-clockwise
        else 
            return 0; // collinear
}
    
    
    // int[] ConvexHullVertex(Point2D[] p) { 函式回傳點集合中形成 convex hull 點的 index }
    public static int[] ConvexHullVertex(Point2D[] a) { 
        if (a.length > 2) {
            Stack<Point2D> hull = new Stack<Point2D>();
        
            hull.push(a[0]);
            hull.push(a[1]);
        
           for (int i = 2; i < a.length; i++) {
                Point2D top = hull.pop();
           while (Point2D.ccw(hull.peek(), top, a[i]) <= 0)
                top = hull.pop();
           hull.push(top);
           hull.push(a[i]);
           }
        
           // 判斷點集合中形成 convex hull 點的 index
           int[] p = new int[hull.size()];
           int j = 0;
           for (Point2D q : hull) {
                p[hull.size()- j - 1] = Arrays.asList(a).indexOf(q);
                j++;
           }
        
//        for (int i = 0; i < p.length; i++) {
//            StdOut.print(""/"" + p[i]);
//        }
//        StdOut.println();
        
        // 回傳ConvexHullVertex的index set，編號請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return p;
        }
        else {
            int[] p = null;
        return p;
        }
    }
    
    public static void main(String[] args) {
        // 讀取文字檔
        In in = new In(args[0]);
        
        // 讀取點個數 N
        int N = in.readInt();
        
        // 初始化視窗大小、畫筆粗細
//        StdDraw.setCanvasSize(500, 500);
//        StdDraw.setPenRadius(.01);
        
        
        // 讀入點座標並在平面上畫出
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D(in.readDouble(), in.readDouble());
//            points[i].draw();
        }
        
        // 設定 Y 值最小的點，並排序
        points = setRedPointAndSort(points);
        
        // 設定筆的粗細、顏色，並標記 Y 值最小的點號碼為 0
        HashMap<Integer, Point2D> map = new HashMap<Integer, Point2D> (); // 號碼索引的 HashMap
        HashMap<Integer, Point2D>  tempMap = new HashMap<Integer, Point2D> (); // 移除 vertex 用的HashMap
        map.put(0, points[0]); // 號碼索引的 HashMap
        tempMap.put(0, points[0]); // 移除 vertex 用的HashMap
//        StdDraw.setPenRadius (.001);
//        StdDraw.setPenColor (StdDraw.BLACK);
//        StdDraw.text(points[0].x(), points[0].y()+0.02, Integer.toString(0));
        // 標記其它點的號碼
        for (int i = 1; i < N; i++) {
            map.put(i, points[i]); // 號碼索引的 HashMap
            tempMap.put(i, points[i]); // 移除 vertex 用的HashMap
//            StdDraw.text(points[i].x(), points[i].y()+0.02, Integer.toString(i));
        }
        
        
        int m;
        while (tempMap.size() >= 3) {
            int[] indexOfVertex = ConvexHullVertex(points);
            
            // 輸出形成 convex hull 點的 vertex
            for (int i = 0; i < indexOfVertex.length; i++) {
                for (Integer key : map.keySet()) {
                    if (points[indexOfVertex[i]] == map.get(key)) {
                        tempMap.remove(key);
                        StdOut.print(key + "" "");
                    }
                }
            }
            StdOut.println();
            
            // 點集合拿掉 vertex
            points = new Point2D[tempMap.size()];
            m = 0;
            for (Integer key : map.keySet()) {
                if (tempMap.containsKey(key)) {
                    points[m] = (Point2D) tempMap.get(key);
                    m++;
                }
            }
            
            if (tempMap.size() >= 3) {
                points = setRedPointAndSort(points);
            }
        }
    }
}

