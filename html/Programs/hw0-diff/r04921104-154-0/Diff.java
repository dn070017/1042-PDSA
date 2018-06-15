/*
.
 * To change this template file, choose Tools | Templates
.
 */
package hw0;

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

public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { // TODO code application logic here

        //Read input and pre-proccess all attribute
        BufferedReader br = null;
        String sCurrentLine;
        List<String[]> info = new ArrayList<String[]>();

        int numAnnounced, size;
        String[] keyName;
        String[][] binngoMtx;

        try {
            br = new BufferedReader(new FileReader(args[0]));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] Line = sCurrentLine.split("","");
                info.add(sCurrentLine.split("",""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        numAnnounced = Integer.valueOf(info.get(0)[0]);
        size = Integer.valueOf(info.get(0)[1]);
        keyName = info.get(1);
        binngoMtx = new String[size][size];
        for (int i = 0; i < size; i++) {
            binngoMtx[i] = info.get(i + 2);
        }

        //System.out.println();
        //Start game
        boolean[][] correctMap = new boolean[size][size];
        int[] straightlineIdx = new int[size * 2 + 2];
        int numstraightLine = 0;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                for (int k = 0; k < numAnnounced; k++) {
                    if (binngoMtx[row][col].equals(keyName[k])) {
                        correctMap[row][col] = true;
                        break;
                    }
                }
                if (!correctMap[row][col]) {
                    straightlineIdx[row] = -1;
                    straightlineIdx[size + col] = -1;
                    if (row == col) {
                        straightlineIdx[size * 2] = -1;
                    }
                    if (row + col == size-1) {
                        straightlineIdx[size * 2 + 1] = -1;
                    }
                }
            }
        }

        for (int c = 0; c < straightlineIdx.length; c++) {
            if (straightlineIdx[c] != -1) {
                numstraightLine++;
            }
            //System.out.println(straightlineIdx[c]);
        }
        System.out.print(numstraightLine);
       
        /*
        for (int i = 0; i < correctMap.length; i++) {
            for (int j = 0; j < correctMap[0].length; j++) {
                System.out.print(correctMap[i][j] + "" "");
            }
            System.out.print(""\n"");
        }
        */
        /*
        for(int k =0 ; k < keyName.length; k++){
            System.out.print(keyName[k]+"" "");
        }
        */
    }
    

}

