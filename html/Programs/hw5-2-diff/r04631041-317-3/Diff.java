/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author stonebreaker
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
    public static int[] ConvexHullVertex(Point2D [] a)
    {
        Point2D[] p = new Point2D[a.length];
        for(int i = 0; i < a.length; i++)
            p[i] = a[i];
        Insertion.sort(p, Point2D.Y_ORDER);
        Insertion.sort(p, p[0].POLAR_ORDER);
        Stack<Point2D> s = new Stack<Point2D>();
        s.push(p[0]);
        s.push(p[1]);
        s.push(p[2]);
        Point2D p1, p2, p3, p0;
        
        int w = 0,index = 2;
        while(index < (p.length))
        {
            p3 = s.pop();
            p2 = s.pop();
            p1 = s.pop();
            w = Point2D.ccw(p1, p2, p3);
          
            if (1 == w)
            {
                s.push(p1);
                s.push(p2);
                s.push(p3);
                index += 1;
                if(index < p.length)
                    s.push(p[index]);
            }
            else
            {
                s.push(p1);
                s.push(p3);
            }
        }
        int [] v = new int[s.size()];
        index = 0;
        while(!s.isEmpty())
        {
            p0 = s.pop();
            for (int i = 0; i < a.length; i++)
                if(p0.equals(a[i]))
                    v[index++] = i;
        }
//        Insertion.;
        Arrays.sort(v);
        return v;
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
     QuickUnionUF connection=new QuickUnionUF(N);
     for (int i=0 ; i<N ; i++)
         for (int j=0 ; j<N ; j++)
              if(i!=j && data[i].distanceTo(data[j])<=0.35)
                 connection.union(i,j);
      int[] cc_count=new int[N];
      for (int i=0 ; i<N ; i++)
          cc_count[connection.find(i)]+=1;
      int ans=0;
      for (int i=0 ; i<N ; i++){
          if (cc_count[i]>=3){
              Point2D[] cc_array=new Point2D[cc_count[i]];
              int count=0;
              for (int j=0 ; j<N ; j++){
                  if (connection.find(j)==i){
                      cc_array[count]=new Point2D(data[j].x(),data[j].y());
                      count++;
                  }
              }
          int[] d=ConvexHullVertex(cc_array);
          ans+=d.length;
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

