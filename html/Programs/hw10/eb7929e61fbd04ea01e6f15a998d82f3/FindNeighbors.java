public class FindNeighbors {


    public final Node root = new Node();

    public static class Node{
        private Node parent;
        private Node left;
        private Node right;
        private double x;
        private double y;
        private boolean horz = true; // horizontal
        private double distance = 0.0;
        Node(){}
        Node(Node parent, double x, double y){
            this.parent = parent;
            //TODO
        }
    }

    // DO NOT MODIFY THE CONSTRUCTOR!
    public FindNeighbors(){}




    // TODO
    // 1. Construct a 2d-tree when given a Point2D array
    // please use the Point2D from algs4.jar
    public void init(Point2D[] points){
        for (Point2D p:points) {
            Node node = new Node(p, null, null);
            if (root == null) {
                root = node;
                node.setDependsOnX(true);
            } else {
                Node thisRoot = root;
                while (thisRoot.getNext(node) != null)
                    thisRoot = thisRoot.getNext(node);
                if (thisRoot.isDependsOnX()) {
                    node.setDependsOnX(false);
                    if (thisRoot.getX() < node.getX()) thisRoot.setRight(node);
                    else thisRoot.setLeft(node);
                } else {
                    node.setDependsOnX(true);
                    if (thisRoot.getY() < node.getY()) thisRoot.setRight(node);
                    else thisRoot.setLeft(node);
                }
            }
        }
    }




    // TODO
    // 2. Report the k-nearest neighbors of a new coordinates
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar
    public Point2D[] query(Point2D point, int k){

        Point2D[] result = new Point2D[k];
        return result;
    }





}
