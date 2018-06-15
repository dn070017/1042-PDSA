
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;

public class FindNeighbors {
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
    public FindNeighbors() {
    }

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points) {
        top = new Node(null, null, points[0]);
        
        for(int m = 1;m < points.length;m++) {
            Node set = new Node(null, null, points[m]);
            Node temp = top;
            while (true) {
                if (points[m].x() < temp.setpoint.x()) {
                    if (temp.left == null) {
                        temp.left = set;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } 
                else{ 
                    if (temp.right == null) {
                        temp.right = set;
                        break;
                    } 
                    else {
                        temp = temp.right;
                    }
                }
                if (points[m].y() < temp.setpoint.y()) {
                    if (temp.left == null) {
                        temp.left = set;
                        break;
                    } 
                    else {
                        temp = temp.left;
                    }
                } 
                else{ 
                    if (temp.right == null) {
                        temp.right = set;
                        break;
                    } 
                    else {
                        temp = temp.right;
                    }
                }
            }
        }
        return;
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
    boolean odd;
    
    private void near(Node run){
        if (run == null) {
            return;
        }

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
                    if (run.right == null || sqrt(target.distanceTo(x.setpoint)) < sqrt(target.x() - run.setpoint.x())) {
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
                    if (run.left == null || sqrt(target.distanceTo(x.setpoint)) < sqrt(target.x() - run.setpoint.x())) {
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
                    if (run.right == null || sqrt(target.distanceTo(x.setpoint)) < sqrt(target.y() - run.setpoint.y())) {
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
                    if (run.left == null || sqrt(target.distanceTo(x.setpoint)) < sqrt(target.x() - run.setpoint.x())) {
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

//
//    public static void main(String[] args) throws Exception {
//        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
//
//            Point2D[] points = new Point2D[1000];
//            String datacut[];
//            int m = 0;
//            double x, y;
//
//            while (m < 1000) {
//                datacut = br.readLine().split("" "");
//                x = Double.parseDouble(datacut[0]);
//                y = Double.parseDouble(datacut[1]);
//                points[m] = new Point2D(x, y);
//
////                StdDraw.setXscale(0, 1.2);
////                StdDraw.setYscale(0, 1.2);
////                StdDraw.setPenRadius(.015);
////                StdDraw.text(x + 0.02, y + 0.02, """" + m);
////                points[m].draw();
//                m++;
//            }
//
//            FindNeighbors in = new FindNeighbors();
//
//            in.init(points);
//            Point2D target = new Point2D(0.531440, 0.616918);
//            Point2D[] back = in.query(target, 10);
//            int tar =10;
//            
//            
//            for(int i=0;i<tar;i++){
//                StdOut.println(back[i]);
//            }
//            
//            back = in.query(target, 5);
//            StdOut.println(""***"");
//            tar =5;
//            for(int i=0;i<tar;i++){
//                StdOut.println(back[i]);
//            }
//            
//        }
//    }
}
