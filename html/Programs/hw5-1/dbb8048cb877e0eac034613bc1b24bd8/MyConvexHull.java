
import com.sun.corba.se.impl.io.ValueHandlerImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
//import edu.princeton.cs.algs4.Point2D;
//import  edu.princeton.cs.algs4.Stack;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author sarahsu
 */
public class MyConvexHull {
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        
        int N = a.length;
        Point2D[] b=a.clone();
        
        
        Arrays.sort(a);//找出y最小的點為point[0]
        
        //Stack<Integer> A = new Stack<Integer>();
        Stack<Point2D> s = new Stack<Point2D>();        
        
        for (int i = 0; i < N; i++) {
            Arrays.sort(a,a[0].POLAR_ORDER);//根據角度重排點順序(POLAR_ORDER)
        }        
        
        s.push(a[0]); 
        //A.push(0);
        s.push(a[1]); 
        //A.push(1);
        
        for (int i = 2; i < N; i++) {
            Point2D top = s.pop();
            //int temp = A.pop();
            while (Point2D.ccw(s.peek(), top, a[i]) <= 0) {
                top = s.pop();
                //temp = A.pop();
            }
            s.push(top); 
            //A.push(temp);
            s.push(a[i]); 
            //A.push(i);
        }
        
        int n = s.size();
        int[] CHPoint=new int[n];
        Point2D[] c=new Point2D[n];
        int k=0;
        for(int i=0;i<n;i++)
        {
            c[i]=s.pop();
            for(int j=0;j<N;j++)
            {
                if(c[i].x()==b[j].x())
                {
                    CHPoint[k]=j;
                    k++;                    
                }
            }            
        }
        
        Arrays.sort(CHPoint);
        
//        for(int i=0;i<n;i++)
//        {
//            System.out.print(CHPoint[i]);
//        }

        return CHPoint;            
    }
    
    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] minDistance = br.readLine().split("" "");
            double minD=Double.parseDouble(minDistance[0]);
            String[] numberOfPoint = br.readLine().split("" "");
            int numOfPoint=Integer.parseInt(numberOfPoint[0]);
            
            WeightedQuickUnionUF wQUF=new WeightedQuickUnionUF(numOfPoint);
            
            Point2D[] pointXY=new Point2D[numOfPoint];//儲存txt的點資料(x,y)
            for(int i=0;i<numOfPoint;i++)
            {
                String[] X_And_Y = br.readLine().split("" "");
                double x=Double.parseDouble(X_And_Y[0]);
                double y=Double.parseDouble(X_And_Y[1]);
                pointXY[i]=new Point2D(x, y);
                //StdDraw.filledCircle(pointXY[i].x(),pointXY[i].y(), 0.01);                
            }
            //MyConvexHull.ConvexHullVertex(pointXY);
//            Point2D[] a=new Point2D[numOfPoint];
            //Stack<Point2D> label = new Stack<Point2D>();
            int[] label=new int[numOfPoint];
            for(int i=0;i<numOfPoint;i++)
            {
                label[i]=-1;
            }
            for(int i=0;i<numOfPoint-1;i++)
            {                
                for(int j=i+1;j<numOfPoint;j++)
                {
                    if(Math.pow(Math.pow(pointXY[i].x()-pointXY[j].x(),2)+Math.pow(pointXY[i].y()-pointXY[j].y(),2), 0.5)<=minD)
                    {
                        wQUF.union(i, j);//一小群CC                             
                    }                    
                }             
                
            }
            int k=0;
            int groupCount=0;
            for(int i=0;i<numOfPoint-1;i++)
            {                
                for(int j=i+1;j<numOfPoint;j++)
                {
                    if(wQUF.connected(i, j))
                    {
                        if(label[i]==-1&&label[j]==-1)
                        {
                            label[i]=k;
                            label[j]=k;
                            k++;
                            groupCount++;
                        }
                        else if(label[i]!=-1&&label[j]==-1)
                        {
                            label[j]=label[i];
                        }
                        else if(label[i]==-1&&label[j]!=-1)
                        {
                            label[i]=label[j];
                        }
                        
                    }
                    
                }
            }
            
            int pointCount=0;
            int L=0;
            //int count=0;
            while(L<=groupCount)
            {
                int numOfGroupPoint=0;
                for(int j=0;j<numOfPoint;j++)
                {
                    if(label[j]==L)
                    {
                        numOfGroupPoint++;
                    }
                }
                Point2D[] pointXYa=new Point2D[numOfGroupPoint];
                int cnt = 0;
                for(int i=0;i<numOfPoint;i++)
                {
                    
                    if(label[i]==L)
                    {
                        pointXYa[cnt]=pointXY[i];
                        cnt++;
                        
                    }                  
                }
                if(pointXYa.length>=3)
                {
                    pointCount+=MyConvexHull.ConvexHullVertex(pointXYa);
                }
                L++;
            }
            System.out.printf(String.valueOf(pointCount));
           
            
           
            //StdOut.printf(MyConvexHull.ConvexHullVertex(pointXY));
            
//            Arrays.sort(pointXY);//畫出起始點(紅色)
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.filledCircle(pointXY[0].x(), pointXY[0].y(), 0.01);
//            StdDraw.setPenRadius();
//            
//            Stack<Integer> A = new Stack<Integer>();//畫出sort後的點編號(藍色)與連線(綠色)
//            for (int i = 0; i < numOfPoint; i++) {
//                Arrays.sort(pointXY,pointXY[0].polarOrder());
//                A.push(i);
//                StdDraw.setPenColor(StdDraw.GREEN);
//                StdDraw.line(pointXY[0].x(), pointXY[0].y(), pointXY[i].x(), pointXY[i].y());
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.text(pointXY[i].x()+0.03, pointXY[i].y()+0.03, A.toString());
//                String pointX=String.valueOf(pointXY[i].x());
//                String pointY=String.valueOf(pointXY[i].y());
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.text(pointXY[i].x()+0.08, pointXY[i].y()+0.03, pointX);
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.text(pointXY[i].x()+0.08, pointXY[i].y(), pointY);
//                
//                A.pop();
//            }
            
//            Stack<Point2D> CC=new Stack<Point2D>();
//            
//            int i=0;
//            while (i!=numOfPoint)
//            {
//                int count=0;     
//                if(pointXY[i]==null)
//                {
//                    break;
//                }
//                
//                double distance=0.0;
//                distance=Math.pow(Math.pow(pointXY[i].x()-pointXY[i+1].x(),2)+Math.pow(pointXY[i].y()-pointXY[i+1].y(),2), 0.5);
//                if(distance<=minD)
//                {
//                    wQUF.union(i, i+1);
//                    
//                    CC.push(pointXY[i]);
//                }
//                if(wQUF.count()>=3)
//                {
//                    //int[] recard=new int[];
//                    Point2D[] pointXYa=new Point2D[wQUF.count()];
//                    StdOut.printf(MyConvexHull.ConvexHullVertex(pointXYa));
//                    //String D = MyConvexHull.ConvexHullVertex(pointXYa);
//                    //while(){}
//                    //D.split("" "");
//                }
//                
//                i++;
//            };
            
     
            
            
            
        }
}
}
//        
//        In in = new In(args[0]); 
//        int N = in.readInt();
//        //int N = 10;
//        Point2D[] a = new Point2D[N];
//        for(int i=0;i<N;i++){
//            double x = StdRandom.uniform();
//            double y = StdRandom.uniform();
//            a[i] = new Point2D(x,y);
//            StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);    
//        }
//        StdOut.printf(MyConvexHull.ConvexHullVertex(a));
//        
//        Arrays.sort(a);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(a[0].x(), a[0].y(), 0.01);
//        StdDraw.setPenRadius();
//        
//        Stack<Integer> A = new Stack<Integer>();
//        for (int i = 0; i < N; i++) {
//            Arrays.sort(a,a[0].POLAR_ORDER);
//            A.push(i);
//            StdDraw.setPenColor(StdDraw.GREEN);
//            StdDraw.line(a[0].x(), a[0].y(), a[i].x(), a[i].y());
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.text(a[i].x()+0.03, a[i].y()+0.03, A.toString());
//            A.pop();
//        }
//
//        Stack<Point2D> s = new Stack<Point2D>();
//        Stack<Integer> B = new Stack<Integer>();
//        StdDraw.setPenColor(StdDraw.BLACK);
//        s.push(a[0]);B.push(0);
//        s.push(a[1]);B.push(1);
//        StdDraw.line(a[0].x(), a[0].y(), a[1].x(), a[1].y());
//        // find index k1 of first point not equal to points[0]
//       /* int i;
//        for (i = 1 ; i < N; i++) {
//            if (!a[0].equals(a[i])) break;
//            if (i == N) return;
//        }
//        // find index k2 of first point not collinear with points[0] and points[k1]
//        int j;
//        for (j = i + 1; j < N; j++){
//            if (Point2D.ccw(a[0], a[i], a[j]) != 0) break;
//            s.push(a[j-1]);    // points[k2-1] is second extreme point
//        }*/
//        // Graham scan; note that points[N-1] is extreme point different from points[0]
//        for (int i = 2; i < N; i++) {
//            Point2D top = s.pop();
//            int temp = B.pop();
//            while (Point2D.ccw(s.peek(), top, a[i]) <= 0) {
//                top = s.pop();
//                temp = B.pop();
//            }
//            s.push(top);B.push(temp);
//            s.push(a[i]);B.push(i);
//        }
//        int n = B.size();
//        for(int j=0;j<n-2;j++){
//            int x = B.pop();
//            StdDraw.line(a[x].x(), a[x].y(), a[B.peek()].x(), a[B.peek()].y());
//            
//            
//        }
//        StdDraw.line(a[N-1].x(), a[N-1].y(), a[0].x(), a[0].y());
//        
//        
//    }


