

/**
 * 1042 PDSA
 * hw05-1_MyConvexHull
 * @author Robert
 */
public class MyConvexHull {
    public MyConvexHull(){}
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.01);
        GrahamScan graham = new GrahamScan(a);
        Stack<Integer> s = new Stack<Integer>();
        for (Point2D p : graham.hull()){
            for (int i = 0; i < a.length; i++) {
                if (p == a[i]){
                    s.push(i);
                    a[i].draw();
                }
            }
        }
        int[] x;
        StdOut.println(s.size());
        x = new int[s.size()+1];
        for (int i=0; i< s.size(); i++){
            x[s.size()-i] = s.pop();
        }
        return x;
    }


    
    
    public static void main(String[] args) {
        int N = 50;
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-20, 120);
        StdDraw.setYscale(-20, 120);
        StdDraw.setPenRadius(.01);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }
        MyConvexHull test = new MyConvexHull();
        StdOut.println(ConvexHullVertex(points));
    }
    
}

