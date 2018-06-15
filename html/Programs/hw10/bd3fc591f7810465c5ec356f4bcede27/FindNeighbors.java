
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
            y_Divide(r.getRight(), Arrays.copyOfRange(ps, ps.length / 2+1, ps.length), mean.x());
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
            y_Divide(r.getRight(), Arrays.copyOfRange(ps, ps.length / 2+1, ps.length), mean.x());
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
            x_Divide(r.getRight(), Arrays.copyOfRange(ps, ps.length / 2+1, ps.length), mean.y());
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

    private Point2D findMin(int depth, Point2D point, Node n, Point2D minpoint, Double mindis) {
        System.out.println(minpoint + "" "" + mindis);

        double cmpdis, Ldis, Rdis;
        if (depth % 2 == 0) {
            cmpdis = Math.abs(point.x() - n.getValue().x());
        } else {
            cmpdis = Math.abs(point.y() - n.getValue().y());
        }

        if (n.getLeft() != null && n.getLeft().getValue() != null) {
            Ldis = point.distanceTo(n.getLeft().getValue());
            if (Ldis < mindis) {
                mindis = Ldis;
                minpoint = n.getLeft().getValue();
            }
            if (Ldis < cmpdis) {
                findMin(++depth, point, n.getLeft(), minpoint, mindis);
            }
        }
        if (n.getRight() != null && n.getRight().getValue() != null) {
            Rdis = point.distanceTo(n.getRight().getValue());
            if (Rdis < mindis) {
                mindis = Rdis;
                minpoint = n.getRight().getValue();
            }
            if (Rdis < cmpdis) {
                findMin(++depth, point, n.getRight(), minpoint, mindis);
            }
        }
        if (n.getLeft() != null && n.getLeft().getValue() != null && n.getRight() != null && n.getRight().getValue() != null) {
            Ldis = point.distanceTo(n.getLeft().getValue());
            Rdis = point.distanceTo(n.getRight().getValue());
            if (Ldis < mindis) {
                mindis = Ldis;
                minpoint = n.getLeft().getValue();
            }
            if (Rdis < mindis) {
                mindis = Rdis;
                minpoint = n.getRight().getValue();
            }
            if (Ldis >= cmpdis && Rdis >= cmpdis) {
                findMin(++depth, point, n.getLeft(), minpoint, mindis);
                findMin(++depth, point, n.getRight(), minpoint, mindis);
            }
        }
        return minpoint;
    }

    private Point2D findMin2(int depth, Point2D point, Node n, Point2D minpoint, double pre_kmindis) {
        if (n != null && n.getValue() != null) {
            double dis = point.distanceTo(n.getValue());
            double mindis = point.distanceTo(minpoint);

            if (dis < mindis && dis > pre_kmindis) {
                mindis = dis;
                minpoint = n.getValue();
            }
            //System.out.println(minp + "" "" + mindis);
            minpoint = findMin2(depth + 1, point, n.getLeft(), minpoint, pre_kmindis);
            mindis = point.distanceTo(minpoint);
            //System.out.println(depth+"":""+minp + "" "" + mindis);
            double decdis;
            if (depth % 2 == 0) {
                decdis = Math.abs(point.x() - n.getValue().x());
            } else {
                decdis = Math.abs(point.y() - n.getValue().y());
            }
            if (mindis > decdis) {
                //System.out.println(depth+"" decdis:"" + decdis);
                minpoint = findMin2(depth + 1, point, n.getRight(), minpoint, pre_kmindis);
            }
        }
        return minpoint;
    }

    // TODO
// the result should be sorted accordingly to their distances to the query, from small to large
// please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];

        double pre_kmindis = 0.0;
        for (int i = 0; i < k; i++) {
            //Double mindis = Double.MAX_VALUE;
            Point2D minpoint = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
            //minpoint = findMin(0, point, root, minpoint, mindis);
            minpoint = findMin2(0, point, root, minpoint, pre_kmindis);
            pre_kmindis = point.distanceTo(minpoint);
            result[i] = minpoint;

//            System.out.println(i + "":"" + result[i] + "" "" + pre_kmindis);
//            StdDraw.setPenColor(Color.yellow);
//            StdDraw.filledCircle(minpoint.x(), minpoint.y(), 0.005);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Point2D[] testps = new Point2D[10];
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int n = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] inpair = in.split("" "");
                Point2D point = new Point2D(Double.parseDouble(inpair[0]), Double.parseDouble(inpair[1]));
                //StdDraw.filledCircle(point.x(), point.y(), 0.01);
                testps[n] = point;
                n++;
            }

            FindNeighbors test = new FindNeighbors();
            test.init(testps);
            //StdDraw.setPenColor(Color.RED);
            //StdDraw.filledCircle(0.531440, 0.616918, 0.01);
            Point2D[] res = test.query(new Point2D(0.531440, 0.616918), 1);
        }
    }
}
