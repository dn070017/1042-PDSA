
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Jayden
 */
public class Bingo {

    public static void main(String[] args) throws Exception {
        //讀檔(利用args[0]為檔名input)
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data;
            data = br.readLine().split("","");
            //城市數量為第一個數
            int countryCount = Integer.valueOf(data[0]);
            //矩陣大小為第二個數
            int matrixSize = Integer.valueOf(data[1]);

            String[] announce = new String[countryCount];
            String[][] matrix = new String[matrixSize][matrixSize];
            //定義好兩個陣列用來存儲檔案資料
            announce = br.readLine().split("","");
            //第二行為宣告的國家
            for (int i = 0; i < matrixSize; i++) {
                matrix[i] = br.readLine().split("","");
            }
            //第三行以後為Bingo內容

            int count = 0;
            byte[][] checkMatrix = new byte[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                int a = 0;
                for (int j = 0; j < matrixSize; j++) {
                    for (int k = 0; k < countryCount; k++) {
                        if (matrix[i][j].equals(announce[k])) {
                            checkMatrix[i][j] = 1;
                            a++;
                            break;
                        }
                    }
                }
                if (a == matrixSize) {
                    count++;
                }
            }
            //將所有的字檢查一次看有沒有可以圈起來的(圈起來設為1)
            //順便檢查是否有橫線可以畫(一條線count就++)

            for (int i = 0; i < matrixSize; i++) {
                int b = 0;
                for (int j = 0; j < matrixSize; j++) {
                    if (checkMatrix[j][i] == 1) {
                        b++;
                    }
                }
                if (b == matrixSize) {
                    count++;
                }
            }
            //檢查是否有直線可以畫

            int c = 0;
            int d = 0;
            for (int i = 0; i < matrixSize; i++) {
                if (checkMatrix[i][i] == 1) {
                    c++;
                }
                if (checkMatrix[i][matrixSize - 1 - i] == 1) {
                    d++;
                }
            }
            if (c == matrixSize) {
                count++;
            }
            if (d == matrixSize) {
                count++;
            }
            //檢查是否有協線可以畫
            System.out.println(count);

        }
    }

}

