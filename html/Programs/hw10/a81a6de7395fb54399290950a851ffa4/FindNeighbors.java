
import java.util.Comparator;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {

    }
    Node root = null;

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        for (int i = 0; i < points.length; i++) {
            this.treehelp(new Node(null, null, points[i]));
        }
    }

    private void treehelp(Node node) {
        if (root == null) {
            root = node;
            node.setseeX(true);
        } else {
            Node thisroot = root;
            while (thisroot.getNext(node) != null) {
                thisroot = thisroot.getNext(node);
            }
            if (thisroot.seeX()) {
                node.setseeX(false);
                if (thisroot.getX() < node.getX()) {
                    thisroot.setRight(node);
                } else {
                    thisroot.setLeft(node);
                }
            } else {
                node.setseeX(true);

                if (thisroot.getY() < node.getY()) {
                    thisroot.setRight(node);
                } else {
                    thisroot.setLeft(node);
                }

            }

        }
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 

    public Point2D[] query(Point2D point , int k){
        MaxPQ<Node> pq = new MaxPQ<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getDistance() > o2.getDistance()) return 1;
                else if (o1.getDistance() < o2.getDistance()) return -1;
                else return 0;
            }
        });
        queryHelper(pq , root , point , k , Double.MAX_VALUE);
        Point2D[] result = new Point2D[k];
        for (int i = k-1; i >= 0 ; i--) {
            result[i] = pq.delMax().getValue();
        }
        return result;
    }

    private void queryHelper(MaxPQ<Node> pq , Node root , Point2D target , int k , double minDistance) {
        if (root == null) return;
        root.setDistance(root.distanceTo(target));
        pq.insert(root);

        if (pq.size() > k) {
            pq.delMax();
            Node peek = pq.delMax();
            minDistance = peek.distanceTo(target);
            pq.insert(peek);
        }

        Node targetNode = new Node(null, null,target);
        queryHelper(pq , root.getNext(targetNode) , target, k , minDistance);

        if (pq.size() < k) {
            queryHelper(pq, root.getOppositeNext(targetNode), target, k , minDistance);
        } else if (root.seeX()) {
            if (Math.abs(root.getX() - target.x()) < minDistance)
                queryHelper(pq , root.getOppositeNext(targetNode) , target , k , minDistance);
        } else {
            if (Math.abs(root.getY() - target.y()) < minDistance)
                queryHelper(pq , root.getOppositeNext(targetNode) , target , k , minDistance);
        }
    }

    public class Node implements Comparable<Node> {

        private Node left;
        private Node right;
        private Point2D value;
        private boolean seeX;
        private double distance;

        public Node(Node left, Node right, Point2D value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node getNext(Node x) {
            if (this.isleaf()) {
                return null;
            }
            if (this.seeX()) {
                if (this.getX() < x.getX()) {
                    return x.getRight();
                } else {
                    return x.getLeft();
                }
            } else if (this.getY() < x.getY()) {
                return x.getRight();
            } else {
                return x.getLeft();
            }
        }

        public Node getOppositeNext(Node target) {
            if (this.isleaf()) {
                return null;
            }
            if (this.seeX()) {
                if (this.getX() < target.getX()) {
                    return this.getLeft();
                } else {
                    return this.getRight();
                }
            } else if (this.getY() < target.getY()) {
                return this.getLeft();
            } else {
                return this.getRight();
            }
        }

        public boolean seeX() {
            return seeX;
        }

        public double getX() {
            return value.x();
        }

        public double getY() {
            return value.y();
        }

        public boolean setseeX(boolean a) {
            return seeX = a;
        }

        public boolean isleaf() {
            return left == null && right == null;
        }

        public Node getLeft() {
            return (this.left);
        }

        public Node getRight() {
            return (this.right);
        }

        public Point2D getValue() {
            return (this.value);
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setValue(Point2D value) {
            this.value = value;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
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

        private double distanceTo(Point2D target) {
            return value.distanceTo(target);
        }

       

    }

    public static void main(String[] args) {

        int num = 1000;
        int k = 10;

        Point2D[] points = new Point2D[num];
        for (int i = 0; i < num; i++) {
            points[i] = new Point2D(Math.random(), Math.random());
        }

        FindNeighbors findNeighbors = new FindNeighbors();
        findNeighbors.init(points);

        Point2D target = new Point2D(Math.random(), Math.random());
        Point2D[] result = findNeighbors.query(target, k);

        for (int i = 0; i < result.length; i++) {
            String p1 = String.format(""(%.3f,%.3f)"", result[i].x(), result[i].y());
            System.out.println(i + ""\t"" + p1 + ""\t"");
            

        }
        
    }
}

