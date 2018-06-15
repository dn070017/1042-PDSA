
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
.
 */

public class MyConvexHull {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            double d = Double.parseDouble(br.readLine());
            int n = Integer.parseInt(br.readLine());
            QuickUnionUF uf = new QuickUnionUF(n);
//
//            StdDraw.setCanvasSize(700, 700);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.setPenRadius(.01);

            Point2D points[] = new Point2D[n];
            for (int i = 0; i < n; i++) {
                String p[] = br.readLine().split("" "");
                points[i] = new Point2D(Double.parseDouble(p[0]), Double.parseDouble(p[1]));
//                points[i].draw();
            }
//            System.out.println(ConvexHullVertex(points).length);
            if (points.length <= 2) {
                System.out.println(0);
                return;
            }

            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (points[i].distanceTo(points[k]) <= d && !uf.connected(i, k)) {
                        uf.union(i, k);
                    }
                }
            }

//            for (int i = 0; i < n; i++) {
//                System.out.println(uf.find(i));
//            }

//            int ufnum=uf.count();

            int vetexNum = 0;
            Stack<Integer> temp=new Stack<>();
            for (int i = 0; i < n; i++) {
                int now = uf.find(i);
                Stack<Point2D> point2DStack = new Stack<>();
                for (int j = 0; j < n; j++) {
                    if (now == uf.find(j) && !temp.contains(now)) point2DStack.push(new Point2D(points[j].x(), points[j].y()));
                }

                temp.push(uf.find(i));
                if (!point2DStack.isEmpty()) {
                    Point2D ccPoints[] = new Point2D[point2DStack.size()];
                    for (int k = 0; k < ccPoints.length; k++) {
                        Point2D tempP = point2DStack.pop();
                        ccPoints[k] = new Point2D(tempP.x(), tempP.y());
                    }


//                    for (int t = 0; t < ccPoints.length; t++) {
//                        System.out.println(ccPoints[t]);
//                    }
//                    System.out.println(""ccc:"" + ConvexHullVertex(ccPoints).length);

                    int CL=ConvexHullVertex(ccPoints).length;
                    if(CL>2) vetexNum +=CL;
//                    System.out.println(vetexNum);


                }
            }
            System.out.println(vetexNum);
        }
    }

    static class pointCmp implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            if (a.x() > b.x()) return 1;
            else return ((a.x() < b.x()) || (a.x() == b.x() && a.y() < b.y())) ? -1 : 0;
        }
    }

    public static int[] ConvexHullVertex(Point2D[] points) {
        Point2D[] v = new Point2D[points.length];
        Point2D[] originP;
        originP=Arrays.copyOf(points,points.length);
        Arrays.sort(points, new pointCmp());
//        if(points.length==3 && Point2D.ccw(points[0],points[1],points[2])!=0) {
//            int[] v3 = new int[3];
//            v3[0] = 0;
//            v3[1] = 1;
//            v3[2] = 2;
//            return v3;
//        } else if(points.length==2){
//            int[] v2=new int[2];
//            v2[0]=0;
//            v2[1]=1;
//            return v2;
//        }

        int num = 0;
        for (int i = 0; i < points.length; i++) {
            while (num >= 2 && Point2D.ccw(v[num - 2], v[num - 1], points[i]) != 1) num--;
            v[num++] = points[i];
        }

//        for (int i = 0; i < v.length; i++) {
//            System.out.println(v[i]);
//        }
//        System.out.println(num + ""\n"");
//        System.out.println(points.length);
        for (int i = points.length - 2,dnum=num+1; i >= 0; --i) {
            while (num >= dnum && Point2D.ccw(v[num - 2], v[num - 1], points[i]) != 1) num--;
            if(num==points.length){
                num++;
                break;
            }
            v[num++] = points[i];
        }
//        for (int i = 0; i < v.length; i++) {
//            System.out.println(v[i]);
//        }
        num--;
//        System.out.println(num + ""\n"");

//        StdDraw.setPenColor(StdDraw.BLUE);
        int vertexes[] = new int[num];

        for (int i = 0; i < vertexes.length; i++) {
//            System.out.println(v[i]);

//            v[i].draw();
            for (int k = 0; k < originP.length; k++) {
                if(originP[k].equals(v[i])) {
                    vertexes[i] = k;
                    break;
                }
            }
        }

//        System.out.println(""\n"");
//        for (int i = 0; i < num; i++) {
//            System.out.println(vertexes[i]);
//        }
        return vertexes;
    }
}

