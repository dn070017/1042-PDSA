public class FindNeighbors {

    public final class RectHV {

        private final double xsmal, ysmal, xlarg, ylarg;

        public RectHV(double xsmal, double ysmal, double xlarg, double ylarg) {
            if (Double.isNaN(xsmal) || Double.isNaN(xlarg)) {
                throw new IllegalArgumentException(""x cannot be NuLL"");
            }
            if (Double.isNaN(ysmal) || Double.isNaN(ylarg)) {
                throw new IllegalArgumentException(""y cannot be NuLL"");
            }
            if (xlarg < xsmal || ylarg < ysmal) {
                throw new IllegalArgumentException(""rectangle Error"");
            }
            this.xsmal = xsmal;
            this.ysmal = ysmal;
            this.xlarg = xlarg;
            this.ylarg = ylarg;
        }
        public double distanceTo(Point2D p) {
            return Math.sqrt(this.distanceSquaredTo(p));
        }
        public double distanceSquaredTo(Point2D p) {
            double deltax = 0.0, deltay = 0.0;
            if (p.x() < xsmal) {
                deltax = p.x() - xsmal;
            } else if (p.x() > xlarg) {
                deltax = p.x() - xlarg;
            }
            if (p.y() < ysmal) {
                deltay = p.y() - ysmal;
            } else if (p.y() > ylarg) {
                deltay = p.y() - ylarg;
            }
            return deltax * deltax + deltay * deltay;
        }
    }

    public class Pair implements Comparable<Pair> {

        private Point2D x, y;
        private double distance;

        public Pair(Point2D x, Point2D y) {
            this.x = x;
            this.y = y;
            this.distance = x.distanceTo(y);
        }

        public Point2D getA() {
            return this.x;
        }

        public Point2D getB() {
            return this.y;
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

        private Node leftValue, rightValue, upValue;
        private Point2D data;
        private boolean origin; // origin=ture vertical  origin=false horizon

        public Node(Node leftValue, Node rightValue, Node upValue, Point2D data, boolean origin) {
            this.leftValue = leftValue;
            this.rightValue = rightValue;
            this.upValue = upValue;
            this.data = data;
            this.origin = origin;
        }
        public Node getLeft() {return (this.leftValue);}
        public Node getRight() {return (this.rightValue);}
        public Point2D getValue() {return (this.data);}
        public Node getUp() {return (this.upValue);}
        public boolean checkUp() {return (this.upValue != null);}
        public void setLeft(Node leftValue) {this.leftValue = leftValue;}

        public void setRight(Node rightValue) {
            this.rightValue = rightValue;
        }

        public boolean getOrien() {
            return this.origin;
        }
    }
    // DO NOT MODIFY THE CONSTRUCTOR! 

//    public FindNeighbors() {
//    }
    // TODO
    public Node root = null;
    private Node pointer = null;
    private int bound = 0;
    private Point2D rowdata = null;
    private MaxPQ<Pair> resultValue = null;
    
    private boolean checkTrue(int index){
        if (index == 0){
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean CheckLarge(double a,double b){
        if (a > b){
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean CheckNull(Node a){
        if (a == null){
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void init(Point2D[] points) {
        Node current = null;
        for (int i = 0; i < points.length; i++) {
            int index = 0;
            boolean tempLayer = true;
            current = root;
            if (index == 0){
                boolean c = true;
            }            
            while (checkTrue(index)) {
                if (current == null) {
                    current = new Node(null, null, null, points[i], tempLayer);
                    root = current;
                    index = 1;
                } 
                else {
                    if (current.getOrien()) {
                        if (CheckLarge(points[i].x() , current.data.x())) {
                            if (CheckNull(current.getRight())) {
                                Node newcurrent = new Node(null, null, current, points[i], !tempLayer);
                                current.setRight(newcurrent);
                                index = 1;
                            } else {
                                current = current.getRight();
                            }
                        } else {
                            if (CheckNull(current.getLeft())) {
                                Node newcurrent = new Node(null, null, current, points[i], !tempLayer);
                                current.setLeft(newcurrent);
                                index = 1;
                            } else {
                                current = current.getLeft();
                            }
                        }
                    } 
                    else {
                        if (points[i].y() > current.data.y()) {
                            if (current.getRight() == null) {
                                Node newcurrent = new Node(null, null, current, points[i], !tempLayer);
                                current.setRight(newcurrent);
                                index = 1;
                            } else {
                                current = current.getRight();
                            }
                        } else {
                            if (CheckNull(current.getLeft())) {
                                Node newcurrent = new Node(null, null, current, points[i], !tempLayer);
                                current.setLeft(newcurrent);
                                index = 1;
                            } else {
                                current = current.getLeft();
                            }
                        }
                    }
                }
                tempLayer = !tempLayer;
            }
        }
        return;
    }

    public double DistanceSmall(Node point, boolean origin) {
        double x = 0;
        RectHV d;
        if (point.checkUp()) {
            if (origin) {
                if (CheckLarge(rowdata.x() , point.getValue().x())) {
                    if (point.getValue().y() > point.getUp().getValue().y()) {
                        d = new RectHV(0, point.getUp().getValue().y(), point.getValue().x(), 1);
                    } else {
                        d = new RectHV(0, 0, point.getValue().x(), point.getUp().getValue().y());
                    }
                } else {
                    if (point.getValue().y() > point.getUp().getValue().y()) {
                        d = new RectHV(point.getValue().x(), point.getUp().getValue().y(), 1, 1);
                    } else {
                        d = new RectHV(point.getValue().x(), 0, 1, point.getUp().getValue().y());
                    }
                }

            } else {
                if (rowdata.y() > point.getValue().y()) {
                    if (point.getValue().x() > point.getUp().getValue().x()) {
                        d = new RectHV(point.getUp().getValue().x(), 0, 1, point.getValue().y());
                    } else {
                        d = new RectHV(0, 0, point.getUp().getValue().x(), point.getValue().y());
                    }
                } else {
                    if (point.getValue().x() > point.getUp().getValue().x()) {
                        d = new RectHV(point.getUp().getValue().x(), point.getValue().y(), 1, 1);
                    } else {
                        d = new RectHV(0, point.getValue().y(), point.getUp().getValue().x(), 1);

                    }
                }
            }
            x = d.distanceTo(rowdata);
        } else {
            x = Math.abs(rowdata.x() - point.getValue().x());
        }

        return x;
    }

    public void FindAns(Node pointer, boolean origin) {

        if (pointer == null) {
            return;
        }

        if (resultValue.size() < bound) {
            resultValue.insert(new Pair(rowdata, pointer.getValue()));
        } else {
            if (resultValue.max().distance > rowdata.distanceTo(pointer.getValue())) {
                resultValue.delMax();
                resultValue.insert(new Pair(rowdata, pointer.getValue()));
            }
        }

        Node near;
        Node far;
        if ((origin && (pointer.getValue().x() < rowdata.x())) || (!origin && (pointer.getValue().y() < rowdata.y()))) {
            near = pointer.getRight();
            far = pointer.getLeft();
        } else {
            near = pointer.getLeft();;
            far = pointer.getRight();
        }
        FindAns(near, !origin);
        if (resultValue.size() < bound) {
            FindAns(far, !origin);
        } else {
            if (resultValue.max().distance > DistanceSmall(pointer, origin)) {
                FindAns(far, !origin);
            }
        }
        return;
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to CheckLarge
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int boundary) {
        Point2D[] result = new Point2D[boundary];
        resultValue = new MaxPQ<Pair>(boundary);
        pointer = root;
        bound = boundary;
        rowdata = point;
        FindAns(pointer, true);
        for (int i = boundary - 1; i > -1; i--) {
            result[i] = resultValue.delMax().getB();
        } 
        return result;
    }
}
