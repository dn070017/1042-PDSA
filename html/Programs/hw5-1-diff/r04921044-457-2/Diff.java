
import java.util.ArrayList;
import java.util.Collections;

public class MyConvexHull {

    private class PointWrapper {
        public PointWrapper(Point2D p, int i) {
            point = p;
            index = i;
        }

        public void description() {
            System.out.println(""index: "" + index + "", theta: "" + theta);
        }

        public double angleTo(Point2D that) {
            double dx = that.x() - point.x();
            double dy = that.y() - point.y();
            return Math.atan2(dy, dx);
        }

        public Point2D point;
        public int index;
        public double theta;
    }

    public static int[] ConvexHullVertex(Point2D[] list) {
        ArrayList<PointWrapper> points = new ArrayList<> ();
        for (int i=0; i<list.length; ++i) {
            MyConvexHull fuckingJAVAsyntax = new MyConvexHull();
            MyConvexHull.PointWrapper point = fuckingJAVAsyntax.new PointWrapper(list[i], i);
            points.add(point);
        }

        // find min
        PointWrapper minYPoint = points.get(0);
        for (int i=0; i<points.size(); ++i) {
            double pointY = points.get(i).point.y();
            if (pointY < minYPoint.point.y()) {
                minYPoint = points.get(i);
            }
        }

        // compute theta
        for (int i=0; i<points.size(); ++i) {
            points.get(i).theta = minYPoint.angleTo(points.get(i).point);
        }

        // sort with theta
        Collections.sort(points, (o1, o2) -> Double.valueOf(o1.theta).compareTo(Double.valueOf(o2.theta)));

        // get convex hull indices
        ArrayList<PointWrapper> resultPoint = new ArrayList<> ();
        resultPoint.add(points.get(0));
        resultPoint.add(points.get(1));
        Point2D lineStartPoint = points.get(0).point;
        Point2D lineEndPoint = points.get(1).point;
        for (int i=2; i<points.size(); ++i) {
            PointWrapper pointWrapper = points.get(i);
            int ccw = Point2D.ccw(lineStartPoint, lineEndPoint, pointWrapper.point);

            while (ccw == -1) { // clock-wise
                resultPoint.remove(resultPoint.size() - 1);
                lineEndPoint = lineStartPoint;
                lineStartPoint = resultPoint.get(resultPoint.size() - 1).point;

                ccw = Point2D.ccw(lineStartPoint, lineEndPoint, pointWrapper.point);
            }

            lineStartPoint = lineEndPoint;
            lineEndPoint = pointWrapper.point;
            resultPoint.add(pointWrapper);
        }

        // return original indices
        int result[] = new int[resultPoint.size()];
        int i = 0;
        for(PointWrapper wrapper : resultPoint) {
            result[i++] = wrapper.index;
        }
        return result;
    }

    public static void main(String[] args) {

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

        Point2D[] points = {
            new Point2D(0.200, 0.250),
            new Point2D(0.147, 0.387),
            new Point2D(0.300, 0.300),
            new Point2D(0.333, 0.213),
            new Point2D(0.353, 0.412),
            new Point2D(0.700, 0.890),
            new Point2D(0.879, 0.700),
            new Point2D(0.867, 0.888),
            new Point2D(0.980, 0.120),
            new Point2D(0.111, 0.932)
        };

        int result[] = MyConvexHull.ConvexHullVertex(points);

        for (int i : result) {
            System.out.println(i);
        }

        // StdDraw.init();
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
    }
}

