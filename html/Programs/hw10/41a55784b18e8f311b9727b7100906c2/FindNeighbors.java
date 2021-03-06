public class FindNeighbors {
    
    private Node root;
    Stack<Node> test = new Stack<Node>();
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
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
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.point(root.key.x(),root.key.y());
//            StdDraw.point(root.left.key.x(),root.left.key.y());
//            StdDraw.point(root.left.left.key.x(),root.left.left.key.y());
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];

//        StdDraw.setPenRadius(0.02);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.point(point.x(),point.y());
        Node x = this.root;
        
        return result;
    }
    public void trace(Node ro,Point2D c) {
            this.test.push(ro);
            if (ro.left == null&&ro.right == null){  return;}
            else{
               if(ro.flag==1){}
               else if(ro.flag==0){}
            }
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
//                StdDraw.setPenColor(StdDraw.GREEN);
//                StdDraw.line(1,key.y(),0,key.y()); 
                  return new Node(key,1,flag);
            }
            else if(flag == 1){
//                StdDraw.setPenRadius(0.001);
//                StdDraw.setPenColor(StdDraw.GREEN);
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
            if (this.key.y() < that.key.y()) return -1;
            if (this.key.y() > that.key.y()) return +1;
            if (this.key.y() < that.key.y()) return -1;
            if (this.key.y() > that.key.y()) return +1;
            return 0;
        }
    }
}

