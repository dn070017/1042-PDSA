
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * 1042 PDSA
 * hw09_Expression
 * @author Robert
 */
public class CriticalDis {
    
    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // first data = number of points(num)
            String header = br.readLine();
            int num = Integer.parseInt(header);
            
//            StdDraw.setCanvasSize(500, 500);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(.01);
//            StdDraw.setPenColor(Color.red);
            
            Point2D[] points = new Point2D[num];
            for(int k = 0; k < num; k++) {
                String[] tmp = br.readLine().split("" "");
                double x = Double.parseDouble(tmp[0]);
                double y = Double.parseDouble(tmp[1]);
                points[k] = new Point2D(x, y);
//                points[k].draw();
            }
            // source and target
            double mindis = 100;
            double maxdis = 0;
            int source = 0;
            int target = 0;
            for (int i = 0; i < num; i++){
                double tmp = points[i].x() + points[i].y();
                if (tmp > maxdis){
                    target = i;
                    maxdis = tmp;
                }
                if (tmp < mindis){
                    source = i;
                    mindis = tmp;
                }
            }
            // distance store
            MinPQ<Double> pq = new MinPQ<Double>();
            for (int i = 0; i < num; i++){
                for (int j = i+1; j < num; j++){
                    pq.insert(points[i].distanceTo(points[j]));
                }
            }
            // while loop to find min-d
            boolean loop = true;
            double d = 0;
            while(loop){
                d = pq.delMin();
                Digraph G = new Digraph(num);
                for (int i = 0; i < num; i++){
                    for (int j = 0; j < num; j++){
                        if (points[i].x() < points[j].x()){
                            if (points[i].y() < points[j].y()){
                                if (points[i].distanceTo(points[j]) < d){
                                    G.addEdge(i, j);
                                }
                            }
                        }
                    }
                }
                DirectedDFS dfs = new DirectedDFS(G, source);
                if (dfs.marked(target))
                    loop = false;
            }
            System.out.printf(""%1.3f\n"", d);
        }
    }
    
}

