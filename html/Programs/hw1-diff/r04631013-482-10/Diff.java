import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader((args[0])))) {

            String[] data = br.readLine().split("","");

            int num = Integer.parseInt(data[0]);

            int[][] matrix = new int[num][num];
            // give ID = 0 for all the components in the matrix
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = 0;
                }
            }
//            the first row connected to head
//            the last row connected to foot
            int head = num * num + 1;
            int foot = num * num + 2;
            UF uf = new UF(num * num + 4);

            String line;
            int initial = 0;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    System.out.print(""-1"");
                    break;
                }
//                line = br.readLine();
                String[] readnum = line.split("","");
                int row = Integer.parseInt(readnum[0]) - 1;
                int col = Integer.parseInt(readnum[1]) - 1;
                initial = initial + 1;
                matrix[row][col] = initial;
                if (row == 0) {
                    uf.union(matrix[row][col], head);
                }
                if (row == num - 1) {
                    uf.union(matrix[row][col], foot);
                }
                //  up sides
                if (row - 1 >= 0) {
                    if (matrix[row - 1][col] != 0) {
                        uf.union(matrix[row][col], matrix[row - 1][col]);
                    }
                }
                //  down sides
                if (row + 1 <= num - 1) {
                    if (matrix[row + 1][col] != 0) {
                        uf.union(matrix[row][col], matrix[row + 1][col]);
                    }
                }
                //  left sides
                if (col - 1 >= 0) {
                    if (matrix[row][col - 1] != 0) {
                        uf.union(matrix[row][col], matrix[row][col - 1]);
                    }
                }
                //  right sides
                if (col + 1 <= num - 1) {
                    if (matrix[row][col + 1] != 0) {
                        uf.union(matrix[row][col], matrix[row][col + 1]);
                    }
                }

                if (uf.connected(head, foot)) {
                    System.out.printf(""%d"", row + 1);
                    System.out.print("","");
                    System.out.printf(""%d"", col + 1);
                    break;
                }
            }
            if (!uf.connected(head, foot)) {
                System.out.print(""-1"");
            }
        }

    }
}

