
import edu.princeton.cs.algs4.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.Stack;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] b = a;
        int i=0;

        for (int j = 1; j < b.length; j++) {
            if(b[i].compareTo(b[j]) == 1) i=j;
        }
        Point2D min=b[i];
        Arrays.sort(b, min.polarOrder());
        
        int cc1=0,cc2=1,cc3=2;
        Stack<Integer> index = new Stack<>();
        /*
        StdDraw.setPenRadius();  
        b[b.length-1].drawTo(b[0]);
        b[0].drawTo(b[1]);
        StdDraw.show(1000);
        */
        index.push(b.length-1);
        index.push(0);
        
        while(cc3 < b.length){
            
            if(Point2D.ccw(b[cc1],b[cc2],b[cc3])!=1){
                cc2=cc3;
            }
            else{
                /*
                StdDraw.setPenColor(StdDraw.RED);
                b[cc1].drawTo(b[cc2]);
                b[cc2].drawTo(b[cc3]);
                StdDraw.show(1000);
                */
                
                index.push(cc2);
                cc1=cc2;
                cc2=cc3;
            }
            cc3++;
        }
        
        //StdOut.println(index);
        
        int count=0,temp;
        int out[]=new int[index.size()];
        int size=index.size();
        
        while(count < size){
            temp=index.pop();
            i=0;
            while(true){
                if(a[i].equals(b[temp])){
                    out[count]=i;
                    
                    break;
                }
                i++;
            }
            count++;
        }     
        Arrays.sort(out);
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return out;
    }

    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data;
            data = br.readLine();
            int N = Integer.parseInt(data);
            /*
            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(0, 1);
            StdDraw.setYscale(0, 1);
            StdDraw.setPenRadius(.01);
*/
            Point2D[] points = new Point2D[N];

            String datacut[];
            double x, y;
            double minx = 0, miny = 100;
            int i = 0;

            while ((data = br.readLine()) != null) {
                datacut = data.split("" "");
                x = Double.parseDouble(datacut[0]);
                y = Double.parseDouble(datacut[1]);
                points[i] = new Point2D(x, y);
                //points[i].draw();
                if (miny > y) {
                    minx = x;
                    miny = y;
                }
                i++;
            }

           // MyConvexHull mch = new MyConvexHull();

            int result[]=MyConvexHull.ConvexHullVertex(points);
/*
            Point2D p = new Point2D(minx, miny);
            StdDraw.setPenColor(StdDraw.RED);

            p.draw();

            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.BLUE);
             Arrays.sort(points, p.atan2Order());
           for (i = 0; i < N; i++) {
                p.drawTo(points[i]);
                StdDraw.show(500);
            }*/
        }
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

    }
}

