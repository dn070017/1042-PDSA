/*
.
 * To change this template file, choose Tools | Templates
.
 */
package LabelCC;

/**
 *
 * @author Tim
 */
import edu.princeton.cs.algs4.UF;
import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    private int[][] matrix;
    private int[] id;
    public static int N;
    private int label;
    UF uf;

    LabelCC(int n) {
        matrix = new int[n][n];
        id = new int[n * n];
        label = 1;
        N = n;
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = k;
                k++;
            }
        }
        for (int i = 0; i < n * n; i++) {
            id[i] = 0;
        }

    }
    public void newunion (int n){
        uf =new UF(label);        
    }
    public void child(int x,int y){
        if(x!=0&&y!=0){
            if(matrix[x-1][y]!=-1&& matrix[x][y-1]!=-1){
                uf.union(id[matrix[x-1][y]]-1,id[matrix[x][y-1]]-1);
            }
        }
    }

    public void block(int x, int y) {
        id[matrix[x-1][y-1]]=100;
        matrix[x - 1][y - 1] = -1;
    }
    
    public int getlabel (int x ,int y){
        return uf.find(id[matrix[x-1][y-1]]);
    } 

    public void firstpass(int x, int y) {
        if (x == 0 & y == 0) {
            id[matrix[x][y]] = label;
        } else {
            if (x == 0&&matrix[x][y]!=-1) {
                if (matrix[x][y - 1] == -1) {
                    label++;
                    id[matrix[x][y]] = label;
                } else {
                    id[matrix[x][y]] = id[matrix[x][y-1]];
                }
            }
            if (y == 0&&matrix[x][y]!=-1) {
                if (matrix[x - 1][y] == -1) {
                    label++;
                    id[matrix[x][y]] = label;
                } else {
                    id[matrix[x][y]] = id[matrix[x - 1][y]];
                }
            }
            if (matrix[x][y] != -1 && x != 0 && y != 0) {
                if (matrix[x - 1][y] != -1 && matrix[x][y - 1] != -1) {
                    if (id[matrix[x - 1][y]] > id[matrix[x][y - 1]]) {
                        id[matrix[x][y]] = id[matrix[x][y - 1]];
                    }
                    else{
                        id[matrix[x][y]]=id[matrix[x-1][y]];
                    }
                }else if(matrix[x-1][y]!=-1 && matrix[x][y-1]==-1){
                    id[matrix[x][y]]=id[matrix[x-1][y]];
                }else if(matrix[x-1][y]==-1 && matrix[x][y-1]!=-1){
                    id[matrix[x][y]]=id[matrix[x][y-1]];
                }else{
                    label++;
                    id[matrix[x][y]] = label;
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            String[] input = data.split("","");
            int number = Integer.parseInt(input[0]);
            int destx = Integer.parseInt(input[1]);
            int desty = Integer.parseInt(input[2]);
            
            LabelCC LC = new LabelCC(number);
            while (true) {
                String position = br.readLine();
                if (position == null) {
                    break;
                }
                String[] place = position.split("","");
                int x = Integer.parseInt(place[0]);
                int y = Integer.parseInt(place[1]);
                LC.block(x, y);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    LC.firstpass(i, j);
                }
            }
            LC.newunion(N);
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    LC.child(i,j);
                }
            }
            int answer=LC.getlabel(destx, desty);
            System.out.printf(""%d"",answer);
        }
        
        // TODO code application logic here
    }

}

