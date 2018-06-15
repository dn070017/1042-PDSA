
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Jayden
 */
public class Percolation {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int num = Integer.valueOf(br.readLine());
            //檔案中第一個數告知為幾乘幾的矩陣            
            int z = num * num;
            UF uf = new UF(z + 2);
            //此class用來儲存所對應的cc，而+2是為了讓第一個row和最後一個row多一個總結，如此判斷次數不用那麼多(和講義一樣的方法)
            byte[][] matrix = new byte[num][num];
            //定義出主要的matrix
            String[] announce = new String[2];
            int a = 0, b = 0;
            if(num==1&&br.ready()==true){
                System.out.println(1+"",""+1);
                System.exit(0);
            }            
            while (uf.find(z) != uf.find(z + 1) && br.ready() == true) {
                announce = br.readLine().split("","");
                a = Integer.valueOf(announce[0]);
                b = Integer.valueOf(announce[1]);
                matrix[a - 1][b - 1] = 1;
                //翻轉成白色
                if (a == 1) {
                    uf.union(b - 1, z);
                    if (matrix[a][b - 1] == 1) {
                        uf.union(b - 1, num + b - 1);
                        //連結他下面的數
                    }
                } else if (a == num) {
                    uf.union(((num - 1) * num + b - 1), z + 1);
                    if (matrix[a - 2][b - 1] == 1) {
                        uf.union((num - 1) * num + b - 1, (num - 2) * num + b - 1);
                        //連結他上面的數
                    }
                } else {
                    if (matrix[a - 2][b - 1] == 1) {
                        uf.union((a - 1) * num + b - 1, (a - 2) * num + b - 1);
                        //連結上面的數
                    }
                    if (matrix[a][b - 1] == 1) {
                        uf.union((a - 1) * num + b - 1, a * num + b - 1);
                        //連結下面的數
                    }
                }
                if (b == 1) {
                    if (matrix[a - 1][b] == 1) {
                        uf.union((a - 1) * num + b - 1, (a - 1) * num + b);
                        //連結後面的數
                    }
                } else if (b == num) {
                    if (matrix[a - 1][b - 2] == 1) {
                        uf.union((a - 1) * num + b - 1, (a - 1) * num + b - 2);
                        //連結前面的數
                    }
                } else {
                    if (matrix[a - 1][b] == 1) {
                        uf.union((a - 1) * num + b - 1, (a - 1) * num + b);
                    }
                    if (matrix[a - 1][b - 2] == 1) {
                        uf.union((a - 1) * num + b - 1, (a - 1) * num + b - 2);
                    }
                }
            }

            if (uf.find(z) == uf.find(z + 1)) {
                System.out.println(a + "","" + b);
            } else {
                System.out.println(-1);
            }
        }
    }

