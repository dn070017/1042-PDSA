
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
            int[][] bin = new int[num][num];

            announce = br.readLine().split("","");

            int k = 0;
            int a = 0;
            for (int i = 0; i < num; i++) {
                String[] coun = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = coun[j];
                    for (int m = 0; m < stringCount; m++) {
                        if (matrix[i][j].equals(announce[m])) {
                            bin[i][j] = 1;
                        }
                    }
                }
            }

            for (int i = 0; i < num; i++) {
                if (i == 0 && bin[0][0] == 1) {
                    for (int j = 0; j < (num - 1); j++) {
                        if (bin[j][j] == bin[j + 1][j + 1]) {
                            k = k + 1;
                        }
                        if (k == num - 1) {
                            a = a + 1;
                        }
                    }
                    k = 0;
                } else if (i == 0 && bin[0][num - 1] == 1) {
                    for (int j = 0; j < (num - 1); j++) {
                        if (bin[j][num - 1 - j] == bin[j + 1][num - 2 - j]) {
                            k = k + 1;
                        }
                        if (k == num - 1) {
                            a = a + 1;
                        }

                    }
                    k = 0;
                }
                for (int j = 0; j < (num - 1); j++) {
                    if (bin[i][j] == bin[i][j + 1] && bin[i][j] == 1) {
                        k = k + 1;
                    }
                    if (k == num - 1) {
                        a = a + 1;
                    }
                }
                k = 0;

                for (int j = 0; j < (num - 1); j++) {
                    if (bin[j][i] == bin[j + 1][i] && bin[j][i] == 1) {
                        k = k + 1;
                    }
                    if (k == num - 1) {
                        a = a + 1;
                    }
                }
                k = 0;
            }
            System.out.printf(""%d "", a);
        }
    }
}

