
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
public class CriticalDis {
    
    private static class Event implements Comparable<Event> {
        private final double distance;         // time that event is scheduled to occur
        private final int a, b;       // particles involved in event, possibly null
                
        // create a new event to occur at time t involving a and b
        public Event(double d, int a, int b) {
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
   
    }
    
    
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
            
            Digraph myGraph = new Digraph(N);
            MinPQ myEdge = new MinPQ<Event>();
            double d=0;
            
            //find s & t
            int s=0,t=0;
            double max=0, min=10;
            for (int i=0; i<N; i++) {
                if (points[i].x() + points[i].y() < min)  {min=points[i].x() + points[i].y(); s=i;}
                if (points[i].x() + points[i].y() > max)  {max=points[i].x() + points[i].y(); t=i;}
            }
            
            //insert distance to myEdge
            for (int i=0; i<N; i++) {
                for (int j=i; j<N; j++){
                    if (points[i].x() < points[j].x() && points[i].y() < points[j].y()) myEdge.insert(new Event(points[i].distanceTo(points[j]), i, j));
                    if (points[i].x() > points[j].x() && points[i].y() > points[j].y()) myEdge.insert(new Event(points[i].distanceTo(points[j]), j, i));
                }
            }
            
            while (!myEdge.isEmpty()) {
                Event step = (Event)myEdge.delMin();
                myGraph.addEdge(step.a, step.b);
                DirectedDFS myDFS = new DirectedDFS(myGraph, s);
                if (myDFS.marked(t))    {d=step.distance;   break;} 
            }
            
            System.out.printf(""%1.3f\n"", d);
            
            
        }
    }
    
}

