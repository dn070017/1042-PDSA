

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * 1042 PDSA
 * hw08_Clustering
 * @author Robert
 */

public class Clustering {

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // first data = number of points(num)
            String header = br.readLine();
            int num = Integer.parseInt(header);

            // main data
            
//            StdDraw.setCanvasSize(500, 500);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(.01);
//            StdDraw.setPenColor(Color.red);

            Point2D[] points = new Point2D[num];
            for(int k = 0; k < num; k++) {
                String[] tmp = br.readLine().split("" "");
                double x = Double.parseDouble(tmp[0]);
                double y = Double.parseDouble(tmp[1]);
                points[k] = new Point2D(x, y);
//                points[k].draw();
            }

            // initialize begining
            // grouplist as cluster group
            ArrayList<Cluster> grouplist = new ArrayList<>();
            for(int i = 0; i < num; i++){
                grouplist.add(new Cluster(points[i]));
            }
            // pq as distance counting
            MinPQ<Pair> pq = new MinPQ<Pair>();
            for(int i = 0; i < num; i++){
                for(int j = i+1; j < num; j++){
                    Pair temp = new Pair(grouplist.get(i), grouplist.get(j));
                    pq.insert(temp);
                }
            }
            int N = num;
            ArrayList<Cluster> delpoints = new ArrayList<>();
            while( N > 3 ){
                // combine two cluster
                // delete two old cluster and add new one
                // delMin and Add new distance pair
                Cluster old_1 = pq.min().cluster_1;
                Cluster old_2 = pq.min().cluster_2;
                if (delpoints.contains(old_1)){
                    pq.delMin();
                } else if (delpoints.contains(old_2)){
                    pq.delMin();
                } else {
                    delpoints.add(old_1);
                    delpoints.add(old_2);
                    Cluster combined = new Cluster(old_1, old_2);
                    grouplist.remove(pq.min().cluster_1);
                    grouplist.remove(pq.min().cluster_2);
                    grouplist.add(combined);

                    pq.delMin();
                    for (int i = 0; i < grouplist.size()-1; i++){
                        Pair tempdis = new Pair(grouplist.get(i), combined);
                        pq.insert(tempdis);
                    }
                    N--;
                }
            }
            Cluster[] ans = new Cluster[N];
            for (int i = 0; i < N; i++){
                ans[i] = grouplist.get(i);
            }
            Arrays.sort(ans);
            // print answer
            for (int i = 0; i < N; i++){
                String answer = """";
                answer += ans[i].size;
                answer += "" "";
                answer += String.format(""%.2f"", ans[i].centroid.x());
                answer += "" "";
                answer += String.format(""%.2f"", ans[i].centroid.y());
                StdOut.println(answer);
            }
            double smalldis = 1000; 
            for (int i = 0; i < N; i++){
                for (int j = i+1; j < N; j++){
                    for (int m = 0; m < ans[i].size; m++){
                        for (int n = 0; n < ans[j].size; n++){
                            double tmp = ans[i].points[m].distanceTo(ans[j].points[n]);
                            if (tmp < smalldis)
                                smalldis = tmp;
                        }
                    }
                }
            }
            StdOut.println(String.format(""%.2f"", smalldis));
        }
    }

    
    private static class Cluster implements Comparable<Cluster> {
        
        private int size;
        private Point2D[] points;
        private Point2D centroid;
   
        // initialize
        public Cluster(Point2D a){
            this.size = 1;
            this.points = new Point2D[size];
            this.points[0] = a;
            this.centroid = a;
        }
        // combined together
        public Cluster(Cluster a, Cluster b){
            this.size = a.size + b.size;
            this.points = new Point2D[this.size];
            for(int i = 0; i < a.size; i++){
                this.points[i] = a.points[i];
            }
            for(int j = a.size; j < this.size; j++){
                this.points[j] = b.points[j-a.size];
            }
            double tempx = a.centroid.x()*a.size + b.centroid.x()*b.size;
            double tempy = a.centroid.y()*a.size + b.centroid.y()*b.size;
            this.centroid = new Point2D(tempx/this.size, tempy/this.size);
        }
        
        @Override
        public int compareTo(Cluster that) {
            if (this.size < that.size)
                return 1;
            else if (this.size > that.size)
                return -1;
            else {
                if (this.centroid.x() > that.centroid.x())
                    return 1;
                else if (this.centroid.x() < that.centroid.x())
                    return -1;
                else
                    return 0;
            }
        }
    }
    private static class Pair implements Comparable<Pair>{

        private Cluster cluster_1;
        private Cluster cluster_2;
        private double distance;
        
        public Pair(Cluster a, Cluster b){
            this.cluster_1 = a;
            this.cluster_2 = b;
            this.distance = a.centroid.distanceTo(b.centroid);
        }
        public int compareTo(Pair that){
            if (this.distance > that.distance)
                return 1;
            else if (this.distance < that.distance)
                return -1;
            else
                return 0;
        }
    }
}

//            return (int)(this.size - that.size);
