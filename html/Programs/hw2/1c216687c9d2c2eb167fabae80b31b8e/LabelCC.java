//package labelcc;

//import edu.princeton.cs.algs4.QuickFindUF; // mask
//import edu.princeton.cs.algs4.UF; // mask
import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) { //args[0]
            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            int announce_v = Integer.parseInt(data[1]);
            int announce_h = Integer.parseInt(data[2]);
            //System.out.format(""%d\n"", num);
            //System.out.format(""%d\n"", announce_v);
            //System.out.format(""%d\n"", announce_h);
            int[][] matrix = new int[num][num];
            int[][] matrix_label = new int[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = 1;
                }
            }
            //for (int x = 0; x < matrix.length; x++) {
            //    for (int y = 0; y < matrix[0].length; y++) {
            //        System.out.print(matrix[x][y] + "" "");
            //   }
            //    System.out.print(""\n"");
            //}
            String block = new String();
            for (int i = 0; i < num * num; i++) {
                if ((block = br.readLine()) == null) {
                    break;
                }
                String[] b = block.split("","");
                int b_v = Integer.parseInt(b[0]);
                int b_h = Integer.parseInt(b[1]);
                matrix[b_v - 1][b_h - 1] = 0;
            }
            //for (int x = 0; x < matrix.length; x++) {
            //    for (int y = 0; y < matrix[0].length; y++) {
            //        System.out.print(matrix[x][y] + "" "");
            //    }
            //    System.out.print(""\n"");
            //}
            QuickFindUF uf = new QuickFindUF(num * num);
            int lab = 0;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (matrix[i][j] == 1) {
                        if (i == 0) {
                            if (j == 0 || matrix[i][j - 1] == 0) {
                                lab = lab + 1;
                                matrix_label[i][j] = lab;
                            } else {
                                matrix_label[i][j] = matrix_label[i][j - 1];
                            }
                        } else if (matrix[i - 1][j] == 1) {
                            if (j == 0 || matrix[i][j - 1] == 0) {
                                matrix_label[i][j] = matrix_label[i - 1][j];
                            } else if (matrix[i][j - 1] == 1) {
                                if (matrix_label[i - 1][j] == matrix_label[i][j - 1]) {/////have to union
                                    matrix_label[i][j] = matrix_label[i][j - 1];
                                } else if (matrix_label[i - 1][j] < matrix_label[i][j - 1]) {
                                    matrix_label[i][j] = matrix_label[i - 1][j];
                                    if (!uf.connected(matrix_label[i][j - 1], matrix_label[i - 1][j])) {
                                        uf.union(matrix_label[i][j - 1], matrix_label[i - 1][j]);
                                    }
                                } else if (matrix_label[i - 1][j] > matrix_label[i][j - 1]) {
                                    matrix_label[i][j] = matrix_label[i][j - 1];
                                    if (!uf.connected(matrix_label[i - 1][j],matrix_label[i][j - 1])) {
                                        uf.union(matrix_label[i - 1][j],matrix_label[i][j - 1]);
                                    }
                                }
                            }
                        } else if (matrix[i - 1][j] == 0) {
                            if (j == 0 || matrix[i][j - 1] == 0) {
                                lab = lab + 1;
                                matrix_label[i][j] = lab;
                            } else if (matrix[i][j - 1] == 1) {
                                matrix_label[i][j] = matrix_label[i][j - 1];
                            }
                        }
                    }
                }
            }
            //for (int x = 0; x < matrix_label.length; x++) {
            //    for (int y = 0; y < matrix_label[0].length; y++) {
            //        System.out.print(matrix_label[x][y] + "" "");
            //    }
            //    System.out.print(""\n"");
            //}
            System.out.printf(""%s\n"", uf.find(matrix_label[announce_v-1][announce_h-1]));
        }
    }
}

