import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
/**
 *
 * @author LAB228
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //System.out.println(""阿里布達的東西"");
        
    try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){           
         

            // read a line and split by ','    Line 1
            String[] data0 = br.readLine().split("","");
             // read a line and split by ','    Line 2
            String[] data1 = br.readLine().split("","");
     /*       
            // read a line and split by ','    Line 3
            String[] data2 = br.readLine().split("","");
            // read a line and split by ','    Line 4
            String[] data3 = br.readLine().split("","");
            // read a line and split by ','    Line 5
            String[] data4 = br.readLine().split("","");   */
            
                // store the first integer in variable stringCount (number of announced strings)
                int stringCount0 = Integer.parseInt(data0[0]);
                // store the second integer in variable num (dimension of matrix: num * num)
                int num = Integer.parseInt(data0[1]);
            
             // printf in Java (you should comment out or delete this in your final submission)
     //       System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount0, num, num);

     //       System.out.printf(""\n%s\n"",data1[0]);      
            
            
            // initilization of a String array in Java
            String[] announce = new String[stringCount0];
            String[][] matrix = new String[num][num];
            
            int[][] plate = new int[num][num];
           
            // input announce contry
            for (int i=0 ; i<stringCount0 ; i++){
                announce[i] = data1[i];
            }
            
            //input bingo metrix         &     counting plate              
            
            for (int colume = 0; colume < num ; colume ++ ){
                
                // read a line and split by ','    Line 3~5
                String[] dataM = br.readLine().split("","");  
                
                for(int row = 0; row < num ; row ++ ){
                    
                    matrix[colume][row] = dataM[row] ;                    
                  
            //    System.out.printf(""%s "",announce[0]); 
            //    System.out.printf(matrix[colume][row]);                    
                
                    for(int a=0 ; a<stringCount0 ; a++){
                    if(matrix[colume][row].equals(announce[a])){
                        plate[colume][row] = 1;}
                    }
                          
                //    System.out.println(plate[colume][row]+""\n"");
                
                
                } //end of for-row
            }//end of for-colume
        
        int totalbingo = 0 ; 
        
        //row check
        
        int[] rowcheck = new int[num];
        
        for(int x=0 ; x < num ; x++){ 
            for(int y=0 ; y < num ; y++){
                
                rowcheck[x] = rowcheck[x] +  plate[x][y] ;           
             
            }//end of for-y
                if(rowcheck[x] == num){
                totalbingo ++;
                }//end of if    
        }//end of for-x   
        
                //colume check
        
        int[] columecheck = new int[num];
        
        for(int x=0 ; x < num ; x++){ 
            for(int y=0 ; y < num ; y++){
                
                columecheck[x] = columecheck[x] +  plate[y][x] ; //the differece between row/colume check is plate[y][x]      
             
            }//end of for-y
                if(columecheck[x] == num){
                totalbingo ++;
                }//end of if    
        }//end of for-x       

                  //diagonal check
        
        int[] diagonalcheck = new int[2];
        
        for(int x=0 ; x < num ; x++){
            
            diagonalcheck[0] = diagonalcheck[0] + plate[x][x];
            diagonalcheck[1] = diagonalcheck[1] + plate[x][num-1-x];
            
        }
        
        if(diagonalcheck[0] == num){
                totalbingo ++;
                }
        if(diagonalcheck[1] == num){
                totalbingo ++;
                }
           
        
        System.out.println(totalbingo+""\n"");

        }

        
    }
    
}

