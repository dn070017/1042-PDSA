import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Clustering {    
    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("" "");
            int Num = Integer.parseInt(header[0]);//幾組數字
            Point2D[] points = new Point2D[Num];
            MinPQ<Pair> pq = new MinPQ<Pair>();
            MinPQ<Double> pqF = new MinPQ<Double>();
            for (int i=0;i<Num;i++) {
                String[] DataPoint = br.readLine().split("" "");
                Double x = Double.parseDouble(DataPoint[0]);
                Double y = Double.parseDouble(DataPoint[1]);
                points[i]=new Point2D(x,y);
                
//                points[i].draw();
//                StdDraw.textLeft(points[i].x(),points[i].y(),Integer.toString(i));
//               StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
            }
            //distance
            //Pair pair = new Pair(cardsArray);
            //Clustering clu = new Clustering();
            
            Pair[] pair = new Pair[Num];//紀錄pair
            Cluster[] clus = new Cluster[Num];//紀錄cluster
            
            for (int i=0;i<Num;i++){              
                //pq.insert(points[i]);
                for (int j=i+1;j<Num;j++){                   
                    Double distance = Math.sqrt(Math.pow(points[i].x() - points[j].x(), 2) + Math.pow(points[i].y() - points[j].y(), 2));

                   Pair p = new Pair(distance,points[i],points[j]);                   
                   pq.insert(p);
                   pair[i]=p;

                }
                Point2D[] ForClus = new Point2D[1];
                   ForClus[0] =  points[i];
                   Cluster c = new Cluster(ForClus);
                   clus[i]=c;
                   
            }
            
            //若是點數目小於3，直接輸出
            //Arrays.s
            Point2D C=new Point2D(0,0);
            Double dist=0.0;
            int ClusNum=Num;         
int AFind=-1;
int BFind=-1;
            if (Num>3)
            {               
                while(ClusNum>3){
                    //System.out.println(ClusNum);
                    for(int i=0;i<ClusNum;i++){                                               
                        if(pq.min().a.equals(clus[i].Centroid)){
                            AFind=i;
//                            System.out.println(pq.min().a);
//                            clus[i].Addpoint(pq.min().b);
//                            
//                            C = new Point2D(clus[i].Centroid.x(),clus[i].Centroid.y());
                            //System.out.println(clus[i].Size);

                        }
                        if(pq.min().b.equals(clus[i].Centroid)){
                            BFind=i;
                            
//                            clus[i]=clus[ClusNum-1];
//                            ClusNum=ClusNum-1;
//                                         BFind++;            
                                      }
                    }
                    if(AFind==-1 || BFind==-1)
                        pq.delMin();
                   else if(AFind!=-1 && BFind!=-1){
                       for(int k=0;k<clus[BFind].getSize();k++){
                        clus[AFind].Addpoint(clus[BFind].point[k]);
//                        System.out.println(clus[BFind].point[k]);
//                         StdDraw.line(pq.min().a.x(),pq.min().a.y(),clus[BFind].point[k].x(),clus[BFind].point[k].y());
                       }
                       // System.out.println(pq.min().b);
                         C = new Point2D(clus[AFind].Centroid.x(),clus[AFind].Centroid.y());
                         
                         clus[BFind]=clus[ClusNum-1];
                         ClusNum=ClusNum-1;
                         for (int i = 0; i < ClusNum; i++) {
                        if (!C.equals(clus[i].Centroid)) {
                            
                            dist = Math.sqrt(Math.pow(C.x() - clus[i].Centroid.x(), 2) + Math.pow(C.y() - clus[i].Centroid.y(), 2));                           
                            Pair p = new Pair(dist, C, clus[i].Centroid);
                            pq.insert(p);
                        }
                        //pair[i]=p;
                    }
                   
                    
                    pq.delMin();
                    }
                    
                     AFind=-1; 
                    BFind=-1;
                }
            }
            Cluster[] clusF = new Cluster[ClusNum];            
//             int [] si=new int[ClusNum];
            for(int i=0;i<ClusNum;i++)
            {
                
                clusF[i]= clus[i];
               // si[i]=clus[i].getSize();
            }
//            
//                Arrays.sort(c2);   
                Arrays.sort(clusF); 
                for(int i=0;i<ClusNum;i++){
                    System.out.print(clusF[i].getSize());
                System.out.print("" "");
                System.out.printf(""%.2f"",clusF[i].Centroid.x());
                System.out.print("" "");
                System.out.printf(""%.2f"",clusF[i].Centroid.y());
                System.out.println();
                }
//            for(int i=0;i<ClusNum;i++){
//                
//                for(int j=0;j<ClusNum;j++){
//                    if(clus[j].Centroid.equals(c2[i])){
//                System.out.print(clus[j].getSize());
//                System.out.print("" "");
//                System.out.printf(""%.2f"",clus[j].Centroid.x());
//                System.out.print("" "");
//                System.out.printf(""%.2f"",clus[j].Centroid.y());
//                System.out.println();
//                    }
//                }
//                }
            
            int Acluster = -1;
            int Bcluster = -1;
            // System.out.println(pq.min().a);
//                             System.out.println(pq.min().b);
            while (Acluster == -1 || Bcluster == -1) {
                for (int i = 0; i < ClusNum; i++) {
                    if (pq.min().a.equals(clus[i].Centroid)) {
                        Acluster = i;
//                        System.out.println(i);
//                        System.out.println(pq.min().a);
//                        System.out.println(clus[i].Centroid);
//                        System.out.println(""A"");
                    }
                    if (pq.min().b.equals(clus[i].Centroid)) {
                        Bcluster = i;
//                        System.out.println(i);
//                        System.out.println(""B"");
                    }
                }
                pq.delMin();
                if (Acluster !=-1&&Bcluster!=-1)
                                 break;
                                 pq.delMin();
                             Acluster=-1;
                             Bcluster=-1;
                             }
                             
                            //System.out.println(clus[Bcluster].getSize());
                             for(int i=0;i<clus[Acluster].getSize();i++){
                                 for(int j=0;j<clus[Bcluster].getSize();j++){
                                     Double distance = Math.sqrt(Math.pow(clus[Acluster].point[i].x() - clus[Bcluster].point[j].x(), 2) + Math.pow(clus[Acluster].point[i].y() - clus[Bcluster].point[j].y(), 2));
                                     //System.out.println(distance);
                                     pqF.insert(distance);
                                     
                                   //  System.out.println(""A"");

                                 }
                             }
                             //System.out.println(""A"");
            System.out.printf(""%.2f\n"",pqF.min());
            //System.out.printf(""%.2f\n"",Math.sqrt(Math.pow(points[0].x() - points[1].x(), 2) + Math.pow(points[0].y() - points[1].y(), 2)));
            
//               for (int i =0;i<Num-1;i++){
//                   System.out.println(pq.min().distance);
//               }        
        //System.out.println(pq.);
            }

        }
    //回傳重心
    public Point2D Centroid(Point2D a, Point2D b){
        double CentX = (a.x()-b.x())/2;
        double CentY = (a.y()-b.y())/2;
        Point2D Centroid = new Point2D(CentX,CentY);
        return Centroid;
    } 
    public static class Cluster implements Comparable<Cluster>{
        private Point2D[] point;
        private Point2D Centroid;
        private int Size;
        public Cluster(Point2D[] point){
        this.point=point;
        this.Centroid = point[0];
        Size=1;
        }
        public void Addpoint(Point2D poi){
           
            if(this.Size==point.length) resize(2*point.length);
          point[Size++]=poi;           
            Centroid=Centroid(this.point);
            
        }
        private void resize(int capacity){           
            Point2D[] copy = new Point2D[capacity];
            for (int i=0;i<this.point.length;i++){
                copy[i]=this.point[i];
            }
            this.point=copy;
        }
        public int getSize(){
        return this.Size;
    }   
        public Point2D Centroid(Point2D[] a){
        double CentX=0.0;
        double CentY=0.0;
        for (int i=0;i<Size;i++){
            CentX=CentX+a[i].x();
            CentY=CentY+a[i].y();
        }
        CentX=CentX/Size;
        CentY=CentY/Size;
        Point2D Centroid = new Point2D(CentX,CentY);
        return Centroid;
    } 
        public int compareTo(Cluster that){
           if (that.getSize() > this.getSize()) 
               return 1;
        else if (that.getSize() < this.getSize())
            return -1;
        else 
            return this.Centroid.compareTo(that.Centroid);
//        els
//            return 0;
        }
    }
            
    //紀錄兩點距離，回傳兩點距離
    public static class Pair implements Comparable<Pair>{
        private Double distance;
        private Point2D a;
        private Point2D b;
    public Pair (Double distance, Point2D a, Point2D b)
    {
        this.distance = distance;
        this.a = a;
        this.b = b;
    }
    public Double getDouble(){
        return this.distance;
    }
    public Point2D geta(){
        return this.a;
    }
    public Point2D getb(){
        return this.b;
    
    }
    public int compareTo(Pair that) {
        if (that.distance > this.distance)
            return -1;
        else if (that.distance < this.distance)
            return 1;
        else
            return 0;
    }
    }
}


