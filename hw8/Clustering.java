import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.geom.Point2D;

class Group implements Comparable<Group>{
    public int size;
    public Point2D point;
    public ArrayList<Point2D> points;
    public Group(double x, double y, int size, ArrayList<Point2D> pts){
        this.size = size;
        this.point = new Point2D.Double(x, y);
        this.points = pts;
    }
    public double distance(Group that){
        return this.point.distance(that.point);
    }
    public int compareTo(Group that){
        if(this.size > that.size) return 1;
        else if(this.size < that.size) return -1;
        else{
            if(this.point.getX() > that.point.getX()) return -1;
            else if(this.point.getX() < that.point.getX()) return 1;
            else{
                if(this.point.getY() > that.point.getY()) return -1;
                else if(this.point.getY() < that.point.getY()) return 1;
                else{
                    if(this.point.getX() > that.point.getX()) return -1;
                    else if(this.point.getX() < that.point.getX()) return 1;
                    else{
                        if(this.point.getY() > that.point.getY()) return -1;
                        else if(this.point.getY() < that.point.getY()) return 1;
                        else return 0;
                    }
                }
            }
        }
    }
}

class Pair implements Comparable<Pair>{
    public Group a;
    public Group b;
    public double distance;
    public Pair(Group a, Group b){
        this.a = a;
        this.b = b;
        this.distance = a.distance(b);
    }
    public int compareTo(Pair that){
        if(this.distance > that.distance) return 1;
        else if(this.distance < that.distance) return -1;
        else{
            if(this.a.point.getX() > that.a.point.getX()) return -1;
            else if(this.a.point.getX() < that.a.point.getX()) return 1;
            else{
                if(this.a.point.getY() > that.a.point.getY()) return -1;
                else if(this.a.point.getY() < that.a.point.getY()) return 1;
                else{
                    if(this.b.point.getX() > that.b.point.getX()) return -1;
                    else if(this.b.point.getX() < that.b.point.getX()) return 1;
                    else{
                        if(this.b.point.getY() > that.b.point.getY()) return -1;
                        else if(this.b.point.getY() < that.b.point.getY()) return 1;
                        else return 0;
                    }
                }
            }
        }
    }
}

public class Clustering{
    private static int n = 0;
    private static ArrayList<Group> points;
    private static PriorityQueue pairQueue;
    
    private static class PriorityQueue{
        private int n;
        private Pair[] pairs;
        public PriorityQueue(int n){
            this.n = 0;
            this.pairs = new Pair[n + 2];
        }
        public int size(){
            return n;
        }
        public boolean isEmpty(){
            return n == 0;
        }
        public Pair[] getPairs(){
            return pairs;
        }
        public void insert(Pair p){
            pairs[++n] = p;
            swim(n);
        }
        public Pair delMin(){
            Pair min = pairs[1];
            Pair tmp = pairs[n];
            pairs[n--] = min;
            pairs[1]   = tmp;
            sink(1);
            pairs[n+1] = null;
            return min;
        }
        private void swim(int k){
            while(k > 1 && pairs[k/2].compareTo(pairs[k]) == 1){
                Pair tmp = pairs[k];
                pairs[k] = pairs[k/2];
                pairs[k/2] = tmp;
                k = k / 2;
            }
        }
        private void sink(int k){
            while(2 * k <= n){
                int j = 2 * k;
                if(j < n && pairs[j].compareTo(pairs[j+1]) == 1) j++;
                if(pairs[k].compareTo(pairs[j]) != 1) break;
                Pair tmp = pairs[j];
                pairs[j] = pairs[k];
                pairs[k] = tmp;
                k = j;
            }
        }
        
        public void print(){
            for(int i = 1; i <= n; i++){
                System.out.printf("%d: %3.3f\n", i, pairs[i].distance);
            }
        }
    }
    
    
    public static void main(String[] argv) throws Exception{
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            n = Integer.parseInt(scanner.nextLine());
            points = new ArrayList<Group>();
            pairQueue = new PriorityQueue(2 * n * n);
        }
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            String[] pointStr = data.split(" ");
            ArrayList<Point2D> tmp = new ArrayList<Point2D>();
            tmp.add(new Point2D.Double(Double.parseDouble(pointStr[0]), Double.parseDouble(pointStr[1])));
            Group point = new Group(Double.parseDouble(pointStr[0]), Double.parseDouble(pointStr[1]), 1, tmp);
            //StdDraw.setPenColor(StdDraw.DARK_GRAY);
            //StdDraw.filledCircle(Double.parseDouble(pointStr[0]), Double.parseDouble(pointStr[1]), 0.008);
            points.add(point);
        }
        for(int i = 0; i < points.size() - 1; i++){
            for(int j = i + 1; j < points.size(); j++){
                Pair pair = new Pair(points.get(i), points.get(j));
                pairQueue.insert(pair);
                //System.out.println(pair.distance);
            }
        }
        //double tmp = 0.008;
        while(points.size() > 3){
            Pair groupPair = pairQueue.delMin();
            Group a = groupPair.a;
            Group b = groupPair.b;
            if(!points.contains(a) || !points.contains(b)) continue;
            //System.out.printf("%f: %d %3.3f %3.3f, %d %3.3f %3.3f\n", groupPair.a.point.distance(groupPair.b.point), groupPair.a.size, groupPair.a.point.getX(), groupPair.a.point.getY(), groupPair.b.size, groupPair.b.point.getX(), groupPair.b.point.getY());
            //System.out.printf("%d", points.size());
            double x = ((a.point.getX() * a.size) + (b.point.getX() * b.size)) / (a.size + b.size);
            double y = ((a.point.getY() * a.size) + (b.point.getY() * b.size)) / (a.size + b.size);
            //System.out.printf("\t%3.3f %3.3f\n", x, y);
            ArrayList<Point2D> tt1 = a.points;
            ArrayList<Point2D> tt2 = b.points;
            tt1.addAll(tt2);
            Group group = new Group(x, y, a.size + b.size, tt1);
            //StdDraw.setPenColor(StdDraw.RED);
            //StdDraw.filledCircle(x, y, tmp);
            //tmp += 0.00001;
            //StdDraw.setPenColor(StdDraw.GREEN);
            //StdDraw.line(a.point.getX(), a.point.getY(),b.point.getX(), b.point.getY());
            points.remove(a);
            //System.out.printf("%d\n", points.size());
            points.remove(b);
            //System.out.printf("%d\n", points.size());
            for(int i = 0; i < points.size(); i++){
                Pair pair = new Pair(points.get(i), group);
                pairQueue.insert(pair);
            }
            points.add(group);
        }
        Group[] result = new Group[points.size()];
        for(int i = 0; i < points.size(); i++){
            //StdDraw.setPenColor(StdDraw.MAGENTA);
            //StdDraw.filledCircle(points.get(i).point.getX(), points.get(i).point.getY(), tmp);
            result[i] = points.get(i);
        }
        Arrays.sort(result, Collections.reverseOrder());
        
        double min = 3;
        if(points.size() == 2){
            ArrayList<Point2D> tt1 = points.get(0).points;
            ArrayList<Point2D> tt2 = points.get(1).points;
            for(int i = 0; i < tt1.size(); i++){
                for(int j = 0; j < tt2.size(); j++){
                    if(tt1.get(i).distance(tt2.get(j)) < min){
                        min = tt1.get(i).distance(tt2.get(j));
                    }
                }
            }
        }

        if(points.size() == 3){
            ArrayList<Point2D> tt1 = points.get(0).points;
            ArrayList<Point2D> tt2 = points.get(1).points;
            ArrayList<Point2D> tt3 = points.get(2).points;

            for(int i = 0; i < tt1.size(); i++){
                for(int j = 0; j < tt2.size(); j++){
                    if(tt1.get(i).distance(tt2.get(j)) < min){
                        min = tt1.get(i).distance(tt2.get(j));
                    }
                }
            }

            for(int i = 0; i < tt2.size(); i++){
                for(int j = 0; j < tt3.size(); j++){
                    if(tt2.get(i).distance(tt3.get(j)) < min){
                        min = tt2.get(i).distance(tt3.get(j));
                    }
                }
            }

            for(int i = 0; i < tt1.size(); i++){
                for(int j = 0; j < tt3.size(); j++){
                    if(tt1.get(i).distance(tt3.get(j)) < min){
                        min = tt1.get(i).distance(tt3.get(j));
                    }
                }
            }
        }

        for(int i = 0; i < result.length; i++){
            System.out.printf("%d %3.2f %3.2f\n", result[i].points.size(), result[i].point.getX(), result[i].point.getY());
        }
        System.out.printf("%.2f\n", min);
        //pairQueue.print();
    }
}

