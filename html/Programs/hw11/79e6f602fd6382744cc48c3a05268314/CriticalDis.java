import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Homework 11: Find Critical Distance
.
 * @author r02b48003 柯逵悅
 */
public class CriticalDis {
    private int idxSrc, idxTar; // index for source and target
    private double cDist;       // critical distance
    private MinPQ<Double> pq;   // Priority Queue; store lengths of all edges
    private boolean[] marked;   // label visited nodes, which are set True
    private final Point2D[] points; // all input points
    private final Digraph graph;    // directed graph formed by input points
    
    /**
     * Constructor
     * find the source and target, which have minimum and maximum x+y
     * construct the graph using input point, edges are constructed 
     * from left below to right above, regardless of distance
     * @param points 
     */
    public CriticalDis (Point2D[] points) {
        // sort by X coordinate and initialization
        Arrays.sort(points, Point2D.X_ORDER);
        this.points = points;
        this.pq = new MinPQ<>();
        this.graph = new Digraph(points.length);
        
        // determine source and target    
        // find min(x+y) and max(x+y)
        idxSrc = 0; idxTar = 0;
        double sumXYMin = points[0].x() + points[0].y();
        double sumXYMax = points[0].x() + points[0].y();
        
        for (int idx = 0; idx < points.length; idx++) {
            double sumXY = points[idx].x() + points[idx].y();
            if (sumXY < sumXYMin) {sumXYMin = sumXY; idxSrc = idx;}
            if (sumXY > sumXYMax) {sumXYMax = sumXY; idxTar = idx;}
        } // end for loop
        
        // construct graph and priority queue
        // for each point p
        for (int idx1 = 0; idx1 < (points.length-1); idx1++) {
            // since points are sorted by X, we only need to 
            // check whether right side of point p is above
            // if true, add edge and store distance of edge
            for (int idx2 = (idx1+1); idx2 < points.length; idx2++) {
                if (points[idx1].y() <= points[idx2].y()) {
                    graph.addEdge(idx1, idx2);
                    pq.insert(points[idx1].distanceTo(points[idx2]));
                } // end if
            } // end inner loop 
        } // end outer loop
        
        // find the critical distance
        cDist = searchDist();
        depthFirstSearch(cDist);
    } // end constructor
    
    /**
     * Getter: getDist
     * return the critical distance
     * @return double
     */
    public double getDist(){
        return cDist;
    } // end fund getDist
    
    /**
     * Method: searchDist
     * find the critical distance and return
     * @return double; the critical distance
     */
    private double searchDist() {
        while(!pq.isEmpty()) {
            // each time get minimum distance edge and perform DFS search
            Double dist = pq.delMin();
            depthFirstSearch(dist);
            
            // check if target is visited;
            // if true, return the distance
            if (visited(idxTar)) return dist;
        } // end while
        
        // in case we do not find critical distance
        System.out.println(""ERROR!! No Distance are found"");
        return -1.0;
    } // end searchCdist
    
    /**
     * Method: depthFirstSearch
     * using input distance to perform depth first search (dfs)
     * from source point
     * @param dist 
     */
    private void depthFirstSearch(double dist) {
        marked = new boolean[graph.V()];
        dfs(idxSrc, dist);
    } // end func DepthFirstSearch
    
    /**
     * Method: dfs
     * the recursive function that run DFS in graph
     * each neighbor will be visited if the distance 
     * to it is smaller than input distance
     * @param v    ; integer: the current nodes
     * @param dist ; double:  the input distance
     */
    private void dfs(int v, double dist) {
        marked[v] = true;
        for (int w : graph.adj(v))
            if (!marked[w] && points[v].distanceTo(points[w]) <= dist){
                dfs(w, dist);
            } // end if
    } // end func dfs
    
    /**
     * Method: visited
     * return true if the input node is visited
     * @param v
     * @return boolean
     */
    public boolean visited(int v) {
        return marked[v];  
    } // end func visited
    
    /**
     * Method: printPoints
     * this method is used for debugging code
     * the method print out all the points, source point and target point
     */
    private void printPoints(){
        // print out the points in array
        // index | X | Y | X+Y
        for (int idx = 0; idx < points.length; idx++) {
            Point2D p = points[idx];
            System.out.println(
                idx + "" "" + p.x() + "" "" + p.y() + "" "" + (p.x()+p.y()));
        } // end for loop
        
        // print out the source & target
        System.out.println(idxSrc);
        System.out.println(idxTar); 
    } // end func printPoints
    
    /**
     * Method plot
     * useful method for bebugging the code
     * show the source, target, edges
     * @param dist 
     */
    public void plot(double dist){
        // set scale and axes
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(-0.2, 1.2);
        StdDraw.setYscale(-0.2, 1.2);
        StdDraw.line(0,-0.1,0,1.1);
        StdDraw.line(-0.1,0,1.1,0);
                
        // plot all the points
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D p : points) {
            StdDraw.filledCircle(p.x(), p.y(), 0.01);
        } // end for loop
        
        // plot all the edges in graph
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int idx1 = 0; idx1 < graph.V(); idx1++) {
            for (int idx2 : graph.adj(idx1)) {
                StdDraw.line(points[idx1].x(), points[idx1].y(),
                             points[idx2].x(), points[idx2].y());
            } // end inner for loop
        } // end outer for loop
        
        // plot the visited points
        StdDraw.setPenColor(StdDraw.RED);
        for (int idx = 0; idx < graph.V(); idx++) {
            if (marked[idx]) {
                StdDraw.filledCircle(points[idx].x(), points[idx].y(), 0.01);
            } // end if
        } // end for loop
        
        // plot source & target points
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
        
        // show if the source is visited & input distance
        if (visited(idxTar)) { StdDraw.text(1.0, 1.0, ""Target Visited"");   }
        else                 { StdDraw.text(1.0, 1.0, ""Target Unvisited""); }
        StdDraw.text( 1.0, 0.95, ""Distance: "" +
            String.valueOf(Math.round(100000.0 * cDist)/100000.0));
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
        
        // find critical Distance
        cds = new CriticalDis(points);        
        System.out.printf(""%1.3f\n"", cds.getDist());
        
        // plot the result
        //cds.plot(cds.getDist());
    } // end main
} // end main class

