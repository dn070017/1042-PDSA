
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;
import java.util.Arrays;

public class FindNeighbors {

    private Node top;

    public class Node implements Comparable<Node> {

        private Node left;
        private Node right;
        private Point2D setpoint;

        public Node(Node left, Node right, Point2D value) {
            this.left = left;
            this.right = right;
            this.setpoint = value;
        }

        @Override
        public int compareTo(Node that) {
            if (target.distanceTo(this.setpoint) < target.distanceTo(that.setpoint)) {
                return -1;
            } else if (target.distanceTo(this.setpoint) < target.distanceTo(that.setpoint)) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    boolean odd;
    Node po;

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {

        //top = new Node(null, null, points[points.length/2]);
        top = new Node(null, null, points[0]);

        for (int m = 1; m < points.length; m++) {
            po = new Node(null, null, points[m]);
            odd = false;
            put(top);
        }
    }

    private void put(Node temp) {
        if (temp == null) {
            return;
        }
        if (!odd) {
            odd = true;
            if (po.setpoint.x() < temp.setpoint.x()) {
                if (temp.left != null) {
                    put(temp.left);
                } else {
                    temp.left = po;
                }
                return;
            } else {
                if (temp.right != null) {
                    put(temp.right);
                } else {
                    temp.right = po;
                }
                return;
            }
        } else {
            odd = false;
            if (po.setpoint.y() < temp.setpoint.y()) {
                if (temp.left != null) {
                    put(temp.left);
                } else {
                    temp.left = po;
                }
                return;
            } else {
                if (temp.right != null) {
                    put(temp.right);
                } else {
                    temp.right = po;
                }
                return;
            }
        }
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    MaxPQ<Node> set;
    Point2D target;
    int num;

    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        set = new MaxPQ<>();
        target = point;
        num = k;
        odd = false;

        near(top);

        for (int i = k - 1; i >= 0; i--) {
            result[i] = set.delMax().setpoint;
        }
//        for (int i = 0; i < k; i++) {
//            System.out.println(result[i]);
//        }
        return result;
    }

    double dis1, dis2;

    private void near(Node run) {
        if (run == null) {
            return;
        }

        if (!odd) {
            odd = true;
            if (target.x() <= run.setpoint.x()) {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.left);

                if (run.right == null) {
                    return;
                }

                if (set.size() >= num) {
                    Node x = set.max();
                    dis1 = target.distanceTo(x.setpoint);
                    dis2 = target.x() - run.setpoint.x();
                    if ((dis1 * dis1) < (dis2 * dis2)) {
                        set.insert(x);
                        while (set.size() > num) {
                            set.delMax();
                        }
                        return;
                    }
                }
                odd = true;
                near(run.right);

            } else {
                set.insert(run);
                near(run.right);

                if (run.left == null) {
                    return;
                }

                if (set.size() >= num) {
                    Node x = set.max();
                    dis1 = target.distanceTo(x.setpoint);
                    dis2 = target.x() - run.setpoint.x();
                    if ((dis1 * dis1) < (dis2 * dis2)) {
                        set.insert(x);
                        while (set.size() > num) {
                            set.delMax();
                        }
                        return;
                    }
                }
                odd = true;
                near(run.left);
            }
        } else {
            odd = false;
            if (target.y() <= run.setpoint.y()) {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.left);
                if (run.right == null) {
                    return;
                }

                if (set.size() >= num) {
                    Node x = set.max();
                    dis1 = target.distanceTo(x.setpoint);
                    dis2 = target.y() - run.setpoint.y();
                    if ((dis1 * dis1) < (dis2 * dis2)) {
                        set.insert(x);
                        return;
                    }
                }
                odd = false;
                near(run.right);

            } else {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.right);
                if (run.left == null) {
                    return;
                }

                if (set.size() >= num) {
                    Node x = set.delMax();
                    dis1 = target.distanceTo(x.setpoint);
                    dis2 = target.y() - run.setpoint.y();
                    if ((dis1 * dis1) < (dis2 * dis2)) {
                        set.insert(x);
                        return;
                    }
                }
                odd = false;
                near(run.left);
            }
        }
        return;
    }

//    public static void main(String[] args) throws Exception {
//                                long time1=System.currentTimeMillis();
//        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
//            String data;
//            int count = 0;
////            StdDraw.setCanvasSize(500, 500);
//            Point2D[] pointss = new Point2D[20];
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
//            FindNeighbors p = new FindNeighbors();
//            p.init(pointss);
//            Point2D ddd = new Point2D(0.1354339200 ,0.7019446863);
//            p.query(ddd, 3);
//
//        }
//            long time2=System.currentTimeMillis();
////    System.out.println(time2-time1);
//    }
}

