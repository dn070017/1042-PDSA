
import java.util.Stack;
import java.util.Arrays;

/**
 *
 * @author Daniel
 */
public class MyConvexHull {
// Point2D[] xs = new Point2D[5];
 
public static int[] ConvexHullVertex(Point2D[] s){
    Point2D[] x = Findconvexhull(s);
    int[] ref = new int[x.length];
    for(int i =0;i<x.length;i++){
        for(int j =0;j<s.length;j++){
            if(x[i].equals(s[j])){
                ref[i] = j;
                break;
            }
        }
    }
    Arrays.sort(ref);
    return ref;
}
 
 private static Point2D[] Findconvexhull(Point2D[] s){
     if(s.length<3){
         return null;
     }
     Point2D[] a = Relativepoint(s.clone());
     a = mergesort2D(a);
     Stack<Point2D> stack = new Stack();
     stack.push(a[0]);
     stack.push(a[1]);
     for(int i = 2; i < a.length; i++){
         Point2D q;
         do {
             q =stack.pop(); 
         } while(Point2D.ccw(stack.lastElement(), q, a[i])<=0&&stack.size()>1);
         stack.push(q);
         stack.push(a[i]);
     }
     Point2D test;
     do{
     test = stack.pop();
     }while(Point2D.ccw(stack.lastElement(), test, stack.firstElement())<=0);
     stack.push(test);
     
     Point2D[] a2 = new Point2D[stack.size()];
     a2 = stack.toArray(a2);
     for (int i = 0; i < a2.length; ++i) {
            a2[i] = new Point2D(a2[i].x() + s[Findlowesty(s)].x(), a2[i].y() + s[Findlowesty(s)].y());
     }
     return a2;
 }
 
 public static int Findlowesty(Point2D[] f){
     int miniy = 0;
     for(int i =0; i<f.length;i++){
         if(f[i].y() < f[miniy].y()){
             miniy = i;
         }
         else if(f[i].y()==f[miniy].y() && f[i].x()<f[miniy].x()){
             miniy = i;
         }
     }
     return miniy;
 }
 public static Point2D[] mergesort2D(Point2D[] arr) {

        int size = arr.length;
        if (size < 2) {
            return arr;
        }
        int i;
        Point2D[] arr1 = new Point2D[size / 2];
        Point2D[] arr2 = new Point2D[size - size / 2];
        for (i = 0; i < size / 2; ++i) {
            arr1[i] = new Point2D(arr[i].x(), arr[i].y());
        }
        for (; i < size; ++i) {
            arr2[i - size / 2] = new Point2D(arr[i].x(), arr[i].y());
        }
        arr1 = mergesort2D(arr1);
        arr2 = mergesort2D(arr2);
        int j = size - size / 2 - 1;
        i = size / 2 - 1;
        int k = size - 1;
        Point2D[] arr3 = new Point2D[size];
        while (i >= 0 && j >= 0) {
            if (arr1[i].theta() >= arr2[j].theta()) {
                arr3[k--] = arr1[i--];
            } else {
                arr3[k--] = arr2[j--];
            }
        }
        while (i >= 0) {
            arr3[k--] = arr1[i--];
        }
        while (j >= 0) {
            arr3[k--] = arr2[j--];
        }
        return arr3;
    }
 
 public static Point2D[] Relativepoint(Point2D[] a){
     int miniy = Findlowesty(a);
     Point2D[] a1 = new Point2D[a.length];
     for (int i =0; i< a.length;++i){
         a1[i] = new Point2D(a[i].x() - a[miniy].x(), a[i].y() - a[miniy].y());
     }
     return a1;
 }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

