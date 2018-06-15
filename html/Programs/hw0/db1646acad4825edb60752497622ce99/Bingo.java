import java.io.*;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

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
            int count = 0;
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int line = 0;

            for(int j = 0; j < num; j ++){
            	for(int k = 0; k < num; k ++){
            		if(matrix[j][k].equals(announce[0]) | matrix[j][k].equals(announce[1])){
            			dot = dot + 1;
            			if(matrix[j][k].equals(announce[0])){
            				count = count + 1;
            			}
            		}
            		if(matrix[k][j].equals(announce[0]) | matrix[k][j].equals(announce[1])){
            			dot1 = dot1 + 1;
            			if(matrix[k][j].equals(announce[0])){
            				count1 = count1 + 1;
            			}
            		}
            	}
            	if(matrix[j][j].equals(announce[0]) | matrix[j][j].equals(announce[1])){
            		dot2 = dot2 + 1;
            		if(matrix[j][j].equals(announce[0])){
            				count2 = count2 + 1;
            			}
            	}
            	if(matrix[num-j-1][j].equals(announce[0]) | matrix[num-j-1][j].equals(announce[1])){
            		dot3 = dot3 + 1;
            		if(matrix[num-j-1][j].equals(announce[0])){
            				count3 = count3 + 1;
            			}
            	}

            	if(dot == num & count < num & count > 0){
            		line = line + 1;
            		dot = 0;
            		count = 0;
            	}
            	else{
            		dot = 0;
            		count = 0;
            	}
            	if(dot1 == num & count1 < num & count1 > 0){
            		line = line + 1;
            		dot1 = 0;
            		count1 = 0;
            	}
            	else{
            		dot1 = 0;
            		count1 = 0;
            	}
            	if(dot2 == num & count2 < num & count2 > 0){
            		line = line + 1;
            	}
            	if(dot3 == num & count3 < num & count3 > 0){
            		line = line + 1;
            	}
            }

            System.out.println(line);
        }
    }
}
