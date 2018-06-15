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
            int line = 0;
            String row = """";
            String column = """";
            String diagonal1 = """";
            String diagonal2 ="""";

            for(int j = 0; j < num; j ++){
            	for(int k = 0; k < num; k ++){
            		if(matrix[j][k].equals(announce[0]) | matrix[j][k].equals(announce[1])){
            			dot = dot + 1;
            			row = row + matrix[j][k];
            		}
            		if(matrix[k][j].equals(announce[0]) | matrix[k][j].equals(announce[1])){
            			dot1 = dot1 + 1;
            			column = column + matrix[k][j];
            		}
            	}
            	if(matrix[j][j].equals(announce[0]) | matrix[j][j].equals(announce[1])){
            		dot2 = dot2 + 1;
            		diagonal1 = diagonal1 + matrix[j][j];
            	}
            	if(matrix[j][num-j-1].equals(announce[0]) | matrix[j][num-j-1].equals(announce[1])){
            		dot3 = dot3 + 1;
            		diagonal2 = diagonal2 + matrix[j][num-j-1];
            	}

            	if(dot == num & row.contains(announce[0]+announce[1])){
            		line = line + 1;
            		dot = 0;
            		row = """";
            	}
            	else{
            		dot = 0;
            		row = """";
            	}
            	if(dot1 == num & column.contains(announce[0]+announce[1])){
            		line = line + 1;
            		dot1 = 0;
            		column = """";
            	}
            	else{
            		dot1 = 0;
            		column = """";
            	}
            	if(dot2 == num & diagonal1.contains(announce[0]+announce[1])){
            		line = line + 1;
            	}
            	if(dot3 == num & diagonal2.contains(announce[0]+announce[1])){
            		line = line + 1;
            	}
            }

            System.out.println(line);
        }
    }
}

