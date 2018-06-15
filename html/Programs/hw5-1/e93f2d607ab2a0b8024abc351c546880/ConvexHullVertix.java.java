import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {

	public static int[] ConvexHullVertex(Point2D[] a) {
		My_Point2D[] m_a = new My_Point2D[a.length];
		
		for(int idx = 0; idx < a.length; idx++){
			m_a[idx] = new My_Point2D(a[idx].x(), a[idx].y(), idx);
		}
		pre_sort(m_a);
		int first  = 0;
		int second = 1;
		int third  = 2;
		//Stack<Point2D> p = new Stack<Point2D>();
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
	
	private static int[] new2ori(My_Point2D[] a, Stack<Integer> idx){
		int[] ori = new int[idx.size()];
		int i = idx.size();
		for(Integer j : idx){
			ori[--i] = a[j].ori_idx();
		}
		Arrays.sort(ori);
		return(ori);
	}
	
	private static void ConvexHullDrawing(My_Point2D[] p){
		Point2D source = p[p.length-1];
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BLUE);
		for(Point2D sink : p){
			source.drawTo(sink);
			//System.out.printf(""x : %.3f  y : %.3f\n"", sink.x(), sink.y());
			source = sink;
			StdDraw.show(100);
		}
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
	
	private static My_Point2D[] pre_sort(My_Point2D[] a){
		int num = a.length;
		Point2D ori = new Point2D(0,0);
		Arrays.sort(a, ori.X_ORDER);
		Arrays.sort(a, ori.Y_ORDER);
		ori = a[0];
		Arrays.sort(a, ori.POLAR_ORDER);
		/*StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < num; i++) {
				ori.drawTo(a[i]);
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
	
	private static int[] index_reverse(Stack<Integer> idx){
		int n = idx.size();
		int[] idx_r = new int[n];
		while(!idx.isEmpty()){
			idx_r[--n] = idx.pop();
		}
		return(idx_r);
	}
	
    public static void main(String[] args) throws Exception {
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            Double dist = Double.parseDouble(br.readLine());
            int num = Integer.parseInt(br.readLine());
            Point2D[] points = new Point2D[num];
			
			
			/*StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setCanvasSize(550, 550);
			StdDraw.setXscale(0, 100);
			StdDraw.setYscale(0, 100);
			StdDraw.setPenRadius(.005);*/
			
			
			/*for(int row = 0; row < num; row++) {
				String[] s = br.readLine().split("" "");
				Double x = Double.parseDouble(s[0]);
				Double y = Double.parseDouble(s[1]);
				//System.out.printf(""x : %.3f y : %.3f\n"", x, y);
				points[row] = new Point2D(x, y);
				points[row].draw();
			}*/
			
			for (int i = 0; i < num; i++) {
				int x = StdRandom.uniform(75)+12;
				int y = StdRandom.uniform(75)+12;
				points[i] = new Point2D(x, y);
				//points[i].draw();
			}
					
			for(Integer i : ConvexHullVertex(points)){
				System.out.println(i);
			}
		}
    }
}

class My_Point2D extends Point2D{
	private int ori_idx;
	
	public My_Point2D(double x, double y, int idx){
		super(x, y);
		ori_idx = idx;
	}
	
	public int ori_idx(){
		return(ori_idx);
	}
}
