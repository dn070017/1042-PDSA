import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindNeighbors {
    // DO NOT MODIFY THE CONSTRUCTOR!

    private Node root;
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar
    public void init(Point2D[] points) {
        for (int i = 0; i < points.length; i++)
            root = kdTree(root,points[i],0);
    }

    private Node kdTree(Node root,Point2D point,int isXY) {
        if (root == null)
            return new Node(point);
        int split = compare(point,root.point,isXY);
        if (split < 0)
            root.left = kdTree(root.left,point,isXY+1);
        else if (split > 0)
            root.right = kdTree(root.right,point,isXY+1);
        return root;
    }

    private int compare(Point2D o1, Point2D o2, int isXY) {
        int split;
        if (isXY % 2 == 0) {
            split = Point2D.X_ORDER.compare(o1,o2);
            return split;
        } else {
            split = Point2D.Y_ORDER.compare(o1,o2);
            return split;
        }
    }

    private void traversalTree(List<Node> list, Node root) {
        if (root == null) return;
        list.add(root);
        traversalTree(list,root.left);
        traversalTree(list,root.right);
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar
    public Point2D[] query(Point2D point, int k) {
//        List<Node> list = queryPath(new ArrayList<>(),root,point,0);
        MaxPQ<Point2D> pq = new MaxPQ<>(new FindNeighbors.pointComparator(point));
        searchSubTree(pq,root,point,k,0,point.distanceTo(root.point));
        Point2D[] result = new Point2D[k];
        for (int i = 0; i < k; i++) {
//            System.out.println(pq.size());
            result[k-1-i] = pq.delMax();
        }
        return result;
    }

//    private List<Node> queryPath(List<Node> list, Node root, Point2D point, int isXY) {
//        if (root != null) {
//            int split = compare(point,root.point,isXY);
//            list.add(root);
//            if (split < 0) {
//                if (root.left == null) {
//                    return list;
//                } else return queryPath(list,root.left,point,isXY+1);
//            } else if (split > 0) {
//                if (root.right == null) {
//                    return list;
//                } else return queryPath(list,root.right,point,isXY+1);
//            }
//        } return null;
//    }

    private void searchSubTree(MaxPQ<Point2D> pq, Node root, Point2D point, int k, int isXY, double maxDistance) {
        if (root == null) return;
        pq.insert(root.point);
        if (pq.size() > k) {
            maxDistance = pq.max().distanceTo(point);
            pq.delMax();
        }
        int split = compare(point,root.point,isXY);

        if (split < 0 && root.left != null)
            searchSubTree(pq,root.left,point,k,isXY+1,maxDistance);
        else if (split > 0 && root.right != null)
            searchSubTree(pq,root.right,point,k,isXY+1,maxDistance);

        if (pq.size() < k) {
            if (split < 0 && root.right != null)
                searchSubTree(pq, root.right, point, k, isXY + 1, maxDistance);
            else if (split > 0 && root.left != null)
                searchSubTree(pq, root.left, point, k, isXY + 1, maxDistance);
        } else if (needSubTree(root,point,split,isXY,maxDistance)) {
            if (split < 0 && root.right != null)
                searchSubTree(pq,root.right,point,k,isXY+1,maxDistance);
            else if (split > 0 && root.left != null)
                searchSubTree(pq,root.left,point,k,isXY+1,maxDistance);
        }
    }

    private boolean needSubTree(Node n, Point2D point, int split, int isXY, double maxDistance) {
        double disToAxis;
        if (isXY == 0)
            disToAxis = Math.abs(n.point.x() - point.x());
        else
            disToAxis = Math.abs(n.point.y() - point.y());

        if (maxDistance > disToAxis) {
            if (split < 0 && n.right != null) return true;
            else if (split > 0 && n.left != null) return true;
            else return false;
        } else return false;
    }

    private boolean isNode(Node n,Point2D p) {
        return n.point == p;
    }

    private boolean isLeaf(Node n) {
        return n.left == null && n.right == null;
    }

    private static class pointComparator implements Comparator<Point2D> {
        private Point2D point;
        pointComparator(Point2D that) {
            point = that;
        }
        @Override
        public int compare(Point2D o1, Point2D o2) {
            if (point.distanceTo(o1) > point.distanceTo(o2)) return 1;
            else if (point.distanceTo(o1) < point.distanceTo(o2)) return -1;
            else return 0;
        }
    }

    public class Node {
        public Node left;
        public Node right;
        public Point2D point;

        Node(Point2D point) {
            this.left = null;
            this.right = null;
            this.point = point;
        }
    }

}



