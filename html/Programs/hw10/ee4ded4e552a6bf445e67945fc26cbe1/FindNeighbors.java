
public class FindNeighbors {
    public class Pair implements Comparable<Pair>{
        private Point2D a;
        private Point2D b;
        private double distance;
        public Pair(Point2D a,Point2D b){
            this.a = a;
            this.b = b;
            this.distance = a.distanceTo(b);
        }
        public Point2D getA(){
            return this.a;
        }
        public Point2D getB(){
            return this.b;
        }
        public int compareTo(Pair that) {
            if(this.distance>that.distance){
                return 1;
            }else if(this.distance<that.distance){
                return -1;
            }
            return 0;
        }
        
        
    }
    public class Node {

        private Node left;
        private Node right;
        private Point2D value;
        private boolean orien; // orien=ture vertical  orien=false horizon
        public Node(Node left, Node right, Point2D value, boolean orien) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.orien = orien;
        }
        public Node getLeft() {
            return (this.left);
        }
        public Node getRight() {
            return (this.right);
        }
        public Point2D getValue() {
            return (this.value);
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public void setRight(Node right) {
            this.right = right;
        }
        public boolean getOrien() {
            return this.orien;
        }
    }  
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }
    // TODO
    public Node root = null;
    private Node pointer = null;
    private int bound = 0;
    private Point2D target = null;
    private MaxPQ<Pair> yuru = null;
    public void init(Point2D[] points) {
        Node current = null;
        for(int i = 0; i <points.length ; i++){
            int flag = 0;
            boolean layer = true;
            current = root;
            while( flag==0 ){
                if(current == null){
                    current = new Node(null,null,points[i], layer );
                    root = current;
                    flag = 1;
                }else{
                    if(current.getOrien()){
                        if(points[i].x()>current.value.x()){
                            if(current.getRight()==null){
                                Node newcurrent = new Node(null,null,points[i], !layer );
                                current.setRight(newcurrent);
                                flag = 1;
                            }else{
                                current = current.getRight();
                            }
                        }else{
                            if(current.getLeft()==null){
                                Node newcurrent = new Node(null,null,points[i], !layer );
                                current.setLeft(newcurrent);
                                flag =1;
                            }else{
                                current = current.getLeft();
                            }
                        }
                }else{
                        if(points[i].y()>current.value.y()){
                            if(current.getRight()==null){
                                Node newcurrent = new Node(null,null,points[i], !layer );
                                current.setRight(newcurrent);
                                flag = 1;
                            }else{
                                current = current.getRight();
                            }
                        }else{
                            if(current.getLeft()==null){
                                Node newcurrent = new Node(null,null,points[i], !layer );
                                current.setLeft(newcurrent);
                                flag =1;
                            }else{
                                current = current.getLeft();
                            }
                        }
                    }
            }
                layer = !layer;
            }
        }
        return;
    }
    public void FindKNN( Node pointer , boolean orien){
        
            if(pointer==null){
                return;
            }          
            
            if(yuru.size()<bound){
                yuru.insert(new Pair(target,pointer.getValue()));
            }else{
                if(yuru.max().distance > target.distanceTo(pointer.getValue())){
                    yuru.delMax();
                    yuru.insert(new Pair(target,pointer.getValue()));
                }
            }
           /* if(   (orien&&(yuru.max().distance>Math.abs( target.x() - pointer.getValue().x())))  ||  (!orien&&(yuru.max().distance>Math.abs( target.y() - pointer.getValue().y()))) ){
                Node near;
                Node far;
                if( (orien&&(pointer.getValue().x()<target.x())) || (!orien&&(pointer.getValue().y()<target.y())) ){
                    near = pointer.getRight();
                    far = pointer.getLeft();
                }else{
                    near = pointer.getLeft();;
                    far = pointer.getRight();
                }*/
                FindKNN(pointer.getLeft(),!orien);
                FindKNN(pointer.getRight(),!orien);
                
            
            
            return;
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        yuru = new MaxPQ<Pair>(k);
        pointer = root;
        bound = k;
        target = point;
        FindKNN(pointer,true);
        for(int i=k-1;i>-1;i--){
            result[i] = yuru.delMax().getB();
        }
        
        return result;
    }
    
}

