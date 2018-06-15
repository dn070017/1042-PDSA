import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;


public class Clustering {
    //public class Hand implements Comparable<Hand> {
    private static class cluster implements Comparable<cluster> {
       private int id;
       private double rx, ry; //clustering position
       private int size;
       private double dis;//the neareast dis
       private int nid;//the nearest j
       private double ox, oy; //original position
       // public static final Comparator<Card> SUIT_ORDER = new SuitOrder();
       public static final Comparator<cluster> DIS_ORDER = new DisOrder();
       public static final Comparator<cluster> OIS_ORDER = new OisOrder();
       public static final Comparator<cluster> ID_ORDER = new IdOrder();
       //    private static class SuitOrder implements Comparator<Card> {
       private static class DisOrder implements Comparator<cluster> {
          public int compare(cluster c1, cluster c2) {
            // complete this function so the Card can be sorted according to the suit
            if(c1.size==0&&c2.size==0){return 0;}
            if(c1.size==0){return -1;}
            if(c2.size==0){return 1;}
            else if (c1.dis>c2.dis){return 1;}
            else if (c1.dis<c2.dis){return -1;}
            else{return 0;}}
       }
       
       private static class OisOrder implements Comparator<cluster> {
          public int compare(cluster c1, cluster c2) {
            if (c1.dis>c2.dis){return 1;}
            else if (c1.dis<c2.dis){return -1;}
            else{return 0;}}
       }
       
       private static class IdOrder implements Comparator<cluster> {
          public int compare(cluster c1, cluster c2) {
            // complete this function so the Card can be sorted according to the suit
            if (c1.id>c2.id){return 1;}
            else if (c1.id<c2.id){return -1;}
            else{return 0;}}
       }
       
       public cluster(int id,double rx, double ry){
          this.rx= rx;
          this.ry= ry;
          this.size=1;
          this.id=id;
          this.ox=rx;
          this.oy=ry;
       }
       //distance without weighting
       public double ndis(cluster that){
          double dx  = that.rx - this.rx;
          double dy  = that.ry - this.ry;
          double dd  = Math.sqrt(dx*dx+dy*dy);
          return dd;
       }
       
       public double odis(cluster that){
          double dx  = that.ox - this.ox;
          double dy  = that.oy - this.oy;
          double dd  = Math.sqrt(dx*dx+dy*dy);
          return dd;
       }
       //centroid by weighting
       public double cx(cluster that){
         double x = Math.abs(that.rx*that.size+this.rx*this.size)/(that.size+this.size);
         return x;
       }
       public double cy(cluster that){
         double y = Math.abs(that.ry*that.size+this.ry*this.size)/(that.size+this.size);
         return y;
       }
       
       public int compareTo(cluster that) {
          if(this.size>that.size){return 1;}
          else if(this.size<that.size){return -1;}
          else {
              if(this.rx<that.rx){return 1;}
              else if(this.rx>that.rx){return -1;}
              else{
                  if(this.ry<that.ry){return 1;}
                  else if(this.ry>that.ry){return -1;}
                  else {return 0;}
              }
          }
       }}
    
    
    
    
  public static void main(String[] args) throws Exception {
        //""input14.txt""  args[0]
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int N = Integer.parseInt(header[0]);
            int ON=N;
            WeightedQuickUnionUF classes = new WeightedQuickUnionUF(ON);            
            int idx=0;
            Clustering.cluster[] fullp = new Clustering.cluster[N];
                for(String in = br.readLine(); in != null; in = br.readLine()) {
                String[] p = in.split(""\\s+"");
                fullp[idx] = new Clustering.cluster(idx,Double.parseDouble(p[0]),Double.parseDouble(p[1]));   
                idx++;
                }
            //count original neareast distance
            double maxdis = 0;
            for(int i =0;i<ON;i++){
                  for(int j=0;j<ON;j++){
                         if (i!=j&&fullp[i].ndis(fullp[j])>maxdis){maxdis=fullp[i].ndis(fullp[j]);}}         
            } 
            for(int i =0;i<ON;i++){
                  fullp[i].dis=maxdis;
                  for(int j=0;j<ON;j++){
                         if (i!=j&&fullp[i].ndis(fullp[j])<fullp[i].dis){
                             fullp[i].dis=fullp[i].ndis(fullp[j]);
                             fullp[i].nid=j;
                         }}         
            } 
            MaxPQ<cluster> fclus = new MaxPQ();
            while(true){
                if(N==3){
                    Arrays.sort(fullp,cluster.DIS_ORDER);
                    for (int i =ON-N;i<ON;i++){ 
                       fclus.insert(fullp[i]);
                    }
                    StdOut.println(fclus.max().size+"" ""+String.format(""%.2f"",fclus.max().rx)+"" ""+String.format(""%.2f"",fclus.max().ry));
                    fclus.delMax();
                    StdOut.println(fclus.max().size+"" ""+String.format(""%.2f"",fclus.max().rx)+"" ""+String.format(""%.2f"",fclus.max().ry));
                    fclus.delMax();
                    StdOut.println(fclus.max().size+"" ""+String.format(""%.2f"",fclus.max().rx)+"" ""+String.format(""%.2f"",fclus.max().ry));
                    break;
                }
                if(N==2){
                    fclus.insert(fullp[0]);
                    fclus.insert(fullp[1]);
                    StdOut.println(fclus.max().size+"" ""+String.format(""%.2f"",fclus.max().rx)+"" ""+String.format(""%.2f"",fclus.max().ry));
                    fclus.delMax();
                    StdOut.println(fclus.max().size+"" ""+String.format(""%.2f"",fclus.max().rx)+"" ""+String.format(""%.2f"",fclus.max().ry));
                    fclus.delMax();
                    break;
                }
                if(N==1){
                    StdOut.println(1+"" ""+String.format(""%.2f"",fullp[0].rx)+"" ""+String.format(""%.2f"",fullp[0].ry));
                    break;
                }
            //distance count    
            Arrays.sort(fullp,cluster.DIS_ORDER);
            int minid=fullp[ON-N].id;
            int minid2=fullp[ON-N].nid;
            //j改成新的point
            
            Arrays.sort(fullp,cluster.ID_ORDER);
            fullp[minid2].rx=fullp[minid].cx(fullp[minid2]);
            fullp[minid2].ry=fullp[minid].cy(fullp[minid2]);
            fullp[minid2].size=fullp[minid2].size+fullp[minid].size;
            classes.union(minid,minid2);
            fullp[minid].size=0;//die

            fullp[minid2].dis=maxdis;
            for (int i=0;i<ON;i++){
               if(fullp[i].size > 0 && minid2!=i){//update the new cluster 
                   if (fullp[i].ndis(fullp[minid2])<fullp[minid2].dis){
                       fullp[minid2].dis=fullp[i].ndis(fullp[minid2]);
                       fullp[minid2].nid=fullp[i].id;
                   }
                   if (fullp[i].nid==minid||fullp[i].nid==minid2){//once nid is dead
                      fullp[i].dis=maxdis;
                      for(int j=0;j<ON;j++){
                         //StdOut.print((i!=j) && (fullp[j].size) > 0 && (fullp[i].ndis(fullp[j])<fullp[i].dis));
                         if ((i!=j) && (fullp[j].size) > 0 && (fullp[i].ndis(fullp[j])<fullp[i].dis)){
                             fullp[i].dis=fullp[i].ndis(fullp[j]);
                             fullp[i].nid=fullp[j].id;
                   }}}
                   if(fullp[i].ndis(fullp[minid2])<fullp[i].dis)//once is new cluster is closer 
                        {fullp[i].dis=fullp[i].ndis(fullp[minid2]);}
               }}
            N--;
            }
            Arrays.sort(fullp,cluster.ID_ORDER);
            for(int i =0;i<ON;i++){
                   fullp[i].dis=maxdis;
                for(int j=0;j<ON;j++){//i.nid is j != j.nid is i
                  if(classes.find(i)!=classes.find(j)){
                      if(fullp[i].odis(fullp[j])<fullp[i].dis) fullp[i].dis=fullp[i].odis(fullp[j]);
            }}}
            if(ON==1){fullp[0].dis=0;}
            Arrays.sort(fullp,cluster.OIS_ORDER);
            StdOut.print(String.format(""%.2f"",fullp[0].dis));
    }
}}

