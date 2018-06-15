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
            int t_row = Integer.parseInt(data[1]);      // target row
            int t_col = Integer.parseInt(data[2]);      // targer column
            
            int cc[] = new int[num * num];
            int matrix[][] = new int[num + 2][num + 2];

            String[] lines = new String[2];
            sec_pass = new int[num * num + 1];

            for (int i = 1; i <= num; i++) {
                for (int j = 1; j <= num; j++) {
                    cc[num * (i - 1) + (j - 1)] = num * (i - 1) + (j - 1);  //標上 0~(N-1)
                    matrix[i][j] = 1;
                }
            }

            for (int i = 0; i < num * num + 1; i++) {
                sec_pass[i] = i;
            }

            while (br.ready()) {
                lines = br.readLine().split("","");
                int row = Integer.parseInt(lines[0]);
                int col = Integer.parseInt(lines[1]);
                cc[num * (row - 1) + (col - 1)] = 0;        //block 標為0
                matrix[row][col] = 0;                       //block 標為0
            }

            int count = 1;                                  //紀錄 label

            for (int i = 1; i <= num; i++) {

                for (int j = 1; j <= num; j++) {

                    int position = num * (i - 1) + (j - 1);

                    if (matrix[i][j] == 0) {
                        continue;
                    } 
                    //找 上、左 皆不是Block
                    else if ((matrix[i][j] - matrix[i - 1][j] == 0) && (matrix[i][j] - matrix[i][j - 1] == 0)) {
                        // 上 < 左
                        if (cc[num * (i - 2) + (j - 1)] < cc[num * (i - 1) + (j - 2)]) {
                            cc[position] = cc[num * (i - 2) + (j - 1)];
                            union(cc[num * (i - 1) + (j - 2)], cc[position]);
                        } 
                        // 左 > 上
                        else if (cc[num * (i - 2) + (j - 1)] > cc[num * (i - 1) + (j - 2)]) {
                            cc[position] = cc[num * (i - 1) + (j - 2)];
                            union(cc[num * (i - 2) + (j - 1)], cc[position]);
                        } 
                        // 左 == 上  隨意給左或上的值
                        else {
                            cc[position] = cc[num * (i - 2) + (j - 1)];
                        }
                        
                    } 
                    //上 跟 左 都是Block --> label
                    else if ((matrix[i][j] - matrix[i - 1][j] == 1) && (matrix[i][j] - matrix[i][j - 1] == 1)) {
                        cc[position] = count;
                        count++;
                    } 
                    //左邊有值 --> 值 = 左邊
                    else if (matrix[i][j] - matrix[i - 1][j] == 0 && (matrix[i][j] - matrix[i][j - 1] == 1)) {
                        cc[position] = cc[num * (i - 2) + (j - 1)];
                    } 
                    //上面有值 --> 值 = 上面
                    else {
                        cc[position] = cc[num * (i - 1) + (j - 2)];
                    }

                }
            }

            for (int i = 0; i < num * num; i++) {
                if (cc[i] != 0) {
                    cc[i] = find(cc[i]);
                }
            }

            System.out.println(cc[num * (t_row - 1) + (t_col - 1)]);

        }
    }

    public static int find(int p) {
        while (p != sec_pass[p]) {
            p = sec_pass[p];
        }
        return p;
    }

    public static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        sec_pass[rootP] = rootQ;
    }

}

