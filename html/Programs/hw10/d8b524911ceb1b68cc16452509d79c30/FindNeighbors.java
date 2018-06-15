public class FindNeighbors {
    private static Node root = null;
    private static class Node{
        private Point2D point ;
        private Node left,right,parent;
        private boolean status =false; //false = radical(x different); true = horizontal(y different)

        public Node(Point2D a, boolean b){
          this.point = a;
          this.status = b;
        }
        public void changestatus(){
           if(status == false){status = true;}
           else{status = false;}
        }
        
        public boolean findstatus(){
            return status;
        }
        
        public Node getLeft(){
            return this.left;
            
        }
        public Node getRight(){
            return this.right;
        }
        public Point2D getNode(){
            return this.point;
        }
        public void setLeft(Node node){
            this.left = node;
            node.parent = this;
        }
        public void setRight(Node node){
            this.right = node;
            node.parent = this;
        }
        public double getX(){
            return this.point.x();
        }
        public double getY(){
            return this.point.y();
        }
        public Node getParent(Node node){
            return this.parent;
        }
     
        
    }
    
    public static class Line implements Comparable<Line>{
        double line;
        Point2D a;
        public Line(double line, Point2D a){
            this.line = line;
            this.a = a;
        }
        
        public int compareTo(Line that){
            if(this.line < that.line){return -1;}
            else if(this.line > that.line){return 1;}
            else return 0;
        }
    }
    
    public static void putnode(Point2D a){
        root = putnode(root, a);        
    }
    
    public static Node putnode(Node x, Point2D key){ // left and down is left, right and up is right
    if(root == null){
        root = new Node(key,false);
        return root;
    }
    if(x.findstatus()==false){//radical
        if(key.x() <= x.getX()){ // left
            if(x.left == null){
                Node y = new Node(key,true);
                x.setLeft(y);}
            else{x.left = putnode(x.left, key);}}
        else {
             if(x.right == null){
                Node y = new Node(key,true);
                x.setRight(y); }
             else{x.right = putnode(x.right, key);}
             }
    }
    else{//horizontal
        if(key.y() <= x.getY()){ // left
            if(x.left == null){
                Node y = new Node(key,false);
                x.setLeft(y);}
            else{x.left = putnode(x.left, key); }
        }    
        else{
            if(x.right == null){
                Node y = new Node(key,false);
                x.setRight(y);}
            else{x.right = putnode(x.right, key);} 
            }
        }
        return x;
    }
    public static Node findnearest(Point2D key){
        Node t = findnearest(root,key);
        if(t == null){return null;}
        return t;
    }
    public static Node findnearest(Node x , Point2D key){
    if(x.findstatus()==false){//radical
        if(key.x() < x.getX()){ // left
            if(x.left == null){return x;}
            else{return findnearest(x.left, key);}}
        else if(key.x() == x.getX()){return x;}
        else {
             if(x.right == null){return x;}
             else{return findnearest(x.right, key);}}
    }
     else{//horizontal
        if(key.y() < x.getY()){ // left
            if(x.left == null){return x;}
            else{return findnearest(x.left, key); }
        }
        else if(key.y() == x.getY()){return x;}
        else{
            if(x.right == null){return x;}
            else{return findnearest(x.right, key);} 
            }
        }   
    }
    
    public static void init(Point2D[] points){
        for (Point2D point : points) {
            putnode(point);
        }
    }

    public static Point2D[] query(Point2D point, int k){
        if(k == 0){return null;}
        Point2D[] result = new Point2D[k];
        MaxPQ<Line> pq = new MaxPQ<>();
        Point2D ref = point;
        Node start = findnearest(ref);
        double bound = 0;
        
        if(start == root){
            querydown(start,ref,pq,k);
            for(int i = result.length-1 ; i >=0 ; i--){
                Line tmp = pq.delMax();
                result[i] = tmp.a;
            }
             return result;            
        }         
        
        while(start.parent != null){
                if(pq.size() == 0){
                    queryfirst(start,pq,ref,k);
                }
                if(pq.size() > 0){
                    queryup(start,pq,ref,k);
                }
                if(start.parent !=null){
                    start = start.parent;}
                else{queryfirst(start,pq,ref,k);}
        }

        for(int i = result.length-1 ; i >=0 ; i--){
            Line tmp = pq.delMax();
            result[i] = tmp.a;
        }
        
    return result;
    
}
    
    public static void queryfirst(Node start, MaxPQ<Line> pq , Point2D ref, int k){
            Line linea = new Line(start.point.distanceTo(ref),start.point);
            pq.insert(linea);
            
            if(pq.size() > k){
                pq.delMax();
            }
               //neccessary to check the oppsite subtree
                    if(start.left!=null){
                            if(start.status == false && Math.abs(start.getX()-ref.x()) < pq.max().line){
                                    querydown(start.left,ref,pq,k);
                            }
                            if(start.status == true &&  Math.abs(start.getY()-ref.y()) < pq.max().line){
                                    querydown(start.left,ref,pq,k);
                            }                            
                    }
                    if(start.right != null){
                            if(start.status == false && Math.abs(start.getX()-ref.x()) < pq.max().line){
                                    querydown(start.right,ref,pq,k);
                            }
                            if(start.status == true &&  Math.abs(start.getY()-ref.y()) < pq.max().line){
                                    querydown(start.right,ref,pq,k);
                            }                            
                    }          
    }   
    public static void queryup(Node start, MaxPQ<Line> pq , Point2D ref, int k){
            Node parentstart = start.parent;
            Line linea = new Line(parentstart.point.distanceTo(ref),parentstart.point);
            pq.insert(linea);
            
            if(pq.size() > k){
                pq.delMax();
            }
               //neccessary to check the oppsite subtree
                    if(parentstart.left!=null){
                        if(parentstart.left.equals(start)==false){
                            if(parentstart.status == false && Math.abs(parentstart.getX()-ref.x()) < pq.max().line){
                                    querydown(parentstart.left,ref,pq,k);
                            }
                            if(parentstart.status == true &&  Math.abs(parentstart.getY()-ref.y()) < pq.max().line){
                                    querydown(parentstart.left,ref,pq,k);
                            }                            
                        }
                    }
                    if(parentstart.right != null){
                        if(parentstart.right.equals(start)==false){
                            if(parentstart.status == false && Math.abs(parentstart.getX()-ref.x()) < pq.max().line){
                                    querydown(parentstart.right,ref,pq,k);
                            }
                            if(parentstart.status == true &&  Math.abs(parentstart.getY()-ref.y()) < pq.max().line){
                                    querydown(parentstart.right,ref,pq,k);
                            }                            
                        }
                    }          
    }
    public static void querydown(Node x , Point2D point,MaxPQ<Line> pq, int k){

            Line line = new Line(x.point.distanceTo(point),x.point);
            pq.insert(line);

            if(pq.size()> k){
             pq.delMax();
            }
            
            if(x.left != null){
            querydown(x.left,point,pq,k);}
            if(x.right != null){
            querydown(x.right,point,pq,k);}

    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int count = in.readInt();
        Point2D[] points = new Point2D[count];
        
        for(int i = 0; i < points.length ; i++){
            points[i] = new Point2D(in.readDouble(),in.readDouble());
            /*String word= Integer.toString((int)(i));
            StdDraw.setScale(-0.2,1.2);
            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
            StdDraw.text(points[i].x(), points[i].y()+0.05 , word );*/
        }
        
        Point2D test = new Point2D(0.0447830281,0.2155560961);
        Point2D test2 = new Point2D(0.3833339428,0.1459115606);
        
        init(points);
        //StdOut.print(findnearest(test).point.x() + "" "" + findnearest(test).point.y()+ "" "");
        
        Point2D[] ans = query(test,15);
        //StdDraw.setPenColor(StdDraw.RED);
        //StdDraw.filledCircle(test.x(), test.y(), 0.01);
        
        //StdDraw.setPenColor(StdDraw.BLUE);
        //StdDraw.filledCircle(test2.x(), test2.y(), 0.01);
        
        for(int i = 0 ; i < ans.length ; i++){
        StdOut.println(i + "" "" + ans[i].x() + "" ""+ans[i].y() + "" distance is "" + ans[i].distanceTo(test));
        }
        
        StdOut.println();
        
        Point2D[] ans2 = query(test2,20);
        for(int i = 0 ; i < ans2.length ; i++){
        StdOut.println(i + "" "" + ans2[i].x() + "" ""+ans2[i].y() + "" distance is "" + ans2[i].distanceTo(test2));
        }

    }
    
}

