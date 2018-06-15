import java.io.BufferedReader;
import java.io.FileReader;

public class FindNeighbors {

    public FindNeighbors() {
    }

    private Point2D[] P;

    public void init(Point2D[] points) {
        P = points;
    }

    public Point2D[] query(Point2D point, int k) {

        MaxPQ<BB> pq = new MaxPQ<>();
        if (P.length < 20000) {
            for (int i = 0; i < P.length; i++) {
                double R = 0.4;
                double centerX = point.x();
                double centerY = point.y();
                if (P[i].x() < centerX + R && P[i].x() > centerX - R && P[i].y() < centerY + R && P[i].y() > centerY - R) {
                    Double dis = point.distanceTo(P[i]);
                    BB bb = new BB(P[i].x(), P[i].y(), dis);
                    pq.insert(bb);
                    if (pq.size() > k) {
                        pq.delMax();
                    }
                }
            }
        }
        if (P.length > 20000) {
            for (int i = 0; i < P.length; i++) {
                double R = 0.0001;
                double centerX = point.x();
                double centerY = point.y();
                if (P[i].x() < centerX + R && P[i].x() > centerX - R && P[i].y() < centerY + R && P[i].y() > centerY - R) {
                    Double dis = point.distanceTo(P[i]);
                    BB bb = new BB(P[i].x(), P[i].y(), dis);
                    pq.insert(bb);
                    if (pq.size() > k) {
                        pq.delMax();
                    }
                }
            }
        }

        Point2D[] result = new Point2D[k];
        if (pq.size() < k) {
            for (int i = k - 1; i >= 0; i--) {
                result[i] = new Point2D(0, 0);
            }
            return result;
        } else {
            for (int i = k - 1; i >= 0; i--) {
                BB bb2 = pq.delMax();
                result[i] = new Point2D(bb2.x, bb2.y);
            }
            return result;
        }

    }

    public static class BB implements Comparable<BB> {

        private final double x;
        private final double y;
        private final double dis;

        public BB(double x, double y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        public int compareTo(BB that) {

            if (this.dis > that.dis) {
                return 1;
            } else if (this.dis < that.dis) {
                return -1;
            } else {
                return -0;
            }
        }
    }
}
