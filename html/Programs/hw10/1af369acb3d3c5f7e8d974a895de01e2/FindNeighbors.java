
public class FindNeighbors {

    public static MinPQ<Double> a = new MinPQ();
    public static BST<Double, Point2D> st = new BST<Double, Point2D>();
    public static FindNeighbors kdtree;

    public final class RectHV {

        private final double xmin, ymin;   // minimum x- and y-coordinates
        private final double xmax, ymax;   // maximum x- and y-coordinates

        /**
         * Initializes a new rectangle [<em>xmin</em>, <em>xmax</em>] x
.
         *
         * @param xmin the <em>x</em>-coordinate of the lower-left endpoint
         * @param xmax the <em>x</em>-coordinate of the upper-right endpoint
         * @param ymin the <em>y</em>-coordinate of the lower-left endpoint
         * @param ymax the <em>y</em>-coordinate of the upper-right endpoint
         * @throws IllegalArgumentException if any of <tt>xmin</tt>,
         * <tt>xmax</tt>, <tt>ymin</tt>, or <tt>ymax</tt>
.
         * @throws IllegalArgumentException if <tt>xmax</tt> &lt;
.
         */
        public RectHV(double xmin, double ymin, double xmax, double ymax) {
            if (Double.isNaN(xmin) || Double.isNaN(xmax)) {
                throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
            }
            if (Double.isNaN(ymin) || Double.isNaN(ymax)) {
                throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
            }
            if (xmax < xmin || ymax < ymin) {
                throw new IllegalArgumentException(""Invalid rectangle"");
            }
            this.xmin = xmin;
            this.ymin = ymin;
            this.xmax = xmax;
            this.ymax = ymax;
        }

        /**
         * Returns the minimum <em>x</em>-coordinate of any point in this
.
         *
         * @return the minimum <em>x</em>-coordinate of any point in this
         * rectangle
         */
        public double xmin() {
            return xmin;
        }

        /**
         * Returns the maximum <em>x</em>-coordinate of any point in this
.
         *
         * @return the maximum <em>x</em>-coordinate of any point in this
         * rectangle
         */
        public double xmax() {
            return xmax;
        }

        /**
         * Returns the minimum <em>y</em>-coordinate of any point in this
.
         *
         * @return the minimum <em>y</em>-coordinate of any point in this
         * rectangle
         */
        public double ymin() {
            return ymin;
        }

        /**
         * Returns the maximum <em>y</em>-coordinate of any point in this
.
         *
         * @return the maximum <em>y</em>-coordinate of any point in this
         * rectangle
         */
        public double ymax() {
            return ymax;
        }

        /**
.
         *
         * @return the width of this rectangle <tt>xmax - xmin</tt>
         */
        public double width() {
            return xmax - xmin;
        }

        /**
.
         *
         * @return the height of this rectangle <tt>ymax - ymin</tt>
         */
        public double height() {
            return ymax - ymin;
        }

        /**
.
         *
         * @param that the other rectangle
         * @return <tt>true</tt> if this rectangle intersect the argument
         * rectagnle at one or more points, including on the boundary
         */
        public boolean intersects(RectHV that) {
            return this.xmax >= that.xmin && this.ymax >= that.ymin
                    && that.xmax >= this.xmin && that.ymax >= this.ymin;
        }

        /**
.
         *
         * @param p the point
         * @return <tt>true</tt> if this rectangle contain the point <tt>p</tt>,
         * possibly at the boundary; <tt>false</tt> otherwise
         */
        public boolean contains(Point2D p) {
            return (p.x() >= xmin) && (p.x() <= xmax)
                    && (p.y() >= ymin) && (p.y() <= ymax);
        }

        /**
         * Returns the Euclidean distance between this rectangle and the point
.
         *
         * @param p the point
         * @return the Euclidean distance between the point <tt>p</tt> and the
         * closest point on this rectangle; 0 if the point is contained in this
         * rectangle
         */
        public double distanceTo(Point2D p) {
            return Math.sqrt(this.distanceSquaredTo(p));
        }

        /**
         * Returns the square of the Euclidean distance between this rectangle
.
         *
         * @param p the point
         * @return the square of the Euclidean distance between the point
         * <tt>p</tt> and the closest point on this rectangle; 0 if the point is
         * contained in this rectangle
         */
        public double distanceSquaredTo(Point2D p) {
            double dx = 0.0, dy = 0.0;
            if (p.x() < xmin) {
                dx = p.x() - xmin;
            } else if (p.x() > xmax) {
                dx = p.x() - xmax;
            }
            if (p.y() < ymin) {
                dy = p.y() - ymin;
            } else if (p.y() > ymax) {
                dy = p.y() - ymax;
            }
            return dx * dx + dy * dy;
        }

        /**
.
         *
         * @param other the other rectangle
         * @return <tt>true</tt> if this rectangle equals <tt>other</tt>;
         * <tt>false</tt> otherwise
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other == null) {
                return false;
            }
            if (other.getClass() != this.getClass()) {
                return false;
            }
            RectHV that = (RectHV) other;
            if (this.xmin != that.xmin) {
                return false;
            }
            if (this.ymin != that.ymin) {
                return false;
            }
            if (this.xmax != that.xmax) {
                return false;
            }
            if (this.ymax != that.ymax) {
                return false;
            }
            return true;
        }

        /**
.
         *
         * @return an integer hash code for this rectangle
         */
        @Override
        public int hashCode() {
            int hash1 = ((Double) xmin).hashCode();
            int hash2 = ((Double) ymin).hashCode();
            int hash3 = ((Double) xmax).hashCode();
            int hash4 = ((Double) ymax).hashCode();
            return 31 * (31 * (31 * hash1 + hash2) + hash3) + hash4;
        }

        /**
.
         *
         * @return a string representation of this rectangle, using the format
         * <tt>[xmin, xmax] x [ymin, ymax]</tt>
         */
        @Override
        public String toString() {
            return ""["" + xmin + "", "" + xmax + ""] x ["" + ymin + "", "" + ymax + ""]"";
        }

        /**
.
         */
        public void draw() {
            StdDraw.line(xmin, ymin, xmax, ymin);
            StdDraw.line(xmax, ymin, xmax, ymax);
            StdDraw.line(xmax, ymax, xmin, ymax);
            StdDraw.line(xmin, ymax, xmin, ymin);
        }

    }
    private int size = 0;

    private enum Orientation {

        LeftRight, AboveBelow;

        public Orientation next() {
            if (this.equals(Orientation.AboveBelow)) {
                return Orientation.LeftRight;
            }

            return Orientation.AboveBelow;
        }
    }

    private static class Node {
        /*
         * the point
         */

        private Point2D p;
        /*
         * the axis-aligned rectangle corresponding to this/ node
         */
        private RectHV rect;
        /*
         * the left/bottom subtree
         */
        private Node lb;
        /*
         * the right/top subtree
         */
        private Node rt;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    private Node root;

    /*
     * construct an empty set of points
     */
    public FindNeighbors() {
    }

    /*
     * is the set empty?
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
     * number of points in the set
     */
    public int size() {
        return size;
    }

    /*
     * add the point p to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (isEmpty()) {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
            size++;
            return;
        }
        root = put(root, p, Orientation.LeftRight);
    }

    private Node put(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            size++;
            return new Node(p);
        }
        if (x.p.equals(p)) {
            return x;
        }
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp < 0) {
            x.lb = put(x.lb, p, nextOrientation);
            if (x.lb.rect == null) {
                if (orientation == Orientation.LeftRight) {
                    x.lb.rect = new RectHV(x.rect.xmin(), x.rect.ymin(),
                            x.p.x(), x.rect.ymax());
                } else {
                    x.lb.rect = new RectHV(x.rect.xmin(), x.rect.ymin(),
                            x.rect.xmax(), x.p.y());
                }
            }
        } else {
            x.rt = put(x.rt, p, nextOrientation);
            if (x.rt.rect == null) {
                if (orientation == Orientation.LeftRight) {
                    x.rt.rect = new RectHV(x.p.x(), x.rect.ymin(),
                            x.rect.xmax(), x.rect.ymax());
                } else {
                    x.rt.rect = new RectHV(x.rect.xmin(), x.p.y(),
                            x.rect.xmax(), x.rect.ymax());
                }
            }
        }
        return x;
    }

    private int compare(Point2D p, Point2D q, Orientation orientation) {
        if (orientation == Orientation.LeftRight) {
            return Double.compare(p.x(), q.x());
        } else {
            return Double.compare(p.y(), q.y());
        }
    }

    /*
     * does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return contains(root, p, Orientation.LeftRight);
    }

    private boolean contains(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            return false;
        }
        if (x.p.equals(p)) {
            return true;
        }
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp < 0) {
            return contains(x.lb, p, nextOrientation);
        } else {
            return contains(x.rt, p, nextOrientation);
        }
    }

    /*
     * draw all of the points to standard draw
     */
    public void draw() {
        draw(root, Orientation.LeftRight);
    }

    private void draw(Node x, Orientation orientation) {
        if (x == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();
        if (orientation == Orientation.LeftRight) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        Orientation next = orientation.next();
        draw(x.lb, next);
        draw(x.rt, next);
    }

    /*
     * all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();

        if (!isEmpty()) {
            findPoints(queue, rect, root);
        }
        return queue;
    }

    private void findPoints(Queue<Point2D> queue, RectHV rect, Node x) {
        if (!rect.intersects(x.rect)) {
            return;
        }
        if (rect.contains(x.p)) {
            queue.enqueue(x.p);
        }
        if (x.lb != null) {
            findPoints(queue, rect, x.lb);
        }
        if (x.rt != null) {
            findPoints(queue, rect, x.rt);
        }
    }

    /*
     * a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return findNearest(root, p, root.p, Double.MAX_VALUE, Orientation.LeftRight);
    }

    private Point2D findNearest(Node x, Point2D p, Point2D nearest,
            double nearestDistance, Orientation orientation) {
        if (x == null) {
            return nearest;
        }
        Point2D closest = nearest;
        double closestDistance = nearestDistance;
        double distance = x.p.distanceSquaredTo(p);
        st.put(distance, x.p);
        a.insert(distance);
        //System.out.printf(""Point: x=%.10f y=%.10f  Distance=%f\n"", x.p.x(), x.p.y(), distance);
        if (distance < nearestDistance) {
            closest = x.p;
            closestDistance = distance;
        }
        Node first, second;
        if (orientation == Orientation.LeftRight) {
            if (p.x() < x.p.x()) {
                first = x.lb;
                second = x.rt;
            } else {
                first = x.rt;
                second = x.lb;
            }
        } else {
            if (p.y() < x.p.y()) {
                first = x.lb;
                second = x.rt;
            } else {
                first = x.rt;
                second = x.lb;
            }
        }
        Orientation nextOrientation = orientation.next();
        if (first != null && first.rect.distanceSquaredTo(p) < closestDistance) {
            closest = findNearest(first, p, closest, closestDistance,
                    nextOrientation);
            closestDistance = closest.distanceSquaredTo(p);
        }
        if (second != null
                && second.rect.distanceSquaredTo(p) < closestDistance) {
            closest = findNearest(second, p, closest, closestDistance,
                    nextOrientation);
        }

        return closest;
    }

    public void init(Point2D[] points) {
//        kdtree = new KdTree();
        for (int i = 0; i < points.length; i++) {
            kdtree.insert(points[i]);
        }
    }

    public Point2D[] query(Point2D point, int k) {
//            public void query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        Point2D last = kdtree.nearest(point);
        for (int i = 0; i < k; i++) {
            double test = 0;
            test = a.delMin();
            result[i]= st.get(test);
            //System.out.printf(""%.10f %.10f\n"", st.get(test).x(), st.get(test).y());
        }
        return result;
        // the points should be sorted accordingly to their distances to the query, from small to large
    }

    public static void main(String[] args) {
//        kdtree = new FindNeighbors();
//        Point2D[] test = new Point2D[20];
//        test[0] = new Point2D(0.3833339428, 0.1459115606);
//        test[1] = new Point2D(0.3426077152, 0.7218207763);
//        test[2] = new Point2D(0.3406783533, 0.3164794008);
//        test[3] = new Point2D(0.5034046695, 0.7964833541);
//        test[4] = new Point2D(0.5969555271, 0.1587087880);
//        test[5] = new Point2D(0.2126349801, 0.4842532332);
//        test[6] = new Point2D(0.5299519862, 0.4636946673);
//        test[7] = new Point2D(0.1171932327, 0.6117403964);
//        test[8] = new Point2D(0.8730132530, 0.5332436770);
//        test[9] = new Point2D(0.6247044587, 0.0213209046);
//        test[10] = new Point2D(0.9218660505, 0.7907778275);
//        test[11] = new Point2D(0.4253832308, 0.3123947194);
//        test[12] = new Point2D(0.4526798481, 0.8498759517);
//        test[13] = new Point2D(0.5245860322, 0.0488684727);
//        test[14] = new Point2D(0.3255085068, 0.9310410020);
//        test[15] = new Point2D(0.7281417757, 0.7145077083);
//        test[16] = new Point2D(0.5720377234, 0.4108029499);
//        test[17] = new Point2D(0.0447830281, 0.2155560961);
//        test[18] = new Point2D(0.8674125381, 0.2314056188);
//        test[19] = new Point2D(0.4914579564, 0.8431045366);
//        kdtree.init(test);
//        kdtree.query(new Point2D(0.1354339200, 0.7019446863), 3);
        //Point2D last = kdtree.nearest(new Point2D(0.1354339200, 0.7019446863));
//        System.out.printf(""\n%f"",a.delMin());
//        System.out.printf(""\n%f"",a.delMin());
//        System.out.printf(""\n%f"",a.delMin());
//        for (int i = 0; i < 3; i++) {
//            double test = 0;
//            test = a.delMin();
//            System.out.printf(""%.10f %.10f\n"", st.get(test).x(), st.get(test).y());
//        }
//        System.out.printf(""%.10f %.10f\n"",st.get(a.delMin()).x(),st.get(a.delMin()).y());
//        System.out.printf(""%.10f %.10f\n"",st.get(a.delMin()).x(),st.get(a.delMin()).y());
//        System.out.printf(""%.10f %.10f\n"",st.get(a.delMin()).x(),st.get(a.delMin()).y());
        //System.out.printf(""%.10f , %.10f "",last.x(),last.y());
//        StdOut.println(""\nsize: "" + kdtree.size());
    }
}

