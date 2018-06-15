
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class Clustering{
    
    public static class Cluster implements Comparable<Cluster> {
        double cx;
        double cy;
        int size = 1;
        //add root, the first number in the cluster
        //Point2D root;
        //add count of cluster times, change the original count
        int count;
        int number_gen=0;

        public Cluster(Point2D a, int i){
            size = 1;
            //root = a;
            count = 0;
            cx = a.x();
            cy = a.y();
            //cnumber_gen = number_gen;
            number_gen = i;
            //return i++;
        }

        public Cluster(Cluster a,Cluster b ,int i){
            size = a.size+b.size;
            //root = a.root;
            count =0;
            a.count ++;
            b.count ++;
            cx = (a.cx*a.size + b.cx*b.size)/size;
            cy = (a.cy*a.size + b.cy*b.size)/size;
            number_gen = i;
            //size
        }
        public int compareTo(Cluster that) {
            if(this.size < that.size) return 1;
            if(this.size > that.size) return -1; 
            
            if(this.cx > that.cx) return 1;
            if(this.cx < that.cx) return -1;
            
            if(this.cy > that.cy) return 1;
            if(this.cy < that.cy) return -1;
            
            else return 0;
        }
        //distant
        public double distanceTo(Cluster that) {
            double dx = this.cx - that.cx;
            double dy = this.cy - that.cy;
            return Math.sqrt(dx*dx + dy*dy);
        }
        public int Number_gen(int i){
            return i=i+1;  
        }
    }

    public static class Pair implements Comparable<Pair>{
        double lenghth_Pair;
        Cluster one;
        Cluster two;
        public Pair(Cluster a,Cluster b){
            lenghth_Pair = a.distanceTo(b);
            one = a;
            two = b;
        }
        //valit of count = 0
        public boolean isValid() {
            if (one.count != 0) return false;
            if (two.count != 0) return false;
            return true;
        }
        
        @Override
        public int compareTo(Pair that) {
            if(this.lenghth_Pair > that.lenghth_Pair) return 1;
            if(this.lenghth_Pair < that.lenghth_Pair) return -1;
            else return 0; 
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        int N = Integer.parseInt(br.readLine());  
        //union the points~
        UF uf = new UF(N*N);
        int cnumber_gen =0;
        
        //Point2D [] points = new Point2D [N];
        ArrayList<Cluster> cluster_union = new ArrayList<Cluster>();//record union
        ArrayList<Cluster> cluster = new ArrayList<Cluster>();//store the cluster
        
        //create the 2D pooint
        for (int i = 0; i < N; i++) {
            String [] sep = br.readLine().split("" "");
            double x = Double.parseDouble(sep[0]);
            double y = Double.parseDouble(sep[1]);
            Point2D point = new Point2D(x,y);
            
            Cluster a = new Cluster(point,cnumber_gen);
            cnumber_gen = a.Number_gen(cnumber_gen);
            //create cluster
            cluster.add(a);
            cluster_union.add(a);
            }
        
        //MinPQ<String> pq = new MinPQ<String>()
        MinPQ<Pair> pq = new MinPQ<Pair>();
        MinPQ<Pair> pq_unoin = new MinPQ<Pair>();

        //create cluster and insert to pq
        for (int i = 0; i<N;i++){
            Cluster a = cluster.get(i);
            for(int j = 0; j<N;j++){
                Cluster b = cluster.get(j);
                pq.insert(new Pair(a,b));
                pq_unoin.insert(new Pair(a,b));
                
            }
        }
        //do the wihle just like the simulate
        
        while(cluster.size()>3){
            //take out the min
            Pair e = pq.delMin();
            //if valide!! not in any cluster if not connect
            if (!e.isValid()) continue;
            //create new cluster
            Cluster a = e.one;
            Cluster b = e.two;
            //2N
            //uf.union(cluster_union.indexOf(a), cluster_union.indexOf(b));
            uf.union(a.number_gen, b.number_gen);
            //if (uf.connected(cluster_union.indexOf(a), cluster_union.indexOf(b))) continue;
            //2N
            cluster.remove(a);
            cluster.remove(b); 
             //1
            Cluster c = new Cluster(a,b,cnumber_gen);
            cnumber_gen = c.Number_gen(cnumber_gen);
            //System.out.printf(""%d is cnumber_gen\n"",c.Number_gen(cnumber_gen));
            cluster.add(c);
            cluster_union.add(c);
            //get union from cluster_union list a to b to c  
            //2N
            //uf.union(cluster_union.indexOf(a), cluster_union.indexOf(c));
            uf.union(a.number_gen, c.number_gen);
            //
            
            // !! physical collision, so update positions, and then simulation clock
            /*for (int i = 0; i < cluster.size(); i++){
                //c.distanceTo(cluster.get(i));
                if(c!=cluster.get(i)) pq_insert.insert(new Pair(c,cluster.get(i)));
                if (pq_insert.size() > 1) pq_insert.delMax();
            }
            pq.insert(pq_insert.delMax());*/
            // make new distance pair into pq
            //N*lgN
            for (int i = 0; i < cluster.size(); i++){
                //c.distanceTo(cluster.get(i));
                if(c!=cluster.get(i)) pq.insert(new Pair(c,cluster.get(i)));
            }
            //System.out.println(""c""+cluster_union.indexOf(c));
            //System.out.println(""cluster.size() ""+cluster.size());
        }
        //System.out.println(""cluster.size() ""+cluster.size());
        //Arrays.sort(cluster);
        //del of pq_unoin.insert(new Pair(a,b));
        double small_dis = 0;//the smallest distance
        //N
        while(!pq_unoin.isEmpty()){
            Pair f = pq_unoin.delMin();
            //if(!uf.connected(cluster_union.indexOf(f.one),cluster_union.indexOf(f.two))) {
            if(!uf.connected(f.one.number_gen,f.two.number_gen)) {
            small_dis = f.lenghth_Pair;
            break;
            }
            //if(!uf.connected(cluster_union.indexOf(f.one),cluster_union.indexOf(f.two))) break;
        }
        
        
        
        Collections.sort(cluster);
        
            for(int i=0;i<cluster.size();i++ ){
                if(cluster.size()<3)System.out.printf(""%d %.2f %.2f\n"",cluster.get(i).size,cluster.get(i).cx,cluster.get(i).cy);
                else
                System.out.printf(""%d %.2f %.2f\n"",cluster.get(i).size/2,cluster.get(i).cx,cluster.get(i).cy);
                //System.out.printf(""%d is number_gen\n"",cluster.get(i).number_gen);
                //System.out.printf(""%d is cnumber_gen\n"",cnumber_gen);
            }
            
        System.out.printf(""%.2f\n"",small_dis);

        
        }
    }
}

