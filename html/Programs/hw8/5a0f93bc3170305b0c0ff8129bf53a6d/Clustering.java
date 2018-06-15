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
    private MinPQ mindistance;
    public Clustering(int N){
        clusterArray = new Point2D[2*N];
        parent = new int[2*N];
        for(int i = 0;i < 2*N;i++){
            parent[i]=i;}
        minpq = new MinPQ(N);//用作者給的class
        mindistance = new MinPQ(N);
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
            RelationBST<Double,Point2D[]> relation = new RelationBST<Double,Point2D[]>();
            for(int i = 0;i < pointCount;i++){
                String[] datas = br.readLine().split("" "");
                double a = Double.parseDouble(datas[0]);
                double b = Double.parseDouble(datas[1]);
                //System.out.println(""a = ""+a+""   b = ""+b);
                clustering.clusterArray[i] = new Point2D(a,b);}
            //Math.pow((B[i].x()-A[j].x()),2)+Math.pow((B[i].y()-A[j].y()), 2)<=d*d
        if(pointCount==0){System.out.println(""0.00"");return;}
        if(clustering.clusterArray[1]==null){
            System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[0].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[0].y()));
            System.out.println(""0.00"");
            return;}    
        /*if(clustering.clusterArray[2]==null){
            double dsq =Math.pow(clustering.clusterArray[0].x()-clustering.clusterArray[1].x(),2)+Math.pow(clustering.clusterArray[0].y()-clustering.clusterArray[1].y(), 2);
            double d = Math.pow(dsq, 1/2.0);
            if(clustering.clusterArray[0].x()<clustering.clusterArray[1].x()){
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[0].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[0].y()));
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[1].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[1].y()));
                System.out.println(String.format(""%.2f"",d));return;}
            else if(clustering.clusterArray[0].x()>clustering.clusterArray[1].x()){
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[1].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[1].y()));
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[0].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[0].y()));
                System.out.println(d);return;}
            else if(clustering.clusterArray[0].y()<clustering.clusterArray[1].y()){
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[0].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[0].y()));
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[1].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[1].y()));
                System.out.println(String.format(""%.2f"",d));return;}
            else if(clustering.clusterArray[0].y()>clustering.clusterArray[1].y()){
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[1].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[1].y()));
                System.out.println(""1 ""+String.format(""%.2f"",clustering.clusterArray[0].x())+"" ""+String.format(""%.2f"",clustering.clusterArray[0].y()));
                System.out.println(String.format(""%.2f"",d));return;}}*/
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
            System.out.println(""1 ""+String.format(""%.2f"",MIN.x())+"" ""+String.format(""%.2f"",MIN.y()));
            System.out.println(""1 ""+String.format(""%.2f"",MIDDLE.x())+"" ""+String.format(""%.2f"",MIDDLE.y()));
            System.out.println(""1 ""+String.format(""%.2f"",MAX.x())+"" ""+String.format(""%.2f"",MAX.y()));
            System.out.println(String.format(""%.2f"", dmax));return;}
        for(int i = 0;i < pointCount;i++){
            for(int j = i+1;j < pointCount;j++){
                double dsq = Math.pow(clustering.clusterArray[i].x()-clustering.clusterArray[j].x(),2)+Math.pow(clustering.clusterArray[i].y()-clustering.clusterArray[j].y(),2);
                double d = Math.pow(dsq,1/2.0);
                Point2D[] doublevalue = new Point2D[2];
                doublevalue[0] = clustering.clusterArray[i];
                doublevalue[1] = clustering.clusterArray[j];
                clustering.minpq.insert(d);//把距離存進去PQ
                relation.put(d,doublevalue);}}//把對應關係存進去KEY-VALUE
       
        int N = pointCount;
        int edge = pointCount*2-3;
        while(pointCount<edge){//double douNumber1=Double.parseDouble(number.toString());
            /*for(int i = 0;i < pointCount;i++){
            System.out.println(""i =""+i+"" ""+clustering.clusterArray[i]);
            }*/
            double d = Double.parseDouble(clustering.minpq.delMin().toString());//把最小的值從PQ取出來  然後轉成DOUBLE
            Point2D[] value = relation.get(d);//用取出的KEY去找來源的兩個點
            Point2D a = value[0];//第一個點
            Point2D b = value[1];//第二個點
            int alocation = -1;
            int blocation = -1;//想找出這兩個點在陣列中的位置 for看是不是自己的根來確認是否為有用值
            for(int i = 0;i < pointCount;i++){
                if(a.equals(clustering.clusterArray[i])) alocation = i;
                else if(b.equals(clustering.clusterArray[i])) blocation = i;}
            //System.out.println(""alocation =""+alocation+""blocation = ""+blocation);
            int acount = 0;
            int bcount = 0;
            if(clustering.find(alocation) != alocation || clustering.find(blocation) != blocation){ //自己是自己的根  沒有被MERGE過
                continue;}
            for(int i = 0;i < N;i++){
                if(clustering.find(i) == alocation)acount++;
                else if(clustering.find(i) == blocation)bcount++;}//計算總共有幾個原始的小孩 for算重心
            double x = (clustering.clusterArray[alocation].x()*acount+clustering.clusterArray[blocation].x()*bcount)/(acount+bcount);
            double y = (clustering.clusterArray[alocation].y()*acount+clustering.clusterArray[blocation].y()*bcount)/(acount+bcount);
            clustering.clusterArray[pointCount] = new Point2D(x,y);//把新的點放進去陣列
            clustering.parent[pointCount] = pointCount;
            clustering.parent[alocation] =  pointCount;
            clustering.parent[blocation] =  pointCount;//把merge的關係記錄下來
            pointCount++;
            //把新的cluster跟其他非小孩的距離算出來然後放進PQ跟RELATION的關係
            for(int i = 0;i < (pointCount-1) ;i++){
                if(clustering.find(i)==pointCount-1)continue;
                if(clustering.find(i)!=i)continue;
                double dsq = Math.pow(clustering.clusterArray[pointCount-1].x()-clustering.clusterArray[clustering.find(i)].x(),2)+Math.pow(clustering.clusterArray[pointCount-1].y()-clustering.clusterArray[clustering.find(i)].y(),2);
                double dd = Math.pow(dsq,1/2.0);
                Point2D[] doublevalue = new Point2D[2];
                doublevalue[0] = clustering.clusterArray[pointCount-1];
                doublevalue[1] = clustering.clusterArray[i];
                //System.out.println(pointCount-1+"" 第一個點""+clustering.clusterArray[pointCount-1]+i+"" 第二個點""+clustering.clusterArray[i]);
                clustering.minpq.insert(dd);//把距離存進去PQ
                relation.put(dd,doublevalue);}}//把對應關係存進去KEY-VALUE
        //輸出SIZE最大的三個跟最短距離
        int[] size = new int[3];
        double[] x = new double[3];
        double[] y = new double[3];
        for(int i = 0;i < N;i++){
            if(clustering.find(i)==pointCount-1) size[0]++;
            else if(clustering.find(i)==pointCount-2) size[1]++;
            else if(clustering.find(i)==pointCount-3) size[2]++;}
        x[0] = clustering.clusterArray[pointCount-1].x();
        x[1] = clustering.clusterArray[pointCount-2].x();
        x[2] = clustering.clusterArray[pointCount-3].x();
        y[0] = clustering.clusterArray[pointCount-1].y();
        y[1] = clustering.clusterArray[pointCount-2].y();
        y[2] = clustering.clusterArray[pointCount-3].y();
        Point2D MAX = clustering.clusterArray[pointCount-1];
        Point2D MIN = clustering.clusterArray[pointCount-1];
        Point2D MIDDLE = clustering.clusterArray[pointCount-1];
        int max = 0;int min = 0;int middle = 0;
        for(int i = 0;i < 3;i++){
            for(int j = i+1;j < 3;j++){
                if(size[j]>size[i]) {MAX = clustering.clusterArray[pointCount-j-1]; max = j;}
                else if(size[j]<size[i]) {MIN = clustering.clusterArray[pointCount-j-1]; min = j;}
                else if(x[j]<x[i]) {MAX = clustering.clusterArray[pointCount-j-1];max = j;;}
                else if(x[j]>x[i]) {MIN = clustering.clusterArray[pointCount-j-1];min = j;}
                else if(y[j]<y[i]) {MAX = clustering.clusterArray[pointCount-j-1];max = j;;}
                else if(y[j]>y[i]) {MIN = clustering.clusterArray[pointCount-j-1];min = j;}}}
        if((MAX.equals(clustering.clusterArray[pointCount-1]) && MIN.equals(clustering.clusterArray[pointCount-2])) || (MAX.equals(clustering.clusterArray[pointCount-2])&& MIN.equals(clustering.clusterArray[pointCount-1]))){
                MIDDLE = clustering.clusterArray[pointCount-3];middle = 2;}
        else if((MAX.equals(clustering.clusterArray[pointCount-1]) && MIN.equals(clustering.clusterArray[pointCount-3])) || (MAX.equals(clustering.clusterArray[pointCount-3])&& MIN.equals(clustering.clusterArray[pointCount-1]))){
                MIDDLE = clustering.clusterArray[pointCount-2];middle = 1;}
        else if((MAX.equals(clustering.clusterArray[pointCount-2]) && MIN.equals(clustering.clusterArray[pointCount-3])) || (MAX.equals(clustering.clusterArray[pointCount-3])&& MIN.equals(clustering.clusterArray[pointCount-2]))){
                MIDDLE = clustering.clusterArray[pointCount-1];middle = 0;}
        System.out.println(size[max]+"" ""+String.format(""%.2f"",MAX.x())+"" ""+String.format(""%.2f"",MAX.y()));
        System.out.println(size[middle]+"" ""+String.format(""%.2f"",MIDDLE.x())+"" ""+String.format(""%.2f"",MIDDLE.y()));
        System.out.println(size[min]+"" ""+String.format(""%.2f"",MIN.x())+"" ""+String.format(""%.2f"",MIN.y()));
        for(int i = 0;i<N;i++){
            for(int j = i+1;j<N;j++){
                if(clustering.find(i) == clustering.find(j))continue;
                else{
                    double dsq = Math.pow(clustering.clusterArray[i].x()-clustering.clusterArray[j].x(),2)+Math.pow(clustering.clusterArray[i].y()-clustering.clusterArray[j].y(),2);
                    double d = Math.pow(dsq,1/2.0);
                    clustering.mindistance.insert(d);}}}
        double d = Double.parseDouble(clustering.mindistance.delMin().toString());
        System.out.println(String.format(""%.2f"",d));
        
        
       
        
        
        
        
        
        }}}
        
            
            
            
            
                    
        
        
        
        
        
        
        
        
      

