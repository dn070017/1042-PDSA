
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Clustering2 {

    static double xaver(ArrayList<Point2D> b) {
        double sumx = 0;
        for (Point2D p : b) {
            sumx = sumx + p.x();
        }
        return sumx / b.size();
    }

    static double yaver(ArrayList<Point2D> b) {
        double sumx = 0;
        for (Point2D p : b) {
            sumx = sumx + p.y();
        }
        return sumx / b.size();
    }

    static class Cluster implements Comparable<Cluster>{

        ArrayList<Point2D> pointlist;
        int size;
        Point2D cent;
        double dist;

        Cluster(ArrayList clustlist) {
            this.pointlist = clustlist;
            this.size = clustlist.size();
            cent = new Point2D(xaver(clustlist), yaver(clustlist));
        }

        public int  getsize(){
            return this.size;
        }
        public void add(Point2D point) {
            this.pointlist.add(point);
            cent = new Point2D(xaver(pointlist), yaver(pointlist));
            this.size = pointlist.size();
        }

        public boolean contain(Point2D point) {
            if (pointlist.contains(point)) {
                return true;
            }
            return false;
        }

        public void merge(Cluster that) {
            for (Point2D point : that.pointlist) {
                this.pointlist.add(point);

            }
        }
         public int compareTo(Cluster that) {
        //write code here for compare name
             return this.size-that.size;
    }
    }

    static class Pair implements Comparable<Pair> {

        Cluster clusterA;
        Cluster clusterB;
        double dist;

        Pair(Cluster clusterA, Cluster clusterB) {
            this.clusterA = clusterA;
            this.clusterB = clusterB;
            this.dist = clusterA.cent.distanceTo(clusterB.cent);
        }

        public double distance(Cluster clusterA, Cluster clusterB) {
            return clusterA.cent.distanceTo(clusterB.cent);
        }

        public boolean contain(Point2D point) {
            if (clusterA.contain(point)) {
                return true;
            }
            if (clusterB.contain(point)) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Pair that) {
            if (this.dist > that.dist) {
                return +1;
            }
            if (this.dist < that.dist) {
                return -1;
            }
            return 0;
        }
    }

    static void merge(Cluster a, Cluster b) {
        
        
        ArrayList al = new ArrayList();
        al.addAll(a.pointlist);
        al.addAll(b.pointlist);
        Cluster cluster = new Cluster(al);
       
        clusters.remove(a);
        clusters.remove(b);
        centpoints.remove(a.cent);
        centpoints.remove(b.cent);

        for (Cluster cl : clusters) {
            pq.insert(new Pair(cl, cluster));
        }

        clusters.add(cluster);
        centpoints.add(cluster.cent);

//        StdDraw.setPenColor(Color.red);
//        StdDraw.filledCircle(cluster.cent.x(), cluster.cent.y(), 0.008);
//        StdDraw.setPenColor(Color.green);
//        StdDraw.line(b.cent.x(), b.cent.y(), a.cent.x(), a.cent.y());
    }

    static MinPQ<Pair> pq;
    static ArrayList<Point2D> centpoints;
    static ArrayList<Cluster> clusters;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        N = Integer.parseInt(br.readLine());
        pq = new MinPQ();
        centpoints = new ArrayList<Point2D>();
        Cluster[] clusterarray = new Cluster[N];
        clusters = new ArrayList<Cluster>();

        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split("" "");
            double x = Double.parseDouble(st[0]);
            double y = Double.parseDouble(st[1]);
//            pointarray[i] = new Point2D(x, y);
            ArrayList<Point2D> firstal = new ArrayList<Point2D>();
            firstal.add(new Point2D(x, y));
            Cluster firstcl = new Cluster(firstal);
            clusterarray[i] = firstcl;
            centpoints.add(firstcl.cent);
            clusters.add(firstcl);
//            StdDraw.filledCircle(x, y, 0.006);
        }

        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                Pair pair = new Pair(clusterarray[i], clusterarray[k]);
                pq.insert(pair);
            }
        }
//        Pair pa = pq.delMin();
//        StdDraw.filledCircle(pa.clusterA.cent.x(),pa.clusterA.cent.y(), 0.01);
//        StdDraw.filledCircle(pa.clusterB.cent.x(),pa.clusterB.cent.y(), 0.01);
        while (N > 3) {
            Pair pair = pq.delMin();
            if (centpoints.contains(pair.clusterA.cent) && centpoints.contains(pair.clusterB.cent)) {
                merge(pair.clusterA, pair.clusterB);
//                StdOut.print(centpoints.size() + ""\n"");
                N--;
            }
        }

        Collections.sort(clusters,Collections.reverseOrder());
//        for(int i=0;i<clusters.size();i++){
//            StdOut.print(clusters.get(i).size);
//            System.out.printf(""%.2f"",clusters.get(i).cent.x());
//            System.out.printf("","");
//            System.out.printf(""%.2f"",clusters.get(i).cent.y());
//            System.out.printf(""\n"");
//        }
        for (Cluster c : clusters) {
            System.out.print(c.size+"" "");
            System.out.printf(""%.2f"",c.cent.x());
            System.out.printf("" "");
            System.out.printf(""%.2f"",c.cent.y());
            System.out.printf(""\n"");
        }
//        Collections.sort(centpoints);
//        for (Point2D p : centpoints) {
//            
//            System.out.printf(""%.2f"",p.x());
//            System.out.printf("" "");
//            System.out.printf(""%.2f"",p.y());
//            System.out.printf(""\n"");
//        }
        double min = clusters.get(0).pointlist.get(0).distanceTo(clusters.get(1).pointlist.get(0));
        Point2D mina=new Point2D(0,0);
        Point2D minb=new Point2D(0,0);
        for (int i = 0; i < clusters.size(); i++) {
            for (int k = i + 1; k < clusters.size(); k++) {
                for (Point2D p : clusters.get(i).pointlist) {
                    for(Point2D q : clusters.get(k).pointlist){
                        if (min>p.distanceTo(q)){
                            min =p.distanceTo(q);
                            mina=p;
                            minb=q;
                        }
                    }
                }
            }
        }
        
        
        System.out.printf(""%.2f"",min);
//        StdDraw.setPenColor(Color.blue);
//        StdDraw.line(mina.x(),mina.y(),minb.x(),minb.y());
    }
}

