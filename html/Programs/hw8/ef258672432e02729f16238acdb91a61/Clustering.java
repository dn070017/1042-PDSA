import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
public class Clustering implements Comparable<Clustering> {

    private Point2D[] points;
    private double distance;

    /*public Player(String name) {
     this.name = name;
     }*/
    // DO NOT MODIFY THIS
    /*public String getName() {
     return this.name;
     }*/
    // DO NOT MODIFY THIS
    public Clustering(Point2D[] points) {
        //only 2 point2D
        this.points = points;
        /*int l=points.length;
         double calculate=0;
         for(int i=0;i<l;i++){
            
         }*/
        this.distance = Math.pow(points[0].x() - points[1].x(), 2) + Math.pow(points[0].y() - points[1].y(), 2);
    }

    public static Point2D clustermean(Point2D[] points) {
        double sig_x = 0.0, sig_y = 0.0;
        for (int i = 0; i < points.length; i++) {
            sig_x = sig_x + points[i].x();
            sig_y = sig_y + points[i].y();
        }
        sig_x = sig_x / points.length;
        sig_y = sig_y / points.length;
        return new Point2D(sig_x, sig_y);
    }

    public Point2D[] getPoint() {
        return this.points;
    }

    public double getDistance() {
        return this.distance;
    }

    // TODO 
    public int compareTo(Clustering that) {
        if (that.distance < this.distance) {
            return -1;
        }
        if (that.distance > this.distance) {
            return 1;
        }
        return 0;
    }

    /*public int compareTo(Point2D[] that){
     if(that.length< this.length){
     return -1;
     }
     if (that.length > this.length) {
     return 1;
     }
     return 0;
     }*/
    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int n = 0;//number of points
            n = Integer.parseInt(br.readLine());

            if (n == 3) {
                System.out.print(1 + "" "");
                String[] s = br.readLine().split("" "");
                double[] d = new double[2];
                d[0] = Double.parseDouble(s[0]);
                d[1] = Double.parseDouble(s[1]);
                System.out.printf(""%.2f"", d[0]);
                System.out.print("" "");
                System.out.printf(""%.2f"", d[1]);
                System.out.printf(""%n"");

                System.out.print(1 + "" "");
                s = br.readLine().split("" "");
                double[] dd = new double[2];
                dd[0] = Double.parseDouble(s[0]);
                dd[1] = Double.parseDouble(s[1]);
                System.out.printf(""%.2f"", dd[0]);
                System.out.print("" "");
                System.out.printf(""%.2f"", dd[1]);
                System.out.printf(""%n"");

                System.out.print(1 + "" "");
                s = br.readLine().split("" "");
                double[] ddd = new double[2];
                ddd[0] = Double.parseDouble(s[0]);
                ddd[1] = Double.parseDouble(s[1]);
                System.out.printf(""%.2f"", ddd[0]);
                System.out.print("" "");
                System.out.printf(""%.2f"", ddd[1]);
                System.out.printf(""%n"");
                double min =100;
                
                min=Math.min(min, Math.pow((d[0]-dd[0]), 2)+Math.pow((d[1]-dd[1]), 2));
                min=Math.min(min, Math.pow((dd[0]-ddd[0]), 2)+Math.pow((dd[1]-ddd[1]), 2));
                min=Math.min(min, Math.pow((d[0]-ddd[0]), 2)+Math.pow((d[1]-ddd[1]), 2));
                System.out.printf(""%.2f"", min);
            } else {

                //ArrayList<Point2D[]> myListarray = new ArrayList<Point2D[]>();
                ArrayList<Point2D[]> myList = new ArrayList<Point2D[]>();

                for (int i = 0; i < n; i++) {
                    String[] s = br.readLine().split("" "");
                    double[] d = new double[2];
                    d[0] = Double.parseDouble(s[0]);
                    d[1] = Double.parseDouble(s[1]);
                    Point2D[] buffer = new Point2D[1];
                    buffer[0] = new Point2D(d[0], d[1]);
                    myList.add(buffer);

                }

                while (n > 3) {
                    MinPQ<Clustering> pq = new MinPQ<Clustering>();

                    for (int i = 0; i < n - 1; i++) {
                        for (int j = i + 1; j < n; j++) {

                            if (i == 0 && j == i + 1) {
                                Point2D[] pp = new Point2D[2];
                                pp[0] = clustermean(myList.get(i));
                                pp[1] = clustermean(myList.get(j));
                                Clustering c = new Clustering(pp);

                                pq.insert(c);

                            } else {

                                Point2D[] pp = new Point2D[2];
                                pp[0] = clustermean(myList.get(i));
                                pp[1] = clustermean(myList.get(j));
                                Clustering c = new Clustering(pp);

                                pq.insert(c);
                                pq.delMin();
                            }
                        }
                    }

                    Clustering cc = new Clustering(pq.delMin().getPoint());

                    int index1 = 50, index2 = 50;
                    for (int i = 0; i < n; i++) {

                        if (clustermean(myList.get(i)).equals(cc.getPoint()[0])) {

                            index1 = i;
                        }
                        if (clustermean(myList.get(i)).equals(cc.getPoint()[1])) {

                            index2 = i;
                        }
                    }

                    Point2D[] fuse = new Point2D[myList.get(index1).length + myList.get(index2).length];
                    for (int i = 0; i < myList.get(index1).length; i++) {
                        fuse[i] = myList.get(index1)[i];
                    }
                    for (int i = myList.get(index1).length; i < myList.get(index1).length + myList.get(index2).length; i++) {
                        fuse[i] = myList.get(index2)[i - myList.get(index1).length];
                    }

                    myList.remove(index1);
                    myList.remove(index2 - 1);
                    myList.add(fuse);
                    n = n - 1;
                }

                if (myList.get(0).length > myList.get(1).length && myList.get(1).length > myList.get(2).length) {
                    System.out.print(myList.get(0).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(1).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(3).length + "" "");
                    System.out.print(clustermean(myList.get(2)).x());
                    System.out.print("" "");
                    System.out.print(clustermean(myList.get(2)).y());
                    System.out.printf(""%n"");
                }
                if (myList.get(0).length > myList.get(2).length && myList.get(2).length > myList.get(1).length) {
                    System.out.print(myList.get(0).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(2).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(1).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).y());
                    System.out.printf(""%n"");
                }
                if (myList.get(1).length > myList.get(0).length && myList.get(0).length > myList.get(2).length) {
                    System.out.print(myList.get(1).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(0).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(2).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).y());
                    System.out.printf(""%n"");
                }
                if (myList.get(1).length > myList.get(2).length && myList.get(2).length > myList.get(0).length) {
                    System.out.print(myList.get(1).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(2).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(0).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).y());
                    System.out.printf(""%n"");
                }
                if (myList.get(2).length > myList.get(0).length && myList.get(0).length > myList.get(1).length) {
                    System.out.print(myList.get(2).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(0).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(1).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).y());
                    System.out.printf(""%n"");
                }
                if (myList.get(2).length > myList.get(1).length && myList.get(1).length > myList.get(0).length) {
                    System.out.print(myList.get(2).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(2)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(1).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(1)).y());
                    System.out.printf(""%n"");
                    System.out.print(myList.get(0).length + "" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).x());
                    System.out.print("" "");
                    System.out.printf(""%.2f"", clustermean(myList.get(0)).y());

                    System.out.printf(""%n"");
                }
                double d = 100;
                for (int i = 0; i < myList.get(1).length; i++) {
                    for (int j = 0; j < myList.get(0).length; j++) {
                        d = Math.min(d, Math.pow(myList.get(0)[j].x() - myList.get(1)[i].x(), 2) + Math.pow(myList.get(0)[j].y() - myList.get(1)[i].y(), 2));
                    }
                }
                for (int i = 0; i < myList.get(2).length; i++) {
                    for (int j = 0; j < myList.get(1).length; j++) {
                        d = Math.min(d, Math.pow(myList.get(1)[j].x() - myList.get(2)[i].x(), 2) + Math.pow(myList.get(1)[j].y() - myList.get(2)[i].y(), 2));
                    }
                }
                for (int i = 0; i < myList.get(2).length; i++) {
                    for (int j = 0; j < myList.get(0).length; j++) {
                        d = Math.min(d, Math.pow(myList.get(0)[j].x() - myList.get(2)[i].x(), 2) + Math.pow(myList.get(0)[j].y() - myList.get(2)[i].y(), 2));
                    }
                }
                System.out.printf(""%.2f"", Math.pow(d, 0.5));

            }
        }
    }
}

