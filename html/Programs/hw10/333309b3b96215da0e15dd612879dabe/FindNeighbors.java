
public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    private Point2D[] P;

    public void init(Point2D[] points) {
//        int num = points.length;
//        Node x = new Node(null, null, 1);
//        for (int i = 1; i < num; i++) {
//            while (x.left==null || x.right==null) {
//                
//            }
//        }
//        return;
        P = points;
    }

//    private FindNeighbors insert(Node x) {
//        int cmp = key.compareTo(x.key);
//        if (cmp < 0) {
//            x.left = insert(x.left, key, val);
//        } else if (cmp > 0) {
//            x.right = insert(x.right, key, val);
//        } 
//        return x;
//
//        return null;
//    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {

        MaxPQ<BB> pq = new MaxPQ<>();
        for (int i = 0; i < P.length; i++) {
            if ((P[i].x() <= point.x() + 0.1 || P[i].x() >= point.x() - 0.1)&&(P[i].y() <= point.y() + 0.1 || P[i].y() >= point.y() - 0.1)) {
                Double dis = Math.sqrt(Math.pow(point.x() - P[i].x(), 2) + Math.pow(point.y() - P[i].y(), 2));
                BB bb = new BB(P[i].x(), P[i].y(), dis);
                pq.insert(bb);
                if (pq.size() > k) {
                    pq.delMax();
                }
            }
        }

        Point2D[] result = new Point2D[k];
        for (int i = k - 1; i >= 0; i--) {
            BB bb2 = pq.delMax();
            result[i] = new Point2D(bb2.x, bb2.y);
        }
        return result;
    }

    public static class BB implements Comparable<BB> {

        private final double x;
        private final double y;
        private final double dis;

        public BB(double x, double y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(BB that) {

            if (this.dis > that.dis) {
                return 1;
            } else if (this.dis < that.dis) {
                return -1;
            } else {
                return -0;
            }
        }
    }

//    public class Node {
//
//        private Node left;
//        private Node right;
//        private int value;
//
//        public Node(Node left, Node right, int value) {
//            this.left = left;
//            this.right = right;
//            this.value = value;
//        }
//
//        public Node getLeft() {
//            return (this.left);
//        }
//
//        public Node getRight() {
//            return (this.right);
//        }
//
//        public int getValue() {
//            return (this.value);
//        }
//
//        public void setLeft(Node left) {
//            this.left = left;
//        }
//
//        public void setRight(Node right) {
//            this.right = right;
//        }
//
//        public void setValue(int value) {
//            this.value = value;
//        }
//
//    }
    public static void main(String[] args) {

    }

}

