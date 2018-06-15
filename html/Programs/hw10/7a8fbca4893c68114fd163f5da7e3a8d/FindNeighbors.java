import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Dennis
 */
public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    
    
    public Node fristroot;

    public MaxPQ<setPoint> Maxfindnearpoints =new MaxPQ<setPoint>() ;
    public Node current ;
    public Node another ;
    private Node pointer ;
    private int bound = 0;
    private Point2D target;
    
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        boolean direct = true;
        Node[] root = new Node[points.length];
        int i=0;
        int j=1;
        int c=0;
        int root_number=0;
        root[i]=new Node(null,null,null,points[i],direct,root_number);
        i=1;
        while( i<points.length ){    
            
                    if( root[j-1].direct == true ){
                         if(points[i].x()>root[j-1].point.x() && root[j-1].right==null){
//                                 System.out.println('e');
                                 root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].direct,i);
//                                 System.out.println(j);
                                 root[j-1].setRightNode( root[i]);
                                 i=i+1;
                                 j=1;
                          }else if( points[i].x()<root[j-1].point.x() && root[j-1].left==null){
//                                 System.out.println('k');
                                 root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].direct,i);
                                 root[j-1].setLeftNode( root[i]);
                                 i=i+1;
                                 j=1;
                         }else if( points[i].x() < root[j-1].point.x() && !(root[j-1].left==null)){
//                             System.out.println('c');
//                             System.out.println(root[j-1].left.number);
                              j=root[j-1].left.number+1;// find next root number
                         }else if( points[i].x() > root[j-1].point.x() && !(root[j-1].right==null)){
                              j=root[j-1].right.number+1;// find next root number
                         }
                    }else if( root[j-1].direct==false ){
                         if( points[i].y()>root[j-1].point.y() && root[j-1].right==null ){
//                                System.out.println('d');
                                root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].direct,i);
                                root[j-1].setRightNode(root[i]);
                                i=i+1;
                                j=1;
                         }else if(  points[i].y()<root[j-1].point.y() && root[j-1].left==null){
                                root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].direct,i);
                                root[j-1].setLeftNode(root[i]);
                                i=i+1;
                                j=1;
                         }else if( points[i].y() < root[j-1].point.y() && !(root[j-1].left==null)){
                              j=root[j-1].left.number+1;// find next root number
                         }else if( points[i].y() > root[j-1].point.y() && !(root[j-1].right==null)){
                              j=root[j-1].right.number+1;// find next root number
                         }
                    }
       }
        fristroot=root[0];
        return;
    }
    
    public void findother(Node otherside,Point2D point,int k){

     while( !(otherside.right==null) || !(otherside.left==null) ){
        if(otherside.direct==true){
                if( point.x()>otherside.point.x() ){
                    setPoint dis2target=new setPoint(point,otherside.point);
                    Maxfindnearpoints.insert(dis2target);
//                    System.out.println(otherside.number+1 +"" ""+otherside.point);
                    otherside=otherside.right;
                }else if(  point.x()<otherside.point.x()){
                    setPoint dis2target=new setPoint(point,otherside.point);
                    Maxfindnearpoints.insert(dis2target);
//                    System.out.println(otherside.number+1 +"" ""+otherside.point);
                    otherside=otherside.left;}
                if( Math.abs(point.x()-otherside.uplevel.point.x()) <  Math.abs(point.y()-otherside.point.y()) && Maxfindnearpoints.size()==k  ){ // Find another side
                            if( point.x()>otherside.uplevel.point.x() && !(otherside.uplevel.left == null) ){ // find left side
                                 setPoint dis2target=new setPoint(point,otherside.uplevel.left.point);
                                 Maxfindnearpoints.insert(dis2target);
//                                 System.out.println(otherside.uplevel.left.number+1 +"" ""+otherside.uplevel.left.point);
                                 findother(otherside.uplevel.left,point,k);
                       }else if(  point.x()<otherside.uplevel.point.x() && !(otherside.uplevel.right == null) ){ // find right side
                                 setPoint dis2target=new setPoint(point,otherside.uplevel.right.point);
                                 Maxfindnearpoints.insert(dis2target);
//                                 System.out.println(otherside.uplevel.right.number+1 +"" ""+otherside.uplevel.right.point);
                                 findother(otherside.uplevel.right,point,k);}
                   }else{
                    break;
                }
                
        }else if(otherside.direct==false){

                 if( point.y()>otherside.point.y() ){
                    setPoint dis2target=new setPoint(point,otherside.point);
                    Maxfindnearpoints.insert(dis2target);
//                    System.out.println(otherside.number+1 +"" ""+otherside.point);
                    otherside=otherside.right;
                }else if(  point.y()<otherside.point.y()){
                    setPoint dis2target=new setPoint(point,otherside.point);
                    Maxfindnearpoints.insert(dis2target);
//                    System.out.println(otherside.number+1 +"" ""+otherside.point);
                    otherside=otherside.left;}
                if( Math.abs(point.y()-otherside.uplevel.point.y()) <  Math.abs(point.x()-otherside.point.x()) && Maxfindnearpoints.size()==k  ){  // Find another side
                    
                                if( point.y()>otherside.uplevel.point.y()  && !(otherside.uplevel.left == null) ){
                                   setPoint dis2target=new setPoint(point,otherside.uplevel.left.point);
                                   Maxfindnearpoints.insert(dis2target);
//                                   System.out.println(otherside.uplevel.left.number+1 +"" ""+otherside.uplevel.left.point);
                                   findother(otherside.uplevel.left,point,k);
                         }else if(  point.y()<otherside.uplevel.point.y() && !(otherside.uplevel.right == null) ){
                                   setPoint dis2target=new setPoint(point,otherside.uplevel.right.point);
                                   Maxfindnearpoints.insert(dis2target);
//                                   System.out.println(otherside.uplevel.right.number+1 +"" ""+otherside.uplevel.right.point);
                                   findother(otherside.uplevel.right,point,k);}
                }else{
                    break;
             }
        }
      }
    }
    

    public void findNearPoint(Point2D point, int k){
        current=fristroot;
        
        while( !(current.right==null) || !(current.left==null) ){
//            System.out.println(point);
//            System.out.println( current.number+1 +"" ""+current.point+"" ""+current.direct);
            if( current.direct == true ){
                if( point.x()>current.point.x() ){
                    setPoint dis2target=new setPoint(point,current.point);
                    Maxfindnearpoints.insert(dis2target);
                    current=current.right;
                }else if(  point.x()<current.point.x()){
                    setPoint dis2target=new setPoint(point,current.point);
                    Maxfindnearpoints.insert(dis2target);
                    current=current.left;}

                if( Math.abs(point.x()-current.uplevel.point.x()) <  Math.abs(point.y()-current.point.y()) && Maxfindnearpoints.size()==k ){ // Find another side

                               if( point.x()>current.uplevel.point.x()&& !(current.uplevel.left == null) ){
                                         findother(current.uplevel.left,point,k);
                               }else if(  point.x()<current.uplevel.point.x() && !(current.uplevel.right == null)){
                                         findother(current.uplevel.right,point,k);}
                }
                if(Maxfindnearpoints.size()==k){ Maxfindnearpoints.delMax();}
            }else if( current.direct == false){
                if( point.y()>current.point.y() ){
                    setPoint dis2target=new setPoint(point,current.point);
                    Maxfindnearpoints.insert(dis2target);
                    current=current.right;
                }else if(  point.y()<current.point.y()){
                    setPoint dis2target=new setPoint(point,current.point);
                    Maxfindnearpoints.insert(dis2target);
                    current=current.left;}

                if( Math.abs(point.y()-current.uplevel.point.y()) <  Math.abs(point.x()-current.point.x()) && Maxfindnearpoints.size()==k ){ // Find another side
                                if( point.y()>current.uplevel.point.y() && !(current.uplevel.left == null) ){
                                   findother(current.uplevel.left,point,k);
                         }else if(  point.y()<current.uplevel.point.y() && !(current.uplevel.right == null)){
                                   findother(current.uplevel.right,point,k);}
                }
                if(Maxfindnearpoints.size()==k){ Maxfindnearpoints.delMax();}
            }
        }

        setPoint dis2target=new setPoint(point,current.point);
        Maxfindnearpoints.insert(dis2target);
//            System.out.println(point);
//            System.out.println( current.number+1 +"" ""+current.point+"" ""+current.direct);
    }
    
    
    
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        int c=1;
        Point2D[] result = new Point2D[k];
        findNearPoint(point,k);

//        System.out.println("" "");

        for(int i=k-1;i>=0;i--){
            result[i]=Maxfindnearpoints.delMax().points;
        }
        
        return result;
    }
    
    
 private static class setPoint implements Comparable <setPoint> { 

     Point2D targert;
     Point2D points;
     double dis;
     
     public setPoint(Point2D targert,Point2D points) { 
            this.targert =targert;
            this.points = points;
            this.dis=this.targert.distanceSquaredTo(this.points);
        }
     public int compareTo(setPoint that) {
        int turnans=0;
        if( this.dis > that.dis ){turnans=1;}
        else if( this.dis < that.dis ){turnans=-1;}
        else{turnans=0;}
        return turnans;
     }
    }
 
     public class Node {
         private Node right;
         private Node left;
         private Node uplevel;
         private Point2D point;
         private boolean direct;
         private int number;
         public Node(Node left, Node right, Node uplevel, Point2D point, boolean direct,int number){
             this.right=right;
             this.left=left;
             this.uplevel=uplevel;
             this.point=point;
             this.direct=direct;
             this.number=number;
         }
         public void setRightNode(Node right){
             this.right=right;
         }
         public void setLeftNode(Node right){
             this.left=right;
         }
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
 
     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
          String[] Num;
          String data;          
          int N=0;
          Queue<Point2D> findleftqueue =new Queue<Point2D>();
          Point2D mytarget = new Point2D(0.13543392,0.7019446863);
          int ansnuber =3;
          do{
          Point2D [] points = new Point2D[N+1];
          N=N+1;
          data=br.readLine();
          if(data==null)
                break;
            Num = data.split("" "");
            double x=Double.parseDouble(Num[0]);
            double y=Double.parseDouble(Num[1]);
           points[N-1]=new Point2D(x,y);
           findleftqueue.enqueue(points[N-1]);
          } while(true);
          
          N=findleftqueue.size();
          Point2D [] points = new Point2D[N];
          for(int i=0;i<N;i++){
              points[i]=findleftqueue.dequeue();
//              System.out.println(points[i]);
          }
           Point2D[] ans =new Point2D[ansnuber];
           FindNeighbors build = new FindNeighbors(); 
           build.init(points);
           ans=build.query(mytarget, ansnuber);
           
//            for(int i=0;i<ansnuber;i++){
//                 System.out.println(ans[i]);
//            }
//          System.out.println(build.fristroot.left.left.point);
          
          
       } 
    }
}

