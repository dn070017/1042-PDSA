
import java.util.Arrays;

public class CriticalDis {
    static BinarySearchST<Point2D,Integer> number = new BinarySearchST<Point2D,Integer>(); 
 
        Point2D[] V;//我所有的V的陣列
        Bag<Point2D>[] adj;//BAG的陣列然後每個空間都可以存放座標
        public CriticalDis(Point2D[] V){
            this.V = new Point2D[V.length];
            adj = (Bag<Point2D>[]) new Bag[V.length];//給BAG一個V陣列的長度
            for(int i = 0; i < V.length;i++){
                this.V[i] = V[i];
                adj[i] = new Bag<Point2D>();
                number.put(V[i], i);
            }
        }
        public void addEdge(Point2D v,Point2D w){
            adj[number.get(v)].add(w);
        }
        public Iterable<Point2D> adj(Point2D v){
            return adj[number.get(v)];
        }
    
    static class DigraphDFS{
        private boolean[] marked;
        public DigraphDFS (CriticalDis G,Point2D S){
            marked = new boolean[G.V.length];
            dfs(G,S);
        }
        private void dfs(CriticalDis G,Point2D v){
            marked[number.get(v)] = true;
            //System.out.println(v.x()+"" ""+v.y());
            for(Point2D w : G.adj[number.get(v)])
                if(!marked[number.get(w)])dfs(G,w);
        }
        public boolean visited(Point2D v){
            return marked[number.get(v)];
        }}
    
        
    
    
    public static void main(String[] args)  throws Exception{
        In in = new In(args[0]);
        int N = Integer.parseInt(in.readLine());
        if(N<2){System.out.println(""0"");return;}
        Point2D[] A = new Point2D[N];
        Point2D S = new Point2D(1,1);
        Point2D T = new Point2D(0,0);
        for(int i = 0;i < N;i++){
            String[] datas = in.readLine().split("" "");
            double a = Double.parseDouble(datas[0]);
            double b = Double.parseDouble(datas[1]);
            A[i] = new Point2D(a,b);
            if(a+b<S.x()+S.y()) S = A[i];
            if(a+b>T.x()+T.y()) T = A[i];            
        }
        CriticalDis digraph = new CriticalDis(A);  
        MinPQ<Double> minpq = new MinPQ<Double>();
        BinarySearchST<Double,Point2D[]> relation = new BinarySearchST<Double,Point2D[]>();   
        for(int i = 0;i < A.length;i++){
            for(int j = i + 1;j < A.length;j++){
                if(A[i].x()<A[j].x() && A[i].y()<A[j].y()){
                    double d = Math.sqrt(Math.pow(A[i].x()-A[j].x(), 2)+Math.pow(A[i].y()-A[j].y(), 2));
                    minpq.insert(d);
                    Point2D[] B = new Point2D[2];
                    B[0] = A[i];
                    B[1] = A[j]; 
                    //System.out.println(B[0]+"" ""+B[1]);
                    relation.put(d,B);
                }
                else if(A[i].x()>A[j].x() && A[i].y()>A[j].y()){
                    double d = Math.sqrt(Math.pow(A[i].x()-A[j].x(), 2)+Math.pow(A[i].y()-A[j].y(), 2));
                    minpq.insert(d);
                    Point2D[] B = new Point2D[2];
                    B[0] = A[j];
                    B[1] = A[i];      
                    //System.out.println(B[0]+"" ""+B[1]);
                    relation.put(d,B);
                    
                }
            }           
        }
        while(!minpq.isEmpty()){
            double d = minpq.delMin();
            Point2D[] C = relation.get(d);
            //System.out.println(C[0]+"" to ""+C[1]);
            digraph.addEdge(C[0], C[1]);
            DigraphDFS find = new DigraphDFS(digraph,S);
            if(find.visited(T)){
                System.out.printf(""%1.3f\n"", d);
                break;
            }
        }
        
        //處理距離重複問題
        
        
        
        
    }
    
}

