
import java.util.Arrays;

public class CriticalDis {
    public CriticalDis(){}
    /*
 
        static Point2D[] V;//我所有的V的陣列
        Bag<Point2D>[] adj;//BAG的陣列然後每個空間都可以存放座標
        public CriticalDis(Point2D[] V){
            this.V = new Point2D[V.length];
            adj = (Bag<Point2D>[]) new Bag[V.length];//給BAG一個V陣列的長度
            for(int i = 0; i < V.length;i++){
                this.V[i] = V[i];
                adj[i] = new Bag<Point2D>();
            }
        }
        public void addEdge(int v,Point2D w){
            adj[v].add(w);
        }
    
        static class DigraphDFS{
        private boolean[] marked;
        public DigraphDFS (CriticalDis G,int s){
            marked = new boolean[G.V.length];
            dfs(G,s);
        }
        private void dfs(CriticalDis G,int v){
            marked[v] = true;
            //System.out.println(v.x()+"" ""+v.y());
            for(Point2D w : G.adj[v]){
                double d = Math.sqrt(Math.pow(V[v].x()-w.x(), 2)+Math.pow(V[v].y()-w.y(), 2));
                int[] temp = new int[2];
                temp = relation.get(d);
                if(!marked[temp[1]])dfs(G,temp[1]);}
        }
        public boolean visited(int v){
            return marked[v];
        }}*/
    
        
    
    
    public static void main(String[] args)  throws Exception{
        In in = new In(args[0]);
        int N = Integer.parseInt(in.readLine());
        if(N<2){System.out.println(""0"");return;}
        Point2D[] A = new Point2D[N];
        Point2D S = new Point2D(1,1);
        Point2D T = new Point2D(0,0);
        int s = -1;
        int t = -1;
        for(int i = 0;i < N;i++){
            String[] datas = in.readLine().split("" "");
            double a = Double.parseDouble(datas[0]);
            double b = Double.parseDouble(datas[1]);
            A[i] = new Point2D(a,b);
            if(a+b<S.x()+S.y()) S = A[i];s = i;
            if(a+b>T.x()+T.y()) T = A[i];t = i;      
        }        
        Digraph digraph = new Digraph(A.length);  
        MinPQ<Double> minpq = new MinPQ<Double>();
        BinarySearchST<Double,int[]> relation = new BinarySearchST<Double,int[]>();
        for(int i = 0;i < A.length;i++){
            for(int j = i + 1;j < A.length;j++){
                if(A[i].x()<A[j].x() && A[i].y()<A[j].y()){
                    double d = Math.sqrt(Math.pow(A[i].x()-A[j].x(), 2)+Math.pow(A[i].y()-A[j].y(), 2));
                    minpq.insert(d);
                    int[] B = new int[2];
                    B[0] = i;
                    B[1] = j; 
                    relation.put(d,B);
                }
                else if(A[i].x()>A[j].x() && A[i].y()>A[j].y()){
                    double d = Math.sqrt(Math.pow(A[i].x()-A[j].x(), 2)+Math.pow(A[i].y()-A[j].y(), 2));
                    minpq.insert(d);
                    int[] B = new int[2];
                    B[0] = j;
                    B[1] = i;      
                    //System.out.println(B[0]+"" ""+B[1]);
                    relation.put(d,B);
                    
                }
            }           
        }
        while(!minpq.isEmpty()){
            double d = minpq.delMin();
            int[] C = relation.get(d);
            digraph.addEdge(C[0], C[1]);
            DirectedDFS find = new DirectedDFS(digraph,s);
            if(find.marked(t)){
                System.out.printf(""%1.3f\n"", d);
                break;
            }
        }
        
        //處理距離重複問題
        
        
        
        
    }
    
}

