import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {

    private static class PQcomp implements Comparable<PQcomp> {
        /*PQcomp(){
        }*/
        private Integer point_a;
        private Integer point_b;
        private Double dist;
        public PQcomp(Integer point_a, Integer point_b, Double dist) {
            this.point_a = point_a;
            this.point_b = point_b;
            this.dist = dist;
        }
        @Override
        public int compareTo(PQcomp that) {
            if (this.dist < that.dist) {
                return -1;
            } else if (this.dist > that.dist) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) { // change ""ex.txt"" to args[0]
            int N = Integer.parseInt(br.readLine());
            int[] pair_list = new int[N * 2];
            int[] point_id = new int[N * 2];
            Point2D[] points = new Point2D[N * 2];
            MaxPQ<PQcomp> MaxPQ = new MaxPQ<PQcomp>();
            MinPQ<PQcomp> MinPQ = new MinPQ<PQcomp>();
            QuickFindUF QFUF = new QuickFindUF(N * 2);
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split("" "");
                pair_list[i] = i;
                pair_list[i + N] = i + N;
                point_id[i] = 1;
                point_id[i + N] = 1;
                points[i] = new Point2D(Double.parseDouble(input[0]), Double.parseDouble(input[1]));
            }
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    double temp_pair_dist = points[i].distanceTo(points[j]);
                    PQcomp temp_min_dist = new PQcomp(i, j, temp_pair_dist);
                    MinPQ.insert(temp_min_dist);
                }
            }
            int mer = 0;
            while (QFUF.count() > 6) {
                PQcomp min_dist = MinPQ.delMin();
                if (point_id[min_dist.point_a] != 0) {
                    if (point_id[min_dist.point_b] != 0) {
                        pair_list[QFUF.find(min_dist.point_a)] = 2 * N + 1;
                        pair_list[min_dist.point_a] = 2 * N + 1;
                        point_id[min_dist.point_a] = 0;
                        point_id[min_dist.point_b] = 0;
                        
                        int root_point_a = QFUF.find(min_dist.point_a);
                        int root_point_b = QFUF.find(min_dist.point_b);
                        
                        int mer_a = 0;
                        int mer_b = 0;
                        for (int i = 0; i < N; i++) {
                            if (QFUF.find(i) == root_point_a) {
                                mer_a = mer_a + 1;
                            }
                            if (QFUF.find(i) == root_point_b) {
                                mer_b = mer_b + 1;
                            }
                        }

                        QFUF.union(min_dist.point_a, min_dist.point_b);

                        double temp_cen_x = (points[min_dist.point_a].x() * mer_a + points[min_dist.point_b].x() * mer_b) / (mer_a + mer_b);
                        double temp_cen_y = (points[min_dist.point_a].y() * mer_a + points[min_dist.point_b].y() * mer_b) / (mer_a + mer_b);
                        points[N + mer] = new Point2D(temp_cen_x, temp_cen_y);
                        QFUF.union(N + mer, min_dist.point_b);

                        for (int i = 0; i < N + mer; i++) {
                            if (point_id[i] != 0) {
                                double update_dist = points[N + mer].distanceTo(points[i]);
                                PQcomp update_min_dist = new PQcomp(N + mer, i, update_dist);
                                MinPQ.insert(update_min_dist);
                            }
                        }
                        mer++;
                    }
                }
                if (QFUF.count() == 6) {
                    break;
                }
            }
            int[] clust_id = new int[3];
            int[] clust_size = new int[3];
            clust_size[0] = 0;
            clust_size[1] = 0;
            clust_size[2] = 0;
            int clust_first = 0;

            for (int i = 0; i < N; i++) {
                if (pair_list[i] != 2 * N + 1) {
                    clust_id[clust_first] = pair_list[i];
                    clust_first = clust_first + 1;
                }
            }

            for (int i = 0; i < N; i++) {
                if (QFUF.find(i) == clust_id[0]) {
                    clust_size[0] = clust_size[0] + 1;
                }
                if (QFUF.find(i) == clust_id[1]) {
                    clust_size[1] = clust_size[1] + 1;
                }
                if (QFUF.find(i) == clust_id[2]) {
                    clust_size[2] = clust_size[2] + 1;
                }
            }

            Point2D[] clust_cen = new Point2D[3];
            int bs_check = (int) Math.floor(mer * 0.5);
            for (int i = N + bs_check; i < N + mer; i++) {
                int clust_root = QFUF.find(i);
                if (clust_root == clust_id[0]) {
                    clust_cen[0] = points[i];
                }
                if (clust_root == clust_id[1]) {
                    clust_cen[1] = points[i];
                }
                if (clust_root == clust_id[2]) {
                    clust_cen[2] = points[i];
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 2; j > 0; j--) {
                    if (clust_size[j] > clust_size[j - 1]) {
                        int clust_size_sub = clust_size[j];
                        clust_size[j] = clust_size[j - 1];
                        clust_size[j - 1] = clust_size_sub;
                        Point2D clust_cen_sub = clust_cen[j];
                        clust_cen[j] = clust_cen[j - 1];
                        clust_cen[j - 1] = clust_cen_sub;
                    }
                }
            }
            if (N >= 3) {
                System.out.println(Integer.toString(clust_size[0]) + "" "" + String.format(""%.2f"", clust_cen[0].x()) + "" "" + String.format(""%.2f"", clust_cen[0].y()));
                System.out.println(Integer.toString(clust_size[1]) + "" "" + String.format(""%.2f"", clust_cen[1].x()) + "" "" + String.format(""%.2f"", clust_cen[1].y()));
                System.out.println(Integer.toString(clust_size[2]) + "" "" + String.format(""%.2f"", clust_cen[2].x()) + "" "" + String.format(""%.2f"", clust_cen[2].y()));

                for (int i = 0; i < N - 1; i++) {
                    for (int j = i + 1; j < N; j++) {

                        if (QFUF.find(i) != QFUF.find(j)) {
                            double temp_pair_dist = points[i].distanceTo(points[j]);
                            PQcomp temp_min_dist = new PQcomp(i, j, temp_pair_dist);
                            MaxPQ.insert(temp_min_dist);
                            if (MaxPQ.size() > 1) {
                                MaxPQ.delMax();
                            }
                        }
                    }
                }
                System.out.println(String.format(""%.2f"", MaxPQ.delMax().dist));
            }
            if (N < 3) {
                System.out.println(""1"" + "" "" + String.format(""%.2f"", points[0].x()) + "" "" + String.format(""%.2f"", points[0].y()));
                System.out.println(""1"" + "" "" + String.format(""%.2f"", points[1].x()) + "" "" + String.format(""%.2f"", points[1].y()));
                double min_dist_2 = points[0].distanceTo(points[1]);
                System.out.println(String.format(""%.2f"", min_dist_2));
            }
        }
    }
}

