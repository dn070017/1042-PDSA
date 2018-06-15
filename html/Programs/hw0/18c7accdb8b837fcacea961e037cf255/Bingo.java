//package hw0;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Bingo {
    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(""C:\\Users\\Wen\\Desktop\\hw0\\input.txt""))){
        //try(BufferedReader br = new BufferedReader(new FileReader(inFile))){

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
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            announce = br.readLine().split("","");
            //matrix = br.readLine().split("","");

            int i,j;
            
            /*Print announce Strings*/
            //System.out.printf(""announce:"");
            for(i=0; i<announce.length; i++){
            	//System.out.printf("" %s"", announce[i]);
            }
        	//System.out.printf(""\n"");
        	
            /*Print matrix*/
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
            
            int total_straight_line = 0;
            int l_diag_line = 0;
            int r_diag_line = 0;
            int row_line = 0;
            int column_line = 0;
            int flag;
            int k;
            
            /*calculate row line*/
            for(i=0; i<num; i++){
            	flag = 0;
            	for(j=0; j<num; j++){
            		for(k=0; k<announce.length; k++){
	            		if (matrix[i][j].equals(announce[k])){
	            			flag ++;
	            		}
            		}
            	}
            	if(flag==num){
            		row_line++;
            	}
            }
            
            /*calculate column line*/
            for(j=0; j<num; j++){
            	flag = 0;
            	for(i=0; i<num; i++){
            		for(k=0; k<announce.length; k++){
            			if ( matrix[i][j].equals(announce[k])){
	            			flag ++;
	            		}
            		}
            	}
            	if(flag==num){
            		column_line++;
            	}
            }  
            
            /*calculate l diagonal line*/
            flag = 0;
            for(i=0; i<num; i++){
        		for(k=0; k<announce.length; k++){
	        		if ( matrix[i][i].equals(announce[k])){
	        			flag ++;
	        		}
        		}
            	if(flag==num){
            		l_diag_line++;
            	}
            }
            
            /*calculate r diagonal line*/
            flag = 0;
            for(i=0; i<num; i++){
        		for(k=0; k<announce.length; k++){
	        		if ( matrix[i][num-1-i].equals(announce[k])){
	        			flag ++;
	        		}
        		}
            	if(flag==num){
            		r_diag_line++;
            	}
            }
            total_straight_line = r_diag_line + l_diag_line + column_line + row_line;
            //System.out.printf(""row:%d /col:%d /l_diag:%d /r_diag:%d /total %d\n"", row_line, column_line, l_diag_line, r_diag_line, total_straight_line);
            System.out.printf(""%d"",total_straight_line);
            
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

