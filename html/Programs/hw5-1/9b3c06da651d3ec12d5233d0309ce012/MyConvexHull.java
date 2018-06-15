import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;


/**
 *
 * @author Daniel
 */
public class MyConvexHull {
 
public static int[] ConvexHullVertex(Point2D[] s){
    ArrayList<Point2D> myarray = new ArrayList<Point2D>();
    for(Point2D a:s){
        myarray.add(a);
    }
    int miny = Findlowesty(s);
    Point2D first = s[miny];
    quickSort(s, first.atan2Order());
    Stack<Point2D> convex = new Stack<Point2D>();
    convex.push(s[0]);
    convex.push(s[1]);
    for(int i=2;i<s.length;i++){
        Point2D b =convex.pop();
        Point2D a = convex.pop();
        Point2D c =s[i];
        while(Point2D.ccw(a, b, c)!=1){
            b = a;
            a = convex.pop();
        }
        convex.push(a);
        convex.push(b);
        convex.push(c);
    }
        Stack<Point2D> oppo = new Stack<Point2D>();
        while(!convex.isEmpty()){
            oppo.push(convex.pop());
        }
        convex = oppo;
        Iterator<Point2D> it = convex.iterator();
        int count = 0;
        int[] ref = new int[convex.size()];
        while(it.hasNext()){
            Point2D n = it.next();
            ref[count++] = myarray.indexOf(n);
        }
    return ref;
}
    public static void quickSort(Point2D[] s, Comparator<Point2D> x){
        Sort_2D(s, x, 0, s.length-1);
    }
    
 
 
 
 public static int Findlowesty(Point2D[] f){
     int miniy = 0;
     for(int i =0; i<f.length;i++){
         if(Point2D.Y_ORDER.compare(f[miniy], f[i])==1){
             miniy = i;
         }

     }
     return miniy;
 }
 
 
// public static Point2D[] Relativepoint(Point2D[] a){
//     int miniy = Findlowesty(a);
//     Point2D[] a1 = new Point2D[a.length];
//     for (int i =0; i< a.length;++i){
//         a1[i] = new Point2D(a[i].x() - a[miniy].x(), a[i].y() - a[miniy].y());
//     }
//     return a1;
// }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private static void Sort_2D(Point2D[] s, Comparator<Point2D> x, int head, int end) {
       if(head>=end){
           return;
       }
       Point2D mid = s[head];
       int left = head;
       int right = end+1;
       while(true){
           while(x.compare(mid, s[++left])==1){
               if(left==end) break;
           }
           while(x.compare(mid, s[--right])==-1){
               if(right>=head) break;
           }
           if(left>=right) break;
           Point2D temp =s[left];
           s[left] = s[right];
           s[right] = temp;
           
       }
       
       s[head]= s[right];
       s[right] = mid;
       
       Sort_2D(s, x, head, right-1);
       Sort_2D(s, x, right+1, end);
       
 }
    
    
}

