import java.io.BufferedReader;
import java.io.FileReader;

public class Hw11 {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String a = br.readLine();
            int num = Integer.parseInt(a);
            int min = 0;
            int max = 0;
            double xx = 0;
            double yy = 0;
            double xxx = 0;
            double yyy = 0;
            double point[][] = new double[num][2];
            for (int i = 0; i < 20; i++) {
                String[] b = br.readLine().split("" "");
                point[i][0] = Double.parseDouble(b[0]);
                point[i][1] = Double.parseDouble(b[1]);
                if (i == 0) {
                    xx = point[i][0];
                    yy = point[i][1];
                    xxx = point[i][0];
                    yyy = point[i][1];
                }
                if (i > 0 && point[i][0] + point[i][1] <= yy + xx) {
                    xx = point[i][0];
                    yy = point[i][1];
                    min = i;
                }
                if (i > 0 && point[i][0] + point[i][1] >= yyy + xxx) {
                    xxx = point[i][0];
                    yyy = point[i][1];
                    max = i;
                }
            }
//            StdDraw.setCanvasSize(800, 800);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(.005);
//            Point2D[] points = new Point2D[num];
//            for (int i = 0; i < num; i++) {
//                double x = point[i][0];
//                double y = point[i][1];
//                points[i] = new Point2D(x, y);
//                points[i].draw();
//                StdDraw.text(x, y + 0.01, Integer.toString(i));
//            }
            MinPQ<BB> pq = new MinPQ<>();
            Digraph G = new Digraph(num);
            for (int i = 0; i < num; i++) {
                for (int j = i; j < num; j++) {
                    if (point[i][0] <= point[j][0] && point[i][1] <=point[j][1]) {
                        Double dis = Math.sqrt(Math.pow(point[i][0] - point[j][0], 2) + Math.pow(point[i][1] - point[j][1], 2));
                        BB bb = new BB(i, j, dis);
                        pq.insert(bb);
                    }
                    if (point[i][0] >= point[j][0] && point[i][1] >= point[j][1]) {
                        Double dis = Math.sqrt(Math.pow(point[i][0] - point[j][0], 2) + Math.pow(point[i][1] - point[j][1], 2));
                        BB bb = new BB(j, i, dis);
                        pq.insert(bb);
                    }
                }

            }

            DirectedDFS dfs = new DirectedDFS(G, min);
            double d = 0;
            while (!dfs.marked(max)) {
                BB bbb = pq.delMin();
                G.addEdge(bbb.x, bbb.y);
                dfs = new DirectedDFS(G, min);
                d = bbb.dis;
                System.out.printf(""%d %d\n"", bbb.x,bbb.y);
            }

            System.out.printf(""%1.3f\n"", d);
        }

    }

    public static class BB implements Comparable<BB> {

        private final int x;
        private final int y;
        private final double dis;

        public BB(int x, int y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(BB that) {

            if (this.dis > that.dis) {
                return 1;
            } else if (this.dis < that.dis) {
                return -1;
            } else {
                return -0;
            }
        }
    }

}

