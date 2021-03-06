import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a) {
           int N = a.length;
           
           float PointAngle[];
           PointAngle = new float[N];
           
           int pindex[];
           pindex = new int[N+1];
           
           double minx = 0;
           double miny = 0;
           double min = 0;
           int n = 0;
           
           Stack<Integer> convexpoint = new Stack<Integer>(); 
           
           for(int i =0;i<N;i++){
               pindex[i] = i;
               
               if(i==0){
                 miny = a[0].y();
               }
               if(a[i].y()<miny){
                   miny = a[i].y();
                   minx = a[i].x(); 
                   min = i;
               }
           }
           
           for(int i =0;i<N;i++){
               if(i!=min){
                    PointAngle[i] = (float)Math.toDegrees(Math.atan2(a[i].y()-miny,a[i].x()-minx));
               }
           }
           
           int NPointAngle = PointAngle.length;
           float exch;
           int indexexch;
           for (int i = 0; i < NPointAngle; i++) {
                for (int j = i; j > 0 && PointAngle[j]<=PointAngle[j-1]; j--){ 
                    exch = PointAngle[j];
                    PointAngle[j] = PointAngle[j-1];
                    PointAngle[j-1] = exch;
                    
                    indexexch = pindex[j];
                    pindex[j] = pindex[j-1];
                    pindex[j-1] = indexexch;    
                }
           }
           
           
           
           pindex[N] = pindex[0];
           
           int icounter = 0;
           int lastccw = 0;
           while(icounter<=N){ 
                   // System.out.println(convexpoint.size());
                    if(convexpoint.size()<3){ convexpoint.push(pindex[icounter]); icounter++;}
                    if(convexpoint.size()>=3){
                        
                        int p3=convexpoint.pop();
                        int p2=convexpoint.pop();
                        int p1=convexpoint.pop();
                        
                        if(Point2D.ccw(a[p1],a[p2],a[p3])>=0){
                           convexpoint.push(p1); 
                           convexpoint.push(p2);
                           convexpoint.push(p3);
                           
                           convexpoint.push(pindex[icounter]);
                           icounter++;
                           
                        }
                        else if(Point2D.ccw(a[p1],a[p2],a[p3])<0){                                                                        
                           convexpoint.push(p1);
                           convexpoint.push(p3);
                           
                        } 
                    }       
            }
            
            int counter = convexpoint.size()-2;
            int convexsize =  convexpoint.size()-1; 
            
            int index[];
            index = new int[convexsize];
            int trash = convexpoint.pop();
            
            while(convexpoint.size()!=0){
                index[counter] = convexpoint.pop();
                counter--;
            }
//            for(int o =1;o<convexsize;o++){
//                StdDraw.setPenRadius(0.001);
//                StdDraw.line(a[index[o-1]].x()/100, a[index[o-1]].y()/100,a[index[o]].x()/100, a[index[o]].y()/100); 
//            }
//            StdDraw.line(a[index[convexsize-1]].x()/100, a[index[convexsize-1]].y()/100,minx/100, miny/100); 
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.point(a[index[0]].x()/100,a[index[0]].y()/100);
            return index;
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1

    }

    public static void main(String[] args)  throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String buf0 = br.readLine();
            float lans = Float.valueOf(buf0);
//            System.out.println(lans);
            
            String buf1 = br.readLine();
            int n = Integer.valueOf(buf1);
//            System.out.println(buf1);
            
            Point2D[] points = new Point2D[n];
            int count = 0;
            while (br.ready())
            {      
                String buf2[] = br.readLine().split("" "");
                double x = Double.valueOf(buf2[0]);
                double y = Double.valueOf(buf2[1]);
//                System.out.println(x+"" ""+y);
                points[count] = new Point2D(x, y);
                count++;
            }
            QuickUnionUF uf = new QuickUnionUF(n);
            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){
                    if(i!=j){
                     if(points[i].distanceTo(points[j])<=lans){
                         uf.union(j,i);
                     }
                   }
                }
            }
            
            int hold = 0;
            
            int cccount = 0;
            int cccount3 = 0;
            int cccountmax = 0;
            
            
            String hccarray[];
            hccarray = new String[n];
            
            
            
            for(int i=0;i<n;i++){
                hccarray[i] = String.valueOf(uf.find(i));
//                System.out.println(hccarray[i]);
            }
            
            
            Quick.sort(hccarray);
            Stack<Integer> leadpoint = new Stack<Integer>();
            
            for(int i=0;i<n;i++){
               if(hold == Integer.valueOf(uf.find(i))){
                   cccount3++;
               }
               else if(hold != Integer.valueOf(uf.find(i))&&cccount3>=2){
                   leadpoint.push(hold);
                   cccount3 = 0;
                   cccount++;
               }
               if(i==n-1&&cccount3>=2){
                   cccount3 = 0;
                   cccount++;
               }
               hold =  Integer.valueOf(uf.find(i)); 
               if(cccount3>cccountmax){
                   cccountmax = cccount3;
               }
            }
//            System.out.println();
//            System.out.println(cccount);
//            System.out.println(cccountmax);
//            
//            System.out.println();
//            System.out.println(leadpoint.pop());
//            System.out.println(leadpoint.pop());
            
            int cclead[];
            cclead = new int[cccount];
            for(int i=0;i<cccount;i++){
                cclead[i] = leadpoint.pop();
            }
            
            
            int counter = 0;
             Stack<Integer> cc = new Stack<Integer>();
             int ans = 0;
            for(int i=0;i<cccount;i++){
                for(int j=0;j<n;j++){
                   if(Integer.valueOf(uf.find(j))==cclead[i]){
                       counter++;
                       cc.push(i);
                   } 
                }
                Point2D[] point1s = new Point2D[counter];
                int b = cc.pop();
                for(int z=0;z<counter;z++){
                    point1s[z] = new Point2D(points[b].x(),points[b].y());
                }
                int index[] =  ConvexHullVertex(point1s);
                ans = ans+index.length;
                
                counter = 0;
                
            }
            
            System.out.println(ans);
            
            
            // 1. read in the file containing N 2-dimentional points
            // 2. create an edge for each pair of points with a distance <= d
            // 3. find connected components (CCs) with a size >= 3
            // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
            // 5. count the number of points in N serving as a convex hull vertex, print it
        }
    }
    
}

