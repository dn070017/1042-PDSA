
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FindNeighbors {

    public class Node {

        private Node left;
        private Node right;
        private Point2D value;

        public Node(Node left, Node right, Point2D value) {
            this.left = left;
            this.right = right;
            this.value = value;
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

    }

    private Node root;

    private void TwoDTree(Node r, Point2D[] ps) {
        if (ps.length != 0) {
            Arrays.sort(ps, Point2D.X_ORDER);
            Point2D mean = ps[ps.length / 2];
            r.setValue(mean);
            r.setLeft(new Node(null, null, null));
            r.setRight(new Node(null, null, null));

            //StdDraw.line(mean.x(), 0, mean.x(), 1);
            y_Divide(r.getLeft(), Arrays.copyOfRange(ps, 0, ps.length / 2), mean.x());
            y_Divide(r.getRight(), Arrays.copyOfRange(ps, ps.length / 2 + 1, ps.length), mean.x());
        }
    }

    private void x_Divide(Node r, Point2D[] ps, double seg_y) {
        if (ps.length != 0) {
            Arrays.sort(ps, Point2D.X_ORDER);
            Point2D mean = ps[ps.length / 2];
            r.setValue(mean);
            r.setLeft(new Node(null, null, null));
            r.setRight(new Node(null, null, null));

//            if (mean.y() < seg_y) {
//                StdDraw.line(mean.x(), 0, mean.x(), seg_y);
//            } else {
//                StdDraw.line(mean.x(), seg_y, mean.x(), 1);
//            }
            y_Divide(r.getLeft(), Arrays.copyOfRange(ps, 0, ps.length / 2), mean.x());
            y_Divide(r.getRight(), Arrays.copyOfRange(ps, ps.length / 2 + 1, ps.length), mean.x());
        }
    }

    private void y_Divide(Node r, Point2D[] ps, double seg_x) {
        if (ps.length != 0) {
            Arrays.sort(ps, Point2D.Y_ORDER);
            Point2D mean = ps[ps.length / 2];
            r.setValue(mean);
            r.setLeft(new Node(null, null, null));
            r.setRight(new Node(null, null, null));

//            if (mean.x() < seg_x) {
//                StdDraw.line(0, mean.y(), seg_x, mean.y());
//            } else {
//                StdDraw.line(seg_x, mean.y(), 1, mean.y());
//            }
            x_Divide(r.getLeft(), Arrays.copyOfRange(ps, 0, ps.length / 2), mean.y());
            x_Divide(r.getRight(), Arrays.copyOfRange(ps, ps.length / 2 + 1, ps.length), mean.y());
        }
    }

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        root = new Node(null, null, null);
        TwoDTree(root, points);
    }

    MaxPQ<Point2D> minptpq;
    MaxPQ<Double> mindispq;

    private Point2D findMin(int depth, Point2D target, Node n, Point2D minpoint, double pre_kmindis) {
        if (n != null && n.getValue() != null) {
            double dis = target.distanceTo(n.getValue());
            double mindis = target.distanceTo(minpoint);

            if (dis < mindis && dis > pre_kmindis) {
                mindis = dis;
                minpoint = n.getValue();
            }
            //System.out.println(minp + "" "" + mindis);

            Node targetnode = n.getLeft();
            Node remainnode = n.getRight();
            if (n.getLeft().getValue() != null && n.getRight().getValue() != null) {
                if (target.distanceTo(n.getLeft().getValue()) > target.distanceTo(n.getRight().getValue())) {
                    targetnode = n.getRight();
                    remainnode = n.getLeft();
                }
            }

            minpoint = findMin(depth + 1, target, targetnode, minpoint, pre_kmindis);
            mindis = target.distanceTo(minpoint);
            //System.out.println(depth+"":""+minp + "" "" + mindis);
            double decdis;
            if (depth % 2 == 0) {
                decdis = Math.abs(target.x() - n.getValue().x());
            } else {
                decdis = Math.abs(target.y() - n.getValue().y());
            }
            if (mindis > decdis) {
                //System.out.println(depth+"" decdis:"" + decdis);
                minpoint = findMin(depth + 1, target, remainnode, minpoint, pre_kmindis);
            }
        }
        return minpoint;
    }

    private void findkMin(int depth, Point2D target, Node n, int k) {
        if (n != null && n.getValue() != null) {
            double dis = target.distanceTo(n.getValue());
            double kmindis;
            Point2D kminpt;
            if (minptpq.size() < k) {
                minptpq.insert(n.getValue());
            } else{
                kminpt = minptpq.delMax();
                kmindis = target.distanceTo(kminpt);
                if(dis < kmindis){
                    minptpq.insert(n.getValue());
                }else{
                    minptpq.insert(kminpt);
                }
            }

            Node targetnode = n.getLeft();
            Node remainnode = n.getRight();
            if (n.getLeft().getValue() != null && n.getRight().getValue() != null) {
                if (target.distanceTo(n.getLeft().getValue()) > target.distanceTo(n.getRight().getValue())) {
                    targetnode = n.getRight();
                    remainnode = n.getLeft();
                }
            }

            findkMin(depth + 1, target, targetnode, k);
            
            kmindis = target.distanceTo(minptpq.max());
            double decdis;
            if (depth % 2 == 0) {
                decdis = Math.abs(target.x() - n.getValue().x());
            } else {
                decdis = Math.abs(target.y() - n.getValue().y());
            }
            
            if (kmindis > decdis) {
                //System.out.println(depth+"" decdis:"" + decdis);
                findkMin(depth + 1, target, remainnode, k);
            }
            
        }
    }

        // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];

        minptpq = new MaxPQ<Point2D>(k,point.distanceToOrder());
        mindispq = new MaxPQ<Double>();
        //minptpq.insert(root.getValue());
        //mindispq.insert(point.distanceTo(root.getValue()));
        findkMin(0, point, root, k);
        for (int i = 0; i < k; i++) {
            result[k-i-1] = minptpq.delMax();
        }
//        double pre_kmindis = 0.0;
//        for (int i = 0; i < k; i++) {
//            Point2D minpoint = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
//            minpoint = findMin(0, point, root, minpoint, pre_kmindis);
//            pre_kmindis = point.distanceTo(minpoint);
//            result[i] = minpoint;
//
//            System.out.println(i + "":"" + result[i] + "" "" + pre_kmindis);
////            StdDraw.setPenColor(Color.yellow);
////            StdDraw.filledCircle(minpoint.x(), minpoint.y(), 0.005);
//        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Point2D[] testps = new Point2D[1000];
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int n = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] inpair = in.split("" "");
                Point2D point = new Point2D(Double.parseDouble(inpair[0]), Double.parseDouble(inpair[1]));
                //StdDraw.filledCircle(point.x(), point.y(), 0.01);
                testps[n] = point;
                n++;
            }
long start = System.currentTimeMillis();

            FindNeighbors test = new FindNeighbors();
            test.init(testps);
                        long elapsedTimeMillis = System.currentTimeMillis()-start;
            System.out.println(elapsedTimeMillis);
            start = System.currentTimeMillis();

            //StdDraw.setPenColor(Color.RED);
            //StdDraw.filledCircle(0.531440, 0.616918, 0.01);
            Point2D[] res = test.query(new Point2D(0.531440 ,0.616918), 50);
            elapsedTimeMillis = System.currentTimeMillis()-start;
            System.out.println(elapsedTimeMillis);

        }
    }
}

