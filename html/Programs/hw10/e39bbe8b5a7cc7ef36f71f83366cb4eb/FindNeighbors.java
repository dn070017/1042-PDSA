import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindNeighbors {

    private Point2D[] pointArray;
    // DO NOT MODIFY THE CONSTRUCTOR!
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar
    public void init(Point2D[] points){
        pointArray = new Point2D[points.length];
        for (int i = 0; i < points.length; i++) {
            pointArray[i] = points[i];
        }
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar
    public Point2D[] query(Point2D point, int k) {



//        version 3
        MaxPQ<Point2D> pq = new MaxPQ<>(new FindNeighbors.pointComparator(point));
        for (int i = 0; i < pointArray.length; i++) {
            pq.insert(pointArray[i]);
            if (pq.size() > k)
                pq.delMax();
        }
//        ----------------------------------------------------
//        version 2
//        List<Point2D> pointList = new ArrayList<>();
//        for (int i = 0; i < pointArray.length; i++) {
//            if (pointList.size() > 0) {
//                int j = pointList.size()-1;
//                while (point.distanceTo(pointArray[i]) < point.distanceTo(pointList.get(j))) {
//                    j--;
//                    if (j == -1) break;
//                }
//                pointList.add(j+1,pointArray[i]);
//            }
//            else pointList.add(pointArray[i]);
//        }
//        ----------------------------------------------------
//        version 1
//        double[] distance = new double[pointArray.length];
//        for (int i = 0; i < distance.length; i++) {
//            double dis = point.distanceTo(pointArray[i]);
//            distance[i] = dis;
//        }
//        double[] distanceCopy = new double[distance.length];
//        for (int i = 0; i < distance.length; i++)
//            distanceCopy[i] = distance[i];
//        Arrays.sort(distanceCopy);
//        for (int i = 0; i < distance.length; i++)
//            System.out.println(distanceCopy[i]);
//        ----------------------------------------------------

        Point2D[] result = new Point2D[k];
        for (int i = 0; i < k; i++)
//            result[i] = pointList.get(i); // version2
            result[k-1-i] = pq.delMax(); // version3
        return result;
    }

    public static class pointComparator implements Comparator<Point2D> {
        private double pointX;
        private double pointY;

        pointComparator(Point2D that) {
            this.pointX = that.x();
            this.pointY = that.y();
        }
        @Override
        public int compare(Point2D o1, Point2D o2) {
            double diso1 = Math.sqrt(Math.pow(o1.x()-pointX,2)+Math.pow(o1.y()-pointY,2));
            double diso2 = Math.sqrt(Math.pow(o2.x()-pointX,2)+Math.pow(o2.y()-pointY,2));
            if (diso1 > diso2) return 1;
            else if (diso1 < diso2) return -1;
            else return 0;
        }
    }
}



