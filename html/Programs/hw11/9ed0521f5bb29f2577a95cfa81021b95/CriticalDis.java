/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.*;

/**
 *
 * @author acer
 */
public class CriticalDis {

     public static double distance(Point2D x, Point2D y) {
          return Math.sqrt(Math.pow(x.x() - y.x(), 2) + Math.pow(x.y() - y.y(), 2));
     }

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          // TODO code application logic here

          In in = new In(args[0]);
          int N = Integer.valueOf(in.readLine());
          Point2D[] point_array = new Point2D[N];
          String line;
          int input_count = 0;
          double Min = 2;
          double Max = 0;
          Point2D MinNode = null;
          Point2D MaxNode = null;
          int MinIndex = 0;
          int MaxIndex = 0;
          while ((line = in.readLine()) != null) {
               point_array[input_count] = new Point2D(Double.valueOf(line.split("" "")[0]), Double.valueOf(line.split("" "")[1]));

////////////////////////////////////     draaw point    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
               StdDraw.setPenColor(StdDraw.BLACK);
               StdDraw.filledCircle(point_array[input_count].x(), point_array[input_count].y(), 0.01);
               String str = String.valueOf(input_count);
               StdDraw.text(point_array[input_count].x(), point_array[input_count].y() + 0.03, str);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               if (Min > (point_array[input_count].x() + point_array[input_count].y())) {
                    Min = point_array[input_count].x() + point_array[input_count].y();
                    MinNode = new Point2D(point_array[input_count].x(), point_array[input_count].y());
                    MinIndex = input_count;
               }
               if (Max < (point_array[input_count].x() + point_array[input_count].y())) {
                    Max = point_array[input_count].x() + point_array[input_count].y();
                    MaxNode = new Point2D(point_array[input_count].x(), point_array[input_count].y());
                    MaxIndex = input_count;
               }
               input_count++;
          }
///////////////////////////// draw max and min point  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.filledCircle(MinNode.x(), MinNode.y(), 0.01);
          StdDraw.filledCircle(MaxNode.x(), MaxNode.y(), 0.01);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//                    ArrayList<Edge> edgeList = new ArrayList<Edge>();
//                    MinPQ<Edge> pq = new MinPQ<Edge>();
          MinPQ<Pair> pq_test = new MinPQ<Pair>();
          for (int idx = 0; idx < point_array.length; idx++) {
               for (int idy = 0; idy < point_array.length; idy++) {
                    if (point_array[idx].x() < point_array[idy].x() && point_array[idx].y() < point_array[idy].y()) {
//                                                  edgeList.add( new Edge( point_array[idx] , point_array[idy] , distance( point_array[idx] , point_array[idy]) ) );
//                                                  pq.insert(new Edge( point_array[idx] , point_array[idy] , distance( point_array[idx] , point_array[idy]) ));
                         pq_test.insert(new Pair(idx, idy, distance(point_array[idx], point_array[idy])));
//                                                  G.addEdge(idx, idy);
                    }
               }
          }
          Digraph G = new Digraph(N);
//          System.out.println(""Min index "" + MinIndex + "" Max index "" + MaxIndex);
          while (!pq_test.isEmpty()) {
               Pair temp = pq_test.delMin();

//               System.out.println(""X  "" + temp.getNodeX() + "" Y "" + temp.getNodeY() + "" dis "" + temp.getDistance());
               G.addEdge(temp.getNodeX(), temp.getNodeY());
               DirectedDFS dfs = new DirectedDFS(G, MinIndex);
//               double d = temp.getDistance();
               if (dfs.marked(MaxIndex)) {
//                    System.out.println(temp.getDistance());
//                    System.out.println(""%1.3f"", d);
                    String D = String.format(""%1.3f"", temp.getDistance());
                    System.out.println(D);

                    break;
               } else {
                    continue;
               }

          }

//                    System.out.println(""Min X: "" + MinNode.x() + "" Y: "" + MinNode.y() + "" dis: "" + (MinNode.x() + MinNode.y()) + "" check "" + Min);
//                    System.out.println(""Max X: "" + MaxNode.x() + "" Y: "" + MaxNode.y() + "" dis: "" + (MaxNode.x() + MaxNode.y()) + "" check "" + Max);
     }

}

class Edge implements Comparable<Edge> {

     private Point2D smallNode;
     private Point2D largeNode;
     private double distance;

     public Edge(Point2D smallNode, Point2D largeNode, double distance) {
          this.smallNode = smallNode;
          this.largeNode = largeNode;
          this.distance = distance;
     }

     public Point2D getSmallNode() {
          return this.smallNode;
     }

     public Point2D getLargeNode() {
          return this.largeNode;
     }

     public double getDistance() {
          return this.distance;
     }

     public int compareTo(Edge that) {
          if (this.distance > that.distance) {
               return +1;
          }
          if (this.distance < that.distance) {
               return -1;
          }
          return 0;
     }

}

class Pair implements Comparable<Pair> {

     private int point_x, point_y;
     private double distance;

     public Pair(int x, int y, double dis) {
          this.point_x = x;
          this.point_y = y;
          this.distance = dis;
     }

     public void setDistance(double dis) {
          this.distance = dis;
     }

     public int getNodeX() {
          return this.point_x;
     }

     public int getNodeY() {
          return this.point_y;
     }

     public double getDistance() {
          return this.distance;
     }

     public int compareTo(Pair that) {
          if (this.distance > that.distance) {
               return +1;
          }
          if (this.distance < that.distance) {
               return -1;
          }
          return 0;
     }
}

