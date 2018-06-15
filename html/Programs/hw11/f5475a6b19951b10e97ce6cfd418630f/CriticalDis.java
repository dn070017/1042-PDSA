import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
.
 */
public class CriticalDis {

    CriticalDis() {
    }

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int size = Integer.parseInt(br.readLine());
            Point2D[] point = new Point2D[size];
            for (int i = 0; i < size; i++) {
                String[] xy = br.readLine().split("" "");
                Point2D p = new Point2D(Double.parseDouble(xy[0]),Double.parseDouble(xy[1]));
                point[i] = p;
            }
            int sourceIndex = 0, targetIndex = 0;
            for (int i = 0; i < size; i++) {
                if (point[i].x()+point[i].y() < point[sourceIndex].x()+point[sourceIndex].y()) {
                    sourceIndex = i;
                }
                if (point[i].x()+point[i].y() > point[targetIndex].x()+point[targetIndex].y()) {
                    targetIndex = i;
                }
            }
            List<List<Integer>> list = new ArrayList<>(size);
            Digraph digraph = new Digraph(size);
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    if (point[i].x() < point[j].x() && point[i].y() < point[j].y())
                        temp.add(j); digraph.addEdge(i,j);
                } list.add(temp);
            }
            List<Double> disList = new ArrayList<>();
            int nextIndex = sourceIndex;
            double thisDis;
            while (nextIndex != targetIndex) {
                List<Integer> thisList = list.get(nextIndex);
                int minIndex = thisList.get(0);
                double minDis = point[nextIndex].distanceTo(point[minIndex]);
                for (int i = 0; i < thisList.size(); i++) {
                    DirectedDFS d = new DirectedDFS(digraph,thisList.get(i));
                    thisDis = point[nextIndex].distanceTo(point[thisList.get(i)]);
                    if (d.marked(targetIndex)) {
                        if (thisDis < minDis) {
                            minIndex = thisList.get(i);
                            minDis = thisDis;
                        }
                    }
                } nextIndex = minIndex; disList.add(minDis);
            }

            double maxDisOfList = disList.get(0);
            for (int i = 0; i < disList.size(); i++)
                if (disList.get(i) > maxDisOfList) maxDisOfList = disList.get(i);
            System.out.printf(""%1.3f\n"", maxDisOfList);
        }
    }
}

