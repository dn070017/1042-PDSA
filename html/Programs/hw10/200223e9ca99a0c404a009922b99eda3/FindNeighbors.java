import java.io.BufferedReader;
import java.io.FileReader;

public class FindNeighbors {

    Node tree_2d;
    int qeurynum;
    double range;

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        tree_2d = new Node(null, null, points[0]); //設定第一點
        tree_2d.level = 0;
        int levelidx = 1;
        int maxlevel = 0;
        Node firsttreenode = tree_2d;
        Node newtree = tree_2d;
        int loopend = 0;
        for (int i = 1; i < points.length; i++) {
            while (loopend == 0) {
                if (newtree.level % 2 == 0) {  //若是even level，則要判斷x座標
                    if ((points[i].x()) > newtree.point.x()) {
                        if (newtree.right == null) {  //若右邊點沒有node
                            Node temp = new Node(null, null, points[i]);
                            newtree.right = temp;
                            if (newtree.level + 1 <= maxlevel) {
                                newtree.right.level = newtree.level + 1;
                            } else {
                                newtree.right.level = levelidx;
                                maxlevel = levelidx;
                                levelidx++;
                            }
                            newtree = firsttreenode;  //指回去第一點node
                            break;
                        }
                        newtree = newtree.right;
                    } else {
                        if (newtree.left == null) {  //若左邊點沒有node
                            Node temp = new Node(null, null, points[i]);
                            newtree.left = temp;
                            if (newtree.level + 1 <= maxlevel) {
                                newtree.left.level = newtree.level + 1;
                            } else {
                                newtree.left.level = levelidx;
                                maxlevel = levelidx;
                                levelidx++;
                            }
                            newtree = firsttreenode;  //指回去第一點node
                            break;
                        }
                        newtree = newtree.left;
                    }
                } else if (newtree.level % 2 != 0) { //若是odd level，則要判斷y座標
                    if ((points[i].y()) > newtree.point.y()) {
                        if (newtree.right == null) {  //若右邊點沒有node
                            Node temp = new Node(null, null, points[i]);
                            newtree.right = temp;
                            if (newtree.level + 1 <= maxlevel) {
                                newtree.right.level = newtree.level + 1;
                            } else {
                                newtree.right.level = levelidx;
                                maxlevel = levelidx;
                                levelidx++;
                            }
                            newtree = firsttreenode;  //指回去第一點node
                            break;
                        }
                        newtree = newtree.right;
                    } else {
                        if (newtree.left == null) {  //若左邊點沒有node
                            Node temp = new Node(null, null, points[i]);
                            newtree.left = temp;
                            if (newtree.level + 1 <= maxlevel) {
                                newtree.left.level = newtree.level + 1;
                            } else {
                                newtree.left.level = levelidx;
                                maxlevel = levelidx;
                                levelidx++;
                            }
                            newtree = firsttreenode;  //指回去第一點node
                            break;
                        }
                        newtree = newtree.left;
                    }
                }
            }
        }
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        MaxPQ maxpq = new MaxPQ();  //用來存所計算的距離
        MaxPQ viomaxpq = new MaxPQ();
        Point2D[] result = new Point2D[k];
        qeurynum = k;
        distance(this.tree_2d, point, maxpq);
        double[] temp = new double[k];
        for (int i = k - 1; i >= 0; i--) {
            Node tt = (Node) maxpq.delMax();
            temp[i] = tt.distance;
            result[i] = tt.point;
        }

//        for (int i = 0; i < k; i++) {
//            System.out.println(result[i].x() + "" "" + result[i].y() + "" "" + temp[i]);
//        }
        return result;
    }

    private void distance(Node x, Point2D point, MaxPQ<Node> q) {
        if (x == null) {
            return;
        }
        if (q.size() < qeurynum) {  //當還沒找到qeury個時都要走
            double distancecount;
            distancecount = Math.sqrt(Math.pow(x.point.x() - point.x(), 2) + Math.pow(x.point.y() - point.y(), 2));
            x.distance = distancecount;
            q.insert(x);
            if (q.size() == qeurynum) {  //當剛好是qeury個時，記錄此時range
                range = q.max().distance;
            }
            distance(x.left, point, q);
            distance(x.right, point, q);
        } else {
            if (x.level % 2 == 0) {
                if (Math.abs(x.point.x() - point.x()) < range) { //更新range
                    double distancecount;
                    distancecount = Math.sqrt(Math.pow(x.point.x() - point.x(), 2) + Math.pow(x.point.y() - point.y(), 2));
                    x.distance = distancecount;
                    q.insert(x);
                    q.delMax();  //去除先前的點
                    range = q.max().distance;
                    distance(x.left, point, q);
                    distance(x.right, point, q);
                } else { //判斷哪一邊的子樹不走
                    if (point.x() > x.point.x()) {
                        distance(x.right, point, q);
                    } else {
                        distance(x.left, point, q);
                    }
                }
            } else {
                if (Math.abs(x.point.y() - point.y()) < range) { //更新range
                    double distancecount;
                    distancecount = Math.sqrt(Math.pow(x.point.x() - point.x(), 2) + Math.pow(x.point.y() - point.y(), 2));
                    x.distance = distancecount;
                    q.insert(x);
                    q.delMax();  //去除先前的點
                    range = q.max().distance;
                    distance(x.left, point, q);
                    distance(x.right, point, q);
                } else { //判斷哪一邊的子樹不走
                    if (point.y() > x.point.y()) {
                        distance(x.right, point, q);
                    } else {
                        distance(x.left, point, q);
                    }
                }
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            FindNeighbors a = new FindNeighbors();
            Point2D[] points = new Point2D[100000];
//            int idx = 0;
//            for (String in = br.readLine(); in != null; in = br.readLine()) {
//                String[] temp = in.split("" "");
//                double[] coord = new double[2];
//                coord[0] = Double.parseDouble(temp[0]);
//                coord[1] = Double.parseDouble(temp[1]);
//                points[idx++] = new Point2D(coord[0], coord[1]);
//            }

            for (int i = 0; i < 100000; i++) {
                double x = StdRandom.uniform();
                double y = StdRandom.uniform();
                points[i] = new Point2D(x, y);
            }

            a.init(points);
            Point2D[] result = new Point2D[50000];
            Point2D target;

            for (int i = 0; i < 1; i++) {
                double x = StdRandom.uniform();
                double y = StdRandom.uniform();
                target = new Point2D(x, y);
                result = a.query(target, 50000);
                //System.out.println("" "");
            }

        }
    }

    private static class Node implements Comparable<Node> {

        public Node left;
        public Node right;
        public Point2D point;
        public int level;
        public double distance;

        public Node(Node left, Node right, Point2D point) {
            this.left = left;
            this.right = right;
            this.point = point;
        }

        public int compareTo(Node that) {
            if (this.distance - that.distance > 0) {
                return 1;
            } else if (this.distance - that.distance < 0) {
                return -1;
            }
            return 0;
        }
    }
}

