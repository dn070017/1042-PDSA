
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    Node root = null;

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        for (int i=0;i<points.length;i++) {
            this.insert(new Node(points[i], null, null));
        }
    }

    private void insert(Node node) {
        if (root == null) {
            root = node;
            node.setseeX(true);
        } else {
            Node thisRoot = root;
            while (thisRoot.getNext(node) != null) {
                thisRoot = thisRoot.getNext(node);
            }
            if (thisRoot.seeX()) {
                node.setseeX(false);
                if (thisRoot.getX() < node.getX()) {
                    thisRoot.setRight(node);
                } else {
                    thisRoot.setLeft(node);
                }
            } else {
                node.setseeX(true);
                if (thisRoot.getY() < node.getY()) {
                    thisRoot.setRight(node);
                } else {
                    thisRoot.setLeft(node);
                }
            }
        }
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        MaxPQ<Node> pq = new MaxPQ<>();

        queryHelper(pq, root, point, k, 0);
        Point2D[] result = new Point2D[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.delMax().getPoint2D();
        }
        return result;
    }

    private void queryHelper(MaxPQ<Node> pq, Node root, Point2D target, int k, double minDistance) {
        if (root == null) {
            return;
        }
        root.setDistance(root.distanceTo(target));
        pq.insert(root);

        if (pq.size() > k) {
            pq.delMax();
            Node p = pq.delMax();
            minDistance = p.distanceTo(target);
            pq.insert(p);
        }

        Node targetNode = new Node(target, null, null);
        queryHelper(pq, root.getNext(targetNode), target, k, minDistance);

        if (pq.size() < k) {
            queryHelper(pq, root.getOppositeNext(targetNode), target, k, minDistance);
        } else if (root.seeX()) {
            if (Math.abs(root.getX() - target.x()) < minDistance) {
                queryHelper(pq, root.getOppositeNext(targetNode), target, k, minDistance);
            }
        } else {
            if (Math.abs(root.getY() - target.y()) < minDistance) {
            queryHelper(pq, root.getOppositeNext(targetNode), target, k, minDistance);
           }
        }
    }

    static class Node implements Comparable<Node> {

        private Point2D point2D;
        private Node left;
        private Node right;
        private boolean seeX;
        private double distance;

        Node(Point2D point2D, Node left, Node right) {
            this.point2D = point2D;
            this.left = left;
            this.right = right;
        }

        public Node getNext(Node target) {
            if (this.isLeaf()) {
                return null;
            }
            if (this.seeX()) {
                if (this.getX() < target.getX()) {
                    return this.getRight();
                } else {
                    return this.getLeft();
                }
            } else {if (this.getY() < target.getY()) {
                return this.getRight();
            }
                else{
                return this.getLeft();
            }
        }
        }
        public int compareTo(Node o) {
            if (this.getDistance() > o.getDistance()) {
                return 1;
            } else if (this.getDistance() < o.getDistance()) {
                return -1;
            } else {
                return 0;
            }
        }

        public Node getOppositeNext(Node target) {
            if (this.isLeaf()) {
                return null;
            }
            if (this.seeX()) {
                if (this.getX() < target.getX()) {
                    return this.getLeft();
                } else {
                    return this.getRight();
                }
            } else {
                if(this.getY() < target.getY()) {
                return this.getLeft();
            } else {
                return this.getRight();
            }
        }
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }

        public double getX() {
            return point2D.x();
        }

        public double getY() {
            return point2D.y();
        }

        public double distanceTo(Point2D p) {
            return point2D.distanceTo(p);
        }

        public Point2D getPoint2D() {
            return point2D;
        }

        public boolean seeX() {
            return seeX;
        }

        public void setseeX(boolean dependsOnX) {
            seeX = dependsOnX;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
         try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int num =1000;
            
            Point2D[] points = new Point2D[num];
            for (int i = 0; i < num; i++) {
                String[] in = br.readLine().split("" "");
                points[i] = new Point2D(Double.parseDouble(in[0]), Double.parseDouble(in[1]));
            }

          
        
        int k = 10;
        

        FindNeighbors findNeighbors = new FindNeighbors();
        findNeighbors.init(points);

        Point2D target = new Point2D(0.1354339200,0.7019446863);
        Point2D[] result = findNeighbors.query(target, k);

        for (int i = 0; i < result.length; i++) {
            String p1 = String.format(""(%.3f,%.3f)"", result[i].x(), result[i].y());
            System.out.println(i + ""\t"" + p1 + ""\t"");

        }
         }
    }
}

