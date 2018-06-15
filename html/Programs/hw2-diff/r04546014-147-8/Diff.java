
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Jayden
 */
public class LabelCC {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] title = new String[3];
            title = br.readLine().split("","");
            int num = Integer.valueOf(title[0]);
            int x = Integer.valueOf(title[1]);
            int y = Integer.valueOf(title[2]);
            byte[][] matrix = new byte[num][num];
            int[][] matrixOutput = new int[num][num];
            int[] family = new int[num * num];
            UF uf = new UF(num * num);

            String[] input = new String[2];
            int a, b;
            if (br.ready() == false) {
                System.out.println(1);
                System.exit(0);
            }
            if (num == 1 && br.ready() == true) {
                System.out.println(0);
                System.exit(0);
                //這個if用來確認是否有1*1的陣列的狀況(助教好心機...)
            }
            while (br.ready() == true) {
                input = br.readLine().split("","");
                a = Integer.valueOf(input[0]);
                b = Integer.valueOf(input[1]);
                matrix[a - 1][b - 1] = 1;
                //1代表黑色，0代表白色
            }
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (matrix[i][j] == 0) {
                        if (i == 0) {
                            if (matrix[i + 1][j] == 0) {
                                uf.union(i * num + j, (i + 1) * num + j);
                                //連結他下面的數
                            }
                        } else if (i == num - 1) {
                            if (matrix[i - 1][j] == 0) {
                                uf.union(i * num + j, (i - 1) * num + j);
                                //連結他上面的數
                            }
                        } else {
                            if (matrix[i - 1][j] == 0) {
                                uf.union(i * num + j, (i - 1) * num + j);
                                //連結上面的數
                            }
                            if (matrix[i + 1][j] == 0) {
                                uf.union(i * num + j, (i + 1) * num + j);
                                //連結下面的數
                            }
                        }
                        //上面三個if else用來確認選到的數字的上下是否為白色，是的話就連結
                        if (j == 0) {
                            if (matrix[i][j + 1] == 0) {
                                uf.union(i * num + j, i * num + j + 1);
                                //連結後面的數
                            }
                        } else if (j == num - 1) {
                            if (matrix[i][j - 1] == 0) {
                                uf.union(i * num + j, i * num + j - 1);
                                //連結前面的數
                            }
                        } else {
                            if (matrix[i][j + 1] == 0) {
                                uf.union(i * num + j, i * num + j + 1);
                            }
                            if (matrix[i][j - 1] == 0) {
                                uf.union(i * num + j, i * num + j - 1);
                            }
                        }
                        //後面三個if else 用來確認選到的數字的左右是否為白色，是的話就連結
                    }
                }
            }
            //確定好矩陣中所有的格子為0或1
            for (int i = 0; i < num * num; i++) {
                int g = i / num;
                if (matrix[g][i % num] == 0) {
                    family[i] = uf.find(i);
                }
            }

            int kk = 1;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (matrix[i][j] == 0) {
                        if (i == 0 && j == 0) {
                            matrixOutput[i][j] = kk;
                            kk++;
                        } else if (i == 0) {
                            if (matrix[i][j - 1] == 0) {
                                matrixOutput[i][j] = matrixOutput[i][j - 1];
                            } else {
                                matrixOutput[i][j] = kk;
                                kk++;
                            }
                        } else if (j == 0) {
                            if (matrix[i - 1][j] == 0) {
                                matrixOutput[i][j] = matrixOutput[i - 1][j];
                            } else {
                                matrixOutput[i][j] = kk;
                                kk++;
                            }
                        } //上面三個是將三種例外情形先解決(包含左上方那格，以及第一行及第一列的數)
                        else {
                            if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                                matrixOutput[i][j] = Math.min(matrixOutput[i - 1][j], matrixOutput[i][j - 1]);
                            } else if (matrix[i - 1][j] == 0) {
                                matrixOutput[i][j] = matrixOutput[i - 1][j];
                            } else if (matrix[i][j - 1] == 0) {
                                matrixOutput[i][j] = matrixOutput[i][j - 1];
                            } else {
                                matrixOutput[i][j] = kk;
                                kk++;
                            }
                        }
                    }
                    if (matrixOutput[i][j] != 0 && matrixOutput[i][j] < family[i * num + j]) {
                        int q = matrixOutput[i][j];
                        int z = family[i * num + j];
                        for (int k = 0; k < num * num; k++) {
                            if (family[k] == z) {
                                family[k] = q;
                            }
                        }
                    }
                }
            }
            //first phase

            System.out.println(family[(x - 1) * num + y - 1]);
        }
    }

}

