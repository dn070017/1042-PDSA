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
            int count=0;
            int line=0;

            String[] data1 = br.readLine().split("","");
            {
                System.arraycopy(data1, 0, announce, 0, stringCount);
            }
           
           String temp=null;
           while((temp=br.readLine())!=null)
           {
            String[] array;
                array = temp.split("","");
                System.arraycopy(array, 0, matrix[count], 0, num);
             count++;
              }
           
           for(int i=0;i<stringCount;i++)
           {
             for(int j=0;j<num;j++)
             {
             for(int k=0;k<num;k++)
             {
             if (matrix[j][k].equals(announce[i]))
             {
             matrix[j][k]=""0"";
             }
             }
             }
                            }
          for(int j=0;j<num;j++)
           {
               int count1=0;
               for(int k=0;k<num;k++)
               {
                   if(""0"".equals(matrix[j][k]))  
                     count1++; 
                   else
                     break;          
               }   
               if(count1==num)
                line++;     
            } 
                      
          for(int j=0;j<num;j++)
           {
               int count1=0;
               for(int k=0;k<num;k++)
               {
                   if(""0"".equals(matrix[k][j]))
                   {  count1++;}
                   else
                   { break;}
               }
               if(count1==num)
               line++;
            }           

          int count1=0;
           for(int j=0;j<num;j++)
           {
               if(""0"".equals(matrix[j][j]))
                 count1++;
               else if(""0"".equals(matrix[j][num-j-1]))
                 count1++;          
               break;
            }
               if(count1==num)
                   line++;
               
           int count2=0;
           for(int j=0;j<num;j++)
           {
               if(""0"".equals(matrix[j][num-j-1]))
                 count2++;          
               break;
            }
               if(count2==num)
                   line++;
                                  
            
           System.out.println(line);
           
            /* printf in Java (you should comment out or delete this in your final submission)
            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num); */
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

