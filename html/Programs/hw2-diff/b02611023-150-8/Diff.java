/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.io.*;

public class LabelCC {

    public static int[] sec_pass;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = new String[3];
            data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            int t_row = Integer.parseInt(data[1]);
            int t_col = Integer.parseInt(data[2]);
            int cc[] = new int[num * num];
            int matrix[][] = new int[num + 2][num + 2];
            String line;
            String[] lines = new String[2];
            sec_pass = new int[num * num + 1];

            for (int i = 1; i <= num; i++) {
                for (int j = 1; j <= num; j++) {
                    cc[num * (i - 1) + (j - 1)] = num * (i - 1) + (j - 1);
                    sec_pass[num * (i - 1) + (j - 1)] = num * (i - 1) + (j - 1);
                    matrix[i][j] = 1;
                }
            }

            line = br.readLine();
            while (line != null) {
                lines = line.split("","");
                int row = Integer.parseInt(lines[0]);
                int col = Integer.parseInt(lines[1]);
                cc[num * (row - 1) + (col - 1)] = 0;
                matrix[row][col] = 0;
                line = br.readLine();
            }

            int count = 1;

            for (int i = 1; i <= num; i++) {

                for (int j = 1; j <= num; j++) {

                    int label = num * (i - 1) + (j - 1);

                    if (matrix[i][j] == 0) {
                        continue;
                    } else if ((matrix[i][j] - matrix[i - 1][j] == 0) && (matrix[i][j] - matrix[i][j - 1] == 0)) {
                        if (cc[num * (i - 2) + (j - 1)] < cc[num * (i - 1) + (j - 2)]) {
                            cc[label] = cc[num * (i - 2) + (j - 1)];
                            sec_pass[cc[num * (i - 1) + (j - 2)]] = cc[num * (i - 2) + (j - 1)];
                        } else if (cc[num * (i - 2) + (j - 1)] > cc[num * (i - 1) + (j - 2)]) {
                            cc[label] = cc[num * (i - 1) + (j - 2)];
                            sec_pass[cc[num * (i - 2) + (j - 1)]] = cc[num * (i - 1) + (j - 2)];
                        } else {
                            cc[label] = cc[num * (i - 2) + (j - 1)];
                        }
                    } else if ((matrix[i][j] - matrix[i - 1][j] == 1) && (matrix[i][j] - matrix[i][j - 1] == 1)) {
                        cc[label] = count;
                        count++;
                    } else if (matrix[i][j] - matrix[i - 1][j] == 0 && (matrix[i][j] - matrix[i][j - 1] == 1)) {
                        cc[label] = cc[num * (i - 2) + (j - 1)];
                    } else {
                        cc[label] = cc[num * (i - 1) + (j - 2)];
                    }

                }
            }

            for (int i = 0; i < num * num; i++) {
                if (cc[i] != 0 && sec_pass [i] != i) {
                    cc[i] = find(cc[i]);
                }
            }

            System.out.println(cc[num*(t_row-1)+(t_col-1)]);
            /*for(int i = 0; i < num*num; i++){
             System.out.println(sec_pass[i]);
             }*/
            /*for (int i = 1; i < num + 1; i++) {
                for (int j = 1; j < num + 1; j++) {
                    System.out.print(cc[num * (i - 1) + (j - 1)] + "" "");
                }
                System.out.println();

            }*/

        }
    }

    public static int find(int p) {
        while (p != sec_pass[p]) {
            p = sec_pass[p];
        }
        return p;
    }

}

