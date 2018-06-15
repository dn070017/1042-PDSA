/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Sophia
 */
class Tree{
    Node root;
    Tree(){
        root = new Node(null,null,null,0);
    }
    public void insert (Node node){
        Node now;
        Node next;
        now = root;
        next = now;
        int l = 0;
        if(root.value == null){
            root = node;
        }else{
            while(next != null){
            if(now.level%2 == 0){
                if(node.value.x()>now.value.x()){
                    next = now.getRight();
                    if(next == null){
                        node.level = l;
                        now.setRight(node);
                        break;
                    }
                }else{
                    next = now.getLeft();
                    if(next == null){
                        node.level = l;
                        now.setLeft(node);
                        break;
                    }
                }            
            }else{
                if(node.value.y()>now.value.y()){
                    next = now.getRight();
                    if(next == null){
                        node.level = l;
                        now.setRight(node);
                        break;
                    }
                }else{
                    next = now.getLeft();
                    if(next == null){
                        node.level = l;
                        now.setLeft(node);
                        break;
                    }
                }
            }
            l++;
            now = next;
        }
        }
        //System.out.println(node.value.x());
    }
}

class Node{
    private Node left;
    private Node right;
    public int level;
    public Point2D value;

    public Node(Point2D value,Node left, Node right, int level){
        this.left = left;
        this.right = right;
        this.value = value;
        this.level = 0;
    }
    public Node(Point2D value){
        this(value,null,null,0);
    }
    public Node getLeft(){
        return(this.left);
    }
    public Node getRight(){
        return(this.right);
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
    public double distanceTo(Point2D that) {
        double dx = this.value.x() - that.x();
        double dy = this.value.y() - that.y();
    return Math.sqrt(dx*dx + dy*dy);
    }
}
class Pair implements Comparable<Pair>{
    Node a;
    Point2D b;
    double d;
    
    Pair(Node a, Point2D b){
        this.a = a;
        this.b = b;
        d = a.distanceTo(b);
    }

    @Override
    public int compareTo(Pair q) {
        if (this.d < q.d) return -1;
            if (this.d > q.d) return +1;
        return 0;
    }
}


    public class FindNeighbors {
        Tree tree;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        this.tree = new Tree();
        for(int i =0; i<points.length;i++){
            Node node = new Node(points[i]);
            tree.insert(node);
        }
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        MaxPQ<Pair> pairs = new MaxPQ<Pair>();
        Node now = tree.root;
        put(point, pairs, k ,now);
        for(int i = 0; i<k; i++){
           if(pairs.size() == 0){
               break;
           }
           result[k-i-1] = pairs.max().a.value;
           pairs.delMax();
        }       
        return result;
    }
    
    public void put(Point2D point,MaxPQ<Pair>pairs,int k, Node now){
        Node next;
        //System.out.print(now.value.x());
        if(now != null){
            if(now.level%2 == 0){                
                if(point.x()>now.value.x()){
                    //System.out.print(""parta"");
                    next = now.getRight();
                    put(point, pairs, k ,next);
                    
                    if(pairs.size()<k){
                        Pair pair = new Pair(now,point);
                        pairs.insert(pair);
                        next = now.getLeft();
                        put(point, pairs, k ,next);
                    }else{
                        if((now.value.x()-point.x())*(now.value.x()-point.x())<=pairs.max().d*pairs.max().d){
                            Pair pair = new Pair(now,point);
                            pairs.insert(pair);
                            pairs.delMax();
                            next = now.getLeft();
                            put(point, pairs, k ,next);
                        }
                    }
                }else{
                    //System.out.print(""partb"");
                    next = now.getLeft();
                    //System.out.print(next);
                    put(point, pairs, k ,next);
                    if(pairs.size()<k){
                        Pair pair = new Pair(now,point);
                        pairs.insert(pair);
                        next = now.getRight();
                        put(point, pairs, k ,next);
                    }else{
                        if((now.value.x()-point.x())*(now.value.x()-point.x())<=pairs.max().d*pairs.max().d){
                            Pair pair = new Pair(now,point);
                            pairs.insert(pair);
                            pairs.delMax();
                            next = now.getRight();
                            put(point, pairs, k ,next);
                        }
                    }
                    
                }            
            }else{
                if(point.y()>now.value.y()){
                    //System.out.print(""partc"");
                    next = now.getRight();
                    put(point, pairs, k ,next);
                    
                    if(pairs.size()<k){
                        
                        Pair pair = new Pair(now,point);
                        pairs.insert(pair);
                        next = now.getLeft();
                        put(point, pairs, k ,next);
                    }else{
                        if((now.value.y()-point.y())*(now.value.y()-point.y())<=pairs.max().d*pairs.max().d){
                            Pair pair = new Pair(now,point);
                            pairs.insert(pair);
                            pairs.delMax();
                            next = now.getLeft();
                            put(point, pairs, k ,next);
                        }
                    }
                }else{
                    //System.out.print(""partd"");
                    next = now.getLeft();
                    put(point, pairs, k ,next);
                    if(pairs.size()<k){
                        Pair pair = new Pair(now,point);
                        pairs.insert(pair);
                        next = now.getRight();
                        put(point, pairs, k ,next);
                    }else{
                        if((now.value.y()-point.y())*(now.value.y()-point.y())<=pairs.max().d*pairs.max().d){
                            Pair pair = new Pair(now,point);
                            pairs.insert(pair);
                            pairs.delMax();
                            next = now.getRight();
                            put(point, pairs, k ,next);
                        }
                    }
                    
                }
            }
        }else{
            return;
        }
        
    }
    
     public static void main(String[] args){
         Point2D[] points = new Point2D[5];
         Point2D[] ans;
         points[0] = new Point2D(0.3833339428,0.1459115606);
         points[1] = new Point2D(0.3426077152,0.7218207763);
         points[2] = new Point2D(0.1171932327,0.6117403964);
         points[3] = new Point2D(0.2126349801,0.4842532332);
         points[4] = new Point2D(0.0447830281,0.2155560961);
         Point2D p = new Point2D(0.1354339200,0.7019446863);
         FindNeighbors f = new FindNeighbors();
         f.init(points);
         ans = f.query(p,3);
         for(int i = 0; i<ans.length; i++){
             //System.out.print(ans[i]);
         }
     
     }

}


