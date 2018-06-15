
import java.io.BufferedReader;
import java.io.FileReader;
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
            result[k - 1 - i] = dpq.get(key);
            dpq.deleteMax();;
        }
        return result;
    }

    private void nearestpoint(Node node, Point2D query, int target) {

        if (node == null) {
            return;
        }
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
        } else {
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

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            Stack<Point2D> shell = new Stack<Point2D>();
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] p = in.split(""\\s+"");
                shell.push(new Point2D(Double.parseDouble(p[0]), Double.parseDouble(p[1])));
            }
            Point2D[] pp = new Point2D[shell.size()];
            for (int i = pp.length - 1; i > -1; i--) {
                pp[i] = shell.pop();
            }
            FindNeighbors e = new FindNeighbors();
            e.init(pp);
            Point2D q1 = new Point2D(0.9865848239, 0.6925894824);
            Point2D q2 = new Point2D(0.8795693399, 0.8849481938);
            Point2D[] re1 = e.query(q1, 5);
            Point2D[] re2 = e.query(q2, 5);
            System.out.println(""result of query1:"");
            for (int i = 0; i < 5; i++) {
                System.out.println(re1[i].x() + "" "" + re1[i].y());
            }
            System.out.println(""result of query1:"");
            for (int i = 0; i < 5; i++) {
                System.out.println(re2[i].x() + "" "" + re2[i].y());
            }
        }
    }
}

