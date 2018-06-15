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
            
            int lines = 0;
            
            int matrix01counter=0;

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] matrix01 = new int[num][num];
            String[] matrixLine = new String[num];

            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

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
            
            announce = br.readLine().split("","");
            int lineCount = 0;

            String matrixLine2;
            while ((matrixLine2 = br.readLine()) != null) {
                String[] countries = matrixLine2.split("","");
                System.arraycopy(countries, 0, matrix[lineCount], 0, 3);
                lineCount++;
            }

            // matrix01 原為3x3的靈矩陣，若subset與announce相同則設為1
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    for (int announceNo = 0; announceNo < stringCount; announceNo++) {
                        if (announce[announceNo].equals(matrix[i][j])) {
                            matrix01[i][j] = 1;
//                            System.out.printf("" [%d][%d] -> %d: %s \n"",i,j, matrix01[i][j], matrix[i][j]);
                            break;
                        }
                    }
                }
            }

            // 橫排一排一排累加subset，加到3(num)的話lines++
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    matrix01counter += matrix01[i][j];
//                    System.out.print(matrix01counter);
                }
                if (matrix01counter == 3) {
                    lines++;
                }
                matrix01counter = 0;
            }

            // check直排
            for (int j = 0; j < num; j++) {
                for (int i = 0; i < num; i++) {
                    matrix01counter += matrix01[i][j];
//                    System.out.print(matrix01counter);
                }
                if (matrix01counter == 3) {
                    lines++;
                }
                matrix01counter = 0;
            }

            // check斜線（左上右下）
            for (int k = 0; k < num; k++) {
                matrix01counter += matrix01[k][k];
            }
            if (matrix01counter == 3) {
                lines++;
            }
            matrix01counter = 0;

            // check斜線（左下右上）
            for (int k = 0; k < num; k++) {
                matrix01counter += matrix01[num - k - 1][k];
            }
            if (matrix01counter == 3) {
                lines++;
            }
            matrix01counter = 0;
            
            /*
            for(int i=0;i<num;i++){
                matrix[i]=br.readLine().split("","");
            }
//            System.out.printf(""%s\n "",announce[0]);

            for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                    for(int k=0;k<announce.length;k++) {
                        if(announce[k].equals(matrix[i][j])){
                            mp[i][j]=true;
                            break;
                        }
                        else mp[i][j]=false;
                    }
                }
            }

//            boolean a=mp[0][0]&mp[0][1];
//            System.out.printf(a);
            boolean diag=true,rdiag=true;
            for(int i=0;i<num;i++){
                boolean row=true,col=true;
                diag&=mp[i][i];
                rdiag&=mp[num-i-1][i];
                for(int j=0;j<num;j++) {
                    row&=mp[i][j];
                    col&=mp[j][i];
                }
                if(row==true)lines++;
                if(col==true)lines++;
            }
            if(diag==true)lines++;
            if(rdiag==true)lines++;
//
//
//            for(int i=0;i<num;i++){
//                for(int j=0;j<num;j++){
//                    System.out.printf(""%s "",matrix[i][j]);
//                }
//                System.out.printf(""\n"");
//            }
//
//            for(int i=0;i<num;i++){
//                for(int j=0;j<num;j++){
//                    System.out.printf(""%b "",mp[i][j]);
//                }
//                System.out.printf(""\n"");
//            }
*/
            System.out.printf(""%d"",lines);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

