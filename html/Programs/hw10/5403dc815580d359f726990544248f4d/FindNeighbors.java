
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.abs;

public class FindNeighbors {
    
    private Node root;
    
    private class Node {
        private Point2D point;
        private int level;
        private Node left;
        private Node right;
        
        public Node(int level, Point2D point){
            this.level = level;
            this.point = point;
        }
    }

    public void put(Point2D point) {
        int level = 0;
        root = put(root, level, point);
    }

    private Node put(Node x, int level, Point2D point) {
        if (x == null) {return new Node(level, point);}
  
        int cmp;
        //level is even
        if(level == 0) {cmp = Point2D.X_ORDER.compare(point,x.point);}
        //level = 1, level is odd
        else {cmp = Point2D.Y_ORDER.compare(point,x.point);}
        
        if (cmp < 0) {x.left = put(x.left, (level+1)%2, point);}
        else if (cmp > 0) {x.right = put(x.right, (level+1)%2, point);} 
        else if (cmp == 0) {x.point = point;}
        
        return x;
    }
    
    // violent
    public void traverse (Node node, Point2D assigned, MaxPQ<Pair> pq, int k){
        if(node == null) { return; }
        Pair pair = new Pair(assigned, node.point);
        if(pq.size()<k){
            pq.insert(pair);
        }
        else if(pq.size() == k){
            pq.insert(pair);
//            System.out.println(""insert:""+pair.second.toString());
            Pair temp_pair = pq.delMax();
//            System.out.println(""delet:""+temp_pair.second.toString());
        }
        traverse(node.left, assigned, pq, k);
        traverse(node.right, assigned, pq, k);
    }
    
    
    public void check_dis(Node node, Point2D assigned, double temp_max, MaxPQ<Pair> pq, int k){
        
        if(node == null){ return; }
        
        Pair pair = new Pair(assigned, node.point);
        if(pq.size()<k){
            pq.insert(pair);
        }
        else if(pq.size() == k){
            pq.insert(pair);
//            System.out.println(""insert:""+pair.second.toString());
            Pair temp_pair = pq.delMax();
            temp_max = temp_pair.distance;
//            System.out.println(""delet:""+temp_pair.second.toString());
        }
        
        int cmp;
        double cmp_dis;
        if(node.level == 0){  //level is even
            cmp = Point2D.X_ORDER.compare(assigned, node.point);
            cmp_dis = abs(assigned.x() - node.point.x());
        }
        else{  //x.level = 1, level is odd
            cmp = Point2D.Y_ORDER.compare(assigned, node.point);
            cmp_dis = abs(assigned.y() - node.point.y());
        }

        if (cmp < 0){   //assigned point is on the left/botton
            check_dis(node.left, assigned, temp_max, pq, k);
            if( !(pq.size() == k && cmp_dis > temp_max)){
                check_dis(node.right, assigned, temp_max, pq, k);
            }
        }

        else if (cmp > 0){   //assigned point is on the right/top
            check_dis(node.right, assigned, temp_max, pq, k);
            if( !(pq.size() == k && cmp_dis > temp_max)){
                check_dis(node.left, assigned, temp_max, pq, k);
            }
        }
        else if (cmp == 0) {
            if( !(pq.size() == k && cmp_dis > temp_max)){
                check_dis(node.left, assigned, temp_max, pq, k);
            }
            if( !(pq.size() == k && cmp_dis > temp_max)){
                check_dis(node.right, assigned, temp_max, pq, k);
            }
        }
        
        return ;
    }
    

    public class Pair implements Comparable<Pair>{
        private double distance;
        private Point2D first;
        private Point2D second;
        
        public Pair (Point2D a , Point2D b){
            this.first = a;
            this.second = b;
            this.distance = a.distanceSquaredTo(b);
        }
        
        public int compareTo(Pair that){
            if(this.distance > that.distance) return +1;
            if(this.distance < that.distance) return -1;
            else return 0;
        }
    }
    
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        for(int i=0;i<points.length;i++){
            put(points[i]);
        }
        return ;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        MaxPQ<Pair> pq = new MaxPQ<Pair>(k+1); 
        
//        traverse(root, point, pq, k);
        double temp_max = 1.0;
        check_dis(root, point, temp_max, pq, k);
        
        for(int i=0;i<k;i++){
            result[k-i-1] = pq.delMax().second;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

        Stopwatch stopwatch = new Stopwatch();
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StringBuilder everything = new StringBuilder();
            String line;
            everything.append(br.readLine());//first point
            while ((line = br.readLine()) != null) {
                everything.append("","");    //split each point by ','
                everything.append(line);
            }
            String[] data = everything.toString().split("","");
            int num = data.length;
            Point2D[] points = new Point2D[num];
            
            
            for(int i=0;i<num;i++){
                String[] given = data[i].split("" "");
                Point2D point = new Point2D(Double.parseDouble(given[0]),Double.parseDouble(given[1]));
                points[i] = point;
            }
            FindNeighbors FN = new FindNeighbors();
            FN.init(points);
            
            Point2D assigned_point = new Point2D(0.1354339200,0.7019446863);
            int assigned_num = 3;
            
            Point2D[] result = FN.query(assigned_point, assigned_num);
            for(int i=0;i<assigned_num;i++){
                System.out.println(result[i].toString());
            }
        }
        double time = stopwatch.elapsedTime();
        System.out.printf(""run time = "" + time + ""(s)\n"");
    }

}

