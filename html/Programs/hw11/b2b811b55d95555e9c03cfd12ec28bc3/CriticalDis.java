
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Steven
 */
public class CriticalDis {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int N = Integer.parseInt(br.readLine());
            Point2D[] p = new Point2D[N];
            for (int i = 0; i < N; i++) {
                String[] in = br.readLine().split("" "");
                p[i] = new Point2D(Double.parseDouble(in[0]), Double.parseDouble(in[1]));
            }
            int source = 0;
            int target = 0;
            for (int i = 1; i < N; i++) {
                if (p[i].x() + p[i].y() > p[source].x() + p[source].y()) {
                    source = i;
                }
                if (p[i].x() + p[i].y() < p[target].x() + p[target].y()) {
                    target = i;
                }
            }
            ArrayList<Distance> dis = new ArrayList<Distance>();
            for (int i = 0; i <p.length; i++) {
                Point2D p1 = p[i];
                for(int j=0;j<p.length;j++){
                    Point2D p2 = p[j];
                    if(p1.x()<p2.x()&&p1.y()<p2.y()) dis.add(new Distance(p1.distanceTo(p2),i,j));
                }
            }
            Distance[] a =dis.toArray(new Distance[dis.size()]);
            
            Arrays.sort(a);
            Digraph digraph = new Digraph(N);
            
            for(int i=0 ; i<a.length;i++){
            digraph.addEdge(a[i].i,a[i].j);
            DirectedDFS dfs = new DirectedDFS(digraph,source);
            
            if(dfs.marked(target)==true)
                System.out.printf(""%1.3f\n"", a[i].d);
                break;
            }
            
            }
            

        }
    
        static class Distance implements Comparable<Distance>{
        double d;
        int i;
        int j;
        public Distance(double distance , int i , int j){
            this.d = distance;
            this.i = i;
            this.j = j;
        }
        @Override
        public int compareTo(Distance o) {
            if (this.d > o.d) return 1;
            else if (this.d < o.d) return -1;
            else return 0;
        }
    }
    }




