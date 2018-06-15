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
import java.util.*;

public class Clustering {

    public static class Clusters {

        ArrayList<Point2D> arr;

        public Clusters(Point2D p) {
            arr = new ArrayList<Point2D>();
            arr.add(p);
        }

        public Point2D center() {
            double x = 0;
            double y = 0;
            int size = arr.size();

            for (int i = 0; i < size; i++) {
                x = x + arr.get(i).x();
                y = y + arr.get(i).y();
            }

            x = x / size;
            y = y / size;
            Point2D center = new Point2D(x, y);

            return center;
        }

    }

    public static class Pairs implements Comparable<Pairs> {

        double dis = 0;
        Clusters group1;
        Clusters group2;

        public Pairs(Clusters a, Clusters b) {
            dis = a.center().distanceTo(b.center());

            this.group1 = a;
            this.group2 = b;
        }

        public int compareTo(Pairs that) {
            if (this.dis < that.dis) {
                return -1;
            }
            if (this.dis > that.dis) {
                return +1;
            }
            if (this.dis < that.dis) {
                return -1;
            }
            if (this.dis > that.dis) {
                return +1;
            }
            return 0;
        }

    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int num = Integer.parseInt(br.readLine());
            int index = 0;

            Point2D[] points = new Point2D[num];
            MinPQ<Pairs> pq = new MinPQ<Pairs>();
            MinPQ<Pairs> min = new MinPQ<Pairs>();
            List<Clusters> cluster = new ArrayList<Clusters>();

            while (br.ready()) {
                String[] temp = br.readLine().split("" "");
                double x = Double.parseDouble(temp[0]);
                double y = Double.parseDouble(temp[1]);
                points[index] = new Point2D(x, y);

                Clusters c = new Clusters(points[index]);
                cluster.add(c);

                index++;
            }

            for (int i = 0; i < num; i++) {
                for (int j = i + 1; j < num; j++) {
                    Clusters c1 = new Clusters(points[i]);
                    Clusters c2 = new Clusters(points[j]);

                    pq.insert(new Pairs(c1, c2));
                }
            }

            min = pq;

            while (num > 3) {
                Pairs minpair = pq.delMin();
                Clusters g1 = minpair.group1;
                Clusters g2 = minpair.group2;
                int index1 = 0;
                int index2 = 0;

                for (int i = 0; i < cluster.size(); i++) {
                    if (cluster.get(i).center().equals(g1.center())) {
                        index1 = i;
                    }
                    if (cluster.get(i).center().equals(g2.center())) {
                        index2 = i;
                    }
                }

                cluster.get(index1).arr.addAll(cluster.get(index2).arr);
                Point2D center = cluster.get(index1).center();
                cluster.remove(index2);
                num = num - 1;

                pq = new MinPQ<Pairs>();
                for (int i = 0; i < cluster.size(); i++) {
                    for (int j = i + 1; j < cluster.size(); j++) {
                        g1 = new Clusters(cluster.get(i).center());
                        g2 = new Clusters(cluster.get(j).center());
                        pq.insert(new Pairs(g1, g2));
                    }
                }

            }

            Collections.sort(cluster, new ClusterOrder());
            MinPQ<Double> compare = new MinPQ<Double>();
            double dis2 = 0.0;

            if (num == 1) {
                dis2 = 0.0;
            } else if (num == 2) {
                dis2 = cluster.get(0).arr.get(0).distanceTo(cluster.get(1).arr.get(0));
            } else {

                for (int i = 0; i < min.size(); i++) {
                    Pairs temp = min.delMin();
                    for (int j = 0; j < 3; j++) {
                        if ((cluster.get(j).arr.contains(temp.group1)) && (cluster.get(j).arr.contains(temp.group2))) {
                            break;
                        } else {
                            compare.insert(temp.dis);
                        }
                    }
                }
                dis2 = compare.delMin();
            }

            while (!cluster.isEmpty()) {
                System.out.println(cluster.get(0).arr.size() + "" "" + String.format(""%.2f"", cluster.get(0).center().x()) + "" "" + String.format(""%.2f"", cluster.get(0).center().y()));
                cluster.remove(0);
            }

            System.out.println(String.format(""%.2f"", dis2));

        }
    }

}

class ClusterOrder implements Comparator<Clustering.Clusters> {

    @Override
    public int compare(Clustering.Clusters c1, Clustering.Clusters c2) {
        if (c1.arr.size() > c2.arr.size()) {
            return -1;
        }
        if (c1.arr.size() < c2.arr.size()) {
            return +1;
        }
        if (c1.center().x() > c2.center().x()) {
            return +1;
        }
        if (c1.center().x() < c2.center().x()) {
            return -1;
        }
        if (c1.center().y() > c2.center().y()) {
            return +1;
        }
        if (c1.center().y() < c2.center().y()) {
            return -1;
        }
        return 0;
    }
}

