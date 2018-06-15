import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class Bingo {

     public static void main(String[] args) throws Exception {

          // read file from args[0] in Java 7 style
//          try (BufferedReader br = new BufferedReader(new FileReader(""C:/Users/user/Desktop/hw0/input.txt""))) {
          try (BufferedReader br = new BufferedReader(new FileReader(""C:/Users/user/Desktop/hw0/input1.txt""))) {
//                         try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

               String[] data = br.readLine().split("","");

               String line;
               String target = """";
    
               while ((line = br.readLine()) != null) {
                    target += "" "" + line;
               }

               String[] numbersArray = target.split("" "");


//            // store the first integer in variable stringCount (number of announced strings)
               int stringCount = Integer.parseInt(data[0]);
//            System.out.println(data.length);
//            // store the second integer in variable num (dimension of matrix: num * num)            
               int num = Integer.parseInt(data[1]);

               String[] announce = new String[stringCount];
               String[][] matrix = new String[num][num];

               String test1 = numbersArray[1];
               String[] test2 = test1.split("","");
               for (int j = 0; j < test2.length; j++) {
                    announce[j] = test2[j];                
               }

               for (int i = 2; i < numbersArray.length; i++) {
                    String test3 = numbersArray[i];
                    String[] test4 = test3.split("","");
                    for (int j = 0; j < test4.length; j++) {
                         matrix[i - 2][j] = test4[j];                        
                    }
               }

               ArrayList<Integer> Column = new ArrayList();
               ArrayList<Integer> Row = new ArrayList();

               for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num; j++) {
                         for (int k = 0; k < announce.length; k++) {
                              if (matrix[i][j].equals(announce[k]) == true) {
                                   Column.add(i + 1);
                                   Row.add(j + 1);
                              }
                         }
                    }
               }

               int output = 0;

               for (int j = 0; j < num; j++) {
                    int storage = 0;
                    for (int i = 0; i < Row.size(); i++) {
                         if (Column.get(i) == j + 1) {
                              storage++;
                         }
                    }
                    if (storage == num) {
                         output++;
                    }
               }

               for (int j = 0; j < num; j++) {
                    int storage = 0;
                    for (int i = 0; i < Row.size(); i++) {
                         if (Row.get(i) == j + 1) {
                              storage++;
                         }
                    }
                    if (storage == num) {
                         output++;
                    }
               }
               int storage = 0;
               for (int i = 0; i < Row.size(); i++) {
                    if (Row.get(i) == Column.get(i)) {
                         storage++;
                    }
                    if (storage == num) {
                         output++;
                    }
               }
               storage = 0;
               for (int i = 0; i < Row.size(); i++) {
                    if (Row.get(i) + Column.get(i) == num + 1) {
                         storage++;
                    }
                    if (storage == num) {
                         output++;
                         storage = 0;
                    }
               }
               System.out.println(output);
          }
     }
}




