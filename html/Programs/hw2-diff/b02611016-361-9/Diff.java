/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;
//import edu.princeton.cs.algs4.QuickUnionUF;
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
            QuickUnionUF uf=new QuickUnionUF(num*num);
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
            //System.out.println(""0"");
            break search;
            }
            
            int row = Integer.parseInt(datalocation.split("","")[0]);         
            int cloumn = Integer.parseInt(datalocation.split("","")[1]);   
            if(row>num||cloumn>num||row<1||cloumn<1)
            {System.out.println(""0"");
            break search;
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
                uf.union(cloumn-1, cloumn-2);
                labelcount[id[cloumn-2]]=labelcount[id[cloumn-2]]+1 ;
                }
                else{
                id[cloumn-1]=label ;
                labelcount[id[cloumn-1]]=labelcount[id[cloumn-1]]+1 ;      
               label=label+1 ;
                }
                
             }
         
                
           else{
               if(matrix[row-2][cloumn]==1)
               {if(matrix[row-1][cloumn-1]!=1){
                   id[(row-1)*num+cloumn-1]=id[(row-2)*num+cloumn-1];
                   uf.union((row-1)*num+cloumn-1, (row-2)*num+cloumn-1);
               labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1 ; 
               }
               else if(matrix[row-1][cloumn-1]==1)
               {if(id[(row-2)*num+cloumn-1]==id[(row-1)*num+cloumn-2])
               {id[(row-1)*num+cloumn-1]=id[(row-2)*num+cloumn-1];
               uf.union((row-1)*num+cloumn-1, (row-2)*num+cloumn-1);
                uf.union((row-1)*num+cloumn-2, (row-2)*num+cloumn-1);
               labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1 ;
               }
               
                  else if(id[(row-2)*num+cloumn-1]<id[(row-1)*num+cloumn-2])
               {k1=id[(row-1)*num+cloumn-2];
                   id[(row-1)*num+cloumn-1]=id[(row-2)*num+cloumn-1];
                   uf.union((row-1)*num+cloumn-2, (row-2)*num+cloumn-1);
                    labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1 ; 
                  /* for(int u=0;u<num*num;u++)
                   {if(labelcount[k1]==0){break;}
                       if(id[u]==k1)
                   {id[u]=id[(row-2)*num+cloumn-1];
                   labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1 ; 
                   labelcount[k1]=labelcount[k1]-1;
                   }
                   }*/
               }
               
               else{k2=id[(row-2)*num+cloumn-1];
                   id[(row-1)*num+cloumn-1]=id[(row-1)*num+cloumn-2];
                   uf.union((row-2)*num+cloumn-1, (row-1)*num+cloumn-2);
                   labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1 ; 
               /*  for(int u=0;u<num*num;u++)
                   {if(labelcount[k2]==0){break;}
                       if(id[u]==k2)
                   {id[u]=id[(row-1)*num+cloumn-2];
                   labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1 ; 
                   labelcount[k2]=labelcount[k2]-1;
                   
                   }
                   }*/
               }
               }
                
                   
               }
               
               else if(matrix[row-2][cloumn]!=1&&matrix[row-1][cloumn-1]==1)
               {id[(row-1)*num+cloumn-1]=id[(row-1)*num+cloumn-2];
               uf.union((row-1)*num+cloumn-1, (row-1)*num+cloumn-2);
                labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1;
              //  labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1;
                 
               }
            
               else
               {id[(row-1)*num+cloumn-1]=label;
               labelcount[id[(row-1)*num+cloumn-1]]=labelcount[id[(row-1)*num+cloumn-1]]+1;
               label=label+1;
               }
           }
         }
     }
 }

/*for(int row=2;row<num+1;row++){
for(int cloumn=1;cloumn<num+1;cloumn++)
{if(matrix[row-1][cloumn]==1)
        {if(matrix[row-2][cloumn]==1&&matrix[row-1][cloumn-1]==1&&(id[(row-2)*num+cloumn-1]!=id[(row-1)*num+cloumn-2]))
        {if(id[(row-2)*num+cloumn-1]<id[(row-1)*num+cloumn-2])
        { uf.union((row-1)*num+cloumn-1, (row-2)*num+cloumn-1);
            k1=id[(row-1)*num+cloumn-2];
                    labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1 ; 
                   for(int u=0;u<num*num;u++)
                   {if(labelcount[k1]==0){break;}
                       if(id[u]==k1)
                   {id[u]=id[(row-2)*num+cloumn-1];
                   labelcount[id[(row-2)*num+cloumn-1]]=labelcount[id[(row-2)*num+cloumn-1]]+1 ; 
                   labelcount[k1]=labelcount[k1]-1;
                   }
                   }
        }
        else 
        {  k2=id[(row-2)*num+cloumn-1];
            for(int u=0;u<num*num;u++)
                   {if(labelcount[k2]==0){break;}
                       if(id[u]==k2)
                   {id[u]=id[(row-1)*num+cloumn-2];
                   labelcount[id[(row-1)*num+cloumn-2]]=labelcount[id[(row-1)*num+cloumn-2]]+1 ; 
                   labelcount[k2]=labelcount[k2]-1;
                   
                   }
                   }
        }
        }
         
            
        }
}
}*/
  /*           for(int q=1;q<num+1;q++){
                 for(int w=1;w<num+1;w++){
           System.out.printf(""data %d, "", id[(q-1)*num+w-1] );
                 }
                 System.out.println("""");
           }
              System.out.printf("" %d,%d "", x_position,y_position );
             System.out.println("""");*/
         if(x_position>num||y_position>num)
          { System.out.printf(""0"");
          
          }
         else{
          if(id[(x_position-1)*num+y_position-1]==0){    System.out.printf(""0"");
            // System.out.println("""");
         }
          else{
             System.out.printf(""%d"",id[ uf.find((x_position-1)*num+y_position-1)] );
            // System.out.println("""");
          }
          if(x_position==0||y_position==0)
          { System.out.printf(""0"");}
          // System.out.printf(""%d,%d,%d,%d,%d, %d "", k1,k2,k3,k4,k5,k6 );
          // System.out.println("""");
         }
            br.close();
        }
    }
}

