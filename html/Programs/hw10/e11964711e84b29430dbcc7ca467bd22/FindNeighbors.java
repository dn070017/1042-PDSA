import java.io.*;
import java.util.*;
/*

 * A kD tree (otherwise known as a kd-tree, or k-Dimension tree) is a data structure 
.
 * It is a binary-search tree that is specialised for coordinate searching, 
 * and is useful for answering questions such as which pub is closest to my current location?
 * @author Kestin

 */


public class FindNeighbors {
    Node root;
    int size;
    int querynum;
    MaxPQ<Pair> nns = new MaxPQ<>();
    
    private class Node{
        private Point2D point=null;
        private int depth;
        private Node left=null;
        private Node right=null;
        public Node parent;
        
        public Node(Point2D p){
            this.point=p;
        }
        
        public Node getright(){
            return this.right;
        }
        
        public Node getleft(){
            return this.left;
        }
        
        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }
        
      
    }
    
    public static class Pair implements Comparable<Pair>{
        Double dis;
        Point2D A,B;
        
        
        public Pair(Point2D a,Point2D b){
            this.A=a;
            this.B=b;
            this.dis=a.distanceTo(b);
        }

        
        private Double Distance(){
            return dis;
        }
        
        private Point2D getPairofQuery(){
            return B;
        }
        
        
        public int compareTo(Pair that){
            Double ans = that.Distance();
            if(this.dis>=ans) return +1;
            if(this.dis<ans) return -1;
            return 0;
        }
        
        
    }
    
    private FindNeighbors(){};
    

    public void init(Point2D[] points){
        Arrays.sort(points,Point2D.X_ORDER);
        root = new Node(points[points.length/2]);
        root.depth=0;
        size=points.length;
        points[points.length/2]=null;
        for(int run=0 ;run<points.length;run++){
            if(points[run]==null) continue;
            else {
                build_kd_tree(points[run], root);
               
            }
        }
        return;
    }

    
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        querynum=k;
        nns.insert(new Pair(point,root.point));
        search_kd_tree(point,root,k);       
        
        for(int vomit=k-1;vomit>-1;vomit--){
            System.out.println(vomit);
            result[vomit]=nns.delMax().getPairofQuery();
        }
        
        
        return result;
        // the points should be sorted accordingly to their distances to the query, from small to large
    }
    
    
    private void build_kd_tree(Point2D points, Node PK){
        
        int D = PK.depth;

        switch(D%2){
            
            case 0:
                //System.out.println(""EVEN"");
                if (points.x()>PK.point.x()){
                    Node next = PK.getright();
                    if(next!=null) build_kd_tree(points,next);
                    else {
                        PK.setRight(new Node(points));
                        PK.right.parent=PK;
                        PK.right.depth = D+1;
                    }
                }
                else{
                    Node next = PK.getleft();
                    if(next!=null) build_kd_tree(points,next);
                    else {
                        Node in =new Node(points);
                        PK.setLeft(in);
                        PK.left.parent=PK;
                        in.depth = D+1;
                    }
                }
            break;
                   
            case 1:
                //System.out.println(""ODD"");
                if (points.y()>PK.point.y()){
                    Node next = PK.getright();
                    if(next!=null) build_kd_tree(points,next);
                    else {
                        PK.setRight (new Node(points));
                        PK.right.parent=PK;
                        PK.right.depth = D+1;
                    }
                }
                else{
                    Node next = PK.getleft();
                    if(next!=null) build_kd_tree(points,next);
                    else {
                        PK.setLeft(new Node(points));
                        PK.left.parent=PK;
                        PK.left.depth = D+1;
                    }
                }
            break;
                             
        }
    
    }
    
    
    private void search_kd_tree(Point2D p, Node n,int k){
        //System.out.println(n.point.x()+"",""+n.point.y());
        int D = n.depth;
        Pair thispair = new Pair(p, n.point);
        // Check the query point in the existed point side
        switch(D%2){
            case 0:
                if (whichside(p.x(),n.point.x())==+1){
                    if (n.getright()!=null) search_kd_tree(p,n.getright(),k);
                }
               
                else{
                    if(n.getleft()!=null) search_kd_tree(p,n.getleft(),k);
                    
                }
                              
            break;
                             
            case 1:
                if (whichside(p.y(),n.point.y())==+1){
                    if(n.getright()!=null) search_kd_tree(p,n.getright(),k);
                }
                else {
                    if(n.getleft()!=null) search_kd_tree(p,n.getleft(),k);
                }

            break;
        }
        
        int flag=0;
        if (nns.size()>=k){
            
            Point2D boundary=null;
            
            switch(D%2){
                case 0:
                    boundary = new Point2D(p.x(),n.point.y());
                case 1:
                    boundary = new Point2D(n.point.x(),p.y());                   
            }
            Pair vomit = nns.max();
            Pair input = new Pair(p, boundary);
            
            if(input.compareTo(vomit)==+1) flag=1;
            else;
            if(thispair.compareTo(vomit)==-1) {
                nns.delMax();
                nns.insert(thispair);            
            }           
        }            
        else {
            nns.insert(thispair);
        }
        
        if(flag==1) return;
        
        switch(D%2){
            case 0:
                if (whichside(p.x(),n.point.x())==-1){
                    if (n.getright()!=null) search_kd_tree(p,n.getright(),k);
                }
               
                else{
                    if(n.getleft()!=null) search_kd_tree(p,n.getleft(),k);
                    
                }
                              
            break;
                             
            case 1:
                if (whichside(p.y(),n.point.y())==-1){
                    if(n.getright()!=null) search_kd_tree(p,n.getright(),k);
                }
                else {
                    if(n.getleft()!=null) search_kd_tree(p,n.getleft(),k);
                }

            break;
        } 
        
        return;
        
    }
    
    private int whichside(double a, double b){
        if(a>b) return+1;
        if(a<b) return -1;
        return 0;
    }
    
    
    
     public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            Point2D[] PointsArray= new Point2D[20];
            int pos=0;           
            for( String coordinate;( coordinate = br.readLine()) != null; ){
                String[] cor=coordinate.split("" "");
                Double x = Double.parseDouble(cor[0]);
                Double y = Double.parseDouble(cor[1]);
                Point2D p = new Point2D(x, y);
                PointsArray[pos]=p;
                pos++;
            }
            FindNeighbors test = new FindNeighbors();
            
            test.init(PointsArray);
            Point2D give = new Point2D(0.1354339200,0.7019446863);
            test.query(give, 3);
            //System.out.println(""testover"");
        }
    }

}

