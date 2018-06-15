/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author wayne17
 */
public class Percolation {
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            // read a line and split by ','
            String[] initial = br.readLine().split("","");
            int size = Integer.parseInt(initial[0]);
            
            //declare two matrix, default false
            boolean[][] input  = new boolean[size][size];
            boolean[][] result  = new boolean[size][size];
            
            String[] anounce;
            int x, y;
            while (br.ready()) {
                //read anounce
                anounce = br.readLine().split("","");
                x = Integer.parseInt(anounce[0])-1;
                y = Integer.parseInt(anounce[1])-1;
                input[y][x] = true;
                System.out.printf(""(%d,%d)\n"",x,y);
                if (y > 0) {
                    for (int i = y; i < size; i++){
                        result[i] = compare(result[i-1], input[i]);
                    }
                }
                else {
                    result[y]=input[y];
                    for (int i =y+1;i<size;i++){
                        result[i] = compare(result[i-1], input[i]);
                    }
                }
                //is_percolation
                if(is_percolation(result[size-1])){
                    System.out.printf(""%d,%d"", x+1, y+1);
                    break;
                }
            }
            if(!is_percolation(result[size-1])){
                System.out.printf(""false"");
            }
        }
    }
     public static boolean[] compare(boolean[] upper,boolean[] lower) {
         int size = upper.length;
         int left_index;
         boolean[] compared  = new boolean[size];
         /////
         System.out.printf(""upper :"");
         for(int j = 0; j<size; j++) {
                    System.out.printf(""%b,"",upper[j]);
                 }
                 System.out.printf(""\n"");
         System.out.printf(""lower :"");
         for(int j = 0; j<size; j++) {
                    System.out.printf(""%b,"",lower[j]);
                 }
                 System.out.printf(""\n"");
         /////
         for (int i = 0; i < size; i++) {
             if (upper[i] & lower[i]) {
                 compared[i] = true;
                 //left_flow
                left_index= i-1;
                while(left_index >= 0) {
                    if (lower[left_index]){
                        compared[left_index] = true;
                    }
                    else {
                        break;
                    }
                    left_index = left_index-1;  
                }
//                 right_flow
                while(i<size) {
                    if (lower[i]) {
                        compared[i] =true;
                        i++;
                    }
                    else {
                        break;
                    }
                }
             }
         }
         /////
         System.out.printf(""result:"");
         for(int j = 0; j<size; j++) {
                    System.out.printf(""%b,"",compared[j]);
                 }
                 System.out.printf(""\n\n"");
         /////
         return compared;
     }
     public static boolean is_percolation(boolean[] bottom) {
         int length = bottom.length;
         boolean contain = false;
         for (int i= 0; i < length; i++) {
             if (bottom[i]) {
                 contain = true;
                 break;
             }
         }
         return contain;
     }
}
