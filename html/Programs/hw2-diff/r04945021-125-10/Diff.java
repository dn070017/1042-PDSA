/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class LabelCC {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int Num = Integer.parseInt(data[0]);
            int FinalNum1 = Integer.parseInt(data[1]);
            int FinalNum2 = Integer.parseInt(data[2]);

            int[][] matrix = new int[Num][Num];
            for (int i = 0; i < Num; i++) {
                Arrays.fill(matrix[i], 1);
            }

            QuickUnionUF uf = new QuickUnionUF(Num * Num);
            String line;

            //標好block的位置
            while ((line = br.readLine()) != null) {
                if ("""".equals(line)) {
                    continue;
                }
                String[] DataBlock = line.split("","");
                int row = Integer.parseInt(DataBlock[0]);
                int col = Integer.parseInt(DataBlock[1]);
                //block
                matrix[row - 1][col - 1] = 0;
            }

         
            
            int PoNum = 0;
            for (int i = 0; i < Num; i++) {
                for (int j = 0; j < Num; j++) {
                    if(matrix[i][j]==0){
                        continue;
                    }
//(0,0)
                   else if (i == 0 && j == 0) {
                        if (matrix[i][j] == 1) {
                            PoNum++;
                            matrix[i][j] = PoNum;
                            
                        }
                    } else if (i == 0 && j > 0) {
                        if (matrix[i][j-1] == 0) {
                            PoNum++;
                            matrix[i][j] = PoNum;
                            
                        } else {
                            matrix[i][j] = matrix[i][j-1];
                        }
                    } else if (i > 0 && j == 0) {
                        if (matrix[i-1][j] == 0) {
                            PoNum++;
                            matrix[i][j] = PoNum;
                            
                        } else {
                            matrix[i][j] = matrix[i-1][j];
                        }
                    } else if(i>0&&j>0){
                        if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                            PoNum++;
                            matrix[i][j] = PoNum;
                            
                        } else if (matrix[i - 1][j] != 0 && matrix[i][j - 1] != 0) {
                            if (matrix[i - 1][j] >= matrix[i][j - 1]) {
                                matrix[i][j] = matrix[i][j - 1];
                                uf.union(matrix[i-1][j], matrix[i][j]);
                                
                            } else {
                                matrix[i][j] = matrix[i - 1][j];
                                uf.union(matrix[i][j - 1], matrix[i][j]);
                                
                            }
                        } else {
                            if (matrix[i - 1][j] == 0 && matrix[i][j - 1] != 0) {
                                matrix[i][j] = matrix[i][j - 1];
                            } else if (matrix[i - 1][j] != 0 && matrix[i][j - 1] == 0) {
                                matrix[i][j] = matrix[i - 1][j];
                            }
                        }
                    }

                }

            }

                       
            System.out.println(uf.find(matrix[FinalNum1-1][FinalNum2-1]));
        }

    }

}

