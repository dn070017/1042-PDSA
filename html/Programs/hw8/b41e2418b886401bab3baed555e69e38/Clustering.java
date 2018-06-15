
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Clustering {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int pointnumber = Integer.parseInt(br.readLine());
            double oripoint[][] = new double[pointnumber][2];
            ArrayList<double[]> myList = new ArrayList();
            QuickFindUF qf = new QuickFindUF(pointnumber);
            ArrayList<double[]> poiinclu1 = new ArrayList();
            ArrayList<double[]> poiinclu2 = new ArrayList();
            ArrayList<double[]> poiinclu3 = new ArrayList();
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
                myList.add(point);

                oripoint[i][0] = point[0];
                oripoint[i][1] = point[1];

//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.setPenRadius(.01);
//                Point2D pointss = new Point2D(point[0], point[1]);
//                pointss.draw();
//                StdDraw.text(point[0], point[1] + 0.01, Double.toString(point[3]));
            }
//            for(int i=0;i<pointnumber;i++){
//                System.out.printf(""%f %f \n"",point[i][0],point[i][1]);
//
//            }

            /*clustering*/
            while (myList.size() > 3) {
                int n = myList.size();
                int m = n * (n - 1) / 2;

                Integer data[] = new Integer[2];
                ST<Double, String> st = new ST<>();

                /*distance*/
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        double[] pointX = myList.get(i);
                        double[] pointY = myList.get(j);
                        double a = Math.sqrt(Math.pow(pointX[0] - pointY[0], 2) + Math.pow(pointX[1] - pointY[1], 2));

                        data[0] = i;
                        data[1] = j;
                        String x = Integer.toString(i);
                        String y = Integer.toString(j);
                        String XWEI = String.valueOf(pointX[2]);
                        String YWEI = String.valueOf(pointY[2]);
                        String num = x + "","" + y + "","" + XWEI + "","" + YWEI;

                        /*put sybol table*/
                        st.put(a, num);

                    }
                }

                /*find min*/
                Double bb = st.min();

                /*min distance of two points */
                String minp[] = st.get(bb).split("","");
                int X1 = Integer.parseInt(minp[0]);
                int Y1 = Integer.parseInt(minp[1]);
                double WeightX = Double.parseDouble(minp[2]);
                double WeightY = Double.parseDouble(minp[3]);
                double[] pointXX = myList.get(X1);
                double[] pointYY = myList.get(Y1);

                /*the weigthed center of tw0 points*/
                double interpX = pointXX[0] + ((pointYY[0] - pointXX[0]) * (WeightY / (WeightX + WeightY)));
                double interpY = pointXX[1] + (pointYY[1] - pointXX[1]) * (WeightY / (WeightX + WeightY));
//                System.out.printf(""\n%f %f %f\n"", interpX, interpY, WeightX + WeightY);
//                for (int i = 0; i < pointnumber; i++) {
//                    if (oripoint[i][0] == pointXX[0] && oripoint[i][1] == pointXX[1]) {
//
//                    }
//                }
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

                st.delete(bb);
                if (X1 > Y1) {
                    myList.remove(X1);
                    myList.remove(Y1);
                } else {
                    myList.remove(X1);
                    myList.remove(Y1 - 1);
                }
                double[] newp = {interpX, interpY, WeightX + WeightY, numclu1};
                myList.add(newp);

//                StdDraw.setPenColor(StdDraw.RED);
//                StdDraw.setPenRadius((WeightX + WeightY) / 100);
//                Point2D pointss = new Point2D(interpX, interpY);
//                pointss.draw();
//                StdDraw.text(interpX, interpY + 0.01, Double.toString(numclu1));
            }

            /*the min legth between three clusters*/
            double minle = 0;
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

            for (int i = 2; i >= 0; i--) {
                if (size[i] == pointXXX[2]) {
                    System.out.printf(""\n%.0f %.2f %.2f"", pointXXX[2], pointXXX[0], pointXXX[1]);
                } else if (size[i] == pointYYY[2]) {

                    System.out.printf(""\n%.0f %.2f %.2f"", pointYYY[2], pointYYY[0], pointYYY[1]);

                } else if (size[i] == pointZZZ[2]) {
                    System.out.printf(""\n%.0f %.2f %.2f"", pointZZZ[2], pointZZZ[0], pointZZZ[1]);
                }
            }
            System.out.printf(""\n%.2f"", ccc);

//            double[] clusterdis = new double[3];
//            clusterdis[0] = Math.sqrt(Math.pow(pointXXX[0] - pointYYY[0], 2) + Math.pow(pointXXX[1] - pointYYY[1], 2));
//            clusterdis[1] = Math.sqrt(Math.pow(pointXXX[0] - pointZZZ[0], 2) + Math.pow(pointXXX[1] - pointZZZ[1], 2));
//            clusterdis[2] = Math.sqrt(Math.pow(pointZZZ[0] - pointYYY[0], 2) + Math.pow(pointZZZ[1] - pointYYY[1], 2));
//            Arrays.sort(clusterdis);
//            System.out.printf(""\n%.2f %.2f %.2f"", clusterdis[0],clusterdis[1],clusterdis[2]);
        }
    }
}

