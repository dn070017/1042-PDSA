
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
public class CriticalDis {

    /**
     * @param args the command line arguments
     */
    private static class Pair implements Comparable<Pair>{
        public Integer id1;
        public Integer id2;
        public double dis;
        public Pair(Integer id1, Integer id2, double dis){
            this.id1 = id1;
            this.id2 = id2;
            this.dis = dis;
        }
        public int compareTo(Pair that){
            if (this.dis > that.dis){return 1;}
            else if (this.dis < that.dis){return -1;}
            else{return 0;}
        }
        
        public Integer getID1(){
            return this.id1;
        }
        
        public Integer getID2(){
            return this.id2;
        }
        
        public double getDis(){
            return this.dis;
        }
    }
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Integer N;
        Point2D[] PointList;
        MinPQ<Pair> PairPQ;
        
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            N = Integer.parseInt(br.readLine());
            PointList = new Point2D[N];
            PairPQ = new MinPQ<Pair>(N^2);
            
            String temp;
            for (int i=0;i<N;i++){
                temp = br.readLine();
                String[] split_temp=temp.split("" "");
                Point2D p = new Point2D(Double.parseDouble(split_temp[0]),Double.parseDouble(split_temp[1]));
                PointList[i]=p;
                
            }
        }
        
        Point2D Source = PointList[0];
        Integer S_ID = 0;
        Point2D Target = PointList[0];
        Integer T_ID = 0;
        
        for (int i=0;i<N;i++){
            Point2D tempi = PointList[i];
            if ((tempi.x()+tempi.y())<(Source.x()+Source.y())){
                Source = tempi;
                S_ID = i;
            }
            else if ((tempi.x()+tempi.y())>(Target.x()+Target.y())){
                Target = tempi;
                T_ID = i;
            }
            for (int j=i+1;j<N;j++){
                Point2D tempj = PointList[j];
                if ((tempi.x()<tempj.x())&&(tempi.y()<tempj.y())){
                    Pair pair = new Pair(i,j,tempi.distanceTo(tempj));
                    PairPQ.insert(pair);
                }
                else if ((tempj.x()<tempi.x())&&(tempj.y()<tempi.y())){
                    Pair pair = new Pair(j,i,tempj.distanceTo(tempi));
                    PairPQ.insert(pair);
                }
            }
        }
        
        Digraph G = new Digraph(N);
        DepthFirstDirectedPaths DFDP = new DepthFirstDirectedPaths(G,S_ID);
        double theDis = 0;
        while(DFDP.hasPathTo(T_ID)==false){
            Pair CP = PairPQ.delMin();
            theDis = CP.getDis();
            G.addEdge(CP.getID1(), CP.getID2());
            DFDP = new DepthFirstDirectedPaths(G,S_ID);
        }
        
        System.out.printf(""%1.3f\n"", theDis);
        
    }
    
}

