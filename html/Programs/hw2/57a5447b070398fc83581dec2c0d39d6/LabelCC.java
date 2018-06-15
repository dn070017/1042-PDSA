
import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] a = br.readLine().split("","");
            int num = Integer.parseInt(a[0]);
            num = num + 2;
            QuickFindUF qf = new QuickFindUF(num * num);

            int[] outla = new int[2];
            outla[0] = Integer.parseInt(a[1]);
            outla[1] = Integer.parseInt(a[2]);

            int[][] ma = new int[num][num];
            int[][] CC = new int[num][num];

            String loca0;
            String[] loca;
            int x;
            int y;
            int c = 1;

            while (!((loca0 = br.readLine()) == null)) {
                loca = loca0.split("","");
                x = Integer.parseInt(loca[0]);
                y = Integer.parseInt(loca[1]);
                ma[x][y] = 1;
                if (x==outla[0] && y==outla[1]){
                    System.out.printf(""%d"", 0);
                    return;
                }
            }
            for (int i = 0; i < num; i++) {
                if (i == 0 || i == num) {
                    for (int j = 0; j < num; j++) {
                        ma[i][j] = 1;
                    }
                }
            }
            for (int j = 0; j < num; j++) {
                if (j == 0 || j == num) {
                    for (int i = 0; i < num; i++) {
                        ma[i][j] = 1;
                    }
                }
            }
//            for (int i = 0; i < num; i++) {
//                for (int j = 0; j < num; j++) {
//                    System.out.printf(""%d"", ma[i][j]);
//                }
//            }

            for (int i = 1; i < num - 1; i++) {
                for (int j = 1; j < num - 1; j++) {
                    if (ma[i][j] == 0) {
                        if (ma[i - 1][j] == 0 && ma[i][j - 1] == 0) {
                            qf.union(i * num + j, (i - 1) * num + j);
                            if (qf.find(i * num + (j - 1)) > qf.find((i - 1) * num + (j))) {
                                qf.union(i * num + j, (i - 1) * num + (j));
                                qf.union(i * num + (j - 1), i * num + j);
                            } else {
                                qf.union(i * num + j, i * num + (j - 1));
                                qf.union((i - 1) * num + (j), i * num + j);
                            }

                        } else if (ma[i - 1][j] != 0 && ma[i][j - 1] == 0) {
                            qf.union(i * num + j, i * num + (j - 1));

                        } else if (ma[i - 1][j] == 0 && ma[i][j - 1] != 0) {
                            qf.union(i * num + j, (i - 1) * num + j);
                        } else if (ma[i - 1][j] != 0 && ma[i][j - 1] != 0) {
                            CC[i][j] = c;
                            c = c + 1;
                        }

                    }

                }
            }
            for (int i = 1; i < num - 1; i++) {
                for (int j = 1; j < num - 1; j++) {
                   if(CC[i][j]!=0){
                      if((i * num + j)==qf.find(outla[0]*num+outla[1])){
                          System.out.printf(""%d"", CC[i][j]);
                      }
                   }
                }
            }
            
            
            
            
            
        }
    }
}

