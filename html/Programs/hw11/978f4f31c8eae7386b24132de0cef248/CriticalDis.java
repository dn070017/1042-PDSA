/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Random;

public class CriticalDis {
   
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
 
            Double min=0.0;
            Double max=0.0;

            int Source=0;
            int Target=0;
            
            
     String[] data = br.readLine().split("" "");
            int N = Integer.parseInt(data[0]);
            Point2D[] a = new Point2D[N];
    //讀N個點
            for (int i=0;i<N;i++) {
                String[] DataPoint = br.readLine().split("" "");
                Double x = Double.parseDouble(DataPoint[0]);
                Double y = Double.parseDouble(DataPoint[1]);
                a[i]=new Point2D(x,y);
//                a[i].draw();
//                StdDraw.textLeft(a[i].x(),a[i].y(),Integer.toString(i));
//                StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
                if(i==0){
                    min = x+y;
                    max = x+y;
                    Source =i;
                    Target =i;
                }
                else{
                    if(min.compareTo(x+y)>0){
                        min = x+y;
                        Source =i;
                    }
                    if(max.compareTo(x+y)<0){
                        max = x+y;
                        Target = i;
                    }
                }
            }
            MinPQ<Pair> pq = new MinPQ<Pair>();
            MinPQ<Double> pqS = new MinPQ<Double>();
            Double distance;
            for (int i=0;i<N;i++) {
                pqS.insert(a[Source].distanceTo(a[i]));
                for(int j=0;j<N;j++){
                if(a[i].x()<a[j].x() && a[i].y()<a[j].y()){   
                distance=a[i].distanceTo(a[j]);
               Pair po = new Pair(distance,i,j);               
                pq.insert(po);
                
                }
                }
            }
            Pair Edge;
            Digraph G = new Digraph(N);
            
            Double d=null;
            
            int bagID=0;
            
//distance=pqS.delMin();
            while(!pq.isEmpty()){
                Edge=pq.delMin();
                d=Edge.distance;
               
                //distance=pqS.delMin();
               // System.out.println(d);
                
                if (a[Edge.a].x() < a[Edge.b].x() && a[Edge.a].y() < a[Edge.b].y()){
                    G.addEdge(Edge.a, Edge.b);
                    DirectedDFS dfs = new DirectedDFS(G,Source);
                    if(dfs.marked(Target)){                       
                  break;
              }
                }
                else 
                    continue;
               
            }
            System.out.printf(""%1.3f\n"", d);                       
}
}
   public static class Pair implements Comparable<Pair>{
        private Double distance;
        private int a;
        private int b;
    public Pair (Double distance, int a, int b)
    {
        this.distance = distance;
        this.a = a;
        this.b = b;
    }
    public Double getDouble(){
        return this.distance;
    }
    public int geta(){
        return this.a;
    }
    public int getb(){
        return this.b;
    
    }
    public int compareTo(Pair that) {
        if (that.distance > this.distance)
            return -1;
        else if (that.distance < this.distance)
            return 1;
        else 
            return 0;
        
        
        
        
        
    }
    }   
    

    }


