import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {
    
    public static boolean find(String x, String[] anno){
        for (String item : anno) {
            if (x.equals(item))
                return true;
        }
        return false;
    }
    
    public static void printResult(String[][] thematrix){
                for (int i = 0; i < thematrix.length; i++) {
                    for (int j = 0; j < thematrix.length; j++)
                        System.out.printf(thematrix[i][j]+"" "");
                    System.out.printf(""\n"");
                }
            }

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
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            // 1. read the rest content of the file
            // 2. store the announce strings (2nd line of the file) in variable announce
            announce = br.readLine().split("","");
            // 3. store the matrix (from the 3rd line to the end of the file) in variable matrix
            for (int i = 0; i < num; i++) {
               String[] temp = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = temp[j];                   
                }
            }
            //printResult(matrix);
            // 4. compare the matrix and announce strings (this is the tricky part)
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if(find(matrix[i][j], announce))
                        matrix[i][j] = ""cross"";
                }
            }
            //printResult(matrix);
            //  5. output how many 'straight line' are there in the matrix
            int lines = 0;
            //check row and colunm
            for (int i = 0; i < num; i++) {
                int temp1 = 0;
                int temp2 = 0;
                for (int j = 0; j < num; j++) {
                    if(matrix[i][j].equals(""cross""))
                        temp1+=1;
                    if(matrix[j][i].equals(""cross""))
                        temp2+=1;
                }
                if(temp1 == num && temp2 == num )
                    lines += 2;
                else if(temp1 == num || temp2 == num)
                    lines += 1;
            }
            //check diagonal
            int temp1 = 0;
            int temp2 = 0;
            for (int i = 0; i < num; i++) {
                if(matrix[i][i].equals(""cross"")){
                    temp1 += 1;
                }
                if(matrix[i][num-1-i].equals(""cross"")){
                    temp2 += 1;
                }
            }
            if(temp1 == num && temp2 == num )
                    lines += 2;
            else if(temp1 == num || temp2 == num)
                    lines += 1;
            
            System.out.printf(""%d"",lines);
            /*  [note]
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
