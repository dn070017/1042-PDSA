import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class MyConvexHull{
     public static int[] ConvexHullVertex(Point2D[] a) {
         int n= a.length;       
         Point2D[] point = new Point2D[n];
         for(int i = 0; i < n;i++){
            point[i] = new Point2D(a[i].x(), a[i].y());    
        }
         
         Arrays.sort(point, Point2D.Y_ORDER);
         Arrays.sort(point, point[0].POLAR_ORDER);
         
       Stack<Integer> stack = new Stack<Integer>(); 
       int p0=0,p1=1,p2=2;
       
       for(int i=2;i<n;){
           int x = Point2D.ccw(point[p0],point[p1],point[p2]);
           if(x==1){
                stack.push(p0);
                p0=p1;p1=p2;p2=i+1;
                i++;
           }
           else{
               p1=p0;
               p0=stack.pop();
            
           }     
       }
       stack.push(p0);
       stack.push(p1);
       
       int[] array=new int[stack.size()];
       int s = stack.size();
       for(int x=0;x<s;x++){
           array[x]=stack.pop();
           //System.out.println(array[x]);
           
       }
       int[] result =new int[s];
       
       for(int i=0;i<s;i++){
           for(int j=0;j<n;j++){
               if(point[array[i]].equals(a[j])){
                   result[i]=j;
                }
           }
       }
       //System.out.print(""\n"");
       //for(int x=0;x<s;x++){
           //System.out.println(result[x]);
           
      // }
       /*
           System.out.println(""======="");
           System.out.println(s);
           System.out.println(array[0]);
           System.out.println(array[s-1]);
       */
        return result;
       
    }
/*
    public static int[] ConvexHullVertex2(Point2D[] a) {
        
        int n = a.length;
        Point2D[] points = new Point2D[n];
        
        for(int i = 0; i < n;i++){
        points[i] = new Point2D(a[i].x(), a[i].y());    
        }
        
            Arrays.sort(points, Point2D.Y_ORDER );
            Arrays.sort(points, points[0].POLAR_ORDER );
            
            Stack<Integer> hull = new Stack<Integer>();
            int[] p = new int[3];
            p[0] = 0;p[1] = 1;p[2] = 2;
            
            
            
            int counter = 2;
            while(counter < n){               
               if(Point2D.ccw(points[p[0]], points[p[1]], points[p[2]]) == 1){
                   hull.push(p[0]);
                   counter++; 
                   p[0] = p[1];
                   p[1] = p[2];
                   p[2] = counter; 
               }
               else{
                   p[1] = p[0];
                   p[0] = hull.pop();               
               }              
            }
            
            for(int i = 0; i<2;i++){
                hull.push(p[i]);
            }
            
            int size = hull.size();
            int[] result = new int[size];
            int[] result2 = new int[size];
            
            for(int i = 0; i < size; i++){
                result[i] = hull.pop();
            }
    
    for(int i=0;i < size;i++){
        for(int j=0;j < n;j++){
            if(points[result[i]].equals(a[j])){
                result2[i] = j;
            }
        }
    }
        
            
    return result2;
    }
    */
    public static void main(String[] args) throws Exception{
           try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
               String data = br.readLine();
               double r = Double.parseDouble(data);
               //System.out.print(r);
               //System.out.print(""\n"");
               
               String data2 =br.readLine();
               int n = Integer.parseInt(data2);
               //System.out.print(n);
              // System.out.print(""\n"");
               
               Point2D[] points = new Point2D[n];
               for(int i=0;i < n ; i++){
                    String cor[] = br.readLine().split("" "");
                    points[i] = new Point2D(Double.parseDouble(cor[0]), Double.parseDouble(cor[1]));                
                }
               
             UF flag = new UF(n);
             for(int i = 0;i<n;i++){
                for(int j=i;j<n;j++){
                    if(i==j);
                    else if(points[i].distanceTo(points[j]) <= r){
                       flag.union(i,j);}   
                }
            }
            int[] int_flag = new int[n];
            for(int i=0; i < n;i++){
                int_flag[i] = -1;}
            int count = 0;            
            for(int i=0;i < n;i++){
                if(int_flag[i] == -1){
                    int_flag[i] =count;
                    count++;
                    for(int j=i;j<n;j++){
                       if(i==j);
                       else if(flag.connected(i, j))
                       int_flag[j] = int_flag[i];                                                           
                    }
                }                    
            }
            int out = 0;
            for(int i = 0;i <= count;i++){
                int count_temp = 0;
                for(int j=0; j < n;j++){
                if(i==int_flag[j])
                    count_temp++;
                }
                if(count_temp > 2){
                   Point2D[] t = new Point2D[count_temp];
                   int c = 0;
                   for(int j2=0; j2 < n;j2++){
                   if(i==int_flag[j2]){
                    t[c] = new Point2D(points[j2].x(), points[j2].y());
                    c++;}
                   }

                   
                   int[] result = ConvexHullVertex(t);
                   out += result.length;
                }
            }
            System.out.println(out);
             
               
            /*  
            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(0, 1);
            StdDraw.setYscale(0, 1);
            StdDraw.setPenRadius(.02);
            for(int i =0;i<n;i++){
                points[i].draw();
            } 
               

            int n =100;
            Point2D[] points = new Point2D[n];
            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(0, 1200);
            StdDraw.setYscale(0, 1200);
            StdDraw.setPenRadius(.01);
         
             for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(1000);
            int y = StdRandom.uniform(1000);
            points[i] = new Point2D(x, y);
            points[i].draw();
                }
             int[] result = ConvexHullVertex(points);
             //int[] result2 = ConvexHullVertex2(points);
             
           System.out.println(""======="");
             
             StdDraw.setPenColor(StdDraw.BLUE);
             for (int i=0; i < result.length;i++){
                 System.out.print(result[i]);
             points[result[i]].draw();    
             }
             /*
                 System.out.println(""~~~"");
             StdDraw.setPenColor(StdDraw.RED);
             for (int i=0; i < result2.length;i++){
                 System.out.print(result2[i]);
             points[result2[i]].draw();    
             }
            */   
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

        }
    }
    
}

