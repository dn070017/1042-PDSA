

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
            int line1 = 0;
            int line2 = 0;

            for(int j = 0; j < num; j ++){
            	for(int k = 0; k < num; k ++){
            		for(int m = 0; m < stringCount; m ++){
            			if(matrix[j][k].equals(announce[m])){
            				dot = dot + 1;
            			}
            			if(matrix[k][j].equals(announce[m])){
            				dot1 = dot1 + 1;
            			}
            			if(matrix[k][k].equals(announce[m])){
            				dot2 = dot2 + 1;
            			}
            			if(matrix[num-k-1][k].equals(announce[m])){
            				dot3 = dot3 + 1;
            			}
            		}
            	}
            	if(dot == num){
            		line = line + 1;
            		dot = 0;
            	}
        		else{
        			dot = 0;
        		}
        		if(dot1 == num){
        			line = line + 1;
        			dot1 = 0;
        		}
        		else{
        			dot1 = 0;
        		}
        		if(dot2 == num){
        			dot2 = 0;
        			line1 = 1;
        		}
        		else{
        			dot2 = 0;
        		}
        		if(dot3 == num){
        			dot3 = 0;
        			line2 = 1;
        		}
        		else{
        			dot3 = 0;
        		}
            }
            System.out.println(line + line1 + line2);
        }
    }
}

