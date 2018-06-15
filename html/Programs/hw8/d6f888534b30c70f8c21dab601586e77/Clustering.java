import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Comparator;
import java.util.Iterator;
class RelationBST<Key extends Comparable<Key>, Value>{
        public RelationBST(){}
        private class Node{
            private Key key;
            private Value val;
            private Node left, right;
            public Node(Key key, Value val){
            this.key = key;
            this.val = val;}}
            private Node root;
            public void put(Key key, Value val)
            { root = put(root, key, val); }
            private Node put(Node x, Key key, Value val){
                if (x == null) return new Node(key, val);
                int cmp = key.compareTo(x.key);
                if (cmp < 0)
                x.left = put(x.left, key, val);
                else if (cmp > 0)
                x.right = put(x.right, key, val);
                else if (cmp == 0)
                x.val = val;
                return x;   }
            public Value get(Key key)
                {Node x = root;
                while (x != null){
                int cmp = key.compareTo(x.key);
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else if (cmp == 0) return x.val;}
                return null;}
            public void delete(Key key){ put(key,null); }
        
    }
public class Clustering {
    private Point2D[]clusterArray;//放cluster的array
    private int[]parent;//merge在一起就設parent
    private MinPQ minpq;
    public Clustering(int N){
        clusterArray = new Point2D[2*N];
        parent = new int[2*N];
        for(int i = 0;i < 2*N;i++){
            parent[i]=i;}
        minpq = new MinPQ(N);//用作者給的class
    }
    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];}
        return p;
    }

    public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int pointCount = Integer.parseInt(br.readLine());
            Clustering clustering = new Clustering(pointCount);
            RelationBST<Integer,String[]> relation = new RelationBST<Integer,String[]>();
            for(int i = 0;i < pointCount;i++){
                String[] datas = br.readLine().split("" "");
                double a = Double.parseDouble(datas[0]);
                double b = Double.parseDouble(datas[1]);
                //System.out.println(""a = ""+a+""   b = ""+b);
                clustering.clusterArray[i] = new Point2D(a,b);}
            //Math.pow((B[i].x()-A[j].x()),2)+Math.pow((B[i].y()-A[j].y()), 2)<=d*d
        if(clustering.clusterArray[1]==null){
            System.out.println(""1 ""+clustering.clusterArray[0].x()+"" ""+clustering.clusterArray[0].y());
            System.out.println(""0.00"");
            return;}    
        if(clustering.clusterArray[2]==null){
            double dsq =Math.pow(clustering.clusterArray[0].x()-clustering.clusterArray[1].x(),2)+Math.pow(clustering.clusterArray[0].y()-clustering.clusterArray[1].y(), 2);
            double d = Math.pow(dsq, 1/2.0);
            if(clustering.clusterArray[0].x()<clustering.clusterArray[1].x()){
                System.out.println(""1 ""+clustering.clusterArray[0].x()+"" ""+clustering.clusterArray[0].y());
                System.out.println(""1 ""+clustering.clusterArray[1].x()+"" ""+clustering.clusterArray[1].y());
                System.out.println(d);return;}
            else if(clustering.clusterArray[0].x()>clustering.clusterArray[1].x()){
                System.out.println(""1 ""+clustering.clusterArray[1].x()+"" ""+clustering.clusterArray[1].y());
                System.out.println(""1 ""+clustering.clusterArray[0].x()+"" ""+clustering.clusterArray[0].y());
                System.out.println(d);return;}
            else if(clustering.clusterArray[0].y()<clustering.clusterArray[1].y()){
                System.out.println(""1 ""+clustering.clusterArray[0].x()+"" ""+clustering.clusterArray[0].y());
                System.out.println(""1 ""+clustering.clusterArray[1].x()+"" ""+clustering.clusterArray[1].y());
                System.out.println(d);return;}
            else if(clustering.clusterArray[0].y()>clustering.clusterArray[1].y()){
                System.out.println(""1 ""+clustering.clusterArray[1].x()+"" ""+clustering.clusterArray[1].y());
                System.out.println(""1 ""+clustering.clusterArray[0].x()+"" ""+clustering.clusterArray[0].y());
                System.out.println(d);return;}}
        if(clustering.clusterArray[3]==null){
            double dsq1 =Math.pow(clustering.clusterArray[0].x()-clustering.clusterArray[1].x(),2)+Math.pow(clustering.clusterArray[0].y()-clustering.clusterArray[1].y(),2);
            double d1 = Math.pow(dsq1, 1/2.0);
            double dsq2 =Math.pow(clustering.clusterArray[0].x()-clustering.clusterArray[2].x(),2)+Math.pow(clustering.clusterArray[0].y()-clustering.clusterArray[2].y(),2);
            double d2 = Math.pow(dsq2, 1/2.0);
            double dsq3 =Math.pow(clustering.clusterArray[1].x()-clustering.clusterArray[2].x(),2)+Math.pow(clustering.clusterArray[1].y()-clustering.clusterArray[2].y(),2);
            double d3 = Math.pow(dsq3, 1/2.0);
            double dmax=d1;
            if(d2>dmax)dmax=d2;
            if(d3>dmax)dmax=d3;
            Point2D MAX = clustering.clusterArray[0];
            if(clustering.clusterArray[1].x()>MAX.x()) MAX= clustering.clusterArray[1];
            if(clustering.clusterArray[2].x()>MAX.x()) MAX= clustering.clusterArray[2];
            if(clustering.clusterArray[1].x()==MAX.x()){
                if(clustering.clusterArray[1].y()>MAX.y())MAX= clustering.clusterArray[1]; }
            if(clustering.clusterArray[1].x()==MAX.x()){
                if(clustering.clusterArray[2].y()>MAX.y())MAX= clustering.clusterArray[2]; }
            Point2D MIN = clustering.clusterArray[0];
            if(clustering.clusterArray[1].x()<MIN.x()) MIN = clustering.clusterArray[1];
            if(clustering.clusterArray[2].x()<MIN.x()) MIN = clustering.clusterArray[2];
            if(clustering.clusterArray[1].x()==MIN.x()){
                if(clustering.clusterArray[1].y()<MIN.y())MIN = clustering.clusterArray[1]; }
            if(clustering.clusterArray[1].x()==MIN.x()){
                if(clustering.clusterArray[2].y()<MIN.y())MIN = clustering.clusterArray[2]; }
            Point2D MIDDLE = clustering.clusterArray[0];
            if((MAX.equals(clustering.clusterArray[0]) && MIN.equals(clustering.clusterArray[1])) || (MAX.equals(clustering.clusterArray[1])&& MIN.equals(clustering.clusterArray[0]))){
                MIDDLE = clustering.clusterArray[2];}
            if((MAX.equals(clustering.clusterArray[0]) && MIN.equals(clustering.clusterArray[2])) || (MAX.equals(clustering.clusterArray[2])&& MIN.equals(clustering.clusterArray[0]))){
                MIDDLE = clustering.clusterArray[1];}
            if((MAX.equals(clustering.clusterArray[1]) && MIN.equals(clustering.clusterArray[2])) || (MAX.equals(clustering.clusterArray[2])&& MIN.equals(clustering.clusterArray[1]))){
                MIDDLE = clustering.clusterArray[0];}
            System.out.println(""1 ""+MIN.x()+"" ""+MIN.y());
            System.out.println(""1 ""+MIDDLE.x()+"" ""+MIDDLE.y());
            System.out.println(""1 ""+MAX.x()+"" ""+MAX.y());
            System.out.println(dmax);return;}
        
   
        
        
        
        
        
        
        
        }}}

