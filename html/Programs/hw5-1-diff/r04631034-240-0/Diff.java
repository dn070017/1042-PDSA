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
                              double dou = a[i].x() * 100+ a[i].y();
                              map.put(dou, i);
//                              System.out.println(map.get(dou));
                    }

                    MergeX.sort(a, Point2D.Y_ORDER);    //sort

                    Point2D p = new Point2D(a[0].x(), a[0].y());
                    MergeX.sort(a, p.POLAR_ORDER);

//                    for (int count = 0; count < a.length; count++) {
////                              System.out.print(a[count].x());
////                              System.out.print(""  "");
////                              System.out.println(a[count].y());
//                              if (count == 0) {
//                                        StdDraw.setPenColor(StdDraw.RED);
//                                        StdDraw.filledCircle(a[count].x(), a[count].y(), 0.01);
//                                        String str = String.valueOf(count);
//                                        StdDraw.text(a[count].x(), a[count].y() + 0.03, str);
//                              } else {
//                                        StdDraw.setPenColor(StdDraw.BLACK);
//                                        StdDraw.filledCircle(a[count].x(), a[count].y(), 0.01);
//                                        String str = String.valueOf(count);
//                                        StdDraw.text(a[count].x(), a[count].y() + 0.03, str);
//                                        StdDraw.setPenColor(StdDraw.YELLOW);
//                                        StdDraw.line(a[count].x(), a[count].y(), a[0].x(), a[0].y());
//                              }
//                    }
                    ArrayList<Integer> storge = new ArrayList<Integer>();
                    storge.add(0);
                    storge.add(1);
                    int count_storge = 0;
                    int count_point = 2;
                    while (count_point < a.length) {
                              if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) != -1) {
                                        storge.add(count_point);
//                                        StdDraw.setPenColor(StdDraw.GREEN);
//                                        StdDraw.line(a[storge.get(count_storge + 1)].x(), a[storge.get(count_storge + 1)].y(), a[count_point].x(), a[count_point].y());
                                        count_storge++;
                                        count_point++;
                              } else if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == -1) {
//                                        StdDraw.setPenColor(StdDraw.RED);
//                                        StdDraw.line(a[storge.get(count_storge + 1)].x(), a[storge.get(count_storge + 1)].y(), a[count_point].x(), a[count_point].y());
                                        storge.remove(storge.size() - 1);
                                        count_storge--;
                              }

                    }
                    int[] output = new int[storge.size()];
                    for (int count = 0; count < storge.size(); count++) {
                              double dou = a[storge.get(count)].y() + a[storge.get(count)].x() * 100;
//                              output[count] = map.get(a[storge.get(count)].y());
                              output[count] = map.get(dou);
//                              System.out.print(""y            "" +a[storge.get(count)].y()+""           "");
                              System.out.print(storge.get(count) + "" "");
//                              System.out.println( map.get(a[storge.get(count)].y()));
//                              System.out.println("""");
                    }
                    System.out.println("""");
                    return output;
          }

          public static void main(String[] args) {
//                     TODO code application logic here
//                    int N = 10;
//                    Point2D[] a = new Point2D[N];
//                    for (int count = 0; count < N; count++) {
//                              a[count] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
////                              System.out.println(a[count]);
//                    }
////
////                    a[0] = new Point2D(0.4, 0.3);
////                    a[1] = new Point2D(0.5, 0.5);
////                    a[2] = new Point2D(0.1, 0.1);
////                    a[3] = new Point2D(0.5, 0.1);
////                    a[4]= new Point2D(0.9,0.1);
//                    int[] output;
//                    output = MyConvexHull.ConvexHullVertex(a);
//
//                    for (int i = 0; i < output.length; i++) {
//                              System.out.print(output[i] + "" "");
//                    }
//
//                    
//                    
//                    
//                    System.out.println("""");
          }

}

