import java.awt.geom.*;
import java.util.*;
import java.util.Stack;

/**
.
 */
public class MyConvexHull {
    private static Point2D[] points;
    private static int N;

    public static int[] ConvexHullVertex(Point2D[] a) {

        boolean routing = true;

//        for (Point2D p:a){System.out.println(""before: ""+p);}

//        /* 將y座標最小者與points[0]對調 */
        for (int i=0;i<N;i++) {
            if(points[i].y()<points[0].y()){
                Point2D temp = points[0];
                points[0] = points[i];
                points[i] = temp;
            }
        }
        Arrays.sort(a,a[0].POLAR_ORDER);
//        for (Point2D p:a){System.out.println(""after: ""+p);}

//        /* Drawing */
//        System.out.println(""Drawing..."");
//        StdDraw.setPenColor(StdDraw.RED);
//        for (int i=0;i<N;i++){
//            System.out.println(""drawing: ""+points[i]);
//            if(i>0){StdDraw.setPenColor(StdDraw.BLUE);}
//            StdDraw.filledCircle(points[i].x(),points[i].y(),0.01);
//            StdDraw.text(points[i].x(),points[i].y()+0.01,String.valueOf(i));
//        }

//        /* routing prepare */
        Stack<Integer> ans = new Stack<>();
        int s1 = 0, s2 = 1, now = 2;
        ans.push(s1);
        ans.push(s2);
        ans.push(now);

//        /* routing */
        while (routing){

            boolean ccwTrue=false;

//            for(Integer i : ans){System.out.println(i);}

            if(now==a.length){ // now到10時停止
                routing=false;
                ccwTrue = Point2D.ccw(a[s1],a[s2],a[0])==1;
            }else {
                ccwTrue = Point2D.ccw(a[s1],a[s2],a[now])==1;
            }

            if(ccwTrue){
                ans.push(now+1);
                now=ans.elementAt(ans.size()-1);
                s2=ans.elementAt(ans.size()-2);
                s1=ans.elementAt(ans.size()-3);
                continue;
            }else {
                ans.pop();
                ans.pop();
                ans.push(now);
                //now不變
                s2=ans.elementAt(ans.size()-2);
                s1=ans.elementAt(ans.size()-3);
                continue;
            }
        }

        ans.pop();
        ans.pop();

//        String returnVal = """";
        int[] returnVal = new int[ans.size()];
        int j = 0;
        for(Integer i : ans){
//            returnVal+=String.valueOf(i);
            returnVal[j]=i;
//            System.out.print("",""+i);
            j++;
        }

        //輸出returnVal
//        for(Integer k:returnVal){
//            System.out.println(k);
//        }

        return returnVal;
    }

    public static void main(String[] args) {
        
    }
    public static Point2D[] random2DPointArr(int N) {
        Point2D [] arr = new Point2D[N];
        for (int i=0;i<N;i++) {
            arr[i] = random2DPoint();
//            System.out.println(""created""+arr[i]);
        }
        return arr;
    }
    public static Point2D random2DPoint() {
        return new Point2D(StdRandom.uniform(),StdRandom.uniform());
    }
}
