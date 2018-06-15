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

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = new String[3];
            data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            int t_row = Integer.parseInt(data[1]);      // target row
            int t_col = Integer.parseInt(data[2]);      // targer column

            int matrix[][] = new int[num + 2][num + 2];

            String[] lines = new String[2];
            
            QuickUnionUF uf = new QuickUnionUF(num*num);

            for (int i = 1; i <= num; i++) {
                for (int j = 1; j <= num; j++) {
                    matrix[i][j] = 1;
                }
            }

            while (br.ready()) {
                lines = br.readLine().split("","");
                int row = Integer.parseInt(lines[0]);
                int col = Integer.parseInt(lines[1]);
                matrix[row][col] = 0;                       //block 標為0
            }

            int count = 1;                                  //紀??label

            for (int i = 1; i <= num; i++) {

                for (int j = 1; j <= num; j++) {

                    if (matrix[i][j] == 0) {
                        continue;
                    }
                    //??上、左 ?��??�Block
                    else if (matrix[i - 1][j] != 0 && matrix[i][j - 1] != 0) {
                        // �?< �?
                        if (matrix[i][j - 1] < matrix[i - 1][j]) {
                            matrix[i][j] = matrix[i][j-1];
                            uf.union(matrix[i - 1][j] , matrix[i][j - 1]);
                        }
                        // �?> �?
                        else if (matrix[i][j - 1] > matrix[i - 1][j]) {
                            matrix[i][j] = matrix[i - 1][j];
                            uf.union(matrix[i][j - 1] , matrix[i - 1][j]);
                        }
                        // �?== �? ?��?給左?��??��?
                        else {
                            matrix[i][j] = matrix[i][j - 1];
                        }

                    }
                    //�?�?�??�是Block --> label
                    else if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                        matrix[i][j] = count;
                        count++;
                    }
                    //上面?��?--> ??= 上面
                    else if (matrix[i - 1][j] == 0 && matrix[i][j - 1] != 0) {
                        matrix[i][j] = matrix[i][j - 1];
                    }
                    //左�??��?--> ??= 左�?
                    else {
                        matrix[i][j] = matrix[i - 1][j];
                    }

                }
            }

            for (int i = 1; i <= num; i++) {
                for(int j = 1; j <= num; j ++){
                    if (matrix[i][j] != 0) {
                    matrix[i][j] = uf.find(matrix[i][j]);
                    }
                }
            }

            System.out.println(matrix[t_row][t_col]);

        }
    }
}

