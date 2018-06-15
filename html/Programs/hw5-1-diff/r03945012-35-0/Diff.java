

/**
 * 1042 PDSA
 * hw05-1_MyConvexHull
 * @author Robert
 */
public class MyConvexHull {
    public MyConvexHull(){}
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        GrahamScan graham = new GrahamScan(a);
        int[] x = new int[a.length];
        int count = 0;
        for (Point2D p : graham.hull()){
            for (int i = 0; i < a.length; i++) {
                if (p == a[i]){
                    x[count] = i;
                    a[i].draw();
                    count +=1;
                }
            }
        }
        return x;
    }


    
    
    public static void main(String[] args) {

    }
    
}

