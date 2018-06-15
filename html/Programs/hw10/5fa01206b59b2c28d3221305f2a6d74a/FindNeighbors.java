import java.util.Comparator;

public class FindNeighbors {
    private Node root;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    
private  class Node implements Comparable<Node>{
    private double key;
    private double val;
    private Node left,right;
    private int way;
    private double dist;
    
    public Node(double key, double val, int way){
        this.key = key;
        this.val = val;
        this.way = way;
    }
    public int compareTo(Node that){
        if(this.dist>that.dist) return 1;
        else if(this.dist<that.dist) return -1;
        return 0;
    }
}
    public void put(double key, double val, int way){
        root = put(root, key, val, way);
    }
    public Node put(Node x, double key, double val, int way){
        if(x==null){
          
            return new Node(key, val, way);
        }
        
        if(way==0) way = 1;
        else way = 0;
        
        if(key < x.key)  x.left = put(x.left, val, key, way);
        else if(key > x.key)  x.right = put(x.right, val, key, way);
        
        return x;
    }
   
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        
        int len = points.length;
        Node first = new Node(points[0].x(), points[0].y(), 0);
        root = first;
        for(int i = 1; i<len;i++){
            put(points[i].x(), points[i].y() ,0);
        } 
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D poiresultnt, int k){
        Point2D[] result = new Point2D[k];                       
        MaxPQ<Node> pq = new MaxPQ<>();        
        this.find(pq, root, poiresultnt, k);
                       
        for(int i = k-1;i>=0;i--){
           Node point = pq.delMax();
            if(point.way == 0) result[i] = new Point2D(point.key, point.val);
            else if(point.way==1) result[i] = new Point2D(point.val, point.key);
            
        }        
        return result;
    }
    public void find(MaxPQ<Node> pq, Node x, Point2D qu, int k){
        
        if(x == null) return;
        
        double dis = 0 ;
        int cmp = 0;
        
        if(x.way == 0){
            double dx,dy;
            dx = x.key - qu.x();
            dy = x.val - qu.y();
            dis = dx*dx + dy*dy;
            
            if(qu.x()>x.key) cmp = 1;
            else cmp = -1;
        }
        else if(x.way == 1){
            double dx,dy;
            dx = x.val - qu.x();
            dy = x.key - qu.y();
            dis = dx*dx + dy*dy;
            
            if(qu.y()>x.key) cmp = 1;
            else cmp = -1;
        }
        x.dist = dis;
        
        if(pq.size()<k) pq.insert(x);
        else{
           Node maxdis = pq.max();
           if(maxdis.dist>dis){
               maxdis = pq.delMax();
               pq.insert(x);
          }
        }    
        if(cmp<0) {
            find(pq, x.left, qu, k); 
            Node maxdis = pq.max();          
            if(pq.size()<k) find(pq, x.right, qu, k);
            
           
            else if(x.way == 0){
                double shortdx = qu.x() - x.key;
                if(maxdis.dist>(shortdx*shortdx)) find(pq, x.right, qu, k);                
            }
            else if(x.way==1){
             double shortdy = qu.y() - x.key;
                if(maxdis.dist>(shortdy*shortdy)) find(pq, x.right, qu, k); 
            }        
        }
        else if(cmp>0){
            find(pq, x.right, qu, k);
            Node maxdis = pq.max();
            if(pq.size()<k) find(pq, x.left, qu, k);
                         
            else if(x.way == 0){
                double shortdx = qu.x() - x.key;
                if(maxdis.dist>(shortdx*shortdx)) find(pq, x.left, qu, k);                
            }
            else if(x.way==1){
             double shortdy = qu.y() - x.key;
                if(maxdis.dist>(shortdy*shortdy)) find(pq, x.left, qu, k); 
            }        
        }
       
    }

}


