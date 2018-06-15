import java.util.Comparator;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
         for (Point2D s:points){
            this.insert(new Node(s, null, null));
         }
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        return result;
    }
    static class Node{
        private Point2D point2D;
        private Node left;
        private Node right;
        private boolean isDependsOnX;
        private double distance;

        Node(Point2D point2D , Node left , Node right){
            this.point2D = point2D;
            this.left = left;
            this.right = right;
        }

        public Node getNext(Node get) {
            if (this.isLeaf()) return null;
            if (this.isDependsOnX()){
                if (this.getX() < get.getX()) return this.getRight();
                else return this.getLeft();
            } else {
                if (this.getY() < get.getY()) return this.getRight();
                else return this.getLeft();
            }
        }

        public Node getOppositeNext(Node op) {
            if (this.isLeaf()) return null;
            if (this.isDependsOnX()){
                if (this.getX() < op.getX()) return this.getLeft();
                else return this.getRight();
            } else {
                if (this.getY() < op.getY()) return this.getLeft();
                else return this.getRight();
            }
        }

        public boolean isLeaf() { return left == null && right == null; }

        public double getX() { return point2D.x(); }

        public double getY() { return point2D.y(); }

        public double distanceTo(Point2D p) { return point2D.distanceTo(p); }

        public Point2D getPoint2D() { return point2D; }

        public boolean isDependsOnX() { return isDependsOnX; }

        public void setDependsOnX(boolean dependsOnX) { isDependsOnX = dependsOnX; }

        public Node getLeft() { return left; }

        public void setLeft(Node left) { this.left = left; }

        public Node getRight() { return right; }

        public void setRight(Node right) { this.right = right; }

        public double getDistance() { return distance; }

        public void setDistance(double distance) { this.distance = distance; }
    }
    

}


