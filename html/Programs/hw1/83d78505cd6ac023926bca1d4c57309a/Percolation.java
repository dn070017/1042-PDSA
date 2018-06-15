public class Percolation {
 private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUFBackEnd;
    private int row = 0;
    private int column = 0;
    private boolean[] open = null;
    private boolean createFlow = false;
    private boolean ijIsFull = false;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        // [NOTE] Check that IllegalArgumentException is thrown if N <= 0 in
        // constructor
        if (N <= 0)
            throw new java.lang.IllegalArgumentException();
        // WQUUF for Percolation
        // weightedQuickUnionUF[N * N] virtual top site
        // weightedQuickUnionUF[N * N + 1] virtual bottom site
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        // [NOTE] WQUUF for Full, without virtual bottom site
        this.weightedQuickUnionUFBackEnd = new WeightedQuickUnionUF(N * N + 1);
        this.row = N;
        this.column = N;
        this.open = new boolean[N * N + 2];
        open[N * N] = true;
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        validateIndex(i, j);
        if (!this.open[matchCoordinateToIndex(i, j)]) {
            this.open[matchCoordinateToIndex(i, j)] = true;
            // initialize ij is full
            ijIsFull = false;
            // check adjacent open sites and the site's position
            checkPositionAdjacent(i, j, this.row);
        }

    }

    private void validateIndex(int i, int j) {
        if (i <= 0 || i > this.row)
            throw new IndexOutOfBoundsException(""row index i out of bounds"");
        if (j <= 0 || j > this.column)
            throw new IndexOutOfBoundsException(""column index j out of bounds"");
    }

    private void checkPositionAdjacent(int i, int j, int N) {
        if (i == 1)
        // set it as a full site
        {
            // connect it with virtual top site
            weightedQuickUnionUF.union(this.row * this.column, (matchCoordinateToIndex(i, j)));
            weightedQuickUnionUFBackEnd.union(this.row * this.column, (matchCoordinateToIndex(i, j)));
            // it creates liquid flow
            ijIsFull = true;
            createFlow = true;
        }
        // if-else for time saving
        else
            checkAdjacentUp(i, j);
        if (i == N) {
            //// connect it with bottom top site
            weightedQuickUnionUF.union(this.row * this.column + 1, (matchCoordinateToIndex(i, j)));
        } else
            checkAdjacentDown(i, j);
        if (j > 1)
            checkAdjacentLeft(i, j);
        if (j < N)
            checkAdjacentRight(i, j);
    }

    private void checkAdjacentLeft(int i, int j) {
        unionAdjacent(i, j, i, j - 1);
    }

    private void checkAdjacentRight(int i, int j) {
        unionAdjacent(i, j, i, j + 1);
    }

    private void checkAdjacentUp(int i, int j) {
        unionAdjacent(i, j, i - 1, j);
    }

    private void checkAdjacentDown(int i, int j) {
        unionAdjacent(i, j, i + 1, j);
    }

    private void unionAdjacent(int i, int j, int k, int l) {
        // if the adjacent one is also open
        if (this.open[matchCoordinateToIndex(k, l)]) {
            // if i,j is full
            // loop 1 for unionAdjacent, ijIsFull = (i == 1)
            // loop 2-4 for unionAdjacent, ijIsFull is the result in the former
            // loop
            boolean full = ijIsFull;
            // if i,j is not full but k,l is full
            if (!full) {
                full = isFull(k, l);
            }
            // connect them
            weightedQuickUnionUF.union((matchCoordinateToIndex(k, l)), (matchCoordinateToIndex(i, j)));
            weightedQuickUnionUFBackEnd.union((matchCoordinateToIndex(k, l)), (matchCoordinateToIndex(i, j)));
            if (full) {
                // it creates liquid flow!
                createFlow = true;
                ijIsFull = true;
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validateIndex(i, j);
        return this.open[matchCoordinateToIndex(i, j)];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validateIndex(i, j);
        // is the site even opened?
        if (!this.isOpen(i, j))
            return false;
        // if the root is full
        // save WeightedQuickUnionUF calls
        if (weightedQuickUnionUFBackEnd.connected(matchCoordinateToIndex(i, j), this.row * this.column))
            return true;
        else if (weightedQuickUnionUFBackEnd.connected(weightedQuickUnionUFBackEnd.find(matchCoordinateToIndex(i, j)),
                this.row * this.column))
            return true;
        else
            return false;
    }

    // does the system percolate?
    public boolean percolates() {
        // no liquid flow, no need to check percolation
        if (!createFlow)
            return false;
        if (weightedQuickUnionUF.connected(this.row * this.column, this.row * this.column + 1)) {
            return true;
        }
        createFlow = false;
        return false;
    }

    // get site index from coordinates
    // (1,1) -> 0; N = 8 (8,8) -> 63
    private int matchCoordinateToIndex(int i, int j) {
        return this.column * (i - 1) + j - 1;
    }

    public static void main(String[] args) {

    }

}
