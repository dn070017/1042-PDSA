
//import edu.princeton.cs.algs4.*;
import java.util.*;

import java.io.FileReader;
import java.io.BufferedReader;

public class CriticalDis {

    private class Edge implements Comparable<Edge> {
        public Point2D origin;
        public Point2D destination;
        public double length;

        public Edge(Point2D O, Point2D D, double L) {
            origin = O;
            destination = D;
            length = L;
        }

        public int compareTo(Edge that) {
            if (this.length > that.length) {
                return 1;
            }
            else if (this.length < that.length) {
                return -1;
            }
            return 0;
        }
    }

    ArrayList<Point2D> points;

    public CriticalDis(ArrayList<Point2D> thePoints) {
        points = thePoints;
    }

    public double solve() {
        Collections.sort(points, new Comparator<Point2D> () {
            @Override
            public int compare(Point2D p1, Point2D p2) {
                double result = (p1.x() + p1.y()) - (p2.x() + p2.y());
                if (result > 0) {
                    return 1;
                }
                else if (result < 0) {
                    return -1;
                }
                return 0;
            }
        });

        Set<Point2D> pointSet = new HashSet<> ();
        MinPQ<Edge> edges = new MinPQ<> ();

        Point2D targetPoint = points.get(0);
        int indexOfPoint = 0;

        // add point and update minPQ
        pointSet.add(targetPoint);
        for (int i=indexOfPoint+1; i<points.size(); ++i) {
            Point2D destination = points.get(i);
            if (targetPoint.x() < destination.x() && targetPoint.y() < destination.y()) {
                double length = targetPoint.distanceTo(destination);
                edges.insert(new Edge(targetPoint, destination, length));
            }
        }

        double min = 0;

        Edge candidate;
        do {
            candidate = edges.delMin();
            if (candidate.length > min) {
                min =  candidate.length;
            }
            if (!pointSet.contains(candidate.destination)) {
                targetPoint = candidate.destination;
                indexOfPoint = points.indexOf(targetPoint);

                pointSet.add(targetPoint);
                for (int i=indexOfPoint+1; i<points.size(); ++i) {
                    Point2D destination = points.get(i);
                    if (targetPoint.x() < destination.x() && targetPoint.y() < destination.y()) {
                        double length = targetPoint.distanceTo(destination);
                        edges.insert(new Edge(targetPoint, destination, length));
                    }
                }
            }
        } while (!candidate.destination.equals(points.get(points.size()-1)));

        return min;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new FileReader(args[0]));

        String nString = buffer.readLine();
        int N = Integer.parseInt(nString);

        ArrayList<Point2D> points = new ArrayList<> ();
        for (int i=0; i<N; ++i) {
            String[] coord = buffer.readLine().split("" "");
            double x = Double.parseDouble(coord[0]);
            double y = Double.parseDouble(coord[1]);
            Point2D newPoint = new Point2D(x, y);
            points.add(newPoint);
        }

        CriticalDis solver = new CriticalDis(points);
        System.out.printf(""%1.3f\n"", solver.solve());
    }
}
