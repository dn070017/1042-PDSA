import java.util.ArrayList;
import java.util.Arrays;

/**
.
 */
public class CriticalDis {

    public static void main(String[] args) {

        In in = new In(args[0]);
        int v = in.readInt();
        Point2D[] points = new Point2D[v];
        for (int i = 0 ; i < v ; i++){
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
        ArrayList<DistanceEvent> distanceArray = new ArrayList<DistanceEvent>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (points[i].x() < points[j].x() && points[i].y() < points[j].y()) {
                    distanceArray.add(new DistanceEvent(points[i].distanceTo(points[j]), i, j));
                }
            }
        }
        DistanceEvent[] distanceEvents = distanceArray.toArray(new DistanceEvent[distanceArray.size()]);
        Arrays.sort(distanceEvents);

        Digraph digraph = new Digraph(v);
        for (DistanceEvent distanceEvent:distanceEvents){
//            Point2D p1 = points[distanceEvent.i];
//            Point2D p2 = points[distanceEvent.j];
//            if (p1.x() < p2.x() && p1.y() < p2.y()) {
                digraph.addEdge(distanceEvent.i,distanceEvent.j);
//            }

            DirectedDFS dfs = new DirectedDFS(digraph , source);
            if (dfs.marked(target)) {
                System.out.println(String.format(""%1.3f"",distanceEvent.d));
                break;
            }
        }

    }

    static class DistanceEvent implements Comparable<DistanceEvent>{
        double d;
        int i;
        int j;
        public DistanceEvent(double distance , int i , int j){
            this.d = distance;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(DistanceEvent o) {
            if (this.d > o.d) return 1;
            else if (this.d < o.d) return -1;
            else return 0;
        }
    }

}

