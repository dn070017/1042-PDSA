import java.io.FileReader;
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

            // printf in Java (you should comment out or delete this in your final submission)
            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            // read in tatrix
            announce = br.readLine().split("","");
            int i,j,k;
            for (i=0;i<num;i++){
                    matrix[i]=br.readLine().split("","");
            }
            //circle the target
            for (k=0;k<stringCount;k++){
                for (i=0;i<num;i++){
                    for (j=0;j<num;j++){
//                        System.out.printf(""(%s,%s)"",matrix[i][j],announce[k]);
                        if (matrix[i][j].equals(announce[k])){
                            matrix[i][j]=""true"";
                            System.out.printf(""(%d,%d)"",i,j);
                        }
                    }
                }
            }
            
            //count lines
            System.out.printf(""\n"");
            int times;
            int lines=0;
            for (i=0;i<num;i++){
                    times=0;
                    for (j=0;j<num;j++){
                        if(matrix[i][j] !=""true"")
                            break;
                        else
                            times++;
                        if(matrix[i][j]==""true"" && j==num-1)
                            lines ++;
                    }
            }
            for (i=0;i<num;i++){
                    times=0;
                    for (j=0;j<num;j++){
                        if(matrix[j][i] !=""true"")
                            break;
                        else
                            times++;
                        if(matrix[j][i]==""true"" && j==num-1)
                            lines ++;
                    }
            }
            times=0;
            for (i=0;i<num;i++){
                j=i;
                if(matrix[i][j] !=""true"")
                    break;
                else
                    times++;
                if(matrix[i][j]==""true"" && i==num-1)
                    lines ++;
            }
            times=0;
            for (i=0;i<num;i++){
                j=num-i-2;
                if(matrix[i][j] !=""true"")
                    break;
                else
                    times++;
                if(matrix[i][j]==""true"" && i==num-1)
                    lines ++;
            }
            
            System.out.printf(""%d"",lines);
                        
                               
                            
                    
             
            
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

