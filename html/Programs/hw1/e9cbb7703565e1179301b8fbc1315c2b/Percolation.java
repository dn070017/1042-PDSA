
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
//import edu.princeton.cs.algs4.*;

public class Percolation {

    public static void main(String[] args) {

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
//                    System.out.println(target);
        String[] numbersArray = target.split("" "");
        for (int i = 0; i < numbersArray.length; i++) {
//                              System.out.println(numbersArray[i]);
        }

        String[] num = numbersArray[0].split("",""); //維度
        int stringCount = Integer.parseInt(num[0]); //轉數字
        int[][] matrix = new int[stringCount][stringCount]; //建立空矩陣

//                    String[] num2 = numbersArray[1].split("","");                    
//                    System.out.println(numbersArray.length);     
        UF uf = new UF(stringCount * stringCount + 2);  //開List
        //                   ArrayList<Integer> Column = new ArrayList<Integer>();

        String[] data = numbersArray[2].split("",""); //讀取input
        int[] List_con = new int[numbersArray.length];

        for (int i = 1; i < numbersArray.length; i++) {
            String[] input = numbersArray[i].split("",""); //
            int input1 = Integer.parseInt(input[0]);
            int input2 = Integer.parseInt(input[1]);

            matrix[input1 - 1][input2 - 1] = 1; //位置從0,0開始
            List_con[i - 1] = (input1 - 1) * stringCount + (input2 - 1); //放入白點(編號)

//          System.out.println(List_con);
            //連結上點
            if ((input1 - 1) == 0) {
                uf.union(List_con[i - 1], stringCount * stringCount);
                //左上
                if (List_con[i - 1] == 0) {
                    if (matrix[0][1] == 1) {
                        uf.union(List_con[i - 1], 1);
                    }
                    if (matrix[1][0] == 1) {
                        uf.union(List_con[i - 1], stringCount);
                    }
                }
                //右上
                if (List_con[i - 1] == stringCount - 1) {
                    if (matrix[0][stringCount - 2] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] - 1 );
                    }
                    if (matrix[1][stringCount - 1] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] + stringCount);
                    }
                }
                //上排
                if (List_con[i - 1] > 0 && List_con[i - 1] < stringCount - 1) {
                    if (matrix[input1 - 1][input2] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] + 1);
                    }
                    if (matrix[input1 - 1][input2 - 2] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] - 1);
                    }
                    if (matrix[input1][input2 - 1] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] + stringCount);
                    }
                }
            } //連結下點
            else if ((input1 - 1) == stringCount - 1) {
                uf.union(List_con[i - 1], stringCount * stringCount + 1);
                //右下
                if (List_con[i - 1] == stringCount * stringCount - 1) {
                    if (matrix[input1 - 2][input2 - 1] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] - stringCount);
                    }
                    if (matrix[input1 - 1][input2 - 2] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] - 1);
                    }
                }
                //左下
                if (List_con[i - 1] == stringCount * (stringCount - 1)) {
                    if (matrix[input1 - 2][input2 - 1] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] - stringCount);
                    }
                    if (matrix[input1 - 1][input2] == 1) {
                        uf.union(List_con[i - 1], List_con[i - 1] + 1);
                    }
                }

                //下排
                if (List_con[i - 1] > stringCount * (stringCount - 1) && List_con[i - 1] < stringCount * stringCount - 1) {
                    if (matrix[input1 - 2][input2 - 1] == 1) { //上
                        uf.union(List_con[i - 1], List_con[i - 1] - stringCount);
                    }
                    if (matrix[input1 - 1][input2] == 1) { //右
                        uf.union(List_con[i - 1], List_con[i - 1] + 1);
                    }
                    if (matrix[input1 - 1][input2 - 2] == 1) { //左
                        uf.union(List_con[i - 1], List_con[i - 1] - 1);
                    }
                }

            } else if ((input1 - 1) != 0 && (input1 - 1) != stringCount - 1) {
                //左排
                if (List_con[i - 1] % stringCount == 0 && List_con[i - 1] != 0 && List_con[i - 1] != stringCount * (stringCount - 1)) {
                    if (matrix[input1 - 2][input2 - 1] == 1) { //上
                        uf.union(List_con[i - 1], List_con[i - 1] - stringCount);
                    }
                    if (matrix[input1 - 1][input2] == 1) { //右
                        uf.union(List_con[i - 1], List_con[i - 1] + 1);
                    }
                    if (matrix[input1][input2 - 1] == 1) { //下
                        uf.union(List_con[i - 1], List_con[i - 1] + stringCount);
                    }

                }
                //右排
                if (List_con[i - 1] % stringCount == stringCount - 1 && List_con[i - 1] != stringCount - 1 && List_con[i - 1] != stringCount * stringCount - 1) {
                    if (matrix[input1 - 2][input2 - 1] == 1) { //上
                        uf.union(List_con[i - 1], List_con[i - 1] - stringCount);
                    }
                    if (matrix[input1 - 1][input2 - 2] == 1) { //左
                        uf.union(List_con[i - 1], List_con[i - 1] - 1);
                    }
                    if (matrix[input1][input2 - 1] == 1) { //下
                        uf.union(List_con[i - 1], List_con[i - 1] + stringCount);
                    }

                } //中間
                else if ((input1 - 1) != 0 && (input1 - 1) != stringCount - 1 && input2 - 1 != 0 && input2 - 1 != stringCount - 1) {
                    if (matrix[input1 - 2][input2 - 1] == 1) { //上
                        uf.union(List_con[i - 1], List_con[i - 1] - stringCount);
                    }
                    if (matrix[input1 - 1][input2 - 2] == 1) { //左
                        uf.union(List_con[i - 1], List_con[i - 1] - 1);
                    }
                    if (matrix[input1 - 1][input2] == 1) { //右
                        uf.union(List_con[i - 1], List_con[i - 1] + 1);
                    }
                    if (matrix[input1][input2 - 1] == 1) { //下
                        uf.union(List_con[i - 1], List_con[i - 1] + stringCount);
                    }
                }

            }

            //判斷是否連接
            if (uf.connected(stringCount * stringCount, stringCount * stringCount + 1) == true) {
 //               System.out.println(""output:"");
                System.out.println((input1) + "","" + (input2));
                break;
            }

        }
        if (uf.connected(stringCount * stringCount, stringCount * stringCount + 1) == false) {
            System.out.println(""-1"");

        }
        //                   System.out.println(numbersArray.length);
    }
}
//

