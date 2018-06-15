import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
.
 */
public class CriticalDis {


    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int num = Integer.parseInt(br.readLine());
            ArrayList<Point2D> points = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                String[] temp = br.readLine().split(""\\s"");
                double x = Double.parseDouble(temp[0]);
                double y = Double.parseDouble(temp[1]);
                points.add(new Point2D(x, y));
            }

            //search the smallest point
            int source = 0;
            int target = 0;

            Point2D o = new Point2D(0, 0);
            double sTemp = points.get(0).x() + points.get(0).y();
            for (int i = 1; i < num; i++) {
                double sum = points.get(i).x() + points.get(i).y();
                if (sum < sTemp) {
                    sTemp = sum;
                    source = i;
                }
            }

            //search the biggest point
            for (int i = 1; i < num; i++) {
                double sum = points.get(i).x() + points.get(i).y();
                if (sum > sTemp) {
                    sTemp = sum;
                    target = i;
                }
            }


            ArrayList<Double> distance = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                for (int j = i + 1; j < num; j++) {
                    distance.add(points.get(i).distanceTo(points.get(j)));
                }
            }

            while (!distance.isEmpty()) {
                double d = distance.get(0);
                for (int i = 1; i < distance.size(); i++) {
                    if (distance.get(i) < d) {
                        d = distance.get(i);
                    }
                }
//                System.out.println(d);

//            double d = 0.3571473074759942;
                Digraph digraph = new Digraph(num);
                for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num; j++) {
                        if (points.get(i).x() < points.get(j).x() &&
                                points.get(i).y() < points.get(j).y() &&
                                points.get(i).distanceTo(points.get(j)) <= d) {

                            digraph.addEdge(i, j);

                        }
                    }
                }

                DirectedDFS dfs = new DirectedDFS(digraph, source);


                if (!dfs.marked(target)) {
                    distance.remove(d);
                } else {
                    System.out.println(String.format(""%1.3f"", d));
                    break;
                }


            }


        }


    }
}

