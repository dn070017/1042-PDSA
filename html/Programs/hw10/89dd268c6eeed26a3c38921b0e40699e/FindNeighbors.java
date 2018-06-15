import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Comparable;

/**
 * Class FindNeighbors
 * create Kd-tree and implement k nearest neighbors
 * Main method:
 *     init:  create Kd-tree
 *     query: acquire k nearest neighbors 
 * @author r02b48003 clint
 */
public class FindNeighbors {
    private Node root;
    private int k;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    /**
     * Inner class Node
     * the basic building block of Kd-Tree
     */
    private class Node {
        Point2D p;
        Node left, right;
        // constructor
        public Node(Point2D point) {
            this.p = point;
        } // end constructor
    } // end inner class Node
    
    /**
     * Inner class Pair
     * the element getting k nodes
     */
    private class Pair implements Comparable<Pair> {
        Point2D p1, p2;
        double d;
        // constructor
        public Pair(Point2D point1, Point2D point2) {
            p1 = point1;
            p2 = point2;
            d  = p1.distanceTo(p2); 
        } // end constructor
        @ Override
        public int compareTo(Pair that) {
            return Double.compare(this.d, that.d);
        } // end method compareTo
    } // end inner class Pair
    
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        // initialize root
        root = new Node(points[0]);
        
        // add rest of the points into the tree under root        
        for (int idx = 1; idx < points.length; idx++) {
            root = init(root, points[idx], true);
        } // end for loop
    } // end public method init

    private Node init(Node x, Point2D point, boolean cmpX) {
        // base case
        if (x == null) return new Node(point);
        
        // recursive case
        int cmp = whichSide(point, x.p, cmpX);
        if   (cmp < 0)       x.left  = init(x.left,  point, !cmpX);
        else /* (cmp > 0) */ x.right = init(x.right, point, !cmpX);
        
        return x;
    } // end private method init
    
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        // initialization
        this.k = k;
        MaxPQ<Pair> pq = new MaxPQ<>();
        
        // query and search k nearest neighbor
        query(root, point, pq, true);
        
        // get k nearset point from MaxPQ
        Point2D[] result = new Point2D[k];
        for (int idx = pq.size()-1; idx >= 0; idx--) {
            result[idx] = pq.delMax().p2;
        } // end for loop
        
        // return the Point2D array
        return result;
    } // end public query
    
    private void query (Node x, Point2D pQuery, MaxPQ<Pair> pq, boolean cmpX) {
        // base case
        if (x == null) return;
        
        // recursive case
        Node nextSubtree, anotherSubtree;
        int cmp = whichSide(pQuery, x.p, cmpX);
        
        // deside which subtree to search
        if (cmp < 0) {
            nextSubtree    = x.left;
            anotherSubtree = x.right;
        } // end if
        else if (cmp > 0) {
            nextSubtree    = x.right;
            anotherSubtree = x.left;
        } // end else if
        else {
            System.out.println(""ERROR"");
            return;
        } // end else
        
        // search the next subtree
        query(nextSubtree, pQuery, pq, !cmpX);
        
        // update Max Priority Queue
        if (pq.size() < k) {
            // if pq size is not enough, insert the pair
            pq.insert(new Pair(pQuery, x.p));
        } else {
            // if pq size is equal to or more than k, 
            // decide whether new Pair is inserted to pq
            Pair pair = pq.max();
            if (pQuery.distanceTo(x.p) < pair.d) {
                pq.delMax();
                pq.insert(new Pair(pQuery, x.p));
            } // end inner if
        } // end if-else
        
        // decide whether search another subtree
        Pair pair = pq.max();
        if ((distHV(pQuery, x.p, cmpX) < pair.d) || (pq.size() < k)) {
            query(anotherSubtree, pQuery, pq, !cmpX);
        } // end if
    } // end private method query
    
    /**
     * Helper Method: whichSide
     * ask which side is the target point from the reference point
     * for compare X axis,
     *     return -1 if target point is at the left
     *     return  1 if target point is at the right
     * for compare Y axis, 
     *     return -1 if target point is at the below
     *     return  1 if target point is at the above
     * @param pTarget   Point2D; target point
     * @param pRef      Point2D; reference point
     * @param compareX  boolean; Compare horizontally or vertically
     * @return integer
     */
    private int whichSide(Point2D pTarget, Point2D pRef, boolean compareX){
        if (compareX) {
            return Double.compare(pTarget.x(), pRef.x());
        } else {
            return Double.compare(pTarget.y(), pRef.y());
        } // end if-else
    } // end private method whichSide
    
    /**
     * Helper Method: distHV
     * @param p1       Point2D; first point
     * @param p2       Point2D; first point
     * @param compareX boolean; compare X or Y
     */
    private double distHV(Point2D p1, Point2D p2, boolean compareX) {
        if (compareX) {
            return Math.abs(p2.x() - p1.x());
        } else {
            return Math.abs(p2.y() - p1.y());
        } // end if-else
    } // end private distHV
    
    public static void main(String[] args) throws Exception {
        // declaration
        Queue<Point2D> queue;
        Point2D[] points;
        String line;
        
        // read in the file
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // initialization Queue and get points from file
            queue = new Queue<Point2D>();
            double x, y;
            while ((line = br.readLine()) != null) {
                // get x, y
                x = Double.parseDouble(line.split("" "")[0]);
                y = Double.parseDouble(line.split("" "")[1]);
                
                // create points and input to the queue
                queue.enqueue(new Point2D(x, y));
                System.out.println(x + "" "" + y);
            } // end while
            System.out.println(""---------------------"");
            
            // initialization Point2D array and get points from queue
            points = new Point2D[queue.size()];
            for (int idx = 0; idx < points.length; idx++) {
                points[idx] = queue.dequeue();
                System.out.println(points[idx].x() + "" "" + points[idx].y());
            } // end for
            System.out.println(""---------------------"");
        } // end try
        
        // get k nearest points
        FindNeighbors fN = new FindNeighbors();
        fN.init(points);
        Point2D pQ = new Point2D(0.1354339200, 0.7019446863);
        Point2D[] pKNear = fN.query(pQ, 3);
        
        // print the result
        for (int idx = 0; idx < pKNear.length; idx++) {
            System.out.println(
                    idx + ""\t"" + 
                    pKNear[idx].x() + "" "" + pKNear[idx].y());
        } // end for loop
    } // end main
} // end class FindNeighbors
