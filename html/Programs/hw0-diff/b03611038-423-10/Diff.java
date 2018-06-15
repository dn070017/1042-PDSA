import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
          //try(BufferedReader br = new BufferedReader(new FileReader(""input.txt""))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
        
        
            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][]matrix2= new int[num][num];
            
           announce = br.readLine().split("","");
           int i = 0;
           while(i<num){
           matrix[i] = br.readLine().split("","");
           i++;
           }
           int z=0;
           int j;
           int count=0;
           for(int x=0;x<num;x++)
           {
               for(int y=0;y<num;y++)
               {
                   z=0;
                   while(z<stringCount)
                   {
                   j = matrix[x][y].compareTo(announce[z]);
                   if(j==0)
                   {
                       matrix2[x][y]=1;
                       break;
                   }
                   else
                   {
                       matrix2[x][y]=0;
                   }
                   z++;
                   }
               }
           }
           
           int sum=0;
           for(int x=0;x<num;x++)
           {
                for(int y=0;y<num;y++)
               {
                 sum=sum+ matrix2[x][y];
               }
                if(sum==num)
                {
                    count++;
                }
                sum=0;
           }
            for(int y=0;y<num;y++)
           {
                for(int x=0;x<num;x++)
               {
                 sum=sum+ matrix2[x][y];
               }
                if(sum==num)
                {
                    count++;
                }
                sum=0;
           }
            int sum2=0;
            for(int x=0;x<num;x++)
            {
                sum=sum+ matrix2[x][num-x-1];
                sum2=sum2+matrix2[x][x];
            }
            if(sum==num)
            {
                count++;
            }
            if(sum2==num)
            {
                count++;
            }
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
System.out.println(count);
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

