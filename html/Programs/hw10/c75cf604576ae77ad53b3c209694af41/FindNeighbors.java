

import java.io.BufferedReader;
import java.io.FileReader;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    private Node root;
    private Node leaf;
    private Node key;
    public static Node query;
    MaxPQ<Node> knear = new MaxPQ();

    private class Node implements Comparable<Node> {
        private Point2D p;
        private Node left;
        private Node right;
        private Node parent;
        private boolean hv;//(TRUE=x  FALSE=y)
        public Node(Point2D p, Node left, Node right,Node parent,boolean hv) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.hv=hv;
        }
        public int compareTo(Node that) {
          if(this.p.distanceSquaredTo(query.p)> that.p.distanceSquaredTo(query.p)){return 1;}
          else if(this.p.distanceSquaredTo(query.p)< that.p.distanceSquaredTo(query.p)){return -1;}
          else {return 0;}
    }}

    // TODO
    // please use the Point2D from algs4.jar 
    
    public void init(Point2D[] points){
        root = new Node(points[0],null,null,null,true);
        for(int i =1;i<points.length;i++){
          Node key = root;
          while(true){
             if(points[i].x()<key.p.x()){
                if(key.left!=null){key=key.left;}
                else{key.left=new Node(points[i],null,null,key,!key.hv);break;}
             }
             else{//p[i].x >= key.p.x()
                if(key.right!=null){key=key.right;}
                else{key.right=new Node(points[i],null,null,key,!key.hv);break;}
             }
             if(points[i].y()<key.p.y()){
                if(key.left!=null){key=key.left;}
                else{key.left=new Node(points[i],null,null,key,!key.hv);break;}
             }
             else{//p[i].x >= key.p.x()
                if(key.right!=null){key=key.right;}
                else{key.right=new Node(points[i],null,null,key,!key.hv);break;}
             }
        }}
        return;
    }
    private int cmp(Node key2, Node key1){//key2 query
        if(key1.hv){
            if(key2.p.x()-key1.p.x()<0) return -1;
            else return 1;}
        else {
            if(key2.p.y()-key1.p.y()<0) return -1;
            else return 1;}
    }
    
    private double abdis(Node key2, Node key1){
        if(key1.hv){return Math.abs(key2.p.x()-key1.p.x());}
        else {return Math.abs(key2.p.y()-key1.p.y());}
    }
    
    private Node put(Node key2, Node key1){//key2 query
        //System.out.println(key1==null);
        if(cmp(key2,key1)<0){
            if(key1.left==null){leaf=new Node(null,null,null,key1,false); key=key1;return key1;}
            else{knear.insert(put(key2,key1.left));}}
        else {
            if(key1.right==null){leaf=new Node(null,null,null,key1,false); key=key1; return key1;}
            else{knear.insert(put(key2,key1.right));}}
        return key1;
    }
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        query=new Node(point,null,null,null,false);
        knear.insert(put(query,root));
        leaf=new Node(point,null,null,key,!key.hv);
        if(cmp(leaf,key)<0){
            key.left= new Node(point,null,null,key,!key.hv);}
        else {key.right=new Node(point,null,null,key,!key.hv);} // add query point
        //System.out.print(""ok1"");
        while(!leaf.equals(root)){//leaf=root end
           //System.out.println(""ok2"");
           while(knear.size()>k){knear.delMax();}
           double maxdis=knear.max().p.distanceSquaredTo(query.p);
            //System.out.println(maxdis);
           if(cmp(query,leaf.parent)<0){
               if(leaf.parent.right!=null){
               //System.out.println(abdis(query,leaf.parent));
                   if(knear.size()>=k&abdis(query,leaf.parent)>=maxdis){
                       leaf=leaf.parent;
                       if(!leaf.equals(root)){
                           while(cmp(leaf,leaf.parent)!=cmp(query,leaf.parent)){leaf=leaf.parent;}}
                   }
                   else{//size<k or abdis<maxdix
                      //System.out.println(""ok333"");
                      //System.out.println(abdis(query,leaf.parent.right));
                      knear.insert(put(query,leaf.parent.right));
               }}
               else{//null
               //System.out.println(""ok4"");
                  leaf=leaf.parent;
                  //depends on this.Node's hv
               if(!leaf.equals(root)){
                  while(cmp(leaf,leaf.parent)!=cmp(query,leaf.parent)){leaf=leaf.parent;}}
            }}
           else{//偏要往左走cmp>0
               if(leaf.parent.left!=null){
                   if(knear.size()>=k&abdis(query,leaf.parent)>=maxdis){
                       //System.out.println(""ok55"");
                       //System.out.println(abdis(query,leaf.parent));
                       leaf=leaf.parent;
                       if(!leaf.equals(root)){
                           while(cmp(leaf,leaf.parent)!=cmp(query,leaf.parent)){leaf=leaf.parent;}}
                   }
                   else{//size<k or abdis<maxdix
                   knear.insert(put(query,leaf.parent.left));
               }}
               else{//null
                  //System.out.println(""ok6"");
                  leaf=leaf.parent;
                  if(!leaf.equals(root)){
                           while(cmp(leaf,leaf.parent)!=cmp(query,leaf.parent)){leaf=leaf.parent;}}
           }}
        }
        while(knear.size()>k){knear.delMax();}   
        Point2D[] result = new Point2D[k];
        for(int i =k-1;i>-1;i--){result[i]=knear.max().p;knear.delMax();}
        //System.out.println(knear.max().p.x());
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        //""input14.txt""  args[0]
        try(BufferedReader br = new BufferedReader(new FileReader(""input10-0.txt""))){
            Stack<Point2D> shell = new Stack<Point2D>();
            for(String in = br.readLine(); in != null; in = br.readLine()) {
              String[] p = in.split(""\\s+"");
              shell.push(new Point2D(Double.parseDouble(p[0]),Double.parseDouble(p[1])));
            }
            Point2D[] pp = new Point2D[shell.size()];
            for(int i =pp.length-1; i>-1; i--){
              pp[i]=shell.pop();
            }
            FindNeighbors e = new FindNeighbors();
            e.init(pp);
            Point2D q=new Point2D(0.1354339200,0.7019446863);
            Point2D[] re =e.query(q,3);
            for(int i=0;i<3;i++){
              System.out.println(re[i].x()+"" ""+re[i].y());
            }
        }    
    }
}

