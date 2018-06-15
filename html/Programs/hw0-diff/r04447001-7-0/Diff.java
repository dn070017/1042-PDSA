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
            String[][] resultmatrix = new String[num][num];
            // printf in Java (you should comment out or delete this in your final submission)
            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            String[] data2 = br.readLine().split("","");
            for(int i =0; i < stringCount; i++){
            announce[i] = data2[i];
            }//build the announce
            for(int i = 0; i < num; i++){
                String[] data3 = br.readLine().split("","");
                for(int j = 0; j < num; j++){
                resultmatrix[i][j] = data3[j];
                }
            }//build the countmatrix
            
            int result = 0;
            result = Matchedline(resultmatrix,announce,num);
            System.out.println(result);
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
    private static int compareString(String sub[], String[] announce) {
        int result = 1;
        int[] counttable = new int[announce.length];
        for (int i = 0; i < announce.length; i++) {
            for (int j = 0; j < sub.length; j++) {
                if (announce[i].equalsIgnoreCase(sub[j])) {
                    counttable[i] = 1;
                }
            }
        }
        int test = 1;
        for (int i = 0; i < announce.length; i++) {
          if(counttable[i] == 0){result = 0;
          }
        }
        return result;
    }
    
    private static int Matchedline(String[][] matrix, String[] announce, int num){
        int finalcount = 0;
        String[] current = new String[num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                current[j] =  matrix[i][j];
            }
            int temp = compareString(current,announce);
            if (temp == 1) {
                finalcount++;
            }
        }// row count
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                current[j] =  matrix[j][i];
            }
            int temp = compareString(current,announce);
            if (temp == 1) {
                finalcount++;
            }
        }//column count
        for (int i = 0; i < num; i++) {
                current[i] = matrix[i][i];}
            int temp = compareString(current,announce);
            if (temp == 1) {
                finalcount++;
            }//diagonal site

        for (int i = 0; i < num ; i++) {
                current[i] = matrix[(num-1-i)][i];
        }
            temp = compareString(current,announce);
            if (temp == 1) {
                finalcount++;
            }//another diagonal site
        return finalcount;
    }
}


