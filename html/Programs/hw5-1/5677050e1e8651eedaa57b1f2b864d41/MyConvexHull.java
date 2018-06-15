
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import static java.lang.Math.atan;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author huangchienpeng
 */
public class MyConvexHull {
    public static double cross(Point2D o, Point2D a, Point2D b){
        return (a.x()-o.x())*(b.y()-a.y())-(a.y()-o.y())*(b.x()-a.x());
    }
    public static boolean ccw(Point2D o, Point2D a, Point2D b){
        boolean ans = false;
        if(cross(o,a,b)>0){
            ans = true;
        }
        else
            ans = false;
        return ans;
    }
    public static Point2D[] sort(Point2D[] a){
        //Find the lowest point
        Point2D p = new Point2D(0,0);
        p = a[0];
        for(int i = 0; i < a.length; i++){
            if(p.y()>a[i].y()){
                p = a[i];
            }
        }
        //Create array to store every point's angle
        double[] angle = new double[a.length];
        for(int i = 0; i < a.length; i++){
            angle[i] = atan((a[0].x()-p.x())/(a[0].y()-p.y()));
        }
        //Sort Point2D array a 
        Point2D temppoint = new Point2D(0,0);
        double tempangle = 0;
        for(int i = 0; i < a.length; i++){
            for(int j = i+1; j < a.length; j++){
                if(angle[i]<angle[j]){
                    tempangle = angle[i];
                    angle[i] = angle[j];
                    angle[j] = tempangle;
                    temppoint = a[i];
                    a[i] = a[j];
                    a[j] = temppoint;
                }
            }
        }
        return a;
    }
    public static int[] ConvexHullVertex(Point2D[] a){
        sort(a);
        //Create Point2D array to store convexhullvertex's points
        Stack <Point2D> vertex = new Stack <Point2D>();
        vertex.push(a[0]);
        vertex.push(a[1]);
        for(int i = 2; i < a.length; i++){
            Point2D secondpoint = vertex.pop();
            while(!ccw(vertex.peek(),secondpoint,a[i])){
                secondpoint = vertex.pop();
            }
            vertex.push(secondpoint);
            vertex.push(a[i]);
        }
        //Create int array to store points' index
        int[] p = new int[vertex.size()];
        int j = 0;
        for (Point2D q : vertex) {
             p[vertex.size()- j - 1] = Arrays.asList(a).indexOf(q);
             j++;
        }
        
        return p;
    }
}

