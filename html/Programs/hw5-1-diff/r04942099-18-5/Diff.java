
import java.util.Arrays;



public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] points = new Point2D[a.length];
        for(int i=0;i<a.length;i++){
            points[i] = a[i];
        }
        
        
        Merge.sort(points);
        Arrays.sort(points, points[0].POLAR_ORDER);
        Stack<Point2D> vals = new Stack<Point2D>();
        
        int N = a.length;
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        //if (k1 == N) return;        // all points equal
        vals.push(points[0]);
        vals.push(points[k1]);
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        vals.push(points[k2-1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = vals.pop();
            while (Point2D.ccw(vals.peek(), top, points[i]) <= 0) {
                top = vals.pop();
            }
            vals.push(top);
            vals.push(points[i]);
        }
        int number = vals.size();
        Point2D[] vertex = new Point2D[number];
        for(int u = 0;u<number;u++){
            vertex[u] = vals.pop();
        }
        int[] index = new int[number];
        int counter=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<number;j++){
                if(a[i].equals(vertex[j])){
                    index[counter]=i;
                    counter++;
                } 
            }
        }
        
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
            return index;
    }

    public static void main(String[] args) {
int N = 30;

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-100, 700);
        StdDraw.setYscale(-100, 700);
        StdDraw.setPenRadius(.02);

        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {

            int x = StdRandom.uniform(600);
            int y = StdRandom.uniform(600);
            points[i] = new Point2D(x, y);
            points[i].draw();

        }
        
        int[] test = new int[N];
        test = MyConvexHull.ConvexHullVertex(points);
        Merge.sort(points);

        Arrays.sort(points, points[0].POLAR_ORDER);
        for (int i = 0; i < N; ++i) {
            StdDraw.text(points[i].x(), points[i].y() + 12, String.valueOf(i));
        }

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.02);
        points[0].draw();
        
        Stack<Point2D> vals = new Stack<Point2D>();
        
        
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        if (k1 == N) return;        // all points equal
        vals.push(points[0]);
        vals.push(points[k1]);
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        vals.push(points[k2-1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = vals.pop();
            while (Point2D.ccw(vals.peek(), top, points[i]) <= 0) {
                top = vals.pop();
            }
            vals.push(top);
            vals.push(points[i]);
        }
        //System.out.println(vals.size());
        
        StdDraw.setPenColor(StdDraw.GREEN);
        int number = vals.size();
        
        
        for(int i=0; i<number;i++){
            System.out.println(test[i]);
        }
        
        
        Point2D[] vertex = new Point2D[number];
        for(int u = 0;u<number;u++){
            vertex[u] = vals.pop();
            vertex[u].draw();
        }
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

    }
}

