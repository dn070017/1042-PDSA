
import java.util.Comparator;

public class FindNeighbors {
       private int size = 0;

    private enum Orientation {
        LeftRight, AboveBelow;

        public Orientation next() {
            if (this.equals(Orientation.AboveBelow))return Orientation.LeftRight;
            return Orientation.AboveBelow;
        }
    }

    private static class Node {
        
        /*implements Comparator<Node>
         * the point
         */
        //Comparator<Node> X_ORDER = new XOrder();
        private Point2D p ;
        /*
         * the axis-aligned rectangle corresponding to this/ node
         */
        //public int rect =0;
        /*
         * the left/bottom subtree
         */
        private Node lb;
        /*
         * the right/top subtree
         */
        private Node rt;
        /*
         * to record that is already finded
         */
        boolean finded=false;
        
        private double dist = Double.MAX_VALUE;
        public Node(Point2D p) {
            this.p = p;
            //this.rect = 1;
        }
        
    }
    public Comparator<Node> distanceToOrder() {
        return new DistanceToOrder();
    }
    public class DistanceToOrder implements Comparator<Node>{
        public int compare(Node p,Node q) {
            return Double.compare(p.dist, q.dist);
        }
        }
    Node root = null;
        
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        for(int i = 0;i<points.length;i++){
            insert(points[i]);
        }
 
        //input all items
        //put(points)
    }
    
    public void insert(Point2D p) {
        if (root==null) {
            root = new Node(p);
            //root.rect = new RectHV(0, 0, 1, 1);
            //size++;
            //return;
        }
        root = put(root, p, Orientation.LeftRight);
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    /*public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        return findNearest(root, p, root.p, Double.MAX_VALUE,
                Orientation.LeftRight);
        //return result;
    }*/ 
    //put in the tree
     private Node put(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            size++;
            return new Node(p);
        }
        if (x.p.equals(p)) {
            return x;
        }
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp < 0) {
            x.lb = put(x.lb, p, nextOrientation);
            if (x.lb == null) {
                if (orientation == Orientation.LeftRight) {
                    x.lb = new Node(p);
                } //else {
                   // x.rt=new Node(p);
                //}
            }
        } else {
            x.rt = put(x.rt, p, nextOrientation);
            if (x.rt == null) {
                if (orientation == Orientation.LeftRight) {
                    x.rt= new Node(p);
                } //else {
                    //x.rt.rect = new RectHV(x.rect.xmin(), x.p.y(),
                      //      x.rect.xmax(), x.rect.ymax());
                //}
            }
        }
        return x;
    }

    private int compare(Point2D p, Point2D q, Orientation orientation) {
        if (orientation == Orientation.LeftRight) {
            return Double.compare(p.x(), q.x());
        } else {
            return Double.compare(p.y(), q.y());
        }
    }
    //Node q = new Node;
    MaxPQ<Node> pq = new MaxPQ<Node>(distanceToOrder()); 
    
    //Queue<Point2D> pq1 = new Queue();
    /*public Point2D query(Point2D point,int k){
        //Point2D[] result = new Point2D[k];
        return findNearest(root, point, root.p, Double.MAX_VALUE,
                Orientation.LeftRight,k);
        //return result;
    }*/
    public Point2D[] query(Point2D point,int k){
        Point2D[] result = new Point2D[k];
        Stack<Point2D> temp = new Stack();
        /*Node e = findNearest(root, point, root, Double.MAX_VALUE,
                Orientation.LeftRight,k);
        Node f = findNearest(root, point, e, Double.MAX_VALUE,
                Orientation.LeftRight,k);
        Node g = findNearest(root, point, f, Double.MAX_VALUE,
                Orientation.LeftRight,k);
        Node h = findNearest(root, point, root, Double.MAX_VALUE,
                Orientation.LeftRight,k);*/
        int count = 0;
        int count1 = 0;
        while(count<k){
            findNearest(root, point, root, Double.MAX_VALUE,
            Orientation.LeftRight,k);
            //
            count++;
            Node e = pq.delMax();
            temp.push(e.p);
            //StdOut.println(e.p);
            
        }
        while(count1<k){
            result[count1] =temp.pop();
               
            count1++;
        }
        /*result[0] = e.p;
        result[1] = f.p;
        result[2] = g.p;
        StdOut.println(e.finded);*/
        return result;
        //return result;
    }
    //private LinkedList<Point2D> findNearest(Node x, Point2D p, Point2D nearest,
    // private Point2D findNearest(Node x root, Point2D p query, Point2D nearest,
        //double nearestDistance, Orientation orientation) {
    private Node findNearest(Node x, Point2D p, Node nearest,
        double nearestDistance, Orientation orientation,int k) {
        /*if (x.finded==true) {
            //nearest.finded=true;
            return nearest;
        }*/
        if (x == null) {
            //LinkedList<Point2D> nearest_temp = new LinkedList<Point2D>();
            //nearest_temp.add(nearest);
            //return nearest_temp;
            return nearest;
        }
        Node closest = nearest;
        double closestDistance = nearestDistance;
        //the distance we wanna
        double distance = x.p.distanceSquaredTo(p);
        x.dist=distance;
        if(x.finded==false){
            pq.insert(x);
        }
        if(pq.size()>k){
            pq.delMax();
        }
        
        if (distance < nearestDistance) {
            //if this point is closer than previous point, renew it
            closest = x;
            closestDistance = distance;
        }
        Node first, second;
        if (orientation == Orientation.LeftRight) {
            if (p.x() < x.p.x()) {
                first = x.lb;
                second = x.rt;
            } else {
                first = x.rt;
                second = x.lb;
            }
        } else {
            if (p.y() < x.p.y()) {
                first = x.lb;
                second = x.rt;
            } else {
                first = x.rt;
                second = x.lb;
            }
        }
        Orientation nextOrientation = orientation.next();

        //if (first != null && first.finded!=true) {
        if (first != null) {    
            closest = findNearest(first, p, closest, closestDistance, nextOrientation, k);
            //first.finded=true;
            closestDistance = closest.p.distanceSquaredTo(p);
        }
        //if (second != null && second.finded!=true) {
        if (second != null) {
            closest = findNearest(second, p, closest, closestDistance,
                    nextOrientation, k);
            //second.finded=true;
        }
        //pq.insert(closest); 
        //StdOut.println(x.compareTo(closest));
        x.finded=true;
        //x.finded=false;
        //closest.finded=true;
        return closest;
    }
}

