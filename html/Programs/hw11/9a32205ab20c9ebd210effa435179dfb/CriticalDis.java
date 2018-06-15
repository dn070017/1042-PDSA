import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Stack;

/**
.
 */
public class CriticalDis {

    public static void main(String[] args) throws Exception {

        boolean DEBUG = false;

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            /* 讀檔，幾十個點讀入 P，同時創造同樣容量的 G */
            int N = Integer.valueOf(br.readLine()); //有幾個點
            Digraph G = new Digraph(N);
            Point2D[] P = new Point2D[N];
            int count=0;
            while(br.ready()) {
                String[] data = br.readLine().split("" "");
                double x = Double.valueOf(data[0]);
                double y = Double.valueOf(data[1]);
                P[count] =  new Point2D(x,y);;
                count++;
            }
            if(DEBUG) for (Point2D p:P){System.out.println(""P(before): ""+p);}

            /* 找出 s = 14, t = 8 */
            int s = 0, t = 0;
            for(int i=0; i<N; i++){
                if(P[i].x()+P[i].y() < P[s].x()+P[s].y()) s = i;
                if(P[i].x()+P[i].y() > P[t].x()+P[t].y()) t = i;
            }



            MinPQ<Double> edges = new MinPQ<>();
            double sMin=9999, tMin=9999, stMin;

            for(int i=0; i<N; i++){
                if(P[i].x() > P[t].x() || P[i].y() > P[t].y() || P[i].x() < P[s].x() || P[i].y() < P[s].y()) continue; //排除

                for(int j=0; j<N; j++){
                    if(P[j].x() > P[t].x() || P[j].y() > P[t].y() || P[j].x() < P[s].x() || P[j].y() < P[s].y()) continue;
                    if(P[i].x() > P[j].x() || P[i].y() > P[j].y() || i==j) continue;

                    /* 搜集所有可能的 d 放到 MinPQ */
                    edges.insert(P[i].distanceTo(P[j]));

                    if(i==s) if(P[i].distanceTo(P[j]) < sMin) sMin = P[i].distanceTo(P[j]);
                    if(j==t) if(P[i].distanceTo(P[j]) < tMin) tMin = P[i].distanceTo(P[j]);

                }
            }



            /* MinPQ 前處理：使 min 為與 s t 有關 */
            stMin = Math.max(sMin,tMin);
            while (edges.min() < stMin) edges.delMin();





            /* DFS */


            DirectedDFS DFS = new DirectedDFS(G,s);


            double d = -999;

            if(DEBUG) StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            int lineCount = 0;
            while(!DFS.marked(t)){

                d = edges.delMin();
                G = new Digraph(N);

                for(int i=0; i<N; i++){
                    if(P[i].x() > P[t].x() || P[i].y() > P[t].y() || P[i].x() < P[s].x() || P[i].y() < P[s].y()) continue; //排除

                    for(int j=0; j<N; j++){
                        if(P[j].x() > P[t].x() || P[j].y() > P[t].y() || P[j].x() < P[s].x() || P[j].y() < P[s].y()) continue;
                        if(P[i].x() > P[j].x() || P[i].y() > P[j].y() || i==j) continue;

                        if(P[i].distanceTo(P[j]) > d) continue;

                        G.addEdge(i,j);
//                        StdDraw.line(P[i].x(),P[i].y(),P[j].x(),P[j].y());
//                        lineCount++;

                    }
                }

                DFS = new DirectedDFS(G,s);
//                System.out.println(""lineCount: ""+lineCount);


            }


            System.out.printf(""%1.3f\n"", d);






            /* 若 v, w 相鄰(adj)便畫線 */

            if(DEBUG) {
                StdDraw.setPenColor(StdDraw.GREEN);
                for (int v = 0; v < G.V(); v++) {
                    for (int w : G.adj(v)) {
                        if(DEBUG) System.out.println(""added: "" + v + ""->"" + w);
                        StdDraw.line(P[v].x(),P[v].y(),P[w].x(),P[w].y());
                    }
                }
            }

            /* 以points畫圖 */
            if(DEBUG) {
                for (int i = 0; i < N; i++) {
                    if (i == s) {
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.filledCircle(P[i].x(), P[i].y(), 0.01);
                        StdDraw.text(P[i].x(), P[i].y() + 0.01, String.valueOf(i));
                    } else if (i == t) {
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.filledCircle(P[i].x(), P[i].y(), 0.01);
                        StdDraw.text(P[i].x(), P[i].y() + 0.01, String.valueOf(i));
                    } else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledCircle(P[i].x(), P[i].y(), 0.01);
                        StdDraw.text(P[i].x(), P[i].y() + 0.01, String.valueOf(i));
                    }
                }
            }






        }
    }
}

