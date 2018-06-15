/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author daf
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
//import edu.princeton.cs.algs4.UF;

public class Percolation {

    public static void main(String[] args) throws Exception {

        //Read input and pre-proccess all attribute
        BufferedReader br = null;
        String sCurrentLine;
        List<String[]> info = new ArrayList<String[]>();

        try {
            br = new BufferedReader(new FileReader(args[0]));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] Line = sCurrentLine.split("","");
                info.add(sCurrentLine.split("",""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int size = Integer.valueOf(info.get(0)[0]);
        int[][] pairlist = new int[info.size() - 1][2];
        for (int i = 0; i < info.size() - 1; i++) {
            pairlist[i][0] = Integer.parseInt(info.get(i + 1)[0]);
            pairlist[i][1] = Integer.parseInt(info.get(i + 1)[1]);
            //System.out.println(pairlist[i][0]+ "" "" +pairlist[i][1]);
        }

        boolean[][] openloc = new boolean[size][size];
        //System.out.print(openloc[8]);

        UF uf = new UF(size * size);

        search:
        {
            for (int i = 0; i < pairlist.length; i++) {
                openloc[(pairlist[i][0] - 1)][(pairlist[i][1] - 1)] = true;
                //System.out.println((pairlist[i][0]-1) +"" ""+ (pairlist[i][1]-1));

                try {
                    if (openloc[(pairlist[i][0] - 1) - 1][(pairlist[i][1] - 1)] == true) {
                        uf.union((pairlist[i][0] - 1) * size + (pairlist[i][1] - 1), ((pairlist[i][0] - 1) - 1) * size + (pairlist[i][1] - 1));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }
                try {
                    if (openloc[(pairlist[i][0] - 1) + 1][(pairlist[i][1] - 1)] == true) {
                        uf.union((pairlist[i][0] - 1) * size + (pairlist[i][1] - 1), ((pairlist[i][0] - 1) + 1) * size + (pairlist[i][1] - 1));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }
                try {
                    if (openloc[(pairlist[i][0] - 1)][(pairlist[i][1] - 1) - 1] == true) {
                        uf.union((pairlist[i][0] - 1) * size + (pairlist[i][1] - 1), (pairlist[i][0] - 1) * size + ((pairlist[i][1] - 1) - 1));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }
                try {
                    if (openloc[(pairlist[i][0] - 1)][(pairlist[i][1] - 1) + 1] == true) {
                        uf.union((pairlist[i][0] - 1) * size + (pairlist[i][1] - 1), (pairlist[i][0] - 1) * size + ((pairlist[i][1] - 1) + 1));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }

                for (int frc = 0; frc < size; frc++) {
                    if (openloc[0][frc]) {
                        for (int lrc = 0; lrc < size; lrc++) {
                            if (openloc[size - 1][lrc]) {
                                if (uf.connected(frc, (size - 1) * size + lrc)) {
                                    System.out.println(info.get(i + 1)[0] + "","" + info.get(i + 1)[1]);
                                    break search;
                                }
                            }
                        }
                    }
                }

            }
            System.out.println(-1);
        }

    }

}

