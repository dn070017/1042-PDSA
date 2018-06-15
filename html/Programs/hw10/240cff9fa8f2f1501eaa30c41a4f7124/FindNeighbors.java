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
        now = root;
        int l = 0;
        while(now.value != null){
            if(now.level%2 == 0){
                if(node.value.x()>now.value.x()){
                    now = now.getRight();
                }else{
                    now = now.getLeft();
                }            
            }else{
                if(node.value.y()>now.value.y()){
                    now = now.getRight();
                }else{
                    now = now.getLeft();
                }
            }
            l++;
        }
        now = node;
        now.level = l;
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
           result[k-i-1] = pairs.max().b;
           pairs.delMax();
        }
        
        return result;
    }
    
    public void put(Point2D point,MaxPQ<Pair>pairs,int k, Node now){
        Node next;
        if(now.value != null){
            if(now.level%2 == 0){
                if(point.x()>now.value.x()){
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
                    next = now.getLeft();
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
     
     
     }
