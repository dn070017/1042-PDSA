

import java.util.Arrays;


public class MyConvexHull {

     public static int[] ConvexHullVertex(Point2D[] a) {
         int N = a.length;
 
         Point2D[] temp = new Point2D[N];
         for (int i = 0; i < N; i++) {
          temp[i]= a[i];
         }
         Arrays.sort(a);
         Point2D p = new Point2D(a[0].x(), a[0].y());
         Arrays.sort(a, p.POLAR_ORDER);
         int[] v = new int[N];
         v[0]=0;
         v[1]=1;     
         int num = 2;
         for (int k = 2; k < N; k++) {
             Point2D aa = a[v[num-2]];
             Point2D b = a[v[num-1]];
             Point2D c = a[k];
             double ccw = (b.x()-aa.x())*(c.y()-aa.y()) - (b.y()-aa.y())*(c.x()-aa.x());  

             if(ccw > 0){

                 v[num]=k;
                 num++;
             } else  {
                 num--;
                 k--;
                 }
         }
         int[] Vertex = new int[num];
         int x = 0;        
         for(int i = 0; i < N; i++) {  
             for(int j = 0; j < num; j++) {
                if (temp[i].distanceTo(a[v[j]])==0){
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

