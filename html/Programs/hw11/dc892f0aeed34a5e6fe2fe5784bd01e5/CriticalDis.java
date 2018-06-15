
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Sophia
 */

class Point implements Comparable<Point>{
    private double x;
    private double y;
    double d;
    
    double getx(){return this.x;}
    double gety(){return this.y;}
    public Point(){}
    
    public Point(double x, double y) {
            if (Double.isInfinite(x) || Double.isInfinite(y))
                throw new IllegalArgumentException(""Coordinates must be finite"");
            if (Double.isNaN(x) || Double.isNaN(y))
                throw new IllegalArgumentException(""Coordinates cannot be NaN"");
            if (x == 0.0) x = 0.0;  // convert -0.0 to +0.0
            if (y == 0.0) y = 0.0;  // convert -0.0 to +0.0
            this.x = x;
            this.y = y;
            this.d = x+y;
        }
    
    public double distanceTo(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    static double distance(Point ori, Point that){
        double dx = ori.x - that.x;
        double dy = ori.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    
    }

    @Override
    public int compareTo(Point o) {
        if (this.d < o.d) return -1;
            if (this.d > o.d) return +1;
        return 0;
    }

}

class Pair implements Comparator<Pair> , Comparable<Pair>{
    Point a;
    Point b;
    int indexa;
    int indexb;
    double d;
    
    Pair(Point a, Point b){
        this.a = a;
        this.b = b;
        d = a.distanceTo(b);
    }
    Pair(int indexa, int indexb, Point a, Point b){
        this.indexa = indexa;
        this.indexb = indexb;
        this.a = a;
        this.b = b;
        d = a.distanceTo(b);
    }

    @Override
    public int compare(Pair p, Pair q) {
        if (p.d < q.d) return -1;
            if (p.d > q.d) return +1;
        return 0;
    }

    @Override
    public int compareTo(Pair q) {
        if (this.d < q.d) return -1;
            if (this.d > q.d) return +1;
        return 0;
    }
}

public class CriticalDis {
    public static void main(String[] args) throws IOException {
    
    try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int N = Integer.parseInt(br.readLine());
            double x;
            double y;
            double dis;
            Point points[] = new Point[N];
            MinPQ<Pair> alldis = new MinPQ<Pair>();
            Digraph graph = new Digraph(N);
            int keycount = N;
            Pair pair;
            for(int i = 0 ; i< N ; i++) {
                String in = br.readLine();
                String[] data = in.split(""\\s+"");
                x = Double.parseDouble(data[0]);
                y = Double.parseDouble(data[1]);
                Point point = new Point(x,y);
                points[i] = point;
            }
            Arrays.sort(points);
            for(int i = 0 ; i< N ; i++) {
                for(int j = 0 ; j < i ; j++){
                    dis = points[i].distanceTo(points[j]);
                    if(points[i].getx()>points[j].getx()&&points[i].gety()>points[j].gety()){
                        pair = new Pair(j,i,points[j],points[i]);
                        alldis.insert(pair);
                    }else if(points[i].getx()<points[j].getx()&&points[i].gety()<points[j].gety()){
                        pair = new Pair(i,j,points[i],points[j]);
                        alldis.insert(pair);
                    }
                }
            }
            DepthFirstDirectedPaths dfs;
            while(alldis.size() != 0){
                pair = alldis.min();
                //System.out.printf(""%1.3f\n"",pair.d);
                graph.addEdge(pair.indexa, pair.indexb);
                dfs = new DepthFirstDirectedPaths(graph,0);
                if(dfs.hasPathTo(N-1)){
                    System.out.printf(""%1.3f\n"",pair.d);
                    break;
                }
                alldis.delMin();
            }
            //System.out.printf(""%1.3f\n"",pair.d);
        
    }
    }
}

