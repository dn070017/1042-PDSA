
.
 * To change this template file, choose Tools | Templates
.
 */
//package bingo;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author steven
 */
public class Bingo {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
     public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int straightLine=0;
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        //try(BufferedReader br = new BufferedReader(new FileReader(""test.txt""))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            boolean[][] bool=new boolean[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            announce = br.readLine().split("","");

            String[] line= new String[num];
            int counter=0;
                
            for (int i=0; i<num;i++){
                line=br.readLine().split("","");
                matrix[counter]=line;
                counter++;
                
            }
            //System.out.println(matrix[0][2].equals(announce[0]));
            for (int y=0;y<num;y++){
                for (int x=0;x<num;x++){
                    for (int i=0;i<stringCount;i++){
                        //System.out.println(announce[0]);
                        //System.out.println(matrix[0][2]);
                        //System.out.println(announce[i]);
                        if (!bool[y][x]){
                            bool[y][x] = matrix[y][x].equals(announce[i]);
                        }
                    }
                    //System.out.println(bool[y][x]);
                }
            }
            
            
            for (int y =0;y<num;y++){
                for (int x = 0; x< num ;x++){
                    if (bool[y][x]==false){
                        break;
                    }
                    if (x==num-1){
                        straightLine++;
                    }
                }
            } 

            for (int y =0;y<num;y++){
                for (int x = 0; x< num ;x++){
                    if (bool[x][y]==false){
                        break;
                    }
                    if (x==num-1){
                        straightLine++;
                    }
                }
            } 
            
            for (int i=0;i<num;i++){
                if (bool[i][i]==false){
                    break;
                }
                if (i==num-1){
                    straightLine++;
                }
            }
            for (int i=0;i<num;i++){
                if (bool[i][num-i-1]==false){
                    break;
                }
                if (i==num-1){
                    straightLine++;
                }
            }
            

        }
        System.out.println(straightLine);
     }
}

