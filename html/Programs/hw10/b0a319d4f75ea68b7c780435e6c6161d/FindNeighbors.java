public class FindNeighbors {

    public final class Square {

        private final double x_max, y_max;
        private final double x_min, y_min;

        public Square(double x_min, double y_min, double x_max, double y_max) {
            this.x_max = x_max;
            this.y_max = y_max;
            this.x_min = x_min;
            this.y_min = y_min;
        }

        public double distanceTo(Point2D p) {
            return Math.sqrt(this.distanceSquaredTo(p));
        }

        public double distanceSquaredTo(Point2D p) {
            double dx = 0, dy = 0;
            if (p.x() < x_min) {
                dx = p.x() - x_min;
            } else if (p.x() > x_max) {
                dx = p.x() - x_max;
            }
            if (p.y() < y_min) {
                dy = p.y() - y_min;
            } else if (p.y() > y_max) {
                dy = p.y() - y_max;
            }
            return dx * dx + dy * dy;
        }

    }

    public class Pair implements Comparable<Pair> {

        private Point2D a;
        private Point2D b;
        private double distance;

        public Pair(Point2D a, Point2D b) {
            this.a = a;
            this.b = b;
            this.distance = a.distanceTo(b);
        }

        public Point2D getA() {
            return this.a;
        }

        public Point2D getB() {
            return this.b;
        }

        public int compareTo(Pair that) {
            if (this.distance > that.distance) {
                return 1;
            } else if (this.distance < that.distance) {
                return -1;
            }
            return 0;
        }

    }

    public class Node {

        private Node left;
        private Node right;
        private Node up;
        private Point2D value;
        private boolean m;

        public Node(Node left, Node right, Node up, Point2D value, boolean m) {
            this.left = left;
            this.right = right;
            this.up = up;
            this.value = value;
            this.m = m;
        }

        public Node getRight() {
            return (this.right);
        }

        public Node getLeft() {
            return (this.left);
        }

        public Node getUp() {
            return (this.up);
        }

        public Point2D getValue() {
            return (this.value);
        }

        public boolean hasUp() {
            return (this.up != null);
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean getM() {
            return this.m;
        }
    }

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }
    // TODO
    public Node root = null;
    private Node target = null;
    private int side = 0;
    private Point2D tip = null;
    private MaxPQ<Pair> PQ = null;

    public void init(Point2D[] points) {
        Node current = null;
        for (int i = 0; i < points.length; i++) {
            int flag = 0;
            boolean layer = true;
            current = root;
            while (flag == 0) {
                if (current == null) {
                    current = new Node(null, null, null, points[i], layer);
                    root = current;
                    flag = 1;
                } else {
                    if (current.getM()) {
                        if (points[i].x() > current.value.x()) {
                            if (current.getRight() == null) {
                                Node newcurrent = new Node(null, null, current, points[i], !layer);
                                current.setRight(newcurrent);
                                flag = 1;
                            } else {
                                current = current.getRight();
                            }
                        } else {
                            if (current.getLeft() == null) {
                                Node newcurrent = new Node(null, null, current, points[i], !layer);
                                current.setLeft(newcurrent);
                                flag = 1;
                            } else {
                                current = current.getLeft();
                            }
                        }
                    } else {
                        if (points[i].y() > current.value.y()) {
                            if (current.getRight() == null) {
                                Node newcurrent = new Node(null, null, current, points[i], !layer);
                                current.setRight(newcurrent);
                                flag = 1;
                            } else {
                                current = current.getRight();
                            }
                        } else {
                            if (current.getLeft() == null) {
                                Node newcurrent = new Node(null, null, current, points[i], !layer);
                                current.setLeft(newcurrent);
                                flag = 1;
                            } else {
                                current = current.getLeft();
                            }
                        }
                    }
                }
                layer = !layer;
            }
        }
        return;
    }

    public double Shortdistance(Node point, boolean m) {
        double a = 0;
        Square d;
        if (point.hasUp()) {
            if (m) {
                if (tip.x() > point.getValue().x()) {
                    if (point.getValue().y() > point.getUp().getValue().y()) {
                        d = new Square(0, point.getUp().getValue().y(), point.getValue().x(), 1);
                    } else {
                        d = new Square(0, 0, point.getValue().x(), point.getUp().getValue().y());
                    }
                } else {
                    if (point.getValue().y() > point.getUp().getValue().y()) {
                        d = new Square(point.getValue().x(), point.getUp().getValue().y(), 1, 1);
                    } else {
                        d = new Square(point.getValue().x(), 0, 1, point.getUp().getValue().y());
                    }
                }

            } else {
                if (tip.y() > point.getValue().y()) {
                    if (point.getValue().x() > point.getUp().getValue().x()) {
                        d = new Square(point.getUp().getValue().x(), 0, 1, point.getValue().y());
                    } else {
                        d = new Square(0, 0, point.getUp().getValue().x(), point.getValue().y());
                    }
                } else {
                    if (point.getValue().x() > point.getUp().getValue().x()) {
                        d = new Square(point.getUp().getValue().x(), point.getValue().y(), 1, 1);
                    } else {
                        d = new Square(0, point.getValue().y(), point.getUp().getValue().x(), 1);

                    }
                }

            }
            a = d.distanceTo(tip);
        } else {
            a = Math.abs(tip.x() - point.getValue().x());
        }

        return a;
    }

    public void Find(Node target, boolean m) {

        if (target == null) {
            return;
        }

        if (PQ.size() < side) {
            PQ.insert(new Pair(tip, target.getValue()));
        } else {
            if (PQ.max().distance > tip.distanceTo(target.getValue())) {
                
                PQ.delMax();
                PQ.insert(new Pair(tip, target.getValue()));
            }
        }

        Node near;
        Node far;
        if ((m && (target.getValue().x() >= tip.x())) || (!m && (target.getValue().y() >= tip.y()))) {
            near = target.getLeft();
            far = target.getRight();

        } else {
            near = target.getRight();
            far = target.getLeft();

        }
        Find(near, !m);
        if (PQ.size() < side) {
            Find(far, !m);
        } else {
            if (PQ.max().distance > Shortdistance(target, m)) {
                Find(far, !m);
            }

        }

        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        PQ = new MaxPQ<Pair>(k);
        target = root;
        side = k;
        tip = point;
        Find(target, true);
        for (int i = k - 1; i > -1; i--) {
            result[i] = PQ.delMax().getB();
        }

        return result;
    }

}

