import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

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
          //  System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
                                 
            data = br.readLine().split("",""); //read second line
            
            int i,j,k;
            for(i=0;i<data.length;i++)  //bingo announce
                announce[i]=data[i];    
            
            int[][] bingocheck = new int[num][num];
            
            int tempcount=1;
            for(i=0;i<num;i++)   //bingo element
                for(j=0;j<num;j++)
                {
                    if(i!=tempcount)
                    {
                        data = br.readLine().split("","");  //read bingo element
                        tempcount=i;
                    }
                    matrix[i][j]=data[j];
                    //System.out.println(data.length);
                    for(k=0;k<stringCount;k++)
                        if(matrix[i][j].equals(announce[k])==true)
                        {
                            bingocheck[i][j]=1;
                            break;
                        }
                            
//                    if(j==num-1)
//                        System.out.println(bingocheck[i][j]);
//                    else
//                        System.out.print(bingocheck[i][j]+"" "");
                }
            
            int tempnum=num;
            int bingoline=2*num+2;
            int rowlinecheck=0,collinecheck=0,rightdialinecheck=0,leftdialinecheck=0;
            for(i=0;i<num;i++)   //check num of bingoline
                for(j=0;j<num;j++)
                {
                    if(bingocheck[i][j]!=1 && rowlinecheck==0)  //check rowline
                    {
                        bingoline=bingoline-1;
                        rowlinecheck=1;
                    }
                    if(bingocheck[j][i]!=1 && collinecheck==0)  //check colline
                    {
                        bingoline=bingoline-1;
                        collinecheck=1;
                    }
                    if(i==j && bingocheck[j][i]!=1 && rightdialinecheck==0)  //check right-dialine
                    {
                        bingoline=bingoline-1;
                        rightdialinecheck=1;
                    }
                    if(i+j==num-1 && (bingocheck[j][i]!=1 || bingocheck[i][j]!=1) && leftdialinecheck==0)  //check left-dialine
                    {
                        bingoline=bingoline-1;
                        leftdialinecheck=1;
                    }
                    if(j==num-1)
                    {
                        rowlinecheck=0;
                        collinecheck=0;
                    }
                }
            System.out.println(bingoline);
           
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
