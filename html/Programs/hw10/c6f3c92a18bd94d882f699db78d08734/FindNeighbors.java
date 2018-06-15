import java.io.BufferedReader;
import java.io.FileReader;
/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author jerry
 */
public class FindNeighbors {

    private class RectHV {

        private final double xmin, ymin;   // minimum x- and y-coordinates
        private final double xmax, ymax;   // maximum x- and y-coordinates

        public RectHV(double xmin, double ymin, double xmax, double ymax) {
            if (Double.isNaN(xmin) || Double.isNaN(xmax)) {
                throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
            }
            if (Double.isNaN(ymin) || Double.isNaN(ymax)) {
                throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
            }
            if (xmax < xmin || ymax < ymin) {
                this.xmin = xmax;
                this.xmax = xmin;
                this.ymin = ymax;
                this.ymax = ymin;
            }
            else{
            this.xmin = xmin;
            this.ymin = ymin;
            this.xmax = xmax;
            this.ymax = ymax;}
        }

        public double xmin() {
            return xmin;
        }

        public double xmax() {
            return xmax;
        }

        public double ymin() {
            return ymin;
        }

        public double ymax() {
            return ymax;
        }

        public double width() {
            return xmax - xmin;
        }

        public double height() {
            return ymax - ymin;
        }

        public boolean intersects(RectHV that) {
            return this.xmax >= that.xmin && this.ymax >= that.ymin
                    && that.xmax >= this.xmin && that.ymax >= this.ymin;
        }

        public boolean contains(Point2D p) {
            return (p.x() >= xmin) && (p.x() <= xmax)
                    && (p.y() >= ymin) && (p.y() <= ymax);
        }

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

        public double distanceTo(Point2D p) {
            return Math.sqrt(this.distanceSquaredTo(p));
        }

    }

    private class Node {

        private Point2D p;
        private Node left, right;
        private RectHV rect;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    private Node root;

    public FindNeighbors() {
    }

    public boolean isEmpty() {
        return root == null;
    }


    public void init(Point2D[] points){
        for(int i = 0; i < points.length;i++){
            this.insert(points[i]);
        }
    }

    public void insert(Point2D p) {
        if (isEmpty() == true) {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
            return;
        }
        root = put(root, p, ""Leftright"");
    }

    private Node put(Node x, Point2D p, String orientation) {
        if (x == null) {
            x = new Node(p);
            return x;
        }        
        String nextorientation;
        if (orientation.equalsIgnoreCase(""Leftright"")) {
            nextorientation = ""Updown"";
        } else {
            nextorientation = ""Leftright"";
        }
        
        int cmp = compare(p, x.p, orientation);
        if (cmp < 0) {
            x.left = put(x.left, p, nextorientation);
            if (x.left.rect == null) {
                if (orientation.equalsIgnoreCase(""Leftright"")) {
                    x.left.rect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x(), x.rect.ymax());
                } else {
                    x.left.rect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y());
                }
            }
        } else {
            x.right = put(x.right, p, nextorientation);
            if (x.right.rect == null) {
                if (orientation.equalsIgnoreCase(""Leftright"")) {
                    x.right.rect = new RectHV(x.p.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax());
                } else {
                    x.right.rect = new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.rect.ymax());
                }
            }
        }
        return x;
    }

    private int compare(Point2D p, Point2D q, String orientation) {
        if (orientation.equalsIgnoreCase(""Leftright"")) {
            if(p.x() < q.x()) return -1;
            else if (p.x() > q.x()) return 1;
            else return 0;
        } else {
            if(p.y() < q.y()) return -1;
            else if (p.y() > q.y()) return 1;
            else return 0;           
        }
    }
    
    
    public static class Event implements Comparable<Event> {

        public double distance;         // time that event is scheduled to occur
        public Point2D a, b;       // particles involved in event, possibly null

        // create a new event to occur at time t involving a and b
        public Event(double d, Point2D a, Point2D b) {
            this.distance = d;
            this.a = a;
            this.b = b;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return +1;
            } else {
                return 0;
            }
        }
    } //use priority queue to sort the distance
    
    
    public Point2D[] query(Point2D p,int k) {
        if (isEmpty()) {
            return null;
        }
        MaxPQ<Event> pq = new MaxPQ<Event>(k);
        findNearest(root, p, k, pq);
        Point2D[] result = new Point2D[k];
        for(int i = (k-1); i >= 0; i--){
        result[i] = pq.delMax().b;
        }
        return result;
    }

    
    
    private void findNearest(Node x, Point2D p, int k, MaxPQ<Event> pq) {
        if (x == null) {
            return;
        }
        
        pq.insert(new Event(p.distanceSquaredTo(x.p),p,x.p));
        while(pq.size() > k) pq.delMax();
        Node left,right;
        
        double distance_left = Double.MAX_VALUE;
        double distance_right = Double.MAX_VALUE;
        if(x.left != null) {distance_left =  x.left.rect.distanceSquaredTo(p);}
        if(x.right != null) {distance_right =  x.right.rect.distanceSquaredTo(p);}
        
        if(distance_left < distance_right){
        left = x.left;
        right = x.right;
        }
        else{
        double tmp = distance_left;
        distance_left = distance_right;
        distance_right = tmp;
        left = x.right;
        right = x.left;
        }
        
        if (left != null && distance_left < pq.max().distance ) {
            findNearest(left, p, k, pq);
        }
        
        if (right != null && distance_right < pq.max().distance) {
            findNearest(right, p, k, pq);
        }
    }
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            Point2D[] test = new Point2D[100];
            int i = 0;
                for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split(""\\s+"");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                test[i++] = new Point2D(x, y);
            }
            FindNeighbors test2 = new FindNeighbors();
            test2.init(test);
            Point2D point = new Point2D(0.113403943134472,0.162591286469251);
            Point2D[] result = test2.query(test[99], 10);
            for(int j = 0; j < 10; j++){
            System.out.print(result[j]);
            System.out.println();
            }
        }
    }
}

