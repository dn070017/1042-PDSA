//import edu.princeton.cs.algs4.MaxPQ;
//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.RectHV;
//import java.util.Arrays;
import java.util.Random;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        Random ran = new Random();
        Point2D tmp;
        for(int i=0;i<points.length;i++)
        {
            int rnd=ran.nextInt(points.length);
            tmp=points[i];
            points[i]=points[rnd];
            points[rnd]=tmp;
        }
        for(int i=0;i<points.length;i++)
        {
            insert(points[i]);
        }
        
    }

     MaxPQ<Kpoint> maxPQ=new MaxPQ<Kpoint>();
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        Point2D ans=nearest(point, k);
        Kpoint[] kpoints=new Kpoint[k];
        
        for(int i=k-1;i>=0;i--)
        {
            kpoints[i]=maxPQ.delMax();
            result[i]=kpoints[i].kpoint;
        }
        
        return result;
    }
    
    //
    private Node root;
    private int size=0;
    private static class Node {
      
        private Point2D p;

        private Node left;

        private Node right;

        private boolean vertical = true;
        
        private boolean passed = false;

        public Node(Point2D p, Node left, Node right, boolean vertical, boolean passed) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
            this.passed=passed;
        } 
    }
    
    public void insert(Point2D p) {
        root = doInsert(root, p, true, false);
    }

    private Node doInsert(Node node, Point2D p, boolean vertical, boolean passed) {
        if (node == null) {
            size++;
            return new Node(p, null, null, vertical, passed);
        }

        if (p.equals(node.p)) {
            return node;
        }

        if (isSmallerThanPointInNode(p, node)) {
            node.left = doInsert(node.left, p, !node.vertical, false);
        } else {
            node.right = doInsert(node.right, p, !node.vertical, false);
        }

        return node;
    }

    //
    private boolean isSmallerThanPointInNode(Point2D p, Node node) {
        int cmp = node.vertical ? Point2D.X_ORDER.compare(p, node.p) : Point2D.Y_ORDER.compare(p, node.p);
        return (cmp < 0);
    }
    
   
    //
    public Point2D nearest(Point2D p, int k) {
        return doNearest(root, p, null, k);
    }

    private Point2D doNearest(Node node, Point2D queryPoint, Point2D nearestPoint, int k) {
        
        if (node == null) {
            return nearestPoint;
        }

        Point2D nearestPointCandidate = nearestPoint;
        double nearestDist = (nearestPointCandidate != null)
                ? queryPoint.distanceSquaredTo(nearestPointCandidate)
                : Double.MAX_VALUE;
        
        double dis=node.p.distanceSquaredTo(queryPoint);
        
        if(node.passed==false)
        {
            maxPQ.insert(new Kpoint(node.p, dis));
            if(maxPQ.size()>k)
            {
                maxPQ.delMax();
            }
        }
        //if(nearestPointCandidate!=null)
        //{
            node.passed=true;
        //}
        
        if (nearestDist > node.p.distanceSquaredTo(queryPoint))
        {
            nearestPointCandidate = node.p;
        }
        
        nearestPointCandidate = doNearest(node.left, queryPoint, nearestPointCandidate, k);
        nearestPointCandidate = doNearest(node.right, queryPoint, nearestPointCandidate, k);
        
        return nearestPointCandidate;
    }
    
    private class Kpoint implements Comparable<Kpoint>{
      
        private Point2D kpoint;

        private double distance;

        public Kpoint(Point2D kpoint,double distance) {
            this.kpoint = kpoint; 
            this.distance=distance;
        } 
        
        public int compareTo(Kpoint that)
        {
            if(this.distance>that.distance)
            {
                return 1;
            }
            else if(this.distance<that.distance)
            {
                return -1;
            }
            else return 0;            
        }
    }
    
    



}


