
import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {

    public static class pair implements Comparable<pair> {

        private int x, y;
        private Point2D px, py;
        private double d;

        public pair(int i, int j, Point2D pi, Point2D pj) {
            this.x = i;
            this.y = j;
            this.px = pi;
            this.py = pj;
            d = pi.distanceTo(pj);
        }

        public int x() {
            return this.x;
        }

        public int y() {
            return this.y;
        }

        public Point2D px() {
            return this.px;
        }

        public Point2D py() {
            return this.py;
        }

        public double Distance() {
            return this.d;
        }

        public int compareTo(pair that) {
            if (this.Distance() > that.Distance()) {
                return 1;
            }
            if (this.Distance() < that.Distance()) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int num = Integer.parseInt(br.readLine());
            Point2D[] data = new Point2D[num];
            for (int i = 0; i < num; i++) {
                String[] input = br.readLine().split("" "");
                double x = Double.parseDouble(input[0]);
                double y = Double.parseDouble(input[1]);
                data[i] = new Point2D(x, y);
            }

            double s = data[0].x() + data[0].y();
            double t = data[num - 1].x() + data[num - 1].y();

            for (int i = 1; i < num - 1; i++) {
                double comparingPoint = data[i].x() + data[i].y();
                if (comparingPoint < s) {
                    Point2D temp = data[i];
                    data[i] = data[0];
                    data[0] = temp;
                    s = data[0].x() + data[0].y();
                } else if (comparingPoint > t) {
                    Point2D temp = data[i];
                    data[i] = data[num - 1];
                    data[num - 1] = temp;
                    t = data[num-1].x() + data[num-1].y();
                }
            }

            MinPQ<pair> find = new MinPQ<pair>();
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (i != j && data[i].x() < data[j].x() && data[i].y() < data[j].y()) {
                        pair temp = new pair(i, j, data[i], data[j]);
                        find.insert(temp);
                    }
                }
            }
            
            Digraph findPath = new Digraph(num);
            DirectedDFS connect = new DirectedDFS(findPath, 0);
            double d = 0;
            while (connect.marked(num - 1) != true) {
                pair temp = find.delMin();
                findPath.addEdge(temp.x(), temp.y());
                connect = new DirectedDFS(findPath, 0);
                d = temp.Distance();
            }
            System.out.printf(""%1.3f\n"", d);
        }
    }
}

