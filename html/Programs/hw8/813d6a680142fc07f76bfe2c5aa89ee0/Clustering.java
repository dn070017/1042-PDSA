
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author hung-wei
 */
public class Clustering {
    

    
    private MinPQ<Event> pq;        // the priority queue
    private Point2D[] points;   // the array of particles
    public Cluster[] clusters; 

    // create a new collision system with the given set of particles
    public Clustering(Point2D[] points) {
        clusters = new Cluster[points.length*2];
        this.points = points.clone();   // defensive copy
        for (int i = 0; i < points.length; i++) 
            clusters[i] = new Cluster(points[i], points.length);
    }

    // updates priority queue with all new events for particle a
    private void predict(Cluster a) {
        if (a == null) return;

        // particle-particle collisions
        for (int i = 0; i < clusters.length; ++i) {
            if ( clusters[i] == null) break;
            if ( a.equals(clusters[i])) continue;
            if ( clusters[i].size == 0) continue;
            double d = a.distanceTo(clusters[i]);
            pq.insert(new Event(d, a, clusters[i]));

        }
    }



    public void simulate() {
        
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < points.length; i++) {
            predict(clusters[i]);
        }
        int go = points.length;

        // the main event-driven simulation loop
        while (go>3) { 

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Cluster a = e.a;
            Cluster b = e.b;

            a.merge(b);
            clusters[2*points.length-go] = new Cluster(a);
            a.size = 0;
            // update the priority queue with new collisions involving a or b
            predict(clusters[2*points.length-go]);
            go--;
        }
    }
    


   /*************************************************************************
    *  An event during a particle collision simulation. Each event contains
    *  the time at which it will occur (assuming no supervening actions)
.
    *
    *    -  a and b both null:      redraw event
    *    -  a null, b not null:     collision with vertical wall
    *    -  a not null, b null:     collision with horizontal wall
    *    -  a and b both not null:  binary collision between a and b
    *
    *************************************************************************/
    private static class Event implements Comparable<Event> {
        private final double distance;         // time that event is scheduled to occur
        private final Cluster a, b;       // particles involved in event, possibly null
                
        
        // create a new event to occur at time t involving a and b
        public Event(double d, Cluster a, Cluster b) {
            this.distance = d;
            this.a    = a;
            this.b    = b;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            if      (this.distance < that.distance) return -1;
            else if (this.distance > that.distance) return +1;
            else                            return  0;
        }
        
        // has any collision occurred between when event was created and now?
        public boolean isValid() {
            if (a.size == 0 ) return false;
            if (b.size == 0 ) return false;
            return true;
        }
   
    }

    public static class Cluster implements Comparable<Cluster> {
        public double x,y;         // time that event is scheduled to occur
        private Point2D[] points;       // particles involved in event, possibly null
        private int size;
                
        // create a new event to occur at time t involving a and b
        public Cluster(Point2D point, int N) {
            this.points = new Point2D[N];
            this.points[0] = point;
            size = 1;
            x = point.x();
            y = point.y();
        }
        
        public Cluster(Cluster a) {
            this.points =  a.points;
            this.size =  a.size;
            this.x = a.x;
            this.y = a.y;
        }
        
        // compare times when two events will occur
        public void merge(Cluster that) {
            double sumx = this.x*this.size + that.x*that.size;
            double sumy = this.y*this.size + that.y*that.size;
            Point2D[] points2 = that.getpoints();
            for (int i=size; i<size+that.size; i++) {
                this.points[i] = points2[i-size];
            }
            this.size = size+that.size;
            x = sumx/size;
            y = sumy/size;
            that.size = 0;
        }
        public double distanceTo(Cluster that) {
            double dx = this.x - that.x;
            double dy = this.y - that.y;
            return Math.sqrt(dx*dx + dy*dy);
        }
        @Override
        public int compareTo(Cluster that) {
            if      (this.size < that.size) return +1;
            else if (this.size > that.size) return -1;
            else if (this.x < that.x) return -1;
            else if (this.x > that.x) return +1;
            else if (this.y < that.y) return -1;
            else if (this.y > that.y) return +1;
            else                            return  0;
        }
        
        
        public Point2D[] getpoints() {
            return points;
        }
    }
    
    public void sort() {
        int N = points.length*2-3 ;
        for (int i=0; i<N; i++)
            for (int j=i; j>0; j--)
                if (this.clusters[j].compareTo(this.clusters[j-1]) == -1) {
                    exch(j, j-1);
                }
    }
    
    public void exch(int i , int j) {
        Cluster swap = this.clusters[i];
        this.clusters[i] = this.clusters[j];
        this.clusters[j] = swap;
    }
    
    public double mindis() {
        double min=10;
        for (int i=0; i<3; i++)
        {
            for (int j=i+1; j<3; j++)
            {
                for (int k=0; k<clusters[i].size; k++) 
                {
                    for (int h=0; h<clusters[j].size; h++)
                    {
                        double d = clusters[i].points[k].distanceTo(clusters[j].points[h]);
                        if (d<min) min=d;
                    }
                }    
            }
        }
        return min;
    }

   /********************************************************************************
    *  Sample client
    ********************************************************************************/
    public static void main(String[] args) throws Exception{
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            // the array of points
            Point2D[] points;
            
            int N = Integer.parseInt(br.readLine());
            points = new Point2D[N];
            double x=0 ,y=0;
            
            for(int i = 0; i < N; i++) {
                String[] inpoints = br.readLine().split("" "");
                x = Double.parseDouble(inpoints[0]);
                y = Double.parseDouble(inpoints[1]);
                points[i] = new Point2D(x, y);
            }
            
            if (points.length<3) {
                Clustering hw = new Clustering(points);
                hw.sort();
                for (int i=0; i<points.length; i++) {
                    System.out.print(hw.clusters[i].size+"" ""+String.format(""%.2f"", hw.clusters[i].x)+"" ""+String.format(""%.2f"", hw.clusters[i].y));
                    System.out.println(); 
                }
                System.out.println(String.format(""%.2f"", hw.mindis())); 
            }

            else
            {
                Clustering hw = new Clustering(points);
                hw.simulate();
                hw.sort();
                for (int i=0; i<3; i++) {
                    System.out.print(hw.clusters[i].size+"" ""+String.format(""%.2f"", hw.clusters[i].x)+"" ""+String.format(""%.2f"", hw.clusters[i].y));
                    System.out.println(); 
                }
                System.out.println(String.format(""%.2f"", hw.mindis())); 
            }
        
        }
    }
      
