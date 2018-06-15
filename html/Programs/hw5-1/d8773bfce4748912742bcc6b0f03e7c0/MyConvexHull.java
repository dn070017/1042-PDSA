

import java.util.Arrays;
import java.util.Comparator;





public class MyConvexHull {

    public String[] ConvexHullVertex (String[][] a) {
         int N = a.length;
         Point2D[] a1 = new Point2D[N];
         for (int i = 0; i < N; i++) {
            double x = Double.parseDouble(a[i][0]);
            double y = Double.parseDouble(a[i][1]);
            a1[i] = new Point2D(x, y);
        }
         Point2D[] A = a1;
         Arrays.sort(a);
         Point2D p = new Point2D(a1[0].x(), a1[0].y());
         Arrays.sort(a1, p.POLAR_ORDER);
         String[] v = new String[N];
         v[0]="""" + 0;
         v[1]="""" + 1;     
         int num = 2;
         for (int i = 2; i < N; i++) {
             Point2D aa = a1[Integer.parseInt(v[num-2])];
             Point2D b = a1[Integer.parseInt(v[num-1])];
             Point2D c = a1[i];
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
                if (A[i].distanceTo(a1[Integer.parseInt(v[j])])==0){
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

