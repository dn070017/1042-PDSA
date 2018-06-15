import java.util.Arrays;

/**
.
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a) {

        int N = a.length;
        int yMin = 0;
        Point2D[] a2 = new Point2D[N];

        for (int i = 0; i < N; i++)
            a2[i] = a[i];

        for (int i = 0; i < N; i++) {
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
            }

            int temp = Point2D.ccw(a2[ccwArray[0]],a2[ccwArray[1]],a2[ccwArray[2]]);
            if (temp == 1) {
                index++;
            } else {
                isConvex[ccwArray[1] - 1] = -1;
                ansCount--;
                if (index != 0) {
                    index--;
                }

            }
        }

        int[] ans = new int[ansCount];
        int[] ansNew = new int[ansCount];
        ansNew[0] = sortIndex[0];
        ans[0] = sortIndex[0];
        for (int i = 0; i < N-1; i++) {
            if (isConvex[i] != -1) {
                ans[ansCount - 1] = sortIndex[isConvex[i]];
                ansCount--;
            }
        }
        for (int i = 0; i < ans.length; i++) {
            int tempMin = 10;
            if (i > 0) {
                for (int j = 0; j < ans.length; j++) {
                    if ((ans[j] < tempMin) && (ans[j] > ansNew[i - 1])) {
                        tempMin = ans[j];
                    }
                }
            } else {
                for (int j = 0; j < ans.length; j++) {
                    if (ans[j] < tempMin) {
                        tempMin = ans[j];
                    }
                }
            }
            ansNew[i] = tempMin;
        }
        return ansNew;
    }
    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//        }
//        double rand1,rand2;
//        Point2D[] a2 = new Point2D[10];
//        for (int i = 0; i < a2.length; i++) {
//            rand1 = StdRandom.uniform();
//            rand2 = StdRandom.uniform();
//            a2[i] = new Point2D(rand1,rand2);
//        }
//        int[] ans =  ConvexHullVertex(a2);
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println(ans[i]);
//        }
    }
}

