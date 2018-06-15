import java.util.*;
import java.util.Stack;

/**
.
 */
public class MyConvexHull {
    private static Point2D[] points;
    private static int N;

    public static int[] ConvexHullVertex(Point2D[] a) {

        ArrayList<Point2D> copy = new ArrayList<>();
        for (int i=0;i<a.length;i++){
            copy.add(a[i]);
        }

        boolean routing = true;
//        for (Point2D p:copy){System.out.println(""unsorted(copy): ""+p);}

        /* 將y座標最小者與points[0]對調 */
        for (int i=0;i<N;i++) {
            if(points[i].y()<points[0].y()){
                Point2D temp = points[0];
                points[0] = points[i];
                points[i] = temp;
            }
        }
        /* 排序 */
        Arrays.sort(a,a[0].POLAR_ORDER);
//        for (Point2D p:a){System.out.println(""sorted: ""+p);}


        /* routing prepare */
        Stack<Integer> ans = new Stack<>();
        int s1 = 0, s2 = 1, now = 2;
        ans.push(s1);
        ans.push(s2);
        ans.push(now);
        /* routing */
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

        int[] vertexs = new int[ans.size()];

        int j = 0;
        for(Integer i : ans){
            vertexs[j]=i;
            j++;
        }

        int counter=0;
        int[] returnVal = new int[vertexs.length];

        for(Integer k:vertexs){
//            System.out.println(""vertexs: ""+a[k]);
            for(int i=0;i<copy.size();i++){
                if(copy.get(i)==a[k]){
                    returnVal[counter]=i;
                    counter++;
                }
            }
        }

        return returnVal;
    }

    public static void main(String[] args) {
        N = 10;//Integer.valueOf(args[0]);


        points = random2DPointArr(10);
        int[] vertexArr = ConvexHullVertex(points);
        for (Integer i:vertexArr){System.out.println(i);}

//        /* Drawing */
//        StdDraw.setPenColor(StdDraw.RED);
//        for (int i=0;i<N;i++){
//            if(i>0){StdDraw.setPenColor(StdDraw.BLUE);}
//            StdDraw.filledCircle(points[i].x(),points[i].y(),0.01);
//            StdDraw.text(points[i].x(),points[i].y()+0.01,String.valueOf(i));
//        }


        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

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
