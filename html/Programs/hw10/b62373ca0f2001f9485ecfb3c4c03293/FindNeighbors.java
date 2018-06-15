
public class FindNeighbors {

    private static class Pqcompare implements Comparable<Pqcompare> {

        Pqcompare() {
.
        }

        private Double b;
        private Double a;
        private Double distance;

        public Pqcompare(Double a, Double b, Double distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;

        }

        @Override
        public int compareTo(Pqcompare that) {
            if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    // DO NOT MODIFY THE CONSTRUCTOR! 

    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    Point2D[] aa;
  

    public void init(Point2D[] points) {
      aa = points;
     
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        MaxPQ<Pqcompare> pq = new MaxPQ<Pqcompare>();

        for (int i = 0; i < aa.length; i++) {
            double arr = aa[i].distanceTo(point);
            Pqcompare keke = new Pqcompare(aa[i].x(), aa[i].y(), arr);
            pq.insert(keke);
            if (pq.size() > k){
                pq.delMax();
            }
        }
        
        for (int i = 0; i<k; i++){
            Pqcompare ans = pq.delMax();
            result[k-1-i] = new Point2D(ans.a, ans.b);
        }
       
        return result;
    }

}
