import java.util.ArrayList;
import java.util.Arrays;


public class CriticalDis {

    public static void main(String[] args) {

        In in = new In(args[0]);
        int va = in.readInt();
        Point2D[] point = new Point2D[va];
        for (int i = 0 ; i < va ; i++) {
            point[i] = new Point2D(in.readDouble() , in.readDouble());
        }
        int source = 0;
        int target = 0;
        for (int i = 0; i < point.length; i++) {
            Point2D p = point[i];
            Point2D s = point[source];
            Point2D t = point[target];
            if (p.x() + p.y() < s.x() + s.y()) source = i;
            if (p.x() + p.y() > t.x() + t.y()) target = i;
        }
        ArrayList<Event> distanceArray = new ArrayList<Event>();
        for (int i = 0; i < point.length; i++) {
            Point2D p1 = point[i];
            for (int j = 0; j < point.length; j++) {
                Point2D p2 = point[j];
                if (p1.x() < p2.x() && p1.y() < p2.y()) {
                    distanceArray.add(new Event(p1.distanceTo(p2), i, j));
                }
            }
        }
        Event[] distanceEvents = distanceArray.toArray(new Event[distanceArray.size()]);
        Arrays.sort(distanceEvents);

        Digraph digraph = new Digraph(v);
        for (Event distanceEvent:distanceEvents){
            digraph.addEdge(distanceEvent.i,distanceEvent.j);
            DirectedDFS dfs = new DirectedDFS(digraph , source);
            if (dfs.marked(target)) {
                System.out.println(String.format(""%1.3f"",distanceEvent.d));
                break;
            }
        }
    }

    static class Event implements Comparable<Event>{
        double d;
        int i;
        int j;
        public Event(double distance , int i , int j){
            this.d = distance;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Event o) {
            if (this.d > o.d) return 1;
            else if (this.d < o.d) return -1;
            else return 0;
        }
    }

}
