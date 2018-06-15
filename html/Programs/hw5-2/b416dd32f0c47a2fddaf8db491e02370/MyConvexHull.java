
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import static java.util.Arrays.sort;
import java.util.HashMap;

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
    static HashMap<Point2D, Integer> map = new HashMap<Point2D, Integer>();

    public static int[] ConvexHullVertex(Point2D[] a) {
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }

        int min = 0;
        for (int j = 1; j < a.length; j++) {
            if (a[min].compareTo(a[j]) == +1) {
                Point2D temp = new Point2D(0, 0);
                temp = a[min];
                a[min] = a[j];
                a[j] = temp;
            }
        }
        Arrays.sort(a, a[0].POLAR_ORDER);

        
        Stack<Integer> vals = new Stack<Integer>();
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

        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = ans[i];
        }
        for (int i = 0; i < m; i++) {
            b[i] = map.get(a[ans[i]]);
        }
        sort(b);

        

        return b;
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String maxdistance = br.readLine();
            double maxdis = Double.parseDouble(maxdistance);
            String count = br.readLine();
            int N = Integer.parseInt(count);
            Point2D[] points = new Point2D[N];
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split("" "");
                double tempx = Double.parseDouble(temp[0]);
                double tempy = Double.parseDouble(temp[1]);
                points[i] = new Point2D(tempx, tempy);
            }
            QuickFindUF uf = new QuickFindUF(N);
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    if (points[i].distanceTo(points[j]) <= maxdis) {
                        uf.union(i, j);
                    }
                }
            }
            
            int ans = 0;
            for (int i = 0; i < N; i++) {
                Stack<Integer> a = new Stack<Integer>();
                for (int j = 0; j < N; j++) {
                    if (uf.find(j)==i) {
                        a.push(j);
                    }
                }
                if (a.size() > 2) {
                    Point2D[] aa= new Point2D[a.size()];
                    for (int j = 0; j < a.size(); j++) {
                        aa[j] = aa[a.pop()];
                    }
                    ans = ans + MyConvexHull.ConvexHullVertex(aa).length;
                }else if (a.size()>0 && a.size()<2) {
                    ans = ans + a.size();
                    a = null;
                }
            }
            System.out.printf(ans + """");
        }

    }

}

