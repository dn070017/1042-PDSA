import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import FindNeighbors.Event;

public class CriticalDis {
	public static void main(String[] args) throws Exception{
		 try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
		        // 1. read in the file containing N 2-dimentional points
	        	int N = Integer.parseInt(br.readLine());
	        	Point2D[] point = new Point2D[N];
	    		String temp =null;
	        	int i=0, j=0;
	        	while((temp = br.readLine())!=null){
		    		String[] coordinates = temp.split("" "");
		    		double x = Double.parseDouble(coordinates[0]);
		    		double y = Double.parseDouble(coordinates[1]);
		            point[i++] = new Point2D(x, y);
		        }
	        	Arrays.sort(point, Point2D.X_ORDER);
	    		
	        	double max;
	        	double min;
	        	double temp_max;
	        	double temp_min;
	        	int source = 0;
	        	int target = 0;
	        	max = point[0].x()+point[0].y();
	        	min = point[0].x()+point[0].y();
	        	for(i=0;i<point.length;i++){
	        		temp_max = point[i].x()+ point[i].y();
	        		temp_min = point[i].x()+ point[i].y();
	        		if(temp_max>max){
	        			target = i;
	        			max = temp_max;
	        		}
	        		if(temp_min<min){
	        			source = i;
	        			min = temp_min;
	        		}
	        	}
	        	System.out.println(source);
	        	System.out.println(target);
	    		MinPQ<Double> minpq = new MinPQ<Double>();			
	    		int newN = N;
				ArrayList<Point2D> pointarray = new ArrayList<Point2D>();
	    		for(i=0; i<point.length;i++){
					if(point[i].x() <= point[target].x() && point[i].y() <= point[target].y() && point[i].x() >= point[source].x() && point[i].y() >= point[source].y()){
						pointarray.add(point[i]);
					}
				}
	        	
	    		for(i=0; i<pointarray.size();i++){
	    			for(j=0; j!=i && j<pointarray.size();j++){
		    			double dist = pointarray.get(i).distanceTo(pointarray.get(j));
		    			minpq.insert(dist);
		    		}
	    		}
	    		
	    		double wantd = minpq.delMin();
	    		
	        	StdDraw.setPenColor(StdDraw.BLUE);
				for(i=0; i<point.length;i++){
					StdDraw.text(point[i].x(), point[i].y()+0.03, """"+i);
					StdDraw.filledCircle(point[i].x(), point[i].y(), 0.008);
				}
		        
		 }
	}
}
//System.out.printf(""%1.3f\n"", d);
