/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.pow;

/**
 *
 * @author tks11
 */
public class CriticalDis {

    /**
     * @param args the command line arguments
     */
    public static class pair implements Comparable<pair> {

        private int x;
        private int y;
        private Point2D tempx;
        private Point2D tempy;
        private double dist;

        public pair(int x, int y, Point2D tempx, Point2D tempy) {
            this.x = x;
            this.y = y;
            this.tempx = tempx;
            this.tempy = tempy;
            dist = tempx.distanceTo(tempy);
        }

        public int x() {  return this.x; }

        public int y() {  return this.y; }

        public Point2D tempx() {  return this.tempx;}

        public Point2D tempy() {  return this.tempy;}

        public double Distance() {return this.dist; }

        public int compareTo(pair that) {
            if (this.Distance() > that.Distance()) {    return 1; }
            if (this.Distance() < that.Distance()) {    return -1;}
            else{ return 0;}          
        }
    }
    private static double sum(double a,double b ){
        double x=a+b;
        return x;
    }
    private static boolean CheckLefLarge(double a,double b ){
        if (a>b){
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        int LineNum = Integer.parseInt(br.readLine()); //讀第一行       
//        Point2D[] add = new Point2D[1] ;
        Point2D[] add = new Point2D[LineNum];
        for (int i = 0; i < LineNum; i++) {
            String RowLine = br.readLine();
            double x = Double.parseDouble(RowLine.split("" "")[0]);
            double y = Double.parseDouble(RowLine.split("" "")[1]);
            add[i] = new Point2D(x, y);
        }
        for (int i = 1; i < LineNum - 1; i++) {
            double s = sum(add[0].x(),add[0].y());            
            double t = sum(add[LineNum - 1].x(), add[LineNum - 1].y());
            double comparing = sum(add[i].x() , add[i].y());
            if (CheckLefLarge(s,comparing)) {
                Point2D temp = add[i];
                add[i] = add[0];
                add[0] = temp;
            } else if (CheckLefLarge(comparing , t)) {
                Point2D temp = add[i];
                add[i] = add[LineNum - 1];
                add[LineNum - 1] = temp;
            }
        }
//--------------------------------------------------------------------------------------//
        MinPQ<pair> Ans = new MinPQ<pair>();
        for (int i = 0; i < LineNum; i++) {
            for (int j = 0; j < LineNum; j++) {
                if ((CheckLefLarge(add[j].x() , add[i].x())) && (CheckLefLarge(add[j].y() , add[i].y())) && i != j) {
                    pair temp = new pair(i, j, add[i], add[j]);
                    Ans.insert(temp);
                }
            }
        }
        Digraph path = new Digraph(LineNum);
        DirectedDFS checkconnect = new DirectedDFS(path, 0);
        double d = 0;
        while (checkconnect.marked(LineNum - 1) != true) {
            pair temp = Ans.delMin();
            path.addEdge(temp.x(), temp.y());
            checkconnect = new DirectedDFS(path, 0);
            d = temp.Distance();
        }
        System.out.printf(""%1.3f\n"", d);
    }
}

