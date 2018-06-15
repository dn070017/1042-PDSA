import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

/**
.
 */

public class MyConvexHull {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            double d = Double.parseDouble(br.readLine());
            int n = Integer.parseInt(br.readLine());

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
            ConvexHullVertex(points);
        }
    }

    static class pointCmp implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            if (a.x() > b.x()) return 1;
            else return ((a.x() < b.x()) || (a.x() == b.x() && a.y() < b.y())) ? -1 : 0;
        }
    }

    public static int[] ConvexHullVertex(Point2D[] points) {
        if(points.length==2){
            int v2[]=new int[2];
            v2[0]=0;
            v2[1]=1;
            return v2;
        }else if(points.length==1){
            int v2[]=new int[1];
            v2[0]=0;
            return v2;
        }

        Point2D[] v = new Point2D[points.length];
        double originP[]=new double[points.length];
        for(int i=0;i<points.length;i++){
            originP[i]=points[i].x();
        }
        Arrays.sort(points, new pointCmp());

//        for(int i=0;i<originP.length;i++){
//            System.out.println(originP[i]);
//        }
//        System.out.println(""\n"");

//        double miny=points[0].y();

        int num = 0;
        for (int i = 0; i < points.length; i++) {
            while (num >= 2 && Point2D.ccw(v[num - 2], v[num - 1], points[i]) != 1) num--;
            v[num++] = points[i];
        }
//        System.out.println(num + ""\n"");

        for (int i = points.length - 1,dnum=1; i >= 0; i--) {
            while (num >= dnum && Point2D.ccw(v[num - 2], v[num - 1], points[i]) != 1) num--;
            v[num++] = points[i];
        }
        num--;
//        System.out.println(num + ""\n"");

//        StdDraw.setPenColor(StdDraw.BLUE);
        int vertexes[] = new int[num];

        for (int i = 0; i < vertexes.length; i++) {
//            System.out.println(v[i]);
//            v[i].draw();
            for (int k = 0; k < originP.length; k++) {
                if (originP[k] == v[i].x()){
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

