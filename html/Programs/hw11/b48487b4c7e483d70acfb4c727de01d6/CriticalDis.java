import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {

    private static class Edge implements Comparable<Edge> {

        public Double Dis;
        public Integer from = null;
        public Integer to = null;

        public Edge(Double d, int f, int t) {
            Dis = d;
            from = f;
            to = t;
        }
        
        public int compareTo(Edge that) {
            if (Dis > that.Dis) {
                return 1;
            } else if (Dis == that.Dis) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            Edge E = new Edge(0.0, 0, 0);
            MinPQ<Edge> pq = new MinPQ();
            String[] points = new String[2];
            String Data = br.readLine();
            int Num = Integer.parseInt(Data);
            int s = 0 ;
            int t = 0 ;
            double S = 2 ;
            double T = 0 ;
            Point2D[] Points = new Point2D[Num];
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(Num) ;
            double distance = 0;

            for (int i = 0; i < Num; i++) {
                points = br.readLine().split("" "");
                Points[i] = new Point2D(Double.parseDouble(points[0]), Double.parseDouble(points[1]));
                if ((Points[i].x() + Points[i].y()) < S){
                    S = (Points[i].x() + Points[i].y()) ;
                    s = i ;
                }
                if ((Points[i].x() + Points[i].y()) > T){
                    T = (Points[i].x() + Points[i].y()) ;
                    t = i ;
                }
            }

            for (int i = 0; i < Num; i++) {
                for (int j = i; j < Num; j++) {
                    if ((Points[i].x() < Points[j].x() && Points[i].y() < Points[j].y()) || (Points[j].x() < Points[i].x() && Points[j].y() < Points[i].y())) {
                        distance = Points[i].distanceTo(Points[j]);
                        E = new Edge(distance, i, j);
                        pq.insert(E);
                    }
                }
            }

            while(!uf.connected(s, t)){
                E = pq.delMin() ;
                uf.union(E.from, E.to);
            }

            System.out.printf(""%1.3f\n"", E.Dis);
        }
    }
}
