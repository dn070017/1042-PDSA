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
        return pts.size(); //To change body of generated methods
    }
    public double get_X() { return this.x; }
    public double get_Y() { return this.y; }
    public List<Point> get_pts() { return pts; }
    
    Clustering(){}
    //an empty constructor
    
    Clustering(Point s){
        pts.add(s);
        x = s.getX();
        y = s.getY();
    }
    public static Clustering merge(Clustering a , Clustering b){
        Clustering c = new Clustering();
        double total_x = 0;
        double total_y = 0;
        for(Point d:a.pts){
            c.pts.add(d);
            total_x += d.getX();
            total_y += d.getY();
        }
        for(Point d:b.pts){
            c.pts.add(d);
            total_x += d.getX();
            total_y += d.getY();
        }
        c.x = total_x/c.pts.size();
        c.y = total_y/c.pts.size();
        return c;
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

            List<Clustering> clu_1 = new ArrayList<Clustering>();

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            int total = Integer.parseInt(br.readLine());
            for (int i = 0; i < total; i++) {
                String[] line = br.readLine().split("" "");
                Point in = new Point(Double.parseDouble(line[0]) , Double.parseDouble(line[1]));
                clu_1.add( new Clustering(in));
            }
            br.close();
            
            while(clu_1.size()>3){
                double least =clu_1.get(0).distanceTo(clu_1.get(1));
                int min01 = 0;
                int min02 = 1;
                // double for loop to find the minimun distance
                for(int i=0; i<clu_1.size(); i++){
                    for(int i2 =i+1; i2<clu_1.size();i2++){
                        double least01 = clu_1.get(i).distanceTo(clu_1.get(i2));
                        if(least>least01){
                            least = least01;
                            min01 = i;
                            min01 = i2;
                        }
                    }
                    
                }
                clu_1.add(Clustering.merge(clu_1.remove(min02), clu_1.remove(min01)));
                
            }
            
            
            
        }  catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}

