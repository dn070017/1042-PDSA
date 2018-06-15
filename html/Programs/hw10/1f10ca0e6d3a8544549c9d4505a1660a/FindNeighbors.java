
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author S410
 */
public class FindNeighbors {

    public class Node {

        Point2D point;
        Node left;
        Node right;
        int level;

        public Node(Node left, Node right, Point2D point) {
            this.left = left;
            this.right = right;
            this.point = point;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public Node insert(Point2D point, Node parent) {
        if (parent.level % 2 == 1) {
            if (point.x() < parent.point.x()) {
                if (parent.left == null) {
                    Node node = new Node(null, null, point);
                    node.level = parent.level + 1;
                    parent.setLeft(node);
                    return parent;
                } else {
                    parent.setLeft(insert(point, parent.left));
                    return parent;
                }
            } else if (point.x() > parent.point.x()) {
                if (parent.right == null) {
                    Node node = new Node(null, null, point);
                    node.level = parent.level + 1;
                    parent.setRight(node);
                    return parent;
                } else {
                    parent.setRight(insert(point, parent.right));
                    return parent;
                }
            }
        } else if (parent.level % 2 == 0) {
            if (point.y() < parent.point.y()) {
                if (parent.left == null) {
                    Node node = new Node(null, null, point);
                    node.level = parent.level + 1;
                    parent.setLeft(node);
                    return parent;
                } else {
                    parent.setLeft(insert(point, parent.left));
                    return parent;
                }
            } else if (point.y() > parent.point.y()) {
                if (parent.right == null) {
                    Node node = new Node(null, null, point);
                    node.level = parent.level + 1;
                    parent.setRight(node);
                    return parent;
                } else {
                    parent.setRight(insert(point, parent.right));
                    return parent;
                }
            }
        }
        return parent;
    }

    static Node root;

    public void init(Point2D[] points) {
        root = new Node(null, null, points[0]);
        root.level = 1;

        int p = 1;
        for (int i = 1; i < points.length; i++) {
            root = insert(points[i], root);
        }
    }

    ///////////////////////////////////////////////////////
    static MaxPQ<Target> pq;

    public class Target implements Comparable<Target> {

        Point2D point;
        double dist;

        public Target(Point2D point, double dist) {
            this.point = point;
            this.dist = dist;
        }

        public int compareTo(Target that) {
            if (this.dist > that.dist) {
                return +1;
            } else if (this.dist < that.dist) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public void find(Node node, Point2D point) {

        if (node.level % 2 == 1) {
            if (point.x() < node.point.x()) {
//                StdOut.print(0);
//                StdOut.print(node.level+""\n"");
                pq.insert(new Target(node.point, point.distanceTo(node.point)));
                if (pq.size() > 3) {
                    pq.delMax();
                }
                if (node.left != null) {
                    find(node.left, point);
                }
                if (node.point.x() - point.x() < pq.max().dist && node.right != null) {
                    find(node.right, point);
                }
            } else if (point.x() > node.point.x()) {
//                StdOut.print(1);
//                StdOut.print(node.point.x());
                pq.insert(new Target(node.point, point.distanceTo(node.point)));
                if (pq.size() > 3) {
                    pq.delMax();
                }
                if (node.right != null) {
                    find(node.right, point);
                }
                if (point.x() - node.point.x() < pq.max().dist && node.left != null) {
                    find(node.left, point);
                }
            }
        } else if (node.level % 2 == 0) {
            if (point.y() < node.point.y()) {
//                StdOut.print(2);
                pq.insert(new Target(node.point, point.distanceTo(node.point)));
                if (pq.size() > 3) {
                    pq.delMax();
                }
                if (node.left != null) {
                    find(node.left, point);
                }
                if (node.point.y() - point.y() < pq.max().dist && node.right != null) {
                    find(node.right, point);
                }
            } else if (point.y() > node.point.y()) {
//                StdOut.print(3);
                pq.insert(new Target(node.point, point.distanceTo(node.point)));
                if (pq.size() > 3) {
                    pq.delMax();
                }
                if (node.right != null) {
                    find(node.right, point);
                }
//                StdOut.print();
                if (point.y() - node.point.y() < pq.max().dist && node.left != null) {
                    find(node.left, point);
                }
            }
        }
    }

    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        pq = new MaxPQ<Target>();
        find(root, point);
        Point2D[] ans = new Point2D[k];
        for (Target tar : pq) {
            tar = pq.delMax();
            ans[k - 1] = tar.point;
            k--;
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int N = Integer.parseInt(br.readLine());
        Point2D[] points = new Point2D[N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("" "");
            double x = Double.parseDouble(str[0]);
            double y = Double.parseDouble(str[1]);
            points[i] = new Point2D(x, y);
//            StdDraw.filledCircle(x, y, 0.005);
//            StdDraw.text(x + 0.02, y, Integer.toString(i));
        }
        FindNeighbors fn =new FindNeighbors();
        fn.init(points);

        for (int i = 0; i < 3; i++) {
            String[] str = br.readLine().split("" "");
            double x = Double.parseDouble(str[0]);
            double y = Double.parseDouble(str[1]);
//            StdDraw.setPenColor(Color.green);
//            StdDraw.filledCircle(x, y, 0.01);
//            StdDraw.text(x+0.02,y,  Integer.toString(i+1));
            Point2D[] answer = fn.query(new Point2D(x, y), 3);
            int count = 0;
            for (Point2D p : answer) {

//                StdDraw.setPenColor(Color.red);
//                StdDraw.line(x, y, p.x(), p.y());
//                StdDraw.filledCircle(p.x(), p.y(), 0.01);
//                StdDraw.text((x + p.x()) / 2 + 0.01, (y + p.y()) / 2, Integer.toString(count++));
            }
        }
    }
}

