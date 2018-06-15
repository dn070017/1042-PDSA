//import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;


// TODO the same line
public class MyConvexHull{
	private static MyPoint2D[] myPoints;

	public static int[] ConvexHullVertex(Point2D[] a) {
		int[] index;
		int size = a.length;
		myPoints = new MyPoint2D[size];
		MyPoint2D low;
		Stack<Integer> convexHullStack = new Stack<Integer>();

		for(int i = 0; i < size; i++){
			myPoints[i] = new MyPoint2D(a[i], i);
		}

		low = myPoints[0];
		for(int i = 1; i < size; i++){
			if(low.point.Y_ORDER.compare(myPoints[i].point, low.point) == -1){
				low = myPoints[i];
			}
		}

		myPoints[low.index] = myPoints[0];
		myPoints[0] = low;

		Arrays.sort(myPoints, myPoints[0].polarOrder());

		convexHullStack.push(0);
		for(int i = 1; i < size - 1; i++){
			compareCV(convexHullStack, i, i+1);
		}
		convexHullStack.push(size-1);
		int indexi = 0;
		index = new int[convexHullStack.size()];
		for(int i : convexHullStack){
			index[indexi] = myPoints[i].index;
			indexi++;
		}
		return index;
	}

	public static void compareCV (Stack<Integer> cvStack, int now, int after){
		if(myPoints[now].point.ccw
		(myPoints[after].point, myPoints[now].point, myPoints[cvStack.peek()].point) == -1){
			cvStack.push(now);
		}
		else{
			compareCV(cvStack, cvStack.pop(), after);
		}
	}

	public static void drawConvexHull(int[] index, Point2D[] a){
		for(int i = 0; i < index.length - 1; i++){
			a[index[i]].drawTo(a[index[i+1]]);
		}
	}

	public static int ccConvexHull(Point2D[] points, double distance){
		int chNum = 0;
		int pointsSize = points.length;
		Bag<Integer>[] pointsBag = new Bag[pointsSize];
		UF uf = new UF(pointsSize);

		for (int i = 0; i<pointsSize; i++){
			pointsBag[i] = new Bag<Integer> ();
		}

		// for (Point2D point: points){
		// 	StdDraw.filledCircle(point.x(), point.y(), 0.01);
		// }

		for(int i = 0; i < pointsSize - 1; i++){
			for(int j = i + 1; j < pointsSize; j++){
				if(points[i].distanceTo(points[j]) < distance){
					uf.union(i,j);
					// points[i].drawTo(points[j]);
				}
			}
		}

		for(int i = 0; i<pointsSize; i++){
			pointsBag[uf.find(i)].add(i);
		}

		for(int i = 0; i<pointsSize; i++){
			if(pointsBag[i].size() > 2){
				Point2D[] ccPoints = new Point2D[pointsBag[i].size()];
				int k = 0;
				for(int j: pointsBag[i]){
					ccPoints[k] = points[j];
					++k;
				}
				chNum += ConvexHullVertex(ccPoints).length;
			}
		}

		return chNum;
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
		// Point2D[] a = new Point2D[10];
		// double l = 0, r = 1;
		// for(int i = 0; i<10; i++){
		// 	a[i] = new Point2D(StdRandom.uniform(l,r),StdRandom.uniform(l,r));
		// }
		// int[] array = MyConvexHull.ConvexHullVertex(a);
		// MyConvexHull.drawConvexHull(array, a);
		// for(int i = 0; i< array.length; i++){
		// 	System.out.print(array[i] + "" "");
		// }
		double distance;
		int line;
		Point2D[] points;
		In in = new In(args[0]);
		distance = Double.parseDouble(in.readLine());
		line = Integer.parseInt(in.readLine());
		points = new Point2D[line];
		for(int i = 0; i<line; i++){
			String[] reg = in.readLine().split("" "");
			points[i] = new Point2D(Double.parseDouble(reg[0]), Double.parseDouble(reg[1]));
		}
		System.out.println(ccConvexHull(points, distance));
	}
}
