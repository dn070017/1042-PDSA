
.
 * To change this template file, choose Tools | Templates
.
 */


import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Lenovo
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //FileReader fr = new FileReader(args[0]);
        //BufferedReader br = new BufferedReader(fr);
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] row1 = br.readLine().split("","");
            //System.out.println(data);
        
            int stringCount = Integer.parseInt(row1[0]);
            int num = Integer.parseInt(row1[1]);
            String[] announce = br.readLine().split("","");
            //String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            for (int i =0; i < num; i++){
                String[] Temparray = br.readLine().split("","");
                for (int j = 0; j < num; j++){
                    matrix[i][j] = Temparray[j];
                }
            }
        //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            
            //System.out.println(Arrays.deepToString(matrix));
            //System.out.println(Arrays.toString(data));
        
        int OutNum = num*2+2;
        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
                if (Arrays.asList(announce).contains(matrix[i][j])){
                    continue;
                }
                else {
                    OutNum = OutNum - 1;
                    break; 
                }
            }
        }
        
        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
            if (Arrays.asList(announce).contains(matrix[j][i])){
                continue;
            }
            else {
                OutNum=OutNum-1;
                break;
                }
            }
        }
        
        for (int i = 0; i < num; i++){
            if (Arrays.asList(announce).contains(matrix[i][i])){
                continue;
            }
            else{
                OutNum = OutNum - 1;
                break;
            }
        }
        
        for (int i = 0; i < num; i++){
            if (Arrays.asList(announce).contains(matrix[i][num-1-i])){
                continue;
            }
            else{
                OutNum = OutNum - 1;
                break;
            }
        }
        
        System.out.println(OutNum);
        }
} 
}
        
    
    




