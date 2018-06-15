/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import edu.princeton.cs.algs4.UF;
import java.io.FileReader;
import java.io.BufferedReader;
//import java.util.Date;
/**
 *
 * @author Lab304
 */
public class Percolation {
 public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){          
            // read a line and split by ','
            String[] data = br.readLine().split("","");                    
            // store the second integer in variable num (dimension of matrix: num * num)           
            int num = Integer.parseInt(data[0]);         
            // initilization of a String array in Java
            int[][] matrix = new int[num+1][num+2];
            int [] id=new int[num*num];
            int[] count=new int[num*num] ;
            int[] labelcount=new int[num*num+5] ;
            int label=num+1;
            int k1=0,k2=0,k3=0,k4=0,k5=0,k6=0;
            UF uf=new UF(num*num) ;
            //Date date = new Date();
            search: 
            
            for(int i=0;i<num*num;i++){
            String datalocation = br.readLine();
            if (datalocation==null) {
            System.out.println(""-1"");
            break ;
            }
            
            int row = Integer.parseInt(datalocation.split("","")[0]);         
            int cloumn = Integer.parseInt(datalocation.split("","")[1]);   
            if(row>num||cloumn>num||row<1||cloumn<1)
            {System.out.println(""-1"");
            break ;
            }            
         //   System.out.printf("" %d  %d\n"", row ,cloumn );
           matrix[row-1][cloumn]=1;
           if (row==1){ count[cloumn-1]=1;
               if(matrix[1][cloumn]==1)
               {  uf.union((row-1)+cloumn-1,1*num+cloumn-1);
                  
               }
           }
           else{
               if(matrix[row-2][cloumn]==1)
               {uf.union((row-1)*num+cloumn-1,(row-2)*num+cloumn-1);
                   if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]!=1)
                   { 
                     uf.union((row-1)*num+cloumn-2,(row-1)*num+cloumn-1);
                   }
                   else if (matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]!=1)
                   {
                     uf.union((row-1)*num+cloumn,(row-1)*num+cloumn-1);                  
                   }
                    else if(matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]==1)
                   { 
                    uf.union((row)*num+cloumn-1,(row-1)*num+cloumn-1);
                   }   
                     
                    else if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]!=1)
                   {  uf.union((row-1)*num+cloumn-2,(row-1)*num+cloumn-1);
                      uf.union((row-1)*num+cloumn,(row-1)*num+cloumn-1);
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                      else if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]==1)
                   {  uf.union((row-1)*num+cloumn-2,(row-1)*num+cloumn-1);
                      uf.union((row)*num+cloumn-1,(row-1)*num+cloumn-1);
                   }
                   
                       else if(matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]==1)
                   {  uf.union((row-1)*num+cloumn,(row-1)*num+cloumn-1);
                      uf.union((row)*num+cloumn-1,(row-1)*num+cloumn-1);
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                          else if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]==1)
                   {   uf.union((row-1)*num+cloumn-2,(row-1)*num+cloumn-1);
                      uf.union((row-1)*num+cloumn,(row-1)*num+cloumn-1);
                      uf.union((row)*num+cloumn-1,(row-1)*num+cloumn-1);
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                   
               }
               else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]==1)
               {uf.union((row-1)*num+cloumn-1,(row-1)*num+cloumn-2);
               
                   if(matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]!=1)
                   { uf.union((row-1)*num+cloumn,(row-1)*num+cloumn-2);
                     
                  // id[(row-1)*num+cloumn]=id[(row-1)*num+cloumn-2];
                   }
                   if(matrix[row-1][cloumn+1]!=1&matrix[row][cloumn]==1)
                   { uf.union((row)*num+cloumn-1,(row-1)*num+cloumn-2);
                   //id[(row)*num+cloumn-1]=id[(row-1)*num+cloumn-2];
                   }
                    if(matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]==1)
                   { uf.union((row-1)*num+cloumn,(row-1)*num+cloumn-2);
                     uf.union((row)*num+cloumn-1,(row-1)*num+cloumn-2);
                  // id[(row-1)*num+cloumn]=id[(row-1)*num+cloumn-2];
                   }
                    
               }
               else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]==1)
               {uf.union((row-1)*num+cloumn-1,(row-1)*num+cloumn);
                 if(matrix[row][cloumn]==1)
                 { uf.union((row)*num+cloumn-1,(row-1)*num+cloumn);
               //  id[row*num+cloumn-1]=id[(row-1)*num+cloumn];
               }
               }
               else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]==1)
               {uf.union(row*num+cloumn-1,(row-1)*num+cloumn-1);
               }
               
           
           }
               
          /* for(int q=0;q<num*num;q++){
           System.out.printf(""data %d,  "", uf.find(q));
           }
          // System.out.printf(""%d,%d,%d,%d,%d, %d "", k1,k2,k3,k4,k5,k6 );
           System.out.println("""");*/
           
           if(num==1&&row==1&&cloumn==1)
           {System.out.printf(""%d,%d\n"",  row,cloumn );
                      
                       /* for(int q=0;q<num;q++){
                        System.out.printf(""count %d,"", count[q] );
                         }
                       System.out.println("""");*/
                      break search;
               
           }
           for(int n=0;n<num;n++)
           {if(count[n]==1)
              {for(int p=0;p<num;p++)
                  {if(uf.connected(n,(num-1)*num+p))
                      {System.out.printf(""%d,%d\n"",  row,cloumn );
                      
                       /* for(int q=0;q<num;q++){
                        System.out.printf(""count %d,"", count[q] );
                         }
                       System.out.println("""");*/
                      break search;
                      }
                  }
              }
           }
           
           
     }
         //   br.close();
     //       for(int j=0 ;j<3;j++ )
      //      {for(int k=0;k<3;k++)
      //      { System.out.println( matrix[j][k]);
      //      }}
            
        
            // printf in Java (you should comment out or delete this in your final submission)
         //   System.out.printf(""number of announced strings: dimension of matrix: %d x %d\n"",  num, num);

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


