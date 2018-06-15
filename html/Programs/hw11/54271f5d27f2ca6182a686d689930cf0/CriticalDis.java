

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author 士齊
 */
public class CriticalDis {

    private static Point2D[] orip;

    private static class distclass implements Comparable<distclass> {
//        用來存進pq

        int A, B;
        double dis;

        distclass(int first, int second) {
            A = first;
            B = second;
            dis = orip[A].distanceTo(orip[B]);
        }

        public int compareTo(distclass that) {
            double value = this.dis - that.dis;

            if (value > 0) {
                return 1;
            } else if (value == 0) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String data = br.readLine();
            int N = Integer.parseInt(data);

            MinPQ<distclass> diss = new MinPQ<distclass>();
            Digraph G = new Digraph(N);
            DirectedDFS D;

            orip = new Point2D[N];
            int s = 0;
            int t = 0;

            for (int i = 0; i < N; i++) {
                data = br.readLine();
                String[] position = data.split("" "");
                orip[i] = new Point2D(Double.parseDouble(position[0]), Double.parseDouble(position[1]));

                for (int j = 0; j < i; j++) {
                    if (orip[i].x() < orip[j].x() && orip[i].y() < orip[j].y()) {
                        diss.insert(new distclass(i, j));
                    } else if (orip[j].x() < orip[i].x() && orip[j].y() < orip[i].y()) {
                        diss.insert(new distclass(j, i));
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                if (orip[i].x() + orip[i].y() < orip[s].x() + orip[s].y()) {
                    s = i;
                }

                if (orip[i].x() + orip[i].y() > orip[t].x() + orip[t].y()) {
                    t = i;
                }
            }

            distclass newdis = null;
            while (!diss.isEmpty()) {
                newdis = diss.delMin();
                G.addEdge(newdis.A, newdis.B);

                D = new DirectedDFS(G, s);
                if (D.marked(t)) {
                    break;
                }
            }

            double ans = newdis.dis;
            System.out.printf(""%1.3f\n"", ans);

        }
    }
}

