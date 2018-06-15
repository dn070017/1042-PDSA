import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author chenchen
 */
public class CriticalDis {

    private static class Pqcompare implements Comparable<Pqcompare> {

        Pqcompare() {
.
        }

        private Integer b;
        private Integer a;
        private Double distance;

        public Pqcompare(Integer a, Integer b, Double distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;

        }

        @Override
        public int compareTo(Pqcompare that) {
            if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int Num = Integer.parseInt(br.readLine());
            Point2D[] points = new Point2D[Num];

            for (int i = 0; i < Num; i++) {
                String[] header = br.readLine().split("" "");
                points[i] = new Point2D(Double.parseDouble(header[0]), Double.parseDouble(header[1]));

            }

            int S = 0;
            Double s_v = points[0].x() + points[0].y();
            int T = 0;
            Double t_v = points[0].x() + points[0].y();

            for (int i = 1; i < Num; i++) {
                Double temp = points[i].x() + points[i].y();
                if (temp < s_v) {
                    s_v = temp;
                    S = i;
                }
                if (temp > t_v) {
                    t_v = temp;
                    T = i;
                }
            }

                System.out.printf(""%d\n"", S);
                System.out.printf(""%d\n"", T);
            MinPQ<Pqcompare> tryy = new MinPQ<Pqcompare>();
            for (int i = 0; i < Num ; i++) {
                for (int j = 0; j < Num; j++) {
                    if (points[i].x() < points[j].x() && points[i].y() < points[j].y()) {
                        if (points[i].x() >= points[S].x() && points[i].y() >= points[S].y()) {
                            if (points[j].x() <= points[T].x() && points[j].y() <= points[T].y()) {
                                double arr = points[i].distanceTo(points[j]);
                                Pqcompare keke = new Pqcompare(i, j, arr);
                                tryy.insert(keke);
                            }
                        }
                    }
                }
            }
             System.out.printf(""%d\n"", tryy.size());
           Digraph gg = new Digraph(Num);
           DirectedDFS dfs = new DirectedDFS(gg, S);
          
            double d = 0;
            while (!dfs.marked(T)) {
                System.out.printf(""%d\n"", tryy.size());
                Pqcompare ff = tryy.delMin();
                gg.addEdge(ff.a, ff.b);
                dfs = new DirectedDFS(gg, S);
                d = ff.distance;
        }
            System.out.printf(""%1.3f\n"", d);
    }

}
}

