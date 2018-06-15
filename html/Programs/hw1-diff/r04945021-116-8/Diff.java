/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Percolation {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int Num = Integer.parseInt(data[0]);
            boolean[][] matrix = new boolean[Num][Num];
            for(int i=0;i<Num;i++){
                           Arrays.fill(matrix[i], false); 
            }
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(Num * Num + 2);
            String line;
            int nopercolation=-1;
            int top = 0;
            int bottom = Num * Num + 1;
            while ((line = br.readLine()) != null) {
                String[] dataOpen = line.split("","");
                int row = Integer.parseInt(dataOpen[0]);
                int col = Integer.parseInt(dataOpen[1]);
                //open
                matrix[row - 1][col - 1] = true;
               //如果上或下有open就和虛擬的2點union
                //Num*(row-1)是id
                if (row == 1) {
                    uf.union((Num * (row - 1) + col), top);
                }
                if (row == Num) {
                    uf.union((Num * (row - 1) + col), bottom);
                }
               //確認點周圍有沒有打開，如果有就union在一起
                //跟上接
                if (row > 1 && (matrix[row - 2][col - 1] == true)) {
                    uf.union((Num * (row - 1) + col), (Num * (row - 2) + col));
                }
                //跟下接
                if (row < Num && (matrix[row][col - 1] == true)) {
                    uf.union((Num * (row - 1) + col), (Num * row + col));
                }
                //跟左接
                if (col > 1 && (matrix[row - 1][col - 2] == true)) {
                    uf.union((Num * (row - 1) + col), (Num * (row - 1) + col - 2));
                }
                //跟右接
                if (col < Num && (matrix[row - 1][col] == true)) {
                    uf.union((Num * (row - 1) + col), (Num * (row - 1) + col));
                }
                if (uf.connected(top, bottom) == true) {
                    System.out.printf(""%d,%d\n"", row, col);
                    break;
                }
            }
            if (uf.connected(top, bottom) == false) {
                System.out.printf(""%d\n"",nopercolation);
            }
        }

    }
}

