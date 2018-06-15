
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.ArrayList;


/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Robert
 */
public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            is = new FileInputStream(args[0]);
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            
            
            // read file.txt
            boolean output = false;
            Scanner inputdata = new Scanner(br);
            String data = inputdata.nextLine();

            // num is matrix size(num*num)
            // matrix starts from 1
            // label = 00
            int num = Integer.parseInt(data);
            int[][] label = new int[num][num];
            for (int[] row : label) {
                java.util.Arrays.fill(row, 0);
            }
            
            ArrayList<String> myList = new ArrayList<String>();

            while(inputdata.hasNextLine()){
                String[] tmp = inputdata.nextLine().split("","");
                for(int i=0; i<tmp.length; i++)
                    myList.add(tmp[i]);                
            }
            // nextLine(point) and totaline
            
            int k = myList.size()/2;
            int count = 0;
            int[][] point = new int[k][2];            
            for (int i=0; i< k; i++){
                point[i][0] = Integer.parseInt((String) myList.get(count)) - 1;
                point[i][1] = Integer.parseInt((String) myList.get(count+1)) - 1;
                count = count+2;
            }
                
            // create class uf (0 to num*num+1)
            // 0 = virtual top
            // num*num+1 = virtual bottom
            UF uf = new UF(num * num + 2);

            // start 1 (using for)
            for (int i = 0; i < point.length; i++) {
                int x = point[i][0];
                int y = point[i][1];
                label[x][y] = 1;
                int index = x * num + y + 1;

                // matrix union
                // union top
                if (x == 0) {
                    uf.union(index, 0);
                    if (label[x + 1][y] == 1)
                        uf.union(index, index + num);
                    if (y == 0) {
                        if (label[x][y + 1] == 1)
                            uf.union(index, index + 1);
                    } else if (y == num-1) {
                        if (label[x][y - 1] == 1)
                            uf.union(index, index - 1);
                    } else {
                        if (label[x][y - 1] == 1)
                            uf.union(index, index - 1);
                        if (label[x][y + 1] == 1)
                            uf.union(index, index + 1);
                    }
                } 
                //union bottom
                else if (x == num - 1) {
                    uf.union(index, num * num + 1);
                    if (label[x - 1][y] == 1)
                        uf.union(index, index - num);
                    if (y == 0) {
                        if (label[x][y + 1] == 1)
                            uf.union(index, index + 1);
                    } else if (y == num-1) {
                        if (label[x][y - 1] == 1)
                            uf.union(index, index - 1);
                    } else {
                        if (label[x][y - 1] == 1)
                            uf.union(index, index - 1);
                        if (label[x][y + 1] == 1)
                            uf.union(index, index + 1);
                    }
                } // union the rest
                else {
                    // union to top one
                    if (label[x - 1][y] == 1)
                        uf.union(index, index - num);
                    // union to down one
                    if (label[x + 1][y] == 1)
                        uf.union(index, index + num);
                    
                    if (y == 0) {
                        if (label[x][y + 1] == 1)
                            uf.union(index, index + 1);
                    } else if (y == num-1) {
                        if (label[x][y - 1] == 1)
                            uf.union(index, index - 1);
                    } else {
                        if (label[x][y - 1] == 1)
                            uf.union(index, index - 1);
                        if (label[x][y + 1] == 1)
                            uf.union(index, index + 1);
                    }
                }
                output = uf.connected(0, num * num + 1);
                if (output == true) {
                    System.out.print((x+1)+"",""+(y+1));
                    break;
                }
            }
            if (output == false)
                System.out.print(""-1""); 
        } catch (FileNotFoundException | NumberFormatException e) {
        } finally {
            // releases resources associated with the streams
            if (is != null) {
                is.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }

}

