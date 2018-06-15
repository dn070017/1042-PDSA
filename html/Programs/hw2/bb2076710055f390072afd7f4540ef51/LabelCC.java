
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int [][] matrix_match = new int[num][num];
            for(int i = 0;i<num;i++)
                for(int j = 0;j<num;j++)
                    matrix_match[i][j] = 0;
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

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
             int ans=0;
             announce = br.readLine().split("","");
             for(int i = 0;i<num;i++){
                matrix[i]  = br.readLine().split("","");
             }
             for(int i = 0;i<num;i++){
                for(int j = 0;j<num;j++){
                 //System.out.printf(""%s "",matrix[i][j]);
                    for(int k = 0; k<stringCount;k++){ 
                        if  (matrix[i][j].equals(announce [k]) ){
                              matrix_match[i][j]=1;
                        }
                    }
                }
                //System.out.printf(""\n"");
            }
             
             
             
                for(int i = 0;i<num;i++){
                    int row = 0;
                    int column = 0;
                    for(int j = 0;j<num;j++){
                        if(matrix_match[i][j] == 1){
                            row++;
                        }
                        if(matrix_match[j][i] == 1){
                            column++;
                        }
                    } 
                    if(row==num)
                        ans++;
                    if(column==num)
                        ans++;    
                }
                 int diag = 0;
                 int invers_diag = 0;
                 for(int i = 0;i<num;i++){
                    
                     if(matrix_match[i][i]==1)
                         diag ++;
                     if(matrix_match[i][num-i-1]==1)
                         invers_diag ++;
                 }
                 if(diag==num)
                    ans++;
                if(invers_diag==num)
                    ans++;   
                
                
             System.out.printf(""%d\n"",ans);
        }
    }
}

