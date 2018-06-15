
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.*;

/**
 *
 * @author asus
 */
public class Percolation {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int state = 0;
            int matrix_length = Integer.parseInt(br.readLine());
            int matrix_size = matrix_length * matrix_length;
            QuickFindUF uf = new QuickFindUF(matrix_size);

            if (matrix_length == 1) {
                String[] pos1;
                pos1 = br.readLine().split("","");

//                System.out.printf(pos1[0] + "" , "" + pos1[1] + ""\n"");
                if ((pos1[0].equals(""1"")) && (pos1[1].equals(""1""))) {
                    state = 2;
//                    System.out.printf(""%d\n"", matrix_length);
//                    System.out.printf(""%d\n"", state);
                    System.out.printf(pos1[0] + "" , "" + pos1[1] + ""\n"");

                } else {
                    state = -1;
//                    System.out.printf(""%d"", matrix_length);
                    System.out.printf(""%d"", state);

                }
            } else if (matrix_length <= 0) {
                state = -1;
                System.out.printf(""%d"", state);
            } else {
                //Union the upper row
                for (int i = 0; i < matrix_length; i++) {
                    uf.union(i, i + 1);
                    if ((i + 1) == matrix_length) {
                        break;
                    }
                }
                //Union the downner row
                for (int i = (matrix_size - matrix_length); i < (matrix_size); i++) {
                    uf.union(i, i + 1);
                    if ((i + 1) == (matrix_size - 1)) {
                        break;
                    }
                }
                //Create a matrx in order to mark the cell
                String[][] markmatrix = new String[matrix_length][matrix_length];
                for (int i = 0; i < matrix_length; i++) {
                    for (int j = 0; j < matrix_length; j++) {
                        markmatrix[i][j] = ""0"";
                    }
                }

                String[] pos;
                while (br.ready()) {
                    pos = br.readLine().split("","");
                    if (pos == null) {
                        break;
                    }

//                System.out.printf(""position："" + pos[0]);
//                System.out.printf("" "");
//                System.out.printf(pos[1]);
//                System.out.printf(""\n"");
                    //mark the opened position
                    int x = Integer.parseInt(pos[0]) - 1;
                    int y = Integer.parseInt(pos[1]) - 1;

//                System.out.printf(""x : "" + ""%d  "", x);
//                System.out.printf("" "");
//                System.out.printf(""y : "" + ""%d"", y);
//                System.out.printf(""\n"");
                    markmatrix[x][y] = ""1"";
                    int num = x * matrix_length + y;
//                System.out.printf(""num = "" + ""%d   "", num);
//                System.out.printf(""\n"");

                    if (x != 0 && y != 0 && x != (matrix_length - 1) && y != (matrix_length - 1)) {
                        //if up is opened
                        if (markmatrix[x - 1][y].equals(""1"")) {
                            uf.union(num, num - matrix_length);
                            //System.out.printf(""v\n"");
                        }
                        //if down is opened
                        if (markmatrix[x + 1][y].equals(""1"")) {
                            uf.union(num, num + matrix_length);
                            //System.out.printf(""w\n"");
                        }
                        //if left is opened
                        if (markmatrix[x][y - 1].equals(""1"")) {
                            uf.union(num, num - 1);
                            //System.out.printf(""x\n"");
                        }
                        //if right is opened
                        if (markmatrix[x][y + 1].equals(""1"")) {
                            uf.union(num, num + 1);
                            //System.out.printf(""y\n"");
                        }
                    } else if (x == 0 && y == 0) { // 左上角
                        if (markmatrix[x][y + 1].equals(""1"")) {
                            uf.union(num, num + 1);
                            //System.out.printf(""a\n"");
                        }
                        if (markmatrix[x + 1][y].equals(""1"")) {
                            uf.union(num, num + matrix_length);
                            //System.out.printf(""b\n"");
                        }

                    } else if (x == 0 && y == (matrix_length - 1)) { //右上角
                        if (markmatrix[x + 1][y].equals(""1"")) {
                            uf.union(num, num + matrix_length);
                            //System.out.printf(""c\n"");
                        }
                        if (markmatrix[x][y - 1].equals(""1"")) {
                            uf.union(num, num - 1);
                            //System.out.printf(""d\n"");
                        }
                    } else if (y == 0 && x == (matrix_length - 1)) { //左下角
                        if (markmatrix[x][y + 1].equals(""1"")) {
                            uf.union(num, num + 1);
                            //System.out.printf(""e\n"");
                        }
                        if (markmatrix[x - 1][y].equals(""1"")) {
                            uf.union(num, num - matrix_length);
                            //System.out.printf(""f\n"");
                        }
                    } else if (x == matrix_length - 1 && y == (matrix_length - 1)) { //右下角
                        if (markmatrix[x - 1][y].equals(""1"")) {
                            uf.union(num, num - matrix_length);
                            //System.out.printf(""g\n"");
                        }
                        if (markmatrix[x][y - 1].equals(""1"")) {
                            uf.union(num, num - 1);
                            //System.out.printf(""h\n"");
                        }
                    } else if (y == 0) { //最左排
                        if (markmatrix[x - 1][y].equals(""1"")) {
                            uf.union(num, num - matrix_length);
                            //System.out.printf(""i\n"");
                        }
                        if (markmatrix[x + 1][y].equals(""1"")) {
                            uf.union(num, num + matrix_length);
                            //System.out.printf(""j\n"");
                        }
                        if (markmatrix[x][y + 1].equals(""1"")) {
                            uf.union(num, num + 1);
                            //System.out.printf(""k\n"");
                        }
                    } else if (x == 0) { //最上排
                        if (markmatrix[x + 1][y].equals(""1"")) {
                            uf.union(num, num + matrix_length);
                            //System.out.printf(""m\n"");
                        }
                        if (markmatrix[x][y - 1].equals(""1"")) {
                            uf.union(num, num - 1);
                            //System.out.printf(""n\n"");
                        }
                        if (markmatrix[x][y + 1].equals(""1"")) {
                            uf.union(num, num + 1);
                            //System.out.printf(""o\n"");
                        }
                    } else if (y == (matrix_length - 1)) { //最右排
                        if (markmatrix[x - 1][y].equals(""1"")) {
                            uf.union(num, num - matrix_length);
                            // System.out.printf(""p\n"");
                        }
                        if (markmatrix[x + 1][y].equals(""1"")) {
                            uf.union(num, num + matrix_length);
                            //System.out.printf(""q\n"");
                        }
                        if (markmatrix[x][y - 1].equals(""1"")) {
                            uf.union(num, num - 1);
                            //System.out.printf(""r\n"");
                        }
                    } else if (x == (matrix_length - 1)) { //最下排
                        if (markmatrix[x][y - 1].equals(""1"")) {
                            uf.union(num, num - 1);
                            //System.out.printf(""s\n"");
                        }
                        if (markmatrix[x][y + 1].equals(""1"")) {
                            uf.union(num, num + 1);
                            //System.out.printf(""t\n"");
                        }
                        if (markmatrix[x - 1][y].equals(""1"")) {
                            uf.union(num, num - matrix_length);
                            //System.out.printf(""u\n"");
                        }
                    }

                    if (uf.connected(0, matrix_size - 1)) {
                        state = 0;
                        System.out.printf(pos[0] + "","" + pos[1]);
                        //System.out.printf(""\n"");
                        break;
                    } else {
                        state = -1;
                    }

                }
                if (state == -1) {
                    System.out.printf(""%d"", state);
                }
            }
        } catch (IOException ex) {
            System.out.printf(""Failed to open the file"");
        }
    }
}

