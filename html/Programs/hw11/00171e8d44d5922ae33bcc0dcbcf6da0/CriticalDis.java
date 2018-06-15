
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class CriticalDis {

    public static class Dist implements Comparable<Dist> {

        private double dd;
        private int left; 
        private int right;

        public Dist(double d, int left, int right) {
            this.dd = d;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Dist that) {
            if (this.dd > that.dd) {
                return +1;
            } else if (this.dd < that.dd) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    


public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String data = br.readLine();
            int N = Integer.parseInt(data);
            Digraph graph = new Digraph(N);
            ArrayList<Point2D> points = new ArrayList<Point2D>();
            //HashMap<Double, String> map = new HashMap<>();
            MinPQ<Dist> pq = new MinPQ<Dist>();

            //QuickUnionUF uf = new QuickUnionUF(N);
            double di;
            int s = 0;
            int t = 0;
            double min = 10000;
            double max = 0;
            for (int i = 0; i < N; i++) {
                String[] d = br.readLine().split("" "");
                Point2D p = new Point2D(Double.parseDouble(d[0]), Double.parseDouble(d[1]));
                points.add(p);
                if (p.x() + p.y() < min) {
                    min = p.x() + p.y();
                    s = i;
                }
                if (p.x() + p.y() > max) {
                    max = p.x() + p.y();
                    t = i;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if ((points.get(i).x() <= points.get(j).x() && points.get(i).y() <= points.get(j).y()) || points.get(i).x() >= points.get(j).x() && points.get(i).y() >= points.get(j).y()) {
                        double d = points.get(i).distanceTo(points.get(j));
                        //pq.insert(d);
                        Dist diss = new Dist(d, i, j);
                        pq.insert(diss);
                        //map.put(d, temp);
                    }
//                    if ((points.get(i).x() <= points.get(j).x() && points.get(i).y() <= points.get(j).y()) || points.get(i).x() >= points.get(j).x() && points.get(i).y() >= points.get(j).y()) {
//                        double d = points.get(i).distanceTo(points.get(j));
//                        pq.insert(d);
//                        String temp = String.valueOf(j);
//                        temp += ("","");
//                        temp += String.valueOf(i);
//                        map.put(d, temp);
//                    }

                }
            }
            while (true) {
                Dist diis = pq.delMin();
//                if (dist < 0.2) {
//                    continue;
//                }
                //String temp = map.get(dis);

//                String[] temps = temp.split("","");
                int a = diis.left;
                int b = diis.right;
                if (points.get(a).x() <= points.get(b).x() && points.get(a).y() <= points.get(b).y()) {
                    graph.addEdge(a, b);
                    //uf.union(a, b);
                }
                if (points.get(a).x() >= points.get(b).x() && points.get(a).y() >= points.get(b).y()) {
                    graph.addEdge(b, a);
                    //uf.union(a, b);
                }
                DirectedDFS dfs = new DirectedDFS(graph, s);
                if (dfs.marked(t)) {
                    di = diis.dd;
                    break;
                }
            }
            System.out.printf(""%1.3f\n"", di);
        }
    }
}

