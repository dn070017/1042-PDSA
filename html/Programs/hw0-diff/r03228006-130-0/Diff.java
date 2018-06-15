
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author user
 */
public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(""input.txt""))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
            
            // store the second integer in variable num (dimension of matrix: num * num)            
            //char coun1 = coun[1];
                    
                 

            // initilization of a String array in Java(declares an array of integers and allocates memory)
            String[] announce = new String[stringCount];
            announce = br.readLine().split("","");
            String[][] matrix = new String[num][num];
            for (int i = 0; i < num; i++) {
                String[] temp = br.readLine().split("","");
                for (int j = 0; j < num; j++){
                 matrix[i][j] = temp[j];
                }}
            
            // Select stringCount announced countries
            String[][] selected = new String[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 1; j < num; j++){
                    for (int k = 0; k < stringCount; k++){
                      if(matrix[i][j].equals(announce[k])) {
                      selected[i][j] = ""1"" ;} 
                }}}

            //* check how many straight lines in the bingo matrix
            int straight = 0;
            String[] bingo = new String[num];
            Arrays.fill(bingo, ""1"");
            //int [][] selected1 = new int[][] {{1,1,1},{1,0,1},{1,1,0}};
            //String selected2 = selected.toString();
            //*check by row
            for (String[] row : selected){
                //System.out.println(Arrays.toString(row));
                if (row.equals(bingo)) {
                     straight ++ ;
                     //System.out.println(""T"");
                } 
                else if (Arrays.equals(row,bingo)) {
                     straight ++ ; 
                     //System.out.println(""True"");
                }
                //else { System.out.println(""FALSE"");}
            }
            //*check by coloum
            String one = ""1"";
            for (int col=0; col<num; col++){
                int tempc = 0;
                for(int i=0; i<num; i++){
                   String sel=selected[i][col];
                   if (!one.equals(sel)) {
                     tempc ++ ;} 
                }
                //System.out.println(tempc);
                if(tempc==0){
                     straight ++;}
            }
             //*check by diagonal
            String[] dl=new String[num];
            String[] dr=new String[num];
            for (int i=0; i<num; i++){
                   dl[i]= selected[i][i];
                   dr[i]= selected[i][num-i-1];
                   if (Arrays.equals(dl,bingo)) {
                     straight ++;}
                   if (Arrays.equals(dr,bingo)) {
                     straight ++;}
            }         
        
            //matrix[0][0] = br.readLine().split("","")[0];    
            //float[] values = new float[] {0.1f, 0.2f, 0.3f};
           
            // String read = null;
            //while ((read = br.readLine()) != null) {

            // allocate data to String array in Java
            //announce[] = {""1"",""2""};
            //matrix[][] = {{""1"",""2"",""3""},{""1"",""2"",""3""}};

            
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            //System.out.println(announce[0]+"",""+announce[1]);
            //System.out.println(br.readLine());   
            //System.out.println(br.readLine().split("","")); 
            //for (int i = 0; i < num; i++) {
                //for (int j = 0; j < num; j++){
                 //System.out.printf(matrix[i][j]);  
                //}
            //System.out.println();
            //}
            //System.out.println(Integer.toString(selected[0][0]));
            //for (String[] row : selected) {
                 //System.out.println(Arrays.toString(row));  
                //}
             //System.out.println(Arrays.toString(bingo));
             System.out.println(straight);
            }
        }
}

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
