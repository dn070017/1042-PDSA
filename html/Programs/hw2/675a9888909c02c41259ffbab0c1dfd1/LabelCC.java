/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author user
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("","");

            int gridsize = Integer.parseInt(data[0]);

            int targetx = Integer.parseInt(data[1]);
            int targety = Integer.parseInt(data[2]);
            int[][] matrix = new int[gridsize][gridsize];
            for (int i = 0; i < gridsize; i++) {
                for (int j = 0; j < gridsize; j++) {
                    matrix[i][j] = 1;
                }
            }
            QuickFindUF uf = new QuickFindUF(gridsize * gridsize);
            String str = null;
            int[] id = new int[gridsize * gridsize];
            while ((str = br.readLine()) != null) {
                String tempArray[] = str.split("","");
                int x = Integer.parseInt(tempArray[0]);
                int y = Integer.parseInt(tempArray[1]);
                matrix[x - 1][y - 1] = 0;
            }
            for (int i = 0; i < gridsize * gridsize; i++) {
                id[i] = i;
            }
            int labelcount = 1;
            for (int i = 0; i < gridsize; i++) {
                for (int j = 0; j < gridsize; j++) {
                    int x = i + 1;
                    int y = j + 1;
                    int gx = i;
                    int gy = j;
                    if (matrix[gx][gy] == 1) {

                        if (x > 1 && y > 1) {
                            if (matrix[gx - 1][gy] != 0 && matrix[gx][gy - 1] == 0) {
                                matrix[gx][gy] = matrix[gx - 1][gy];
                            }
                            if (matrix[gx][gy - 1] != 0 && matrix[gx - 1][gy] == 0) {
                                matrix[gx][gy] = matrix[gx][gy - 1];
                            }
                            if (matrix[gx - 1][gy] != 0 && matrix[gx][gy - 1] != 0) {
                                int up = matrix[gx - 1][gy];
                                int left = matrix[gx][gy - 1];
                                if (up <= left) {
                                    matrix[gx][gy] = matrix[gx - 1][gy];
                                    uf.union(matrix[gx][gy - 1], matrix[gx - 1][gy]);
                                    id[left] = up;
                                }
                                if (up > left) {
                                    uf.union(matrix[gx - 1][gy], matrix[gx][gy - 1]);
                                    id[up] = left;
                                }
//                                if (up == left) {
//                                    matrix[gx][gy] = matrix[gx - 1][gy];
//                                }
                            }
                            if (matrix[gx][gy - 1] == 0 && matrix[gx - 1][gy] == 0) {
                                matrix[gx][gy] = labelcount;
                                labelcount++;
                            }

                        } else if (x > 1 && y == 1) {
                            if (matrix[gx - 1][gy] != 0) {
                                matrix[gx][gy] = matrix[gx - 1][gy];
                            } else {
                                matrix[gx][gy] = labelcount;
                                labelcount++;
                            }

                        } else if (y > 1 && x == 1) {
                            if (matrix[gx][gy - 1] != 0) {
                                matrix[gx][gy] = matrix[gx][gy - 1];
                            } else {
                                matrix[gx][gy] = labelcount;
                                labelcount++;
                            }
                        } else {
                            matrix[gx][gy] = labelcount;
                            labelcount++;
                        }
                    }
                }
            }

            for (int i = 0; i < gridsize; i++) {
                for (int j = 0; j < gridsize; j++) {
                    if (matrix[i][j] != 0 && matrix[i][j] != uf.find(matrix[i][j])) {
                        matrix[i][j] = uf.find(matrix[i][j]);
                    }
                }
            }
            int x = matrix[targetx - 1][targety - 1];
//            while (id[x] != x) {
//                x = id[x];
//            }
            System.out.printf("""" + x);

        }
    }

}

