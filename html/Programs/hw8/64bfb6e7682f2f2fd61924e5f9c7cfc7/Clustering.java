
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
        //ArrayList<Cluster> cluster_union = new ArrayList<Cluster>();//record union
        ArrayList<Cluster> cluster = new ArrayList<Cluster>();//store the cluster
        
        //create the 2D pooint
        for (int i = 0; i < N; i++) {
            String [] sep = br.readLine().split("" "");
            double x = Double.parseDouble(sep[0]);
            double y = Double.parseDouble(sep[1]);
            Point2D point = new Point2D(x,y);
            
            Cluster a = new Cluster(point,cnumber_gen);
            cnumber_gen = a.Number_gen(cnumber_gen);
            cluster.add(a);
            }
        
        //MinPQ<String> pq = new MinPQ<String>()
        MinPQ<Pair> pq = new MinPQ<Pair>();
        MinPQ<Pair> pq_unoin = new MinPQ<Pair>();

        //create cluster and insert to pq
        for (int i = 0; i<N;i++){
            Cluster a = cluster.get(i);
            for(int j = i+1; j<N;j++){
                Cluster b = cluster.get(j);
                pq.insert(new Pair(a,b));
                pq_unoin.insert(new Pair(a,b));
            }
        }
        //do the wihle just like the simulate
        int doit=cluster.size();
        while(doit>3){
            Pair e = pq.delMin();
            if (!e.isValid()) continue;
            Cluster a = e.one;
            //System.out.printf("" a cluster remove is %f \n"",a.cx);
            Cluster b = e.two;
            //System.out.printf("" b cluster remove is %f \n"",b.cx);
            //2N
            uf.union(a.number_gen, b.number_gen);
            //2N
            //System.out.printf(""cluster remove is %d\n"",cluster.size());
            //cluster.remove(a);
            //System.out.printf("" a cluster remove is %d\n"",cluster.size());
            //cluster.remove(b); 
            //System.out.printf("" b cluster remove is %d\n"",cluster.size());
             //1
            Cluster c = new Cluster(a,b,cnumber_gen);
            cnumber_gen = c.Number_gen(cnumber_gen);
            cluster.add(c);
            uf.union(a.number_gen, c.number_gen);
            //
            //N*lgN
            for (int i = 0; i < cluster.size()-1; i++){
                if(c!=cluster.get(i)&&cluster.get(i).count==0) {
                    pq.insert(new Pair(c,cluster.get(i)));
                }
            }
            //System.out.printf(""cluster is %d\n"",cluster.size());
            doit=doit-1;
        }
        double small_dis = 0;//the smallest distance
        //N
        while(!pq_unoin.isEmpty()){
            Pair f = pq_unoin.delMin();
            if(!uf.connected(f.one.number_gen,f.two.number_gen)) {
            small_dis = f.lenghth_Pair;
            break;
            }
        }
        
        
        
        Collections.sort(cluster);
        //Collections.reverse(cluster);
            for(int i=0;i<cluster.size();i++ ){
                if(cluster.size()<3)System.out.printf(""%d %.2f %.2f\n"",cluster.get(i).size,cluster.get(i).cx,cluster.get(i).cy);
                if(cluster.get(i).count==0)
                System.out.printf(""%d %.2f %.2f\n"",cluster.get(i).size,cluster.get(i).cx,cluster.get(i).cy);
            }
            
        System.out.printf(""%.2f\n"",small_dis);
        //System.out.printf(""doit is %d\n"",doit);

        
        }
    }
}

