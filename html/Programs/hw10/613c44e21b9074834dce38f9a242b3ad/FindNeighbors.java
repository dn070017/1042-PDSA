
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FindNeighbors {
    long start = System.nanoTime(); // requires java 1.5
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    
    private enum Orientation {
        LeftRight, AboveBelow;
        public Orientation next() {
            if (this.equals(Orientation.AboveBelow))return Orientation.LeftRight;
            return Orientation.AboveBelow;
        }
    }

    private static class Node {
        //Comparator<Node> X_ORDER = new XOrder();
        private final Point2D p ;
        private Node lb;
        /*
         * the right/top subtree
         */
        private Node rt;
        /*
         * to record that is already finded
         */
        boolean finded=false;
        
        private double dist = Double.MAX_VALUE;
        
        public Node(Point2D p) {
            this.p = p;
        }
        
    }
    
    private Comparator<Node> distanceToOrder() {
        return new DistanceToOrder();
    }

    MaxPQ<Node> pq = new MaxPQ<>(distanceToOrder()); 
    //public DistanceToOrder();
    Node root = null;
        

    

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
            for(int i = 0;i<points.length;i++) {
                
               insert(points[i]);
           }
        //input all items
        //put(points)
        return;
    }
    //Node root1 =null;
    public void insert(Point2D p) {
        /*if (root==null) {
            root = new Node(p);
            root1 = root;
            //root.rect = new RectHV(0, 0, 1, 1);
            //size++;
            //return;
        }*/
        root = put(root, p, Orientation.LeftRight);
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    //put in the tree
     private Node put(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            //size++;
            Node q = new Node(p);
             //StdOut.println(""Node(p)""+q.p);
            return  q;
        }
        //if (x.p.equals(p)) {
            //return x;
        //}
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp <= 0) {
            x.lb = put(x.lb, p, nextOrientation);
            /*if (x.lb == null) {
                if (orientation == Orientation.LeftRight) {
                    x.lb = new Node(p);
                } 
            }*/
        } else {
            x.rt = put(x.rt, p, nextOrientation);
            /*if (x.rt == null) {
                if (orientation == Orientation.LeftRight) {
                    x.rt= new Node(p);
                } 
            }*/
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

    public Point2D[] query(Point2D point, int k){
        
        Point2D[] result = new Point2D[k];
        //Point2D[] result2 = new Point2D[k];
        
        Stack<Point2D> temp = new Stack<Point2D>();
        int count = 0;
        int count1 = 0;
        int q = k;
        Node a = findNearest(root, point,
        Orientation.LeftRight,k);
            while(!pq.isEmpty()){
            count++;
            //StdOut.println(pq.size());
            Node e = pq.delMax();
            result[k-count] = e.p;
            //temp.push(e.p);
        }
        /*while(count1<k){
            result[count1] =temp.pop(); 
            count1++;
        }*/
        pq = new MaxPQ<>(distanceToOrder());
        return result;

    }

    private Node findNearest(Node x, Point2D p, Orientation orientation,int k) {
        double distance = Math.sqrt(x.p.distanceSquaredTo(p));
        
        x.dist=distance;
       Node closet = x;
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

        //Node flow=root1;
        Orientation nextOrientation = orientation.next();
        if (first != null) {    
            closet = findNearest(first, p, nextOrientation, k);
            if(pq.size()>=k){
                //StdOut.println(""eeeeeeeeeeeeee ""+x.p);
                if(orientation == Orientation.LeftRight && Math.abs(p.x()-x.p.x())>closet.dist){
                    //StdOut.println(""@@@``````````@@@@@@@@@ ""+x.p+""dist""+x.dist);
                    //StdOut.println("" Math.abs(p.x()-x.p.x() ""+Math.abs(p.x()-x.p.x()));
                    //StdOut.println(""eeeeeee```````````eeeeeee ""+closet.p+""dist""+closet.dist);
                   // StdOut.println(""first ""+closet.p);
                    return closet;
                }
                else if(orientation == Orientation.AboveBelow && Math.abs(p.y()-x.p.y())>closet.dist){
                     //StdOut.println(""!!!!!!!!!!!! ""+x.p);
                     //StdOut.println(""eeeeeeeeeeeeee ""+closet.p);
                    return closet;
                }
            }
            //closestDistance = closest.p.distanceSquaredTo(p);
        } 
        if (second != null) {
            closet=findNearest(second, p, 
                    nextOrientation, k);
            }

            if(pq.size()<k){pq.insert(x);
            //StdOut.println(""insert x.p ""+x.p);
            return x;
            }
            else{
            Node e =pq.delMax();
            if(e.dist>x.dist){
                pq.insert(x);
                //StdOut.println(""@@@@@@@@@@@@ ""+x.p+""dist""+x.dist);
                //StdOut.println(""eeeeeeeeeeeee ""+e.p+""dist""+e.dist);
                return x;
            }
            else{
                pq.insert(e);
                return e;
            }
           }
        //return x;
    }
        private class DistanceToOrder implements Comparator<Node>{
      
        public int compare(Node p,Node q) {
            return Double.compare(p.dist, q.dist);
        }
        
    }

}



