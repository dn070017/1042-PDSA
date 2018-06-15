

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author daf
 */
public class Clustering {

    private static class SetCluster implements Comparable<SetCluster> {

        private Point2D centroid;
        private int size;
        public int id;//optional
        public SetCluster(Point2D p) {
            centroid = p;
            size = 1;}
        public SetCluster(Point2D p, int id) {
            this.id = id;
            centroid = p;
            size = 1;}
        public int compareTo(SetCluster that) {
            if (this.getSize() > that.getSize()) {
                return -1;} 
            else if (this.getSize() < that.getSize()) {
                return 1;} 
            else if (this.centroid.x() < that.centroid.x()) {
                return -1;} 
            else if (this.centroid.x() > that.centroid.x()) {
                return 1;} 
            else if (this.centroid.y() < that.centroid.y()) {
                return -1;} 
            else if (this.centroid.y() > that.centroid.y()) {
                return 1;} 
            else {return 0;}}
        public Point2D getCentroid() {
            return centroid;}
        public int getSize() {return size;}
        public static SetCluster merge(SetCluster a, SetCluster b) {
            double x = (a.getCentroid().x() * a.getSize() + b.getCentroid().x() * b.getSize()) / (a.getSize() + b.getSize());
            double y = (a.getCentroid().y() * a.getSize() + b.getCentroid().y() * b.getSize()) / (a.getSize() + b.getSize());
            SetCluster c = new SetCluster(new Point2D(x, y));
            c.size = a.getSize() + b.getSize();
            return c;}}

    private static class SetDistance implements Comparable<SetDistance> {

        public int originsizeA, originsizeB;
        private final double d;
        private final SetCluster clusterA;
        private final SetCluster clusterB;

        public SetDistance(SetCluster a, SetCluster b) {
            d = a.getCentroid().distanceTo(b.getCentroid());
            clusterA = a;
            clusterB = b;
            originsizeA = a.getSize();
            originsizeB = b.getSize();
        }

        public int compareTo(SetDistance that) {
            if (this.d > that.d) {
                return 1;
            } else if (this.d < that.d) {
                return -1;
            } else {
                return 0;
            }
        }

        public SetCluster getCluster_A() {
            return clusterA;
        }

        public SetCluster getCluster_B() {
            return clusterB;
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String header = br.readLine();
            int N = Integer.parseInt(header);

            BST<Integer, SetCluster> clusterBST = new BST<Integer, SetCluster>();
            SetCluster[] clustersOriginal = new SetCluster[N];
            SetCluster[] clustersDo = new SetCluster[N];
//先放進去資料
            int n = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] inpair = in.split("" "");
                Point2D point = new Point2D(Double.parseDouble(inpair[0]), Double.parseDouble(inpair[1]));
                clustersOriginal[n] = new SetCluster(point, n);
                clustersDo[n] = new SetCluster(point, n);
                clusterBST.put(n, clustersOriginal[n]);
                n++;}
            MinPQ<SetDistance> disEvpq = new MinPQ<SetDistance>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    disEvpq.insert(new SetDistance(clustersOriginal[i], clustersOriginal[j])); }}
//把距離近的設在一起
            UF cc = new UF(N);
            while (clusterBST.size() > 3) {
                SetDistance ev = disEvpq.delMin();
                //System.out.println(ev.getCluster_A().id + "" "" + ev.getCluster_B().id + "": "" + ev.d);
                if (clusterBST.contains(ev.getCluster_A().id) && clusterBST.contains(ev.getCluster_B().id)) {
                    if (clusterBST.get(ev.getCluster_A().id).getSize() == ev.originsizeA && clusterBST.get(ev.getCluster_B().id).getSize() == ev.originsizeB) {
                        cc.union(ev.getCluster_A().id, ev.getCluster_B().id);
                        SetCluster c = SetCluster.merge(ev.getCluster_A(), ev.getCluster_B());
                        c.id = ev.getCluster_A().id;
                        clusterBST.put(c.id, c);
                        clusterBST.delete(ev.getCluster_B().id);
                        clustersDo[c.id] = c;
//                        StdDraw.filledCircle(c.getCentroid().x(), c.getCentroid().y(), c.getSize() * 0.004);
                        for (Integer k : clusterBST.keys()) {
                            if (k == c.id) {
                                continue;
                            }
                            disEvpq.insert(new SetDistance(c, clustersDo[k]));
                        }
                    }
                }
            }
            
            int i = 0;
            SetCluster[] res = new SetCluster[clusterBST.size()];
            for (Integer k : clusterBST.keys()) {
                res[i++] = clusterBST.get(k);
            }

            Arrays.sort(res);

            for (int j = 0; j < res.length; j++) {
                System.out.println(String.format(""%d %.2f %.2f"", res[j].getSize(), res[j].getCentroid().x(), res[j].getCentroid().y()));
            }
            double min = Double.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (cc.find(j) != cc.find(k)) {
                        double d = clustersOriginal[j].getCentroid().distanceTo(clustersOriginal[k].getCentroid());
                        if (d < min) {
                            min = d;}}}}
            if (N == 1) {
                System.out.println(String.format(""%.2f"", 0.000));} 
            else {
                System.out.println(String.format(""%.2f"", min));}}}}

