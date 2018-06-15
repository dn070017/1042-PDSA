
public class FindNeighbors {

    public final class Square {

        private final double x_max, y_max;
        private final double x_min, y_min;   
           
        
        public Square(double x_min, double y_min, double x_max, double y_max) {
            if (Double.isNaN(x_min) || Double.isNaN(x_max)) {
                throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
            }
            if (Double.isNaN(y_min) || Double.isNaN(y_max)) {
                throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
            }
            if (x_max < x_min || y_max < y_min) {
                throw new IllegalArgumentException(""Invalid rectangle"");
            }
            
            this.x_max = x_max;
            this.y_max = y_max;
            this.x_min = x_min;
            this.y_min = y_min;
        }

        public double distanceTo(Point2D p) {
            return Math.sqrt(this.distanceSquaredTo(p));
        }

        public double distanceSquaredTo(Point2D p) {
            double dx = 0.0, dy = 0.0;
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

        public Node(Node left, Node right, Node up, Point2D value, boolean orien) {
            this.left = left;
            this.right = right;
            this.up = up;
            this.value = value;
            this.m = m;
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

        public Node getUp() {
            return (this.up);
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
    private Node pointer = null;
    private int bound = 0;
    private Point2D target = null;
    private MaxPQ<Pair> yuru = null;

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
                            if (current.getLeft() != null) {
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

    public double Shortdistance(Node point, boolean orien) {
        double a = 0;
        Square d;
        if (point.hasUp()) {
            if (orien) {
                if (target.x() > point.getValue().x()) {
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
                if (target.y() > point.getValue().y()) {
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
            a = d.distanceTo(target);
        } else {
            a = Math.abs(target.x() - point.getValue().x());
        }

        return a;
    }

    public void FindKNN(Node pointer, boolean orien) {

        if (pointer == null) {
            return;
        }

        if (yuru.size() < bound) {
            yuru.insert(new Pair(target, pointer.getValue()));
        } else {
            if (yuru.max().distance > target.distanceTo(pointer.getValue())) {
                yuru.delMax();
                yuru.insert(new Pair(target, pointer.getValue()));
            }
        }

        Node near;
        Node far;
        if ((orien && (pointer.getValue().x() < target.x())) || (!orien && (pointer.getValue().y() < target.y()))) {
            near = pointer.getRight();
            far = pointer.getLeft();
        } else {
            near = pointer.getLeft();;
            far = pointer.getRight();
        }
        FindKNN(near, !orien);
        if (yuru.size() < bound) {
            FindKNN(far, !orien);
        } else {
            if (yuru.max().distance > Shortdistance(pointer, orien)) {
                FindKNN(far, !orien);
            }

        }

        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 

    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        yuru = new MaxPQ<Pair>(k);
        pointer = root;
        bound = k;
        target = point;
        FindKNN(pointer, true);
        for (int i = k - 1; i > -1; i--) {
            result[i] = yuru.delMax().getB();
        }

        return result;
    }

}

