/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            int num = Integer.parseInt(data);
            //System.out.print(num);
            QuickUnionUF uf = new QuickUnionUF(num * num + 3);
            int[][] matrix = new int[num + 2][num + 2];
            for (int i = 1; i < num + 1; i++) {
                matrix[0][i] = num * num + 1;
                matrix[num + 1][i] = num * num + 2;
            }
            int[] coordinate = new int[2];

            while (matrix[0][1] != matrix[num + 1][1]) {
                if (br.ready() == false) {
                    System.out.print(-1);
                    break;
                }
                String[] coordinateString = br.readLine().split("","");
                
                coordinate[0] = Integer.parseInt(coordinateString[0]);
                coordinate[1] = Integer.parseInt(coordinateString[1]);
                //assign id
                matrix[coordinate[0]][coordinate[1]]=num*(coordinate[0]-1)+coordinate[1];
                if(matrix[coordinate[0]-1][coordinate[1]]!=0&&!uf.connected(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]-1][coordinate[1]])){
                    uf.union(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]-1][coordinate[1]]);
                }
                if(matrix[coordinate[0]+1][coordinate[1]]!=0&&!uf.connected(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]+1][coordinate[1]])){
                    uf.union(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]+1][coordinate[1]]);
                }
                if(matrix[coordinate[0]][coordinate[1]-1]!=0&&!uf.connected(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]][coordinate[1]-1])){
                    uf.union(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]][coordinate[1]-1]);
                }
                if(matrix[coordinate[0]][coordinate[1]+1]!=0&&!uf.connected(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]][coordinate[1]+1])){
                    uf.union(matrix[coordinate[0]][coordinate[1]], matrix[coordinate[0]][coordinate[1]+1]);
                }
                
                
                if(uf.connected(matrix[0][1], matrix[num + 1][1]) ){
                    System.out.print(coordinate[0]+"",""+coordinate[1]);
                    break;
                }
                /*for (int i = 0; i < 2; i++) {
                 System.out.print(coordinate[i] + "" "");
                 }
                 System.out.printf(""%n"");*/

            }
        }
    }
}

