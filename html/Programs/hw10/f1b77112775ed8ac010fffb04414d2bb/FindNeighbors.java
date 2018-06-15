
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
        int p=0;
        root[i]=new Node(null,null,null,points[i],direct,root_number,p);

        i=1;
        while( i<points.length ){    
                    if( root[j-1].getDirect() == true ){
                         if(points[i].x()>root[j-1].getPoint().x() && root[j-1].getRightNode()==null){
                                 root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].getDirect(),i,p);
                                 root[j-1].setRightNode( root[i]);
                                 i=i+1;
                                 j=1;
                          }else if( points[i].x()<root[j-1].getPoint().x() && root[j-1].getLeftNode()==null){
                                 root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].getDirect(),i,p);
                                 root[j-1].setLeftNode( root[i]);
                                 i=i+1;
                                 j=1;
                         }else if( points[i].x() < root[j-1].getPoint().x() && !(root[j-1].getLeftNode()==null)){
                              j=root[j-1].getLeftNode().number+1;// find next root number
                         }else if( points[i].x() > root[j-1].getPoint().x() && !(root[j-1].getRightNode()==null)){
                              j=root[j-1].getRightNode().number+1;// find next root number
                         }
                    }else if( root[j-1].getDirect()==false ){
                         if( points[i].y()>root[j-1].getPoint().y() && root[j-1].getRightNode()==null ){
//                                System.out.println('d');
                                root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].getDirect(),i,p);
                                root[j-1].setRightNode(root[i]);
                                i=i+1;
                                j=1;
                         }else if(  points[i].y()<root[j-1].getPoint().y() && root[j-1].getLeftNode()==null){
                                root[i]=new Node(null,null,root[j-1],points[i],!root[j-1].getDirect(),i,p);
                                root[j-1].setLeftNode(root[i]);
                                i=i+1;
                                j=1;
                         }else if( points[i].y() < root[j-1].getPoint().y() && !(root[j-1].getLeftNode()==null)){
                              j=root[j-1].getLeftNode().number+1;// find next root number
                         }else if( points[i].y() > root[j-1].getPoint().y() && !(root[j-1].getRightNode()==null)){
                              j=root[j-1].getRightNode().number+1;// find next root number
                         }
                    }
       }
        fristroot=root[0];

        return;
    }
    
    public void putInPQ(Node current,Point2D point,int k){
        if( !(current==null )){
            if( current.getPass()==0){
            current.setPass(1);
                    if( Maxfindnearpoints.size()<k){setPoint dis2target=new setPoint(point,current.point);
                        Maxfindnearpoints.insert(dis2target); }
                    else if(Maxfindnearpoints.max().dis>current.getPoint().distanceSquaredTo(point)){
                    Maxfindnearpoints.delMax();
                    setPoint dis2target=new setPoint(point,current.getPoint());
                    Maxfindnearpoints.insert(dis2target);}
            }
        }
    }
     public void finanotherside(Node otherside,Point2D point,int k){
         if(otherside.getRightNode()==null){
            if( Math.abs(point.x()-otherside.getPoint().x()) <  Math.abs(point.y()-otherside.getPoint().y()) || Maxfindnearpoints.size()<k ){
               putInPQ(otherside,point,k);
               putInPQ(otherside.getLeftNode(),point,k);
               findother(otherside.getLeftNode(),point,k);
           }
         }else if(otherside.getLeftNode()==null){
            if( Math.abs(point.x()-otherside.getPoint().x()) <  Math.abs(point.y()-otherside.getPoint().y()) || Maxfindnearpoints.size()<k ){
              putInPQ(otherside,point,k);
              putInPQ(otherside.getRightNode(),point,k);
              findother(otherside.getRightNode(),point,k);
          }
         }
     }
    
    public void findother(Node otherside,Point2D point,int k){

      while( otherside.getRightNode()!=null || otherside.getLeftNode()!=null ){
        if(otherside.getDirect()==true){

                if( point.x()>otherside.getPoint().x() ){
                            putInPQ(otherside,point,k);
                            if(otherside.getRightNode()==null){
                            finanotherside(otherside,point,k);
                            }
                    otherside=otherside.getRightNode();
                }else if(  point.x()<otherside.getPoint().x()){
                           putInPQ(otherside,point,k);
                           if(otherside.getLeftNode()==null){
                           finanotherside(otherside,point,k);
                           }
                    otherside=otherside.getLeftNode();
                }
                if(otherside==null){
                }else if( Math.abs(point.x()-otherside.getUplevel().getPoint().x()) <  Math.abs(point.y()-otherside.getPoint().y()) || Maxfindnearpoints.size()<k ){ // Find another side
                       if( point.x()>otherside.getUplevel().getPoint().x() && !(otherside.getUplevel().getLeftNode() == null) ){ // find left side
                                   putInPQ(otherside,point,k);
                                   putInPQ(otherside.getUplevel().getLeftNode(),point,k);
                                   findother(otherside.getUplevel().getLeftNode(),point,k);
                       }else if(  point.x()<otherside.getUplevel().getPoint().x() && !(otherside.getUplevel().getRightNode() == null) ){ // find right side
                                   putInPQ(otherside,point,k);
                                   putInPQ(otherside.getUplevel().getRightNode(),point,k);
                                   findother(otherside.getUplevel().getRightNode(),point,k);}
                   }
                                  putInPQ(otherside,point,k);
        }else if(otherside.getDirect()==false){
                 if( point.y()>otherside.getPoint().y() ){
                            putInPQ(otherside,point,k);
                            if(otherside.getRightNode()==null){
                            finanotherside(otherside,point,k);
                            }
                      otherside=otherside.getRightNode();
                }else if(  point.y()<otherside.getPoint().y()){
                           putInPQ(otherside,point,k);
                           if(otherside.getLeftNode()==null){
                           finanotherside(otherside,point,k);
                           }
                    otherside=otherside.getLeftNode();}
                    if(otherside==null){}
                    else if( Math.abs(point.y()-otherside.getUplevel().getPoint().y()) <  Math.abs(point.x()-otherside.getPoint().x()) || Maxfindnearpoints.size()<k ){  // Find another side
                            if( point.y()>otherside.getUplevel().getPoint().y()  && !(otherside.getUplevel().getLeftNode() == null) ){
                                   putInPQ(otherside,point,k);
                                   putInPQ(otherside.getUplevel().getLeftNode(),point,k);
                                   findother(otherside.getUplevel().getLeftNode(),point,k);
                         }else if(  point.y()<otherside.getUplevel().getPoint().y() && !(otherside.getUplevel().getRightNode() == null) ){
                                    putInPQ(otherside,point,k);
                                    putInPQ(otherside.getUplevel().getRightNode(),point,k);
                                    findother(otherside.getUplevel().getRightNode(),point,k);}
                    }
        }
          if(otherside==null){break;}
      }
       putInPQ(otherside,point,k);
    }
    

    public void findNearPoint(Point2D point, int k){
        Node current ;
        current=fristroot;
//      System.out.println(current.right.point);
        while( !(current.getRightNode()==null) || !(current.getLeftNode()==null) ){
            if( current.getDirect() == true ){
                if( point.x()>current.getPoint().x() ){
                    putInPQ(current,point,k);
                        current=current.getRightNode();
                }else if(  point.x()<current.getPoint().x()){
                    putInPQ(current,point,k);
                        current=current.getLeftNode();}
                if(current.getPoint()==null){}
                else if( Math.abs(point.x()-current.getUplevel().getPoint().x()) <  Math.abs(point.y()-current.getPoint().y()) || Maxfindnearpoints.size()<k ){ // Find another side
                               if( point.x()>current.getUplevel().getPoint().x()&& !(current.getUplevel().getLeftNode() == null) ){
                                         putInPQ(current,point,k);
                                         putInPQ(current.getUplevel().getLeftNode(),point,k);
                                         findother(current.getUplevel().getLeftNode(),point,k);
                               }else if(  point.x()<current.getUplevel().getPoint().x() && !(current.getUplevel().getRightNode() == null)){
                                         putInPQ(current,point,k);
                                         putInPQ(current.getUplevel().getRightNode(),point,k);
                                         findother(current.getUplevel().getRightNode(),point,k);}
                }
            }else if( current.getDirect() == false){
                if( point.y()>current.getPoint().y() ){
                    putInPQ(current,point,k);
                    current=current.getRightNode();
                }else if(  point.y()<current.getPoint().y()){
                    putInPQ(current,point,k);
                     current=current.getLeftNode();}
//                System.out.println( Math.abs(point.y()-current.uplevel.point.y()) <  Math.abs(point.x()-current.point.x()));
                if(current==null){}
                else if( Math.abs(point.y()-current.getUplevel().getPoint().y()) <  Math.abs(point.x()-current.getPoint().x())  || Maxfindnearpoints.size()<k ){ // Find another side
                                if( point.y()>current.getUplevel().getPoint().y() && !(current.getUplevel().getLeftNode() == null) ){
                                   putInPQ(current,point,k);
                                   putInPQ(current.getUplevel().getLeftNode(),point,k);
                                   findother(current.getUplevel().getLeftNode(),point,k);
                         }else if(  point.y()<current.getUplevel().getPoint().y() && !(current.getUplevel().getRightNode() == null)){
                                   putInPQ(current,point,k);
                                   putInPQ(current.getUplevel().getRightNode(),point,k);
                                   findother(current.getUplevel().getRightNode(),point,k);}
                }
            }
        }
                    putInPQ(current,point,k);
//        System.out.println( current.number+1 +"" ""+current.point+"" ""+current.direct);
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        int c=1;
        Point2D[] result = new Point2D[k];
//        System.out.println(k);
        findNearPoint(point,k);
//        System.out.println(Maxfindnearpoints.size());
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
         private int pass;
         public Node(Node left, Node right, Node uplevel, Point2D point, boolean direct,int number,int pass){
             this.right=right;
             this.left=left;
             this.uplevel=uplevel;
             this.point=point;
             this.direct=direct;
             this.number=number;
             this.pass=pass;
         }
         public void setRightNode(Node right){
             this.right=right;
         }
         public void setLeftNode(Node right){
             this.left=right;
         }
         public void setPass(int pass){
              this.pass=pass;
         }
         public Node getUplevel(){
          return  this.uplevel;
         } 
         public Point2D getPoint(){
          return  this.point;
         } 
         public Node getLeftNode(){
          return  this.left;
         }
         public Node getRightNode(){
          return  this.right;
         }
         public int getPass(){
          return  this.pass;
         }
         public boolean getDirect(){
          return  this.direct;
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
          Point2D mytarget = new Point2D(0.1354339200,0.7019446863);
          int ansnuber =5;
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
//           System.out.println(build.Maxfindnearpoints.size());
           ans=build.query(mytarget, ansnuber);

            for(int i=0;i<ansnuber;i++){
                 System.out.println(ans[i]);
            }
//          System.out.println(build.fristroot.left.left.point);
          
          
       } 
    }
}

