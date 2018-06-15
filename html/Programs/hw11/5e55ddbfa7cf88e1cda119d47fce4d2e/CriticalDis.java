import java.util.ArrayList;
import java.util.Arrays;

public class CriticalDis {

    public static void main(String[] args) {

        In in = new In(args[0]);
        int va = in.readInt();
        Point2D[] points = new Point2D[va];
        for (int i = 0 ; i < va ; i++) {
            points[i] = new Point2D(in.readDouble() , in.readDouble());
        }
        int source = 0;
        int target = 0;
        for (int i = 0; i < points.length; i++) {
            Point2D p = points[i];
            Point2D s = points[source];
            Point2D t = points[target];
            if (p.x() + p.y() < s.x() + s.y()) source = i;
            if (p.x() + p.y() > t.x() + t.y()) target = i;
        }
        ArrayList<Distance> distanceArray = new ArrayList<Distance>();
        for (int i = 0; i < points.length; i++) {
            Point2D p1 = points[i];
            for (int j = 0; j < points.length; j++) {
                Point2D p2 = points[j];
                if (p1.x() < p2.x() && p1.y() < p2.y()) {
                    distanceArray.add(new Distance(p1.distanceTo(p2), i, j));
                }
            }
        }
        Distance[] distanceEvents = distanceArray.toArray(new Distance[distanceArray.size()]);
        Arrays.sort(distanceEvents);

        Digraph digraph = new Digraph(v);
        for (Distance distanceEvent:distanceEvents){
            digraph.addEdge(distanceEvent.i,distanceEvent.j);
            DirectedDFS dfs = new DirectedDFS(digraph , source);
            if (dfs.marked(target)) {
                System.out.println(String.format(""%1.3f"",distanceEvent.d));
                break;
            }
        }
    }

    static class Distance implements Comparable<Distance>{
        double d;
        int i;
        int j;
        public Distance(double distance , int i , int j){
            this.d = distance;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Distance o) {
            if (this.d > o.d) return 1;
            else if (this.d < o.d) return -1;
            else return 0;
        }
    }

}