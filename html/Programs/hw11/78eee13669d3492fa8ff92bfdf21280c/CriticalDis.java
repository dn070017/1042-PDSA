import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Kestin
 */
public class CriticalDis {
    private Digraph G;
    private Point2D[]  sourcepoint;
    private HashMap<Point2D,Integer> pointindex;
    private int number;
    
    public static class Path implements Comparable<Path>{ 
        private final double path;
        private final Point2D m ;
        private final Point2D n ;
        
        public Path(Point2D a , Point2D b){
            path = a.distanceTo(b);
            this.m=a;
            this.n=b;
        }
        
        public Point2D getfrom(){
            return this.m;
        }
        public Point2D geto(){
            return this.n;
        }

        public double getpathdis(){
            return this.path;
        }
        public int compareTo(Path that){
            if(this.path < that.path) return -1;
            if(this.path > that.path) return +1;
            else return 0;
        }
        
    }

    public static class Point2D implements Comparable<Point2D>{
        double x, y;
        int index;
        
        public Point2D(double a , double b){
            x = a;
            y = b;
        }
        public double x(){
            return this.x;
        }
        public double y(){
            return this.y;
        }

        public double distanceTo(Point2D that){
            double dx = this.x - that.x;
            double dy = this.y - that.y;
            double dis = Math.sqrt(dx*dx + dy*dy);
            return dis;
        }
        
        public int compareTo(Point2D that){
            if ((this.x + this.y) < (that.x + that.y)) return -1;
            else if ((this.x + this.y) > (that.x + that.y)) return +1;
            else return 0;
        }
    }

    public CriticalDis(){
        pointindex= new HashMap<>();
    }
    
    public void SetsourcePoint(int x){
        sourcepoint = new Point2D[x];
        G = new Digraph(x);
    }
    
    private void GraphBuild(Point2D start, Point2D end){
        MinPQ<Path> pathpq = new MinPQ<>();
        for(int i = 0 ; i < sourcepoint.length ; i++){
            for(int j = i+1 ; j < sourcepoint.length ; j++){
                if(sourcepoint[i].x() < sourcepoint[j].x()){
                    if(sourcepoint[i].y() < sourcepoint[j].y()){
                    Path newpath = new Path(sourcepoint[i], sourcepoint[j]);
                    pathpq.insert(newpath);
                    }
                }
            }
        }
        
//        int runtime=0;
        while(!pathpq.isEmpty()){
            Path tmp = pathpq.delMin();
            G.addEdge(pointindex.get(tmp.getfrom()), pointindex.get(tmp.geto()));
            DirectedDFS dfs = new DirectedDFS(G,pointindex.get(start));       
            if(dfs.marked(pointindex.get(end))==true){
                double d = tmp.getpathdis();
                System.out.printf(""%1.3f\n"", d);
                break;
            }
//            System.out.println(runtime);
//            runtime++;
        }
       
    }
      
    public static void main(String[] args) throws Exception {

        // Read file
        CriticalDis test = new CriticalDis();
        try(BufferedReader br = new BufferedReader(new FileReader(args[1]))){           
            test.number = Integer.parseInt(br.readLine());
            test.SetsourcePoint(test.number);
            int count=0;
            for(String coordinate;( coordinate = br.readLine()) != null; ){
                String[] cor=coordinate.split("" "");
                Double x = Double.parseDouble(cor[0]);
                Double y = Double.parseDouble(cor[1]);
                Point2D p = new Point2D(x, y);
                test.sourcepoint[count]=p;
                test.pointindex.put(p, count);
                count++;
            }

        }
        Arrays.sort(test.sourcepoint);
        
        //Find the start and end
        Point2D start = test.sourcepoint[0];
        Point2D end =test.sourcepoint[test.number-1];
        test.GraphBuild(start,end);
        
      
    }
    
}

