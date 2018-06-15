import java.util.Arrays;
import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public class CriticalPath {

    /**
     * @param args the command line arguments
     */
    //counter for digraph
    
    public static void main(String[] args) {
        In in = new In(args[0]);
		int n_pts = in.readInt();
		Point2D[] pts = new Point2D[n_pts];
		String[] inputs = new String[2];
		for (int i = 0 ; i < n_pts ; i++) {
		inputs = in.readLine().split("" "");
                pts[i] = new Point2D(Double.parseDouble(inputs[0]) , Double.parseDouble(inputs[1]));
                }
        int source = 0;
        int goal = 0;     
        for (int i = 0; i < pts.length; i++) {
            Point2D p = pts[i];
            Point2D s = pts[source];
            Point2D t = pts[goal];
            if (p.x() + p.y() < s.x() + s.y()){ source = i;
            
            }
            if (p.x() + p.y() > t.x() + t.y()) {goal = i;
            
            }
        }
        
        
        // deciding the edges i pointing to j 
        
                
    }
    
}

