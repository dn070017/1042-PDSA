import java.util.*;
import java.lang.*;

public class MyConvexHull {
	public static int[] ConvexHullVertex (Point2D[] a){
		Point2D[] b = new Point2D[a.length];
		int[] index = new int[a.length];
		
		for(int i = 0; i<a.length; i++){
			b[i]=a[i];
			index[i]=i;
		}

		Stack<Point2D> hull = new Stack<Point2D>();
		Stack<Integer> inthull = new Stack<Integer>(); 
		Arrays.sort(a, Point2D.Y_ORDER);
		Arrays.sort(a, a[0].POLAR_ORDER);

		hull.push(a[0]);
		hull.push(a[1]);
		inthull.push(index[0]);
		inthull.push(index[1]);
		
//		StdDraw.setPenColor(StdDraw.BLUE);
//		StdDraw.text(a[0].x(), a[0].y()+0.03, """"+0);
//		StdDraw.text(a[1].x(), a[1].y()+0.03, """"+1);
//		StdDraw.text(a[a.length-1].x(), a[a.length-1].y()+0.03, """"+(a.length-1));
//		a[0].drawTo(a[1]);
//		a[a.length-1].drawTo(a[0]);

		for(int i = 2; i<a.length; i++){
//			StdDraw.setPenColor(StdDraw.BLUE);
//			StdDraw.text(a[i].x(), a[i].y()+0.03, """"+i);
//			StdDraw.filledCircle(a[i].x(), a[i].y(), 0.008);
			Point2D top = hull.pop();
			int inttop = inthull.pop();
			while(Point2D.ccw(hull.peek(), top, a[i]) <=0){
				top = hull.pop();
				inttop = inthull.pop();
			}
			hull.push(top);
			inthull.push(inttop);
			hull.push(a[i]);
			inthull.push(index[i]);
			
//			StdDraw.setPenColor(StdDraw.RED);
//			top.drawTo(a[i]);
		}
		int[] out = new int[hull.size()];
		//System.out.println(""hullsize:""+hull.size());
		int i = 0;
		while(!hull.isEmpty()){
				Point2D temp = hull.pop();
				for(int j=0;j<a.length;j++){
					if(temp.compareTo(b[j])==0){
						//System.out.println(""j""+j);
						out[i++]=j;
						break;
					}
				}
		}
		Arrays.sort(out);
		return out;
	}
	public static void main(String[] args) throws Exception{
	    int N = Integer.parseInt(args[0]);
		Point2D[] point = new Point2D[N];
		int[] m = new int[N];
		double min_y = 0;
		double min_x = 0;
		int min = 0;
		int find = 0;
		int fail =0;
		for(int i = 0; i<N; i++){
				double x =StdRandom.uniform();
				double y =StdRandom.uniform();
				point[i] = new Point2D(x, y);
		}
		MyConvexHull ch = new MyConvexHull();
		m = ch.ConvexHullVertex(point);
		for(int i=0; i<m.length; i++){
			//System.out.print(m[i]+"" "");
		}
	}
}

