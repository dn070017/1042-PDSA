import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Win8
 */
public class CriticalDis {
    
    public static class Point implements Comparable<Point>{
      double x;
      double y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }        
        public int compareTo(Point that){
            if((this.x+this.y)>(that.x+that.y)) return 1;
            else if((this.x+this.y)<(that.x+that.y)) return -1;
            else return 0;
        }
        public double getx(){
            return x;
        }
        public double gety()
        {
            return y;
        }
    }
   
    public static class  DirectedDFS{
        private boolean[] marked;
        public DirectedDFS(Digraph G, int s){
            marked = new boolean[G.V()];
            dfs(G,s);
        }
         private void dfs(Digraph G, int v){
             marked[v] = true;
             for(int w: G.adj(v))
                 if(!marked[w])  dfs(G, w);
         }
         public boolean visited(int v){
              return marked[v]; 
         }
    }
    public static class realpath{
       
    }
    public static void findpath(Point[] a,Digraph g,MaxPQ<Double> pq, int b,int n){
        double min = 0;
            int mi = 0;
        for(int i = b+1;i<n;i++){
            
            if(a[b].x<a[i].x && a[b].y<a[i].y){               
                double dx = a[i].x - a[b].x;
                double dy = a[i].y - a[b].y;
                double dis = Math.sqrt(dx*dx+dy*dy);
                if(min == 0){
                    min = dis;
                    mi = i;
                }
                else if(dis<min){
                    min = dis;
                    mi = i;
                }
              }
            pq.insert(min);
            g.addEdge(b, mi);
                DirectedDFS check = new DirectedDFS(g, 0);
               
                if(check.marked[n-1] == true ) return;
                
               
            
        }
         findpath(a,g,pq,mi,n);
    }
        
      public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String num = br.readLine();
            int n = Integer.parseInt(num);
            Point[] points = new Point[n];
            
            for(int i =0;i<n;i++){
             String[] position = br.readLine().split("" "");
             double x = Double.parseDouble(position[0]);
             double y = Double.parseDouble(position[1]);
             points[i] = new Point(x, y);
            }
            Arrays.sort(points);
            
          Digraph gra = new Digraph(n);
           
         MaxPQ<Double> pq = new MaxPQ<>();
         findpath(points, gra, pq, 0, n);
            
         double d = pq.delMax();
         System.out.printf(""%1.3f\n"", d);
        // System.out.print(d);
            
        }                
      }
}

