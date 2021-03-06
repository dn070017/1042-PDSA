
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
         
         int K = a.length;
         int min = 0;
         Double[] degree = new Double [K];
         Double minX = a[0].x();
         Double minY = a[0].y();
         Stack<Integer> cnxpoint = new Stack<Integer>(); 
         //************************* find min ********************************\\
         int[] orderlist = new int[K+1];
         for(int i=0; i<K; i++){
             orderlist[i]=i;
             if(a[i].y()<minY){
                 minX = a[i].x();
                 minY = a[i].y();
                 min = i;
             }             
         }
         int temp;  Double tempX,tempY;
        //************************** degree stuff*****************************\\
         for(int i=0; i<K; i++){
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
        orderlist[K]=orderlist[0];
        //*********************** ccw link ***********************************\\
         
           int CNXcount = 0;
           while(CNXcount<=K){ 
                   // System.out.println(cnxpoint.size());
                    if(cnxpoint.size()<3){ cnxpoint.push(orderlist[CNXcount]); CNXcount++;}
                    else if(cnxpoint.size()>=3){
                        
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
     /*  for(int i=0; i<N; i++){
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
    
    
     public static void main(String[] args)  throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            // 1. read in the file containing N 2-dimentional points
            String buf0 = br.readLine();
            float okan = Float.valueOf(buf0);
            
            String buf1 = br.readLine();
            int n = Integer.valueOf(buf1);
            
            Point2D[] points = new Point2D[n];
            int count2D = 0;
            while (br.ready())
            {      
                String buf2[] = br.readLine().split("" "");
                double x = Double.valueOf(buf2[0]);
                double y = Double.valueOf(buf2[1]);
                points[count2D] = new Point2D(x, y);
                count2D++;
            }
            // 2. create an edge for each pair of points with a distance <= d
            QuickUnionUF uf = new QuickUnionUF(n);
            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){
                    if(i!=j){
                     if(points[i].distanceTo(points[j])<=okan){
                         uf.union(j,i);
                     }
                   }
                }
            }
            // 3. find connected components (CCs) with a size >= 3
            int hold = 0;
            
            int cccount3 = 0;
            Stack<Integer> leadpoint1 = new Stack<Integer>();
            Stack<Integer> leadpoint = new Stack<Integer>();

            for(int i=0;i<n;i++){
               if(i==uf.find(i)){
                   leadpoint1.push(uf.find(i));
               }
            }
            int cccount = leadpoint1.size();
            
            for(int i=0;i<cccount;i++){
                hold = leadpoint1.pop();
                for(int j=0;j<n;j++){
                   if(uf.find(j)==hold){
                      cccount3++; 
                   }
                }
                if(cccount3>2){
                   leadpoint.push(hold);
                   
                   cccount3 = 0;
                }  
            }
            
            int cclead[];
            cclead = new int[leadpoint.size()];
            int leadcccount = leadpoint.size();
            
            cclead = new int[leadcccount];
            for(int i=0;i<leadcccount;i++){
                cclead[i] = leadpoint.pop();
            }
            
            // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
            int counter = 0;
            Stack<Integer> cc = new Stack<Integer>();
            int ans = 0;
            for(int i=0;i<leadcccount;i++){
                for(int j=0;j<n;j++){
                   if(Integer.valueOf(uf.find(j))==cclead[i]){
                       counter++;
                       cc.push(j);
                  } 
                }
                Point2D[] point1s = new Point2D[counter];
                for(int z=0;z<counter;z++){
                    int b = cc.pop();
                    point1s[z] = new Point2D(points[b].x(),points[b].y());

                }

                
                int index[] =  ConvexHullVertex(point1s);
                if(index.length>2){
                    ans = ans+index.length;
                }
                counter = 0;
            }
            // 5. count the number of points in N serving as a convex hull vertex, print it
            System.out.println(ans);  
        }
    } 
     
     
/*   public static void main(String[] args) throws IOException{
       
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
       System.out.println(ConvexHullVertex(dotdata).length);
    }*/
}
    
  
