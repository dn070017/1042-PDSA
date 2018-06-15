
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
class SetDistance implements Comparable<SetDistance> {
        public int origansizeA, origansizeB;
        private final double d;
        private final  Clustering clusterA;
        private final  Clustering clusterB;
        public SetDistance( Clustering a,  Clustering b) {
            d = a.getCentroid().distanceTo(b.getCentroid());
            clusterA = a;
            clusterB = b;
            origansizeA = a.getSize();
            origansizeB = b.getSize();}
        public int compareTo(SetDistance that) {
            if (this.d > that.d) {
                return 1;} 
            else if (this.d < that.d) {
                return -1;} 
            else {return 0;}}
        public Clustering geta() {
            return clusterA;}
        public Clustering getb() {
            return clusterB;}}
public class Clustering implements Comparable<Clustering>{
        private Point2D centroid;
        private int size;
        public int id;
        public  Clustering(Point2D p) {
            centroid = p;
            size = 1;}
        public  Clustering(Point2D p, int id) {
            this.id = id;
            centroid = p;
            size = 1;}
        public int compareTo( Clustering that) {
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
        public static  Clustering merge( Clustering a,  Clustering b) {
            double x = (a.getCentroid().x() * a.getSize() + b.getCentroid().x() * b.getSize()) / (a.getSize() + b.getSize());
            double y = (a.getCentroid().y() * a.getSize() + b.getCentroid().y() * b.getSize()) / (a.getSize() + b.getSize());
            Clustering c = new  Clustering(new Point2D(x, y));
            c.size = a.getSize() + b.getSize();
            return c;}    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String header = br.readLine();
            int N = Integer.parseInt(header);
            BST<Integer, Clustering> clusterBST = new BST<Integer, Clustering>();
            Clustering[] cluster1 = new Clustering[N];
            Clustering[] cluster2 = new Clustering[N];
//先放進去資料
            int n = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split("" "");
                double a = Double.parseDouble(data[0]);
                double b = Double.parseDouble(data[1]);
                Point2D point = new Point2D(a,b);
                cluster1[n] = new Clustering(point, n);
                cluster2[n] = new Clustering(point, n);
                clusterBST.put(n, cluster1[n]);
                n++;}
            MinPQ<SetDistance> finddistance = new MinPQ<SetDistance>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    finddistance.insert(new SetDistance(cluster1[i], cluster1[j]));}}
//把距離近的設在一起
            //開始時間
  //long startTime = System.currentTimeMillis();
            UF uf = new UF(N);
            while (clusterBST.size() > 3) {
                //如果超過三就繼續做
                SetDistance min = finddistance.delMin();
                if (clusterBST.contains(min.geta().id) && clusterBST.contains(min.getb().id)) {
                    if (clusterBST.get(min.geta().id).getSize() == min.origansizeA && clusterBST.get(min.getb().id).getSize() == min.origansizeB) {
                        uf.union(min.geta().id, min.getb().id);
                        Clustering c = Clustering.merge(min.geta(), min.getb());
                        c.id = min.geta().id;
                        clusterBST.put(c.id, c);
                        clusterBST.delete(min.getb().id);
                        cluster2[c.id] = c;
                        for (Integer k : clusterBST.keys()) {
                            if (k == c.id) {
                                continue;}
                            finddistance.insert(new SetDistance(c, cluster2[k]));}}}}  
 //結束時間
  //long endTime = System.currentTimeMillis();
//執行時間
  //long totTime = startTime - System.currentTimeMillis();
  
//印出執行時間
 // System.out.println(""Using Time:"" + totTime);                       
            int i = 0;
            Clustering[] c = new Clustering[clusterBST.size()];
            for (Integer k : clusterBST.keys()) {
                c[i++] = clusterBST.get(k);}
            Arrays.sort(c);
            for (int k = 0; k < c.length; k++) {
                System.out.println(c[k].getSize()+"" ""+String.format(""%.2f"",c[k].getCentroid().x())+"" ""+String.format(""%.2f"",c[k].getCentroid().y())); }
            double min = Double.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (uf.find(j) != uf.find(k)) {
                        double d = cluster1[j].getCentroid().distanceTo(cluster1[k].getCentroid());
                        if (d < min) {min = d;}}}}}}}
