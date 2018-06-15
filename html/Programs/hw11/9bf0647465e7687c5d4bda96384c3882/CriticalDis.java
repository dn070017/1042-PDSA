
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class CriticalDis implements Comparable<CriticalDis> {
    public int in1;
    public int in2;
    public double distance; 
    
    public CriticalDis(int a,int b,double c){
        in1=a;
        in2=b;
        distance=c;    
    }
    
    public int compareTo(CriticalDis that) {
        if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return 1;
            } else {
                return 0;
            }
    }
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            int N = Integer.parseInt(data);
            Point2D[] points = new Point2D[N];

            String datacut[];
            double x, y;
            double min = 100;
            double max = 0;
            int minid = 0;
            int maxid = 0;            
                       
            MinPQ<CriticalDis> dis = new MinPQ<>();

            for (int i = 0; i < N; i++) {
                datacut = br.readLine().split("" "");

                x = Double.parseDouble(datacut[0]);
                y = Double.parseDouble(datacut[1]);
                points[i] = new Point2D(x, y);

                if (x + y < min) {
                    minid = i;
                    min = x + y;
                }
                if (x + y > max) {
                    maxid = i;
                    max = x + y;
                }
                
                int m = i - 1;
                while (m >= 0) {
                    double ddd=points[i].distanceTo(points[m]);
                    dis.insert(new CriticalDis(m,i,ddd));
                    m--;
                }
            }
            double d;
            Digraph G = new Digraph(N);   
            CriticalDis run;
            
            while(true){
                run = dis.delMin();
                int i = run.in1;
                int j = run.in2;
                if (points[i].x() < points[j].x()) {
                    if (points[i].y() < points[j].y()) {
                        G.addEdge(i, j);
                    }
                } else if (points[i].x() > points[j].x()) {
                    if (points[i].y() > points[j].y()) {
                        G.addEdge(j, i);
                    }
                }
                
                DirectedDFS dfs = new DirectedDFS(G, minid);
                if (dfs.marked(maxid)) break;
            }

            d=run.distance;
            System.out.printf(""%1.3f\n"", d);
        }

    }
}

