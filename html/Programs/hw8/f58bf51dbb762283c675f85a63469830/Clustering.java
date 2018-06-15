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
        if(clustering.clusterArray[2]==null){
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
                System.out.println(String.format(""%.2f"",d));return;}}
       
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
            for(int i = 0;i < pointCount;i++){
            //System.out.println(""i =""+i+"" ""+clustering.clusterArray[i]);
            }
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
        int alocation = clustering.find(0);
        int acount = 0;
        for(int i =0;i < N;i++){
            if(clustering.find(i)==alocation)acount++;}
        int blocation = 0;
        int bcount = 0;
        for(int i =0;i < N;i++){
            if(clustering.find(i)!=alocation)
            {blocation = clustering.find(i);
            break; }}
        for(int i =0;i < N;i++){
            if(clustering.find(i)==blocation)bcount++;}
        int clocation = 0;
        int ccount = 0;
        for(int i =0;i < N;i++){
            if(clustering.find(i)!=alocation && clustering.find(i)!=blocation)
            {clocation = clustering.find(i);
            break; }}
        for(int i =0;i < N;i++){
            if(clustering.find(i)==clocation)ccount++;}
        //System.out.println(""alocation =""+alocation+""acount = ""+acount);
        //System.out.println(""blocation =""+blocation+""bcount = ""+bcount);
        //System.out.println(""clocation =""+clocation+""ccount = ""+ccount);
        size[0] = acount;
        size[1] = bcount;
        size[2] = ccount;
        x[0] = clustering.clusterArray[alocation].x();
        x[1] = clustering.clusterArray[blocation].x();
        x[2] = clustering.clusterArray[clocation].x();
        y[0] = clustering.clusterArray[alocation].y();
        y[1] = clustering.clusterArray[blocation].y();
        y[2] = clustering.clusterArray[clocation].y();
        for(int i = 0;i < 3;i++){
            for(int j = i;j > 0;j--){
                if(size[j-1]<size[j]){
                    int swap;swap = size[j-1];size[j-1] = size[j];size[j] = swap;
                    double swap1;swap1 = x[j-1];x[j-1] = x[j];x[j] = swap1;
                    double swap2;swap2 = y[j-1];y[j-1] = y[j];y[j] = swap2;}
                else if(size[j-1]>size[j]) break;
                else {
                    if(x[j-1]>x[j]){
                        int swap;swap = size[j-1];size[j-1] = size[j];size[j] = swap;
                        double swap1;swap1 = x[j-1];x[j-1] = x[j];x[j] = swap1;
                        double swap2;swap2 = y[j-1];y[j-1] = y[j];y[j] = swap2;}
                    else if(x[j-1]<x[j])break;
                    else {
                        if(y[j-1]>y[j]){
                            int swap;swap = size[j-1];size[j-1] = size[j];size[j] = swap;
                            double swap1;swap1 = x[j-1];x[j-1] = x[j];x[j] = swap1;
                            double swap2;swap2 = y[j-1];y[j-1] = y[j];y[j] = swap2;}
                        else break;}}}}
        System.out.println(size[0]+"" ""+String.format(""%.2f"",x[0])+"" ""+String.format(""%.2f"",y[0]));
        System.out.println(size[1]+"" ""+String.format(""%.2f"",x[1])+"" ""+String.format(""%.2f"",y[1]));
        System.out.println(size[2]+"" ""+String.format(""%.2f"",x[2])+"" ""+String.format(""%.2f"",y[2]));
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
        
            
            
            
            
                    
        
        
        
        
        
        
        
        
      

