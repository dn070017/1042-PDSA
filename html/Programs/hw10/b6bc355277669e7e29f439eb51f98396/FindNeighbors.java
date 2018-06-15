/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author USER
 */
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
    public final class RectHV {
    private final double xmin, ymin;  
    private final double xmax, ymax;   

    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        if (Double.isNaN(xmin) || Double.isNaN(xmax))
            throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
        if (Double.isNaN(ymin) || Double.isNaN(ymax))
            throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
        if (xmax < xmin || ymax < ymin) {
            throw new IllegalArgumentException(""Invalid rectangle"");
        }
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
    
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    root=null;
    pointer=null;
    bound=0;
    target=null;
    yuru=null;
    }
    // TODO
    public Node root;
    private Node pointer;
    private int bound;
    private Point2D target;
    private MaxPQ<Pair> yuru;
    public void init(Point2D[] points) {
        Node current;
        int n=points.length;
        for(int i = 0; i <n ; i++){
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
    private void FindKNN( Node pointer , boolean orien){
        
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
                if(yuru.size()<bound){
                    FindKNN(far,!orien);                
                }else{
                    if(yuru.max().distance>Shortdistance(pointer,orien)){
                        FindKNN(far,!orien); 
                    }
                    
                }
            
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
        public double Shortdistance(Node point,boolean orien){
        double a=0;
        RectHV d;
        if(point.hasUp()){
            if(orien){
                if(target.x()>point.getValue().x()){
                    if(point.getValue().y()>point.getUp().getValue().y()){
                        d = new RectHV(0,point.getUp().getValue().y(),point.getValue().x(),1);
                    }else{
                        d = new RectHV(0,0,point.getValue().x(),point.getUp().getValue().y());
                    }
                }else{
                    if(point.getValue().y()>point.getUp().getValue().y()){
                        d = new RectHV(point.getValue().x(),point.getUp().getValue().y(),1,1);
                    }else{
                        d = new RectHV(point.getValue().x(),0,1,point.getUp().getValue().y());
                    }
                }
                
            }else{
                if(target.y()>point.getValue().y()){
                    if(point.getValue().x()>point.getUp().getValue().x()){
                        d = new RectHV(point.getUp().getValue().x(),0,1,point.getValue().y());
                    }else{
                        d = new RectHV(0,0,point.getUp().getValue().x(),point.getValue().y());
                    }
                }else{
                    if(point.getValue().x()>point.getUp().getValue().x()){
                        d = new RectHV(point.getUp().getValue().x(),point.getValue().y(),1,1);
                    }else{
                        d = new RectHV(0,point.getValue().y(),point.getUp().getValue().x(),1);
                        
                    }
                }
                
            }
            a = d.distanceTo(target);
        }else{
            a = Math.abs(target.x()-point.getValue().x());
        }
        
        return a;
    }
    public class Node {

        private Node left,right,up;
        private Point2D value;
        private boolean orien;
        public Node(Node l, Node r,Node up ,Point2D v, boolean direction) {
            this.left = l;
            this.right = r;
            this.up = up;
            this.value = v;
            this.orien = direction;
        }
        public Node getLeft() {
            return (this.left);
        }
        public Node getRight() {
            return (this.right);
        }
          public Node getUp() {
            return (this.up);
        }
        public Point2D getValue() {
            return (this.value);
        }
          public boolean getOrien() {
            return this.orien;
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
      
    }
    public static void main(String[] args) {
        Point2D[] input=new Point2D[20];
        input[0]=new Point2D(0.3833339428,0.1459115606);
        input[1]=new Point2D(0.3426077152,0.7218207763);
        input[2]=new Point2D(0.3426077152,0.7218207763);
    }
}

