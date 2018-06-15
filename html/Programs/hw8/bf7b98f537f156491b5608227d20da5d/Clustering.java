//import edu.princeton.cs.algs4.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.lang.Math;

// author: ymy

public class Clustering{

	private class Point{
		double x,y;

		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}

		public double distanceTo(Point that){
			double xpower = Math.pow(this.x - that.x, 2);
			double ypower = Math.pow(this.y - that.y, 2);
			return Math.sqrt(xpower + ypower);
		}
	}

	private class Cluster implements Comparable<Cluster> {
		public int index;
		public int size;
		public Point[] points;
		public Point centroid;

		public Cluster(Point point, int index){
			this.index = index;
			size = 1;
			points = new Point[1];
			points[0] = point;
			centroid = point;
		}

		public Cluster(Cluster a, Cluster b, int index){
			this.index = index;
			size = a.size + b.size;
			points = new Point[size];
			double xsum = 0;
			double ysum = 0;
			boolean debugflag;

			for(int i = 0; i < a.size; i++){
				points[i] = a.points[i];
				xsum += points[i].x;
				ysum += points[i].y;
			}
			for(int i = a.size; i < size; i++){
				points[i] = b.points[i - a.size];
				xsum += points[i].x;
				ysum += points[i].y;
			}
			centroid = new Point(xsum/size, ysum/size);
		}

		public int compareTo(Cluster that){
			if(this.size < that.size) return +1;
			if(this.size > that.size) return -1;
			if(this.centroid.x > that.centroid.x) return +1;
			if(this.centroid.x < that.centroid.x) return -1;
			if(this.centroid.y > that.centroid.y) return +1;
			if(this.centroid.x < that.centroid.x) return -1;
			return 0;
		}
	}

	private class Distance implements Comparable<Distance>{
		public Cluster a;
		public Cluster b;
		public double distance;

		public Distance(Cluster a, Cluster b){
			this.a = a;
			this.b = b;
			distance = a.centroid.distanceTo(b.centroid);
		}

		public int compareTo(Distance that){
			if(distance < that.distance) return -1;
			if(distance > that.distance) return 1;
			return 0;
		}
	}

	public void doClustering(String[] args){
		int num;
		int limit;
		boolean[] clusterVaild;
		Cluster[] clusters;
		MinPQ <Distance> minPQ = new MinPQ<Distance>();

		In in = new In(args[0]);
		num = Integer.parseInt(in.readLine());
		limit = 2*num-3;

		if(num>3){
			clusters = new Cluster[limit];
			clusterVaild = new boolean[limit];
			for(int i = 0; i < num; i++){
				String[] xy = in.readLine().split("" "");
				Point point = new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
				clusters[i] = new Cluster(point, i);
				clusterVaild[i] = true;
			}

			for(int i = 0; i < num - 1; i++){
				for(int j = i + 1; j < num; j++){
					Distance distance = new Distance(clusters[i], clusters[j]);
					minPQ.insert(distance);
				}
			}

			int n = num - 1;
			while(true){
				Distance min = minPQ.delMin();
				if(clusterVaild[min.a.index] && clusterVaild[min.b.index]){
					clusterVaild[++n] = true;
					clusterVaild[min.a.index] = clusterVaild[min.b.index] = false;
					clusters[n] = new Cluster(min.a, min.b, n);
					for(int i = 0; i < n; i++){
						if(clusterVaild[i]){
							minPQ.insert(new Distance(clusters[n], clusters[i]));
						}
					}
				}
				if(n == limit - 1){
					break;
				}
			}
			Cluster[] result = new Cluster[3];
			int vaildIndex = 0;
			for(int i = 0; i < 3; i++){
				while(!clusterVaild[vaildIndex]){
					++vaildIndex;
				}
				result[i] = clusters[vaildIndex++];
			}
			Arrays.sort(result);
			for(int i = 0; i < 3; i++){
				String x = String.format(""%.2f"", result[i].centroid.x);
				String y = String.format(""%.2f"", result[i].centroid.y);
				System.out.println(result[i].size + "" "" + x + "" "" + y);
			}

			double min = Double.POSITIVE_INFINITY;
			for(int i = 0; i < 2; i++){
				for(int j = i+1; j < 3; j++){
					for(int k = 0; k < result[i].size; k++){
						for(int l = 0; l < result[j].size; l++){
							double dis = result[i].points[k].distanceTo(result[j].points[l]);
							min = dis < min ? dis : min;
						}

					}
				}
			}
			String minStr = String.format(""%.2f"", min);
			System.out.println(minStr);
		}

		else{
			clusters = new Cluster[num];
			for(int i = 0; i < num; i++){
				String[] xy = in.readLine().split("" "");
				Point point = new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
				clusters[i] = new Cluster(point, i);
			}
			Arrays.sort(clusters);
			for(int i = 0; i < num; i++){
				String x = String.format(""%.2f"", clusters[i].centroid.x);
				String y = String.format(""%.2f"", clusters[i].centroid.y);
				System.out.println(clusters[i].size + "" "" + x + "" "" + y);
			}
			if (num != 1) {
				double min = Double.POSITIVE_INFINITY;
				for(int i = 0; i < num - 1; i++){
					for(int j = i + 1; j<num; j++){
						double dis = clusters[i].centroid.distanceTo(clusters[j].centroid);
						min = min > dis ? dis : min;
					}
				}
				String minStr = String.format(""%.2f"", min);
				System.out.println(minStr);
			}
			else{
				double min = 0;
				String minStr = String.format(""%.2f"", min);
				System.out.println(minStr);
			}
		}

	}

	public static void main(String[] args) {
		Clustering clustering = new Clustering();
		clustering.doClustering(args);
	}
}
