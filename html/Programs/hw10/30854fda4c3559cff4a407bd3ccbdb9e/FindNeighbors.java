import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author CHIN LUNG
 */
public class FindNeighbors {
    
    private static KdTree kt = new KdTree(); 
    private static LinkedList  outcome  = new LinkedList<Point2D>();
    protected static MaxPQ mpq = new MaxPQ();
    
    public void init(Point2D[] points)
    {
        for(int i = 0; i < points.length ;i++)
        {
            kt.insert(points[i]);
        }
        return;
    }

    //RectHV(double xmin, double ymin, double xmax, double ymax)
    public Point2D[] query(Point2D point, int k)
    {
        Point2D[] result = new Point2D[k];
        RectHV target;
  
        if(point.x() < 0.5 && point.y() <0.5)
        {
            target = new  RectHV(0, 0, 0.5, 0.5);
             outcome= (LinkedList) kt.range( target );
        }
        else if(point.x() >= 0.5 && point.y() >=0.5)
        {
            target = new  RectHV(0.5, 0.5, 1, 1);
            outcome= (LinkedList) kt.range( target );
        }
        else if(point.x() < 0.5 && point.y() >=0.5)
        {
            target = new  RectHV(0, 0.5, 0.5, 1);
            outcome= (LinkedList) kt.range( target );
        }
        else
        {
            target = new  RectHV(0.5, 0, 1, 0.5);
            outcome= (LinkedList) kt.range( target );
        }
        Pair c;
        for(int i = 0; i<outcome.size();i++)
        {
           Point2D a  = (Point2D)outcome.remove(i);
           Pair x = new Pair(a,a.distanceSquaredTo(point));
           mpq.insert(x);
        }
        
        while(mpq.size() >k)
        {
            mpq.delMax();
        }
        
           
        for(int i = result.length-1; i >=0;i--)
        {
            c  = (Pair)mpq.delMax();
            result[i] = c.point;
        }
        
        return result;
    // the points should be sorted accordingly to their distances to the query, from small to large
}
       public static class Pair implements Comparable<Pair> {
        
         Point2D point;
         double distance;
         
        public Pair(Point2D point, double dist)
        {
            this.point = point;
            distance = dist;
        }
        @Override
        public int compareTo(Pair that) 
        {
              if(this.distance > that.distance)
            {
                return 1;
            }
            else if(this.distance == that.distance)
            {
                return 0;
            }
            else{ return -1;}
        }
    }
public static class KdTree {

    private static  final RectHV BORAD_RECT = new RectHV(0, 0, 1, 1);

    private class TreeNode {
        private double x;
        private double y;

        private boolean isVertical = true;

        private TreeNode left;
        private TreeNode right;

        public TreeNode(double x, double y, TreeNode left, TreeNode right, boolean isVertical) {
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
            this.isVertical = isVertical;
        }
    }

    private TreeNode tree = null;
    private int treeSize = 0;

    public KdTree() {
    }

    public boolean isEmpty() {
        return treeSize == 0;
    }

    public int size() {
        return treeSize;
    }

    public void insert(Point2D p) {
        tree = insertHelper(tree, p, true);
    }

    private TreeNode insertHelper(TreeNode node, Point2D p, boolean isVertical) {
        if (node == null) {
            ++treeSize;
            return new TreeNode(p.x(), p.y(), null, null, isVertical);
        } else {
            if (node.x == p.x() && node.y == p.y()) {
                return node;
            } else if ((node.isVertical && p.x() <= node.x) || (!node.isVertical && p.y() <= node.y)) {
                node.left = insertHelper(node.left, p, !node.isVertical);
            } else { // if ((node.isVertical && p.x() > node.x) || (!node.isVertical && p.y() > node.y)) {
                node.right = insertHelper(node.right, p, !node.isVertical);
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
                node = node.left;
            } else { //if ((node.isVertical && p.x() > node.x) || (!node.isVertical && p.y() > node.y)) {
                node = node.right;
            }
        }
        return false;
    }

    public void draw() {
        StdDraw.setScale(0, 1);
        draw(tree, BORAD_RECT);
    }

    private RectHV getLeftChildRect(RectHV nodeRect, TreeNode node) {
        if (node.isVertical) {
            return new RectHV(nodeRect.xmin(), nodeRect.ymin(), node.x, nodeRect.ymax());
        } else {
            return new RectHV(nodeRect.xmin(), nodeRect.ymin(), nodeRect.xmax(), node.y);
        }
    }

    private RectHV getRightChildRect(RectHV nodeRect, TreeNode node) {
        if (node.isVertical) {
            return new RectHV(node.x, nodeRect.ymin(), nodeRect.xmax(), nodeRect.ymax());
        } else {
            return new RectHV(nodeRect.xmin(), node.y, nodeRect.xmax(), nodeRect.ymax());
        }
    }
    private void draw(TreeNode node, RectHV rect) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.02);
            new Point2D(node.x, node.y).draw();
            StdDraw.setPenRadius();

            if (node.isVertical) {
                StdDraw.setPenColor(StdDraw.RED);
                new Point2D(node.x, rect.ymin()).drawTo(new Point2D(node.x, rect.ymax()));
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                new Point2D(rect.xmin(), node.y).drawTo(new Point2D(rect.xmax(), node.y));
            }
            draw(node.left, getLeftChildRect(rect, node));
            draw(node.right, getRightChildRect(rect, node));
        }
    }

    private void rangeHelper(TreeNode node, RectHV nodeRect, RectHV rect, List<Point2D> pointInRect) {
        if (node != null) {
            if (rect.intersects(nodeRect)) {
                Point2D p = new Point2D(node.x, node.y);
                if (rect.contains(p)) {
                    pointInRect.add(p);
                }
                rangeHelper(node.left, getLeftChildRect(nodeRect, node), rect, pointInRect);
                rangeHelper(node.right, getRightChildRect(nodeRect, node), rect, pointInRect);
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        final List<Point2D> pointInRect = new ArrayList<Point2D>();

        rangeHelper(tree, BORAD_RECT, rect, pointInRect);

        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {
                return pointInRect.iterator();
            }
        };
    }

    private Point2D nearestHelper(TreeNode node, RectHV rect, double x, double y, Point2D nearestPointCandidate) {

        Point2D nearestPoint = nearestPointCandidate;

        if (node != null) {
            RectHV leftRect = null;
            RectHV rightRect = null;
            Point2D queryPoint = new Point2D(x, y);

            if (nearestPoint == null || queryPoint.distanceSquaredTo(nearestPoint) > rect.distanceSquaredTo(queryPoint)) {

                Point2D nodePoint = new Point2D(node.x, node.y);

                if (nearestPoint == null) {
                    nearestPoint = nodePoint;
                } else {
                    if (queryPoint.distanceSquaredTo(nearestPoint) > queryPoint.distanceSquaredTo(nodePoint)) {
                        nearestPoint = nodePoint;
                    }
                }

                if (node.isVertical) {
                    leftRect = new RectHV(rect.xmin(), rect.ymin(), node.x, rect.ymax());
                    rightRect = new RectHV(node.x, rect.ymin(), rect.xmax(), rect.ymax());
                    if (x <= node.x) {
                        nearestPoint = nearestHelper(node.left, leftRect, x, y, nearestPoint);
                        nearestPoint = nearestHelper(node.right, rightRect, x, y, nearestPoint);
                    } else {
                        nearestPoint = nearestHelper(node.right, rightRect, x, y, nearestPoint);
                        nearestPoint = nearestHelper(node.left, leftRect, x, y, nearestPoint);
                    }
                } else {
                    leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.y);
                    rightRect = new RectHV(rect.xmin(), node.y, rect.xmax(), rect.ymax());
                    if (y <= node.y) {
                        nearestPoint = nearestHelper(node.left, leftRect, x, y, nearestPoint);
                        nearestPoint = nearestHelper(node.right, rightRect, x, y, nearestPoint);
                    } else {
                        nearestPoint = nearestHelper(node.right, rightRect, x, y, nearestPoint);
                        nearestPoint = nearestHelper(node.left, leftRect, x, y, nearestPoint);
                    }
                }
            }
        }
        return nearestPoint;
    }

    public Point2D nearest(Point2D p) {
        return nearestHelper(tree, BORAD_RECT, p.x(), p.y(), null);
    }
}
public static final class RectHV {
    private final double xmin, ymin;   // minimum x- and y-coordinates
    private final double xmax, ymax;   // maximum x- and y-coordinates

    /**
     * Initializes a new rectangle [<em>xmin</em>, <em>xmax</em>]
.
     *
     * @param  xmin the <em>x</em>-coordinate of the lower-left endpoint
     * @param  xmax the <em>x</em>-coordinate of the upper-right endpoint
     * @param  ymin the <em>y</em>-coordinate of the lower-left endpoint
     * @param  ymax the <em>y</em>-coordinate of the upper-right endpoint
     * @throws IllegalArgumentException if any of <tt>xmin</tt>,
     *         <tt>xmax</tt>, <tt>ymin</tt>, or <tt>ymax</tt>
.
     * @throws IllegalArgumentException if <tt>xmax</tt> &lt;
.
     */
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        if (Double.isNaN(xmin) || Double.isNaN(xmax))
            throw new IllegalArgumentException(""x-coordinate cannot be NaN"");
        if (Double.isNaN(ymin) || Double.isNaN(ymax))
            throw new IllegalArgumentException(""y-coordinates cannot be NaN"");
        if (xmax < xmin || ymax < ymin) {
            throw new IllegalArgumentException(""Invalid rectangle"");
        }
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    /**
.
     *
     * @return the minimum <em>x</em>-coordinate of any point in this rectangle
     */
    public double xmin() {
        return xmin;
    }

    /**
.
     *
     * @return the maximum <em>x</em>-coordinate of any point in this rectangle
     */
    public double xmax() {
        return xmax;
    }

    /**
.
     *
     * @return the minimum <em>y</em>-coordinate of any point in this rectangle
     */
    public double ymin() {
        return ymin;
    }

    /**
.
     *
     * @return the maximum <em>y</em>-coordinate of any point in this rectangle
     */
    public double ymax() {
        return ymax;
    }

    /**
.
     *
     * @return the width of this rectangle <tt>xmax - xmin</tt>
     */
    public double width() {
        return xmax - xmin;
    }

    /**
.
     *
     * @return the height of this rectangle <tt>ymax - ymin</tt>
     */
    public double height() {
        return ymax - ymin;
    }

    /**
.
     *
     * @param  that the other rectangle
     * @return <tt>true</tt> if this rectangle intersect the argument
               rectagnle at one or more points, including on the boundary
     */
    public boolean intersects(RectHV that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
            && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }

    /**
.
     * @param  p the point
     * @return <tt>true</tt> if this rectangle contain the point <tt>p</tt>,
               possibly at the boundary; <tt>false</tt> otherwise
     */
    public boolean contains(Point2D p) {
        return (p.x() >= xmin) && (p.x() <= xmax)
            && (p.y() >= ymin) && (p.y() <= ymax);
    }

    /**
.
     *
     * @param  p the point
     * @return the Euclidean distance between the point <tt>p</tt> and the closest point
               on this rectangle; 0 if the point is contained in this rectangle
     */
    public double distanceTo(Point2D p) {
        return Math.sqrt(this.distanceSquaredTo(p));
    }

    /**
.
     *
     * @param  p the point
     * @return the square of the Euclidean distance between the point <tt>p</tt> and
     *         the closest point on this rectangle; 0 if the point is contained
     *         in this rectangle
     */
    public double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
    }

    /**
.
     *
     * @param  other the other rectangle
     * @return <tt>true</tt> if this rectangle equals <tt>other</tt>;
     *         <tt>false</tt> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        RectHV that = (RectHV) other;
        if (this.xmin != that.xmin) return false;
        if (this.ymin != that.ymin) return false;
        if (this.xmax != that.xmax) return false;
        if (this.ymax != that.ymax) return false;
        return true;
    }

    /**
.
     * @return an integer hash code for this rectangle
     */
    @Override
    public int hashCode() {
        int hash1 = ((Double) xmin).hashCode();
        int hash2 = ((Double) ymin).hashCode();
        int hash3 = ((Double) xmax).hashCode();
        int hash4 = ((Double) ymax).hashCode();
        return 31*(31*(31*hash1 + hash2) + hash3) + hash4;
    }

    /**
.
     *
     * @return a string representation of this rectangle, using the format
     *         <tt>[xmin, xmax] x [ymin, ymax]</tt>
     */
    @Override
    public String toString() {
        return ""["" + xmin + "", "" + xmax + ""] x ["" + ymin + "", "" + ymax + ""]"";
    }

    /**
.
     */
    public void draw() {
        StdDraw.line(xmin, ymin, xmax, ymin);
        StdDraw.line(xmax, ymin, xmax, ymax);
        StdDraw.line(xmax, ymax, xmin, ymax);
        StdDraw.line(xmin, ymax, xmin, ymin);
    }


}

}

