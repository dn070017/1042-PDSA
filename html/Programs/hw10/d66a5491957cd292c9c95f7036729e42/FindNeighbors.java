//import edu.princeton.cs.algs4.MaxPQ;
//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.RectHV;
import java.util.Arrays;
//import java.util.Random;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        add(points, 0);
    }
    
    public int add(Point2D[] points, int n){
        Point2D[] R, L;
        
        if(n%2==0){
            Arrays.sort(points, Point2D.X_ORDER);
            insertAPoint(points[points.length/2]);
            
            if((points.length/2)>0){
                L = new Point2D[points.length/2];
                for(int i=0;i<points.length/2;i++){
                    L[i] = new Point2D(points[i].x(), points[i].y());
                }
                add(L, n+1);
                
            }

            if((points.length-points.length/2-1)>0){
                R = new Point2D[points.length-points.length/2-1];
            
                for(int i=points.length/2+1;i<points.length;i++){
                    R[i-(points.length/2+1)] = new Point2D(points[i].x(), points[i].y());
                }

                add(R, n+1);
                
            }
            return 0;
        }
        else{
            Arrays.sort(points, Point2D.Y_ORDER);
            insertAPoint(points[points.length/2]);
            
            if(points.length/2>0){
                L = new Point2D[points.length/2];
                for(int i=0;i<points.length/2;i++){
                    L[i] = new Point2D(points[i].x(), points[i].y());
                }
                add(L, n+1);
                
            }
            
            if(points.length-points.length/2-1>0){
                R = new Point2D[points.length-points.length/2-1];
            
                for(int i=points.length/2+1;i<points.length;i++){
                    R[i-(points.length/2+1)] = new Point2D(points[i].x(), points[i].y());
                }

                add(R, n+1);
            }            
            return 0;
        }
        
    }

     MaxPQ<Kpoint> maxPQ=new MaxPQ<Kpoint>();
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        nearestPoints(point, k);
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


        public Node(Point2D p, Node left, Node right, boolean vertical) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
        } 
    }
    
    public void insertAPoint(Point2D p) {
        root = doInsert(root, p, true);
    }

    private Node doInsert(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(p, null, null, vertical);
        }

        if (p.equals(node.p)) {
            return node;
        }

        if (isVorH(p, node)) {
            node.left = doInsert(node.left, p, !node.vertical);
        } else {
            node.right = doInsert(node.right, p, !node.vertical);
        }

        return node;
    }

    //
    private boolean isVorH(Point2D p, Node node) {
        int cmp = node.vertical ? Point2D.X_ORDER.compare(p, node.p) : Point2D.Y_ORDER.compare(p, node.p);
        return (cmp < 0);
    }
    
   
    //
    public void nearestPoints(Point2D p, int k) {
        doNearest(root, p, k);
    }

    private void doNearest(Node node, Point2D queryPoint, int k) {
        
        if (node == null) {
            return;
        }
        Point2D nearestPointCandidate = node.p;
        
        double dis = nearestPointCandidate.distanceTo(queryPoint);

        maxPQ.insert(new Kpoint(nearestPointCandidate, dis));
        if(maxPQ.size()>k)
        {
            maxPQ.delMax();
        }
        
        
        if(maxPQ.size()==k)
        {
            Kpoint tmp= maxPQ.max();
            if(node.vertical==true)
            {
                if(node.p.x()- queryPoint.x()>0)
                {            
                    doNearest(node.left, queryPoint, k);
                    if(Math.abs(node.p.x()- queryPoint.x()) < tmp.distance)
                    {
                        doNearest(node.right, queryPoint, k);                     
                    }
                    
                }
                else
                {          
                    doNearest(node.right, queryPoint, k);
                    if(Math.abs(node.p.x()- queryPoint.x()) < tmp.distance)
                    {
                        doNearest(node.left, queryPoint, k);
                    }
                    
                    
                }
                
            }
            else if(node.vertical==false)
            {
                if(node.p.y()- queryPoint.y()>0)
                {            
                    doNearest(node.left, queryPoint, k);
                    if(Math.abs(node.p.y()- queryPoint.y()) < tmp.distance)
                    {
                        doNearest(node.right, queryPoint, k);                   
                    }
                    
                }
                else
                {               
                    doNearest(node.right, queryPoint, k);
                    if(Math.abs(node.p.y()- queryPoint.y()) < tmp.distance)
                    {
                        doNearest(node.left, queryPoint, k);             
                    }
                    
                }
                
            }
        }
        else
        {
            doNearest(node.left, queryPoint, k);        
            doNearest(node.right, queryPoint, k);
        }
        
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


