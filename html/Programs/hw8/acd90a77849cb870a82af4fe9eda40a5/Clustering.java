import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String Num = br.readLine();
            int N = Integer.parseInt(Num);
            int count = N;
            int index = N;
            int sumNum = 0;
            double x = 0;
            double y = 0;
            int[] indPosition = new int[2];
            int[] Weight = new int[2 * N];
            Point2D[] clusters = new Point2D[2 * N];
            MaxPQ<Distance> pq = new MaxPQ<Distance>();
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(2 * N);

            for (int i = 0; i < N; i++) {
                String[] points = br.readLine().split("" "");
                clusters[i] = new Point2D(Double.parseDouble(points[0]), Double.parseDouble(points[1]));
                Weight[i] = 1;
            }
            Point2D[] newClusters = clusters;
            int[] newWeight = Weight;

            if (N == 1) {
                System.out.print(newWeight[0]);
                System.out.print("" "");
                System.out.print(String.format(""%.2f"", newClusters[0].x()));
                System.out.print("" "");
                System.out.println(String.format(""%.2f"", newClusters[0].y()));
                System.out.println(""0.00"");
                return ;
            }

            if (N == 2) {
                for (int i = 0; i < 2; i++) {
                    System.out.print(newWeight[i]);
                    System.out.print("" "");
                    System.out.print(String.format(""%.2f"", newClusters[i].x()));
                    System.out.print("" "");
                    System.out.println(String.format(""%.2f"", newClusters[i].y()));
                }
                System.out.println(String.format(""%.2f"", newClusters[0].distanceTo(newClusters[1])));
                return;
            }

            while (count > 3) {
                pq = new MaxPQ<Distance>();
                for (int i = 0; i < count - 1; i++) {
                    for (int j = i + 1; j < count; j++) {
                        Distance a = new Distance(newClusters[i], newClusters[j]);
                        pq.insert(a);
                        if (pq.size() > 1) {
                            pq.delMax();
                        }
                    }
                }
                Distance min = pq.delMax();

                for (int i = 0; i < index; i++) {
                    if (min.first.equals(clusters[i])) {
                        indPosition[0] = i;
                    }
                    if (min.second.equals(clusters[i])) {
                        indPosition[1] = i;
                    }
                }

                uf.union(indPosition[0], indPosition[1]);
                uf.union(indPosition[0], index);

                x = (clusters[indPosition[0]].x() * Weight[indPosition[0]] + clusters[indPosition[1]].x() * Weight[indPosition[1]]) / (Weight[indPosition[0]] + Weight[indPosition[1]]);
                y = (clusters[indPosition[0]].y() * Weight[indPosition[0]] + clusters[indPosition[1]].y() * Weight[indPosition[1]]) / (Weight[indPosition[0]] + Weight[indPosition[1]]);

                Point2D[] newClustersSave = new Point2D[count - 1];
                int[] newWeightSave = new int[count - 1];
                sumNum = 0;
                for (int i = 0; i < count; i++) {
                    if (!newClusters[i].equals(min.first) && !newClusters[i].equals(min.second)) {
                        newClustersSave[sumNum] = newClusters[i];
                        newWeightSave[sumNum] = newWeight[i];
                        sumNum++;
                    }
                }

                newClustersSave[sumNum] = new Point2D(x, y);
                newWeightSave[sumNum] = Weight[indPosition[0]] + Weight[indPosition[1]];
                newClusters = newClustersSave;
                newWeight = newWeightSave;
                clusters[index] = new Point2D(x, y);
                Weight[index] = Weight[indPosition[0]] + Weight[indPosition[1]];
                count--;
                index++;

            }

            pq = new MaxPQ<Distance>();
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (!uf.connected(i, j)) {
                        Distance a = new Distance(clusters[i], clusters[j]);
                        pq.insert(a);
                        if (pq.size() > 1) {
                            pq.delMax();
                        }
                    }
                }
            }

            MaxPQ<Double> Order_pq = new MaxPQ<Double>();
            double[] level = new double[3];

            for (int i = 0; i < 3; i++) {
                level[i] = newWeight[i] * 10000 + (1 - newClusters[i].x()) * 100 + (1 - newClusters[i].y());
                Order_pq.insert(level[i]);
            }

            for (int i = 0; i < 3; i++) {
                double order = Order_pq.delMax();
                for (int j = 0; j < 3; j++) {
                    if (level[j] == order) {
                        System.out.print(newWeight[j]);
                        System.out.print("" "");
                        System.out.print(String.format(""%.2f"", newClusters[j].x()));
                        System.out.print("" "");
                        System.out.println(String.format(""%.2f"", newClusters[j].y()));
                    }
                }
            }
            System.out.println(String.format(""%.2f"", pq.delMax().Dis));
        }
    }

    private static class Distance implements Comparable<Distance> {

        public double Dis;
        public Point2D first;
        public Point2D second;

        public Distance(Point2D a, Point2D b) {
            first = a;
            second = b;
            Dis = a.distanceTo(b);
        }

        public int compareTo(Distance that) {
            if (this.Dis > that.Dis) {
                return 1;
            } else if (this.Dis < that.Dis) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

