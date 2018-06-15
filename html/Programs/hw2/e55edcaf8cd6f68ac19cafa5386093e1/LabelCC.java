
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
            int[][] resultmatrix = new int[num][num];
            // printf in Java (you should comment out or delete this in your final submission)
            String[] data2 = br.readLine().split("","");
            for(int i =0; i < stringCount; i++){
            announce[i] = data2[i];
            }//build the announce
            int current = 0;
            for(int i = 0; i < num; i++){
                String[] data3 = br.readLine().split("","");
                for(int j = 0; j < num; j++){
                resultmatrix[i][j] = compareString(data3[j],announce);
                current++;
                }
            }//build the countmatrix
            
            int result = 0;
            result = Matchedline(resultmatrix,num);
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
    private static int compareString(String sub,String[] obj){
        int result = 0;
        for(int i = 0; i < obj.length; i++){
            if(obj[i].equalsIgnoreCase(sub)){
                result = 1;
                break;
            }
        }
        return result;
    }
    private static int Matchedline(int[][] matrix, int num){
        int finalcount = 0;
        for (int i = 0; i < num; i++) {
            int totalcount = 0;
            for (int j = 0; j < num; j++) {
                totalcount += matrix[i][j];
            }
            if (totalcount == num) {
                finalcount++;
            }
        }// row count
        for (int i = 0; i < num; i++) {
            int totalcount = 0;
            for (int j = 0; j < num; j++) {
                totalcount += matrix[j][i];
            }
            if (totalcount == num) {
                finalcount++;
            }
        }//column count
            int diagcount = 0;
        for (int i = 0; i < num; i++) {
                diagcount += matrix[i][i];
            if (diagcount == num) {
                finalcount++;
            }//diagonal site
        }
            diagcount = 0;//re-initialize
        for (int i = 0; i < num ; i++) {
                diagcount += matrix[(num-1-i)][i];
            if (diagcount == num) {
                finalcount++;
            }//another diagonal site
        }
        return finalcount;
    }
}
