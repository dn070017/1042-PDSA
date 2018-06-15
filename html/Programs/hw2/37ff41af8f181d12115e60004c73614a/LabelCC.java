/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 許志鵬
 */
import java.io.*;
import java.util.Scanner;

public class LabelCC {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    @SuppressWarnings(""empty-statement"")
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            InputStream is = new FileInputStream(args[0]);

            InputStreamReader isr = new InputStreamReader(is, ""UTF8"");

            BufferedReader br = new BufferedReader(isr);

            Scanner sn = new Scanner(br);

            StringBuilder buf = new StringBuilder();

            while (sn.hasNext()) {

                buf.append(sn.next()).append(""\n"");

            }

            String str = buf.toString();

            String[] token = str.split(""\n"");

            String[] given = token[0].split("","");

            int[] x = new int[token.length - 1];

            int[] y = new int[token.length - 1];

            int size = Integer.parseInt(given[0]);

            int a = Integer.parseInt(given[1]) - 1;

            int b = Integer.parseInt(given[2]) - 1;         //a given site

            for (int i = 0; i < token.length - 1; i++) {

                String[] site = token[i + 1].split("","");

                x[i] = Integer.parseInt(site[0]);

                y[i] = Integer.parseInt(site[1]);

            }

            int[][] map = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[i][j] = 1;
                }
            }

            for (int i = 0; i < x.length; i++) {
                map[x[i] - 1][y[i] - 1] = 0;
            }                                      //mapping

            //scanning
            int label = 1;

            for (int i = 0; i < size; i++) //row 1 : special case
            {
                if (i == 0) //[0][0]
                {
                    if (map[0][i] != 0) {
                        map[0][i] = label;
                    } else if (map[0][i] == 0) {
                        map[0][i] = 0;
                    }
                } else {
                    if (map[0][i] == 0) {
                        map[0][i] = 0;
                       // label++;
                    } else {
                        if(map[0][i-1]!=0)
                        {
                            map[0][i] = map[0][i-1];                            
                        }
                        else{
                            label++;
                            map[0][i]=label;
                        }
                    }

                }

            }

            for (int i = 1; i < size; i++) {                //other rows

                for (int j = 0; j < size; j++) {

                    if (j == 0) {
                        if (map[i - 1][j] == 0 && map[i][j] != 0) //column 1 special case
                        {
                            label++;
                            map[i][j] = label;
                        } else if (map[i - 1][j] != 0 && map[i][j] != 0) {
                            map[i][j] = map[i - 1][j];
                        }
                    } else {

                        if (map[i - 1][j] == 0 && map[i][j - 1] == 0 && map[i][j] != 0) {
                            label++;
                            map[i][j] = label;
                        } else if (map[i - 1][j] != 0 && map[i][j - 1] == 0 && map[i][j] != 0) {
                            map[i][j] = map[i - 1][j];
                        } else if (map[i - 1][j] == 0 && map[i][j - 1] != 0 && map[i][j] != 0) {
                            map[i][j] = map[i][j - 1];
                        } else if (map[i - 1][j] != 0 && map[i][j - 1] != 0 && map[i][j] != 0) {

                            if (map[i - 1][j] > map[i][j - 1]) {
                                map[i][j] = map[i][j - 1];
                            } else if (map[i - 1][j] < map[i][j - 1]) {
                                map[i][j] = map[i - 1][j];
                            } else if (map[i - 1][j] == map[i][j - 1]) {
                                map[i][j] = map[i - 1][j];
                            }
                        }

                    }

                }
            }
            // start connecting
            QuickUnionUF LC = new QuickUnionUF(size * size);

            for (int i = 0; i < size * size; i++) {
                if (i / size == 0 && i % size == 0) {
                    ;
                } else if (i / size == 0 && i % size != 0) {
                    if (map[i / size][i % size - 1] == map[i / size][i % size]) {
                        LC.union(i, i - 1);
                    }

                } else if (i % size == 0 && i / size != 0) {
                    if (map[i / size - 1][i % size] == map[i / size][i % size]) {
                        LC.union(i, i - size);
                    }
                } else if (i / size != 0 && i % size != 0) {
                    if (map[i / size][i % size - 1] == map[i / size][i % size]) {
                        LC.union(i, i - 1);
                    }

                    if (map[i / size - 1][i % size] == map[i / size][i % size]) {
                        LC.union(i, i - size);
                    }
                }
            }

            //start merging
            for (int i = 0; i < size * size; i++) {
                if (i / size == 0 && i % size == 0) //    ( 0 , 0 )
                {
                    ;
                } else if (i / size == 0 && i % size != 0) //row 1
                {
                    if ((map[i / size][i % size] > map[i / size][i % size - 1]) && (map[i / size][i % size - 1] > 0)) {
                        LC.union(i, i - 1);
                    } else if ((map[i / size][i % size - 1] > map[i / size][i % size]) && (map[i / size][i % size] > 0)) {
                        LC.union(i - 1, i);
                    }
                } else if (i % size == 0 && i / size != 0) //column 1
                {
                    if ((map[i / size][i % size] > map[i / size - 1][i % size] )&& (map[i / size - 1][i % size] > 0)) {
                        LC.union(i, i - size);
                    } else if ((map[i / size - 1][i % size] > map[i / size][i % size]) && (map[i / size][i % size] > 0)) {
                        LC.union(i - size, i);
                    }
                } else if (i % size != 0 && i / size != 0) {
                    if ((map[i / size][i % size] > map[i / size - 1][i % size]) && (map[i / size - 1][i % size] > 0)) {
                        LC.union(i, i - size);
                    } else if ((map[i / size - 1][i % size] > map[i / size][i % size]) && (map[i / size][i % size] > 0)) {
                        LC.union(i - size, i);
                    }

                    if ((map[i / size][i % size] > map[i / size][i % size - 1]) && (map[i / size][i % size - 1] > 0)) {
                        LC.union(i, i - 1);
                    } else if ((map[i / size][i % size - 1] > map[i / size][i % size]) && (map[i / size][i % size] > 0)) {
                        LC.union(i - 1, i);
                    }
                }
            }

            int ans = LC.find(a * size + b);

            System.out.print(map[ans / size][ans % size]);

        } catch (RuntimeException e) {

            throw e;
        }
    }
}

