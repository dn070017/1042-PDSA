public class FindNeighbors {
    private Node oldnode= null;
    private int size = 0;
	//Construct a 2d-tree when given a Point2D array
	public void init(Point2D[] points){
		for(int i = 0 ; i< points.length;i++){
			put(points[i]);
		}
	    return;
	}
	
    private static class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private boolean vertical;

        public Node(Point2D p) {
            this.p = p;
        }
    }
    
	public void put(Point2D p){
        oldnode = put(oldnode, p);
	}
	
	private Node put(Node oldnode ,Point2D p){
        Node newnode = new Node(p);
		if(oldnode==null){
			newnode.vertical=true;
			return newnode;
		} 
        int cmp = 0;
        if(oldnode.vertical==true){
        	newnode.vertical = false;
        	if(oldnode.p.x()>newnode.p.x()){
        		cmp = -1;
        	}else if(oldnode.p.x()<newnode.p.x()){
        		cmp = +1;
        	}else{
        		cmp = 0;
        	}
        }else if(oldnode.vertical==false){
        	newnode.vertical = true;
        	if(oldnode.p.y()>newnode.p.y()){
        		cmp = -1;
        	}else if(oldnode.p.y()<newnode.p.y()){
        		cmp = +1;
        	}else{
        		cmp = 0;
        	}
        }
        if(cmp<0){
        	oldnode.left = put(oldnode.left, p);
        }else if(cmp>0){
        	oldnode.right = put(oldnode.right, p);
        }else{
        	oldnode.p = p;
        }
		return oldnode;
	}
	
    private static class Event implements Comparable<Event>{
        private Point2D p;
        private double distance;

        public Event(Point2D p, double distance) {
            this.p = p;
            this.distance = distance;
        }
        public int compareTo(Event that) {
            if      (this.distance < that.distance) return -1;
            else if (this.distance > that.distance) return +1;
            else                            return  0;
        }
    }	
	//Report the k-nearest neighbors of a new coordinates
	public Point2D[] query(Point2D point, int k){
		MaxPQ<Event> pq = new MaxPQ<Event>();			
		Node pointer = oldnode;
		double distance;
		while(oldnode!=null){
			distance = oldnode.p.distanceTo(point);
			pq.insert(new Event(oldnode.p, distance));
			if(pq.size()>k){
				pq.delMax();
			}
			if(oldnode.vertical==true){
				if(point.x()<oldnode.p.x()){
					oldnode = oldnode.left;
				}else if(point.x()>oldnode.p.x()){
					oldnode = oldnode.right;
				}
			}else{
				if(point.y()<oldnode.p.y()){
					oldnode = oldnode.left;
				}else if(point.y()>oldnode.p.y()){
					oldnode = oldnode.right;
				}
			}
		}
		Point2D[] inverseresult = new Point2D[k];
		Point2D[] result = new Point2D[k];
		int j = 0;
		while(!pq.isEmpty()){
			inverseresult[j++] = pq.max().p;
			pq.delMax();
		}
		j = 0;
		for(int i = inverseresult.length-1 ; i>=0 ; i--){
			result[j++] = inverseresult[i];
		}
	    return result;
	    // the points should be sorted accordingly to their distances to the query, from small to large
	}
}

