import java.util.*;
import java.io.*;
import java.lang.*;
//Hw 5-2
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
		 try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
		        // 1. read in the file containing N 2-dimentional points
	        	double d = Double.parseDouble(br.readLine());
	        	int N = Integer.parseInt(br.readLine());
	        	Point2D[] point = new Point2D[N];
	            UF uf = new UF(N);
	            int n = uf.count();
	            System.out.println(n);
	            int i, j, k;
	        	i=0;
	        	String temp;
	        	while((temp = br.readLine())!=null){
		    		String[] coordinates = temp.split("" "");
		    		double x = Double.parseDouble(coordinates[0]);
		    		double y = Double.parseDouble(coordinates[1]);
		            point[i++] = new Point2D(x, y);
		        }
				
	        	
	        	StdDraw.setPenColor(StdDraw.BLUE);
				for(i=0; i<point.length;i++){
					StdDraw.text(point[i].x(), point[i].y()+0.03, """"+i);
					StdDraw.filledCircle(point[i].x(), point[i].y(), 0.008);
				}
		        // 2. create an edge for each pair of points with a distance <= d
				StdDraw.setPenColor(StdDraw.GREEN);
				
				for(i=0; i<point.length;i++){
					Point2D[] t = new Point2D[point.length];
					k=0;
					//Stack<Point2D> uni = new Stack<Point2D>();
					//uni.push(point[i]);
					for(j=0; j<point.length;j++){
						if(point[i].distanceTo(point[j])<=d){
							point[i].drawTo(point[j]);
							uf.union(i, j);
							//t[k++] = new Point2D(point[j].x(), point[j].y());
							//uni.push(point[j]);
						}
					}
					
//					int[] m = new int[k];
//					MyConvexHull ch = new MyConvexHull();
//					m = ch.ConvexHullVertex(t);
//					System.out.println(m.length);
				}
				int un=-1;
				k=0;
				for(i =0;i<uf.count() && uf.find(i)!=un;i++){
					Point2D[] t = new Point2D[point.length];
					for(j =0;j<uf.count();j++){
						if(uf.find(i)==uf.find(j)){
							t[k++] = new Point2D(point[j].x(), point[j].y());							
						}
					}
					un=uf.find(i);

					int[] m = new int[k];
					MyConvexHull ch = new MyConvexHull();
					m = ch.ConvexHullVertex(t);
					System.out.println(m.length);
				}

		        // 3. find connected components (CCs) with a size >= 3
//				Stack<Point2D> uni = new Stack<Point2D>();
//				int[] forcount = new int[N];
//				int[] record = new int[N];
//				int rec = 0;
//				int z=-2;
//				int z_prev=-1;
//				int size;
//				Stack<Point2D> stack = new Stack<Point2D>();
//				Point2D[] temppoint;
//				for(i=0;i<N;i++){
//					z=uf.find(i);
//					if(z_prev!=z){
//						for(j=0;j<N;j++){
//							z=uf.find(i);
//							if(z==uf.find(j)){
//								if(!stack.isEmpty()){
//									if(stack.peek().equals(point[j])){
//										stack.push(point[j]);
//									}
//								}
//							}
//						}
//						size=stack.size();
//						temppoint = new Point2D[size];
//						for(k=0; k<size;k++){
//							temppoint[k]=stack.pop();
//						}
//						if(size>=3){
//							int[] m = new int[size];
//							MyConvexHull ch = new MyConvexHull();
//							m = ch.ConvexHullVertex(temppoint);
//							System.out.println(m.length);
//						}
//					}
//					z_prev=z;
//				}
				
		}
	}
}







/* Assignment: HW5-1 (below)

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

*/
