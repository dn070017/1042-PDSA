import java.awt.Color;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Random;
/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Ted
 */
public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {

        
        Point2D[] b = a.clone();
        Arrays.sort(b, Point2D.Y_ORDER);
//        StdDraw.setPenColor(StdDraw.RED);
        b[0].draw();
//        StdDraw.text(b[0].x(), 0.03 + b[0].y(),String.valueOf(0));
//        StdDraw.setPenColor(StdDraw.BLACK);
        
        Arrays.sort(b, b[0].POLAR_ORDER);
        
        for(int i = 1; i < b.length ;i++)
        {
            b[i].draw();
//            StdDraw.text(b[i].x(), 0.03 + b[i].y(),String.valueOf(i));
        }
        
        Stack<Point2D> _stack = new Stack<Point2D>();
        _stack.push(b[0]);
        _stack.push(b[1]);
        _stack.push(b[2]);
        int k = 3;
        int ccw = 0;
        while(k != b.length)
        {
            Point2D p3 = b[k++];
            Point2D p2 = _stack.pop();
            Point2D p1 = _stack.pop();
               
            ccw = Point2D.ccw(p1, p2, p3);
            if(ccw == 0)
            {
                p2 = p1;
                p1 = _stack.pop();   
            }
            else if(ccw == -1)
            {
                do
                {
                    p2 = p1;
                    p1 = _stack.pop();
                }while(!(Point2D.ccw(p1, p2, p3) == 1));
            }
            _stack.push(p1);
            _stack.push(p2);
            _stack.push(p3);
           
        }        
//         StdDraw.setPenColor(Color.yellow);
//         StdDraw.setPenRadius(.005);
         
        int[] id = new int[_stack.size()];
        int index = 0;
         
         Point2D d1 = _stack.pop();
         Point2D op = d1;
         int i = -1;
         while(!a[++i].equals(op))
         {
         }
         id[index++] = i;
       
         do
         {
             Point2D d2 = _stack.pop();
             i = -1;
             while(!a[++i].equals(d2))
             {
             }
             id[index++] = i;
            // StdDraw.line(d1.x(), d1.y(),d2.x(), d2.y());
             d1 = d2;
         }while(!(_stack.size() == 0));
         //StdDraw.line(d1.x(), d1.y(),op.x(), op.y());
         
         Arrays.sort(id);
       
        return id;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        int n = 10;
//        Point2D[] a = new Point2D[n];
//        StdDraw.setCanvasSize(800, 800);
//        StdDraw.setXscale(-0.2, 1.2);
//        StdDraw.setYscale(-0.2, 1.2);
//        StdDraw.setPenRadius(.01);
//         StdDraw.setPenColor(StdDraw.BLUE);
//        for(int i = 0; i < n ;i++)
//        {
//            a[i] = new  Point2D(StdRandom.uniform(),StdRandom.uniform());
//             StdDraw.text(a[i].x()+0.03 , 0.03 + a[i].y(),String.valueOf(i));
//        }
//        int[] k = ConvexHullVertex(a);
        
        
        
    }
    
}

