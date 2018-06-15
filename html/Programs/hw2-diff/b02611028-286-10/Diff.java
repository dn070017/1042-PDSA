import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int N = Integer.parseInt(data[0]);
            int[] target = new int[2];
            target[0] = Integer.parseInt(data[1]);
            target[1] = Integer.parseInt(data[2]);
            int[][] matrix = new int[N + 2][N + 2];
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    matrix[i][j] = 1;
                    if (i == 0 || j == 0 || i == N + 1 || j == N + 1) {
                        matrix[i][j] = 0;
                    }
                }
            }
            //finish maps
            int ready = 1;
            if (!br.ready()) {
                ready++;
            }
            while (ready == 1) {
                String[] ordinary = br.readLine().split("","");
                int[] ordinarynum = new int[2];
                ordinarynum[0] = Integer.parseInt(ordinary[0]);
                ordinarynum[1] = Integer.parseInt(ordinary[1]);
                matrix[ordinarynum[0]][ordinarynum[1]] = 0;

                if (!br.ready()) {
                    break;
                }

            }
            /*for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    System.out.print(matrix[i][j] + ""　　 "");
                }
                System.out.printf(""%n"");
            }*/

            QuickUnionUF uf = new QuickUnionUF(N * N + 1);
            int count = 1;
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (matrix[i][j] == 1) {

                        if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                            matrix[i][j] = count;
                            count++;
                            continue;
                        }
                        if (matrix[i][j - 1] != 0) {
                            matrix[i][j] = matrix[i][j - 1];

                        }
                        if (matrix[i - 1][j] != 0) {
                            matrix[i][j] = matrix[i - 1][j];

                        }
                        if (matrix[i][j - 1] != 0 && matrix[i - 1][j] != 0) {
                            matrix[i][j] = Math.min(matrix[i][j - 1], matrix[i - 1][j]);
                            uf.union(Math.max(matrix[i][j - 1], matrix[i - 1][j]), Math.min(matrix[i][j - 1], matrix[i - 1][j]));
                        }
                    }
                }
            }

            /*for (int i = 0; i < N+2; i++) {
                for (int j = 0; j < N+2; j++) {
                    System.out.print(uf.find(matrix[i][j]) + ""　　 "");
                }
                System.out.printf(""%n"");
            }*/
            //if(matrix[target[0]-1][target[1]-1]==0)System.out.print(0);
            System.out.print(uf.find(matrix[target[0]][target[1]]));

        }
    }
}

