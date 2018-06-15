import java.io.BufferedReader;
import java.io.FileReader;
 import java.util.ArrayList;
import java.util.Arrays;
public class FindNeighbors{
    private Node root;//root of 2-d tree
    private int size;
    private int len;
    private static class Node {
      private Point2D p;
      private Node left;
      private Node right;
      public int R=0;
      public int L=0;
      private boolean vertical = true;
      public Node(Point2D p, Node left, Node right, boolean vertical) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;         
        }   
    }
    public FindNeighbors(){
        root=null;
        size=0;
        len=0;
        
    }
      
    public void insert(Point2D p) {
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
        if (isSmallerThanPointInNode(p, node)) {
            node.left = doInsert(node.left, p, !node.vertical);
        } else {
            node.right = doInsert(node.right, p, !node.vertical);
        }

        return node;
    }

    //vertical用X排序，反之Y排序

    private boolean isSmallerThanPointInNode(Point2D p, Node node) {
        int cmp = node.vertical ? Point2D.X_ORDER.compare(p, node.p) : Point2D.Y_ORDER.compare(p, node.p);
        return (cmp < 0);
    }

    public void init(Point2D[] points) {
        len = points.length;
        for (int i = 0; i < points.length; i++) {
            insert(points[i]);
        }
       
        return;
    }
int A=0;
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        MinPQ<Pair> pq = new MinPQ<Pair>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        Node node=root;
       // stack.push(root);
        Pair po = new Pair(point.distanceTo(root.p), point, root.p);
        pq.insert(po);
        while(node.left!=null&&node.left!=null){           
            //Node node =stack.pop();           
            if(node.vertical==true){
                if(point.x()<node.p.x()){
                    node.L=1;
                    if(node.left!=null){
                        
                        stack.push(node.left);
                        po = new Pair(point.distanceTo(node.left.p), point, node.left.p);
                        pq.insert(po);
                        node=node.left;
                    }
                    else{
                        break;
                    }
                }
                else{
                    node.R=1;
                    if(node.right!=null){
                        
                        stack.push(node.right);
                        po = new Pair(point.distanceTo(node.right.p), point, node.right.p);
                        pq.insert(po);
                        node=node.right;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                if(point.y()<node.p.y()){
                    node.L=1;
                    if(node.left!=null){
                        
                        stack.push(node.left);
                        po = new Pair(point.distanceTo(node.left.p), point, node.left.p);
                        pq.insert(po);
                        node=node.left;
                    }
                    else{
                        break;
                    }
                }
                else{
                    node.R=1;
                    if(node.right!=null){
                        
                        stack.push(node.right);
                        po = new Pair(point.distanceTo(node.right.p), point, node.right.p);
                        pq.insert(po);
                        node=node.right;
                }
                    else{
                        break;
                    }
            }
        }            
            if (pq.size() > k) {
                    pq.delMin();
                }
        }        
     
        while(stack.size()>0){
            Node se=stack.pop();
            //底部的點
//            System.out.println(++A);
//            System.out.println(se.p);
            if(se.left==null && se.right==null){
                //se=stack.pop();
                continue;               
            }
            if(se.L==0&&se.R==0){
                node=se;
               stack.push(node);
               node.L=1;
               node.R=1;             
                while(node.left!=null&&node.left!=null){  
                    //System.out.println(stack.peek().p);
            //Node node =stack.pop();           
            if(node.vertical==true){
                if(point.x()<node.p.x()){
                    node.L=1;
                    if(node.left!=null){
                        
                        stack.push(node.left);
                        po = new Pair(point.distanceTo(node.left.p), point, node.left.p);
                        pq.insert(po);
                        node=node.left;
                    }
                    else{
                        break;
                    }
                }
                else{
                    node.R=1;
                    if(node.right!=null){
                        
                        stack.push(node.right);
                        po = new Pair(point.distanceTo(node.right.p), point, node.right.p);
                        pq.insert(po);
                        node=node.right;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
             
                if(point.y()<node.p.y()){
                    node.L=1;
                    if(node.left!=null){
                        
                        stack.push(node.left);
                        po = new Pair(point.distanceTo(node.left.p), point, node.left.p);
                        pq.insert(po);
                        node=node.left;
                    }
                    else{
                        break;
                    }
                }
                else{
                    node.R=1;
                    if(node.right!=null){
                        
                        stack.push(node.right);
                        po = new Pair(point.distanceTo(node.right.p), point, node.right.p);
                        pq.insert(po);
                        node=node.right;
                }
                    else{
                        break;
                    }
            }
        }            
            if (pq.size() > k) {
                    pq.delMin();
                }
           //System.out.println(node.p); 
        } 
                continue;
            }
 
            if(se.vertical==true){                
                    if(point.x()<se.p.x()){
                        if(pq.min().distance>Math.abs(point.x()-se.p.x())){
                        if(se.right!=null){                            
                            se=se.right;
                            stack.push(se);
                            po = new Pair(point.distanceTo(se.p), point, se.p);
                        pq.insert(po);
                            
                        }
                        
                    }
                    }
                    else{
                        if(pq.min().distance>Math.abs(point.x()-se.p.x())){
                        if(se.left!=null){
                            se=se.left;
                            stack.push(se);
                            po = new Pair(point.distanceTo(se.p), point, se.p);
                        pq.insert(po);
                    }
                        
                    }
                    }
                }
                       
        
            else{                
                    if(point.y()<se.p.y()){
                        if(pq.min().distance>Math.abs(point.y()-se.p.y())){
                        if(se.right!=null){
                            se=se.right;
                            stack.push(se);
                            po = new Pair(point.distanceTo(se.p), point, se.p);
                        pq.insert(po);
                            
                        }
                       
                        }
                    } else {
                        if (pq.min().distance > Math.abs(point.y() - se.p.y())) {
                            if (se.left != null) {
                                se = se.left;
                                stack.push(se);
                                po = new Pair(point.distanceTo(se.p), point, se.p);
                                pq.insert(po);
                            }
                            
                        }
                    }
                
            }

            if (pq.size() > k) {
                    pq.delMin();
                }
                       
        }
        
        
        
//            ArrayList<Node> arrl = preorder(root);
//            for (int i = 0; i < size; i++) {
//                Pair pi = new Pair(point.distanceTo(arrl.get(i).p), point, arrl.get(i).p);
//                pq.insert(pi);
//                if (pq.size() > k) {
//                    pq.delMin();
//                }
//            }
        
         
 
for(int i=k-1;i>=0;i--){
    result[i]=pq.delMin().b;
}
      
    return result;
    // the points should be sorted accordingly to their distances to the query, from small to large
}
    
  ArrayList<Node> arrli = new ArrayList<Node>();
  public ArrayList<Node> preorder(Node root){
    Node re=root;
    if(root!=null){
        arrli.add(root);
        preorder(root.left);
        preorder(root.right);
    }
    return arrli;
}
  public static class Pair implements Comparable<Pair>{
        private Double distance;
        private Point2D a;
        private Point2D b;
    public Pair (Double distance, Point2D a, Point2D b)
    {
        this.distance = distance;
        this.a = a;
        this.b = b;
    }
    public Double getDouble(){
        return this.distance;
    }
    public Point2D geta(){
        return this.a;
    }
    public Point2D getb(){
        return this.b;
    
    }
    public int compareTo(Pair that) {
        if (that.distance > this.distance)
            return 1;
        else if (that.distance < this.distance)
            return -1;
        else 
            return 0;
        
        
        
        
        
    }
    }
  
  
    
    public static void main(String[] args) throws Exception {
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           String line;
           
           ArrayList<Point2D> point = new ArrayList<Point2D>();
           while ((line = br.readLine()) != null) {  
               String[] DataPoint = line.split("" "");
                Double x = Double.parseDouble(DataPoint[0]);
                Double y = Double.parseDouble(DataPoint[1]);
                point.add(new Point2D(x,y));
           }
           Point2D[] a = new Point2D[point.size()];
           for(int i=0;i<point.size();i++){
               
               a[i]=point.get(i);
//               point.get(i).draw();
//               StdDraw.textLeft(point.get(i).x(),point.get(i).y(),Integer.toString(i));
//               StdDraw.filledCircle(point.get(i).x(), point.get(i).y(), 0.01);
           }
 
           FindNeighbors find = new FindNeighbors();
           find.init(a);
           
           //0.144932 0.938569
          // Point2D p= new Point2D(0.144932,0.938569);
           //(0.1354339200 0.7019446863)
           Point2D p= new Point2D(0.1354339200,0.7019446863);
           int Num=6;
           Point2D[] result = new Point2D[Num];
          result= find.query(p, Num);
           for (int i=0;i<Num;i++){
               System.out.println(result[i]);
           }
           
           
       } 
}
        
}

        
    


