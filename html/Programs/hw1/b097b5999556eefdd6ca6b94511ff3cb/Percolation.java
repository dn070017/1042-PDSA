import java.io.FileReader;
import java.io.BufferedReader;


public class Bingo  {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
           

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] match = new int[num][num];
            int temp1 ;
            int temp2;
            int count = 0;
            
            String[] data1 = br.readLine().split("","");
            for(int i=0;i<stringCount;i++){
                announce[i] = data1[i];
            }
            for(int i=0;i<num;i++){
               String[] data2 =  br.readLine().split("","");
               for(int j=0;j<num;j++){
                   matrix[i][j] = data2[j];
               }
            }
           

            for(int i=0; i<num; i++){
                for(int j=0; j<num; j++){
                    match[i][j] = 0;
                    for(int k=0; k<stringCount; k++){
                        if(matrix[i][j].equals(announce[k])) match[i][j]=1;
                    }
                }
            }
            for(int i=0; i<num; i++){
                temp1 = 1;
                temp2 = 1;
                for(int j=0; j<num;j++){
                    if(match[i][j]==0) temp1 =0;
                    if(match[j][i]==0) temp2 =0;
                }
                count = count + temp1 + temp2;
            }
            temp1 = 1;
            temp2 = 1;
            for(int i=0; i<num; i++){
                if(match[i][i]==0) temp1 = 0;
                if(match[i][num-1-i]==0) temp2 = 0;
            }
            count = count  + temp1 + temp2;
            
            System.out.println(count);

        }
    }
}

