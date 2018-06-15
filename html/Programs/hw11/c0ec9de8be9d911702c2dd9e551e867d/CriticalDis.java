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
            Point2D[] position = new Point2D[num];
            for (int i = 0; i < num; i++) {
                String[] input = br.readLine().split("" "");
                double x = Double.parseDouble(input[0]);
                double y = Double.parseDouble(input[1]);
                position[i] = new Point2D(x, y);
            }
            double t = position[num - 1].x() + position[num - 1].y();
            double s = position[0].x() + position[0].y();

            for (int i = 1; i < num - 1; i++) {
                double comparingPoint = position[i].x() + position[i].y();
                Point2D temp = position[i];
                if (comparingPoint > t) {
                    position[i] = position[num - 1];
                    position[num - 1] = temp;
                    t = position[num-1].x() + position[num-1].y();
                }
                else if (comparingPoint < s) {
                    position[i] = position[0];
                    position[0] = temp;
                    s = position[0].x() + position[0].y();
                } 
            }

            MinPQ<pair> find = new MinPQ<pair>();
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (i != j && position[i].x() < position[j].x() && position[i].y() < position[j].y()) {
                        pair temp = new pair(i, j, position[i], position[j]);
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
