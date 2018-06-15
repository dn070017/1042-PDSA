import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class MyConvexHull{

    private static UF uf;
    private static Point2D[] points;
    private static HashMap<Integer, CC> ccs;
    private static HashMap<Point2D, Integer> id;

    private static class CC{
        private ArrayList<Point2D> pointsCC;


        public CC(Point2D point){
            pointsCC = new ArrayList<Point2D>();
            pointsCC.add(point);
        }

        public void addPoint(Point2D point){
            if(!pointsCC.contains(point)) pointsCC.add(point);
        }

        public Point2D[] getPoints(){
            Point2D[] temp = new Point2D[pointsCC.size()];
            return pointsCC.toArray(temp);
        }
    }

    private static int FindLowestPoint(Point2D[] points){
        int index = 0;
        double min = 1.0;
        for(int i = 0; i < points.length; i++) {
            if (points[i].y() < min) {
                min = points[i].y();
                index = i;
            }
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(points[index].x(), points[index].y() - 0.03, ""L"");
        return index;
    }

    private static Point2D[] sortByAngle(int index, Point2D[] p){
        Comparator<Point2D> cmp = p[index].ATAN2_ORDER;
        Arrays.sort(p, cmp);
        return p;
    }

    private static Point2D[] convexHull(Point2D[] p){
        Stack<Integer> st = new Stack<Integer>();

        for(int i = 0; i < p.length; i++){
            st.push(i);
            if(st.size() < 3){ continue; }
            else{
                int c = st.pop();
                int b = st.pop();
                int a = st.pop();
                while(Point2D.ccw(p[a], p[b], p[c]) == -1){
                    st.push(a);
                    st.push(c);
                    if(st.size() < 3) { break; }
                    c = st.pop();
                    b = st.pop();
                    a = st.pop();
                }
                st.push(a);
                st.push(b);
                st.push(c);
            }
        }
        int i = 0;
        if(st.size() < 3) { return null; }
        Point2D[] convexHullCC = new Point2D[st.size()];
        for(Integer idx: st){
            convexHullCC[i++] = p[idx];
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.point(p[idx].x(), p[idx].y());
        }
        return convexHullCC;
    }


    public static double getDistance(int a, int b){
        return points[a].distanceTo(points[b]);
    }

    public static int[] ConvexHullVertex(Point2D[] a){

        int index = FindLowestPoint(a);
        a = sortByAngle(index, a);
        //System.out.printf(""%d\n"", a.length);
        a = convexHull(a);
        if(a == null) { return null; }
        int[] results = new int[a.length];
        int i = 0;
        for(Point2D point: a){
            results[i++] = id.get(point);
        }
        int[] rev = new int[results.length];
        for(i=0; i < results.length; i++){
            rev[results.length - i - 1] = results[i];
        }
        return rev;
    }

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StdDraw.setXscale(-0.1, 1.1);
            StdDraw.setYscale(-0.1, 1.1);

            int i = 0;
            double t = Double.parseDouble(br.readLine());
            int n = Integer.parseInt(br.readLine());

            uf = new UF(n);
            points = new Point2D[n];
            ccs = new HashMap<Integer, CC>();
            id = new HashMap<Point2D, Integer>();

            /*for(String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                StdDraw.point(x, y);

                Point2D point = new Point2D(x, y);

                if(id.containsKey(point)){
                    System.err.printf(""Duplicated Coordinates: (%.3f, %.3f)\n"", point.x(), point.y());
                }
                else {
                    id.put(point, i);
                    StdDraw.setPenColor(StdDraw.GRAY);
                    StdDraw.text(point.x(), point.y() + 0.03, String.valueOf(i));
                }
                points[i++] = point;
            }

            int[] out = MyConvexHull.ConvexHullVertex(points);
            for(Integer idx: out){
                System.out.printf(""%d\n"", idx);
            }*/


            // Read Points into Array
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                StdDraw.point(x, y);

                Point2D point = new Point2D(x, y);

                if(id.containsKey(point)){
                    System.err.printf(""Duplicated Coordinates: (%.3f, %.3f)\n"", point.x(), point.y());
                }
                else {
                    id.put(point, i);
                    StdDraw.setPenColor(StdDraw.GRAY);
                    StdDraw.text(point.x(), point.y() + 0.03, String.valueOf(i));
                }
                points[i++] = point;
            }

            // Union Points
            for(i = 0; i < n; i++){
                for(int j = i + 1; j < n; j++){
                    if(getDistance(i, j) <= t){
                        StdDraw.setPenRadius(0.001);
                        StdDraw.setPenColor(StdDraw.GRAY);
                        StdDraw.line(points[i].x(), points[i].y(), points[j].x(), points[j].y());
                        uf.union(i, j);
                    }
                }
            }

            // Construct CC Array
            for(i = 0; i < n; i++){
                int root = uf.find(i);
                Point2D point = points[i];
                if(ccs.containsKey(root)){
                    ccs.get(root).addPoint(point);
                }
                else{
                    CC cc = new CC(point);
                    ccs.put(root, cc);
                }
            }

            int count = 0;
            for(Integer key: ccs.keySet()){
                Point2D[] input = ccs.get(key).getPoints();
                int[] result = MyConvexHull.ConvexHullVertex(input);
                if(result == null) { continue; }
                count += result.length;
            }
            System.out.printf(""%d\n"", count);

            //System.out.printf(""\nProgram Completed\n"");
        }
    }
}
