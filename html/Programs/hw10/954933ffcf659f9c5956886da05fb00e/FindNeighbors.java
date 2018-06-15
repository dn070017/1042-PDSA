

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR!
    public FindNeighbors() {
    }

    private Node tree;

    private class Node {
        private Node left;
        private Node right;
        private Point2D value;

        public Node(Point2D value) {
            this.value = value;
        }
    }

//        private Point2D[] pts;
//        private Node root=null;
//        private Queue<Point2D> q=new Queue<>();



//        private Point2D chooseMediumPts(Point2D[] p){
//            Point2D[] tmp=p.clone();
//            Arrays.sort(tmp, new pointCmpX());
//            for (int i = 0; i < tmp.length; i++) {
//                System.out.println(tmp[i]);
//            }
//            return tmp[tmp.length/2];
//        }
    private Comparator<Point2D> getComparator(int i){
        return pointmpX.values()[i];
    }


    private enum pointmpX implements Comparator<Point2D> {
        vertical {
            @Override
            public int compare(Point2D a, Point2D b) {
                if (a.x() > b.x()) return 1;
                else return ((a.x() < b.x()) || (a.x() == b.x() && a.y() < b.y())) ? -1 : 0;
            }
        },
        horizontal {
            @Override
            public int compare(Point2D a, Point2D b) {
                if (a.y() > b.y()) return 1;
                else return ((a.y() < b.y()) || (a.y() == b.y() && a.x() < b.x())) ? -1 : 0;
            }
        }

    }

    private Node buildTree(List<Point2D> points, int vOrh) {
        if (points.isEmpty()) {
            return null;
        }
        Collections.sort(points, getComparator(vOrh % 2));
        int index = points.size() / 2;
        Node root = new Node(points.get(index));
        root.left = buildTree(points.subList(0, index), vOrh + 1);
        root.right = buildTree(points.subList(index + 1, points.size()), vOrh + 1);
        return root;

//            Arrays.sort(points,pointCmpX.values()[vOrh%2]);
//            int index=points.length/2;
//
//            if(points.length==1){
//                Node root=new Node(points[index]);
//                root.left=null;
//                root.right=null;
//                return root;
//            }
//
//            Node root=new Node(points[index-1]);
////
//            Point2D[] pL = Arrays.copyOfRange(points, 0, index);
//            Point2D[] pR = Arrays.copyOfRange(points, index, points.length);
//
//            root.left=buildTree(pL,vOrh+1);
//            root.right=buildTree(pR,vOrh+1);
//            return root;

//            Node nodeL,nodeR;
//            if(points.length==1)return;
//
//            if (verticalOrHorizontal) {
//                Arrays.sort(points, new pointCmpX());
//            } else {
//                Arrays.sort(points);
//            }
//
//            if(verticalOrHorizontal)verticalOrHorizontal=false;
//            else verticalOrHorizontal=true;
//
////            q.enqueue(points[points.length/2]);
//            if(points.length==1)return;
//
//            Point2D[] pL = Arrays.copyOfRange(points, 0, points.length / 2);
//            Point2D[] pR = Arrays.copyOfRange(points, points.length / 2, points.length);
//
//            buildTree(pL);
//            nodeL = new Node(null, null, pL[pL.length-1]);
//
//            buildTree(pR);
//            nodeR = new Node(null, null, pR[pR.length-1]);
//
//            root = new Node(nodeL, nodeR, points[points.length / 2 -1]);

    }


    // TODO
    // please use the Point2D from algs4.jar
    public void init(Point2D[] points) {
        List<Point2D> point2DList = new ArrayList<>(points.length);
        for (int i = 0; i < points.length; i++) {
            point2DList.add(points[i]);
        }
        tree = buildTree(point2DList, 0);
    }


    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar
    private Node tmp=null;
    private double bestDist=1.5;

    private void findKNN(Point2D point,int k,MaxPQ<Point2D> knn,Node root){
        if(root==null)return;

    }

    public Point2D[] query(Point2D point, int k) {
        MaxPQ<Point2D> knn=new MaxPQ<>();
        Point2D[] result = new Point2D[k];
        findKNN(point,k,knn,tree);
        return result;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String infix;
            ArrayList<Point2D> point2DArrayList = new ArrayList<>();
            while ((infix = br.readLine()) != null) {
                String tmp[] = infix.split("" "");
                point2DArrayList.add(new Point2D(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1])));
            }
            Point2D pts[] = new Point2D[point2DArrayList.size()];
            for (int i = 0; i < pts.length; i++) {
                pts[i] = point2DArrayList.get(i);
            }
            FindNeighbors find = new FindNeighbors();
            find.init(pts);

            Point2D p=new Point2D(0.1354339200, 0.7019446863);
            find.query(p,3);
        }
    }
}

