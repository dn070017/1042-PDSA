import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class JavaApplication11 {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int pointnumber = Integer.parseInt(br.readLine());
            double oripoint[][] = new double[pointnumber][2];
            ArrayList<double[]> myList = new ArrayList();
            QuickFindUF qf = new QuickFindUF(pointnumber);
//            MinPQ<BBB> pq = new MinPQ<>();
            for (int i = 0; i < pointnumber; i++) {
                /*read file*/
                String p[] = br.readLine().split("" "");
                double point[] = new double[4];
                point[0] = Double.parseDouble(p[0]);
                point[1] = Double.parseDouble(p[1]);
                point[2] = 1;
                point[3] = i;
                myList.add(point);

                oripoint[i][0] = point[0];
                oripoint[i][1] = point[1];

            }


            /*clustering*/
            while (myList.size() > 3) {
                int n = myList.size();
//                System.out.printf(""\n %d"", n);
                int m = n * (n - 1) / 2;
                MinPQ<BBB> pq = new MinPQ<>();
//                double data[] = new double[5];
                /*distance*/
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        double[] pointX = myList.get(i);
                        double[] pointY = myList.get(j);
                        double a = Math.sqrt(Math.pow(pointX[0] - pointY[0], 2) + Math.pow(pointX[1] - pointY[1], 2));
                        double data[] = new double[5];
                        data[0] = a;
                        data[1] = (double) i;
                        data[2] = (double) j;
                        data[3] = pointX[2];
                        data[4] = pointY[2];

//                        String x = Integer.toString(i);
//                        String y = Integer.toString(j);
//                        String XWEI = String.valueOf(pointX[2]);
//                        String YWEI = String.valueOf(pointY[2]);
//                        String num = x + "","" + y + "","" + XWEI + "","" + YWEI;

                        /*put sybol table*/
                        BBB bbb = new BBB(data);
                        pq.insert(bbb);
//                        System.out.printf(""\n%.2f %.2f %.2f"", bbb.p[0], bbb.p[1], bbb.p[2]);
                    }
                }

                /*find min*/
                BBB aa = pq.delMin();

                /*min distance of two points */
                int X1 = (int) aa.p[1];
                int Y1 = (int) aa.p[2];
                double WeightX = aa.p[3];
                double WeightY = aa.p[4];
                double[] pointXX = myList.get(X1);
                double[] pointYY = myList.get(Y1);

//                System.out.printf(""\n%.2f %d %d"", aa.p[0], X1, Y1);
//                System.out.printf(""\n%.2f %d %d"", aa.p[0], X1, Y1);

                /*the weigthed center of tw0 points*/
                double interpX = pointXX[0] + ((pointYY[0] - pointXX[0]) * (WeightY / (WeightX + WeightY)));
                double interpY = pointXX[1] + (pointYY[1] - pointXX[1]) * (WeightY / (WeightX + WeightY));

                int numcluXX = 0;
                int numcluYY = 0;
                double numclu1 = 0;
                if (WeightY == 1 && WeightX == 1) {
                    if (pointXX[3] > pointYY[3]) {
                        numcluXX = (int) pointXX[3];
                        numcluYY = (int) pointYY[3];
                        qf.union(numcluYY, numcluXX);
                        numclu1 = pointXX[3];
                    } else {
                        numcluXX = (int) pointXX[3];
                        numcluYY = (int) pointYY[3];
                        qf.union(numcluXX, numcluYY);
                        numclu1 = pointYY[3];
                    }

                } else if (WeightY == 1 && WeightX != 1) {
                    numcluXX = (int) pointXX[3];
                    numcluYY = (int) pointYY[3];
                    qf.union(numcluYY, numcluXX);
                    numclu1 = pointXX[3];

                } else if (WeightY != 1 && WeightX == 1) {
                    numcluXX = (int) pointXX[3];
                    numcluYY = (int) pointYY[3];
                    qf.union(numcluXX, numcluYY);
                    numclu1 = pointYY[3];

                } else {
                    if (pointXX[3] > pointYY[3]) {
                        numcluXX = (int) pointXX[3];
                        numcluYY = (int) pointYY[3];
                        qf.union(numcluYY, numcluXX);
                        numclu1 = pointXX[3];

                    } else {
                        numcluXX = (int) pointXX[3];
                        numcluYY = (int) pointYY[3];
                        qf.union(numcluXX, numcluYY);
                        numclu1 = pointYY[3];
                    }
                }

                if (X1 > Y1) {
                    myList.remove(X1);
                    myList.remove(Y1);
                } else {
                    myList.remove(X1);
                    myList.remove(Y1 - 1);
                }
                double[] newp = {interpX, interpY, WeightX + WeightY, numclu1};
                myList.add(newp);

            }

            /*the min legth between three clusters*/
            double minle = 0;
            if (pointnumber == 1) {
                System.out.printf(""\n%d %.2f %.2f"", 1, oripoint[0][0], oripoint[0][1]);
                System.out.printf(""\n%d"", 0);
            } else if (pointnumber == 2) {
                System.out.printf(""\n%d %.2f %.2f"", 1, oripoint[0][0], oripoint[0][1]);
                System.out.printf(""\n%d %.2f %.2f"", 1, oripoint[1][0], oripoint[1][1]);
                minle = Math.sqrt(Math.pow(oripoint[0][0] - oripoint[1][0], 2) + Math.pow(oripoint[0][1] - oripoint[1][1], 2));
                System.out.printf(""\n%.2f"", minle);

            } else {
                /*the min legth between three clusters*/
                double ccc = 0;
                for (int i = 0; i < pointnumber; i++) {
                    for (int j = i; j < pointnumber; j++) {
                        if (!qf.connected(i, j)) {
                            minle = Math.sqrt(Math.pow(oripoint[i][0] - oripoint[j][0], 2) + Math.pow(oripoint[i][1] - oripoint[j][1], 2));
                            if (ccc == 0) {
                                ccc = minle;
                            } else if (minle <= ccc) {
                                ccc = minle;
                            }

                        }
                    }
                }
                /*the weigthed center of three clusters*/
                double[] pointXXX = myList.get(0);
                double[] pointYYY = myList.get(1);
                double[] pointZZZ = myList.get(2);
                double[] size = {pointXXX[2], pointYYY[2], pointZZZ[2]};
                Arrays.sort(size);

                if (size[0] == 1 && size[1] == 1 && size[2] == 1) {
                    System.out.printf(""\n%.0f %.2f %.2f"", pointXXX[2], pointXXX[0], pointXXX[1]);
                    System.out.printf(""\n%.0f %.2f %.2f"", pointYYY[2], pointYYY[0], pointYYY[1]);
                    System.out.printf(""\n%.0f %.2f %.2f"", pointZZZ[2], pointZZZ[0], pointZZZ[1]);
                } else {
                    for (int i = 2; i >= 0; i--) {
                        if (size[i] == pointXXX[2]) {
                            System.out.printf(""\n%.0f %.2f %.2f"", pointXXX[2], pointXXX[0], pointXXX[1]);
                        } else if (size[i] == pointYYY[2]) {

                            System.out.printf(""\n%.0f %.2f %.2f"", pointYYY[2], pointYYY[0], pointYYY[1]);

                        } else if (size[i] == pointZZZ[2]) {
                            System.out.printf(""\n%.0f %.2f %.2f"", pointZZZ[2], pointZZZ[0], pointZZZ[1]);
                        }
                    }
                }

                System.out.printf(""\n%.2f"", ccc);
            }
        }
    }

    public static class BBB implements Comparable<BBB> {

        private final double p[];

        public BBB(double p[]) {
            this.p = p;
        }

        @Override
        public int compareTo(BBB that) {

            if (this.p[0] > that.p[0]) {
                return 1;
            } else if (this.p[0] < that.p[0]) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}

