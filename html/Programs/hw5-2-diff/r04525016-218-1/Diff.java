import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

/**
.
 */
public class MyConvexHull {
    public static void main(String args[]) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String data = br.readLine();
            double minDistance = Double.parseDouble(data);
            String data2 = br.readLine();
            int numPoint = Integer.parseInt(data2);
//            Point2D[] cc = new Point2D[numPoint];


            numPoint = 15;
            Point2D[] cc = new Point2D[numPoint];
            for(int i = 0 ; i < numPoint; i++){
                cc[i] = new Point2D(Math.random(), Math.random());
            }


            int i = 0;
            while (br.ready()) {
                String[] temp = br.readLine().split("" "");
                double x = Double.parseDouble(temp[0]);
                double y = Double.parseDouble(temp[1]);
//                cc[i] = new Point2D(x, y);
//                cc[i] = new Point2D(Math.random(), Math.random());
                i++;
            }
            br.close();


            int[] father = new int[numPoint];
            for (int c = 0; c < numPoint; c++) {
                father[c] = c;
            }
            for (int j = 0; j < numPoint; j++) {
                for (int k = 0; k < numPoint; k++) {
                    if (cc[j].distanceTo(cc[k]) <= minDistance) {
                        int root1 = getRoot(father, j);
                        int root2 = getRoot(father, k);
                        if (root1 < root2) {
                            father[root2] = root1;
                        } else father[root1] = root2;
                    }
                }
            }

            int[] root = new int[father.length];
            for(int k = 0; k < numPoint; k ++) {
                int temp = getRoot(father, k);
                root[k] = temp;
            }


            int numC = 0;
            for (i = 0; i < numPoint; i++) {
                ArrayList<Integer> label = new ArrayList<>();
                for (int j = 0; j < numPoint; j++) {
                    if (father[j] == i) {
                        label.add(j);
                    }
                }
                Point2D[] label5 = new Point2D[label.size()];
                for (int k = 0; k < label.size(); k++) {
                    label5[k] = cc[label.get(k)];
                }
                if (label5.length > 2) {
                    int[] index2 = ConvexHullVertex(label5);
                    numC += index2.length;
                }
            }

            System.out.println(numC);


            
//            for (int e = 0; e < father.length; e++) {
//                System.out.print(father[e] + "" "");
//            }


//            StdDraw.setCanvasSize(800, 800);
//            StdDraw.setXscale(-0.1, 1.1);
//            StdDraw.setYscale(-0.1, 1.1);
//            int counter = 0;
//            for (Point2D p : cc) {
//                StdDraw.setPenRadius(0.015);
//                p.draw();
//                StdDraw.setPenRadius(0.002);
//                StdDraw.circle(p.x(), p.y(), minDistance / 2);
//                StdDraw.text(p.x() + 0.03, p.y(), counter + ""("" + root[counter++] + "")"");
//            }
        }
    }

    public static int getRoot(int[] root, int index1) {

        while (index1 != root[index1]) index1 = root[index1];
        return index1;
    }

    public static int[] ConvexHullVertex(Point2D[] point2Ds) {
        ArrayList<Point2D> PG = new ArrayList<>();
        for (int i = 0; i < point2Ds.length; i++) {
            PG.add(point2Ds[i]);
        }


        int minIndex = getMin(point2Ds);
        Point2D min = point2Ds[minIndex];

        Comparator<Point2D> comparator = min.ATAN2_ORDER;
        sort(point2Ds, comparator);

        Stack<Point2D> data = new Stack<Point2D>();
        data.push(point2Ds[0]);
        data.push(point2Ds[1]);

        for (int i = 2; i < point2Ds.length; i++) {
            Point2D b = data.pop();
            Point2D c = data.pop();
            Point2D a = point2Ds[i];

            while (Point2D.ccw(c, b, a) != 1) {
                b = c;
                c = data.pop();
            }
            data.push(c);
            data.push(b);
            data.push(a);
        }


        int[] index = new int[data.size()];
        int count = 0;
        while (!data.isEmpty()) {
            Point2D p = data.pop();
            int i = PG.indexOf(p);
            index[count++] = i;
        }


        int[] index2 = new int[index.length];
        for (int i = 0; i < index.length; i++) {
            index2[i] = index[index.length - 1 - i];
        }


        return index2;
    }


    public static int getMin(Point2D[] a) {
        double min = a[0].y();
        int minIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].y() < min) {
                min = a[i].y();
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void sort(Point2D[] array, Comparator<Point2D> c) {
        sort2(array, 0, array.length - 1, c);
    }

    public static void sort2(Point2D[] array, int start, int end, Comparator<Point2D> c) {

        boolean goOn = true;
        int i = start + 1;
        int n = end;

        if (start >= end) return;

        while (goOn) {
            while (c.compare(array[start], array[i]) != -1) {
                if (i == end) break;
                i++;
            }

            while (c.compare(array[start], array[n]) != 1) {
                if (n == start) break;
                n--;
            }


            if (i < n) {
                Point2D temp = array[i];
                array[i] = array[n];
                array[n] = temp;
                goOn = true;
            } else {
                Point2D temp = array[n];
                array[n] = array[start];
                array[start] = temp;
                goOn = false;
            }

        }
        sort2(array, start, n - 1, c);
        sort2(array, n + 1, end, c);
    }


}
