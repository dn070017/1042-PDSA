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
//    public static int[] ConvexHullVertex(Point2D[] a) {
//
//        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
//
//    }
//    

          public static Point2D[] cotSort(Point2D[] in) {

                    double[] cotan = new double[in.length];
                    for (int i = 0; i < in.length; i++) {
                              cotan[i] = -(in[i].x() - in[0].x()) / (in[i].y() - in[0].y());
//               System.out.println(cotan[i]);
                    }

                    double temp;
                    Point2D temp2;
                    for (int i = 1; i < cotan.length; i++) {
                              for (int j = i; j > 0; j--) {
                                        if (cotan[j] < cotan[j - 1]) {
                                                  temp = cotan[j];
                                                  cotan[j] = cotan[j - 1];
                                                  cotan[j - 1] = temp;
                                                  temp2 = in[j];
                                                  in[j] = in[j - 1];
                                                  in[j - 1] = temp2;
                                        }
                              }
                    }
                    return in;
          }

          public static double ccwju(Point2D a, Point2D b, Point2D c) {
                    double area2 = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
                    if (area2 < 0) {
                              return -1;
                    } else if (area2 > 0) {
                              return +1;
                    } else {
                              return 0;
                    }
          }

          public static int[] ConvexHullVertex(Point2D[] a) {
//                    int N=a.length
                    MergeX.sort(a, Point2D.Y_ORDER);    //sort
                    cotSort(a);
                    ArrayList<Integer> storge = new ArrayList<Integer>();
                    storge.add(0);
                    storge.add(1);
                    int count_storge = 0;
                    int count_point = 2;
                    while (count_point < a.length) {
                              if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == 1) {

//                                        System.out.println(""check\t"" + count_storge + ""\t"" + (count_storge + 1) + '\t' + (count_point));
                                        storge.add(count_point);
                                        StdDraw.setPenColor(StdDraw.GREEN);
                                        StdDraw.line(a[storge.get(count_storge + 1)].x(), a[storge.get(count_storge + 1)].y(), a[count_point].x(), a[count_point].y());

                                        count_storge++;
                                        count_point++;

                              } else if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == -1) {
                                        StdDraw.setPenColor(StdDraw.RED);
                                        StdDraw.line(a[storge.get(count_storge + 1)].x(), a[storge.get(count_storge + 1)].y(), a[count_point].x(), a[count_point].y());
                                        storge.remove(storge.size() - 1);
                                        count_storge--;
//                                        count_point++;
                              }

                    }
                    int[] output = new int[storge.size()];
                    for (int count = 0; count < storge.size(); count++) {
                              System.out.print(storge.get(count) + "" "");
                              output[count] = storge.get(count);
                    }
                    return output;
          }

          public static void main(String[] args) {
                    // TODO code application logic here
//                    int N = 10;
//                    Point2D[] a = new Point2D[N];
//                    for (int count = 0; count < N; count++) {
//                              a[count] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
////                              System.out.print(a[count].x());
////                              System.out.print(""  "");
////                              System.out.println(a[count].y());
////                              StdDraw.filledCircle(a[count].x(), a[count].y(), 0.01);
//                    }
//
////          System.out.println("""");
////          System.out.println("""");
//                    MergeX.sort(a, Point2D.Y_ORDER);    //sort
//                    cotSort(a);
//
//                    for (int count = N - 1; count > -1; count--) {
////                              System.out.print(a[count].x());
////                              System.out.print(""  "");
////                              System.out.println(a[count].y());
//                              if (count == 0) {
//                                        StdDraw.setPenColor(StdDraw.RED);
//                                        StdDraw.filledCircle(a[count].x(), a[count].y(), 0.01);
////                                        a[count].draw();
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
//
//                    ArrayList<Integer> storge = new ArrayList<Integer>();
//                    storge.add(0);
//                    storge.add(1);
//                    int count_storge = 0;
//                    int count_point = 2;
//                    while (count_point < N) {
//                              if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == 1) {
//
////                                        System.out.println(""check\t"" + count_storge + ""\t"" + (count_storge + 1) + '\t' + (count_point));
//                                        storge.add(count_point);
//                                        StdDraw.setPenColor(StdDraw.GREEN);
//                                        StdDraw.line(a[storge.get(count_storge + 1)].x(), a[storge.get(count_storge + 1)].y(), a[count_point].x(), a[count_point].y());
//
//                                        count_storge++;
//                                        count_point++;
//
//                              } else if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == -1) {
//                                        StdDraw.setPenColor(StdDraw.RED);
//                                        StdDraw.line(a[storge.get(count_storge + 1)].x(), a[storge.get(count_storge + 1)].y(), a[count_point].x(), a[count_point].y());
//                                        storge.remove(storge.size() - 1);
//                                        count_storge--;
////                                        count_point++;
//                              }
//
//                    }
//                    int[] output = new int[storge.size()];
//                    for (int count = 0; count < storge.size(); count++) {
//                              System.out.print(storge.get(count) + "" "");
//                              output[count] = storge.get(count);
//                    }

//                    for (int count = 0; count + 2 < N; count++) {
//                              if (Point2D.ccw(storge[count], storge[count + 1], a[count + 2]) == 1) {
//
//                                        System.out.println(""check\t"" + count + ""\t"" + (count + 1) + '\t' + (count + 2));
//                                        storge[count+2]=count+2;
//                              }
//                              else if (Point2D.ccw(a[count], a[count + 1], a[count + 2]) == -1){
//                                        
//                                        
//                              }
//                        
//                    }
          }

}

