import java.util.ArrayList;
import java.util.Comparator;

/**
.
 */
public class MyConvexHull {

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

