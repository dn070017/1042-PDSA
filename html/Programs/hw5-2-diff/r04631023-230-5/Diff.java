import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MyConvexHull {
    private static double AngleTo(Point2D a, Point2D b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        return Math.atan2(dy, dx);
    }
    private static double Distance(Point2D a, Point2D b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) ;
    }
    

    public static int[] ConvexHullVertex(Point2D[] a) {
        double min = a[0].y();
        double[] angle = new double[a.length] ;
        double[] angleSort = new double[a.length] ;
        int[] indexSort = new int[a.length] ;
        int[] indexNum = new int[a.length] ;
        Stack<Integer> convexHull = new Stack<Integer>();
        int index = 0 ;
        
        for (int i = 0 ; i < a.length ; i++){
            if (a[i].y() < min){
                min = a[i].y() ;
                index = i ;
            }
        }
        for (int i = 0; i < a.length ; i++){
            angle[i] = 180+AngleTo(a[index], a[i])* 180/Math.PI ;
            if (angle[i] == 360){
                angle[i] = 0 ;
            }
            angleSort[i] = angle[i] ;
        }
        Arrays.sort(angleSort);
        for (int i = 0; i < a.length ; i++){
            indexSort[i] = Arrays.binarySearch(angleSort, angle[i]) ;
            indexNum[indexSort[i]] = i ;
        }
        
        convexHull.push(index);
        convexHull.push(indexNum[0]);
        int count = 1 ;
        while (count != a.length){
            int sec = convexHull.pop() ;
            int first = convexHull.pop() ;
            if (Point2D.ccw(a[first], a[sec], a[indexNum[count]]) == 1){
                convexHull.push(first);
                convexHull.push(sec);
                convexHull.push(indexNum[count]);
                count++ ;
            }
            else{
                convexHull.push(first);
            }
        }
        
        int[] answer = new int[convexHull.size()-1] ;
        count = 0 ;
        for (int i = 0; i < answer.length; i++){
            answer[i] = convexHull.pop() ;
        }
        /*
        while(!convexHull.isEmpty()){
            System.out.println(convexHull.pop());
        }
        
        //draw
        for (int i = 0; i < a.length; i++) {
            StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
            StdDraw.text(a[i].x(), a[i].y()+0.03, String.valueOf(indexSort[i]));
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(a[index].x(), a[index].y(), 0.01) ;
        while(!convexHull.isEmpty()){
            int x = convexHull.pop() ;
            StdDraw.filledCircle(a[x].x(), a[x].y(), 0.01) ;
        }
        */
        if (answer.length < 3){
            int[] empty = new int[0] ;
            return empty ;
        }else{
            return answer ;
        }
    }
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            double Dis = Double.parseDouble(data);
            
            String Num = br.readLine();
            int N = Integer.parseInt(Num);
            
            int count = 0 ;
            int sumNum = 0 ;
            int[] groupNum = new int[N] ;
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
            Point2D[] a = new Point2D[N];
            String[] Loc;
            for (int i = 0; i < N; i++) {
                Loc = br.readLine().split("" "");
                a[i] = new Point2D(Double.parseDouble(Loc[0]), Double.parseDouble(Loc[1]));
                for (int j = i-1; j >= 0; j--){
                    if (a[i].distanceTo(a[j]) <= Dis){
                        if (!uf.connected(i, j)){
                            uf.union(i, j);
                        }
                    }
                }
            }
            
            for (int i = 0; i < N; i++){
                groupNum[uf.find(i)] =  groupNum[uf.find(i)] + 1 ;
            }
            
            for (int i = 0; i < N; i++){
                if (groupNum[i] > 3){
                    Point2D[] group = new Point2D[groupNum[i]];
                    count = 0 ;
                    for (int j = 0 ; j < N; j++){
                        if (uf.find(j) == i){
                            group[count] = new Point2D(a[j].x(), a[j].y());
                            count++ ;
                            if (count == groupNum[i]){
                                break ;
                            }
                        }
                    }
                    int[] convexIndex = ConvexHullVertex(group) ;
                    sumNum = sumNum + convexIndex.length ;
                }
                if (groupNum[i] == 3){
                    sumNum = sumNum + 3 ;
                }
            }
            System.out.println(sumNum) ;
            /*
            int[] convexIndex = ConvexHullVertex(a) ;
            for (int i = 0; i < convexIndex.length; i++){
                System.out.println(convexIndex[i]) ;
            }
            */
        }
    }
}
