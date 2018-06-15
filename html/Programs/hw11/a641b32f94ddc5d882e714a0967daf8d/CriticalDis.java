import java.io.BufferedReader;
import java.io.FileReader;


public class CriticalDis {
    public static class edge implements Comparable<edge> {
        private int p1;
        private int p2;
        private double p1p2;
        
        public edge(int p1, int p2, double p1p2){
            this.p1 = p1;
            this.p2 = p2;
            this.p1p2 = p1p2;
        }
        public int compareTo(edge that){
            if (this.p1p2 > that.p1p2){
                return +1;
            }
            else if (this.p1p2 < that.p1p2){
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int N = Integer.parseInt(br.readLine());
            Point2D[] inputs = new Point2D[N];
            int index_a = 0;
            int index_b = 0;
            double index_hi = 10;
            double index_lo = 0;
                        
            for (int i = 0; i < N; i++){
                String[] point = br.readLine().split("" "");
                inputs[i] = new Point2D(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
                if (inputs[i].x() + inputs[i].y() < index_hi){
                    index_hi = inputs[i].x() + inputs[i].y();
                    index_a = i;
                }
                if (inputs[i].x() + inputs[i].y() > index_lo){
                    index_lo = inputs[i].x() + inputs[i].y();
                    index_b = i;
                }
            }
            
            MinPQ<edge> dist = new MinPQ<edge>();
            
            for (int i = 0; i < N-1; i++){
                for (int j = 0; j < N; j++){
                    double d = Math.pow(Math.pow(inputs[i].x()-inputs[j].x(), 2) + Math.pow(inputs[i].y()-inputs[j].y(), 2), 0.5);
                    edge edge = new edge(i, j, d);
                    dist.insert(edge);
                }
            }
            Digraph digraph = new Digraph(N);
            DirectedDFS ddfs = new DirectedDFS(digraph, index_a);
            edge temp_edge = null;
            while(true){
                temp_edge = dist.delMin();
                if (inputs[temp_edge.p1].x() < inputs[temp_edge.p2].x() && inputs[temp_edge.p1].y() < inputs[temp_edge.p2].y()){
                    digraph.addEdge(temp_edge.p1, temp_edge.p2);
                    ddfs = new DirectedDFS(digraph, index_a);
                }
                if (inputs[temp_edge.p1].x() > inputs[temp_edge.p2].x() && inputs[temp_edge.p1].y() > inputs[temp_edge.p2].y()){
                    digraph.addEdge(temp_edge.p2, temp_edge.p1);
                    ddfs = new DirectedDFS(digraph, index_a);
                }
                if (ddfs.marked(index_b)){
                    System.out.printf(""%1.3f\n"", temp_edge.p1p2);
                    //System.out.printf(""%1.3f\n"", d);
                    break;
                }
            }
        }
    }
}

