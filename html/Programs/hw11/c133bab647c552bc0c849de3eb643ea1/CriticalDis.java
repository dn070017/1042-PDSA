import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;


 
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
    private static class Event implements Comparable<Event>{
        private int a,b;
        private double dis;
        public Event(int a, int b, double dis){
           this.a = a;
           this.b = b;  
           this.dis = dis;
        }
      /*  public double find(){            
            double dx = a.x-b.x;
            double dy = a.y-b.y;
            double dis = Math.sqrt(dx*dx+dy*dy);
            return dis;
        }*/
          public int compareTo(Event that){
             if(this.dis<that.dis) return -1;
             else if(this.dis>that.dis) return 1;
              else return 0;
          }
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
            double d = 0;
          Digraph gra = new Digraph(n);
          MinPQ<Event> pq = new MinPQ<>();
         for(int i = 0;i<n;i++){
                for(int j = i+1;j<n;j++){
               if(points[i].x<points[j].x&&points[i].y<points[j].y){
                   double dx = points[j].x - points[i].x;
                   double dy = points[j].y - points[i].y;
                   double dis = Math.sqrt(dx*dx+dy*dy);
                   pq.insert(new Event(i,j,dis));
               }
             }
         }
         while(!pq.isEmpty()){
             Event remove = pq.delMin();
             gra.addEdge(remove.a, remove.b);
             DirectedDFS check = new DirectedDFS(gra,0);
             if(check.marked[n-1]){
                 d = remove.dis;
                 break;
             }
         }
        
             
        
         System.out.printf(""%1.3f\n"", d);
        
        // System.out.print(d);
            
        }                
      }
}

