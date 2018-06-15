/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package labelcc;

import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author 士齊
 *
 *
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
    private static int[][] CC;
    private static int[][] borw;
    private static int[] id;
//    CC: 每個格子中的cc
//    borw: 每個格子是黑是白

    public LabelCC(int N) {
        CC = new int[N][N];
        borw = new int[N][N];
        id = new int[(N * N + 1) / 2 + 1];
        for (int i = 0; i < ((N * N + 1) / 2 + 1); i++) {
            id[i] = i;
        }

    }

    private static int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

//          part1
            String[] dataf = br.readLine().split("","");

            LabelCC labelcc = new LabelCC(Integer.parseInt(dataf[0]));
            int N = Integer.parseInt(dataf[0]);

            while (Boolean.TRUE) {
                String data = br.readLine();

                if (data != null) {
                    String[] randc = new String[2];
                    randc = data.split("","");
                    borw[Integer.parseInt(randc[0]) - 1][Integer.parseInt(randc[1]) - 1] = 1;
                } else {
                    break;
                }
            }

//           part2
            int labelnow = 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (borw[i][j] == 0) {
                        switch (i) {
                            case 0:
//                                最左邊一排
                                if (j == 0) {
                                    CC[0][0] = labelnow;
                                    labelnow++;
                                } else {
                                    if (borw[i][j - 1] == 0) {
                                        CC[i][j] = CC[i][j - 1];
                                    } else {
                                        CC[i][j] = labelnow;
                                        labelnow++;
                                    }
                                }
                                break;
                            default:
                                if (j == 0) {
                                    if (borw[i - 1][j] == 0) {
                                        CC[i][j] = CC[i - 1][j];
                                    } else {
                                        CC[i][j] = labelnow;
                                        labelnow++;
                                    }
//                                    最上面一排
                                } else {
                                    if (borw[i][j - 1] == 0) {
                                        if (borw[i - 1][j] == 0) {
//                                            左跟上都有
//                                            if (root(CC[i][j - 1]) > root(CC[i - 1][j])) {
//                                                id[root(CC[i][j - 1])] = root(CC[i - 1][j]);
//                                                CC[i][j] = CC[i - 1][j];
//                                            } else if (root(CC[i][j - 1]) == root(CC[i - 1][j])) {
//                                                CC[i][j] = CC[i - 1][j];
//                                            } else {
//                                                id[root(CC[i - 1][j])] = root(CC[i][j - 1]);
//                                                CC[i][j] = CC[i][j - 1];
//                                            }
                                            if (CC[i][j - 1] > CC[i - 1][j]) {
                                                id[root(CC[i][j - 1])] = root(CC[i - 1][j]);
                                                CC[i][j] = CC[i - 1][j];
                                            } else if (CC[i][j - 1] == CC[i - 1][j]) {
                                                CC[i][j] = CC[i - 1][j];
                                            } else {
                                                id[root(CC[i - 1][j])] = root(CC[i][j - 1]);
                                                CC[i][j] = CC[i][j - 1];
                                            }

                                        } else {
//                                            上有左沒有
                                            CC[i][j] = CC[i][j - 1];
                                        }
                                    } else {
                                        if (borw[i - 1][j] == 0) {
//                                            左有上沒有
                                            CC[i][j] = CC[i - 1][j];
                                        } else {
//                                            都沒有
                                            CC[i][j] = labelnow;
                                            labelnow++;
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }

//            part3
            for (int i = 0; i < (N * N + 1) / 2; i++) {
                id[i] = root(i);
            }

//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (borw[i][j] == 0) {
//                        CC[i][j] = root(CC[i][j]);
//
//                    }
//                }
//            }
            int r = Integer.parseInt(dataf[1]) - 1;
            int c = Integer.parseInt(dataf[2]) - 1;

            System.out.print(root(CC[r][c]));
//            System.out.print(id[CC[6][4]]);
        }
    }

}

