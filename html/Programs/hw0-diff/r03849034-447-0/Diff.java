import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        //*  1. read the rest content of the file
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
            
            //*  2. store the announce strings (2nd line of the file) in variable announce
            announce = br.readLine().split("","");


            
            //*  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
            for (int i = 0; i < num; i++){
            matrix[i] = br.readLine().split("","");
            }
            
            
            boolean[][] card = new boolean [num] [num];
            for(int row = 0; row < num; row++){
                for(int col = 0; col < num; col++){
                    card[row][col] = false;
                    for(int line = 0; line < stringCount; line++){
                        if(matrix[row][col].equals(announce[line]))
                        card[row][col] = true;
                    }
                    
                }
            }
            
            //*  4. compare the matrix and announce strings (this is the tricky part)
            // Number of straight lines
            int count = 0;
            
            //* Check rows
            for(int row = 0; row < num; row++){
                int c = 0;
                for(int col = 0; col < num; col++){
                    if(card[row][col] = true){
                    c++;
                    }
                }
                if(c == num){
                    count++;
                }
            }
            
            //* Check columns
            for(int col = 0; col < num; col++){
                int c = 0;
                for(int row = 0; row < num; row++){
                    if(card[row][col] = true){
                    c++;
                    }
                }
                if(c == num){
                    count++;
                }
            }
            
            //* Check diagonals
            for(int row = 0; row < num; row++){
                int c = 0;
                if(card[row][row] = true){
                c++;
                }
                if(c == num){
                    count++;
                }
            }
            

            System.out.println(count);
        }
    }
}

