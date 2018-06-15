import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashSet;

public class Bingo {

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            HashSet<String> hash = new HashSet<String>();
            String[] data = br.readLine().split("","");
            int count = 0;
            int set = Integer.parseInt(data[0]);
            int num = Integer.parseInt(data[1]);
            boolean[][] bingo = new boolean[num][num];
            boolean[][] result = new boolean[2 * num + 2][num];
            boolean[] cmp = new boolean[num];

            Point2D point = new Point2D(2.1, 3.1);
            data = br.readLine().split("","");
            for(int i = 0; i < set; i++){
                if(!hash.contains(data[i])) hash.add(data[i]);
            }

            for(int i = 0; i < num; i++){
                data = br.readLine().split("","");
                for(int j = 0; j < num; j++){
                    if(hash.contains(data[j])) bingo[i][j] = true;
                }
            }

            for(int i = 0; i < num; i++){
                result[i] = bingo[i];
                for(int j = 0; j < num; j++) {
                    result[num + j][i] = bingo[i][j];
                    if(i == j){
                        result[2 * num][i] = bingo[i][i];
                    }
                    if(i + j == num - 1){
                        result[2 * num + 1][j] = bingo[i][j];
                    }
                }
            }

            /*for(int i = 0; i < 2 * num + 2; i++){
                if(i == num || i == 2*num){
                    System.err.printf(""-----------------------------------\n"");
                }
                for(int j = 0; j < num; j++){
                    System.err.printf(""%b\t"", result[i][j]);
                }
                System.err.printf(""\n"");
            }*/

            for(int i = 0; i < num; i++){
                cmp[i] = true;
            }

            for(int i = 0; i < num * 2 + 2; i++){
                if(Arrays.equals(cmp, result[i])) count++;
            }
            System.out.printf(""%d\n"", count);
        }
    }
}

