//============================================================================//
import java.io.BufferedReader;
import java.io.FileReader;
public class Clustering {
//------------------------------Class: cluster--------------------------------//
    public static class cluster {

        private Point2D[] points;
        private Point2D centroid;
        private int size;

        public cluster(Point2D[] input) {
            points = input;
            size = input.length;
            centroid();
        }

        public int getSize() {
            return size;
        }

        public Point2D getCentroid() {
            return centroid;
        }

        private void centroid() {//calculate the centroid
            double sx = 0, sy = 0; //sum of x and y of all points
            for (int i = 0; i < size; i++) {
                sx += points[i].x();
                sy += points[i].y();
            }
            centroid = new Point2D(sx / size, sy / size);
        }

        //----- calculate the  distance between this & that ------------------// 
        public double distanceTo(cluster that) {
            double dx = this.centroid.x() - that.centroid.x();
            double dy = this.centroid.y() - that.centroid.y();
            return Math.sqrt(dx * dx + dy * dy);
        }

        public void clustering(cluster that) {
            Point2D[] temp = new Point2D[this.size + that.size];
            for (int i = 0; i < this.size; i++) {
                temp[i] = this.points[i];
            }
            for (int i = this.size; i < this.size + that.size; i++) {
                temp[i] = that.points[i - this.size];
            }
            size = this.size + that.size;
            points = temp;
            centroid();
        }
    }
//------------------------------Class: pair-----------------------------------//
    public static class Pair implements Comparable<Pair> {

        private cluster c1;
        private cluster c2;
        private int c1_index;
        private int c2_index;
        private double distance;

        public Pair(cluster cluster1, cluster cluster2, int i, int j) {
            c1 = cluster1;
            c2 = cluster2;
            c1_index = i;
            c2_index = j;
            distance = c1.distanceTo(c2);
        }

        public double getDistance() {
            return distance;
        }

        public int compareTo(Pair that) {
            if (this.getDistance() < that.getDistance()) {
                return -1;
            } else if (this.getDistance() > that.getDistance()) {
                return 1;
            } else {
                return 0;
            }
        }

        public cluster getCluster1() {
            return c1;
        }

        public cluster getCluster2() {
            return c2;
        }

        public int getC1_index() {
            return c1_index;
        }

        public int getC2_index() {
            return c2_index;
        }
    }
//---------------------------------main---------------------------------------//
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {            
            int size = Integer.parseInt(br.readLine());
            
            cluster[] points = new cluster[size];
            for (int i = 0; i < size; i++) {
                String[] data = br.readLine().split("" "");
                Point2D[] temp = new Point2D[1];
                temp[0] = new Point2D(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
                points[i] = new cluster(temp);
                //System.out.println(points[i].getCentroid().x()+"" ""+points[i].getCentroid().y());
            }
            MinPQ<Pair> priority = new MinPQ();
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    Pair temp = new Pair(points[i], points[j], i, j);
                    priority.insert(temp);
                }
            }
            
            
            QuickUnionUF systemconnection = new QuickUnionUF(size);
            while (systemconnection.count() > 3) {
                Pair temp = priority.delMin();
                int u = temp.getC1_index();
                int v = temp.getC2_index();
                while (!(systemconnection.find(u) == u || systemconnection.find(v) == v)) {
                    temp = priority.delMin();
                    u = temp.getC1_index();
                    v = temp.getC2_index();
                }
                if (points[u].getSize() < points[v].getSize()) {
                    systemconnection.union(u, v);
                    points[v].clustering(points[u]);
                } else {
                    systemconnection.union(v, u);
                    points[u].clustering(points[v]);
                }
                for (int i = 0; i < size - 1; i++) {
                    for (int j = i + 1; j < size; j++) {
                        if (systemconnection.find(i) == i && systemconnection.find(j) == j) {
                            Pair temp1 = new Pair(points[i], points[j], i, j);
                            priority.insert(temp1);
                        }
                    }
                }
            }
            System.out.println(systemconnection.count());
            cluster[] ans = new cluster[systemconnection.count()];
            for (int i = 0; i < size; i++) {
                if (systemconnection.find(i) == i) {
                    System.out.println(points[i].getSize() + "" "" + points[i].getCentroid().x() + "" "" + points[i].getCentroid().y());
                }
            }
            
        }
    }
}
