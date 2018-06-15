
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
/*
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Digraph;
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import edu.princeton.cs.algs4.UF;

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
    private int number;
    private Digraph gr;
    private  UF UnionFind;
    public boolean isConnected(Point2D start, Point2D end)
    {
        
        return false;
    }
    
    
    public static class Vertex
    {
        private Point2D point;
        public Point2D getPoint()
        {
            return point;
        }
        private int index;
        public int getIndex()
        {
            return index;
        }
        public Vertex(Point2D pt, int x)
        {
            point = pt;
            index = x;
        }
        public static  Vertex[] vertexArray;
        
    }
    
    
    
    
    public static class Edge
    {
        private double length;
        public double getLength()
        {
            return length; 
        }
        
        private Vertex pointOne;
        public Vertex getPointOne()
        {
            return pointOne;
        }
        
        private Vertex pointTwo;
        public Vertex getPointTwo()
        {
            return pointTwo;
        }
        
        public Edge(Vertex one, Vertex two)
        {
            pointOne = one;
            pointTwo = two;
            length = one.point.distanceTo(two.point);
        }
        
        public void set(Vertex one, Vertex two)
        {
            pointOne = one;
            pointTwo = two;
            length = one.point.distanceTo(two.point);
        }
    }
    
    
     public static class  EdgeComparator implements Comparator<Edge> 
     {
        public int compare(Edge o1, Edge o2)
        {
            return 1*Double.compare(o1.length, o2.length);
        }
     }
     
    public static class VertexComparator implements Comparator<Vertex> 
     {
        public int compare(Vertex o1, Vertex o2)
        {
            return 1*Double.compare(o1.point.x()+o1.point.y(), o2.point.x()+o2.point.y());
        }
     }
     
     
     
     
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {
        int count = 0;
        int number;
        Point2D[] points;
          try(BufferedReader br = new BufferedReader(new FileReader(""in.txt"")))
          { 
              number = Integer.parseInt(br.readLine());
              points = new Point2D[number];
              Vertex.vertexArray = new Vertex[number];

            for(String in = br.readLine(); in != null; in = br.readLine()) 
            {
              String[] p = in.split("" "");
             Point2D pt =(new Point2D(Double.parseDouble(p[0]), Double.parseDouble(p[1])));
              Vertex vt = new Vertex(pt,count);
              Vertex.vertexArray[count]=vt;
              count ++;
            }
          }
          
          ArrayList<Vertex> list = new ArrayList<Vertex>(Arrays.asList(Vertex.vertexArray));
          Collections.sort(list, new VertexComparator());
   //      System.out.print(list.get(0).index+""\n"");
          
         Vertex start = list.get(0);
         Vertex end = list.get(count-1);
         
          MinPQ<Edge> edges = new MinPQ<Edge>(count*(count-1)/2,new EdgeComparator());
          for(int i = 0; i<count; i++)
          {
              for(int j = 0; j < i; j++)
              {
                  Point2D a = list.get(i).point;
                  Point2D b = list.get(j).point;
                  if((a.x()-b.x())*(a.y()-b.y())>0)
                  edges.insert(new Edge(list.get(i), list.get(j)));
              }
          }
           UF UnionFind = new UF(count);
           double distance=2;
       //    System.out.print(start.index+"" ""+ end.index+""\n"");
         while(!UnionFind.connected(start.index, end.index))
                // edges.isEmpty())//!UnionFind.connected(start.index, end.index))
         {
          Edge E1 = edges.delMin();
          Vertex v1 = E1.pointOne;
          Vertex v2 = E1.pointTwo;
          distance = E1.getLength();
           //System.out.print(v1.index+""  ""+v2.index+""\n"");
           //System.out.print(distance+""\n"");
          
           UnionFind.union(v1.index, v2.index);
         }
          
          
          
    //     System.out.print(distance+""\n"");
         System.out.printf(""%1.3f\n"", distance);
      //   System.out.print(UnionFind.connected(start.index, end.index)+""\n"");
       //   System.out.print(Vertex.vertexArray[2].point.distanceTo(Vertex.vertexArray[19].point)+""\n"");
       //   System.out.print(Vertex.vertexArray[14].point.distanceTo(Vertex.vertexArray[2].point)+""\n"");


    }
    
}

