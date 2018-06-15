
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LabelCC {

    class My_QuickUnionUF {

        public int[] id;

        public My_QuickUnionUF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        public int num(int N) {
            return id[N];
        }

        private int root(int i) {
            while (i != id[i]) {
                i = id[i];
            }
            return i;
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            id[i] = j;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            //read and record the information of the matrix size and the targeted site
            String[] info = br.readLine().split("","");

            int matrix_length = Integer.parseInt(info[0]);
            int matrix_size = matrix_length * matrix_length;
            int target_x = Integer.parseInt(info[1]) - 1;
            int target_y = Integer.parseInt(info[2]) - 1;
            int child;

            //Create a matrx in order to mark the blocked site
            int[][] markmatrix = new int[matrix_length][matrix_length];
            for (int i = 0; i < matrix_length; i++) {
                for (int j = 0; j < matrix_length; j++) {
                    markmatrix[i][j] = -1;
                }
            }

            LabelCC LCC = new LabelCC();
            My_QuickUnionUF uf = LCC.new My_QuickUnionUF(matrix_size);

            //set the blocked site to 0
            while (br.ready()) {
                String[] blocked = br.readLine().split("","");
                if (blocked == null) {
                    break;
                }
                int x = Integer.parseInt(blocked[0]) - 1;
                int y = Integer.parseInt(blocked[1]) - 1;

                markmatrix[x][y] = 0;
            }

            int IDnum = 1;
            for (int i = 0; i < matrix_length; i++) {
                for (int j = 0; j < matrix_length; j++) {
                    //左上角
                    if (i == 0 && j == 0) {
                        if (markmatrix[i][j] == 0) {
                            continue;
                        } else {
                            markmatrix[i][j] = IDnum;
                            IDnum++;
                        }
                    } //上排
                    else if (i == 0 && j != 0) {
                        if (markmatrix[i][j] == 0) {
                            continue;
                        } else if (markmatrix[i][j - 1] == 0) {
                            markmatrix[i][j] = IDnum;
                            IDnum++;
                        } else {
                            markmatrix[i][j] = markmatrix[i][j - 1];
                        }
                    } //左排
                    else if (i != 0 && j == 0) {
                        if (markmatrix[i][j] == 0) {
                            continue;
                        } else if (markmatrix[i - 1][j] == 0) {
                            markmatrix[i][j] = IDnum;
                            IDnum++;
                        } else {
                            markmatrix[i][j] = markmatrix[i - 1][j];
                        }

                    } else {
                        if (markmatrix[i][j] == 0) {
                            continue;
                        } else {
                            //上>左
                            if (markmatrix[i - 1][j] > markmatrix[i][j - 1]) {
                                child = markmatrix[i - 1][j];
                                //左 = 0
                                if (markmatrix[i][j - 1] == 0) {
                                    markmatrix[i][j] = markmatrix[i - 1][j];
                                } else {
                                    markmatrix[i][j] = markmatrix[i][j - 1];
                                    
//                                    System.out.printf(""+ \n"");
//                                    System.out.printf(""i:%d "" + ""j:%d \n"", i, j);
                                    for (int m = 0; m < matrix_length; m++) {
                                        for (int n = 0; n < matrix_length; n++) {
                                            if (markmatrix[m][n] == child) {
                                                markmatrix[m][n] = markmatrix[i][j];
//                                                System.out.printf(""m:%d "" + ""n:%d \n"", m, n);
//                                                System.out.printf(""%d\n"", markmatrix[1][5]);
//                                                System.out.printf(""%d\n"", markmatrix[0][5]);
//                                                System.out.printf(""%d\n"", markmatrix[i-1][j]);
                                            }

                                        }
                                    }
                                }
                            }
                            //上<左
                            if (markmatrix[i - 1][j] < markmatrix[i][j - 1]) {
                                child = markmatrix[i][j - 1];
                                //上=0
                                if (markmatrix[i - 1][j] == 0) {
                                    markmatrix[i][j] = markmatrix[i][j - 1];
                                } else {
                                    markmatrix[i][j] = markmatrix[i - 1][j];
//                                    System.out.printf(""+ \n"");
//                                    System.out.printf(""i:%d "" + ""j:%d \n"", i, j);
                                    for (int m = 0; m < matrix_length; m++) {
                                        for (int n = 0; n < matrix_length; n++) {
                                            if (markmatrix[m][n] == child) {
                                                markmatrix[m][n] = markmatrix[i][j];
//                                                System.out.printf(""m:%d "" + ""n:%d \n"", m, n);
                                            }
                                        }
                                    }
                                    
                                }
                            }
                            //上=左
                            if (markmatrix[i - 1][j] == markmatrix[i][j - 1]) {
                                if (markmatrix[i - 1][j] == 0) {
                                    markmatrix[i][j] = IDnum;
                                    IDnum++;
                                } else {
                                    markmatrix[i][j] = markmatrix[i - 1][j];
                                }
                            }
                        }
                    }
                }

            }

//            System.out.printf(""%d , "", matrix_length);
//            System.out.printf(""%d , "", target_x);
//            System.out.printf(""%d \n"", target_y);
//            for (int i = 0; i < matrix_length; i++) {
//                for (int j = 0; j < matrix_length; j++) {
//                    System.out.printf(""%2d "", markmatrix[i][j]);
//                }
//                System.out.printf(""\n"");
//            }
            
            System.out.printf(""%d"", markmatrix[target_x][target_y]);
        } catch (IOException ex) {
            System.out.printf(""Failed to open the file"");
        }
    }
}

