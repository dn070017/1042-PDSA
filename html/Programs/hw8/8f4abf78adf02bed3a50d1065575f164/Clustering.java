import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {
    private static class Event implements Comparable<Event> {
    	
        private final double distance;         
        private final cluster a, b;       // particles involved in event, possibly null
        private final int index_a, index_b;
        //private final int countA, countB;  
        
        // create a new event to occur at time t involving a and b
        public Event(double d, cluster a, cluster b, int index_a, int index_b) {
            //Point2D tempa = a.mean_cluster;
            //Point2D tempb = b.mean_cluster;
            //this.distance = tempa.distanceTo(tempb);
            this.distance = d;
            this.a    = a;
            this.b    = b;
            this.index_a = index_a;
            this.index_b = index_b;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            if      (this.distance < that.distance) return -1;
            else if (this.distance > that.distance) return +1;
            else                            return  0;
        }
    }
    
    
    public static class cluster implements Comparable<cluster> {
    	
        public static final Comparator<cluster> Size_cluster = new Size_cluster();
        public static final Comparator<cluster> Mean_x = new Mean_x();
        public static final Comparator<cluster> Mean_y = new Mean_y();

    	
        private double mean_x=0.0;
        private double mean_y=0.0;
        private cluster mean_cluster;
        private int size_cluster;
        private ArrayList<Point2D> arrlist;
    	
        public cluster(Point2D a) {
        	ArrayList<Point2D> templist = new ArrayList<Point2D>();
        	templist.add(a);
        	this.arrlist = templist;
    		this.size_cluster = templist.size();
    		for(int i = 0 ; i<templist.size();i++){
            	Point2D temp = templist.get(i);
            	this.mean_x = temp.x() + this.mean_x;
            	this.mean_y = temp.y() + this.mean_y;
            }
    		this.mean_x = this.mean_x/(templist.size()*1.0);
    		this.mean_y = this.mean_y/(templist.size()*1.0);
        }
        
    	public cluster(ArrayList<Point2D> list) {
    		this.arrlist = list;
    		this.size_cluster = list.size();
    		for(int i = 0 ; i<list.size();i++){
            	Point2D temp = list.get(i);
            	this.mean_x = temp.x() + this.mean_x;
            	this.mean_y = temp.y() + this.mean_y;
            }
    		this.mean_x = this.mean_x/(list.size()*1.0);
    		this.mean_y = this.mean_y/(list.size()*1.0);
        }
//    	public cluster(cluster a){
//    		this.size_cluster = this.size_cluster +a.size_cluster;
//    		this.mean_x = (this.mean_x*this.size_cluster*1.0+a.mean_x*a.size_cluster*1.0)/(this.size_cluster+a.size_cluster);
//    		this.mean_y = (this.mean_y*this.size_cluster*1.0+a.mean_y*a.size_cluster*1.0)/(this.size_cluster+a.size_cluster);
//    		ArrayList<Point2D> temp = null;
//    		temp.add(new Point2D(mean_x, mean_y));
//    		this.mean_cluster = new cluster(temp);
//    	}
    	public void add(cluster b){
    		this.size_cluster = this.size_cluster +b.size_cluster;
    		this.mean_x = (this.mean_x*this.size_cluster*1.0+b.mean_x*b.size_cluster*1.0)/(this.size_cluster+b.size_cluster);
    		this.mean_y = (this.mean_y*this.size_cluster*1.0+b.mean_y*b.size_cluster*1.0)/(this.size_cluster+b.size_cluster);
    		for(int i=0; i<b.arrlist.size();i++){
    			this.arrlist.add(b.arrlist.get(i));
    		}
    	}
    	
        public int compareTo(cluster that) {
            if (this.size_cluster < that.size_cluster) return -1;
            else if (this.size_cluster < that.size_cluster) return +1;
            else{
            	if (this.mean_x < that.mean_x) return -1;
            	else if (this.mean_x > that.mean_x) return +1;
            	else{
            		if (this.mean_y < that.mean_y) return -1;
                	else if (this.mean_y > that.mean_y) return +1;
            	}
            }
            return 0;
        }
        
        // compare points according to size
        private static class Size_cluster implements Comparator<cluster> {
            public int compare(cluster p, cluster q) {
                if (p.size_cluster < q.size_cluster) return -1;
                if (p.size_cluster > q.size_cluster) return +1;
                return 0;
            }
        }

        // compare points according to their y-coordinate
        private static class Mean_x implements Comparator<cluster> {
            public int compare(cluster p, cluster q) {
                if (p.mean_x < q.mean_x) return -1;
                if (p.mean_x > q.mean_x) return +1;
                return 0;
            }
        }
        
        private static class Mean_y implements Comparator<cluster> {
            public int compare(cluster p, cluster q) {
                if (p.mean_y < q.mean_y) return -1;
                if (p.mean_y > q.mean_y) return +1;
                return 0;
            }
        }
    }
	public static void main(String[] args) throws Exception{
		 try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
		        // 1. read in the file containing N 2-dimentional points
	        	int N = Integer.parseInt(br.readLine());
	        	Point2D[] point = new Point2D[N];
	        	cluster[] cluster = new cluster[N];
	            UF uf = new UF(N);
				ArrayList<Point2D> pointlist = new ArrayList<Point2D>();
				ArrayList<cluster> clusterarrlist = new ArrayList<cluster>();

	            //System.out.println(n);
	            int i, j, k, n;
	        	i=0;
	        	String temp;
	        	while((temp = br.readLine())!=null){
		    		String[] coordinates = temp.split("" "");
		    		double x = Double.parseDouble(coordinates[0]);
		    		double y = Double.parseDouble(coordinates[1]);
		            point[i++] = new Point2D(x, y);
		            pointlist.add(new Point2D(x, y));
		        }
	        	
//	        	StdDraw.setPenColor(StdDraw.BLUE);
//				for(i=0; i<point.length;i++){
//					StdDraw.text(point[i].x(), point[i].y()+0.03, """"+i);
//					StdDraw.filledCircle(point[i].x(), point[i].y(), 0.008);
//				}
				
				MinPQ<Event> pq = new MinPQ<Event>();
				//pq.insert(new Event(0, null, null));
				
				
				Event event = null;
				double circlesize = 0.001;
				int countcircle = 0;
				//System.out.println(""ptsize: ""+ pointlist.size());
				for(i=0;i<pointlist.size();i++){
					cluster c = new cluster(pointlist.get(i));
					cluster[i] = c;
					clusterarrlist.add(c);
				}
								
				
				
				while(true){
					for(i=0; i<clusterarrlist.size(); i++){
						for(j=i+1; j<clusterarrlist.size(); j++){
							Point2D tempa = new Point2D(clusterarrlist.get(i).mean_x,clusterarrlist.get(i).mean_y);
							Point2D tempb = new Point2D(clusterarrlist.get(j).mean_x,clusterarrlist.get(j).mean_y);
							pq.insert(new Event(tempa.distanceTo(tempb),clusterarrlist.get(i),clusterarrlist.get(j),i,j));	
						}
					}
					if(pq.size()==3){
						break;
					}else{
						event = pq.delMin();
						cluster a = event.a;
						cluster b = event.b;
						for(i=0;i<b.arrlist.size();i++){
							a.arrlist.add(b.arrlist.get(i));
						}
						cluster c = new cluster(a.arrlist);
						Point2D tempPoint = new Point2D(c.mean_x,c.mean_y);
						clusterarrlist.add(c);
						clusterarrlist.remove(event.a);
						clusterarrlist.remove(event.b);

//						circlesize = circlesize + 0.001;
//						StdDraw.setPenColor(StdDraw.RED);
//						StdDraw.text(tempPoint.x(), tempPoint.y()+0.03, """"+countcircle++);
//						StdDraw.filledCircle(tempPoint.x(), tempPoint.y(), circlesize);
						while(!pq.isEmpty()){
							pq.delMin();
						}
					}
				}		
				int s;
				ArrayList<Integer> size = new ArrayList<Integer>();
				int[] record_size = new int[3];
				int temps;
				int max_size;
				double[] record_mean_x = new double[3];
				double[] record_mean_y = new double[3];
				double min_mean_x;
				double min_mean_y;
				double tempx;
				double tempy;
				for(i = 0 ; i<3 ;i++){
					record_size[i] = clusterarrlist.get(i).size_cluster;
					record_mean_x[i] = clusterarrlist.get(i).mean_x;
					record_mean_y[i] = clusterarrlist.get(i).mean_y;
				}
				max_size = record_size[0];
				
				for(i=0 ; i < 3 ; i++){
					for(j=i+1 ; j < 3 ; j++){
						if(record_size[j]>record_size[i]){
							temps = record_size[j];
							record_size[j] = record_size[i];
							record_size[i] = temps;
							
							tempx = record_mean_x[j];
							record_mean_x[j] = record_mean_x[i];
							record_mean_x[i] = tempx;
							
							tempy = record_mean_y[j];
							record_mean_y[j] = record_mean_y[i];
							record_mean_y[i] = tempy;
						}
					}
				}
				for(i=0 ; i <=0 ; i++){
					System.out.printf(record_size[i] + "" "");
					System.out.printf(String.format(""%.2f"", record_mean_x[i]));
					System.out.printf("" "");
					System.out.println(String.format(""%.2f"", record_mean_y[i]));
				}
				for(i=1 ; i < 3 ; i++){
					for(j=i+1 ; j < 3 ; j++){
						if(record_mean_x[j]>record_mean_x[i]){
							temps = record_size[j];
							record_size[j] = record_size[i];
							record_size[i] = temps;
							
							tempx = record_mean_x[j];
							record_mean_x[j] = record_mean_x[i];
							record_mean_x[i] = tempx;
							
							tempy = record_mean_y[j];
							record_mean_y[j] = record_mean_y[i];
							record_mean_y[i] = tempy;
						}
					}
				}
				for(i=1 ; i <3 ; i++){
					System.out.printf(record_size[i] + "" "");
					System.out.printf(String.format(""%.2f"", record_mean_x[i]));
					System.out.printf("" "");
					System.out.println(String.format(""%.2f"", record_mean_y[i]));
				}
				
//					Event eventa = pq.delMin();
//					Event eventb = pq.delMin();
//					Event eventc = pq.delMin();
//				Arrays.sort(clusterarrlist, cluster);
				
				MinPQ<Double> pq_small_dist = new MinPQ<Double>();
				for(i=0; i<clusterarrlist.size(); i++){
					for(j=i+1; j<clusterarrlist.size(); j++){
						ArrayList<Point2D> a = clusterarrlist.get(i).arrlist;
						ArrayList<Point2D> b = clusterarrlist.get(j).arrlist;
						for(k=0; k<a.size();k++){
							for(n=0; n<b.size();n++){
								pq_small_dist.insert(a.get(k).distanceTo(b.get(n)));
							}
						}
					}
				}
				double small_dist = pq_small_dist.delMin();
				System.out.println(String.format(""%.2f"", small_dist));
		}
	}
}

