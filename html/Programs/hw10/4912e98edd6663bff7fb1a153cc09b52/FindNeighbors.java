
import java.util.Comparator;



public class FindNeighbors {
    
    private Node root;

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        root = new Node(1,null,null,points[0]);
        
        for(int i = 1; i < points.length;i++){
            root.getNext(new Node(1,null,null,points[i]));
        } 
    }
    
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        
        MaxPQ<Node> pq = new MaxPQ<>(k+1,new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if(n1.distance > n2.distance)
                    return 1;
                else if(n1.distance < n2.distance)
                    return -1;
                else
                    return 0;
            }
        });        
        
        getquery(pq,root,point,k,Double.MAX_VALUE);
        
        
        for(int i = k-1; i >= 0; i--)
            result[i] = pq.delMax().getValue();        
        return result;
    }
    
    public void getquery(MaxPQ<Node> pq, Node n, Point2D target, int k, double min_d ){
        if(n == null) return;
        
        n.setDistance(target);
        pq.insert(n);
            
        if(pq.size() > k){
        pq.delMax();
        Node temp= pq.delMax();
        min_d = temp.distance;
        pq.insert(temp);
        }
        
        getquery(pq,n.findNext(n),target,k,min_d);
        
        if(pq.size() < k){
            getquery(pq,n.findOps(n),target,k,min_d);}
        else if((n.getflag() == 1)&&(Math.abs(target.x() - n.getValue().x()) < min_d)){
            getquery(pq,n.findOps(n),target,k,min_d);}
        else if((n.getflag() == 0)&&(Math.abs(target.y() - n.getValue().y()) < min_d)){
            getquery(pq,n.findOps(n),target,k,min_d);}        
    }
    
    public class Node{
    private int flag;    
    private Node left;
    private Node right;
    private Point2D value;
    double distance;
    
    public Node findNext(Node in){
        if(this.flag == 1){
            if(in.getValue().x() < this.value.x()){
                return this.left;
            }else{
                return this.right;
            }            
        }
        else{
            if(in.getValue().y() < this.value.y()){
                return this.left;
            }else{
                return this.right;
            }                        
        }        
    }

    public Node findOps(Node in){
        if(this.flag == 1){
            if(in.getValue().x() < this.value.x()){
                return this.right;
            }else{
                return this.left;
            }            
        }
        else{
            if(in.getValue().y() < this.value.y()){
                return this.right;
            }else{
                return this.left;
            }                        
        }        
    }    
    
    public void getNext(Node in){
        
        if(this.flag == 1){
            in.setflag(0);
            if(in.getValue().x() < this.value.x()){
                if(this.left == null){
                    this.setLeft(in);
                }else
                this.left.getNext(in);
            }else{
                if(this.right == null){
                    this.setRight(in);
                }else
                this.right.getNext(in);
            }            
        }
        else{
            in.setflag(1);
            if(in.getValue().y() < this.value.y()){
                if(this.left == null){
                    this.setLeft(in);
                }else
                this.left.getNext(in);
            }else{
                if(this.right == null){
                    this.setRight(in);
                }else
                this.right.getNext(in);
            }                        
        }
    }

    public Node(int flag, Node left, Node right, Point2D value){
        this.flag  = flag;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getflag(){
        return(this.flag);
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

    public void setflag(int flag){
        this.flag  = flag;
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

    public void setDistance(Point2D point){
        this.distance = point.distanceTo(this.getValue());
    }

    }
    
    public static void print2D(Point2D pf){
            System.out.printf(""( %.2f , %.2f )\n"",pf.x(),pf.y());     
    }           

}


