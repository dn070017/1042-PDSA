

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FindNeighbors {

    public class Node implements Comparable<Node> {

        public Point2D point;
        private Node big;
        private Node small;
        private boolean coordinate;
//        big as 右上，small as 左下，coordiante true X、false Y

        public Node(Point2D A, boolean precor) {
            this.point = A;
            this.coordinate = !precor;
        }

        public int compareTo(Node that) {
            if (this.point.distanceTo(target) > that.point.distanceTo(target)) {
                return 1;
            } else if (this.point.distanceTo(target) == that.point.distanceTo(target)) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    public Node root;
    public Point2D target;
    private MaxPQ<Node> dis;

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
//        先sortX 從一半當root;
        int length = points.length;
        Arrays.sort(points, Point2D.X_ORDER);

        root = new Node(points[length / 2], true);

        for (int i = 0; i < length / 2; i++) {
            union(points[i]);
        }
        for (int i = length / 2 + 1; i < length; i++) {
            union(points[i]);
        }
    }

    public void union(Point2D A) {
        Node current = root;
        while (true) {
            boolean curcoor = current.coordinate;
            if (curcoor == true) {
                if (A.x() > current.point.x()) {
                    if (current.big == null) {
                        current.big = new Node(A, !curcoor);
                        break;
                    } else {
                        current = current.big;
                    }
                } else {
                    if (current.small == null) {
                        current.small = new Node(A, !curcoor);
                        break;
                    } else {
                        current = current.small;
                    }
                }
            } else {
                if (A.y() > current.point.y()) {
                    if (current.big == null) {
                        current.big = new Node(A, !curcoor);
                        break;
                    } else {
                        current = current.big;
                    }
                } else {
                    if (current.small == null) {
                        current.small = new Node(A, !curcoor);
                        break;
                    } else {
                        current = current.small;
                    }
                }
            }
        }
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
//        MinPQ<Node> dis = new MinPQ<Node>();
//         XXX是newclass 有dis跟node?        

        target = point;
        dis = new MaxPQ<Node>(k);
        dis.insert(root);
        findkdis(root, k);

        for (int i = 0; i < k; i++) {
            result[k - i -1] = dis.delMax().point;
        }

        return result;
    }

    public void findkdis(Node A, int k) {
        if (A == null) {
            return;
        }
        Node B = dis.delMax();

        if (dis.size() < k - 1) {
            dis.insert(B);
            dis.insert(A);
        } else {
            if (B.point.distanceTo(target) <= A.point.distanceTo(target)) {
                dis.insert(B);
            } else {
                dis.insert(A);
            }
        }

        if (A.coordinate == true) {
            if (target.x() > A.point.x()) {
                findkdis(A.big, k);

                if (dis.size() < k) {
                    findkdis(A.small, k);
                } else {
                    B = dis.delMax();
                    dis.insert(B);
                    if (B.point.distanceTo(target) <= target.x() - A.point.x()) {
                        return;
                    } else {
                        findkdis(A.small, k);
                    }
                }
            } else {
                findkdis(A.small, k);

                if (dis.size() < k) {
                    findkdis(A.big, k);
                } else {
                    B = dis.delMax();
                    dis.insert(B);
                    if (B.point.distanceTo(target) <= A.point.x() - target.x()) {
                        return;
                    } else {
                        findkdis(A.big, k);
                    }
                }
            }
        } else {
            if (target.y() > A.point.y()) {
                findkdis(A.big, k);

                B = dis.delMax();
                dis.insert(B);
                if (B.point.distanceTo(target) <= target.y() - A.point.y()) {
                    return;
                } else {
                    findkdis(A.small, k);
                }
            } else {
                findkdis(A.small, k);

                B = dis.delMax();
                dis.insert(B);
                if (B.point.distanceTo(target) <= A.point.y() - target.y()) {
                    return;
                } else {
                    findkdis(A.big, k);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
//public static void main(String[] args){
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int NUM = 20;
            Point2D[] poi = new Point2D[NUM];
            int countpoint = 0;

            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] xy = in.split("" "");
                poi[countpoint] = new Point2D(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));

                countpoint++;
            }

            FindNeighbors A = new FindNeighbors();
            A.init(poi);

            int k = 3;
            double x = 0.1354339200;
            double y = 0.7019446863;
            Point2D target = new Point2D(x, y);

            Point2D[] ans = new Point2D[k];
            ans = A.query(target, k);

            for (int i = 0; i < k; i++) {
                System.out.print(ans[i].x());
                System.out.println("" "" + ans[i].y());
            }
        }
    }
}

