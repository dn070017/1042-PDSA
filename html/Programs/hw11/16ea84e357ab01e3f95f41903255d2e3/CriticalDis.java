
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 余軒
 */
public class CriticalDis {
    
        
    
        public static class Pair implements Comparable<Pair>{
            private Point2D a;
            private Point2D b;
            private int IDa;
            private int IDb;
            private double distabce;
            public Pair(Point2D a, Point2D b, int IDa, int IDb){
                this.a = a;
                this.b = b;
                this.IDa = IDa;
                this.IDb = IDb;
                this.distabce = a.distanceTo(b);
            }
//            public double distance(Point2D a, Point2D b){
//                return a.distanceTo(b);
//            }
            public int compareTo(Pair that){
                if (this.distabce > that.distabce)
                    return 1;
                else return -1;
            }
        }
    
           public static void main(String[] args) throws IOException {
        // TODO code application logic here
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           String str_num = br.readLine();
           int num = Integer.parseInt(str_num);
           int cc = 0;
           double[] x = new double[num];
           double[] y = new double[num];
           for (String in=br.readLine();in!=null;in=br.readLine()){
               String[] data = in.split("" "");
               x[cc] = Double.parseDouble(data[0]);
               y[cc] = Double.parseDouble(data[1]);
               cc++;
           }
           double[] sum = new double[num];
           // initialization
           sum[0] = x[0]+y[0];
           double min = sum[0];
           double max = sum[0];
           int source = 0;
           int target = 0;
           //System.out.println(y[0]);
           Point2D[] points = new Point2D[num];
           points[0] = new Point2D(x[0],y[0]);
           for (int i = 1;i<num;i++){
                sum[i] = x[i] + y[i];
                if (min > sum[i]){
                    min = sum[i];
                    source = i;
                }
                if (max<sum[i]){
                    max = sum[i];
                    target = i;
                 }
                points[i] = new Point2D(x[i],y[i]);
           }
           
//           System.out.println(source);
//           System.out.println(target);
//           System.out.println(points[0]);
           Digraph DG = new Digraph(num);
           DirectedDFS DDG = null;
           MinPQ<Pair> pq = new MinPQ<Pair>(num*(num-1)/2);
           double d = 0;
           for (int i=0;i<num;i++){
               for (int j=i+1;j<num;j++){
                   Pair temp = new Pair(points[i],points[j],i,j);
                   pq.insert(temp);
               }
           }
           while(true){
               Pair temp = pq.delMin(); //第一個加入的邊 最小
               if (temp.a.x()<temp.b.x()&& temp.a.y()<temp.b.y()){
                   DG.addEdge(temp.IDa,temp.IDb);
               }
               else if (temp.a.x()>temp.b.x() && temp.a.y()>temp.b.y()){
                   DG.addEdge(temp.IDb,temp.IDa);
               }
               DDG = new DirectedDFS(DG,source);
               if (DDG.marked(target)){
                   d = temp.distabce;
                   break;
               }
           }
           
           System.out.printf(""%1.3f\n"", d);
           
    }
   }
}
