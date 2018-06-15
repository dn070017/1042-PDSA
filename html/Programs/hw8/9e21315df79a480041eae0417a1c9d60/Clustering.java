
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
        private boolean valid;
        private int index;
        private int uf_index;
        
        public Cluster(int num){
            this.points = new Point2D[num];
            this.size = 0;
            this.centroid = null;
        }
        
        public Cluster(Point2D that,int num, int index){
            this.points = new Point2D[num];
            this.size = 1;
            this.points[0] = that;
            this.centroid = that;
            this.index = index;
            this.uf_index = index;
            this.valid = true;
        }
        
        public Cluster addCluster(Cluster that){
            Cluster cluster = new Cluster(this.size + that.size);
//            System.arraycopy(this.points, 0, cluster.points, 0, this.size);
//            System.arraycopy(that.points, 0, cluster.points,this.size,that.size);
            cluster.x = (this.centroid.x()*this.size + that.centroid.x()*that.size)/(this.size + that.size);
            cluster.y = (this.centroid.y()*this.size + that.centroid.y()*that.size)/(this.size + that.size);
            cluster.centroid = new Point2D(cluster.x,cluster.y);
            cluster.size = this.size + that.size;
            cluster.uf_index = this.uf_index;
            cluster.valid = true;
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
        
        public int getindex(){
            return index;
        }
        
        public boolean equals(Cluster that){
            if ( (this.size != that.size) || (!this.centroid.equals(that.centroid) ) ) return false;
            //if ( ! Arrays.equals(this.pointsArray(), that.pointsArray()) ) return false;
            //  can add more constrians
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
    
    public static void exchange(Cluster[] cluster, int i, int j){
        Cluster temp = cluster[i];
        cluster[i] = cluster[j];
        cluster[j] = temp;
        cluster[i].index = i;
        cluster[j].index = j;
    }
    
    public static void main(String[] args) throws Exception {
        
        Stopwatch stopwatch = new Stopwatch();

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
            MinPQ<Pair> pq = new  MinPQ<Pair>(num*num);
            MinPQ<Pair> dis_pq = new  MinPQ<Pair>(num*num);
            QuickUnionUF uf = new QuickUnionUF(num);
            int[] cluster_num = new int[3];
            double min = 1;
//            StdDraw.setCanvasSize(600, 600);
//            StdDraw.setPenRadius(0.01);
            
            
//            Cluster[] test_cluster = new Cluster[num];
//            Pair[] test_pair = new Pair[num];
            
            for(int i=0;i<num;i++){
                String[] given = data[i+1].split("" "");
                point[i] = new Point2D(Double.parseDouble(given[0]), Double.parseDouble(given[1]));
                cluster[i] = new Cluster(point[i],num,i);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                cluster[i].centroid.draw();
                
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.text(cluster[i].centroid.x(), cluster[i].centroid.y()-0.02, Integer.toString(i));
            }
      
//          *************initializing************
            for(int i=0; i<current_N; i++){
                for(int j=i+1; j<current_N; j++){
                    Pair pair = new Pair(cluster[i],cluster[j]);
                    pq.insert(pair);
                    dis_pq.insert(pair);
//                    StdDraw.setPenRadius(0.0005);
//                    StdDraw.setPenColor(StdDraw.GRAY);
//                    cluster[i].centroid().drawTo(cluster[j].centroid());
                }
            }  
            // double time_0 = stopwatch.elapsedTime();
            // System.out.printf(""initial time = "" + time_0 + ""(s)\n"");
            
            while(current_N > 3){

                Pair new_pair = pq.delMin();

                if( new_pair.first.valid && new_pair.second.valid ){ 
                    
                    new_pair.first().valid = false;
                    new_pair.second().valid = false;
                    
                    uf.union(new_pair.first().uf_index, new_pair.second().uf_index);
                    
                    cluster[new_pair.first().index] = new_pair.first().addCluster(new_pair.second());
                    
                    exchange(cluster, 0, new_pair.first.index);
                    exchange(cluster, new_pair.second.index, current_N - 1);
                    current_N--;  //discard the last one
                    
//                    StdDraw.setPenRadius(0.01 * cluster[0].size());
//                    StdDraw.setPenColor(StdDraw.RED);
//                    cluster[0].centroid.draw();
                    
                    for(int i=1; i<current_N; i++){
                        Pair pair = new Pair ( cluster[0] , cluster[i] );
                        pq.insert(pair);
                    }
                }
            }
            
            Cluster[] ans = new Cluster[current_N];
            System.arraycopy(cluster, 0, ans, 0, current_N);

            for(int i=0;i<current_N;i++){
                cluster_num[i] = ans[i].size();
            }

            // double time_1 = stopwatch.elapsedTime();
            // System.out.printf(""run time = "" + time_1 + ""(s)\n"");
            
            Pair temp = dis_pq.delMin();
            int cluster_index_1 = temp.first.uf_index;
            int cluster_index_2 = temp.second.uf_index;
  
            while( uf.connected(cluster_index_1, cluster_index_2) ){
                temp = dis_pq.delMin();
                cluster_index_1 = temp.first.uf_index;
                cluster_index_2 = temp.second.uf_index;
            }
            
            min = temp.distance();

            Arrays.sort(ans);
            for(int i=0;i<current_N;i++){
                System.out.printf(""%d %.2f %.2f\n"",ans[i].size, ans[i].centroid.x(), ans[i].centroid.y());
            }
            System.out.printf(""%.2f\n"",min);
        }
        // double time_2 = stopwatch.elapsedTime();
        // System.out.printf(""total time = "" + time_2 + ""(s)\n"");
    }
}

