
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int stringCount = Integer.parseInt(data[0]);
            int num = Integer.parseInt(data[1]);
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            announce = br.readLine().split("","");
            String[] temp = new String[num];
            for (int i = 0; i < matrix[0].length; i++) {
                temp = br.readLine().split("","");
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = temp[j];
                }
            }
            int[][] matrix_a = new int[matrix[0].length][matrix.length];
            int[][] matrix_s = new int[matrix[0].length][matrix.length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    for (int k = 0; k < announce.length; k++) {
                        if (matrix[i][j].equals(announce[k])) {
                            matrix_a[i][j] = 1;
                        } else {
                            matrix_a[i][j] = 0;
                        }
                        matrix_s[i][j] = matrix_s[i][j] + matrix_a[i][j];
                    }
                }
            }
            int[] sum_h = new int[matrix.length];
            int[] sum_v = new int[matrix.length];
            int sum_l = 0;
            int sum_r = 0;
            int bingo = 0;
            for (int i = 0; i < matrix_s.length; i++) {
                for (int j = 0; j < matrix_s.length; j++) {
                    sum_h[i] = sum_h[i] + matrix_s[i][j];
                    {
                        if (sum_h[i] == matrix_s.length) {
                            bingo = bingo + 1;
                        }
                    }
                    sum_v[i] = sum_v[i] + matrix_s[j][i];
                    {
                        if (sum_v[i] == matrix_s.length) {
                            bingo = bingo + 1;
                        }
                    }
                }
                sum_l = sum_l + matrix_s[i][matrix_s.length - i - 1];
                {
                    if (sum_l == matrix_s.length) {
                        bingo = bingo + 1;
                    }
                }
                sum_r = sum_r + matrix_s[i][i];
                {
                    if (sum_r == matrix_s.length) {
                        bingo = bingo + 1;
                    }
                }
            }
            String bingo2 = String.valueOf(bingo);
            System.out.printf(""%s\n"", bingo2);
        }
    }
}

