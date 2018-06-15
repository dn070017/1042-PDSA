
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
.
 */
public class CriticalDis {
    //    private Stack<Integer> path=new Stack<>();
    private List<Integer> path = new ArrayList<>();
    private SET<Integer> onPath = new SET<>();
//    private static double glmin=1.5;
    private static ArrayList<Point2D> vertexes;
//    private static DepthFirstDirectedPaths dfs;
    private static Digraph digraph;

    static class ptCompare implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            if (a.x() > b.x()) return 1;
            else return (a.x() < b.x() || (a.x() == b.x() && a.y() > b.y())) ? -1 : 0;
        }
    }

    static class edgeCompare implements Comparator<edge> {
        public int compare(edge a, edge b) {
            if(a.getDis()>b.getDis())return 1;
            else return -1;
        }
    }

    private static class edge{
        private int a;
        private int b;
        private Point2D pa;
        private Point2D pb;
        private double dis;

        public edge(int i,int j,Point2D pi,Point2D pj){
            this.a=i;
            this.b=j;
            this.pa=pi;
            this.pb=pj;
            this.dis=pa.distanceTo(pb);
        }

        public int getA(){
            return this.a;
        }

        public int getB(){
            return this.b;
        }

        public  double getDis(){
            return this.dis;
        }
    }

    public static void main(String[] args) throws Exception {
        CriticalDis criticalDis = new CriticalDis();
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int vertexNum = Integer.parseInt(br.readLine());
            String[] tmp;
            Point2D source = new Point2D(1, 1), target = new Point2D(0, 0);
            vertexes = new ArrayList<>(vertexNum);
            digraph = new Digraph(vertexNum);
//            Stack<int[]> allEdge=new Stack<>();
            MinPQ<edge> edges=new MinPQ<>(new edgeCompare());

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
//                        digraph.addEdge(i, j);
//                        int[] tmpEdge={i,j};
//                        allEdge.push(tmpEdge);
                        edge edge=new edge(i,j,vertexes.get(i),vertexes.get(j));
                        edges.insert(edge);
//                        edges1.add(edge);
                    }
                }
            }
//            edges1.sort(new edgeCompare());
            //            directedDFS =new DirectedDFS(digraph,vertexes.indexOf(source));
//            dfs=new DepthFirstDirectedPaths(digraph,vertexes.indexOf(source));

            int s=vertexes.indexOf(source);
            int t=vertexes.indexOf(target);
            for (int i = 0; i < edges.size(); i++) {
                edge E=edges.delMin();
                digraph.addEdge(E.getA(),E.getB());
                DirectedDFS directedDFS=new DirectedDFS(digraph,s);
                if(directedDFS.marked(t)){
                    System.out.printf(""%1.3f\n"", E.getDis());
                    break;
                }
            }
//            for (int i = 0; i < allEdge.size(); i++) {
//                int[] E=allEdge.pop();
//                vertexes.get(E[0]).distanceTo(vertexes.get(E[1]));
//            }

//            criticalDis.findAllPath(vertexes.indexOf(source), vertexes.indexOf(target));
//            System.out.printf(""%1.3f\n"", glmin);
        }
    }


//    private void findAllPath(int s, int t) {
//        path.add(s);
//        onPath.add(s);
//        double max=0;
//        if (s == t) {
////            System.out.println(path);
//            for (int i = 0; i < path.size()-1; i++) {
//                double tmpDis=vertexes.get(path.get(i)).distanceTo(vertexes.get(path.get(i+1)));
////                System.out.println(path.get(i)+"" ""+path.get(i+1)+"" ""+tmpDis);
//                if(tmpDis>max)max=tmpDis;
//            }
////            System.out.println(max);
//            if(max<glmin)glmin=max;
//        } else {
//            for (int i : digraph.adj(s)) {
//                if (directedDFS.marked(i)) findAllPath(i, t);
//            }
//        }
//        path.remove(path.size()-1);
//        onPath.delete(s);
//    }
}

