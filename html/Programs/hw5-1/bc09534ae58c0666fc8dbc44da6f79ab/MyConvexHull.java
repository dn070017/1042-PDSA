import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {
	public static int[] ConvexHullVertex(Point2D[] a) {
		int first  = 0;
		int second = 1;
		int third  = 2;
		Stack<Point2D> p = new Stack<Point2D>();
		Stack<Integer> idx = new Stack<Integer>();
		idx.push(first);
		idx.push(second);
		p.push(a[first]);
		p.push(a[second]);
		while(third < a.length){
			if(Point2D.ccw(a[first], a[second], a[third]) == 1){
				//System.out.printf(""I first : %2d  second : %2d third : %2d\n"",first, second, third);
				first = second;
				second = third;
				//System.out.printf(""push x : %.3f  y : %.3f\n"",a[third].x(),a[third].y());
				idx.push(third);
				p.push(a[third++]);
			}else{
				idx.pop();
				Point2D e = p.pop();
				//System.out.printf(""O first : %2d  second : %2d third : %2d\n"",first, second, third);
				//System.out.printf(""pop  x : %.3f  y : %.3f\n"",e.x(), e.y());
				first--;
				second--;
			}
		}
		//ConvexHullDrawing(p);
		return(index_reverse(idx));
	}
	
	private static void ConvexHullDrawing(Stack<Point2D> p){
		Point2D source = p.peek();
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BLUE);
		for(Point2D sink : p){
			source.drawTo(sink);
			System.out.printf(""x : %.3f  y : %.3f\n"", sink.x(), sink.y());
			source = sink;
			StdDraw.show(100);
		}
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
			StdDraw.setCanvasSize(300, 300);
			StdDraw.setXscale(0, 1);
			StdDraw.setYscale(0, 1);
			StdDraw.setPenRadius(.005);
			
			
			for(int row = 0; row < num; row++) {
				String[] s = br.readLine().split("" "");
				Double x = Double.parseDouble(s[0]);
				Double y = Double.parseDouble(s[1]);
				//System.out.printf(""x : %.3f y : %.3f\n"", x, y);
				points[row] = new Point2D(x, y);
				points[row].draw();
			}
			
			Point2D ori = new Point2D(0,0);
			Arrays.sort(points, ori.Y_ORDER);
			ori = points[0];
			Arrays.sort(points, ori.POLAR_ORDER);
			StdDraw.setPenColor(StdDraw.RED);
			
			StdDraw.setPenRadius();
			StdDraw.setPenColor(StdDraw.GRAY);
			for (int i = 0; i < num; i++) {
				ori.drawTo(points[i]);
				StdDraw.show(200);
			}
			
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.01);
			ori.draw();
			
			
			ConvexHullVertex(points);
		}
    }
}
