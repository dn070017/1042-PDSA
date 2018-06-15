//package hw0;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

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
            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            announce = br.readLine().split("","");
//            matrix = br.readLine().split("","");            
            int i,j;
            String[] temp = new String[num];
            for(i=0; i<num; i++){
            	temp = br.readLine().split("","");
            	for(j=0; j<num; j++){
            		matrix[i][j] = temp[j];
            		if(j!=num && j!=0){
            			//System.out.printf("", "");
            		}
            		//System.out.printf(""%s"", matrix[i][j]);
            	}
            	//System.out.printf(""\n"");
            }
            //System.out.printf(""ann: %s %s\n"", announce[0], announce[1]);
           
            //System.out.printf(""hahhaha %s %s %s\n"", matrix[0][0], matrix[0][1], matrix[0][2]);
            
            int straight_line = 0;
            int flag;
            for(i=0; i<num; i++){
            	flag = 0;
            	for(j=0; j<num; j++){
            		if ( matrix[i][j].equals(announce[0]) || matrix[i][j].equals(announce[1])){
            			flag ++;
            		}else{
            			break;
            		}
            	}
            	if(flag==num){
            		straight_line++;
            	}
            }
            
            for(j=0; j<num; j++){
            	flag = 0;
            	for(i=0; i<num; i++){
            		if ( matrix[i][j].equals(announce[0]) || matrix[i][j].equals(announce[1])){
            			flag ++;
            		}else{
            			break;
            		}
            	}
            	if(flag==num){
            		straight_line++;
            	}
            }
            
            for(i=0; i<num; i++){
            	flag = 0;
        		if ( matrix[i][i].equals(announce[0]) || matrix[i][i].equals(announce[1])){
        			flag ++;
        		}else{
        			break;
        		}
            	if(flag==num){
            		straight_line++;
            	}
            }
            
            for(i=0; i<num; i++){
            	flag = 0;
        		if ( matrix[i][num-1-i].equals(announce[0]) || matrix[i][num-1-i].equals(announce[1])){
        			flag ++;
        		}else{
        			break;
        		}
            	if(flag==num){
            		straight_line++;
            	}
            }
            System.out.printf(""%d\n"", straight_line);
            /*  now you can write your own solution to hw0
             *  you can follow the instruction described below:
             * 
             *  1. read the rest content of the file
             *  2. store the announce strings (2nd line of the file) in variable announce
             *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
             *  4. compare the matrix and announce strings (this is the tricky part)
             *  5. output how many 'straight line' are there in the matrix
             * 
             *  [note]
             *  you can use every data structure in standard Java packages (Java 8 supported)
             *  the packages in stdlib.jar and algs4.jar are also available for you to use
             *
             *  [hint]
.
             *  2. some data structure such as HashSet, HashMap, Arrays, ArrayList, Vector are very
             *     useful for solving problems. 
             */
        }
    }
}

