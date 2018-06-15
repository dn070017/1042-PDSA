import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author 林康維
 */
public class MyConvexHull {
    
     public static int ConvexHullVertex(Point2D[] a) {

        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
         
         Point2D[] temp1 = new Point2D [a.length] ;
         Comparable[] comp = new  Comparable[a.length];
         Comparable[] comp1 = new  Comparable[a.length];
         for(int i = 0 ; i < a.length ; i++){
             comp[i] = a[i].y();
             temp1[i] = a[i] ;
             comp1[i] = a[i].y();
         }
         for(int i = 0 ; i < a.length ; i++){
             
                //System.out.println(comp[i]);
                // System.out.println(comp1[i]);
         }
         Merge.sort(comp);
         for(int i = 0 ; i < a.length ; i++){                 
                 //System.out.println(comp[i]);
                 //System.out.println(comp1[i]);
             for (int j = 0 ; j < a.length ; j ++){
                 //int result = comp[i].compareTo(comp1[j]);
                 //System.out.println(result) ;
                 if (comp[i].compareTo(comp1[j])==0){
                 a[i] = temp1 [j] ;
                 }
             }
         }
         /*for(int i = 0 ; i < a.length ; i++){
            
         System.out.println(a[i].x());
         System.out.println(a[i].y());
         }*/
         Point2D orig = new Point2D(a[0].x() , a[0].y()) ;
         Point2D temp2 = new Point2D(a[0].x() , a[0].y()) ; 
         //此時temp2 為原點
         double theta = 0 ;
         
         int convexhullvertex = 1 ;
         Arrays.sort (a,temp2.polarOrder()) ;
         //由小到大排列
         
         double dy = a[1].y()-temp2.y() ;
         double dx = a[1].x()-temp2.x() ;
         theta = Math.atan2(dy,dx)*180 / 3.1415926 ;
         temp2 = a[1] ;
         //此時temp2為第一個出去的點
         
         System.out.println(theta) ;
         convexhullvertex ++ ;
        
         
         while(true){
             if(theta <= 180 && theta >0){
                  Arrays.sort (a,temp2.polarOrder()) ;
                  double dy1 = a[1].y() - temp2.y() ;
                  double dx1 = a[1].x() - temp2.x() ;
                  theta = Math.atan2(dy1, dx1) *180 / 3.1415926 ;
                  temp2 = a[1] ;
                  convexhullvertex ++ ;
                  System.out.println(theta) ;
                  System.out.println(convexhullvertex+""nope"") ;
             }
             else if( orig.x()==temp2.x() && orig.y()==temp2.y()){
                              convexhullvertex-- ;
                              break ;
                         }
             else if(theta < 0 ){
                 
                 MergeX.sort (a,temp2.polarOrder()) ; 
                 /*for(int k = 0 ; k <a.length; k++){
                    double dy2 = a [k]. y() -temp2 .y();
                     double dx2 = a [k] .x() -temp2 .x() ;
                     double theta2 = Math.atan2(dy2, dx2);
                     System.out.println(theta2) ;    
                 }*/
                 /*theta -= theta2 ;
                     temp2 = a[1] ;
                     convexhullvertex ++ ;
                  System.out.println(theta) ;
                  System.out.println(convexhullvertex) ;*/
                 for (int i = 0 ; i < a.length ; i++){
                     double dy2 = a [i]. y() -temp2 . y();
                     double dx2 = a [i] .x() -temp2 . x() ;
                     if(Math.atan2(dy2, dx2) < 0){
                         temp2 = a[i] ;
                         theta = Math.atan2(dy2,dx2) *180 / 3.1415926 ;
                         convexhullvertex ++;
                         System.out.println(theta) ;                         
                         System.out.println(convexhullvertex) ;
                     }
                 }
             }
         }
         return convexhullvertex ;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*File file = new File ( args[0] ) ;
        try{
        Scanner file_in = new Scanner( file ) ;
        
        String data1 = file_in.nextLine();
        double distance = Double.parseDouble(data1) ;
        
        String data2 = file_in.nextLine() ;
        int number = Integer.parseInt(data2) ;
        
        Point2D[] points = new Point2D [ number ] ;
        
        UF uf = new UF ( number ) ;
        double[][] matrix = new double [ number ][ 2 ] ;
        int count = 0 ;
        while(file_in.hasNextLine()){
            
            String[] data3 = file_in.nextLine().split("" "") ;
            double x = Double.parseDouble(data3[0]) ;
            double y = Double.parseDouble(data3[1]) ;
            matrix [count][0] = x ;
            matrix [count][1] = y ;
            points [ count ] = new Point2D (x,y) ;
            StdDraw.setPenRadius(0.01) ;
            points[count] . draw() ;
            for (int i = 0 ; i < count ; i++){
                if(points[count].distanceTo(points[i]) <= distance){
                    StdDraw.setPenRadius(0.001) ;
                    points[count].drawTo(points[i]) ;
                    uf.union(i,count);
                }
                if(points[count].compareTo(points[i])==1){
                }
            }
            //System.out.println(uf.find(count));
            count ++ ;
        }
        
        System.out.print(ConvexHullVertex(points)) ;*/
        /*Comparable [] a = new Comparable [number] ;
        
        for(int i = 0 ; i < number ; i++){
                a[i] = matrix [i][1] ;
            
        }
        Merge . sort ( a ) ;
        
        
        StdDraw.setPenRadius(0.01) ;
        for(int i = 0 ; i < number ; i++){
            StdDraw . point (matrix[i][0],matrix[i][1]) ;
        }
        for(int i = 0 ; i < number ; i++){
            for (int j = i ; j <number ; j++){
                double x1_x0 = Math.pow(matrix[i][0]-matrix[j][0],2);
                double y1_y0 = Math.pow(matrix[i][1]-matrix[j][1],2);
                if(Math.sqrt( x1_x0 + y1_y0 ) < distance){
                    StdDraw.setPenRadius(0.002) ;
                    StdDraw.line(matrix[i][0],matrix[i][1],matrix[j][0],matrix[j][1]) ;
                }
            }
        }
        int count1 = 0 ;
        for(int i = 0 ; i <number ; i++){
        if( a[0] . compareTo ( matrix[i][1] ) == 0){
            count1 = i ;
            StdDraw.setPenRadius(0.01) ;
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(matrix[i][0], matrix[i][1]);
            break;
                          }
                  }*/
        
        /*}
        catch(IOException e){
            System.out.println(""error!"");
        }*/
        // TODO code application logic here
    }
    
}

