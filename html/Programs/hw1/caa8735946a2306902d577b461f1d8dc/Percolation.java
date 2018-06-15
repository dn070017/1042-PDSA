public class Percolation {
    private int[] board;
    private boolean[] openGrid;
    private final int dim; 
    
    public Percolation(int n) {
        dim = n;
        board = new int[n*n];
        openGrid = new boolean[n*n];
        reset();
    } // end constructor
        
    public int[] getBoard() { 
        return board.clone(); 
    } // end func getBoard
        
    public int getDim() { 
        return dim; 
    } // end func getDim
     
    public int getBoardItem(int row, int col) {
        assert row > 0 & row <= dim;
        assert col > 0 & col <= dim;
        return board[(row-1) * dim + (col-1)];
    } // end func getBoardItem
    
    public boolean isOpen(int row, int col){
        return openGrid[(row-1) * dim + (col-1)];
    }
    public Bag getBoardNeighbor(int row, int col) {
        assert row > 0 & row <= dim;
        assert col > 0 & col <= dim;
        
        Bag<Integer> neighbor = new Bag<>();
        
        if (row > 1) {
            //StdOut.println(getBoardItem(row-1, col));
            if (isOpen(row-1, col)) {
                neighbor.add(getBoardItem(row-1, col));
            }
        }
        if (row < dim) {
            //StdOut.println(getBoardItem(row+1, col));
            if (isOpen(row+1, col)) {
                neighbor.add(getBoardItem(row+1, col));
            }
        }
        if (col > 1) {
            //StdOut.println();
            if (isOpen(row, col-1)) {
                neighbor.add(getBoardItem(row, col-1));
            }
        }
        if (col < dim) {
            //StdOut.println(getBoardItem(row, col+1));
            if (isOpen(row, col+1)) {
                neighbor.add(getBoardItem(row, col+1));
            }
        }
        return neighbor;
    } // end func getBoardNeighbor
        
    public void openBoardItem(int row, int col) {
        assert row > 1 & row <= dim;
        assert col > 1 & col <= dim;
        openGrid[(row-1) * dim + (col-1)] = true;
    } // end func setBoardItem
        
    public void reset(){
        for (int idx = 0; idx < dim*dim; idx++) {
            board[idx] = idx;
            openGrid[idx] = false;
        } // end loop
    } // end func reset
        
    public void display() {
        System.out.println(""--------------------------"");
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                System.out.printf(""%d "", getBoardItem(row, col));
            } // end inner for
            System.out.println();
        } // end outer for
        System.out.println(""--------------------------"");
    } // end display
    
    public static void main(String[] args) {
        // initialization
        String[] readLines = In.readStrings(""input01.txt"");
        int n = Integer.valueOf(readLines[0]);
        Percolation percolate = new Percolation(n);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n*n+2);
        int nodeUP = n*n;
        int nodeDW = n*n+1;               
        
        //
        for (int idx = 0; idx < n; idx++){
            //System.out.println(uf.connected(nodeUP, idx));
            uf.union(nodeUP, idx);
            //System.out.println(uf.connected(nodeUP, idx));
        } 
        for (int idx = n*(n-1); idx < n*n; idx++){
            uf.union(nodeDW, idx);
        }
        
        //
        boolean flag = false;
        for (int idx = 1; idx < readLines.length; idx++){
            String[] line = readLines[idx].split("","");
            int row = Integer.valueOf(line[0]);
            int col = Integer.valueOf(line[1]);
            int node = percolate.getBoardItem(row, col);
            
            //
            percolate.openBoardItem(row, col);
            
            //
            Bag<Integer> neighbors = percolate.getBoardNeighbor(row, col);
            //System.out.printf(""%d %d\n"", row, col);
            
            //
            for (Integer t : neighbors) {  
                //StdOut.println(t);
                uf.union(node, t);
            //    StdOut.print("" "");
            }
            if(uf.connected(nodeUP, nodeDW)){
                StdOut.printf(""%d,%d"", row, col);
                flag = true;
                break;
            }
            //StdOut.println(uf.connected(nodeUP, nodeDW));
            //StdOut.println();
                    
        } // end loop for
        if (!flag){
            StdOut.print(-1);
        }
    } // end main
} // end class Percolation
