
import java.text.NumberFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Arrays;

public class CriticalDis {

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        double d = 0.000;


        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int DotNumber = Integer.parseInt(br.readLine());

            Point2D[] dot = new Point2D[DotNumber];

            double[] value = new double[DotNumber];

            int e = 0;
            while (true) {
                String header = br.readLine();
                if (header == null) {
                    break;
                }
                String[] place = header.split("" "");
                double x = Double.parseDouble(place[0]);
                double y = Double.parseDouble(place[1]);
                
                value[e] = x + y;

                dot[e] = new Point2D(x, y);

//                StdDraw.filledCircle(dot[e].x(), dot[e].y(),0.01);
//                StdDraw.text(dot[e].x(), dot[e].y()+0.03,String.valueOf(e+1));
                e++;
            }
            Arrays.sort(value);
            double s = value[0];
            double t = value[DotNumber - 1];
//            System.out.print(""s: "");
//            System.out.print(s);
//            System.out.printf(""\n"");
//            System.out.print(""t: "");
//            System.out.print(t);
//            System.out.printf(""\n"");

            int dotmin = 0;
            int dotmax = 0;

            for (int m = 0; m < DotNumber; m++) {
                if (dot[m].x() + dot[m].y() == s) {
                    dotmin = m;
//                    StdDraw.setPenColor(Color.RED);
//                    StdDraw.text(dot[m].x(), dot[m].y()+0.03,String.valueOf(m+1));
                } else if (dot[m].x() + dot[m].y() == t) {
                    dotmax = m;
//                    StdDraw.setPenColor(Color.GREEN);
//                    StdDraw.text(dot[m].x(), dot[m].y()+0.03,String.valueOf(m+1));

                }
            }

            Digraph G = new Digraph(DotNumber);
            while (true) {
                for (int i = 0; i < DotNumber; i++) {
                    for (int k = 0; k < DotNumber; k++) {
                        double b = dot[k].x() - dot[i].x();
                        double c = dot[k].y() - dot[i].y();
                        double Distance=Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2));
                        double RoundDis=Math.round(Distance/0.001)*0.001;
                        if ( dot[k].x() - dot[i].x() > 0 && dot[k].y() - dot[i].y() > 0 && RoundDis<= d) {
                            G.addEdge(i, k);
//                                StdDraw.line(dot[k].x(),dot[k].y() , dot[i].x(), dot[i].y());
//                                StdDraw.text((dot[k].x()+dot[i].x())/2, (dot[k].y()+dot[i].y())/2, String.valueOf(Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2))));
                        }
                    }
                }

                DirectedDFS dfs = new DirectedDFS(G, dotmin);
                if (dfs.marked(dotmax) == true) {
                    break;
                }
                d = d + 0.001;
            }
        }

        System.out.printf(""%1.3f\n"", d);
//            StdOut.println(G);        

    }

}

