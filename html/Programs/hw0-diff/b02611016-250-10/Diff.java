import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data1 = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data1[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data1[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
           // System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            int[][] match=new int[num][num];
            int we=0;
            int bingoline=0;
            String[] data2 = br.readLine().split("","");
           for(int i=0;i<stringCount;i++)
           {
            announce[i]=data2[i];
                    }
           for(int i=0;i<num;i++)
           {   String[] data3 = br.readLine().split("","");
               for(int j=0;j<num;j++)
              {matrix[i][j]=data3[j];
                  
              }
               
           }
         for(int k=0;k<stringCount;k++)
         {
           for(int i=0;i<num;i++)
           { for(int j=0;j<num;j++)
             { if(matrix[i][j].equals(announce[k]))
                { match[i][j]=1;
                }
               
                  
             }
           }
         }
         for(int i=0;i<num;i++)
         {for(int j=0;j<num;j++)
           { if( match[i][j]!=1)
             {break;
             }
            we=we+1;
            if(we==num)
             {we=0;
              bingoline=bingoline+1;
     //         System.out.printf(""1"");
           //       System.out.printf(""\n"");
             }
           }
             we=0;
         }
         
         for(int i=0;i<num;i++)
         {for(int j=0;j<num;j++)
           { if( match[j][i]!=1)
             {break;
             }
            we=we+1;
            if(we==num)
             {we=0;
              bingoline=bingoline+1;
         //     System.out.printf(""2"");
           //       System.out.printf(""\n"");
             }
            
           }
          we=0;
         }
         
         for(int i=0;i<num;i++)
         {  if( match[i][i]!=1)
             {break;
             }
             we=we+1;
             if(we==num)
             {we=0;
              bingoline=bingoline+1;
          //    System.out.printf(""3"");
           //       System.out.printf(""\n"");
             }
           
         }
         we=0;
           for(int i=0;i<num;i++)
         {  if( match[num-i-1][i]!=1)
             {break;
             }
             we=we+1;
             if(we==num)
             {we=0;
              bingoline=bingoline+1;
            //  System.out.printf(""4"");
          //        System.out.printf(""\n"");
             }
         }
           we=0;
          //     System.out.printf(announce[0]);
         //          System.out.printf(""\n"");
           //        System.out.printf(matrix[0][2]);
            //       System.out.printf(""\n"");
          //         System.out.printf(matrix[1][2]);
          //         System.out.printf(""\n"");
          //         System.out.printf(matrix[2][2]);
         //          System.out.printf(""\n"");
         //          System.out.printf(""%d"",we);
         //          System.out.printf(""\n"");
                      System.out.printf(""%d"",bingoline);
             //      System.out.printf(""\n"");
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

