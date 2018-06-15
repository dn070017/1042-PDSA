import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

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

            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            announce = br.readLine().split("","");
            //store Bingo announcement
            
            String[] temp = new String[num];
            for(int i = 0; i < num; i++){
                temp = br.readLine().split("","");
                for(int j = 0; j < num; j++){
                    matrix[i][j] = temp[j];
//                    System.out.printf(matrix[i][j]);
                }
            }
            //build Bingo matrix
            
            boolean[][] checkmatrix = new boolean[num][num];
            for(int i = 0; i < num; i++){
                for(int j = 0; j < num; j++){
                   for(int k = 0; k < stringCount; k++){
                       if(matrix[i][j].equals(announce[k])){
                           checkmatrix[i][j] = true;
                       }
                   }
                }
            }
            //build boolean checkmatrix
            
            int Countline = 0;
            int Countelement = 0;
            
            for(int i = 0; i < num; i++){
                for(int j = 0; j < num; j++){
                    if(checkmatrix[i][j]){
                        Countelement++;
                    }
                }
                if(Countelement == num){
                    Countline++;
                }            
                Countelement = 0;
            }
            //count the row line
            
            for(int j = 0; j < num; j++){
                for(int i = 0; i < num; i++){
                    if(checkmatrix[i][j]){
                        Countelement++;
                    }
                }
                if(Countelement == num){
                    Countline++;
                }
                Countelement = 0;
            }
            //count the column line
            
            int i = 0, j = 0;
            for(int c = 0; c < num; c++){
                if(checkmatrix[i][j]){
                    Countelement++;
                }
                i++;
                j++;
            }
            if(Countelement == num){
                    Countline++;
                }
            Countelement = 0;
            //count diagonal line (from left to right)
            
            i = 0; 
            j = num - 1;
            for(int c = 0; c < num; c++){
                if(checkmatrix[i][j]){
                    Countelement++;
                }    
                i++;
                j--;
            }
            if(Countelement == num){
                    Countline++;
                }
            Countelement = 0;
            //count diagonal line (from right to left)
            System.out.printf(""%d"", Countline);
        }
    }
}

