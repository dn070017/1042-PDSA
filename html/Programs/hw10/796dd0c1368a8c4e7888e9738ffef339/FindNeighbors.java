
public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}
    public Point2D[] p;
    public Point2D[] init(Point2D[] points){
        p = points;
        return p;
    }
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        ST<Double,Point2D> st = new ST<Double,Point2D>();
        for(int i=0;i<p.length;i++){
            st.put(p[i].distanceTo(point), p[i]);
        }
        for(int i=0;i<k;i++){
            result[i]=st.get(st.min());
            st.delete(st.min());
        }
        return result;
    }

}
