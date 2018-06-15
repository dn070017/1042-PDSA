
import java.util.Arrays;

public class FindNeighbors {
    public Node root;
    public Point2D[] tree;
    

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        tree = points;
        if(points.length==0){
            return;
        }
        if(points.length==1){
           new Node(null,null,points[0],1);
        }
        //else
        root = new Node(null,null,points[0],1);
        //System.out.println(root.getValue());
        //System.out.println(""==========="");
        
        for(int i=1;i<points.length;i++){
                 Node a = new Node(null,null,points[i],1);
                 //System.out.println(a.getValue());                 
                 root.getNext(a);
        }
        return;
    }//end of init

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    
    public void distsort(Point2D p){
        //double[] parray = new double[points.length];
        Point2D[] pd = new Point2D[tree.length];
        
        Arrays.sort(tree, p.DISTANCE_TO_ORDER);
        int k =3;
        for(int i=0;i<k;i++){
        //System.out.println(tree[i]);
    }
    }
    public Point2D[] query(Point2D point, int k){
       //Point2D[] pd = new Point2D[tree.length];
       Point2D[] ans = new Point2D[k]; 
        Arrays.sort(tree, point.DISTANCE_TO_ORDER);
        
        for(int i=0;i<k;i++){
            ans[i]=tree[i];
        //System.out.println(tree[i]);
        }
        
        return ans;
        /*
        MaxPQ<Point2D> pq = new MaxPQ<Point2D>();
        Node query = new Node(null,null,point,0);
        
        root.getNext(query);
        
        if(pq.size()>k){
            pq.delMax();
 
        }
        Point2D[] result = new Point2D[k];
        return result;
*/
    }//end of query
    public void search (Node tree ,Point2D p,Point2D c,int depth){
        

        
        
    }
       
    
    
    private class Node{
        private Node left;
        private Node right;
        private Point2D value;
        private int depth;

        public Node(Node left, Node right, Point2D value,int depth){
            this.left = left;
            this.right = right;
            this.value = value;
            this.depth = depth;
        }

        public Node getLeft(){
            return(this.left);
        }

        public Node getRight(){
            return(this.right);
        }


        public Point2D getValue(){
            return(this.value);
        }

        public int getDepth(){
            return(this.depth);
        }

        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }

        public void setValue(Point2D value){
            this.value = value;
        }
        public void setDepth(int depth){
            this.depth = depth;
        }
        public void getNext(Node n){
            if(this.depth==1){//x
                n.setDepth(0);
                if(n.getValue().x()>this.getValue().x()){
                    if(this.getRight()==null){
                        this.setRight(new Node(null,null,n.getValue(),1));                        
                    }
                    else
                    this.right.getNext(n);
                }
                if(n.getValue().x()<this.getValue().x()){
                    if(this.getLeft()==null){
                        this.setLeft(new Node(null,null,n.getValue(),1));
                         //n.setLeft((new Node(null,null,n.getValue(),1),null));
                    }
                    else
                    this.left.getNext(n);
                }
           
            }//end of depth 1 
            if(this.depth==0){
                n.setDepth(1);
                if(n.getValue().y()>this.getValue().y()){
                    if(this.getRight()==null){
                        this.setRight(new Node(null,null,n.getValue(),0));                        
                    }
                    else

                  this.right.getNext(n);
                }
                if(n.getValue().y()<this.getValue().y()){
                    if(this.getLeft()==null){
                        this.setLeft(new Node(null,null,n.getValue(),0));
                         
                    }
                    else
                  this.left.getNext(n);
                }
           
            }//end of depth 1 

        }

    }//end of node
    
    public class RectHV {
    private final double xmin, ymin;   // minimum x- and y-coordinates
    private final double xmax, ymax;   // maximum x- and y-coordinates

    // construct the axis-aligned rectangle [xmin, xmax] x [ymin, ymax]
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        if (xmax < xmin || ymax < ymin) {
            throw new IllegalArgumentException(""Invalid rectangle"");
        }
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    // accessor methods for 4 coordinates
    public double xmin() { return xmin; }
    public double ymin() { return ymin; }
    public double xmax() { return xmax; }
    public double ymax() { return ymax; }

    // width and height of rectangle
    public double width()  { return xmax - xmin; }
    public double height() { return ymax - ymin; }

    // does this axis-aligned rectangle intersect that one?
    public boolean intersects(RectHV that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
            && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }

    // draw this axis-aligned rectangle
    public void draw() {
        StdDraw.line(xmin, ymin, xmax, ymin);
        StdDraw.line(xmax, ymin, xmax, ymax);
        StdDraw.line(xmax, ymax, xmin, ymax);
        StdDraw.line(xmin, ymax, xmin, ymin);
    }

    // distance from p to closest point on this axis-aligned rectangle
    public double distanceTo(Point2D p) {
        return Math.sqrt(this.distanceSquaredTo(p));
    }

    // distance squared from p to closest point on this axis-aligned rectangle
    public double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
    }

    // does this axis-aligned rectangle contain p?
    public boolean contains(Point2D p) {
        return (p.x() >= xmin) && (p.x() <= xmax)
            && (p.y() >= ymin) && (p.y() <= ymax);
    }

    // are the two axis-aligned rectangles equal?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        RectHV that = (RectHV) y;
        if (this.xmin != that.xmin) return false;
        if (this.ymin != that.ymin) return false;
        if (this.xmax != that.xmax) return false;
        if (this.ymax != that.ymax) return false;
        return true;
    }

    // return a string representation of this axis-aligned rectangle
    public String toString() {
        return ""["" + xmin + "", "" + xmax + ""] x ["" + ymin + "", "" + ymax + ""]"";
    }

}
}//end of f


