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
        List<Node> list = queryPath(new ArrayList<>(),root,point,0);
        MaxPQ<Point2D> pq = new MaxPQ<>(new FindNeighbors.pointComparator(point));
        for (int i = list.size()-1; i > -1; i--) {
            Node n = list.get(i);
//            System.out.println(n.point.x() + "" / "" + n.point.y() + "" / "" + n.point.distanceTo(point));
            if (pq.size() > k) pq.delMax();
            pq.insert(n.point);
            int split = compare(point,n.point,i%2);
            if (pq.size() < k) {
                if (split < 0 && n.right != null)
                    searchSubTree(pq,n.right,k);
                else if (split > 0 && n.left != null)
                    searchSubTree(pq,n.left,k);
            } else {
                if (needSubTree(n,point,split,i%2,pq.max().distanceTo(point))) {
                    if (split < 0)
                        searchSubTree(pq,n.right,k);
                    else if (split > 0)
                        searchSubTree(pq,n.left,k);
                }
            }
        }

        if (pq.size() > k) pq.delMax();

        Point2D[] result = new Point2D[k];
        for (int i = 0; i < k; i++) {
//            System.out.println(pq.size());
            result[k-1-i] = pq.delMax();
        }
        return result;
    }

    private List<Node> queryPath(List<Node> list, Node root, Point2D point, int isXY) {
        if (root != null) {
            int split = compare(point,root.point,isXY);
            list.add(root);
            if (split < 0) {
                if (root.left == null) {
                    return list;
                } else return queryPath(list,root.left,point,isXY+1);
            } else if (split > 0) {
                if (root.right == null) {
                    return list;
                } else return queryPath(list,root.right,point,isXY+1);
            }
        } return null;
    }

    private void searchSubTree(MaxPQ<Point2D> pq, Node n, int k) {
        List<Node> list = new ArrayList<>();
        traversalTree(list,n);
        for (int i = 0; i < list.size(); i++) {
            if (pq.size() > k) pq.delMax();
            pq.insert(list.get(i).point);
            if (pq.size() > k) pq.delMax();
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



