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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Clustering {

    private static class Clusters implements Comparable<Clusters> {

        int size = 0;
        double dis = 0;
        Clusters group1;
        Clusters group2;
        Point2D center;
        ArrayList<Point2D> arr;

        public Clusters(ArrayList clist) {
            arr = clist;
            size = clist.size();
            center = center(clist);
        }

        public Clusters(Clusters a, Clusters b) {
            dis = a.center.distanceTo(b.center);
            group1 = a;
            group2 = b;
        }

        public int compareTo(Clusters that) {
            if (this.dis < that.dis) {
                return -1;
            }
            if (this.dis > that.dis) {
                return +1;
            }
            return 0;
        }
        
        public static class ClusterOrder implements Comparator<Clusters> {
            public int compare(Clusters c1, Clusters c2) {
                if (c1.arr.size() > c2.arr.size()) {
                    return -1;
                }
                if (c1.arr.size() < c2.arr.size()) {
                    return +1;
                }
                if (c1.center.x() > c2.center.x()) {
                    return +1;
                }
                if (c1.center.x() < c2.center.x()) {
                    return -1;
                }
                if (c1.center.y() > c2.center.y()) {
                    return +1;
                }
                if (c1.center.y() < c2.center.y()) {
                    return -1;
                }
                return 0;
            }
        }

    }

    static Point2D center(ArrayList<Point2D> a) {
        double x = 0;
        double y = 0;
        for (Point2D p : a) {
            x = x + p.x();
            y = y + p.y();
        }
        x = x / a.size();
        y = y / a.size();

        return new Point2D(x, y);
    }

    static void merge(Clusters a, Clusters b) {
        ArrayList new_cl = new ArrayList();
        new_cl.addAll(a.arr);
        new_cl.addAll(b.arr);
        Clusters cluster = new Clusters(new_cl);

        clusters.remove(a);
        clusters.remove(b);
        centpoints.remove(a.center);
        centpoints.remove(b.center);

        for (Clusters cl : clusters) {
            pq.insert(new Clusters(cl, cluster));
        }

        clusters.add(cluster);
        centpoints.add(cluster.center);
    }

    static MinPQ<Clusters> pq = new MinPQ<Clusters>();
    static ArrayList<Point2D> centpoints = new ArrayList<Point2D>();
    static ArrayList<Clusters> clusters = new ArrayList<Clusters>();

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int index = 0;
            int num = Integer.parseInt(br.readLine());
            Clusters[] original = new Clusters[num];

            while (br.ready()) {
                String[] temp = br.readLine().split("" "");
                double x = Double.parseDouble(temp[0]);
                double y = Double.parseDouble(temp[1]);
                ArrayList<Point2D> origin = new ArrayList<Point2D>(1);

                origin.add(new Point2D(x, y));
                Clusters first = new Clusters(origin);
                original[index] = first;
                clusters.add(first);
                centpoints.add(first.center);

                index++;
            }

            for (int i = 0; i < num; i++) {
                for (int j = i + 1; j < num; j++) {
                    Clusters pair = new Clusters(original[i], original[j]);
                    pq.insert(pair);
                }
            }

            while (num > 3) {
                Clusters pair = pq.delMin();

                if (centpoints.contains(pair.group1.center) && centpoints.contains(pair.group2.center)) {
                    merge(pair.group1, pair.group2);
                    num = num - 1;
                }

            }

            Collections.sort(clusters, new Clusters.ClusterOrder());

            for (Clusters last : clusters) {
                System.out.println(last.arr.size() + "" "" + String.format(""%.2f"", last.center.x()) + "" "" + String.format(""%.2f"", last.center.y()));
            }

            double dis2 = 0;
            MinPQ<Double> pq = new MinPQ<Double>();

            if (num == 1) {
                dis2 = 0.0;
            } else if (num == 2) {
                dis2 = original[0].arr.get(0).distanceTo(original[1].arr.get(0));
            } else {
                for (int i = 0; i < clusters.get(0).arr.size(); i++) {
                    for (int j = 0; j < clusters.get(1).arr.size(); j++) {
                        Point2D c1 = clusters.get(0).arr.get(i);
                        Point2D c2 = clusters.get(1).arr.get(j);
                        pq.insert(c1.distanceTo(c2));
                    }
                }

                for (int i = 0; i < clusters.get(0).arr.size(); i++) {
                    for (int j = 0; j < clusters.get(2).arr.size(); j++) {
                        Point2D c1 = clusters.get(0).arr.get(i);
                        Point2D c2 = clusters.get(2).arr.get(j);
                        pq.insert(c1.distanceTo(c2));
                    }
                }

                for (int i = 0; i < clusters.get(1).arr.size(); i++) {
                    for (int j = 0; j < clusters.get(2).arr.size(); j++) {
                        Point2D c1 = clusters.get(1).arr.get(i);
                        Point2D c2 = clusters.get(2).arr.get(j);
                        pq.insert(c1.distanceTo(c2));
                    }
                }

                dis2 = pq.delMin();
            }

            System.out.println(String.format(""%.2f"", dis2));
        }
    }
    
}

