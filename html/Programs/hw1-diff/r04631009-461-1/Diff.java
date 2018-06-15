
/**
 * @author Cyuan
 */
import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {

    /**
     * @param args the command line arguments
     */
    private static int convert(int x, int y, int size) {
        int p = x * size + y - 1;
        return p;
    }

    public static void main(String[] args) throws Exception {
//----------------Read the first line and store the matrix size---------------//
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String FirstLine = br.readLine();
            int size = Integer.parseInt(FirstLine);
            QuickUnionUF uf = new QuickUnionUF(size * size + 2);
            int a = 0;
            int b = 0;
            int xold = 0;
            int yold = 0;
            int end = 0;
            int g =1 ;
//----------------Set the matrix and initialized to be zero-------------------//
            int[][] matrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = 0;
                }
            }
//----------------------------------------------------------------------------//

            String str = null;
            while ((str = br.readLine()) != null) {
                String[] data = str.split("","");

                int x = Integer.parseInt(data[0]) - 1; //matrix row
                int y = Integer.parseInt(data[1]) - 1; //matrix column

                //matrix[x-1][y-1]=0 (block), 1 (open) , 2 (full)
                //open the read site
                matrix[x][y] = 1;
                //test for neighbor
                //when the site is on the top

                int p = convert(x, y, size);

                // sudo node 0,size*size+1
                //TOP
                if (x == 0) {
                    uf.union(p, 0);
                }
                //Bottom
                if (x == size - 1) {
                    uf.union(p, size * size + 1);
                }
                //
                if (x > 0 && x <= size) {
                    int pu = convert(x, y + 1, size);
                    if (1 == matrix[x - 1][y]) {
                        uf.union(p, pu);
                    }
                }
                //
                if ((x + 2) <= size && (x + 2) > 0) {
                    int pl = convert(x + 2, y + 1, size);
                    if (1 == matrix[x + 1][y]) {
                        uf.union(p, pl);
                    }
                }

                if ((y) > 0 && (y) <= size) {
                    int pl = convert(x + 1, y, size);
                    if (1 == matrix[x][y - 1]) {
                        uf.union(p, pl);
                    }
                }

                if ((y + 2) <= size && (y + 2) > 0) {
                    int pr = convert(x + 1, y + 2, size);
                    if (1 == matrix[x][y + 1]) {
                        uf.union(p, pr);
                    }
                }

                if ((uf.connected(0, size * size + 1)) && (0 == end)) {
                    xold = x;
                    yold = y;
                    end = 1;
                     g = 2;
                }

                // check the N row 
                //for (int j = 0; j < size; j++) {
                //    int i = size - 1;
                //   if (matrix[i][j] == 2) {
                //       System.out.println(Integer.parseInt(data[0]) + "","" + Integer.parseInt(data[1]));
                //System.exit(0);
                //}
                //}
            }
            
            switch(g){
                 case 1:
                     System.out.println(-1);
                     break;
                 case 2:
                     System.out.println(xold+"",""+yold);
                     break;
                 default:
             }

            //System.out.println(""-1"");
            //show for debug

            //for (int i = 0; i < size; i++) {
            //    for (int j = 0; j < size; j++) {
            //        System.out.print(matrix[i][j] + "" "");
            //    }
            //    System.out.println("""");
            //}

        }

    }

}

