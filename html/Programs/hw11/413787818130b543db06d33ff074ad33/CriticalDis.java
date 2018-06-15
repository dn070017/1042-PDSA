
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Arthur
 */
public class CriticalDis {

    public static boolean slope(Point2D a, Point2D b) {
        if (b.x() >= a.x() && b.y() >= a.y()) {
            return true;
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            {

                int size = Integer.parseInt(br.readLine());
                Point2D[] p = new Point2D[size];
                double[] xy = new double[size];
                BST<Double, Integer> st = new BST<Double, Integer>();
                for (int i = 0; i < size; i++) {
                    String[] input = br.readLine().split("" "");
                    p[i] = new Point2D(Double.parseDouble(input[0]), Double.parseDouble(input[1]));
//                    StdDraw.filledCircle(p[i].x(), p[i].y(), 0.005);
                    double temp;
                    temp = p[i].x() + p[i].y();
                    xy[i] = temp;
                    st.put(temp, i);
//                    System.out.println(p[i]);
                }
                if (size == 2) {
                    System.out.printf(""%1.3f\n"", p[0].distanceTo(p[1]));
                }
                Arrays.sort(xy);
                Digraph G = new Digraph(size);

                //x+y
//                System.out.printf(""%f\n"", xy[0]);
//                System.out.printf(""%f\n"", xy[19]);
                int minindex = st.get(xy[0]);
                int maxindex = st.get(xy[size - 1]);
                double d = 0;
//                System.out.println(st.get(xy[0]));//Min Index
//                System.out.println(st.get(xy[19]));//Max Index
//                StdDraw.setPenColor(StdDraw.RED);
//                StdDraw.filledCircle(p[maxindex].x(), p[maxindex].y(), 0.005);
//                StdDraw.filledCircle(p[minindex].x(), p[minindex].y(), 0.005);
//                StdDraw.setPenColor(StdDraw.GREEN);
                DirectedDFS dfs = new DirectedDFS(G, minindex);
                while (!dfs.marked(maxindex) && size != 2) {
                    dfs = new DirectedDFS(G, minindex);
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (slope(p[i], p[j]) && p[i].distanceTo(p[j]) < d && p[i].x() >= p[minindex].x() && p[i].y() >= p[minindex].y()
                                    && p[j].x() <= p[maxindex].x() && p[j].y() <= p[maxindex].y()) {
                                G.addEdge(i, j);
//                            StdDraw.line(p[i].x(), p[i].y(), p[j].x(), p[j].y());
//                        }
//
                            }
                        }
                    }
                    d = d + 0.001;
                }
                if(size<1000)
                d = d - 0.01;
                if(size>1000)
                    d= d-0.02;
//                if(size!=2)
//                System.out.printf(""%1.3f\n"",d);
                G = null;
                Digraph F = new Digraph(size);
                DirectedDFS dfsf = new DirectedDFS(F, minindex);
//                
                while (!dfsf.marked(maxindex)) {
                    dfsf = new DirectedDFS(F, minindex);
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (slope(p[i], p[j])) {
                                if (p[i].distanceTo(p[j]) < d && p[i].x() >= p[minindex].x() && p[i].y() >= p[minindex].y()
                                        && p[j].x() <= p[maxindex].x() && p[j].y() <= p[maxindex].y()) {
                                    F.addEdge(i, j);
//                            StdDraw.line(p[i].x(), p[i].y(), p[j].x(), p[j].y());
//                        }
//
                                }
                            }
                        }
                    }
                    if(size<1000)
                    d = d + 0.00001;
                    if(size>=1000)
                        d=d+0.00001;
                }
                if (size != 2) {
                    System.out.printf(""%1.3f\n"", d);
                }
            }

        }

    }
}

