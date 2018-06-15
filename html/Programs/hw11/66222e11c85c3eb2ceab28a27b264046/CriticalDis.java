
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
public class CriticalDis {

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
            
            MinPQ<Double> dis = new MinPQ<>();

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
                    dis.insert(points[i].distanceTo(points[m]));
                    m--;
                }
            }

            double d;

            while (true) {
                d = dis.delMin();
                Digraph G = new Digraph(N);
                int count=0;
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        if (points[i].distanceTo(points[j]) <= d) {
                            if ((points[i].x() < points[j].x()) &&  (points[i].y()< points[j].y())) {
                                if(i==minid ||i==maxid) count++;
                                G.addEdge(i, j);
                            } else if((points[i].x() > points[j].x()) &&  (points[i].y()> points[j].y())){
                                if(i==minid ||i==maxid) count++;
                                G.addEdge(j, i);
                            }
                        }
                    }
                    if(i==minid && count==0)break;
                    if(i==maxid && count==0)break;
                }
                DirectedDFS dfs = new DirectedDFS(G, minid);
                if (dfs.marked(maxid)) break;
            }

            System.out.printf(""%1.3f\n"", d);
        }

    }
}

