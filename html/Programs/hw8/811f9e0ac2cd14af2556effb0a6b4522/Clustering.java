/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

/**
 *
 * @author stonebreaker
 */
public class Clustering {

    /**
     * @param args the command line arguments
     */
    private static class Cluster implements Comparable<Cluster>{
        int size;
        double xc,yc;
//        int capacity;
        Point2D[] cpts;
        Point2D center;
        
        public Cluster(){
//            capacity = 10;
            cpts = new Point2D[10];
            xc = 0;
            yc = 0;
            size = 0;
            center = new Point2D(xc,yc);
        }
        
        private void resize(int n){
            Point2D[] temp = new Point2D[n];
            for(int i = 0; i < size; i++) temp[i] = cpts[i];
            cpts = temp;    
        }
        
        public void Insert(Point2D p){
            if(size > cpts.length) resize(2*cpts.length);
            xc = xc*size + p.x();
            xc = xc/(size + 1);
            yc = yc*size + p.y();
            yc = yc/(size + 1);
            center = new Point2D(xc,yc);
            cpts[size++] = p;
        }
        
        public int compareTo(Cluster that){
            if(this.size >= that.size)
                return 1;
            else 
                return -1;
        }
        
 
    }
    private static class Distance implements Comparable<Distance>{
        double dis;
        Point2D p1, p2;
        
       public Distance(Point2D p1, Point2D p2){
           this.p1 = p1;
           this.p2 = p2;
           dis = p1.distanceTo(p2);
       }
       
       
        public int compareTo(Distance that){
            if(this.dis > that.dis)
                return 1;
            else if(this.dis == that.dis)
                return 0;
            else 
                return -1;
        }
        
        public boolean equals(Distance that){
            if(that == this)
                return true;
            if(that == null)
                return false;
            if(that.getClass() != this.getClass())
                return false;
            if(that.dis == this.dis)
                if(that.p1.equals(this.p1)||that.p1.equals(this.p2))
                    if(that.p2.equals(this.p1)||that.p2.equals(this.p2))
                        return true;
            return false;
        }
       
    }
   
    
    public static void main(String[] args) throws Exception{
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            int idx = 0;
            int N = Integer.parseInt(br.readLine());
            
            Point2D[] points = new Point2D[N];
            Cluster[] Cluts = new Cluster[N];
          
            for(String in = br.readLine(); in != null; in = br.readLine()){
                String[] pts = in.split("" "");
                points[idx] = new Point2D(Double.parseDouble(pts[0]),Double.parseDouble(pts[1]));
                Cluts[idx] = new Cluster();
                Cluts[idx].Insert(points[idx]);
                idx++;
            }
            
             MinPQ<Distance> pq = new MinPQ<Distance>();
             Distance[] Dp = new Distance[N*(N-1)/2];
             int iDp = 0;
             
             for(int i = 0; i < N; i++){
                 for(int j = i+1;j < N; j++){
                     Dp[iDp] = new Distance(points[i],points[j]);
                     pq.insert(Dp[iDp++]);
                 }
             }       
             
//              while(pq.size() != 0){
//                    Distance Dtemp = pq.delMin();
//                     System.out.print(Dtemp.p1+""\t""+Dtemp.p2+""\n"");
//                }
             
             while(N > 3){
                 Distance Del = pq.delMin();
                 Cluster C1 = new Cluster();
                 C1.Insert(Del.p1);
                 C1.Insert(Del.p2);
                 Distance D1;
                 MinPQ<Distance> pq1 = new MinPQ<Distance>();
                 // Delete PQ-----------
                while(pq.size() != 0){
                    D1 = pq.delMin();
                    boolean flag1, flag2;
                    flag1 = (D1.p1.equals(Del.p1))||(D1.p1.equals(Del.p2));
                    flag2 = (D1.p2.equals(Del.p1))||D1.p2.equals(Del.p2);
                    if((!flag1)&&(!flag2))
                        pq1.insert(D1);
                }
                pq = pq1;
//                while(pq1.size() != 0){
//                    Distance Dtemp1 = pq1.delMin();
//                     System.out.print(Dtemp1.p1+""\t""+Dtemp1.p2+""\n"");
//                }
                
                 
                 //----------------------                 
                 int idx1 = 0;
                 Cluster[] CL1 = new Cluster[N-1];
                 CL1[idx1++] = C1;
//                 System.out.print(CL1[idx1-1].center+""\n"");
//                 System.out.print(CL1.length+""\n"");
//                 System.out.print(Cluts.length+""\n"");
                 
                 for(int i = 0; i < Cluts.length; i++){
                    boolean flag3;                   
                    flag3 = (Cluts[i].center.equals(Del.p1))||(Cluts[i].center.equals(Del.p2));
//                    System.out.print(flag3+""\n"");
//                    System.out.print(i+""\n"");
                    if(!flag3){
                        CL1[idx1++] = Cluts[i];
//                        System.out.print(idx1+""\n"");
//                        System.out.print(CL1[idx1-1].center+""\n"");
                    }
                 }
                 Cluts = CL1;
                 for(int i = 1; i < CL1.length; i++){
                     Distance dnew = new Distance(CL1[0].center, CL1[i].center);
                     pq.insert(dnew);
                 }
                 
//                 while(pq.size() != 0){
//                    Distance Dtemp1 = pq.delMin();
//                     System.out.print(Dtemp1.p1+""\t""+Dtemp1.p2+""\n"");
//                }
                 
                 N = N -1;
             }
//                           
             Arrays.sort(Cluts);
             for (int i = 0; i < Cluts.length; i++){
                 int idf = Cluts.length-i-1;
                 System.out.print(Cluts[idf].size + ""\t"" + Cluts[idf].center.x()+ ""\t"" + Cluts[idf].center.y() + ""\n"");
             }
////                 
             
             
      // Test----------------------------------------------------------------
//            Point2D p1 = new Point2D(0.2, 0.2);
//            Point2D p2 = new Point2D(0.2, 0.1);
//            Point2D p3 = new Point2D(0.2, 0.2);
//            Point2D p4 = new Point2D(0.2, 0.4);
//            Distance d1 = new Distance(p1,p2);
//            Distance d2 = new Distance(p3,p4);
//            boolean test1;
//            test1 = (d1.p1.equals(d2.p2))||(d1.p2.equals(d2.p2));
//            if(!test1)
//            System.out.print(test1);
         
      //------------------------------------------------------------------------
        }
    }
}

