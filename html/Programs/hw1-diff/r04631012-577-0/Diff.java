
import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {

    private static final int FULL = 0;     // Makes it easier to keep track of the
.

.
.
    // Begins with default initialization: FULL (0)

.
    // We use union(), find(), connected(),
.

    /**
     * Constructor initializes two dimensional array of integers with default
.
     *
     * The WeightedQuickUnionUF object is initialized to a size that will
     * contain one element for each site in the grid (N*N), plus two more to
     * represent ""imaginary"" sites at the top and bottom of the grid that will
.
     *
     * @param n the number of rows and column in the grid
     */
    public Percolation(int n) {
        N = n;
        grid = new int[N][N];
        ufHelper = new UF((N * N) + 2);
    } // end constructor

    /**
     * Open site (row i, column j) by setting site to OPEN and calling the union
.
     *
     * @param i the integer representing the site location on the x axis
     * @param j the integer representing the site location on the y axis
     * @throws IndexOutOfBoundsException if the values for i and j are off the
     * grid
     */
    public void open(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException(""Illegal parameter value."");
        }
        grid[row][col] = OPEN;
.
            ufHelper.union(0, xyTo1D(row, col));
        }
        if (row == N - 1) { // If it's on the bottom row, connect to imaginary
.
            ufHelper.union((N * N) + 1, xyTo1D(row, col));
        }
        if ((row + 1) < N) { // Make sure we don't fall off the grid
            if (grid[row + 1][col] == OPEN) {
                ufHelper.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }
        if ((row - 1) >= 0) { // Make sure we don't fall off the grid
            if (grid[row - 1][col] == OPEN) {
                ufHelper.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
        }
        if ((col + 1) < N) { // Make sure we don't fall off the grid
            if (grid[row][col + 1] == OPEN) {
                ufHelper.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
        if ((col - 1) >= 0) { // Make sure we don't fall off the grid
            if (grid[row][col - 1] == OPEN) {
                ufHelper.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
        }
    } // end open()

    /**
     * Is site (row i, column j) open?
     *
     * @param i the integer representing the site location on the x axis
     * @param j the integer representing the site location on the y axis
     * @return true if the site at (i, j) is OPEN
     * @throws IndexOutOfBoundsException if the values for i and j are off the
     * grid
     */
    public boolean isOpen(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException(""Illegal parameter value."");
        }
        return grid[row][col] == OPEN;
    } // end isOpen()

    /**
     * Is site (row i, column j) full?
     *
     * @param i the integer representing the site location on the x axis
     * @param j the integer representing the site location on the y axis
     * @return true if the site at (i, j) is FULL
     * @throws IndexOutOfBoundsException if the values for i and j are off the
     * grid
     */
    public boolean isFull(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IndexOutOfBoundsException(""Illegal parameter value."");
        }
        return grid[row][col] == FULL;
    } // end isFull()

    /**
     * Does the system percolate? Checks to see if the imaginary site at
     * location 0 in the union-find object is in the same set as the imaginary
     * site at location N*N+1 in the union-find object. These two sites are
     * imaginary; they are not actually represented in the grid, although they
     * are represented in the union-find object. They are ""located"" at the top
     * and the bottom of the grid, and each connects to all the sites
.
     *
     * @return true if open path from the bottom of the grid to the top exists
     */
    public boolean percolates() {
        return ufHelper.connected(0, (N * N) + 1);
    }

    public static void main(String[] args) throws Exception {
        Percolation Pe = new Percolation();

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("","");

            int MatrixSize = Integer.parseInt(data[0]);
            Pe.Percolation(MatrixSize);

            do {
                int row = 0, column = 0;
                String[] data2 = br.readLine().split("","");
                row = Integer.parseInt(data2[0]);
                column = Integer.parseInt(data2[1]);
                Pe.open(row, column);
                if (Pe.percolates()) {
                    System.out.println(""row"" + "","" + ""column"");
                    break;
                }
                }while (br.readLine() != null);
                    if (!Pe.percolates()) {
                    System.out.println(""-1"");
                }
                       
        }

    
}

