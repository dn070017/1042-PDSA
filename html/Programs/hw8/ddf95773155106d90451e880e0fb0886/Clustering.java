import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Clustering {

    private static int Pointnum;
    private static Point2D[] pointsarray;
    private MinPQ<Pair> pq = new MinPQ<Pair>();
    public static ArrayList<Cluster> clustercollector;
    
    public static class Pair implements Comparable<Pair>{
        Double dis;
        Point2D A,B;
        Cluster x,y;
        
        public Pair(Cluster a,Cluster b){
            this.A=a.getCenter();
            this.B=b.getCenter();
            this.dis=a.distanceTo(b);
            this.x=a;
            this.y=b;
        }
        
        private Double Distance(){
            return dis;
        }
        
        private Point2D[] getPair(){
            Point2D[] ans = new Point2D[2];
            ans[0]=this.A;
            ans[1]=this.B;
            return ans;
        }
        
        private Cluster[] getCluster(){
            Cluster[] ans = new Cluster[2];
            ans[0]=this.x;
            ans[1]=this.y;
            return ans;
        }
        
        private boolean needtocloned(Pair x){
            Cluster a,b;
            Cluster[] n = x.getCluster();
            a = n[0];
            b = n[1];
            for(int t=0;t<clustercollector.size();t++){
                if (clustercollector.contains(a) && clustercollector.contains(b)) return true;           
            }
            return false;
           
        }
        
        public int compareTo(Pair that){
            Double ans = that.Distance();
            if(this.dis>ans) return +1;
            if(this.dis<ans) return -1;
            return 0;
        }

        
    }

    public static class Cluster implements Comparable<Cluster>{
        private static ArrayList<Point2D> cluster= new ArrayList<Point2D>();
        private Point2D CenterG;
        private int howmanypoints;

        public Cluster(){}
        
        private Cluster(Point2D a){
            cluster.add(a);
            this.CenterG=a;
            this.howmanypoints=1;
        }

        public Point2D getCenter(){
            return this.CenterG;
        }

        public int getsize(){
            return this.howmanypoints;
        }
        
        public double distanceTo(Cluster that){
            return this.getCenter().distanceTo(that.getCenter());           
        }


        private static Cluster UNION(Cluster[] arr) {
            Cluster a =arr[0];
            Cluster b =arr[1];
            //System.out.println(""union!"");
            double x1,x2,y1,y2;
            int SIZE = a.getsize()+b.getsize();

            x1=a.getCenter().x();
            y1=a.getCenter().y();
            x2=b.getCenter().x();
            y2=b.getCenter().y();
            Point2D newp = new Point2D((x1*a.getsize()+x2*b.getsize())/SIZE,(y1*a.getsize()+y2*a.getsize())/SIZE);
            
            Cluster n = new Cluster(newp);
            n.howmanypoints=SIZE;
            for(int i = 0;i < a.getsize();i++){
                n.cluster.add(a.cluster.get(i));
            }
            clustercollector.remove(a);
            
            for(int i = 0;i < b.getsize();i++){
                n.cluster.add(b.cluster.get(i));
            }
            clustercollector.remove(b);
            
            clustercollector.add(n);

            return n;
        }
        
        
        public int compareTo(Cluster that){
            int ans = that.getsize();
            if(this.howmanypoints >ans) return +1;
            if(this.howmanypoints<ans) return -1;
            return 0;
        }
        public void draw() {
            StdDraw.point(CenterG.x(), CenterG.y());
        }
    
        public void drawTo(Cluster that) {
            StdDraw.line(this.CenterG.x(), this.CenterG.y(), that.CenterG.x(), that.CenterG.y());
        }
    
    }
    
    public void ScanDistance(Cluster x){       
        for(Cluster s:clustercollector){ 
            Pair ans = new Pair(s, x);
            pq.insert(ans);
        }
       
    }
    


    private void pointOUT(){
        MinPQ<Cluster> mine = new MinPQ<Cluster>();
        
        
        mine.insert(clustercollector.get(0));
        mine.insert(clustercollector.get(1));
        mine.insert(clustercollector.get(2));
        
        for(int outprint=0;outprint < mine.size();outprint++){
            Cluster ans =mine.delMin();
            System.out.println(ans.getsize()+"" ""+String.format(""%.2f"",ans.CenterG.x())+"" ""+String.format(""%.2f"",ans.CenterG.y()));
        }
        
    }
    
    
    private void PriorQprocessor(){
            while (clustercollector.size()>3){
                Pair needclone = pq.delMin();               
                if (needclone.needtocloned(needclone)){               
                   ScanDistance(Cluster.UNION(needclone.getCluster()));                  
                   System.out.println(""plus one!"");
                }
            }
     }

    private void start(){
        clustercollector = new ArrayList<Cluster>();
        for(Point2D points:pointsarray) {
            Cluster nc = new Cluster(points);
           // nc.draw();
            clustercollector.add(nc);
        }
        
        for(int ind =0 ;ind<clustercollector.size()-1;ind++){
            for(int nextind = ind+1; nextind<clustercollector.size();nextind++){
                Pair trypair = new Pair(clustercollector.get(ind),clustercollector.get(nextind));
                pq.insert(trypair);
            }   
        }
        PriorQprocessor();
        
    }

    public static void main(String[] args) throws Exception {
            // TODO code application logic here
               
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            int Num_point = Integer.parseInt(br.readLine());
            Clustering program = new Clustering();

            program.Pointnum=Num_point;       
            program.pointsarray = new Point2D[Pointnum];
            int time = 0;

            for(String points;(points = br.readLine()) != null; ){
                String[] cor=points.split("" "");
                Double x = Double.parseDouble(cor[0]);
                Double y = Double.parseDouble(cor[1]);
                Point2D w = new Point2D(x, y);               
                pointsarray[time]=w;
                time++;
            }

            if (program.Pointnum<=3){
                program.Justout(pointsarray);
                program.Justdis();
            }
            else{
                program.start();
                program.pointOUT();
            }
        }
    }
    public void Justout(Point2D[] s){
        Insertion.sort(s);
        for(int x = 0 ; x < s.length ; x++){
        System.out.println(""1 ""+String.format(""%.2f"",s[x]));
        
        }
    }
    
    public void Justdis(){
        MinPQ<Double> haha = new MinPQ<Double>();
        for(int x = 0 ;x <pointsarray.length-1; x++){
            for(int y =x +1;y<pointsarray.length;y++){
                haha.insert(pointsarray[x].distanceTo(pointsarray[y]));
            }     
        }
        System.out.println(String.format(""%.2f"",haha.delMin())); 
    }
    
    
}





