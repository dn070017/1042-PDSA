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
            int[][] matrix = new int[num][num];
            for(int i=0; i<num; i++)
                for(int j=0; j<num; j++)
                    matrix[i][j]=0;
            
            String[] data1 = br.readLine().split("","");
            for(int i=0; i<stringCount; i++) announce[i] = data1[i];
            for (int i=0; i<num; i++){
                String[] data2 = br.readLine().split("","");
                for(int j=0; j<num; j++){
                    for (int a=0; a<stringCount; a++){
                        if(data2[j].compareTo(announce[a]) == 0) matrix[i][j] = 1;
                    }
                }
            }
            
            int line=0;
            int count=0;
            //row major
            for (int i=0; i<num; i++){
                count=0;
                for (int j=0; j<num; j++){
                    //System.out.printf(""%d"",count);
                    if(matrix[i][j] == 0) break;
                    else count += 1;
                }
                line += count/stringCount;
            }
            //column major
            for (int j=0; j<num; j++){
                count=0;
                for (int i=0; i<num; i++){
                    if(matrix[i][j] == 0) break;
                    else count += 1;
                }
                line += count/stringCount;
            }
            //diagnoal
            count=0;
            for (int i=0; i<num; i++){ 
                int j=i;    
                count += matrix[i][j];
            }
            line += count/stringCount;
            //sub diagnoal
            count=0;
            int j=0;
            for (int i=num-1;i>=0;i--){
                count += matrix[i][j];   //sub diagnoal
                j++;
            }
            line += count/stringCount;
            System.out.printf(""%d\n"",line);
        }
    }
}

