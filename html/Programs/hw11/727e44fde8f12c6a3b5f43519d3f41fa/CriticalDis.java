import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class CriticalDis {
    private static class Vertex implements Comparable<Vertex>{
        private Point2D point;
        private int id;
        public final Comparator<Vertex> DISTANCE_TO_ORDER = new DistanceToOrder();
        public Vertex(Point2D p, int i) {
            this.point = p;
            this.id = i;
        }
        public int compareTo(Vertex that) {
            double a = this.point.x()+this.point.y();
            double b = that.point.x()+that.point.y();
            if(a < b)   return -1;
            else        return +1;
        }
        public double distanceSquaredTo(Vertex that) {
        double dx = this.point.x() - that.point.x();
        double dy = this.point.y() - that.point.y();
        return dx*dx + dy*dy;
        }
        private class DistanceToOrder implements Comparator<Vertex> {
        public int compare(Vertex p, Vertex q) {
            double dist1 = distanceSquaredTo(p);
            double dist2 = distanceSquaredTo(q);
            if      (dist1 < dist2) return -1;
            else if (dist1 > dist2) return +1;
            else                    return  0;
            }
        }
    }
    public static Vertex[] findEdge(Vertex[] pts, Vertex source, double d){
        ArrayList<Vertex> temp = new ArrayList<Vertex>();
        for(int i = 0; i < pts.length; i++){
            if(source.point.x() < pts[i].point.x() && source.point.y() < pts[i].point.y() && source.point.distanceTo(pts[i].point) > d){
                temp.add(pts[i]);
            }
        }
        Vertex[] output = temp.toArray(new Vertex[temp.size()]);
        return output;
    }

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String num = br.readLine();
            String in;
            Vertex[] points = new Vertex[Integer.parseInt(num)];
            int i = 0;
            while((in = br.readLine()) != null){
                String[] data = in.split("" "");
                points[i++] = new Vertex(new Point2D(Double.parseDouble(data[0]), Double.parseDouble(data[1])), i-1);
            }
            Arrays.sort(points);
            int source = points[0].id;
            int target = points[Integer.parseInt(num)-1].id;
            double d = 0;
            Digraph G = new Digraph(Integer.parseInt(num));;
            DirectedDFS dfs = new DirectedDFS(G, source);;
            while(!(dfs.marked(target))){
                MinPQ<Double> pq = new MinPQ<Double>();
                G = new Digraph(Integer.parseInt(num));
                Vertex tempsource = points[0];
                Vertex[] initial = points;
                while(true){
                    Vertex[] temp = findEdge(initial, tempsource, d);
                    if(temp.length == 0 && !dfs.marked(target)){
                        d = pq.min();
                        break;
                    }
                    Arrays.sort(temp, tempsource.DISTANCE_TO_ORDER);
                    G.addEdge(tempsource.id, temp[0].id);
                    pq.insert(tempsource.point.distanceTo(temp[0].point));
//                    if(pq.size() > 1)
//                        pq.delMin();
                    initial = temp;
                    tempsource = temp[0];
                    dfs = new DirectedDFS(G, source);
                    if(dfs.marked(target))
                        break;
                }
            }
            System.out.printf(""%1.3f\n"", d);
//            for(int j = 0; j < points.length; j++)
//                System.out.printf(""%1.3f %1.3f\n"", points[j].point.x(), points[j].point.y());
        }
    }
}

