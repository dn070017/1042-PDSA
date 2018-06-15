/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Sophia
 */
class Cluster implements Comparable<Cluster>{
    private double x = 0.00;
    private double y = 0.00;
    private int size = 0;
    public int key;
    ArrayList <Point> points;
    boolean flag = true;
    public Cluster(int key){
        points = new ArrayList <Point>();
        this.key = key;
    }
    public Cluster(Point p,int key){
        points = new ArrayList <Point>();
        points.add(p);
        this.x = p.getx();
        this.y = p.gety();
        this.key = key;
        this.size = 1;
    }
    public Cluster(Cluster ori, Cluster that, int key){
        points = new ArrayList <Point>();
        this.union(ori, that);
        this.key = key;
    }
    
    public void union(Cluster ori, Cluster c){
        this.x = (ori.x * ori.getsize() + c.getx()*c.getsize())/(ori.getsize() + c.getsize());
        this.y = (ori.y * ori.getsize() + c.gety()*c.getsize())/(ori.getsize() + c.getsize());
        this.size = ori.getsize() + c.getsize();
        this.points.addAll(ori.points);
        this.points.addAll(c.points);
    }
    public static double getmind(Cluster a, Cluster b,Cluster c){
        double d = 0.00;
        double temp = 0.00; 
        d = getmind(a,b);
        temp = getmind(c,b);
        if(d>temp)
            d = temp;
        temp = getmind(c,a);
        if(d>temp)
            d = temp;
        
        return d;
    }
    public static double getmind(Cluster p, Cluster q){
        double d = p.points.get(0).distanceTo(q.points.get(0));
        double temp;
        for(int i = 0; i < p.size; i++){
            for(int j = 0; j < q.size; j++){
                temp = p.points.get(i).distanceTo(q.points.get(j));
                if(d>temp){
                    d = temp;
                }
            }
        }
        
        return d;
    }
    
    double getx(){return this.x;}
    double gety(){return this.y;}
    int getsize(){return this.size;}
        public double distanceTo(Cluster that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    static double distance(Cluster ori, Cluster that){
        double dx = ori.x - that.x;
        double dy = ori.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    
    }


    @Override
    public int compareTo(Cluster o) {
        int flag;
        if(this.size>(o.getsize())){
            flag = 1;
        }else if(this.size == (o.getsize())){
            if(this.x>(o.getx())){
            flag = 1;
            }else if(this.x == (o.x)){
                if(this.y>(o.gety())){
                flag = 1;
                }else if(this.y == (o.y)){
                    flag = -1;
                }else{
                    flag = -1;
                }
            }else{
                flag = -1;
            }    
        }else{
            flag = -1;
        }
        return flag * -1;
    }

}

class Pair implements Comparator<Pair> , Comparable<Pair>{
    Cluster a;
    Cluster b;
    double d;
    
    Pair(Cluster a, Cluster b){
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



class Point{
    private double x;
    private double y;
    
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

}

public class Clustering {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int N = Integer.parseInt(br.readLine());
            double x;
            double y;
            Point points[] = new Point[N];
            HashMap<Integer,Cluster> map = new HashMap<Integer,Cluster>();
            MinPQ<Pair> pairs = new MinPQ<Pair>();
            Pair pair;
            int keycount = N;
            for(int i = 0 ; i< N ; i++) {
                String in = br.readLine();
                String[] data = in.split(""\\s+"");
                x = Double.parseDouble(data[0]);
                y = Double.parseDouble(data[1]);
                Point point = new Point(x,y);
                points[i] = point;
                Cluster cluster = new Cluster(points[i], i);
                map.put(i,cluster);
                
                for(int j=0 ; j<i ; j++){
                    pair = new Pair(map.get(i),map.get(j));
                    pairs.insert(pair);
                }
            }
            double mind = 0.00;
            
            if(N <= 3){
                Cluster[] answer = map.values().toArray(new Cluster[map.values().size()]);
                Arrays.sort(answer);
                for(int i = 0; i<answer.length ;i++){
                    System.out.println(answer[i].getsize()+"" ""+String.format(""%.2f"", answer[i].getx())+"" ""+String.format(""%.2f"", answer[i].gety()));
                }
                
                if(N == 2){
                    mind = answer[0].distanceTo(answer[1]);
                } else if( N == 3){
                    mind = Cluster.getmind(answer[0], answer[1], answer[2]);
                }
                System.out.println(mind);
            }else{
                while(N>3){
                    pair = pairs.min();
                    pairs.delMin();
                    if(pair.a.flag == true & pair.b.flag == true){
                        pair.a.flag = false;
                        pair.b.flag = false;
                        Cluster cluster = new Cluster(pair.a,pair.b,keycount);
                        map.remove(pair.a.key);
                        map.remove(pair.b.key);
                        map.put(keycount, cluster);
                        keycount ++;
                        N = N-1;
                        for(int j = 0 ; j < keycount-1; j++){
                            if(map.containsKey(j)){
                                pair = new Pair(map.get(keycount-1),map.get(j));
                                pairs.insert(pair);
                            }
                        }
                    }
                }
                //System.out.println(N);
                //System.out.println(map.size());
                Cluster[] answer = map.values().toArray(new Cluster[map.values().size()]);
                Arrays.sort(answer);
                for(int i = 0; i<answer.length ;i++){
                    System.out.println(answer[i].getsize()+"" ""+String.format(""%.2f"", answer[i].getx())+"" ""+String.format(""%.2f"", answer[i].gety()));
                }
                mind = Cluster.getmind(answer[0], answer[1], answer[2]);
                System.out.println(String.format(""%.2f"", mind));
            
            }
            


            
            
            
            
        }
    }
    
}

