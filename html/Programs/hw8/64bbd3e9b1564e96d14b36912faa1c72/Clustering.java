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

    public static class Clusters implements Comparable<Clusters> {

        double dis = 0;
        Clusters group1;
        Clusters group2;

        ArrayList<Point2D> arr;

        public Clusters(Point2D p) {
            arr = new ArrayList<Point2D>();
            arr.add(p);
        }

        public Clusters(Clusters a, Clusters b) {
            dis = a.center().distanceTo(b.center());
            group1 = a;
            group2 = b;
        }

        public int size() {
            return arr.size();
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

        public int compareTo(Clusters that) {
            if (this.dis < that.dis) {
                return -1;
            }
            if (this.dis > that.dis) {
                return +1;
            }
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Clusters) {
                Clusters toCompare = (Clusters) o;
                return this.center().equals(toCompare.center());
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 83 * hash + Objects.hashCode(this.arr);
            return hash;
        }

    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int num = Integer.parseInt(br.readLine());
            int index = 0;

            Point2D[] points = new Point2D[num];
            MinPQ<Clusters> pq = new MinPQ<Clusters>();
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

                    pq.insert(new Clusters(c1, c2));
                }
            }

            List<Clusters> drop = new ArrayList<Clusters>();

            while (num > 3) {
                Clusters minpair = pq.delMin();

                while ((!drop.isEmpty()) && ((drop.contains(minpair.group1) || drop.contains(minpair.group2)))) {
                    minpair = pq.delMin();
                }

                Clusters g1 = minpair.group1;
                Clusters g2 = minpair.group2;
                drop.add(g1);
                drop.add(g2);

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

                for (int i = 0; i < cluster.size(); i++) {
                    g1 = new Clusters(center);
                    g2 = new Clusters(cluster.get(i).center());

                    if (g1.equals(g2)) {
                        continue;
                    }
                    pq.insert(new Clusters(g1, g2));
                }
            }

            Collections.sort(cluster, new ClusterOrder());
            double dis2 = 0.0;

            if (num == 1) {
                dis2 = 0.0;
            } else if (num == 2) {
                dis2 = cluster.get(0).arr.get(0).distanceTo(cluster.get(1).arr.get(0));
            } else {
                for (int i = 0; i < cluster.get(0).arr.size(); i++) {
                    for (int j = 0; j < cluster.get(1).arr.size(); j++) {
                        Clusters c1 = new Clusters(cluster.get(0).arr.get(i));
                        Clusters c2 = new Clusters(cluster.get(1).arr.get(j));
                        pq.insert(new Clusters(c1, c2));
                    }
                }

                for (int i = 0; i < cluster.get(1).arr.size(); i++) {
                    for (int j = 0; j < cluster.get(2).arr.size(); j++) {
                        Clusters c1 = new Clusters(cluster.get(1).arr.get(i));
                        Clusters c2 = new Clusters(cluster.get(2).arr.get(j));
                        pq.insert(new Clusters(c1, c2));
                    }
                }

                for (int i = 0; i < cluster.get(0).arr.size(); i++) {
                    for (int j = 0; j < cluster.get(2).arr.size(); j++) {
                        Clusters c1 = new Clusters(cluster.get(0).arr.get(i));
                        Clusters c2 = new Clusters(cluster.get(2).arr.get(j));
                        pq.insert(new Clusters(c1, c2));
                    }
                }

                dis2 = pq.delMin().dis;
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

