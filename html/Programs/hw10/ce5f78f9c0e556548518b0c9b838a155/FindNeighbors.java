import java.io.BufferedReader;
import java.io.FileReader;
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
    public static Node put(Node x) {
        if (x == null) {
            x = new Node(null, null, where, dm);
            copy = x;
            return x;
        }
        dm++;
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
    private static Node root;
    public static int dm = 0;
    public static int where;
    private static Node copy;
    public static Point2D[] points;

    public static void init(Point2D[] point) {
        points=point;
        root = new Node(root, root, 0, 0);
        for (where = 1; where < point.length; where++) {
            dm = 0;
            put(root);
        }
    }

    public static Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        Node x = root;
        Point2D p = new Point2D(point.x(), point.y());
        for (int i = 0; i < k; i++) {
            result[i] = new Point2D(-2, -2);
        }
        for (int i = 1; i > 0; i++) {
            if (x == null) {
                break;
            }
            if ((x.deep % 2) == 0) {
                if (points[x.value].x() >= point.x()) {
                    if (points[x.value].distanceTo(point) < result[k - 1].distanceTo(point)) {
                        result[k - 1] = points[x.value];
                        Arrays.sort(result, p.DISTANCE_TO_ORDER);
                    }
                    x = x.left;

                } else {
                    if (points[x.value].distanceTo(point) < result[k - 1].distanceTo(point)) {
                        result[k - 1] = points[x.value];
                        Arrays.sort(result, p.DISTANCE_TO_ORDER);
                    }
                    x = x.right;
                }
            } else {
                if (points[x.value].y() >= point.y()) {
                    if (points[x.value].distanceTo(point) < result[k - 1].distanceTo(point)) {
                        result[k - 1] = points[x.value];
                        Arrays.sort(result, p.DISTANCE_TO_ORDER);
                    }
                    x = x.left;
                } else {
                    if (points[x.value].distanceTo(point) < result[k - 1].distanceTo(point)) {
                        result[k - 1] = points[x.value];
                        Arrays.sort(result, p.DISTANCE_TO_ORDER);
                    }
                    x = x.right;
                }
            }
        }
        Arrays.sort(result, p.DISTANCE_TO_ORDER);
//        System.out.println(result[0]);
//        System.out.println(result[1]);
//        System.out.println(result[2]);
        return result;
        // the points should be sorted accordingly to their distances to the query, from small to large
    }

//    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
//            String data;
//            int count = 0;
////            StdDraw.setCanvasSize(500, 500);
//            Point2D[] pointss=new Point2D[20];
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
//            init(pointss);
//            Point2D d = new Point2D(0.1354339200, 0.7019446863);
//            query(d, 3);
//
//        }
//    }
}

