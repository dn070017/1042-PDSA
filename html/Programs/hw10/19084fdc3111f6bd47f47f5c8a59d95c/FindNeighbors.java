
import java.util.Arrays;
import java.util.Comparator;


public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    private Node root;
    
    private class Node{
        private Node left, right;
        public Point2D p;
        private boolean vertical = true;
        
        public Node(Point2D p, Node left, Node right, boolean vertical){
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
        }
    }
    
    public void insert(Point2D p){
        root = doInsert(root,p,true);
    }
    
    
    private Node doInsert(Node node, Point2D p, boolean vertical){
        if(node == null){
            return new Node(p, null, null, vertical);
        }
        
        if(smaller(p,node)){
            node.left = doInsert(node.left, p, !node.vertical);
        }
        else
            node.right = doInsert(node.right, p, !node.vertical);
        
        return node;
    }
    
    private boolean smaller(Point2D p, Node node){
        boolean direction = node.vertical;
        int temp;
        
        if(direction){
            temp = Point2D.X_ORDER.compare(p, node.p);
        }
        else 
            temp = Point2D.Y_ORDER.compare(p, node.p);
        
        return temp < 0;
    }
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
//        StdDraw.setCanvasSize(500, 500);
//        StdDraw.clear(StdDraw.WHITE);
//        StdDraw.setPenColor(StdDraw.RED);
//        
//        System.out.print(points.length);
//        
        Point2D[] input = new Point2D[points.length];
        for (int i = 0; i < points.length; i++) {
            input[i] = points[i];
//            StdDraw.filledCircle(input[i].x(), input[i].y(), 0.01);
//            StdDraw.text(input[i].x(), input[i].y() + 0.03, Integer.toString(i));
            
            insert(input[i]);
            
        }
        
//        System.out.println(root.p.x());
//        System.out.println(root.left.p.x());
//        System.out.println(root.right.p.x());
//        System.out.println(root.left.left.p.x());
//        System.out.println(root.right.p.x());
//        System.out.println(root.right.left.p.x());

    }
    
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        int count = k;
        MaxPQ<Point2D> pq = new MaxPQ<Point2D>(count);
        
               
        Point2D[] result = new Point2D[k];
        result = nearest(point, k);
        return result;
    }
    
    public Point2D[] nearest(Point2D querypoint, int count){
        MaxPQ<Point2D> pq = new MaxPQ<Point2D>(count);
        if(pq.size() < count){
            pq.insert(doNearest(root, querypoint, root.vertical, count));
        }
        
        Point2D[] ans = new Point2D[count];
        
        for(int i = 0 ; i < count ; i++){
            ans[i] = pq.delMax();
        }
        
        
        return ans;
        
    }
    
    private Point2D doNearest(Node node, Point2D querypoint, boolean vertical, int count){
        if(node == null){
            return node.p;
        }
        Point2D nearest_now = node.p;
        
        double dis;
        
        if(nearest_now != null){
            dis = querypoint.distanceTo(nearest_now);
        }
        else
            dis = Double.MAX_VALUE;
       
        if(vertical){
            int temp_x = Point2D.X_ORDER.compare(querypoint, node.p);
            if(temp_x < 0)
                nearest_now = doNearest(node.left, querypoint, node.left.vertical, count);
            else
                nearest_now = doNearest(node.right, querypoint, node.left.vertical, count);               
        }
        else{
            int temp_y = Point2D.Y_ORDER.compare(querypoint, node.p);
            if(temp_y < 0)
                nearest_now = doNearest(node.left, querypoint, node.left.vertical, count);
            else
                nearest_now = doNearest(node.right, querypoint, node.left.vertical, count);       
        }
        
        return nearest_now;
    }
    
    
    
//    public static void main(String[] args) {
//        FindNeighbors input = new FindNeighbors();
//        Point2D[] test = new Point2D[5];
//        
//        test[0] = new Point2D(0.3833339428,0.1459115606);
//        test[1] = new Point2D(0.3426077152,0.7218207763);
//        test[2] = new Point2D(0.3406783533,0.3164794008);
//        test[3] = new Point2D(0.5034046695,0.7964833541);
//        test[4] = new Point2D(0.5969555271,0.1587087880);
//        
//        input.init(test);
//    }

}

