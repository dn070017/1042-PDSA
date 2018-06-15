
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {
 
    private boolean[][] grid;
    private int gridSize;
    private WeightedQuickUnionUF unionUF;
    private WeightedQuickUnionUF backWash;
    private final int top;
    private final int bottom;
 

    public Percolation(int N) {          
        if (N <= 0) {
            throw new IllegalArgumentException(""The input N is illegal!"");
        }
        grid = new boolean[N][N];
        gridSize = N;
        top = 0;
        bottom = N * N + 1;
        unionUF = new WeightedQuickUnionUF(N * N + 1);
        backWash = new WeightedQuickUnionUF(N * N + 2);
    }
 
    public void open(int i, int j) {     
        validateArray(i, j);
        grid[i - 1][j - 1] = true;
        if (i == 1) {
            unionUF.union(top, xyTo1D(i, j));
            backWash.union(top, xyTo1D(i, j));
        }
 
        if (i == gridSize) {
            backWash.union(xyTo1D(i, j), bottom);
        }
 
        if (i > 1 && isOpen(i - 1, j)) {
            unionUF.union(xyTo1D(i, j), xyTo1D(i - 1, j));
            backWash.union(xyTo1D(i, j), xyTo1D(i - 1, j));
        }
 
        if (i < gridSize && isOpen(i + 1, j)) {
            unionUF.union(xyTo1D(i, j), xyTo1D(i + 1, j));
            backWash.union(xyTo1D(i, j), xyTo1D(i + 1, j));
        }
 
        if (j > 1 && isOpen(i, j - 1)) {
            unionUF.union(xyTo1D(i, j), xyTo1D(i, j - 1));
            backWash.union(xyTo1D(i, j), xyTo1D(i, j - 1));
        }
 
        if (j < gridSize && isOpen(i, j + 1)) {
            unionUF.union(xyTo1D(i, j), xyTo1D(i, j + 1));
            backWash.union(xyTo1D(i, j), xyTo1D(i, j + 1));
        }
    }
    
    
    public boolean isOpen(int i, int j) { 
        validateArray(i, j);
        return grid[i - 1][j - 1];
    }
 
    public boolean isFull(int i, int j) {   
        validateArray(i, j);
        return unionUF.connected(xyTo1D(i, j), top);
    }
 
    public boolean percolates() {        
        return backWash.connected(top, bottom);
    }
 
    private int xyTo1D(int i, int j) {
        return (i - 1) * gridSize + j;
    }
 
    private void validateArray(int i, int j) {
        if (i <= 0 || j <= 0 || i > gridSize || j > gridSize) {
            throw new IndexOutOfBoundsException(""index: ("" + i + "", "" + j + "") are out of bound!"");
        }
    }
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");

            int N = Integer.parseInt(data[0]);

            Percolation percolation = new Percolation(N);
            In in = new In(args[0]);
            while (!in.isEmpty()) {
            String[] da = br.readLine().split("","");
            int p = Integer.parseInt(da[0]);
            int q = Integer.parseInt(da[1]);
            percolation.open(p,q);
            if (percolation.percolates()==true){
                StdOut.println(p+"",""+q);
                break;}
            }

        }
}
}

