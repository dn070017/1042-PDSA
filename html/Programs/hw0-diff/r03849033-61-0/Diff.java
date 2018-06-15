import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(""input.txt""))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
        
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            announce = br.readLine().split("","");
            for (int i = 0; i < num; i++){
            matrix[i] = br.readLine().split("","");
            }
            
            boolean[][] mat = new boolean[num][num];
            for (int col = 0; col < num; col++){
                for (int row = 0; row < num; row++){
                    mat[row][col]=false;
                    for (int ann = 0; ann < stringCount; ann++){
                       if(matrix[row][col].equals(announce[ann]))
                       mat[row][col]=true;
                    }
                } 
            }            
            
            
            int count=0;
            //check col
            for (int col = 0; col < num; col++){
                int mark = 0;
                for (int row = 0; row < num; row++){
                    if(mat[row][col]==true){ 
                    mark++;}
                }
                if(mark == num)
                count++;
            }
            
            //check row
            for (int row = 0; row < num; row++){
                int mark = 0;
                for (int col = 0; col < num; col++){
                    if(mat[row][col]==true){ 
                    mark++;}
                }
                if(mark == num)
                count++;
            }

             //check dig
            int mark = 0;
            int mark2 = 0;            
            for (int x = 0; x < num; x++){
                if(mat[x][x]==true)
                    mark++;      
                if(mat[x][num-x-1]==true)
                    mark2++;                 
            }
            if(mark == num)
            count++;
            if(mark2 == num)
            count++;   
            System.out.println(count);
        }
    }
}

