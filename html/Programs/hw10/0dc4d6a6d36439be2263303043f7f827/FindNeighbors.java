import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;

public class FindNeighbors {
    private Node root = null;
    private static Point2D queryPoint;
    private static int k;
    private MaxPQ<Node> pq = new MaxPQ<Node>();

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    private static class Node implements Comparable<Node> {
        private Point2D key;
        private Node left;
        private Node right;
        private boolean vertical = true;

        public Node(Point2D key, Node left, Node right, boolean vertical) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.vertical = vertical;
        }
        
        public int compareTo(Node that) {
            if (this.key.distanceTo(queryPoint) < that.key.distanceTo(queryPoint)) {
                return -1;
            }
            if (this.key.distanceTo(queryPoint) > that.key.distanceTo(queryPoint)) {
                return +1;
            }
            return 0;
        }
    }
    
    public void put(Point2D key) {
        root = put(root, key, true);
    }

    private Node put(Node node, Point2D key, boolean vertical) {
        if (node == null) {
            return new Node(key, null, null, vertical);
        }

        // do not insert the point if it is already in the 2d-tree;
.
        if (key.equals(node.key)) {
            return node;
        }

        // at the root we use the x-coordinate (if the point to be inserted has a smaller x-coordinate
        // than the point at the root, go left; otherwise go right);
        // then at the next level, we use the y-coordinate (if the point to be inserted has a smaller y-coordinate
        // than the point in the node, go left; otherwise go right);
.
        if (smallerNode(key, node)) {
            node.left = put(node.left, key, !node.vertical);
        } else {
            node.right = put(node.right, key, !node.vertical);
        }

        return node;
    }

    private boolean smallerNode(Point2D key, Node node) {
        int cmp = node.vertical ? Point2D.X_ORDER.compare(key, node.key) : Point2D.Y_ORDER.compare(key, node.key);
        return (cmp < 0);
    }
    
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {    
        
        for(int i = 0; i < points.length; i++) {
            put(points[i]);
        }       
        return;
    }
           
    public void minDistance(Point2D queryPoint, Node node) {        
        if(node == null){
            return;
        }
        
        if (smallerNode(queryPoint, node)) {
            pq.insert(node);
            minDistance(queryPoint, node.left);
            double dis = node.vertical ? (node.key.x() - queryPoint.x()) : (node.key.y() - queryPoint.y());
            
            if(queryPoint.distanceTo(node.key) > dis){
                minDistance(queryPoint, node.right);
            }
        }        
        else {
            pq.insert(node);
            minDistance(queryPoint, node.right);
            double dis = node.vertical ? (queryPoint.x() - node.key.x()) : (queryPoint.y() - node.key.y());
            
            if(queryPoint.distanceTo(node.key) > dis){
                minDistance(queryPoint, node.left);
            }
        }
        
        if(pq.size() > k){
            pq.delMax();
        }
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D queryPoint, int k) {
        this.queryPoint = queryPoint;
        this.k = k;        
        Point2D[] result = new Point2D[k];
        minDistance(queryPoint, root);
        
        for(int i = k -1 ; i >= 0; i--) {
            result[i] = pq.delMax().key;
        }
        return result;
    }

}

