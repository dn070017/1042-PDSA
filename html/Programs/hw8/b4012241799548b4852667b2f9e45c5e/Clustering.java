/*
 * Homework: Clustering
 * In this homework, you are going to implement a clustering algorithm
 * called ""centroid hierarchical clustering algorithm"" to hierarchically 
.
 * To change this template file, choose Tools | Templates
.
 */

//import edu.princeton.cs.algs4.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Main Class Clustering
 * @author clintko r02b48003
 */
public class Clustering {
    // minpq:    the minimum priority queue that stores pairs
    // minpqPts: same as above, but for storing the distance between points
    //           it is used for finding the minimum distance among clusters
    // clusters: the queue that stores exist clusters
    // uf      : points within same cluster is union together
    private final MinPQ<Pair>    minpq;
    private final MinPQ<Pair>    minpqPts;
    private final Queue<Cluster> clusters; 
    private final WeightedQuickUnionUF uf;
    
    /**
     * Inner Class Pair
     * Class Pair stores two Cluster and holds an numeric, 
     * which represent the distance between the centroids
     * of the clusters
     */
    private class Pair implements Comparable<Pair> {
        private final double dist;
        private final Cluster c1;
        private final Cluster c2;
        
        /**
         * Constructor
         * input two cluster, calculate the distance between two centroids
         * @param cluster01
         * @param cluster02
         */
        public Pair(Cluster cluster01, Cluster cluster02) {
            c1 = cluster01;
            c2 = cluster02;
            dist = calDist();
        } // end constructor
        
        /**
         * method: distance
         * calculate distance of two cluster
         */
        private double calDist() {
            double num01 = Math.pow(c1.getCentroid().x() - c2.getCentroid().x(), 2);
            double num02 = Math.pow(c1.getCentroid().y() - c2.getCentroid().y(), 2);        
            return Math.pow(num01+num02, 0.5);
        } // end method calDist
        
        /**
         * method: compareTo
         * the pair is compare using the distance within two clusters
         * @param that
         * @return int; {-1, 0, 1}
         */
        @Override
        public int compareTo(Pair that){
            if (this.dist < that.getDistance()) { return -1; }
            if (this.dist > that.getDistance()) { return  1; }
            else                                { return  0; }
        }
        
        /**
         * method: getCluster1 & getCluster2
         * getter function which return the cluster one and two
         */
        public Cluster getCluster1()
        { return c1; }
        public Cluster getCluster2()
        { return c2; }
        
        /**
         * method: getDistance
         * getter function which return the distance
         * @return double
         */
        public double getDistance() {
            return dist;
        } // end method getDistance
        
        
        /**
         * method: merge
         * return the new cluster by merging two cluster
         * @return Cluster
         */
        public Cluster merge() {
            // calculate new centroid
            double newX = 
                    c1.getCentroid().x() * c1.getSize() + 
                    c2.getCentroid().x() * c2.getSize();
            double newY = 
                    c1.getCentroid().y() * c1.getSize() + 
                    c2.getCentroid().y() * c2.getSize();
            int newSize = c1.getSize() + c2.getSize();
            MyPoint newCentroid = new MyPoint(newX / newSize, newY / newSize);
            
            // merge two points array from two clusters
            MyPoint[] newPoints = new MyPoint[newSize];
            int label0 = c1.getPoints()[0].label(); // label of first node
            //// points in first cluster
            for (int i = 0; i < c1.getSize(); i++){
                // copy array
                MyPoint pts = c1.getPoints()[i];
                newPoints[i] = pts;
                // union each points together
                uf.union(label0, pts.label());
            } // end for loop
            //// points in second cluster
            for (int i = 0; i < c2.getSize(); i++){
                // copy array
                MyPoint pts = c2.getPoints()[i];
                newPoints[i + c1.getSize()] = pts;
                // union each points together
                uf.union(label0, pts.label());
            } // end for loop
            
            // create a new cluster and return
            return new Cluster(newPoints, newCentroid);
        } // end merge
        
        /**
         * method: delete
         * delete two clusters belongs to the pair
         */
        public void delete() {
            c1.delete();
            c2.delete();
        } // end method delete
        
        /**
         * method: isValid
         * return if both cluster exist
         * @return boolean
         */    
        public boolean isValid() {
            return c1.isExist() && c2.isExist();
        } // end method isValid
        
        /**
         * method: output
         * print out the information of the Pair
         * the method is used for debugging
         */
        public void output() {
            System.out.println(""+++++++++++"");
            System.out.printf(""Clust01 [ size: %d center: (%.2f, %.2f) ]"", c1.getSize(), c1.getCentroid().x(), c1.getCentroid().y());
            if (c1.isExist()) {System.out.println("" Exist "");} else {System.out.println("" NotExist "");}
            System.out.printf(""Clust02 [ size: %d center: (%.2f, %.2f) ]"", c2.getSize(), c2.getCentroid().x(), c2.getCentroid().y());
            if (c2.isExist()) {System.out.println("" Exist "");} else {System.out.println("" NotExist "");}
            System.out.printf(""Distance: %.2f\n"", dist);
        } // end method output
    } // end inner class Pair

    /**
     * Inner class Cluster
     * 
     */
    private class Cluster implements Comparable<Cluster>{
        private final MyPoint[] points;
        private final MyPoint centroid;
        private final int size;
        private boolean exist;
        
        /**
         * constructor: Cluster
         * @param points
         * @param point
         */
        public Cluster(MyPoint[] points, MyPoint point) {
            this.points   = points;
            this.centroid = point;
            this.size     = points.length;
            this.exist    = true;
        } // end constructor
        
        /**
         * method: compareTo
         * compare two cluster by size and centroid
         * @param that
         * @return 
         */
        @Override
        public int compareTo(Cluster that){
            int cmpSize = Integer.compare(this.getSize(), that.getSize());
            int cmpX    = Double.compare(this.getCentroid().x(), that.getCentroid().x());
            int cmpY    = Double.compare(this.getCentroid().y(), that.getCentroid().y());
            if      (cmpSize != 0) {return cmpSize;} // descending order by size
            else if (cmpX    != 0) {return -cmpX;  } // ascending order by coordinates x
            else                   {return -cmpY;  } // ascending order by coordinates y
        } // end method compareTo
        
        /**
         * method: getPoints
         * getter method that return array of points
         * @return MyPoint[]
         */
        public MyPoint[] getPoints() {
            return points;
        } // end method getCentroid
        
        /**
         * method: getCentroid
         * getter method that return centroid
         * @return MyPoint
         */
        public MyPoint getCentroid() {
            return centroid;
        } // end method getCentroid
        
        /**
         * method: getSize
         * getter method that return size of Clusters
         * @return int
         */
        public int getSize() {
            return size;
        } // end method getCentroid
        
        /**
         * method: isExist
         * getter method that return if the cluster exist or not
         * @return boolean
         */
        public boolean isExist() {
            return exist;
        }
        
        /**
         * method: delete
         * set the field exist to false
         */
        public void delete() {
            exist = false;
        } // end method delete
        
        /**
         * method: output
         * print out the size and centroid
         */
        public void output() {
            //System.out.println(size + "" "" + centroid.x() + "" "" + centroid.y());
            System.out.printf(""%d %.2f %.2f\n"", size, centroid.x(), centroid.y());
        } // end method output
    } // end inner class Cluster

    /**
     * Inner class MyPoint
     * wrapper class of Point2D; MyPoint adds an integer ""label"" to Point2D
     */
    private class MyPoint {
        private final Point2D point;
        private final int label;
    
        public MyPoint(Point2D point, int label) {
            this.point = point;
            this.label = label;
        } // end constructor
        
        public MyPoint(double x, double y) {
            this.point = new Point2D(x, y);
            this.label = -1;
        } // end constructor
    
        public double x() {
            return point.x();
        } // end x
    
        public double y() {
            return point.y();
        } // end y

        public int label() {
            return label;
        } // end y

        public Point2D getPoint() {
            return point;
        } // end y
    } // end class MyPoint

    
    /**
     * constructor
     * initialize minimum priority queue and 
     * @param points
     */
    public Clustering(Point2D[] points) {
        // initialization
        int len;                  
        Cluster[] arrClusters;
        MyPoint[] arrMyPoints;
        len = points.length;            // length of variables below 
        arrMyPoints = new MyPoint[len]; // Array of Point with label
        arrClusters = new Cluster[len]; // Array of Cluster
        clusters    = new Queue<>();    // Queue of Cluster
        minpq       = new MinPQ<>(len); // MinPQ of Cluster
        minpqPts    = new MinPQ<>(len); // MinPQ of Cluster with one point only
        
        // UF of points, the index corresponded to the label of points
        uf = new WeightedQuickUnionUF(len); 
        
        // initialize MyPoint array
        for (int idx = 0; idx < len; idx++) {
            arrMyPoints[idx] = new MyPoint(points[idx], idx);
        } // end for loop
        
        // initialize Cluster array
        // each cluster consist a point, which is as well the center
        for (int idx = 0; idx < len; idx++) {
            arrClusters[idx] = new Cluster( 
                    new MyPoint[] { arrMyPoints[idx] },
                    arrMyPoints[idx]);  
        } // end for loop
        
        // construct the queue
        for (Cluster cluster : arrClusters) {
            clusters.enqueue(cluster);
        } // end for loop
        
        // construct the MinPQ
        for (int idx01 = 0; idx01 < (arrClusters.length-1); idx01++) {
            for (int idx02 = (idx01 + 1); idx02 < arrClusters.length; idx02++) {
                minpq.insert( 
                        new Pair(arrClusters[idx01], arrClusters[idx02]));
                minpqPts.insert( 
                        new Pair(arrClusters[idx01], arrClusters[idx02]));
            } // end inner loop
        } // end outer loop
    } // end constructor
    
    /**
     * method: update
     * update minpq, queue
     * @param newCluster
     */
    public void update(Cluster newCluster) {
        // get the size of cluster
        int len = clusters.size();
        
        // update minpq
        for (int idx = 0; idx < len; idx++) {
            // get each cluster in the clusters
            Cluster cluster = clusters.dequeue();
            
            // check if the cluster is still 
            if (!cluster.isExist()) { continue;                  }
            else                    { clusters.enqueue(cluster); }
            
            // form newCluster with each exist Cluster and insert into minpq
            minpq.insert( new Pair(newCluster, cluster));
        } // end for loop
        
        // update queue
        clusters.enqueue(newCluster);
    } // end method updatePQ
    
    /**
     * method: clustering
     * start cluster the points
     * @param numClust 
     */
    public void clustering(int numClust) {
        while ( clusters.size() > numClust) {
            // pop the pair with smallest distance from minpq
            Pair pair = minpq.delMin();
            
            // check if pair is valid
            if (!pair.isValid()) { continue; }
            
            // merging the pair into a new cluster
            Cluster cluster = pair.merge();
            
            // delete old clusters
            pair.delete();
            
            // update minpq and queue
            update(cluster);
        } // end while loop
    } // end method clustering
    
    /**
     * method: output
     * print out the clusters information(size, centroid)
     */
    public void outputClusters() {
        MaxPQ<Cluster> clusterPQ = new MaxPQ<>(clusters.size());
        //System.out.println(""=========="");
        //System.out.println(""Number of Valid clusters: "" + clusters.size());
        for (Cluster c : clusters) {
            clusterPQ.insert(c);
        } // end for loop
        
        for (Cluster c : clusterPQ) {
            c.output();
        } // end for loop
    } // end method output
    
    /**
     * method: outputPairs
     * print out all the pairs information
     */
    public void outputPairs() {
        for (Pair pair : minpq) {
            pair.output();
        } // end for loop
    } // end method outputPairs
    
    /**
     * method: outputMinDist
     * the distance is defined among two points, belong to different clusters
     */
    public void outputMinDist() {
        // if there is only one cluster, then it's 
        // meaningless to ask the minimum distance between clusters
        if (clusters.size() == 1){
            System.out.println(0); 
            return;
        } // end if
        
        // while not minpq not empty and 
        while (!minpqPts.isEmpty()) {
            Pair pair = minpqPts.delMin();
            MyPoint point01 = pair.getCluster1().getPoints()[0];
            MyPoint point02 = pair.getCluster2().getPoints()[0];
            
            // check if two points belong to different clusters
            if (!uf.connected(point01.label(), point02.label())) {
                System.out.printf(""%.2f\n"", pair.getDistance());
                return;
            } // end if
        } // end while loop
    } // end method outputMinDist
    
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // initialization
        int numPoints;
        Point2D[] points;
        Clustering myCluster;
        
        // read in 2D points from file
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // initialize 
            numPoints = Integer.parseInt(br.readLine());
            points = new Point2D[numPoints];
            String[] line;
            
            // read in the 2D points 
            for (int idx = 0; idx < numPoints; idx++){
                // read in cards and initialize cards
                line = br.readLine().split("" "");
                points[idx] = new Point2D(
                         Double.parseDouble(line[0]),
                         Double.parseDouble(line[1]));
            } // end for loop
        } // end try
        
        // cluster points
        myCluster = new Clustering(points);
        myCluster.clustering(3);
        
        // output the information of clusters and distance
        myCluster.outputClusters();
        myCluster.outputMinDist();
    } // end main    
} // end class Clustering

