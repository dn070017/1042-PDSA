//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class FindNeighbors {
//    
//    // DO NOT MODIFY THE CONSTRUCTOR! 
//    public FindNeighbors(){}
//
//    // TODO
//    // please use the Point2D from algs4.jar 
//    public Node root=null;
//    private Node pointer=null;
//    private int bound = 0;
//    private Point2D target = null;
//    private MaxPQ<Pair> yuru = null;
//    
//    public class RectHV{
//    private final double xmin, ymin;   // minimum x- and y-coordinates
//    private final double xmax, ymax;   // maximum x- and y-coordinates
//        
//        public RectHV(double xmin,double xmax,double ymin,double ymax){
//        if (Double.isNaN(xmin) || Double.isNaN(xmax))
//            throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
//        if (Double.isNaN(ymin) || Double.isNaN(ymax))
//            throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
//        if (xmax < xmin || ymax < ymin) {
//            throw new IllegalArgumentException(""Invalid rectangle"");
//        }
//        
//            this.xmin = xmin;
//            this.xmax = xmax;
//            this.ymin = ymin;
//            this.ymax = ymax;
//        }
//        
//        public double distanceTo(Point2D p) {
//        return Math.sqrt(this.distanceSquaredTo(p));
//        }
//        public double distanceSquaredTo(Point2D p) {
//        double dx = 0.0, dy = 0.0;
//        if      (p.x() < xmin) dx = p.x() - xmin;
//        else if (p.x() > xmax) dx = p.x() - xmax;
//        if      (p.y() < ymin) dy = p.y() - ymin;
//        else if (p.y() > ymax) dy = p.y() - ymax;
//        return dx*dx + dy*dy;
//        }
//        
//    }
//    
//    
//    public void init(Point2D[] points){
//        Node current = null;
//        for (int i = 0;i<points.length;i++){
//            int flag = 0;
//            boolean layer = true;
//            while (flag ==0){
//                if (current == null){
//                    current = new Node(null,null,null,points[i],layer);
//                    root = current;
//                    flag =1 ;
//                }
//                else {
//                    if (current.getOrien()){// vertical
//                        if (points[i].x()>current.value.x()){
//                            if (current.getRight()==null){
//                                Node newcurrent = new Node(null,null,current,points[i],!layer);
//                                current.setRight(newcurrent);
//                                flag=1;
//                            }else{
//                                current = current.getRight(); //so far : flag = 0;
//                            }
//                        }else{
//                            if (current.getLeft()==null){
//                                Node newcurrent = new Node(null,null,current,points[i],!layer);
//                                current.setLeft(newcurrent);
//                                flag =1; 
//                            }else{
//                                current = current.getLeft(); // so far : flag = 0;
//                                }
//                            }
//                    }else{
//                        if (points[i].y()>current.value.y()){
//                            if (current.getRight()==null){
//                                Node newcurrent = new Node(null,null,current,points[i],layer);
//                                current.setRight(newcurrent);
//                                flag = 1;
//                            }else{
//                                current = current.getRight(); // so far : flag = 0;
//                            }
//                        }else{
//                            if (current.getLeft()==null){
//                                Node newcurrent = new Node(null,null,current,points[i],layer);
//                                current.setLeft(newcurrent);
//                                flag = 1;
//                            }else{
//                                current = current.getLeft(); // so far : flag = 0;
//                            }
//                        }
//                    }
//                }
//                layer = !layer;
//            }
//        }
//        return;
//    }
//    
//    
//    public class Node {
//        private Node left;
//        private Node right;
//        private Node up;
//        private Point2D value;
//        private boolean orien; // orien=ture vertical  orien=false horizon
//        public Node(Node left,Node right,Node up,Point2D value,boolean orien){
//         this.left = left;
//         this.right = right;
//         this.up = up;
//         this.value = value;
//         this.orien = orien;
//       }
//        public Node getLeft(){
//            return this.left;
//        }
//        public Node getRight(){
//            return this.right;
//        }
//        public Point2D getValue() {
//            return (this.value);
//        }
//        public Node getUp() {
//            return (this.up);
//        }
//        public boolean getOrien() {
//            return this.orien;
//        }
//        public void setLeft(Node Left){
//            this.left = Left;
//        }
//        public void setRight(Node Right){
//            this.right = Right;
//        }        
//        public boolean hasUp(){
//            return (this.up != null);
//        }
//    }
//    
//    public double Shortdistance(Node point,boolean orien){
//        double a=0;
//        RectHV d;
//        if(point.hasUp()){
//            if(orien){
//                    if(target.x()>point.getValue().x()){
//                        if (target.y()>)
//                    }
//            }else{
//                
//            }
//        }
//        
//        
//        return a;
//    }
//    
//
//    // TODO
//    // the result should be sorted accordingly to their distances to the query, from small to large
//    // please use the Point2D from algs4.jar 
//    public Point2D[] query(Point2D point, int k){
//        Point2D[] result = new Point2D[k];
//        return result;
//    }
//    
//    public static void main(String[] args) throws IOException {
//        // TODO code application logic here
//       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
//           int cc = 0;
//           Stack<Double> X = new Stack<Double>();
//           Stack<Double> Y = new Stack<Double>();
//           for(String in = br.readLine(); in != null; in = br.readLine()) {
//               String[] temp = in.split("" "");
//               X.push(Double.parseDouble(temp[0]));
//               Y.push(Double.parseDouble(temp[1]));
//               cc++;
//           }
//           System.out.println(cc);
//           Point2D[] points = new Point2D[cc];
//           for (int i = cc-1 ;i>-1;i--){
//               System.out.println(i);
//               points[i] = new Point2D(X.pop(), Y.pop());
//            }
////           System.out.println(points[0].x());
////           System.out.println(points[0].y());
////           System.out.println(points[19].x());
////           System.out.println(points[19].y());     
//    }
//   }
//    
//}



public class FindNeighbors {
   
    public final class RectHV {
    private final double xmin, ymin;   // minimum x- and y-coordinates
    private final double xmax, ymax;   // maximum x- and y-coordinates

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

    public double distanceSquaredTo(Point2D p) { // 點跟長方形的距離 ，邊界內部視為0
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
    
}
