import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
.
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a) {

        int N = a.length;
        double rand1,rand2;
        int yMin = 0;
        Point2D[] a2 = new Point2D[N];

        for (int i = 0; i < N; i++) {
            rand1 = StdRandom.uniform();
            rand2 = StdRandom.uniform();
//            System.out.println((int)(rand1*1000)/1000.0 + "" / "" + (int)(rand2*1000)/1000.0);
            a[i] = new Point2D(rand1,rand2);
            a2[i] = new Point2D(rand1,rand2);
            if (i > 0) {
                int yOrder = Point2D.Y_ORDER.compare(a[yMin], a[i]);
                if (yOrder == 1) yMin = i;
            }
        }
        Point2D p = new Point2D(a[yMin].x(), a[yMin].y());
        Arrays.sort(a2, p.ATAN2_ORDER);

        int[] sortIndex = new int[N];
        int[] isConvex = new int[N-1];
        for (int i = 1; i < N; i++)
            isConvex[i - 1] = i;

        for (int i = 0; i < N; i++) {
            boolean e = false;
            int j = 0;
            while (!e) {
                e = a2[i].equals(a[j]);
                if (e)
                    sortIndex[i] = j;
                else
                    j++;
            }
        }

        int index = 0;
        int[] ccwArray = new int[3];
        int ansCount = N;

        while (index < isConvex.length - 1) {
            if (ccwArray[1] == N-2)
                break;
            int ccwCount = 0;
            int j = index;
            while (ccwCount < 3) {
                if (isConvex[j] != -1) {
                    ccwArray[ccwCount] = isConvex[j];
                    ccwCount++;
                }
                j++;
//                System.out.println(j+"" /j"");
//                System.out.println(index+"" /index"");
            }

//            System.out.println(ccwArray[0]+""/""+ccwArray[1]+""/""+ccwArray[2]);

            int temp = Point2D.ccw(a2[ccwArray[0]], a2[ccwArray[1]], a2[ccwArray[2]]);
            if (temp == 1) {
                index++;
//                System.out.println(""ha"");
            } else {
                isConvex[ccwArray[1] - 1] = -1;
                ansCount--;
                if (index != 0) {
                    index--;
//                    System.out.println(""hi"");
                }

            }
        }

        int[] ans = new int[ansCount];
        ans[0] = sortIndex[0];
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int i = 0; i < N-1; i++) {
            if (isConvex[i] != -1) {
                ans[ansCount - 1] = sortIndex[isConvex[i]];
                a2[isConvex[i]].draw();
                ansCount--;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

        }
    }
}

