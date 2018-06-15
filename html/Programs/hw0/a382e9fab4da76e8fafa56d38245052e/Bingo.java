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
        
        try(BufferedReader br = new BufferedReader(new FileReader(""src/input.txt""))){
            String[] data = br.readLine().split("" "");
            for(int i=0; data[i]!=null;){
                int j =0;
            }
            String[] firstline = data[0].split("","");
            String[] secline = data[1].split("","");
            String[] thrline = data[2].split("","");
            String[] forthline = data[2].split("","");
            int announced_numbers = Integer.parseInt(firstline[0]);
            int a = Integer.parseInt(firstline[1]);
            String[][] matrix;
            matrix = new String [a][a] ;
                       
            
        }
        // TODO code application logic here
    }
    public static void creat_matrix(){
    
    
}
}
    


