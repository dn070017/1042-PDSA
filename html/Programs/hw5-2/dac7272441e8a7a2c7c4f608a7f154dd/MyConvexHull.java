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
  
        QuickFindUF uf = new QuickFindUF(N);
        
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

                ans=ans+convex.length;
                }

               cc=cc+m;
            }
            
        }
        System.out.println(ans);
            

            
        

        
        
    }
    }
}
