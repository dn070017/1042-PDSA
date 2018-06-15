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
            MinPQ<Double> pq = new MinPQ<Double>();
            Double distance;
            for (int i=0;i<N;i++) {
                for(int j=i+1;j<N;j++){
                if(a[i].x()<a[j].x() && a[i].y()<a[j].y()){   
                distance=a[i].distanceTo(a[j]);
                pq.insert(distance);  
                }
                }
            }
            
            Digraph G = new Digraph(N);
            Double d=null;          

            while(!pq.isEmpty()){
                d=pq.delMin();
                
                //System.out.printf(""%1.3f\n"", d);
              for(int i=0;i<N;i++){
                  for(int j=0;j<N;j++){
                         
                      if(a[i].distanceTo(a[j])<=d && a[i].x()<a[j].x() && a[i].y()<a[j].y())
                      G.addEdge(i, j);
                      
                  }
              }
              DirectedDFS dfs = new DirectedDFS(G,Source);
              
              if(dfs.marked(Target)){
                  break;
              }
            }
            System.out.printf(""%1.3f\n"", d);
            


            
}
}

    }

