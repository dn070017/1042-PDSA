import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FindNeighbors {

    private Point2D Point;
    private Node<Point2D> first = new Node(null, null, null);
    private MaxPQ<targetPoint> pq = new MaxPQ<targetPoint>();

    public void init(Point2D[] points) {
        first.setCoordinate(points[0]);
        for (int i = 1; i < points.length; i++) {
            Node<Point2D> next = new Node(null, null, null);
            next.setCoordinate(points[i]);
            insertion(first, next, 1);
        }
    }

    public void insertion(Node<Point2D> parent, Node<Point2D> child, int level) {
        if (level % 2 == 1) {
            if (child.coordinate.x() < parent.coordinate.x()) {
                if (parent.left == null) {
                    parent.left = child;
                } else {
                    insertion(parent.left, child, level + 1);
                }
            } else {
                if (parent.right == null) {
                    parent.right = child;
                } else {
                    insertion(parent.right, child, level + 1);
                }
            }
        } else {
            if (child.coordinate.y() < parent.coordinate.y()) {
                if (parent.left == null) {
                    parent.left = child;
                } else {
                    insertion(parent.left, child, level + 1);
                }
            } else {
                if (parent.right == null) {
                    parent.right = child;
                } else {
                    insertion(parent.right, child, level + 1);
                }
            }
        }
    }

    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        targetPoint[] pointsFinal = new targetPoint[k];
        Point = point;
        findShort(first, Point, k, 1);
        for (int i = 0; i < k; i++) {
            pointsFinal[i] = pq.delMax();
        }
        Arrays.sort(pointsFinal);
        for (int i = 0; i < k; i++) {
            result[i] = pointsFinal[i].targetPt;
        }
        return result;
    }

    public void findShort(Node<Point2D> start, Point2D point, int k, int level) {
        targetPoint maxShort = new targetPoint(null, 0);
        double Dis = start.coordinate.distanceTo(Point);
        targetPoint target = new targetPoint(start.coordinate, Dis);
        pq.insert(target);
        if (pq.size() <= k) {
            if (start.left != null) {
                findShort(start.left, point, k, level + 1);
            }
            if (start.right != null) {
                findShort(start.right, point, k, level + 1);
            }
        } else {
            pq.delMax();
            maxShort = pq.delMax();
            pq.insert(maxShort);
            if (level % 2 == 1) {
                if (Math.abs(point.x() - start.coordinate.x()) < maxShort.distance) {
                    if (start.left != null) {
                        findShort(start.left, point, k, level + 1);
                    }
                    if (start.right != null) {
                        findShort(start.right, point, k, level + 1);
                    }
                } else {
                    if (point.x() < start.coordinate.x()) {
                        if (start.left != null) {
                            findShort(start.left, point, k, level + 1);
                        }
                    } else {
                        if (start.right != null) {
                            findShort(start.right, point, k, level + 1);
                        }
                    }
                }
            } else {
                if (Math.abs(point.y() - start.coordinate.y()) < maxShort.distance) {
                    if (start.left != null) {
                        findShort(start.left, point, k, level + 1);
                    }
                    if (start.right != null) {
                        findShort(start.right, point, k, level + 1);
                    }
                } else {
                    if (point.y() < start.coordinate.y()) {
                        if (start.left != null) {
                            findShort(start.left, point, k, level + 1);
                        }
                    } else {
                        if (start.right != null) {
                            findShort(start.right, point, k, level + 1);
                        }
                    }
                }
            }
        }
    }

    private static class targetPoint implements Comparable<targetPoint> {

        public Point2D targetPt;
        public Double distance;

        public targetPoint(Point2D a, double d) {
            targetPt = a;
            distance = d;
        }

        public int compareTo(targetPoint that) {
            if (distance > that.distance) {
                return 1;
            } else if (distance == that.distance) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private static class Node<Point2D> {

        private Point2D coordinate;
        private Node<Point2D> left = null;
        private Node<Point2D> right = null;

        public Node(Point2D c, Node<Point2D> l, Node<Point2D> r) {
            coordinate = c;
            left = l;
            right = r;
        }

        public void setCoordinate(Point2D c) {
            coordinate = c;
        }

        public void setLeft(Node<Point2D> l) {
            left = l;
        }

        public void setRight(Node<Point2D> r) {
            right = r;
        }

        public Point2D getCoordinate() {
            return coordinate;
        }

        public Node<Point2D> getLeft() {
            return left;
        }

        public Node<Point2D> getRight() {
            return right;
        }
    }
}

