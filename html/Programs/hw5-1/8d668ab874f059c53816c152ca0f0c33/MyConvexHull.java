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
         
         Point2D p = new Point2D(0, 0);
         //Arrays.sort(a, p.X_ORDER);
         Arrays.sort(point, p.Y_ORDER);
         
         Arrays.sort(point, point[0].POLAR_ORDER);
        /*for(int i =0;i<n;i++){
            System.out.println(a[i].x());
            System.out.println(a[i].y());
         } 
         */
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
       Stack<Integer> stack = new Stack<Integer>(); 
       int p0=0,p1=1,p2=2;
       for(int i=2;i<n;){
           //System.out.print(stack.size());
           //System.out.print(' ');
           int x = Point2D.ccw(point[p0],point[p1],point[p2]);
           if(x==1){
           stack.push(p0);
           //System.out.print(p0);
           //System.out.print(p1);
           //System.out.println(p2);
           p0=p1;p1=p2;p2=i+1;
           i++;
           }
           else{
           //System.out.print('_');
           //System.out.print(p0);
           //System.out.print(p1);
           //System.out.println(p2);
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
       int[] result =new int[a.length];
       
       for(int i=0;i<s;i++){
           for(int j=0;j<n;j++){
               if(point[array[i]].equals(a[j])){
                   result[i]=j;
                }
           }
       }
       for(int x=0;x<s;x++){
          // System.out.println(result[x]);
           
       }
     
       
        return result;
       
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
              ConvexHullVertex(points);
             
               
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

        }
    }
    
}

