
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class Clustering {
    
    public static class Cluster implements Comparable<Cluster>{
        private int size;
        private Point2D centroid;
        private Point2D[] points;
        private double x;
        private double y;

        public static Comparator<Cluster> SIZE_ORDER = new Cluster.SizeOrder();
        public static Comparator<Cluster> X_ORDER = new Cluster.XOrder();
        public static Comparator<Cluster> Y_ORDER = new Cluster.YOrder();

        public Cluster(int num){
            this.points = new Point2D[num];
            this.size = 0;
            this.centroid = null;
        }
        
        public Cluster(Point2D that,int num){
            this.points = new Point2D[num];
            this.size = 1;
            this.points[0] = that;
            this.centroid = that;
        }
        
        public Cluster addCluster(Cluster that){
            Cluster cluster = new Cluster(this.size + that.size);
            System.arraycopy(this.points, 0, cluster.points, 0, this.size);
            System.arraycopy(that.points, 0, cluster.points,this.size,that.size);
            
            cluster.x = (this.centroid.x()*this.size + that.centroid.x()*that.size)/(this.size + that.size);
            cluster.y = (this.centroid.y()*this.size + that.centroid.y()*that.size)/(this.size + that.size);
            cluster.centroid = new Point2D(cluster.x,cluster.y);
            cluster.size = this.size + that.size;
            return cluster;
        }
        
        public Point2D[] pointsArray(){
            return points;
        }
        
        public Point2D centroid(){
            return centroid;
        }
        
        public int size(){
            return size;
        }
        
        public boolean equals(Cluster that){
            if ( (this.size != that.size) || (!this.centroid.equals(that.centroid) ) ) return false;
            if ( ! Arrays.equals(this.pointsArray(), that.pointsArray()) ) return false;
            return true;
        }
        
        public void print_points(){
            System.out.printf(""points = "");
            for(int i=0; i<this.size; i++){
                System.out.printf(this.points[i].toString()+"" "");
            }
            System.out.printf(""\n"");
        }
        
        public int compareTo(Cluster that) {
            if (this.size > that.size) return -1;
            if (this.size < that.size) return +1;
            if (this.centroid.x() < that.centroid.x() ) return -1;
            if (this.centroid.x() > that.centroid.x() ) return +1;
            if (this.centroid.y() < that.centroid.y() ) return -1;
            if (this.centroid.y() > that.centroid.y() ) return +1;
            return 0;
        }
        
        private static class SizeOrder implements Comparator<Cluster> {
            public int compare(Cluster p, Cluster q) {
                if (p.size < q.size) return -1;
                if (p.size > q.size) return +1;
                return 0;
            }
        }
        
        private static class XOrder implements Comparator<Cluster> {
            public int compare(Cluster p, Cluster q) {
                if (p.centroid.x() < q.centroid.x() ) return -1;
                if (p.centroid.x() > q.centroid.x() ) return +1;
                return 0;
            }
        }
        
        private static class YOrder implements Comparator<Cluster> {
            public int compare(Cluster p, Cluster q) {
                if (p.centroid.y() < q.centroid.y() ) return -1;
                if (p.centroid.y() > q.centroid.y() ) return +1;
                return 0;
            }
        }
    }
    
    public static class Pair implements Comparable<Pair> {
        private double distance;
        private Cluster first;
        private Cluster second;

        public Pair (Cluster a , Cluster b){
            this.first = a;
            this.second = b;
            double dx = a.centroid.x() - b.centroid.x();
            double dy = a.centroid.y() - b.centroid.y();
            this.distance = Math.sqrt(( dx*dx + dy*dy ));
        }
        
        public int compareTo(Pair that){
            if(this.distance() > that.distance()) return +1;
            if(this.distance() < that.distance()) return -1;
            else return 0;
        }
        
        public double distance(){
            return distance;
        }
        
        public Cluster first(){
            return first;
        }
        
        public Cluster second(){
            return second;
        }
    }
    
    public static boolean isValid(Pair pair , int ic_index , Cluster[] invalid){
        for(int i = 0; i<ic_index;i++){
            if( pair.first().equals(invalid[i]) || pair.second().equals(invalid[i]) )  return false;
        }
        return true;
    }
    
    public static boolean isValid(Cluster cluster , int ic_index , Cluster[] invalid){
        for(int i = 0; i<ic_index;i++){
            if(cluster.equals(invalid[i]))  return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        
        // Stopwatch stopwatch = new Stopwatch();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StringBuilder everything = new StringBuilder();
            String line;
            everything.append(br.readLine());//first point
            while ((line = br.readLine()) != null) {
                everything.append("","");    //split each point by ','
                everything.append(line);
            }
            String[] data = everything.toString().split("","");
            
            int num = Integer.parseInt(data[0]);
            int current_N = num;
            Point2D[] point = new Point2D[num];
            Cluster[] cluster = new Cluster[num];
            Cluster[] new_cluster = new Cluster[num];
            Cluster[] invalid_cluster = new Cluster[num*num];
            MinPQ<Pair> pq = new  MinPQ<Pair>(num*num);
            int ic_index = 0;  //invalid_cluster index
            int nc_index = 0;  //new_cluster index
            int cluster_1_num = 0 , cluster_2_num = 0 , cluster_3_num = 0;
            int ans_index = 0;
            double min = 1;

//          *************read data***************
            for(int i=0;i<num;i++){
                String[] given = data[i+1].split("" "");
                point[i] = new Point2D(Double.parseDouble(given[0]), Double.parseDouble(given[1]));
                cluster[i] = new Cluster(point[i],num);
            }

//          *************initializing************
            for(int i=0; i<current_N; i++){
                for(int j=i+1; j<current_N; j++){
                    Pair pair = new Pair(cluster[i],cluster[j]);
                    pq.insert(pair);
                }
            }

            while(current_N > 3){
                Pair new_pair = pq.delMin();
                if( isValid(new_pair , ic_index , invalid_cluster) ){
    
                    invalid_cluster[ic_index] = new_pair.first();
                    ic_index++;
                    
                    invalid_cluster[ic_index] = new_pair.second();
                    ic_index++;
                    
                    new_cluster[nc_index] = new_pair.first().addCluster(new_pair.second());
//          *************rebulid pair**************
                    for(int i=0; i<num; i++){
                        if( isValid( cluster[i] , ic_index , invalid_cluster ) ){
                            Pair pair = new Pair ( new_cluster[nc_index] , cluster[i] );
                            pq.insert(pair);
                        }
                    }
                    for(int i=0;i<nc_index;i++){
                        if( isValid( new_cluster[i] , ic_index , invalid_cluster ) ){
                            Pair pair = new Pair (new_cluster[nc_index] , new_cluster[i]);
                            pq.insert(pair);
                        }
                    }
                    nc_index++;
                    current_N--;
                }
            }
            
            if(num<=3) {
                for(int i=0; i<num; i++){
                    for(int j=i+1; j<num;j++){
                        double dis = cluster[i].centroid.distanceTo(cluster[j].centroid);
                        if (dis < min) min = dis;
                    }
                }
                Arrays.sort(cluster);
                for(int i=0;i<num;i++){
                    System.out.printf(""%d %.2f %.2f\n"",cluster[i].size, cluster[i].centroid.x(), cluster[i].centroid.y());
                }
                System.out.printf(""%.2f\n"",min);
            }

            else {
                Cluster[] ans = new Cluster[3];
                int nc_flag = nc_index - 1;
                while(ans_index < 3 && nc_flag >= 0) {
                    if( isValid(new_cluster[nc_flag], ic_index, invalid_cluster) ){
                        ans[ans_index] = new_cluster[nc_flag];
                        ans_index++;
                    }
                    nc_flag--;
                }

                cluster_1_num = ans[0].size();
                if( ans[1] != null && ans[1].size()!=0 ) {
                    cluster_2_num = ans[1].size();
                }
                if( ans[2] != null && ans[2].size()!=0 ) {
                    cluster_3_num = ans[2].size();
                }
                for(int i=0;i<cluster_1_num;i++){
                    for(int j=0;j<cluster_2_num;j++){
                        double dis = ans[0].points[i].distanceTo(ans[1].points[j]);
                        if (dis < min) min = dis;
                    }
                    for(int k=0;k<cluster_3_num;k++){
                        double dis = ans[0].points[i].distanceTo(ans[2].points[k]);
                        if (dis < min) min = dis;
                    }
                }
                for(int i=0;i<cluster_2_num;i++ ){
                    for(int j=0;j<cluster_3_num;j++ ){
                        double dis = ans[1].points[i].distanceTo(ans[2].points[j]);
                        if (dis < min) min = dis;
                    }
                }
                Arrays.sort(ans);
                for(int i=0;i<3;i++){
                    System.out.printf(""%d %.2f %.2f\n"",ans[i].size, ans[i].centroid.x(), ans[i].centroid.y());
                }
                System.out.printf(""%.2f\n"",min);
            }
        }
        // double time = stopwatch.elapsedTime();
        // System.out.printf(""run time = "" + time + ""(s)\n"");
    }
}

