import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Eric
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read 1st line and split by ','
            String[] data = br.readLine().split("","");
            // read 2nd line and split by ','
            String[] data2 = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
            
            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            
            // read 3rd matrix and split by ','
            for (int i=0 ; i<num ; i++){
                String[] data3 = br.readLine().split("","");
                for (int j=0 ; j<num ; j++)
                    matrix[i][j]=data3[j];
            }   
            
            // check if elements match announce strings
            Integer[][] judge = new Integer[num][num];
            for(int i=0;i<num;i++)
                for(int j=0;j<num;j++)
                    judge[i][j] = 0;
                
            for(int k=0;k<stringCount;k++)
                for(int i=0;i<num;i++)
                    for(int j=0;j<num;j++)
                        if(matrix[i][j].equals(data2[k]))
                            judge[i][j] = 1;

            
            // count vertical, horizontal, and diagonal lines
            int[] col = new int[num];
            for(int i=0;i<num;i++)
                for(int j=0;j<num;j++)
                    col[i] += judge[i][j];
            
            int[] row = new int[num];
            for(int i=0;i<num;i++)
                for(int j=0;j<num;j++)
                    row[i] += judge[j][i];
            
            int dia1 = 0;            
            for(int i=0;i<num;i++)
                dia1 += judge[i][i];
            
            int dia2 = 0;            
            for(int i=0;i<num;i++)
                dia2 += judge[i][num-1-i];
            
            int line = 0;
            for(int i=0;i<num;i++){
                if(col[i]==num)
                    line++;
                else if(row[i]==num)
                    line++;
                else if(dia1==num)
                    line++;
                else if(dia2==num)
                    line++;
            }
            
            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
//            for(int i=0;i<num;i++){
//                for(int j=0;j<num;j++)
//                    System.out.printf(""%d"",judge[i][j]);
//                System.out.printf(""\n"");
//            }
            System.out.printf(""%d\n"", line);
        }
    }
}
