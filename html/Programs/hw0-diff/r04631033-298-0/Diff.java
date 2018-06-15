import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
//import java.util.ArrayList;
import java.util.Vector;
public class Bingo {
public static void main(String[] args) throws Exception {

            // read file from args[0] in Java 7 style
try(BufferedReader br = new BufferedReader(new FileReader(""input.txt""))){
            // read a line and split by ','
    String[] data = br.readLine().split("","");
    String[] data2 = br.readLine().split("","");
            // store the first integer in variable stringCount (number of announced strings)
    int stringCount = Integer.parseInt(data[0]);
            // store the second integer in variable num (dimension of matrix: num * num)            
    int num = Integer.parseInt(data[1]);
    String[] announce = new String[stringCount];
    String[][] matrix = new String[num][num];         
 for(int i=0;i<stringCount;i++){
         announce[i] = data2[i];
                 }     
Vector<String []> str_vec = new Vector<String []>();
    int j = 0;
    int i = 0;
    String line;
    while ((line = br.readLine()) != null) { //每次讀一行 
           str_vec.add(line.split("",""));
             }            
for(i = 0; i < num; i++) {
matrix[i] = str_vec.elementAt(i);
}
for( i=0;i<num;i++){
    for(j=0;j<num;j++){
        int k;
        for(k=0;k<stringCount;k++){
            String d = announce[k];
            if (matrix[i][j].equals(d))break;
        }//System.out.print(matrix[i][j]);
        if(k==stringCount)matrix[i][j]=""0"";
        else matrix[i][j]=""1"";
    }
}
//System.out.print(Arrays.deepToString(matrix));
String x =  Integer.toString(1);
String y =  Integer.toString(0);
int ab = 0; 
 for(i=0;i<num;i++){
     int count = 0;
     int count1 = 0;
     for(j=0;j<num;j++){
         if (matrix[i][j].equals(x)){
             count++;         
         }
         if (matrix[j][i].equals(x)){
             count1++;
         }
    }
     if(count == num){
         ab += 1;
         }
     if(count1 == num){
         ab += 1;
     }
 }     
for(i=0;i<num;i++){
    int count2 = 0;
    if (matrix[i][i].equals(x)){
       count2++;         
          }
    if(count2 == num){
         ab += 1;
         } 
}
for(i=0;i<num;i++){
    int count3 = 0;
    if (matrix[i][num-1-i].equals(x)){
       count3++;         
          }
    if(count3 == num){
         ab += 1;
          } 
}
System.out.print(ab);
}
}
}
