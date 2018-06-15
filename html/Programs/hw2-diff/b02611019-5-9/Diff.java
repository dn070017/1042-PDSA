
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author S410
 */


public class LabelCC {

    
    static WeightedQuickUnionUF uf;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String[] numstr = br.readLine().split("","");
        int num = Integer.parseInt(numstr[0]);
        int tarx=Integer.parseInt(numstr[1]);
        int tary=Integer.parseInt(numstr[2]);
        

        Integer[][] matrix = new Integer[num][];
        for (int i = 0; i < num; i++) {
            matrix[i] = new Integer[num];
            for (int j = 0; j < num; j++) {
                matrix[i][j] = -1;
            }
        }
        while (br.ready()) {
            String[] coor = br.readLine().split("","");
            int x = Integer.parseInt(coor[0]) - 1;
            int y = Integer.parseInt(coor[1]) - 1;
            matrix[x][y] = 0;
        }
        int index = 1;
        if (matrix[0][0] == -1) {
            matrix[0][0] = index++;
        }
        uf = new WeightedQuickUnionUF (num*num);
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i * j == 0 && matrix[i][j] == -1) {
                    if (i==0&&j==0){
                        matrix[i][j]=index++;
                    }
                    else if (i == 0 && matrix[i][j - 1] != 0) {
                        matrix[i][j] = matrix[i][j - 1];
                    } else if (j == 0 && matrix[i - 1][j] != 0) {
                        matrix[i][j] = matrix[i-1][j];
                    } else {
                        matrix[i][j] = index++;
                    }
                }

                else if (matrix[i][j] == -1) {
                    if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                        matrix[i][j] = index++;
                    }
                    if (matrix[i - 1][j] != 0&&matrix[i][j - 1] != 0){
                        
                        if (matrix[i][j-1]>matrix[i-1][j]){
                            matrix[i][j] = matrix[i-1][j ];
                            uf.union(matrix[i - 1][j], matrix[i][j-1]);
                        }
                        else{
                            matrix[i][j] = matrix[i][j-1 ];
                            uf.union(matrix[i ][j-1], matrix[i-1][j]);
                        }
                            
                        
                    }
                    else if (matrix[i - 1][j] != 0) {
                        matrix[i][j] = matrix[i - 1][j];
                    }
                    else if (matrix[i][j - 1] != 0) {
                        matrix[i][j] = matrix[i][j - 1];
                    }
                }
            }
        }
        
        
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if(matrix[i][j]!=0&&matrix[i][j]!=uf.find(matrix[i][j])){
                    matrix[i][j]=uf.find(matrix[i][j]);
                }
            }
        }
        //測試輸出label
//        for (int i = 0; i < num; i++) {
//            for (int j = 0; j < num; j++) {
//                StdOut.print(matrix[i][j]+"" "");
//            }
//            StdOut.print(""\n"");
//        }
//        
        StdOut.print(matrix[tarx-1][tary-1]);
    }
}

