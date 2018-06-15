import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author SimonHan
 */
public class Percolation {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String data = br.readLine();

            // store the first integer in variable stringCount (number of announced strings)
            int N = Integer.parseInt(data);

            // initilization of aUF uf = new UF(N*N+2) ; String array in Java
            String[][] matrix = new String[N][N];

            // printf in Java (you should comment out or delete this in your final submission           
            int[] id = new int[N * N + 1];
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N * N + 2);
            String Data = new String() ;             
            String[] Open = new String[2] ;

            while ((Data = br.readLine()) != null) {
                Open = Data.split("","") ;
                if(Open[0].isEmpty()){
                    System.out.printf(""%d\n"", -1);
                    return ;
                }
                int x = Integer.parseInt(Open[0]);
                int y = Integer.parseInt(Open[1]);
                if(x > N || y > N || x < 1 || y < 1){
                    System.out.printf(""%d\n"", -1);
                    return ;
                }
                id[N * (x - 1) + y] = N * (x - 1) + y;
                if (x != 1 && x != N && y != 1 && y != N) {
                    if (id[N * (x - 1) + y - N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - N);
                        }
                    }
                    if (id[N * (x - 1) + y + N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + N);
                        }
                    }
                    if (id[N * (x - 1) + y - 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - 1);
                        }
                    }
                    if (id[N * (x - 1) + y + 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + 1);
                        }
                    }
                } else if (x == 1 && y == 1) {
                    uf.union(0, N * (x - 1) + y);
                    if (id[N * (x - 1) + y + N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + N);
                        }
                    }
                    if (id[N * (x - 1) + y + 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + 1);
                        }
                    }
                } else if (x == 1 && y == N) {
                    uf.union(0, N * (x - 1) + y);
                    if (id[N * (x - 1) + y + N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + N);
                        }
                    }
                    if (id[N * (x - 1) + y - 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - 1);
                        }
                    }
                } else if (x == N && y == 1) {
                    uf.union(N * N + 1, N * (x - 1) + y);
                    if (id[N * (x - 1) + y - N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y -N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y -N);
                        }
                    }
                    if (id[N * (x - 1) + y + 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + 1);
                        }
                    }
                } else if (x == N && y == N) {
                    uf.union(N * N + 1, N * (x - 1) + y);
                    if (id[N * (x - 1) + y - N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - N);
                        }
                    }
                    if (id[N * (x - 1) + y - 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - 1);
                        }
                    }
                } else if (x == 1) {
                    uf.union(0, N * (x - 1) + y);
                    if (id[N * (x - 1) + y + N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + N);
                        }
                    }
                    if (id[N * (x - 1) + y - 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - 1);
                        }
                    }
                    if (id[N * (x - 1) + y + 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + 1);
                        }
                    }
                } else if (x == N) {
                    uf.union(N * N + 1, N * (x - 1) + y);
                    if (id[N * (x - 1) + y - N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - N);
                        }
                    }
                    if (id[N * (x - 1) + y - 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - 1);
                        }
                    }
                    if (id[N * (x - 1) + y + 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + 1);
                        }
                    }
                } else if (y == 1) {
                    if (id[N * (x - 1) + y - N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y -N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y -N);
                        }
                    }
                    if (id[N * (x - 1) + y + N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + N);
                        }
                    }
                    if (id[N * (x - 1) + y + 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + 1);
                        }
                    }
                } else if (y == N) {
                    if (id[N * (x - 1) + y - N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y -N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y -N);
                        }
                    }
                    if (id[N * (x - 1) + y + N] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y + N)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y + N);
                        }
                    }
                    if (id[N * (x - 1) + y - 1] != 0) {
                        if (!uf.connected(N * (x - 1) + y, N * (x - 1) + y - 1)) {
                            uf.union(N * (x - 1) + y, N * (x - 1) + y - 1);
                        }
                    }
                }
                if (uf.connected(0, N * N + 1)) {
                    String s = Open[0]+"",""+Open[1];
                    System.out.println(s);
                    break;
                }
            }
            if (!uf.connected(0, N * N + 1)) {
                System.out.printf(""%d\n"", -1);
            }
        }
    }
}

