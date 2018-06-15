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

    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        MinPQ<Pair> pq = new MinPQ<Pair>();
        if (k > size / 2) {
            ArrayList<Node> arrl = preorder(root);
            for (int i = 0; i < size; i++) {
                Pair po = new Pair(point.distanceTo(arrl.get(i).p), point, arrl.get(i).p);
                pq.insert(po);
                if (pq.size() > k) {
                    pq.delMin();
                }
            }
        } else {
            if (point.x() < root.p.x()) {
                if (point.distanceTo(root.left.p) < point.distanceTo(root.right.p)) {
                    ArrayList<Node> arrl = preorder(root.left);
                    for (int i = 0; i < arrl.size(); i++) {
                        Pair po = new Pair(point.distanceTo(arrl.get(i).p), point, arrl.get(i).p);
                        pq.insert(po);
                        if (pq.size() > k) {
                            pq.delMin();
                        }
                    }
                } else {
                    ArrayList<Node> arrl = preorder(root);
                    for (int i = 0; i < arrl.size(); i++) {
                        Pair po = new Pair(point.distanceTo(arrl.get(i).p), point, arrl.get(i).p);
                        pq.insert(po);
                        if (pq.size() > k) {
                            pq.delMin();
                        }
                    }
                }
            } else {
                if (point.distanceTo(root.left.p) > point.distanceTo(root.right.p)) {
                    ArrayList<Node> arrl = preorder(root.right);
                    for (int i = 0; i < arrl.size(); i++) {
                        Pair po = new Pair(point.distanceTo(arrl.get(i).p), point, arrl.get(i).p);
                        pq.insert(po);
                        if (pq.size() > k) {
                            pq.delMin();
                        }
                    }
                } else {
                    ArrayList<Node> arrl = preorder(root);
                    for (int i = 0; i < arrl.size(); i++) {
                        Pair po = new Pair(point.distanceTo(arrl.get(i).p), point, arrl.get(i).p);
                        pq.insert(po);
                        if (pq.size() > k) {
                            pq.delMin();
                        }
                    }
                }
            }
        }
    //Pair po = new Pair(point.distanceTo(root.p),point,root.p);
    
                   
                  
//            if (root.p.x() > point.x()) {
//                ArrayList<Node> arrl = preorder(root.left);               
//                for(int i=0;i<arrl.size();i++){
//                    po = new Pair(point.distanceTo(arrl.get(i).p),point,arrl.get(i).p);
//               pq.insert(po);
//               if(pq.size()>k)
//                   pq.delMin();
//                }               
//            }
//            else{
//                ArrayList<Node> arrl = preorder(root.right);
//                for(int i=0;i<arrl.size();i++){
//                    po = new Pair(point.distanceTo(arrl.get(i).p),point,arrl.get(i).p);
//               pq.insert(po);
//               if(pq.size()>k){
//                   
//                   pq.delMin();  
//               }
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
           }
 
           FindNeighbors find = new FindNeighbors();
           find.init(a);
           Point2D p= new Point2D(0.1354339200,0.7019446863);
           Point2D[] result = new Point2D[3];
          result= find.query(p, 3);
           for (int i=0;i<3;i++){
               System.out.println(result[i]);
           }
           
           
       } 
}
        
}

        
    


