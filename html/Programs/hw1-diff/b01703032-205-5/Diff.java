
import java.io.BufferedReader;
import java.io.FileReader;

public class Percolation {
 
    private boolean[][] grid;
    private int gridSize;
    private WeightedQuickUnionUF unionUF;
    private WeightedQuickUnionUF backWash;
    private final int top;
    private final int bottom;
 
    /**
     * Construction Method, declare two WQUUF, and two virtual node
     *
     * @param N the scale of the input
     * @throws java.lang.IllegalArgumentException if N < 0
     */
    public Percolation(int N) {               // create N-by-N grid, with all sites blocked
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

    public void open(int i, int j) {         // open site (row i, column j) if it is not open already
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
    
    
    public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
        validateArray(i, j);
        return grid[i - 1][j - 1];
    }
 
    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        validateArray(i, j);
        return unionUF.connected(xyTo1D(i, j), top);
    }
 
    public boolean percolates() {            // does the system percolate?
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
 
    public static void main(String[] args) throws Exception {  // test client (optional)
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        String[] data = br.readLine().split("","");
        int stringCount = Integer.parseInt(data[0]);
        Percolation percolation = new Percolation(stringCount);
         String temp=null;
           while((temp=br.readLine())!=null)
           {
            String[] array;
                array = temp.split("","");
                int i = Integer.parseInt(array[0]);
                int j = Integer.parseInt(array[1]);
               percolation.open(i,j);
               if(percolation.percolates()==true)
               {
                   System.out.println(i + "","" +j);
                   break;
               }

            }
        }



    }
 
}

