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
            int start = 0;
            int end = union.count()-1;
            for (int i = 0; i < gridsize; i++) {
               union.union(start, i+1); 
            }
            for (int i = 0; i < gridsize; i++) {
               union.union(end, end-1-i);
            }
          
            int site ;
            String str = null;
            while ((str = br.readLine())!= null) {
                String tempArray[]  = str.split("","");
                int x = Integer.parseInt(tempArray[0]);
                int y = Integer.parseInt(tempArray[1]);
                int gx = x-1;
                int gy = y-1;
                grid[gx][gy]=(""1"");
                site = gx*gridsize+y;
                if(x > 1){
                    if (grid[gx-1][gy]==(""1"")) {
                        union.union(site, site-gridsize);
                    }
                }
                if(x < gridsize){
                    if (grid[gx+1][gy]==(""1"")) {
                        union.union(site, site+gridsize);
                    }
                }
                if(y > 1){
                    if (grid[gx][gy-1]==(""1"")) {
                        union.union(site, site-1);
                    }
                }
                if(y != gridsize){
                    if (grid[gx][gy+1]==(""1"")) {
                        union.union(site, site+1);
                    }
                }
                if (union.connected(start, end)) {
                    System.out.printf(tempArray[0]+"",""+tempArray[1]);
                    break;
                }
            }
            if (union.connected(start, end)==false) {
                    System.out.printf(""-1"");
                }
        }
    }
}

