
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
            temp_max = pq.max().distance;
        }
        else if(pq.size() == k){
            pq.insert(pair);
//            System.out.println(""insert:""+pair.second.toString());
            Pair temp_pair = pq.delMax();
            temp_max = pq.max().distance;
//            System.out.println(""delet:""+temp_pair.second.toString());
        }
        
        int cmp;
        double cmp_dis;
        if(node.level == 0){  //level is even
            cmp = Point2D.X_ORDER.compare(assigned, node.point);
            cmp_dis = abs(assigned.x() - node.point.x());
//            System.out.println(""cmp_dis = ""+cmp_dis);
        }
        else{  //x.level = 1, level is odd
            cmp = Point2D.Y_ORDER.compare(assigned, node.point);
            cmp_dis = abs(assigned.y() - node.point.y());
//            System.out.println(""cmp_dis = ""+cmp_dis);
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
//            if( !(pq.size() == k && cmp_dis > temp_max)){
                check_dis(node.left, assigned, temp_max, pq, k);
//            }
//            if( !(pq.size() == k && cmp_dis > temp_max)){
                check_dis(node.right, assigned, temp_max, pq, k);
//            }
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
            this.distance = a.distanceTo(b);
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
    
    public Point2D[] query_violent(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        MaxPQ<Pair> pq = new MaxPQ<Pair>(k+1); 
        
        traverse(root, point, pq, k);
//        double temp_max = 1.0;
//        check_dis(root, point, temp_max, pq, k);
        
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
//            example HW10
            Point2D assigned_point = new Point2D(0.1354339200,0.7019446863);
            int assigned_num = 3;
            
//            HW10_1
//            Point2D assigned_point = new Point2D(0.531440,0.616918);
//            int assigned_num = 10;
                        
//            HW10_2
//            Point2D assigned_point = new Point2D(0.144932,0.938569);
//            int assigned_num = 50;
            
//            HW10_3
//            Point2D assigned_point = new Point2D(0.1354339200,0.7019446863);
//            int assigned_num = 20;
            
//            HW10_4
//            Point2D q1=new Point2D(0.9865848239,0.6925894824);
//            Point2D q2=new Point2D(0.8795693399,0.8849481938);
//            Point2D[] re1 =FN.query(q1,5);
//            Point2D[] re2 =FN.query(q2,5);
//            System.out.println(""result of query1:"");
//            for(int i=0;i<5;i++){
//              System.out.println(re1[i].x()+"" ""+re1[i].y());
//            }
//                        System.out.println(""result of query1:"");
//            for(int i=0;i<5;i++){
//              System.out.println(re2[i].x()+"" ""+re2[i].y());
//            }
            
            double time_interval = stopwatch.elapsedTime();
            System.out.printf(""interval time = "" + time_interval + ""(s)\n"");
            
            Point2D[] result_violent = FN.query_violent(assigned_point, assigned_num);
            System.out.println(""correct ans:"");
            for(int i=0;i<assigned_num;i++){
                System.out.println(result_violent[i].toString());
            }
            double time_1 = stopwatch.elapsedTime();
            System.out.printf(""violent run time = "" + time_1 + ""(s)\n"");
            System.out.println("" "");
            
            Point2D[] result = FN.query(assigned_point, assigned_num);
            System.out.println(""text ans:"");
            for(int i=0;i<assigned_num;i++){
                System.out.println(result[i].toString());
            }
            double time_2 = stopwatch.elapsedTime();
            System.out.printf(""simplified run time = "" + (time_2-time_1+time_interval) + ""(s)\n"");
        }
        double time_final = stopwatch.elapsedTime();
        System.out.printf(""total run time = "" + time_final + ""(s)\n"");
    }

}

