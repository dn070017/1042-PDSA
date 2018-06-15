
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] b = new Point2D[a.length];
        for (int copy = 0; copy < a.length; copy++) {
            b[copy] = a[copy];
        }

        int i = 0;

        for (int j = 1; j < b.length; j++) {
            if (b[i].compareTo(b[j]) == 1) {
                i = j;
            }
        }

        Point2D min = b[i];
        Arrays.sort(b, min.ATAN2_ORDER);
        
        /*
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.01);
        min.draw();
        StdOut.println(""==="" + min);

        for (int h = 0; h < 10; h++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            b[h].draw();
            //StdOut.println(b[h]);
            StdDraw.show(100);
        }
        StdDraw.setPenColor(StdDraw.RED);
        min.draw();
      */
        int cc1,cc2,cc3,num=2;
        Stack<Integer> index1 = new Stack<>();
/*
        StdDraw.setPenRadius();
        b[0].drawTo(b[1]);
        StdDraw.show(1000);
*/

        index1.push(0);
        index1.push(1);
        
        while (true) {
            index1.push(num);
            cc3=index1.pop();
            cc2=index1.pop();
            cc1=index1.pop();        
            
            if (Point2D.ccw(b[cc1], b[cc2], b[cc3]) != 1) {
                while (Point2D.ccw(b[cc1], b[cc2], b[cc3]) != 1) {
                    if (cc1 == 0) {
                        cc1 = b.length - 1;
                    } else {
                        cc2=cc1;
                        cc1=index1.pop();
                    }
                }
            } else {
/*
                StdDraw.setPenColor(StdDraw.RED);
                b[cc1].drawTo(b[cc2]);
                b[cc2].drawTo(b[cc3]);
                StdDraw.show(1000);
*/
            }
            index1.push(cc1);
            index1.push(cc2);
            index1.push(cc3);
            if(cc3==0) break;
            num++;
            if(num == b.length){
                num=0;
            }
        }
        index1.pop();
        //StdOut.println(index1);

        int count = 0, temp;
        int out[] = new int[index1.size()];
        int size = index1.size();
        
        while (count < size) {
            temp = index1.pop();
            i = 0;
            //StdOut.println(temp);
            while (true) {
                if (a[i].equals(b[temp])) {
                    //StdOut.println(i);
                    //StdOut.println(b[temp]);
                    out[count] = i;
                    break;
                }
                i++;
            }
            count++;
        }

        Arrays.sort(out);
        /*for (int h = 0; h < count; h++) {
            StdOut.println(out[h]);
        }
        */
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return out;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data;
            data = br.readLine();
            double d = Double.parseDouble(data);
            data = br.readLine();
            int N = Integer.parseInt(data);
/*
            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(0, 1.2);
            StdDraw.setYscale(0, 1.2);
            StdDraw.setPenRadius(.01);
*/
            Point2D[] points = new Point2D[N];

            String datacut[];
            double x, y;
            int i = 0;

            while ((data = br.readLine()) != null) {
                datacut = data.split("" "");
                x = Double.parseDouble(datacut[0]);
                y = Double.parseDouble(datacut[1]);
                points[i] = new Point2D(x, y);

                //StdDraw.text(x + 0.02, y + 0.02, """" + i);
                //points[i].draw();
                i++;
            }
            Stack<Integer> index = new Stack<>();
            int index1[] = new int[N];
            for (i = 0; i < N; i++) {
                index1[i] = i;
            }

            int sum=0;
            
            i = 0;
            while(i<N){
            while(index1[i] != i) i++;   
            index.push(i);
            for (int l = i + 1; l < N; l++) {
                if (points[i].distanceTo(points[l]) <= d) {
                    index.push(l);
                    index1[l] = i;
                }
            }
            for (int j=0;j < N && index.size()>1;j++) {
                if (index1[j] == i) {
                    for (int l = j + 1; l < N; l++) {
                        if (index1[l] != i) {
                            if (points[j].distanceTo(points[l]) <= d) {
                                index.push(l);
                                index1[l] = i;
                            }
                        }
                    }
                }
            }
            /*
            StdOut.println(index);
            for (i = 0; i < N; i++) {
                StdOut.println(index1[i]);
            }
            */
            MyConvexHull mch = new MyConvexHull();
            int length = index.size();
            if (length >= 3) {
                Point2D[] input = new Point2D[length];
                for (int copy = 0; copy < length; copy++) {
                    input[copy] = points[index.pop()];
                }
                sum+=(mch.ConvexHullVertex(input)).length;
                //StdOut.println(sum);
            }
            i++;
            }
            StdOut.println(sum);
            // 
            //MyConvexHull.ConvexHullVertex(points);
            // 1. read in the file containing N 2-dimentional points
            // 2. create an edge for each pair of points with a distance <= d
            // 3. find connected components (CCs) with a size >= 3
            // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
            // 5. count the number of points in N serving as a convex hull vertex, print it
        }
    }
}
