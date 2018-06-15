
import java.io.FileReader;
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
    public static void main(String[] args) throws Exception {
//        int N = 10;
//        Point2D[] points = new Point2D[N];
//        for (int i = 0; i < N; i++) {
//            double x = StdRandom.uniform();
//            double y = StdRandom.uniform();
//            points[i] = new Point2D(x, y);
//        }
//
//        int[] A=ConvexHullVertex(points);
//        for (int i = 0; i < A.length; i++) {
//            System.out.printf(""%d\n"",A[i]);
//            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
//        }
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            double d = Double.parseDouble(data[0]);
            data = br.readLine().split("","");
            int N = Integer.parseInt(data[0]);
            String line;
            String[] broken_line;
            int i = 0; int j =0; int A = 0;
            double x,y;
            Point2D [] pts = new Point2D[N];
            WeightedQuickUnionUF  uf = new WeightedQuickUnionUF (N);

            while((line = br.readLine()) != null){
                broken_line = line.split("" "");
                x = Double.parseDouble(broken_line[0]);
                y = Double.parseDouble(broken_line[1]);
                pts[i++] = new Point2D(x, y);
            }

            for(i = 0; i < N; i++) {
                for(j = 0; j < N; j++) {
                    if(pts[i].distanceTo(pts[j])<=d ) {
                        uf.union(i,j);
                    }
                }
            }
            
            for(i = 0; i < N; i++) {
                int k=0;
                for(j = 0; j < N; j++) 
                    if(uf.find(j) == i) 
                        k++;
                if(k>=3){
                    Point2D[] temp=new Point2D[k];k=0;
                    for(j = 0; j < N; j++) 
                        if(uf.find(j) == i)
                            temp[k++]=pts[j];
                    A+=ConvexHullVertex(temp).length;  
                }  
            }
   
//            int[] A=ConvexHullVertex(pts);
//            for ( i = 0; i < pts.length; i++) {
//            System.out.printf(""%d\n"",length[i]);
//            StdDraw.filledCircle(pts[i].x(), pts[i].y(), 0.01);
//            }
            System.out.printf(""%d\n"",A);
        }
    }
}
            

