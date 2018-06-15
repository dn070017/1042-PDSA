import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Array;
public class Bingo {
    public static void main(String[] args) throws Exception {
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
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
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
             String[] data = br.readLine().split("","");
             for(int t=0;t<stringCount;t++){
             	announce[t]=data[t] ;
			 }
             for(int k=1;k<=num;k++){
             	String[] data = br.readLine().split("","");
             	for(int p=1;p<=num;p++){
             		matrix[k][p]=data[p];
				 }
			 }
			 
             for(int e=0;e<stringCount;e++){
             	for(int f=1;f<=num;f++){
             		for(int g=1;g<=num;g++){
             			while(announce[e]!=null){
             				if(matrix[f][g]==announce[e]){
             					matrix[f][g]=""NZ"";
							 }
						 }
					 }
				 }
			 }
			 int Count = 0 ;
			 int row = 0 ;
			 int Column = 0 ;
             for(int z=1;z<=num;z++){
             	for(int q=1;q<=num;q++){
             		if(matrix[z][q] == ""NZ"")
             		{
             			row++ ;
					 }
				 }
				 if(row==num){
				 	Count++ ;
				 	row = 0 ;
				 }
			 }
             
             for(int q=1;q<=num;q++){
             	for(int z=1;z<=num;z++){
             		if(matrix[z][q] == ""NZ"")
             		{
             			Column++ ;
					 }
				 }
				 if(Column==num){
				 	Count++ ;
				 	Column = 0 ;
				 }
			 }
             int oblique1 = 0 ;
             int oblique2 = 0 ;
             for(int t=1;t<=num;t++){
             	if(matrix[t][t]==""NZ""){
             		oblique1++;
				 }
			 }
			 if(oblique1==num){
			 	Count++;
			 }
			 for(int u=1;u<=num;u++){
			 	for(int o=num;o>=1;o--){
			 		if(matrix[u][o]==""NZ""){
             		oblique2++;
					 }
					 if(oblique2==num){
					 	Count++;
					 }
				}
			 }
			 System.out.printf(""%d"", Count);
             
    }
}
















