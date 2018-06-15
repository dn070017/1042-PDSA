import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Eric
 */
public class Percolation {
    
    private static int[] idd;
    public static void construct(int N, int n){
        idd = new int[N+2*n+2];
        for (int i=0; i<N+2*n+2; i++)
            idd[i]=i;
        for (int i=1; i<=n; i++){
            union(i,0);
            union(N+2*n+1-i, N+2*n+2-1);
            }
    }

    public static boolean connected(int p, int q) {
        return idd[p] == idd[q];
    }
    public static void union(int p, int q) {
        int pid = idd[p];
        int qid = idd[q];
        for (int i=0; i<idd.length; i++)
            if (idd[i] == pid) idd[i] = qid;
    }

    
    public static void check_union(int[][] m,int n,int x_axis,int y_axis){
        int nx = x_axis-1;
        int ny = y_axis-1;
        if (x_axis == 1) 
            union(y_axis+n, y_axis);
        if (x_axis == n)
            union(nx*n+ny+n+1, nx*n+ny+2*n+1);
        if (m[x_axis+1][y_axis] == 1)
           union(n*(nx+1)+ny+n+1, nx*n+ny+n+1);
        if (m[x_axis-1][y_axis] == 1)
           union(n*(nx-1)+ny+n+1, nx*n+ny+n+1);  
        if (m[x_axis][y_axis+1] == 1)
           union(nx*n+ny+1+n+1, nx*n+ny+n+1);
        if (m[x_axis][y_axis-1] == 1)
            union(nx*n+ny-1+n+1, nx*n+ny+n+1); 
    }
    
    public static boolean percolation(int N, int n) {
        if (idd[0] == idd[N+2*n+1])
            return (true);
        else
            return (false);
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            // read first line (matrix size)
            String[] data = br.readLine().split("","");
            int sz = Integer.parseInt(data[0]);
            
            // initilization of a coordinate array in Java
            int[][] coord = new int[sz+2][sz+2];
            for (int i=0; i<sz+2; i++)
                for (int j=0; j<sz+2; j++) 
                    coord[i][j] = 0;

            // Initialize the number to id
            int N = sz*sz;
            construct(N, sz);
            
            String str = null;
            while ((str = br.readLine())!=null){
                String[] data2 = str.split("","");
                int x_crd = Integer.parseInt(data2[0]);
                int y_crd = Integer.parseInt(data2[1]);
                coord[x_crd][y_crd] = 1;
                check_union(coord, sz, x_crd, y_crd);
                if (percolation(N, sz)==true){
                    System.out.println(x_crd + "","" + y_crd);
                    break;
                }
            }

            if ((str=br.readLine())==null && percolation(N,sz)==false)
                System.out.println(""-1"");
            
        }
    }
}
