public class FindNeighbors {
    public final class RectH {
    private final double xmin, ymin;
    private final double xmax, ymax;
    public RectH(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }
    public double distanceTo(Point2D p) {
        return Math.sqrt(this.distanceSquaredTo(p));
    }
    public double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
    }
}
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
        private Node up;
        private Point2D value;
        private boolean orien; // orien=ture vertical  orien=false horizon
        public Node(Node left, Node right,Node up ,Point2D value, boolean orien) {
            this.left = left;
            this.right = right;
            this.up = up;
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
        public Node getUp() {
            return (this.up);
        }
        public boolean hasUp(){
            return (this.up != null);
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
    public Node root = null;
    private Node pointer = null;
    private int bound_original = 0;
    private Point2D target = null;
    private MaxPQ<Pair> yuruccc = null;
    public void init(Point2D[] points) {
        Node current = null;
        for(int i = 0; i <points.length ; i++){
            int flag = 0;
            boolean layer = true;
            current = root;
            while( flag==0 ){
                if(current == null){
                    current = new Node(null,null,null,points[i], layer );
                    root = current;
                    flag = 1;
                }else{
                    if(current.getOrien()){
                        if(points[i].x()>current.value.x()){
                            if(current.getRight()==null){
                                Node newcurrent = new Node(null,null,current,points[i], !layer );
                                current.setRight(newcurrent);
                                flag = 1;
                            }else{
                                current = current.getRight();
                            }
                        }else{
                            if(current.getLeft()==null){
                                Node newcurrent = new Node(null,null,current,points[i], !layer );
                                current.setLeft(newcurrent);
                                flag =1;
                            }else{
                                current = current.getLeft();
                            }
                        }
                }else{
                        if(points[i].y()>current.value.y()){
                            if(current.getRight()==null){
                                Node newcurrent = new Node(null,null,current,points[i], !layer );
                                current.setRight(newcurrent);
                                flag = 1;
                            }else{
                                current = current.getRight();
                            }
                        }else{
                            if(current.getLeft()==null){
                                Node newcurrent = new Node(null,null,current,points[i], !layer );
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
    public double Shortdistance(Node point,boolean orien){
        double a=0;
        RectH d;
        if(point.hasUp()){
            if(orien){
                if(target.x()>point.getValue().x()){
                    if(point.getValue().y()>point.getUp().getValue().y()){
                        d = new RectH(0,point.getUp().getValue().y(),point.getValue().x(),1);
                    }else{
                        d = new RectH(0,0,point.getValue().x(),point.getUp().getValue().y());
                    }
                }else{
                    if(point.getValue().y()>point.getUp().getValue().y()){
                        d = new RectH(point.getValue().x(),point.getUp().getValue().y(),1,1);
                    }else{
                        d = new RectH(point.getValue().x(),0,1,point.getUp().getValue().y());
                    }
                }           
            }else{
                if(target.y()>point.getValue().y()){
                    if(point.getValue().x()>point.getUp().getValue().x()){
                        d = new RectH(point.getUp().getValue().x(),0,1,point.getValue().y());
                    }else{
                        d = new RectH(0,0,point.getUp().getValue().x(),point.getValue().y());
                    }
                }else{
                    if(point.getValue().x()>point.getUp().getValue().x()){
                        d = new RectH(point.getUp().getValue().x(),point.getValue().y(),1,1);
                    }else{
                        d = new RectH(0,point.getValue().y(),point.getUp().getValue().x(),1);      
                    }
                }
            }
            a = d.distanceTo(target);
        }else{
            a = Math.abs(target.x()-point.getValue().x());
        }
        return a;
    }
    public void FindKNN( Node pointer , boolean orien){  
            if(pointer==null){
                return;
            }         
            if(yuruccc.size()<bound_original){
                yuruccc.insert(new Pair(target,pointer.getValue()));
            }else{
                if(yuruccc.max().distance > target.distanceTo(pointer.getValue())){
                    yuruccc.delMax();
                    yuruccc.insert(new Pair(target,pointer.getValue()));
                }
            }
                Node near;
                Node far;
                if( (orien&&(pointer.getValue().x()<target.x())) || (!orien&&(pointer.getValue().y()<target.y())) ){
                    near = pointer.getRight();
                    far = pointer.getLeft();
                }else{
                    near = pointer.getLeft();;
                    far = pointer.getRight();
                }
                FindKNN(near,!orien);
                if(yuruccc.size()<bound_original){
                    FindKNN(far,!orien);                
                }else{
                    if(yuruccc.max().distance>Shortdistance(pointer,orien)){
                        FindKNN(far,!orien); 
                    }       
                }
            return;
    }
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result0915 = new Point2D[k];
        yuruccc = new MaxPQ<Pair>(k);
        pointer = root;
        bound_original = k;
        target = point;
        FindKNN(pointer,true);
        for(int i=k-1;i>-1;i--){
            result0915[i] = yuruccc.delMax().getB();
        }   
        return result0915;
    }
}

