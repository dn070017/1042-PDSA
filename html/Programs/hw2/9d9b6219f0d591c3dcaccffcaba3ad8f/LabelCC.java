
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
            int[][] bingoCircle=new int[num][num];
            

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
            data = br.readLine().split("","");
            for(int i=0;i<stringCount;i++)
            {
                announce[i]= data[i];
                //System.out.printf(announce[i]+""\n"");  
            }
            for(int i=0;i<num;i++)
            {
                data = br.readLine().split("","");
                for(int j=0;j<num;j++)
                {
                    matrix[i][j]=data[j];
                    bingoCircle[i][j]=0;
                    
                    for(int k=0;k<stringCount;k++)
                    {
                        if(matrix[i][j].equals(announce[k]))
                        {
                            bingoCircle[i][j]=1;
                        }
                    }
                    //System.out.printf(matrix[i][j]+"" ""+bingoCircle[i][j]+""\n""); 
                }
            }
            int circleCountRow=0;
            int circleCountColumn=0;
            int circleCountLeft=0;
            int circleCountRight=0;
            int lineCount=0;
            int numR=num;
            for(int i=0;i<num;i++)
            {                
                for(int j=0;j<num;j++)
                {                    
                    circleCountRow+=bingoCircle[i][j];
                    circleCountColumn+=bingoCircle[j][i];                    
                    if(j==num-1)
                    {
                        if(circleCountRow==num)
                        {
                            lineCount++;
                        }
                        if(circleCountColumn==num)
                        {
                            lineCount++;
                        }                        
                        circleCountRow=0;
                        circleCountColumn=0;                         
                    }
                }                  
            }
            for(int j=0;j<num;j++)
            {
                    numR--;                    
                    circleCountLeft+=bingoCircle[j][j];
                    circleCountRight+=bingoCircle[j][numR];
                    if(j==num-1)
                    {                        
                        if(circleCountLeft==num)
                        {
                            lineCount++;
                        }
                        if(circleCountRight==num)
                        {
                            lineCount++;
                        }                        
                        circleCountLeft=0;
                        circleCountRight=0;
                        numR=num;
                    }
            }
            System.out.printf(""%d"",lineCount);
            
            
        }
    }
}

