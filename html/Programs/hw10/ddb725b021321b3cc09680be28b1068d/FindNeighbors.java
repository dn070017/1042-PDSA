mport java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author CHIN LUNG
 */
public class FindNeighbors {
    
     protected static Point2D[] AllPoint;
     protected static KdTree kt = new KdTree();
     protected static MaxPQ mpq = new MaxPQ();
     protected static Pair outcome;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        
        for(int i = 0; i < points.length; i++)
        {
           AllPoint[i]  = points[i];
        }       
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k)
    {
        Point2D[] result = new Point2D[k];
  

            for(int i = 0; i < AllPoint.length ;i++)
            {
               outcome = new Pair(AllPoint[i],AllPoint[i].distanceSquaredTo(point));
               mpq.insert(outcome);
            }
            while(mpq.size() != k)
        	{
        		mpq.delMax();
        	}
            for(int i = result.length-1; i >=0;i--)
            {
                outcome  = (Pair)mpq.delMax();
                result[i] = outcome.point;
            }
            return result;
        
        
	}
    
     public static class Pair implements Comparable<Pair> {
        
         Point2D point;
         double distance;
         
        public Pair(Point2D point, double dist)
        {
            this.point = point;
            distance = dist;
        }
        @Override
        public int compareTo(Pair that) 
        {
              if(this.distance > that.distance)
            {
                return 1;
            }
            else if(this.distance == that.distance)
            {
                return 0;
            }
            else{ return -1;}
        }
    }

    
    
    
   
}
