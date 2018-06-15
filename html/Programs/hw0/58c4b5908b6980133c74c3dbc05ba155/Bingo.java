
import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {
        //use args to recieve input data
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]); //parseInt: read as Decimal Integer

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] refCountries = new String[stringCount];
            String[][] inputCountries = new String[num][num];    //2D arrays
//            String[] inputCountries = new String[num * num];    //1D array

            refCountries = br.readLine().split("","");

//            //1D array
//            for (int i = 0; i < num * num; i++) {
//                String[] temp = new String[num * num];
//                temp[i] = br.readLine().split("","")
//            }
//            System.out.println(refCountries[0]);
            //try to understand 2D arrays
            for (int i = 0; i < num; i++) {
                inputCountries[i] = br.readLine().split("","");
            }

            //change all the other refCountries to Japan
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    for (int k = 1; k < stringCount; k++) {
                        if (inputCountries[i][j].equals(refCountries[k])) {
                            inputCountries[i][j] = refCountries[0];
                        }
                    }
                }
            }

            int point = 0;
            int line = 0;

            //left-right
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (inputCountries[i][j].equals(refCountries[0])) {
                        point += 1;//換行如何重新計算？
                        if (point == num) {
                            line += 1;
                        }
                    }
                }
            }

            //up-down
            for (int j = 0; j < num; j++) {
                for (int i = 0; i < num; i++) {
                    if (inputCountries[i][j].equals(refCountries[0])) {
                        point += 1;
                        if (point == num) {
                            line += 1;
                        }
                    }
                }
            }

            //leftup-rightdown
            for (int i = 0; i < num; i++) {
                if (inputCountries[i][i].equals(refCountries[0])) {
                    point += 1;
                    if (point == num) {
                        line += 1;
                    }
                }
            }

            //leftdown-rightup
            for (int i = 0; i < num; i++) {
                if (inputCountries[num - 1 - i][i].equals(refCountries[0])) {
                    point += 1;
                    if (point == num) {
                        line += 1;
                    }
                }
            }
            System.out.println(line);
            /*try (BufferedReader br2 = new BufferedReader(new FileReader(args[1]))) {
             String[] data2 = br2.readLine().split("","");
                
             }*/
            // printf in Java (you should comment out or delete this in your final submission)
//        System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
.
        }

        /*  now you can write your own solution to hw0
         *  you can follow the instruction described below:
         * 
         *  1. read the rest content of the file
         *  2. store the announce strings (2nd line of the file) in variable announce
         *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
         *  4. compare the matrix and announce strings (this is the tricky part)
         *  5. output how many 'straight line' are there in the matrix
         *  6. alter the input file (changing countries) to assure the stability of your program
         * 
         *  [note]
         *  you can use every data structure in standard Java packages (Java 8 supported)
         *  the packages in stdlib.jar and algs4.jar are also available for you to use
         *
         *  [hint]
.
         *  2. some data structure such as HashSet, HashMap, Arrays, ArrayList, Vector are very
         *     useful for solving problems. 
         */
    }
}

