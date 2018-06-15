import java.util.Comparator;
public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    Node root = null;

    // TODO
    // please use the Point2D from algs4.jar 

    public void init(Point2D[] points) {
        if (points == null) {
            return;//如果傳進來沒有點
        }
        root = new Node(points[0], true);//第一個點是root        
        for (int i = 1; i < points.length; i++) {//增加每一個點到tree下面
            addTree(points[i]);
        }
        return;
    }

    private void addTree(Point2D p) {//增加新的點在樹下
        Node x = root;
        while (x != null) {
            if (x.getDirection() == true) {//x是垂直
                if (p.x() <= x.getXValue()) {//如果新點在x的左邊
                    if (x.getLeft() != null) {
                        x = x.getLeft();
                    } else {
                        x.setLeft(new Node(p, false));//左邊沒有值，代表p必須長在那裏
                        break;
                    }
                } else {//如果新點在x的右邊
                    if (x.getRight() != null) {
                        x = x.getRight();
                    } else {
                        x.setRight(new Node(p, false));//右邊沒有值，代表p必須長在那邊
                        break;
                    }
                }
            } else {//x是水平
                if (p.y() <= x.getYValue()) {//如果新點在x的下面
                    if (x.getLeft() != null) {
                        x = x.getLeft();
                    } else {
                        x.setLeft(new Node(p, true));
                        break;
                    }
                    //下面沒有值，代表p必須長在那邊
                } else {//如果新點在x的上面
                    if (x.getRight() != null) {
                        x = x.getRight();
                    } else {
                        x.setRight(new Node(p, true));
                        break;
                    }
                    //上面沒有值，代表p必須長在那邊
                }
            }
        }
    }

    MaxPQ<Node> pq ;
    Node thisNode;
    Point2D p;
    int z;

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 

    public Point2D[] query(Point2D point, int k) {
        pq = new MaxPQ<Node>();
        Point2D[] result = new Point2D[k];
        z = k;
        p = point;
        thisNode = new Node(point, true);

        findQuery(root);
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.delMax().getValue();
        }
        return result;
    }

    private void findQuery(Node n) {
        if (n == null) {
            return;
        }
        n.setDistance(p);
        if (pq.size() < z) {//pq裡面的數量小於z就直接將n寫進去
            pq.insert(n);
        } else if (p.distanceTo(pq.max().getValue()) > p.distanceTo(n.getValue())) {
            pq.delMax();
            pq.insert(n);
        }
        if (thisNode.leftRight(n) == -1) {//p點在n的左邊
            findQuery(n.getLeft());
            if (thisNode.lineDistance(n) < p.distanceTo(pq.max().getValue()) || pq.size() < z) {
                findQuery(n.getRight());
            }
            return;
        } else {//p點在n的右邊
            findQuery(n.getRight());
            if (thisNode.lineDistance(n) < p.distanceTo(pq.max().getValue()) || pq.size() < z) {
                findQuery(n.getLeft());
            }
            return;
        }
    }
}

class Node implements Comparable<Node>{

    private Node left = null;
    private Node right = null;
    private Point2D value;
    private boolean direction;//true 代表垂直，false代表水平
    private double distance;

    public Node(Point2D value, boolean b) {//使用者須輸入點的值和水平垂直
        this.value = value;
        direction = b;
    }

    public Node getLeft() {
        return (this.left);
    }

    public Node getRight() {
        return (this.right);
    }

    public int leftRight(Node n) {
        if (n.getDirection() == true) {//比較對象是垂直線
            if (this.getXValue() < n.getXValue()) {
                return -1;//比x
            } else if (this.getXValue() == n.getXValue()) {
                return 0;
            }
            return 1;
        } else {//比較對象是水平線
            if (this.getYValue() < n.getYValue()) {
                return -1;//比y
            } else if (this.getYValue() == n.getYValue()) {
                return 0;
            }
            return 1;
        }
    }

    public double lineDistance(Node n) {//得到該點與縣的最短路徑
        if (n.getDirection() == true) {
            return Math.abs(this.getXValue() - n.getXValue());
        } else {
            return Math.abs(this.getYValue() - n.getYValue());
        }
    }
    
    public void setDistance(Point2D p){
        distance= this.value.distanceTo(p);
    }
    public int compareTo(Node that){
        if(this.distance>that.distance) return 1;
        else if(this.distance==that.distance) return 0;
        else return -1;
    }

    public Point2D getValue() {
        return (this.value);
    }

    public double getXValue() {
        return (this.value.x());
    }

    public double getYValue() {
        return (this.value.y());
    }

    public boolean getDirection() {
        return (this.direction);
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

