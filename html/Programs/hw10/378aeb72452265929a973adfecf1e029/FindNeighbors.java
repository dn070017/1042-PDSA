
import java.io.BufferedReader;
import java.io.FileReader;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    private Node root;
    private int size;
    private int k;
    private Point2D queryPoint;
    private MaxPQ<Event> neighbors;
    
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        for (int i=0; i<points.length; i++) {
            insert(points[i]);
        }
        return;
    }
    
    
    
    private static class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private boolean vertical = true;
        public Node(Point2D p, Node left, Node right, boolean vertical) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
        }
    }
    
    public int size() {
        return size;
    }
    
    public void insert(Point2D p) {
        root = doInsert(root, p, true);
    }

    private Node doInsert(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(p, null, null, vertical);
        }

        // do not insert the point if it is already in the 2d-tree;
.
        if (p.equals(node.p)) {
            return node;
        }

        // at the root we use the x-coordinate (if the point to be inserted has a smaller x-coordinate
        // than the point at the root, go left; otherwise go right);
        // then at the next level, we use the y-coordinate (if the point to be inserted has a smaller y-coordinate
        // than the point in the node, go left; otherwise go right);
.
        if (isSmallerThanPointInNode(p, node)) {
            node.left = doInsert(node.left, p, !node.vertical);
        } else {
            node.right = doInsert(node.right, p, !node.vertical);
        }

        return node;
    }

    private boolean isSmallerThanPointInNode(Point2D p, Node node) {
        int cmp = node.vertical ? Point2D.X_ORDER.compare(p, node.p) : Point2D.Y_ORDER.compare(p, node.p);
        return (cmp < 0);
    }

    private static class Event implements Comparable<Event> {
        private final double distance;        
        private final Point2D p;       
        // create a new event to occur at time t involving a and b
        public Event(double d, Point2D p) {
            this.distance = d;
            this.p    = p;
        }
        // compare times when two events will occur
        public int compareTo(Event that) {
            if      (this.distance < that.distance) return -1;
            else if (this.distance > that.distance) return +1;
            else                            return  0;
        }
    }
    
    
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        this.k = k;
        queryPoint = point;
        neighbors = new MaxPQ(k+1);
        RectHV unit = new RectHV(0, 0, 1, 1);
        doNearest(root, unit, point);
        for (int i=k-1; i>-1; i--) {
            result[i] = neighbors.delMax().p;
        }
        return result;
    }
    

    private void doNearest(Node node, RectHV nodeRect, Point2D queryPoint) {
        // To find a closest point to a given query point, start at the root and recursively search in both subtrees
        // using the following pruning rule:
        // if the closest point discovered so far is closer than the distance between the query point and the rectangle
.
.
.
        // To do this, organize your recursive method so that when there are two possible subtrees to go down,
        // you always choose the subtree that is on the same side of the splitting line as the query point as the first
        // subtree to explore — the closest point found while exploring the first subtree may enable pruning of the
.
        if (node == null) {
            return;
        }
        
        double largestDist = (neighbors.size() == k)
                ? neighbors.max().distance
                : Double.MAX_VALUE;

        if (largestDist > nodeRect.distanceSquaredTo(queryPoint)) {
            double dist = queryPoint.distanceSquaredTo(node.p);
            Event a = new Event(dist, node.p);
            neighbors.insert(a);
            if (neighbors.size() > k)
                neighbors.delMax();
            

            RectHV leftNodeRect = leftNodeRect(node, nodeRect);
            RectHV rightNodeRect = rightNodeRect(node, nodeRect);

            if (isSmallerThanPointInNode(queryPoint, node)) {
                // explore left subtree first
                doNearest(node.left, leftNodeRect, queryPoint);
                doNearest(node.right, rightNodeRect, queryPoint);
            } else {
                // explore right subtree first
                doNearest(node.right, rightNodeRect, queryPoint);
                doNearest(node.left, leftNodeRect, queryPoint);
            }
        }
    }
    
    
    private RectHV leftNodeRect(Node node, RectHV nodeRect) {
        return node.vertical
                ? new RectHV(nodeRect.xmin(), nodeRect.ymin(), node.p.x(), nodeRect.ymax())
                : new RectHV(nodeRect.xmin(), nodeRect.ymin(), nodeRect.xmax(), node.p.y());
    }

    private RectHV rightNodeRect(Node node, RectHV nodeRect) {
        return node.vertical
                ? new RectHV(node.p.x(), nodeRect.ymin(), nodeRect.xmax(), nodeRect.ymax())
                : new RectHV(nodeRect.xmin(), node.p.y(), nodeRect.xmax(), nodeRect.ymax());
    }
    
    
    
    public static class RectHV {
    private final double xmin, ymin;   // minimum x- and y-coordinates
    private final double xmax, ymax;   // maximum x- and y-coordinates

    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        if (Double.isNaN(xmin) || Double.isNaN(xmax))
            throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
        if (Double.isNaN(ymin) || Double.isNaN(ymax))
            throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
        if (xmax < xmin || ymax < ymin) {
            throw new IllegalArgumentException(""Invalid rectangle"");
        }
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public double xmin() {
        return xmin;
    }

    public double xmax() {
        return xmax;
    }

    public double ymin() {
        return ymin;
    }

    public double ymax() {
        return ymax;
    }

    public double width() {
        return xmax - xmin;
    }

    public double height() {
        return ymax - ymin;
    }

    public boolean intersects(RectHV that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
            && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }

    public boolean contains(Point2D p) {
        return (p.x() >= xmin) && (p.x() <= xmax)
            && (p.y() >= ymin) && (p.y() <= ymax);
    }

    public double distanceTo(Point2D p) {
        return Math.sqrt(this.distanceSquaredTo(p));
    }

    public double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        RectHV that = (RectHV) other;
        if (this.xmin != that.xmin) return false;
        if (this.ymin != that.ymin) return false;
        if (this.xmax != that.xmax) return false;
        if (this.ymax != that.ymax) return false;
        return true;
    }
}
    
    
    
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int N = 20;
            FindNeighbors test = new FindNeighbors();
            Point2D[] points = new Point2D[N];
            double x=0 ,y=0;
            for(int i = 0; i < N; i++) {
                String[] inpoints = br.readLine().split("" "");
                x = Double.parseDouble(inpoints[0]);
                y = Double.parseDouble(inpoints[1]);
                points[i] = new Point2D(x, y);
            }
            test.init(points);
            Point2D[] result = new Point2D[3];
            Point2D queryPoint = new Point2D(0.1354339200, 0.7019446863);
            result = test.query(queryPoint, 3);
            System.out.println();
            
        }
    }
    
    

}

