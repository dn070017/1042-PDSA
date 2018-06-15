
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
            }


            MinPQ<Double> dis = new MinPQ<>();

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    dis.insert(points[i].distanceTo(points[j]));
                }
            }
            double d;

            while (true) {
                d = dis.delMin();
                Digraph G = new Digraph(N);
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        if (points[i].distanceTo(points[j]) < d) {
                            if ((points[i].x() + points[i].y()) < (points[j].x() + points[j].y())) {
                                G.addEdge(i, j);
                            } else {
                                G.addEdge(j, i);
                            }
                        }
                    }
                }
                DirectedDFS dfs = new DirectedDFS(G, minid);
                if (dfs.marked(maxid)) break;
            }

            System.out.printf(""%1.3f\n"", d);
        }

    }
}

