import java.io.FileReader;
import java.io.BufferedReader;

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

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            boolean[][] mp = new boolean[num][num];
            String CurrentLine;

            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);

            /*  now you can write your own solution to hw0
             *  you can follow the instruction described below:
             * 
             *  1. read the rest content of the file
             *  2. store the announce strings (2nd line of the file) in variable announce
             *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
             *  4. compare the matrix and announce strings (this is the tricky part)
             *  5. output how many 'straight line' are there in the matrix
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
            announce=br.readLine().split("","");
            int lines=0;

            for(int i=0;i<num;i++){
                matrix[i]=br.readLine().split("","");
            }
//            System.out.printf(""%s\n "",announce[0]);

            for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                    for(int k=0;k<announce.length;k++) {
                        if(announce[k].equals(matrix[i][j])){
                            mp[i][j]=true;
                            break;
                        }
                        else mp[i][j]=false;
                    }
                }
            }

//            boolean a=mp[0][0]&mp[0][1];
//            System.out.printf(a);
            boolean diag=true,rdiag=true;
            for(int i=0;i<num;i++){
                boolean row=true,col=true;
                diag&=mp[i][i];
                rdiag&=mp[num-i-1][i];
                for(int j=0;j<num;j++) {
                    row&=mp[i][j];
                    col&=mp[j][i];
                }
                if(row==true)lines++;
                if(col==true)lines++;
            }
            if(diag==true)lines++;
            if(rdiag==true)lines++;
//
//
//            for(int i=0;i<num;i++){
//                for(int j=0;j<num;j++){
//                    System.out.printf(""%s "",matrix[i][j]);
//                }
//                System.out.printf(""\n"");
//            }
//
//            for(int i=0;i<num;i++){
//                for(int j=0;j<num;j++){
//                    System.out.printf(""%b "",mp[i][j]);
//                }
//                System.out.printf(""\n"");
//            }

            System.out.printf(""%d"",lines);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

