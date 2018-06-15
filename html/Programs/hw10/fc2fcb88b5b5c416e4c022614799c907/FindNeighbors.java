import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

public class FindNeighbors {

    public class Node {

        private Node lb;
        private Node rt;
        private int level;
        private Point2D point;
       

        public Node(Node lb, Node rt, Point2D point) {
            this.lb = lb;
            this.rt = rt;
            this.level=1;
            this.point = point;
       
        }

        public Node getlb() {
            return (this.lb);
        }

        public Node getrt() {
            return (this.rt);
        }

        public Point2D getpoint() {
            return (this.point);
        }
        
        public int getlevel() {
            return (this.level);
        } 
        
        public void setlb(Node lb) {
            this.lb = lb;
        }

        public void setrt(Node rt) {
            this.rt = rt;
        }

        public void setpoint(Point2D point) {
            this.point = point;
        }
        
        public void setlevel(int i) {
            this.level=i;
        }
        

    }
    private Node root;
    // DO NOT MODIFY THE CONSTRUCTOR! 

    public FindNeighbors() {
    }
    
    public void insert(Node node,Point2D point){
        if(node.getlevel()%2==1){
            if(node.getpoint().x()>point.x()){
                if(node.getlb()==null){
                    int i=node.getlevel();
                    Node n=new Node(null,null,point);
                    n.setlevel(i+1);
                    node.setlb(n);
                }else{
                    insert(node.getlb(),point);
                }
            }else{
                if(node.getrt()==null){
                    int i=node.getlevel();
                    Node n=new Node(null,null,point);
                    n.setlevel(i+1);
                    node.setrt(n);
                }else{
                    insert(node.getrt(),point);
                }
            }
        }else{
            if(node.getpoint().y()>point.y()){
                if(node.getlb()==null){
                    int i=node.getlevel();
                    Node n=new Node(null,null,point);
                    n.setlevel(i+1);
                    node.setlb(n);
                }else{
                    insert(node.getlb(),point);
                }
            }else{
                if(node.getrt()==null){
                    int i=node.getlevel();
                    Node n=new Node(null,null,point);
                    n.setlevel(i+1);
                    node.setrt(n);
                }else{
                    insert(node.getrt(),point);
                }
            }
        }
    }
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        Node node=new Node(null,null,points[0]);
        root=node;
        for(int i=1;i<points.length;i++){
            insert(root,points[i]);
        }
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public double calculate(Point2D point1,Point2D query){
        double ans=Math.pow(Math.pow(point1.x()-query.x(), 2)+Math.pow(point1.y()-query.y(), 2), 0.5);
        return ans;
    }
    
    public class Target implements Comparable<Target> {

        Point2D point;
        double dist;

        public Target(Point2D point, double dist) {
            this.point = point;
            this.dist = dist;
        }

        public int compareTo(Target that) {
            if (this.dist > that.dist) {
                return +1;
            } else if (this.dist < that.dist) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    
    
    public void find(Node node ,Point2D point,int k,MaxPQ<Target> p){
       
        p.insert(new Target(node.getpoint(),calculate(node.getpoint(),point)));
        if(p.size()>k){
            p.delMax();
        }
        if(node.getlevel()%2==1){
            if(node.getpoint().x()>point.x()){
                
                if(node.getlb()!=null){
                    find(node.getlb(),point,k,p);
                }
                if(p.max().dist>node.getpoint().x()-point.x()){
                    if(node.getrt()!=null){
                        find(node.getrt(),point,k,p);
                    }
                }
            }else{
                if(node.getrt()!=null){
                    find(node.getrt(),point,k,p);
                }
                if(p.max().dist>point.x()-node.getpoint().x()){
                    if(node.getlb()!=null){
                        find(node.getlb(),point,k,p);
                    }
                }
            }  
        }else{
            if(node.getpoint().y()>point.y()){
                
                if(node.getlb()!=null){
                    find(node.getlb(),point,k,p);
                }
                if(p.max().dist>node.getpoint().y()-point.y()){
                    if(node.getrt()!=null){
                        find(node.getrt(),point,k,p);
                    }
                }
            }else{
                if(node.getrt()!=null){
                    find(node.getrt(),point,k,p);
                }
                if(p.max().dist>point.y()-node.getpoint().y()){
                    if(node.getlb()!=null){
                        find(node.getlb(),point,k,p);
                    }
                }
            }  
        }
    }
    
    public Point2D[] query(Point2D point, int k) {
        MaxPQ<Target> p=new MaxPQ<Target>();
//        p =new MaxPQ<Target>();
        
        find(root,point,k,p);
        
        Point2D[] result = new Point2D[k];
        for(int i=0;i<k;i++){
         result[k-i-1]=p.delMax().point;
        }
        return result;
    }

    /*public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {

            LinkedQueue<Point2D> q = new LinkedQueue<Point2D>();
            int count = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] Num = in.split("" "");
                double d1, d2;
                d1 = Double.parseDouble(Num[0]);
                d2 = Double.parseDouble(Num[1]);
                Point2D a = new Point2D(d1, d2);
                q.enqueue(a);
            }
            Point2D[] points = new Point2D[q.size()];
            int num = q.size();
            for (int i = 0; i < num; i++) {
                points[i] = q.dequeue();
                StdDraw.setPenColor(Color.black);
                StdDraw.setPenRadius(0.01);
                points[i].draw();
            }*/
            /*Expression expression = new Expression();
            String infix = br.readLine();
            expression.Infix2BT(infix);*/
    
            /*FindNeighbors f= new FindNeighbors();
            f.init(points);
            Point2D point=new Point2D(0.1354339200,0.7019446863);
            Point2D[] array=f.query(point, 3);
            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+""%n"");
            }
        }
    }*/

}
