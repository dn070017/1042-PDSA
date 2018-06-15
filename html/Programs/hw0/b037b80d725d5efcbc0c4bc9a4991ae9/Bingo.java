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

            // record how many straight lines are in the matrix
            int lines = 0;
            
            // matrix01中用來計數的變數，累加到num時lines++
            int matrix01counter=0;
            
            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] matrix01 = new int[num][num];            
            String[] matrixLine = new String[num];


            
            announce = br.readLine().split("","");

            
            int lineCount = 0;
            String matrixLine2;
            while ((matrixLine2 = br.readLine()) != null) {
                String[] countries = matrixLine2.split("","");
                System.arraycopy(countries, 0, matrix[lineCount], 0, 3);
                lineCount++;
            }
            
            
             for (int i = 0; i < num; i++){
                for (int j = 0; j < num; j++){
                    for (int announceNo=0; announceNo<stringCount; announceNo++){
                        if (announce[announceNo].equals(matrix[i][j])){
                            matrix01[i][j]=1;
                            break;
                        }
                    }
                }
            }
            
             
            for (int i=0;i<num;i++){
                for (int j=0;j<num;j++){
                    matrix01counter += matrix01[i][j];
                }
                if (matrix01counter==3){lines++;}
                matrix01counter=0;
            }

            
            for (int j=0;j<num;j++){
                for (int i=0;i<num;i++){
                    matrix01counter += matrix01[i][j];
                }
                if (matrix01counter==3){lines++;}
                matrix01counter=0;
            }
            
            for (int k=0;k<num;k++){
                matrix01counter += matrix01[k][k];
            }
            if (matrix01counter==3){lines++;}
            matrix01counter=0;
            

            for (int k = 0; k < num; k++) {
                matrix01counter += matrix01[num-k-1][k];
            }
            if (matrix01counter == 3) {lines++;}
//            matrix01counter = 0;
            
            System.out.print(lines);

        }
    }
}

