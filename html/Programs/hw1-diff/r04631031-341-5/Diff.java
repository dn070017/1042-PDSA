
//import edu.princeton.cs.algs4.UF;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * @author huangchienpeng
 */
public class Percolation {

    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");
            // store the matrix size
            int size = Integer.parseInt(data[0]);
            // 0 ~ n*n+1 for one start point and end point
            int num = size * size + 2;

            //build checkmatrix which is bigger than matrix and cover all matrix
            boolean[][] checkmatrix = new boolean[size+2][size+2];
            for (int i = 0; i < size+2; i++) {
                for (int j = 0; j < size+2; j++) {
                    checkmatrix[i][j] = false;
                }
            }

            UF uf = new UF(num);
            //connect first row to start point 0
            for (int i = 1; i <= size; i++) {
                uf.union(i, 0);
            }
            //connect last row to end point num
            for (int i = num - size-1; i <= num - 2; i++) {
                uf.union(i, num-1);
            }
            
            int row = 0;
            int column = 0;
            int matrixnum = 0;      
            do {
                //read the input data
                String[] announce = br.readLine().split("","");
                row = Integer.parseInt(announce[0]);
                column = Integer.parseInt(announce[1]);
                matrixnum = size * (row - 1) + column;

                //mark the announced site
                checkmatrix[row][column] = true;

                //check up, down, left, right side of announced site
                while (checkmatrix[row - 1][column]) {
                    uf.union(matrixnum, matrixnum - size);
                    break;
                }
                while (checkmatrix[row + 1][column]) {
                    uf.union(matrixnum, matrixnum + size);
                    break;
                }
                while (checkmatrix[row][column - 1]) {
                    uf.union(matrixnum, matrixnum - 1);
                    break;
                }
                while (checkmatrix[row][column + 1]) {
                    uf.union(matrixnum, matrixnum + 1);
                    break;
                }
            } 
            while (!uf.connected(0, num-1));
            
            System.out.printf(""%d,%d"", row, column);
        }
    }
}

