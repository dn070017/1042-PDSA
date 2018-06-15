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
        ArrayList<Double> dists = new ArrayList<Double>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                dists.add(points[i].distanceTo(points[j]));
            }
        }
        Double[] distances = dists.toArray(new Double[dists.size()]);
        Arrays.sort(distances);

        Digraph digraph = new Digraph(v);


        for (double d:distances){
            for (int i = 0; i < points.length; i++) {
                Point2D p1 = points[i];
                for (int j = 0; j < points.length; j++) {
                    Point2D p2 = points[j];
                    if (p1.x() < p2.x() && p1.y() < p2.y()) {
                        if (p1.distanceTo(p2) <= d) {
                            digraph.addEdge(i,j);
                        }
                    }
                }
            }
            DirectedDFS dfs = new DirectedDFS(digraph , source);
            if (dfs.marked(target)) {
                System.out.println(String.format(""%1.3f"",d));
                break;
            }
        }

    }
}

