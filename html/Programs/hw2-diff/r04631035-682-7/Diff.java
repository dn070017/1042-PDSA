
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
//import edu.princeton.cs.algs4.*;/*

 /*
 * @author Pan
 */


public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        In in = new In(args[0]);
//                    System.out.println(in);
//                    System.out.println(""**"");
//               String[] data = in.readLine().split("","");
//               System.out.println(data[1]);
        String line;
        String target = """";

        while ((line = in.readLine()) != null) {
            target += line + "" "";
        }
        String[] numbersArray = target.split("" "");
        //System.out.println(numbersArray[0]);
        String[] num = numbersArray[0].split("",""); //num:維度,目標位置

        int stringCount = Integer.parseInt(num[0]); //維度轉數字
        String[][] matrix = new String[stringCount][stringCount]; //建立空矩陣
        int targetr = Integer.parseInt(num[1]); //目標位置row
        int targetc = Integer.parseInt(num[2]); //目標位置col

        UF uf = new UF(stringCount * stringCount);  //開List
        //ArrayList<Integer> replace = new ArrayList<Integer>();

        //放0
        for (int i = 1; i < numbersArray.length; i++) {
            String[] input = numbersArray[i].split("",""); //
            int input1 = Integer.parseInt(input[0]);
            int input2 = Integer.parseInt(input[1]);
            matrix[input1 - 1][input2 - 1] = ""0""; //位置從0,0開始
        }
        //放1
        for (int i = 0; i < stringCount; i++) {
            for (int j = 0; j < stringCount; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = ""1"";
                    // System.out.println(matrix[i][j]);
                }
            }
        }

        int label = 1; //標籤數字

        for (int i = 0; i < stringCount; i++) {
            for (int j = 0; j < stringCount; j++) {
                if (!""0"".equals(matrix[i][j])) {

                    if (i - 1 < 0 && j - 1 < 0) { //沒上沒左
                        String labels = Integer.toString(label);
                        matrix[i][j] = labels;
                        label++;
                    }
                    if (i - 1 >= 0 && j - 1 < 0) { //有上沒左
                        if (""0"".equals(matrix[i - 1][j])) {
                            String labels = Integer.toString(label);
                            matrix[i][j] = labels;
                            label++;
                        }
                        if (!""0"".equals(matrix[i - 1][j])) {
                            matrix[i][j] = matrix[i - 1][j];
                        }

                    }
                    if (i - 1 < 0 && j - 1 >= 0) { //沒上有左
                        if (""0"".equals(matrix[i][j - 1])) {
                            //label++;
                            String labels = Integer.toString(label);
                            matrix[i][j] = labels;
                            label++;
                        }
                        if (!""0"".equals(matrix[i][j - 1])) {
                            matrix[i][j] = matrix[i][j - 1];
                        }
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) { //有上有左
                        if (""0"".equals(matrix[i - 1][j]) && ""0"".equals(matrix[i][j - 1])) { //左0上0
                            //label++;
                            String labels = Integer.toString(label);
                            matrix[i][j] = labels;
                            label++;
                        }
                        if (!""0"".equals(matrix[i - 1][j]) && ""0"".equals(matrix[i][j - 1])) { //上非0左0

                            matrix[i][j] = matrix[i - 1][j];
                        }
                        if (""0"".equals(matrix[i - 1][j]) && !""0"".equals(matrix[i][j - 1])) { //上0左非0

                            matrix[i][j] = matrix[i][j - 1];
                        }
                        if (!""0"".equals(matrix[i - 1][j]) && !""0"".equals(matrix[i][j - 1])) { //上左皆非0
                            int left = Integer.valueOf(matrix[i][j - 1]);
                            int up = Integer.valueOf(matrix[i - 1][j]);
                            if (left > up) {

                                matrix[i][j] = matrix[i - 1][j];
                                uf.union(up, left);
                                //System.out.println(uf.find(4));
                            }
                            if (up > left) {
                                matrix[i][j] = matrix[i][j - 1];
                                uf.union(left, up);
                                //System.out.println(uf.find(4));
                            }
                            if (up == left) {
                                matrix[i][j] = matrix[i][j - 1];
                            }

                        }

                    }

                }

            }
        }
        //System.out.println(uf.find(4));
        int[] change = new int[stringCount * stringCount];
        int[] parent = new int[stringCount * stringCount];
        int k = 0;
        for (int i = 0; i < stringCount * stringCount; i++) {
            if (i != uf.find(i)) {
                change[k] = i;
                parent[k] = uf.find(i);
                k++;
            }
        }
        //System.out.println(change[3]);
        //System.out.println(parent.length);

        //System.out.println(label);
        //int b = 0;
        for (int i = 0; i < stringCount; i++) {
            for (int j = 0; j < stringCount; j++) {
                for (int l = 0; l < stringCount * stringCount; l++) {
                    String c = Integer.toString(change[l]);
                    if (c.equals(matrix[i][j])) {
                        matrix[i][j] = Integer.toString(parent[l]);

                    }
                }

            }
        }
        System.out.println(matrix[targetr - 1][targetc - 1]);

    }
}

