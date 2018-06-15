/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.util.Arrays;
import java.util.Comparator;

public class MyConvexHull {
//
    public static int[] ConvexHullVertex(Point2D[] A) {
        Point2D[] origin = A.clone();
        Arrays.sort(A, Point2D.Y_ORDER);
        Point2D p = new Point2D(A[0].x(), A[0].y());

        Arrays.sort(A, p.POLAR_ORDER);

        Point2D a = new Point2D(0.0, 0.0);
        Point2D b = a;
        Point2D c = a;
        int count = 0;
        int n_count = 0;
        int k0 = 0;
        Point2D[] point = new Point2D[A.length+1];
        for (int i = 0; i < A.length; i++) {
            point[i] = A[i];
        }
        point[A.length] = A[0];
        for (int i = 2; i < A.length+1; i++) {
            for (int j = 0; j <= i - 2; j++) {
                for (int k = j + 1; k <= i; k++) {
                    if (A[j] != null) {
                        a = new Point2D(A[j].x(), A[j].y());
                        if (A[k] != null) {
                            if (count <= 0) {
                                b = new Point2D(A[k].x(), A[k].y());
                                count++;
                                k0 = k;
                            } else {
                                c = new Point2D(A[k].x(), A[k].y());
                                count++;
                            }
                        }
                    }
                    if (count == 2 && Point2D.ccw(a, b, c) <= 0) {
                        if (j == 0) {
                            A[k] = null;
                            n_count++;
                        } else {
                            A[k0] = null;
                            n_count = n_count + 2;
                        }
                    }
                }
                count = 0;
            }

        }

        int[] index = new int[A.length - n_count];
        int l = 0;
        for (int m = 0; m < A.length; m++) {
            for (int n = 0; n < A.length; n++) {
                if (point[m] != null) {
                    if (point[m] == origin[n]) {
                        index[l] = n;
                        l++;
                        break;
                    }
                }
                if (l == (index.length - 1)) {
                    break;
                }
            }
        }
        Arrays.sort(index);
        return index;
    }
}

