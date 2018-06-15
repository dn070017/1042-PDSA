

import java.util.Arrays;


public class MyConvexHull {

     public static int[] ConvexHullVertex(Point2D[] a) {
         int N = a.length;
         Point2D[] A = a;
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
         int[] Vertex = new int[num];
         int x = 0;        
         for(int i = 0; i < N; i++) {  
             for(int j = 0; j < num; j++) {
                if (A[i].distanceTo(a[Integer.parseInt(v[j])])==0){
                    Vertex[x]=i;
                    x++;
                    break;
                }
             }
         }
         return(Vertex);
    }
    
    
    public static void main(String[] args) {

    }
}

