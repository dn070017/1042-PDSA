import java.awt.Color;
import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;


    
//******************************* 庫拉斯特 ************************************\\
//--------------------------------structor------------------------------------\\    
    class Cluster implements Comparable <Cluster>{
        
        public double cluX;
        public double cluY;
        public int sit = 0;
        int size = 1;
        
        public Cluster(String x, String y){
            this.cluX = Double.parseDouble(x);
            this.cluY = Double.parseDouble(y);
        }
                
        public int compareTo(Cluster that) {
           if(this.size>that.size)return +1;
           else if(this.size<that.size)return -1;
           else return 0;
        }
        
    }
    
//********************************* 佩兒***************************************\\
//--------------------------------FUNCTION------------------------------------\\
    class pair implements Comparable <pair>{
        
        public Cluster A;
        public Cluster B;
        public int N;
        public double dis ;
        
        
        public pair(Cluster O,Cluster P){
            this.A = O;
            this.B = P;
            return;
        }
        
        void setDIS (){
            dis = Math.sqrt((A.cluX-B.cluX)*(A.cluX-B.cluX) + (A.cluY-B.cluY)*(A.cluY-B.cluY));
        }
                        
        public int compareTo(pair that) {
           if(this.dis>that.dis)return +1;
           else if(this.dis<that.dis)return -1;
           else return 0;
        }
        
    }
//******************************** 麵方選 *************************************\\
//--------------------------------FUNCTION------------------------------------\\
   public class Clustering{ 
    public static void main(String[] args) throws Exception{
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            int N = Integer.valueOf(br.readLine()); // this N is total clu number
            int count = N;
            Cluster[] set = new Cluster[2*N-3];
            MaxPQ<Cluster> PQ = new MaxPQ<>();
            
            
            for(int i=0 ; i<N ; i++){ // INITIAL set point to array
               String[] sep = br.readLine().split("" "");
               Cluster temp = new Cluster(sep[0],sep[1]);
               set[i] =temp;
            /*   StdDraw.setPenRadius(set[i].size*0.01);
               StdDraw.setPenColor(StdDraw.RED);
               StdDraw.point(set[i].cluX,set[i].cluY);  */
            }
                       
            while(count < 2*N-3){
                  Merge(count,set);
                  count++;
            }
          // for(int i=0; i<count;i++){
          //  System.out.println(set[i].sit);}
            //System.out.println(N);
          //  System.out.println(set[N].cluX+"" ""+set[N].cluY);
          
          for(int i=2*N-6; i<2*N-3;i++){              
                 PQ.insert(set[i]);              
          }
          
          for(int i=0; i<3;i++){
              Cluster T = PQ.delMax();
              System.out.println(T.size+"" ""+String.format(""%.2f"", T.cluX)+"" ""+String.format(""%.2f"", T.cluY));
          }
          
        }
    }
//****************************************************************************\\
//*******************************EXTRA FUNTION********************************\\
public static Cluster Fuse(Cluster a,Cluster b){
       String X = Double.toString(a.cluX+(b.cluX-a.cluX)*(b.size)/(a.size+b.size));
       String Y = Double.toString(a.cluY+(b.cluY-a.cluY)*(b.size)/(a.size+b.size));
       Cluster C = new Cluster(X,Y); 
       C.size = a.size+b.size;       
       return C;
}
public static pair Merge(int N,Cluster[] O){        
    
       // Stack<Integer> cnxpoint = new Stack<Integer>();
        MaxPQ<pair> pq = new MaxPQ<>();
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                if( O[i].sit==0 && O[j].sit==0){
                pair tempP = new pair(O[i],O[j]);
                tempP.setDIS();
                pq.insert(tempP);
                if(pq.size()>1){pq.delMax();}
               }               
           }
        }
        pair targ = pq.delMax();  //------- prcessing to done fuse 2 cluster
        targ.setDIS();
        targ.A.sit = 1;
        targ.B.sit = 1;
      /*  System.out.println(""The closest distance is :\t""+targ.dis);
        System.out.println(""The cluster a is :\t\t""+targ.A.cluX+"" ""+targ.A.cluY);
        System.out.println(""The cluster b is :\t\t""+targ.B.cluX+"" ""+targ.B.cluY);*/
      /*  StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(targ.A.cluX, targ.A.cluY, targ.B.cluX, targ.B.cluY);*/
        
        O[N] = Fuse(targ.A,targ.B);
        return targ;
    }
//****************************************************************************\\
    
}
    
