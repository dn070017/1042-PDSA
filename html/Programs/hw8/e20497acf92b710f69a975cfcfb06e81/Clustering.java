
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import static java.lang.Math.pow;

public class Clustering {

    private static class Distance implements Comparable<Distance>{
        private double point_x1;
        private double point_y1;
        private double point_x2;
        private double point_y2;
        private double distance;
        
        public Distance(double x1, double y1, double x2, double y2){
            this.point_x1 = x1;
            this.point_y1 = y1;
            this.point_x2 = x2;
            this.point_y2 = y2;
        }
        
        public int compareTo(Distance that){
            this.distance = pow((pow(this.point_x1-this.point_x2,2) + pow(this.point_y1-this.point_y2,2)),(1/2));
            this.distance = pow((pow(that.point_x1-that.point_x2,2) + pow(that.point_y1-that.point_y2,2)),(1/2));
            
            if(this.distance > that.distance)
                return 1;
            else if(this.distance < that.distance)
                return -1;
            else
                return 0;
        }
    }
    
    
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//            StdDraw.setCanvasSize(500, 500);
//            StdDraw.setPenColor(StdDraw.RED);
            int count = Integer.parseInt(br.readLine());
            double[][] point = new double[count][2];

            MinPQ<Distance> pq_distance = new MinPQ<Distance>(count);
            
            
            for (int i = 0; i < count; i++) {
                String[] a = br.readLine().split("" "");
                point[i][0] = Double.parseDouble(a[0]);
                point[i][1] = Double.parseDouble(a[1]);
//                
//                StdDraw.filledCircle(point[i][0], point[i][1], 0.01);                
            }
            
            Distance input1;
            Distance input2;
            for (int i = 0; i < (count-1); i++) {                
                input1 = new Distance(point[i][0], point[i][1], point[i+1][0], point[i+1][1]);
                for(int j = 1 ; j < count ; j++){
                    input2 = new Distance(point[i][0], point[i][1],point[j][0],point[j][1]);
                    if(input1.compareTo(input2) > 0){
                        pq_distance.insert(input2);
                    }
                    else 
                        pq_distance.insert(input1);
                    
                }
                
            }
            
        }
    }
}
