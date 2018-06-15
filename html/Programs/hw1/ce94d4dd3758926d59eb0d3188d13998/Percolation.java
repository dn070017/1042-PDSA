
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] a = br.readLine().split("","");
            int num = Integer.parseInt(a[0]);
            UF uf = new UF(num * num + 2);

            int[][] ma = new int[num][num];
//            for (int i = 0; i < num; i++) {
//                for (int j = 0; j < num; j++) {
//                    ma[i][j] = i * num + j ;
//                    System.out.printf(""%d\n "", ma[i][j]);
//                }
//            }
//            
            if (num < 2) {
                System.out.printf(""%d"", -1);
                return;
            } else {
                for (int i = 0; i < num; i++) {
                    uf.union(num * num, i);
                    uf.union((num * num - i - 1), (num * num + 1));
                }

//            String[] loca = br.readLine().split("","");
//            int x = Integer.parseInt(loca[0]);
//            int y = Integer.parseInt(loca[1]);
//            int loca_num1 = (x - 1) * num + y - 1;
//            int xx = x - 1;
//            int yy = y - 1;
//            ma[xx][yy] = 1;
                String loca;
                String[] loca1;

                String[] no;
                int x = 0;
                int y = 0;
                int loca_num2;
                int xx;
                int yy;

                while (!uf.connected(num * num, (num * num + 1))) {

                    if ((loca = br.readLine()) == null) {
                        System.out.printf(""%d"", -1);
                        return;
                    } else {

                        loca1 = loca.split("","");
                        x = Integer.parseInt(loca1[0]);
                        y = Integer.parseInt(loca1[1]);
                        loca_num2 = (x - 1) * num + y - 1;
                        xx = x - 1;
                        yy = y - 1;
                        ma[xx][yy] = 1;

                        if (xx == 0 && yy == 0) {
                            if (ma[xx][yy + 1] == 1) {
                                uf.union(((x - 1) * num + (y + 1) - 1), loca_num2);
                            }
                            if (ma[xx + 1][yy] == 1) {
                                uf.union((x) * num + (y) - 1, loca_num2);
                            }
                        } else if (xx == 0 && yy == num - 1) {
                            if (ma[xx + 1][yy] == 1) {
                                uf.union((x) * num + (y) - 1, loca_num2);
                            }
                            if (ma[xx][yy - 1] == 1) {
                                uf.union((x - 1) * num + (y - 1) - 1, loca_num2);
                            }

                        } else if (xx == num - 1 && yy == num - 1) {
                            if (ma[xx - 1][yy] == 1) {
                                uf.union(((x - 1 - 1) * num + y - 1), loca_num2);
                            }
                            if (ma[xx][yy - 1] == 1) {
                                uf.union((x - 1) * num + (y - 1) - 1, loca_num2);
                            }
                        } else if (xx == num - 1 && yy == 0) {
                            if (ma[xx - 1][yy] == 1) {
                                uf.union(((x - 1 - 1) * num + y - 1), loca_num2);
                            }
                            if (ma[xx][yy + 1] == 1) {
                                uf.union(((x - 1) * num + (y + 1) - 1), loca_num2);
                            }

                        } else if (xx == 0 && yy != 0 && yy != num - 1) {
                            if (ma[xx][yy + 1] == 1) {
                                uf.union(((x - 1) * num + (y + 1) - 1), loca_num2);
                            }
                            if (ma[xx + 1][yy] == 1) {
                                uf.union((x) * num + (y) - 1, loca_num2);
                            }
                            if (ma[xx][yy - 1] == 1) {
                                uf.union((x - 1) * num + (y - 1) - 1, loca_num2);
                            }

                        } else if (xx == num - 1 && yy != 0 && yy != num - 1) {
                            if (ma[xx - 1][yy] == 1) {
                                uf.union(((x - 1 - 1) * num + y - 1), loca_num2);
                            }
                            if (ma[xx][yy + 1] == 1) {
                                uf.union(((x - 1) * num + (y + 1) - 1), loca_num2);
                            }
                            if (ma[xx][yy - 1] == 1) {
                                uf.union((x - 1) * num + (y - 1) - 1, loca_num2);
                            }

                        } else if (yy == 0 && xx != num - 1 && xx != 0) {
                            if (ma[xx - 1][yy] == 1) {
                                uf.union(((x - 1 - 1) * num + y - 1), loca_num2);
                            }
                            if (ma[xx][yy + 1] == 1) {
                                uf.union(((x - 1) * num + (y + 1) - 1), loca_num2);
                            }
                            if (ma[xx + 1][yy] == 1) {
                                uf.union((x) * num + (y) - 1, loca_num2);
                            }
                        } else if (yy == num - 1 && xx != num - 1 && xx != 0) {
                            if (ma[xx - 1][yy] == 1) {
                                uf.union(((x - 1 - 1) * num + y - 1), loca_num2);
                            }
                            if (ma[xx][yy - 1] == 1) {
                                uf.union((x - 1) * num + (y - 1) - 1, loca_num2);
                            }
                            if (ma[xx + 1][yy] == 1) {
                                uf.union((x) * num + (y) - 1, loca_num2);
                            }
                        } else {
                            if (ma[xx - 1][yy] == 1) {
                                uf.union(((x - 1 - 1) * num + y - 1), loca_num2);
                            }
                            if (ma[xx][yy + 1] == 1) {
                                uf.union(((x - 1) * num + (y + 1) - 1), loca_num2);
                            }
                            if (ma[xx][yy - 1] == 1) {
                                uf.union((x - 1) * num + (y - 1) - 1, loca_num2);
                            }
                            if (ma[xx + 1][yy] == 1) {
                                uf.union((x) * num + (y) - 1, loca_num2);
                            }
                        }

                    }
                }
                System.out.printf(""%d,%d\n"", x, y);

            }

        }

    }

}

