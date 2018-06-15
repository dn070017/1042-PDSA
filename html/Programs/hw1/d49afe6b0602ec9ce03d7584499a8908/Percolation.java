import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LAB228
 */
public class Percolation {
    public static void main(String[] args) throws IOException
    {
    
     try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
         
            
            //read scale of grids            
            String data = br.readLine();            
            int scale = Integer.parseInt(data);
            UF uf = new UF(scale*scale+2);
            
            int[][] metrix = new int[scale][scale];
            
            //build a metrix with pure value content
            for(int y=0; y<scale; y++){
                for(int x=0; x<scale; x++){
                                        
                    metrix[x][y] = x+scale*y+1;
            //      System.out.println(mertix[x][y]);
                }
            }
            
            //conect first row & last row with symbol 0 & n*n+1
            for(int x=0; x<scale; x++){
                 uf.union(0, metrix[x][0]);
                 uf.union(scale*scale+1, metrix[x][scale-1]);
            }
      
/********************************************************************************************************************/            
            //union and check part
            int[] axis1 = new int[scale*scale]; //max of element is n*n  // store for input X-axis in order of [i]
            int[] axis2 = new int[scale*scale]; //max of element is n*n  // store for input Y-axis in order of [i]
            int counting = 0;
                   
            while (br.ready())
                {
                    String[] num = br.readLine().split("","");
                    
                    if(num[0].equals("" "")){break;}
                    
                    axis1[counting] = Integer.parseInt(num[0]);
                    axis2[counting] = Integer.parseInt(num[1]);

                    
                    
                    for(int i=0; i<counting; i++){
                        
                        if ( (axis1[counting]==axis1[counting-i-1]) && (Math.abs(axis2[counting]-axis2[counting-i-1])==1) && 
                                !uf.connected(metrix[axis1[counting]-1][axis2[counting]-1], metrix[axis1[counting-i-1]-1][axis2[counting-i-1]-1])
                           )
                        {
                            
                          uf.union(metrix[axis1[counting]-1][axis2[counting]-1], metrix[axis1[counting-i-1]-1][axis2[counting-i-1]-1]);
                          System.out.println(axis1[counting]+"",""+axis2[counting]+""~""+axis1[counting-i-1]+"",""+axis2[counting-i-1]);
                          System.out.println(""cc"");
                        }//end of if (these is for check and connect)
                        
                        if ( (axis2[counting]==axis2[counting-i-1]) && (Math.abs(axis1[counting]-axis1[counting-i-1])==1)
                               && !uf.connected(metrix[axis1[counting]-1][axis2[counting]-1], metrix[axis1[counting-i-1]-1][axis2[counting-i-1]-1])
                                
                           )
                        {
                        uf.union(metrix[axis1[counting]-1][axis2[counting]-1], metrix[axis1[counting-i-1]-1][axis2[counting-i-1]-1]);                                      
                        System.out.println(axis1[counting]+"",""+axis2[counting]+""~""+axis1[counting-i-1]+"",""+axis2[counting-i-1]);
                        System.out.println(""cc"");
                        }//end of if (these is for check and connect) 
                       
   
                    }//end of for

                                    if (uf.connected(0, scale*scale+1))
                                    {
                                    System.out.println(axis1[counting]+"",""+axis2[counting]);
                                    break;
                                    }//check percolation
                    counting ++;      
                    
                }//end of while
         
                        if(!uf.connected(0, scale*scale+1))
                        {    
                        System.out.println(""-1"");
                        }

                        if((axis1[0] == 0) && (scale == 1))
                        {    
                        System.out.println(""-1"");
                        }
         

         //int
     }//end of try
    
    }
    
}
