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
public class Clustering {

          /**
           * @param args the command line arguments
           */
          public static double distance(Point2D x, Point2D y) {
                    return Math.sqrt(Math.pow(x.x() - y.x(), 2) + Math.pow(x.y() - y.y(), 2));
          }

          public static double distance(Cluster x, Cluster y) {
                    return Math.sqrt(Math.pow(x.getXcor() - y.getXcor(), 2) + Math.pow(x.getYcor() - y.getYcor(), 2));
          }

          public static void modif(MinPQ pq, Pair[] pair, ArrayList<Cluster> cluster_array, Cluster cluster) {

                    for (int index = 0; index < pair.length; index++) {
                              if (pair[index].getNodeX() == cluster.getIndex()) {
                                        pair[index].setDistance(distance(cluster, cluster_array.get(pair[index].getNodeY())));
                                        pq.insert(pair[index]);
                              } else if (pair[index].getNodeY() == cluster.getIndex()) {
                                        pair[index].setDistance(distance(cluster, cluster_array.get(pair[index].getNodeX())));
                                        pq.insert(pair[index]);
                              } else {
                                        continue;
                              }
                    }

          }

          public static int findCluster(int[] array, int node) {
                    while (array[node] != node) {
                              node = array[node];
                    }
                    return node;
          }

          public static void main(String[] args) {
                    // TODO code application logic here

                    In in = new In(args[0]);
                    int N = Integer.valueOf(in.readLine());
                    Point2D[] point_array = new Point2D[N];
                    ArrayList<Cluster> cluster = new ArrayList<Cluster>();

                    String line;
                    int input_count = 0;
                    int[] cluster_array = new int[N];

                    while ((line = in.readLine()) != null) {
                              point_array[input_count] = new Point2D(Double.valueOf(line.split("" "")[0]), Double.valueOf(line.split("" "")[1]));
                              Cluster clu = new Cluster(input_count, point_array[input_count].x(), point_array[input_count].y());
                              cluster.add(clu);
                              cluster_array[input_count] = input_count;
                              input_count++;
                    }

                    MinPQ<Pair> pq = new MinPQ<Pair>();
                    MinPQ<Pair> pq_out = new MinPQ<Pair>();
                    Pair[] pair = new Pair[N * (N - 1) / 2];

                    int counter = 0;

                    for (int indx = 0; indx < N; indx++) {
                              for (int indy = indx + 1; indy < N; indy++) {
                                        pair[counter] = new Pair(indx, indy, Clustering.distance(point_array[indx], point_array[indy]));
                                        pq.insert(pair[counter]);
                                        pq_out.insert(pair[counter]);
                                        counter++;
                              }
                    }

                    int count = N;

                    while (count > 3) {

                              double storge = pq.delMin().getDistance();

                              for (int i = 0; i < pair.length; i++) {
                                        if (storge == Clustering.distance(cluster.get(pair[i].getNodeX()), cluster.get(pair[i].getNodeY()))) {
                                                  if (pair[i].getNodeX() == cluster_array[pair[i].getNodeX()] && pair[i].getNodeY() == cluster_array[pair[i].getNodeY()]) {
                                                            cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).combine(cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getMember(), 
                                                                    cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getXcor(),
                                                                    cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getYcor(),
                                                                    cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getSize());

                                                            Clustering.modif(pq, pair, cluster, cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))));

                                                            cluster_array[cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getIndex()] = cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getIndex();

                                                            count--;
                                                            break;
                                                  } else {
                                                            break;
                                                  }
                                        } else {
                                                  continue;
                                        }
                              }

                    }

                    Cluster[] output_group = new Cluster[count];
                    int output_count = 0;
                    for (int j = 0; j < cluster.size(); j++) {
                              if (cluster_array[j] == j) {
                                        output_group[output_count] = cluster.get(j);
                                        output_count++;
                              } else {
                                        continue;
                              }
                    }

                    MergeX.sort(output_group);
                    for (int i = 0; i < output_group.length; i++) {
                              String X = String.format(""%.2f"", output_group[i].getXcor());
                              String Y = String.format(""%.2f"", output_group[i].getYcor());
                              System.out.println(output_group[i].getSize() + "" "" + X + "" "" + Y);
                    }
                    Pair out = pq_out.delMin();
                    while (Clustering.findCluster(cluster_array, out.getNodeX()) == Clustering.findCluster(cluster_array, out.getNodeY())) {
                              out = pq_out.delMin();
                    }
                    if (count == 1) {
                              System.out.println(""0"");
                    } else {
                              String D = String.format(""%.2f"", Clustering.distance(point_array[out.getNodeX()], point_array[out.getNodeY()]));
                              System.out.println(D);
                    }
          }

}

class Cluster implements Comparable<Cluster> {

          private int N;
          private int index;

          private double Xcor;
          private double Ycor;
          private ArrayList member;

          public Cluster(int index, double x, double y) {
                    this.index = index;
                    this.Ycor = y;
                    this.Xcor = x;
                    this.member = new ArrayList();
                    member.add(index);
                    this.N = 1;
          }

          public void setCluster(Cluster that) {
                    this.index = that.index;
                    this.Xcor = that.Xcor;
                    this.Ycor = that.Ycor;
                    this.member = that.member;
                    this.N = that.N;
          }

          public ArrayList getMember() {
                    return (this.member);
          }

          public int getIndex() {
                    return this.index;
          }

          public double getXcor() {
                    return this.Xcor;
          }

          public double getYcor() {
                    return this.Ycor;
          }

          public int getSize() {
                    return N;
          }

          public void combine(ArrayList member, double x, double y, int size /*, int[] cluster_array*/) {
//                    ArrayList temp = new ArrayList(index);

                    for (int i = 0; i < member.size(); i++) {
                              this.member.add(member.get(i));
//                              int a =Integer.parseInt(member.get(i).toString());
//                              cluster_array[Integer.parseInt(member.get(i).toString())]=this.N;
                    }

                    this.Xcor = (this.N * this.Xcor + size * x) / (this.N + size);
                    this.Ycor = (this.N * this.Ycor + size * y) / (this.N + size);
                    this.N = this.N + size;
          }

          public int compareTo(Cluster that) {
                    if (this.N > that.N) {
                              return -1;
                    } else if (this.N < that.N) {
                              return +1;
                    } else if (this.Xcor > that.Xcor) {
                              return +1;
                    } else if (this.Xcor < that.Xcor) {
                              return -1;
                    } else if (this.Ycor > that.Ycor) {
                              return +1;
                    } else if (this.Ycor < that.Ycor) {
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

