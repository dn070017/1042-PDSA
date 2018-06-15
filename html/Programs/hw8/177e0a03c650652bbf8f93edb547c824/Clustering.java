import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;



public class Clustering {
    
    private static int Pointnum;
    public static Point2D[] pointsarray;
    public static Point2D[] clusterarray;
    private MinPQ<Pair> pq = new MinPQ<Pair>();
    
    public static class Pair implements Comparable<Pair>{
        Double dis;
        Point2D A,B;
        
        public Pair(Point2D a,Point2D b){
            this.A=a;
            this.B=b;
            this.dis=a.distanceTo(b);
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
        
        private boolean havecloned(Point2D x, Point2D y){
            for (int sc=0;sc<Clustering.Cluster.howmanyclus.size();sc++){              
               if(!Cluster.amIthere(sc,x)||!Cluster.amIthere(sc,y)) {                   
                   return true;
               }              
               else break;
            }
            return false;
        }
              
        @Override
        public int compareTo(Pair that){
            Double ans = that.Distance();
            if(this.dis>ans) return +1;
            if(this.dis<ans) return -1;
            return 0;
        }
    }
    
    public static class Cluster {
        private static ArrayList<ArrayList<Point2D>> howmanyclus= new ArrayList<ArrayList<Point2D>>();
        
        private static void Cluster_initial (Point2D p){
      
            ArrayList<Point2D> cluster = new ArrayList<Point2D>();
            cluster.add(p);
            howmanyclus.add(cluster);                            
            
        }
        
        
        private Cluster(Point2D p, Point2D q) {
            int ind1 = -1;
            int ind2 = -1;
            for (int index=0;index<howmanyclus.size();index++){
                if(scanArraylist(index,p)){
                    ind1=index;}
                if(scanArraylist(index,q)){
                    ind2=index;}                   
            }
            ArrayList<Point2D> temp = howmanyclus.get(ind1);
            temp.addAll(howmanyclus.get(ind2));
            howmanyclus.remove(ind2);
                        
        }

        public static Point2D CenterG(ArrayList<Point2D> all){
            int len = all.size();
            if (len==1) {
                return all.get(0);
            }
            
            Double sumx=0.0;
            Double sumy=0.0;
            for(int i=0; i<len;i++){
                sumx=sumx+all.get(i).x();
                sumy=sumy+all.get(i).y();
            }
            Point2D CGans = new Point2D(sumx/len,sumy/len);        
            return CGans;
        }
        
        public static boolean amIthere(int x,Point2D u){
            for(int t=0;t<clusterarray.length;t++){
                if (clusterarray[t].equals(u)) return true;           
            }
            return false;
        }
        
        
        private boolean scanArraylist(int x,Point2D y){
            return clusterarray[x].equals(y);
        }
        
        private void clusterending(){
            for (int index=0;index<howmanyclus.size();index++){
                ArrayList<Point2D> thisHashSet = howmanyclus.get(index);
                if(thisHashSet.isEmpty()) howmanyclus.remove(thisHashSet);                      
            }
        }
        
        
      
    }
     
    
    
    
    private void ScanDistance(){
        //Double disinput;
        clusterarray = new Point2D[Cluster.howmanyclus.size()];
        
        for(int run=0;run<Cluster.howmanyclus.size();run++){
            ArrayList<Point2D> a = Cluster.howmanyclus.get(run);          
            clusterarray[run]=Cluster.CenterG(a);          
        }
        scanner(clusterarray);
        
    }
    
    private void scanner(Point2D[] x){
        Pair pair = null ;
        for(int iter=0;iter<x.length-1;iter++){
            for(int i=iter+1;i<x.length;i++){
                pair = new Pair(x[iter],x[i]);
                pq.insert(pair);
            }           
        }        
        
    }
    
    
    private void PriorQprocessor(){
        while (Cluster.howmanyclus.size()>3){
            Pair needclone = pq.delMin();               
            Point2D[] E = needclone.getPair();
            
            if (!needclone.havecloned(E[0],E[1])){               
               Cluster one = new Cluster(E[0],E[1]);              
               ScanDistance();                
            }
            
        }
       
            
    }

    public void Justout(){
        int howlong = pointsarray.length;
        for(int pr=0;pr<howlong;pr++){
            System.out.println(""1""+' '+String.format(""%.2f"",pointsarray[pr].x())+' '+String.format(""%.2f"",pointsarray[pr].y()));
        }
    }
    
    public void output(){
        int howlong = Cluster.howmanyclus.size();
        HashMap<Integer,Point2D> sizesort = new HashMap<Integer,Point2D>();
        
        for(int pr=0;pr<howlong;pr++){         
            sizesort.put(Cluster.howmanyclus.get(pr).size(),clusterarray[pr]);           
        }
        sortMAP(sizesort);
        
    }
    
    private void sortMAP(HashMap<Integer,Point2D> f){
        SortedSet<Integer> keys= new TreeSet<Integer>(Collections.reverseOrder());
        keys.addAll(f.keySet());
        for (int key : keys) { 
            Point2D value = f.get(key);
            System.out.println(key+"" ""+String.format(""%.2f"", value.x())+"" ""+String.format(""%.2f"", value.y()));
        }
    }
     
     private void pointOUT(){
         MinPQ<Pair> mine = new MinPQ<Pair>();
         Pair pair;
         for(int iter=0;iter<Cluster.howmanyclus.size()-1;iter++){
             for(int i = iter+1;i<Cluster.howmanyclus.size();i++){
                for(Point2D each : Cluster.howmanyclus.get(iter)){
                   for(Point2D every:Cluster.howmanyclus.get(i)){
                      pair = new Pair(each,every);
                      mine.insert(pair);
                   }
                }
             }
         } 
         Pair ans =mine.delMin();
         System.out.println(String.format(""%.2f"",ans.dis));
     }
     
     private void specialdis(){
        MinPQ<Double> haha = new MinPQ<Double>();
        haha.insert(pointsarray[0].distanceTo(pointsarray[1]));
        haha.insert(pointsarray[0].distanceTo(pointsarray[2]));
        haha.insert(pointsarray[1].distanceTo(pointsarray[2]));
        System.out.println(String.format(""%.2f"",haha.delMin()));      
     }
    
     private void start(){
         for(Point2D points: pointsarray) Cluster.Cluster_initial(points);
         ScanDistance();        
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
         
            for(String points;( points = br.readLine()) != null; ){
                
                String[] cor=points.split("" "");
                Double x = Double.parseDouble(cor[0]);
                Double y = Double.parseDouble(cor[1]);
                Point2D w = new Point2D(x, y);               
                pointsarray[time]=w;
                time++;
            }
                       
                
            
            if (program.Pointnum<=3){
                program.Justout();
                program.specialdis();
            }
            else{
                program.start();
                program.output();
                program.pointOUT();
            }
        
            
        
    }
    
}
}

