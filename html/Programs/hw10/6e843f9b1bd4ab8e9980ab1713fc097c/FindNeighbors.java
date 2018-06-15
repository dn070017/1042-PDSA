public class FindNeighbors {


    public KDNode root = new KDNode();

    public static class KDNode{
//        private KDNode parent;
        Point2D content;
//        private double x;
//        private double y;
        private KDNode left;
        private KDNode right;

        private boolean horz = true; // horizontal
        private double d = 0.0;

        public KDNode() {}

        KDNode(Point2D content, KDNode left, KDNode right){
            this.content = content;
            this.left = left;
            this.right = right;
        }

        public KDNode(Point2D content) {
            this.content = content;
            this.left = null;
            this.right = null;
        }

        public boolean isLeaf() { return left == null && right == null; }

        public KDNode peekNext(KDNode target) {
            if (this.isLeaf()) return null;
            if (this.horz){
                if (this.content.x() < target.content.x()) {
                    return this.right;
                } else {
                    return this.left;
                }
            } else {
                if (this.content.y() < target.content.y()) {
                    return this.right;
                } else {
                    return this.left;
                }
            }
        }
    }





    // DO NOT MODIFY THE CONSTRUCTOR!
    public FindNeighbors(){}




    // TODO
    // 1. Construct a 2d-tree when given a Point2D array
    // please use the Point2D from algs4.jar
    public void init(Point2D[] pointsToBuildTree){

        for(Point2D p:pointsToBuildTree){
            KDNode added = new KDNode(p);
            if(root == null) root = added;
            else {
                KDNode myParent = root;
                while (myParent.peekNext(added)!=null){
                    myParent = myParent.peekNext(added);
                }
                if(myParent.horz){
                    added.horz=false;
                    if(myParent.content.x()<added.content.x()) myParent.right=added;
                    else myParent.left=added;
                }else {
                    added.horz=true;
                    if(myParent.content.y()<added.content.y()) myParent.right=added;
                    else myParent.left=added;
                }
            }
        }
        return;
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

