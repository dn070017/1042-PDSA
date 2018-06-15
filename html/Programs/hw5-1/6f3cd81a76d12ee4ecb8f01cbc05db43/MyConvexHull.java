
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.Point2D;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Math;
import java.util.Collections;
import java.lang.Double;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;
import java.util.List;
//import java.awt.geom.Point2D;
//import edu.princeton.cs.algs4.Point2D;



public class MyConvexHull 
{      
    //private Point2D[] array;
    
    static public double cross( Point2D o, Point2D a, Point2D b)
    {
          return (a.x()- o.x()) * (b.y() - o.y()) - (a.y() - o.y()) * (b.x() - o.x());
    }

   static boolean  compare(Point2D a, Point2D b)
  {
      return (a.y() < b.y()) || (a.y() == b.y() && a.x() < b.x());
  }

    static boolean compare_angle(Point2D origin, Point2D a, Point2D b)
  {
      // 加入角度相同時，距離長度的判斷。
        double c = cross(origin, a, b);
      return c > 0 ||
              (c == 0 && origin.distanceTo(a) < origin.distanceTo( b));
  }

    static boolean far(Point2D o, Point2D a, Point2D b)
  {
      return o.distanceTo(a) > o.distanceTo(b);
  }
    
    public static int[] ConvexHullVertex(Point2D[] a)
    {
       int[] sequence = new int[10] ;
       
       int count = 0;// verte/*x number
        int start = 0;
        for (int i=0; i<a.length; i++)
        {
            if(compare(a[i], a[start]))
                start = i;
        }
        count++;
        sequence[0] = start;
        
        int next = start;
        while(count<10)//until the vertex back to origin
        {
            for( int i = 0; i<a.length; i++)
            {
                double angle = cross(a[sequence[count-1]], a[i], a[next]);
                if (angle > 0 ||angle == 0 && far(a[sequence[count-1]], a[i], a[next]))
                next = i;
            }
             if (next == start) 
             {
                // System.out.print(count + ""shit\n"");
                 break;
             }
                    // 繞一圈後回到起點了
             else
                sequence[count] = next; 
            count++;
        }
        
        int[] answer = Arrays.copyOf(sequence, count);      
        
        return answer;       
        }

    
    
    
    
    public static void main(String[] argv)
    {
        Point2D[] array = {new Point2D(0,0), new Point2D(10,0),new Point2D(0,10), new Point2D(10,10), new Point2D(-5,5),new Point2D(5,5)};
        int[] sequence =ConvexHullVertex(array);
        for (int i =0; i<sequence.length ;i++)
        System.out.print(sequence[i] + ""\t"");
        



    }
}
   
