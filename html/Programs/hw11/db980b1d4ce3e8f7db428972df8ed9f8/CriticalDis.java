import java.util.*;

public class CriticalDis {

   public static void main(String[] args){
        
 
            In in = new In(args[0]);
            
            String data = in.readLine();            
            int n = Integer.parseInt(data);
            
            Digraph g = new Digraph(n);
            
            String[] in_str = new String[n];
            Point2D[] points = new Point2D[n];
            
            for(int i = 0; i < n; i++){
                in_str = in.readLine().split("" "");
                points[i] = new Point2D(Double.parseDouble(in_str[0]),Double.parseDouble(in_str[1]));
            }
            
            List<dist> p;
            p = new ArrayList<>();
            
            for(int v = 0; v < n; v++){
                for(int w = 0; w < n; w++){
                    if(v != w){
                        if((points[v].x() < points[w].x()) && (points[v].y() < points[w].y()))
                            p.add(new dist(points[v].distanceTo(points[w]),v,w));
                    }
                }
            }
            
            dist[] d = p.toArray(new dist[p.size()]);
            Arrays.sort(d);
            
            int s = 0;
            int t = 0;
            
            double s_temp = Double.MAX_VALUE;
            double t_temp = Double.MIN_VALUE;
            
            for(int i = 0; i < n; i++){
                if(points[i].x() + points[i].y() < s_temp){
                    s_temp = points[i].x() + points[i].y();
                    s = i;
                }
                if(points[i].x() + points[i].y() > t_temp){
                    t_temp = points[i].x() + points[i].y();
                    t = i;
                }        
            }
            
            
            
            
            
            for(int i = 0; i < d.length; i++){
                g.addEdge(d[i].geti(), d[i].getj()); 
                DirectedDFS dfs = new DirectedDFS(g,s);
                if(dfs.marked(t)){
                    System.out.printf(""%1.3f\n"", d[i].getd());
                    break;
                }                            
            }
            
            
            
            
            
            

            

 
        
    
    

        
    }  
   
    public static class dist implements Comparable<dist>{
        private final double d;
        private final int i;
        private final int j;
        
        public dist(double d, int i, int j){
            this.d = d;
            this.i = i;
            this.j = j;
        }
        
        public double getd(){
            return d;
        }
        
        public int geti(){
            return i;
        }

        public int getj(){
            return j;
        }        
        
        public void print(){
            System.out.printf(""( %d , %d ) d = %1.3f\n"",i,j,d);
        }
        
        @Override
        public int compareTo(dist that) {
        if (this.d < that.d) return -1;
        if (this.d > that.d) return +1;
        return 0;
         }


    }
    
}

