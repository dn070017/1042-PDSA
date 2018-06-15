/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;
//import java.util.Date;
/**
 *
 * @author Lab304
 */
public class LabelCC {
 public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){          
            // read a line and split by ','
            String[] data = br.readLine().split("","");                    
            // store the second integer in variable num (dimension of matrix: num * num)           
            int num = Integer.parseInt(data[0]);         
            int x_position=Integer.parseInt(data[1]); 
            int y_position=Integer.parseInt(data[2]); 
            
            // initilization of a String array in Java
            int[][] matrix = new int[num+1][num+2];
            int [] id=new int[num*num];
            int[] count=new int[num*num] ;
            int[] labelcount=new int[num*num+5] ;
            int label=1;
            int k1=0,k2=0,k3=0,k4=0,k5=0,k6=0;
            
            //Date date = new Date();
            for(int x=0;x<num;x++)
            {for(int y=1;y<num+1;y++)
            {matrix[x][y]=1;
            }
            }
            
            search: 
            for(int i=0;i<num*num;i++){
            String datalocation = br.readLine();
            if (datalocation==null) {
           // System.out.println(""-1"");
            break ;
            }
            
            int row = Integer.parseInt(datalocation.split("","")[0]);         
            int cloumn = Integer.parseInt(datalocation.split("","")[1]);   
            if(row>num||cloumn>num||row<1||cloumn<1)
            {System.out.println(""-1"");
            break ;
            }            
         //   System.out.printf("" %d  %d\n"", row ,cloumn );
           matrix[row-1][cloumn]=0;
           
           
     }
labelchange_1:           
 for(int row=1;row<num+1;row++){        
     for(int cloumn=1;cloumn<num+1;cloumn++){
         
         if(matrix[row-1][cloumn]==1){
            if (row==1){ 
                
                if(matrix[row-1][cloumn-1]==1){
                id[cloumn-1]=id[cloumn-2];
                }
                else{
                id[cloumn-1]=label ;
                //   labelcount[label]=labelcount[label]+1 ;      
               label=label+1 ;
                }
                
             }
         
                
           else{
               if(matrix[row-2][cloumn]==1)
               {if(matrix[row-1][cloumn-1]!=1){
                   id[(row-1)*num+cloumn-1]=id[(row-2)*num+cloumn-1];}
               else if(matrix[row-1][cloumn-1]==1)
               {if(id[(row-2)*num+cloumn-1]<id[(row-1)*num+cloumn-2])
               {k1=id[(row-1)*num+cloumn-2];
                   id[(row-1)*num+cloumn-1]=id[(row-2)*num+cloumn-1];
                   for(int u=0;u<(row-1)*num+cloumn-1;u++)
                   {if(id[u]==k1)
                   {id[u]=id[(row-2)*num+cloumn-1];
                   }
                   }
                   }
               
               else{k2=id[(row-2)*num+cloumn-1];
                   id[(row-1)*num+cloumn-1]=id[(row-1)*num+cloumn-2];
                 for(int u=0;u<(row-1)*num+cloumn-1;u++)
                   {if(id[u]==k2)
                   {id[u]=id[(row-1)*num+cloumn-2];
                   }
                   }
               }
               }
                //labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                 /*  if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]!=1)
                   {  k1=id[(row-1)*num+cloumn-2];
                       for(int j=0;j<num*num;j++)
                      {if(labelcount[k1]==0){break;}
                          if(id[j]==k1)
                        {id[j]=id[(row-2)*num+cloumn-1];
                         labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                         labelcount[k1]=labelcount[k1]-1;
                        }
                      }
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                   else if (matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]!=1)
                   {k2=id[(row-1)*num+cloumn];
                    for(int j=0;j<num*num;j++)
                      {if(labelcount[k2]==0){break;}
                          if(id[j]==k2)
                        {id[j]=id[(row-2)*num+cloumn-1];
                        labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                        labelcount[k2]=labelcount[k2]-1;
                        }
                      }
                     //id[(row-1)*num+cloumn]=id[(row-2)*num+cloumn-1];                  
                   }
                    else if(matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]==1)
                   { k3=id[(row)*num+cloumn-1];
                       for(int j=0;j<num*num;j++)
                      {if(labelcount[k3]==0){break;}
                          if(id[j]==k3)
                        {id[j]=id[(row-2)*num+cloumn-1];
                         labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                        labelcount[k3]=labelcount[k3]-1;
                        
                        }
                      }
                    // id[(row)*num+cloumn-1]=id[(row-2)*num+cloumn-1];
                   }   
                     
                    else if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]!=1)
                   {  k1=id[(row-1)*num+cloumn-2];
                      k2=id[(row-1)*num+cloumn];
                      int k12=labelcount[k1]+labelcount[k2];
                       for(int j=0;j<num*num;j++)
                      {if(k12==0){break;}
                          if(id[j]==k1||id[j]==k2)
                        {id[j]=id[(row-2)*num+cloumn-1];
                        labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                        k12=k12-1;
                        }
                      }
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                      else if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]==1)
                   {  k1=id[(row-1)*num+cloumn-2];
                      k3=id[(row)*num+cloumn-1];
                      int k13=labelcount[k1]+labelcount[k3];
                       for(int j=0;j<num*num;j++)
                      {if(k13==0){break;}
                          if(id[j]==k1||id[j]==k3)
                        {id[j]=id[(row-2)*num+cloumn-1];
                        labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                        k13=k13-1;
                        }
                      }
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                   
                       else if(matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]==1)
                   {  k2=id[(row-1)*num+cloumn];
                      k3=id[(row)*num+cloumn-1];
                      int k23=labelcount[k2]+labelcount[k3];
                       for(int j=0;j<num*num;j++)
                      {if(k23==0){break;}
                          if(id[j]==k2||id[j]==k3)
                        {id[j]=id[(row-2)*num+cloumn-1];
                        labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                        k23=k23-1;
                        }
                      }
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }
                          else if(matrix[row-1][cloumn-1]==1&&matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]==1)
                   {   k1=id[(row-1)*num+cloumn-2];
                       k2=id[(row-1)*num+cloumn];
                      k3=id[(row)*num+cloumn-1];
                      int k123=labelcount[k1]+labelcount[k2]+labelcount[k3];
                       for(int j=0;j<num*num;j++)
                      {if(k123==0){break;}
                          if(id[j]==k2||id[j]==k3||id[j]==k1)
                        {id[j]=id[(row-2)*num+cloumn-1];
                        labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1;
                        k123=k123-1;
                        }
                      }
                     //id[(row-1)*num+cloumn-2]=id[(row-2)*num+cloumn-1];
                   }*/
                   
               }
               
               else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]==1)
               {id[(row-1)*num+cloumn-1]=id[(row-1)*num+cloumn-2];
              //  labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1;
                 /*  if(matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]!=1)
                   { k4=id[(row-1)*num+cloumn];
                       for(int f=0;f<num*num;f++)
                     {if(labelcount[k4]==0){break;}
                         if(id[f]==k4)
                        {id[f]=id[(row-1)*num+cloumn-2];
                        labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1;
                        labelcount[k4]=labelcount[k4]-1;
                        }
                     }
                  // id[(row-1)*num+cloumn]=id[(row-1)*num+cloumn-2];
                   }
                   if(matrix[row-1][cloumn+1]!=1&matrix[row][cloumn]==1)
                   { k5=id[(row)*num+cloumn-1];
                       for(int f=0;f<num*num;f++)
                     {if(labelcount[k5]==0){break;}
                         if(id[f]==k5)
                        {id[f]=id[(row-1)*num+cloumn-2];
                        labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1;
                        labelcount[k5]=labelcount[k5]-1;
                        }
                     }
                   //id[(row)*num+cloumn-1]=id[(row-1)*num+cloumn-2];
                   }
                    if(matrix[row-1][cloumn+1]==1&&matrix[row][cloumn]==1)
                   { k4=id[(row-1)*num+cloumn];
                     k5=id[(row)*num+cloumn-1];
                     int k45=labelcount[k4]+labelcount[k5];
                       for(int f=0;f<num*num;f++)
                     {if(k45==0){break;}
                         if(id[f]==k4||id[f]==k5)
                        {id[f]=id[(row-1)*num+cloumn-2];
                        labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1;
                        k45=k45-1;
                        }
                     }
                  // id[(row-1)*num+cloumn]=id[(row-1)*num+cloumn-2];
                   }
                    */
               }
            /*   else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]==1)
               {id[(row-1)*num+cloumn-1]=id[(row-1)*num+cloumn];
                labelcount[id[(row-1)*num+cloumn]]=labelcount[id[(row-1)*num+cloumn]]+1;
                 if(matrix[row][cloumn]==1)
                 { k6=id[row*num+cloumn-1];
                     for(int m=0;m<num*num;m++)
                   {if(labelcount[k6]==0){break;}
                       if(id[m]==k6)
                     {id[m]=id[(row-1)*num+cloumn];
                     labelcount[id[(row-1)*num+cloumn]]=labelcount[id[(row-1)*num+cloumn]]+1;
                     labelcount[k6]=labelcount[k6]-1;
                     }
                   }
                 }
               //  id[row*num+cloumn-1]=id[(row-1)*num+cloumn];
               }
               else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]!=1&&matrix[row-1][cloumn+1]!=1&&matrix[row][cloumn]==1)
               {id[(row-1)*num+cloumn-1]=id[row*num+cloumn-1];
               labelcount[id[row*num+cloumn-1]]=labelcount[id[row*num+cloumn-1]]+1;
               }*/
               else
               {id[(row-1)*num+cloumn-1]=label;
               labelcount[id[(row-1)*num+cloumn-1]]=labelcount[id[(row-1)*num+cloumn-1]]+1;
               label=label+1;
               }
           }
         
           /*for(int n=0;n<num;n++)
           {if(count[n]==1)
              {for(int p=0;p<num;p++)
                  {if(id[n]==id[(num-1)*num+p])
                      {System.out.printf(""%d,%d\n"",  row,cloumn );
                      
                        for(int q=0;q<num;q++){
                        System.out.printf(""count %d,"", count[q] );
                         }
                       System.out.println("""");
                      break labelchange_1;
                      }
                  }
              }
           }*/
         }
     }
 }
          
          
            /* for(int q=1;q<num+1;q++){
                 for(int w=1;w<num+1;w++){
           System.out.printf(""data %d, "", id[(q-1)*num+w-1] );
                 }
                 System.out.println("""");
           }
              System.out.printf("" %d,%d "", x_position,y_position );
             System.out.println("""");*/
        
          if(id[(x_position-1)*num+y_position-1]==0){    System.out.printf(""0"");
             System.out.println("""");
             
         }
          else{
             System.out.printf("" %d "", id[(x_position-1)*num+y_position-1] );
             System.out.println("""");}
          // System.out.printf(""%d,%d,%d,%d,%d, %d "", k1,k2,k3,k4,k5,k6 );
          // System.out.println("""");
             
             
             
             
             
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


