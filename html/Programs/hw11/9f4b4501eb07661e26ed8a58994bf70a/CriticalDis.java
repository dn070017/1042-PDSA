import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class CriticalDis {

    private class BlockDisToOrder implements Comparator<Point2D> {

        public BlockDisToOrder() {
        }

        public int compare(Point2D p, Point2D q) {
            double blockdist1 = p.x() + p.y();
            double blockdist2 = q.x() + q.y();
            if (blockdist1 < blockdist2) {
                return -1;
            } else if (blockdist1 > blockdist2) {
                return +1;
            } else {
                return 0;
            }
        }
    }

    private final Point2D[] pts;
    private final int s, t;

    private boolean[] marked;
    public MinPQ<Double> discanditate;
    public double disthr;
    private double rangedis;

    CriticalDis(Point2D[] pts,double disthr) {
        this.pts = pts;
        Arrays.sort(this.pts, new BlockDisToOrder());
        this.s = 0;
        this.t = pts.length - 1;

        this.disthr = disthr;
        this.rangedis = Math.sqrt(2) * disthr;
        marked = new boolean[this.pts.length];
        Arrays.fill(marked, false);
        discanditate = new MinPQ();
    }

    private void setdisthr(){
        int w = 1;
        while (!(pts[0].x() < pts[w].x() && pts[0].y() < pts[w].y())) {w++;}
        this.disthr = pts[0].distanceTo(pts[w]);
    }
    
    public void reset(double disthr) {
        this.disthr = disthr;
        this.rangedis = Math.sqrt(2) * disthr;
        Arrays.fill(marked, false);
        discanditate = new MinPQ();
    }

    private void findPathtoT(int v, double searchrange) {
        int w = v + 1;
        while (w < pts.length) {
            if (!marked[w] && pts[v].x() < pts[w].x() && pts[v].y() < pts[w].y()) {
                double dis = pts[v].distanceTo(pts[w]);
                if (dis <= disthr) {
                    marked[w] = true;
                    findPathtoT(w, searchrange + rangedis);
                } else {
                    discanditate.insert(dis);
                }
            }
            w++;
        }
    }

    public boolean hasPathStoT() {
        marked[0] = true;
        findPathtoT(s, pts[0].x() + pts[0].y() + rangedis);
        if (marked[pts.length - 1]) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int N = Integer.parseInt(br.readLine());
            Point2D[] pts = new Point2D[N];
            for (int n = 0; n < N; n++) {
                String in = br.readLine();
                String[] inpair = in.split("" "");
                pts[n] = new Point2D(Double.parseDouble(inpair[0]), Double.parseDouble(inpair[1]));
            }

            double d = 0;           
            CriticalDis test = new CriticalDis(pts,d);
            while (!test.hasPathStoT()) {
                d = test.discanditate.delMin();
                test.reset(d);
            }
            System.out.printf(""%1.3f\n"",d);

        }
    }

}
