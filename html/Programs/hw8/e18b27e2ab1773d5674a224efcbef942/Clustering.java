import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
 */
public class Clustering {
    
    public static class cluster implements Comparable<cluster> {
        private final double x;    // x coordinate
        private final double y;
        private final int index;
        private int size;
        private int state;
        
        public cluster(double x, double y,int size,int state,int index){
            this.x = x;
            this.y = y;
            this.size = size;
            this.state = state;
            this.index = index;
        }
        public double x(){
            return x;
        }
        public double y(){
            return y;
        }
        public int size(){
            return size;
        }
        public int index(){
            return index;
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
            else if (this.x() > that.x()) return +1;
            else if (this.x() < that.x()) return -1;
            else if (this.y() > that.y()) return +1;   
            else if (this.y() < that.y()) return -1; 
            else 
            return 0;
        }
        
    }
    
    //public static class pair{
    public static class pair implements Comparable<pair> {
        
        private final cluster a;    // x coordinate
        private final cluster b;
        private final double distance;
        
        public pair(cluster a, cluster b){
            this.a = a;
            this.b = b;
            this.distance = Math.sqrt(((a.x()-b.x())*(a.x()-b.x())) + ((a.y()-b.y())*(a.y()-b.y()))); 
        }
        public cluster a(){
            return a;
        }
        public cluster b(){
            return b;
        }
        public double distance(){
            return distance;
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
           String buf = br.readLine();
           int n = Integer.valueOf(buf);
           QuickUnionUF uf = new QuickUnionUF(n*n);
           
           cluster[] points = new cluster[n];
           int count = 0;
           MinPQ<pair>pq = new MinPQ<pair>();
           //MinPQ<pair>pq2 = new MinPQ<pair>();
           
           Queue<cluster> pointqueue = new Queue<cluster>();
           Queue<pair> pq2 = new Queue<pair>();
           while (br.ready())
           {      
                String buf2[] = br.readLine().split("" "");
                double x = Double.valueOf(buf2[0]);
                double y = Double.valueOf(buf2[1]);
                points[count] = new cluster(x,y,1,1,count);
                pointqueue.enqueue(points[count]);
                //===============================
//                StdDraw.setPenRadius(0.01);
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.point(points[count].x(),points[count].y());
                //===============================
                count++;
            }
            
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    pair CC = new pair(points[i],points[j]);
                    pq.insert(CC);
                    //pq2.en(CC);
                }
            }
            
            
            int newcount = 0;
            while(pointqueue.size()>3){               
                pair out = pq.delMin();
                pq2.enqueue(out);
                if(out.a().state()==1&&out.b().state()==1){ 
                    
//                    StdDraw.setPenRadius(0.001);
//                    StdDraw.setPenColor(StdDraw.GREEN);
//                    StdDraw.line(out.a().x(),out.a().y(),out.b().x(),out.b().y());
                    out.a().setdelete();
                    out.b().setdelete();
                    
                    uf.union(out.a().index(),out.b().index());
                    
                    newcount++;
                    uf.union(out.a().index(),n+newcount);
                    cluster newc = new cluster((out.a().x()*out.a().size()+out.b().x()*out.b().size())/(out.a().size()+out.b().size()),(out.a().y()*out.a().size()+out.b().y()*out.b().size())/(out.a().size()+out.b().size()),(out.a().size()+out.b().size()),1,n+newcount);

                    int queuesize = pointqueue.size();
                    cluster merge;
                    
                    for(int i=0;i<queuesize;i++) {
                        merge = pointqueue.dequeue();
                        if(!((merge.x()==out.a().x())&&(merge.y()==out.a().y()))&&!((merge.x()==out.b().x())&&(merge.y()==out.b().y()))){ 
                            pair newCC = new pair(newc,merge);
                            pq.insert(newCC);
                            pointqueue.enqueue(merge); 
                        }
                    }
                    pointqueue.enqueue(newc);
                    
//                    StdDraw.setPenRadius((double)(out.a().size()+out.b().size())/200);
//                    StdDraw.setPenColor(StdDraw.RED);
//                    StdDraw.point((out.a().x()*out.a().size()+out.b().x()*out.b().size())/(out.a().size()+out.b().size()),(out.a().y()*out.a().size()+out.b().y()*out.b().size())/(out.a().size()+out.b().size()));
                }
            }
            int ouputcount = 0;
            cluster [] ouputarray = new cluster [3];
            while(!pointqueue.isEmpty()){
                cluster ouput = pointqueue.dequeue();
                ouputarray[ouputcount] = ouput;
                ouputcount++;
            }
            Insertion.sort(ouputarray);
            for(int i=ouputcount-1;i>=0;i--){
                System.out.println(ouputarray[i].size()+"" ""+String.format(""%.2f"",ouputarray[i].x())+"" ""+String.format(""%.2f"",ouputarray[i].y()));
            }
            int pqsize = pq2.size();
            int pqcount = 0;
                for(int i=0;i<pqsize;i++){
                    pair out = pq2.dequeue();
                    
                        if(!uf.connected(out.a().index(),out.b().index())){
                            if(pqcount==0){
//                            StdDraw.setPenRadius(0.001);
//                            StdDraw.setPenColor(StdDraw.BLUE);
//                            StdDraw.line(out.a().x(),out.a().y(),out.b().x(),out.b().y());
                            System.out.println(String.format(""%.2f"",out.distance()));
                                break;
                            }
                            pqcount++;
                        }
                }
       }
    }
    
}

