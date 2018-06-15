
import static java.lang.Math.abs;

public class FindNeighbors {

    private Node root;
    // DO NOT MODIFY THE CONSTRUCTOR! 

//    private MaxPQ<Point2D> pq = new MaxPQ<Point2D>();
//    private MaxPQ<Double> dpq = new MaxPQ<Double>();
    private BST<Double, Point2D> dpq = new BST<Double, Point2D>();

    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    private class Node {

        private Point2D p;         // associated data
        private Node left, right;  // left and right subtrees
        private boolean vertical = true;
        private int N;

        public Node(Point2D p, Node left, Node right, boolean vertical) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
        }
    }

    public void init(Point2D[] points) {
        for (int i = 0; i < points.length; i++) {
            root = doInsert(root, points[i], true);
        }
        return;
    }

    private Node doInsert(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            return new Node(p, null, null, vertical);
        }

        if (p.equals(node.p)) {
            return node;
        }

        if (isSmallerThanPointInNode(p, node)) {
            node.left = doInsert(node.left, p, !node.vertical);
        } else {
            node.right = doInsert(node.right, p, !node.vertical);
        }
        node.N = 1 + node.left.N + node.right.N;
        return node;
    }

    private boolean isSmallerThanPointInNode(Point2D p, Node node) {
        int cmp;
        if (node.vertical) {
            cmp = Point2D.X_ORDER.compare(p, node.p);
        } else {
            cmp = Point2D.Y_ORDER.compare(p, node.p);
        }
        return (cmp < 0);
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        nearestpoint(root, point, k);
        Point2D[] result = new Point2D[k];
        for (int i = 0; i < k; i++) {
            double key = dpq.max();
            result[k-1-i] = dpq.get(key);
            dpq.deleteMax();;
        }
        return result;
    }

    private void nearestpoint(Node node, Point2D query, int target) {

        double dist = node.p.distanceTo(query);
        dpq.put(dist, node.p);
        if (dpq.size() > target) {
            dpq.deleteMax();
        }

        if (isSmallerThanPointInNode(query, node)) {
            nearestpoint(node.left, query, target);
            double distv;
            if (node.vertical) {
                distv = abs(query.x() - node.p.x());
            } else {
                distv = abs(query.y() - node.p.y());
            }
            if (dpq.max() > distv || dpq.size() < target) {
                nearestpoint(node.right, query, target);
            }
        }else{
            nearestpoint(node.right, query, target);
            double distv;
            if (node.vertical) {
                distv = abs(query.x() - node.p.x());
            } else {
                distv = abs(query.y() - node.p.y());
            }
            if (dpq.max() > distv || dpq.size() < target) {
                nearestpoint(node.left, query, target);
            }
        }
    }

}

