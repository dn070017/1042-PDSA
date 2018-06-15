/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Tim
 */
import edu.princeton.cs.algs4.UF;
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    private int[] id;
    private int[][] matrix;
    
    UF uf;// = new UF(N * N);

    Percolation(int n) {
        matrix = new int[n][n];
        id = new int[n * n];
        uf = new UF(n*n);
        int a = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = a;
                a++;    
            }
        }
        
        for (int i = 1; i <= a; i++) {
            id[i - 1] = i;
        }
        
    }

    public void un(int x, int y) {
        id[matrix[x - 1][y - 1]] = 0;
        if (x != 1) {
            if (id[matrix[x - 2][y - 1]] == 0) {
                uf.union(matrix[x - 2][y - 1], matrix[x - 1][y - 1]);
            }
        }
        if (x != 3) {
            if (id[matrix[x][y - 1]] == 0) {
                uf.union(matrix[x][y - 1], matrix[x - 1][y - 1]);
            }
        }
        if (y != 1) {
            if (id[matrix[x - 1][y - 2]] == 0) {
                uf.union(matrix[x - 1][y - 2], matrix[x - 1][y - 1]);
            }
        }
        if (y != 3) {
            if (id[matrix[x - 1][y]] == 0) {
                uf.union(matrix[x - 1][y], matrix[x - 1][y - 1]);
            }
        }
        if(check()==true){
            System.out.printf(""%d,%d"", x,y);
        }
        
    }
    public boolean check(){
        if(uf.connected(matrix[2][2], matrix[0][0])||uf.connected(matrix[2][1],matrix[0][0])||uf.connected(matrix[2][0],matrix[0][0])){
            return true;
        }
        else return false;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            int number = Integer.parseInt(data);
            Percolation PL = new Percolation(number);
            int n=0;
            
            while (!PL.check()) {
                String[] coordinate = br.readLine().split("","");
                int x = Integer.parseInt(coordinate[0]);
                int y = Integer.parseInt(coordinate[1]);

                PL.un(x, y);
                n++;
            }
        }
    }
}


