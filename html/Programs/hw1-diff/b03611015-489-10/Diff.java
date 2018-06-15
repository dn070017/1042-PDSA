

import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Steven
 */
public class Percolation {

    /**
     * @param args the command line arguments
     *
     *
     * by union!!!!!!!!!!!!!!
     *
     *
     *
     *
     */
    private static int[] id;
    private static int[] matrix;
//    0 as black, 1 as white
    static int N;
    static int site;

    public Percolation(int N) {
//        initial the id&bw of the mitrix
        id = new int[N * N + 2];
        matrix = new int[N * N + 2];
//       [0] is top, [N+1] is bottom
        for (int i = 0; i < N * N + 2; i++) {
            id[i] = i;
            matrix[i] = 0;
        }
//       martix begin from 1 end at N
        matrix[0] = 1;
        matrix[N * N + 1] = 1;
    }

    public static void open(int row, int col) {
//        open a new site and check the connection
        site = (row - 1) * N + col;
        matrix[site] = 1;

        if (site <= N) {
            union(site, 0);
//          connect the top     

            if (matrix[site + N] == 1) {
                union(site, site + N);
            }
//            connect the one below

        } else if (site > N * N - N) {
            union(site, N * N + 1);
//          connect the bottom 

            if (matrix[site - N] == 1) {
                union(site, site - N);
            }
//            connect the one above

        } else {
//          the sites between
            if (matrix[site - N] == 1) {
                union(site, site - N);
            }
            if (matrix[site - 1] == 1) {
                union(site, site - 1);
            }
            if (matrix[site + 1] == 1) {
                union(site, site + 1);
            }
            if (matrix[site + N] == 1) {
                union(site, site + N);
            }
        }

    }

    public static void union(int A, int B) {
//        把id 設成最小的位子
        int a = root(A);
        int b = root(B);

        if (a < b) {
            id[b] = a;
        } else {
            id[a] = b;
        }
    }

    private static int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public static boolean connection(int A, int B) {
        return root(A) == root(B);
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String data = br.readLine();
            N = Integer.parseInt(data);

            Percolation matrix = new Percolation(N);

            String[] randc = new String[2];
            int row = 0;
            int col = 0;
            String brs;
            
            while (Boolean.TRUE) {

                brs = br.readLine();
                if(connection(0, N * N + 1)){
                     System.out.print(row + "","" + col);
                     break;
                }
                if (brs == null) {
                    System.out.print(-1);
                    break;
                }
                randc = brs.split("","");
                row = Integer.parseInt(randc[0]);
                col = Integer.parseInt(randc[1]);

                open(row, col);
            }

//            if (br.readLine() != null) {
//                System.out.print(row + "","" + col);
//            }

        }
    }

}
