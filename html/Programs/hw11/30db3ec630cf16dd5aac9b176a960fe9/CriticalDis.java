
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
.
 */
public class CriticalDis {
    private static ArrayList<Point2D> vertexes;
    private static Digraph digraph;

    static class ptCompare implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            if (a.x() > b.x()) return 1;
            else return (a.x() < b.x() || (a.x() == b.x() && a.y() > b.y())) ? -1 : 0;
        }
    }

    static class edgeCompare implements Comparator<edge> {
        public int compare(edge a, edge b) {
            if (a.getDis() > b.getDis()) return 1;
            else if (a.getDis() == b.getDis()) return 0;
            else return -1;
        }
    }

    private static class edge {
        private int a;
        private int b;
        private Point2D pa;
        private Point2D pb;
        private double dis;

        public edge(int i, int j, Point2D pi, Point2D pj) {
            this.a = i;
            this.b = j;
            this.pa = pi;
            this.pb = pj;
            this.dis = pa.distanceTo(pb);
        }

        public int getA() {
            return this.a;
        }

        public int getB() {
            return this.b;
        }

        public double getDis() {
            return this.dis;
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int vertexNum = Integer.parseInt(br.readLine());
            String[] tmp;
            Point2D source = new Point2D(1, 1), target = new Point2D(0, 0);
            vertexes = new ArrayList<>(vertexNum);
            digraph = new Digraph(vertexNum);
//            MinPQ<edge> edges = new MinPQ<>(new edgeCompare());
            ArrayList<edge>edges =new ArrayList<>();

            for (int i = 0; i < vertexNum; i++) {
                String t = br.readLine();
                tmp = t.split("" "");
                vertexes.add(new Point2D(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1])));
                if (source.x() + source.y() >= vertexes.get(i).x() + vertexes.get(i).y()) source = vertexes.get(i);
                if (target.x() + target.y() <= vertexes.get(i).x() + vertexes.get(i).y()) target = vertexes.get(i);
            }
            Collections.sort(vertexes, new ptCompare());

            for (int i = 0; i < vertexNum; i++) {
                for (int j = i + 1; j < vertexNum; j++) {
                    if (vertexes.get(j).y() < vertexes.get(i).y()) continue;
                    else {
                        edge edge = new edge(i, j, vertexes.get(i), vertexes.get(j));
//                        edges.insert(edge);
                        edges.add(edge);
                    }
                }
            }
            edges.sort(new edgeCompare());

            int s = vertexes.indexOf(source);
            int t = vertexes.indexOf(target);
            DirectedDFS dfs = new DirectedDFS(digraph, s);
//            edge EE = edges.delMin();
//            digraph.addEdge(EE.getA(), EE.getB());
            double d=0;

//            while (!dfs.marked(t)){
//                edge E = edges.delMin();
//                digraph.addEdge(E.getA(), E.getB());
//                d=E.getDis();
//            }

            for (int i = 0; i < edges.size(); i++) {
                digraph.addEdge(edges.get(i).getA(), edges.get(i).getB());
                dfs=new DirectedDFS(digraph,s);
                if(dfs.marked(t)){
                    System.out.printf(""%1.3f\n"", edges.get(i).getDis());
                    break;
                }
            }
//            System.out.printf(""%1.3f\n"", d);

            //            for (int i = 0; i < edges.size(); i++) {
//                edge E = edges.delMin();
//                DirectedDFS dfs = new DirectedDFS(digraph, s);
//                if (dfs.marked(t)) {
//                    System.out.printf(""%1.3f\n"", E.getDis());
//                    break;
//                }
//            }

        }
    }
}
