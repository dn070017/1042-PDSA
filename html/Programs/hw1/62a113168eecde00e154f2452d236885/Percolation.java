/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Tim
 */

import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    private int[] id;
    private int[][] matrix;
    private int N;
    UF uf;// = new UF(N * N);

    Percolation(int n) {
        matrix = new int[n][n];
        id = new int[n * n];
        uf = new UF(n*n);
        N=n;
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
        if (x != N) {
            if (id[matrix[x][y - 1]] == 0) {
                uf.union(matrix[x][y - 1], matrix[x - 1][y - 1]);
            }
        }
        if (y != 1) {
            if (id[matrix[x - 1][y - 2]] == 0) {
                uf.union(matrix[x - 1][y - 2], matrix[x - 1][y - 1]);
            }
        }
        if (y != N) {
            if (id[matrix[x - 1][y]] == 0) {
                uf.union(matrix[x - 1][y], matrix[x - 1][y - 1]);
            }
        }
        if(check()==true){
            System.out.printf(""%d,%d"", x,y);
        }
        
    }
    public boolean check(){
        //if(uf.connected(matrix[N-1][N-1], matrix[0][0])||uf.connected(matrix[N-1][N-2],matrix[0][0])||uf.connected(matrix[N-1][N-3],matrix[0][0])){
        for(int i=N*N-1;i>N*2;i--){
            if(uf.find(i)!=i){
                return true;
            }
        }
        return false;
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
            while (PL.check() == false){
                String coordinate = br.readLine();
                if(coordinate == null)
                    break;
                String[] place=coordinate.split("","");
                int x = Integer.parseInt(place[0]);
                int y = Integer.parseInt(place[1]);
                PL.un(x, y);
            }
            if(PL.check()==false){
                System.out.printf(""-1"");
            }
        }
    }
}


