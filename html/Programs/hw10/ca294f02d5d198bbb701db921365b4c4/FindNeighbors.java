
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;

public class FindNeighbors {
    private Node top;
    
    public class Node implements Comparable<Node>{

        private Node left;
        private Node right;
        private Point2D setpoint;

        public Node(Node left, Node right, Point2D value) {
            this.left = left;
            this.right = right;
            this.setpoint = value;
        }


        @Override
        public int compareTo(Node that) {
            if(target.distanceTo(this.setpoint) < target.distanceTo(that.setpoint)){
                return -1;
            }
            else if(target.distanceTo(this.setpoint) < target.distanceTo(that.setpoint)){
                return 1;
            }
            else{
                return 0;
            }
        }


    }

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        top = new Node(null, null, points[0]);
        Node temp =top;
               
        for(int m = 1;m < points.length;m++) {
            Node set = new Node(null, null, points[m]);
            while (true) {
                if (points[m].x() < temp.setpoint.x()) {
                    if (temp.left == null) {
                        temp.left = set;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } 
                else{ 
                    if (temp.right == null) {
                        temp.right = set;
                        break;
                    } 
                    else {
                        temp = temp.right;
                    }
                }
                if (points[m].y() < temp.setpoint.y()) {
                    if (temp.left == null) {
                        temp.left = set;
                        break;
                    } 
                    else {
                        temp = temp.left;
                    }
                } 
                else{ 
                    if (temp.right == null) {
                        temp.right = set;
                        break;
                    } 
                    else {
                        temp = temp.right;
                    }
                }
            }
        }
        return;
    }
    
//    private Node put(Node x){
//        
//    
//    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    
    MaxPQ<Node> set;
    Point2D target;
    int num;
    
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        set = new MaxPQ<>();
        target = point;
        num=k;
        
        near(top);
        
        for(int i=k-1;i>=0;i--){
            result[i]=set.delMax().setpoint;
        }
        
        return result;
    }
    boolean odd=false;
    
    private void near(Node run){
        if(run==null) return;
            if (!odd) {
                odd=true;
                
                if (target.x() < run.setpoint.x()) {
                    set.insert(run);
                    
                    while(set.size()>num){
                        set.delMax();
                    }
                    near(run.left);
                    if (run.right==null){
                        return ;
                    }
                    
                    Node temp =  set.delMax();
                    if (sqrt(target.distanceTo(temp.setpoint)) < sqrt(target.x()-run.setpoint.x())) {
                        set.insert(temp);
                        return ;
                    }
                    
                    odd=true;
                    near(run.right);
                    
                } else {
                    set.insert(run);
                    while(set.size()>num){
                        set.delMax();
                    }
                    near(run.right);
                    if (run.left==null) return;
                    Node temp =  set.delMax();
                    if (sqrt(target.distanceTo(temp.setpoint)) < sqrt(target.x()-run.setpoint.x())) {
                        set.insert(temp);
                        return ;
                    }

                    odd=true;
                    near(run.left);
                }
            }
            else{
                odd=false;
             if (target.y() < run.setpoint.y()) {
                    set.insert(run);
                    while(set.size()>num){
                        set.delMax();
                    }
                    near(run.left);
                    if (run.right==null) return;
                    
                    Node temp =  set.delMax();
                    if (sqrt(target.distanceTo(temp.setpoint)) < sqrt(target.y()-run.setpoint.y())) {
                        set.insert(temp);
                        return ;
                    }

                    odd=false;
                    near(run.right);
                    
                } else {
                    
                    set.insert(run);
                    while(set.size()>num){
                        set.delMax();
                    }                    
                    near(run.right);
                    if (run.left==null)return;
                    
                    Node temp =  set.delMax();
                    if ( sqrt(target.distanceTo(temp.setpoint)) < sqrt(target.x()-run.setpoint.x())) {
                        set.insert(temp);
                        return ;
                    }
                    odd=false;
                    near(run.left);
                }
            }
        return;
    }


   
}

