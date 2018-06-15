import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Comparator;

public class FindNeighbors {
    private Node root;
    private int size = 0;
    
    private static class Node {
        private Point2D point;
        private Node left;
        private Node right;
        public Node(Point2D p) {
            this.point = p;
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int size() {
        return size;
    }
 
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    public void insert(Point2D p) {
        if (isEmpty()) {
            root = new Node(p);
            size++;
            return;
        }
        root = kdtree(root, p, 1);
    }
    private Node kdtree(Node x, Point2D p, int depth){
        int axis = depth % 2;
        if (x == null) {
            size++;
            return new Node(p);
        }
        if (x.point.equals(p)) {
            return x;
        }
        int cmp = compare(p, x.point, axis);
        depth++;
        if (cmp < 0)
            x.left =  kdtree(x.left, p, depth);
        else
            x.right = kdtree(x.right, p, depth);
        return x;
    }
    private int compare(Point2D p, Point2D q, int axis) {
        if (axis == 1) 
            return Double.compare(p.x(), q.x()); 
        else
            return Double.compare(p.y(), q.y());
    }
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        for(int i = 0; i < points.length; i++){
            insert(points[i]);
        }
        return;
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        MaxPQ<Point2D> pq = new MaxPQ<Point2D>(point.DISTANCE_TO_ORDER);
        Point2D[] result = new Point2D[k];
        findNearest(root, point, root.point, Double.MAX_VALUE, 1, pq, k);
        int i = pq.size()-1;
        while(!pq.isEmpty())
            result[i--] = pq.delMax();
        return result;
    }

//    public Point2D nearest(Point2D p) {
//        return findNearest(root, p, root.point, Double.MAX_VALUE, 1);
//    }
    private Point2D findNearest(Node x, Point2D p, Point2D nearest, double nearestDistance, int depth, MaxPQ<Point2D> pq, int k) {
        int axis = depth % 2;
        if (x == null) {
            return nearest;
        }
        pq.insert(x.point);
        if(pq.size() > k)
            pq.delMax();
        Point2D closest = nearest;
        double closestDistance = nearestDistance;
        double distance = x.point.distanceTo(p);
//        if (distance < nearestDistance) {
//            closest = x.point;
//            closestDistance = distance;
//        }
        Node first, second;
        if (axis == 1) {
            if (p.x() < x.point.x()){
                first = x.left;
                second = x.right;
            }
            else{
                first = x.right;
                second = x.left;
            }
        } 
        else{
            if (p.y() < x.point.y()){
                first = x.left;
                second = x.right;
            } else {
                first = x.right;
                second = x.left;
            }
        }
        depth++;
        if(pq.size() < k){
            closest = findNearest(first, p, closest, closestDistance, depth, pq, k);
            closestDistance = closest.distanceTo(p);
            closest = findNearest(second, p, closest, closestDistance, depth, pq, k);
        }
        else{
            if(axis == 1){
                if (Math.abs((p.y()-x.point.y())) > p.distanceTo(pq.max())) {
                    closest = findNearest(first, p, closest, closestDistance, depth, pq, k);
                }
                else{
                    closest = findNearest(first, p, closest, closestDistance, depth, pq, k);
                    closestDistance = closest.distanceTo(p);
                    closest = findNearest(second, p, closest, closestDistance, depth, pq, k);
                }
            }
            else{
                if (Math.abs((p.x()-x.point.x())) > p.distanceTo(pq.max())) {
                    closest = findNearest(first, p, closest, closestDistance, depth, pq, k);
                }
                else{
                    closest = findNearest(first, p, closest, closestDistance, depth, pq, k);
                    closestDistance = closest.distanceTo(p);
                    closest = findNearest(second, p, closest, closestDistance, depth, pq, k);
                }
            }
        }
        return closest;
    }
    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String in;
            Double x,y;
            int i=0;
            Point2D [] points = new Point2D[1000];
            while((in = br.readLine()) != null){
            String[] value = in.split("" "");
            x = Double.parseDouble(value[0]);
            y = Double.parseDouble(value[1]);
            points[i++] = new Point2D(x,y);
            }
            FindNeighbors a = new FindNeighbors();
            Point2D test = new Point2D(x = 0.531440, y = 0.616918);
            a.init(points);
            Point2D[] query = a.query(test, 10);
            for(int j = 0; j < query.length; j++)
                System.out.printf(""%.6f %.6f\n"", query[j].x(), query[j].y());
        }
    }

}

