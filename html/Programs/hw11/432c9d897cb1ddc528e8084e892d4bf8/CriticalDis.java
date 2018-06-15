/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */


import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Han
 */
public class CriticalDis {

    public static class edge implements Comparable<edge> {

        private int a;
        private int b;
        private double dist;

        public edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        public int compareTo(edge that) {
            if (this.dist > that.dist) {
                return +1;
            } else if (this.dist < that.dist) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int num=Integer.parseInt(br.readLine());
            Point2D[] points=new Point2D[num];
            double minindex=500;
            double maxindex=0;
            int index1=0;
            int index2=0;
            for(int i=0;i<num;i++){
                String[] s=br.readLine().split("" "");
                points[i]=new Point2D(Double.parseDouble(s[0]),Double.parseDouble(s[1]));
                if(points[i].x()+points[i].y()<minindex){
                    minindex=points[i].x()+points[i].y();
                    index1=i;
                }
                if(points[i].x()+points[i].y()>maxindex){
                    maxindex=points[i].x()+points[i].y();
                    index2=i;
                }
                /*StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.black);
                StdDraw.text(points[i].x(), points[i].y()+0.02, String.valueOf(i));*/
                //points[i].draw();
            }
            MinPQ<edge> p=new MinPQ<edge>();
            
            for(int i=0;i<num-1;i++){
                for(int j=i+1;j<num;j++){
                    
                    double dist=Math.pow(Math.pow(points[i].x()-points[j].x(), 2)+Math.pow(points[i].y()-points[j].y(), 2), 0.5);
                    edge e=new edge(i,j,dist);
                    p.insert(e);
                }
            }
            Digraph G = new Digraph(num);
            DirectedDFS dfs = new DirectedDFS(G, index1);
            edge ee = null;
            while(true){
                ee=p.delMin();
                
                if(points[ee.a].x()<points[ee.b].x()&&points[ee.a].y()<points[ee.b].y()){
                    
                    G.addEdge(ee.a, ee.b);
                    dfs=new DirectedDFS(G, index1);
                    /*StdDraw.setPenRadius(0.01);
                    StdDraw.line(points[ee.a].x(), points[ee.a].y(), points[ee.b].x(), points[ee.b].y());*/
                }
                if(points[ee.a].x()>points[ee.b].x()&&points[ee.a].y()>points[ee.b].y()){
                    
                    G.addEdge(ee.b, ee.a);
                    dfs=new DirectedDFS(G, index1);
                    /*StdDraw.setPenRadius(0.01);
                    StdDraw.line(points[ee.a].x(), points[ee.a].y(), points[ee.b].x(), points[ee.b].y());*/
                }
                if(dfs.marked(index2)){
                    System.out.printf(""%1.3f\n"", ee.dist);
                    break;
                }
            }
            
        }
    }
}

