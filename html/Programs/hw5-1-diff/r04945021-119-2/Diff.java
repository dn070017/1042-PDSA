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
        for (int i = 0; i < a.length; i++){
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
        int A=1;
        int B=2;
        
//        for (int ll=0;ll<a.length-2;ll++){
//        for(A=1;A<a.length;A++){
//            if (!points[0].equals(points[A])) break;    
//        }
////        if (A==a.length) break;//全部item都一樣
//        
//        for(B=A+1;B<a.length;B++){
//            if(Point2D.ccw(points[0], points[A], points[B])!=0) 
//                break;    
//        }
        stack.push(points[B - 1]);

        for (int i = B; i < a.length; i++) {
            Point2D top = stack.pop();
            //not ccw,pop next point
            if (Point2D.ccw(stack.peek(), top, points[i]) < 0) {
                
stack.push(points[i]);            
            }
            else{
            stack.push(top);
            stack.push(points[i]);
            }
        }
        
       
      int Size=stack.size();
      int[] Convex = new int[Size];//紀錄a的id，y由小到大


        for (int i=0;i<Size;i++){   
//            System.out.println(stack.pop());
            for(int j=0;j<a.length;j++){

                if(stack.peek().equals(a[j])){
                   
                    Convex[Size-1-i]=j;
                    stack.pop();
                    break;
                }
            }
        }
//    }
        
        return Convex;
    }

    // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
    public static void main(String[] args) throws Exception {
StdDraw.setCanvasSize(400, 400);
        StdDraw.setXscale(-1, 10);
        StdDraw.setYscale(-1, 10);
        StdDraw.setPenRadius(.01);
        
        
        int N=7;

Point2D[] a = new Point2D[N];
//a[0]=new Point2D(0,1);
//a[1]=new Point2D(4,3);
//a[2]=new Point2D(0,2);
//a[3]=new Point2D(1,4);
//a[4]=new Point2D(2,0);
for(int i=0;i<N;i++){
    int x = StdRandom.uniform(N);
    int y = StdRandom.uniform(N);
    a[i]=new Point2D(x,y);
   a[i].draw();

System.out.println(a[i]);
 
}

int asd[]=ConvexHullVertex(a);
for (int i=0;i<asd.length;i++){
    System.out.println(asd[i]);
}
    }
}

