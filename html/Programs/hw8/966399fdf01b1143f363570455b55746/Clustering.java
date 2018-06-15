
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */


/*
class Pair implements Comparable<Pair>{
    public int ID1;
    public int ID2;
    public double Dis;
    public Pair(int id1, int id2, double dis){
        this.ID1 = id1;
        this.ID2 = id2;
        this.Dis = dis;
    }
    public int compareTo(Pair that){
        if (this.Dis >= that.Dis){
            return 1;
        }
        else{
            return 0;
        }
    }
    public int getID1(){
        return this.ID1;
    }
    public int getID2(){
        return this.ID2;
    }
    public double getDis(){
        return this.Dis;
    }
    public boolean isValid(){
        if (CentroidDict.containsKey(this.ID1))
        Clustering.main();
        return true;
    } 
}
*/
/////////////////////////////////////////////////////////////
public class Clustering {

    /**
     * @param args the command line arguments
     */
    static Hashtable<Integer, Point2D> CentroidDict = new Hashtable<Integer, Point2D>();
    static Hashtable<Integer, Integer[]> ClusterDict = new Hashtable<Integer, Integer[]>();
    
    private static class Pair implements Comparable<Pair>{
        public int ID1;
        public int ID2;
        public double Dis;
        public Pair(int id1, int id2, double dis){
            this.ID1 = id1;
            this.ID2 = id2;
            this.Dis = dis;
        }
        public int compareTo(Pair that){
            if (this.Dis > that.Dis){
                return 1;
            }
            else{
                return 0;
            }
        }
        public int getID1(){
            return this.ID1;
        }
        public int getID2(){
            return this.ID2;
        }
        public double getDis(){
            return this.Dis;
        }
        public boolean isValid(){
            if (!CentroidDict.containsKey(this.ID1)) return false;
            if (!CentroidDict.containsKey(this.ID2)) return false;
            return true;
        }
    }
    
    
    private static Integer[] Merge(Integer[] Array1,Integer[] Array2){
        int len1 = Array1.length;
        int len2 = Array2.length;
        Integer[] Out = new Integer[len1+len2];
        for (int i=0;i<len1;i++){
            Out[i]=Array1[i];
        }
        for (int i=0;i<len2;i++){
            Out[len1+i]=Array2[i];
        }
        return Out;
    }
    
    private static Integer Compare(int ID1, int ID2){
        Point2D C1 = CentroidDict.get(ID1);
        Point2D C2 = CentroidDict.get(ID2);
        if (C1== null && C2 == null) return 0;
        else if (C1 == null) return -1;
        else if (C2 == null) return 1;
            
        int S1 = ClusterDict.get(ID1).length;
        int S2 = ClusterDict.get(ID2).length;
        
        if (S1 > S2)return 1;
        else if(S1 < S2)return -1;
        else{
            if (C1.x() > C2.x()) return -1;
            else if(C1.x() < C2.x()) return 1;
            else{
                if (C1.y() > C2.y()) return -1;
                else if(C1.y() < C2.y()) return 1;
                else return 0;
            }
        }
        
    }
    
    private static Integer[] Sort(Integer[] R){
        int n = R.length;
        Integer[] R2 = new Integer[n];
        if (n==1)return R;
        if (n==2){
            if (Compare(R[0],R[1])>=0)return R;
            else {
                R2[0]=R[1];
                R2[1]=R[0];
                return R2;
            }
        }
        if (n==3){
            if (Compare(R[0],R[1])==1){
                if (Compare(R[1],R[2])==1)return R;
                else if(Compare(R[2],R[0])==1){
                        R2[0]=R[2];
                        R2[1]=R[0];
                        R2[2]=R[1];
                        return R2;
                    }
                else{
                    R2[0]=R[0];
                    R2[1]=R[2];
                    R2[2]=R[1];
                    return R2;
                }
            }
            else if (Compare(R[0],R[1])==-1){
                if (Compare(R[2],R[1])==1){
                    R2[0]=R[2];
                    R2[1]=R[1];
                    R2[2]=R[0];
                    return R2;
                }
                else if(Compare(R[0],R[2])==1){
                    R2[0]=R[1];
                    R2[1]=R[0];
                    R2[2]=R[2];
                    return R2;
                }
                else{
                    R2[0]=R[1];
                    R2[1]=R[2];
                    R2[2]=R[0];
                    return R2;
                }
            }
            else{
                if (Compare(R[2],R[1])==1){
                    R2[0]=R[2];
                    R2[1]=R[0];
                    R2[2]=R[1];
                    return R2;
                }
                else return R;
            }
        }
    return R;    
    }
    ////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int Size = Integer.parseInt(br.readLine());
            int N = Size;
            
            Point2D[] PointList = new Point2D[N];
            MinPQ<Pair> PairPQ1 = new MinPQ<Pair>(N);
            
            for (int i=0; i<N; i++){
                Integer[] ii = new Integer[1];
                ii[0] = i;
                ClusterDict.put(i,ii);
                
                String[] row = br.readLine().split("" "");
                Point2D PP = new Point2D(Double.parseDouble(row[0]),Double.parseDouble(row[1]));
                //System.out.println(PP);
                PointList[i]=(PP);
                CentroidDict.put(i, PP);
                
                for (int j=0;j<i;j++){
                    double D = PointList[i].distanceTo(PointList[j]);
                    PairPQ1.insert(new Pair(i,j,D));
                }
                /*
                double MinD = PointList[i].distanceTo(PointList[0]);
                if (i==0) continue;
                int Partner = 0;
                for (int j=1;j<i;j++){
                    double D = PointList[i].distanceTo(PointList[j]);
                    if (D < MinD){
                        MinD = D;
                        Partner = j;
                    }
                    
                }
                PairPQ1.insert(new Pair(i,Partner,MinD)); 
                */
            }
            /*
            Iterator<Pair> PairI = PairPQ1.iterator();
            while(PairI.hasNext()){
                Pair Min = PairI.next();
                System.out.println(Min.getID1());
                System.out.println(Min.getID2());
                System.out.println(""stop"");
            }
            */
           
            
            while(N > 3){
                //Iterator<Pair> PairI = PairPQ1.iterator();
                //Pair Min = PairI.next();
                Pair Min = PairPQ1.delMin();
                /*
                System.out.println(Min.ID1);
                System.out.println(Min.ID2);
                System.out.println(Min.isValid());
                */        
                if (!Min.isValid()) continue;
                //System.out.println(Min);
                
                int MinID1 = -1;
                int MinID2 = -1;
                if (Min.getID1() <= Min.getID2()){
                    MinID1=Min.getID1();
                    MinID2=Min.getID2();
                }
                else{
                    MinID1=Min.getID2();
                    MinID2=Min.getID1();
                }
                
                double X=0;
                double Y=0;
                int n=0;
                
                Integer[] IDList1 = ClusterDict.get(MinID1);
                Integer[] IDList2 = ClusterDict.get(MinID2);
                System.out.println(Arrays.toString(IDList1));
                System.out.println(Arrays.toString(IDList2));
                
                Integer[] New = Merge(IDList1,IDList2);
                System.out.println(Arrays.toString(New));
                System.out.println(""stop"");
                ClusterDict.put(MinID1,New);
                ClusterDict.remove(MinID2);
                //ClusterDict.put(MinID2,null);
                    
                n=New.length;
                for (int i=0;i<n;i++){
                    X=X+PointList[New[i]].x();
                    Y=Y+PointList[New[i]].y(); 
                }
                
                Point2D Centroid = new Point2D(X/n,Y/n);
                CentroidDict.put(MinID1, Centroid);
                CentroidDict.remove(MinID2);
                
                Set<Integer> ST =CentroidDict.keySet();
                Integer[] ST2 = ST.toArray(new Integer[ST.size()]);
                //System.out.println(Arrays.toString(ST2));
                
                for (int i=0;i<ST2.length;i++){
                    //System.out.println(i);
                    if (ST2[i] != MinID1){
                        //System.out.println(i);
                        //System.out.println(CentroidDict.get(i));
                        double d = Centroid.distanceTo(CentroidDict.get(ST2[i]));
                        PairPQ1.insert(new Pair(MinID1,ST2[i],d));
                    }          
                }
                N=N-1;
            }
            
            Set<Integer> ST =CentroidDict.keySet();
            //System.out.println(ST);
            Integer[] ST2 = ST.toArray(new Integer[ST.size()]);
            
            Integer[] Result = Sort(ST2);
            //System.out.println(Arrays.toString(Result));
            for (int i=0;i<Result.length;i++){
                String r1 = Integer.toString(ClusterDict.get(Result[i]).length);
                String r2 = Double.toString(CentroidDict.get(Result[i]).x());
                String r3 = Double.toString(CentroidDict.get(Result[i]).y());
                System.out.println(r1+"" ""+r2+"" ""+r3);
            }
                  
        }
    }
    
}

