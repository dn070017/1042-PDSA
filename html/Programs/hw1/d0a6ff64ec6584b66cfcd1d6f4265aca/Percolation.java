
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author S410
 */
public class Percolation {

    static int[][] ID;
    static WeightedQuickUnionUF uf;
    static int num;
    static boolean toptodown(){
        for(int j=0;j<num;j++){
            for (int k=0;k<num;k++){
                while(uf.connected(ID[0][j],ID[num-1][k] )){
                    return true; 
                }
            }
                
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        String[] data = br.readLine().split("","");
        num = Integer.parseInt(data[0]);
        int numcr = Integer.parseInt(data[1]);
//        StdOut.print(num);
//        System.out.print(numcr);
        ID = new int[num][num];

//        初始化matrix  0~num^2-1
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ID[i][j] = num * i + j;
//                StdOut.print(matrix[i][j]);
            }
//            StdOut.print(""\n"");
        }

        uf = new WeightedQuickUnionUF(num * num);
        
        boolean[][] cell = new boolean[num ][];
        for (int i = 0; i < num; i++) {
            cell[i] = new boolean[num];
            for (int j = 0; j < num; j++) {
                cell[i][j] = false;
            }
        }

        boolean co =false;
        for (int i = 0; i < numcr; i++) {
            String[] coor = br.readLine().split("","");
            int x = Integer.parseInt(coor[0])-1;
            int y = Integer.parseInt(coor[1])-1;
//            StdOut.print(x+"" ""+y);
            cell[x ][y ] = true;
            if (x - 1 >= 0 && cell[x - 1][y]) {
                uf.union(ID[x][y], ID[x - 1][y]);
            }
            if (x + 1 < num && cell[x + 1][y]) {
                uf.union(ID[x][y], ID[x + 1][y]);
            }
            if (y - 1 >= 0 && cell[x][y - 1]) {
                uf.union(ID[x][y], ID[x][y - 1]);
            }
            if (y + 1 < num && cell[x][y + 1]) {
                uf.union(ID[x][y], ID[x][y + 1]);
            }
//            int[] ans = new int[2];
//            ans[0] = x;
//            ans[1] = y;
            
            if (toptodown()){
                StdOut.print((x+1)+"",""+(y+1));
                co = true;
                break;
            }
            
        }

    if(!co)
        StdOut.print(-1);
//        StdOut.print(ans[0] + "","" + ans[1]);
    }

}

