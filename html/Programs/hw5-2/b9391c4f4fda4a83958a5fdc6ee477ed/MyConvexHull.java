/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author 許志鵬
 */
public class MyConvexHull {
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        
        Queue ansl = new Queue();
        GrahamScan preans = new GrahamScan(a);
        for (Point2D p : preans.hull()) {
            ansl.enqueue(p);
        }
        int[] an=new int[ansl.size()];
        int count = 0;
        for(int i=0;i<a.length;i++)
        {
            for (Point2D p: preans.hull()){
                if (p == a[i]){
                    an[count] = i;
                    count ++;
                }
            }
        }
        return an;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
       try {
            InputStream is = new FileInputStream(args[0]);
            
            InputStreamReader isr = new InputStreamReader(is, ""UTF8"");
            
            BufferedReader br = new BufferedReader(isr);
            
            Scanner sn = new Scanner(br);
            
            StringBuilder buf = new StringBuilder();
            
            while (sn.hasNext()) {
                
                buf.append(sn.next()).append(""\n"");
                
            }
            
            String str = buf.toString();
            
            String[] token = str.split(""\n"");
            
            double d = Double.parseDouble(token[0]);
            
            int N = Integer.parseInt(token[1]);
            
            Point2D[] points = new Point2D[N];

//            StdDraw.setCanvasSize(500, 500);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenRadius(0.015);

            for (int i = 0; i < N; i++) {
                
                double x = Double.parseDouble(token[i * 2 + 2]);
                double y = Double.parseDouble(token[i * 2 + 3]);
                points[i] = new Point2D(x, y);
                
            }
            int[] ans=ConvexHullVertex(points);

            UF excute=new UF(points.length);
            
            for(int i=0;i<points.length;i++)
            {
                for(int j=i+1;j<points.length;j++)
                {
                    if(points[i].distanceTo(points[j])<=d)
                    {
                        excute.union(i, j);
                    }
                }
            }
            Stack<Point2D> intermid=new Stack<Point2D>();
            int[] groups=new int[excute.count()];
            int k=0;
            int size1=0;
            int size2=0;
            for(int i=0;i<points.length;i++)
            {
                for(int j=0;j<points.length;j++)
                {
                    if(i==excute.find(j))
                    {
                        intermid.push(points[j]);
                        groups[k]++;
                        size2++;
                    }
                }
                if(size2!=size1)
                {
                    k++;
                    size1=size2;
                }
            }
            int report=0;
            for(int i=excute.count()-1;i>=0;i--)
            {
                Point2D[] finale=new Point2D[groups[i]];
                        
                for(int j=0;j<groups[i];j++)
                {
                    finale[j]=intermid.pop();
                }
                report=report+ConvexHullVertex(finale).length;
                
            }
            System.out.print(report);
        } catch (RuntimeException e) {
            
            throw e;
        }
    }
}

