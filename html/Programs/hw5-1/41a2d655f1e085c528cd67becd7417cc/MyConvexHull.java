//import java.util.Arrays;

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
      Point2D [] b =new Point2D[N];
      int intq1=0,intq2=0,intq3=0,intq4=0;
      Stack convexhull = new Stack();
      
      for(i=0;i<N;i++){b[i]=a[i];}
      Insertion.sort(a,Point2D.Y_ORDER);
      Insertion.sort(a,a[0].POLAR_ORDER);
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
              if(intq4<N)
              convexhull.push(intq4);
          }
          else if(decide == -1){
              convexhull.push(intq1);
              convexhull.push(intq3);
          }
          else if(decide == 0){
              convexhull.push(intq1);
              convexhull.push(intq3);

          }
      }
      
      size=convexhull.size();
      String[] output=new String[size];
      int[] ans=new int[size];

      for(i=0;i<size;i++){
      output[i]=convexhull.pop().toString();
      ans[i]= Integer.parseInt(output[i]);

      }
      
      int[] orians=new int[size];      
      int k=0;
      for(i=0;i<N;i++){
//          System.out.println(b[i]);
          for(int j=0;j<size;j++){
              if(b[i].compareTo(a[ans[j]])==0){orians[k]=i;k++;}
          }
      }
      
//        for(i=0;i<size;i++){
//            System.out.println(orians[i]); 
//        }
      return  orians;
}

//  
//  public static void sort(Point2D[] a){
//        int N =a.length;
//        for (int i =0; i<N ;i++){
//            for(int j =i;j>0;j--){
//                if(Point2D.Y_ORDER.compare(a[j], a[j-1])<0)
//                    exch(a,j,j-1);
//            }
//           assert isSorted(a, 0, i);
//        }
//        assert isSorted(a);
//    }
//
//    private static void exch(Object[] a, int i, int j) {
//        Object swap = a[i];
//        a[i] = a[j];
//        a[j] = swap;
//    }
//    
//    private static boolean less(Comparable v, Comparable w) {
//        return (v.compareTo(w) < 0);
//    }  
//    
//    private static boolean isSorted(Comparable[] a, int lo, int hi) {
//        for (int i = lo + 1; i <= hi; i++)
//            if (less(a[i], a[i-1])) return false;
//        return true;
//    }
//    
//   private static boolean isSorted(Comparable[] a) {
//        return isSorted(a, 0, a.length - 1);
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int N=10;
        Point2D[] a = new Point2D[N];
        Point2D[] c = new Point2D[N];        
        int i=0,j=0,intt1=0,intt2=4,intt3=2,dd=0;
        int convexsize;
        for (i=0;i<N;i++){
        a[i]=new Point2D(StdRandom.uniform(),StdRandom.uniform());
//        System.out.println(a[i]);
        c[i]=a[i];
        }
        

        
        int [] convex=ConvexHullVertex(a);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(c[0].x(), c[0].y(), 0.01);
        StdDraw.text(c[0].x(), c[0].y()+0.03,Integer.toString(0));
        
        for (i=1;i<N;i++){    
        StdDraw.setPenColor(StdDraw.BLACK);         
        StdDraw.filledCircle(c[i].x(), c[i].y(), 0.01);
        StdDraw.text(c[i].x(), c[i].y()+0.03,Integer.toString(i));
        StdDraw.setPenColor(StdDraw.GREEN);  
        StdDraw.line(c[0].x(), c[0].y(),c[i].x(), c[i].y());
        }  


        convexsize=convex.length;
        for (j=0;j<convexsize-1;j++){
//            System.out.println(convex[j]);
            StdDraw.setPenColor(StdDraw.BLUE);  
            StdDraw.line(c[convex[j]].x(), c[convex[j]].y(),c[convex[j+1]].x(), c[convex[j+1]].y());
            }

    }
    
}
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
      int i,decide;
      int N = a.length,size;
 
      String q1,q2,q3;
      Point2D [] b =new Point2D[N];
      int intq1,intq2,intq3,intq4=0;
      Stack convexhull = new Stack();
      
      for(i =0;i<N;i++){b[i]=a[i];}
      Insertion.sort(a,Point2D.Y_ORDER);
      Insertion.sort(a,a[0].POLAR_ORDER);
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
              if(intq4<N)
              convexhull.push(intq4);
          }
          else if(decide == -1){
              convexhull.push(intq1);
              convexhull.push(intq3);
          }
          else if(decide == 0){
              convexhull.push(intq1);
              convexhull.push(intq3);

          }
      }
      
      size=convexhull.size();
      String[] output=new String[size];
      int[] ans=new int[size];
      for(i=0;i<size;i++){
      output[i]=convexhull.pop().toString();
      ans[i]= Integer.parseInt(output[i]);
      }
      
      int[] orians=new int[size];      
      int k=0;
      for(i=0;i<N;i++){
          for(int j=0;j<size;j++){
              if(b[i].equals(a[ans[j]])){orians[k]=i;k++;}
          }
      }

      return  orians;
}

//  
//  public static void sort(Point2D[] a){
//        int N =a.length;
//        for (int i =0; i<N ;i++){
//            for(int j =i;j>0;j--){
//                if(Point2D.Y_ORDER.compare(a[j], a[j-1])<0)
//                    exch(a,j,j-1);
//            }
//           assert isSorted(a, 0, i);
//        }
//        assert isSorted(a);
//    }
//
//    private static void exch(Object[] a, int i, int j) {
//        Object swap = a[i];
//        a[i] = a[j];
//        a[j] = swap;
//    }
//    
//    private static boolean less(Comparable v, Comparable w) {
//        return (v.compareTo(w) < 0);
//    }  
//    
//    private static boolean isSorted(Comparable[] a, int lo, int hi) {
//        for (int i = lo + 1; i <= hi; i++)
//            if (less(a[i], a[i-1])) return false;
//        return true;
//    }
//    
//   private static boolean isSorted(Comparable[] a) {
//        return isSorted(a, 0, a.length - 1);
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int N=10;
        Point2D[] a = new Point2D[N];
        Point2D[] c = new Point2D[N];        
        int i=0,j=0;
        int convexsize;
        for (i=0;i<N;i++){
        a[i]=new Point2D(StdRandom.uniform(),StdRandom.uniform());
        c[i]=a[i];
        }
        

        
        int [] convex=ConvexHullVertex(a);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(c[0].x(), c[0].y(), 0.01);
        StdDraw.text(c[0].x(), c[0].y()+0.03,Integer.toString(0));
        
        for (i=1;i<N;i++){    
        StdDraw.setPenColor(StdDraw.BLACK);         
        StdDraw.filledCircle(c[i].x(), c[i].y(), 0.01);
        StdDraw.text(c[i].x(), c[i].y()+0.03,Integer.toString(i));
        StdDraw.setPenColor(StdDraw.GREEN);  
        StdDraw.line(c[0].x(), c[0].y(),c[i].x(), c[i].y());
        }  


        convexsize=convex.length;
        for (j=0;j<convexsize-1;j++){
            StdDraw.setPenColor(StdDraw.BLUE);  
            StdDraw.line(c[convex[j]].x(), c[convex[j]].y(),c[convex[j+1]].x(), c[convex[j+1]].y());
            }

    }
    
}

