
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            announce = br.readLine().split("","");
            for (int i = 0; i < num; i++) {
                String[] row = br.readLine().split("","");
                matrix[i] = row;
            }

            int rightslash = 0;
            int leftslash = 0;
            int horizental = 0;
            int vertical = 0;
            int end = num - 1;
            int count = 0;
            int[][] bingo = new int[num][num];
            int[] HorLoc = new int[num];
            int[] VerLoc = new int[num];

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    for (int k = 0; k < stringCount; k++) {
                        int check = announce[k].compareTo(matrix[i][j]);
                        if (check == 0) {
                            bingo[i][j] = 1;
                        }
                    }
                }
            }

            int CalD = 0;
            int CalBD = 0;
            for (int i = 0; i < num; i++) {
                CalD += bingo[i][i];
                CalBD += bingo[end - i][i];
            }
            if (CalD == num) {
                count++;
            }
            if (CalBD == num) {
                count++;
            }

            //horizental line
            int CalH = 0;
            int CalV = 0;
            for (int i = 0; i < num; i++) {
                CalH = 0;
                CalV = 0;
                for (int j = 0; j < num; j++) {
                    CalH += bingo[i][j];
                    CalV += bingo[j][i];

                }
                if (CalH == num) {
                    count++;
                }
                if (CalV == num) {
                    count++;
                }
            }
            System.out.printf(""%d\n"", count);
        }
    }
}

