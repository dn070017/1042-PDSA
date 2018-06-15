
import java.awt.Color;
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
 * @author Dennis
 */


public class Clustering implements Comparable <Clustering> {

    private Point2D p1;
    private Point2D p2;
    private int num1;
    private int num2;
    private int union1;
    private int union2;    
    
    public Clustering(Point2D p1,Point2D p2,int num1,int num2,int union1,int union2){
        this.p1 = p1;
        this.p2 = p2;
        this.num1 =num1;
        this.num2 =num2;
        this.union1 =union1;
        this.union2 =union2;
    }
    public int compareTo(Clustering that) {
        int turn;
        double thisdis,thatdis;
        thisdis=this.p1.distanceTo(this.p2);
        thatdis=that.p1.distanceTo(that.p2);
        if (thisdis>thatdis){turn=1;}
        else if(thisdis<thatdis){turn=-1;}
        else{turn=0;}
        return turn;
    }
        public double Key() {
        return this.p1.distanceTo(this.p2);
    }

    public Point2D getCenter() {
        int allnumber = this.num1+this.num2;
        double thiscenterx,thiscentery;
        thiscenterx=(this.p1.x()*num1+this.p2.x()*num2)/allnumber;
        thiscentery=(this.p1.y()*num1+this.p2.y()*num2)/allnumber;
        Point2D p3=new Point2D(thiscenterx,thiscentery);
        return p3;
    }
    public int getunion1(){
        return  this.union1;
    } 
    
     public int getunion2(){
        return  this.union2;
    } 
    
    public int getPointsNumber(){
        
        return this.num1+this.num2;
    }
 private static class setPoint implements Comparable <setPoint> { 
     int number;
     double x;
     double y;
     
     public setPoint(int number,double x, double y) { 
            this.number=number;
            this.x = x; 
            this.y = y; 
        }
     public int compareTo(setPoint that) {
        int turnans=0;
        if(this.number>that.number){turnans=1;}
        else if(this.number<that.number){turnans=-1;}
        else{
                 if(this.x>that.x){turnans=1;}
                 else if(this.x<that.x){turnans=-1;}
                 else{
                     if(this.y>that.y){turnans=1;}
                     else if(this.y<that.y){turnans=-1;}
                     else{turnans=0;}
                 }
        }
        return turnans;
     }
        // .... 
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
 
     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
         
        String[] Num = br.readLine().split("" "");
        int N = Integer.parseInt(Num[0]);
        Point2D[] points = new Point2D[N];
        Point2D[] centerpoints = new Point2D[N];
        Point2D[] noconnectpoints = new Point2D[N];
        int [] indexcenterpoints = new int[N];
        int [] tempindexcenterpoints = new int[N];
        int [] unioncenterpoints = new int[N];
        int [] indexcenterpoints2or1 = new int[N];
        int [] indexpoints = new int[N];
        UF indexall =new UF(N);
        Clustering[] cluster = new Clustering[N*3];

        
//        System.out.println(N);
        
        double pointx0=0;
        double pointy0=0;
        MinPQ<Clustering> pqueue =new MinPQ<Clustering>();
        MinPQ<Clustering> finalpqueue =new MinPQ<Clustering>();
        MinPQ<Clustering> Allpointpqueue =new MinPQ<Clustering>();

        
        for(int i=0;i<N;i++){

           String[] d = br.readLine().split("" "");
           double pointx=Double.parseDouble(d[0])-pointx0;
           double pointy=Double.parseDouble(d[1])-pointy0;
           points[i]=new Point2D(pointx,pointy);
           indexpoints[i]=1;
//           System.out.println(indexpoints[i]);
//          System.out.println(points[i].x()+""\t""+points[i].y());
        }


        int k=0;
        for (int i=0;i<N;i++){

            Clustering[] findsmallcluster = new Clustering[N*3];
            MinPQ<Clustering> findsmallpqueue =new MinPQ<Clustering>();
            int t=0;
                for(int j=i+1;j<N;j++){

                   findsmallcluster [t]=new Clustering(points[i],points[j],1,1,i,j);
                   findsmallpqueue.insert(findsmallcluster[t]);
                   t=t+1;

                 if(j==N-1){
                    cluster[k] =new Clustering(points[i],points[j],1,1,i,j);
                    pqueue.insert(findsmallpqueue.delMin());
                    k=k+1;
                    t=0;
                    }
                }
            }

          int Alldata=pqueue.size();

        
        
        int nn=0;
        int t=0;
        Clustering[] tempcluster2 = new Clustering[N*3];
        Clustering[] tempcluster3 = new Clustering[1];
        
        
        while( !pqueue.isEmpty() ){

            tempcluster3[0]=pqueue.delMin();
            if(nn==0){
                tempcluster2[nn]=tempcluster3[0];
                Allpointpqueue.insert( tempcluster3[0] );
                nn=nn+1;
                
            for(int i=0;i<N;i++){
                 if(tempcluster3[0].p1.equals(points[i]) || tempcluster3[0].p2.equals(points[i]) ){
                        indexpoints[i]=0;
                         }
                   }
            }
            else{
                for(int i=0;i<nn;i++){
                    if( tempcluster3[0].p1.equals(tempcluster2[i].p1)){t=0;break;}
                    else if(tempcluster3[0].p1.equals(tempcluster2[i].p2)){t=0;break;}
                    else if(tempcluster3[0].p2.equals(tempcluster2[i].p1)){t=0;break;}
                    else if(tempcluster3[0].p2.equals(tempcluster2[i].p2)){t=0;break;}
                    else{t=1;}
                }
                   if(t==1){
                         tempcluster2[nn]=tempcluster3[0];
                         Allpointpqueue.insert( tempcluster3[0]);
                         nn=nn+1;
                         t=0;
                         
                         for(int i=0;i<N;i++){
                             if(tempcluster3[0].p1.equals(points[i]) || tempcluster3[0].p2.equals(points[i]) ){
                                 indexpoints[i]=0;
                             }
                         }
                    }
              }
          }
        

        int c=0;
        for(int i=0;i<indexpoints.length;i++ )    // plot no connected points
        {
            int ii =0;
//            System.out.println(indexpoints[i]);
            if(indexpoints[i]==1){
            unioncenterpoints[c]=indexall.find(i);
//            System.out.println(points[i]);
            noconnectpoints[ii]=points[i];
            centerpoints[c]=new Point2D(noconnectpoints[ii].x(), noconnectpoints[ii].y());
            indexcenterpoints2or1[c]=1;       // mark no connected points
            ii=ii+1;
            c=c+1;
            }
        }


        int Allpointpqueuesize=Allpointpqueue.size();
//        System.out.println(Allpointpqueuesize);
       
        int NN = N;
        while(!Allpointpqueue.isEmpty()){  //plot cluster orignal points

        Clustering[] tempcluster4 = new Clustering[1];
        Clustering[] tempcluster7 = new Clustering[1];


        tempcluster4[0]=Allpointpqueue.delMin();
        NN=NN-1;
        
        centerpoints[c]=new Point2D(tempcluster4[0].getCenter().x(), tempcluster4[0].getCenter().y()); // save new center points
        indexcenterpoints2or1[c]=tempcluster4[0].getPointsNumber();
        indexall.union(tempcluster4[0].getunion1(),tempcluster4[0].getunion2());                      //union
        unioncenterpoints[c]=tempcluster4[0].getunion1();
//        StdDraw.text(tempcluster4[0].getCenter().x(),tempcluster4[0].getCenter().y()+0.01,Integer.toString(indexcenterpoints2or1[c]));
        c=c+1;
//        StdDraw.filledCircle(tempcluster4[0].p1.x(), tempcluster4[0].p1.y(),0.005);
//        Thread.currentThread().sleep(10);
//        StdDraw.line(tempcluster4[0].p1.x(),tempcluster4[0].p1.y(),tempcluster4[0].p2.x(), tempcluster4[0].p2.y());
//        Thread.currentThread().sleep(50);
        
        if(Allpointpqueue.isEmpty()){

           MinPQ<Clustering> cneterpointpqueue =new MinPQ<Clustering>();
           MinPQ<Clustering> tempointpqueue =new MinPQ<Clustering>();//find min for each center and no connected point
           Clustering[] tempcluster5 = new Clustering[1];
           
            for(int i=0;i<c;i++){
                for(int j=i+1;j<c;j++){
                    
                    if(indexcenterpoints2or1[i]==1){                                                     // decide 2-1 or 1-2 or 2-2connected
                    tempcluster5[0]=new Clustering(centerpoints[j],centerpoints[i],indexcenterpoints2or1[j],1,unioncenterpoints[j],unioncenterpoints[i]); // if it connects one point        
                    }else if(indexcenterpoints2or1[j]==1){
                    tempcluster5[0]=new Clustering(centerpoints[i],centerpoints[j],indexcenterpoints2or1[i],1,unioncenterpoints[i],unioncenterpoints[j]);  // if it connects one point         
                    }else{
                    tempcluster5[0]=new Clustering(centerpoints[i],centerpoints[j],indexcenterpoints2or1[i],indexcenterpoints2or1[j],unioncenterpoints[j],unioncenterpoints[i]);      
                    }

                    if(tempindexcenterpoints[i]==0 && tempindexcenterpoints[j]==0 && indexcenterpoints[i]==0 && indexcenterpoints[j]==0){ // compare min
                        tempointpqueue.insert(tempcluster5[0]);
                    }

                    }
                 if(i ==c-1 ){     // i is final 

//                              Thread.currentThread().sleep(500);
//                              StdDraw.clear();
                            while(!tempointpqueue.isEmpty()){
                                cneterpointpqueue.insert(tempointpqueue.delMin());
                            }
                            tempcluster7[0]=cneterpointpqueue.delMin();
                            Allpointpqueue.insert(tempcluster7[0]);

                            
                            for(int m=0;m<c;m++){
                                 if(tempcluster7[0].p1.equals(centerpoints[m]) || tempcluster7[0].p2.equals(centerpoints[m])){
                                     indexcenterpoints[m]=1;
                                     tempindexcenterpoints[m]=1;
                                     }
                                 else{
                                     tempindexcenterpoints[m]=0;
                                 }
                            }
                        }
            }

        }
        
        
        if(NN==3){
            int[] finalindex =new int[N];
            MinPQ<Clustering> finalclusterpqueue =new MinPQ<Clustering>();
            MaxPQ<setPoint> ans =new MaxPQ<setPoint>();
            setPoint[] ansstructure =new setPoint[N];
            Clustering[] finalcluster = new Clustering[3*N];
//            StdDraw.clear();
            int countclaster=0;
            int clastercount=0;
                for(int i=0;i<N;i++){
                    
                    if(finalindex[i]==0){
                        int count=0;
                        double centerx =0;
                        double centery =0;
                        for(int j=0;j<N;j++){
                            if(indexall.find(i)==indexall.find(j)){
                                centerx=centerx+points[j].x();
                                centery=centery+points[j].y();
                                finalindex[j]=1;
                                count=count+1;
                            }else{
                                finalcluster[clastercount]=new Clustering(points[i],points[j],1,1,1,1);
                                finalclusterpqueue.insert(finalcluster[clastercount]);
                                
                                clastercount=clastercount+1;
                            }
                        }
                        ansstructure[countclaster]=new setPoint(count,centerx/count,centery/count);
                        ans.insert(ansstructure[countclaster]);
                        
                        countclaster=countclaster+1;
                        
                    }
                    
                }
                
                while(!ans.isEmpty()){
                   System.out.println(ans.max().number+"" ""+String.format(""%.2f"",ans.max().x)+"" ""+String.format(""%.2f"",ans.max().y));
                   ans.delMax();
                }
            System.out.println( String.format(""%.2f"",finalclusterpqueue.min().Key()));
            break;
        }
        }
     }
    }
}
