import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;
import java.util.Arrays;

public class tryhw10 {
    private Node top;
    
    public class Node implements Comparable<Node>{

        private Node left;
        private Node right;
        private Point2D setpoint;

        public Node(Node left, Node right, Point2D value) {
            this.left = left;
            this.right = right;
            this.setpoint = value;
        }


        @Override
        public int compareTo(Node that) {
            if(target.distanceTo(this.setpoint) < target.distanceTo(that.setpoint)){
                return -1;
            }
            else if(target.distanceTo(this.setpoint) < target.distanceTo(that.setpoint)){
                return 1;
            }
            else{
                return 0;
            }
        }


    }

    // DO NOT MODIFY THE CONSTRUCTOR! 
    
    boolean odd;
    Node po;
    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        
        //top = new Node(null, null, points[points.length/2]);
        top = new Node(null, null, points[0]);
        
        for(int m = 1;m < points.length;m++) {
            po = new Node(null, null, points[m]);
            odd=false;
            put(top);
        }
    }


    private void put(Node temp) {
        if (temp == null) {
            return;
        }
        if (!odd) {
            odd = true;
            if (po.setpoint.x() < temp.setpoint.x()) {
                if(temp.left!=null)  put(temp.left);
                else temp.left=po;
                return;
            } else {
                if(temp.right!=null) put(temp.right);
                else temp.right=po;
                return;
            }
        } else{ 
            odd = false;
            if (po.setpoint.y() < temp.setpoint.y()) {
                if(temp.left!=null) put(temp.left);
                else temp.left=po;
                return;
            } else {
                if(temp.right!=null) put(temp.right);
                else temp.right=po;
                return;
            }
        }
    }    
        
    
    
    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    
    MaxPQ<Node> set;
    Point2D target;
    int num;
    
    public Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        set = new MaxPQ<>();
        target = point;
        num=k;
        odd=false;
        
        near(top);
        while(set.size()>num){
            set.delMax();
        }
        for(int i=k-1;i>=0;i--){
            result[i]=set.delMax().setpoint;
        }
        return result;
    }
    
    
    private void near(Node run){
        if (run == null) return;

        if (!odd) {
            odd = true;
            if (target.x() < run.setpoint.x()) {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.left);
                
                if (set.size() >= num) {
                    Node x = set.delMax();
                    if (run.right == null || (target.distanceTo(x.setpoint))*(target.distanceTo(x.setpoint)) < target.x() - run.setpoint.x()*target.x() - run.setpoint.x()) {
                        set.insert(x);
                        return;
                    }
                    set.insert(x);
                }
                odd = true;
                near(run.right);

            } else {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.right);
                if (set.size() >= num) {
                    Node x = set.delMax();
                    if (run.left == null || target.distanceTo(x.setpoint) < target.x() - run.setpoint.x()*target.x() - run.setpoint.x()) {
                        set.insert(x);
                        return;
                    }
                    set.insert(x);
                }
                odd = true;
                near(run.left);
            }
        } else {
            odd = false;
            if (target.y() < run.setpoint.y()) {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.left);
                if (set.size() >= num) {
                    Node x = set.delMax();
                    if (run.right == null || (target.distanceTo(x.setpoint))*(target.distanceTo(x.setpoint)) < (target.y() - run.setpoint.y())*(target.y() - run.setpoint.y())) {
                        set.insert(x);
                        return;
                    }
                    set.insert(x);
                }
                odd = false;
                near(run.right);

            } else {
                set.insert(run);
                while (set.size() > num) {
                    set.delMax();
                }
                near(run.right);
                if (set.size() >= num) {
                    Node x = set.delMax();
                    if (run.left == null || target.distanceTo(x.setpoint)*target.distanceTo(x.setpoint) < target.x() - run.setpoint.x()*target.x() - run.setpoint.x()) {
                        set.insert(x);
                        return;
                    }
                    set.insert(x);
                }
                odd = false;
                near(run.left);
            }

        }

        return;
    }


//    public static void main(String[] args) throws Exception {
//         double time1=System.currentTimeMillis();
//         double time2;
//        //try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//             
//            Point2D[] points = new Point2D[100000];
//            String datacut[];
//            int m = 0;
//            double x, y;
//
//            while (m < 100000) {
////                datacut = br.readLine().split("" "");
////                x = Double.parseDouble(datacut[0]);
////                y = Double.parseDouble(datacut[1]);
//                x=Math.random();
//                y=Math.random();
//                points[m] = new Point2D(x, y);
//                
//                m++;
//            }
//
//            FindNeighbors in = new FindNeighbors();
//
//            in.init(points);
//            //time2=System.currentTimeMillis();
//            int cou=0;
//            while(cou<50){
//                //time1=System.currentTimeMillis();
//             x=Math.random();
//             y=Math.random();
//                
//            Point2D target = new Point2D(x,y);
//            Point2D[] back = in.query(target, 5);
//
//                for(int i=0;i<5;i++){
//                    //StdOut.println(back[i]);
//                }
//                cou++;
//                time2=System.currentTimeMillis();
//            double dis=(time2-time1);
//            time1=time2;
//        StdOut.println(String.format(""%.4f"", dis));
//            }
//
//        time2=System.currentTimeMillis();
//        double dis=(time2-time1);
//        StdOut.println(String.format(""%.4f"", dis));
//   }
}
