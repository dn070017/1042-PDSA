
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

            int[][] matrixTF = new int[num][num];
            int bingoCount = 0;
            for (int i = 0; i < num; i++) {
                String[] row = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = row[j];
                    for (int k = 0; k < stringCount; k++) {
                        if (announce[k].equals(matrix[i][j])) {
                            matrixTF[i][j] = 1;
                            break;
                        }

                    }

                }
            }
//            check rows
            int rowCount = 0;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (matrixTF[i][j] == 1) {
                        rowCount++;
                    }
                }
                if (rowCount == num) {
                    bingoCount++;
                }
                rowCount = 0;
            }
//            check columns
            int columnCount = 0;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (matrixTF[j][i] == 1) {
                        columnCount++;
                    }
                }
                if (columnCount == num) {
                    bingoCount++;
                }
                columnCount = 0;
            }
//            check positive diagonal
            int pdglCount = 0;
            for (int i = 0; i < num; i++) {
                if (matrixTF[i][i] == 1) {
                    pdglCount++;
                }
            }
            if (pdglCount == num) {
                bingoCount++;
            }
//            check negative diagonal
            int ndglCount = 0;
            for (int i = 0; i < num; i++) {
                if (matrixTF[i][num - i - 1] == 1) {
                    ndglCount++;
                }
            }
            if (ndglCount == num) {
                bingoCount++;
            }
            System.out.print(bingoCount);
        }
    }
}

