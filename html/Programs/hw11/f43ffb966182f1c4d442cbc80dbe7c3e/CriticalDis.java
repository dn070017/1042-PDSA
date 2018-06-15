import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/**
.
 */
public class CriticalDis {

    public static class Point {
        private int index;
        private double x;
        private double y;
        private double dinstance;
        Point(double x,double y,int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        public void distance(Point that) {
            this.dinstance = Math.sqrt(Math.pow(this.x-that.x,2)+Math.pow(this.y-that.y,2));
        }

        public boolean compareXY(Point that) {
            return this.x + this.y < that.x + that.y;
        }

        public boolean compareEdge(Point that) {
            return this.x < that.x && this.y < that.y;
        }

    }

    public static class pointComparator implements Comparator<Point> {
        public int compare(Point p1,Point p2) {
            if (p1.dinstance < p2.dinstance) return -1;
            else if (p1.dinstance > p2.dinstance) return 1;
            else return 0;
        }
    }

    public static Point findMinPoint(Digraph digraph,Point[] pArray,int targetIndex) {
        int minPointIndex = 0;
        for (int i = minPointIndex; i < pArray.length; i++){
            DirectedDFS d = new DirectedDFS(digraph,pArray[i].index);
            if (d.marked(targetIndex)) {
                minPointIndex = i; break;
            }
        } return pArray[minPointIndex];
    }

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int size = Integer.parseInt(br.readLine());
            Point[] pArray = new Point[size];
            for (int i = 0; i < size; i++) {
                String[] xy = br.readLine().split("" "");
                Point p = new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]), i);
                pArray[i] = p;
            }
            int sourceIndex = 0, targetIndex = 0;
            for (int i = 0; i < size; i++) {
                if (pArray[i].compareXY(pArray[sourceIndex])) sourceIndex = i;
                if (!pArray[i].compareXY(pArray[targetIndex])) targetIndex = i;
            }


            ArrayList<ArrayList<Point>> list = new ArrayList<>(size);
            Digraph digraph = new Digraph(size);
            for (int i = 0; i < size; i++) {
                ArrayList<Point> temp = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    if (pArray[i].compareEdge(pArray[j])) {
                        Point p = new Point(pArray[j].x, pArray[j].y, j);
                        p.distance(pArray[i]);
                        temp.add(p);
                        digraph.addEdge(i, j);
//                        System.out.println(i + "" to "" + j);
                    }
                } list.add(temp);
            }

            ArrayList<Point[]> newlist = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                newlist.add(list.get(i).toArray(new Point[list.get(i).size()]));
                Arrays.sort(newlist.get(i), new CriticalDis.pointComparator());
            }
//            for (int i = 0; i < newlist.get(0).length; i++)
//                System.out.println(newlist.get(0)[i].x + "" / "" + newlist.get(0)[i].y + "" / ""+ newlist.get(0)[i].dinstance);

            int nextIndex = sourceIndex;
            double ans = newlist.get(sourceIndex)[0].dinstance;
            while (nextIndex != targetIndex) {
                Point minPoint = CriticalDis.findMinPoint(digraph,newlist.get(nextIndex),targetIndex);
                if (minPoint.dinstance > ans) ans = minPoint.dinstance;
                nextIndex = minPoint.index;
            } System.out.printf(""%1.3f\n"", ans);
        }
    }
}

