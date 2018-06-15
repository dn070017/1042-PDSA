/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(""input1.txt""))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
        
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            announce = br.readLine().split("","");
            for (int i = 0; i < num; i++){
            matrix[i] = br.readLine().split("","");
            }
            
            boolean[][] mat = new boolean[num][num];
            for (int col = 0; col < num; col++){
                for (int row = 0; row < num; row++){
                    mat[row][col]=false;
                    for (int ann = 0; ann < stringCount; ann++){
                       if(matrix[row][col].equals(announce[ann]))
                       mat[row][col]=true;
                    }
                } 
            }            
            
            
            int count=0;
            //check col
            for (int col = 0; col < num; col++){
                int mark = 0;
                for (int row = 0; row < num; row++){
                    if(mat[row][col]==true){ 
                    mark++;}
                }
                if(mark == num)
                count++;
            }
            
            //check row
            for (int row = 0; row < num; row++){
                int mark = 0;
                for (int col = 0; col < num; col++){
                    if(mat[row][col]==true){ 
                    mark++;}
                }
                if(mark == num)
                count++;
            }

             //check dig
            int mark = 0;
            int mark2 = 0;            
            for (int x = 0; x < num; x++){
                if(mat[x][x]==true)
                    mark++;      
                if(mat[x][num-x-1]==true)
                    mark2++;                 
            }
            if(mark == num)
            count++;
            if(mark2 == num)
            count++;   
 
            System.out.println(count);
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
}

