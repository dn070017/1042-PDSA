/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.*;
//import edu.princeton.cs.algs4.*;

/**
 *
 * @author user
 */
public class MyConvexHull {

         public static int[] ConvexHullVertex(Point2D[] a) {
                    HashMap<Double, Integer> map = new HashMap<Double, Integer>();
                    for (int i = 0; i < a.length; i++) {
                              map.put((a[i].x()*100+a[i].y()), i);
                    }
                    MergeX.sort(a, Point2D.Y_ORDER);    //sort
                    Point2D p = new Point2D(a[0].x(),a[0].y());
                    MergeX.sort(a,p.POLAR_ORDER);
                    ArrayList<Integer> storge = new ArrayList<Integer>();
                    storge.add(0);
                    storge.add(1);
                    int count_storge = 0;
                    int count_point = 2;
                    while (count_point < a.length) {
                              if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) != -1) {
                                        storge.add(count_point);
                                        count_storge++;
                                        count_point++;
                              } else if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == -1) {
                                        storge.remove(storge.size() - 1);
                                        count_storge--;
                              }

                    }
                    int[] output = new int[storge.size()];
                    for (int count = 0; count < storge.size(); count++) {
                              output[count] = map.get((a[storge.get(count)].x()*100)+a[storge.get(count)].y());
                    }
                    return output;
          }

          public static void main(String[] args) {
//                     TODO code application logic here
                 In in = new In(args[0]);
          double distance = Double.valueOf(in.readLine());
//          System.out.println(distance);
          int N = Integer.valueOf(in.readLine());
//          System.out.println(N);
          Point2D[] point_array = new Point2D[N];
          UF uf = new UF(N);
          String line;
          int input_count = 0;
          while ((line = in.readLine()) != null) {
               point_array[input_count] = new Point2D(Double.valueOf(line.split("" "")[0]), Double.valueOf(line.split("" "")[1]));
               input_count++;
          }

          for (int indx = 0; indx < N; indx++) {
               for (int indy = indx + 1; indy < N; indy++) {
                    if (point_array[indx].distanceTo(point_array[indy]) <= distance) {
                         uf.union(indx, indy);
                    }
               }
          }

//          for (int i = 0; i < N; i++) {
//               System.out.println(uf.find(i));
//               StdDraw.setPenColor(StdDraw.BLACK);
//               StdDraw.filledCircle(point_array[i].x(), point_array[i].y(), 0.01);
//               String str = String.valueOf(uf.find(i));
//               StdDraw.text(point_array[i].x(), point_array[i].y() + 0.03, str);
//          }
          int[] cc_count = new int[N];
          for (int i = 0; i < N; i++) {
               cc_count[uf.find(i)] += 1;
          }
          int output=0;
          for (int i = 0; i < N; i++) {
               if (cc_count[i] < 3) {
                    continue;
               } else {
                    Point2D[] cc_array = new Point2D[cc_count[i]];
                    int count = 0;
                    for (int j = 0; j < N; j++) {
                         if (uf.find(j) == i) {
                              cc_array[count] = new Point2D(point_array[j].x(), point_array[j].y());
                              count++;
                         }
                    }
                    output+=MyConvexHull.ConvexHullVertex(cc_array).length;
               }
          }
          System.out.println(output);
//                    System.out.println("""");
          }

}

