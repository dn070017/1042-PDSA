import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import  java.lang.Math;
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
   Stack<Node> rem = new Stack<Node>();
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    Node tree; 
   static Node tree5; 
   // MaxPQ maxpq;
    int mode=0;
    int start=0;
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] point){
        //BST tree=new BST();
        // System.out.println(point.length);
        Node tree1=new Node(point[0].x(),point[0].y(),point[0],1);
       
        //tree1.put(point[0].x(),point[0].y(),point[0]); 
        for(int i=1;i<point.length;i++)
        {  tree1.put(point[i].x(), point[i].y() ,point[i]);
         //   System.out.println(point[i]);
        
        
        }
    //    System.out.println(tree1.size());
   //      System.out.println(tree1.getLeft().getLeft().getLeft().getValue());
         tree=tree1;
         rem.push(tree1);
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
   /* public void finddis(Node tree2,Point2D point){
        int mode=0;
        if(mode==0){
            if(tree2.compareTo1(point.x())>0)
               {  double dis=tree2.getdistance(point);
           //     System.out.println(dis);
            //     System.out.println(1);
                  minpq.insert((tree2));
                  if(tree2.getLeft() == null){return;}
                   tree2=tree2.getLeft();
                   mode=1;}
            else if(tree2.compareTo1(point.x())<0){ double dis=tree2.getdistance(point);
          //       System.out.println(dis);
           //       System.out.println(2);
                  minpq.insert(tree2);
                  if(tree2.getRight() == null){break;}
                   tree2=tree2.getRight();
                    
                   mode=1;}
                }
        else if(mode==1){
             if(tree2.compareTo2(point.y())>0)
               {  double dis=tree2.getdistance(point);
            //     System.out.println(dis);
             //     System.out.println(3);
                  minpq.insert(tree2);
                  if(tree2.getLeft() == null){break;}
                   tree2=tree2.getLeft();
                   mode=0;}
             else if(tree2.compareTo2(point.y())<0){ 
                 double dis=tree2.getdistance(point);
              //    System.out.println(dis);
               //    System.out.println(4);
                  minpq.insert(tree2);
                  if(tree2.getRight() == null){break;}
                  tree2=tree2.getRight();
                   mode=0;
             }
            }
     //   System.out.println(i);
       
    
    }*/
    
private  void printPreOrderRec(MaxPQ maxpq,Node tree3,Point2D point ,int k) {
      
     if (tree3== null) return ;
     
     
     if(mode==0){  
      if(maxpq.size()==k)
         { double dis2=Math.abs(tree3.getValue().x()-point.x());
           double dis3=(tree3.getValue().x()-point.x());
         Node temp =(Node)maxpq.max();
         if(dis2<temp.getdistance(point)){ mode=1;start=0;
             double dis=tree3.getdistance(point);    
             if(dis<=temp.getdistance(point)){maxpq.delMax();maxpq.insert((tree3));//System.out.println(""1"");
             }
             }
         else{ mode=1;  
               //System.out.println(""close1"");
              if(dis3>=0){  start=4;//tree3.setRight(null);
                          }
               else{  start=3;
                    //tree3.setLeft(null);
                    }
            }
         }
      else{ //System.out.println(""2"");
          start=0;
        double dis=tree3.getdistance(point);
        maxpq.insert((tree3));mode=1;}
       }
     else if(mode==1){
        if(maxpq.size()==k)
          { double dis2=Math.abs(tree3.getValue().y()-point.y());
            double dis3=(tree3.getValue().y()-point.y());
         Node temp =(Node)maxpq.max();
         if(dis2<=temp.getdistance(point)){mode=0;  start=0;
             double dis=tree3.getdistance(point);    
             if(dis<temp.getdistance(point)){maxpq.delMax();maxpq.insert(tree3);//System.out.println(""3"");
             }
             }
         else{mode=0; //System.out.println(""close2"");
             if(dis3>=0){  start=4;//tree3.setRight(null);
                            }
               else{  start=3;//tree3.setLeft(null);
                     }
            
             }
         }
      else{  //start=0;System.out.println(""4"");
        double dis=tree3.getdistance(point);
        maxpq.insert((tree3));
        mode=0;}
  }
      
    
    
     if(start==3) {printPreOrderRec(maxpq,tree3.getRight(),point,k);}
     else if(start==4){printPreOrderRec(maxpq,tree3.getLeft(),point,k);}
      else{printPreOrderRec(maxpq,tree3.getLeft(),point,k);
            printPreOrderRec(maxpq,tree3.getRight(),point,k);}
      start=0;
      if(mode==1){mode=0;} 
      else if(mode==0){mode=1;}
      
     }
  /*private void printPostOrderRec1(Node currRoot) {
  if (currRoot == null) {
    return;
  }
  printPostOrderRec1(currRoot.getLeft());
  printPostOrderRec1(currRoot.getRight());
  System.out.println(currRoot.getValue());
  
}*/
    public Point2D[] query(Point2D point, int k){
      /*   StdDraw.filledCircle(point.x(), point.y(), 0.01);
               StdDraw.text(point.x(), point.y()+0.03,Integer.toString(500) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(point.x(), point.y(), 0.01);
              StdDraw.text(point.x(),point.y()+0.03 ,Integer.toString(500));*/
       // point.r()
      MaxPQ maxpq=new MaxPQ();
       
       int count=tree.size();
       //System.out.println(count);
       
     Node tree2;
 
       tree2=tree;
       
        Point2D[] result = new Point2D[k];
       // printPostOrderRec1(tree2);
         printPreOrderRec( maxpq,tree2,point,k);
         mode=0;
      // System.out.println(tree2.size());
 /*search: {      for(int i=0;i<count;i++)
        {if(mode==0){
            if(tree2.compareTo1(point.x())>0)
               {  double dis=tree2.getdistance(point);
           //     System.out.println(dis);
            //     System.out.println(1);
                  minpq.insert((tree2));
                  if(tree2.getLeft() == null){break;}
                   tree2=tree2.getLeft();
                   mode=1;}
            else if(tree2.compareTo1(point.x())<0){ double dis=tree2.getdistance(point);
          //       System.out.println(dis);
           //       System.out.println(2);
                  minpq.insert(tree2);
                  if(tree2.getRight() == null){break;}
                   tree2=tree2.getRight();
                    
                   mode=1;}
                }
        else if(mode==1){
             if(tree2.compareTo2(point.y())>0)
               {  double dis=tree2.getdistance(point);
            //     System.out.println(dis);
             //     System.out.println(3);
                  minpq.insert(tree2);
                  if(tree2.getLeft() == null){break;}
                   tree2=tree2.getLeft();
                   mode=0;}
             else if(tree2.compareTo2(point.y())<0){ 
                 double dis=tree2.getdistance(point);
              //    System.out.println(dis);
               //    System.out.println(4);
                  minpq.insert(tree2);
                  if(tree2.getRight() == null){break;}
                  tree2=tree2.getRight();
                   mode=0;
             }
            }
     //   System.out.println(i);
        }
 }*/

       for(int j=k-1;j>=0;j--){
       Node temp=(Node)maxpq.delMax();
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
        
     /*    for(int i=0;i<num;i++){   
               
             StdDraw.filledCircle(pointgo[i].x(), pointgo[i].y(), 0.01);
               StdDraw.text(pointgo[i].x(), pointgo[i].y()+0.03,Integer.toString(i) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(pointgo[i].x(), pointgo[i].y(), 0.01);
              StdDraw.text(pointgo[i].x(),pointgo[i].y()+0.03,Integer.toString(i) );
               }*/
         
         
        FindNeighbors opop=new FindNeighbors();
         opop.init(pointgo);
        Point2D a=new Point2D(0.144932 ,0.938569);
        Point2D[] temp= opop.query( a , 3);
        a=new Point2D(0.1354339200, 0.7019446863);
        temp= opop.query( a,10);
        a=new Point2D(0.144932 ,0.938569);
       temp= opop.query(a,10);
       for(int i=0;i<10;i++)
       {System.out.println(temp[i]);
       }
     }
     
}


//0.144932 ,0.938569
//0.531440,0.616918

//0.1354339200 ,0.7019446863
