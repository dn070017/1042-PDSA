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
            String[] data = br.readLine().split("","");
            String[] announces =br.readLine().split("","");
            String[] firstline = data[0].split("","");
            int announced_numbers = Integer.parseInt(firstline[0]); 
            int a = Integer.parseInt(firstline[1]);            
            String[][] matrix;
            matrix = new String [a][a] ;    //create 2D array
            String str;
            while ((str=br.readLine())!=null){
            int i = 0;
            matrix [i] = str.split("","");
            i++;
            }
            int z=0;         //turn the called into 0
            while(announces[z]!=null){
            for(int x=0; x<=a; x++){
                for(int y=0; y<=a; y++){
                        if(matrix[x][y]==announces[z]){
                            matrix[x][y]= ""0"";
                        }
                      
                    }
                }
            z++;
            }
            int numoflines=0;
            int row = 0, column = 0;
            while(matrix[row][0]!=null){
            for(int k=0; k<=a;k++){
                while(matrix[row][k]!=""0""){
                    return;
                }   numoflines++;
            }
            row++;
            }
            while(matrix[0][column]!=null){
            for(int j=0; j<=a;j++){
                while(matrix[row][j]!=""0""){
                    return;
                }   numoflines++;
            }
            column++;
            }
            if((matrix[0][0]==matrix[1][1])){
                if(matrix[1][1]==matrix[2][2]){
                    if(matrix[2][2]==""0""){
                        numoflines++;
                    }
                }
            }
            
            
            System.out.println(""there are""+numoflines+""of straight lines"");
//            for(int l=0; l<=a; l++){
//                if(matrix[l][column]==Integer.parseInt(0)){
//                    
//                }
//            }
            
            
        }
        // TODO code application logic here
       
    }
   
}
    


