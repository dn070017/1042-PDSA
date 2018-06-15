import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Clustering {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int pointnumber = Integer.parseInt(br.readLine());
            double oripoint[][] = new double[pointnumber][2];
            QuickFindUF qf = new QuickFindUF(2 * pointnumber);
            int total[] = new int[2 * pointnumber];
            int weigth[] = new int[2 * pointnumber];
            Point2D[] ori = new Point2D[2 * pointnumber];
            Point2D[] poi = new Point2D[2 * pointnumber];
            MinPQ<BB> pq = new MinPQ<>();
            MinPQ<BB> finaldis = new MinPQ<>();
            
             int make[] = new int[2 * pointnumber];
//
//            StdDraw.setCanvasSize(800, 800);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(.005);
            for (int i = 0; i < pointnumber; i++) {
                /*read file*/
                String p[] = br.readLine().split("" "");
                double point[] = new double[4];
                point[0] = Double.parseDouble(p[0]);
                point[1] = Double.parseDouble(p[1]);
                point[2] = 1;
                point[3] = i;

                oripoint[i][0] = point[0];
                oripoint[i][1] = point[1];

                total[i] = i;
                total[i + pointnumber] = i + pointnumber;
                weigth[i] = 1;
                weigth[i + pointnumber] = 1;

//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.setPenRadius(.01);
//                Point2D pointss = new Point2D(point[0], point[1]);
//                pointss.draw();
//                StdDraw.text(point[0], point[1] + 0.01, Double.toString(point[3]));

                ori[i] = new Point2D(point[0], point[1]);
                poi[i] = new Point2D(point[0], point[1]);

            }
            if (pointnumber < 3) {
                double be_dis = ori[0].distanceTo(ori[1]);
                System.out.printf(""%d %.2f %.2f\n"", 1, ori[0].x(), ori[0].y());
                System.out.printf(""%d %.2f %.2f\n"", 1, ori[1].x(), ori[1].y());
                System.out.printf(""%.2f"", be_dis);
            }

            for (int i = 0; i < pointnumber; i++) {
                for (int j = i + 1; j < pointnumber; j++) {
                    double be_dis = ori[i].distanceTo(ori[j]);
                    BB bb = new BB(i, j, be_dis);
                    pq.insert(bb);
                }
            }

            int newww = 0;
        
            while (qf.count() > 6) {
                BB bb = pq.delMin();
//            System.out.printf(""\n%.2f %d %d"", bb.dis, bb.x, bb.y);
                if (weigth[bb.x] != 0 && weigth[bb.y] != 0) {

                    weigth[bb.x] = 0;
                    weigth[bb.y] = 0;

                    make[bb.x] = 2 * pointnumber + 1;
                    make[qf.find(bb.x)] = 2 * pointnumber + 1;

                    double newweigth_x = 0;
                    double newweigth_y = 0;

                    for (int i = 0; i < pointnumber; i++) {
                        if (qf.find(bb.x) == qf.find(i)) {
                            newweigth_x = newweigth_x + 1;
                        }
                        if (qf.find(bb.y) == qf.find(i)) {
                            newweigth_y = newweigth_y + 1;
                        }
                    }

                    qf.union(bb.x, bb.y);
//                    System.out.printf(""\n%d "", qf.count());

//                System.out.printf(""\n%f %f"", newweigth_x, newweigth_y);
                    double interpX = ori[bb.x].x() + ((ori[bb.y].x() - ori[bb.x].x()) * (newweigth_y / (newweigth_x + newweigth_y)));
                    double interpY = ori[bb.x].y() + ((ori[bb.y].y() - ori[bb.x].y()) * (newweigth_y / (newweigth_x + newweigth_y)));

                    ori[pointnumber + newww] = new Point2D(interpX, interpY);
                    qf.union(pointnumber + newww, bb.y);
//                    System.out.printf(""\n%d "", qf.count());
                    for (int i = 0; i < pointnumber + newww; i++) {
                        if (weigth[i] != 0) {
                            double be_dis = ori[i].distanceTo(ori[pointnumber + newww]);
                            BB newbb = new BB(i, (pointnumber + newww), be_dis);
                            pq.insert(newbb);
                        }
                    }

                    newww = newww + 1;

//                    StdDraw.setPenColor(StdDraw.RED);
//                    StdDraw.setPenRadius((newweigth_x + newweigth_y) / 100);
//                    Point2D pointss = new Point2D(interpX, interpY);
//                    pointss.draw();
//                    StdDraw.text(interpX, interpY + 0.01, Double.toString(newww));
                }
            }

            int cluster1[] = new int[3];
            int clustersize[] = new int[3];
            int aa = 0;

            for (int i = 0; i < pointnumber; i++) {
                if (make[i] != 2 * pointnumber + 1) {
                    cluster1[aa] = total[i];
                    aa = aa + 1;

                }
            }

            for (int i = 0; i < pointnumber; i++) {
                if (qf.find(i) == cluster1[0]) {
                    clustersize[0] = clustersize[0] + 1;
                }
                if (qf.find(i) == cluster1[1]) {
                    clustersize[1] = clustersize[1] + 1;
                }
                if (qf.find(i) == cluster1[2]) {
                    clustersize[2] = clustersize[2] + 1;
                }
            }

            Point2D center[] = new Point2D[3];
            int kkk = (int)Math.floor(newww*0.5);
            for (int i = pointnumber+kkk; i < pointnumber + newww; i++) {
                if (qf.find(i) == cluster1[0]) {
                    center[0] = ori[i];

                }
                if (qf.find(i) == cluster1[1]) {
                    center[1] = ori[i];

                }
                if (qf.find(i) == cluster1[2]) {
                    center[2] = ori[i];

                }
            }


            for (int i = 0; i < 2; i++) {
                for (int j = 2; j > 0; j--) {
                    if (clustersize[j] > clustersize[j - 1]) {
                        int b = clustersize[j];
                        clustersize[j] = clustersize[j - 1];
                        clustersize[j - 1] = b;
                        Point2D y = center[j];
                        center[j] = center[j - 1];
                        center[j - 1] = y;
                    }
                }
            }
            if (pointnumber >= 3) {
                System.out.printf(""%d %.2f %.2f\n"", clustersize[0], center[0].x(), center[0].y());
                System.out.printf(""%d %.2f %.2f\n"", clustersize[1], center[1].x(), center[1].y());
                System.out.printf(""%d %.2f %.2f\n"", clustersize[2], center[2].x(), center[2].y());

                for (int i = 0; i < pointnumber - 1; i++) {
                    for (int j = i + 1; j < pointnumber; j++) {

                        if (qf.find(i) != qf.find(j)) {
                            double arr = ori[i].distanceTo(ori[j]);
                            BB aaaa = new BB(i, j, arr);
                            finaldis.insert(aaaa);
                        }
                    }

                }
                BB cccc = finaldis.delMin();
                System.out.println(String.format(""%.2f"", cccc.dis));
            }
        }
    }
    //
    //                qf.union((int) min.getCards()[3], (int) min.getCards()[7]);
    //                double interpX = min.getCards()[0] + ((min.getCards()[4] - min.getCards()[0]) * (min.getCards()[6] / (min.getCards()[2] + min.getCards()[6])));
    //                double interpY = min.getCards()[1] + (min.getCards()[5] - min.getCards()[1]) * (min.getCards()[6] / (min.getCards()[2] + min.getCards()[6]));
    //
    //                StdDraw.setPenColor(StdDraw.RED);
    //                StdDraw.setPenRadius((min.getCards()[2] + min.getCards()[6]) / 100);
    //                Point2D pointss = new Point2D(interpX, interpY);
    //                pointss.draw();
    //                StdDraw.text(interpX, interpY + 0.01, Double.toString(id+plu));

    public static class BB implements Comparable<BB> {

        private final Integer x;
        private final Integer y;
        private final Double dis;

        public BB(Integer x, Integer y, Double dis) {
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

