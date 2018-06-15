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
            int familyArrayCount = (int) num * num / 2;
            int[] family = new int[familyArrayCount];
            String[] input = new String[2];
            int a, b;
            //a,b是用來存取使用者輸入的兩個數字
            if (br.ready() == false) {
                System.out.println(1);
                System.exit(0);
                //當什麼資料都沒有時，下方的所有格子一定connect，因此回傳1
            }
            if (num == 1 && br.ready() == true) {
                System.out.println(0);
                System.exit(0);
                //如果是1*1的矩陣，且下方有顯示格子，則表示該格為黑色，因此回傳0
            }
            while (br.ready() == true) {
                input = br.readLine().split("","");
                a = Integer.valueOf(input[0]);
                b = Integer.valueOf(input[1]);
                matrix[a - 1][b - 1] = 1;
                //1代表黑色，0代表白色
            }

            for (int i = 0; i < familyArrayCount; i++) {
                family[i] = i;
            }
            int kk = 1;
            //kk只是用來寫入第一個phase的陣列裡的數字
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    //兩層for迴圈用來跑矩陣裡的每一個數字，當該數字是0則進入下面的if
                    if (matrix[i][j] == 0) {
                        if (i == 0 && j == 0) {
                            matrixOutput[i][j] = kk;
                            kk++;
                            //最左上角的特例
                        } else if (i == 0) {
                            if (matrix[i][j - 1] == 0) {
                                matrixOutput[i][j] = matrixOutput[i][j - 1];
                                //該數字前方數字若有值，則跟他一樣
                            } else {
                                matrixOutput[i][j] = kk;
                                kk++;
                                //前方數字沒有值，就自己寫入一個新的值kk
                            }
                            //第一列的情況
                        } else if (j == 0) {
                            if (matrix[i - 1][j] == 0) {
                                matrixOutput[i][j] = matrixOutput[i - 1][j];
                                //該數字上方數字若有值，則跟他一樣
                            } else {
                                matrixOutput[i][j] = kk;
                                kk++;
                                //上方數字沒有值，就自己寫入一個新的值kk
                            }
                            //第一行的情況
                        } //上面三個是將三種例外情形先解決(包含左上方那格，以及第一行及第一列的數)
                        else {
                            if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                                matrixOutput[i][j] = Math.min(matrixOutput[i - 1][j], matrixOutput[i][j - 1]);
                                int min = matrixOutput[i][j];
                                int max = Math.max(matrixOutput[i - 1][j], matrixOutput[i][j - 1]);
                                family[max] = min;
                                //若上方和前方都有值，則取最小的
                            } else if (matrix[i - 1][j] == 0) {
                                matrixOutput[i][j] = matrixOutput[i - 1][j];
                                //只有上方有值，就跟他一樣
                            } else if (matrix[i][j - 1] == 0) {
                                matrixOutput[i][j] = matrixOutput[i][j - 1];
                                //只有前方有值，就跟他一樣
                            } else {
                                matrixOutput[i][j] = kk;
                                kk++;
                                //上方前方都沒有值，就自己寫入一個新的值kk
                            }
                        }
                    }
                }
            }
            if (matrix[x - 1][y - 1] == 1) {
                System.out.println(0);
            } else {
                System.out.println(family[matrixOutput[x - 1][y - 1]]);
            }
        }
        //first phase
    }

}

