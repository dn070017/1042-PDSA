/*
.
 * To change this template file, choose Tools | Templates
.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;


/**
 *
 * @author clint
 */
public class Bingo {
    private int lenList;
    private int dimBoard;
    private String[] bingoBoard;
    
    public Bingo(int m, int n){
        lenList  =  m; // m: the length of countryList
        dimBoard =  n; // n: the dimension of board = n*n
        bingoBoard = new String[n*n];
    } // end constructor
    
    public String[] getBoard() {
        return bingoBoard;
    } // end getBoard
        
    public String getBoardItem(int row, int col) {
        return bingoBoard[row * dimBoard + col];
    } // end getItem
        
    public void setBoardItem(int row, int col, String val) {
        bingoBoard[row * dimBoard + col] = val;
    } // end setBoard
    
    public void crossOut(String val){
        for (int idx = 0; idx < dimBoard * dimBoard; idx++){
            if (bingoBoard[idx].equals(val)){
                bingoBoard[idx] = ""0"";
            }
        }
    }
    
    public int checkWin() {
        int flag = 0;
        
        // check row
        for (int row = 0; row < dimBoard; row++){
            flag = 0;
            for (int col = 0; col < dimBoard; col++) {
                if (bingoBoard[row * dimBoard + col].equals(""0"")){
                    flag += 1;
                } // end if
            } // end inner loop
            
            // check isWin
            if (flag == dimBoard){
                return 1;
            } // end if
        } // end outer loop
        
        
        
        // check col
        for (int col = 0; col < dimBoard; col++){
            flag = 0;
            for (int row = 0; row < dimBoard; row++) {
                if (bingoBoard[row * dimBoard + col].equals(""0"")){
                    flag += 1;
                } // end if
            } // end inner loop
            
            // check isWin
            if (flag == dimBoard){
                return 1;
            } // end if
        } // end outer loop
        
        
        
        // check 
        flag = 0;
        for (int row = 0; row < dimBoard; row++) {
            if (bingoBoard[row * dimBoard + row].equals(""0"")){
                    flag += 1;
            } // end if
        } // end loop
        
        // check isWin
        if (flag == dimBoard){
            return 1;
        } // end if
        
        
        return 0;
    }
    
    public static void main(String[] args) {
        // Write your name and age to the file
        int m = 0;
        int n = 0;
        Bingo bingo = new Bingo(m, n);
        String   temptLine = """";
        String[] temptList = {""""};
        String[] temptBoard = {""""};
        String[] countryList = {""""};
               
        try {
            //File file = new File(""input.txt"");
            File file = new File(args[0]);
            Scanner input = new Scanner(file);
            
            // read in the first line to get m and n
            temptLine = input.nextLine();
            temptList = temptLine.split("","");
            m = Integer.valueOf(temptList[0]);
            n = Integer.valueOf(temptList[1]);
            
            // initialized the countryList and board
            bingo = new Bingo(m, n);
            countryList = new String[m];
            temptBoard = new String[n*n];

            // read in the second line to get country list
            temptLine = input.nextLine();
            temptList = temptLine.split("","");
            System.arraycopy(temptList, 0, countryList, 0, m);

            // read in the third line to get bingo board
            for (int row = 0; row < n; row++) {
                // read in a row
                temptLine = input.nextLine();
                temptList = temptLine.split("","");
                System.arraycopy(temptList, 0, temptBoard, row * n, n);
                
                for (int col = 0; col < n; col++) {
                    bingo.setBoardItem(row, col, temptList[col]);    
                } // end inner loop
            } // end outer loop
            
            for (int idx = 0; idx < m; idx++) {
                bingo.crossOut(countryList[idx]);
            }
            
        }catch (FileNotFoundException ex) {
            System.out.println(ex);
        }    
              
//        System.out.println(""Length: m and n"");
//        System.out.print(""    m: "");
//        System.out.print(m);
//        System.out.print(""    n: "");
//        System.out.print(n);
//        System.out.println(""\n"");
//        
//        System.out.println(""List of Countries"");
//        System.out.println(Arrays.toString(countryList));
//        System.out.println("""");
//        
//        System.out.println(""Bingo Board"");
//        System.out.println(Arrays.toString(temptBoard));
//        System.out.println(Arrays.toString(bingo.getBoard()));
//        System.out.println("""");
//        
//        System.out.println(""Check Win"");
        System.out.println(bingo.checkWin());
            
        
        
        
            
        
        
    } // end main
}

