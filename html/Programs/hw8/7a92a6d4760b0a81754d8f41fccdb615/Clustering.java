
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 余軒
 */
public class Clustering {
    
    private static class Cluster implements Comparable<Cluster>{
        private double cx; // center x after clustering
        private double cy; // center y after clustering
        private int size;  // size after clustering
        private int index;
        private int head;
        private int valid = 1;
        public Cluster(double x,double y,int size,double x2,double y2,int size2,int id,int head){
            this.cx = (x*size+x2*size2)/(size+size2);
            this.cy = (y*size+y2*size2)/(size+size2);
            this.size = size+size2;
            this.head = head;
            this.index = id;
            
        }
        
        public double centroidx(){
            return this.cx;
        }
        public double centroidy(){
            return this.cy;
        }
        public int getSize(){
            return this.size;
        }
        public int getHead(){
            return this.head;
        }
        
        public int compareTo(Cluster that){
            if (this.size < that.size) return -1;
            if (this.size > that.size) return 1;
            if (this.centroidx() > that.centroidx()) return -1;
            if (this.centroidx() < that.centroidx()) return 1;
            if (this.centroidy() > that.centroidy()) return -1;
            if (this.centroidy() < that.centroidy()) return 1;
            return 0;
        }

    }
    
    private static class Pair implements Comparable<Pair>{
        private Cluster A,B;
        public Pair(Cluster a,Cluster b){
           this.A = a;
           this.B = b;
        }
        public double getDistance(){
            double dx = this.A.centroidx() - this.B.centroidx();
            double dy = this.A.centroidy() - this.B.centroidy();
            return Math.sqrt(dx*dx+dy*dy);
        }
        
        public Cluster getClusterA(){
            return this.A;
        }
        public Cluster getClusterB(){
            return this.B;
        }
        
        public int compareTo(Pair that){
            if (this.getDistance() > that.getDistance()) return 1;
            if (this.getDistance() < that.getDistance()) return -1;
            return 0;
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int np = Integer.parseInt(br.readLine());
            int Cluster_num = np;
            Cluster[] GroupC = new Cluster[np*2]; // every point is one indivsiual Cluster, and another np is to store new centroid point when np-- til 3
            int id = 0;
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(np);
            
            
            for(String in = br.readLine(); in != null; in = br.readLine()) { 
                String[] data = in.split("" "");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                GroupC[id] = new Cluster(x,y,1,0,0,0,id,id);
                        id++;
                        
            }
             //System.out.println(GroupC[0].cx);
            MinPQ<Pair> pq = new MinPQ<Pair>(np);
            
            int idp = 0;
            Pair[] GroupP= new Pair[np*np/2];
            for (int i = 0;i<np;i++){
                for (int j = i+1;j<np;j++){
                    GroupP[idp] = new Pair(GroupC[i],GroupC[j]);
                    pq.insert(GroupP[idp]);
                    idp++;
                }
            }
            //System.out.println(idp);
            Pair temp;
            while (Cluster_num > 3){
                temp = pq.delMin();
                if (temp.A.valid ==1 && temp.B.valid==1){
                    temp.A.valid = 0;
                    temp.B.valid = 0;
                
                uf.union(temp.A.getHead(),temp.B.getHead());
                GroupC[id] = new Cluster(temp.A.cx,temp.A.cy,temp.A.getSize(),temp.B.cx,temp.B.cy,temp.B.getSize(),id,temp.A.head);   // new cluster valid = 1
                for (int i=0;i<id ;i++){  //insert new pair
                    if(GroupC[i].valid==1){
                    Pair newP = new Pair(GroupC[id],GroupC[i]);
                    pq.insert(newP);
                    }
                }
                id++;
                Cluster_num--;    
             } 
             }
            
            // Now, we have seperate 3 group,and we store new pair pq from different group
            MinPQ<Pair> pq2 = new MinPQ<Pair>(np);
            Pair[] diff_p = new Pair[np * np / 2];
            int d = 0;
            for (int i = 0; i < np; i++) { // we don't the original point belongs to which final group
                for (int j = i + 1; j < np; j++) {
                    if (!uf.connected(i, j)) {
                        diff_p[d] = new Pair(GroupC[i], GroupC[j]);
                        pq2.insert(diff_p[d]);
                        d++;
                    }
                }
            }
            
            
            MaxPQ<Cluster> final_pq = new MaxPQ<Cluster>(3);
            for (int i = 0; i < id; i++) {
                if (GroupC[i].valid == 1) {  // every final cluster valid = 1 ,this time , id have been > np (2np)
                    final_pq.insert(GroupC[i]);
                }
            }
            
            Pair temp2 = pq2.delMin();
            Cluster p1 = final_pq.delMax();
            Cluster p2 = final_pq.delMax();
            Cluster p3 = final_pq.delMax();
            
            System.out.println(p1.size +"" ""+ String.format(""%.2f"",p1.cx)+"" ""+String.format(""%.2f"",p1.cy));
            System.out.println(p2.size +"" ""+ String.format(""%.2f"",p2.cx)+"" ""+String.format(""%.2f"",p2.cy));
            System.out.println(p3.size +"" ""+ String.format(""%.2f"",p3.cx)+"" ""+String.format(""%.2f"",p3.cy));
//String.format(""%d.2f"",temp2.getDistance());
            System.out.println(String.format(""%.2f"",temp2.getDistance()));
            
           

        }
    }
}
