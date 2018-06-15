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
        String number;
        double xc,yc;
//        int capacity;
//        Point2D[] cpts;
        Point2D center;
        
        public Cluster(){          
            xc = 0;
            yc = 0;
            size = 0;
        }
        
        public void SetNum(int Num){
            number = Integer.toString(Num)+"","";
        }
//        private void Combine (Point2D[] pts){
//            if(0 != size){
//            Point2D [] temp = new Point2D[size];
//            temp = cpts;
//            int L1 = temp.length + pts.length;
//            cpts = new Point2D[L1];
//            for(int i = 0; i < L1; i++){
//                if(i < (temp.length))
//                    cpts[i] = temp[i];
//                else
//                    cpts[i] = pts[i-temp.length];
//                }            
//            }
//            else{
//                cpts = pts;
//            }
//        }
        
//        public boolean SameClut (Point2D p1, Point2D p2){
//            boolean f1 = false, f2 = false;
//            for(int i = 0; i < cpts.length; i++){
//                if(f1&&f2)  break;                                
//                if(cpts[i].equals(p1)) f1 = true;
//                if(cpts[i].equals(p2)) f2 = true;                           
//            }
//            if(f1&&f2)
//                return true;
//            else
//                return false;
//        }
//        
        public void InP2D(Point2D p){
            xc = (xc*size + p.x())/(size + 1);           
            yc = (yc*size + p.y())/(size + 1);            
            center = new Point2D(xc,yc);
//            Point2D [] pts = new Point2D [1];
//            this.Combine(pts);
            size += 1;           
        }
        
        public void InClus(Cluster that){
            this.xc = (this.xc*this.size + that.xc*that.size)/(this.size+that.size);
            this.yc = (this.yc*this.size + that.yc*that.size)/(this.size+that.size);;
            this.center = new Point2D(xc, yc);
            this.number = (this.number + that.number).replace(""null"", """");
            
//            this.Combine(that.cpts);
            this.size = this.size + that.size;
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
        Cluster c1, c2;
        
       public Distance(Cluster c1, Cluster c2){
           this.c1 = c1;
           this.c2 = c2;
           dis = this.c1.center.distanceTo(this.c2.center);
       }
       
        public int compareTo(Distance that){
            if(this.dis > that.dis)
                return 1;
            else if(this.dis == that.dis)
                return 0;
            else 
                return -1;
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
                Cluts[idx].InP2D(points[idx]);
                Cluts[idx].SetNum(idx++);
            }
            
            MinPQ<Distance> pq = new MinPQ<Distance>();
            MinPQ<Distance> pqdis = new MinPQ<Distance>();
             Distance[] Dp = new Distance[N*(N-1)/2];
             int iDp = 0;
             
             for(int i = 0; i < N; i++){
                 for(int j = i+1;j < N; j++){
                     Dp[iDp] = new Distance(Cluts[i],Cluts[j]);
                     pq.insert(Dp[iDp]);
                     pqdis.insert(Dp[iDp++]);
                 }
             }       
             
             while(N > 3){
                 Distance Del = pq.delMin();
                 Cluster C1 = new Cluster();
                 C1.InClus(Del.c1);
                 C1.InClus(Del.c2);
                 Distance D1;
                 MinPQ<Distance> pq1 = new MinPQ<Distance>();
                 int idx1 = 0;
                 Cluster[] CL1 = new Cluster[N-1];
                 CL1[idx1++] = C1;                                 
                 // Delete PQ-----------
                while(pq.size() != 0){
                    D1 = pq.delMin();                  
                    boolean flag1, flag2;
                    flag1 = (D1.c1.center.equals(Del.c1.center))||(D1.c1.center.equals(Del.c2.center));
                    flag2 = (D1.c2.center.equals(Del.c1.center))||(D1.c2.center.equals(Del.c2.center));
                    if((!flag1)&&(!flag2))
                        pq1.insert(D1);
                }
                pq = pq1;
                                                           
                 for(int i = 0; i < Cluts.length; i++){
                    boolean flag3;                   
                    flag3 = (Cluts[i].center.equals(Del.c1.center))||(Cluts[i].center.equals(Del.c2.center));
                    if(!flag3){
                        CL1[idx1++] = Cluts[i];
                    }
                 }
                 
                 Cluts = CL1;
                 for(int i = 1; i < Cluts.length; i++){
                     Distance dnew = new Distance(Cluts[0], Cluts[i]);
                     pq.insert(dnew);
                 }               
                 N = N -1;                
             }
                           
             Arrays.sort(Cluts);
             for (int i = 0; i < Cluts.length; i++){
                 int idf = Cluts.length-i-1;
                 System.out.print(Cluts[idf].size +""\t""+ String.format(""%.2f"", Cluts[idf].center.x())+""\t""+ String.format(""%.2f"", Cluts[idf].center.y()) + ""\n"");
             }
             
//             String[] A = Cluts[0].number.split("","");
//             System.out.print(A+""\n"");
//              System.out.print(Cluts[0].number+""\n"");
//              String[] A = Cluts[0].number.split("","");
//              for(int i=0; i<A.length; i++)
//                  System.out.print(A[i]+""\n"");
             
              MinPQ<Double> pqf = new MinPQ<Double>();
              String[] A = Cluts[0].number.split("","");
              if(N > 1){
              String[] B = Cluts[1].number.split("","");
              for(int i=0; i<A.length; i++)
                for(int j=0; j<B.length; j++)
                  pqf.insert(points[Integer.parseInt(A[i])].distanceTo(points[Integer.parseInt(B[j])]));
              if(3 == N){
                  String[] C = Cluts[2].number.split("","");
                  for(int i=0; i<A.length; i++)
                    for(int j=0; j<C.length; j++)
                        pqf.insert(points[Integer.parseInt(A[i])].distanceTo(points[Integer.parseInt(C[j])]));
                   
                  for(int i=0; i<B.length; i++)
                    for(int j=0; j<C.length; j++)
                        pqf.insert(points[Integer.parseInt(B[i])].distanceTo(points[Integer.parseInt(C[j])]));
              }
              
              System.out.print(String.format(""%.2f"", pqf.delMin())+""\n"");  
              }
              if(1 == N)
                  System.out.print(0+""\n"");
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

