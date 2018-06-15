
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
   public static void main(String[] args) throws Exception{
        
        try (BufferedReader br = new BufferedReader (new FileReader(args[0])))
        {
        double r = Double.parseDouble(br.readLine());
        int N = Integer.parseInt(br.readLine());
        
        Point2D[] p = new Point2D[N];
        UF uf = new UF(N);
        
        
        for(int i = 0; i < N; i++)
        {
            String[] d1 =br.readLine().split("" "");
            p[i] = new Point2D(Double.parseDouble(d1[0]),Double.parseDouble(d1[1]));
//            System.out.print(p[i]+""\n"");
//             StdDraw.filledCircle(p[i].x(), p[i].y(), 0.01);
//            StdDraw.text(p[i].x(), p[i].y() + 0.03, Integer.toString(i));
        }   
        
        for(int i = 0; i < N; i++)
        {
                for(int j = i + 1; j < N; j++ )
                {
                    if(p[j].distanceTo(p[i]) <= r)
                    {
                        uf.union(j, i);
//                        StdDraw.line(p[i].x(), p[i].y(), p[j].x(), p[j].y());
//                        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
                    }
                }   
        }
        
        int next = -1, index = 0, count = 0, pts = 0, index1 = 0;
        Stack<Point2D> pcc = new Stack<Point2D>();
        
        for(int i = 0; i < N; i++)
        {
           
            if( next != i)
            {
                index = 0;
                index1 = 0;
                count = 0;
                pcc.push(p[i]);
                for(int j = i + 1; j < N; j++)
                {
                    if(uf.connected(i, j))
                    {
                        pcc.push(p[j]);
                        if(count == 0)
                        {
                            count = 1;
                            next = j;
                        }
                    }  
                }
                Point2D[] pt = new Point2D[pcc.size()];
                int[] v = new int [pt.length];
//                System.out.print(pcc.size()+""\n"");
                while(!pcc.isEmpty())
                {
//                    System.out.print(pcc.pop());
                    pt[index1] = pcc.pop();
//                    System.out.print(pt[index1]+""\n"");
                    index1 += 1;
                }
//                System.out.print(pt.length+""\n"");
                if(pt.length > 2)
                {
                    v = ConvexHullVertex(pt);
//                    System.out.print(v.length);
                    pts += v.length;
                }
               pt = null;
               v = null;
//                 System.out.print(pts+""\n"");
//                 System.out.print(i);
            }
            else
            {
                for(int j = i + 1; j < N; j++)
                {
                    count = 0;
                    if(uf.connected(i, j))
                    {
                        if(0 == count)
                        {
                            count = 1;
                            next = j;
                            break;
                        }
                    }
                }
            }
        }
        
        System.out.println(pts);
      
        }
    }
}

