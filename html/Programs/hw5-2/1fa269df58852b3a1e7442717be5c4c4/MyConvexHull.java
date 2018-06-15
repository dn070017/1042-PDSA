/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class MyConvexHull {

    public static int ConvexHullVertex(Point2D[] a) {
        /*int[] seed=new int[10];
         int min=-1000;
         int max=1000;
         for (int i=0;i<10;i++){
         seed[i]=(int) StdRandom.uniform(min, max);
         System.out.print(""seed=""+seed[i]);
         System.out.printf(""%n"");
         }*/
        Point2D[] b = new Point2D[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i];
        }
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
                //StdDraw.setPenColor(StdDraw.RED);
                // StdDraw.setPenRadius(.01);
                //a[i].draw();
                point0 = a[i];
                a[i] = a[0];
                a[0] = point0;
                //StdDraw.setPenColor(StdDraw.BLACK);
                /*System.out.print(point0);
                 System.out.printf(""%n"");
                 point0angle=points[i].theta();
                 System.out.print(point0angle);*/
            } else {
                // StdDraw.setPenColor(StdDraw.BLACK);
                //StdDraw.setPenRadius(.01);
                // a[i].draw();
            }
        }

        Arrays.sort(a, a[0].POLAR_ORDER);//sort array
        for (int i = 0; i < N; i++) {
            // StdDraw.setPenRadius(.001);
            // StdDraw.text(a[i].x(), a[i].y() + 1, Integer.toString(i));
            //  StdDraw.line(point0.x(), point0.y(), a[i].x(), a[i].y());
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
        
        int s = pp.size();
        
        return s;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            double distant=Double.parseDouble(br.readLine());
            int Num = Integer.parseInt(br.readLine());
            Point2D[] a = new Point2D[Num];
            /*StdDraw.setCanvasSize(800, 800);
             StdDraw.setXscale(0, 100);
             StdDraw.setYscale(0, 100);
             StdDraw.setPenRadius(.01);*/
            int k=0;
            //for (int i = 0; i < 10; i++) {
            //int x = StdRandom.uniform(100);
            //int y = StdRandom.uniform(100);
            for (int i=0;i<Num;i++){
                String[] s=br.readLine().split("" "");
                a[i]=new Point2D(Double.parseDouble(s[0]),Double.parseDouble(s[1]));
            }
            int[] id =new int[Num];
            for (int i=0;i<Num;i++){
                id[i]=i;
            }
            UF uf = new UF(Num);
            for(int i=0;i<Num;i++){
                for(int j=i+1;j<Num;j++){
                    if(Math.sqrt(Math.pow(a[i].x()-a[j].x(),2)+(Math.pow(a[i].y()-a[j].y(),2)))<=distant){
                        uf.union(id[i], id[j]);
                    }
                }
            }
            int idindex=0;
            while(idindex!=id.length){
                
                Stack<Point2D> s=new Stack<Point2D>();
                //for(int i=0;i<Num;i++){
                    
                    for(int j=0;j<Num;j++){
                        if(uf.find(id[j])==idindex){
                            s.push(a[j]);
                        }
                    }
                    idindex=idindex+1;
                    Point2D[] lengthofid= new Point2D[s.size()];
                    int size=s.size();
                    
                    if(size<3)continue;
                    for(int j=0;j<size;j++){
                        lengthofid[j]=s.pop();
                    }
                    
                    k=k+ConvexHullVertex(lengthofid);
                    
                //}
            }
            
            //System.out.print(a[i]);
            //System.out.printf(""%n"");
            //}
            //int[] b = ConvexHullVertex(a);
            
                
            System.out.print(k);
            //System.out.print(uf.count());
        }
    }
}

