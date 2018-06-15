import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        //StdDraw.setCanvasSize(400, 400);
        //StdDraw.setXscale(0, 100);
        //StdDraw.setYscale(0, 100);
        //StdDraw.setPenRadius(0.01);
        Point2D[] graph = new Point2D[a.length];
        graph = a;
        int k = 0;
        double min_y = 0;
        for (int i = 0; i < graph.length; i++) {
            //double x = StdRandom.uniform(25, 75);
            //double y = StdRandom.uniform(25, 75);
            //graph[i] = new Point2D(x, y);
            //graph[i].draw();

            if (i == 0) {
                min_y = graph[0].y();
            } else if (i > 0 && graph[i].y() < min_y) {
                k = i;
                min_y = graph[i].y();
            }
            //System.out.print(graph[i] + ""\n"");
        }
        //StdDraw.setPenRadius(0.02);
        //StdDraw.setPenColor(StdDraw.BOOK_RED);
        //StdDraw.point(graph[k].x(), graph[k].y()); // draw the point with min_y
        //System.out.print(k + ""\n"");

        double angle[] = new double[a.length];
        double angle_ord[] = new double[a.length];

        for (int i = 0; i < graph.length; i++) {
            double dist_x = graph[i].x() - graph[k].x();
            double dist_y = graph[i].y() - graph[k].y();
            angle[i] = Math.atan2(dist_y, dist_x);
            //System.out.printf(""%f\n"", angle[i]);
            angle_ord[i] = angle[i];
        }
        //System.out.printf(""%s\n"", "" "");

        //Point2D k_graph = new Point2D(graph[k].x(), graph[k].y());
        //System.out.print(k_graph + ""\n"");
        //StdDraw.setPenRadius(0.02);
        //StdDraw.setPenColor(StdDraw.RED);
        //StdDraw.point(k_graph.x(), k_graph.y()); // draw the point with min_y
        //StdDraw.setPenRadius();
        //StdDraw.setPenColor(StdDraw.BLUE);
        Arrays.sort(angle_ord);
        for (int i = 0; i < graph.length; i++) {
            angle[i] = Math.floor(angle[i] * 1000000) / 1000000;
            angle_ord[i] = Math.floor(angle_ord[i] * 1000000) / 1000000;
        }
        for (int i = 0; i < graph.length; i++) {
            //System.out.print(angle_ord[i] + ""\n"");
        }

        Point2D[] new_graph = new Point2D[a.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (angle[j] == angle_ord[i]) {
                    //ord[i] = j;
                    new_graph[i] = graph[j];
                }
            }
            //System.out.print(new_graph[i] + ""\n"");
            //StdDraw.text(new_graph[i].x(), new_graph[i].y()+2, Integer.toString(i));
        }
        int graph_ccw[] = new int[a.length];
        //graph_ccw[0]=1;
        for (int i = 1; i < graph.length - 1; i++) {
            graph_ccw[i] = Point2D.ccw(new_graph[i - 1], new_graph[i], new_graph[i + 1]);
            //System.out.print(graph_ccw[i] + ""\n"");
        }
        graph_ccw[0] = Point2D.ccw(new_graph[a.length-1], new_graph[0], new_graph[1]);
        graph_ccw[a.length-1] = Point2D.ccw(new_graph[a.length-2], new_graph[a.length-1], new_graph[0]);
        for (int i = 0; i < graph.length; i++) {
            //System.out.print(graph_ccw[i] + ""\n"");
        }
        int ord[] = new int[a.length];
        int ori_ch[] = new int[a.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (new_graph[j] == graph[i]) {
                    ord[i] = j;
                    ori_ch[i] = graph_ccw[j];
                }
            }
            //System.out.print(ord[i] + ""\n"");
        }
        int ch_num = 0;
        for (int i = 0; i < graph.length; i++) {
            if (ori_ch[i] == -1) {
                ori_ch[i] = 0;
            }
            //System.out.print(ori_ch[i]+""\n"");
            ch_num = ch_num + ori_ch[i];
        }
        //System.out.print(ch_num+""\n"");
        int fin_ch[] = new int[ch_num];
        int n = 0;
        for (int j = 0; j < graph.length; j++) {
            if (ori_ch[j] == 1) {
                fin_ch[n] = j;
                n++;
            }
        }

        return fin_ch;
        //for (int i = 0; i < ch_num; i++) {
        //    System.out.print(fin_ch[i] + "" "");
        //}

        //System.out.printf(""%d\n"", """");
        //for (int i = 0; i < graph.length; i++){
        //System.out.print(graph[i] + ""\n"");
        //}
    }
public static void main(String[] args){
}
}
