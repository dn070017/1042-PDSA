
import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {
    
    public static class Pair implements Comparable<Pair> {
        
        private double distance;
        private int index_a;
        private int index_b;
        private Point2D first;
        private Point2D second;
        
        public Pair (int index_a, Point2D a, int index_b, Point2D b){
            this.first = a;
            this.second = b;
            this.index_a = index_a;
            this.index_b = index_b;
            this.distance = a.distanceTo(b);
        }
        
        public int compareTo(Pair that){
            if(this.distance > that.distance) return +1;
            if(this.distance < that.distance) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        
//        Stopwatch stopwatch = new Stopwatch();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StringBuilder everything = new StringBuilder();
            String line;
            everything.append(br.readLine());//first num
            while ((line = br.readLine()) != null) {
                everything.append("","");
                everything.append(line);
            }
            String[] data = everything.toString().split("","");
            int num = Integer.parseInt(data[0]);
            Point2D[] points = new Point2D[num];
            
//            StdDraw.setCanvasSize(600, 600);
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.BLACK);
            
            for(int i=0;i<num;i++){
                String[] given = data[i+1].split("" "");
                Point2D point = new Point2D(Double.parseDouble(given[0]),Double.parseDouble(given[1]));
                points[i] = point;
//                System.out.println(point.toString());
//                point.draw();
//                StdDraw.text(point.x(), point.y()-0.02, Integer.toString(i));
            }
            
            MinPQ<Pair> pq = new MinPQ<Pair>();
            Digraph G = new Digraph(num);
            Point2D source = new Point2D(1.0,1.0);
            Point2D target = new Point2D(0.0,0.0);
            int source_index = 0;
            int target_index = 0;
            
//            StdDraw.setPenRadius(0.001);
//            StdDraw.setPenColor(StdDraw.GRAY);
            
            for(int i=0;i<num; i++){
                for(int j=i+1;j<num;j++){
                    if( points[i].x()<points[j].x() && points[i].y()<points[j].y() ){
                        Pair pair = new Pair(i,points[i],j,points[j]);
                        pq.insert(pair);
                        
//                        points[i].drawTo(points[j]);
                    }
                    else if( points[i].x()>points[j].x() && points[i].y()> points[j].y()){
                        Pair pair = new Pair(j,points[j],i,points[i]);
                        pq.insert(pair);
                        
//                        points[j].drawTo(points[i]);
                    }
                }
                
                double pos = points[i].x() + points[i].y();
                if( pos < (source.x()+source.y()) ){
                    source = points[i];
                    source_index = i;
                }
                if( pos > (target.x()+target.y())){
                    target = points[i];
                    target_index = i;
                }
            }
//            StdDraw.setPenRadius(0.03);
//            StdDraw.setPenColor(StdDraw.BLUE);
//            source.draw();
//            System.out.println(""Source_index = "" + source_index + "" , "" + ""Source = "" + source);
//            StdDraw.setPenColor(StdDraw.RED);
//            target.draw();
//            System.out.println(""Target_index = "" + target_index + "" , "" + ""Target = "" + target);
            
//            StdDraw.setPenRadius(0.005);
//            StdDraw.setPenColor(StdDraw.BOOK_RED);
            
            double d = 0.0;

            boolean target_marked = false;
            
            do {
                Pair temp_pair = pq.delMin();
                d = temp_pair.distance;
                G.addEdge(temp_pair.index_a, temp_pair.index_b);  // pointing from points[inde_a] to points[inde_b]
                DirectedDFS dfs = new DirectedDFS( G, source_index );
                target_marked = dfs.marked(target_index);
                
//                temp_pair.first.drawTo(temp_pair.second);
//                System.out.println(""break point"");
            }
            while(!target_marked);
            System.out.printf(""%1.3f\n"" , d );
            
        }
//        double time = stopwatch.elapsedTime();
//        System.out.printf(""run time = "" + time + ""(s)\n"");
    }
    
}

