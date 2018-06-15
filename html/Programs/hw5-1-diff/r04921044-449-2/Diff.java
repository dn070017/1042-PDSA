
import java.util.ArrayList;
import java.util.Stack;
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
        Stack<Integer> resultPoint = new Stack<> ();
        resultPoint.push(points.get(0).index);
        resultPoint.push(points.get(1).index);
        Point2D lineStartPoint = points.get(0).point;
        Point2D lineEndPoint = points.get(1).point;
        for (int i=2; i<points.size(); ++i) {
            PointWrapper pointWrapper = points.get(i);
            int ccw = Point2D.ccw(lineStartPoint, lineEndPoint, pointWrapper.point);
            if (ccw == 1) { // counter-clock-wise
                lineStartPoint = lineEndPoint;
                lineEndPoint = pointWrapper.point;
                resultPoint.push(pointWrapper.index);
            }
            else if (ccw == -1) { // clock-wise
                lineEndPoint = pointWrapper.point;
                resultPoint.pop();
                resultPoint.push(pointWrapper.index);
            }
        }

        // return original indices
        int result[] = new int[resultPoint.size()];
        int i = 0;
        for(Integer index : resultPoint) {
            result[i++] = index;
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
            new Point2D(1.f, 1.f),
            new Point2D(-1.f, 3.f),
            new Point2D(0.f, 0.f),
            new Point2D(1.f, 3.f),
            new Point2D(-1.f, 1.f)
        };

        MyConvexHull.ConvexHullVertex(points);
    }
}

