
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author hung-wei
 */
public class MyConvexHull {
    
    public static Point2D[] Go(Point2D[] points, Stack S) {
        int N = S.size();
        Point2D[] GO = new Point2D[N];
        for (int i = 0; i < N; i++) {
            GO[i] = points[(int)S.pop()];
        }
        return GO;
    }
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        
        int length = 0;
        for (Point2D i : a) {
            length++;
        }
        //copy the origin array
        Point2D[] Origin = new Point2D[length];
        System.arraycopy(a, 0, Origin, 0, length);
        
        //find the y minimun
        Arrays.sort(a, Point2D.Y_ORDER);
        
        //Sort points by polar angle
        Arrays.sort(a, a[0].POLAR_ORDER);
.
        Stack Vertice = new Stack();
        Vertice.push(0); Vertice.push(1); Vertice.push(2);
        int A,B;
        for (int i = 3; i<length; i++) {
            B = (int)Vertice.pop(); A = (int)Vertice.pop();
            if (Point2D.ccw(a[A],a[B],a[i]) == 1) {
                Vertice.push(A); Vertice.push(B); 
                Vertice.push(i);
            }
            else if (Point2D.ccw(a[A],a[B],a[i]) == -1) {
                Vertice.push(A);
                i--;
            }        
        }
        
        int[] ans = new int[Vertice.size()];
        int f;
        int VerSize = Vertice.size();
        for (int i = 0; i<VerSize; i++) {
            f = (int)Vertice.pop();
            for (int j = 0; j<length; j++) {
                if (a[f].equals(Origin[j]))
                    ans[i] = j;
            }
        }
        
        Arrays.sort(ans);
//        for (int i=0; i<ans.length; i++)
//            a[ans[i]].draw();
        return ans;
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
    }
    
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0])))
        {
            double d = Double.parseDouble(br.readLine());
            int N = Integer.parseInt(br.readLine());
            
            Point2D[] points = new Point2D[N];
            
//            StdDraw.setCanvasSize(800, 800);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(.02);
            
            
            for (int i = 0; i < N; i++) {
                String[] data = br.readLine().split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                points[i] = new Point2D(x, y);
//                points[i].draw();
            }
            
            QuickFindUF CC = new QuickFindUF(N);
            for (int i = 0; i < N-1; i++) {
                for (int j = i; j < N; j++) {
                    if (points[i].distanceTo(points[j]) <= d)
                        CC.union(i, j);
                }
            }
            
            int[] Array = new int[N];
            for (int i = 0; i < N; i++) {
                Array[i] = CC.find(i);
            }
            
            int[] CopyArray = new int[N];
            System.arraycopy(Array, 0, CopyArray, 0, N);
            Arrays.sort(CopyArray);
            
            int ans = 0;
            Stack S = new Stack();
            for (int i = 0; i < N; i++) {
                if(Array[i] == CopyArray[0]) {
                    S.push(i);
                }
            }
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.setPenRadius(.02);
            
            int Ssize = S.size();
            if (Ssize >= 3) {
                
                ans += ConvexHullVertex(Go(points, S)).length;
            }
            else
                for (int i = 0; i<Ssize; i++) S.pop();
//            S = null;
            
            for (int k = 0; k < N-1; k++) {
                if(CopyArray[k+1] != CopyArray[k]) {
                    for (int i = 0; i < N; i++) {
                        if(Array[i] == CopyArray[k+1]) {
                            S.push(i);
                        }
                    }
                    Ssize = S.size();
                    if (Ssize >= 3)
                        ans += ConvexHullVertex(Go(points, S)).length;
                    else
                        for (int i = 0; i<Ssize; i++) S.pop();
                    
                }
            }
            
            System.out.println(ans);
            
        }
        
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it


            
    

    } 
}

