

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
            int[] line = {0,0,0,0};

            for(int j = 0; j < num; j ++){
            	for(int k = 0; k < num; k ++){
            		for(int m = 0; m < stringCount; m ++){
            			if(matrix[j][k].equals(announce[m])){
            				dot = dot + 1;
            			}
            			if(matrix[k][j].equals(announce[m])){
            				dot1 = dot1 + 1;
            			}
            		}
            	}
        		if(dot == num){
					line[0] = line[0] + 1;
					dot = 0;
				}
				else{
					dot = 0;
				}
				if(dot1 == num){
					line[1] = line[1] + 1;
					dot1 = 0;
				}
				else{
					dot1 = 0;
				}
            }

            for(int j = 0; j < num; j ++){
        		for(int m = 0; m < stringCount; m ++){
        			if(matrix[j][j].equals(announce[m])){
        				dot2 = dot2 + 1;
        			}
        			if(matrix[num-j-1][j].equals(announce[m])){
        				dot3 = dot3 + 1;
        			}
        		}
            }
            if(dot2 == num){
    			line[2] = 1;
    		}
    		if(dot3 == num){
    			line[3] = 1;
        	}
            System.out.println(line[0] + line[1] + line[2] + line[3]);
            /*System.out.println(line[0]);
            System.out.println(line[1]);
            System.out.println(line[2]);
            System.out.println(line[3]);*/
        }
    }
}

