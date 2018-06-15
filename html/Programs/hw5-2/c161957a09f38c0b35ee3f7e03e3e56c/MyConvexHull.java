/*
.
 * To change this template file, choose Tools | Templates
.
 */


import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Point2D;
import static edu.princeton.cs.algs4.Point2D.area2;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.UF;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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
            Point2D[] a = null;
           
            int[] count =null;
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();//D的直
            double distance = Double.parseDouble(line);
            
            line = br.readLine();//測資長度
            int lengths = Integer.parseInt(line);
            count = new int[lengths];
            UF uf = new UF(lengths);
            //System.out.println(lengths);
            a = new Point2D[lengths];
            int i = 0;
            //StdDraw.setPenColor(StdDraw.BLACK);
          //  StdDraw.setPenRadius(.01);
            while ((line = br.readLine()) != null) {

                String[] buffer = line.split("" "");
                double x = Double.parseDouble(buffer[0]);
                double y = Double.parseDouble(buffer[1]);
                a[i] = new Point2D(x, y);
              // StdDraw.point(a[i].x(), a[i].y());
              // StdDraw.text(a[i].x(), a[i].y(), a[i].x()+"" ""+a[i].y());
               // System.out.println(a[i]);
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
            // System.out.println(u);           
        }   
         int output =0;
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
                 }
                    
                 }
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
        
     Arrays.sort(a,v.atan2Order());  
      int[] index;
        index = new int[a.length];
        index[0] = 0;
        index[1]=1;
       
      
     int co =0;
     
     while(co!=(a.length-1)){
         double area = area2(a[index[co]], a[index[co+1]],a[index[co+1]+1] );
            for (int i =co+2;i<a.length;i++) {
                
                double areaa = area2(a[index[co]], a[index[co+1]],a[i] );
              // System.out.println(i+""= ""+areaa);
                
                if (areaa>area)
                {
                    index[co+2] = i;
                  //  System.out.println(""= ""+i+""co""+co+2);
                area = areaa;
               
                }
              
            }
            
            co++;
             // System.out.println(""co+2 =="" +co);
//           /* if (index[co+2]!=a.length-1)
//            {   
           // System.out.println(co+""             231546453"");
//            else
//                break;*/
                           
}
int count = 0;
   for(int i =(index.length)-1;i>0;i--)
   { if (index[i]!=0)
   {count =i;
  // System.out.println(count+""count"");
   break;
  }
   }
   
        return count+2;
    }

}

