
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
            int NumOfmatrix = size * size + 2;

            //build checkmatrix which is bigger than old matrix and cover it
            boolean[][] checkmatrix = new boolean[size+2][size+2];
            for (int i = 0; i < size+2; i++) {
                for (int j = 0; j < size+2; j++) {
                    checkmatrix[i][j] = false;
                }
            }

            UF uf = new UF(NumOfmatrix);
            //connect first row to start point 0
            for (int i = 1; i <= size; i++) {
                uf.union(i, 0);
            }
            //connect last row to end point matrixNum-1
            for (int i = NumOfmatrix - size-1; i <= NumOfmatrix - 2; i++) {
                uf.union(i, NumOfmatrix-1);
            }
            String [] announce = new String[2];
            int row = 0;
            int column = 0;
            int matrixnum = 0;
            boolean endflag = false;
          
            while(br.ready()) {
                    announce = br.readLine().split("","");
                    row = Integer.parseInt(announce[0]);
                    column = Integer.parseInt(announce[1]);
//                    System.out.printf(""%d,%d\n"", row, column);
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
                    while(uf.connected(0, NumOfmatrix-1)){
                        endflag = true;
                        break;
                    }
                    if(endflag == true)
                        break;
            } 
            
            if(endflag != true){
                System.out.printf(""-1"");
            }
            else
                System.out.printf(""%d,%d"", row, column);
        }
    }
}

