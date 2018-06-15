
import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader((args[0])))) {
            String[] data = br.readLine().split("","");

            int num = Integer.parseInt(data[0]);

            int[][] matrix = new int[num + 1][num + 1];
            int target_row = Integer.parseInt(data[1]);
            int target_col = Integer.parseInt(data[2]);
            // System.out.printf(""%d %d %d"", num, target_row, target_col);
            // creat a zero- matrix with bigger size(one more row and col)
            for (int i = 0; i < num + 1; i++) {
                for (int j = 0; j < num + 1; j++) {
                    matrix[i][j] = 0;
                }
            }
            //creat a one-matrix
            for (int i = 1; i < num + 1; i++) {
                for (int j = 1; j < num + 1; j++) {
                    matrix[i][j] = 1;
                }
            }
            //the bolcked site is zero
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    System.out.print(""-1"");
                    break;
                }
                String[] readnum = line.split("","");
                int row = Integer.parseInt(readnum[0]);
                int col = Integer.parseInt(readnum[1]);
                matrix[row][col] = 0;
            }
            // first run
            int initial = 0;
            QuickUnionUF uf = new QuickUnionUF(num * num + 4);
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[i][j] != 0) {
                        if (matrix[i][j - 1] == 0 && matrix[i - 1][j] == 0) {
                            initial = initial + 1;
                            matrix[i][j] = initial;
                        } else if (matrix[i][j - 1] == 0 && matrix[i - 1][j] != 0) {
                            matrix[i][j] = matrix[i - 1][j];
                        } else if (matrix[i][j - 1] != 0 && matrix[i - 1][j] == 0) {
                            matrix[i][j] = matrix[i][j - 1];
                        } else {
                            int a = matrix[i][j - 1];
                            int b = matrix[i - 1][j];
                            if (a > b) {
                                matrix[i][j] = b;
                                uf.union(a, b);
                            } else {
                                matrix[i][j] = a;
                                uf.union(b, a);
                            }

                        }
                    }
                }
            }

            // print rhe matrix
            int kkk = matrix[target_row][target_col];
            int rooo;
            rooo = uf.find(kkk);
            System.out.print(rooo);
//            for (int i = 0; i < matrix.length; i++) {
//                for (int j = 0; j < matrix[0].length; j++) {
//                    System.out.print(matrix[i][j] + "" "");
//                }
//                System.out.print(""\n"");
//            }
        }
    }
}

