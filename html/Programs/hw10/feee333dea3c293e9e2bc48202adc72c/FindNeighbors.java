//import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.awt.geom.Line2D;
//author: ymy

public class FindNeighbors {
    private TwoDTree tree;

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        tree = new TwoDTree();

        for(int i = 0; i < points.length; i++){
            try{
                tree.put(points[i]);
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        }
        return;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = tree.query(point, k);

        return result;
    }

    private class TwoDTree{
        public Node root;
        private MaxPQ<Distance> distancePQ;

        public TwoDTree(){}

        public void put (Point2D point) throws Exception{
            try{
                root = put(root, point, true, 0, 1, 0, 1);
            }
            catch(Exception e){
                throw e;
            }
        }

        public Node put(Node n, Point2D point, boolean odd, double xmin, double xmax, double ymin, double ymax) throws Exception{
            if(n == null){
                if(odd){
                    return new Node(point, odd, new Line2D.Double(point.x(), ymin, point.x(), ymax));
                }
                else{
                    return new Node(point, odd, new Line2D.Double(xmin, point.y(), xmax, point.y()));
                }
            }
            if(n.odd == true){                // vertical line
                if(point.x() < n.point.x()){        // point in right
                    n.left = put(n.left, point, !odd, xmin, n.point.x(), ymin, ymax);
                    return n;
                }
                else if(point.x() > n.point.x()){     // point in left
                    n.right = put(n.right, point, !odd, n.point.x(), xmax, ymin, ymax);
                    return n;
                }
                else{
                    throw new Exception(""In the same vertical line!"");
                }
            }
            else{                                   // horizontal line
                if(point.y() < n.point.y()){        // point below put right
                    n.left = put(n.left, point, !odd, xmin, xmax, ymin, n.point.y());
                    return n;
                }
                else if(point.y() > n.point.y()){              // point above put left
                    n.right = put(n.right, point, !odd, xmin, xmax, n.point.y(), ymax);
                    return n;
                }
                else{
                    throw new Exception(""In the same horizontal line!"");
                }
            }
        }

        public void printTree(){
            printNode(root);
        }

        public void printNode(Node n){
            System.out.println(n.point.toString());

            if(n.left != null){
                System.out.print(n.point.toString() + "" left: "");
                printNode(n.left);
            }
            if(n.right != null){
                System.out.print(n.point.toString() + "" right: "");
                printNode(n.right);
            }
        }

        public Point2D[] query(Point2D target, int num){
            distancePQ = new MaxPQ<Distance>();
            recursiveFind(root, target, num);
            Point2D[] result = new Point2D[distancePQ.size()];

            int i = distancePQ.size();
            for(Distance d : distancePQ){
                result[--i] = d.node.point;
            }

            return result;
        }

        private void recursiveFind(Node node, Point2D target, int num){
            if(node != null){
                double distance = target.distanceTo(node.point);

                if(distancePQ.size() >= num){
                    if(distancePQ.max().distance > distance){
                        distancePQ.delMax();
                        distancePQ.insert(new Distance(node, distance));
                    }
                }
                else{
                    distancePQ.insert(new Distance(node, distance));
                }

                if(node.odd == true){                                                                           // vertical line
                    if(target.x() < node.point.x()){                                                            // first find left
                        recursiveFind(node.left, target, num);
                        if(node.line.ptLineDist(target.x(), target.y()) < distancePQ.max().distance){
                            recursiveFind(node.right, target, num);
                        }
                    }
                    else{                                                                                       // first find right
                        recursiveFind(node.right, target, num);
                        if(node.line.ptLineDist(target.x(), target.y()) < distancePQ.max().distance){
                            recursiveFind(node.left, target,num);
                        }
                    }
                }
                else{                                                                                           // horizontal
                    if(target.y() < node.point.y()){                                                            // first find left
                        recursiveFind(node.left, target, num);
                        if(node.line.ptLineDist(target.x(), target.y()) < distancePQ.max().distance){
                            recursiveFind(node.right, target, num);
                        }
                    }
                    else{                                                                                       // first find right
                        recursiveFind(node.right, target, num);
                        if(node.line.ptLineDist(target.x(), target.y()) < distancePQ.max().distance){
                            recursiveFind(node.left, target,num);
                        }
                    }
                }
            }
        }

        private class Node{
            public Node right;
            public Node left;
            public Point2D point;
            public Line2D.Double line;
            public boolean odd;                 // detect point level

            public Node(Point2D point, boolean odd, Line2D.Double line){
                this.point = point;
                this.odd = odd;
                this.line = line;
            }
        }

        private class Distance implements Comparable<Distance> {
            public Node node;
            public double distance;

            public Distance(Node node, double distance){
                this.node = node;
                this.distance = distance;
            }
            public int compareTo(Distance that){
                if(this.distance < that.distance) return -1;
                if(this.distance < that.distance) return +1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String reg;
        ArrayList <Point2D> arrayPoints = new ArrayList<Point2D>();

        while(in.hasNextLine()){
            reg = in.readLine();
            String[] coordinate = reg.split("" "");
            double x = Double.parseDouble(coordinate[0]);
            double y = Double.parseDouble(coordinate[1]);
            arrayPoints.add(new Point2D(x,y));
        }

        Point2D[] points = arrayPoints.toArray(new Point2D[0]);

        FindNeighbors find = new FindNeighbors();
        find.init(points);
        // find.tree.printTree();
        Point2D target = new Point2D(0.1354339200, 0.7019446863);
        Point2D[] pointss = find.query(target, 3);
        for(int i = 0; i < pointss.length; i++){
            System.out.println(i + "": ("" + pointss[i].x() + "" , "" + pointss[i].y() + "")"");
        }
    }

}

