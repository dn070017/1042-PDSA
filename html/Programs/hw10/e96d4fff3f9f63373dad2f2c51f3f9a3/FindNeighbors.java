
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
public class FindNeighbors {
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    private enum Orientation {
        LeftRight, AboveBelow;
        public Orientation next() {
            if (this.equals(Orientation.AboveBelow))return Orientation.LeftRight;
            return Orientation.AboveBelow;
        }
    }

    private static class Node {
        //Comparator<Node> X_ORDER = new XOrder();
        private final Point2D p ;
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
        }
        
    }
    
    private Comparator<Node> distanceToOrder() {
        return new DistanceToOrder();
    }
    private class DistanceToOrder implements Comparator<Node>{
        @Override
        public int compare(Node p,Node q) {
            return Double.compare(p.dist, q.dist);
        }
    }
    MaxPQ<Node> pq = new MaxPQ<>(distanceToOrder()); 
    //public DistanceToOrder();
    Node root = null;
        

    

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
            for(int i = 0;i<points.length;i++) {
                
               insert(points[i]);
           }
        //input all items
        //put(points)
        return;
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
    //put in the tree
     private Node put(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            //size++;
            return new Node(p);
        }
        //if (x.p.equals(p)) {
            //return x;
        //}
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp < 0) {
            x.lb = put(x.lb, p, nextOrientation);
            if (x.lb == null) {
                if (orientation == Orientation.LeftRight) {
                    x.lb = new Node(p);
                } 
            }
        } else {
            x.rt = put(x.rt, p, nextOrientation);
            if (x.rt == null) {
                if (orientation == Orientation.LeftRight) {
                    x.rt= new Node(p);
                } 
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
    public Point2D[] query(Point2D point,int k){
        Point2D[] result = new Point2D[k];
        Stack<Point2D> temp = new Stack<Point2D>();
        int count = 0;
        int count1 = 0;
        Node a = findNearest(root, point, root, Double.MAX_VALUE,
        Orientation.LeftRight,k);
        while(count<k){
            count++;
            Node e = pq.delMax();
            temp.push(e.p);
        }
        while(!temp.isEmpty()){
            result[count1] =temp.pop(); 
            count1++;
        }
        return result;
    }

    private Node findNearest(Node x, Point2D p, Node nearest,
        double nearestDistance, Orientation orientation,int k) {
        double closestDistance = nearestDistance;
        double distance = x.p.distanceSquaredTo(p);
        x.dist=distance;
        if (x == null) {
        if(x.finded==false){
            pq.insert(x);
            if(pq.size()>k){
                pq.delMax();}
        }
            return nearest;
        }
        Node closest = nearest;

        if(x.finded==false){
            pq.insert(x);
            if(pq.size()>k){
                pq.delMax();}
        }
        
        if (distance < nearestDistance) {
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
        if (first != null) {    
            closest = findNearest(first, p, closest, closestDistance, nextOrientation, k);
            closestDistance = closest.p.distanceSquaredTo(p);
        }
        if (second != null) {
            closest = findNearest(second, p, closest, closestDistance,
                    nextOrientation, k);
        }
        x.finded=true;
        closest.finded=true;
        return closest;
    }
}

