import edu.princeton.cs.algs4.UF;
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
 * @author daf
 */
public class Clustering {

    private static class SimpleCluster implements Comparable<SimpleCluster> {

        private SimpleCluster innerClusters;
        private Point2D centroid;
        private int size;
        public int id;//optional

        public SimpleCluster(Point2D p) {
            centroid = p;
            size = 1;
        }

        public SimpleCluster(Point2D p, int id) {
            this.id = id;
            centroid = p;
            size = 1;
        }

        public int compareTo(SimpleCluster that) {
            if (this.getSize() > that.getSize()) {
                return -1;
            } else if (this.getSize() < that.getSize()) {
                return 1;
            } else if (this.centroid.x() < that.centroid.x()) {
                return -1;
            } else if (this.centroid.x() > that.centroid.x()) {
                return 1;
            } else if (this.centroid.y() < that.centroid.y()) {
                return -1;
            } else if (this.centroid.y() > that.centroid.y()) {
                return 1;
            } else {
                return 0;
            }
        }

        public Point2D getCentroid() {
            return centroid;
        }

        public int getSize() {
            return size;
        }

        public static SimpleCluster merge(SimpleCluster a, SimpleCluster b) {
            double x = (a.getCentroid().x() * a.getSize() + b.getCentroid().x() * b.getSize()) / (a.getSize() + b.getSize());
            double y = (a.getCentroid().y() * a.getSize() + b.getCentroid().y() * b.getSize()) / (a.getSize() + b.getSize());
            SimpleCluster c = new SimpleCluster(new Point2D(x, y));
            c.size = a.getSize() + b.getSize();
            return c;
        }
    }

    private static class distanceEvent implements Comparable<distanceEvent> {

        private double d;
        private SimpleCluster Cluster_A;
        private SimpleCluster Cluster_B;
        public int old_A_size, old_B_size;

        public distanceEvent(SimpleCluster a, SimpleCluster b) {
            d = a.getCentroid().distanceTo(b.getCentroid());
            Cluster_A = a;
            Cluster_B = b;
            old_A_size = a.getSize();
            old_B_size = b.getSize();
        }

        public int compareTo(distanceEvent that) {
            if (this.d > that.d) {
                return 1;
            } else if (this.d < that.d) {
                return -1;
            } else {
                return 0;
            }
        }

        public SimpleCluster getCluster_A() {
            return Cluster_A;
        }

        public SimpleCluster getCluster_B() {
            return Cluster_B;
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String header = br.readLine();
            int N = Integer.parseInt(header);

            BST<Integer, SimpleCluster> clusterBST = new BST<Integer, SimpleCluster>();
            SimpleCluster[] clusters = new SimpleCluster[N];

            int n = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] inpair = in.split("" "");
                Point2D point = new Point2D(Double.parseDouble(inpair[0]), Double.parseDouble(inpair[1]));
//                StdDraw.filledCircle(point.x(), point.y(), 0.01);
//                StdDraw.text(point.x(), point.y() + 0.025, String.valueOf(n));
                clusters[n] = new SimpleCluster(point, n);
                clusterBST.put(n, clusters[n]);
                n++;
            }

            MinPQ<distanceEvent> disEvpq = new MinPQ<distanceEvent>();

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    disEvpq.insert(new distanceEvent(clusterBST.get(i), clusterBST.get(j)));
                }
            }

            UF cc = new UF(N);
            if (clusterBST.size() <= 3)
                ; else {
                while (clusterBST.size() != 3) {
                    distanceEvent ev = disEvpq.delMin();
                    //System.out.println(ev.getCluster_A().id + "" "" + ev.getCluster_B().id + "": "" + ev.d);
                    if (clusterBST.contains(ev.getCluster_A().id) && clusterBST.contains(ev.getCluster_B().id)) {
                        if (clusterBST.get(ev.getCluster_A().id).getSize() == ev.old_A_size && clusterBST.get(ev.getCluster_B().id).getSize() == ev.old_B_size) {
                            cc.union(ev.getCluster_A().id, ev.getCluster_B().id);

//                        StdDraw.setPenColor(StdDraw.GREEN);
//                        StdDraw.line(ev.getCluster_A().getCentroid().x(), ev.getCluster_A().getCentroid().y(), ev.getCluster_B().getCentroid().x(), ev.getCluster_B().getCentroid().y());
//                        StdDraw.setPenColor(StdDraw.RED);
                            SimpleCluster c = SimpleCluster.merge(ev.getCluster_A(), ev.getCluster_B());
                            c.id = ev.getCluster_A().id;
                            clusterBST.put(c.id, c);
                            clusterBST.delete(ev.getCluster_B().id);
//                        StdDraw.filledCircle(c.getCentroid().x(), c.getCentroid().y(), c.getSize() * 0.004);
                            for (Integer k : clusterBST.keys()) {
                                if (k == c.id) {
                                    continue;
                                }
                                disEvpq.insert(new distanceEvent(c, clusterBST.get(k)));
                            }
                        }
                    }
                }
            }
            int i = 0;
            SimpleCluster[] res = new SimpleCluster[clusterBST.size()];
            for (Integer k : clusterBST.keys()) {
                res[i++] = clusterBST.get(k);
            }

            Arrays.sort(res);

            for (int j = 0; j < res.length; j++) {
                System.out.println(String.format(""%d %.2f %.2f"", res[j].getSize(), res[j].getCentroid().x(), res[j].getCentroid().y()));
            }

            double min = 1;
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (cc.find(j) != cc.find(k)) {
                        double d = clusters[j].getCentroid().distanceTo(clusters[k].getCentroid());
                        if (d < min) {
                            min = d;
                        }
                    }
                }
            }
            System.out.println(String.format(""%.2f"", min));
        }
    }
}

