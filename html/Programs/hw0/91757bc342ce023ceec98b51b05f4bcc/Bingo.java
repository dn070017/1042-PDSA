/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author acer
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;


public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
//        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
//        try(BufferedReader br = new BufferedReader(new FileReader(""C:/Users/acer/Desktop/hw0/input.txt""))){
            
                 In in = new In(args[0]);
                  
                  
                  
                  
                  
                  
         String[] data = in.readLine().split("","");

               String line;
               String target = """";
               StringBuffer sb = new StringBuffer();
               while ((line = in.readLine()) != null) {
                    target += "" "" + line;
               }

               String[] numbersArray = target.split("" "");

               int count = numbersArray.length;

               for (int i = 1; i < numbersArray.length; i++) {
               }

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
               ArrayList<Integer> Column = new ArrayList<Integer>();
               ArrayList<Integer> Row = new ArrayList<Integer>();

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
//     }

}



