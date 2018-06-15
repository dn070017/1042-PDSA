
import java.util.Comparator;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    
    class Node{
    private Node left;
    private Node right;
    private Point2D value;
    private int level;

    public Node(Node left, Node right, Point2D value,int level){
        this.left = left;
        this.right = right;
        this.value = value;
        this.level = level;
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
    public int getLevel(){
        return(this.level);
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
    public void setLevel(int level){
        this.level = level;
    }}
    
    private Node root;
    int countLevel = 0;
    public void init(Point2D[] points){
        countLevel = 0;
        Point2D[] PointArray = points;
        Node a = new Node(null,null,PointArray[0],countLevel);
        root = a;
        for(int i = 1;i < PointArray.length;i++){
            countLevel = 0;
            BuildTree(root,PointArray[i]);
        }
        //System.out.println(root.right.right.value.x()+"" ""+root.right.right.value.y()+"" ""+root.left.level);
    }
    private void BuildTree(Node x,Point2D newpoint){
        if(countLevel%2==0){
            if(newpoint.x()<x.value.x()){
                if(x.getLeft()==null)
                {
                    Node temp = new Node(null,null,newpoint,countLevel+1);
                    x.setLeft(temp);
                }
                else{
                    countLevel++;
                    BuildTree(x.getLeft(),newpoint);
                }}
            else{
                if(x.getRight()==null)
                {
                    Node temp = new Node(null,null,newpoint,countLevel+1);
                    x.setRight(temp);
                }
                else{
                    countLevel++;
                    BuildTree(x.getRight(),newpoint);
                }}  
        }
        else{
            if(newpoint.y()<x.value.y()){
                if(x.getLeft()==null){
                    Node temp = new Node(null,null,newpoint,countLevel+1);
                    x.setLeft(temp);
                }
                else{
                    countLevel++;
                    BuildTree(x.getLeft(),newpoint);
                }}
            else{
                if(x.getRight()==null){
                    Node temp = new Node(null,null,newpoint,countLevel+1);
                    x.setRight(temp);
                }
                else{
                    countLevel++;
                    BuildTree(x.getRight(),newpoint);
                }
            }
        }
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    class LinkedStackOfStrings
    {
        private littleNode first = null;
        private class littleNode
        {
            Node item;
            littleNode next;
        }
        public boolean isEmpty()
        { return first == null; }
        public void push(Node item)
        {
            littleNode oldfirst = first;
            first = new littleNode();
            first.item = item;
            first.next = oldfirst;
        }
        public Node pop()
        {
            Node item = first.item;
            first = first.next;
            return item;
        }
    }
    class RelationBST<Key extends Comparable<Key>, Value>{
        public RelationBST(){}
        private class Node{
            private Key key;
            private Value val;
            private Node left, right;
            public Node(Key key, Value val){
            this.key = key;
            this.val = val;}}
            private Node root;
            public void put(Key key, Value val)
            { root = put(root, key, val); }
            private Node put(Node x, Key key, Value val){
                if (x == null) return new Node(key, val);
                int cmp = key.compareTo(x.key);
                if (cmp < 0)
                x.left = put(x.left, key, val);
                else if (cmp > 0)
                x.right = put(x.right, key, val);
                else if (cmp == 0)
                x.val = val;
                return x;   }
            public Value get(Key key)
                {Node x = root;
                while (x != null){
                int cmp = key.compareTo(x.key);
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else if (cmp == 0) return x.val;}
                return null;}
            public void delete(Key key){ put(key,null); }
        
    }
    private void insert(Node x, Point2D point, int level) {
        if (x.getValue() == null) {
            x.setValue(point);
            x.setLevel(level);
            x.setLeft(new Node(null, null,null,level+1));
            x.setRight(new Node(null, null,null,level+1));
        } else {
            if (x.getLevel() % 2 == 0) {
                if (point.x() > x.getValue().x()) {
                    insert(x.getRight(), point, level + 1);
                } else {
                    insert(x.getLeft(), point, level + 1);
                }
            } else {
                if (point.y() > x.getValue().y()) {
                    insert(x.getRight(), point, level + 1);
                } else {
                    insert(x.getLeft(), point, level + 1);
                }
            }
        }
    }
    MaxPQ<Point2D> pq;
    private void findK(int depth, Point2D target, Node n, int k) {
        if (n != null && n.getValue() != null) {
            double dis = target.distanceTo(n.getValue());
            double kmindis;
            Point2D kminpt;
            if (pq.size() < k) {
                pq.insert(n.getValue());
            } else {
                kminpt = pq.delMax();
                kmindis = target.distanceTo(kminpt);
                if (dis < kmindis) {
                    pq.insert(n.getValue());
                } else {
                    pq.insert(kminpt);
                }
            }

            Node targetnode = new Node(null, null, null,n.getLevel()+1);
            Node remainnode = new Node(null, null, null,n.getLevel()+1);

            if (depth % 2 == 0) {
                if (target.x() < n.getValue().x()) {
                    targetnode = n.getLeft();
                    remainnode = n.getRight();
                } else {
                    targetnode = n.getRight();
                    remainnode = n.getLeft();
                }
            }else{
                if (target.y() < n.getValue().y()) {
                    targetnode = n.getLeft();
                    remainnode = n.getRight();
                } else {
                    targetnode = n.getRight();
                    remainnode = n.getLeft();
                }                
            }
            findK(depth + 1, target, targetnode, k);

            kmindis = target.distanceTo(pq.max());
            double decdis;
            if (depth % 2 == 0) {
                decdis = Math.abs(target.x() - n.getValue().x());
            } else {
                decdis = Math.abs(target.y() - n.getValue().y());
            }
            if (kmindis >= decdis) {
                findK(depth + 1, target, remainnode, k);
            }
        }
    }
    private class Order implements Comparator<Point2D> {

        private Point2D pt;

        public Order(Point2D p) {
            pt = p;
        }

        public int compare(Point2D p, Point2D q) {
            double dist1 = pt.distanceSquaredTo(p);
            double dist2 = pt.distanceSquaredTo(q);
            if (dist1 > dist2) {
                return +1;
            } else if (dist1 < dist2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    LinkedStackOfStrings searchpath = new LinkedStackOfStrings();
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        pq = new MaxPQ<Point2D>(k, new Order(point));
        findK(0, point, root, k);
        for (int i = 0; i < k; i++) {
            result[k - i - 1] = pq.delMax();
            //System.out.println(k - i - 1 + "":"" + result[k - i - 1]);
        }
        return result;
    }
    
    public void findnearest(Node x,Point2D point){
        //System.out.println(x.getValue().x()+"" ""+x.getValue().y());
        searchpath.push(x);
        
        if(x.getLevel()%2==0){
            if(point.x()<x.getValue().x()){
                if(x.getLeft()!=null)findnearest(x.getLeft(),point);
            }
            else{
                if(x.getRight()!=null)findnearest(x.getRight(),point);
            }
        }
        else{
            if(point.y()<x.getValue().y()){
                if(x.getLeft()!=null)findnearest(x.getLeft(),point);
            }
            else{
                if(x.getRight()!=null)findnearest(x.getRight(),point);  }
        }
    }
    
    
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        In in = new In(args[0]);
        Point2D[] A = new Point2D[1000];
        int i = 0;
        while(!in.isEmpty()){
            String[] datas = in.readLine().split("" "");
            
            double a = Double.parseDouble(datas[0]);
            double b = Double.parseDouble(datas[1]);
            A[i] = new Point2D(a,b);
            i++; }
        Point2D[] B = new Point2D[i];
        for(int j = 0;j < B.length;j++){
            B[j] = A[j];
        }
        FindNeighbors find = new FindNeighbors();
        find.init(B);
        Point2D C = new Point2D(0.144932,0.938569);
        Point2D[] result = find.query(C,8);
        for(int j = 0;j < result.length;j++){
            System.out.println(result[j]);
        }
        /*Point2D D = new Point2D(0.1354339200,0.7019446863);
        Point2D[] result2 = find.query(D, 1);
        for(int j = 0;j < result2.length;j++){
            System.out.println(result2[j]);
        }
        Point2D E = new Point2D(0,0);
        Point2D[] result3 = find.query(E, 2);
        for(int j = 0;j < result3.length;j++){
            System.out.println(result3[j]);
        }*/
        
        
        
        
        
    }

}




