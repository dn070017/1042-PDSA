import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {
    public static class clust implements Comparable<clust> {

        private int index1, index2;
        private double distance;

        public clust(int in1, int in2, double dis) {
            this.index1 = in1;
            this.index2 = in2;
            this.distance = dis;
        }

        public int compareTo(clust that) {
            if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static class sizecom implements Comparable<sizecom> {

        private int index, size;

        public sizecom(int ind, int si) {
            this.index = ind;
            this.size = si;
        }

        public int compareTo(sizecom that) {
            if (this.size < that.size) {
                return -1;
            } else if (this.size > that.size) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            int N = Integer.parseInt(data);

            String datacut[];
            double x, y;
            int m = 0;

            Point2D[] points = new Point2D[2 * N];
            boolean[] alive = new boolean[2 * N];
            UF uf = new UF(2 * N);
//
//            StdDraw.setXscale(0, 1.2);
//            StdDraw.setYscale(0, 1.2);
//            StdDraw.setPenRadius(.015);

            while ((data = br.readLine()) != null) {
                datacut = data.split("" "");
                x = Double.parseDouble(datacut[0]);
                y = Double.parseDouble(datacut[1]);
                points[m] = new Point2D(x, y);
                alive[m] = true;
//
//                StdDraw.text(x + 0.02, y + 0.02, """" + m);
//                points[m].draw();
                m++;
            }

            MinPQ<clust> pq = new MinPQ<clust>();
            int newc = N;

            while (m != 3) {
                if (newc == N) {
                    for (int i = 0; i < N; i++) {
                        for (int j = i + 1; j < N; j++) {
                            double dis = points[i].distanceTo(points[j]);
                            pq.insert(new clust(i, j, dis));
                        }
                    }
                }

                int po1, po2;
                clust clu;

                do {
                    clu = pq.delMin();
                    po1 = clu.index1;
                    po2 = clu.index2;
                } while (!alive[po1] || !alive[po2]);

                uf.union(po1, po2);
//                StdDraw.setPenColor(StdDraw.RED);
//                StdDraw.setPenRadius();
//                points[po1].drawTo(points[po2]);
                alive[po1] = false;
                alive[po2] = false;

                int count = 0;
                double xx = 0;
                double yy = 0;

                for (int i = 0; i < N; i++) {
                    if (uf.connected(i, po1)) {
                        xx += (points[i].x());
                        yy += (points[i].y());
                        count++;
                    }
                }
                xx = xx / count;
                yy = yy / count;

                points[newc] = new Point2D(xx, yy);
//                StdDraw.setPenRadius(.015);
//                StdDraw.text(xx + 0.02, yy + 0.02, """" + newc);
//                points[newc].draw();
                uf.union(po1, newc);
                alive[newc] = true;

                for (int i = 0; i < newc; i++) {
                    if (!uf.connected(i, newc)) {
                        double dis = points[i].distanceTo(points[newc]);
                        pq.insert(new clust(i, newc, dis));
                    }
                }
                newc++;
                m--;
            }

            MaxPQ<sizecom> mpq = new MaxPQ<sizecom>();
            for (int i = 0; i < newc; i++) {
                int count = 0;
                if (alive[i]) {
                    for (int j = 0; j < N; j++) {
                        if (uf.connected(j, i)) {
                            count++;
                        }
                    }
                    mpq.insert(new sizecom(i, count));
                }
            }
            for (int n = 0; n < 3; n++) {
                sizecom a = mpq.delMax();
                x = points[a.index].x();
                y = points[a.index].y();
                StdOut.print(a.size);
                StdOut.print("" "" + String.format(""%.2f"", x));
                StdOut.println("" "" + String.format(""%.2f"", y));
            }
            double dis = 100;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (!uf.connected(i, j) && dis > points[i].distanceTo(points[j])) {
                        dis = points[i].distanceTo(points[j]);
                    }
                }
            }
            StdOut.println(String.format(""%.2f"", dis));
        }
    }

}

