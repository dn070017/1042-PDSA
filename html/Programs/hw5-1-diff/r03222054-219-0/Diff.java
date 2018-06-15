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
import java.awt.geom.Point2D;


public class MyConvexHull 
{      
    //private Point2D[] array;
    
    static public double cross( Point2D o, Point2D a, Point2D b)
    {
          return (a.getX() - o.getX()) * (b.getY() - o.getY()) - (a.getY() - o.getY()) * (b.getY() - o.getY());
    }

   static boolean  compare(Point2D a, Point2D b)
  {
      return (a.getY() < b.getY()) || (a.getY() == b.getY() && a.getX() < b.getX());
  }

    static boolean compare_angle(Point2D origin, Point2D a, Point2D b)
  {
      // 加入角度相同時，距離長度的判斷。
        double c = cross(origin, a, b);
      return c > 0 ||
              (c == 0 && origin.distance(a) < origin.distance( b));
  }

    static boolean far(Point2D o, Point2D a, Point2D b)
  {
      return o.distance(a) > o.distance(b);
  }
    
    public static int[] ConvexHullVertex(Point2D[] array)
    {
       int[] sequence = null;
       int count = 0;// vertex number
        int start = 0;
        for (int i=0; i<array.length; i++)
        {
            if(compare(array[i], array[start]))
                start = i;
        }
        count++;
        sequence[1] = start;
        
        int next = start;
        while(true)//until the vertex back to origin
        {
            for( int i = 0; i<array.length; i++)
            {
                double angle = cross(array[sequence[count-1]], array[i], array[next]);
                if (angle > 0 ||angle == 0 && far(array[sequence[count-1]], array[i], array[next]))
                next = i;
            }
             if (next == start) break;   // 繞一圈後回到起點了
                sequence[count] = next; 
            count++;
        }
        
        return sequence;       
        }

    
    
    
    
    public static void main(String[] argv)
    {



    }
}
