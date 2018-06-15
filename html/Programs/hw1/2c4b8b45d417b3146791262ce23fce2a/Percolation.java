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
    UF uf;

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
        
        for (int i = 0; i < a; i++) {
            id[i] = 1;
        }
        
    }

    public void open(int x, int y){
        id[matrix[x-1][y-1]]=0;
    }

    public void un(int x, int y) {
        
        if (x != 1) {
            if (id[matrix[x - 2][y - 1]] == 0) {
                uf.union(matrix[x - 1][y - 1], matrix[x - 2][y - 1]);
            }
        }
        if (x != N) {
            if (id[matrix[x][y - 1]] == 0) {
                uf.union(matrix[x - 1][y - 1],matrix[x][y - 1] );
            }
        }
        if (y != 1) {
            if (id[matrix[x - 1][y - 2]] == 0) {
                uf.union(matrix[x - 1][y - 1], matrix[x - 1][y - 2]);
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
        if(N==1){
            if(id[matrix[0][0]]==0){
                return true;
            }
        }
        for(int i=1;i<=N;i++){
            for(int j=0;j<N;j++){
                if(uf.connected(matrix[N-1][N-i],matrix[0][j])){
                    return true;
                }
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
                PL.open(x, y);
                PL.un(x, y);
            }
            if(PL.check()==false){
                System.out.printf(""-1"");
            }
        }
    }
}


