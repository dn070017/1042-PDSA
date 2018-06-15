
import java.util.Arrays;
import static java.util.Arrays.sort;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author user
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
    public static int[] ConvexHullVertex(Point2D[] a) {
        int min = 0;
        for (int j = 1; j < a.length; j++) {
            if (a[min].compareTo(a[j]) == +1) {
                min = j;
            }
        }
        Arrays.sort(a, a[min].POLAR_ORDER);
        int m = 0;      // m 為凸包頂點數目
        int ans[] = null;
        for (int i = 0; i < a.length; i++) {
            while (m >= 2 && Point2D.ccw(a[ans[m - 2]], a[ans[m - 1]], a[i]) < 0) {
                m--;
            }
            // 添加新的點
            ans[m++] = i;
            System.out.printf(ans[m]+"""");
        }
        sort(ans);
        return ans;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        int N = 10;
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        
    }
    
}

