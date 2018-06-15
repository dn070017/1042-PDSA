/*
.
 * To change this template file, choose Tools | Templates
.
 */







import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
//import edu.princeton.cs.algs4.*;
/**
 *
 * @author YuChing
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        try {
                   
            int[] count =null;
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();//D的直
            //System.out.println(line);
            double distance = Double.parseDouble(line);
            
            line = br.readLine();//測資長度
            int lengths = Integer.parseInt(line);
            count = new int[lengths];
            UF uf = new UF(lengths);
            //System.out.println(lengths);
            
           Point2D[] a = new Point2D[lengths];
           
            int i = 0;
//            StdDraw.setPenColor(StdDraw.BLACK);
//            StdDraw.setPenRadius(.01);
            while ((line = br.readLine()) != null) {

                String[] buffer = line.split("" "");
                double x = Double.parseDouble(buffer[0]);
                double y = Double.parseDouble(buffer[1]);
                a[i] = new Point2D(x, y);
//              StdDraw.point(a[i].x(), a[i].y());
//               StdDraw.text(a[i].x(), a[i].y(), a[i].x()+"" ""+a[i].y());
//               // System.out.println(a[i]);
                i++;
            }
            int p =0;
            for(int first=0;first<lengths;first++)
        {
            for(int second=first+1;second<lengths;second++)
            {
             double d = a[first].distanceTo(a[second]);
             if (d<=distance) {                 
                 uf.union(first, second);                
              // StdDraw.line(a[first].x(), a[first].y(), a[second].x(), a[second].y());                
             } else {            }
             
            }
        }
           
                    
         for(int first=0;first<lengths;first++)
        {   int u =uf.find(first);     
             for(int j=0;j<lengths;j++)
             {if(u==j)
                 count[u]++;}
         //    System.out.println(u);           
        }   
         int output =0;
         int aaa = 0;
         for(int j=0;j<lengths;j++)
             {
                 if(count[j]>2)
                 {
                     int groupfit = 0;
                      Point2D[] group = null;
                     group = new Point2D[count[j]];
                     for(int q=0;q<lengths;q++)
                 {if(uf.find(q)==j)
                 {
                     group[groupfit]=a[q];
                    // System.out.println(group[groupfit]);           
                 groupfit++;}
                     }               
               output=output+ ConvexHullVertex(group);     
               aaa++;
                 }
                    
                 }
       //  if (aaa == 1)
     //  System.out.println(""                  ""+(output+1));
         //else
             System.out.println(output);
         //////////////////////////////////////////////////////////////////upper
        } catch (IOException e) {
            System.out.println(e);
        }
        //create an edge for each pair of points with a distance <= d
       
    }

    public static int ConvexHullVertex(Point2D[] a) {
        
        Point2D v;
        Point2D[] b;
      int[] index;
      index=new int[a.length];
      index[0]=1;
      index[1]=1;
     
        b =a;     
        double low;
        low = a[0].y();
         v = a[0];
        //find the corner
        for (Point2D a1 : a) {
            if (a1.y() > low) {
            } else {
                low = a1.y();
                v = a1;
            }
        }
        //System.out.println(""v""+v);
        Arrays.sort(a,v.DISTANCE_TO_ORDER);
 Arrays.sort(a,v.ATAN2_ORDER);
 ////////////sort
 
 for(int i=0;i<a.length;i++ )
 {//System.out.println(a[i]);
 }     
 int p1 =0;
 int p2 =1;

 
 
 for(int i =0;i<a.length-1;i++)
 {
   int ccTv = Point2D.ccw(a[p1], a[p2], a[i]);
  // System.out.println(i);
   if(ccTv==0)
   {p2=i;}
   if(ccTv==1)
   {
       int ccTv2 = Point2D.ccw(a[p2], a[i], a[i+1]);
       if(ccTv2==1)
       { index[i]=1;
       p1 = p2;
       p2 = i;
       }
           
   }
  
     
 }
 int haha =0;
for(int i=0;i<index.length;i++ )
 {
     haha = haha+index[i];
   //  System.out.println(index[i]);
 }   
//System.out.println(""haha""+haha);

        return haha+1;
       
}
}

