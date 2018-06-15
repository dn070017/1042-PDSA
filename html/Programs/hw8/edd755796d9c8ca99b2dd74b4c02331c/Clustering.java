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

          public static void modif(QuickUnionUF uf, MinPQ pq, Pair[] pair, ArrayList<Cluster> cluster_array, Cluster cluster) {

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

          public static void main(String[] args) {
                    // TODO code application logic here

                    In in = new In(args[0]);
                    int N = Integer.valueOf(in.readLine());
                    Point2D[] point_array = new Point2D[N];
                    ArrayList<Cluster> cluster = new ArrayList<Cluster>();

                    String line;
                    int input_count = 0;
                    while ((line = in.readLine()) != null) {
                              point_array[input_count] = new Point2D(Double.valueOf(line.split("" "")[0]), Double.valueOf(line.split("" "")[1]));
                               Cluster clu = new Cluster(input_count, point_array[input_count].x(), point_array[input_count].y());
                               cluster.add(clu);
                              input_count++;
                    }

//                    for (int count = 0; count < point_array.length; count++) {
//                              StdDraw.setPenColor(StdDraw.BLACK);
//                              StdDraw.filledCircle(point_array[count].x(), point_array[count].y(), 0.01);
//                              String str = String.valueOf(count);
//                              StdDraw.text(point_array[count].x(), point_array[count].y() + 0.03, str);
//                    }
                    QuickUnionUF uf = new QuickUnionUF(N);

                    MinPQ<Pair> pq = new MinPQ<Pair>();

                    Pair[] pair = new Pair[N * (N - 1) / 2];

                    int counter = 0;

                    for (int indx = 0; indx < N; indx++) {
                              for (int indy = indx + 1; indy < N; indy++) {
                                        pair[counter] = new Pair(indx, indy, Clustering.distance(point_array[indx], point_array[indy]));
                                        pq.insert(pair[counter]);
                                        counter++;
                              }
                    }

//                    ArrayList<Cluster> cluster = new ArrayList<Cluster>();
//
//                    for (int i = 0; i < N; i++) {
//                              Cluster clu = new Cluster(i, point_array[i].x(), point_array[i].y());
//                              cluster.add(clu);
//                    }

                    int count = N;
                    double scale = 0.01;

                    while (count > 3) {

                              double storge = pq.delMin().getDistance();

                              for (int i = 0; i < pair.length; i++) {
                                        if (/*storge == pair[i].getDistance() &&*/ storge == Clustering.distance(cluster.get(pair[i].getNodeX()), cluster.get(pair[i].getNodeY()))) {
//                                                  System.out.println(pair[i].getNodeX() + ""\t"" + pair[i].getNodeY() + ""\t"" + pair[i].getDistance());
//                                         System.out.println(""cluster size "" + cluster.size());
//                                       System.out.println(pair[0].getNodeX()+""cheack ""+ cluster.indexOf(pair[0].getNodeX()));
                                                  if (  pair[i].getNodeX() == uf.find(pair[i].getNodeX()) &&  pair[i].getNodeY() == uf.find(pair[i].getNodeY())  ) {

//                                                            System.out.println("""");
//                                                            System.out.println(""pair[i].getNodeX() "" + cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getIndex());
//                                                            System.out.println("" cluster.get(pair[i].getNodeY()).getNumber() "" + cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getNumber());
//                                                            System.out.println("" cluster.get(pair[i].getNodeY()).getXcor() "" + cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getXcor());
//                                                            System.out.println("" cluster.get(pair[i].getNodeY()).getYcor() "" + cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getYcor());
//                                                            System.out.println("""");
//                                                            StdDraw.setPenColor(StdDraw.GREEN);
//                                                            StdDraw.line(cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getXcor()
//                                                                    , cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getYcor()
//                                                                    , cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getXcor()
//                                                                    , cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getYcor()
//                                                            );
                                                            
                                                            cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).combine( cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getMember()
                                                                    , cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getXcor()
                                                                    , cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getYcor()
                                                                    , cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeY()))).getSize() );

                                                            
//                                                            cluster.remove(cluster.get(pair[i].getNodeY()));
//                                                            removeCluster.remove(pair[i].getNodeY());
                                                            uf.union(pair[i].getNodeY(), pair[i].getNodeX());
                                                            Clustering.modif(uf, pq, pair, cluster, cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))));

//
//                                                            StdDraw.setPenColor(StdDraw.RED);
//                                                            StdDraw.filledCircle(cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getXcor(), cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getYcor(), scale);
//                                                            scale += 0.002;
//                                                            String str = String.valueOf(cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getIndex());
//                                                            StdDraw.text(cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getXcor(), cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getYcor() + 0.06, str);
//                                                            StdDraw.setPenColor(StdDraw.BLUE);
//                                                             str = String.valueOf(count);
//                                                            StdDraw.text(cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getXcor(), cluster.get(cluster.indexOf(cluster.get(pair[i].getNodeX()))).getYcor(), str);
//                                                            for (int con = 0; con < N; con++) {
//                                                                      System.out.print(""  \"""" + con + ""\"" :"" + uf.find(con) + ""  ."");
//                                                            }
//                                                            System.out.println("""");
//                                                            for (int j = 0; j < cluster.size(); j++) {
//                                                                      if (uf.find(j) == j) {
//                                                                                ArrayList num_temp = new ArrayList();
//                                                                                num_temp = cluster.get(j).getNumber();
//                                                                                System.out.print(""cluster num :\"""" + cluster.get(j).getIndex() + ""\""  cluster size :"" + cluster.get(j).getSize());
//                                                                                for (int k = 0; k < num_temp.size(); k++) {
//                                                                                          System.out.print(""  num_temp: "" + k + "" is "" + num_temp.get(k));
//                                                                                }
//                                                                                System.out.println("""");
//                                                                      }
//                                                            }
//                                                            System.out.println(""combine one cluster"");
                                                            count--;
//                                                            System.out.println("""");
                                                  } //                                                  System.out.println(count);
                                                  else {
                                                            continue;
                                                  }
                                        } else {
                                                  continue;
                                        }
                              }

                    }
//                    System.out.println(count);
                    Cluster[] output_group = new Cluster[count];
                    int output_count = 0;
                    for (int j = 0; j < cluster.size(); j++) {
                              if (uf.find(j) == j) {
                                        output_group[output_count] = cluster.get(j);
                                        output_count++;
//                                        ArrayList num_temp = new ArrayList();
//                                        num_temp = cluster.get(j).getMember();
//                                        System.out.print(""cluster num :\"""" + cluster.get(j).getIndex() + ""\""  cluster size :"" + cluster.get(j).getSize());
//                                        for (int k = 0; k < num_temp.size(); k++) {
//                                                  System.out.print(""  num_temp: "" + k + "" is "" + num_temp.get(k));
//                                        }
//                                        System.out.println("""");
//                                        System.out.println(""X: "" + cluster.get(j).getXcor() + "" Y: "" + cluster.get(j).getYcor());
//                                        
//                                        String X =String.format(""%.2f"",  cluster.get(j).getXcor());
//                                        String Y =String.format(""%.2f"",  cluster.get(j).getYcor());
//                                        System.out.println(""X: "" +X+ "" Y: "" +Y );
                              } else {
                                        continue;
                              }
                    }

                    MergeX.sort(output_group);

                    for (int i = 0; i < output_group.length; i++) {
//                              System.out.print(""cluster num :\"""" + output_group[i].getIndex() + ""\""  cluster size :"" + output_group[i].getSize());
                              String X = String.format(""%.2f"", output_group[i].getXcor());
                              String Y = String.format(""%.2f"", output_group[i].getYcor());
                              System.out.println(output_group[i].getSize() + "" "" + X + "" "" + Y);
                    }

//                    Pair[] output_pair = new Pair[3];
//                    output_count = 0;
//                    for (int i = 0; i < output_group.length; i++) {
//                              for (int j = i + 1; j < output_group.length; j++) {
//                                        output_pair[output_count] = new Pair(output_group[i].getIndex(), output_group[j].getIndex(), distance(output_group[i], output_group[j]));
//                                        output_count++;
//                              }
//                    }
//                    MergeX.sort(output_pair);
//                    System.out.println(output_pair[0].getNodeX() + ""\t"" + output_pair[0].getNodeY()+""\t""+output_pair[0].getDistance());
//                    System.out.println(output_pair[1].getNodeX() + ""\t"" + output_pair[1].getNodeY()+""\t""+output_pair[1].getDistance());
//                    System.out.println(output_pair[2].getNodeX() + ""\t"" + output_pair[2].getNodeY()+""\t""+output_pair[2].getDistance());
                    double output_distance = 1;
                    for (int i = 0; i < output_group.length; i++) {
                              for (int j = i + 1; j < output_group.length; j++) {
                                        ArrayList Cluster_A = output_group[i].getMember();
                                        ArrayList Cluster_B = output_group[j].getMember();
//                                        System.out.println("""");
                                        for (int count_a = 0; count_a < Cluster_A.size(); count_a++) {
                                                  for (int count_b = 0; count_b < Cluster_B.size(); count_b++) {
//                                                              System.out.println(Integer.valueOf(Cluster_A.get(count_a).toString()));
//                                                              Clustering.distance(point_array[Integer.valueOf(Cluster_A.get(count_a).toString())], point_array[Integer.valueOf(Cluster_A.get(count_a).toString())]);

                                                            if (Clustering.distance(point_array[Integer.valueOf(Cluster_A.get(count_a).toString())], point_array[Integer.valueOf(Cluster_B.get(count_b).toString())]) < output_distance) {
                                                                      output_distance = Clustering.distance(point_array[Integer.valueOf(Cluster_A.get(count_a).toString())], point_array[Integer.valueOf(Cluster_B.get(count_b).toString())]);
                                                            } else {
                                                                      continue;
                                                            }
                                                  }
                                        }
                              }
                    }
                    if (count == 1) {
                              System.out.println(""0"");

                    } else {
                              String D = String.format(""%.2f"", output_distance);
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

          public void combine(ArrayList index, double x, double y, int size) {
                    ArrayList temp = new ArrayList(index);

                    for (int i = 0; i < temp.size(); i++) {
                              this.member.add(index.get(i));
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

