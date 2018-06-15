/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
    
    public static int[] ConvexHullVertex(Point2D[] a){
        int N = a.length;
        if (N==1){
            int[] IndexList=new int[1];
            IndexList[0]=0;
            return(IndexList);
        }
        Point2D[] PList= new Point2D[N];
        for (int i = 0; i < N; i++){
            PList[i]=a[i];
        }
        Double minx=PList[0].x();
        Double miny=PList[0].y();
        Point2D MP = new Point2D(minx,miny);
        for (int i=0;i<N;i++){
            Double cx=PList[i].x();
            Double cy=PList[i].y();
            if (cy < miny){
                //edu.princeton.cs.algs4.StdDraw.circle(minx,miny,0.01);
                minx=cx;
                miny=cy;
                MP = new Point2D(cx,cy);
                PList[i]=PList[0];
                PList[0]=MP;
            }
            /*
            else{
                edu.princeton.cs.algs4.StdDraw.circle(cx,cy,0.01);        
            }
                    */
        }
        Arrays.sort(PList,MP.POLAR_ORDER);
        
        Stack<Point2D> CHP = new Stack<Point2D>();
        CHP.push(PList[0]);
        CHP.push(PList[1]);
        for (int i=2;i<N;i++){
            Point2D CurrentP=PList[i];
            Point2D Second=CHP.pop();
            Point2D First=CHP.pop();
            int CCW=Point2D.ccw(First, Second, CurrentP);
            while(CCW!=1){
                Second=First;
                First=CHP.pop();
                CCW=Point2D.ccw(First, Second, CurrentP);
            }
            CHP.push(First);
            CHP.push(Second);
            CHP.push(CurrentP);
            /*
            if (CCW==1){
                CHP.push(First);
                CHP.push(Second);
                CHP.push(CurrentP);
            }
            else if (CCW==-1){
                
            }
            else{
                CHP.push(First);
                CHP.push(CurrentP);
            }
            */
        }
        
        int N2=CHP.size();
        //Iterator I = CHP.iterator();
        Point2D[] CHPList = new Point2D[N2];
        for (int i=0;i<N2;i++){
            CHPList[i]=CHP.pop();
        }
        
        int[] IndexList = new int[N2];
        int key = 0;
        for (int i=0;i<N;i++){
            if (key!=N2){
                for (int j=0;j<N2;j++){
                if (a[i].equals(CHPList[j])){ //Plist=a
                    IndexList[key]=i;
                    key++;
                    break;
                    }
                }
            }
            else{
                break;
            } 
        }
        return IndexList;
    }
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        FileReader FileIn = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(FileIn);
        String row = br.readLine();
        double Dis = Double.parseDouble(row); //Get distance
        row = br.readLine();
        int N = Integer.parseInt(row);//Get N
        
        //Below is to get Data
        Point2D[] PList=new Point2D[N];
        for (int i=0;i<N;i++){
            String[] row2 = br.readLine().split("" "");
            double X = Double.parseDouble(row2[0]);
            double Y = Double.parseDouble(row2[1]);
            Point2D P = new Point2D(X,Y);
            PList[i]=P;
        }
        
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        for (int i=0;i<N;i++){
            Point2D CP= PList[i];
            for (int j=i+1;j<N;j++){
                double dt = CP.distanceTo(PList[j]);
                if (dt <= Dis && !uf.connected(i, j)){
                    uf.union(i, j);
                }
            }
        }
        
        int CHP_Number = 0;
        //Dictionary ClusterDic = new  Hashtable();
        ArrayList<Integer> RootList= new ArrayList<Integer>();
        for (int i=0;i<N;i++){
            int root = uf.find(i);
            if (RootList.contains(root)){
                continue;
            }
            else{
                RootList.add(root);
                ArrayList<Point2D> cluster = new ArrayList<Point2D>();
                cluster.add(PList[i]);
                for (int j=i+1;j<N;j++){
                    if (uf.connected(i, j)){
                        cluster.add(PList[j]);
                    }
                }
                int n =cluster.size();
                //System.out.println(n);
                //System.out.println(""stop"");
                Point2D[] cluster2 = cluster.toArray(new Point2D[n]);
                //System.out.println(Arrays.toString(cluster2));
                //System.out.println(""stop"");
                int[] CHP_Index = ConvexHullVertex(cluster2);
                int New_Number=Array.getLength(CHP_Index);
                if (New_Number==1){
                    New_Number=0;
                }
                
                //System.out.println(New_Number);
                CHP_Number=CHP_Number+New_Number;
            }
            
        }
        System.out.println(CHP_Number);
        
        
        
        /*
        int N=1;
        Point2D[] a = new Point2D[N];
        StdRandom.setSeed(N);
        
        for (int i = 1; i < N+1; i++){
            Double x = StdRandom.uniform();
            Double y = StdRandom.uniform();
            //System.out.println(x);
            //System.out.println(y);
            //System.out.println(""stop"");
            Point2D P = new Point2D(x,y);
            a[i-1]=P;
        }
        int[] R = ConvexHullVertex(a);
        
        System.out.println(Arrays.toString(R));
        
        int RR=R.length;
        
        int done = 0;
        for (int i=0;i<N;i++){
            for (int j =0;j<RR;j++){
                if (i==R[j]){
                    StdDraw.setPenColor(Color.red);
                    StdDraw.circle(a[i].x(), a[i].y(), 0.01);
                    done=1;
                    break;
                }
                
            }
            if (done==0){
                StdDraw.setPenColor(Color.black);
                StdDraw.circle(a[i].x(), a[i].y(), 0.01);
            }
            done=0;
        }
        */
        
        
    }
    
}

