import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.awt.Color;
import java.util.Arrays;

public class Clustering {
    
    public static class cluster implements Comparable<cluster> {
        public int size;
        public int state;
        public int countput;
        final double ClusX;    // x coordinate
        final double ClusY;
        final int num;

        
        public cluster(double x, double y,int size,int state,int index){
            this.ClusX = x;
            this.ClusY = y;
            this.size = size;
            this.state = state;
            this.num = index;
        }
        public double x(){
            return ClusX;
        }
        public double y(){
            return ClusY;
        }
        public int size(){
            return size;
        }
        public int index(){
            return num;
        }
        public void setsize(int s){
            this.size = s;
        }
        public int state(){
            return state;
        }
        public void setdelete(){
            this.state = 0;
        }
        public int compareTo(cluster that) 
        {
            if (this.size() > that.size()) return +1;
            else if(this.size() < that.size()) return -1;
            else 
            return 0;
        }
        
    }
    
  
    public static class pair implements Comparable<pair> {
        
        private final cluster a;    // x coordinate
        private final cluster b;
        private final double DIS;
        
        public pair(cluster a, cluster b){
            this.a = a;
            this.b = b;
            this.DIS = Math.sqrt(((a.x()-b.x())*(a.x()-b.x())) + ((a.y()-b.y())*(a.y()-b.y()))); 
        }
        public cluster a(){
            return a;
        }
        public cluster b(){
            return b;
        }
        public double distance(){
            return DIS;
        }
        public int compareTo(pair that) 
        {
            if (this.distance() < that.distance()) return -1;
            if (this.distance() > that.distance()) return +1;
            if (this.distance() == that.distance()) return 0;         
            return 0;
        }
    }
    
    public static void main(String[] args) throws Exception{
       try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
           int count = 0;
           String buf = br.readLine();
           int n = Integer.valueOf(buf);
           MinPQ<pair>pq = new MinPQ<pair>();
           QuickUnionUF uf = new QuickUnionUF(n*n);
           
           cluster[] points = new cluster[n+n];
                      
           Queue<cluster> PQ = new Queue<cluster>();
           Queue<pair> pq2 = new Queue<pair>();
           for(int i=0 ; i<n ; i++){
                
                String[] sep = br.readLine().split("" "");
                points[i] = new cluster(Double.valueOf(sep[0]),Double.valueOf(sep[1]),1,1,i);
                PQ.enqueue(points[i]); 
                
            }
            count = n;
            
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    pair CC = new pair(points[i],points[j]);
                    pq.insert(CC);
                }
            }

            int newcount = 0;
            while(PQ.size()>3){               
                pair out = pq.delMin();
                pq2.enqueue(out);
                if(out.a().state()==1&&out.b().state()==1){ 
     
                    out.a().setdelete();
                    out.b().setdelete();
                    
                    uf.union(out.a().index(),out.b().index());
                    
                    newcount++;
                    uf.union(out.a().index(),n+newcount);
                    cluster newc = new cluster((out.a().x()*out.a().size()+out.b().x()*out.b().size())/(out.a().size()+out.b().size()),(out.a().y()*out.a().size()+out.b().y()*out.b().size())/(out.a().size()+out.b().size()),(out.a().size()+out.b().size()),1,n+newcount);

                    int queuesize = PQ.size();
                    
                    
                    for(int i=0;i<queuesize;i++) {
                        cluster tempM;
                        tempM = PQ.dequeue();
                        if(!((tempM.x()==out.a().x())&&(tempM.y()==out.a().y()))&&!((tempM.x()==out.b().x())&&(tempM.y()==out.b().y()))){ 
                            pair newCC = new pair(newc,tempM);
                            pq.insert(newCC);
                            PQ.enqueue(tempM); 
                        }
                    }
                    PQ.enqueue(newc);
                }
                
            }
            int OpCo = 0;
            int outsize = 3;
            if(n<3){outsize = n;}
            cluster [] ouputarray = new cluster [outsize];
           
            while(!PQ.isEmpty()){
                cluster ouput = PQ.dequeue();
                ouputarray[OpCo] = ouput;
                OpCo++;
            }
            
            Insertion.sort(ouputarray);
            if(n>3){
            for(int i=OpCo-1;i>=0;i--){
                System.out.println(ouputarray[i].size()+"" ""+String.format(""%.2f"",ouputarray[i].x())+"" ""+String.format(""%.2f"",ouputarray[i].y()));
            }
            }
            else{
            for(int i=0;i<OpCo;i++){
                System.out.println(ouputarray[i].size()+"" ""+String.format(""%.2f"",ouputarray[i].x())+"" ""+String.format(""%.2f"",ouputarray[i].y()));
                
            }
            }
            int pqsize = pq2.size();
            int pqcount = 0;
      
                for(int i=0;i<pqsize;i++){
                    pair out = pq2.dequeue();
                    
                        if(!uf.connected(out.a().index(),out.b().index())){
                            if(pqcount==0){
                            System.out.println(String.format(""%.2f"",out.distance()));
                                break;
                            }
                            pqcount++;
                        }
                }
                if(n<=3){pair out = pq.delMin(); System.out.println(String.format(""%.2f"",out.distance()));}
       }
    }
    
}

