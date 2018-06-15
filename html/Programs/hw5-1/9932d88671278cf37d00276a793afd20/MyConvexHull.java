
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
                Point2D temp = new Point2D(0,0);
                temp = a[min];
                a[min] = a[j];
                a[j] = temp;
            }
        }
        Arrays.sort(a, a[0].POLAR_ORDER);
        
        
       
        int m = 0;      // m 為凸包頂點數目
        Point2D[] an = new Point2D[a.length];
        int[] ans = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            while (m >= 2 && Point2D.ccw(an[m - 2], an[m - 1], a[i]) < 0) {
                m--;
            }
            // 添加新的點
            
            an[m] = a[i];
            ans[m] = i;
            m++;
        }
        
        int[] b = new int[m] ;
        for (int i = 0; i < m; i++) {
            b[i] = ans[i];
        }
        sort(b);
        
        
        
        return b;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        

        
    }
    
}
