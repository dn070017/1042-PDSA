import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;
public class CriticalDis {
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int N=Integer.parseInt(br.readLine());
            
            P2D[] gg=new P2D[N];
            for(int i=0;i<N;i++){
                String temp[]=new String[2];
                temp=br.readLine().split("" "");
                double x=Double.parseDouble(temp[0]);
                double y=Double.parseDouble(temp[1]);
                gg[i]=new P2D(x,y);
            }
            //先找到s和t，用XpY_Order
            P2D origin=new P2D(0,0);
            Arrays.sort(gg,origin.XpY_ORDER);
            P2D s=gg[0];
            P2D t=gg[N-1];
            /*寫法1，超87
            //建Digraph，當v-w距離<d，並且v比w嚴格左下時建立單向連結            
            //找到最小距離d使s-t至少有一條路
            
            
            double d=2.8284271247461900976033774484194;
            DirectedDFS test;
            do{
                d=d/2;
                Digraph gra=new Digraph(N);
                for(int i=0;i<N;i++){
                    for(int j=i+1;j<N;j++){
                        if(gg[i].x()<gg[j].x()&&gg[i].y()<gg[j].y()&&gg[i].distanceTo(gg[j])<d){
                            gra.addEdge(i, j);
                        }                        
                    }
                }
                test=new DirectedDFS(gra,0);
            }while(test.marked(N-1));
           
            do{
                
                d+=0.0005;
                Digraph gra=new Digraph(N);
                for(int i=0;i<N;i++){
                    for(int j=i+1;j<N;j++){
                        if(gg[i].x()<gg[j].x()&&gg[i].y()<gg[j].y()&&gg[i].distanceTo(gg[j])<d){
                            gra.addEdge(i, j);
                        }                        
                    }
                }
                test=new DirectedDFS(gra,0);
            }while(!test.marked(N-1));
            
            d=d*1000;
            d=Math.floor(d);
            d=d/1000;
            System.out.printf(""%1.3f\n"", d);
            */
            //寫法1結束
            
            //寫法2，主流
            MinPQ <dis>aya=new MinPQ();
            
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    if(gg[i].x()<gg[j].x()&&gg[i].y()<gg[j].y()){
                        dis add=new dis(gg[i],gg[j],i,j);
                        aya.insert(add);
                    }                    
                }
            }
            Digraph gra=new Digraph(N);
            dis temp=aya.delMin();
            aya.insert(temp);
            while(N>0){
                temp=aya.delMin();
                gra.addEdge(temp.getid1(), temp.getid2());
                DirectedDFS haha=new DirectedDFS(gra,0);
                if(haha.marked(N-1))break;
            }
            double d=temp.getdis();
            System.out.printf(""%1.3f\n"", d);
        }
    }
}
class dis implements Comparable<dis>{
    final double distance;
    final P2D v;
    final P2D w;
    final int id1;
    final int id2;
    public dis(P2D a,P2D b,int i,int j){
        distance=a.distanceTo(b);
        v=a;
        w=b;
        id1=i;
        id2=j;
    }
    public double getdis(){
        return distance;
    }
    public P2D[] getPoint(){
        P2D[]a=new P2D[2];
        return a;
    }
    public int getid1(){
        return id1;
    }
    public int getid2(){
        return id2;
    }
    public int compareTo(dis that){
        if(this.distance>that.distance) return 1;
        else if(this.distance<that.distance) return -1;
        else return 0;
    }
}
class P2D implements Comparable<P2D> {

    /**
.
     */
    public static final Comparator<P2D> X_ORDER = new XOrder();

    /**
.
     */
    public static final Comparator<P2D> Y_ORDER = new YOrder();

    /**
.
     */
    public static final Comparator<P2D> R_ORDER = new ROrder();


    /**
.
     */
    public final Comparator<P2D> ATAN2_ORDER = new Atan2Order();

    /**
.
     */
    public final Comparator<P2D> DISTANCE_TO_ORDER = new DistanceToOrder();
    public final Comparator<P2D> XpY_ORDER = new XpYOrder();
    private final double x;    // x coordinate
    private final double y;    // y coordinate

    /**
.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if either <tt>x</tt> or <tt>y</tt>
     *    is <tt>Double.NaN</tt>, <tt>Double.POSITIVE_INFINITY</tt> or
     *    <tt>Double.NEGATIVE_INFINITY</tt>
     */
    public P2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException(""Coordinates must be finite"");
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException(""Coordinates cannot be NaN"");
        if (x == 0.0) x = 0.0;  // convert -0.0 to +0.0
        if (y == 0.0) y = 0.0;  // convert -0.0 to +0.0
        this.x = x;
        this.y = y;
    }

    /**
.
     * @return the x-coordinate
     */
    public double x() {
        return x;
    }

    /**
.
     * @return the y-coordinate
     */
    public double y() {
        return y;
    }

    /**
.
     * @return the polar radius of this point in polar coordiantes: sqrt(x*x + y*y)
     */
    public double r() {
        return Math.sqrt(x*x + y*y);
    }

    /**
.
     * @return the angle (in radians) of this point in polar coordiantes (between -pi/2 and pi/2)
     */
    public double theta() {
        return Math.atan2(y, x);
    }

    /**
.
     * @return the angle in radians (between -pi and pi) between this point and that point (0 if equal)
     */
    private double angleTo(P2D that) {
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
    }
    /**
.
     * @param that the other point
     * @return the Euclidean distance between this point and that point
     */
    public double distanceTo(P2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
.
     * @param that the other point
     * @return the square of the Euclidean distance between this point and that point
     */
    public double distanceSquaredTo(P2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return dx*dx + dy*dy;
    }

    /**
.
     * @param that the other point
     * @return { a negative integer, zero, a positive integer } if this point is
     *    { less than, equal to, greater than } that point
     */
    public int compareTo(P2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }
    private static class XpYOrder implements Comparator<P2D> {
        public int compare(P2D p, P2D q) {
            if (p.x+p.y < q.x+q.y) return -1;
            if (p.x+p.y > q.x+q.y) return +1;
            return 0;
        }
    }
    // compare points according to their x-coordinate
    private static class XOrder implements Comparator<P2D> {
        public int compare(P2D p, P2D q) {
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            return 0;
        }
    }

    // compare points according to their y-coordinate
    private static class YOrder implements Comparator<P2D> {
        public int compare(P2D p, P2D q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }

    // compare points according to their polar radius
    private static class ROrder implements Comparator<P2D> {
        public int compare(P2D p, P2D q) {
            double delta = (p.x*p.x + p.y*p.y) - (q.x*q.x + q.y*q.y);
            if (delta < 0) return -1;
            if (delta > 0) return +1;
            return 0;
        }
    }
 
    // compare other points relative to atan2 angle (bewteen -pi/2 and pi/2) they make with this Point
    private class Atan2Order implements Comparator<P2D> {
        public int compare(P2D q1, P2D q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            if      (angle1 < angle2) return -1;
            else if (angle1 > angle2) return +1;
            else                      return  0;
        }
    }
    // compare points according to their distance to this point
    private class DistanceToOrder implements Comparator<P2D> {
        public int compare(P2D p, P2D q) {
            double dist1 = distanceSquaredTo(p);
            double dist2 = distanceSquaredTo(q);
            if      (dist1 < dist2) return -1;
            else if (dist1 > dist2) return +1;
            else                    return  0;
        }
    }


    /**
     * Does this point equal y?
     * @param other the other point
     * @return true if this point equals the other point; false otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        P2D that = (P2D) other;
        return this.x == that.x && this.y == that.y;
    }

    /**
.
     * @return a string representation of this point in the format (x, y)
     */
    public String toString() {
        return ""("" + x + "", "" + y + "")"";
    }

    /**
.
     * @return an integer hash code for this point
     */
    public int hashCode() {
        int hashX = ((Double) x).hashCode();
        int hashY = ((Double) y).hashCode();
        return 31*hashX + hashY;
    }
}


