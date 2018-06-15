/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author user
 */
public class Percolation {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            int gridsize = Integer.parseInt(data);
            String[][] grid = new String[gridsize][gridsize];
            QuickFindUF union = new QuickFindUF(gridsize*gridsize+2);
            for (int i = 0; i < gridsize; i++) {
               union.union(union.count()-1, i); 
               union.union(union.count()-2, union.count()-3-i); 
            }
            
            String str = null;
            while ((str = br.readLine())!= null) {
                String tempArray[]  = str.split("","");
                int x = Integer.parseInt(tempArray[0]);
                int y = Integer.parseInt(tempArray[1]);

                grid[x-1][y-1]=(""1"");
                int site = (x-1)*gridsize+(y-1);
                if(x != 1){
                    if (grid[x-2][y-1]==(""1"")) {
                        union.union(site, site-gridsize);
                    }
                }
                if(x != gridsize){
                    if (grid[x][y-1]==(""1"")) {
                        union.union(site, site+gridsize);
                    }
                }
                if(y != 1){
                    if (grid[x-1][y-2]==(""1"")) {
                        union.union(site, site-1);
                    }
                }
                if(y != gridsize){
                    if (grid[x-1][y]==(""1"")) {
                        union.union(site, site+1);
                    }
                }
                if (union.connected(union.count(), union.count()-1)) {
                    System.out.printf(tempArray[0]+"",""+tempArray[1]);
                    break;
                }
            }
            if (union.connected(union.count(), union.count()-1)==false) {
                    System.out.printf(""-1"");
                }
        }
    }
}

