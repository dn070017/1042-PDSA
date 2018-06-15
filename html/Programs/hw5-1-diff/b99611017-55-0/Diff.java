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
        
        Queue ans = new Queue();
        GrahamScan preans = new GrahamScan(a);
        for (Point2D p : preans.hull()) {
            ans.enqueue(p);
        }
        int[] an=new int[ans.size()];
        for(int i=0;i<ans.size();i++)
        {
            an[i]=Integer.parseInt(ans.dequeue().toString());
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
            
            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(0, 1);
            StdDraw.setYscale(0, 1);
            StdDraw.setPenRadius(0.015);
            
            for (int i = 0; i <= N; i++) {
                
                double x = Double.parseDouble(token[i * 2 + 2]);
                double y = Double.parseDouble(token[i * 2 + 3]);
                points[i] = new Point2D(x, y);
                //points[i].draw();
            }

// TODO code application logic here
        } catch (RuntimeException e) {
            
            throw e;
        }
    }
}

