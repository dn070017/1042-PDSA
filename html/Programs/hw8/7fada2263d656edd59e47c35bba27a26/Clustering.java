import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class Clustering {

    private static class Cluster implements Comparable<Cluster> {
        public Set<Point2D> points;

        public Cluster() {
            points = new HashSet<Point2D> ();
        }

        public Point2D getCentroid() {
            double x = 0.f, y = 0.f;
            for (Point2D p : points) {
                x += p.x();
                y += p.y();
            }

            return new Point2D(x / (double)points.size(), y / (double)points.size());
        }

        public int compareTo(Cluster that) {
            if (this.points.size() != that.points.size()) {
                return that.points.size() - this.points.size();
            }

            // TODO: X and Y should all be compared
            if (this.getCentroid().x() != that.getCentroid().x()) {
                return (int)(this.getCentroid().x() - that.getCentroid().x());
            }
            return (int)(this.getCentroid().y() - that.getCentroid().y());
        }

        public void description() {
            Point2D point = getCentroid();
            System.out.println(points.size() + "" "" + String.format(""%.2f %.2f"", point.x(), point.y()));
        }
    }

    private static class Edge implements Comparable<Edge> {
        public Cluster a, b;
        public double distance;

        public Edge(Cluster _a, Cluster _b) {
            a = _a;
            b = _b;
            distance = a.getCentroid().distanceTo(b.getCentroid());
        }

        public int compareTo(Edge that) {
            if (this.distance > that.distance) {
                return 1;
            }
            else if (this.distance < that.distance) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int lines = Integer.parseInt(br.readLine());

            ArrayList<Cluster> clusters = new ArrayList<> ();

            // read #lines of points
            for (int i=0; i<lines; ++i) {
                String line = br.readLine();
                String[] coord = line.split("" "");
                Cluster cluster = new Cluster();
                cluster.points.add(new Point2D(Double.parseDouble(coord[0]), Double.parseDouble(coord[1])));
                clusters.add(cluster);
            }

            // construct edges
            MinPQ<Edge> edges = new MinPQ<> ();

            for (int i=0; i<clusters.size(); ++i) {
                for (int j=i+1; j<clusters.size(); ++j) {
                    edges.insert(new Edge(clusters.get(i), clusters.get(j)));
                }
            }

            // main algo
            while (clusters.size() > 3) {
                // get min edge
                Edge edge;
                do {
                    edge = edges.delMin();
                } while (!clusters.contains(edge.a) || !clusters.contains(edge.b));

                // construct new cluster
                Cluster cluster = new Cluster();
                cluster.points.addAll(edge.a.points);
                cluster.points.addAll(edge.b.points);

                // delete two clusters in edge
                clusters.remove(edge.a);
                clusters.remove(edge.b);

                // add egdes candidate to edges
                for (Cluster c : clusters) {
                    edges.insert(new Edge(cluster, c));
                }

                clusters.add(cluster);
            }

            Collections.sort(clusters);

            ArrayList<ArrayList<Point2D>> pointCandidates = new ArrayList<ArrayList<Point2D>> ();

            for (int i=0; i<3 && i<clusters.size(); ++i) {
                clusters.get(i).description();

                ArrayList<Point2D> tmpList = new ArrayList<> ();
                tmpList.addAll(clusters.get(i).points);
                pointCandidates.add(tmpList);
            }

            double minDistance = Double.MAX_VALUE;
            for (int i=0; i<pointCandidates.size(); ++i) {
                for (int j=i+1; j<pointCandidates.size(); ++j) {
                    ArrayList<Point2D> a = pointCandidates.get(i);
                    ArrayList<Point2D> b = pointCandidates.get(j);

                    for (int m=0; m<a.size(); ++m) {
                        for (int n=0; n<b.size(); ++n) {
                            double distance = a.get(m).distanceTo(b.get(n));
                            if (distance < minDistance) {
                                minDistance = distance;
                            }
                        }
                    }
                }
            }
            System.out.println(String.format(""%.2f"", minDistance));
        }
    }
}
