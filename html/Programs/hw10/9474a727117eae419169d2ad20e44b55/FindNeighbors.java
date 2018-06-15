
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import static java.lang.Math.abs;

public class FindNeighbors {

    private Node root = null;
    public static Point2D queryPoint;
    private static int k;
    private MaxPQ<Node> pq = new MaxPQ<Node>();

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    public static class Node implements Comparable<Node> {

        public Point2D key;
        private Node left;
        private Node right;
        public double dist = 0.0;
        public boolean vertical = true;

        public Node(Point2D key, Node left, Node right, boolean vertical) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.vertical = vertical;
        }

        public int compareTo(Node that) {
            if (this.dist < that.dist) {
                return -1;
            }
            if (this.dist > that.dist) {
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
        for (int i = 0; i < points.length; i++) {
            put(points[i]);
        }
        return;
    }

    public void minDistance(Point2D queryPoint, Node node) {

        if (node == null) {
            return;
        }

        if (smallerNode(queryPoint, node)) {
            node.dist = node.key.distanceTo(queryPoint);
            pq.insert(node);

            if (pq.size() > k) {
                double dis = node.vertical ? Math.abs(node.key.x() - queryPoint.x()) : Math.abs(node.key.y() - queryPoint.y());

                if (dis > pq.max().dist) {
                    node.right = null;
                    pq.delMax();
                } else {
                    pq.delMax();
                }
            }
            minDistance(queryPoint, node.left);
            minDistance(queryPoint, node.right);
            
        } 
        
        else {
            node.dist = node.key.distanceTo(queryPoint);
            pq.insert(node);

            if (pq.size() > k) {
                double dis = node.vertical ? Math.abs(node.key.x() - queryPoint.x()) : Math.abs(node.key.y() - queryPoint.y());

                if (dis > pq.max().dist) {
                    node.left = null;
                    pq.delMax();
                } else {
                    pq.delMax();
                }
            }

            minDistance(queryPoint, node.right);
            minDistance(queryPoint, node.left);
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

        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.delMax().key;
        }
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//            int index = 0;
//            Point2D[] array = new Point2D[1000];
//            FindNeighbors f = new FindNeighbors();
//
//            while (br.ready()) {
//                String[] data = br.readLine().split("" "");
//
//                Double x = Double.parseDouble(data[0]);
//                Double y = Double.parseDouble(data[1]);
//                Point2D p = new Point2D(x, y);
//
//                array[index++] = p;
//            }
//            f.init(array);
//            Point2D p = new Point2D(0.531440, 0.616918);
//            f.query(p, 10);
//
//            for (int i = 0; i < 10; i++) {
//                System.out.println(f.query(p, 10)[i]);
//            }
//        }
//    }
}

