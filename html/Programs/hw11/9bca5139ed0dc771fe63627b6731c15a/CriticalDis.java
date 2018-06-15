/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package criticaldis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author ncslab
 */
public class CriticalDis {
    public static double d = 0;
    public static PriorityQueue<event> eventPQ = new PriorityQueue();
    public static int N;
    public static Point2D[] input = null;
    public static Point2D target; 
    public static Point2D source;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        

        
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            N = Integer.parseInt(br.readLine());
            input = new Point2D[N];
            for (int i = 0 ; i < N ; i++){
                String[] read = br.readLine().split("" "");
                double x = Double.parseDouble(read[0]);
                double y = Double.parseDouble(read[1]);
                
                input[i] = new Point2D(x, y);
                
            }
        }
        xyComparator xy = new xyComparator();
        Arrays.sort(input, xy);
        source = input[0];
        target = input[N-1];
        recurSearch();
        System.out.printf(""%1.3f\n"", d); 
        
        
    }
    
    public static void recurSearch(){
        Point2D to = source;
        generateEvent(source, 0);
        while(to.x() != target.x() && to.y() != target.y()){
            event e = eventPQ.poll();
            if (e.dis > d)
                d = e.dis;
            generateEvent(e.to, e.to_index);
            to = e.to;
        }
    }
    
    public static void generateEvent(Point2D s, int index){
        for (int i = index + 1 ; i < N ; i++){
            if (input[i].x() > s.x() && input[i].y() > s.y())
                 eventPQ.add(new event(s, input[i], i));
        }
        
    }

}
        class xyComparator implements Comparator<Point2D>{
            @Override
            public int compare(Point2D t, Point2D t1) {
                
                if ((t.x() + t.y()) > (t1.x() + t1.y()))
                    return 1;
                else if ((t.x() + t.y()) == (t1.x() + t1.y()))
                    return 0;
                else
                    return -1;
            }
        }

    class event implements Comparable<event>{
        public Point2D from;
        public Point2D to;
        public double dis;
        public int to_index;
        public event(Point2D from, Point2D to, int index){
            this.from = from;
            this.to = to;
            this.to_index = index;
            this.dis = from.distanceTo(to);
        }
        @Override
        public int compareTo(event that) {
            if (this.dis > that.dis) return 1;
            else if (this.dis == that.dis) return 0;
            else return -1;
        }
        
    }
