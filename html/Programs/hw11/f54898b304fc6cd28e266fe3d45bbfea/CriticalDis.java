
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LAB228
 */

//************************** inner class ************************************\\
//************************** storing type of distance ************************\\    
public class CriticalDis {

//******************************* properties *********************************\\            
  //  public Point2D[] points; // The constructor of points can be changed; !!!!!!!!!!!!!!!
    public CriticalDis(){}
  

//******************* MAIN : read position information ***********************\\    
    public static void main(String[] args) throws Exception{
       try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            String N = br.readLine();
            Point2D[] points = new Point2D[Integer.parseInt(N)];         //所有讀取到的點 放起來
            Map<Point2D,Integer> map = new HashMap<Point2D,Integer>(); //放著點跟代號的對照表
            Point2D ref = new Point2D(0,0);
            Point2D max = new Point2D(0,0);
            Point2D min = new Point2D(0.999,0.999);
            MinPQ<Double> distances = new MinPQ<>();
            
            int count=0;
            
            
           for (int i=0; i<Integer.parseInt(N); i++)
            {
                String buf[] = br.readLine().split("" "");
          //      System.out.println(buf[0]+"" \t""+buf[1]);
                double x = Double.valueOf(buf[0]);
                double y = Double.valueOf(buf[1]);
                points[i] = new Point2D(x,y);
                if( (points[i].x()+points[i].y()) > (max.x()+max.y() ) )  max = points[i];
                if( (points[i].x()+points[i].y()) < (min.x()+min.y() ) )   min = points[i];
                map.put(points[i],i);
          //      count++;
            }
            
           for(int i=0; i<Integer.parseInt(N); i++){
                for(int j=i+1 ; j<Integer.parseInt(N); j++){
                    distances.insert(points[i].distanceTo(points[j]));
                }
            }
            
            boolean connect = false;
            double tempd = 0;
            
            while(!connect&&!distances.isEmpty()){
                tempd = distances.delMin();
                DepthFirstDirectedPaths p = new DepthFirstDirectedPaths(Graphing(points,tempd),map.get(min));
                connect = p.hasPathTo(map.get(max));
            }
               System.out.printf(""%1.3f\n"", tempd);
           
//                StdDraw.setPenRadius(0.01);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.point(max.x(),max.y());
//                StdDraw.setPenColor(StdDraw.PINK);
//                StdDraw.point(min.x(),min.y());
//                System.out.println(""MAX : \t""+map.get(max)+"" \t""+max.x()+"" \t""+max.y());
//                System.out.println(""MIN : \t""+map.get(min)+"" \t""+min.x()+"" \t""+min.y());

            
       }
     }       
//****************************************************************************\\
//***************************** get every edge *******************************\\    
    static public Digraph Graphing(Point2D[] points,double dis){
        
        int N = points.length;
        Digraph G = new Digraph (N);
        int k = 0;
        for(int i=0; i<N; i++){
            for(int j=0 ; j<N; j++){
                if(points[i].x()>points[j].x() && points[i].y()>points[j].y()){
                    if(dis > points[i].distanceTo(points[j])&&points[i].distanceTo(points[j])!=0){
                    G.addEdge(j, i);
                    }
                }
            }            
        }//*********** end of for
        
//                for(int i=0; i<N ; i++){
//                StdDraw.setPenRadius(0.01);
//                StdDraw.setPenColor(StdDraw.GRAY);
//                StdDraw.point(points[i].x(),points[i].y());
//                }
//                StdDraw.setPenRadius(0.02);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.point(points[8].x(),points[8].y());
//                StdDraw.point(points[14].x(),points[14].y());
//                StdDraw.setPenRadius(0.001);
//                StdDraw.circle(points[8].x(),points[8].y(), 0.357);
//                StdDraw.circle(points[14].x(),points[14].y(), 0.357);
//                StdDraw.line(points[14].x(), points[14].y(), points[2].x(), points[2].y());
            return G;
    }
//****************************************************************************\\      
}

