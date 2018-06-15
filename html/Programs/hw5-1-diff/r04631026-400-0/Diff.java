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
}
