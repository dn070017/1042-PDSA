
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
//
//import edu.princeton.cs.algs4.MinPQ;
//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
//import edu.princeton.cs.algs4.UF;
//import edu.princeton.cs.algs4.DirectedDFS;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 
 * @author LinWeiKuan
 */
public class CriticalDis
{
    public static Point2D[] points;
    private int number;
    private Digraph gr;
    public boolean isConnected(Point2D start, Point2D end)
    {
        
        return false;
    }
    
    
   
    
    
    
    public static class Edges
    {
        private double length;
        public double getLength()
        {
            return length; 
        }
        
        private int pointOne;
        public Point2D getPointOne()
        {
            return points[pointOne];
        }
        
        private int pointTwo;
        public Point2D getPointTwo()
        {
            return points[pointTwo];
        }
        
        public Edges(int one, int two)
        {
            pointOne = one;
            pointTwo = two;
            length = points[one].distanceTo(points[two]);
        }
        
        public void set(int one, int two)
        {
            pointOne = one;
            pointTwo = two;
            length = points[one].distanceTo(points[two]);
        }
    }
    
    
     public static class  EdgeComparator implements Comparator<Edges> 
     {
        public int compare(Edges o1, Edges o2)
        {
            return 1*Double.compare(o1.length, o2.length);
        }
     }
     

 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {
        int count = 0;
        int number;
        int maxIndex = 0;
        double max =0;
        int minIndex = 0;
        double min =2;
          try(BufferedReader br = new BufferedReader(new FileReader(""in.txt"")))
          { 
              number = Integer.parseInt(br.readLine());
              points = new Point2D[number];
              //Vertex.vertexArray = new Vertex[number];

            for(String in = br.readLine(); in != null; in = br.readLine()) 
            {
              String[] p = in.split("" "");
              double value =Double.parseDouble(p[0]) + Double.parseDouble(p[1]);
              
              if(value>max)
              {
                  maxIndex = count;
                  max =value;
              }
              if(value<min)
              {
                  minIndex = count;
                  min =value;
              }
             Point2D pt =(new Point2D(Double.parseDouble(p[0]), Double.parseDouble(p[1])));
             points[count]=pt;
        //      Vertex vt = new Vertex(pt,count);
           //   Vertex.vertexArray[count]=vt;
              count ++;
            }
          }
          
        //  ArrayList<Vertex> list = new ArrayList<Vertex>(Arrays.asList(Vertex.vertexArray));
         // Collections.sort(list, new VertexComparator());
   //      System.out.print(list.get(0).index+""\n"");
          
         Point2D start = points[minIndex];
         Point2D end = points[maxIndex];
         
          MinPQ<Edges> edges = new MinPQ<Edges>(count*(count-1)/2,new EdgeComparator());
          for(int i = 0; i<count; i++)
          {
              for(int j = 0; j < i; j++)
              {
                  Point2D a = points[i];
                  Point2D b = points[j];
                  if((a.x()-b.x())*(a.y()-b.y())>0)
                  edges.insert(new Edges(i,j));
              }
          }
         
    //      System.out.print(maxIndex+"" "" +minIndex+""\n"");
          Digraph di = new Digraph(count);
          int index = 0;
          while(!edges.isEmpty())
          {
              
              Edges E1 = edges.delMin();
              
              int v1 = E1.pointOne;
              int v2 = E1.pointTwo;
              
              //System.out.print(v1+"" ""+v2+""\n""+ E1.length+""\n"");
              
              if(points[v1].x()<points[v2].x())
                  di.addEdge(v1, v2);
              else
                  di.addEdge(v2, v1);
              DirectedDFS ddfs = new DirectedDFS(di, minIndex);
              // System.out.print(ddfs.marked(maxIndex-1)+""\n"");
              if(ddfs.marked(maxIndex))
              {
                  System.out.printf(""%.3f"",E1.length);
                  break;
              }
              index ++;
          }
          

    }
    
}

