
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Frank
 */
public class MyConvexHull {

    public static int N=10;
    private String[] s;
    private int M =0;

    
    
    public static void main(String[] args) {
        int minpoint=0;
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[N];
        Point2D[] cc = new Point2D[N];
        Point2D[] ww = new Point2D[N];
        Point2D min = new Point2D(100,100);
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
            switch(points[i].compareTo(min)){
                case 1:
                    break;
                case 0:
                    break;
                case -1:
                    min=points[i];
                    minpoint=i;
                    break;
            }
        }
        cc=points.clone();

        // draw p = (x0, x1) in red
        Point2D p = new Point2D(min.x(), min.y());
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.02);
        p.draw();
        // draw line segments from p to each point, one at a time, in polar order
        
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Arrays.sort(points, p.POLAR_ORDER);
        
//        for (int i = 0; i < N; i++) {
//            p.drawTo(points[i]);
//            StdDraw.show(10);
//        }
        
        ww[0] = p;
        ww[1] = points[0];
        ww[0].drawTo(ww[1]);
        int j = 0;
        int i = 0;
        while (i < N - 1) {
            ww[j + 2] = points[i + 1];
            switch (Point2D.ccw(ww[j], ww[j + 1], ww[j + 2])) {
            case -1:
                j--;
                break;
            case 0:
                                ww[j+1].drawTo(ww[j+2]);
                j++;
                i++;
                break;
            case 1:
                                ww[j+1].drawTo(ww[j+2]);
                j++;
                i++;
                break;
            }
        }
        for (int k = 0; k < N; k++) {
            for (i = 1; i < j+2; i++) {
                if(cc[k]==ww[i]){
                System.out.println(k);
                }
            }

        }
    }
    
    
}

