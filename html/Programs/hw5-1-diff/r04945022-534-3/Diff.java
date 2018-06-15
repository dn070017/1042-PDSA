
import java.util.*;

/**
 *
 * @author Daniel
 */
public class MyConvexHull {
 
private static Stack<Point2D> chh = new Stack<Point2D>();
    Stack h = new Stack();

    /**
.
     *
     * @param pts the array of points
     * @throws NullPointerException if <tt>points</tt> is <tt>null</tt> or if
     * any entry in <tt>points[]</tt> is <tt>null</tt>
     */
    public static int[] ConvexHullVertex(Point2D[] pts) {

        // defensive copy
        int N = pts.length;
        int[] ans;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = pts[i];
        }

        // preprocess so that points[0] has lowest y-coordinate; break ties by x-coordinate
        // points[0] is an extreme point of the convex hull
        // (alternatively, could do easily in linear time)
        Arrays.sort(points);

        // sort by polar angle with respect to base point points[0],
        // breaking ties by distance to points[0]
        Arrays.sort(points, 1, N, points[0].POLAR_ORDER);

        chh.push(points[0]);       // p[0] is first extreme point

        // find index k1 of first point not equal to points[0]
        int k1;
        for (k1 = 1; k1 < N; k1++) {
            if (!points[0].equals(points[k1])) {
                break;
            }
        }
//        if (k1 == N) {
//            return;        // all points equal
//        }
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++) {
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) {
                break;
            }
        }
        chh.push(points[k2 - 1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = chh.pop();
            while (Point2D.ccw(chh.peek(), top, points[i]) <= 0) {
                top = chh.pop();
            }
            chh.push(top);
            chh.push(points[i]);

        }
        ans = new int[chh.size()];
//        System.out.printf(""hull size=%d\n"", hull.size());
        for (int i = 0; i < ans.length; i++) {
            double check = 0;
            check = chh.pop().y();
            //System.out.printf(""check=%f\n"", check);
            for (int j = 0; j < points.length; j++) {
                if (check == pts[j].y()) {
                    //System.out.printf(""value=%f  index= %d\n"",back[i].y(),j);
                    ans[i] = j;
                }
            }

//                System.out.printf(""index= %d\n"",ans[i]);
        }
        Arrays.sort(ans);
        return ans;
        //assert isConvex();
    }

    /**
.
     *
     * @return the extreme points on the convex hull in counterclockwise order
     */
    public Iterable<Point2D> hull() {
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D p : chh) {
            s.push(p);
        }
        return s;
    }

    /**
     * Unit tests the <tt>ClosestPair</tt> data type. Reads in an integer
     * <tt>N</tt> and <tt>N</tt> points (specified by their <em>x</em>- and
     * <em>y</em>-coordinates) from standard input; computes their convex hull;
.
     */
    public static void main(String[] args) {
       
//            //StdOut.println(p);
//        }
    }

}

