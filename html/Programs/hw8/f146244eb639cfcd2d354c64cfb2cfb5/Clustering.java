
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
            int count;

            Point2D[] points = new Point2D[2 * N];
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(2 * N);
            int size[] = new int[2 * N];

            while ((data = br.readLine()) != null) {
                datacut = data.split("" "");
                x = Double.parseDouble(datacut[0]);
                y = Double.parseDouble(datacut[1]);
                points[m] = new Point2D(x, y);
                size[m] = 1;
                m++;
            }
            if (m == 2) {
                for (int i = 0; i < 2; i++) {
                    StdOut.print(1);
                    StdOut.print("" "" + String.format(""%.2f"", points[i].x()));
                    StdOut.println("" "" + String.format(""%.2f"", points[i].y()));
                }
                StdOut.println(String.format(""%.2f"", points[0].distanceTo(points[1])));
            } else if (m == 1) {
                StdOut.print(1);
                StdOut.print("" "" + String.format(""%.2f"", points[0].x()));
                StdOut.println("" "" + String.format(""%.2f"", points[0].y()));
                StdOut.println(0.00);
            } else {
                MinPQ<clust> pq = new MinPQ<clust>();
                int newc = N;
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        double dis = points[i].distanceTo(points[j]);
                        pq.insert(new clust(i, j, dis));
                    }
                }
                int po1, po2;
                clust clu;

                while (m != 3) {

                    do {
                        clu = pq.delMin();
                        po1 = clu.index1;
                        po2 = clu.index2;
                    } while (size[po1] == 0 || size[po2] == 0);

                    x = points[po1].x() * size[po1] + points[po2].x() * size[po2];
                    y = points[po1].y() * size[po1] + points[po2].y() * size[po2];
                    count = size[po1] + size[po2];
                    x = x / count;
                    y = y / count;

                    points[newc] = new Point2D(x, y);

                    size[po1] = 0;
                    size[po2] = 0;
                    size[newc] = count;

                    uf.union(po1, po2);
                    uf.union(po1, newc);
                    m--;
                    if (m == 3) {
                        newc++;
                        break;
                    }
                    for (int i = 0; i < newc; i++) {
                        if (!uf.connected(i, newc)) {
                            double dis = points[i].distanceTo(points[newc]);
                            pq.insert(new clust(i, newc, dis));
                        }
                    }
                    newc++;
                }

                MaxPQ<sizecom> mpq = new MaxPQ<sizecom>();
                for (int i = 0; i < newc; i++) {
                    if (size[i] != 0) {
                        mpq.insert(new sizecom(i, size[i]));
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
}

