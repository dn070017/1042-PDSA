/*
.
 * To change this template file, choose Tools | Templates
.
 */
package bingo;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
/**
 *
 * @author Lenovo
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        String[] data = br.readLine().split("","");
        
        int stringCount = Integer.parseInt(data[0]);
        int num = Integer.parseInt(data[1]);
        String[] announce = new String[stringCount];
        String[][] matrix = new String[num][num];
        System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
        
        //System.out.println(Arrays.deepToString(matrix));
        System.out.println(Arrays.toString(data));
        announce[0]=data[2];
        announce[1]=data[3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
            matrix[i][j] = data[i*3+j+4];
        }
        }
        System.out.println(Arrays.toString(announce));
        System.out.println(Arrays.deepToString(matrix));
        
        int OutNum = 0;
        for (int i = 0; i < 3; i++){
            String S0 = matrix[i][0];
            String S1 = matrix[i][1];
            String S2 = matrix[i][2];
            if (Arrays.asList(announce).contains(S0) & Arrays.asList(announce).contains(S1) & Arrays.asList(announce).contains(S2)){
                  OutNum=OutNum+1;
                    }
            else {
                continue;
                }
        }
        for (int i = 0; i < 3; i++){
            String S0 = matrix[0][i];
            String S1 = matrix[1][i];
            String S2 = matrix[2][i];
            if (Arrays.asList(announce).contains(S0) & Arrays.asList(announce).contains(S1) & Arrays.asList(announce).contains(S2)){
                  OutNum=OutNum+1;
                    }
            else {
                continue;
                }
        }
        String S00 = matrix[0][0];
        String S11 = matrix[1][1];
        String S22 = matrix[2][2];
        if (Arrays.asList(announce).contains(S00) & Arrays.asList(announce).contains(S11) & Arrays.asList(announce).contains(S22)){
            OutNum = OutNum+1;
        }
        String S0 = matrix[0][2];
        String S1 = matrix[1][1];
        String S2 = matrix[2][0];
        if (Arrays.asList(announce).contains(S0) & Arrays.asList(announce).contains(S1) & Arrays.asList(announce).contains(S2)){
            OutNum = OutNum+1;
        }
        System.out.println(OutNum);
} 
}
        
    
    




