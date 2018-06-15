
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    Node root=new Node(null,null,null,true);
    
   int N;
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

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D goal, int k){
        Point2D[] result = new Point2D[k];
        MaxPQ <Node>gg=new MaxPQ(k);
        Node temp=root;
        
        //開始找距離，每找到一個丟入MaxPQ，記得size上限=k
        while(temp!=null){
            temp.setdistance(goal);
            gg.insert(temp);
            if(temp.XorY()==true){
                if(goal.x()>temp.getPoint().x()){
                    temp=temp.getRight();
                }else temp=temp.getLeft();
            }else if(temp.XorY()==false){   
                if(goal.y()>temp.getPoint().y()){
                    temp=temp.getRight();
                }else temp=temp.getLeft();
            }
            if(gg.size()>k){
                gg.delMax();
            }
        }
        Node[] haha=new Node[k];
        for(int i=0;i<k;i++){
            haha[i]=gg.delMax();
        }
        Arrays.sort(haha);
        for(int i=0;i<k;i++) {
            result[i]=haha[i].getPoint();
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
        Point2D b=new Point2D(0.1354339200,0.7019446863);
        Point2D[]haha=gg.query(b,3);
        for(int i=0;i<3;i++){
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
