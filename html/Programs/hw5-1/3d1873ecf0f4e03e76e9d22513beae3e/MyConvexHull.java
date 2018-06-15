
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LAB228
 */
public class MyConvexHull {
     public static int[] ConvexHullVertex(Point2D[] a) {
         
         int N = a.length;
         int min = 0;
         Double[] degree = new Double [N];
         Double minX = a[0].x();
         Double minY = a[0].y();
         Stack<Integer> cnxpoint = new Stack<Integer>(); 
         //************************* find min ********************************\\
         int[] orderlist = new int[N+1];
         for(int i=0; i<N; i++){
             orderlist[i]=i;
             if(a[i].y()<minY){
                 minX = a[i].x();
                 minY = a[i].y();
                 min = i;
             }             
         }
         int temp;  Double tempX,tempY;
        //************************** degree stuff*****************************\\
         for(int i=0; i<N; i++){
               degree[i]=Math.toDegrees(Math.atan2(a[i].y()-minY,a[i].x()-minX));
         }
        //***********************  sorting  **********************************\\
        
        for(int i=1; i<a.length; i++){
            Double tempD = degree[i];
            int    tempO = orderlist[i];
            int j=0;
                for( j=i-1; j>=0 && tempD < degree[j]; j--){
                    orderlist[j+1] = orderlist[j];
                    degree[j+1]    = degree [j];
                }               
            degree[j+1] = tempD;
            orderlist[j+1] = tempO;
        }
        orderlist[N]=orderlist[0];
        //*********************** ccw link ***********************************\\
         
           int CNXcount = 0;
           while(CNXcount<=N){ 
                   // System.out.println(convexpoint.size());
                    if(cnxpoint.size()<3){ cnxpoint.push(orderlist[CNXcount]); CNXcount++;}
                    if(cnxpoint.size()>=3){
                        
                        int p3=cnxpoint.pop();
                        int p2=cnxpoint.pop();
                        int p1=cnxpoint.pop();
                        
                        if(Point2D.ccw(a[p1],a[p2],a[p3])>=0){
                           cnxpoint.push(p1); 
                           cnxpoint.push(p2);
                           cnxpoint.push(p3);
                           
                           cnxpoint.push(orderlist[CNXcount]);
                           CNXcount++;
                           
                        }
                        else if(Point2D.ccw(a[p1],a[p2],a[p3])<0){                                                                        
                           cnxpoint.push(p1);
                           cnxpoint.push(p3);
                           
                        } 
                    }       
            }
            
            int counter = cnxpoint.size()-2;
            int convexsize =  cnxpoint.size()-1; 
            
            int index[];
            index = new int[convexsize];
            int trash = cnxpoint.pop();
            
            while(cnxpoint.size()!=0){
                index[counter] = cnxpoint.pop();
                counter--;
            }
        //************************ show dots**********************************\\
  /*      for(int i=0; i<N; i++){
             //  System.out.print(a[i].x()+""\t"");
             //  System.out.print(a[i].y()+""\t"");
               System.out.println(degree[i]);
         }
        for(int i=0; i<N+1; i++){
               System.out.print(orderlist[i]);
         }        
        //----------------------------data------------------------------------\\
                
         for(int i=0; i<N; i++){
            if(i==min){
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(a[i].x(),a[i].y());                
            }
            else{
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(a[i].x(),a[i].y());
            }
        }*/
        //--------------------------------------------------------------------\\
        return index;
      }
    
   public static void main(String[] args) throws IOException{
       
       Scanner scanner = new Scanner(System.in); 
       System.out.println(""How many random dot you want?"");
       int N = scanner.nextInt();
       System.out.printf(""Processing......""+""\n    creating %d dot\n"",N);
       
              
       Point2D[] dotdata = new Point2D [N]; //create dot data [dot order][two axis num// x: ,y: //]
       Point2D[] b = new Point2D [N];
       
       
       for(int i=0; i<N; i++){
           
                dotdata[i] =new Point2D(StdRandom.random(),StdRandom.random());
                System.out.println(dotdata[i].x()+""\t""+dotdata[i].y());
                           
       }
      // ConvexHullVertex(dotdata);
    }
}
    
  
