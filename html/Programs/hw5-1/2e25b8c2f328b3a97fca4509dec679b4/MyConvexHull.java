
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdDraw;
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
    
    public static int[] ConvexHullVertex(Point2D[] a)
    {
       int[] sequence = null;
       int count = 0;// vertex number
        int start = 0;
        for (int i=0; i<a.length; i++)
        {
            if(compare(a[i], a[start]))
                start = i;
        }
        count++;
        sequence[1] = start;
        
        int next = start;
        while(true)//until the vertex back to origin
        {
            for( int i = 0; i<a.length; i++)
            {
                double angle = cross(a[sequence[count-1]], a[i], a[next]);
                if (angle > 0 ||angle == 0 && far(a[sequence[count-1]], a[i], a[next]))
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
       /*
    int N = 10;
    double yMin = 1;
    int index = 0;
    doublePoint[] array= new doublePoint[N];
   // ArrayList<doublePoint> array = new ArrayList<doublePoint>();
    for ( int i = 0; i<N; i++)
    {
        double x = StdRandom.uniform();
        double y = StdRandom.uniform();
        if (y<yMin)
        {
            yMin = y;
            index = i;
        }
        array[i]=( new doublePoint(x,y));
    }    
    doublePoint min = array[index];
    
     doublePoint last = new doublePoint(array[index].getX()-0.0000001, array[index].getY());
     array[index] = array[N-1];
  //   array.remove(index);
  //   Vector angleArray = new Vector();
    for ( int i = 0; i<N-1; i++)
    {
        doublePoint newPoint = array[i];
        double angle = getAngle(last, min, newPoint);
        newPoint.setAngle(angle);
        array[i]= newPoint;
       
    }    

    Arrays.sort(array);
     //array.sort();
    // Collections.sort(array);
     for( int i =0; i< N-1; i++ )
     {
        System.out.println(array[i].getX());
     }
       */
         
     
     
     
     
     
     /*
     doublePoint temp = array.get(index);
     array.set(index, array.get(0)); 
     array.set(0, temp);

     int count = 0;
     for ( int i = 0; i<array.size()-1; i++)
        {
            double minAngle = 2;
            int minIndex = count+1;
            for(int j =count+1; j<array.size(); j++)
            {
                double angle = getAngle(last, array.get(i), array.get(j));
                if(angle<minAngle)
                {
                    minAngle = angle;
                    minIndex = j;
                }
            }
            temp = array.get(i+1);
            array.set(i+1, array.get(minIndex)); 
            array.set(minIndex, temp);
            last = array.get(i+1);
            count++;
    }
  
     for (int i=0; i<array.size(); i++)
     {
         System.out.print(array.get(i).x+""\n"");
     }
      */

     
        /*
        if (i == index)
        {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.filledCircle( array[i].x , array[i].y ,0.01);
        }
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle( array[i].x , array[i].y ,0.01);
        }
                */
     
        

