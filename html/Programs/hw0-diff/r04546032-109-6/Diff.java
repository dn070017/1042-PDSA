import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.io.*;




public class Bingo {

    public static void main(String[] args) throws IOException {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);
            
            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
        
            if(stringCount <2 || num <2)
            {
                throw new ValueException(""重新輸入你的bingo"");
            }
            LinkedHashSet<String> Announce= new LinkedHashSet<String>();
          
            
            String[][] matrix = new String[num][num];
            boolean [][] bingo = new boolean[num][num];
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            String[] line2 = br.readLine().split("","");
           
            
            for(int i = 0 ; i < stringCount ; i++)
            {
                Announce.add(line2[i]);
            }
            if(Announce.size()<2)
            {
                throw new ValueException(""重新輸入你的bingo"");
            }
             // initilization of a String array in Java
            String[] announce = new String[Announce.size()];
            Announce.toArray(announce);
            for(int a = 0;a < num ;a++)
            {
                String[] line = br.readLine().split("","");
                for(int b = 0; b < num ; b++)
                {                    
                    matrix[a][b] = line[b];
                    bingo[a][b] = false;
                }
            }
            
            for(int i = 0; i < Announce.size();i++)
            {
                for(int j =0; j < num;j++)
                {
                    for(int k = 0; k < num;k++)
                    {
                        if(announce[i].compareTo(matrix[j][k])==0)
                        {
                            bingo[j][k] = true;
                        }
                       
                    }
                }
            }
            int count = 0;
            int win = 0;
            for(int a =0;a<num;a++)
            {
                for(int b =0; b<num;b++)
                {
                    if(bingo[a][b] == true)
                    {
                        count++;
                    }
                    else 
                    {
                        count = count;
                    }
                }
                if(count == num)
                {
                    win++;
                }
                else
                {
                    count = 0;
                }
            }
            
            
            for(int c = 0; c< num;c++)
            {
                for(int r = 0; r < num;r++)
                {
                     if(bingo[r][c] == true)
                    {
                        count++;
                    }
                    else 
                    {
                        count = count;
                    }
                }
                 if(count == num)
                {
                    win++;
                }
                else
                {
                    count = 0;
                }
            }
            
             count = 0;
            for(int a =0;a<num;a++)
            {
                for(int b = 0; b < num;b++)
                {
                    if(a == b && bingo[a][b] == true)
                    {
                        count++;
                    }
                    
                    else 
                    {
                        count = count;
                    }
                }
            
                 if(count == num)
                {
                    win++;
                }
                else
                {
                    count = 0;
                }
            }
            
            
             count = 0;
             for(int a =0;a<num;a++)
            {
                for(int b = 0; b < num;b++)
                {
                    if((a+b) == num)
                   {
                         if(bingo[a][b] == true)
                    {
                        count++;
                    }
                    else 
                    {
                        count = count;
                    }
                   }
                }
                 if(count == num)
                {
                    win++;
                }
                else
                {
                    count = 0;
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
             System.out.println(win);
        }
    catch(ValueException e){
        System.out.println(e);
        }
    }
}

    
     class ValueException extends IOException{
        public ValueException(String message)
        {
            super(message);
        }
}
