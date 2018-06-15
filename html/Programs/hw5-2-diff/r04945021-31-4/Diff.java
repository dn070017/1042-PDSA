/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Random;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {

//copy a
        Point2D[] points = new Point2D[a.length];
        for (int i = 0; i < a.length; i++) {
            points[i] = a[i];
//            StdDraw.textLeft(points[i].x(),points[i].y(),Integer.toString(i));
        }
        Arrays.sort(points);
//           StdDraw.setPenColor(StdDraw.RED);
//           points[0].draw();
        Arrays.sort(points, 1, a.length, points[0].POLAR_ORDER);

        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        Stack<Point2D> stack = new Stack<Point2D>();
        stack.push(points[0]);//從0開始
        int A = 1;
        int B = 2;
        stack.push(points[B - 1]);

        for (int i = B; i < a.length; i++) {
            Point2D top = stack.pop();
            //not ccw,pop next point
            while (Point2D.ccw(stack.peek(), top, points[i]) < 0) {
                top = stack.pop();
//stack.push(points[i]);            
            }
            stack.push(top);
            stack.push(points[i]);
        }              
      int Size=stack.size();
      int[] Convex = new int[Size];//紀錄a的id

        for (int i=0;i<Size;i++){   

            for(int j=0;j<a.length;j++){

                if(stack.peek().equals(a[j])){
                   
                    Convex[Size-1-i]=j;
                    stack.pop();
                    break;
                }
            }
        }       
        return Convex;
    }

    // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("" "");
            double d = Double.parseDouble(data[0]);
     
            data = br.readLine().split("" "");//
            int N = Integer.parseInt(data[0]);
            Point2D[] a = new Point2D[N];
    //讀N個點
            for (int i=0;i<N;i++) {
                String[] DataPoint = br.readLine().split("" "");
                Double x = Double.parseDouble(DataPoint[0]);
                Double y = Double.parseDouble(DataPoint[1]);
                a[i]=new Point2D(x,y);
//                a[i].draw();
            }
            QuickUnionUF uf = new QuickUnionUF(N);
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    if(a[i].distanceTo(a[j])<=d){
                        uf.union(i,j);
//                    StdDraw.line(a[i].x(),a[i].y(),a[j].x(),a[j].y());
                    }
    
                }
            }
            int CCroot = -1;
            int finalResults = 0;//計算所有CC的ConvexHull點的數目
            for (int i = 0; i < N; i++) {
                int CCsize = 1;//CC的大小
                int CC[] = new int[N];
                int CCrootN = uf.find(i);
                if (CCroot != CCrootN)
                    CCroot = CCrootN;
                else
                    continue;
                for (int j = i + 1; j < N; j++) {
              CC[0] = i;
                    if (uf.find(i) == uf.find(j)) {
                        CC[CCsize] = j;
                        CCsize++;

                    }
                }
                Point2D[] aCC = new Point2D[CCsize];
                for (int k = 0; k < CCsize; k++) {

                    aCC[k] = a[CC[k]];
                }
                if(CCsize!=1){
                int CCConvexHull[] = ConvexHullVertex(aCC);
                finalResults = finalResults + CCConvexHull.length;
                        }
            }
            System.out.println(finalResults);
//StdDraw.setCanvasSize(400, 400);
//        StdDraw.setXscale(-1, 10);
//        StdDraw.setYscale(-1, 10);
//        StdDraw.setPenRadius(.01);
//        
//        
//        int N=7;
//
//Point2D[] a = new Point2D[N];
//
//for(int i=0;i<N;i++){
//    int x = StdRandom.uniform(N);
//    int y = StdRandom.uniform(N);
//    a[i]=new Point2D(x,y);
//   
//
//System.out.println(a[i]);
 
}

//int asd[]=ConvexHullVertex(a);
//for (int i=0;i<asd.length;i++){
//    System.out.println(asd[i]);
//}
    }
}

