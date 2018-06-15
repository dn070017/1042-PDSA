
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author USER
 */
public class MyConvexHull {
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] data=new Point2D[a.length] ;
        Point2D[] sortdata=new Point2D[a.length] ;
        for (int i=0 ; i<a.length ; i++){
        double x=a[i].x();
        double y=a[i].y();
        data[i]=new Point2D(x,y);
        sortdata[i]=new Point2D(x,y);
        }
//--------------------Find the less y and set to data[0]----------------------//
        int minindex=0; 
        double miny=sortdata[0].y();
        for (int i =0 ; i<a.length ; i++)
            if (sortdata[i].y()<miny){
            miny=sortdata[i].y();
            minindex=i;
            }
        double maxx=sortdata[minindex].x();
        for (int i=0 ; i<a.length ;i++)
        if(sortdata[i].y()==miny && sortdata[i].x()>maxx){
            maxx=sortdata[i].x();
            minindex=i;
        }
        Point2D swap=sortdata[minindex];
        sortdata[minindex]=sortdata[0];
        sortdata[0]=swap;
//-----------------------------Sort by polar angle----------------------------//
        Selection.sort(sortdata,sortdata[0].POLAR_ORDER);
//------------------------Store the imformation of sorting--------------------//
        int[] sortimformation=new int[a.length];
        for (int i=0 ; i<a.length ; i++){
        for (int j=0 ; j<a.length ; j++){
            if (data[j].x()==sortdata[i].x()&&data[j].y()==sortdata[i].y())
                sortimformation[i]=j;
                
        }
        }
//--------------------------------Find ccw------------------------------------//
        Stack<Point2D> convexhull=new Stack<Point2D>();
        convexhull.push(sortdata[0]);
        convexhull.push(sortdata[1]);

        for (int i=2 ; i<a.length ; i++){
            Point2D temp1=convexhull.pop();
            Point2D temp2=convexhull.pop();
            if (sortdata[i].ccw(temp2,temp1,sortdata[i])!=0){
            while(sortdata[i].ccw(temp2,temp1,sortdata[i])<0){
                temp1=temp2;
                temp2=convexhull.pop();
                
               }
            convexhull.push(temp2);
            convexhull.push(temp1);
            convexhull.push(sortdata[i]);
            }
            else{
                convexhull.push(temp2);
                convexhull.push(sortdata[i]);
            }
        }
        int counter=0;
        int[] ans=new int[convexhull.size()];
        while(convexhull.size()!=0){
            Point2D temp=convexhull.pop();
            for(int i=0 ; i<a.length ; i++){
                if (sortdata[i].equals(temp)){
                ans[counter]=sortimformation[i];
               }
            }
            counter++;
        }
       Arrays.sort(ans);
     return (ans);
    }
 public static void main(String[] args)  throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            // 1. read in the file containing N 2-dimentional points
            String buf0 = br.readLine();
            float lans = Float.valueOf(buf0);
            
            String buf1 = br.readLine();
            int n = Integer.valueOf(buf1);
            
            Point2D[] points = new Point2D[n];
            int count = 0;
            while (br.ready())
            {      
                String buf2[] = br.readLine().split("" "");
                double x = Double.valueOf(buf2[0]);
                double y = Double.valueOf(buf2[1]);
//                //===============================
//                double x = StdRandom.uniform(0.01,0.99);
//                double y = StdRandom.uniform(0.01,0.99);
//                //===============================
                points[count] = new Point2D(x, y);
                count++;
//                //===============================
//                StdDraw.setPenRadius(0.01);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                StdDraw.point(x,y);
//                //===============================
            }
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.GREEN);
//            StdDraw.line(0.9,0.1,0.9,0.1+lans);
            
            // 2. create an edge for each pair of points with a distance <= d
            QuickUnionUF uf = new QuickUnionUF(n);
            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){
                    if(i!=j){
                     if(points[i].distanceTo(points[j])<=lans){
                         uf.union(j,i);
//                         StdDraw.setPenRadius(0.001);
//                         StdDraw.setPenColor(StdDraw.BLUE);
//                         StdDraw.line(points[j].x(),points[j].y(),points[i].x(),points[i].y());
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
//                   System.out.println(uf.find(i));
               }
            }
            int cccount = leadpoint1.size();
            
            for(int i=0;i<cccount;i++){
                hold = leadpoint1.pop();
//                System.out.println(hold);
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
                       //System.out.print(j);
                   } 
                }
                Point2D[] point1s = new Point2D[counter];
                for(int z=0;z<counter;z++){
                    int b = cc.pop();
                    point1s[z] = new Point2D(points[b].x(),points[b].y());
                }
//                System.out.println(""input size: ""+point1s.length);
                int index[] =  ConvexHullVertex(point1s);
                
//                //==================================
//                for(int j=0;j<index.length;j++){
//                   System.out.print(index[j]);
//                }
//                System.out.println();
//                //===================================
                if(index.length>2){
                    ans = ans+index.length;
                }
                counter = 0;
            }
            // 5. count the number of points in N serving as a convex hull vertex, print it
            System.out.println(ans);  
        }
    }
}

