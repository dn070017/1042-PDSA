import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {
	public static int[] ConvexHullVertex(Point2D[] a) {
		pre_sort(a);
		int first  = 0;
		int second = 1;
		int third  = 2;
		//Stack<Point2D> p = new Stack<Point2D>();
		Stack<Integer> idx = new Stack<Integer>();
		while(third < a.length){
			if(Point2D.ccw(a[first], a[second], a[third]) == 1){	
				//System.out.printf(""I first : %2d  second : %2d third : %2d\n"",first, second, third);
				//System.out.printf(""push x : %.3f  y : %.3f\n"",a[third].x(),a[third].y());
				idx.push(first);
				idx.push(second);
				idx.push(third++);
			}else if(Point2D.ccw(a[first], a[second], a[third]) == 0){
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
		//ConvexHullDrawing(index2pos(a, idx));
		return(index_reverse(idx));
	}
	
	private static void ConvexHullDrawing(Point2D[] p){
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

	private static Point2D[] index2pos(Point2D[] a, Stack<Integer> idx){
		Point2D[] c_h = new Point2D[idx.size()];
		int n = idx.size();
		for(int i = 0; i < n; i++){
			c_h[i] = a[idx.pop()];
		}
		return(c_h);
	}
	
	private static Point2D[] pre_sort(Point2D[] a){
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
			
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setCanvasSize(550, 550);
			StdDraw.setXscale(0, 100);
			StdDraw.setYscale(0, 34);
			StdDraw.setPenRadius(.005);
			
			
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
				int y = StdRandom.uniform(2)+12;
				points[i] = new Point2D(x, y);
				//points[i].draw();
			}
			
			
			/*Point2D ori = new Point2D(0,0);
			Arrays.sort(points, ori.X_ORDER);
			Arrays.sort(points, ori.Y_ORDER);
			ori = points[0];
			Arrays.sort(points, ori.POLAR_ORDER);
			StdDraw.setPenColor(StdDraw.RED);
			
			StdDraw.setPenRadius();
			StdDraw.setPenColor(StdDraw.GRAY);
			for (int i = 0; i < num; i++) {
				ori.drawTo(points[i]);
				//StdDraw.show(200);
			}
			
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.01);
			ori.draw();*/
						
			for(Integer i : ConvexHullVertex(points)){
				System.out.println(i);
			}
		}
    }
}
