import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class FindNeighbors {
	private MaxPQ<Node>  pq;
	private Node rt;
	
	// DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){
		pq = new MaxPQ<Node>();
	}

    // TODO
    // please use the Point2D from algs4.jar 
	public void init(Point2D[] points){
		rt = new Node(points[0], 0);
		for(int i = 1; i < points.length; i++){
			Node n = new Node(points[i], i);
			kd_T(rt , n, 0);
		}
		return;
    }
	
	public Node kd_T(Node root, Node n, int d){
		if(root == null){
			n.set_depth(d);	
			return n;
		}
		
		if(root.get_depth()%2 == 0){
			if(root.get_pos().x() > n.get_pos().x()) {
				root.left  = kd_T(root.get_left(), n, ++d);
			}else{
				root.right = kd_T(root.get_right(), n, ++d);
			}
		}else{
			if(root.get_pos().y() > n.get_pos().y()) {
				root.left  = kd_T(root.get_left() , n, ++d);
			}else{
				root.right = kd_T(root.get_right(), n, ++d);
			}
		}
		return root;
	}
	
	public void NKN(Point2D p, int k){
		NKN(rt, p, k);
	}
	
	private void NKN(Node root, Point2D p, int k){
		if(pq.size() > k) pq.delMax();
		if(root == null) return;
		if(root.get_depth()%2 == 0){
			if(root.get_pos().x() - p.x() > 0){
				root.set_dist(p);
				pq.insert(root);
				NKN(root.get_left() , p, k);
				if(pq.max().get_dist() > Math.abs(root.get_pos().x() - p.x())){
					NKN(root.get_right(), p, k);
				} 
			}else{
				root.set_dist(p);
				pq.insert(root);
				NKN(root.get_right(), p, k);
				if(pq.max().get_dist() > Math.abs(root.get_pos().x() - p.x())){
					NKN(root.get_left(), p, k);
				}
			}
		}else{
			if(root.get_pos().y() - p.y() > 0){
				root.set_dist(p);
				pq.insert(root);
				NKN(root.get_left() , p, k);
				if(pq.max().get_dist() > Math.abs(root.get_pos().y() - p.y())){
					NKN(root.get_right(), p, k);
				}
				
			}else{
				root.set_dist(p);
				pq.insert(root);
				NKN(root.get_right(), p, k);
				if(pq.max().get_dist() > Math.abs(root.get_pos().y() - p.y())){
					NKN(root.get_left(), p, k);
				}
			}
		}
	}
	
	public void Tree_tr(){
		Tree_tr(rt);
	}
	
	private void Tree_tr(Node root){
		if(root == null) return;
		System.out.println(root.get_idx());
		Tree_tr(root.get_left() );
		System.out.println(""Right"");
		Tree_tr(root.get_right());
	}

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
		NKN(point, k);
		
		int i = k-1;
		while(!pq.isEmpty()){
			result[i] = pq.delMax().get_pos();
			//System.out.println(i + "" "" + result[i].toString());
			i--;
		}
		return result;
    }
	
	private class Node implements Comparable<Node>{
		private Node    left;
		private Node    right;
		private Point2D pos;
		private Double  dist;
		private int     depth;
		private int     idx;
		
		public Node(Point2D p, int id){
			pos   = p;
			idx   = id;
		}
		
		public Point2D get_pos(){
			return pos;
		}
		
		public Double get_dist(){
			return dist;
		}
		
		public Node get_left(){
			return left;
		}
		
		public Node get_right(){
			return right;
		}
		
		public int get_depth(){
			return depth;
		}
		
		public int get_idx(){
			return idx;
		}
		
		public void set_dist(Point2D n){
			dist = pos.distanceTo(n);
		}
		
		public void set_depth(int d){
			depth = d;
		}
		
		public void set_left(Node n){
			left  = n;
		}
		
		public void set_right(Node n){
			right = n;
		}
		
		public int compareTo(Node that) {
			if (this.dist < that.dist)      return -1;
			else if (this.dist > that.dist) return +1;
			else                            return  0;
		}
	}
	
	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			int N      = Integer.parseInt(br.readLine());
			Point2D[] PointArray = new Point2D[N];
		
			FindNeighbors FN = new FindNeighbors();
            
			for(int i = 0; i < N; i++) {
				String[] s = br.readLine().split("" "");
				double x = Double.parseDouble(s[0]);
				double y = Double.parseDouble(s[1]);
				Point2D p = new Point2D(x, y);
				PointArray[i] = p;
			}
			
			FN.init(PointArray);
			Point2D p = new Point2D(0.531440, 0.616918 );
			FN.query(p, 10);
		}
	}
	
}
