import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
public class FindNeighbors {
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    Node root=new Node(null,null,null,true);    
    int N;
    MaxPQ <Node>gg=new MaxPQ();
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        //需要一組座標來存當下方塊的xy
        double x,y;
        N=points.length;
        root.setPoint(points[0]);
        for(int i=1;i<N;i++){
            Node.insert(root,points[i]);
        }
        return;
    }
    private void query(Node last,Point2D goal,int k,double min){
        if(last==null) return;
        last.setdistance(goal);
        gg.insert(last);
        
        if(gg.size()>k){
            gg.delMax();
            Node temp=gg.delMax();
            min=temp.getdistance();
            gg.insert(temp);
        }
       // 先找正確順序
        query(last.getNext(goal),goal,k,min);
        //如果min比goal到Node的垂直距離還長，才需要找另一邊的樹
        if(gg.size()<k){
            query(last.getOp(goal),goal,k,min);
        }else if(last.XorY()==true){
            if(min>Math.abs(goal.x()-last.getPoint().x())){
                query(last.getOp(goal),goal,k,min);
            }
        }else if(last.XorY()==false){
            if(min>Math.abs(goal.y()-last.getPoint().y())){
                query(last.getOp(goal),goal,k,min);
            }
        }
        while(gg.size()>k) gg.delMax();
        
        return;
    }
    public Point2D[] query(Point2D goal, int k){
        Point2D[] result = new Point2D[k];
        
        Node temp=root;
        
        query(temp,goal,k,Double.MAX_VALUE);
        
        Node[] haha=new Node[k];
        for(int i=0;i<k;i++){
            haha[i]=gg.delMax();
        }
        for(int i=0;i<k;i++) {
            result[i]=haha[k-1-i].getPoint();
        }
        
        return result;
    }
    public static void main(String[] args)  throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(""input.sat""))){
        FindNeighbors gg=new FindNeighbors();
        Queue<Point2D> read=new Queue();
        while(br.ready()){
            String[] tt=new String[2];
            tt=br.readLine().split("" "");
            Point2D a=new Point2D(Double.parseDouble(tt[0]),Double.parseDouble(tt[1]));        
            read.enqueue(a);
        }
        int N=read.size();
        Point2D[] points=new Point2D[N];
        for(int i=0;i<N;i++){
            points[i]=read.dequeue();
        }
        gg.init(points);
        //哪三個點跟這個b點最近
        Point2D b=new Point2D(0.531440,0.616918 );
        int z=10;
        Point2D[]haha=gg.query(b,z);
        for(int i=0;i<z;i++){
            System.out.print(haha[i].x());
            System.out.print("" "");
            System.out.print(haha[i].y());
            System.out.println();
        }
    }
    }
}

class Node implements Comparable<Node>{
    private Node left;
    private Node right;
    private Point2D point;
    private boolean eigen;
    private double distance=0;
    
    public void setdistance(Point2D a){
        distance=point.distanceTo(a);
    }
    public double getdistance(){
        return distance;
    }
    public Node(Node left, Node right, Point2D value, boolean a){
        this.left = left;
        this.right = right;
        this.point = value;
        //true為直線false為橫線
        this.eigen=a;
    }
    
    public Node(Point2D value, boolean a){
        this.point = value;
        //true為直線false為橫線
        this.eigen=a;
    }
    
    public int compareTo(Node that){
        if(this.distance>that.distance)return 1;
        else return -1;
    }
    
    static public void insert(Node root,Point2D a){
        Node temp=root;
        Node last=null;
        while(temp!=null){
            if(temp.eigen==true){
                if(temp.getPoint().x()>a.x()){
                    last=temp;
                    temp=temp.getLeft();
                }else if(temp.getPoint().x()<a.x()){
                    last=temp;
                    temp=temp.getRight();
                }
            }else if(temp.eigen==false){
                if(temp.getPoint().y()>a.y()){
                    last=temp;
                    temp=temp.getLeft();
                }else if(temp.getPoint().y()<a.y()){
                    last=temp;
                    temp=temp.getRight();
                }
            }
        }
        //檢查上一個是橫的還直的
        if(last.eigen==true){//這個是直的
            temp=new Node(a,false);
            if(last.getPoint().x()>a.x()){//如果前一個比他右邊 把它放在前一個左邊
                last.setLeft(temp);
            }else{
                last.setRight(temp);
            }
        }else{//這個是橫的
            temp=new Node(a,true);
            if(last.getPoint().y()>a.y()){//如果前一個比他右邊 把它放在前一個左邊
                last.setLeft(temp);
            }else{
                last.setRight(temp);
            }
        }
    }
    
    public Node getNext(Point2D goal){
        if(this.left==null&&this.right==null) return null;
        if(eigen==true){
            if(this.point.x()<goal.x()){
                return this.getRight();
            }else return this.getLeft();
        }else{
            if(this.point.y()<goal.y()){
                return this.getRight();
            }else return this.getLeft();
        }
    }
    
    public Node getOp(Point2D goal){
        if(this.left==null&&this.right==null) return null;
        if(eigen==true){
            if(this.point.x()>goal.x()){
                return this.getRight();
            }else return this.getLeft();
        }else{
            if(this.point.y()>goal.y()){
                return this.getRight();
            }else return this.getLeft();
        }
    }
    
    public Node getLeft(){
        return(this.left);
    }

    public Node getRight(){
        return(this.right);
    }
    public boolean XorY(){
        return(this.eigen);
    }
    public Point2D getPoint(){
        return(this.point);
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setPoint(Point2D value){
        this.point= value;
    }
    
}
