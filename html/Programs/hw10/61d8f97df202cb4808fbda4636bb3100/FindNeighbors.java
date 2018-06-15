
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class BlockHV {
    private final double xmin, ymin;
    private final double xmax, ymax;

    public BlockHV(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public double xmin() { return xmin; }
    public double ymin() { return ymin; }
    public double xmax() { return xmax; }
    public double ymax() { return ymax; }

    public double width()  { return xmax - xmin; }
    public double height() { return ymax - ymin; }

    public boolean intersects(BlockHV that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
                && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }

    public double dist_to_block(Point2D p) {
        return Math.sqrt(this.dist_squr(p));
    }

    public double dist_squr(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
    }
    public boolean contains(Point2D p) {
        return (p.x() >= xmin) && (p.x() <= xmax)
                && (p.y() >= ymin) && (p.y() <= ymax);
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        BlockHV that = (BlockHV) y;
        if (this.xmin != that.xmin) return false;
        if (this.ymin != that.ymin) return false;
        if (this.xmax != that.xmax) return false;
        if (this.ymax != that.ymax) return false;
        return true;
    }
}

class KdTree {

    private static final BlockHV block_rect = new BlockHV(0, 0, 1, 1);

    private class TreeNode {
        private double x;
        private double y;
        private TreeNode left_node;
        private TreeNode right_node;
        private boolean isVertical = true;
        
        public TreeNode(double x, double y, TreeNode left_node, TreeNode right_node, boolean isVertical) {
            this.x = x;
            this.y = y;
            this.left_node = left_node;
            this.right_node = right_node;
            this.isVertical = isVertical;
        }
    }

    private TreeNode tree = null;
    private int node_num = 0;

    public KdTree() {}

    public boolean isEmpty() {
        return node_num == 0;
    }

    public void insert(Point2D p) {
        tree = insert_node(tree, p, true);
    }

    private TreeNode insert_node(TreeNode node, Point2D p, boolean isVertical) {
        if (node == null) {
            ++ node_num;
            return new TreeNode(p.x(), p.y(), null, null, isVertical);
        } else {
            if (node.x == p.x() && node.y == p.y()) {
                return node;
            } else if ((node.isVertical && p.x() <= node.x) || (!node.isVertical && p.y() <= node.y)) {
                node.left_node = insert_node(node.left_node, p, !node.isVertical);
            } else {
                node.right_node = insert_node(node.right_node, p, !node.isVertical);
            }
            return node;
        }
    }

    public boolean contains(Point2D p) {
        TreeNode node = tree;
        while (node != null) {
            if (node.x == p.x() && node.y == p.y()) {
                return true;
            } else if ((node.isVertical && p.x() <= node.x) || (!node.isVertical && p.y() <= node.y)) {
                node = node.left_node;
            } else { //if ((node.isVertical && p.x() > node.x) || (!node.isVertical && p.y() > node.y)) {
                node = node.right_node;
            }
        }
        return false;
    }

    private BlockHV getLeftChildBlock(BlockHV nodeBlock, TreeNode node) {
        if (node.isVertical) {
            return new BlockHV(nodeBlock.xmin(), nodeBlock.ymin(), node.x, nodeBlock.ymax());
        } else {
            return new BlockHV(nodeBlock.xmin(), nodeBlock.ymin(), nodeBlock.xmax(), node.y);
        }
    }

    private BlockHV getRightChildBlock(BlockHV nodeBlock, TreeNode node) {
        if (node.isVertical) {
            return new BlockHV(node.x, nodeBlock.ymin(), nodeBlock.xmax(), nodeBlock.ymax());
        } else {
            return new BlockHV(nodeBlock.xmin(), node.y, nodeBlock.xmax(), nodeBlock.ymax());
        }
    }
    

    private void range_res(TreeNode node, BlockHV nodeBlock, BlockHV rect, List<Point2D> pointInBlock) {
        if (node != null) {
            if (rect.intersects(nodeBlock)) {
                Point2D p = new Point2D(node.x, node.y);
                if (rect.contains(p)) {
                    pointInBlock.add(p);
                }
                range_res(node.left_node, getLeftChildBlock(nodeBlock, node), rect, pointInBlock);
                range_res(node.right_node, getRightChildBlock(nodeBlock, node), rect, pointInBlock);
            }
        }
    }

    public Iterable<Point2D> range(BlockHV rect) {
        final List<Point2D> pointInBlock = new ArrayList<Point2D>();

        range_res(tree, block_rect, rect, pointInBlock);

        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {
                return pointInBlock.iterator();
            }
        };
    }

    private Point2D nearest_res(TreeNode node, BlockHV rect, double x, double y, Point2D nearest_p_hit) {

        Point2D nearest_p = nearest_p_hit;

        if (node != null) {
            BlockHV left_nodeBlock = null;
            BlockHV right_nodeBlock = null;
            Point2D query_p = new Point2D(x, y);

            if (nearest_p == null || query_p.dist_squr(nearest_p) > rect.dist_squr(query_p)) {

                Point2D nodePoint = new Point2D(node.x, node.y);

                if (nearest_p == null) {
                    nearest_p = nodePoint;
                } else {
                    if (query_p.dist_squr(nearest_p) > query_p.dist_squr(nodePoint)) {
                        nearest_p = nodePoint;
                    }
                }

                if (node.isVertical) {
                    left_nodeBlock = new BlockHV(rect.xmin(), rect.ymin(), node.x, rect.ymax());
                    right_nodeBlock = new BlockHV(node.x, rect.ymin(), rect.xmax(), rect.ymax());
                    if (x <= node.x) {
                        nearest_p = nearest_res(node.left_node, left_nodeBlock, x, y, nearest_p);
                        nearest_p = nearest_res(node.right_node, right_nodeBlock, x, y, nearest_p);
                    } else {
                        nearest_p = nearest_res(node.right_node, right_nodeBlock, x, y, nearest_p);
                        nearest_p = nearest_res(node.left_node, left_nodeBlock, x, y, nearest_p);
                    }
                } else {
                    left_nodeBlock = new BlockHV(rect.xmin(), rect.ymin(), rect.xmax(), node.y);
                    right_nodeBlock = new BlockHV(rect.xmin(), node.y, rect.xmax(), rect.ymax());
                    if (y <= node.y) {
                        nearest_p = nearest_res(node.left_node, left_nodeBlock, x, y, nearest_p);
                        nearest_p = nearest_res(node.right_node, right_nodeBlock, x, y, nearest_p);
                    } else {
                        nearest_p = nearest_res(node.right_node, right_nodeBlock, x, y, nearest_p);
                        nearest_p = nearest_res(node.left_node, left_nodeBlock, x, y, nearest_p);
                    }
                }
            }
        }
        return nearest_p;
    }

    public Point2D nearest(Point2D p) {
        return nearest_res(tree, block_rect, p.x(), p.y(), null);
    }
}

public class FindNeighbors {

    private static KdTree kd;
    private static int point_num;

    // DO NOT MODIFY THE CONSTRUCTOR! 

    public FindNeighbors() {}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        for (int i = 0; i < point_num; i++) {
            kd.insert(points[i]);
        }
        return;
    }

    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        for (int i = 0; i < k; k++){
            result[i] = kd.nearest_res(point);
        }

        return result;
    }
}

