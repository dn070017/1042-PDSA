import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Stack;


/**
.
 */
public class MyConvexHull {
//    public static int N;
//    public static double d;
    public static int output=0;

    public static int[] ConvexHullVertex(Point2D[] a) {

        ArrayList<Point2D> copy = new ArrayList<>();
        for (int i=0;i<a.length;i++){
            copy.add(a[i]);
        }

        boolean routing = true;
        int n = a.length;
//        for (Point2D p:copy){System.out.println(""unsorted(copy): ""+p);}

        /* 將y座標最小者與points[0]對調 */
        for (int i=0;i<n;i++) {
            if(a[i].y()<a[0].y()){
                Point2D temp = a[0];
                a[0] = a[i];
                a[i] = temp;
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


    public static void main(String[] args) throws Exception {


        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

/* 1. read in the file containing N 2-dimentional points */
            /* 讀檔，幾十個點讀入points */
            double d = Double.valueOf(br.readLine());  //d
            int N = Integer.valueOf(br.readLine()); //有幾個點
            Point2D[] points = new Point2D[N];
            int count=0;
            while(br.ready()) {
                String[] data = br.readLine().split("" "");
                double x = Double.valueOf(data[0]);
                double y = Double.valueOf(data[1]);
                points[count] =  new Point2D(x,y);;
                count++;
            }

//            for (Point2D p:points){System.out.println(""p(brfore): ""+p);} //print(x,y)(before)

            /* points重新排序，並輸出vertex */
//            System.out.println(ConvexHullVertex(points));

//            for (Point2D p:points){System.out.println(""p(after): ""+p);} //print(x,y)(after)

            /* 以points畫圖 */
//            StdDraw.setPenColor(StdDraw.RED);
//            for (int i = 0; i < N; i++) {
//                if (i > 0) {
//                    StdDraw.setPenColor(StdDraw.BLUE);
//                }
//                StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
//                StdDraw.text(points[i].x(), points[i].y() + 0.01, String.valueOf(i));
//            }

//        /* random 2Dpoints testing */
//            N = 10;
//            points = random2DPointArr(10);
//            int[] vertexArr = ConvexHullVertex(points);
//            for (Integer i : vertexArr) {
//                System.out.println(i);
//            }
//
//        /* Drawing */
//            StdDraw.setPenColor(StdDraw.RED);
//            for (int i = 0; i < N; i++) {
//                if (i > 0) {
//                    StdDraw.setPenColor(StdDraw.BLUE);
//                }
//                StdDraw.filledCircle(points[i].x(), points[i].y(), 0.01);
//                StdDraw.text(points[i].x(), points[i].y() + 0.01, String.valueOf(i));
//            }

/* 2. create an edge for each pair of points with a distance <= d */

            UF uf = new UF(N);

            for(int i=0;i<N;i++) {
                for (int j = i; j < N; j++) {   //走訪所有的任兩點組合(i,j)
//                    System.out.printf(""(%d,%d)\n"", i, j);
                    if (points[i].distanceTo(points[j]) < d) {  //發現距離夠近的兩點則將兩個index嘗試放進CCs中
                        points[i].drawTo(points[j]);
                        uf.union(i,j);
                    }
                }
            }

            // 印出uf
//            for(int ii=0;ii<N;ii++){
//                System.out.println(uf.find(ii));
//            }

            ArrayList<Stack<Integer>> AA = new ArrayList<>();
            for(int i=0;i<N;i++){
                AA.add(new Stack<>());
            }
            for(int ii=0;ii<N;ii++){    //ii: 0,1,2,3,4,5,6,7,8,9
                AA.get(uf.find(ii)).add(ii);
            }

//            System.out.println(AA);

            for(Stack subgp:AA){
                if(subgp.size()>=3){    //跑兩次
                    Point2D[] aa = new Point2D[subgp.size()];
                    int cont = 0;
                    for(Object io:subgp){   //跑五次、跑三次
                        int index = Integer.valueOf(io.toString());
                        aa[cont]=points[index];
                        cont++;
                    }
//                    for(Point2D aaa:aa){System.out.println(aaa);}
                    output+=ConvexHullVertex(aa).length;
                }
            }

            System.out.println(output);


//            ArrayList<ArrayList<Integer>> CCs = new ArrayList<ArrayList<Integer>>();
//            int NCC=CCs.size();
//
//            for(int i=0;i<N;i++){
//                for(int j=i;j<N;j++){   //走訪所有的任兩點組合(i,j)
//                    System.out.printf(""(%d,%d)\n"",i,j);
//                    if(points[i].distanceTo(points[j])<d){  //發現距離夠近的兩點則將兩個index嘗試放進CCs中
//                        points[i].drawTo(points[j]);
//
//                        for (ArrayList<Integer> CC:CCs){    //走訪一個個Connected Components
//                            for (int CCc:CC){   //走訪某個Connected Components中的元素(Integer)
//                                if (i==CCc){
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }

/* 3. find connected components (CCs) with a size >= 3 */


/* 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[]) */


/* 5. count the number of points in N serving as a convex hull vertex, print it */


        }
    }
    public static Point2D[] random2DPointArr(int N) {
        Point2D[] arr = new Point2D[N];
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
