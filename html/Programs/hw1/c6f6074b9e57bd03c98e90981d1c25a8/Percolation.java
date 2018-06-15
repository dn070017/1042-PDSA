import java.io.IOException;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author 林康維
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file = new File(args[0]);
        try{
            Scanner File_in = new Scanner(file);
            String data = File_in.nextLine();
            String[] Nums = data.split("","");
            //System.out.println( Nums[0]+Nums[1] );
        //Scanner sc = new Scanner(new File(""input.txt""));
		// read a line and split by ','
            
            
            
            String data2 = File_in.nextLine();
            String[] announce = data2.split("","");
            //System.out.println( announce[0]+announce[1]);
            
            int LineIndex = 0 ;
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(Nums[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(Nums[1]);
            
            int [][] jumbo = new int[num][num];
            
            while(File_in.hasNextLine()){
                String data3 = File_in.nextLine();
                String[] mat = data3.split("","");
                for(int i=0 ; i<num  ; i++){
                    for(int j = 0 ; j < stringCount  ;j++){
                        //System.out.println(mat[i]);
                    if(mat[i].equalsIgnoreCase(announce[j])){
                        jumbo[LineIndex][i] = 1 ;
                    }
                }
                          }
            LineIndex +=1 ;
            }
            
            
            // initilization of a String array in Java

for(int i=0;i<num;i++){
    for(int j=0;j<num;j++){
    
    }
}

			int line = 0 ;
			

            // for(int x = 0 ; x < num ; x++){
				// for(int y = 0 ; y <num ; y++){
					// System.out.println(jumbo[x][y]) ;
				// }
			// }
			for (int k = 0 ; k < num ; k++){
				int count = 0 ;
				for (int l = 0 ; l < num ; l++){
					count += jumbo [k][l] ;					
				}
				if (count == num){
					line += 1 ;
				}
			}
			for (int m = 0 ; m < num ; m++){
				int count = 0 ;
				for (int n = 0 ; n < num ; n++){
					count += jumbo [n][m] ;					
				}
				if (count == num){
					line += 1 ;
				}
			}
			int count1 = 0 ;
			int count2 = 0 ;
			int p = 0 ;
			int q = num -1;
                     int qq = 0 ;
			while (p < num){
				count1 += jumbo[p][p] ;
				p += 1 ;
			}
			if (count1 == num){
				line += 1 ;
			}
			while (q >= 0){                            
				
			     count2 += jumbo[qq][q] ;
                          qq += 1 ;
			     q -= 1 ;
                        }
			if (count2 == num){
				line += 1;
			}	
        
                        		    System.out.println(line);
        }
                        
        catch(IOException e){
            System.out.println(""error!""); 
        }
    }
}
    


