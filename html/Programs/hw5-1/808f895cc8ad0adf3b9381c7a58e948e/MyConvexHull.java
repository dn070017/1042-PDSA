/*
.
 * To change this template file, choose Tools | Templates
.
 */
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;
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
        Arrays.sort(PList,MP.polarOrder());
        
        Stack<Point2D> CHP = new Stack<Point2D>();
        CHP.push(PList[0]);
        CHP.push(PList[1]);
        for (int i=2;i<N;i++){
            Point2D CurrentP=PList[i];
            Point2D Second=CHP.pop();
            Point2D First=CHP.pop();
            int CCW=Point2D.ccw(First, Second, CurrentP);
            if (CCW==1){
                CHP.push(First);
                CHP.push(Second);
                CHP.push(CurrentP);
            }
            
            else{
                CHP.push(First);
                CHP.push(CurrentP);
            }
            
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
    public static void main(String[] args) {
        // TODO code application logic here
        int N=10;
        Point2D[] a = new Point2D[N];
        edu.princeton.cs.algs4.StdRandom.setSeed(N);
        
        for (int i = 1; i < N+1; i++){
            Double x = edu.princeton.cs.algs4.StdRandom.uniform();
            Double y = edu.princeton.cs.algs4.StdRandom.uniform();
            //System.out.println(x);
            //System.out.println(y);
            //System.out.println(""stop"");
            Point2D P = new Point2D(x,y);
            a[i-1]=P;
        }
        int[] R = ConvexHullVertex(a);
        System.out.println(Arrays.toString(R));
    }
    
}

