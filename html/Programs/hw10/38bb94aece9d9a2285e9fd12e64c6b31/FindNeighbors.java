import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;
import java.util.Arrays;

public class FindNeighbors {

    public static class Node {

        private Node left;
        private Node right;
        private int value;
        private int deep;

        public Node(Node left, Node right, int value, int deep) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.deep = deep;
        }

        public Node getLeft() {
            return (this.left);
        }

        public Node getRight() {
            return (this.right);
        }

        public int getdeep() {
            return (this.deep);
        }

        public int getValue() {
            return (this.value);
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setdeep(int deep) {
            this.deep = deep;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public Node put(Node x) {
        if (x == null) {
            x = new Node(null, null, where, howdeep);
            return x;
        }
        howdeep++;
        if ((x.deep % 2) == 0) {
            if (points[x.value].x() >= points[where].x()) {
                x.setLeft(put(x.left));
            } else {
                x.setRight(put(x.right));
            }
        } else {
            if (points[x.value].y() >= points[where].y()) {
                x.setLeft(put(x.left));
            } else {
                x.setRight(put(x.right));
            }
        }
        return x;
    }

    private Node root;
    public int howdeep;
    public int where;
    private Node copy;
    public Point2D[] points;

    public void init(Point2D[] point) {
        points = point;
        root = new Node(null, null, 0, 0);
        for (where = 1; where < point.length; where++) {
            howdeep = 0;
            put(root);
        }
    }

    public Point2D[] dd;//save the origin array
    public int large;//array large
    public Point2D d;//target

    public Node recur(Node x) {
        if (x == null) {
            return x;
        }
        if ((x.deep % 2) == 0) {
            if (points[x.value].x() >= d.x()) {
                if (points[x.value].distanceTo(d) < dd[large - 1].distanceTo(d)) {
                    dd[large - 1] = points[x.value];
                    Arrays.sort(dd, d.DISTANCE_TO_ORDER);
                }
                recur(x.left);
                if (((points[x.value].x() - d.x()) * (points[x.value].x() - d.x())) < (dd[large - 1].distanceTo(d) * dd[large - 1].distanceTo(d))) {
                    recur(x.right);
                } else {
                    return x;
                }
            } else {
                if (points[x.value].distanceTo(d) < dd[large - 1].distanceTo(d)) {
                    dd[large - 1] = points[x.value];
                    Arrays.sort(dd, d.DISTANCE_TO_ORDER);
                }
                recur(x.right);
                if (((points[x.value].x() - d.x()) * (points[x.value].x() - d.x())) < (dd[large - 1].distanceTo(d) * dd[large - 1].distanceTo(d))) {
                    recur(x = x.left);
                } else {
                    return x;
                }
            }
        } else {
            if (points[x.value].y() >= d.y()) {
                if (points[x.value].distanceTo(d) < dd[large - 1].distanceTo(d)) {
                    dd[large - 1] = points[x.value];
                    Arrays.sort(dd, d.DISTANCE_TO_ORDER);
                }
                recur(x.left);
                if (((points[x.value].y() - d.y()) * (points[x.value].y() - d.y())) < (dd[large - 1].distanceTo(d) * dd[large - 1].distanceTo(d))) {
                    recur(x.right);
                } else {
                    return x;
                }
            } else {
                if (points[x.value].distanceTo(d) < dd[large - 1].distanceTo(d)) {
                    dd[large - 1] = points[x.value];
                    Arrays.sort(dd, d.DISTANCE_TO_ORDER);
                }
                recur(x.right);
                if (((points[x.value].y() - d.y()) * (points[x.value].y() - d.y())) < (dd[large - 1].distanceTo(d) * dd[large - 1].distanceTo(d))) {
                    recur(x.left);
                } else {
                    return x;
                }
            }
        }
        return x;
    }

    public Point2D[] query(Point2D point, int k) {
        d = point;
        Point2D[] result = new Point2D[k];
        large = k;
        for (int i = 0; i < k; i++) {
            result[i] = new Point2D(-3, -3);
        }
        dd = result;
        recur(root);
        result = dd;
//        for (int i = 0; i < large; i++) {
//            System.out.println(result[i]);
//        }
        return result;
        // the points should be sorted accordingly to their distances to the query, from small to large
    }

//    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
//            String data;
//            int count = 0;
////            StdDraw.setCanvasSize(500, 500);
//            Point2D[] pointss = new Point2D[50];
//
//            while ((data = br.readLine()) != null) {
//                String fund[] = data.split("" "");
//                double x = Double.parseDouble(fund[0]);
//                double y = Double.parseDouble(fund[1]);
//                pointss[count] = new Point2D(x, y);
////                String word = Integer.toString(count);
////                StdDraw.setPenColor(StdDraw.BLACK);
////                points[count].draw();
////                StdDraw.text(x, y, word);
//                count++;
//            }
//            MaxPQ<String> pq = new MaxPQ<String>();
//            FindNeighbors p = new FindNeighbors();
//            p.init(pointss);
//            Point2D ddd = new Point2D(0.144932,0.938569);
//            p.query(ddd, 50);
//
//        }
//    }
}
