import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Iterator;

public class MyConvexHull {
    
    public static int[] ConvexHullVertex(Point2D[] a) {
            Arrays.sort(a, Point2D.Y_ORDER );
            Arrays.sort(a, a[0].POLAR_ORDER );
            
            Stack<Integer> hull = new Stack<Integer>();
            int[] p = new int[3];
            p[0] = 0;p[1] = 1;p[2] = 2;
            
            int n = a.length;
            
            int counter = 2;
            while(counter < n){               
               if(Point2D.ccw(a[p[0]], a[p[1]], a[p[2]]) == 1){
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
            
            for(int i = 0; i < size; i++){
                result[i] = hull.pop();
            }
          
    return result;
    }
    
    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String data = br.readLine();
            double d = Double.parseDouble(data);
            data = br.readLine();
            int n = Integer.parseInt(data);
            Point2D[] points = new Point2D[n];
            
            for(int i=0;i < n ; i++){
                String cor[] = br.readLine().split("" "");
                points[i] = new Point2D(Double.parseDouble(cor[0]), Double.parseDouble(cor[1]));
            }
            
            
            int[] result = ConvexHullVertex(points);
            System.out.println(6);
/*            
            
            
           
            
            
            StdDraw.setCanvasSize(800, 800);
            StdDraw.setXscale(0, 1);
            StdDraw.setYscale(0, 1);
            StdDraw.setPenRadius(.01);
            

            
            StdDraw.setPenColor(StdDraw.BLUE);
            for(int i=0;i < n ; i++){
                points[i].draw();
            }
            
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.003);
            for(int i = 1; i < result.length; i++){
                points[result[i]].drawTo(points[result[i-1]]);
            }   
            points[result[result.length-1]].drawTo(points[result[0]]);


            for(int i = 0; i < n;i++){
                System.out.printf(""(%f,%f)\n"",points[i].x(),points[i].y());
            }
*/            
       
        }
    }
    public  static  void printQue(Stack<Integer> q  ){
            Iterator<Integer> iterator = q.iterator();        
            while(iterator.hasNext())
              System.out.printf(""%d,"",iterator.next()); 
             System.out.printf(""\n"" ); 
    }
    
}

