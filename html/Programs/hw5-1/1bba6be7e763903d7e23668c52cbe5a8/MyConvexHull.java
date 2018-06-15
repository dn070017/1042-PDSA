//import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;

// TODO the same line
public class MyConvexHull{
	private static MyPoint2D[] myPoints;

	public static int[] ConvexHullVertex(Point2D[] a) {
		int[] index;
		int size = a.length, before = 1;
		myPoints = new MyPoint2D[size];
		MyPoint2D low;
		Stack<Integer> convexHullStack = new Stack<Integer>();

		for(int i = 0; i < size; i++){
			myPoints[i] = new MyPoint2D(a[i], i);
			StdDraw.filledCircle(myPoints[i].point.x(), myPoints[i].point.y(), 0.01);
			// System.out.println("""" + i + "":"" + myPoints[i].point.x() + "","" + myPoints[i].point.y());
		}

		low = myPoints[0];
		for(int i = 1; i < size; i++){
			if(low.point.Y_ORDER.compare(myPoints[i].point, low.point) == -1){
				low = myPoints[i];
			}
		}

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(low.point.x(), low.point.y(), 0.01);

		myPoints[low.index] = myPoints[0];
		myPoints[0] = low;

		Arrays.sort(myPoints, myPoints[0].polarOrder());
		for(int i = 0; i < size; i++){
			// myPoints[0].point.drawTo(myPoints[i].point);
			StdDraw.text(myPoints[i].point.x(), myPoints[i].point.y() + 0.03, """" + i);
		}

		convexHullStack.push(0);
		for(int i = 1; i < size - 1; i++){
			compareCV(convexHullStack, i, i+1);
			// Comparator comparator = myPoints[before].polarOrder();
			// System.out.println(""Base on "" + before + "", compare"" + i + "","" + convexHullStack.peek());
			// if(myPoints[i].point.ccw(myPoints[i].point, myPoints[before].point, myPoints[convexHullStack.peek()].point) == -1){
			// // if(comparator.compare(myPoints[convexHullStack.peek()], myPoints[i]) == 1){
			// 	convexHullStack.push(before);
			// 	System.out.println(""Compare true, Push:"" + before);
			// 	before = i;
			// 	System.out.print(""Stack: "");
			// 	for(int j : convexHullStack){
			// 		System.out.print(j + "" "");
			// 	}
			// 	System.out.println();
			// 	System.out.println(""Before: "" + before);
			// }
			// else{
			// 	System.out.println(""Compare false, before = "" + i);
			// 	before = i;
			// }
		}
		convexHullStack.push(size-1);
		int indexi = 0;
		index = new int[convexHullStack.size()];
		System.out.println(""cvStack size:"" + convexHullStack.size());
		for(int i : convexHullStack){
			index[indexi] = myPoints[i].index;
			indexi++;
		}
		  // index[indexi] = size - 1;

		for(int i : index){
			System.out.print(i + "" "");
		}
		return index;
	}

	public static void compareCV (Stack<Integer> cvStack, int now, int after){
		if(myPoints[now].point.ccw(myPoints[after].point, myPoints[now].point, myPoints[cvStack.peek()].point) == -1){
			cvStack.push(now);
			System.out.print(""Stack: "");
			for(int j : cvStack){
					System.out.print(j + "" "");
				}
			System.out.println();
		}
		else{
			compareCV(cvStack, cvStack.pop(), after);
		}
	}

	public void drawConvexHull(int[] index, Point2D[] a){
		for(int i = 0; i < index.length - 1; i++){
			a[index[i]].drawTo(a[index[i+1]]);
		}
	}

	static class MyPoint2D{
		int index;
		Point2D point;

		public MyPoint2D(Point2D point, int index){
			this.index = index;
			this.point = point;
		}

		public Comparator<MyPoint2D> polarOrder(){
			return new PolarOrder();
		}

		private class PolarOrder implements Comparator<MyPoint2D> {
			public int compare(MyPoint2D p1, MyPoint2D p2){
				double dx1 = p1.point.x() - point.x();
            	double dy1 = p1.point.y() - point.y();
            	double dx2 = p2.point.x() - point.x();
            	double dy2 = p2.point.y() - point.y();

            	if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            	else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            	else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                	if      (dx1 >= 0 && dx2 < 0) return -1;
                	else if (dx2 >= 0 && dx1 < 0) return +1;
                	else                          return  0;
            	}
            	else return -point.ccw(point, p1.point, p2.point);
			}
		}
	}

	public static void main(String[] args) {
		Point2D[] a = new Point2D[10];
		double l = 0, r = 1;
		for(int i = 0; i<10; i++){
			a[i] = new Point2D(StdRandom.uniform(l,r),StdRandom.uniform(l,r));
		}
		MyConvexHull ch = new MyConvexHull();
		ch.drawConvexHull(ch.ConvexHullVertex(a), a);
	}
}
