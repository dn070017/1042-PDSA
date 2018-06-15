
public class FindNeighbors {
    
    private Node root;
    public Point2D refer;
    private MaxPQ<Node> NearPoints = new MaxPQ<>();
    public FindNeighbors(){}

    
    public void init(Point2D[] points){
        for(int i=0;i<points.length;i++){
            if(i==0){
                put(points[i],1);
            }
            else{
                put(points[i],1);
            }
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.point(points[i].x(),points[i].y());
        }
        return;
    }

    public Point2D[] query(Point2D point, int k){
        this.refer = point;//  save point to class
        Point2D[] nearla = new Point2D[k];
        
        near(this.root,k);
        
        for(int i=0; i<k; i++){
            nearla[k-i-1] = NearPoints.delMax().key;
//            
//            StdDraw.setPenRadius(0.02);
//            StdDraw.setPenColor(StdDraw.DARK_GRAY);
//            StdDraw.point(nearla[k-i-1].x(),nearla[k-i-1].y());
        }
//         for(int i=0; i<k; i++){
//             System.out.println(nearla[i].x()+"" ""+nearla[i].y());
//         }

//
//        StdDraw.setPenRadius(0.015);
//        StdDraw.setPenColor(StdDraw.CYAN);
//        StdDraw.point(point.x(),point.y());
        
        return nearla;
    }
    public void near(Node start ,int rank) {
        if(start == null){//2. if It's leaf return it
        return;
        }
        
        NearPoints.insert(start);//1. store and remain the least N node
        if(NearPoints.size()>rank){
            NearPoints.delMax();
        }
        
        if(start.left == null && start.right == null){//2. if It's leaf return it
        return;
        }   
        
        if(start.flag == 1){
            
            if(refer.x()>start.key.x()){
                near(start.right,rank);
                Node temp = NearPoints.delMax();
                if(temp.key.distanceTo(refer)<Math.abs(start.key.x()-refer.x())){
                    NearPoints.insert(temp); return;
                }
                NearPoints.insert(temp);
                near(start.left,rank);
                return;
            }
            
            else if(refer.x()<start.key.x()){
                near(start.left,rank);
                Node temp = NearPoints.delMax();
                if(temp.key.distanceTo(refer)<Math.abs(start.key.x()-refer.x())){
                    NearPoints.insert(temp); return;
                }
                NearPoints.insert(temp);
                near(start.right,rank);
                return;
            }
            else
            return;
        }
        
        if(start.flag == 0){
            if(refer.y()>start.key.y()){
                 near(start.right,rank);
                Node temp = NearPoints.delMax();
                if(temp.key.distanceTo(refer)<Math.abs(start.key.y()-refer.y())){
                    NearPoints.insert(temp); return;
                }
                NearPoints.insert(temp);
                near(start.left,rank);
                return;
            }
            
            else if(refer.y()<start.key.y()){
                near(start.left,rank);
                Node temp = NearPoints.delMax();
                if(temp.key.distanceTo(refer)<Math.abs(start.key.y()-refer.y())){
                    NearPoints.insert(temp); return;
                }
                NearPoints.insert(temp);
                near(start.right,rank);
                return;                
            }
            return;
        }
        else
        return;
    } 
    
    
    
    public void put(Point2D key,int flag) {
        if (key == null) throw new NullPointerException(""first argument to put() is null"");
        root = put(root, key,flag);
    }
 

    private Node put(Node x, Point2D key,int flag) {
        if (x == null) {
            if(flag == 0){
//                StdDraw.setPenRadius(0.001);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.line(1,key.y(),0,key.y()); 
                  return new Node(key,1,flag);
            }
            else if(flag == 1){
//                StdDraw.setPenRadius(0.001);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.line(key.x(),0,key.x(),1); 
                  return new Node(key,1,flag);
            }
        }
        if(x.flag == 1){       
            if (x.key.x() > key.x()) {x.left  = put(x.left, key, 0);}
            else if (x.key.x() < key.x()) {x.right = put(x.right, key, 0);}
        }
        else if(x.flag == 0){
            if      (x.key.y() > key.y()) {x.left  = put(x.left,  key, 1);}
            else if (x.key.y() < key.y()) {x.right = put(x.right, key, 1);}
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
//****************************** NODE ****************************************\\
    
    public class Node implements Comparable<Node> {
        private Point2D key;           // sorted by key
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree
        private int flag;      // 1:right or left 0:up and down

        public Node(Point2D key, int N,int flag) {
            this.key = key;
            this.N = N;
            this.flag = flag;
        }
        public void setFlag(int flag){
            this.flag = flag;
        }
        
        public int compareTo(Node that) {
            if(this.key.distanceTo(refer) > that.key.distanceTo(refer)) return +1;
            else if(this.key.distanceTo(refer) < that.key.distanceTo(refer)) return -1;
            else return 0;
        }
    }
//***************************** END NODE *************************************\\    
}

