
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;
import java.util.List;
//import edu.princeton.cs.algs4.UF;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
//import java.awt.geom.Point2D;
//import edu.princeton.cs.algs4.Point2D;



public class MyConvexHull 
{      
    //private Point2D[] array;
    //private boolean[][] connectArray;
    
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
       int[] sequence = new int[a.length] ;
       
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
        while(true)//until the vertex back to origin
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
    public static UF connected(Point2D[] a, double distance)
    {
        boolean[][] connectArray = new boolean[a.length][a.length];
        UF unionArray = new UF(a.length);
        for(int i =0; i<a.length; i++)
        {
            for(int j =a.length-1; j>i; j--)
            {
                if(a[i].distanceTo(a[j]) <= distance)
                {
                    connectArray[i][j] = true;
                    unionArray.union(i,j);
                }
            }
        }
        return unionArray;
        
    }

    
    
    
    
    public static void main(String[] argv) throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(argv[0]));
        String line = br.readLine();
         double distance= Double.parseDouble(line);
         //Double.pa
         line = br.readLine();
         int number = Integer.parseInt(line);
         Point2D[] array = new Point2D[number];
         
         for(int i =0; i<number ;i++)
         {
             String[]data = br.readLine().split("" "");
             array[i] = new Point2D( Double.parseDouble(data[0]), Double.parseDouble(data[1]));
         }
        
         
        UF union= connected(array,distance);
        
       // int count =0;//number of groups
        
        
        
        //int[] sequence =ConvexHullVertex(array);
        //for (int i =0; i<sequence.length ;i++)
        //System.out.print(union.count());
        List<Integer> root = new ArrayList<Integer>();
        List<Integer> parent = new ArrayList<Integer>();
        for (int i =0; i< number; i++)
        {
            root.add (union.find(i)); 
        }
        //System.out.print(root.size());
        
        List<List<Integer>> indexArray= new ArrayList<List<Integer>>();
        for(int i =0; i<root.size(); i++)
        {
            //int index;
            if(!parent.contains(root.get(i)))
            {
                parent.add(root.get(i));
                indexArray.add(new ArrayList<Integer>());
            }
            int index = parent.indexOf(root.get(i));
           
            indexArray.get(index).add(i);
         //   System.out.print(index);
        }
        //System.out.print(""\n"");
        // System.out.print(indexArray.size());
        
       int count =0;
        
        for(int i =0 ; i<indexArray.size(); i++)
        {
            
            if(indexArray.get(i).size()>=3)
            {
              //  System.out.print(i + ""\n"");
                 Point2D[] vertexArray =new Point2D[indexArray.get(i).size()];
                for(int j =0; j< indexArray.get(i).size(); j++)
                {
                    vertexArray[j] = array[indexArray.get(i).get(j)];
                   // System.out.print(indexArray.get(i).get(j));
                }
              //  System.out.print(""\n"");
                count+=ConvexHullVertex(vertexArray).length;
               // System.out.print(ConvexHullVertex(vertexArray).length+""\n"");
                
            }
        }
        
        System.out.print(count); 
        
        }
    }

   
