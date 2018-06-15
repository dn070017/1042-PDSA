import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author jerry
 */
public class Clustering implements Comparable<Clustering> {

    public Point2D[] points;
    public Point2D centroid;
    public int size;
    public int isValid;

    public Clustering(Point2D a) {
        this.points = new Point2D[1];
        this.points[0] = a;
        this.centroid = a;
        size = 1;
        isValid = 1;
    }

    public Clustering(Point2D[] a, int size, Point2D cent) {
        this.points = a;
        this.size = size;
        centroid = cent;
        isValid = 1;
    }

    public Clustering combine(Clustering that) {
        int newsize = this.size + that.size;
        Point2D[] old1 = this.points;
        Point2D[] old2 = that.points;
        Point2D[] newpoints = new Point2D[newsize];
        System.arraycopy(old1, 0, newpoints, 0, old1.length);
        System.arraycopy(old2, 0, newpoints, old1.length, old2.length);
        Point2D newcent = new Point2D((this.centroid.x()*this.size+that.centroid.x()*that.size)/newsize,(this.centroid.y()*this.size+that.centroid.y()*that.size)/newsize);
        Clustering newcluster = new Clustering(newpoints, newsize, newcent);
        this.isValid = 0;
        that.isValid = 0;//nullify the two old clusters
        return newcluster;
    }
    
    public double Dist(Clustering that) {
        return this.centroid.distanceTo(that.centroid);
    }

    public double minDist(Clustering that) {
        double result = this.points[0].distanceTo(that.points[0]);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < that.size; j++) {
                if (this.points[i].distanceTo(that.points[j]) < result) {
                    result = this.points[i].distanceTo(that.points[j]);
                }
            }
        }
        return result;
    }

    public int compareTo(Clustering that) {
        if (this.size > that.size) {
            return -1;
        } else if (this.size < that.size) {
            return +1;
        } else {
            double x1 = this.centroid.x(); 
            double x2 = that.centroid.x(); 
            if(x1 > x2) return 1;
            else if (x1 < x2) return -1;
            else{
                double y1 = this.centroid.y();
                double y2 =  that.centroid.y();
                if(y1 > y2) return 1;
                else if(y1 < y2) return -1;
                else return 0;
            }
        }
    }


    public static class Event implements Comparable<Event> {

        public final double distance;         // time that event is scheduled to occur
        public final Clustering a, b;       // particles involved in event, possibly null

        // create a new event to occur at time t involving a and b
        public Event(double d, Clustering a, Clustering b) {
            this.distance = d;
            this.a = a;
            this.b = b;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return +1;
            } else {
                return 0;
            }
        }
        // has any collision occurred between when event was created and now?
    }

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            MinPQ<Clustering.Event> pq = new MinPQ<Clustering.Event>();

            String countstring = br.readLine();
            int N = Integer.parseInt(countstring);
            Clustering[] clusters = new Clustering[3 * N];
            int current = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split(""\\s+"");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                Point2D nowpoint = new Point2D(x, y);
                clusters[current] = new Clustering(nowpoint);
                current++;
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = (i + 1); j < N; j++) {
                    pq.insert(new Clustering.Event(clusters[i].Dist(clusters[j]), clusters[i], clusters[j]));
                }
            }//initialize

            while (N > 3) {
                Clustering.Event event = pq.delMin();
                Clustering a = event.a;
                Clustering b = event.b;
                if (a.isValid == 0 || b.isValid == 0 ) {
                    continue;
                }
                
                clusters[current] = a.combine(b);
                int count = 0;
                for(int i = 0; i < current; i++){
                if(clusters[i] == a) {clusters[i] = null;
                count++;}
                if(clusters[i] == b) {clusters[i] = null;
                count++;}
                if(count == 2) break;
                }
                
                for (int i = 0; i < current; i++) {
                    if(clusters[i] != null && clusters[i].isValid == 1)
                    pq.insert(new Clustering.Event(clusters[current].Dist(clusters[i]), clusters[current], clusters[i]));
                }
                
                current++;
                N = N - 1;
            }//making clustering

            Clustering[] finalclusters = new Clustering[3];
            int ind = 0;

            for (int i = 0; i < current; i++) {
                if (clusters[i] != null && clusters[i].isValid == 1) {
                    finalclusters[ind++] = clusters[i];
                }
            }

            Arrays.sort(finalclusters);



            double minimum = finalclusters[0].minDist(finalclusters[1]);
            minimum = Math.min(minimum, finalclusters[0].minDist(finalclusters[2]));
            minimum = Math.min(minimum, finalclusters[1].minDist(finalclusters[2]));

            
            for (int i = 0; i < 3; i++) {
                int size = finalclusters[i].size;
                double x = finalclusters[i].centroid.x();
                double y = finalclusters[i].centroid.y();
                System.out.println(size + "" "" + String.format(""%.2f"", x) + "" "" + String.format(""%.2f"", y));
            }
            System.out.print(String.format(""%.2f"", minimum));
        }
    }
}

