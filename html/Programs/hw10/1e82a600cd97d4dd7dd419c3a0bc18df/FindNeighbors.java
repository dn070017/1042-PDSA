import java.util.Comparator;

public class FindNeighbors {


    public KDNode ROOT = null;

    public static class KDNode {//implements Comparator<KDNode> {
//        private KDNode parent;
        Point2D content;
//        private double x;
//        private double y;
        public KDNode left;
        public KDNode right;

        public boolean horz = true; // horizontal
        public double d = 0.0;

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

        /*RM*/
        public KDNode getOppositeNext(KDNode target) {
            if (this.isLeaf()) return null;
            if (this.horz){
                if (this.content.x() < target.content.x()) return this.left;
                else return this.right;
            } else {
                if (this.content.x() < target.content.y()) return this.left;
                else return this.right;
            }
        }
        /**/

//        public Comparator<KDNode> comparator = new Comparator<KDNode>() {
//            @Override
//            public int compare(KDNode o1, KDNode o2) {
//                if(o1.d > o2.d) {
//                    return 1;
//                } else if(o1.d < o2.d) {
//                    return -1;
//                } else return 0;
//            }
//        };

//        @Override
//        public int compare(KDNode o1, KDNode o2) {
//            if(o1.d > o2.d) {
//                return 1;
//            } else if(o1.d < o2.d) {
//                return -1;
//            } else return 0;
//        }
    }





    // DO NOT MODIFY THE CONSTRUCTOR!
    public FindNeighbors(){}




    // TODO
    // 1. Construct a 2d-tree when given a Point2D array
    // please use the Point2D from algs4.jar
    public void init(Point2D[] pointsToBuildTree){

        for(Point2D p:pointsToBuildTree){
            KDNode added = new KDNode(p);
            if(ROOT == null) ROOT = added;
            else {
                KDNode myParent = ROOT;
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

        MaxPQ<KDNode> pq = new MaxPQ<>(new Comparator<KDNode>() {
            @Override
            public int compare(KDNode o1, KDNode o2) {
                if(o1.d > o2.d) {
                    return 1;
                } else if(o1.d < o2.d) {
                    return -1;
                } else return 0;
            }
        });


        /*RM*/
        traverseR(pq, k, point , ROOT, Double.MAX_VALUE);


        Point2D[] result = new Point2D[k];
        for (int i = k-1; i >= 0 ; i--) {
            result[i] = pq.delMax().content;
        }
        return result;
        /**/
    }
    
    public void traverseR(MaxPQ<KDNode> pq, int k, Point2D target, KDNode ROOT, double distance){
        if(ROOT == null){
//            System.out.println(""null ROOT!"");
            return;
        }


        ROOT.d = ROOT.content.distanceTo(target);
        pq.insert(ROOT);

        /*RM*/
        if (pq.size() > k) {
            pq.delMax();
            KDNode peek = pq.delMax();
            distance = peek.content.distanceTo(target);
            pq.insert(peek);
        }

        KDNode targetKDNode = new KDNode(target, null, null);
        traverseR(pq, k  , target , ROOT.peekNext(targetKDNode), distance);

        if (pq.size() < k) {
            traverseR(pq, k, target, ROOT.getOppositeNext(targetKDNode) , distance);
        } else if (ROOT.horz) {
            if (Math.abs(ROOT.content.x() - target.x()) < distance)
                traverseR(pq, k  , target, ROOT.getOppositeNext(targetKDNode)  , distance);
        } else {
            if (Math.abs(ROOT.content.y() - target.y()) < distance)
                traverseR(pq, k , target, ROOT.getOppositeNext(targetKDNode)   , distance);
        }
        /**/
        
    }





}


















