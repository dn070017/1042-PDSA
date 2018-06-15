import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

class Node implements Comparable<Node> {
    private Node root;
    private Node left;
    private Node right;
    private Point2D value;
    private double key1;
    private double key2;
    private int N;  
     private double distance ;
    private int change=0;  
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of key-value pairs in BST
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    
  public Node(double key1,double key2, Point2D value,int N){
        this.key1 = key1;
        this.key2 = key2;
        this.value = value;
        this.N = N;
        if(size()==0){root=this;
           // root.key1 = key1;
            //        root.key2 = key2;
             //       root.value = value;
              //      root.N = N;
        }
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
/*      public double get1(double key) {
        return get1(root, key);
    }
       private double get1(Node x, double key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key1);
        if      (cmp < 0) return get1(x.left, key);
        else if (cmp > 0) return get1(x.right, key);
        else              return x.value;
    }
       
        public Value get2(Key key) {
        return get2(root, key);
    }*/
    public double getdistance( Point2D value){
     this.distance =this.getValue().distanceTo(value);
     return this.distance;
    }
     public void put(double key1,double key2, Point2D value) {
        if (value == null) {return;  }
        root = put(root, key1,key2, value);
        
    }

    private Node put(Node x, double key1,double key2, Point2D value) {
        if (x == null) {change=0; return new Node(key1,key2, value, 1);}
        if(change==0)
        { int cmp1 = x.compareTo1(key1);
        if      (cmp1 > 0) {change=1 ;x.left  = put(x.left, key1,key2, value);}
        else if (cmp1 < 0){ change=1 ; x.right = put(x.right, key1,key2, value);}
        else              x.value= value;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
        }
        else{
        int cmp2 = x.compareTo2(key2);
        if      (cmp2 > 0) {change=0 ;x.left  = put(x.left, key1,key2, value);}
        else if (cmp2 < 0) {change=0;x.right = put(x.right, key1,key2, value);}
        else              x.value= value;
        x.N = 1 + size(x.left) + size(x.right);
        return x;}
    }
     public int compareTo(Node that) {
          if(this.distance>that.distance){return 1;}
          if(this.distance<that.distance){return -1;}
          else return 0;
     }
     public int compareTo1(double point1) {
          if(this.key1>point1){return 1;}
          if(this.key1<point1){return -1;}
          else return 0;
     }
     public int compareTo2(double point2) {
          if(this.key2>point2){return 1;}
          if(this.key2<point2){return -1;}
          else return 0;
     }
        public int height() { return height(root); }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


     
 }
public class FindNeighbors {
   // private Node root;

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    Node tree; 
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] point){
        //BST tree=new BST();
        // System.out.println(point.length);
        Node tree1=new Node(point[0].x(),point[0].y(),point[0],1);
       
        //tree1.put(point[0].x(),point[0].y(),point[0]); 
        for(int i=1;i<point.length;i++)
        {  tree1.put(point[i].x(), point[i].y() ,point[i]);
           // System.out.println(point[i]);
        
        
        }
    //    System.out.println(tree1.size());
   //      System.out.println(tree1.getLeft().getLeft().getLeft().getValue());
         tree=tree1;
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        /* StdDraw.filledCircle(point.x(), point.y(), 0.01);
               StdDraw.text(point.x(), point.y()+0.03,Integer.toString(500) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(point.x(), point.y(), 0.01);
              StdDraw.text(point.x(),point.y()+0.03 ,Integer.toString(500));*/
       // point.r()
       MinPQ minpq=new MinPQ();
       int mode=0;
       int count=tree.size();
        Point2D[] result = new Point2D[k];
 search: {      for(int i=0;i<count;i++)
        {if(mode==0){if(tree.getValue().equals(null)){ System.out.println(""out1"");break;}
            if(tree.compareTo1(point.x())>0)
               {  double dis=tree.getdistance(point);
           //     System.out.println(dis);
            //     System.out.println(1);
                  minpq.insert((tree));
                  if(tree.getLeft() == null){break;}
                   tree=tree.getLeft();
                   mode=1;}
            else if(tree.compareTo1(point.x())<0){ double dis=tree.getdistance(point);
          //       System.out.println(dis);
           //       System.out.println(2);
                  minpq.insert(tree);
                  if(tree.getRight() == null){break;}
                   tree=tree.getRight();
                    
                   mode=1;}
                }
        else if(mode==1){if(tree.isEmpty()){ System.out.println(""out2"");break search;}
             if(tree.compareTo2(point.y())>0)
               {  double dis=tree.getdistance(point);
            //     System.out.println(dis);
             //     System.out.println(3);
                  minpq.insert(tree);
                  if(tree.getLeft() == null){break;}
                   tree=tree.getLeft();
                   mode=0;}
             else if(tree.compareTo2(point.y())<0){ 
                 double dis=tree.getdistance(point);
              //    System.out.println(dis);
               //    System.out.println(4);
                  minpq.insert(tree);
                  if(tree.getRight() == null){break;}
                  tree=tree.getRight();
                   mode=0;
             }
            }
     //   System.out.println(i);
        }}
       for(int j=0;j<k;j++){
       Node temp=(Node)minpq.delMin();
        result[j]=temp.getValue();
  //      System.out.println(result[j]);
       }
        
        return result;
    }

     public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
      
        int num=0;
        int num1=0;
        Point2D[] point=new Point2D[1000];
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            for(String in = br.readLine(); in != null; in = br.readLine()) {
             String[] data=in.split("" "");
             point[num]=new Point2D(Double.parseDouble(data[0]),Double.parseDouble(data[1]));
    //          System.out.println(point[num]);
          num=num+1;
        //  System.out.println(num);
         
         }
         
        /* for(String in = br.readLine(); in != null; in = br.readLine()) {
             String[] data=in.split("" "");
              point[num1]=new Point2D(Double.parseDouble(data[0]),Double.parseDouble(data[1]));
               System.out.println(point[num1]);
          num=num+1;
          System.out.println(num1);
         }*/
         
       
        }
        
        
        Point2D[] pointgo=new Point2D[num];
        for(int i=0;i<num;i++)
        {pointgo[i]=point[i];}
        
        /* for(int i=0;i<num;i++){   
               
             StdDraw.filledCircle(pointgo[i].x(), pointgo[i].y(), 0.01);
               StdDraw.text(pointgo[i].x(), pointgo[i].y()+0.03,Integer.toString(i) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(pointgo[i].x(), pointgo[i].y(), 0.01);
              StdDraw.text(pointgo[i].x(),pointgo[i].y()+0.03,Integer.toString(i) );
               }*/
         
         
        FindNeighbors tree=new FindNeighbors();
         tree.init(pointgo);
         Point2D a=new Point2D(0.1354339200,0.7019446863);
        Point2D[] temp= tree.query( a , 3);
       for(int i=0;i<3;i++)
       {System.out.println(temp[i]);
       }
     }
     
}


