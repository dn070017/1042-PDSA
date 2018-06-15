
import java.util.ArrayList;
import java.util.Collections;

import java.io.FileReader;
import java.io.BufferedReader;

public class MyConvexHull {

    private class PointWrapper {
        public PointWrapper(Point2D p, int i) {
            point = p;
            index = i;
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

            while (Point2D.ccw(lineStartPoint, lineEndPoint, pointWrapper.point) == -1) { // clock-wise
                resultPoint.remove(resultPoint.size() - 1);
                lineEndPoint = lineStartPoint;
                lineStartPoint = resultPoint.get(resultPoint.size() - 2).point;
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

    public static void main(String[] args) throws Exception {

        // 1. read in the file containing N 2-dimentional points
        // 2. create an edge for each pair of points with a distance <= d
        // 3. find connected components (CCs) with a size >= 3
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
        // 5. count the number of points in N serving as a convex hull vertex, print it

        BufferedReader buffer = new BufferedReader(new FileReader(args[0]));
        double distance = Double.parseDouble(buffer.readLine());
        int pointCount = Integer.parseInt(buffer.readLine()); // no use

        ArrayList<ArrayList<Point2D>> CCs = new ArrayList<ArrayList<Point2D>> ();

        String line;
        while ((line = buffer.readLine()) != null) {
            String[] coord = line.split("" "");
            Point2D newPoint = new Point2D(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));

            boolean inAnyCC = false;
            for (ArrayList<Point2D> CC : CCs) {
                for (Point2D point : CC) {
                    if (point.distanceTo(newPoint) <= distance) {
                        CC.add(newPoint);
                        inAnyCC = true;
                        break;
                    }
                }

                if (inAnyCC) {
                    break;
                }
            }

            if (!inAnyCC) {
                ArrayList<Point2D> CC = new ArrayList<> ();
                CC.add(newPoint);
                CCs.add(CC);
            }
        }

        int count = 0;
        for (ArrayList<Point2D> CC : CCs) {
            if (CC.size() >= 3) {
                Point2D[] points = new Point2D[CC.size()];
                points = CC.toArray(points);
                count += MyConvexHull.ConvexHullVertex(points).length;
            }
        }

        System.out.println(count);
    }
}

