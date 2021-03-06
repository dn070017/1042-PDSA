/*
.
 * To change this template file, choose Tools | Templates
.
 */

//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.Stack;
//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.StdRandom;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author 士齊
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
//    畫10個點 從 0,0 到 9,9
    public void DrawingPratice1() {
        Point2D[] a = new Point2D[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = new Point2D(i * 0.1, i * 0.1);
            StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
        }
    }

//    畫random 10個點，連線上一個，第一個點是紅的
    public void DrawingPratice2() {
        int N = 10;
        Point2D[] a = new Point2D[N];

        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();

            a[i] = new Point2D(x, y);

            if (i != 0) {
                StdDraw.setPenColor(Color.BLACK);
                a[i].drawTo(a[i - 1]);
            } else {
                StdDraw.setPenColor(Color.red);
            }
            StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
        }
    }

//    畫random 10個點，最左下的是紅色
    public static void DrawingPratice3(int N) {
//        int N = 10;
        Point2D[] a = new Point2D[N];
        int smallest = 0;

        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();

            a[i] = new Point2D(x, y);

            StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);

            if (a[smallest].compareTo(a[i]) == 1) {
                smallest = i;
            }

        }

        StdDraw.setPenColor(Color.red);
        StdDraw.filledCircle(a[smallest].x(), a[smallest].y(), 0.01);

    }

    public static void DrawingPratice4() {
        double x0 = 0.5;
        double y0 = 0.5;
        int N = 50;
        int smallest = 0;

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            points[i] = new Point2D(x, y);
            points[i].draw();
            if (points[smallest].compareTo(points[i]) == 1) {
                smallest = i;
            }
        }

        // draw p = (x0, x1) in red
//        Point2D p = new Point2D(x0, y0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.02);
        points[0].draw();

        // draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Arrays.sort(points, points[smallest].POLAR_ORDER);
        for (int i = 0; i < N; i++) {
            points[0].drawTo(points[i]);
//            StdDraw.show(100);
        }

        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < N; i++) {
            StdDraw.text(points[i].x(), points[i].y(), """" + i);

        }
    }

    public static int[] ConvexHullVertex(Point2D[] a) {
        Stack<Point2D> hull = new Stack<Point2D>();
        int[] Convex;

        Point2D[] ori = new Point2D[a.length];
        for (int i = 0; i < a.length; i++) {
            ori[i] = new Point2D(a[i].x(), a[i].y());
        }

        Arrays.sort(a, Point2D.Y_ORDER);
        Arrays.sort(a, a[0].POLAR_ORDER);

        hull.push(a[0]);
        hull.push(a[1]);

        
        
        for (int i = 2; i < a.length; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, a[i]) <= 0) {
                top = hull.pop();
            }

                            
            hull.push(top);
            hull.push(a[i]);
        }

        
//        StdDraw.setPenColor(Color.red);
//        StdDraw.filledCircle(a[0].x(), a[0].y(), 0.01);
//
//        StdDraw.setPenRadius(.001);
//        StdDraw.setPenColor(Color.green);
//        for (int i = 1; i < a.length; i++) {
//    
////            a[i].drawTo(a[0]);
//
////            StdDraw.show(10);
//        }
//        StdDraw.setPenColor(Color.BLUE);
        
        
        Point2D begin = a[0];
        int size = hull.size();
        Convex = new int[size];

        for (int i = 0; i < size; i++) {
            Point2D end = hull.pop();
            for (int j = 0; j < a.length; j++) {
                if (end.x() == ori[j].x()) {
                    if (end.y() == ori[j].y()) {
                        Convex[i] = j;
                        break;
                    }
                }
            }

//            begin.drawTo(end);
//            StdDraw.show();
          
            
            begin = end;
        }

        Arrays.sort(Convex);

        return Convex;
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String data = br.readLine();
            double distance = Double.parseDouble(data);

            data = br.readLine();
            int N = Integer.parseInt(data);

//           int N = 20;
            
            Point2D[] datapoint = new Point2D[N];
            WeightedQuickUnionUF UF = new WeightedQuickUnionUF(N);

            for (int i = 0; i < N; i++) {
                data = br.readLine();
                String[] position = new String[2];
                position = data.split("" "");
                datapoint[i] = new Point2D(Double.parseDouble(position[0]), Double.parseDouble(position[1]));

            }
            
 
  
//        for (int i = 0; i < N; i++) {
//            double x = StdRandom.uniform();
//            double y = StdRandom.uniform();
//
//            datapoint[i] = new Point2D(x, y);
//
//
//         StdDraw.setPenRadius(.01);
//          
//          datapoint[i].draw();
//  
//        }
//
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if (datapoint[i].distanceTo(datapoint[j]) <= distance) {
                        UF.union(i, j);
//                         StdDraw.setPenRadius(.002);
//                        StdDraw.setPenColor(Color.RED);
//                        datapoint[i].drawTo(datapoint[j]);
                    }
                }
            }

            int[] CC = new int[UF.count()];

            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = UF.find(i);
            }

            Arrays.sort(a);
            CC[0] = a[0];

            int[] CCnumber = new int[UF.count()];
            int k = 0;
            int n = 0;
            int size = 1;

            for (int i = 1; i < N; i++) {
                if (a[i] == CC[n]) {
                    size++;

                }
                if (a[i] > a[i - 1]) {
                    if (size >= 3) {
                        CCnumber[k] = a[i - 1];
                        k++;
                    }
                    size = 1;
                    n++;
                    CC[n] = a[i];
                }
                if((i == N - 1) && size >= 3){
                    CCnumber[k] = a[i];
                    k++;
                }
            }
           
            int leng = 0;
            
            for(int i =0; i < k; i++){
                
                int count = 0;
                for(int j = 0; j < N; j++){
                    if(CCnumber[i] == UF.find(j)){
                        count++;
                    }
                }
                
                
                Point2D[] convex = new Point2D[count];
                
                int num = 0;
                for(int j = 0; j < N; j++){
                    if(CCnumber[i] == UF.find(j)){
                        convex[num] = datapoint[j];
                        num++;
                    }
                }
                int[] b = ConvexHullVertex(convex);
                leng += b.length;
            }
            

//            System.out.print(UF.count());
            
                System.out.print(leng);
            

        }
    }

}

