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
 * @author DANNY
 */
public class CriticalDis {
        public static class cedge implements Comparable<cedge>{
        int s;
        int t;
        double dis;
        
        public cedge(int s, int t, double dis){
            this.s = s;
            this.t = t;
            this.dis = dis;
            return;
        }
        public int compareTo(cedge that) {            
            if(this.dis > that.dis) return +1;
            else if(this.dis < that.dis)  return -1;
            else return 0;
        }
    }
        
    public static void main(String[] args) throws Exception{
       try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                      
           String buf0 = br.readLine();
           int n = Integer.valueOf(buf0);
           
           Point2D[] points = new Point2D[n];
           Point2D summax = new Point2D(0,0);
           Point2D summin = new Point2D(1,1);
           
           Map<Point2D,Integer> table = new HashMap<Point2D,Integer>();
           Digraph graph = new Digraph (n);
           
           MinPQ<cedge> edges = new MinPQ<>();
           
           int count = 0;
           
           while(br.ready()){
                String buf2[] = br.readLine().split("" "");
                double x = Double.valueOf(buf2[0]);
                double y = Double.valueOf(buf2[1]);
//                //===============================
//                StdDraw.setPenRadius(0.01);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.point(x,y);
//                //===============================
                points[count] = new Point2D(x, y);
                if( (points[count].x()+points[count].y()) > (summax.x()+summax.y() ) )  
                    summax = points[count];
                
                if( (points[count].x()+points[count].y()) < (summin.x()+summin.y() ) )   
                    summin = points[count];
                
                table.put(points[count],count);
                count++;
           }
//            //===============================
//                StdDraw.setPenRadius(0.01);
//                StdDraw.setPenColor(StdDraw.RED);
//                StdDraw.point(summax.x(),summax.y());
//                StdDraw.point(summin.x(),summin.y());
//           //===============================
           for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                   if(points[i].x()>points[j].x()&&points[i].y()>points[j].y()&&points[i].distanceTo(points[j])!=0){
//                   tempE =new EDGEs(j,i,points[i].distanceTo(points[j]));                   
                    edges.insert(new cedge(j,i,points[i].distanceTo(points[j])));
//                    StdDraw.setPenRadius(0.001);
//                    StdDraw.setPenColor(StdDraw.GREEN);
//                    StdDraw.line(points[i].x(),points[i].y(),points[j].x(),points[j].y());
                   }
                } 
           }
           boolean connect = false;
           cedge hold = new cedge(0,0,0);
            
            while(!connect&&!edges.isEmpty()){
                
                hold = edges.delMin();
                
                graph.addEdge(hold.s, hold.t);
                
                DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(graph,table.get(summin));
                connect = dfs.hasPathTo(table.get(summax));
//                StdDraw.setPenRadius(0.001);
//                StdDraw.setPenColor(StdDraw.GREEN);
//                StdDraw.line(points[hold.s].x(),points[hold.s].y(),points[hold.t].x(),points[hold.t].y());
//                if (dfs.hasPathTo(table.get(summax))) {
//                    StdOut.printf(""%d to %d:  "", graph, table.get(summax));
//                    for (int x : dfs.pathTo(table.get(summax))) {
//                        if (x == s) StdOut.print(x);
//                        else        StdOut.print(""-"" + x);
//                    }
//                    StdOut.println();
//                }
            }
               System.out.printf(""%1.3f\n"",hold.dis);
       }
     }
}

