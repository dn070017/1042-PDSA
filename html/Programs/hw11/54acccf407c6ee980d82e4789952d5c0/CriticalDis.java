import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
/*
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
*/


public class CriticalDis
{
    public static Point2D[] points;
    private static Digraph di;

    public static class Edges
    {
        private double length;
        private int pointOne;
        
        private int pointTwo;
        
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
              count ++;
            }
          }

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
          di = new Digraph(count);
          while(!edges.isEmpty())
          {
              Edges E1 = edges.delMin();
              int v1 = E1.pointOne;
              int v2 = E1.pointTwo;

              if(points[v1].x()<points[v2].x())
                  di.addEdge(v1, v2);
              else
                  di.addEdge(v2, v1);
              DirectedDFS ddfs = new DirectedDFS(di, minIndex);
              if(ddfs.marked(maxIndex))
              {
                  System.out.printf(""%.3f"",E1.length);
                  break;
              }
          }
                
    }
    
}

