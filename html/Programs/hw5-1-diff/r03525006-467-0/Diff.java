import java.util.Arrays;

/**
.
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a) {
        int N = a.length;
        int yMin = 0;


        for (int i = 0; i < N; i++) {
            if (i > 0) {
                int yOrder = Point2D.Y_ORDER.compare(a[yMin], a[i]);
                if (yOrder == 1) yMin = i;
            }
        }
        Point2D p = new Point2D(a[yMin].x(), a[yMin].y());
        Arrays.sort(a, p.ATAN2_ORDER);

        int[] sortIndex = new int[N];
        int[] isConvex = new int[N-1];
        for (int i = 1; i < N; i++)
            isConvex[i - 1] = i;

        for (int i = 0; i < N; i++) {
            boolean e = false;
            int j = 0;
            while (!e) {
                e = a[i].equals(a[j]);
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

            int temp = Point2D.ccw(a[ccwArray[0]], a[ccwArray[1]], a[ccwArray[2]]);
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
                a[isConvex[i]].draw();
                ansCount--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {


    }

}

