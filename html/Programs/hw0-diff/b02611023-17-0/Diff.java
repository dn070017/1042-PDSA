import java.io.*;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(""input.txt""))){

            // read a line and split by ','
            String [] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            announce = br.readLine().split("","");

            String[][] matrix = new String[num][num];
            for(int i = 0; i < num; i ++){
            	matrix[i] = br.readLine().split("","");
            }

            int dot = 0;
            int dot1 = 0;
            int dot2 = 0;
            int dot3 = 0;
            int line = 0;

            for(int j = 0; j < num; j ++){
            	if(matrix[j][j].equals(announce[0]) | matrix[j][j].equals(announce[1])){
            		dot = dot + 1;
            	}
            	if(matrix[num-j-1][j].equals(announce[0]) | matrix[num-j-1][j].equals(announce[1])){
            		dot1 = dot1 + 1;
            	}
            	for(int k = 0; k < num; k ++){
            		if(matrix[j][k].equals(announce[0]) | matrix[j][k].equals(announce[1])){
            			dot2 = dot2 + 1;
            		}
            		if(matrix[k][j].equals(announce[0]) | matrix[k][j].equals(announce[1])){
            			dot3 = dot3 + 1;
            		}
            	}
            	if(dot == num){
            		line = line + 1;
            	}
            	if(dot1 == num){
            		line = line + 1;
            	}
            	if(dot2 == num){
            		line = line + 1;
            		dot2 = 0;
            	}
            	else{
            		dot2 = 0;
            	}
            	if(dot3 == num){
            		line = line + 1;
            		dot3 = 0;
            	}
            	else{
            		dot3 = 0;
            	}
            }

            System.out.println(line);
        }
    }
}

