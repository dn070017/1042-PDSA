import java.awt.Font;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author clint
 */
public class MyConvexHull {
    private static final double radius = 0.01; // point radius for plotting
    
    public static class MyUF extends WeightedQuickUnionUF {
        private final int count; // number of items
        
        public MyUF (int N) {
            /* execute WeightedQuickUnionUF constructor and 
               assign number of items*/
            super(N);
            count = N;
        } // end constructor
        
        public int[] getRoots() {
            /* The function gets all the roots */
            // initialization
            Stack<Integer> rootStack = new Stack<>(); // roots container
            
            // find roots and push to the container
            for (int node = 0; node < count; node++) {
                if (node == find(node)) { // if the root of a node is itself
                    rootStack.push(node);     // push to the container
                } // end if
            } // end loop
            
            // extract the roots from container and return
            int[] roots = stack2array(rootStack);
            return roots;
        } // end func getRoots
        
        public Stack<int[]> getCC() {
            /* get connected components */
            // initialization
            int[] roots = getRoots();           // get all the roots
            Stack<int[]> ccStack = new Stack<>(); // cc container
            
            // each root extract a connected component
            for (int root : roots){
                // loop initialization
                Stack<Integer> cc = new Stack<>();
                
                // element in connected component is connected to the root
                for (int node = 0; node < count; node++) {
                    if (root == find(node)) { cc.push(node); }
                } // end inner loop
                
                // collect each connected component
                ccStack.push(stack2array(cc));
            } // end outer loop
            
            return ccStack;
        } // end func getCC
        
        public int[] stack2array(Stack<Integer> stack){
            /* helper function:
               convert stack into array by popping all items into array */
            // initialization
            int[] arr = new int[stack.size()];
            
            // pop element and collect to an array
            for (int idx = 0; idx < arr.length; idx++) {
                arr[idx] = stack.pop();
            } // end loop
            
            return arr;
        } // end func stack2array
    } // end class myUF
    
    public static Point2D[] idx2points (Point2D[] points, int[] index) {
        /* helper function: 
           input an array of Point2D element and an array of index,
           return an array of Point2D where each element is matched the index 
           of the input array */
        // initialization
        int N = index.length;
        Point2D[] pts = new Point2D[N];
        
        // get the element based on the index array
        for (int i = 0; i < N; i++) {
            int idx = index[i];
            pts[i] = points[idx];
        } // end loop
        
        return pts;
    } // end func idx2point
    
    public static Stack<int[]> distUnionPoints (Point2D[] points, double dist) {
        /* union points and return stack of connected components */
        // initialization
        int N = points.length;
        MyUF uf; 
        
        // double for loop to compare each two points
        uf = new MyUF(N);
        for (int idx1 = 0; idx1 < N; idx1++) {
            for (int idx2 = idx1; idx2 < N; idx2++) {
                // union the points if the distance between points <= distance
                if (points[idx1].distanceTo(points[idx2]) <= dist) {
                    uf.union(idx1, idx2);
                } // end if
            } // end inner loop
        } // end outer loop
        
        return uf.getCC();
    } // end func unionPoints
    
    /**
     * 
     * @param points
     * @return 
     */
    public static int[] ConvexHullVertex(Point2D[] points) {
	// check size
	int N = points.length;  // number of points
	if (N < 3) { return new int[0]; }

	// initialization
        Integer[] vex = new Integer[N]; // vertex: idx of points
	Point2D[] pts = new Point2D[N]; // all points
        
        for (int i = 0; i < N; i++) {
	    vex[i] = i;
	    pts[i] = new Point2D(points[i].x(),
				 points[i].y());
	} // end loop
        
        // initialize index
        Stack<Integer> convex = new Stack<Integer>(); // store index
	convex.push(0);
	convex.push(1);
	convex.push(2);
	
	// show points before sort
	// show(pts, vex);
        // plot(pts, vex);
	
	// sort points by Y axis
	sortByXYaxis(pts, vex);

	// show points before sort
	// show(pts, vex);
        // plot(pts, vex);
	
	// sort points by angle
	sortByAngle(pts, vex);
	
	// show points after sort
	// show(pts, vex);
        // plot(pts, vex);
	// plot(points);
	// plot(pts);
	
	// solve convex hull problem
	//*
	int idx = 2;
	while (true) {
	    
	    if (checkCCW(pts, convex)) {
		// update index
		idx += 1;
		idx %= N;
		
		// if index run through a cycle, break
		if (idx == 1) { break; }

		// push index into stack
		convex.push(idx);
	    } else {
		// skip a index by
		// pop two index & push current index
		convex.pop();
		convex.pop();
		convex.push(idx);
	    } // end if-else
	} // end loop

	// return answer
	int size = convex.size()-1;
        
	int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = vex[convex.pop()];
        } // end loop
        
	//plot(pts, ans1);
	//StdDraw.show(100);
	//StdDraw.clear();
	//plot(points, ans2);
	//*/
	return ans;
    } // end func ConvexHullVertex
    
    public static boolean checkCCW (Point2D[] points, Stack<Integer> index) {
        /* check whether the top 3 points form a counter clockwise angle */
	// get the top three index of the stack
	int idx3 = index.pop();
	int idx2 = index.pop();
	int idx1 = index.pop();

	// get three corresponding points
	Point2D p1 = points[idx1];
	Point2D p2 = points[idx2];
	Point2D p3 = points[idx3];

	// push back the index
	index.push(idx1);
	index.push(idx2);
	index.push(idx3);

	// return false if clockwise turn; return true otherwise
	//StdOut.println(idx1+"" ""+idx2+"" ""+idx3+"" ""+Point2D.ccw(p1, p2, p3));
	return Point2D.ccw(p1, p2, p3) != -1;
    } // end func checkCCW
    
    /**
     * Helper Function for ConvexHullVertex
     * Apply insertion sort to sort the points by X and Y axis
     * @param points
     * @param vertex
     */
    public static void sortByXYaxis(Point2D[] points, Integer[] vertex) {
        // initialization
	int N = points.length;
        
        // first sort points by X axis
        for (int i = 0; i < N; i++) {
	    for (int j = i;
		 j > 0 && less(Point2D.X_ORDER, points[j], points[j-1]);
		 j--) {
		exch(points, j, j-1);
		exch(vertex, j, j-1);
	    } // end inner loop
	} // end outer loop
        
	// then sort points by Y axis
	for (int i = 0; i < N; i++) {
	    for (int j = i;
		 j > 0 && less(Point2D.Y_ORDER, points[j], points[j-1]);
		 j--) {
		exch(points, j, j-1);
		exch(vertex, j, j-1);
	    } // end inner loop
	} // end outer loop
    } // end class sortByYaxis

    /**
     * Helper Function for ConvexHullVertex
     * Apply insertion sort to sort the points by angle to the reference point
     * The reference point is the first point in the array
     * @param points
     * @param vertex
     */
    public static void sortByAngle(Point2D[] points, Integer[] vertex) {
	// initialization
	int N = points.length;
        Point2D refPoint = points[0];
        Point2D[] pts = new Point2D[N];

	/* first sort points by radius in reversed order
	for (int i = 0; i < N; i++) {
	    for (int j = i;
		 j > 0 && more(Point2D.R_ORDER, points[j], points[j-1]);
		 j--) {
		exch(points, j, j-1);
		exch(vertex, j, j-1);
	    } // end inner loop
	} // end outer loop */
        
	// next shift points so that the reference point is at the origin
        double xi, yi, x0, y0;
	x0 = refPoint.x();
	y0 = refPoint.y();
	for (int i = 0; i < N; i++) {
	    xi = points[i].x();
	    yi = points[i].y();
	    //StdOut.println(xi + "" "" + yi + "" | "" + x0 + "" "" + y0);
	    pts[i] = new Point2D(xi - x0, yi - y0);
        } // end loop
        
        //System.out.println(""Inner:"");
        //show(pts, vertex);
	// then sort points by angle
        for (int i = 0; i < N; i++) {
	    for (int j = i;
		 j > 0 && lessAngle(pts[j], pts[j-1]);
                 //j > 0 && less(refPoint.polarOrder(), points[j], points[j-1]);
		 j--) {
                exch(pts, j, j-1);
		exch(points, j, j-1);
		exch(vertex, j, j-1);
	    } // end inner loop
	} // end outer loop
        
    } // end func sortByAngle
    
    /**
     * @param v
     * @param w
     */
    private static boolean lessAngle (Point2D v, Point2D w) {
	return Double.compare(v.theta(), w.theta()) < 0;
    } // end func less
    
    /**
     * This method compare whether v < w
     * @param v The first object with comparable implemented
     * @param w The second object with comparable implemented
     * @return bool true if v is less than w
     */
    private static boolean less (Comparator c, Object v, Object w) {
	return c.compare(v, w) < 0;
    } // end func less

    /**
     * 
     * @param c
     * @param v
     * @param w
     * @return 
     */
    private static boolean more (Comparator c, Object v, Object w) {
	return c.compare(v, w) > 0;
    } // end func more

    /**
     * This method swap the position of a[idx01] and a[idx02]
     * @param a an array of elements with comparable implemented
     * @param idx01 the first index
     * @param idx02 the second index
     */
    private static void exch (Object[] a, int idx01, int idx02) {
	Object swap = a[idx01];
	a[idx01] = a[idx02];
	a[idx02] = swap;
    } // end func exch
    
    /**
     * 
     * @param points an array of elements with comparable implemented
     * @return
     */
    public static boolean isSortedXYaxis (Point2D[] points){
        for (int idx = 0; idx < points.length-1; idx++) {
            if (points[idx].y() == points[idx+1].y()) {
                if (points[idx].x() > points[idx+1].x()) {
                    return false;
                } // end inner if
            } // end outer if
            
            if (points[idx].y() > points[idx+1].y()) {
                return false;
            } // end if
        } // end loop
        return true;
    } // end func isSortedXYaxis
    
    /**
     * @param points
     * @return 
     */
    public static boolean isSortedAngle(Point2D[] points){
        for (int idx = 0; idx < points.length-1; idx++) {
            if (points[idx].theta() > points[idx+1].theta()) {
                return false;
            } // end if
        } // end loop
        return true;
    } // end func isSortedAngle
    
    /**
     * show the points with point ID
     * @param points
     * @param vertex
     */
    public static void show (Point2D[] points, Integer[] vertex) {
        // initialization
	int N = points.length;
        
        StdOut.println(""ID | X     | Y     | R     | Theta"");
	for (int i = 0; i < N; i++) {
	    StdOut.printf("" %d | %.3f | %.3f | %.3f | %.3f)\n"",
			  vertex[i],
			  points[i].x(),
			  points[i].y(),
                          points[i].r(),
			  points[i].theta());
	} // end loop
	StdOut.println(""------------"");
    } // end func show
    
    public static void plotSet () {
        // set scale
	StdDraw.setXscale(-1.2, 1.2);
	StdDraw.setYscale(-1.2, 1.2);
	
	// set coordinate
	StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(-1.1, 0, 1.1, 0);
	StdDraw.line(0, -1.1, 0, 1.1);
	
	// set Font
        Font font = new Font(""Consolas"", Font.BOLD, 15); 
	StdDraw.setFont(font);
    }
    /**
     * plot the points with point order
     * @param points
     */
    public static void plot (Point2D[] points) {
        plotSet();
        
	// plot each point
        int N = points.length;
	StdDraw.setPenColor(StdDraw.BLACK);
	for (int i = 0; i < N; i++) {
	    int     v = i;
	    Point2D p = points[i];
	    StdDraw.filledCircle(p.x(), p.y(), radius);
	    StdDraw.text(p.x() + 0.03, p.y() + 0.03,
			 """" + v);
	} // end for loop
    } // end func plot
    
    /**
     * plot the points with point ID
     * @param points
     * @param vertex
     */
    public static void plot (Point2D[] points, Integer[] vertex) {
        plotSet();
        
	// plot each point
        int N = points.length;
	StdDraw.setPenColor(StdDraw.BLACK);
	for (int i = 0; i < N; i++) {
	    int     v = vertex[i];
	    Point2D p = points[i];
	    StdDraw.filledCircle(p.x(), p.y(), radius);
	    StdDraw.text(p.x() + 0.03, p.y() + 0.03,
			 """" + v);
	} // end for loop

	// label the first point
	StdDraw.setPenColor(StdDraw.RED);
	StdDraw.filledCircle(points[0].x(), points[0].y(), radius);
    } // end func plotPoint

    /**
     * 
     * @param points
     * @param ans 
     */
    public static void plotConvex (Point2D[] points, int[] ans) {
        plotSet();
	
	// plot each point
        int N = points.length;
	StdDraw.setPenColor(StdDraw.BLACK);
	for (int i = 0; i < N; i++) {
	    int     v = i;
	    Point2D p = points[i];
	    StdDraw.filledCircle(p.x(), p.y(), radius);
	    StdDraw.text(p.x() + 0.03, p.y() + 0.03,
			 """" + v);
	} // end for loop

	// plot lines
	StdOut.println("""");
	StdDraw.setPenColor(StdDraw.BLUE);
	for (int i = 0; i < ans.length; i++) {
	    int     v1 = ans[i];
	    int     v2 = ans[(i+1) % ans.length];
	    Point2D p1 = points[v1];
	    Point2D p2 = points[v2];
	    StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
	    //StdOut.println(v1 + "" "" + v2);
	} // end loop

	// label the first point
	// StdDraw.setPenColor(StdDraw.RED);
	// StdDraw.filledCircle(points[0].x(), points[0].y(), radius);
    } // end func plotPoint
    
    public static void main(String[] args) {
        Stack<Point2D> pointStack = new Stack<>();
        File file = new File(args[0]);
        In input = new In(file);
        String line;
        
        // read in the threshold distance
        line = input.readLine();
        Double dist = Double.parseDouble(line);
        
        // read in the number of points
        line = input.readLine();
        Integer N = Integer.parseInt(line);
        
        // check
        StdOut.println(""D: "" + dist + ""\nN: "" + N);
        while (!input.isEmpty()) {
            //double x = StdIn.readDouble();  
.
            //points.push(new Point2D(x, y));
.
            line = input.readLine();
            String[] pos = line.split("" ""); // position
            Point2D point = new Point2D(
                    Double.parseDouble(pos[0]), 
                    Double.parseDouble(pos[1]));
            pointStack.push(point);
        } // end while loop 
        
        //*
        Point2D[] points = new Point2D[pointStack.size()];
        for (int idx = 0; idx < points.length; idx++) {
            points[idx] = pointStack.pop();
            //StdOut.printf(""%.3f, %.3f\n"", points[idx].x(), points[idx].y());
        } // end loop
        //*/
        //MyConvexHull.plot(points);
        int[] convex = MyConvexHull.ConvexHullVertex(points);
        //MyConvexHull.plotConvex(points, convex);
        System.out.println(Arrays.toString(convex));
    } // end func main
} // end class MyConvexHull

