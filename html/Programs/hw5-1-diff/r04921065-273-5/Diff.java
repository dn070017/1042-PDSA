import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;



public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        Point2D[] points = new Point2D[a.length];
        for(int i=0;i<a.length;i++){
            points[i] = a[i];
        }
        
        
        Merge.sort(points);
        Arrays.sort(points, points[0].POLAR_ORDER);
        Stack<Point2D> vals = new Stack<Point2D>();
        
        int N = a.length;
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        //if (k1 == N) return;        // all points equal
        vals.push(points[0]);
        vals.push(points[k1]);
        // find index k2 of first point not collinear with points[0] and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        vals.push(points[k2-1]);    // points[k2-1] is second extreme point

        // Graham scan; note that points[N-1] is extreme point different from points[0]
        for (int i = k2; i < N; i++) {
            Point2D top = vals.pop();
            while (Point2D.ccw(vals.peek(), top, points[i]) <= 0) {
                top = vals.pop();
            }
            vals.push(top);
            vals.push(points[i]);
        }
        int number = vals.size();
        Point2D[] vertex = new Point2D[number];
        for(int u = 0;u<number;u++){
            vertex[u] = vals.pop();
        }
        int[] index = new int[number];
        int counter=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<number;j++){
                if(a[i].equals(vertex[j])){
                    index[counter]=i;
                    counter++;
                } 
            }
        }
        
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
            return index;
    }

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            String data = br.readLine();
            double connect = Double.parseDouble(data);
            String data2 = br.readLine();
            int size = Integer.parseInt(data2);
            Point2D[] points = new Point2D[size];
            String open;
            String [] temp = new String[2];
            for(int i=0;i<size;i++){
                open = br.readLine();
                temp = open.split("" "");
                points[i] = new Point2D(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
            }
            
            QuickFindUF wei = new QuickFindUF(size);
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(!wei.connected(i, j)){
                        if(points[i].distanceTo(points[j])<=connect) wei.union(i, j);
                    }                        
                }
            }/*
            for(int i=0; i<size;i++){
                System.out.println(wei.find(i));
            }*/
            int[] num = new int[size];
            for(int i = 0;i<size;i++){
                num[i]=0;
            }
            Point2D[][] t = new Point2D[size][size];
            for(int i = 0;i<size;i++){
                t[wei.find(i)][num[wei.find(i)]]=points[i];
                num[wei.find(i)]++;
            }
            int result=0;
            int[] temp3 = new int[size];
            //Point2D[] t2 = new Point2D[size];
            for(int i=0;i<size;i++){
            if(num[i]>=3){
                Point2D[] t2 = new Point2D[num[i]];
                for(int j=0;j<num[i];j++) t2[j] = t[i][j];
                temp3 = MyConvexHull.ConvexHullVertex(t2);
                result = result+temp3.length;
            }
            //System.out.println(temp3.length);
            //System.out.println(num[i]);
        }
            System.out.println(result);
        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it
        }
    }
}

