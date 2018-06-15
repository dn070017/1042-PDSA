
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author clint
 */
public class LabelCC {
    private final int dim;
    private Board_ID    board_id;
    private Board_Label board_label;
    private Board_Open  board_open;
    private UF uf;
    //private QuickFind uf;
    
    public LabelCC (int n) {
        dim = n;
        board_id = new Board_ID(n);
        board_label = new Board_Label(n);
        board_open = new Board_Open(n);
        uf = new UF(n*n);
        //uf = new QuickFind(n*n); // it turns out that for this problem quickUnion (even with path compression) is much more slower than QuickFind 
    } // end constructor
    
    public int getLabel(int row, int col) {
        return board_label.getLabel(row, col);
    } // end func getLabel
    
    public void setLabel(int row, int col, int value) {
        board_label.setLabel(row, col, value);
    } // end func setLabel
    
    public void setLabelfromRoot(int row, int col, int rootId) {
        int rootRow = rootId / dim + 1;
        int rootCol = rootId % dim + 1;
        //System.out.println(row + "" "" + col + "" "" +getLabel(row, col) + "" "" + rootId + "" "" + rootRow + "" "" + rootCol);
        setLabel(row, col,
                 board_label.getLabel(rootRow, rootCol));
    } // end func setLabelfromRoot
    
    public void setBlock(int row, int col) {
        board_open.setBlock(row, col);
    } // end func setBlock 
    
    public boolean isBlock(int row, int col) {
        return board_open.isBlock(row, col);
    } // end func isOpen
    
    public boolean isOpen(int row, int col) {
        return board_open.isOpen(row, col);
    } // end func isOpen
    
    public String checkCase(Boolean isOpenUp, Boolean isOpenLf) {
        // case 1: up left neighbors not open or not exist
        // case 2: only up neighbor open and exist
        // case 3: only left neighbor open and exist
        // case 4: up left neighbors exist and open
        String whichCase = """";
        
        if (isOpenUp == null && isOpenLf == null) {whichCase = ""1"";}
        
        if (isOpenUp != null && isOpenLf == null) {
            if (isOpenUp){whichCase = ""2"";}
            else         {whichCase = ""1"";}
        } // end if
        
        if (isOpenUp == null && isOpenLf != null) {
            if (isOpenLf){whichCase = ""3"";}
            else         {whichCase = ""1"";}
        } // end if
        
        if (isOpenUp != null && isOpenLf != null) {
            if (isOpenUp == true  && isOpenLf == true)  {whichCase = ""4"";}
            if (isOpenUp == true  && isOpenLf == false) {whichCase = ""2"";}
            if (isOpenUp == false && isOpenLf == true)  {whichCase = ""3"";}
            if (isOpenUp == false && isOpenLf == false) {whichCase = ""1"";}
        } // end if
        return whichCase;
    } // end func checkCase
    
    public void passFirst() {
        int label = 1;
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                
                // check if the grid is open
                if (isBlock(row, col)) {
                    continue;
                } // end if
                
                // if open, check neighbor
                Boolean isOpenUp = board_open.getNeighbor(row, col, ""UP"");
                Boolean isOpenLf = board_open.getNeighbor(row, col, ""LEFT"");
                Integer labelUp  = board_label.getNeighbor(row, col, ""UP"");
                Integer labelLf  = board_label.getNeighbor(row, col, ""LEFT"");
                Integer id       = board_id.getId(row, col);
                Integer idUp     = board_id.getNeighbor(row, col, ""UP"");
                Integer idLf     = board_id.getNeighbor(row, col, ""LEFT"");
                
                // case 1: up left neighbors not open or not exist
                // case 2: only up neighbor open and exist
                // case 3: only left neighbor open and exist
                // case 4: up left neighbors exist and open        
                String gridCase = checkCase(isOpenUp, isOpenLf);
                
                switch (gridCase) {
                    case ""1"": 
                        //System.out.print(""1 "");
                        setLabel(row, col, label++);
                        break;
                    case ""2"": 
                        //System.out.print(""2 "");
                        uf.union(idUp, id);
                        setLabel(row, col, labelUp);
                        break;
                    case ""3"": 
                        //System.out.print(""3 "");
                        uf.union(idLf, id);
                        setLabel(row, col, labelLf);
                        break;
                    case ""4"": 
                        //System.out.print(""4 "");
                        if (labelUp <= labelLf) {
                            uf.union(idUp, idLf);
                            uf.union(idUp, id);
                            setLabel(row, col, labelUp);
                        } else {
                            uf.union(idLf, idUp);
                            uf.union(idLf, id);
                            setLabel(row, col, labelLf);
                        } // end if-else
                        break;
                    default: 
                        System.out.println(
                            ""Error: LabelCC omit the case: "" + 
                            ""("" + row + "", "" + col + "")"");
                } // end switch
            } // end inner loop
        } // end outer loop
    } // end func passFirst
    
    public void passSecond() {
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                // check if the grid is open
                if (isBlock(row, col)) {
                    continue;
                } // end if
                int id = board_id.getId(row, col);
                int root = uf.root(id);
                //int root = uf.find(id);
                setLabelfromRoot(row, col, root);
            } // end inner loop
        } // end outer loop
    } // end func passSecond
    
    public void displayOpen(){ 
        System.out.println(""---Display: Open---------"");
        board_open.display();  }
    public void displayLabel() { 
        System.out.println(""---Display: Label--------"");
        board_label.display(); }
    public void displayID() { 
        System.out.println(""---Display: ID-----------"");
        board_id.display();    }
    /*
    public void displayRoot()  {
        System.out.println(""---Display: Root---------"");
        System.out.println(""-------------------------"");
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                System.out.print(uf.root(board_id.getId(row, col)) + "" "");
            } // end inner loop
            System.out.println();
        } // end outer loop
        System.out.println(""-------------------------"");
    } // end func displayRoot
    //*/
    public void displayCase()  {
        System.out.println(""-------------------------"");
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {            
                // check if the grid is open
                if (isBlock(row, col)) {
                    System.out.print(""X "");
                    continue;
                } // end if
                
                // if open, check neighbor
                Boolean isOpenUp = board_open.getNeighbor(row, col, ""UP"");
                Boolean isOpenLf = board_open.getNeighbor(row, col, ""LEFT"");
                
                // case 1: up left neighbors not open or not exist
                // case 2: only up neighbor open and exist
                // case 3: only left neighbor open and exist
                // case 4: up left neighbors exist and open        
                String gridCase = checkCase(isOpenUp, isOpenLf);
                System.out.print(gridCase + "" "");
            } // end inner loop
            System.out.println();
        } // end outer loop
        System.out.println(""-------------------------"");
    } // end func displayCase
    public void displayUF() {uf.display();}
    public void displayUFRecord() {
        int numSum = 0;
        System.out.println(""\nRecordRoot:"");
        for(int i: uf.recordRoot){
            System.out.print(i + "" "");
            numSum += i;
        } // end loop
        System.out.println();
        System.out.println(""Size: "" + uf.recordRoot.size());
        System.out.println(""Sum:  "" + numSum);
        System.out.println();
        
        numSum = 0;
        System.out.println(""RecordUnion:"");
        for(int i: uf.recordUnion){
            System.out.print(i + "" "");
            numSum += i;
        } // end loop
        System.out.println();
        System.out.println(""Size: "" + uf.recordRoot.size());
        System.out.println(""Sum:  "" + numSum);
        System.out.println();
        
    } // end func displayUFRecord
    
    public static void main(String[] args) {
        // testing classes
        //Board.main(args);
        //Board_Open.main(args);
        //Board_Label.main(args);
        //Board_ID.main(args);
        //UF.main(args);
        
        // read in the content of a file
        String[] readLines = In.readStrings(args[0]);
        
        // initialization
        String[] line0 = readLines[0].split("","");
        int N         = Integer.valueOf(line0[0]); // prepare for N-by-N grid
        int targetRow = Integer.valueOf(line0[1]); // row of target site: (row, col)
        int targetCol = Integer.valueOf(line0[2]); // col of target site: (row, col)
        LabelCC labelcc = new LabelCC(N);
        
        // iterate through the file
        for (String s: Arrays.copyOfRange(readLines, 1, readLines.length)){
            String[] line = s.split("","");
            //System.out.println(s);
            labelcc.setBlock(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
	} // end loop for
        
        if (labelcc.isBlock(targetRow, targetCol)) {
            System.out.print(0);
            return;
        } // end if
        
        labelcc.passFirst();
        //labelcc.displayLabel();
        
        labelcc.passSecond();
        System.out.print(labelcc.getLabel(targetRow, targetCol));
        //System.out.println(""-------------------------"");
        //labelcc.displayID();
        //labelcc.displayOpen();
        //labelcc.displayCase();
        //labelcc.displayLabel();
        //labelcc.displayUF();
        //labelcc.displayRoot();
        //labelcc.displayUFRecord();
    } // end main
} // end class LabelCC

class UF {
    private int[] id;
    // this is for monitoring the number of function call and loop
    public Queue<Integer> recordRoot  = new Queue<Integer>();
    public Queue<Integer> recordUnion = new Queue<Integer>();
    
    public UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    } // end contructor
    
    public boolean connected(int p, int q) { 
        return root(p) == root(q); 
    } // end func connected
    
    public int root(int i) {
        int recordLoop = 0;
        
        // Follow links to find a root
        while (i != id[i]) {
            recordLoop++;
            id[i] = id[id[i]]; // path compression
            i = id[i];         // search upper node
        } // end while
        
        recordRoot.enqueue(recordLoop);
        recordUnion.enqueue(0);
        
        return i; 
    } // end func root
    
    public void union(int p, int q) {
        recordUnion.enqueue(1);
        
        // Parent: p ; Child: q
        int i = root(p);     // component of p
        int j = root(q);     // component of q
        if (i == j) return;  // if same root, same component
        id[j] = i;           // link component q under component p
                             // Parent: i
                             // Child:  j
    } // end func union
    public void display(){
        System.out.println(Arrays.toString(id));
    } // end func display
} // end class UF

class QuickFind {
    private int[] id;
    public Queue<Integer> recordRoot  = new Queue<Integer>();
    public Queue<Integer> recordUnion = new Queue<Integer>();
    
    public QuickFind(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }// end constructor
    
    public boolean connected(int p, int q)
    { return root(p) == root(q); }
    
    public int root(int p) {
        recordRoot.enqueue(1);
        return id[p]; 
    }
    
    public void union(int p , int q) {
        int pID = root(p);
        int qID = root(q);
        
        if (pID == qID) return;
        
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qID) id[i] = pID;
        }
        recordUnion.enqueue(id.length);
    } // end func union
    
    public void display(){
        System.out.println(Arrays.toString(id));
    } // end func display
    
} // end class Quickfind

class Board_ID {
    private final int dim;
    private final Board<Integer> board;
    
    public Board_ID (int n) {
        board = new Board<Integer>(n);
        dim = n;
        reset(); 
    } // end constructor
    
    public void setId(int row, int col, int value){
        board.setGrid(row-1, col-1, value);
    } // end func setId
    
    public Integer getId(int row, int col){
        return board.getGrid(row-1, col-1);
    } // end func getId
    
    public Integer getNeighbor(int row, int col, String direction){
        return board.getNeighbor(row-1, col-1, direction);
    } // end func getNeighbor
    
    private Board getBoard(){
        return board.copy();
    } // end func getBoard
    
    public boolean compare(Board_ID anotherBoard){
        return board.compare(anotherBoard.getBoard());
    } // end func compared
    
    public Board_ID copy() {
        Board_ID board_copy = new Board_ID(dim);
        
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                board_copy.setId(row, col, getId(row, col));
            } // end inner loop
        } // end outer loop
        return board_copy;
    } // end func copy
    
    public void reset() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                board.setGrid(row, col, row * dim + col);
            } // end inner loop
        } // end outer loop
    } // end func reset
    
    public void display() {
        board.display();
    } // end func display
} // end class Board_ID

class Board_Label {
    private final int dim;
    private Board<Integer> board;
    
    public Board_Label(int n){
        board = new Board<Integer>(n);
        dim = n;
        reset(); 
    }  // end constructor
    
    public Integer getLabel(int row, int col) {
        //if (row >= 1 && row <= dim && col >= 1 && col <= dim){
        return board.getGrid(row-1, col-1);
        //} // end if
    } // end func getLabel
    
    public void setLabel(int row, int col, int value) {   
        //if (row >= 1 && row <= dim && col >= 1 && col <= dim){
        board.setGrid(row-1, col-1, value);
        //} // end if
    } // end func setLabel 
    
    public Boolean isLabel(int row, int col) {
        if (row >= 1 && row <= dim && col >= 1 && col <= dim){
            if (board.getGrid(row-1, col-1) == -1) { return false; } 
            else                                   { return true; }
        } // end if-else
        //System.out.println(""Error: \n\tClass Board_Label \n\tfunction isLabel \n\tMessage: Out of Boundary"");
        return null;
    } // end func isLabel
    
    public Integer getNeighbor(int row, int col, String direction) {
        return board.getNeighbor(row-1, col-1, direction);
    } // end func Integer
    
    private Board getBoard(){
        return board.copy();
    } // end func getBoard
    
    public boolean compare(Board_Label anotherBoard){
        return board.compare(anotherBoard.getBoard());
    } // end func compared
    
    public Board_Label copy() {
        Board_Label board_copy = new Board_Label(dim);
        
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                board_copy.setLabel(row, col, getLabel(row, col));
            } // end inner loop
        } // end outer loop
        return board_copy;
    } // end func copy
    
    public void reset(){
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                board.setGrid(row, col, -1);
            } // end inner loop
        } // end outer loop
    } // end func reset
    
    public void display() {
        board.display();
    } // end func display
} // end class Board_Label

class Board_Open {
    private final int dim;
    private Board<Boolean> board;
    
    public Board_Open(int n){
        board = new Board<Boolean>(n);
        dim = n;
        reset();    
    }  // end constructor
    
    public void setOpen(int row, int col) {
        //if (row >= 1 && row <= dim && col >= 1 && col <= dim){
        board.setGrid(row-1, col-1, true);
        //} // end if
    } // end func setOpen
    
    public void setBlock(int row, int col) {
        //if (row >= 1 && row <= dim && col >= 1 && col <= dim){
        board.setGrid(row-1, col-1, false);
        //} // end if
    } // end func setBlock
    
    public Boolean isOpen(int row, int col) {
        if (row >= 1 && row <= dim && col >= 1 && col <= dim){
            return board.getGrid(row-1, col-1) == true;    
        }
        //System.out.println(""Error: \n\tClass Board_Open \n\tfunction isOpen \n\tMessage: Out of Boundary"");
        return null;
    } // end func isOpen
    
    public Boolean isBlock(int row, int col) {
        if (row >= 1 && row <= dim && col >= 1 && col <= dim){
            return board.getGrid(row-1, col-1) == false;
        } // end if
        //System.out.println(""Error: \n\tClass Board_Open \n\tfunction isBlock \n\tMessage: Out of Boundary"");
        return null;
    } // end func isOpen
    
    public Boolean getNeighbor(int row, int col, String direction) {
        //if (row >= 1 && row <= dim && col >= 1 && col <= dim){
        return board.getNeighbor(row-1, col-1, direction);
        //} // end if
        //System.out.println(""Error: \n\tClass Board_Open \n\tfunction getNeighbor \n\tMessage: Out of Boundary"");
        //return null;
    } // end func getNeighbor
    
    private Board getBoard(){
        return board.copy();
    } // end func getBoard
    
    public boolean compare(Board_Open anotherBoard){
        return board.compare(anotherBoard.getBoard());
    } // end func compared
    
    public Board_Open copy() {
        Board_Open board_copy = new Board_Open(dim);
        
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                if (isOpen(row, col)) { board_copy.setOpen(row, col);  } 
                else                  { board_copy.setBlock(row, col); }
            } // end inner loop
        } // end outer loop
        return board_copy;
    } // end func copy
    
    public void reset() {
        // default: all open
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                board.setGrid(row, col, true);
            } // end inner loop
        } // end outer loop
    } // end func reset
    
    public void display() {
        System.out.println(""-------------------------"");
        for (int row = 1; row <= dim; row++) {
            for (int col = 1; col <= dim; col++) {
                if (isOpen(row, col)) {System.out.print(""1 "");}
                else                  {System.out.print(""0 "");}
            } // end inner loop
            System.out.println();
        } // end outer loop
        System.out.println(""-------------------------"");
    } // end func display
} // end class Board_Open

class Board<Item> {
    private final int dim;
    private Item[] board;
    
    public Board(int n) {
        dim = n;
        board = (Item[]) new Object[n*n];
    } // end constructor
    
    public int getDim(){ return dim; }
    
    public Item getGrid(int row, int col){
        if (row >= 0 && col >= 0 && row < dim && col < dim) {
            return board[row * dim + col];
        } // end if
        return null;
    } // end func getGrid
    
    public void setGrid(int row, int col, Item value){
        if (row >= 0 && col >= 0 && row < dim && col < dim) {
            board[row * dim + col] = value;
        } // end if
    } // end func setGrid
    
    public Item getNeighbor(int row, int col, String direction) {
        if (!(row >= 0 && col >= 0 && row < dim && col < dim)) {
            return null;
        } // end if
        
        if (direction.equals(""UP"")) {
            if (row > 0) { return getGrid(row-1, col); }
        } // end if
        
        if (direction.equals(""DOWN"")) {
            if (row < (dim - 1)) { return getGrid(row+1, col); }
        } // end if
        
        if (direction.equals(""LEFT"")) {
            if (col > 0) { return getGrid(row, col-1); }
        } // end if
        
        if (direction.equals(""RIGHT"")) {
            if (col < (dim - 1)) { return getGrid(row, col+1); }
        } // end if
        
        return null;
    } // end func getNeighbor
    
    public boolean compare(Board<Item> anotherBoard) { 
        if (anotherBoard.getDim() != dim) {
            return false;
        } // end if
        
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                if (getGrid(row, col) != anotherBoard.getGrid(row, col)) {
                    return false;
                } // end if
            } // end inner loop
        } // end outer loop
        
        return true;
    } // end func compare
    
    public Board copy(){
        Board<Item> board_copy = new Board(dim);
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                board_copy.setGrid(row, col, getGrid(row, col));
            } // end inner loop
        } // end outer loop
        return board_copy;
    } // end func copy
    
    public void display() {
        System.out.println(""-------------------------"");
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                System.out.print(board[row * dim + col] + "" "");
            } // end inner loop
            System.out.println();
        } // end outer loop
        System.out.println(""-------------------------"");
    } // end func display
} // end class Board
