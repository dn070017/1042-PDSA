import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import  java.lang.Math;
import java.util.Arrays;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Lab304
 */
class Pair implements Comparable<Pair>{
     private Point2D a1;
     private Point2D  a2;
     private int num1;
     private int  num2;
     private double distance ;
     public  Pair(Point2D  a ,Point2D  b,int num1,int num2 ){
         this.a1=a;
         this.a2=b;
         this.num1=num1;
         this.num2=num2;
        // distance =a1.getpoint().distanceTo(a2.getpoint());
         
     }
     public int  point1(){
     return this.num1;
     }
     public int  point2(){
     return this.num2;
     }
     
     public Point2D  getpoint1(){
     return this.a1;
     }
      public Point2D  getpoint2(){
     return this.a2;
     }
     public double getdistance(){
         this.distance =this.a1.distanceTo(this.a2);
     return this.distance;
     }
      //   (Clustering this ,Clustering that)
   //  {double distance =this.getpoint().distanceTo(that.getpoint());
     //}
      public int compareTo(Pair that) {
          if(this.getdistance()>that.getdistance()){return 1;}
          if(this.getdistance()<that.getdistance()){return -1;}
          else return 0;
     }
 }
public class CriticalDis {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
       Point2D[] point;
     int num;
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
          String[] data = br.readLine().split("" "");
               num = Integer.parseInt(data[0]);
               point=new Point2D[num];
              //Point2D[] a=new Point2D[num];
               int source=0;
               int target=0;
              MinPQ<Pair> minpq=new MinPQ<Pair>();
              MaxPQ maxpq=new MaxPQ();
             // Digraph graph=new Digraph(num);
              
              //graph.addEdge(14,2);
              //DepthFirstDirectedPaths dfs=new DepthFirstDirectedPaths(graph,14);
              //System.out.println(dfs.hasPathTo(2));
              
              
         for(int i=0;i<num;i++){
            String[] data1 = br.readLine().split("" "");
         //   a[i]=new Point2D(Double.parseDouble(data1[0]),Double.parseDouble(data1[1]));
            point[i]=new Point2D(Double.parseDouble(data1[0]),Double.parseDouble(data1[1]));
            if(i==0){source=0;target=0;}
            if(point[source].x()+point[source].y()>point[i].x()+point[i].y())
            {source=i;}
            if(point[target].x()+point[target].y()<point[i].x()+point[i].y())
            {target=i;}
          //  System.out.println(a[i]);
       //   System.out.println(cluster[i].getpoint());
          //System.out.println(cluster[i].size());
         }
        //  System.out.println(source);
          //System.out.println(target);
          Pair[] pair=new Pair[num*(num-1)/2];
          int k=0;
           for(int i=0;i<num;i++)
        {for(int j=i+1;j<num;j++)
           {   
               pair[k]=new Pair(point[i],point[j],i,j);
               minpq.insert(pair[k]);
               //    System.out.println(pair[k].point1());
                   
               k=k+1;
           }
        }
           //System.out.println(k);
        Digraph graph=new Digraph(num);
        DepthFirstDirectedPaths dfs=new DepthFirstDirectedPaths(graph,source);
        double dis=0;
           while(!dfs.hasPathTo(target))
           { Pair temp=minpq.delMin();
            
           //  System.out.println(temp.getdistance());
             if((temp.getpoint1().x()-temp.getpoint2().x())/(temp.getpoint1().y()-temp.getpoint2().y())>0)
             {  graph.addEdge(temp.point1(), temp.point2());
             graph.addEdge(temp.point2(), temp.point1());
             dfs=new DepthFirstDirectedPaths(graph,source);
             //System.out.println(temp.point1());
            // System.out.println(temp.point2());
             dis=temp.getdistance();
             }
             
           }
          
          System.out.printf(""%1.3f\n"", dis);
          
          
          
          
        }
        
        
       
        
     /*    for(int i=0;i<num;i++){   
               
             StdDraw.filledCircle(point[i].x(), point[i].y(), 0.01);
               StdDraw.text(point[i].x(), point[i].y()+0.03,Integer.toString(i) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(point[i].x(), point[i].y(), 0.01);
              StdDraw.text(point[i].x(),point[i].y()+0.03,Integer.toString(i) );
               }*/
         
         

       
      /* for(int i=0;i<10;i++)
       {System.out.println(temp[i]);
       }*/
     }
    
}

