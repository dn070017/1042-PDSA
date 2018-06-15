

import java.util.Arrays;
import java.util.Comparator;





public class MyConvexHull {

    public String[] ConvexHullVertex (Point2D[] a) {
         Point2D[] A = a;
         int N = a.length;
         for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform(10);
            double y = StdRandom.uniform(10);
            a[i] = new Point2D(x, y);
        }
         Arrays.sort(a);
         Point2D p = new Point2D(a[0].x(), a[0].y());
         Arrays.sort(a, p.POLAR_ORDER);
         String[] v = new String[N];
         v[0]="""" + 0;
         v[1]="""" + 1;     
         int num = 2;
         for (int i = 2; i < N; i++) {
             Point2D aa = a[Integer.parseInt(v[num-2])];
             Point2D b = a[Integer.parseInt(v[num-1])];
             Point2D c = a[i];
             double ccw = (b.x()-aa.x())*(c.y()-aa.y()) - (b.y()-aa.y())*(c.x()-aa.x());
             if(ccw > 0){
                 v[num]="""" + i;
                 num++;
             }
         }
         String[] ConvexHullVertex = new String[num];
         int x = 0;        
         for(int i = 0; i < N; i++) {  
             for(int j = 0; j < num; j++) {
                if (A[i].distanceTo(a[Integer.parseInt(v[j])])==0){
                    ConvexHullVertex[x]="""" + i;
                    x++;
                    break;
                }
             }
         }
         return(ConvexHullVertex);
    }
    
    
    public static void main(String[] args) {

    }
}

