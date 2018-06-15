
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


//************************** storing type of distance ************************\\    
public class CriticalDis {
//************************** inner class *************************************\\
    public static class EDGEs implements Comparable<EDGEs>{
        int source;
        int target;
        double dis;
        
        public EDGEs(int source, int target, double dis){
            this.source = source;
            this.target = target;
            this.dis = dis;
            return;
        }

        @Override
        public int compareTo(EDGEs that) {            
            if(this.dis > that.dis) return +1;
            else if(this.dis < that.dis)  return -1;
            else return 0;
        }
    }
//******************************* properties *********************************\\            
  //  public Point2D[] points; // The constructor of points can be changed; !!!!!!!!!!!!!!!
    public CriticalDis(){}
  

//******************* MAIN : read position information ***********************\\    
    public static void main(String[] args) throws Exception{
       try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            String N = br.readLine();
            Point2D[] points = new Point2D[Integer.parseInt(N)];         //所有讀取到的點 放起來
            Map<Point2D,Integer> map = new HashMap<Point2D,Integer>(); //放著點跟代號的對照表
            Point2D max = new Point2D(0,0);
            Point2D min = new Point2D(1,1);
            Digraph G = new Digraph (Integer.parseInt(N));
            MinPQ<Integer> AAA = new MinPQ<>();
            MinPQ<EDGEs> edges = new MinPQ<>();
            
            int count=0;
            
            
           for (int i=0; i<Integer.parseInt(N); i++)
            {
                String buf[] = br.readLine().split("" "");
                double x = Double.valueOf(buf[0]);
                double y = Double.valueOf(buf[1]);
                points[i] = new Point2D(x,y);
                if( (points[i].x()+points[i].y()) > (max.x()+max.y() ) )  max = points[i];
                if( (points[i].x()+points[i].y()) < (min.x()+min.y() ) )   min = points[i];
                map.put(points[i],i);
            }
//           
           EDGEs tempE;
           for(int i=0; i<Integer.parseInt(N); i++){
                for(int j=0 ; j<Integer.parseInt(N); j++){
                   if(points[i].x()>points[j].x() && points[i].y()>points[j].y()&&points[i].distanceTo(points[j])!=0){
//                   tempE =new EDGEs(j,i,points[i].distanceTo(points[j]));                   
                   edges.insert(new EDGEs(j,i,points[i].distanceTo(points[j])));
                   }
                }
           }
            
            
            boolean connect = false;
            EDGEs tempd = new EDGEs(0,0,0);
            
            while(!connect && !edges.isEmpty()){
                tempd = edges.delMin();
                G.addEdge(tempd.source, tempd.target);
                DepthFirstDirectedPaths p = new DepthFirstDirectedPaths(G,map.get(min));
                connect = p.hasPathTo(map.get(max));
            }
               System.out.printf(""%1.3f\n"",tempd.dis);
       }
     }       
//****************************************************************************\\
//***************************** get every edge *******************************\\    
  
//****************************************************************************\\      
}

