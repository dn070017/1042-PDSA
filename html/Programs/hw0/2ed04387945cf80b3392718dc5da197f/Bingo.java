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
            boolean[][] board = new boolean[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            // read cross conuntry
            data = br.readLine().split("","");
            for(int idx = 0; idx < stringCount; idx++){
                announce[idx] = data[idx];
            }       
                        
            // read board
            for(int row = 0; row < num; row++) {
                data = br.readLine().split("","");
                for(int col = 0; col < num; col++){
                    matrix[row][col] = data[col];
                    board[row][col] = false;
                    //System.out.printf(""%s "",data[col]);
                }
                //System.out.printf(""\n"");
            }
            // answer reading
            //int ans = Integer.parseInt(br.readLine());
            // checking announce
            for(String s : announce){
                //System.out.println(s);
                for(int row = 0; row < num; row++){
                    for(int col = 0; col < num; col++){
                        board[row][col] = board[row][col] | s.equals(matrix[row][col]);
                        //System.out.printf(""%b "",board[row][col]);
                    }
                    //System.out.printf(""\n"");
                }
            }
            int count = game_check(board);
            System.out.println(count);
            //System.out.println(game_check(board) == ans);
            //return count;
        }
    }
    // for checking rows
    public static int row_check(boolean[][] board){
        int count = 0;// how many rows are straight lines
        boolean out =true; // Is the country be announced in the Bingo game
        int num = board[0].length;
        for(int row = 0; row < num; row++){
            int col = 0;
            out = true;
            do{
                out = out & board[row][col];
                col++;
            }while(out && col < num);
            if(out)count++;
            //System.out.println(out);
        }
        return count;
    }
    // for checking columns
    public static int col_check(boolean[][] board){
        return row_check(matrix_trans(board));
    }
    // for checking diagonals
    public static int cross_check(boolean[][] board){
        int count = 0;
        int num = board[0].length;
        boolean out_l2r = true;
        boolean out_r2l = true;
        // left to right diagonal check
        for(int idx = 0; idx < num; idx++){
            out_l2r = out_l2r & board[idx][idx];
            if(!out_l2r) break;
        }
        // right to left diagonal check
        for(int idx = 0; idx < num; idx++){
            out_r2l = out_r2l & board[idx][-idx-1+num];
            if(!out_r2l) break;
        }
        if(out_l2r)count++;
        //System.out.println(out_l2r);
        if(out_r2l)count++;
        //System.out.println(out_r2l);
        return count;
    }
    // Suming lines
    public static int game_check(boolean[][] board){
        return row_check(board) + col_check(board) + cross_check(board);
    }
    // For martrx transform row -> col; col -> row
    public static boolean[][] matrix_trans(boolean[][] matrix){
        int num = matrix[0].length;
        boolean[][] t_matrix = new boolean[num][num];
        for(int row = 0; row < num; row++){
            for(int col = 0; col < num; col++){
                t_matrix[row][col] = matrix[col][row];
            }
        }
        return t_matrix;
    }
}

