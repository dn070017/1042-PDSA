import java.io.BufferedReader;
import java.io.FileReader;


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
    public static void main(String[] args)throws Exception {
 
     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        String[] Dis = br.readLine().split("","");
        double D=Double.parseDouble(Dis[0]);
        String[] Num = br.readLine().split("","");
        int N=Integer.parseInt(Num[0]);
        Point2D [] data = new Point2D[N];
  
        QuickUnionUF uf = new QuickUnionUF(N);
        
        for(int i=0;i<N;i++){
           String[] d = br.readLine().split("" "");
           double pointx=Double.parseDouble(d[0]);
           double pointy=Double.parseDouble(d[1]);
           data[i]=new Point2D(pointx,pointy);

        }
        
        for(int i=0;i<N;i++){
            for(int j=0; j<N; j++){
                if(data[i].distanceTo( data[j]) < D){
                    uf.union(j, i);
//                StdDraw.setPenColor(StdDraw.BLACK);         
//                StdDraw.filledCircle(data[j].x(), data[j].y(), 0.01);
//                StdDraw.text(data[j].x(), data[j].y()+0.03,Integer.toString(j));
//                StdDraw.setPenColor(StdDraw.GREEN);
//                StdDraw.line(data[i].x(), data[i].y(),data[j].x(), data[j].y());
            }
          }
        }
        
        int k =0,cc=0,ans=0;
        for(k=0;k<N;k++){
                int m =0,n=0;
                for(int i=0;i<N;i++){
                    if(k==uf.find(i)){
                        m++;
                        n=1;
                    }
                }

            if(n==1){

                Point2D [] a = new Point2D[m];
                int j=0;
                    for(int i=0;i<N;i++){
                        if((cc)==uf.find(i)){
                         a[j]=data[i];
//                         System.out.println(a[j]); 
                         j++;
                        }
                    }
                if(m>=3){
                int [] convex=ConvexHullVertex(a);
//                System.out.println(convex.length);
                ans=ans+convex.length;
                }

               cc=cc+m;
            }
            
        }
        System.out.println(ans);
            
//            for(int i=0;i<a.length;i++){
//            System.out.println(a[i]);
//            }
            
        
/***HW5-1
//        Point2D[] a = new Point2D[N];
//        Point2D[] c = new Point2D[N];        
//        int i=0,j=0;
//        int convexsize;
//        for (i=0;i<N;i++){
//        a[i]=new Point2D(StdRandom.uniform(),StdRandom.uniform());
//        c[i]=a[i];
//        }   
//        int [] convex=ConvexHullVertex(a);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(c[0].x(), c[0].y(), 0.01);
//        StdDraw.text(c[0].x(), c[0].y()+0.03,Integer.toString(0));
//        
//        for (i=1;i<N;i++){    
//        StdDraw.setPenColor(StdDraw.BLACK);         
//        StdDraw.filledCircle(c[i].x(), c[i].y(), 0.01);
//        StdDraw.text(c[i].x(), c[i].y()+0.03,Integer.toString(i));
//        StdDraw.setPenColor(StdDraw.GREEN);  
//        StdDraw.line(c[0].x(), c[0].y(),c[i].x(), c[i].y());
//        }  
//
//
//        convexsize=convex.length;
//        for (j=0;j<convexsize-1;j++){
//            StdDraw.setPenColor(StdDraw.BLUE);  
//            StdDraw.line(c[convex[j]].x(), c[convex[j]].y(),c[convex[j+1]].x(), c[convex[j+1]].y());
//            }
HW5-1- */
        
        
    }
    }
}

