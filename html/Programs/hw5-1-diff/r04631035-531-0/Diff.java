/*
.
 * To change this template file, choose Tools | Templates
.
 */

import edu.princeton.cs.algs4.Point2D;
import java.util.*;
import java.math.*;
import java.awt.Color;

/**
 *
 * @author cypan
 */
public class MyConvexHull {

    public double ConvexHullVertex(Point2D[] a) {

//        double i = StdRandom.uniform(0, 1);
//        System.out.println(i);
        
        Point2D.ccw(a[0], a[1], a[2]);
        
        return 0;
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

    private static void insertionSort(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            double valueToSort = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > valueToSort) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = valueToSort;
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
        // TODO code application logic here
        int num = StdRandom.uniform(5, 10);
        double[][] Point2D = new double[num][3];

        System.out.println(num);

        for (int i = 0; i < num; i++) {
            double y = StdRandom.uniform(0, 1000);
            double angle = StdRandom.uniform(0, 1000);
            Point2D[i][0] = y / 1000;
            Point2D[i][1] = angle / 1000;
            StdDraw.circle(Point2D[i][0], Point2D[i][1], 0.01);
//            System.out.println(Point2D[i][0]);
//            System.out.println(Point2D[i][1]);
        }

        insertionSort_Point(Point2D, 1); //找y軸最低
        StdDraw.setPenColor(new Color(255, 0, 0));   // red
        StdDraw.circle(Point2D[0][0], Point2D[0][1], 0.01);

        Point2D[0][2] = 0;
        for (int i = 1; i < Point2D.length; i++) {

            double deg = (double) Math.toDegrees(Math.atan2(Point2D[i][0] - Point2D[0][0], Point2D[i][1] - Point2D[0][1]));
            if (deg > 0) {
                Point2D[i][2] = deg;
            } else if (deg < 0) {
                deg = 180 + deg;
                Point2D[i][2] = deg;
            }
        }

        insertionSort_Point(Point2D, 2);

        System.out.println(Point2D[0][2]);
        System.out.println(Point2D[1][2]);
        System.out.println(Point2D[2][2]);
        System.out.println(Point2D[3][2]);
        System.out.println(Point2D[4][2]);

    }

}

