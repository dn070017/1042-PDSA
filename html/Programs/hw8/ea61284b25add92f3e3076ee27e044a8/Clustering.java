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

            Point[] storepointcoord = new Point[pointCount];
            int idx = 0;
            int clusteridx = pointCount + 1;
            Point initial = new Point(0.1, 0.1);
            Event event=new Event(initial,initial);
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String[] tempcoord = in.split("" "");
                storepointcoord[idx] = new Point(Double.parseDouble(tempcoord[0]), Double.parseDouble(tempcoord[1]));
                storepointcoord[idx].root = idx;
                storepointcoord[idx].idx = idx;
                idx++;
            }
            Point[] pointcoord = storepointcoord;

            if (pointCount <= 3) {   //小於三點的情況，不需做clustering
                for (int i = 0; i < pointCount; i++) {
                    System.out.printf(""%d %.2f %.2f%n"", 1, pointcoord[i].x, pointcoord[i].y);
                }
                if (pointCount != 1) {
                    MinPQ minpq=new MinPQ();
                    for (int i = 0; i < pointCount; i++) {
                        for (int j = i + 1; j < pointCount; j++) {
                            event = new Event(pointcoord[i], pointcoord[j]);
                            minpq.insert(event);
                            
                        }
                    }
                    event=(Event)minpq.delMin();
                    System.out.printf(""%.2f%n"", event.distance);
                } else {
                    System.out.printf(""%.2f%n"", 0.0);
                }

            } else {
                int[] checkarray = new int[pointCount + (pointCount - 3)];

                WeightedQuickUnionUF uf = new WeightedQuickUnionUF(pointCount);

                MinPQ newminpq = new MinPQ();
                MinPQ minpq = new MinPQ();  //存所有點與點之間的距離

                Point[] newpointcoord;

                int tempidx = 0;
                double newx = 0.0;
                double newy = 0.0;
                int pointnum = 0;

                while (pointcoord.length > 3) {
                    if (pointcoord.length == pointCount) {
                        for (int i = 0; i < pointcoord.length; i++) {
                            for (int j = i + 1; j < pointcoord.length; j++) {
                                minpq.insert(new Event(pointcoord[i], pointcoord[j]));
                                newminpq.insert(new Event(pointcoord[i], pointcoord[j]));
                                
                            }
                        }
                    } else {
                        for (int i = 0; i < pointcoord.length - 1; i++) {  //計算新的座標與其他點的距離
                            newminpq.insert(new Event(pointcoord[i], pointcoord[pointcoord.length - 1]));
                        }
                    }

                    int loopend = 0;
                    int cc = 0;
                    while (loopend == 0) {
                        event = (Event) newminpq.delMin();

                        if (checkarray[event.point1.idx] == 1 || checkarray[event.point2.idx] == 1) {
                            cc = 1;
                        }
                        if (cc == 0) {
                            loopend = 1;
                        } else {
                            cc = 0;
                        }
                    }
                    newpointcoord = new Point[pointcoord.length - 1];
                    tempidx = 0;
                    for (int i = 0; i < pointcoord.length; i++) {
                        if ((pointcoord[i].equals(event.point1)) || (pointcoord[i].equals(event.point2))) {
                        } else {
                            newpointcoord[tempidx++] = pointcoord[i];
                        }
                    }

                    checkarray[event.point1.idx] = 1;
                    checkarray[event.point2.idx] = 1;

                    uf.union(event.point1.root, event.point2.root);  //把同clustering的作uf
                    newx = 0.0;
                    newy = 0.0;
                    pointnum = 0;
                    for (int i = 0; i < pointCount; i++) {
                        if (uf.connected(i, event.point1.root) == true) {
                            newx = newx + storepointcoord[i].x;
                            newy = newy + storepointcoord[i].y;
                            pointnum++;
                        }
                    }
                    newpointcoord[tempidx] = new Point(newx / pointnum, newy / pointnum);
                    newpointcoord[tempidx].root = event.point1.root;
                    newpointcoord[tempidx].idx = clusteridx;
                    clusteridx++;
                    pointcoord = newpointcoord;
                }
                int[] sizecount = new int[3];
                int[] sortsizecount;

                Point[] cluster0 = new Point[pointCount];
                Point[] cluster1 = new Point[pointCount];
                Point[] cluster2 = new Point[pointCount];

                for (int i = 0; i < pointCount; i++) {
                    if (uf.connected(i, pointcoord[0].root) == true) {
                        cluster0[sizecount[0]] = storepointcoord[i];
                        sizecount[0]++;
                    } else if (uf.connected(i, pointcoord[1].root) == true) {
                        cluster1[sizecount[1]] = storepointcoord[i];
                        sizecount[1]++;
                    } else if (uf.connected(i, pointcoord[2].root) == true) {
                        cluster2[sizecount[2]] = storepointcoord[i];
                        sizecount[2]++;
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
                
                int whileend=0;
                while(whileend==0)
                {
                    Event outputevent = (Event) minpq.delMin();
                    if(uf.connected(outputevent.point1.root,outputevent.point2.root)!=true){
                        System.out.printf(""%.2f%n"", outputevent.distance);
                        whileend=1;
                    }
                }

            }

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

