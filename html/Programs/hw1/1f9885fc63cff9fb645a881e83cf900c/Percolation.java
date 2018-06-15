import java.io.BufferedReader;
import java.io.FileReader;


public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)
            int num = Integer.parseInt(data[1]);

            // initialization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            // System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            // read the rest content of the file
            String[] temp = br.readLine().split("","");

            // store the announce strings (2nd line of the file) in variable announce
            for(int i = 0; i < stringCount; i++){
                announce[i] = temp[i];
            }

            // store the matrix (from the 3rd line to the end of the file) in variable matrix
            for(int i = 0; i < num; i++){
                String[] temp2 = br.readLine().split("","");
                for(int j = 0; j < num; j++){
                    matrix[i][j] = temp2[j];
                }
            }

            // compare the matrix and announce strings (this is the tricky part)

            int count = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;
            int straightLines = 0;


                for(int i = 0; i < num; i++) {
                    for (int j = 0; j < num; j++) {
                        for (String Announce : announce) {
                            if (matrix[i][j].equals(Announce)) count++;
                            if (matrix[j][i].equals(Announce)) count2++;
                        }
                    }
                    if (count == num) straightLines++;
                    if (count2 == num) straightLines++;
                    count = 0;
                    count2 = 0;
                }

            for(int j = 0; j < num; j++) {
                for (String Announce : announce) {
                    //if (matrix[0][0].equals(Announce)) {
                        if (matrix[j][j].equals(Announce)) count3++;

                    //}
                    //if (matrix[0][num-1].equals(Announce)) {
                        if (matrix[j][num-1-j].equals(Announce)) count4++;

                    //}
                }
            }


            if(count3 == num) straightLines++;
            if(count4 == num) straightLines++;

            //output how many 'straight line' are there in the matrix
            System.out.println(straightLines);

        }
    }
}
