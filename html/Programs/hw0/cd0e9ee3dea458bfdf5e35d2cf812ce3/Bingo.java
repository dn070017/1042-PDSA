
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
            //System.out.printf(""announced: %s %s \n"",announce[0], announce[1]);

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
                        } 
                        else {
                            matrix_a[i][j] = 0;
                        }
                        matrix_s[i][j] = matrix_s[i][j] + matrix_a[i][j];
                    }
                    //System.out.print(matrix_s[i][j] + ""\t"");
                }
                //System.out.println();
            }
            int[] sum_h = new int[matrix.length];
            int bingo_h = 0;
            int[] sum_v = new int[matrix.length];
            int bingo_v = 0;
            int[] sum_l = new int[matrix.length];
            int bingo_l = 0;
            int[] sum_r = new int[matrix.length];
            int bingo_r = 0;
            // horizontal addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                for (int j = 0; j < matrix_s.length; j++) {
                    sum_h[i] = sum_h[i] + matrix_s[i][j];
                    {
                        if (sum_h[i] == matrix_s[0].length) {
                            bingo_h = bingo_h + 1;
                        }
                    }
                }
            }
            // vertical addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                for (int j = 0; j < matrix_s.length; j++) {
                    sum_v[i] = sum_v[i] + matrix_s[j][i];
                    {
                        if (sum_v[i] == matrix_s[0].length) {
                            bingo_v = bingo_v + 1;
                        }
                    }
                }
            }
            // 45 degree-cross addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                for (int j = 0; j < matrix_s.length; j++) {
                    sum_l[i] = sum_l[i] + matrix_s[i][matrix_s.length - j - 1];
                    {
                        if (sum_l[i] == matrix_s[0].length) {
                            bingo_l = bingo_l + 1;
                        }
                    }
                }
            }
            // -45 degree-cross addition
            for (int i = 0; i < matrix_s[0].length; i++) {
                sum_r[i] = sum_r[i] + matrix_s[i][i];
                {
                    if (sum_r[i] == matrix_s[0].length) {
                        bingo_l = bingo_l + 1;
                    }
                }
            }
            int bingo = 0;
            bingo = bingo_h + bingo_v + bingo_l + bingo_r;
            //System.out.printf(""bingo h:\n%d\n"", bingo_h);
            //System.out.printf(""bingo v:\n%d\n"", bingo_v);
            //System.out.printf(""bingo l:\n%d\n"", bingo_l);
            //System.out.printf(""bingo r:\n%d\n"", bingo_r);
            //System.out.printf(""bingo:\n%d\n"", bingo);
            System.out.printf(""%d\n"", bingo);

        }
    }
}

