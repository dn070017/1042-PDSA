/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LinWeiKuan
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import edu.princeton.cs.algs4.Point2D;
import java.util.Arrays;
//import edu.princeton.cs.algs4.MinPQ;
import static java.lang.Math.abs;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;


public class Clustering
{
    static MinPQ<Pair> pairList =new MinPQ<Pair>() ;
   static ArrayList<Cluster> clusters = new ArrayList<Cluster>();
   static int runTime;
 //  static ArrayList<Integer> shit = new ArrayList<Integer>();

   public static class Pair implements Comparable
   {

       Pair(Cluster firstCluster, Cluster lastCluster)
       {
           first =new Cluster(firstCluster);
           last = new Cluster(lastCluster);
           distance = CalculateDistance();
       }
       private Cluster first;
       private Cluster last;
       private double distance;
       
       
       public Cluster getFirst()
       {
           return first;
       }
       
        public Cluster getSecond()
       {
           return last;
       }
        
        public double CalculateDistance()
        {
            return (first.midPoint.distanceTo(last.midPoint));           
        }        

        @Override
        public int compareTo(Object o)
        {
            Pair pair1 = (Pair) o;

            if(this.CalculateDistance()- pair1.CalculateDistance()< 0)
                return -1;
            else 
                return 1;
.
        }
   }
   private static class Cluster implements Comparable
   {
 
       Cluster(Cluster a)
       {
           count = a.count;
           points = new ArrayList<Point2D>();
           for(int i= 0; i<a.count; i++)
           {
               points.add(a.points.get(i));
           }
           index = a.index;
           midPoint = new Point2D(a.midPoint.x(),a.midPoint.y());
       }
       
       Cluster(Point2D x)
       {
           count=1;
           points.add(x);
           midPoint = x;
           index = clusters.size();
          clusters.add(this);           
       }
       
       
     public void merge(Cluster a)
       {
          double midX=(a.count*(a.midPoint.x()) + this.count*(this.midPoint.x()))/(a.count+this.count);
          double midY=(a.count*(a.midPoint.y()) + this.count*(this.midPoint.y()))/(a.count+this.count);
          midPoint= new Point2D(midX, midY);          
          count=a.count+this.count;
          
          for(int i = 0; i<a.count; i++)
          {
             points.add(a.points.get(i));
          }     
          Cluster newCluster = new Cluster(this);
          int tempIndex = this.index;
          int tempAindex = a.index;
          clusters.set(tempAindex, null );
          clusters.set(tempIndex, null );
          newCluster.index = clusters.size();
          clusters.add(newCluster);
       }
      public ArrayList<Point2D> points = new ArrayList<Point2D>();
      public int count=0;
       Point2D midPoint;      
       public int index;
     //  private int mergeTime;

        @Override
        public int compareTo(Object o) {
            Cluster cluster1 = (Cluster) o;
            if(this.count>cluster1.count)
                return -1;
            else if (this.count<cluster1.count)
                return 1;
            else if (this.midPoint.x()>cluster1.midPoint.x())
                return 1;
            else if (this.midPoint.x()<cluster1.midPoint.x())
                return -1;
             else if (this.midPoint.y()>cluster1.midPoint.y())
                return 1;
            else if (this.midPoint.y()<cluster1.midPoint.y())
                return -1;
            
            
       
                return 0;
        }
   }
   

 public static boolean isValid (Pair a)
   {
       Cluster one = a.first;
       Cluster two = a.last;
        if(clusters.get(one.index) == null || clusters.get(two.index)== null)
            return false;
        else 
            return true;
                 
   }

    
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(args[0])); 
        int size = Integer.parseInt(br.readLine());
        Point2D[] array =new Point2D[size];
       int count =0;
       String line;
       double xData = 0;
       double yData = 0;
       
       while((line = br.readLine())!=null )
        {           
            double x=Double.parseDouble(line.split("" "")[0]);
            double y=Double.parseDouble(line.split("" "")[1]);
            Point2D aaa = new Point2D(x,y);
           Cluster m =new Cluster(aaa);
        }
        for(int i =0; i<clusters.size(); i++)
        {
            for(int j = i+1; j<clusters.size(); j++)
            {
                Pair temp = new Pair(clusters.get(i), clusters.get(j));
                pairList.insert(temp);
            }
        }
       int countTimes = size;
        while(countTimes>3)
        {
            Pair smallest = pairList.delMin();
            if(isValid(smallest))
            {
                Cluster clusterOne = smallest.first;
                Cluster clusterTwo = smallest.last;
                clusterOne.merge(clusterTwo);  

                for(int i = 0; i<clusters.size()-1; i++)
                {
                    if(clusters.get(i) != null )
                    {
                       Pair temp = new Pair(clusters.get(i), clusters.get(clusters.size()-1));
                        pairList.insert(temp);
                    }
                }
                countTimes--;
            }
        }
        
        MinPQ <Cluster> remaining = new MinPQ<Cluster>();
        Cluster[] remainingClusters = new Cluster[3];
        int counter=0;
        for(int i =0; i <clusters.size(); i++)
        {
            if(clusters.get(i)!=null)
            {       
                remaining.insert(clusters.get(i));
                remainingClusters[counter] = clusters.get(i);
                counter++;
            }
        }       
        
        MinPQ<Double> values =new MinPQ<Double>();
    //     System.out.print(remaining.size()+""\n"");
         int remainingSize = remaining.size();
        for(int i =0; i<remainingSize;i++)
        {
            Cluster left = remaining.delMin();
           System.out.print(left.count);
           System.out.print(String.format("" %.2f %.2f\n"", left.midPoint.x(),left.midPoint.y()));           
        }
        for(int i =0; i<2; i++)
            for(int j=i+1; j<3;j++)
                for(int k =0; k<remainingClusters[i].count; k++)
                    for(int l =0; l<remainingClusters[j].count; l++)
                    {
                       //  System.out.print(remainingClusters[0].count+""\n"");
                        double distance = remainingClusters[i].points.get(k).distanceTo(remainingClusters[j].points.get(l));
                     //   System.out.print(distance);
                        values.insert(distance);
                    }
         System.out.print(String.format(""%.2f"", values.delMin()));     
        }
    }
        
    



