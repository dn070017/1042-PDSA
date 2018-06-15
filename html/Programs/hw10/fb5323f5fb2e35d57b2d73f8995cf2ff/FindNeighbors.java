//package findneighbors;

import static java.lang.Math.abs;
import java.util.PriorityQueue;

public class FindNeighbors {
    public Node root;
    public PriorityQueue<Node> nodePQ = new PriorityQueue();
    public int k;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        int l = points.length;
        boolean horizontal = true;
        for (int i = 0 ; i < l ; i++){
            if (i == 0)
                root = new Node(null, null, points[i], horizontal);
            else{
                Node newNode = new Node(null, null, points[i], horizontal);
                recur(root, newNode);
            }
            horizontal = !horizontal;
        }
        
    }
    
    public void recur(Node parent, Node child){
        boolean parentIsLarger;
        if (parent.horizontal)
            parentIsLarger = parent.getValue().y() > child.getValue().y();
        
        else
            parentIsLarger = parent.getValue().x() > child.getValue().x();
        
        if (parentIsLarger){
                if (parent.getLeft() == null)
                    parent.setLeft(child);
                else
                    recur(parent.getLeft(), child);
            }
        else{
                if (parent.getRight() == null)
                    parent.setRight(child);
                else
                    recur(parent.getRight(), child);
            }
        /*if (parent != null)
            System.out.println(""value""+parent.getValue());
        if (parent.getLeft()!=null)
            System.out.println(""left ""+parent.getLeft().getValue());
        if (parent.getRight()!=null)
            System.out.println(""right""+parent.getRight().getValue());*/
    }


    // the result should be sorted accordingly to their distances to the query, from small to large

    public Point2D[] query(Point2D point, int k){
        this.k = k;
        Point2D[] result = new Point2D[k];
        queryRecur(root, point);
        int N = nodePQ.size();
        for (int i = 0 ; i < N ; i++){
            result[nodePQ.size()-1] = nodePQ.poll().getValue();
        }
        return result;
    }
    public void checkShouldSearch(Node parent, Point2D point){
        if (this.nodePQ.size() >= this.k){
            
            if (parent.horizontal && parent.getLeft()!= null && this.nodePQ.peek().distanceToQuery < abs(parent.getLeft().getValue().y() - point.y()))
                parent.getLeft().shouldSearch = false;
            else if (!parent.horizontal && parent.getLeft()!= null && this.nodePQ.peek().distanceToQuery < abs(parent.getLeft().getValue().x() - point.x()))
                parent.getLeft().shouldSearch = false;
            
            if (parent.horizontal && parent.getRight()!= null && this.nodePQ.peek().distanceToQuery < abs(parent.getRight().getValue().y() - point.y()))
                parent.getRight().shouldSearch = false;
            else if (!parent.horizontal && parent.getRight()!= null && this.nodePQ.peek().distanceToQuery < abs(parent.getRight().getValue().x() - point.x()))
                parent.getRight().shouldSearch = false;
        }
            
    }
    public void queryRecur(Node parent, Point2D point){
        parent.distanceToQuery = parent.getValue().distanceTo(point);
        
        if (this.nodePQ.size() < this.k)
            nodePQ.add(parent);
        else if (parent.distanceToQuery < nodePQ.peek().distanceToQuery){
            nodePQ.add(parent);
            nodePQ.poll();
        }
        

        
        boolean parentIsLarger;
        
        if (parent.horizontal)
            parentIsLarger = parent.getValue().y() > point.y();
        else
            parentIsLarger = parent.getValue().x() > point.x();
        
        if (parentIsLarger){
            if (parent.getLeft() != null && parent.getLeft().shouldSearch)
                queryRecur(parent.getLeft(), point);
            checkShouldSearch(parent, point);
            if (parent.getRight() != null && parent.getRight().shouldSearch)
                queryRecur(parent.getRight(), point);
        }
        else{
            if (parent.getRight() != null && parent.getRight().shouldSearch)
                queryRecur(parent.getRight(), point);
            checkShouldSearch(parent, point);
            if (parent.getLeft() != null && parent.getLeft().shouldSearch)
                queryRecur(parent.getLeft(), point);
        }
    }
    class Node implements Comparable<Node>{
        public Node left;
        public Node right;
        public Point2D value;
        public boolean horizontal;
        public boolean shouldSearch;
        public double distanceToQuery;

        public Node(Node left, Node right, Point2D value, boolean horizontal){
            this.left = left;
            this.right = right;
            this.value = value;
            this.horizontal = horizontal;
            this.shouldSearch = true;
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

        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }

        public void setValue(Point2D value){
            this.value = value;
        }

        @Override
        public int compareTo(Node that){
            if (this.distanceToQuery > that.distanceToQuery)
                return -1;
            else if (this.distanceToQuery == that.distanceToQuery)
                return 0;
            else
                return +1;
        }
    }
    public static void main(String[] args){
        FindNeighbors fd = new FindNeighbors();
        Point2D[] Ps = new Point2D[10];
        for (int i = 0 ; i < 10 ; i++)
            Ps[i] = new Point2D(i, i);
        fd.init(Ps);
        Point2D q = new Point2D(5.1, 5.1);
        Point2D[] result = fd.query(q, 3);
        /*for (Point2D r : result)
            System.out.println(r);*/
    }
}




