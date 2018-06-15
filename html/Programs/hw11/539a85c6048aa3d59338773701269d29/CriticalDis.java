
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;

public class CriticalDis {

    public static class Pair implements Comparable<Pair> {

        private Point2D a;
        private Point2D b;
        private double distance;
        private int a1, b1;

        public Pair(Point2D a, Point2D b, int a1, int b1) {
            this.a = a;
            this.b = b;
            this.a1 = a1;
            this.b1 = b1;
            this.distance = a.distanceTo(b);
        }

        public Point2D getA() {
            return this.a;
        }

        public Point2D getB() {
            return this.b;
        }

        public int getIndexA() {
            return this.a1;
        }

        public int getIndexB() {
            return this.b1;
        }

        public int compareTo(Pair that) {
            if (this.distance > that.distance) {
                return 1;
            } else if (this.distance < that.distance) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int size = Integer.parseInt(br.readLine());
            Point2D[] wei = new Point2D[size];
            String[] temp = new String[2];
            int s = 0; //source
            int t = 0; //target
            double s_size = 2;
            double t_size = 0;
            double x = 0;
            double y = 0;
            for (int i = 0; i < size; i++) {
                temp = br.readLine().split("" "");
                x = Double.parseDouble(temp[0]);
                y = Double.parseDouble(temp[1]);
                if (x + y > t_size) {
                    t_size = x + y;
                    t = i;
                }
                if (x + y < s_size) {
                    s_size = x + y;
                    s = i;
                }
                wei[i] = new Point2D(x, y);
                //System.out.println(wei[i]);
            }

            MinPQ<Pair> yuru = new MinPQ<Pair>(size * (size - 1) / 2);

            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    yuru.insert(new Pair(wei[i], wei[j], i, j));
                }
            }
            Digraph dg = new Digraph(size);
            DirectedDFS chi = null;
            int flag = 0;
            Pair cheng;
            while (flag == 0) {
                cheng = yuru.delMin();
                if (cheng.getA().x() > cheng.getB().x()) {
                    if (cheng.getA().y() > cheng.getB().y()) {
                        dg.addEdge(cheng.getIndexB(), cheng.getIndexA());
                    }

                } else if (cheng.getA().x() < cheng.getB().x()) {
                    if (cheng.getA().y() < cheng.getB().y()) {
                        dg.addEdge(cheng.getIndexA(), cheng.getIndexB());
                    }
                }
                chi = new DirectedDFS(dg, s);
                if (chi.marked(t)) {
                    break;
                }
            }
            BreadthFirstDirectedPaths feng = new BreadthFirstDirectedPaths(dg, s);

            //double distance = 0;
            double j = 2;
            int current = s;
            int counter = 0;
            double m=0;

            for (int u : feng.pathTo(t)) {
                //distance = distance + wei[s].distanceTo(wei[u]);
                m = wei[s].distanceTo(wei[u]);
               // System.out.println(m);
                if(counter>0){
                    if(wei[s].distanceTo(wei[u])<j){
                        j = wei[s].distanceTo(wei[u]);
                    }
                }
                s = u;
                counter++;
            }

            System.out.printf(""%1.3f\n"", j);

        }
    }
}

