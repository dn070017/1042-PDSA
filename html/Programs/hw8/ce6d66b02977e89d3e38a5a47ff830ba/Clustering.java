import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;

public class Clustering {
    private static class Event implements Comparable<Event> {
        private final double distance;         
        private final cluster a, b;
        private final int index_a, index_b;
        
        public Event(double d, cluster a, cluster b, int index_a, int index_b) {
            this.distance = d;
            this.a    = a;
            this.b    = b;
            this.index_a = index_a;
            this.index_b = index_b;
        }

        public int compareTo(Event that) {
            if      (this.distance < that.distance) return -1;
            else if (this.distance > that.distance) return +1;
            else    return  0;
        }
    }
    
    public static class cluster implements Comparable<cluster>{    	
        private double x = 0.0;    // x coordinate
        private double y = 0.0;    // y coordinate
//    	private double mean_x=0.0;
//      private double mean_y=0.0;
        private int size_cluster = 0;
        private int id;
        //private  ArrayList<cluster> clusterarr = new ArrayList<cluster>(); 
        public static final Comparator<cluster> BY_SIZE = new BySize();
        public static final Comparator<cluster> BY_X = new ByX();
        public static final Comparator<cluster> BY_Y = new ByY();

        public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		/**constructor(x,y)*/
        public cluster(double x, double y){
        	if (Double.isInfinite(x) || Double.isInfinite(y))
                throw new IllegalArgumentException(""Coordinates must be finite"");
            if (Double.isNaN(x) || Double.isNaN(y))
                throw new IllegalArgumentException(""Coordinates cannot be NaN"");
            if (x == 0.0) x = 0.0;  // convert -0.0 to +0.0
            if (y == 0.0) y = 0.0;  // convert -0.0 to +0.0
            this.x = x;
            this.y = y;
            this.size_cluster++;
    	}
        
        /**merge method*/
    	public void merge(cluster b){
    		this.x = (this.x*this.size_cluster*1.0+b.x*b.size_cluster*1.0)/(this.size_cluster+b.size_cluster);
    		this.y = (this.y*this.size_cluster*1.0+b.y*b.size_cluster*1.0)/(this.size_cluster+b.size_cluster);
    		this.size_cluster = this.size_cluster + b.size_cluster;
    		b.id = this.id;
    		//this.clusterarr.addAll(b.clusterarr);
    		//return new cluster(this.x,this.y);
    	}
    	
    	/**calculate the distance btw this.mean_cluster to another mean_cluster*/
        public double distanceTo(cluster that) {
            double dx = this.x - that.x;
            double dy = this.y - that.y;
            return Math.sqrt(dx*dx + dy*dy);
        }
        
        public void draw() {
            StdDraw.point(x, y);
        }
        public void drawTo(cluster that) {
            StdDraw.line(this.x, this.y, that.x, that.y);
        }
        
        /**
.
         * @return the x-coordinate
         */
        public double x() {
            return x;
        }

        /**
.
         * @return the y-coordinate
         */
        public double y() {
            return y;
        }

		@Override
		public int compareTo(cluster that) {
			// TODO Auto-generated method stub
			if(this.size_cluster < that.size_cluster) return -1;
			if(this.size_cluster > that.size_cluster) return +1;
			if(this.x() < that.x()) return -1;
			if(this.x() > that.x()) return +1;
			if(this.y() < that.y()) return -1;
			if(this.y() > that.y()) return +1;
			return 0;
		}
		private static class BySize implements Comparator <cluster>{
			@Override
			public int compare(cluster o1, cluster o2) {
				// TODO Auto-generated method stub
				return o1.size_cluster-o2.size_cluster;
			}
		}
		private static class ByX implements Comparator <cluster>{
			@Override
			public int compare(cluster o1, cluster o2) {
				if(o1.x() < o1.x()) return -1;
				if(o2.x() > o2.x()) return +1;
				return 0;
			}
		}
		private static class ByY implements Comparator <cluster>{
			@Override
			public int compare(cluster o1, cluster o2) {
				// TODO Auto-generated method stub
				if(o1.y() < o1.y()) return -1;
				if(o2.y() > o2.y()) return +1;
				return 0;
			}
		}
    }
    
	public static void main(String[] args) throws Exception{
		 try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
		        // 1. read in the file containing N 2-dimentional points
	        	int N = Integer.parseInt(br.readLine());
	        	cluster[] clusterarr = new cluster[N];
	            UF uf = new UF(N);
				ArrayList<cluster> clusterarrlist = new ArrayList<cluster>();
				ArrayList<cluster> clusterarrlist2 = new ArrayList<cluster>();

	            int i, j, k, n;
	        	i=0;
	        	String temp;
	        	while((temp = br.readLine())!=null){
		    		String[] coordinates = temp.split("" "");
		    		double x = Double.parseDouble(coordinates[0]);
		    		double y = Double.parseDouble(coordinates[1]);
		    		clusterarr[i] = new cluster(x, y);
		            clusterarrlist.add(new cluster(x, y));
		            clusterarrlist.get(i).setId(i);
		            i= i+1;
		        }
	        	clusterarrlist2 = (ArrayList<Clustering.cluster>) clusterarrlist.clone();
//	        	StdDraw.setPenColor(StdDraw.BLUE);
//				for(i=0; i<clusterarrlist.size();i++){
//					StdDraw.text(clusterarrlist.get(i).x(), clusterarrlist.get(i).y()+0.03, """"+clusterarrlist.get(i).getId());
//					StdDraw.filledCircle(clusterarrlist.get(i).x(), clusterarrlist.get(i).y(), 0.008);
//				}
//				
//	        	StdDraw.setPenColor(StdDraw.RED);

	        	
//				cluster c = clusterarrlist.get(0);
//				StdDraw.text(c.x(), c.y()+0.03, """"+-1);
//				StdDraw.filledCircle(c.x(), c.y(), 0.008);
//				System.out.println(c.size_cluster);
//	        	
//				c = clusterarrlist.get(1);
//				StdDraw.text(c.x(), c.y()+0.03, """"+-2);
//				StdDraw.filledCircle(c.x(), c.y(), 0.008);
//				System.out.println(c.size_cluster);
				
//				ArrayList<Integer> rec2 = new ArrayList<Integer>();
//				ArrayList<Double> dist = new ArrayList<Double>();
				double distance = 0.0;
	
				double circlesize = 0.003;
				while(clusterarrlist.size()>3){
					MinPQ<Event> pq = new MinPQ<Event>();
					for(i=0;i<clusterarrlist.size();i++){
//						distance = clusterarrlist.get(0).distanceTo(clusterarrlist.get(0));
//						pq.insert(new Event(distance, clusterarrlist.get(0), clusterarrlist.get(0), 0, 0));
						for(j=i+1;j<clusterarrlist.size();j++){
							distance = clusterarrlist.get(i).distanceTo(clusterarrlist.get(j));
//							if(distance < pq.min().distance){
								pq.insert(new Event(distance, clusterarrlist.get(i), clusterarrlist.get(j), i, j));
//							}
						}
					}
					if(!pq.isEmpty()){
						Event minevent = pq.delMin();
						cluster a = minevent.a;
						cluster b = minevent.b;
						cluster c;
						int index1 = clusterarrlist2.indexOf(a);
						int index2 = clusterarrlist2.indexOf(b);
						clusterarrlist2.get(index2).id = index1;
						uf.union(index1, index2);
						clusterarrlist.get(minevent.index_a).merge(clusterarrlist.get(minevent.index_b));
						c = clusterarrlist.get(minevent.index_a);
						clusterarrlist.remove(clusterarrlist.get(minevent.index_b));
						clusterarrlist2.add(c);
						int index = clusterarrlist2.indexOf(c);
						clusterarrlist2.get(index).id = index;
//						StdDraw.text(c.x(), c.y()+0.03, """"+-1);
//						circlesize = circlesize + 0.001;
//						StdDraw.filledCircle(c.x(), c.y(), circlesize);
						//System.out.println(c.size_cluster);
					}
				}
//				for(i=0; i<clusterarrlist2.size();i++){
//					StdDraw.text(clusterarrlist2.get(i).x(), clusterarrlist2.get(i).y()+0.03, """"+uf.find(i));
////					StdDraw.filledCircle(clusterarrlist2.get(i).x(), clusterarrlist2.get(i).y(), clusterarrlist2.get(i).id);
////					System.out.println(""uffind:""+i+"":""+ uf.find(i));
//				}
				
				Collections.sort(clusterarrlist, cluster.BY_SIZE);
				System.out.print(clusterarrlist.get(2).size_cluster);
				System.out.print("" ""+String.format(""%.2f"",clusterarrlist.get(2).x()));
				System.out.println("" ""+String.format(""%.2f"",clusterarrlist.get(2).y()));
				clusterarrlist.remove(2);
				Collections.sort(clusterarrlist, cluster.BY_X);
				System.out.print(clusterarrlist.get(1).size_cluster);
				System.out.print("" ""+String.format(""%.2f"",clusterarrlist.get(1).x()));
				System.out.println("" ""+String.format(""%.2f"",clusterarrlist.get(1).y()));
				clusterarrlist.remove(1);
				System.out.print(clusterarrlist.get(0).size_cluster);
				System.out.print("" ""+String.format(""%.2f"",clusterarrlist.get(0).x()));
				System.out.println("" ""+String.format(""%.2f"",clusterarrlist.get(0).y()));
				
				
				
				
//				System.out.println(""UF:""+uf.count());
//				StdDraw.setPenColor(StdDraw.BLUE);
//				for(i=0; i<clusterarrlist2.size();i++){
//					StdDraw.text(clusterarrlist2.get(i).x(), clusterarrlist2.get(i).y()+0.03, """"+uf.find(i));
//					StdDraw.filledCircle(clusterarrlist2.get(i).x(), clusterarrlist2.get(i).y(), 0.008);
//					System.out.println(""uffind:""+i+"":""+ uf.find(i));
//				}
				
				
				
//				c = clusterarrlist.get(0);
//				StdDraw.text(c.x(), c.y()+0.03, """"+-3);
//				StdDraw.filledCircle(c.x(), c.y(), 0.008);
//				System.out.println(c.size_cluster);
				
//				clusterarrlist.remove(clusterarrlist.get(1));
//				for(i=0; i<clusterarrlist.size();i++){
//					StdDraw.text(clusterarrlist.get(i).x(), clusterarrlist.get(i).y()+0.03, """"+i);
//					StdDraw.filledCircle(clusterarrlist.get(i).x(), clusterarrlist.get(i).y(), 0.008);
//				}
				
				
//				MinPQ<Event> pq = new MinPQ<Event>();			
//				
//				Event event = null;
//				double circlesize = 0.001;
//				int countcircle = 0;
//				//System.out.println(""ptsize: ""+ pointlist.size());
//				for(i=0;i<pointlist.size();i++){
//					cluster c = new cluster(pointlist.get(i));
//					cluster[i] = c;
//					clusterarrlist.add(c);
//				}
//								
//				
//				while(true){
//					for(i=0; i<clusterarrlist.size(); i++){
//						for(j=i+1; j<clusterarrlist.size(); j++){
//							Point2D tempa = new Point2D(clusterarrlist.get(i).mean_x,clusterarrlist.get(i).mean_y);
//							Point2D tempb = new Point2D(clusterarrlist.get(j).mean_x,clusterarrlist.get(j).mean_y);
//							pq.insert(new Event(tempa.distanceTo(tempb),clusterarrlist.get(i),clusterarrlist.get(j),i,j));	
//						}
//					}
//					if(pq.size()==3){
//						break;
//					}else{
//						event = pq.delMin();
//						cluster a = event.a;
//						cluster b = event.b;
//						for(i=0;i<b.arrlist.size();i++){
//							a.arrlist.add(b.arrlist.get(i));
//						}
//						cluster c = new cluster(a.arrlist);
//						Point2D tempPoint = new Point2D(c.mean_x,c.mean_y);
//						clusterarrlist.add(c);
//						clusterarrlist.remove(event.a);
//						clusterarrlist.remove(event.b);
//
////						circlesize = circlesize + 0.001;
////						StdDraw.setPenColor(StdDraw.RED);
////						StdDraw.text(tempPoint.x(), tempPoint.y()+0.03, """"+countcircle++);
////						StdDraw.filledCircle(tempPoint.x(), tempPoint.y(), circlesize);
//						while(!pq.isEmpty()){
//							pq.delMin();
//						}
//					}
//				}		
//				int s;
//				ArrayList<Integer> size = new ArrayList<Integer>();
//				int[] record_size = new int[3];
//				int temps;
//				int max_size;
//				double[] record_mean_x = new double[3];
//				double[] record_mean_y = new double[3];
//				double min_mean_x;
//				double min_mean_y;
//				double tempx;
//				double tempy;
//				for(i = 0 ; i<3 ;i++){
//					record_size[i] = clusterarrlist.get(i).size_cluster;
//					record_mean_x[i] = clusterarrlist.get(i).mean_x;
//					record_mean_y[i] = clusterarrlist.get(i).mean_y;
//				}
//				max_size = record_size[0];
//				
//				for(i=0 ; i < 3 ; i++){
//					for(j=i+1 ; j < 3 ; j++){
//						if(record_size[j]>record_size[i]){
//							temps = record_size[j];
//							record_size[j] = record_size[i];
//							record_size[i] = temps;
//							
//							tempx = record_mean_x[j];
//							record_mean_x[j] = record_mean_x[i];
//							record_mean_x[i] = tempx;
//							
//							tempy = record_mean_y[j];
//							record_mean_y[j] = record_mean_y[i];
//							record_mean_y[i] = tempy;
//						}
//					}
//				}
//				for(i=0 ; i <=0 ; i++){
//					System.out.printf(record_size[i] + "" "");
//					System.out.printf(String.format(""%.2f"", record_mean_x[i]));
//					System.out.printf("" "");
//					System.out.println(String.format(""%.2f"", record_mean_y[i]));
//				}
//				for(i=1 ; i < 3 ; i++){
//					for(j=i+1 ; j < 3 ; j++){
//						if(record_mean_x[j]>record_mean_x[i]){
//							temps = record_size[j];
//							record_size[j] = record_size[i];
//							record_size[i] = temps;
//							
//							tempx = record_mean_x[j];
//							record_mean_x[j] = record_mean_x[i];
//							record_mean_x[i] = tempx;
//							
//							tempy = record_mean_y[j];
//							record_mean_y[j] = record_mean_y[i];
//							record_mean_y[i] = tempy;
//						}
//					}
//				}
//				for(i=1 ; i <3 ; i++){
//					System.out.printf(record_size[i] + "" "");
//					System.out.printf(String.format(""%.2f"", record_mean_x[i]));
//					System.out.printf("" "");
//					System.out.println(String.format(""%.2f"", record_mean_y[i]));
//				}
//				
////					Event eventa = pq.delMin();
////					Event eventb = pq.delMin();
////					Event eventc = pq.delMin();
////				Arrays.sort(clusterarrlist, cluster);
//				
//				MinPQ<Double> pq_small_dist = new MinPQ<Double>();
//				for(i=0; i<clusterarrlist.size(); i++){
//					for(j=i+1; j<clusterarrlist.size(); j++){
//						ArrayList<Point2D> a = clusterarrlist.get(i).arrlist;
//						ArrayList<Point2D> b = clusterarrlist.get(j).arrlist;
//						for(k=0; k<a.size();k++){
//							for(n=0; n<b.size();n++){
//								pq_small_dist.insert(a.get(k).distanceTo(b.get(n)));
//							}
//						}
//					}
//				}
//				double small_dist = pq_small_dist.delMin();
//				System.out.println(String.format(""%.2f"", small_dist));
		}
	}
}

