/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;
/*import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.UF;*/
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author jerry
 */
public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] original = a.clone();
        int N = a.length;
        Stack<Point2D> hull = new Stack<Point2D>();
        Arrays.sort(a);
        Arrays.sort(a, 1, N, a[0].POLAR_ORDER);

        /*StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(a[0].x(), a[0].y(), 0.01);
        StdDraw.setPenColor(StdDraw.BLACK);*/
        
        //int count = 0;
        
        /*for (int i = 0; i < a.length; i++) {
            StdDraw.text(a[i].x(), a[i].y() + 0.01, String.valueOf(count));
            count++;
        }*/

        hull.push(a[0]);
        int k1;
        for (k1 = 1; k1 < N; k1++) {
            if (!a[0].equals(a[k1])) {
                break;
            }
        }
        if (k1 == N) {
            int[] last = {0};
            return last;
        }

        int k2;
        for (k2 = k1 + 1; k2 < N; k2++) {
            if (Point2D.ccw(a[0], a[k1], a[k2]) != 0) {
                break;
            }
        }
        hull.push(a[(k2 - 1)]);

        for (int i = k2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, a[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(a[i]);
        }
        Stack<Point2D> finalhull = new Stack<Point2D>();
        int finalnum = 0;
        
        for (Point2D p : hull) {
            finalhull.push(p);
            finalnum++;
        }
        
        int[] finalindex = new int[finalnum];
        int currentpoint = 0;
        for (Point2D p : finalhull) {
            for (int i = 0; i < N; i++) {
                if (p.equals(original[i])) {
                    finalindex[currentpoint] = i;
                    currentpoint++;
                }
            }
        }
        
        /*for(int i = 0; i < finalnum; i++){
        System.out.print(finalindex[i]+"" "");
        System.out.println();
        }*/
        
        Arrays.sort(finalindex);
        
        return finalindex;
    }
    /*public static void main(String[] args) {
        Point2D[] test = new Point2D[10];
        for(int i = 0; i <10; i++){
        double x = StdRandom.uniform();
        double y = StdRandom.uniform();
        test[i] = new Point2D(x,y);
        }
        for(int i = 0; i < 10; i++){
            StdDraw.filledCircle(test[i].x(), test[i].y(), 0.01);
            StdDraw.text(test[i].x(), test[i].y()+0.05, String.valueOf(i));
        }
        int[] result = ConvexHullVertex(test);
        for(int i = 0; i < result.length; i++){
        System.out.print(result[i]+"" "");
        }
    }
    
}*/

           
    public static void main(String[] args) throws Exception {
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            double distance = Double.parseDouble(data);
            data = br.readLine();
            int totalnum = Integer.parseInt(data);
            Point2D[] a = new Point2D[totalnum];
            double x, y;
            int currentcount = 0;

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] data2 = line.split("" "");
                x = Double.parseDouble(data2[0]);
                y = Double.parseDouble(data2[1]);
                a[currentcount] = new Point2D(x, y);
                currentcount++;
            }//read in datapoint
            
            UF PointsCC = new UF(totalnum);
            for(int i = 0; i < totalnum; i++){
                for(int j = i; j < totalnum; j++){
                    if (a[i].distanceTo(a[j]) <= distance){
                    PointsCC.union(i,j);
                    }//Checking all pairs to get the union
                }
            }
            
            int[] roots = new int[totalnum];
            for (int i = 0; i < totalnum; i++) {
                roots[PointsCC.find(i)]++;
            }//filling roots to the toal point counts per CC
            

            
            
            int result = 0;
            
            for (int i = 0; i < totalnum; i++) {
                if (roots[i] >= 3) {
                    Point2D[] Currentconvex = new Point2D[roots[i]];
                    int currentpoint = 0;
                    
                    for(int j = 0; j < totalnum; j++){
                        if(PointsCC.find(j) == i){
                            Currentconvex[currentpoint] = a[j];
                            currentpoint++;
                        }// adding selected Point2D to currentarray
                    }
                    
                    int[] currentresult = ConvexHullVertex(Currentconvex);
                    result += currentresult.length;
                }
            }
            System.out.print(result);
        }

    }
}

