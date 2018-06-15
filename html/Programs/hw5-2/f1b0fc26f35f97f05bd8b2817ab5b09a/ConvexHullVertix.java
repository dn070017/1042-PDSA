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
                //a[i].draw();
//                StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
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

      int finalResults = 0;//計算所有CC的ConvexHull點的數目
            for (int i = 0; i < N; i++) {
                int CCsize = 0;//CC的大小
                int CC[] = new int[N];
                for (int j = 0; j < N; j++) {
                    if (i == uf.find(j)) {
                        CC[CCsize] = j;
                        CCsize++;

   
                    }

                }   

                Point2D[] aCC = new Point2D[CCsize];
                for (int k = 0; k < CCsize; k++) {

                    aCC[k] = a[CC[k]];
                }
                //至少要大於3才有可能包起來
                if (CCsize > 2) {
//                    int CLine=0;//共線的點
//                    for (int check = 0; check < CCsize; check++) {
//                        if (Point2D.ccw(aCC[check], aCC[check + 1], aCC[check + 2]) == 0) {
//                            CLine++;
//                        }
//                        if((check+2)==CCsize-1)
//                            break;
//                    }
//                    if((CCsize%3+CCsize/3)!=CLine){
                    int CCConvexHull[] = ConvexHullVertex(aCC);
         
                    finalResults = finalResults + CCConvexHull.length;}
                }
            
        
            System.out.println(finalResults);
}
 }
}



