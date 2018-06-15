/*
.
 * To change this template file, choose Tools | Templates
.
 */
package bingo;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author USER
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(""Bingo3.txt""))){
           
            String[] data = br.readLine().split("","");// read a line and split by ','
            int stringCount = Integer.parseInt(data[0]);// store the first integer in variable stringCount (number of announced strings)

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
            
            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            String[] data2=br.readLine().split("","");
            for (int i=0 ; i<stringCount ; i++){
                announce[i]= data2[i];
//                System.out.print(announce[i]+""\t"");
           }
//                System.out.println("""");
            for (int i=0 ; i<num ; i++){
                String[] data_temp=br.readLine().split("","");
                for (int j=0 ; j<num ; j++){
                    matrix[i][j]=data_temp[j];
//                    System.out.print(matrix[i][j]+""\t"");
                    }
//                System.out.println("""");
                }
             int [][] checking = new int[num][num];
             for (int i=0 ; i<num ; i++){
                 for (int j=0 ; j<num ; j++)
                     checking[i][j]=0;
             }
             for (int p=0 ; p<stringCount ; p++){
             for (int i=0 ; i<num ; i++){
               for (int j=0 ; j<num ; j++){
               if (matrix[i][j].equals(announce[p]))
                   checking[i][j]=1;
               }
           }
                 }
             //for (int i=0 ; i<num ; i++){
             //    for (int j=0 ; j<num ; j++)
             //        System.out.print(checking[i][j]+""\t"");
             //    System.out.println("""");
             //}
             //---------------------------Find Bingo line---------------------//
            int counter=0,counter_diagonal1=0,counter_diagonal2=0;
            for (int i=0 ; i<num ; i++){
            if (checking[i][i]==1)
                counter_diagonal1++;
            }
            for (int i=0 ; i<num ; i++){
            if (checking[i][(num-1)-i]==1)
                counter_diagonal2++;
            }
            if (counter_diagonal1==num)
                counter++;
            if (counter_diagonal2==num)
                counter++;
            
                for (int i=0 ; i<num ; i++){
                    int temp=0,temp2=0;
                    for (int j=0 ; j<num ; j++){
                        temp+=checking[i][j];
                        temp2+=checking[j][i];
                    }
                    if (temp==num)
                        counter++;
                    if (temp2==num)
                        counter++;
            }
                System.out.println(counter);
             }
}
}
            
               
                
        

    
    


