import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;


public class MyConvexHull {
	public static Double dist;
	public static int cc_num;
	
	private static int[] ConvexHullVertex(My_Point2D[] m_a) {
		pre_sort(m_a);
		int first  = 0;
		int second = 1;
		int third  = 2;
		Stack<Integer> idx = new Stack<Integer>();
		while(third < m_a.length){
			if(Point2D.ccw(m_a[first], m_a[second], m_a[third]) == 1){	
				//System.out.printf(""I first : %2d  second : %2d third : %2d\n"",first, second, third);
				//System.out.printf(""push x : %.3f  y : %.3f\n"",m_a[third].x(),m_a[third].y());
				idx.push(first);
				idx.push(second);
				idx.push(third++);
			}else if(Point2D.ccw(m_a[first], m_a[second], m_a[third]) == 0){
				idx.push(first);
				idx.push(third++);
			}else{
				//System.out.printf(""O first : %2d  second : %2d third : %2d\n"",first, second, third);
				//System.out.printf(""pop  x : %.3f  y : %.3f\n"",e.x(), e.y());
				idx.push(first);
			}
			second = idx.pop();
			first =  idx.pop();
		}
		idx.push(first);
		idx.push(second);
		//index_print(idx);
		//ConvexHullDrawing(index2pos(m_a, idx));
		return(new2ori(m_a, idx));
	} 
	
	public static int[] ConvexHullVertex(Point2D[] a) {
		My_Point2D[] m_a = new My_Point2D[a.length];
		
		int[] cc_idx = points_cc(a, dist);
		int[] s_e = s_e_find(cc_idx);
		
		for(int idx = 0; idx < a.length; idx++){
			m_a[idx] = new My_Point2D(a[idx].x(), a[idx].y(), idx, cc_idx[idx]);
		}
		
		int[] len = new int[cc_num];
		for(int i = 0; i<cc_num; i++){
			if(s_e[2*i+1] - s_e[2*i] > 1){
				My_Point2D[] sub_m_a = M_a_split(m_a, s_e[2*i], s_e[2*i+1]);
				len[i] = ConvexHullVertex(sub_m_a).length;
			}
		}
						
		return(len);
	}
	
	private static int[] s_e_find(int[] cc_idx){
		int[] out = new int[cc_num*2];
		int s = 0;
		int e = 1;
		int i = 0;
		while(e < cc_idx.length){
			if(cc_idx[e] == cc_idx[s]){
				e++;
			}else{
				out[i++] = s;
				out[i++] = e-1;
				s = e;
				e++;
			}
			if(e == cc_idx.length){
				out[i++] = s;
				out[i++] = s;
			}
			//System.out.printf(""s : %2d e : %2d\n"", s, e);
		}
		/*for(Integer idx : out){
			System.out.printf("" %2d"", idx);
		}
		System.out.println();*/
		return(out);
	}
	
	private static My_Point2D[] M_a_split(My_Point2D[] a, int start, int end){
		My_Point2D[] out = new My_Point2D[end - start + 1];
		for(int i = start; i < end+1; i++){
			out[i-start] = a[i];
		}
		return(out);
	}	
	
	private static int[] new2ori(My_Point2D[] a, Stack<Integer> idx){
		int[] ori = new int[idx.size()];
		int i = idx.size();
		for(Integer j : idx){
			ori[--i] = a[j].ori_idx();
		}
		Arrays.sort(ori);
		return(ori);
	}
	
	private static My_Point2D[] index2pos(My_Point2D[] a, Stack<Integer> idx){
		My_Point2D[] c_h = new My_Point2D[idx.size()];
		int n = idx.size();
		int i = 0;
		for(Integer j : idx){
			c_h[i++] = a[j];
		}
		return(c_h);
	}

	private static void ConvexHullDrawing(My_Point2D[] p){
		My_Point2D source = p[p.length-1];
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BLUE);
		for(My_Point2D sink : p){
			source.drawTo(sink);
			//System.out.printf(""x : %.3f  y : %.3f\n"", sink.x(), sink.y());
			source = sink;
			StdDraw.show(100);
		}
	}
	
	private static My_Point2D[] pre_sort(My_Point2D[] a){
		int num = a.length;
		My_Point2D ori = new My_Point2D(0,0,0,0);
		Arrays.sort(a, ori.X_ORDER);
		Arrays.sort(a, ori.Y_ORDER);
		ori = a[0];
		Arrays.sort(a, ori.POLAR_ORDER);
		/*StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < num; i++) {
				//ori.drawTo(a[i]);
				//StdDraw.show(200);
			}
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(.01);
		ori.draw();*/
		return(a);
	}
	
	private static void index_print(Stack<Integer> idx){
		for(Integer i : idx){
			System.out.println(i);
		}
	}
	
	private static int[] points_cc(Point2D[] a, Double dist){
		int num = a.length;
		My_QF cc = new My_QF(num);
		for(int i=0; i < num ; i++){
			for(int j = i+1; j < num; j++){
				if(a[i].distanceTo(a[j]) < dist){
					cc.union(i,j);
					//a[i].drawTo(a[j]);
				}
			}
		}
			
		/*for(Integer i : cc.id_array()){
			System.out.printf("" %2d"", i);
		}*/
		//System.out.printf(""\n"");
		cc_num = cc.count();
		return(cc.id_array());
	}
	
    public static void main(String[] args) throws Exception {
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            dist = Double.parseDouble(br.readLine());
            int num = Integer.parseInt(br.readLine());
            Point2D[] points = new Point2D[num];
			
			
			/*StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setCanvasSize(550, 550);
			StdDraw.setXscale(0, 1);
			StdDraw.setYscale(0, 1);
			StdDraw.setPenRadius(.005);*/
			
			
			for(int row = 0; row < num; row++) {
				String[] s = br.readLine().split("" "");
				Double x = Double.parseDouble(s[0]);
				Double y = Double.parseDouble(s[1]);
				points[row] = new Point2D(x, y);
			}
			
			/*for (int i = 0; i < num; i++) {
				int x = StdRandom.uniform(75)+12;
				int y = StdRandom.uniform(75)+12;
				points[i] = new Point2D(x, y);
				//points[i].draw();
			}*/
					
			
			int sum = 0;
			for(Integer i : ConvexHullVertex(points)){
				sum += i;
			}
			
			
			System.out.println(sum);
			
		}
    }
}

class My_Point2D extends Point2D{
	private int ori_idx;
	private int cc_idx;
	
	public My_Point2D(double x, double y, int idx, int cc){
		super(x, y);
		ori_idx = idx;
		cc_idx = cc;
	}
	
	public int ori_idx(){
		return(ori_idx);
	}
	
	public int cc_idx(){
		return(cc_idx);
	}
}

class My_QF {
    public  int[] id;    // id[i] = component identifier of i
    private int count;   // number of components
    
	public My_QF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int[] id_array(){
		return(id);
	}
	
	public int count() {
        return count;
    }
	
    public int find(int p) {
        validate(p);
        return id[p];
    }

    // validate that p is a valid index
    private void validate(int p) {
        int N = id.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + N);
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }
  
    public void union(int p, int q) {
        if (connected(p, q)) return;
        int pid = id[p];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = id[q]; 
        count--;
    }
}
