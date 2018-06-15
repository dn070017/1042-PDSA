import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
            
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
//        System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

        int answer=0;
        int[] x=new int[num];
        int[] y=new int[num];
        int[] z=new int[2];
        announce = br.readLine().split("","");
        for (int i=0;i<num;i++){
            String[] content = br.readLine().split("","");
            x[i]=y[i]=0;
            for (int j=0;j<num;j++){
//                StdOut.print(content[j]);
                matrix[i][j]=content[j];
                if ((Arrays.binarySearch(announce, matrix[i][j]))<0){
                    x[i]++;
                    y[j]++;
                    
                }
                if (i==j&&(Arrays.binarySearch(announce, matrix[i][j]))<0)
                    z[0]++;
                if (i+j==num-1&&(Arrays.binarySearch(announce, matrix[i][j]))<0)
                    z[1]++;
                
            }
//            System.out.print(""\n"");
        }
        
        for(int i=0;i<num;i++){
            if(x[i]==0)
                answer++;
            if(y[i]==0)
                answer++;
            if(z[0]==0)
                answer++;
            if(z[1]==0)
                answer++;
                
        }            
        System.out.print(answer);
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

