import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int pointnum = Integer.parseInt(br.readLine());
            Point2D[] points = new Point2D[pointnum];
            int idx = 0;
            Point2D source;
            Point2D target;
            double min = 100.0;
            double max = -1.0;
            int minidx = 0;
            int maxidx = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] temp = in.split("" "");
                double[] coord = new double[2];
                coord[0] = Double.parseDouble(temp[0]);
                coord[1] = Double.parseDouble(temp[1]);
                double count = coord[0] + coord[1];
                points[idx] = new Point2D(coord[0], coord[1]);
                if (count > max) {
                    max = count;
                    target = points[idx];
                    maxidx = idx;
                } 
                if (count < min) {
                    min = count;
                    source = points[idx];
                    minidx = idx;
                }
                idx++;
            }
            MinPQ minpq = new MinPQ();
            for (int i = 0; i < pointnum; i++) { //將所有edge存放到minpq
                for (int j = i + 1; j < pointnum; j++) {
                    Edge temp = new Edge(points[i], points[j], i, j);
                    minpq.insert(temp);
                }
            }
            int minpqsize = minpq.size();
            Digraph digraph = new Digraph(pointnum);
            double d = 0.0;
            for (int i = 0; i < minpqsize; i++) {
                Edge temp = (Edge) (minpq.delMin());
                if (temp.point1.x() > temp.point2.x() && temp.point1.y() > temp.point2.y()) {  //將新增的edge加入至graph
                    digraph.addEdge(temp.idx1, temp.idx2);        
                } else if (temp.point2.x() > temp.point1.x() && temp.point2.y() > temp.point1.y()) {
                    digraph.addEdge(temp.idx2, temp.idx1);
                }
                DirectedDFS dfs = new DirectedDFS(digraph, maxidx);
                if (dfs.marked(minidx) == true) {
                    d=temp.distance;
                    break;
                }
            }
            System.out.printf(""%1.3f\n"", d);
        }
    }

    private static class Edge implements Comparable<Edge> {

        public Point2D point1;
        public Point2D point2;
        public int idx1;
        public int idx2;
        public double distance;

        public Edge(Point2D point1, Point2D point2, int idx1, int idx2) {
            this.point1 = point1;
            this.point2 = point2;
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.distance = Math.sqrt(Math.pow(point1.x() - point2.x(), 2) + Math.pow(point1.y() - point2.y(), 2));
        }

        public int compareTo(Edge that) {
            if (this.distance - that.distance > 0) {
                return 1;
            } else if (this.distance - that.distance < 0) {
                return -1;
            }
            return 0;
        }
    }

}

