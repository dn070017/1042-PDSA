/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Dennis
 */
public class CriticalDis {
    
    public CriticalDis(){}
    /**
     * @param args the command line arguments
     */
     private static class setPoint implements Comparable <setPoint> { 

     Point2D pointA;
     Point2D pointB;
     int numberA,numberB;
     double dis;
     
     public setPoint(Point2D pointA,Point2D pointB,int numberA,int numberB) { 
            this.pointA = pointA;
            this.pointB = pointB;
            this.numberA =numberA;
            this.numberB =numberB;
            this.dis=this.pointA.distanceTo(this.pointB);
        }
     public int compareTo(setPoint that) {
        int turnans=0;
        if( this.dis > that.dis ){turnans=1;}
        else if( this.dis < that.dis ){turnans=-1;}
        else{turnans=0;}
        return turnans;
     }
    }
    
    
        public static void main(String[] args)throws Exception {
 
     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
  
          String[] Num = br.readLine().split("" "");
          int N = Integer.parseInt(Num[0]);
          Point2D[] data =new Point2D[N];
          Graph detectGraph=new Graph(N);
//          System.out.println(N);
          for(int i=0;i<N;i++){
             String[] readlinedata = br.readLine().split("" "");
             double pointx=Double.parseDouble(readlinedata[0]);
             double pointy=Double.parseDouble(readlinedata[1]);
             data[i]=new Point2D(pointx,pointy);
//             StdDraw.setPenColor(Color.BLACK);
//             StdDraw.text(data[i].x(),data[i].y()+0.02,Integer.toString(i));
//             StdDraw.filledCircle(data[i].x(), data[i].y(),0.01);
//             System.out.println(data[i]);
          }
          
        Point2D sourcePoint =new Point2D(data[0].x(),data[0].y());
        Point2D targetPoint =new Point2D(data[1].x(),data[0].y());
        
        int Max=0,min=0;
                 
                for(int v=0;v<N;v++){
                    
                    if( data[v].x()+data[v].y() < sourcePoint.x()+sourcePoint.y()){// Find min
                        sourcePoint=data[v];
                        min=v;
                    }
                    
                    if( data[v].x()+data[v].y() > targetPoint.x()+targetPoint.y()){ // Find Max
                        targetPoint=data[v];
                        Max=v;
                    }
//                        for(int w :detectGraph.adj(v)){
////                          StdOut.println( v+""->""+w);
//                   }
                }
        
//        System.out.println(min+"" ""+Max);
        MinPQ<setPoint> findminDis =new MinPQ<setPoint>();

//        System.out.println(N);
            for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        if( data[i].x()<data[j].x() && data[i].y()<data[j].y()){
                            setPoint dis2target=new setPoint(data[i],data[j],i,j);
                            findminDis.insert(dis2target);

                        }
                    }
            }

            int out=0,A=0,B=0;
            double distance;
//            System.out.println(findminDis.size());
            while( !findminDis.isEmpty() ){

                   A=findminDis.min().numberA;
                   B=findminDis.min().numberB;
                   
                    distance=findminDis.min().dis;
//                    System.out.printf( ""%1.3f\n"", distance);
                    detectGraph.addEdge(A,B);
                    findminDis.delMin();
                    DepthFirstSearch Path = new DepthFirstSearch(detectGraph,min);

//                    StdDraw.setPenColor(Color.GREEN);
//                    StdDraw.filledCircle(data[A].x(),data[A].y(),0.004);
//                    StdDraw.setPenColor(Color.RED);
//                    StdDraw.filledCircle(data[B].x(),data[B].y(),0.008);
//                    StdDraw.line(data[A].x(), data[A].y(),data[B].x(), data[B].y());
//                    StdDraw.text(data[B].x(), data[B].y()+0.01,Double.toString(distance));
                    
//                    Thread.currentThread().sleep(100);
//                   System.out.printf( ""%1.3f\n"",distance);
                          if( Path.marked(Max) ){
                          System.out.printf( ""%1.3f\n"",distance);
//                          StdOut.println( min+""->""+Max);
                          out=1;
                          }
//               if(findminDis.isEmpty()){
//                   distance=data[min].distanceTo(data[Max]);
//                   System.out.printf( ""%1.3f\n"",distance);
//               }
                          

              if(out==1){
                  break;
              }
            }
        
        
       }
    }
}


