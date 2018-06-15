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
            int[][] matrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = 1;
                }
            }
            //finish maps
            int ready=1;
            if(!br.ready())ready++;
            while (ready==1) {
                String[] ordinary = br.readLine().split("","");
                int[] ordinarynum = new int[2];
                ordinarynum[0] = Integer.parseInt(ordinary[0]);
                ordinarynum[1] = Integer.parseInt(ordinary[1]);
                matrix[ordinarynum[0] - 1][ordinarynum[1] - 1] = 0;

                if (!br.ready()) {
                    break;
                }

            }
            QuickUnionUF uf = new QuickUnionUF(N * N + 1);
            int count = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == 1) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        if (i == 0) {
                            if (matrix[i][j - 1] == 0) {
                                count++;
                            }
                            matrix[i][j] = count;
                            continue;
                        }
                        if (i != 0) {
                            if (j == 0) {
                                if (matrix[i - 1][j] != 0) {
                                    matrix[i][j] = matrix[i - 1][j];
                                    continue;
                                } else {
                                    count++;
                                    matrix[i][j] = count;
                                    continue;
                                }
                            }
                            if (j != 0) {
                                if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                                    count++;
                                    matrix[i][j] = count;
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
                                    uf.union(Math.max(matrix[i][j - 1], matrix[i - 1][j]),Math.min(matrix[i][j - 1], matrix[i - 1][j]));
                                }
                            }
                        }
                    }
                }
            }

            /*for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(uf.find(matrix[i][j]) + "" "");
                }
                System.out.printf(""%n"");
            }*/
            System.out.print(uf.find(matrix[target[0]-1][target[1]-1]));

        }
    }
}

