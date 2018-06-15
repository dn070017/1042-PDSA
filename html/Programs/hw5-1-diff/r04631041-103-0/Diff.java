public class MyConvexHull {
    
    public static void ConvexHullVertex(int N) {
        Point2D[] data=new Point2D[N] ;
        Point2D[] sortdata=new Point2D[N] ;
        for (int i=0 ; i<N ; i++){
        double x=StdRandom.uniform();
        double y=StdRandom.uniform();
        data[i]=new Point2D(x,y);
        sortdata[i]=new Point2D(x,y);
        }
//--------------------Find the less y and set to data[0]----------------------//
        int minindex=0; 
        double miny=sortdata[0].y();
        for (int i =0 ; i<N ; i++)
            if (sortdata[i].y()<miny){
            miny=sortdata[i].y();
            minindex=i;
            }
        double maxx=sortdata[minindex].x();
        for (int i=0 ; i<N ;i++)
        if(sortdata[i].y()==miny && sortdata[i].x()>maxx){
            maxx=sortdata[i].x();
            minindex=i;
        }
        Point2D swap=sortdata[minindex];
        sortdata[minindex]=sortdata[0];
        sortdata[0]=swap;
//-----------------------------Sort by polar angle----------------------------//
        Point2D p=new Point2D(sortdata[0].x(),sortdata[0].y());
        Selection.sort(sortdata,p.polarOrder());
//------------------------Store the imformation of sorting--------------------//
        int[] sortimformation=new int[N];
        for (int i=0 ; i<N ; i++){
        for (int j=0 ; j<N ; j++){
            if (data[j].x()==sortdata[i].x()&&data[j].y()==sortdata[i].y())
                sortimformation[i]=j;
                
        }
        }
//--------------------------------Find ccw------------------------------------//
        Stack<Point2D> convexhull=new Stack<Point2D>();
        convexhull.push(sortdata[0]);
        convexhull.push(sortdata[1]); 
        for (int i=2 ; i<N ; i++){
            Point2D temp1=convexhull.pop();
            Point2D temp2=convexhull.pop();
            while(sortdata[i].ccw(temp2,temp1,sortdata[i])<0){
                temp1=temp2;
                temp2=convexhull.pop();
            }
            convexhull.push(temp2);
            convexhull.push(temp1);
            convexhull.push(sortdata[i]);
        }
        while(convexhull.size()!=0){
            Point2D temp=convexhull.pop();
            for(int i=0 ; i<N ; i++){
                if (sortdata[i].equals(temp)){
                    System.out.println(sortimformation[i]);
                }
            }
        }
        
//-------------------------------Show the ans---------------------------------//
        //StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(data[0].x(),data[0].y(), 0.01);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.text(data[0].x(),data[0].y()+0.02,String.valueOf(0));
//        for (int i=1 ; i<N ; i++){
//           StdDraw.filledCircle(data[i].x(), data[i].y(), 0.01);
//           StdDraw.text(data[i].x(), data[i].y()+0.02, String.valueOf(i));
//        }
        //for (int i=0 ; i<N ; i++)
        //    System.out.println(sortimformation[i]);
  
        
    }
 
}

