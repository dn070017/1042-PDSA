/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;

public class CriticalDis {

    public static Point2D[] p;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int N = Integer.parseInt(br.readLine());
            int index = 1;
            p = new Point2D[N];

            String[] temp0 = br.readLine().split("" "");
            double x0 = Double.parseDouble(temp0[0]);
            double y0 = Double.parseDouble(temp0[1]);
            p[0] = new Point2D(x0, y0);

            double min = x0 + y0;
            double max = min;
            int index_max = 0, index_min = 0;

            while (br.ready()) {
                String[] temp = br.readLine().split("" "");
                double x = Double.parseDouble(temp[0]);
                double y = Double.parseDouble(temp[1]);

                if (x + y > max) {
                    index_max = index;
                    max = x + y;
                }
                if (x + y < min) {
                    index_min = index;
                    min = x + y;
                }

                p[index++] = new Point2D(x, y);
            }

            Point2D s = p[index_min];
            Point2D t = p[index_max];

            Digraph dg = new Digraph(N);
            DirectedDFS dfs = new DirectedDFS(dg, index_min);
            MinPQ<Edge> pq = new MinPQ<Edge>();
            double d = 0.0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (p[i].x() < p[j].x() && p[i].y() < p[j].y()) {
                        pq.insert(new Edge(i, j));
                    }
                }
            }
            
            Edge temp = null;
            while (!dfs.marked(index_max)) {
                temp = pq.delMin();
                dg.addEdge(temp.p1, temp.p2);
                dfs = new DirectedDFS(dg, index_min);
            }

            System.out.printf(""%1.3f\n"", temp.dis);

        }
    }

    public static class Edge implements Comparable<Edge> {

        int p1, p2;
        double dis;

        public Edge(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
            dis = p[p1].distanceTo(p[p2]);
        }

        public int compareTo(Edge that) {
            if (this.dis > that.dis) {
                return +1;
            }
            if (this.dis < that.dis) {
                return -1;
            }
            return 0;
        }
    }

}

