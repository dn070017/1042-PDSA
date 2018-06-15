
import java.util.Arrays;
import java.util.Comparator;
import java.lang.Math;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    private Node root;

    private class Node {

        private Node left, right;
        public Point2D p;
        private boolean vertical = true;
        public double dis;

        public Node(Point2D p, Node left, Node right, boolean vertical, double dis) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
            this.dis = dis;
        }
    }

    public void insert(Point2D p) {
        root = doInsert(root, p, true);
    }

    private Node doInsert(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            return new Node(p, null, null, vertical, 0);
        }

        if (smaller(p, node)) {
            node.left = doInsert(node.left, p, !node.vertical);
        } else {
            node.right = doInsert(node.right, p, !node.vertical);
        }

        return node;
    }

    public boolean smaller(Point2D p, Node node) {
        boolean direction = node.vertical;
        int temp;

        if (direction) {
            temp = Point2D.X_ORDER.compare(p, node.p);
        } else {
            temp = Point2D.Y_ORDER.compare(p, node.p);
        }

        return temp < 0;
    }

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {

//        
//        System.out.print(points.length);
//        
        Point2D[] input = new Point2D[points.length];
        for (int i = 0; i < points.length; i++) {
            input[i] = points[i];
            StdDraw.filledCircle(input[i].x(), input[i].y(), 0.01);
            StdDraw.text(input[i].x(), input[i].y() + 0.03, Integer.toString(i));

            insert(input[i]);

        }

//        System.out.println(root.p.x());
//        System.out.println(root.left.p.x());
//        System.out.println(root.right.p.x());
//        System.out.println(root.left.left.p.x());
//        System.out.println(root.right.left.p.x());
//
//        System.out.println(""--------------------"");

    }
    public BST<Double, Point2D> apq = new BST<Double, Point2D>();

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {

        Point2D[] result = new Point2D[k];
        doNearest(root,point, k);
        
        for(int i = 0 ; i < k ; i++){
            result[i] = apq.get(apq.max());
            apq.deleteMax();
//            System.out.println(result[i].x());
        }
        
        return result;
    }   

    private void doNearest(Node node, Point2D querypoint, int count) {
        if (node == null) {
            return;
        }

//        System.out.println(""pq.delMax().x():"" + pq.delMax().x());
//        System.out.println(""++"");
        double dis;

        if (node.p != null) {
            dis = querypoint.distanceTo(node.p);
        } else {
            dis = Double.MAX_VALUE;
        }

        apq.put(dis, node.p);

        if (apq.size() > count) {
            apq.deleteMax();
        }

        if (smaller(querypoint, node)) {
            doNearest(node.left, querypoint, count);
            if (node.vertical) {
                if ((Math.abs(querypoint.x() - node.p.x()) < apq.max()) || apq.size() < count) {
                    doNearest(node.right, querypoint, count);
                } else {
                    if ((Math.abs(querypoint.y() - node.p.y()) < apq.max()) || apq.size() < count) {
                        doNearest(node.right, querypoint, count);
                    }
                }
            }
        } else {
            doNearest(node.right, querypoint, count);
            if (node.vertical) {
                if ((Math.abs(querypoint.x() - node.p.x()) < apq.max()) || apq.size() < count) {
                    doNearest(node.left, querypoint, count);
                } else {
                    if ((Math.abs(querypoint.y() - node.p.y()) < apq.max()) || apq.size() < count) {
                        doNearest(node.left, querypoint, count);
                    }
                }
            }
        }
    }

//    public static void main(String[] args) {
//        FindNeighbors input = new FindNeighbors();
//        Point2D[] test = new Point2D[5];
//
//        test[0] = new Point2D(0.3833339428, 0.1459115606);
//        test[1] = new Point2D(0.3426077152, 0.7218207763);
//        test[2] = new Point2D(0.3406783533, 0.3164794008);
//        test[3] = new Point2D(0.5034046695, 0.7964833541);
//        test[4] = new Point2D(0.5969555271, 0.1587087880);
//
//        Point2D testa = new Point2D(0.3, 0.5);
//        StdDraw.setCanvasSize(500, 500);
//        StdDraw.clear(StdDraw.WHITE);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(testa.x(), testa.y(), 0.01);
//        StdDraw.text(testa.x(), testa.y() + 0.03, ""query"");
//
//        input.init(test);
//
//        input.query(testa, 3);
//    }

}

