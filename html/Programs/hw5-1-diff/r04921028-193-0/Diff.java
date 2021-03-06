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
           Point2D[] points = new Point2D[N];
           for(int i=0;i<N;i++){
               points[i] = new Point2D(a[pindex[i]].x(), a[pindex[i]].y());
           }
           for(int i=0;i<pindex.length;i++){
               pindex[i] = i;
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
                        
                        if(Point2D.ccw(points[p1],points[p2],points[p3])>=0){
                           convexpoint.push(p1); 
                           convexpoint.push(p2);
                           convexpoint.push(p3);
                           
                           convexpoint.push(pindex[icounter]);
                           icounter++;
                           
                        }
                        else if(Point2D.ccw(points[p1],points[p2],points[p3])<0){                                                                        
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
//                StdDraw.line(points[index[o-1]].x()/100, points[index[o-1]].y()/100,points[index[o]].x()/100, points[index[o]].y()/100); 
//            }
//            StdDraw.line(points[index[convexsize-1]].x()/100, points[index[convexsize-1]].y()/100,minx/100, miny/100); 
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.RED);
//            StdDraw.point(points[index[0]].x()/100,points[index[0]].y()/100);
            return index;
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1

    }

    public static void main(String[] args) {
           int N = 20;
           
           Point2D[] a = new Point2D[N];

           for(int i =0;i<N;i++){
              double x = StdRandom.uniform(20,99);
              double y = StdRandom.uniform(20,99);
              a[i] = new Point2D(x, y);
              StdDraw.setPenRadius(0.01);
              StdDraw.setPenColor(StdDraw.BLUE);
              StdDraw.point(a[i].x()/100,a[i].y()/100);
           }
           
           
           int index[] = ConvexHullVertex(a);
           
           for(int i=0;i<index.length;i++){
               System.out.println(index[i]);
           }
           
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

    }
    
}

