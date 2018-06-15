
import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {

    public static class point implements Comparable<point> {

        private int a, b;
        private Point2D x, y;
        private double d;

        public point(int a, int b, Point2D x, Point2D y) {
            this.a = a;
            this.b = b;
            this.x = x;
            this.y = y;

            d = x.distanceTo(y);
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public Point2D x() {
            return this.x;
        }

        public Point2D y() {
            return this.y;
        }

        public double getDistance() {
            return d;
        }

        public int compareTo(point that) {
            if (this.getDistance() > that.getDistance()) {
                return 1;
            }
            if (this.getDistance() < that.getDistance()) {
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

            for (int i = 1; i < num; i++) {
                double s = data[i].x() + data[i].y();
                if (s < data[0].x() + data[0].y()) {
                    Point2D start = data[i];
                    data[i] = data[0];
                    data[0] = start;
                }
                if (s > data[num - 1].x() + data[num - 1].y()) {
                    Point2D end = data[i];
                    data[i] = data[num - 1];
                    data[num - 1] = end;
                }
            }

            MinPQ<point> route = new MinPQ<point>();
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (data[j].x() > data[i].x() && data[j].y() > data[i].y()) {
                        point temp = new point(i, j, data[i], data[j]);
                        route.insert(temp);
                    }
                }
            }

            Digraph path = new Digraph(num);
            DirectedDFS connect = new DirectedDFS(path, 0);
            double d = 0;

            while (connect.marked(num - 1) == false) {
                point temp = route.delMin();
                path.addEdge(temp.a(), temp.b());
                connect = new DirectedDFS(path, 0);
                d = temp.getDistance();
            } 
                System.out.printf(""%1.3f\n"", d);
        }
    }
}
