import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author Po-Lin
 */
public class Clustering {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int pointCount = Integer.parseInt(br.readLine());
            int[] checkarray = new int[pointCount + (pointCount - 3 - 1)];
            int checknum = 0;
            Point[] storepointcoord = new Point[pointCount];
            int idx = 0;
            int clusteridx = pointCount + 1;
            Event event;

            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] tempcoord = in.split("" "");
                storepointcoord[idx] = new Point(Double.parseDouble(tempcoord[0]), Double.parseDouble(tempcoord[1]));
                storepointcoord[idx].root = idx;
                storepointcoord[idx].idx = idx;
                idx++;
            }
            Point[] pointcoord = storepointcoord;
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(pointCount);

            MinPQ minpq = new MinPQ();
            MinPQ newminpq = new MinPQ();
            for (int i = 0; i < pointcoord.length; i++) {
                for (int j = i + 1; j < pointcoord.length; j++) {
                    event = new Event(pointcoord[i], pointcoord[j]);
                    minpq.insert(event);
                    newminpq.insert(event);
                }
            }
            Event temp = (Event) minpq.delMin();
            newminpq.delMin();
            Point[] newpointcoord = new Point[pointcoord.length - 1];
            int tempidx = 0;
            for (int i = 0; i < pointcoord.length; i++) {
                if ((pointcoord[i].equals(temp.point1)) || (pointcoord[i].equals(temp.point2))) {
                } else {
                    newpointcoord[tempidx++] = pointcoord[i];
                }
            }

            checkarray[temp.point1.idx] = 1;
            checkarray[temp.point2.idx] = 1;

            uf.union(temp.point1.root, temp.point2.root);  //把同clustering的作uf
            double newx = 0.0;
            double newy = 0.0;
            int pointnum = 0;
            for (int i = 0; i < pointCount; i++) {
                if (uf.connected(i, temp.point1.root) == true) {
                    newx = newx + storepointcoord[i].x;
                    newy = newy + storepointcoord[i].y;
                    pointnum++;
                }
            }
            newpointcoord[tempidx] = new Point(newx / pointnum, newy / pointnum);
            newpointcoord[tempidx].root = temp.point1.root;
            newpointcoord[tempidx].idx = clusteridx;
            clusteridx++;

            pointcoord = newpointcoord;

            while (pointcoord.length > 3) {
                for (int i = 0; i < pointcoord.length - 1; i++) {  //計算新的座標與其他點的距離
                    event = new Event(pointcoord[i], pointcoord[pointcoord.length - 1]);
                    newminpq.insert(event);
                }

//                for (int i = 0; i < pointcoord.length; i++) {
//                    for (int j = i + 1; j < pointcoord.length; j++) {
//                        event = new Event(pointcoord[i], pointcoord[j]);
//                        newminpq.insert(event);
//                    }
//                }
                int loopend = 0;
                int cc = 0;
                while (loopend == 0) {
                    temp = (Event) newminpq.delMin();

                    if (checkarray[temp.point1.idx]==1 || checkarray[temp.point2.idx]==1) {
//                            System.out.println(""checknum""+"" ""+checknum);
//                            System.out.println(""checkarray""+"" ""+checkarray[i].x+"" ""+checkarray[i].y);
//                            System.out.println(""temp.point1""+"" ""+temp.point1.x+"" ""+temp.point1.y);
//                            System.out.println(""temp.point2""+"" ""+temp.point2.x+"" ""+temp.point2.y);                           
                        cc = 1;

                    }

                    if (cc == 0) {
                        loopend = 1;
                    } else {
                        cc = 0;
                    }
                }
                //temp = (Event) newminpq.delMin();
                newpointcoord = new Point[pointcoord.length - 1];
                tempidx = 0;
                for (int i = 0; i < pointcoord.length; i++) {
                    if ((pointcoord[i].equals(temp.point1)) || (pointcoord[i].equals(temp.point2))) {
                    } else {
                        newpointcoord[tempidx++] = pointcoord[i];
                    }
                }

                checkarray[temp.point1.idx] = 1;
                checkarray[temp.point2.idx] = 1;

                uf.union(temp.point1.root, temp.point2.root);  //把同clustering的作uf
                newx = 0.0;
                newy = 0.0;
                pointnum = 0;
                for (int i = 0; i < pointCount; i++) {
                    if (uf.connected(i, temp.point1.root) == true) {
                        newx = newx + storepointcoord[i].x;
                        newy = newy + storepointcoord[i].y;
                        pointnum++;
                    }
                }
                newpointcoord[tempidx] = new Point(newx / pointnum, newy / pointnum);
                newpointcoord[tempidx].root = temp.point1.root;
                newpointcoord[tempidx].idx = clusteridx;
                clusteridx++;
                pointcoord = newpointcoord;
            }
            int[] sizecount = new int[3];
            int[] sortsizecount;

            for (int i = 0; i < pointCount; i++) {
                if (uf.connected(i, pointcoord[0].root) == true) {
                    sizecount[0]++;
                } else if (uf.connected(i, pointcoord[1].root) == true) {
                    sizecount[1]++;
                } else if (uf.connected(i, pointcoord[2].root) == true) {
                    sizecount[2]++;
                }
            }
            Point[] cluster0 = new Point[sizecount[0]];
            Point[] cluster1 = new Point[sizecount[1]];
            Point[] cluster2 = new Point[sizecount[2]];
            int numcount_0 = 0, numcount_1 = 0, numcount_2 = 0;

            for (int i = 0; i < pointCount; i++) {
                if (uf.connected(i, pointcoord[0].root) == true) {
                    cluster0[numcount_0] = storepointcoord[i];
                    numcount_0++;
                } else if (uf.connected(i, pointcoord[1].root) == true) {
                    cluster1[numcount_1] = storepointcoord[i];
                    numcount_1++;
                } else if (uf.connected(i, pointcoord[2].root) == true) {
                    cluster2[numcount_2] = storepointcoord[i];
                    numcount_2++;
                }
            }

            sortsizecount = sizecount.clone();
            Arrays.sort(sortsizecount);

            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    if (sizecount[j] == sortsizecount[i]) {
                        System.out.printf(""%d %.2f %.2f%n"", sizecount[j], pointcoord[j].x, pointcoord[j].y);
                    }
                }
            }

            minpq = new MinPQ();
            for (int i = 0; i < cluster0.length; i++) {  //第一個cluster與第二個的每個點距離
                for (int j = 0; j < cluster1.length; j++) {
                    event = new Event(cluster0[i], cluster1[j]);
                    minpq.insert(event);
                }
            }

            for (int i = 0; i < cluster0.length; i++) {  //第一個cluster與第三個的每個點距離
                for (int j = 0; j < cluster2.length; j++) {
                    event = new Event(cluster0[i], cluster2[j]);
                    minpq.insert(event);
                }
            }

            for (int i = 0; i < cluster1.length; i++) {  //第二個cluster與第三個的每個點距離
                for (int j = 0; j < cluster2.length; j++) {
                    event = new Event(cluster1[i], cluster2[j]);
                    minpq.insert(event);
                }
            }
//            int m = 0;
//            while (m == 0) {
//                event = (Event) minpq.delMin();
//                if (uf.connected(event.point1.root, event.point2.root) == false) {
//                    System.out.printf(""%.2f%n"", event.distance);
//                    m = 1;
//                }
//            }

            Event outputevent = (Event) minpq.delMin();
            System.out.printf(""%.2f%n"", outputevent.distance);
        }

    }

    private static class Point {

        public double x, y;
        public int root;
        public int idx;

        Point(double coordx, double coordy) {
            x = coordx;
            y = coordy;
        }
    }

    private static class Event implements Comparable<Event> {

        public double distance; // time of event
        public Point point1, point2;

        public Event(Point a, Point b) {
            point1 = a;
            point2 = b;
            distance = (Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2)));
        }

        public int compareTo(Event that) {
            if (this.distance - that.distance > 0) {
                return 1;
            } else if (this.distance - that.distance < 0) {
                return -1;
            }
            return 0;
        }
    }

}

