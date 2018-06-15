/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author Cyuan
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
    public static int[] ConvexHullVertex(Point2D [] a)
    {
        Point2D[] p = new Point2D[a.length];
        for(int i = 0; i < a.length; i++)
            p[i] = a[i];
        Insertion.sort(p, Point2D.Y_ORDER);
        Insertion.sort(p, p[0].POLAR_ORDER);
        Stack<Point2D> s = new Stack<Point2D>();
        s.push(p[0]);
        s.push(p[1]);
        s.push(p[2]);
        Point2D p1, p2, p3, p0;
        
        int w = 0,index = 2;
        while(index < (p.length))
        {
            p3 = s.pop();
            p2 = s.pop();
            p1 = s.pop();
            w = Point2D.ccw(p1, p2, p3);
          
            if (1 == w)
            {
                s.push(p1);
                s.push(p2);
                s.push(p3);
                index += 1;
                if(index < p.length)
                    s.push(p[index]);
            }
            else
            {
                s.push(p1);
                s.push(p3);
            }
        }
        int [] v = new int[s.size()];
        index = 0;
        while(!s.isEmpty())
        {
            p0 = s.pop();
            for (int i = 0; i < a.length; i++)
                if(p0.equals(a[i]))
                    v[index++] = i;
        }
//        Insertion.;
        Arrays.sort(v);
        return v;
    }
    
    
    public static void main(String[] args) throws Exception{
        
        try (BufferedReader br = new BufferedReader (new FileReader(args[0])))
        {
        double r = Double.parseDouble(br.readLine());
        int N = Integer.parseInt(br.readLine());
        
        Point2D[] p = new Point2D[N];
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        
        
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
        
        System.out.print(pts);
      
        }
    }
    
}

