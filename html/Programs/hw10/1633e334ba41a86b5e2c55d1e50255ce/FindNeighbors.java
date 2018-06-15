
public class FindNeighbors {
    private Node root;
    public FindNeighbors(){}
    
private  class Node implements Comparable<Node>{
    private double data;
    private double val;
    private double dist;
    private Node l,r;
    private int pat;
   
    public int compareTo(Node that){
        if(this.dist>that.dist) return 1;
        else if(this.dist<that.dist) return -1;
        return 0;
    } 
    public Node(double data, double val, int pat){
        this.data = data;
        this.val = val;
        this.pat = pat;
    }
    }
    public void put(double data, double val, int pat){
        root = put(root, data, val, pat);
    }
    public Node put(Node x, double data, double val, int pat){
        if(x==null){
          
            return new Node(data, val, pat);
        }        
        if(pat==0) pat = 1;
        else pat = 0;
        
        if(data < x.data)  x.l = put(x.l, val, data, pat);
        else if(data > x.data)  x.r = put(x.r, val, data, pat);
        
        return x;
    }
   
    public void init(Point2D[] points){
        
        int len = points.length;
        Node first = new Node(points[0].x(), points[0].y(), 0);
        root = first;
        for(int i = 1; i<len;i++){
            put(points[i].x(), points[i].y() ,0);
        } 
        return;
    }
    public Point2D[] query(Point2D poiresultnt, int k){
        Point2D[] result = new Point2D[k];                       
        MaxPQ<Node> pq = new MaxPQ<>();        
        this.find(pq, root, poiresultnt, k);
                       
        for(int i = k-1;i>=0;i--){
           Node point = pq.delMax();
            if(point.pat == 0) result[i] = new Point2D(point.data, point.val);
            else if(point.pat==1) result[i] = new Point2D(point.val, point.data);           
        }        
        return result;
    }
    public void find(MaxPQ<Node> pq, Node x, Point2D qu, int k){
        
        if(x == null) return;
        
        double xt = 0 ;
        int yt = 0;
        if(x.pat == 0){
            double dx,dy;
            dx = x.data - qu.x();
            dy = x.val - qu.y();
            xt = dx*dx + dy*dy;
            if(qu.x()>x.data) yt = 1;
            else yt = -1;
        }
        else if(x.pat == 1){
            double dx,dy;
            dx = x.val - qu.x();
            dy = x.data - qu.y();
            xt = dx*dx + dy*dy;
            if(qu.y()>x.data) yt = 1;
            else yt = -1;
        }
        x.dist = xt;
        
        if(pq.size()<k) pq.insert(x);
        else{
           Node maxdis = pq.max();
           if(maxdis.dist>xt){
               maxdis = pq.delMax();
               pq.insert(x);
          }
        }    
        if(yt<0) {
            find(pq, x.l, qu, k); 
            Node maxdis = pq.max();          
            if(pq.size()<k) find(pq, x.r, qu, k);
            else if(x.pat == 0){
                double shortdx = qu.x() - x.data;
                if(maxdis.dist>(shortdx*shortdx)) find(pq, x.r, qu, k);                
            }
            else if(x.pat==1){
             double shortdy = qu.y() - x.data;
                if(maxdis.dist>(shortdy*shortdy)) find(pq, x.r, qu, k); 
            }        
        }
        
        else if(yt>0){
            find(pq, x.r, qu, k);
            Node maxdis = pq.max();
            if(pq.size()<k) find(pq, x.l, qu, k);
                         
            else if(x.pat == 0){
                double shortdx = qu.x() - x.data;
                if(maxdis.dist>(shortdx*shortdx)) find(pq, x.l, qu, k);                
            }
            else if(x.pat==1){
             double shortdy = qu.y() - x.data;
                if(maxdis.dist>(shortdy*shortdy)) find(pq, x.l, qu, k); 
            }        
        }
       
    }

}


