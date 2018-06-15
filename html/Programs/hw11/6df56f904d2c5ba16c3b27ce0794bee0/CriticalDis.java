// author: ymy

//import edu.princeton.cs.algs4.*;
import java.util.HashSet;
import java.util.Arrays;

class CritialDis {

	private Point2D[] points;
	private MinPQ<Distance> disPQ;						// all the points dis between two points
	private int source = 0, destination = 0;
	private int pointNum;
	private boolean[] mark;
	private double d = 0;
	
	public CritialDis(Point2D[] points_){
		points = points_;
		Arrays.sort(points);
		pointNum = points.length;
		mark = new boolean[pointNum];
		disPQ = new MinPQ<Distance>();

		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < pointNum; i++){
			double sum = points[i].x() + points[i].y();
			if(sum < min){
				min = sum;
				source = i;
			}
			if(sum > max){
				max = sum;
				destination = i;
			}
		}

		for(int i = source+1; i < pointNum; i++){
			if(points[i].x() > points[source].x()){
				disPQ.insert(new Distance(points[source], source, points[i], i));
			}
			mark[source] = true;
		}
		
		// for(int i = 0; i < pointNum; i++){
		// 	for(int j = i + 1; j < pointNum; j++){
		// 		pointsDis[disFlag++] = points[i].distanceTo(points[j]);
		// 	}
		// }
		// Arrays.sort(pointsDis);
	}
	
	public double findDis(){
		while(!disPQ.isEmpty()){
			Distance dis = disPQ.delMin();
			if(dis.distance > d){
				d = dis.distance;
			}
			int index = dis.largerIndex;

			if(index == destination){
				break;
			}

			if(mark[index] == false){
				mark[index] = true;
				for(int i = dis.largerIndex + 1; i < pointNum; i++){
					if(points[i].x() > points[index].x()){
						disPQ.insert(new Distance(points[index], index, points[i], i));
					}
				}
			}
		}
		return d;
	}
	private class Distance implements Comparable<Distance>{
		public Point2D smaller;
		public Point2D larger;
		public double distance;
		public int smallerIndex;
		public int largerIndex;

		public Distance(Point2D smaller_, int smallerIndex_, Point2D larger_, int largerIndex_){
			smaller = smaller_;
			larger = larger_;
			smallerIndex = smallerIndex_;
			largerIndex = largerIndex_;
			distance = smaller.distanceTo(larger);
		}

		public int compareTo(Distance that){
			if(this.distance < that.distance) return -1;
            if(this.distance > that.distance) return +1;
            else return 0;
		}
	}
	
	public static void main(String [] args){
		int lineNum;
		Point2D[] points;
		
		In in = new In(args[0]);
		lineNum = Integer.parseInt(in.readLine());
		points = new Point2D[lineNum];
		
		for(int i = 0; i<lineNum; i++){
			String[] xy;
			xy = in.readLine().split("" "");
			points[i] = new Point2D(Double.parseDouble(xy[0]),Double.parseDouble(xy[1]));
		}
		
		CritialDis critialDis = new CritialDis(points);
		System.out.printf(""%1.3f\n"", critialDis.findDis());
	}
}
