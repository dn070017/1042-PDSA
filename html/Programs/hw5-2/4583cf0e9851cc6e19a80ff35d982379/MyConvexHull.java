import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Iterator;

public class MyConvexHull {
    
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
            double d = Double.parseDouble(data);
            data = br.readLine();
            int n = Integer.parseInt(data);
            Point2D[] points = new Point2D[n];
      
            for(int i=0;i < n ; i++){
                String cor[] = br.readLine().split("" "");
                points[i] = new Point2D(Double.parseDouble(cor[0]), Double.parseDouble(cor[1]));
                //points[i] = new Point2D(StdRandom.uniform(100),StdRandom.uniform(100));
            }
            
            //int[] result = ConvexHullVertex(points);
            
            UF flag = new UF(n);
            for(int i = 0;i<n;i++){
                for(int j=i;j<n;j++){
                    if(i==j);
                    else if(points[i].distanceTo(points[j]) <= d){
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

