import java.io.FileReader;
import java.io.BufferedReader;

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
            String[][] check = new String[num][num];
            int [][] intmatrix= new int[num][num];
            
            // printf in Java (you should comment out or delete this in your final submission)
           // System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            // read the rest content of the file
            String[] country = br.readLine().split("","");
            /*
            String[] row1 = br.readLine().split("","");
            String[] row2 = br.readLine().split("","");
            String[] row3 = br.readLine().split("","");
           */
            //store the announce strings (2nd line of the file) in variable announce
           for(int i=0; i < stringCount; i++)
           {announce [i]= country[i];
        //   System.out.print(announce[i]);
        //  System.out.print(""\n"");
            }
          //store the matrix (from the 3rd line to the end of the file) in variable matrix

          for(int i=0;i<num;i++)
          { matrix[i] = br.readLine().split("",""); 
              }
            // print stored matrix
            for (String[] matrix1 : matrix) {
                for (int j = 0; j<matrix[0].length; j++) {
                //    System.out.print(matrix1[j]);
                }
              //  System.out.print(""\n"");
            }
          //compare the matrix and announce strings (this is the tricky part)
          //1)creat a same size matrix
          for (int i = 0 ;i<matrix.length;i++){
              for(int j=0;j<matrix[0].length;j++){
              intmatrix[i][j]=0;    
            }
          }
         //2)check matrix[][].equal(accounce[])
         for(int x = 0;x<stringCount;x++){
                for (int i=0;i<matrix.length;i++) {
                    for (int j = 0; j<matrix[0].length; j++) {
                        if(announce[x].equals(matrix[i][j])){
                            intmatrix[i][j]=1;
                        }
                    }
                    
                }
         }
         //print intmatrix
         /* for (int[] matrix3 : intmatrix) {
                for (int j = 0; j<intmatrix[0].length; j++) {
                    System.out.print(matrix3[j]);
                }
                System.out.print(""\n"");
            }
        */
         //3)print new check
/*         for (String[] matrix2 : check) {
                for (int j = 0; j<check[0].length; j++) {
             //       System.out.print(matrix2[j]);
                }
          //      System.out.print(""\n"");
            }
         //4)check straight
         int straight =0;
         //rightdown
         for (int i=0;i<check[0].length;i++){
             int counter=0;
             for(int j=0;j<check[0].length;j++){
                 if(i==j&&check[i][j]==""bingo""){
                     counter++;
            //         System.out.print(counter);
            //         System.out.print(""\n"");
                     
                     }
                      
                 }
                 if(counter==check[0].length){
                         straight++;
             
             }
             
 /*        }
//System.out.print(""\n"");
        //row
         for (int i=0;i<check[0].length;i++){
             int counter=0;
             for(int j=0;j<check[0].length;j++){
             
                 if(check[i][j]==""bingo""){
                     counter++;
                    // System.out.print(counter);
                    // System.out.print(""\n"");
                     
                     }
                  if(counter==check[0].length){
                         straight++;    
                    }
             
             
             }
         }
        
 //System.out.print(""\n"");        
         //col
         for (int i=0;i<check[0].length;i++){
             int counter=0;
             for(int j=0;j<check[0].length;j++){
                 if(check[j][i]==""bingo""){
                     counter++;
                  //   System.out.print(counter);
                  //   System.out.print(""\n"");
                     
                     }
                  if(counter==check[0].length){
                         straight++;    
                 }
             
             }
             
         }
  //  System.out.print(""\n"");     
         //leftdown
         
         for (int i=0;i<check[0].length;i++){
             int counter=0;
             for(int j=0;j<check[0].length;j++){
                 if(i+j==num&&check[i][j]==""bingo""){
                     counter++;
                  //   System.out.print(counter);
                   //  System.out.print(""\n"");
                     
                     }
                      
                 }
                 if(counter==check[0].length){
                         straight++;
             
             }
             
         }
         
     //    System.out.print(""\n"");
         
         //print out answer
          System.out.print(straight);
            /*  now you can write yur own solution to hw0
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
              int line = 0;
             //row
             for(int i = 0;i<num;i++){
                 int counter = intmatrix[i][0];
                 for(int j = 1;j<num;j++){
                     counter = counter + intmatrix[i][j];
                  //      System.out.print(counter);
                  //   System.out.print(""\n"");
                     
                 }
                         if(counter == num)
                             line++;
             }
            // System.out.print(""\n"");
             //col
             for(int i = 0;i<num;i++){
                 int counter = intmatrix[0][i];
                 for(int j = 1;j<num;j++){
                     counter = counter + intmatrix[j][i];
                 //       System.out.print(counter);
                  //   System.out.print(""\n"");
                     
                 }
                         if(counter == num)
                             line++;
             }
             // /
      
                 int count = intmatrix[num-1][0];
                 for(int i = 1;i<num;i++){
                     count = count + intmatrix[num-1-i][i];
                  //      System.out.print(count);
                  //   System.out.print(""\n"");
                     
                 }
                         if(count == num)
                             line++;
             
             // \
                int count2 = intmatrix[0][0];
                 for(int i = 1;i<num;i++){
                     count2 = count2 + intmatrix[i][i];
                 //       System.out.print(count2);
                 //    System.out.print(""\n"");
                     
                 }
                         if(count2 == num)
                             line++;
                         
 
            System.out.print(line);
 
        }
    }
}

