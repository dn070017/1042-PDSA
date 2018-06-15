import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class Clustering implements Comparable<Clustering>{
    private double x;
    private double y;
    private List<Point> pts = new ArrayList<Point>();

    private int size() {
.
    }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public List<Point> getPoints() { return pts; }
    
//    Clustering(){}
    
    Clustering(Point s){
        pts.add(s);
        x = s.getX();
        y = s.getY();
    }
    
    
    
    
    public static class Point {
        private double x;
        private double y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double getX(){ return x; }
        public double getY(){ return y; }
        public double distanceTo(Point that){
            return Math.sqrt( (this.y-that.y)*(this.y-that.y)+(this.x-that.x)*(this.x-that.x) );
        }
    }
    @Override
    public int compareTo(Clustering that){
        if(this.size()<that.size()) {
            return 1;
        } else if(this.size()>that.size()){
            return -1;
        } else{
            return 0;
        }
        
        
    }
    public double distanceTo(Clustering that){
        return Math.sqrt( (this.x-that.x)*(this.x-that.x) + (this.y-that.y)*(this.y-that.y) );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            List<Clustering> clusterings = new ArrayList<Clustering>();

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            int total = Integer.parseInt(br.readLine());
            for (int i = 0; i < total; i++) {
                String[] line = br.readLine().split("" "");
                Point in = new Point(Double.parseDouble(line[0]) , Double.parseDouble(line[1]));
                clusterings.add( new Clustering(in));
            }
            br.close();
            
            
            
        }  catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}

