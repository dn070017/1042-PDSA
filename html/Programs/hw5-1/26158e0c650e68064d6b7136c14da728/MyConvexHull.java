import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
/**
 *
 * @author philip
 */
public class MyConvexHull {
    /**
     * @param args the command line arguments
     */
    
    private int[] _parent;
    private int[] _rank;
    private int[] _true;

    public int find(int i) {
        int p = _parent[i];
        if (i == p) {
          return i;
        }
        return _parent[i] = find(p);
    }
    public void union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        if (root2 == root1) return;
        _parent[j] = i;
        _rank[j]++;
        /*if (_rank[root1] > _rank[root2]) {
          _parent[root2] = root1;
        } else if (_rank[root2] > _rank[root1]) {
          _parent[root1] = root2;
        } else {
          _parent[root2] = root1;
          _rank[root1]++;
        }*/
    }
    public MyConvexHull(int max) {
        _parent = new int[max];
        _rank = new int[max];
        for (int i = 0; i < max; i++) {
          _parent[i] = i;
        }
    }
    public String toString() {
        return ""<UnionFind\np "" + Arrays.toString(_parent) + ""\nr "" + Arrays.toString(_rank) + ""\n>"";
    }
    
    
    public static double angle(double x1, double y1, double x2, double y2)
    {
        double dx=x2-x1,dy=y2-y1, PI=(double)Math.PI;
        double angle=0.0;
        
        if(dx==0)
           if(dy==0)angle=0;
           else if(dy>0)angle=PI/2;
           else angle=PI*3/2;
        else if(dy==0)
           if(dx>0)angle=0;
           else angle=PI;
        else if(dx<0)angle=Math.atan(dy/dx)+PI;
        else if(dy<0)angle=Math.atan(dy/dx)+(2*PI);
        else angle=Math.atan(dy/dx);

        return (double)angle*180/PI;
    }
    
    
    public static double ccwd(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x()-a.x())*(c.y()-a.y()) - (b.y()-a.y())*(c.x()-a.x());
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        int []index = new int[a.length];
//        int[] index = { 1, 2, 3, 4, 5, 6 };
        
        int minAng = 360;
        int lastAng = 0;
        int NxtPt = 0;
        int NxtPt0 = 0;
        int EndPtFlag = 0;
        int ifflag = 0;
        int CX = 0;
        Point2D[] pts = a;
        int ArrNum = a.length;
        
        int minPtsNum = 0; 
        double lastptsy = 1;
        for(int i = 0; i < ArrNum; i++){
            if(pts[i].y() < lastptsy){
               minPtsNum = i; 
            }
            lastptsy = pts[i].y();
        }
        
//        for(int i = 0; i < ArrNum; i++){
                ifflag = 0;
                lastAng = 0;
                NxtPt = 0;
                NxtPt0 = 0;
                if(ifflag == 0){
                    NxtPt = minPtsNum;
                    while(EndPtFlag == 0){
                        double [] AngleArr = new double[ArrNum];
                        int [] AngleArrI = new int[ArrNum]; 
                        minAng = 360;
//                        System.out.println(NxtPt);
                        index[CX] = NxtPt;
                        for(int j = 0; j < ArrNum; j++){
//                            if(uf._parent[j] == RedArr[i]){
                                AngleArr[j] = angle(pts[NxtPt].x(),pts[NxtPt].y(),pts[j].x(),pts[j].y());
                                AngleArrI[j] = (int)AngleArr[j];
                                if(AngleArrI[j]-lastAng > 0 && AngleArrI[j]-lastAng < minAng){
                                    minAng = AngleArrI[j]-lastAng;
                                    NxtPt0 = j;
                                }
//                            }
                        }
                        NxtPt = NxtPt0;
                        lastAng = minAng;
//                        System.out.println(NxtPt);
                        CX++;
                        
                        if(NxtPt == minPtsNum){
                            EndPtFlag = 1;
                            break;
                        }
                    }
                    ifflag = 1;
                }
                EndPtFlag=0;
//            }
        
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        return index;
    }
    
     
    public static void main(String[] args) {
        File file = new File(args[0]);
        try{
            Scanner File_in = new Scanner(file);
            String line_1 = File_in.nextLine();
            double MinDis = Double.parseDouble(line_1);
//            System.out.println(MinDis);
            
            String line_2 = File_in.nextLine();
            int ArrNum = Integer.parseInt(line_2);
//            System.out.println(ArrNum);
            
            int Ai = 0;
            Point2D[] pts = new Point2D[ArrNum];
            
            while (File_in.hasNextLine())
            {
                String line_3 = File_in.nextLine();
                String[] GivePoint = line_3.split("" "");
                double Px = Double.parseDouble(GivePoint[0]);
                double Py = Double.parseDouble(GivePoint[1]);
                
                pts[Ai] = new Point2D(Px ,Py);
                Ai++;
            }
//            System.out.println(pts[3].x());
//            System.out.println(Points[3][1]);
            double Distance = 0.0;
            MyConvexHull uf = new MyConvexHull(ArrNum);
            //Distance = Math.sqrt(Math.pow(Points[0][0]-Points[1][0],2)+Math.pow(Points[0][1]-Points[1][1],2));
//            System.out.println(Distance);
                      
            
            for(int i = 0; i < ArrNum; i++){
                for(int j = 0; j < ArrNum; j++){
                    if(i != j ){
//                        StdDraw.setPenRadius(0.01);
//                        StdDraw.setPenColor(StdDraw.BLACK);
//                        StdDraw.point(pts[i].x(), pts[i].y());
//                        StdDraw.point(pts[j].x(), pts[j].y());
//                        StdDraw.setPenColor(StdDraw.BLUE);
//                        StdDraw.text(pts[i].x()+0.02, pts[i].y(),  Integer.toString(i));
                        
                        Distance = Math.sqrt(Math.pow(pts[i].x()-pts[j].x(),2)+Math.pow(pts[i].y()-pts[j].y(),2));

                        if(Distance <= MinDis){
                            //System.out.println(Distance);
//                            StdDraw.setPenRadius(0.001);
//                            StdDraw.setPenColor(StdDraw.GREEN);
//                            StdDraw.line(pts[i].x(), pts[i].y(), pts[j].x(), pts[j].y());
                            
                            if(pts[i].y() < pts[j].y()){
                                uf.union(i,j);
                            }
                            else{
                                uf.union(j,i);
                            }
                        }
                    }
                }
            }
//            System.out.println(uf);
            

            
            
            int[] RedArr = new int[ArrNum];
            for(int i = 0; i < ArrNum; i++){
                RedArr[i] = -1;
            }
            
            int RedFlag = 0;
            for(int i = 0; i < ArrNum; i++){
                int ParCount = 0;
                for(int j = 0; j < ArrNum; j++){
                    if(uf._parent[j] == i){
                        ParCount++;  
                        if(ParCount >= 3){
                            if(RedFlag == 0){
                                RedArr[i] = i;
                                RedFlag = 1;
                            }
                            uf._rank[i] = ParCount;
//                            StdDraw.setPenColor(StdDraw.RED);
//                            StdDraw.point(pts[i].x(), pts[i].y());
//                            System.out.println(i);
                        }
                    }
                }
                RedFlag = 0;
            }
//            System.out.println(uf);
//            System.out.println(RedArr[3]);
            
//            int CCnum = 0;
//            for(int i = 0; i < ArrNum; i++){
//                if(RedArr[i] != -1){
//                    CCnum++;
//                }
//            }
//            System.out.println(CCnum);
            
            
            
            int ArrSiz = 0;
            int[] NewArr = new int[ArrNum];
            
            
            for(int i = 0; i < ArrNum; i++){
                int Pt2count = 0;
                if(RedArr[i] != -1){
                    Point2D[] pts2 = new Point2D[uf._rank[i]];
                    for(int j = 0; j < ArrNum; j++){
                        if(uf._parent[j] == i){
                            pts2[Pt2count] = pts[j];
                            Pt2count++;
                        }
                    }
//                    System.out.println(ConvexHullVertex(pts2));
                    NewArr = ConvexHullVertex(pts2);
//                    System.out.println(pts2[4].x());
                }
            }
            
            for(int i = 0; i < ArrNum; i++){
//                System.out.println(NewArr[i]);
            }
            
            
//            System.out.println(uf);
//            double [] AngleArr = new double[ArrNum];
//            int [] AngleArrI = new int[ArrNum]; 
//            int minAng = 360;
//            int lastAng = 0;
//            int NxtPt = 0;
//            int NxtPt0 = 0;
//            int EndPtFlag = 0;
//            int ifflag = 0;
//            int CX = 0;
            
//            for(int i = 0; i < ArrNum; i++){
//                ifflag = 0;
//                lastAng = 0;
//                NxtPt = 0;
//                NxtPt0 = 0;
//                if(RedArr[i] != -1 && ifflag == 0){
//                    NxtPt = RedArr[i];
//                    while(EndPtFlag == 0){
//                        double [] AngleArr = new double[ArrNum];
//                        int [] AngleArrI = new int[ArrNum]; 
//                        minAng = 360;
////                        System.out.println(NxtPt);
//                        for(int j = 0; j < ArrNum; j++){
//                            if(uf._parent[j] == RedArr[i]){
//                                AngleArr[j] = angle(pts[NxtPt].x(),pts[NxtPt].y(),pts[j].x(),pts[j].y());
//                                AngleArrI[j] = (int)AngleArr[j];
//                                if(AngleArrI[j]-lastAng > 0 && AngleArrI[j]-lastAng < minAng){
//                                    minAng = AngleArrI[j]-lastAng;
//                                    NxtPt0 = j;
//                                }
//                            }
//                        }
//                        NxtPt = NxtPt0;
//                        lastAng = minAng;
////                        System.out.println(NxtPt);
//                        CX++;
//                        
//                        if(NxtPt == RedArr[i]){
//                            EndPtFlag = 1;
//                            break;
//                        }
//                    }
//                    ifflag = 1;
//                }
//                EndPtFlag=0;
//            }

            
        }
        catch(IOException e){
            System.out.println(""error!""); 
        }
        // TODO code application logic here
    }
    
}

