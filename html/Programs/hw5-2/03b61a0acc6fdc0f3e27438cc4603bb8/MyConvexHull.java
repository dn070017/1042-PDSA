import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a) {
           int N = a.length;
           
           float PointAngle[];
           PointAngle = new float[N];
           
           int pindex[];
           pindex = new int[N+1];
           
           double minx = 0;
           double miny = 0;
           double min = 0;
           int n = 0;
           
           Stack<Integer> convexpoint = new Stack<Integer>(); 
           
           for(int i =0;i<N;i++){
               pindex[i] = i;
               
               if(i==0){
                 miny = a[0].y();
               }
               if(a[i].y()<miny){
                   miny = a[i].y();
                   minx = a[i].x(); 
                   min = i;
               }
           }
           
           for(int i =0;i<N;i++){
               if(i!=min){
                    PointAngle[i] = (float)Math.toDegrees(Math.atan2(a[i].y()-miny,a[i].x()-minx));
               }
           }
           
           int NPointAngle = PointAngle.length;
           float exch;
           int indexexch;
           for (int i = 0; i < NPointAngle; i++) {
                for (int j = i; j > 0 && PointAngle[j]<=PointAngle[j-1]; j--){ 
                    exch = PointAngle[j];
                    PointAngle[j] = PointAngle[j-1];
                    PointAngle[j-1] = exch;
                    
                    indexexch = pindex[j];
                    pindex[j] = pindex[j-1];
                    pindex[j-1] = indexexch;    
                }
           }
           
           pindex[N] = pindex[0];
           
           int icounter = 0;
           int lastccw = 0;
           while(icounter<=N){ 
                   // System.out.println(convexpoint.size());
                    if(convexpoint.size()<3){ convexpoint.push(pindex[icounter]); icounter++;}
                    else if(convexpoint.size()>=3){
                        if(icounter>N) break;
                        
                        int p3=convexpoint.pop();
                        int p2=convexpoint.pop();
                        int p1=convexpoint.pop();
                        
                        if(Point2D.ccw(a[p1],a[p2],a[p3])>=0){
                           convexpoint.push(p1); 
                           convexpoint.push(p2);
                           convexpoint.push(p3);
                           
                           convexpoint.push(pindex[icounter]);
                           icounter++;
                        }
                        else if(Point2D.ccw(a[p1],a[p2],a[p3])<0){                                                                        
                           convexpoint.push(p1);
                           convexpoint.push(p3);
                        } 
                    }       
            }
            
            int counter = convexpoint.size()-2;
            int convexsize =  convexpoint.size()-1; 
            
            int index[];
            index = new int[convexsize];
            int trash = convexpoint.pop();
            
            while(convexpoint.size()!=0){
                index[counter] = convexpoint.pop();
                counter--;
            }
            //===============================
//            for(int o =1;o<convexsize;o++){
//                StdDraw.setPenRadius(0.001);
//                StdDraw.setPenColor(StdDraw.GREEN);
//                StdDraw.line(a[index[o-1]].x(), a[index[o-1]].y(),a[index[o]].x(), a[index[o]].y()); 
//            }
//            StdDraw.line(a[index[convexsize-1]].x(), a[index[convexsize-1]].y(),a[index[0]].x(), a[index[0]].y()); 
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.point(a[index[0]].x(),a[index[0]].y());
            //===============================
            return index;
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1

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
              if(data[i].distanceTo(data[j])<=0.35)
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

