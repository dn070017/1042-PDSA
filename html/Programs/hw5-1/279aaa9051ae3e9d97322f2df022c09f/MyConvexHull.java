/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
import java.util.Arrays;

public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a){
           /*int[] seed=new int[10];
         int min=-1000;
         int max=1000;
         for (int i=0;i<10;i++){
         seed[i]=(int) StdRandom.uniform(min, max);
         System.out.print(""seed=""+seed[i]);
         System.out.printf(""%n"");
         }*/

        
        int N = a.length;

        double miny = 0.0;
        //Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
           
            if (i == 0) {
                miny = a[0].y();
            } else {
                miny = Math.min(miny, a[i].y());
            }

        }
        //draw and find point0's angle

        Point2D point0 = new Point2D(0.0, 0.0);

        for (int i = 0; i < N; i++) {
            if (a[i].y() == miny) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(.01);
                a[i].draw();
                point0 = a[i];
                a[i] = a[0];
                a[0] = point0;
                StdDraw.setPenColor(StdDraw.BLACK);
                /*System.out.print(point0);
                 System.out.printf(""%n"");
                 point0angle=points[i].theta();
                 System.out.print(point0angle);*/
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(.01);
                a[i].draw();
            }
        }

        Arrays.sort(a, a[0].POLAR_ORDER);//sort array
        for (int i = 0; i < N; i++) {
            StdDraw.setPenRadius(.001);
            StdDraw.text(a[i].x(), a[i].y() + 1, Integer.toString(i));
            StdDraw.line(point0.x(), point0.y(), a[i].x(), a[i].y());
        }
        //System.out.print(Point2D.ccw(points[1], points[2], points[3]));
        //draw convexhull
        Stack<Point2D> p = new Stack<Point2D>();
        for (int i = 0; i < N; i++) {
            p.push(a[N - 1 - i]);
        }
        Point2D[] convex = new Point2D[3];
        Stack<Point2D> pp = new Stack<Point2D>();

        while (true) {
            if (p.isEmpty()) {
                break;
            }
            convex[0] = p.pop();
            convex[1] = p.pop();
            convex[2] = p.pop();
            if (Point2D.ccw(convex[0], convex[1], convex[2]) == 1) {
                if (pp.isEmpty()) {
                    pp.push(convex[0]);
                    pp.push(convex[1]);
                    pp.push(convex[2]);
                } else {
                    pp.push(convex[2]);
                }
                if (!p.isEmpty()) {
                    p.push(convex[2]);
                    p.push(convex[1]);
                }
            } else {
                if (Point2D.ccw(convex[0], convex[1], convex[2]) == -1) {
                    p.push(convex[2]);
                    pp.pop();
                    convex[1] = pp.pop();
                    convex[0] = pp.pop();
                    pp.push(convex[0]);
                    pp.push(convex[1]);
                    p.push(convex[1]);
                    p.push(convex[0]);
                    //System.out.print(1);
                }
            }
        }
        /*for (int i=0;i<N;i++){
         System.out.print(p.pop());
         System.out.printf(""%n"");
         }*/
        int[] ans = new int[pp.size()];
        int s=pp.size();
        Point2D buf;
        for (int j = 0; j < s; j++) {
            buf=pp.pop();
            for (int i = 0; i < a.length; i++) {
                if(buf.equals(a[i])){
                    ans[j]=i;
                }
            }
        }
        Arrays.sort(ans);
        /*for (int i=0;i<ans.length;i++){
            System.out.print(ans[i]);
        }*/
        return ans;
    }

    public static void main(String[] args) {
        int Num=10;
        Point2D[] a = new Point2D[Num];
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.01);
        
        for (int i=0;i<Num;i++){
         int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            a[i] = new Point2D(x, y);
            //System.out.print(a[i]);
            //System.out.printf(""%n"");
        }
        int[] b=ConvexHullVertex(a);
    }
}

