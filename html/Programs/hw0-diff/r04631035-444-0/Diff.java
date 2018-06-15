import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;
import java.util.Scanner;
import java.util.*; 
import java.lang.String;
import java.util.Arrays;

public class Bingo {

    public static void main(String[] args) throws Exception {

            //讀取Input字串
            int i = 0;
            int j = 0;
            String[] strs = new String[100];
            Scanner sc = null;
            try {
              sc = new Scanner(new File(""C:\\Users\\Pan\\Desktop\\Data_structure\\project\\Test2\\src\\input.txt""));
            } catch (FileNotFoundException e) {
              e.printStackTrace();
              System.exit(0);
            }
            while (sc.hasNext()) {
              strs[i] = sc.next();  
              i++;
            }
            sc.close();
            
            String[] data1 = strs[0].split("","");
            String[] row1 = strs[1].split("","");
                                   
            int stringCount = Integer.parseInt(data1[0]);    //讓Bingo連成線的物件    
            int num = Integer.parseInt(data1[1]);  //幾成幾的矩陣
            
            //處理連線物件
           String[] Count =  new String[20];
           for(i = 1; i <= stringCount; i++){
              Count = strs[1].split("","");
           }
           //處理Bingo表格
           String[][] row = new String[20][20];
           for(i = 1; i <= num; i++){
                   row[i] = (strs[i+1].split("",""));          
           }
           
           //strs[2].split("","");
           
           
             //System.out.println(Count[0]);
             System.out.println(Count[0]);
             System.out.println(row[2]);
            
            //System.out.println(data1[0]);
            //System.out.println(row1[1]);
            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            
            
            
            /*  now you can write your own solution to hw0
             *  you can follow the instruction described below:
             * 
             *  1. read the rest content of the file
             *  2. store the announce strings (2nd line of the file) in variable announce
             *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
             *  4. compare the matrix and announce strings (this is the tricky part)
             *  5. output how many 'straight line' are there in the matrix
             * 
             *  [note]
             *  you can use every data structure in standard Java packages (Java 8 supported)
             *  the packages in stdlib.jar and algs4.jar are also available for you to use
             *
             *  [hint]
.
             *  2. some data structure such as HashSet, HashMap, Arrays, ArrayList, Vector are very
             *     useful for solving problems. 
             */
        }
    }

