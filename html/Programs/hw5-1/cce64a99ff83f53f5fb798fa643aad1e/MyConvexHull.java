/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.util.*;
import java.math.*;
import java.awt.Color;

/**
 *
 * @author cypan
 */
public class MyConvexHull {

     public static int[] ConvexHullVertex(Point2D[] a) {
                    HashMap<Double, Integer> map = new HashMap<Double, Integer>();
                    for (int i = 0; i < a.length; i++) {
                              map.put((a[i].x() * 100 + a[i].y()), i);
                    }
                    MergeX.sort(a, Point2D.Y_ORDER);    //sort
                    Point2D p = new Point2D(a[0].x(), a[0].y());
                    MergeX.sort(a, p.POLAR_ORDER);
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
                              output[count] = map.get((a[storge.get(count)].x() * 100) + a[storge.get(count)].y());
                    }
                    return output;
          }



    private static void insertionSort_Point(double[][] arr, int col) {

        for (int i = 1; i < arr.length; i++) {
            int col1 = 0;
            int col2 = 0;
            if (col == 0) {
                col1 = 1;
                col2 = 2;
            } else if (col == 1) {
                col1 = 0;
                col2 = 2;
            } else if (col == 2) {
                col1 = 0;
                col2 = 1;
            }
            double valueToSort = arr[i][col];
            double valueNoSort = arr[i][col1];
            double valueNoSort1 = arr[i][col2];
            int j = i;
            while (j > 0 && arr[j - 1][col] > valueToSort) {
                arr[j][col] = arr[j - 1][col];
                arr[j][col1] = arr[j - 1][col1];
                arr[j][col2] = arr[j - 1][col2];
                j--;
            }
            arr[j][col] = valueToSort;
            arr[j][col1] = valueNoSort; //綁定
            arr[j][col2] = valueNoSort1; //綁定

        }
    }

    private static void insertionSort(Point2D[] arr) {
        for (int i = 1; i < arr.length; i++) {
            double valueNoSort = arr[i].x();
            double valueToSort = arr[i].y();
            int j = i;
            while (j > 0 && arr[j - 1].y() > valueToSort) {
                arr[j] = new Point2D(arr[j - 1].x(),arr[j - 1].y());
                j--;
            }
            
            arr[j]= new Point2D(valueNoSort,valueToSort);
            
        }
    }

    public static void printArray(double[][] B, int col) {
        System.out.println(Arrays.toString(B));
    }

    public static void populateArray(int[] B) {
        for (int i = 0; i < B.length; i++) {
            B[i] = (int) (Math.random() * 100);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int num = StdRandom.uniform(5, 10);
        //double[][] Point2D = new double[num][3];
        Point2D[] A = new Point2D[num];
        int[] f = ConvexHullVertex(A);
        
        System.out.println(A);

        
        for (int i = 0; i < num; i++) {
           
            double x = StdRandom.uniform(0, 1000);
            double y = StdRandom.uniform(0, 1000);
            A[i]=new Point2D(x/1000 ,y/1000);
            StdDraw.circle(A[i].x(), A[i].y(), 0.01);
        }
        MergeX.sort(A, Point2D.Y_ORDER); //哪來的
        //insertionSort(A); //找y軸最低
        StdDraw.setPenColor(new Color(255, 0, 0));   // red
        StdDraw.circle(A[0].x(), A[0].y(), 0.01);
        
        System.out.println(A[0].x());
        System.out.println(A[0].y());
        System.out.println(A[1].x());
        System.out.println(A[1].y());
        System.out.println(A[2].x());
        System.out.println(A[2].y());
        
        MergeX.sort(A, A[0].polarOrder());
        
        for (int i = 1; i < A.length; i++) {
            
            Comparator<Point2D> a = A[i].polarOrder();
            
//            System.out.println();
//            double deg = (double) Math.toDegrees(Math.atan2(A[i].x() - A[0].x(), A[i].y() - A[0].y()));
//            if (deg > 0) {
//                Point2D[i][2] = deg;
//                
//                
//            } else if (deg < 0) {
//                deg = 180 + deg;
//                Point2D[i][2] = deg;
//            }
        }

//        insertionSort_Point(Point2D, 2);

//        System.out.println(Point2D[0][2]);
//        System.out.println(Point2D[1][2]);
//        System.out.println(Point2D[2][2]);
//        System.out.println(Point2D[3][2]);
//        System.out.println(Point2D[4][2]);

    }

}

