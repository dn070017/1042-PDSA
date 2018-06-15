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

		for(int i = 2; i<a.length; i++){
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
			
		}
		int[] out = new int[hull.size()];
		int i = 0;
		while(!hull.isEmpty()){
				Point2D temp = hull.pop();
				for(int j=0;j<a.length;j++){
					if(temp.compareTo(b[j])==0){
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
	            //System.out.println(n);
	            int i, j, k;
	        	i=0;
	        	String temp;
	        	while((temp = br.readLine())!=null){
		    		String[] coordinates = temp.split("" "");
		    		double x = Double.parseDouble(coordinates[0]);
		    		double y = Double.parseDouble(coordinates[1]);
		            point[i++] = new Point2D(x, y);
		        }
				
//	        	StdDraw.setPenColor(StdDraw.BLUE);
				for(i=0; i<point.length;i++){
//					StdDraw.text(point[i].x(), point[i].y()+0.03, """"+i);
//					StdDraw.filledCircle(point[i].x(), point[i].y(), 0.008);
				}
		        
				// 2. create an edge for each pair of points with a distance <= d
//				StdDraw.setPenColor(StdDraw.GREEN);
				for(i=0; i<point.length;i++){
					for(j=0; j<point.length;j++){
						if(point[i].distanceTo(point[j])<=d){
//							point[i].drawTo(point[j]);
							uf.union(i, j);
						}
					}
				}
				
				int[] ufcontent = new int[N];
				for(i=0; i<N; i++){
					ufcontent[i]=uf.find(i);
				}
				Arrays.sort(ufcontent);
				ArrayList<Integer> arrlist = new ArrayList<Integer>();
				
				for(i=0; i<ufcontent.length; i++){
					if(i==0){
						arrlist.add(ufcontent[i++]);
					}else{
						if(ufcontent[i]>arrlist.get(arrlist.size()-1)){
							arrlist.add(ufcontent[i]);
						}
					}
				}

				int[] key = new int[arrlist.size()];
				for(i=0;i<arrlist.size();i++){
					key[i]=arrlist.get(i);
//					System.out.println(key[i]);
				}
				int h=0;
				int total=0;
				ArrayList<Point2D> pointlist = new ArrayList<Point2D>();
				for(i=0;i<key.length;i++){
					for(j=0;j<point.length;j++){
						if(key[i]==uf.find(j)){
							pointlist.add(point[j]);
						}
					}
					if(pointlist.size()>=3){
						Point2D[] intputarray = new Point2D[pointlist.size()];
						for(h = 0; h<pointlist.size(); h++){
							intputarray[h]=pointlist.get(h);
						}
						int[] m = new int[pointlist.size()];
						MyConvexHull ch = new MyConvexHull();
						m = ch.ConvexHullVertex(intputarray);
						total = m.length + total;
//						System.out.println(m.length);
//						System.out.println(""t""+total);
					}
						pointlist.clear();

				}
				System.out.println(total);
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

