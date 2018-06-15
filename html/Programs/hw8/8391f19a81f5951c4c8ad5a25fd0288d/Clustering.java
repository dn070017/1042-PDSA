
//import edu.princeton.cs.algs4.MinPQ;
//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;


 
public class Clustering {

    /**
     * @param args the command line arguments
     */
    private static Point2D[] orip;

    private static class distclass implements Comparable<distclass> {
//        用來存進pq

        int A, B;
        double dis;

        distclass(int first, int second) {
            A = first;
            B = second;
            dis = orip[A].distanceTo(orip[B]);
        }

        public int compareTo(distclass that) {
            double value = this.dis - that.dis;

            if (value > 0) {
                return 1;
            } else if (value == 0) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    public static void main(String[] args) throws Exception {
//public static void main(String[] args){

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(0.5);
//            StdDraw.setPenColor(Color.red);
            int NUM = Integer.parseInt(br.readLine());
            orip = new Point2D[NUM * 2 ];          // first NUM 是input ,NUM 後為new
            int[] exist = new int[NUM * 2 ];          // 0是存在，1是不存在
            int[] size = new int[NUM * 2];           // cluster的點數
            int num = NUM;
            int countpoint = 0;

//            0~NUM-1 初始的點數為1
            for (int i = 0; i < NUM; i++) {
                size[i] = 1;
            }

//            StdDraw.setPenColor(Color.red);
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] xy = in.split("" "");
                orip[countpoint] = new Point2D(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));

//                StdDraw.filledCircle(orip[countpoint].x(), orip[countpoint].y(), 0.01);
//                StdDraw.textLeft(orip[countpoint].x() + 0.02, orip[countpoint].y() + 0.05, String.valueOf(countpoint));
                countpoint++;
            }
            if (NUM > 3) {
                MinPQ<distclass> dispq = new MinPQ<distclass>(NUM * NUM - 3 * NUM / 2);
                distclass[] disss = new distclass[NUM * NUM - 3 * NUM / 2];
                WeightedQuickUnionUF UF = new WeightedQuickUnionUF(NUM * NUM - 3 * NUM / 2);

//            最初NUM個點，連NUM*(NUM-1) 
                int countdis = 0;
                for (int i = 0; i < NUM; i++) {
                    for (int j = i + 1; j < NUM; j++) {
                        disss[countdis] = new distclass(i, j);
                        dispq.insert(disss[countdis]);
                        countdis++;
                    }
                }

//            StdDraw.setPenColor(Color.GREEN);
//            orip[5].drawTo(orip[6]);
//            StdDraw.setPenColor(Color.BLUE);
//            StdDraw.filledCircle(orip[countpoint - 1].x(), orip[countpoint - 1].y(), 0.5);
//            keep存distance.dis進dispq，new point 時要新存
                while (num > 3) {
                    //                    兩點都在，make 型星
                    distclass thiscentroid = dispq.delMin();
                    while (exist[thiscentroid.A] != 0 || exist[thiscentroid.B] != 0) {
                        thiscentroid = dispq.delMin();
                    }

                    int a = thiscentroid.A;
                    int b = thiscentroid.B;

                    size[countpoint] = size[a] + size[b];
                    orip[countpoint] = new Point2D((orip[a].x() * size[a] + orip[b].x() * size[b]) / size[countpoint], (orip[a].y() * size[a] + orip[b].y() * size[b]) / size[countpoint]);

                    UF.union(a, b);
                    UF.union(a, countpoint);
                    exist[a] = 1;
                    exist[b] = 1;

//                StdDraw.setPenColor(Color.GREEN);
//                orip[a].drawTo(orip[b]);
//                StdDraw.setPenColor(Color.BLUE);
//                StdDraw.filledCircle(orip[countpoint].x(), orip[countpoint].y(), size[countpoint] / 100);
                    num--;

                    for (int i = 0; i < countpoint; i++) {
                        if (exist[i] == 0) {
                            disss[countdis] = new distclass(countpoint, i);
                            dispq.insert(disss[countdis]);
                            countdis++;
                        }
                    }

                    countpoint++;
                }

                //            找最後3個CC         
                int[] centroid = new int[3];

                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < countpoint; i++) {
                        if (exist[i] == 0) {
                            centroid[j] = i;
                            exist[i] = 1;
                            break;
                        }
                    }
                }

                for (int j = 0; j < 3; j++) {

                    for (int i = j; i < 3; i++) {
                        if (size[centroid[i]] > size[centroid[j]]) {
                            int swap;
                            swap = centroid[i];
                            centroid[i] = centroid[j];
                            centroid[j] = swap;
                        }
                    }

                }

                for (int i = 0; i < 3; i++) {
                    System.out.println(size[centroid[i]] + "" "" + String.format(""%.2f"", orip[centroid[i]].x()) + "" "" + String.format(""%.2f"", orip[centroid[i]].y()));

                }

                while (!dispq.isEmpty()) {
                    dispq.delMin();
                }
                countdis = 0;

                for (int i = 0; i < NUM; i++) {
                    for (int j = i + 1; j < NUM; j++) {
                        if (!UF.connected(i, j)) {
                            disss[countdis] = new distclass(i, j);
                            dispq.insert(disss[countdis]);
                            countdis++;
                        }
                    }
                }

                distclass A = dispq.delMin();

                System.out.println(String.format(""%.2f"", A.dis));
            } 
                else if (NUM == 2) {
                System.out.println(1 + "" "" + String.format(""%.2f"",orip[0].x()) + "" "" +String.format(""%.2f"", orip[0].y()));
                System.out.println(1 + "" "" + String.format(""%.2f"",orip[1].x()) + "" "" + String.format(""%.2f"",orip[1].y()));
                System.out.println(String.format(""%.2f"",orip[0].distanceTo(orip[1])));
            }
            
                
        }

    }

}

