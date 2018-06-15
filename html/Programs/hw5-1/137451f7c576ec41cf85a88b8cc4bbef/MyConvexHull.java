import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {
      public static int[] ConvexHullVertex(Point2D[] a) {
          int size = 0;
           int k = 0;
           size = a.length;
          
        /*   while(a[k]. !=null){
               k++;
               size++;
           }*/
          
                  
               
         //  int[] z = new int[10];
           Arrays.sort(a, Point2D.Y_ORDER);
          // System.out.print(a[0].y());
            
           Point2D p = new Point2D(a[0].x(),a[0].y());
          /*  StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.02);
             p.draw();
           Arrays.sort(a, p.POLAR_ORDER);
            StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
          for(int i =0 ;i<size;i++){
             
                   
                p.drawTo(a[i]);
            StdDraw.show(100);
           // System.out.print(a[i].y());           
           }*/
           int[] con = new int[size];
           for(int q = 0;q<size;q++){
               con[q] = 1;
           }
           Stack<Point2D> hull = new Stack<>();
           Stack<Integer> in = new Stack<>();
           int[] point = new int[size];
          int i = 0;
          for(i = 0;i<size;i++){
              point[i] = i;
          }
           
           hull.push(a[0]);
           in.push(point[0]);
           hull.push(a[1]);
           in.push(point[1]);
           
           for( i = 2;i<size;i++){
           Point2D top = hull.pop();
           int ttop = in.pop();
           while(Point2D.ccw(hull.peek(), top, a[i])<=0){
               top = hull.pop();
               ttop = in.pop();
           }
              hull.push(top);
              in.push(ttop);
              hull.push(a[i]);
              in.push(point[i]);
           }
          i = in.size();
          int[] ans = new int[size];
          for(int  j = i-1;j>=0;j--){
              ans[j] = in.pop();
          }
       /*   for(int j = 0;j<i;j++){
              System.out.print(ans[j]);
          }*/
      return ans;
    }
        public static void main(String[] args) {

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

    }
      
}

