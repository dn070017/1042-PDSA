
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author USER
 */
public class MyConvexHull {
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] data=new Point2D[a.length] ;
        Point2D[] sortdata=new Point2D[a.length] ;
        for (int i=0 ; i<a.length ; i++){
        double x=a[i].x();
        double y=a[i].y();
        data[i]=new Point2D(x,y);
        sortdata[i]=new Point2D(x,y);
        }
//--------------------Find the less y and set to data[0]----------------------//
        int minindex=0; 
        double miny=sortdata[0].y();
        for (int i =0 ; i<a.length ; i++)
            if (sortdata[i].y()<miny){
            miny=sortdata[i].y();
            minindex=i;
            }
        double maxx=sortdata[minindex].x();
        for (int i=0 ; i<a.length ;i++)
        if(sortdata[i].y()==miny && sortdata[i].x()>maxx){
            maxx=sortdata[i].x();
            minindex=i;
        }
        Point2D swap=sortdata[minindex];
        sortdata[minindex]=sortdata[0];
        sortdata[0]=swap;
//-----------------------------Sort by polar angle----------------------------//
        Selection.sort(sortdata,sortdata[0].POLAR_ORDER);
//------------------------Store the imformation of sorting--------------------//
        int[] sortimformation=new int[a.length];
        for (int i=0 ; i<a.length ; i++){
        for (int j=0 ; j<a.length ; j++){
            if (data[j].x()==sortdata[i].x()&&data[j].y()==sortdata[i].y())
                sortimformation[i]=j;
                
        }
        }
//--------------------------------Find ccw------------------------------------//
        Stack<Point2D> convexhull=new Stack<Point2D>();
        convexhull.push(sortdata[0]);
        convexhull.push(sortdata[1]);

        for (int i=2 ; i<a.length ; i++){
            Point2D temp1=convexhull.pop();
            Point2D temp2=convexhull.pop();
            if (sortdata[i].ccw(temp2,temp1,sortdata[i])!=0){
            while(sortdata[i].ccw(temp2,temp1,sortdata[i])<0){
                temp1=temp2;
                temp2=convexhull.pop();
                
               }
            convexhull.push(temp2);
            convexhull.push(temp1);
            convexhull.push(sortdata[i]);
            }
            else{
                convexhull.push(temp2);
                convexhull.push(sortdata[i]);
            }
        }
        int counter=0;
        int[] ans=new int[convexhull.size()];
        while(convexhull.size()!=0){
            Point2D temp=convexhull.pop();
            for(int i=0 ; i<a.length ; i++){
                if (sortdata[i].equals(temp)){
                ans[counter]=sortimformation[i];
               }
            }
            counter++;
        }
       Arrays.sort(ans);
     return (ans);
    }
  public static void main(String[] args)throws Exception {
     try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
//----------------------------Read the first line-----------------------------//
         String FirstLine=br.readLine();
         double threshold=Double.parseDouble(FirstLine);
//----------------------------Read the second line----------------------------//
         String SecondLine=br.readLine();
         int N=Integer.parseInt(SecondLine);
     Point2D[]data=new Point2D[N]; 
     String str=null;
     int counter1=0;
     while ((str=br.readLine())!=null){
         String[] inputxy=str.split("" "");
         double x=Double.parseDouble(inputxy[0]);
         double y=Double.parseDouble(inputxy[1]);
         data[counter1]=new Point2D(x,y);
         counter1++;
     }
     WeightedQuickUnionUF connection=new WeightedQuickUnionUF(N);
     for (int i=0 ; i<N ; i++)
         for (int j=0 ; j<N ; j++)
              if(i!=j && data[i].distanceTo(data[j])<=0.35)
                 connection.union(i,j);
      int[] connectimformation=new int[N];
      for (int i=0 ; i<N ; i++)
          connectimformation[i]=connection.find(i);
      int[] ccroot=new int[connection.count()];
      int ccroot_i=0;
      for (int i=0 ; i<N ; i++){
          if (connectimformation[i]==i){
              ccroot[ccroot_i]=i;
              ccroot_i++;
          }
      }
      for (int i=0 ; i<ccroot.length ; i++){
          for (int j=0 ; j< N ; j++)
              if (connectimformation[j]==ccroot[i])
                  connectimformation[j]=i;
      }
////////////////////////////////////////////////////////////////////////////////
      int temp=0,ans=0;    
//      for (int i=0 ; i<N ; i++){
//          if(i==connectimformation[i]){
//              connectimformation[i]=temp;
//              for (int j=0 ; j<N ; j++)
//                  if (connectimformation[j]==i)
//                      connectimformation[j]=temp;
//          temp++;
//          }
//        }
////////////////////////////////////////////////////////////////////////////////
//      for (int i=0 ; i<N ; i++)
//          System.out.print(connectimformation[i]+""\t"");
//          System.out.println("""");
      for (int i=0 ; i<connection.count() ; i++){
          int counter2=0;
          for (int j=0 ; j<N ; j++){
              if (connectimformation[j]==i)
                  counter2++;
          }
          Point2D[] cc=new Point2D[counter2];
          int counter3=0;
          for (int j=0 ; j<N ; j++)
              if (connectimformation[j]==i){
              cc[counter3]=data[j];
              counter3++;
                  }
          if (cc.length>2){
         int[] d=ConvexHullVertex(cc);
         ans=ans+d.length;
          }
      }
      System.out.println(ans);
//-------------------------------Show the ans---------------------------------//
        //StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(data[0].x(),data[0].y(), 0.01);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.text(data[0].x(),data[0].y()+0.02,String.valueOf(0));
//        for (int i=1 ; i<N ; i++){
//           StdDraw.filledCircle(data[i].x(), data[i].y(), 0.01);
//           StdDraw.text(data[i].x(), data[i].y()+0.02, String.valueOf(i));
//        }
//           System.out.println("""");
//        for (int i=0 ; i<d.length ; i++)
//            System.out.println(d[i]);
     }
 }
}

