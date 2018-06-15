//package Bingo;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;


public class Bingo {

    public static void main(String[] args) throws IOException {

        // read file from args[0] in Java 7 style
        FileReader fr = new FileReader(""C:\\Users\\余軒\\Documents\\algs4\\Bingo\\src\\Bingo\\input.txt"");
        BufferedReader br = new BufferedReader(fr);
            
            // read a line and split by ','
            String[] data= new String[2];
            data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
           // System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            
            String[] va = new String[2]; //pointer
            
            va = br.readLine().split("","");
           // System.out.println(va[0]+"",""+va[1]);
           
           String[] country ;
            country= new String[num];
            String rd=null;
            int ct=0;
            while ((rd = br.readLine())!=null)
            {
                country = rd.split("","");
                matrix[ct]=country;
                /*for (int i=0;i<num;i++)
                System.out.print(matrix[ct][i]+"","");  //depointer */
                //System.out.println(); //換行
                
                ct++;
            }
        //  System.out.println(matrix[0][1]); 
     
          boolean[][] b = new boolean[num][num];
          for (int i=0;i<num;i++){
              for (int j=0;j<num;j++)
              {
                  if (va[0].equals(matrix[i][j]))
                  {
                      b[i][j] = true;
                  }
                  else b[i][j] = va[1].equals(matrix[i][j]);  //若是為true,不是為false
                  }
              }
         
          //System.out.println(b[2][2]);
          
          int count = 0;
          //horizatal
          for (int i=0;i<num;i++){
          if (b[i][0]==b[i][1]&&b[i][1]==b[i][2]&&b[i][2]==true)
          {
          count++;
          }   
              }
          //vertical
          for(int j=0;j<num;j++){
          if (b[0][j]==b[1][j]&&b[1][j]==b[2][j]&&b[2][j]==true)
          {
              count++;
          }
          }
          //diaonal
          if (b[0][0]==b[1][1]&&b[1][1]==b[2][2]&&b[2][2]==true)
          {
              count++;
          }
          if(!(b[2][0]==b[1][1]&&b[1][1]==b[0][2]&&b[0][2]==true))
          {
          } else {
              count++;
        }
          System.out.println(count); 
          
          
              
            // printf in Java (you should comment out or delete this in your final submission)
            

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
