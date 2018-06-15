

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR!
    public FindNeighbors() {
    }

    private Node tree;

    private class Node {
        private Node left;
        private Node right;
        private Point2D value;
        private double dist;

        public Node(Point2D value) {
            this.value = value;
        }

        public Node getR(){return this.right;}

        public Node getL(){return this.left;}

        public void setDist(Point2D a,Point2D b){
            this.dist=a.distanceTo(b);
        }

        public double getDist(){return  this.dist;}
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
    private Comparator<Node> getComparator(int i){
        return pointmpX.values()[i];
    }

    private enum pointmpX implements Comparator<Node> {
        vertical {
            @Override
            public int compare(Node a, Node b) {
                if (a.value.x() > b.value.x()) return 1;
                else return ((a.value.x() < b.value.x()) || (a.value.x() == b.value.x() && a.value.y() < b.value.y())) ? -1 : 0;
            }
        },
        horizontal {
            @Override
            public int compare(Node a, Node b) {
                if (a.value.y() > b.value.y()) return 1;
                else return ((a.value.y() < b.value.y()) || (a.value.y() == b.value.y() && a.value.x() < b.value.x())) ? -1 : 0;
            }
        },
        distance {
            @Override
            public int compare(Node a,Node b){
                if(a.dist>b.dist)return 1;
                else return -1;
            }
        }

    }

    private Node buildTree(List<Node> points, int vOrh) {
        if (points.isEmpty()) {
            return null;
        }
        Collections.sort(points, getComparator(vOrh % 2));
        int index = points.size() / 2;
        Node root = points.get(index);
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
    List<Node> pl;

    // TODO
    // please use the Point2D from algs4.jar
    public void init(Point2D[] points) {
        List<Node> point2DList = new ArrayList<>(points.length);
        pl=new ArrayList<>(points.length);

        for (int i = 0; i < points.length; i++) {
            point2DList.add(new Node(points[i]));
            pl.add(new Node(points[i]));
        }
        tree = buildTree(point2DList, 0);
    }


    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar
    private Node tmp=new Node(new Point2D(100,100));
//    private double bestDist=1.5;

    private Node findKNN(Node testNode,int k,MaxPQ<Node> knn,Node currentNode,int vOrh) {
        if (currentNode == null) return null;
        int lOrr = getComparator(vOrh%k).compare(testNode, currentNode);

        Node tmp = (lOrr < 0) ? currentNode.left : currentNode.right;
        Node otherSide = (lOrr < 0) ? currentNode.right : currentNode.left;
        Node bestNode = (tmp == null) ? currentNode : findKNN(testNode, k, knn,tmp, vOrh + 1);
        if(currentNode.value.distanceTo(testNode.value)<bestNode.value.distanceTo(testNode.value)){
            bestNode=currentNode;
        }
        if(otherSide!=null) {
            Node pBestNode;
            if (vOrh == 0) {
                if(abs(currentNode.value.x()-testNode.value.x())<abs(testNode.value.x()-bestNode.value.x())){
                    pBestNode=findKNN(testNode,k,knn,otherSide,vOrh+1);
                    if(pBestNode.value.distanceTo(testNode.value)<bestNode.value.distanceTo(testNode.value));
                }
            }else{
                if(abs(currentNode.value.y()-testNode.value.y())<abs(testNode.value.y()-bestNode.value.y())){
                    pBestNode=findKNN(testNode,k,knn,otherSide,vOrh+1);
                    if(pBestNode.value.distanceTo(testNode.value)<bestNode.value.distanceTo(testNode.value));
                }
            }
        }
        return bestNode;
//        if(knn.size()<k){
//            currentNode.setDist(currentNode.value,testNode.value);
//            knn.insert(currentNode);
//            int lOrr=getComparator(vOrh%2).compare(testNode,currentNode);
//
//            if(lOrr<0){
//                findKNN(testNode,k,knn,currentNode.getL(),vOrh+1);
//            }
//            else {
//                findKNN(testNode,k,knn,currentNode.getR(),vOrh+1);
//            }
//        }else if(tmp.value.distanceTo(testNode.value)>currentNode.value.distanceTo(testNode.value)){
//            tmp=currentNode;
//            currentNode.setDist(currentNode.value,testNode.value);
//            knn.insert(currentNode);
//            if(knn.size()>k)knn.delMax();
//
//            if((vOrh%2==0)){
//                if(abs(testNode.value.x()-currentNode.value.x())<knn.max().getDist()){
//                    int lOrr=getComparator(vOrh%2).compare(testNode,currentNode);
//
//                    if(lOrr<0){
//                        findKNN(testNode,k,knn,currentNode.getL(),vOrh+1);
//                    }
//                    else {
//                        findKNN(testNode,k,knn,currentNode.getR(),vOrh+1);
//                    }
//                }
//            }else {
//                if (abs(testNode.value.y() - currentNode.value.y()) < knn.max().getDist()) {
//                    int lOrr=getComparator(vOrh%2).compare(testNode,currentNode);
//
//                    if(lOrr<0){
//                        findKNN(testNode,k,knn,currentNode.getL(),vOrh+1);
//                    }
//                    else {
//                        findKNN(testNode,k,knn,currentNode.getR(),vOrh+1);
//                    }
//                }
//            }
//        }

//        int lOrr=getComparator(vOrh%2).compare(testNode,currentNode);
//
//        if(lOrr<0){
//            findKNN(testNode,k,knn,currentNode.getL(),vOrh+1);
////            if(currentNode.getR()!=null)findKNN(testNode,k,knn,currentNode.getR(),vOrh+1);
//        }
//        else {
//            findKNN(testNode,k,knn,currentNode.getR(),vOrh+1);
////            if(currentNode.getL()!=null)findKNN(testNode,k,knn,currentNode.getL(),vOrh+1);
//        }

//        Node otherSide=(lOrr<0)?currentNode.right:currentNode.left;
//        if(otherSide!=null  && (currentNode.value.distanceTo(testNode.value)<tmp.value.distanceTo(testNode.value))){
//            findKNN(testNode,k,knn,otherSide,vOrh+1);
//        }
    }

    public Point2D[] query(Point2D point, int k) {
        MaxPQ<Node> knn=new MaxPQ<>(getComparator(2));
        Point2D[] result = new Point2D[k];
        Node testNode=new Node(point);
        result[0]= findKNN(testNode,k,knn,tree,0).value;

//        for (int i = result.length-1; i >0; i--) {
//            result[i]=knn.delMax().value;
//        }
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

            Point2D p=new Point2D(6,3);

            Point2D[] r=find.query(p,1);
            for (int i = 0; i < r.length; i++) {
                System.out.println(r[i]);
            }
        }
    }
}

