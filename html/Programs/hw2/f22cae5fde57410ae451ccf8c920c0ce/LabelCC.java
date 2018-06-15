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
    
    public LabelCC (int n) {
        dim = n;
        board_id = new Board_ID(n);
        board_label = new Board_Label(n);
        board_open = new Board_Open(n);
        uf = new UF(n*n);
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
        
        /*
        System.out.println(
                ""Dimension: "" + N + ""\n"" + 
                ""Target site: "" + ""("" + targetRow + "","" + targetCol + "")"");
        */
        // iterate through the file
        for (String s: Arrays.copyOfRange(readLines, 1, readLines.length)){
            String[] line = s.split("","");
            //System.out.println(s);
            labelcc.setBlock(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
	} // end loop for
        
        if (labelcc.isBlock(targetRow, targetCol)) {
            System.out.println(0);
            return;
        } // end if
        
        labelcc.passFirst();
        labelcc.passSecond();
        System.out.println(labelcc.getLabel(targetRow, targetCol));
        //System.out.println(""-------------------------"");
        //labelcc.displayID();
        //labelcc.displayOpen();
        //labelcc.displayCase();
        //labelcc.displayLabel();
        //labelcc.displayRoot();
        
    } // end main
} // end class LabelCC

/**
 * 
 * @author clint
 */
class UF {
    private int[] id;
    
    public UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    } // end contructor
    
    public boolean connected(int p, int q) { 
        return root(p) == root(q); 
    } // end func connected
    
    public int root(int i) { 
        // Follow links to find a root
        while (i != id[i]) {
            id[i] = id[id[i]]; // path compression
            i = id[i];         // search upper node
        } // end while
        return i; 
    } // end func root
    
    public void union(int p, int q) {
        // Parent: p ; Child: q
        int i = root(p);     // component of p
        int j = root(q);     // component of q
        if (i == j) return;  // if same root, same component
        id[j] = i;           // link component q under component p
                             // Parent: i
                             // Child:  j
    } // end func union
    
    //public void display() {
        
    //} // end 
    
    public static void main(String[] args) {
        System.out.println(""Testing Class UF"");
        System.out.println(""========================="");
        UF uf = new UF(8);
        uf.union(1, 4);
        uf.union(4, 5);
        uf.union(2, 3);
        uf.union(3, 6);
        uf.union(3, 7);
        
        System.out.println(""---Testing Method root---"");
        if (uf.root(0) == 0)  { System.out.println(""Test01 pass""); }
        else                  { System.out.println(""Test01 fail""); }
        if (uf.root(1) == 1)  { System.out.println(""Test02 pass""); }
        else                  { System.out.println(""Test02 fail""); }
        if (uf.root(4) == 1)  { System.out.println(""Test03 pass""); }
        else                  { System.out.println(""Test03 fail""); }
        if (uf.root(5) == 1)  { System.out.println(""Test04 pass""); }
        else                  { System.out.println(""Test04 fail""); }
        if (uf.root(7) == 2)  { System.out.println(""Test05 pass""); }
        else                  { System.out.println(""Test05 fail""); }
        
        System.out.println(""---Testing Method connected---"");
        if (uf.connected(0, 0) == true)  { System.out.println(""Test01 pass""); }
        else                             { System.out.println(""Test01 fail""); }
        if (uf.connected(1, 1) == true)  { System.out.println(""Test02 pass""); }
        else                             { System.out.println(""Test02 fail""); }
        if (uf.connected(0, 1) == false) { System.out.println(""Test03 pass""); }
        else                             { System.out.println(""Test03 fail""); }
        if (uf.connected(0, 7) == false) { System.out.println(""Test04 pass""); }
        else                             { System.out.println(""Test04 fail""); }
        if (uf.connected(1, 4) == true)  { System.out.println(""Test05 pass""); }
        else                             { System.out.println(""Test05 fail""); }
        if (uf.connected(1, 5) == true)  { System.out.println(""Test06 pass""); }
        else                             { System.out.println(""Test06 fail""); }
        if (uf.connected(2, 7) == true)  { System.out.println(""Test07 pass""); }
        else                             { System.out.println(""Test07 fail""); }
        System.out.println(""========================="");
    } // end main
} // end class UF

/**
 * 
 * @author clint
 * @param <Item> 
 */

class Board_ID {
    private final int dim;
    private Board<Integer> board;
    
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
    
    public static void main(String[] args) {
        System.out.println(""Testing Class Board_ID"");
        System.out.println(""========================="");
        int n = 5;
        Board_ID board = new Board_ID(n);
        //board.display();
        
        System.out.println(""---Testing Method getLabel & setLabel---"");
        if (board.getId(1, 1) == 0) {System.out.println(""Test01 pass"");}
        else                        {System.out.println(""Test01 fail"");}
        if (board.getId(1, 2) == 1) {System.out.println(""Test02 pass"");}
        else                        {System.out.println(""Test02 fail"");}
        if (board.getId(2, 2) == 6) {System.out.println(""Test03 pass"");}
        else                        {System.out.println(""Test03 fail"");}
        board.setId(2, 2, 1);
        if (board.getId(2, 2) == 1) {System.out.println(""Test04 pass"");}
        else                        {System.out.println(""Test04 fail"");}
        
        System.out.println(""---Testing Method getNeighbor---"");
        board.reset();
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                board.setId(row, col, row * col);
            } // end inner loop
        } // end outer loop
        //board.display();
        
        if (board.getNeighbor(0, 0, ""UP"") == null) {
            System.out.println(""Test01 pass"");
        } else {
            System.out.println(""Test01 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""UP""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""DOWN"") == board.getId(1, 0)) {
            System.out.println(""Test02 pass"");
        } else {
            System.out.println(""Test02 fail"");
            System.out.println(""Expected: "" + board.getId(1, 0));
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""DOWN""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""LEFT"") == null) {
            System.out.println(""Test03 pass"");
        } else {
            System.out.println(""Test03 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""LEFT""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""RIGHT"") == board.getId(0, 1)) {
            System.out.println(""Test04 pass"");
        } else {
            System.out.println(""Test04 fail"");
            System.out.println(""Expected: "" + board.getId(0, 1));
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""RIGHT""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""UP"") == board.getId(0, 1)) {
            System.out.println(""Test05 pass"");
        } else {
            System.out.println(""Test05 fail"");
            System.out.println(""Expected: "" + board.getId(0, 1));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""UP""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""DOWN"") == board.getId(2, 1)) {
            System.out.println(""Test06 pass"");
        } else {
            System.out.println(""Test06 fail"");
            System.out.println(""Expected: "" + board.getId(2, 1));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""DOWN""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""LEFT"") == board.getId(1, 0)) {
            System.out.println(""Test07 pass"");
        } else {
            System.out.println(""Test07 fail"");
            System.out.println(""Expected: "" + board.getId(1, 0));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""LEFT""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""RIGHT"") == board.getId(1, 2)) {
            System.out.println(""Test08 pass"");
        } else {
            System.out.println(""Test08 fail"");
            System.out.println(""Expected: "" + board.getId(1, 2));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""RIGHT""));
        } // end if-else
        
        System.out.println(""========================="");
    } // end main
} // end class Board_ID

/**
 * 
 * @author clint
 */
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
    
    //public boolean compare(Board_Label anotherBoard){
    //    return board.compare(anotherBoard);
    //} // end func compared
    
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
    
    public static void main(String[] args) {
        /* Test cases for Board_Label */
        System.out.println(""Testing Class Board_Label"");
        System.out.println(""========================="");
        int n = 5;
        Board_Label board = new Board_Label(n);
        
        /* Test setOpen, isOpen, setBlock, isBlock */
        // board_int.display();
        System.out.println(""---Testing Method getLabel & setLabel---"");
        if (board.getLabel(1, 1) == -1) {System.out.println(""Test01 pass"");}
        else                            {System.out.println(""Test01 fail"");}
        board.setLabel(1, 1, 1);
        if (board.getLabel(1, 1) == 1)  {System.out.println(""Test02 pass"");}
        else                            {System.out.println(""Test02 fail"");}
        
        System.out.println(""---Testing Method isLabel---"");
        if (board.isLabel(0, 0) == null)  {System.out.println(""Test01 pass"");}
        else                              {System.out.println(""Test01 fail"");}
        if (board.isLabel(1, 1) == true)  {System.out.println(""Test02 pass"");}
        else                              {System.out.println(""Test02 fail"");}
        if (board.isLabel(2, 2) == false) {System.out.println(""Test03 pass"");}
        else                              {System.out.println(""Test03 fail"");}
        
        System.out.println(""---Testing Method getNeighbor---"");
        board.reset();
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                board.setLabel(row, col, row * col);
            } // end inner loop
        } // end outer loop
        
        if (board.getNeighbor(0, 0, ""UP"") == null) {
            System.out.println(""Test01 pass"");
        } else {
            System.out.println(""Test01 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""UP""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""DOWN"") == board.getLabel(1, 0)) {
            System.out.println(""Test02 pass"");
        } else {
            System.out.println(""Test02 fail"");
            System.out.println(""Expected: "" + board.getLabel(1, 0));
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""DOWN""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""LEFT"") == null) {
            System.out.println(""Test03 pass"");
        } else {
            System.out.println(""Test03 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""LEFT""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""RIGHT"") == board.getLabel(0, 1)) {
            System.out.println(""Test04 pass"");
        } else {
            System.out.println(""Test04 fail"");
            System.out.println(""Expected: "" + board.getLabel(0, 1));
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""RIGHT""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""UP"") == board.getLabel(0, 1)) {
            System.out.println(""Test05 pass"");
        } else {
            System.out.println(""Test05 fail"");
            System.out.println(""Expected: "" + board.getLabel(0, 1));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""UP""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""DOWN"") == board.getLabel(2, 1)) {
            System.out.println(""Test06 pass"");
        } else {
            System.out.println(""Test06 fail"");
            System.out.println(""Expected: "" + board.getLabel(2, 1));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""DOWN""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""LEFT"") == board.getLabel(1, 0)) {
            System.out.println(""Test07 pass"");
        } else {
            System.out.println(""Test07 fail"");
            System.out.println(""Expected: "" + board.getLabel(1, 0));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""LEFT""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""RIGHT"") == board.getLabel(1, 2)) {
            System.out.println(""Test08 pass"");
        } else {
            System.out.println(""Test08 fail"");
            System.out.println(""Expected: "" + board.getLabel(1, 2));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""RIGHT""));
        } // end if-else
        
        System.out.println(""---Testing Method compare---"");
        if (board.compare(board.copy())) { System.out.println(""Test01 pass""); } 
        else                             { System.out.println(""Test01 fail""); }
        System.out.println(""========================="");
    } // end main
} // end class Board_Label

/**
 * 
 * @author clint
 */
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
    
    public static void main(String[] args) {
        /* Test cases for Board_Open */
        System.out.println(""Testing Class Board_Open"");
        System.out.println(""========================="");
        int n = 5;
        Board_Open board = new Board_Open(n);
        
        /* Test setOpen, isOpen, setBlock, isBlock */
        // board_int.display();
        System.out.println(""---Testing Method isOpen & isBlock---"");
        if (board.isOpen(1, 1) == true) {System.out.println(""Test01 pass"");}
        else                            {System.out.println(""Test01 fail"");}
        
        if (board.isBlock(1, 1) == false) {System.out.println(""Test02 pass"");}
        else                              {System.out.println(""Test02 fail"");}
        
        System.out.println(""---Testing Method setOpen & setBlock---"");
        board.setBlock(2, 2);
        if (board.isOpen(2, 2)  == false) {System.out.println(""Test01 pass"");}
        else                              {System.out.println(""Test01 fail"");}
        if (board.isBlock(2, 2) == true)  {System.out.println(""Test02 pass"");}
        else                              {System.out.println(""Test02 fail"");}
        board.setOpen(2, 2);
        if (board.isOpen(2, 2)  == true)  {System.out.println(""Test03 pass"");}
        else                              {System.out.println(""Test03 fail"");}
        if (board.isBlock(2, 2) == false) {System.out.println(""Test04 pass"");}
        else                              {System.out.println(""Test04 fail"");}
        board.setOpen(-1, -1);
        if (board.isOpen(-1, -1)  == null)  {System.out.println(""Test05 pass"");}
        else                              {System.out.println(""Test05 fail"");}
        if (board.isBlock(-1, -1) == null) {System.out.println(""Test06 pass"");}
        else                              {System.out.println(""Test06 fail"");}
        /* Test getNeighbor */
        board.reset();
        boolean flag = true;
        for (int row = 1; row <= n; row++){
            for (int col = 1; col <= n; col++) {
                if (flag) { board.setOpen(row, col); }
                else      { board.setBlock(row, col); }
                flag = !flag;
            } // end inner for
        } // end outer for
        //board.display();
        System.out.println(""---Testing Method getNeighbor---"");
        if (board.getNeighbor(0, 0, ""UP"") == null) {
            System.out.println(""Test01 pass"");
        } else {
            System.out.println(""Test01 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""UP""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""DOWN"") == board.isOpen(1, 0)) {
            System.out.println(""Test02 pass"");
        } else {
            System.out.println(""Test02 fail"");
            System.out.println(""Expected: "" + board.isOpen(1, 0));
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""DOWN""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""LEFT"") == null) {
            System.out.println(""Test03 pass"");
        } else {
            System.out.println(""Test03 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""LEFT""));
        } // end if-else
        
        if (board.getNeighbor(0, 0, ""RIGHT"") == board.isOpen(0, 1)) {
            System.out.println(""Test04 pass"");
        } else {
            System.out.println(""Test04 fail"");
            System.out.println(""Expected: "" + board.isOpen(0, 1));
            System.out.println(""Get:      "" + board.getNeighbor(0, 0, ""RIGHT""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""UP"") == board.isOpen(0, 1)) {
            System.out.println(""Test05 pass"");
        } else {
            System.out.println(""Test05 fail"");
            System.out.println(""Expected: "" + board.isOpen(0, 1));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""UP""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""DOWN"") == board.isOpen(2, 1)) {
            System.out.println(""Test06 pass"");
        } else {
            System.out.println(""Test06 fail"");
            System.out.println(""Expected: "" + board.isOpen(2, 1));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""DOWN""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""LEFT"") == board.isOpen(1, 0)) {
            System.out.println(""Test07 pass"");
        } else {
            System.out.println(""Test07 fail"");
            System.out.println(""Expected: "" + board.isOpen(1, 0));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""LEFT""));
        } // end if-else
        
        if (board.getNeighbor(1, 1, ""RIGHT"") == board.isOpen(1, 2)) {
            System.out.println(""Test08 pass"");
        } else {
            System.out.println(""Test08 fail"");
            System.out.println(""Expected: "" + board.isOpen(1, 2));
            System.out.println(""Get:      "" + board.getNeighbor(1, 1, ""RIGHT""));
        } // end if-else
        
        System.out.println(""---Testing Method compare---"");
        if (board.compare(board.copy())) { System.out.println(""Test01 pass""); } 
        else                             { System.out.println(""Test01 fail""); }
        
        System.out.println(""========================="");
    } // end main
} // end class Board_Open

// --------------------------
/**
 * 
 * @author clint
 */
class Board<Item> //implements Iterable<Item> 
{
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
        /**
         * 
         */
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
    
    public static void main(String[] args) {
        /* Test cases for Board */
        System.out.println(""Testing Class Board"");
        System.out.println(""========================="");
        int n = 5;
        Board<Integer> board_int = new Board<Integer>(n);
        Board<String>  board_str = new Board<String>(n);
        
        /* Test getGrid and setGrid */
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++) {
                board_int.setGrid(row, col, row * n + col);
            } // end inner loop
        } // end outer loop
        // board_int.display();
        
        System.out.println(""---Testing Method getGrid---"");
        if (board_int.getGrid(0, 0) == 0) {System.out.println(""Test01 pass"");}
        else                              {System.out.println(""Test01 fail"");}
        
        if (board_int.getGrid(1, 1) == 6) {System.out.println(""Test02 pass"");}
        else                              {System.out.println(""Test02 fail"");}
        
        System.out.println(""---Testing Method setGrid---"");
        board_int.setGrid(0, 0, 10);
        if (board_int.getGrid(0, 0) == 10) {System.out.println(""Test01 pass"");}
        else                               {System.out.println(""Test01 fail"");}
        
        board_int.setGrid(1, 1, 20);
        if (board_int.getGrid(1, 1) == 20) {System.out.println(""Test02 pass"");}
        else                              {System.out.println(""Test02 fail"");}
        
        /* Test getNeighbor */
        System.out.println(""---Testing Method getNeighbor---"");
        if (board_int.getNeighbor(0, 0, ""UP"") == null) {
            System.out.println(""Test01 pass"");
        } else {
            System.out.println(""Test01 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board_int.getNeighbor(0, 0, ""UP""));
        } // end if-else
        
        if (board_int.getNeighbor(0, 0, ""DOWN"") == board_int.getGrid(1, 0)) {
            System.out.println(""Test02 pass"");
        } else {
            System.out.println(""Test02 fail"");
            System.out.println(""Expected: "" + board_int.getGrid(1, 0));
            System.out.println(""Get:      "" + board_int.getNeighbor(0, 0, ""DOWN""));
        } // end if-else
        
        if (board_int.getNeighbor(0, 0, ""LEFT"") == null) {
            System.out.println(""Test03 pass"");
        } else {
            System.out.println(""Test03 fail"");
            System.out.println(""Expected: "" + null);
            System.out.println(""Get:      "" + board_int.getNeighbor(0, 0, ""LEFT""));
        } // end if-else
        
        if (board_int.getNeighbor(0, 0, ""RIGHT"") == board_int.getGrid(0, 1)) {
            System.out.println(""Test04 pass"");
        } else {
            System.out.println(""Test04 fail"");
            System.out.println(""Expected: "" + board_int.getGrid(0, 1));
            System.out.println(""Get:      "" + board_int.getNeighbor(0, 0, ""RIGHT""));
        } // end if-else
        
        if (board_int.getNeighbor(1, 1, ""UP"") == board_int.getGrid(0, 1)) {
            System.out.println(""Test05 pass"");
        } else {
            System.out.println(""Test05 fail"");
            System.out.println(""Expected: "" + board_int.getGrid(0, 1));
            System.out.println(""Get:      "" + board_int.getNeighbor(1, 1, ""UP""));
        } // end if-else
        
        if (board_int.getNeighbor(1, 1, ""DOWN"") == board_int.getGrid(2, 1)) {
            System.out.println(""Test06 pass"");
        } else {
            System.out.println(""Test06 fail"");
            System.out.println(""Expected: "" + board_int.getGrid(2, 0));
            System.out.println(""Get:      "" + board_int.getNeighbor(1, 1, ""DOWN""));
        } // end if-else
        
        if (board_int.getNeighbor(1, 1, ""LEFT"") == board_int.getGrid(1, 0)) {
            System.out.println(""Test07 pass"");
        } else {
            System.out.println(""Test07 fail"");
            System.out.println(""Expected: "" + board_int.getGrid(2, 0));
            System.out.println(""Get:      "" + board_int.getNeighbor(1, 1, ""LEFT""));
        } // end if-else
        
        if (board_int.getNeighbor(1, 1, ""RIGHT"") == board_int.getGrid(1, 2)) {
            System.out.println(""Test08 pass"");
        } else {
            System.out.println(""Test08 fail"");
            System.out.println(""Expected: "" + board_int.getGrid(1, 2));
            System.out.println(""Get:      "" + board_int.getNeighbor(1, 1, ""RIGHT""));
        } // end if-else
        
        /* Test copy */
        System.out.println(""---Testing Method copy---"");
        Board<Integer> board_int_copy = board_int.copy();
        boolean flag = true;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if ( board_int_copy.getGrid(row, col) != board_int.getGrid(row, col) ) {
                    flag = false;
                } // end if
            } // end inner loop
        } // end outer loop
        if (flag) { System.out.println(""Test01 pass""); } 
        else      { System.out.println(""Test01 fail""); }
        
        System.out.println(""---Testing Method getDim---"");
        if (board_int.getDim() == n) { System.out.println(""Test01 pass""); } 
        else                          { System.out.println(""Test01 fail""); }
        
        System.out.println(""---Testing Method compare---"");
        board_int_copy = board_int.copy();
        if (board_int.compare(board_int_copy)) { System.out.println(""Test01 pass""); } 
        else                                   { System.out.println(""Test01 fail""); }
        System.out.println(""========================="");
    } // end main
} // end class Board
