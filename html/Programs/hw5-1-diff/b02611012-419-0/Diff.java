

import java.util.Arrays;
import static java.util.Arrays.sort;
import java.util.Comparator;

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
     * @param a
     * @param args the command line arguments
     */
    public static int[] ConvexHullVertex(Point2D[] a) {
        int min = 0;
        for (int j = 1; j < a.length; j++) {
            if (a[min].compareTo(a[j]) == +1) {
                min = j;
            }
        }
        Arrays.sort(a, a[min].polarOrder());
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
        

    }


}
