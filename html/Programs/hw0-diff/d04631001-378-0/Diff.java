
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {
            String[] data = br.readLine().split("","");
            int stringCount = Integer.parseInt(data[0]);
            int num = Integer.parseInt(data[1]);
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            announce = br.readLine().split("","");
            String[] temp = new String[num];
            for (int x = 0; x < matrix[0].length; x++) {
                temp = br.readLine().split("","");
                for (int y = 0; y < matrix.length; y++) {
                    matrix[x][y] = temp[y];
                }
            }
            int[][] matrix_a = new int[matrix[0].length][matrix.length];
            int[][] matrix_s = new int[matrix[0].length][matrix.length];
            for (int a = 0; a < matrix[0].length; a++) {
                for (int b = 0; b < matrix.length; b++) {
                    for (int k = 0; k < announce.length; k++) {
                        if (matrix[a][b].equals(announce[k])) {
                            matrix_a[a][b] = 1;
                        } else {
                            matrix_a[a][b] = 0;
                        }
                        matrix_s[a][b] = matrix_s[a][b] + matrix_a[a][b];
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

