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
            
            int counter = convexpoint.size()-1;
            int convexsize =  convexpoint.size(); 
            
            int index[];
            index = new int[convexpoint.size()];
           
            while(convexpoint.size()!=0){
                index[counter] = convexpoint.pop();
                counter--;
            }
            return index;
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1

    }
