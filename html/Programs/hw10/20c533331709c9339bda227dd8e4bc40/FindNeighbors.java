
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class Main {
    public static void main(String[] args) throws Exception{
        //Point2D p1 = null ;
        //StdOut.println(p1.x());
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        int N = 20;
        Point2D[] p = new Point2D[N];
        //ArrayList<Point2D> p = new ArrayList<Point2D>();
         //StdOut.println(""Hi"");
    for (int i = 0; i<N; i++) {
        //StdOut.println(""Hi Again"");
            String[] xy =br.readLine().split("" "");
            Point2D point = new Point2D(Double.parseDouble(xy[0]),Double.parseDouble(xy[1]));
            //p.add(point);
            p[i] = point;
            //StdOut.println(p[i].x()+"" ""+p[i].y());
            //StdOut.println(""f"");
        }
        FindNeighbors FN = new FindNeighbors();
        FN.init(p);
        Point2D q= new Point2D(0.1354339200, 0.7019446863);
        //0.1354339200, 0.7019446863
        //0.531440,0.616918
        //0.144932,0.938569
        //0.416793,0.610965
        //Point2D close = FN.query(q,3);
        //StdOut.println(close.x()+"" ""+close.y());
       Point2D[] close = FN.query(q,N+1);
       int count =0;
       while(count<close.length){
            StdOut.println(count+"" ""+close[count]);
            count++;
        }

    }
    }
}

