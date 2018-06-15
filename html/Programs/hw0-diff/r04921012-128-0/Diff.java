/*
.
 * To change this template file, choose Tools | Templates
.
 */
package bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author steven
 */
public class Bingo {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
     public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        //try(BufferedReader br = new BufferedReader(new FileReader(""test.txt""))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            boolean[][] bool=new boolean[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            announce = br.readLine().split("","");

            String[] line= new String[num];
            int counter=0;
            //while ((line=br.readLine().split("",""))!=null){
                
            for (int i=0; i<num;i++){
                line=br.readLine().split("","");
                matrix[counter]=line;
                counter++;
                
            }
            //System.out.println(matrix[0][2].equals(announce[0]));
            for (int y=0;y<num;y++){
                for (int x=0;x<num;x++){
                    for (int i=0;i<stringCount;i++){
                        //System.out.println(announce[0]);
                        //System.out.println(matrix[0][2]);
                        //System.out.println(announce[i]);
                        if (!bool[y][x]){
                            bool[y][x] = matrix[y][x].equals(announce[i]);
                        }
                    }
                    //System.out.println(bool[y][x]);
                }
            }
            int straightLine=0;
            
            for (int y =0;y<num;y++){
                for (int x = 0; x< num ;x++){
                    if (bool[y][x]==false){
                        break;
                    }else{
                    }
                    if (x==num-1){
                        straightLine++;
                    }
                }
            } 
            /*boolean[][] newbool=new boolean[num][num];
            
            for (int y=0; y<num;y++){
                for (int x = 0; x< num ;x++){
                    newbool[x][y]=bool[y][x];
                }
            }*/
            
            for (int y =0;y<num;y++){
                for (int x = 0; x< num ;x++){
                    if (bool[x][y]==false){
                        break;
                    }else{
                    }
                    if (x==num-1){
                        straightLine++;
                    }
                }
            } 
            
            for (int i=0;i<num;i++){
                if (bool[i][i]==false){
                    break;
                }else{
                    
                }
                if (i==num-1){
                    straightLine++;
                }
            }
            for (int i=0;i<num;i++){
                if (bool[i][num-i-1]==false){
                    break;
                }else{
                    
                }
                if (i==num-1){
                    straightLine++;
                }
            }
            //System.out.println(bool[2][2]);
            System.out.println(straightLine);

            
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

