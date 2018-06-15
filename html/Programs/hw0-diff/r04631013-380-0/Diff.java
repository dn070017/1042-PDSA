import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(""args[0]""))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
 
            String[] called = br.readLine().split("",""); //called countries
            for (int i = 0; i < num; i++) {      //matrix countries depends on the given size
            String[] row = br.readLine().split("","");
            matrix[i] = row;
            }
            for (int i = 0; i < stringCount ; i++){
                for (int j = 0; j < num ; j++){
                    for (int k = 0; k < num ; k++){
                        if  (called[i].compareTo(matrix[j][k])==0) {
                          matrix[j][k] = ""check"";
                        } 
                    }
                }
            }
            String z = ""check"";
            int line = 0;
            for (int i = 0; i < num ; i++){
                int count = 0;
                for (int j = 0; j < num ; j++){
                  if (z.compareTo(matrix[i][j])!=0)  {
                      break;}
                  else{
                      count = count + 1;
                  }
                }
                if (count == num){
                    line = line + 1;    
                }
            }
            
            for (int i = 0; i < num ; i++){
                int count = 0;
                for (int j = 0; j < num ; j++){
                  if (z.compareTo(matrix[j][i])!=0)  {
                      break;}
                  else{
                      count = count + 1;
                  }
                }
                if (count == num){
                    line = line + 1;    
                }
            }
               
                int count = 0;
                for (int j = 0; j < num ; j++){
                  if (z.compareTo(matrix[j][j])!=0)  {
                      break;}
                  else{
                      count = count + 1;
                  }
                }
                if (count == num){
                    line = line + 1;    
                }
                
                count = 0;
                for (int j = 0; j < num ; j++){
                    int k = num - 1 - j; 
                  if (z.compareTo(matrix[j][k])!=0)  {
                      break;}
                  else{
                      count = count + 1;
                  }
                }
                if (count == num){
                    line = line + 1;    
                }
            
         System.out.printf(""%d\n"",line);
            
    

//         for (int i = 0; i < matrix.length; i++) {
//         for (int j = 0; j < matrix[0].length; j++) {
//                System.out.print(matrix[i][j] + "" "");
//            }
//                System.out.print(""\n"");
//         }

              
              
              
              
              
              
              
           //System.out.printf(""lalala %s %s %s\n"",matrix[2]);
            
           
            
            
         
            
     
        
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

