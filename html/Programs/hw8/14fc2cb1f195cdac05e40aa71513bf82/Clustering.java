
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author 許志鵬
 */
public class Clustering {

    /**
     * @param args the command line arguments
     */
    private static class cluster implements Comparable<cluster> {
        
        private int size;
        private Point2D[] points;
        private Point2D centr;
        
        public cluster(Point2D a) {
            points = new Point2D[1];
            points[0] = a;
            centr = this.getCentr();
            size = this.getSize();
        }
        
        public cluster(cluster c1, cluster c2) {
            points = new Point2D[c1.size + c2.size];
            for (int i = 0; i < c1.size; i++) {
                points[i] = c1.points[i];
            }
            for (int i = 0; i < c2.size; i++) {
                points[c1.size + i] = c2.points[i];
            }
            double cx = (c1.centr.x() * c1.size + c2.centr.x() * c2.size) / (c1.size + c2.size);
            double cy = (c1.centr.y() * c1.size + c2.centr.y() * c2.size) / (c1.size + c2.size);
            centr = new Point2D(cx, cy);
            size = c1.size + c2.size;
        }
        
        public Point2D getCentr() {
            double tempx = 0;
            double tempy = 0;
            for (int i = 0; i < points.length; i++) {
                tempx = tempx + points[i].x();
                tempy = tempy + points[i].y();
            }
            tempx = tempx / points.length;
            tempy = tempy / points.length;
            centr = new Point2D(tempx, tempy);
            return centr;
        }
        
        public int getSize() {
            size = points.length;
            return size;
        }
        
        @Override
        public int compareTo(cluster t) {
            
            int[] ans = new int[3];
            int outCome = 0;
            if (this.size > t.size) {
                ans[0] = 1;
            }
            if (this.size == t.size) {
                ans[0] = 0;
            }
            if (this.size < t.size) {
                ans[0] = -1;
            }
            if (this.centr.x() > t.centr.x()) {
                ans[1] = 1;
            }
            if (this.centr.x() == t.centr.x()) {
                ans[1] = 0;
            }
            if (this.centr.x() < t.centr.x()) {
                ans[1] = -1;
            }
            if (this.centr.y() > t.centr.y()) {
                ans[2] = 1;
            }
            if (this.centr.y() == t.centr.y()) {
                ans[2] = 0;
            }
            if (this.centr.y() < t.centr.y()) {
                ans[2] = -1;
            }
            if (ans[0] == 1) {
                outCome = 1;
            }
            if (ans[0] == 0) {
                outCome = 0;
            }
            if (ans[0] == -1) {
                outCome = -1;
            }
            if (ans[0] == 0 && ans[1] == 1) {
                outCome = 1;
            }
            if (ans[0] == 0 && ans[1] == 0) {
                outCome = 0;
            }
            if (ans[0] == 0 && ans[1] == -1) {
                outCome = -1;
            }
            if (ans[0] == 0 && ans[1] == 0 && ans[2] == 1) {
                outCome = 1;
            }
            if (ans[0] == 0 && ans[1] == 0 && ans[2] == 0) {
                outCome = 0;
            }
            if (ans[0] == 0 && ans[1] == 0 && ans[2] == -1) {
                outCome = -1;
            }
            return outCome;
        }
    }
    
    private static class pair implements Comparable<pair> {
        
        private cluster c1;
        private cluster c2;
        private double distance;
        
        public pair(cluster c1, cluster c2) {
            this.c1 = c1;
            this.c2 = c2;
            distance = this.getDistance();
        }
        
        private double getDistance() {
            
            double ans = c1.centr.distanceTo(c2.centr);
            return ans;
            
        }
        
        @Override
        public int compareTo(pair t) {
            
            int ans = 0;
            if (this.distance > t.distance) {
                ans = 1;
            }
            if (this.distance < t.distance) {
                ans = -1;
            }
            return ans;
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            String[] header = br.readLine().split("" "");
            int num = Integer.parseInt(header[0]);
            
            ArrayList<cluster> temp = new ArrayList<cluster>();
            ArrayList<cluster> remain = new ArrayList<cluster>();
            Point2D[] in = new Point2D[num];
            MinPQ<pair> dists = new MinPQ<pair>();
            for (int i = 0; i < num; i++) {
                String[] test1 = br.readLine().split("" "");
                double x = Double.parseDouble(test1[0]);
                double y = Double.parseDouble(test1[1]);
                in[i] = new Point2D(x, y);
                cluster tt = new cluster(in[i]);
                temp.add(i, tt);
            }
            for (int i = 0; i < temp.size(); i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    pair pp = new pair(temp.get(i), temp.get(j));
                    dists.insert(pp);
                }
            }
            pair inter = null;
            while (temp.size() > 3) {
                
                inter = dists.delMin();
                
                int flag = 1;
                while (flag == 1) {
                    flag = 0;
                    
                    for (int i = 0; i < remain.size(); i++) {
                        if (inter.c1 == remain.get(i) || inter.c2 == remain.get(i)) {
                            flag = 1;
                            // inter=dists.delMin();
                        }
                        
                    }
                    if (flag == 1) {
                        inter = dists.delMin();
                    }
                }
                cluster buff = new cluster(inter.c1, inter.c2);
                temp.remove(inter.c1);
                temp.remove(inter.c2);
                remain.add(inter.c1);
                remain.add(inter.c2);
                for (int i = 0; i < temp.size(); i++) {
                    pair paa = new pair(buff, temp.get(i));
                    dists.insert(paa);
                }
                temp.add(buff);

                //StdOut.println(temp.size());
            }
            // TODO code application logic hereStdOut.println(temp.get(0));
            MinPQ<cluster> finale = new MinPQ<cluster>();
            for (int i = 0; i < temp.size(); i++) {
                finale.insert(temp.get(i));
            }
            ArrayList<cluster> finn = new ArrayList<cluster>();
            for (int i = 0; i < temp.size(); i++) {
                finn.add(i, finale.delMin());
            }
            for (int i = 0; i < finn.size(); i++) {
                System.out.print(finn.get(finn.size() - i - 1).size);
                System.out.print("" "");
                System.out.print(String.format(""%.2f"", finn.get(finn.size() - i - 1).centr.x()));
                System.out.print("" "");
                System.out.print(String.format(""%.2f"", finn.get(finn.size() - i - 1).centr.y()));
                System.out.print(""\n"");
            }
            MinPQ r = new MinPQ();
            Point2D[][] pp = new Point2D[finn.size()][];
            for (int i = 0; i < finn.size(); i++) {
                pp[i] = finn.get(i).points;
                
            }
            for (int i = 0; i < finn.size(); i++) {
                for (int j = i + 1; j < finn.size(); j++) {
                    for (int k = 0; k < pp[j].length; k++) {
                        for (int l = 0; l < pp[i].length; l++) {
                            r.insert(pp[i][l].distanceTo(pp[j][k]));
                        }
                    }
                }
            }
            System.out.print(String.format(""%.2f"", r.delMin()));
        }
    }
}

