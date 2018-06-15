

import java.util.Comparator;

public class FindNeighbors {
    long start = System.nanoTime(); // requires java 1.5
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    
    private enum Direct {
        RL,UD;
        public Direct next() {
            if (this.equals(Direct.UD))return Direct.RL;
            return Direct.UD;
        }
    }

    private static class Node {
        private final Point2D p ;
        private Node lb;
        private Node rt;
        private double dist = Double.MAX_VALUE;
        public Node(Point2D p) {
            this.p = p;
        }
        
    }
    
    private Comparator<Node> distanceToOrder() {
        return new DistanceToOrder();
    }

    MaxPQ<Node> pq = new MaxPQ<>(distanceToOrder()); 
    Node root = null;
        

    

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
            for(int i = 0;i<points.length;i++) {
                
               insert(points[i]);
           }
        //input all items
        return;
    }
    public void insert(Point2D p) {
        root = put(root, p, Direct.RL);
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    //put in the tree
     private Node put(Node x, Point2D p, Direct direct) {
        if (x == null) {
            Node q = new Node(p);
            return  q;
        }

        int cmp = compare(p, x.p, direct);
        Direct nextdirect = direct.next();
        if (cmp <= 0) {
            x.lb = put(x.lb, p, nextdirect);
        } else {
            x.rt = put(x.rt, p, nextdirect);
        }
        return x;
    }

    private int compare(Point2D p, Point2D q, Direct direct) {
        if (direct == Direct.RL) {
            return Double.compare(p.x(), q.x());
        } else {
            return Double.compare(p.y(), q.y());
        }
    }

    public Point2D[] query(Point2D point, int k){
        
        Point2D[] result = new Point2D[k];       
        Stack<Point2D> temp = new Stack<Point2D>();
        int count = 0;
        int q = k;
        Node a = findNearest(root, point,
        Direct.RL,k);
            while(!pq.isEmpty()){
            count++;
            Node e = pq.delMax();
            result[k-count] = e.p;
        }
        pq = new MaxPQ<>(distanceToOrder());
        return result;

    }

    private Node findNearest(Node x, Point2D p, Direct direct,int k) {
        double distance = Math.sqrt(x.p.distanceSquaredTo(p));
        
        x.dist=distance;
       Node closet = x;
        Node first, second;
        if (direct == Direct.RL) {
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

        Direct nextdirect = direct.next();
        if (first != null) {    
            findNearest(first, p, nextdirect, k);
            if(pq.size()>=k){
                Node e =pq.delMax();
                /*
                do not do if function depends on the return numbre, it is 
                very hard to trace, instead, you sould use PQ to do the if function
                , that will be easier to trace, since PQ's scope is top
                */

                closet = e;
                if(direct == Direct.RL && Math.abs(p.x()-x.p.x())>closet.dist){
                    pq.insert(e);
                    return closet;
                }
                else if(direct == Direct.UD && Math.abs(p.y()-x.p.y())>closet.dist){
                     pq.insert(e);
                    return closet;
                }
                pq.insert(e);
            }
        } 
        if (second != null) {
            findNearest(second, p,nextdirect, k);
            }

            if(pq.size()<k){pq.insert(x);
            return x;
            }
            else{
            Node e =pq.delMax();
            if(e.dist>x.dist){
                pq.insert(x);
                closet = e;
                return closet;
            }
            else{
                pq.insert(e);
                return x;
            }
           }
        
    }
    // define the comparator of Node, it is neccessary for creating a user defined PQ 
        private class DistanceToOrder implements Comparator<Node>{
        public int compare(Node p,Node q) {
            return Double.compare(p.dist, q.dist);
        }
        
    }

}



