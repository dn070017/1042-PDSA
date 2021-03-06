import java.awt.Color;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Dennis
 */

public class MyConvexHull {
    

public static int[] ConvexHullVertex(Point2D[] a) {     
      int i=0,decide=0;
      int N = a.length,size=0;
      String q1=""0"",q2=""0"",q3=""0"";
      Point2D ddd;
      int intq1=0,intq2=0,intq3=0,intq4=0;
      Stack convexhull = new Stack();
      sort(a);
      Arrays.sort(a,a[0].POLAR_ORDER);
      convexhull.push(0);
      convexhull.push(1);
      convexhull.push(2);
      
      while(intq4<N){

          q3=convexhull.pop().toString();
          intq3=Integer.parseInt(q3);
          q2=convexhull.pop().toString();
          intq2=Integer.parseInt(q2);
          q1=convexhull.pop().toString();
          intq1=Integer.parseInt(q1);
          decide=Point2D.ccw(a[intq1], a[intq2], a[intq3]);
          
          if(decide == 1){
              convexhull.push(intq1);
              convexhull.push(intq2);
              convexhull.push(intq3);
              intq4=intq3+1;
              convexhull.push(intq4);
          }
          else if(decide == -1){
              convexhull.push(intq1);
              convexhull.push(intq3);
              intq4=intq3+1;
              convexhull.push(intq4);     
          }
          else if(decide == 0){
              convexhull.push(intq1);
              convexhull.push(intq3);
              intq4=intq3+1;
              convexhull.push(intq4);    
          }
          if(intq4==N)break;
      }

      size=convexhull.size();
      String[] output=new String[size];
      int[] ans=new int[size];
      for(i=0;i<size;i++){
      output[i]=convexhull.pop().toString();
        ans[i]= Integer.parseInt(output[i]);
      }

      return  ans;
}

  


  
  public static void sort(Point2D[] a){
        int N =a.length;
        for (int i =0; i<N ;i++){
            for(int j =i;j>0;j--){
                if(Point2D.Y_ORDER.compare(a[j], a[j-1])<0)
                    exch(a,j,j-1);
            }
           assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }  
    
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    
   private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int N=10,convexsize;
        Point2D[] a = new Point2D[N];
        
        int i=0,j=0,intt1=0,intt2=4,intt3=2,dd=0;

        
        for (i=0;i<N;i++){
        a[i]=new Point2D(StdRandom.uniform(),StdRandom.uniform());
        System.out.println(a[i]);
        }
        
        System.out.println(ConvexHullVertex(a));

        
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(a[0].x(), a[0].y(), 0.01);
        StdDraw.text(a[0].x(), a[0].y()+0.03,Integer.toString(0));
        
        for (i=1;i<N;i++){    
        StdDraw.setPenColor(Color.BLACK);         
        StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
        StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i));
        StdDraw.setPenColor(Color.GREEN);  
        StdDraw.line(a[0].x(), a[0].y(),a[i].x(), a[i].y());
        }

        int [] convex=ConvexHullVertex(a);
        convexsize=convex.length;
        int[] cox=new int[convexsize];
        j=0;
        for (i=convexsize-1;i>0;i--){
            System.out.println(convex[i]);
            cox[j]=convex[i];
            j++;
        }
        
        
//        int[]cox=new int[convexsize];
//        cox[0]=0;
//        j=1;
//        for (i=convexsize-2;i>0;i--){ 
//            
//            cox[j]=Integer.parseInt(convex[i]);
//            System.out.println(cox[j]);
//            j++;
//        }
        
        for (j=0;j<convexsize-1;j++){
            StdDraw.setPenColor(Color.BLUE);  
            StdDraw.line(a[cox[j]].x(), a[cox[j]].y(),a[cox[j+1]].x(), a[cox[j+1]].y());
            }
    }
    
}
