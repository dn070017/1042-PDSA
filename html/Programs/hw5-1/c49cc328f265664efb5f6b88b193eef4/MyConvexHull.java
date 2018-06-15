import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class MyConvexHull{
     public static int[] ConvexHullVertex(Point2D[] a) {
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
            /*  
            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(0, 1);
            StdDraw.setYscale(0, 1);
            StdDraw.setPenRadius(.02);
            for(int i =0;i<n;i++){
                points[i].draw();
            } 
*/
             int[] result = ConvexHullVertex(points);
             
               
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

        }
    }
    
}

