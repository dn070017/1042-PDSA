import java.io.FileReader;
import java.io.BufferedReader;


public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            
            announce = br.readLine().split("","");
            
            int count = 0;

            for (int i = 0; i < num; i++) {
                String[] inputmatrix = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = inputmatrix[j];
                }
            }
              
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    for (int k = 0; k < stringCount; k++) {
                        if (matrix[i][j].equals(announce[k])) {
                            matrix[i][j] = ""0"";
                        }
                    }
                }
            }

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (matrix[i][j] == ""0"") {
                        if (j == num - 1) {
                            count++;
                        }
                    } else {
                        break;
                    }
                }
            }

            for (int j = 0; j < num; j++) {
                for (int i = 0; i < num; i++) {
                    if (matrix[i][j] == ""0"") {
                        if (i == num - 1) {
                            count++;
                        }
                    } else {
                        break;
                    }
                }
            }

            for (int i = 0; i < num; i++) {
                if (matrix[i][i] == ""0"") {
                    if (i == num - 1) {
                        count++;
                    }
                } else {
                    break;
                }
            }

            for (int i = 0; i < num; i++) {
                if (matrix[i][num - 1 - i] == ""0"") {
                    if (i == 0) {
                        count++;
                    }
                } else {
                    break;
                }
            }

            System.out.printf(""%d"", count);
            System.out.printf(""\n"");
        }        
    }
}

