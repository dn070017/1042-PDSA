
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author acicula
 */
public class CriticalDis {
    
        public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String input = br.readLine();
             int number = Integer.parseInt(input);
             //System.out.print(number);
             Point2D[] a = new Point2D[number];
            // List<Point2D> c = new ArrayList<>();
             //Digraph d = new Digraph(number);
             for(int i =0;i<number;i++){
                  String cor[] = br.readLine().split("" "");                    
                  a[i]=new Point2D(Double.parseDouble(cor[0]), Double.parseDouble(cor[1]));
             }
             //////////////////////////
            int source = 0;int target = 0;
            
            for (int i = 0; i <number; i++) {
                Point2D p = a[i];
                Point2D s = a[source];
                Point2D t = a[target];
                if (p.x() + p.y() < s.x() + s.y()) 
                        source = i;
                if (p.x() + p.y() > t.x() + t.y()) 
                        target = i;
                }
            
            ArrayList<discompare> distanceArray = new ArrayList<discompare>();      
            for (int i = 0; i < number; i++) {
                Point2D p1 = a[i];
                for (int j = 0; j < number; j++) {
                    Point2D p2 = a[j];
                    if (p1.x() < p2.x() && p1.y() < p2.y()) {
                        distanceArray.add(new  discompare(p1.distanceTo(p2), i, j));
                    }//end of if
                }//end of for 1
            }//end of for 2
        
            discompare[] d = distanceArray.toArray(new discompare[distanceArray.size()]);
            
            Arrays.sort(d);

            Digraph digraph = new Digraph(number);
            
            for (discompare dist:d){
                digraph.addEdge(dist.i,dist.j);
                DirectedDFS dfs = new DirectedDFS(digraph , source);
                if (dfs.marked(target)) {
                    System.out.print(String.format(""%1.3f"",dist.d));
                    break;
                }//end of if
            } //end of dfs      
        }//end of read
    }//end of main
    static class discompare implements Comparable<discompare>{
        double d;
        int i;
        int j;
        public discompare(double distance,int i,int j){
            this.d = distance;
            this.i = i;
            this.j = j;
        }
        public int compareTo(discompare c) {
            if (this.d > c.d) return 1;
            else if (this.d < c.d) return -1;
            else return 0;
        }
    }//end of disc

}

