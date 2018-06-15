import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;

public class CriticalDis{
	private MinPQ<Double> pq;
	private My_Point2D[] PointArray;
	private int N;
	private Digraph DG;
	private Double[][] DistTable;
	private DepthFirstPaths DFS;
	
	CriticalDis(Point2D[] ps){
		N 			= ps.length;
		for(int i = 0; i < N; i++){
			PointArray[i] = new My_Point2D(ps[i], i);
		}
		DG 			= new Digraph(N);
		DistTable 	= new Double[N][N];
		pq 			= new MinPQ<Double>();
	}
	
	CriticalDis(My_Point2D[] m_ps){
		N 			= m_ps.length;
		PointArray 	= m_ps;
		DG 			= new Digraph(N);
		DistTable 	= new Double[N][N];
		pq 			= new MinPQ<Double>();
	}
	
	private void DG_construction(){
		SortByDiagonal();
		//DrawPoints();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(PointArray[i].x() < PointArray[j].x() && PointArray[i].y() < PointArray[j].y() ){
					//DrawEdge(i, j, 0);
					DG.addEdge(i, j); //addEdge(tail vertex, head vertex)
					DistTable[i][j] = PointArray[i].distanceTo(PointArray[j]);
					pq.insert(DistTable[i][j]);
				}
			}
		}
	}
	
	private Double CriticalDisFinf(){
		Double c_dist = pq.delMin();
		DFS = new DepthFirstPaths(DG, 0, c_dist);
		while(!DFS.hasPathTo(N-1)){
			c_dist = pq.delMin();
			//System.out.println(""Now dist :"" + c_dist);
			DFS = new DepthFirstPaths(DG, 0, c_dist);
		}
		return c_dist;
	}
	
	private void SortByIdx(){
		Arrays.sort(PointArray, PointArray[0].INDEX_ORDER);
	}
	
	private void SortByDiagonal(){
		Arrays.sort(PointArray, PointArray[0].DIAGONAL_ORDER);
	}
	
	public void DrawPoints(){
		StdDraw.setPenRadius(.01);
		StdDraw.setPenColor(StdDraw.RED);
		PointArray[0].draw();
		PointArray[N-1].draw();
		StdDraw.setPenColor(StdDraw.BLACK);
		for(int i = 0; i < N; i++){
			String s = String.valueOf(i);
			StdDraw.text(PointArray[i].x()+0.05, PointArray[i].y(), s);
		}
	}
	
	private void DrawEdge(int i, int j, int col){
		StdDraw.setPenRadius(.001);
		if		(col == 0)StdDraw.setPenColor(StdDraw.GRAY);
		else if	(col == 1)StdDraw.setPenColor(StdDraw.RED);
		else			  StdDraw.setPenColor(StdDraw.BLUE);
		PointArray[i].drawTo(PointArray[j]);
	}
	
	public static void SetConvas(){
		StdDraw.setCanvasSize(600, 600);
		StdDraw.setXscale(-0.05, 1.05);
		StdDraw.setYscale(-0.05, 1.05);
		StdDraw.setPenRadius(.005);
	}
	
	
	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			int N      = Integer.parseInt(br.readLine());
			My_Point2D[] PointArray = new My_Point2D[N];
			
			//SetConvas();
           
			for(int i = 0; i < N; i++) {
				String[] line = br.readLine().split("" "");
				//StdDraw.setPenColor(StdDraw.BLACK);
				double x = Double.parseDouble(line[0]);
				double y = Double.parseDouble(line[1]);
				My_Point2D p = new My_Point2D(x, y, i);
				PointArray[i] = p;
				//p.draw();
				//StdDraw.setPenColor(StdDraw.BLUE);
				//String s = String.valueOf(i);
				//StdDraw.text(PointArray[i].x()+0.05, PointArray[i].y(), s);
			}
			
			CriticalDis CDis = new CriticalDis(PointArray);
			CDis.DG_construction();
			System.out.printf(""%1.3f\n"", CDis.CriticalDisFinf());
			//System.out.println(""Done!"");
		}	
	}
	
	private class DepthFirstPaths {
		private boolean[] marked;    // marked[v] = is there an s-v path?
		private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
		private final int s;         // source vertex
		private Double c_dist;

		public DepthFirstPaths(Digraph G, int s, Double c_dist) {
			this.s = s;
			this.c_dist = c_dist;
			edgeTo = new int[G.V()];
			marked = new boolean[G.V()];
			dfs(G, s);
		}

		private void dfs(Digraph G, int v) {
			marked[v] = true;
			for (int w : G.adj(v)) {
				if (!marked[w] && DistTable[v][w] <= c_dist) {
					edgeTo[w] = v;
					//DrawEdge(w, v, 1);
					dfs(G, w);
				}
			}
		}
		
		public boolean hasPathTo(int v) {
			return marked[v];
		}
	
		public Iterable<Integer> pathTo(int v) {
			if (!hasPathTo(v)) return null;
			Stack<Integer> path = new Stack<Integer>();
			for (int x = v; x != s; x = edgeTo[x])
				path.push(x);
			path.push(s);
			return path;
		}
	}
}



class My_Point2D extends Point2D{
	
	private int idx;
	
	My_Point2D(double x, double y, int idx){
		super(x, y);
		this.idx = idx;
	}
	
	My_Point2D(Point2D p, int idx){
		super(p.x(), p.y());
		this.idx = idx;
	}
	
	public static final Comparator<My_Point2D> DIAGONAL_ORDER = new DIAGONALOrder();
	
	public static final Comparator<My_Point2D> INDEX_ORDER = new INDEXOrder();
		
	private static class DIAGONALOrder implements Comparator<My_Point2D> {
		public int compare(My_Point2D p, My_Point2D q) {
		if (p.x() + p.y() < q.x() + q.y()) return -1;
		if (p.x() + p.y() < q.x() + q.y()) return +1;
			return 0;
		}
	}
	
	private static class INDEXOrder implements Comparator<My_Point2D> {
		public int compare(My_Point2D p, My_Point2D q) {
		if (p.idx < q.idx) return -1;
		if (p.idx > q.idx) return +1;
		return 0;
		}
	}
}

