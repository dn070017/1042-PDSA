

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author 許志鵬
 */
public class CriticalDis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("" "");
            int num = Integer.parseInt(header[0]);
            Point2D[] pts = new Point2D[num];

            for (int i = 0; i < num; i++) {
                String[] test1 = br.readLine().split("" "");
                double x = Double.parseDouble(test1[0]);
                double y = Double.parseDouble(test1[1]);
                pts[i] = new Point2D(x, y);

            }
            //find_s
            Point2D s = pts[0];
            int sl = 0;
            for (int i = 1; i < num; i++) {
                if (s.x() + s.y() > pts[i].x() + pts[i].y()) {
                    s = pts[i];
                    sl = i;
                }
            }
            //find_t
            Point2D t = pts[0];
            int tl = 0;
            for (int i = 1; i < num; i++) {
                if (t.x() + t.y() <= pts[i].x() + pts[i].y()) {
                    t = pts[i];
                    tl = i;
                }
            }

            Digraph excute = new Digraph(num);
            double[] dist = new double[num * (num - 1)];
            int a = 0;
            for (int i = 0; i < num; i++) {
                for (int j = i + 1; j < num; j++) {
                    dist[a] = pts[i].distanceTo(pts[j]);
                    a++;
                }
            }
            double token = 0;
            //sort every distance value
            for (int i = 0; i < dist.length; i++) {
                for (int j = i + 1; j < dist.length; j++) {
                    if (dist[i] >= dist[j]) {
                        token = dist[i];
                        dist[i] = dist[j];
                        dist[j] = token;
                    }
                }
            }
            int k = 0;
            while (k<dist.length) {
                for (int i = 0; i < num-1; i++) {
                    for (int j =1; j < num; j++) {
                        if ((pts[i].x() < pts[j].x() && pts[i].y() < pts[j].y()) && pts[i].distanceTo(pts[j]) < dist[k]) {
                            excute.addEdge(i, j);
                        }
                        if ((pts[j].x() < pts[i].x() && pts[j].y() <pts[i].y()) && pts[i].distanceTo(pts[j]) < dist[k]) {
                            excute.addEdge(j, i);
                        }
                    }
                }
                
                
                DirectedDFS ans=new DirectedDFS(excute,sl);
                if(ans.marked(tl))
                {
                    break;
                }
                k++;
                
            }
            
           
            System.out.printf(""%1.3f\n"", dist[k-1]);
            
        }

    }

}

