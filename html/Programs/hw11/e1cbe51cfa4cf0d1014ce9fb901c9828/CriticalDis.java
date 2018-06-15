import java.io.BufferedReader;
import java.io.FileReader;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author jerry
 */
public class CriticalDis {
    
        public static class Event implements Comparable<Event> {

        public double distance;         // time that event is scheduled to occur
        int a, b;       // particles involved in event, possibly null

        // create a new event to occur at time t involving a and b
        public Event(double d, int a, int b) {
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
    }//use priority queue to sort the distance
        
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String init = br.readLine();
            int total_number = Integer.parseInt(init);
            Point2D[] total_p = new Point2D[total_number];
            int current = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split(""\\s+"");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                total_p[current++] = new Point2D(x, y);
            }
            int source_N = 0;
            int target_N = 0;
            for(int i = 0; i < total_number; i++){
                if((total_p[i].x()+total_p[i].y()) < (total_p[source_N].x()+total_p[source_N].y())) {source_N = i;}
                if((total_p[i].x()+total_p[i].y()) > (total_p[target_N].x()+total_p[target_N].y())) {target_N = i;}
            }
            MinPQ<CriticalDis.Event> pq= new MinPQ<CriticalDis.Event>();
            for(int i = 0; i < total_number; i++){
                for(int j = 1; j <  total_number; j++){
                    if((total_p[i].x() < total_p[j].x()) && (total_p[i].y() < total_p[j].y())){
                    pq.insert(new CriticalDis.Event(total_p[i].distanceTo(total_p[j]), i, j));
                    }
                }
            }
            Digraph graph = new Digraph(total_number);
            double dist = 2.0;
            while(pq.size() > 0){
                CriticalDis.Event currentEvent = pq.delMin();
                if(currentEvent.distance > dist){break;}
                graph.addEdge(currentEvent.a, currentEvent.b);
                DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(graph, source_N);
                if(dfs.hasPathTo(target_N)){
                    double newDist = total_p[currentEvent.a].distanceTo(total_p[currentEvent.b]);
                    if(newDist < dist) dist = newDist;
                    /*Queue<Integer> q = new Queue<Integer>();
                    double newDist = 2.0;
                    for(int s:dfs.pathTo(target_N)){
                        q.enqueue(s);
                        System.out.println(s);
                    }
                    while (q.size() > 1) {
                        int first = q.dequeue();
                        int second = q.peek();
                        System.out.println(first+"" ""+second+"" ""+total_p[first].distanceTo(total_p[second]));
                        if(total_p[first].distanceTo(total_p[second]) < newDist ){
                            newDist = total_p[first].distanceTo(total_p[second]);
                            if(newDist < dist){
                                dist = newDist;
                            }
                        }*/
                    }
                }
            System.out.printf(""%1.3f\n"", dist);
            }
            }
        }

