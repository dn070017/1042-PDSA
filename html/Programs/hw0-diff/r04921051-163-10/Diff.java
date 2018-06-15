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
            
            //matrix for bingo
            int[][] matrix_int = new int[num][num];
            
            //read the rest content of the file
            String[] country = br.readLine().split("","");
            
            for(int i = 0; i < num; i++){
                matrix[i] = br.readLine().split("","");
            }

            for(int w = 0; w < stringCount; w++){                           
                for(int i = 0;i < num; i++){
                    for(int j = 0;j < num; j++){
                        if(country[w].equals(matrix[i][j]))
                            matrix_int[i][j] = 1;
                    }
                }
            }
            
            int line = 0;
          
            //row  
            for(int i = 0; i < num; i++){
                int temp = matrix_int[i][0];
                for(int j = 1; j < num; j ++){
                    temp = temp + matrix_int[i][j];
                    }
                if(temp == num)
                    line++;
            }    

            //column  
            for(int i = 0; i < num; i++){
                int temp = matrix_int[0][i];
                for(int j = 1; j < num; j ++){
                    temp = temp + matrix_int[j][i];
                    }
                if(temp == num)
                    line++;
            }  
            
            // ""\""  
            int temp = matrix_int[0][0];
            for(int i = 1; i < num; i++){
                temp = temp + matrix_int[i][i];
            }                         
            if(temp == num)
            line++;
            
            // ""/""  
            int temp2 = matrix_int[num - 1][0];
            for(int i = 1; i < num; i++){
                temp2 = temp2 + matrix_int[num-1-i][i];
            }                         
            if(temp2 == num)
            line++;
            
                
            System.out.println(line);
            
            /*
            for(int i = 0; i < stringCount; i++)
                System.out.printf(country[i] + "","");
            
            System.out.printf(""\n"");
            
            for(int i = 0; i < num; i++){
                for(int j=0; j < num; j++)
                System.out.printf(matrix_int[i][j] + "","");
                System.out.printf(""\n"");
            }
            */
        }
    }
}

