/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import edu.princeton.cs.algs4.Point2D;
import java.util.Arrays;
import java.util.Comparator;
/**
 *
 * @author user
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
        public static int[] ConvexHullVertex(Point2D[] a) {

           //StdRandom.uniform();
       
 
       Insertion.sort(a);
       //compareTo(a[0],a[1]);
      Arrays.sort(a,a[0].POLAR_ORDER );
           for(int i=0;i<10;i++){ 
               if(i==0){StdDraw.setPenColor(StdDraw.RED);
             StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
               StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i) );}
         else{
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);}
              StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i) );
       System.out.println(a[i]);}
           //a[1].POLAR_ORDER;
           int[] b=new int[a.length-1];
           int q=0;
           b[q]=0;
           q=q+1;
           b[q]=1;
            for(int i=1;i<9;i++)  {
               int c=i+1;
              for(int j=c+1;j<10;j++){
              if(a[i].POLAR_ORDER.compare(a[c],a[j])==1)
              {c=j;}
              }
              q=q+1;
              b[q]=c;
            // System.out.println(c);
               i=c-1;
              Point2D temp=new Point2D(0,0);
              
 
        }
           
           
            //System.out.print(b);
        return b;
    }

    
    
    public static void main(String[] args) {
    /*    Point2D[] a=new Point2D[10];
             for(int i=0;i<10;i++){
       a[i]=new Point2D(StdRandom.uniform(),StdRandom.uniform());
       //StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
       }
     int[]b=ConvexHullVertex(a);
     System.out.print(b);*/
             
}
   
   
    
}

