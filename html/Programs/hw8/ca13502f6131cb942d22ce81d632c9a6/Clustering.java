import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
.
 */
public class Clustering implements Comparable<Clustering> {

    double x;
    double y;

    ArrayList<point> points;

    Clustering(point p) {
        this.points = new ArrayList<>();
        points.add(p);
        this.x = p.getX();
        this.y = p.getY();

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Clustering that) {

        double distance = Math.sqrt(Math.pow((this.x - that.x), 2) + Math.pow((this.y - that.y), 2));
        return distance;

    }

    public void centroidOf(ArrayList<point> points) {

        double centerX = 0;
        double centerY = 0;
        int lengthP = points.size();

        for (int i = 0; i < lengthP; i++) {
            centerX += points.get(i).getX();
            centerY += points.get(i).getY();
        }

        this.x = centerX / lengthP;
        this.y = centerY / lengthP;
    }

    public void merge(Clustering that) {

//        ArrayList<Clustering> newPoints = new ArrayList<>();

        int lengthC = that.points.size();
        for (int i = 0; i < lengthC; i++) {
            this.points.add(that.points.get(i));
        }

        this.centroidOf(this.points);
    }

    @Override
    public int compareTo(Clustering that) {
        if (this.points.size() > that.points.size()) {
            return 1;
        } else if (this.points.size() < that.points.size()) {
            return -1;
        } else if (this.points.size() == that.points.size()) {
            if (this.x < that.x) {
                return 1;
            } else if (this.x > that.x) {
                return -1;
            } else if (this.x == that.x) {
                if (this.y < that.y) {
                    return 1;
                } else if (this.y > that.y) {
                    return -1;
                } else if (this.y == that.y) {
                    return 0;
                }
            }
        }
        return 0;
    }

    public double getClusterDis(Clustering that) {

        double d = this.points.get(0).pointDistanceTo(that.points.get(0));
        for (int i = 0; i < this.points.size(); i++) {
            for (int j = 0; j < that.points.size(); j++) {

                double tempD = this.points.get(i).pointDistanceTo(that.points.get(j));
                if (tempD < d) {
                    d = tempD;
                }
            }
        }
        return d;
    }


    public static class point {

        double x;
        double y;

        point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double pointDistanceTo(point that) {

            double d = Math.sqrt(Math.pow((this.x - that.x), 2) + Math.pow((this.y - that.y), 2));
            return d;
        }
    }


    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String temp = br.readLine();
            int total = Integer.parseInt(temp);

            ArrayList<Clustering> clus = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                String[] temp2 = br.readLine().split("" "");
                point p = new point(Double.parseDouble(temp2[0]), Double.parseDouble(temp2[1]));
                Clustering c = new Clustering(p);
                clus.add(c);
            }
            br.close();

            while (clus.size() > 3) {
                int merge1 = 0;
                int merge2 = 1;
                double miniDis = clus.get(0).distanceTo(clus.get(1));
                for (int i = 0; i < clus.size(); i++) {
                    for (int j = i + 1; j < clus.size(); j++) {
                        double tempDis = clus.get(i).distanceTo(clus.get(j));
                        if (tempDis < miniDis) {
                            miniDis = tempDis;
                            merge1 = i;
                            merge2 = j;
                        }
                    }
                }
                clus.get(merge1).merge(clus.get(merge2));
                clus.remove(merge2);
            }

            double d = -1;
            if (clus.size() > 1) {
                d = clus.get(0).getClusterDis(clus.get(1));
                for (int i = 0; i < clus.size(); i++) {
                    for (int j = i + 1; j < clus.size(); j++) {
                        double tempD = clus.get(i).getClusterDis(clus.get(j));
                        if (tempD < d) {
                            d = tempD;
                        }
                    }
                }
            } else d = 0;


            Clustering[] arrays = new Clustering[clus.size()];
            Clustering[] clusterings = clus.toArray(arrays);

            Arrays.sort(clusterings);


            if (clus.size() == 3) {
                String s1 = String.format(""%2d %.2f %.2f"", clusterings[2].points.size(), clusterings[2].getX(), clusterings[2].getY());
                System.out.println(s1);
            }else if (clus.size() == 2) {
                String s2 = String.format(""%2d %.2f %.2f"", clusterings[1].points.size(), clusterings[1].getX(), clusterings[1].getY());
                System.out.println(s2);
            }

            String s3 = String.format(""%2d %.2f %.2f"", clusterings[0].points.size(), clusterings[0].getX(), clusterings[0].getY());
            String D = String.format(""%.2f"", d);

            System.out.println(s3);
            System.out.println(D);

        }
    }


}

