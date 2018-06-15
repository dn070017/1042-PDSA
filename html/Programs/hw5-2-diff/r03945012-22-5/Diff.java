
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * 1042 PDSA
 * hw05-2_MyConvexHull
 * @author Robert
 */
public class MyConvexHull {
    public MyConvexHull(){}
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        GrahamScan graham = new GrahamScan(a);
        int count = 0;
        int size = 0;
        for (Point2D p : graham.hull()){
            for (int i = 0; i < a.length; i++) {
                if (p == a[i]){
                    size +=1;
                }
            }
        }
        int[] x = new int[size];
        for (Point2D p : graham.hull()){
            for (int i = 0; i < a.length; i++) {
                if (p == a[i]){
                    x[count] = i;
                    count +=1;
                }
            }
        }    
        return x;
    }


    public static void main(String[] args) throws Exception{
        
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
        
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(args[0]);
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);

            // read file.txt as inputdata
            Scanner inputdata = new Scanner(br);
            
            // first data = max distance (d)
            String data = inputdata.nextLine();
            double max_d = Double.parseDouble(data);
            
            // second data = N points
            String num = inputdata.nextLine();
            int N = Integer.parseInt(num);
            
            // rest data = 0-1 points
            Point2D[] myPoints = new Point2D[N];
            int k = 0;
            while (inputdata.hasNextLine()) {
                String[] tmp = inputdata.nextLine().split("" "");
                double x = Double.parseDouble(tmp[0]);
                double y = Double.parseDouble(tmp[1]);
                myPoints[k] = new Point2D(x, y);
                k++;
            }
            UF uf = new UF(N);
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (myPoints[i].distanceTo(myPoints[j]) <= max_d){
                        uf.union(i, j);
                    }
                }
            }
            int myGroups = uf.count();
            SET<Integer> myParents = new SET<Integer>();
            int[] myUFIndex = new int[N];
            for (int i = 0; i < N; i++){
                myUFIndex[i] = uf.find(i);
                myParents.add(myUFIndex[i]);
            }
            int myTotal = 0;
            for (Integer myVal : myParents) {
                int count =0;
                for (int i = 0; i < N; i++){
                    if (uf.connected(myVal, myUFIndex[i])){
                        count++;
                    }
                }
                Point2D[] myTmps = new Point2D[count];
                int m = 0;
                for (int j = 0; j < N; j++){
                    if (uf.connected(myVal, myUFIndex[j])){
                        double x = myPoints[j].x();
                        double y = myPoints[j].y();
                        myTmps[m] = new Point2D(x, y);
                        m++;
                    }
                }
                if (count >= 3){
                    int[] x = ConvexHullVertex(myTmps);
                    myTotal += x.length;
                }
            }
            StdOut.println(myTotal);
//            for (int m = 0; m < myGroups; m++){
//                for (int n = 0; n < N; n++){
//                    if (uf.connected(m, n)){
//                        Point2D[] myTmps = new Point2D[ ];
//                    }
//                }
//            
//            }
            

//            
//            for (int i =0; i < uf.count(); i++){
//                
//            }
//            StdOut.println(uf.count());
       
            
            
        }
        catch (FileNotFoundException | NumberFormatException e) {}
        finally {
            // releases resources associated with the streams
            if (is != null) { is.close(); }
            if (isr != null) { isr.close(); }
            if (br != null) { br.close(); }
        }
    }
}
