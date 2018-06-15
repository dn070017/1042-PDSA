import java.util.Arrays;

public class FindNeighbors {

    private Point2D[] pointArray;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        pointArray = points;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        double[] distance = new double[pointArray.length];
        for (int i = 0; i < distance.length; i++) {
            double dis = point.distanceTo(pointArray[i]);
            distance[i] = dis;
        }
        double[] distanceCopy = new double[distance.length];
        for (int i = 0; i < distance.length; i++)
            distanceCopy[i] = distance[i];
        Arrays.sort(distanceCopy);
//        for (int i = 0; i < distance.length; i++)
//            System.out.println(distanceCopy[i]);

        Point2D[] result = new Point2D[k];
        for (int i = 0; i < k; i++) {
            int j = 0;
            while (distanceCopy[i] != distance[j]) j++;
            result[i] = pointArray[j];
        }
        return result;
    }
}


