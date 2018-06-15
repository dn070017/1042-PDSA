import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Dennis
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */

  public static void main(String[] args)throws Exception  {

     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
      String[] data = br.readLine().split("","");
      int stringCount = Integer.parseInt(data[0]);
      int num = Integer.parseInt(data[1]);    
      int count=0;
      int output=0;
      
      String[] announce = new String[stringCount];
      String[][] matrix = new String[num][num];
      int[][] table = new int[num][num] ;
//      StdOut.println(data[1]);

       announce = br.readLine().split("","");
        
    for( int i=0; i<num ;i++ )
    {
        matrix[i]= br.readLine().split("","");
    }
    
    
        for( int i=0; i<stringCount ;i++ )
    {
            for( int j=0; j<num ;j++ )
        {           
                for( int k=0; k<num ;k++ )
            {

                if(announce[i].equals(matrix[j][k]))
                {
                    table[j][k]=1;
//                    StdOut.println(table[j][k]);
                }
                else 
                {
                    if(  table[j][k] != 1)
                    {
                        table[j][k]=0;
//                    StdOut.println(table[j][k]);
                    }
                }
            }
        }
    }
 
             for( int j=0; j<num ;j++ )
        {           
                 count=0;
                for( int k=0; k<num ;k++ )
            {
                  if(table[j][k]==1)
                  {
                      count=count+1;
                  }
                  if(count==num)   
                  {
                      output=output+1;
                      
                  }
            } 
        }
             
             for( int k=0; k<num ;k++ )
        {    
                count=0;           
                for( int j=0; j<num ;j++ )
            {
                  if(table[j][k]==1)
                  {
                      count=count+1;
                  }
                  if(count==num)   
                  {
                      output=output+1;
                      
                  }
            } 
        }
             
                    count=0;
                 for( int k=0; k<num ;k++ )
                   {
                    if(table[k][k]==1)
                  {
                      count=count+1;
                  }
                  if(count==num)   
                  {
                      output=output+1; 
                   }
                   }
                 
                    count=0;                 
                  for( int k=0; k<num ;k++ )
                  {
                    if(table[k][num-k-1]==1)
                  {
                      count=count+1;
                  }
                  if(count==num)   
                  {
                      output=output+1;   
                   }
                  }
                
             
             System.out.println(output);

            }
        }
    }

