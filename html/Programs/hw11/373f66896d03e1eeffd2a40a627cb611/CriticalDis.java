import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Homework 11
 * @author r02b48003
 */
public class CriticalDis {
    private int idxSrc, idxTar;
    private double cDist;
    private MinPQ<Double> pq;
    private boolean[] marked;
    private final Point2D[] points;
    private final Digraph graph;
    
    public CriticalDis (Point2D[] points) {
        // sort by X coordinate
        Arrays.sort(points, Point2D.X_ORDER);
        
        // determine source and target    
        idxSrc = 0; idxTar = 0;
        double sumXYMin = points[0].x() + points[0].y();
        double sumXYMax = points[0].x() + points[0].y();
        
        for (int idx = 0; idx < points.length; idx++) {
            double sumXY = points[idx].x() + points[idx].y();
            if (sumXY < sumXYMin) {sumXYMin = sumXY; idxSrc = idx;}
            if (sumXY > sumXYMax) {sumXYMax = sumXY; idxTar = idx;}
        } // end for loop
        
        this.points = points;
        this.pq = new MinPQ<>();
        this.graph = new Digraph(points.length);
        for (int idx1 = 0; idx1 < (points.length-1); idx1++) {
            for (int idx2 = (idx1+1); idx2 < points.length; idx2++) {
                if (points[idx1].y() <= points[idx2].y()){
                    graph.addEdge(idx1, idx2);
                    pq.insert(points[idx1].distanceTo(points[idx2]));
                } // end if
            } // end inner loop 
        } // end outer loop
        
        // show result
        cDist = searchDist();
        depthFirstSearch(cDist);
        System.out.printf(""%1.3f\n"", cDist);
        //if (Double.compare(cDist, -1.0) != 0)
        //    plot(cDist);
    } // end constructor
    
    private double searchDist() {
        while(!pq.isEmpty()) {
            // get the minimum
            Double dist = pq.delMin();
            depthFirstSearch(dist);
            
            // check if target is visited
            if (visited(idxTar)) {
                return dist;
            } // end if
        } // end while
        
        System.out.println(""ERROR!! No Distance are found"");
        return -1.0;
    } // end searchCdist
    
    private void depthFirstSearch(double dist) {
        marked = new boolean[graph.V()];
        dfs(idxSrc, dist);
    } // end func DepthFirstSearch
    
    private void dfs(int v, double dist) {
        marked[v] = true;
        for (int w : graph.adj(v))
            if (!marked[w] && points[v].distanceTo(points[w]) <= dist){
                dfs(w, dist);
            } // end if
    } // end func dfs
    
    public boolean visited(int v) {
        return marked[v];  
    } // end func visited
    
    private void printPoints(){
       // print out the points and source/target
        for (int idx = 0; idx < points.length; idx++) {
            Point2D point = points[idx];
            System.out.println(
                    idx + "" "" + 
                    point.x() + "" "" + point.y() + "" "" + 
                    (point.x()+point.y()));
        } // end for loop
        System.out.println(idxSrc);
        System.out.println(idxTar); 
        //System.out.println(idxSrc + "" "" + sumXYMin);
        //System.out.println(idxTar + "" "" + sumXYMax); 
    } // end func printPoints
    
    private void plot(double dist){
        // set scale and axes
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(-0.2, 1.2);
        StdDraw.setYscale(-0.2, 1.2);
        StdDraw.line(0,-0.1,0,1.1);
        StdDraw.line(-0.1,0,1.1,0);
                
        // plot points
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D p : points) {
            StdDraw.filledCircle(p.x(), p.y(), 0.01);
        } // end for loop
        
        // plot line
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int idx1 = 0; idx1 < graph.V(); idx1++) {
            for (int idx2 : graph.adj(idx1)) {
                StdDraw.line(points[idx1].x(), points[idx1].y(),
                             points[idx2].x(), points[idx2].y());
            } // end inner for loop
        } // end outer for loop
        
        // plot marked points
        StdDraw.setPenColor(StdDraw.RED);
        for (int idx = 0; idx < graph.V(); idx++) {
            if (marked[idx]) {
                StdDraw.filledCircle(points[idx].x(), points[idx].y(), 0.01);
            } // end if
        } // end for loop
        
        // plot source, target
        StdDraw.setPenColor(StdDraw.PINK);
        StdDraw.filledCircle(points[idxSrc].x(), points[idxSrc].y(), 0.01);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(points[idxTar].x(), points[idxTar].y(), 0.01);
        
        // plot edge with length <= input distance
        StdDraw.setPenColor(StdDraw.RED);
        for (int idx1 = 0; idx1 < graph.V(); idx1++) {
            for (int idx2 : graph.adj(idx1)) {
                if (points[idx1].distanceTo(points[idx2]) <= dist) {
                    StdDraw.line(points[idx1].x(), points[idx1].y(),
                                 points[idx2].x(), points[idx2].y());
                } // end if
            } // end inner for loop
        } // end outer for loop
        
        // show if the source is visited
        if (visited(idxTar)) {
            StdDraw.text(1.0, 1.0,  ""Target Visited"");
            StdDraw.text(1.0, 0.95, ""Critical Distance: "" +
                    String.valueOf(Math.round(10000.0 * cDist)/10000.0));
        } // end if
    } // end func plot
    
    public static void main(String[] args) throws Exception {
        // declaration        
        String[]    line;
        Point2D     points[];
        int         count;
        CriticalDis cds;
        
        // read in the points from 
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // first line contain the number of points
            String header = br.readLine();
            count  = Integer.parseInt(header);
            points = new Point2D[count];
            
            // rest of lines contain points
            for (int idx = 0; idx < count; idx++) {
                line = br.readLine().split("" "");
                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                points[idx] = new Point2D(x, y);
                //System.out.println(x + "" "" + y);
            } // end for loop
        } // end try
        
        // 
        cds = new CriticalDis(points);        
        
        
    } // end main
} // end main class

