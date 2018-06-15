
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] b = new Point2D[a.length];
        System.arraycopy(a, 0, b, 0, a.length);

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
            int m = 0;

            while ((data = br.readLine()) != null) {
                datacut = data.split("" "");
                x = Double.parseDouble(datacut[0]);
                y = Double.parseDouble(datacut[1]);
                points[m] = new Point2D(x, y);

                //StdDraw.text(x + 0.02, y + 0.02, """" + i);
                //points[i].draw();
                m++;
            }
                   WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        boolean[] unused = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (points[i].distanceTo(points[j]) <= d) {
                    uf.union(i, j);
                }
            }
            unused[i] = true;
        }

        Stack<Integer> index = new Stack<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (unused[i]) {
                for (int j = 0; j < N; j++) {
                    if (uf.find(j) == i) {
                        index.push(j);
                        unused[j] = false;
                    }
                }

                int length = index.size();
                
                if (length >= 3) {
                    Point2D[] input = new Point2D[length];
                    for (int copy = 0; copy < length; copy++) {
                        input[copy] = points[index.pop()];
                    }
                    sum += (MyConvexHull.ConvexHullVertex(input)).length;
                } else {
                    for (int n = 0; n < length; n++) {
                        index.pop();
                    }
                }
            }
        }
        StdOut.println (sum);
    }
    }   
}

