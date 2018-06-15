import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Lab304
 */
 //public class Pair{}
class Pair implements Comparable<Pair>{
     private Clustering a1;
     private Clustering a2;
     private double distance ;
     public  Pair(Clustering a ,Clustering b){
         this.a1=a;
         this.a2=b;
        // distance =a1.getpoint().distanceTo(a2.getpoint());
         
     }
     public Clustering getcluster1(){
     return this.a1;
     }
      public Clustering getcluster2(){
     return this.a2;
     }
     public double getdistance(){
         this.distance =this.a1.getpoint().distanceTo(this.a2.getpoint());
     return this.distance;
     }
      //   (Clustering this ,Clustering that)
   //  {double distance =this.getpoint().distanceTo(that.getpoint());
     //}
      public int compareTo(Pair that) {
          if(this.getdistance()>that.getdistance()){return 1;}
          if(this.getdistance()<that.getdistance()){return -1;}
          else return 0;
     }
 }
public class Clustering implements Comparable<Clustering>{
    
    private Point2D point; 
    int uf;
    int n=1;
     public Clustering(Point2D a ,int uf_num){
      this.point = a;
      this.uf=uf_num;
        return;
    }
     public Point2D getpoint()
     {return this.point;
     
     }     
     public int size()
     {return n;
     
     }     
  
          public int compareTo(Clustering that) {
          if(this.size()>that.size()){return 1;}
          if(this.size()<that.size()){return -1;}
          else return 0;
     }
    
/*     class Pair{
     private Clustering a1;
     private Clustering a2;
     private double distance ;
     public  Pair(Clustering a ,Clustering b){
         this.a1=a;
         this.a2=b;
        // distance =a1.getpoint().distanceTo(a2.getpoint());
         
     }
     public double getdistance(){
         distance =this.a1.getpoint().distanceTo(this.a2.getpoint());
     return distance;
     }
      //   (Clustering this ,Clustering that)
   //  {double distance =this.getpoint().distanceTo(that.getpoint());
     //}
     }*/

 public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
      
               String[] data = br.readLine().split("" "");
              int num = Integer.parseInt(data[0]);
              int num_k=num;
              //Point2D[] a=new Point2D[num];
              MinPQ minpq=new MinPQ();
              MaxPQ maxpq=new MaxPQ();
              Clustering[] cluster=new Clustering[2*num]; 
              UF uf = new UF(num*2) ;
               final long startTime = System.nanoTime();
         for(int i=0;i<num;i++){
            String[] data1 = br.readLine().split("" "");
         //   a[i]=new Point2D(Double.parseDouble(data1[0]),Double.parseDouble(data1[1]));
            cluster[i]=new Clustering(new Point2D(Double.parseDouble(data1[0]),Double.parseDouble(data1[1])),i);
          //  System.out.println(a[i]);
       //   System.out.println(cluster[i].getpoint());
          //System.out.println(cluster[i].size());
         }
         //cluster[5].changesize(cluster[6]);
         //cluster[10].changesize(cluster[5]);
         //System.out.println(cluster[10].size());
       /*  for(int i=0;i<num;i++){   
               
             StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
               StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
              StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i) );
               }*/
       double []f=new double[num];
       double []g=new double[num];
         int k=0;
         int fail_k=0;
         int time=0;
         Pair[] pair=new Pair[num*(num-1)/5] ;
     if(num>1){    
         for(int i=0;i<num;i++)
        {for(int j=i+1;j<num;j++)
           {  if(cluster[i].point.distanceTo(cluster[j].point)<0.3)
           {pair[k]=new Pair(cluster[i],cluster[j]);
               minpq.insert(pair[k]);
                   //System.out.println(pair[k].getdistance());
                   
               k=k+1;}
           }
        }
         //Pair pairnow1=(Pair)minpq.min();
       //  System.out.println(pairnow1.getdistance());
         Clustering[] fail_cluster=new Clustering[num*2];
   
 if(num>3){
while(num_k>3){ 
         Pair pairnow=(Pair)minpq.delMin();
   search:{
         for(int i=0;i<fail_k;i++)
         {if( pairnow.getcluster1().equals(fail_cluster[i])||pairnow.getcluster2().equals(fail_cluster[i]))
         { // System.out.println(""fail_k"");
             break search;}
         }
          time=time+1;
    //      System.out.println(pairnow.getdistance());
          
          
     //      System.out.println(pairnow.getcluster1().getpoint());
     //      System.out.println(pairnow.getcluster2().getpoint());
           
           uf.union(pairnow.getcluster1().uf, pairnow.getcluster2().uf);
           
           double new_x;
           double new_y ;
           new_x=(pairnow.getcluster1().size()*pairnow.getcluster1().getpoint().x()+pairnow.getcluster2().size()*pairnow.getcluster2().getpoint().x())/(pairnow.getcluster1().size()+pairnow.getcluster2().size()) ;
           new_y=(pairnow.getcluster1().size()*pairnow.getcluster1().getpoint().y()+pairnow.getcluster2().size()*pairnow.getcluster2().getpoint().y())/(pairnow.getcluster1().size()+pairnow.getcluster2().size()) ;
          Point2D b=new Point2D(new_x,new_y);
     //      System.out.println(b);
          Clustering new_cluster=new Clustering(b,num+time-1);
          new_cluster.n=pairnow.getcluster2().size()+pairnow.getcluster1().size();
       //   System.out.println(new_cluster.n);
          pairnow.getcluster1().n=0;
          pairnow.getcluster2().n=0;
        
          for(int j=0;j<time+num-1;j++){   
              //if( !(pairnow.getcluster1().equals(cluster[j])||pairnow.getcluster2().equals(cluster[j])))
               if(cluster[j].n!=0)
              { Pair new_pair=new Pair(new_cluster,cluster[j]);
                 minpq.insert(new_pair);}
              
              
          }
          
          cluster[num+time-1]=new_cluster;
          uf.union(pairnow.getcluster1().uf, cluster[num+time-1].uf );
           fail_cluster[fail_k]=pairnow.getcluster1();
          fail_k=fail_k+1;
          fail_cluster[fail_k]=pairnow.getcluster2();
          fail_k=fail_k+1;
          num_k=num_k-1;
       //   System.out.println(num_k);
   }
 }
int[] find=new int[3];
MinPQ minlast=new MinPQ();
      for(int i=0;i<num+time;i++)
      {maxpq.insert(cluster[i]);}
      for(int i=0;i<3;i++)
      {Clustering clusterlast=(Clustering)maxpq.delMax();
       int count=clusterlast.size();
       double last_x=clusterlast.point.x();
       double last_y=clusterlast.point.y();
       find[i]=clusterlast.uf;
       System.out.print(count);
       System.out.print("" "");
       System.out.printf(""%.2f"" ,last_x);
       System.out.print("" "");
       System.out.printf(""%.2f"",last_y);
       System.out.println();
      // System.out.println(find[i]);
      }
      
      for(int w=0;w<num;w++)
        {for(int q=0;q<num;q++){
            if(uf.connected(find[0],cluster[w].uf))
            {if(uf.connected(find[1],cluster[q].uf)||uf.connected(find[2],cluster[q].uf))
               { double last_d=cluster[w].point.distanceTo(cluster[q].point);
               minlast.insert(last_d);
               }
            }
        }
      }
      for(int w=0;w<num;w++)
        {for(int q=0;q<num;q++){
            if(uf.connected(find[1],cluster[w].uf))
            {if(uf.connected(find[2],cluster[q].uf))
               { double last_d=cluster[w].point.distanceTo(cluster[q].point);
               minlast.insert(last_d);
               }
            }
        }
      }
      double s_d= (double)minlast.delMin();
      System.out.printf(""%.2f"",s_d);
       
 }
 if(num<=3){
     for(int e=0;e<num;e++){
       System.out.print(""1"");
       System.out.print("" "");
       System.out.printf(""%.2f"",cluster[e].point.x());
       System.out.print("" "");
       System.out.printf(""%.2f"" ,cluster[e].point.y());
       System.out.println();
    
     }
     MinPQ minlast=new MinPQ();
       for(int w=0;w<num;w++)
      {for(int q=w+1;q<num;q++){
     
      double last_d=cluster[w].point.distanceTo(cluster[q].point);
               minlast.insert(last_d);
        }
        }
       double s_d= (double)minlast.delMin();
      System.out.printf(""%.2f"",s_d);
       
 }
 
       /*StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
               StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i) );
               
         
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
              StdDraw.text(a[i].x(), a[i].y()+0.03,Integer.toString(i) );*/
     }
     if(num==1)
     {System.out.print(cluster[0].n);
       System.out.print("" "");
       System.out.printf(""%.2f"",cluster[0].point.x());
       System.out.print("" "");
       System.out.printf(""%.2f"" ,cluster[0].point.y());
       System.out.println();
       System.out.println(""0.00"");
     }

         //  final long duration = (System.nanoTime() - startTime);
   // System.out.println();       
//System.out.println(duration);
}
  
        

 }
}
    




