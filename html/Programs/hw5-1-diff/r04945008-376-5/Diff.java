import java.util.Arrays;
    
public class MyConvexHull {private Stack<Point2D> hull = new Stack<Point2D>();

    public static int[] ConvexHullVertex(Point2D[] pts) {
        Stack<Point2D> hull = new Stack<Point2D>();
        // defensive copy
        int N = pts.length;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++)
            points[i] = pts[i];
        Arrays.sort(points);
        Arrays.sort(points, 1, N, points[0].POLAR_ORDER);
        hull.push(points[0]);       // p[0] is first extreme point
        // find index k1 of first point not equal to points[0]
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        //if (k1 == N) {vertex[0]=N-1; return vertex;}        // all points equal
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        hull.push(points[k2-1]);    // points[k2-1] is second extreme point
        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
        
        int[] vertex = new int[hull.size()];
        Point2D[] temp = new Point2D[hull.size()];
        int i=0;
        while (hull.size() > 0) {
            temp[i++]=hull.pop();
        }
        
        int k=0;
        for( i=0; i < temp.length; i++)
            for (int j = 0; j < N; j++) {
                if (pts[j] == temp[i]) {
                    vertex[k++]=j;
                }
            }
        Arrays.sort(vertex);
        return vertex;
    }
    public static void main(String[] args) {
        int N = 10;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            points[i] = new Point2D(x, y);
        }

        int[] A=ConvexHullVertex(points);
        for (int i = 0; i < A.length; i++) {
            System.out.printf(""%d\n"",A[i]);
            //StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
        }
    }
}
            

