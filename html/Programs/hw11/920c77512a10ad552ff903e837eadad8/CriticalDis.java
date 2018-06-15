
//import edu.princeton.cs.algs4.DepthFirstSearch;
//import edu.princeton.cs.algs4.Digraph;
//import edu.princeton.cs.algs4.Graph;
//import edu.princeton.cs.algs4.MinPQ;
//import edu.princeton.cs.algs4.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {

    public static class Cc implements Comparable<Cc> {

        public int place;
        public int placee;

        public Cc(int place, int placee) {
            this.place = place;
            this.placee = placee;
        }

        // TODO
        @Override
        public int compareTo(Cc that) {
            if (points[this.place].distanceTo(points[this.placee]) > points[that.place].distanceTo(points[that.placee])) {
                return 1;
            } else if (points[this.place].distanceTo(points[this.placee]) > points[that.place].distanceTo(points[that.placee])) {
                return -1;
            } else {
                return 0;
            }
        }

        public double getplace() {
            return this.place;
        }

        public double getplacee() {
            return this.placee;
        }

    }
    public static Point2D[] points;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String fund = br.readLine();
            int M = Integer.parseInt(fund);
            points = new Point2D[M];
            int count = 0;
            while ((fund = br.readLine()) != null) {
                String data[] = fund.split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                points[count] = new Point2D(x, y);
//                String word = Integer.toString(count);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                points[count].draw();
//                StdDraw.text(x, y - 0.05, word);
                count++;
            }
            double min = 1;
            int minn = 0, maxx = 0;
            double max = 0;
            for (int i = 0; i < M; i++) {
                if ((points[i].x() + points[i].y()) <= min) {
                    min = (points[i].x() + points[i].y());
                    minn = i;
                }
                if ((points[i].x() + points[i].y()) >= max) {
                    max = (points[i].x() + points[i].y());
                    maxx = i;
                }
            }
            int x = 0;
            MinPQ<Cc> pq = new MinPQ<Cc>();
            double[] out = new double[M * M / 2];
            double d = 0;
            Graph G = new Graph(M);
            DepthFirstSearch search = new DepthFirstSearch(G, maxx);
            Cc c;
            for (int i = 0; i < M; i++) {
                for (int j = i + 1; j < M; j++) {
                    c = new Cc(i, j);
                    pq.insert(c);
                }
            }
            int a = 0, b = 0;
//            System.out.println(minn + "" "" + maxx);
            for (int i = 0; i >= 0; i++) {
                a = pq.min().place;
                b = pq.min().placee;
//                System.out.println(a + "" "" + b + "" "" + points[a].distanceTo(points[b]));
                if ((points[a].x() - points[b].x()) * (points[a].y() - points[b].y()) > 0) {
                    if (points[a].x() > points[b].x()) {
                        G.addEdge(a, b);
                    } else {
                        G.addEdge(b, a);
                    }
                    search = new DepthFirstSearch(G, maxx);
                }
                if (search.marked(minn)) {
                    double z = points[a].distanceTo(points[b]);
                    String format = String.format(""%.3f"", z);
                    System.out.println(format);
                    break;
                }
                pq.delMin();
            }
//            System.out.println(pq.size());
            pq.delMin();
        }
    }

}

