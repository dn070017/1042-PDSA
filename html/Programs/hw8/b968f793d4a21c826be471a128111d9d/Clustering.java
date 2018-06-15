import java.util.*;
import java.awt.Event;
import java.io.*;

public class TestClustering {
    private static class Event implements Comparable<Event> {
        private final double distance;         
        private final cluster a, b;
        private final int index_a, index_b;
        public static final Comparator<Event> BY_INTDEX_A = new ByIndexA();
        public static final Comparator<Event> BY_INTDEX_B = new ByIndexB();
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
        private static class ByIndexA implements Comparator <Event>{
			@Override
			public int compare(Event o1, Event o2) {
				// TODO Auto-generated method stub
				return o1.index_a-o2.index_a;
			}
		}
        private static class ByIndexB implements Comparator <Event>{
			@Override
			public int compare(Event o1, Event o2) {
				// TODO Auto-generated method stub
				return o1.index_b-o2.index_b;
			}
		}
    }
    
    public static class cluster implements Comparable<cluster>{    	
        private double x = 0.0;    // x coordinate
        private double y = 0.0;    // y coordinate
//    	private double mean_x=0.0;
//      private double mean_y=0.0;
        private int size_cluster = 0;
        private int cluster_id;
        public List<cluster> clusterlist = new ArrayList<cluster>();
        //private  ArrayList<cluster> clusterarr = new ArrayList<cluster>(); 
        public static final Comparator<cluster> BY_SIZE = new BySize();
        public static final Comparator<cluster> BY_X = new ByX();
        public static final Comparator<cluster> BY_Y = new ByY();
        private ArrayList<Integer> intarray = new ArrayList<Integer>();
        
        public int getId() {
			return this.cluster_id;
		}

		public void setId(int id) {
			this.cluster_id = id;
//			intarray.add(id);
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
        public void setcluster(List<cluster> c){
        	this.clusterlist = c;
    	}
        
        /**merge method*/
    	public void merge(cluster b){
    		this.x = (this.x*this.size_cluster*1.0+b.x*b.size_cluster*1.0)/((this.size_cluster+b.size_cluster)*1.0);
    		this.y = (this.y*this.size_cluster*1.0+b.y*b.size_cluster*1.0)/((this.size_cluster+b.size_cluster)*1.0);
    		this.size_cluster = this.size_cluster + b.size_cluster;
    		intarray.add(b.cluster_id);
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
    public static class distancerecord implements Comparable<distancerecord>{
    	private double distance = 0.0;
    	private int dist_place_id_i = 0;
    	private int dist_place_id_j = 0;

    	public distancerecord(double dist, int id_i, int id_j){
    		this.distance = dist;
    		this.dist_place_id_i = id_i;
    		this.dist_place_id_j = id_j;
    	}
    	@Override
		public int compareTo(distancerecord that) {
			// TODO Auto-generated method stub
    		if(this.distance < that.distance) return -1;
    		if(this.distance > that.distance) return +1;
			return 0;
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
				ArrayList<Integer> mergerecord  = new ArrayList<Integer>();
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
//	        	StdDraw.setPenColor(StdDraw.BLUE);
//				for(i=0; i<clusterarrlist.size();i++){
//					StdDraw.text(clusterarrlist.get(i).x(), clusterarrlist.get(i).y()+0.03, """"+clusterarrlist.get(i).getId());
//					StdDraw.filledCircle(clusterarrlist.get(i).x(), clusterarrlist.get(i).y(), 0.008);
//				}

				ArrayList<distancerecord> distancerecordlist = new ArrayList<distancerecord>();
				ArrayList<Event> eventlist = new ArrayList<Event>();
				MinPQ<Event> pq = new MinPQ<Event>();
				//MinPQ<Event> pqtest = new MinPQ<Event>();

				double distance=0.0;
				double circlesize = 0.003;
				Event e = null;
				Event minevent = null;
				int flag=0;
				for(i=0; i<clusterarrlist.size()-1; i++){
					for(j=i+1; j<clusterarrlist.size(); j++){
						distance = clusterarrlist.get(i).distanceTo(clusterarrlist.get(j));
						distancerecordlist.add(new distancerecord(distance,i,j));
						//System.out.println(""distance :""+distance + "" i_id:""+ clusterarrlist.get(i).getId() + "" j_id:"" + clusterarrlist.get(j).getId());
						e = new Event(distance, clusterarrlist.get(i), clusterarrlist.get(j), clusterarrlist.get(i).getId(), clusterarrlist.get(j).getId());
						pq.insert(e);
						eventlist.add(e);
					}
				}
				//System.out.println(""eeeeeeeeeeeeeeeee"");
				cluster newcluster;
				double m;
				int count =0; 
				minevent = null;
				cluster clustera = null;
				cluster clusterb = null;
				cluster clustertemp = null;
				int place_a = 0;
				int place_b = 0;
				int cluster_a_id = 0;
				int cluster_b_id = 0;
				boolean flaga = false;
				boolean flagb = false;
				boolean flagc = false;
				//StdDraw.setPenColor(StdDraw.RED);
//				while(!pq.isEmpty()){
//					minevent = pq.delMin();//delete min
//					clustera = minevent.a;
//					clusterb = minevent.b;
//					cluster_a_id = clustera.cluster_id;
//					cluster_b_id = clusterb.cluster_id;
//					distance = minevent.distance;
//					System.out.println(distance +"" cluster_a_id ""+cluster_a_id +"" cluster_b_id ""+ cluster_b_id);
//				}
				
				while(clusterarrlist.size() > 3 ){
					Collections.sort(eventlist);
					if(count<=4){
					for(i = 0; i<eventlist.size();i++){
						e = eventlist.get(i);
						distance = e.distance;
						clustera = e.a;
						clusterb = e.b;
						cluster_a_id = clustera.getId();
						cluster_b_id = clusterb.getId();
						//System.out.println(""distance: ""+ distance + "" cluster_a_id: ""+cluster_a_id+ "" cluster_b_id: ""+cluster_b_id);
					}
					}
//					System.out.println("""");
//					System.out.println(""COUNT""+count);
//					System.out.print(""id: before merge"");
//					for(i=0; i< clusterarrlist.size();i++){
//						System.out.print(clusterarrlist.get(i).getId() + "" "");
//					}
//					System.out.println("""");
//					pqtest = pq;
//					System.out.print(""pq: "");
//					while(!pqtest.isEmpty()){
//						e = pqtest.delMin();
//						System.out.println(""pqtest:""+ e.distance +"" ""+ e.index_a +"" ""+ e.index_b);
//					}
					//System.out.println("""");
					if(count==0){
						//minevent = pq.delMin();//delete min
						minevent = eventlist.get(0);
						eventlist.remove(0);
						clustera = minevent.a;
						clusterb = minevent.b;
						cluster_a_id = clustera.getId();
						cluster_b_id = clusterb.getId();
//						System.out.println(""cluster_a_id:""+ cluster_a_id);
//						System.out.println(""cluster_b_id:""+ cluster_b_id);
					}
					else{
						while(true){
							//minevent = pq.delMin();//delete min
							minevent = eventlist.get(0);
							eventlist.remove(0);
							clustera = minevent.a;
							clusterb = minevent.b;
							distance = minevent.distance;
							cluster_a_id = clustera.getId();
							cluster_b_id = clusterb.getId();
							flaga=true;
							flagb=true;
							flagc=true;
							//System.out.println(""cluster_a_id: ""+ cluster_a_id+ "" cluster_b_id: ""+ cluster_b_id);
							
							for(i=0;i<mergerecord.size();i++){
								if(cluster_a_id == mergerecord.get(i)){
									flaga=false;
									break;
								}
								if(cluster_b_id == mergerecord.get(i)){
									flagb=false;
									break;
								}
							}
							
							if(flaga==true & flagb==true){
								break;
							}
							//System.out.println("""");
						}
					}
					count++;
					for(i = 0 ; i<clusterarrlist.size(); i++){
						clustertemp = clusterarrlist.get(i);
						if(clustertemp.getId() == cluster_a_id){
							place_a = i;
						}
						if(clustertemp.getId() == cluster_b_id){
							place_b = i;
						}
					}
					//clusterarrlist.get(place_a).drawTo(clusterarrlist.get(place_b));
					mergerecord.add(clusterarrlist.get(place_a).getId()); 
					mergerecord.add(clusterarrlist.get(place_b).getId());
					clusterarrlist.get(place_a).merge(clusterarrlist.get(place_b));
					newcluster = new cluster(clusterarrlist.get(place_a).x, clusterarrlist.get(place_a).y);
					newcluster.cluster_id = N++;
					newcluster.size_cluster = clusterarrlist.get(place_a).size_cluster;
					for(i= 0; i<clusterarrlist.size();i++){
						if(cluster_b_id==clusterarrlist.get(i).getId()){
							clusterarrlist.remove(i);
						}
					}
					for(i= 0; i<clusterarrlist.size();i++){
						if(cluster_a_id==clusterarrlist.get(i).getId()){
							clusterarrlist.remove(i);
						}
					}
					clusterarrlist.add(newcluster);
					//System.out.println(""before pq.size:"" +pq.size());
					for(j = 0 ; j< clusterarrlist.size(); j++){
						if(clusterarrlist.get(j).getId()!=newcluster.getId()){
							distance = newcluster.distanceTo(clusterarrlist.get(j));
							//System.out.println(""newcluster.cluster_id, clusterarrlist.get(j).cluster_id: ""+ newcluster.cluster_id +"" ""+ clusterarrlist.get(j).cluster_id);
							e = new Event(distance, newcluster, clusterarrlist.get(j), newcluster.getId(), clusterarrlist.get(j).getId());
							
							eventlist.add(e);
							
							//pq.insert(e);
						}
					}
					//System.out.println(""after pq.size:"" +pq.size());
//					StdDraw.text(newcluster.x(), newcluster.y()+0.03, """"+newcluster.getId());
//					circlesize = circlesize + 0.001;
//					StdDraw.filledCircle(newcluster.x(), newcluster.y(), circlesize);
				}
//				}
				
				
				
//				for(i=0; i<clusterarrlist2.size();i++){
//					StdDraw.text(clusterarrlist2.get(i).x(), clusterarrlist2.get(i).y()+0.03, """"+uf.find(i));
////					StdDraw.filledCircle(clusterarrlist2.get(i).x(), clusterarrlist2.get(i).y(), clusterarrlist2.get(i).id);
////					System.out.println(""uffind:""+i+"":""+ uf.find(i));
//				}
					
					
//				System.out.println(""*************"");
//				for(i=0;i<clusterarrlist.get(2).intarray.size();i++){
//					int a = clusterarrlist.get(2).intarray.get(i);
//					System.out.print(a + "" "");
//				}
//				System.out.println("" "");	
//				System.out.println(""*************"");
//				System.out.println(""*************"");
//				for(i=0;i<clusterarrlist.get(1).intarray.size();i++){
//					int a = clusterarrlist.get(1).intarray.get(i);
//					System.out.print(a + "" "");
//				}
//				System.out.println("" "");	
//				System.out.println(""*************"");
//				System.out.println(""*************"");
//				for(i=0;i<clusterarrlist.get(0).intarray.size();i++){
//					int a = clusterarrlist.get(0).intarray.get(i);
//					System.out.print(a + "" "");
//				}
//				System.out.println("" "");	
				//System.out.println(""*************"");
				
				
				
				Collections.sort(clusterarrlist, cluster.BY_SIZE);
//				for(i=0;i<clusterarrlist.get(2).size_cluster;i++){
//					System.out.println(clusterarrlist.get(2).clusterlist.get(i).x() + "" "" +clusterarrlist.get(2).clusterlist.get(i).y());
//				}
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
				
				
				Collections.sort(clusterarrlist, cluster.BY_SIZE);
				double small;
//				for(i=0;i<clusterarrlist.get(2).size_cluster)
				
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

