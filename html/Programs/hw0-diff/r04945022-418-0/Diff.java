package bingo;

import java.io.FileReader;
import java.lang.Integer;
import java.io.BufferedReader;
import java.util.Arrays;
import java.lang.Exception;


/**
 * @author Daniel r04945022
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
          try(BufferedReader br = new BufferedReader(new FileReader(""input.txt""))){
//            int lines = br.readLine().split(""/n"").length;
            String[] data = br.readLine().split(""/n"");
            String[] announces =br.readLine().split("","");
            String[] firstline = data[0].split("","");
            int announced_numbers = Integer.parseInt(firstline[0]); 
            int size;            
              size = Integer.parseInt(firstline[1]);
            String[][] matrix;
            matrix = new String [size][size] ;    //create 2D array
            String str;
            int i = 0;
            while ((str=br.readLine())!=null){
            matrix [i] = str.split("","");
            i++;
            }
                   //turn the called into 0
            for(int z=0; z<announced_numbers; z++){
            for(int x=0; x<size; x++){
                for(int y=0; y<size; y++){
                        if(matrix[x][y].equals(announces[z])){
                            matrix[x][y]= ""0"";
                        }
                      
                    }
                }
             }

   
            int numoflines=0;
            int k = 0;          //check lines
            for(int row=0; row<size;row++) {
                if(matrix[row][k].equals(matrix[row][k+1]) && matrix[row][k].equals(matrix[row][k+2])){
                    numoflines++;
                }   
            }

             for(int column = 0; column<size;column++) {
                if(matrix[k][column].equals(matrix[k+1][column]) && matrix[k][column].equals(matrix[k+2][column])){
                    numoflines++;
                }   
            }
            if((matrix[0][0].equals(matrix[1][1]))){
                if(matrix[1][1].equals(matrix[2][2])){
                    if(matrix[2][2]==""0""){
                        numoflines++;
                    }
                }
            }
            
            
            
           System.out.println(""there are""+numoflines+""straightlines"");
        }
        // TODO code application logic here
    }
    
}
    


