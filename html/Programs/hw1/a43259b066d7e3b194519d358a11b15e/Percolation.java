import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {    
    public static boolean check(boolean[][] site, int i, int j) {
        int N = site.length;

        if (i < 0 || i >= N) return false;    // invalid row
        if (j < 0 || j >= N) return false;    // invalid column
        if (site[i][j] == false) return false;    // not an open site
        return true;
    }

    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            // store the first integer in variable stringCount (number of announced strings)
            String line;
            String[] broken_line;
            int i = 0; int j = 0;
            boolean[][] site = new boolean[num][num];
            WeightedQuickUnionUF  uf = new WeightedQuickUnionUF (num*num+2);
            for (i = 0; i < num; i++) {
                uf.union(0, i+1);
                uf.union(num*num+1, num*num-i);
            }
//            System.out.println(uf.count());
//            System.out.println(uf.connected(1, 3));
            
            while((line = br.readLine()) != null){
                broken_line = line.split("","");
                i = Integer.parseInt(broken_line[0])-1;
                j = Integer.parseInt(broken_line[1])-1;
                site[i][j] = true;
                if (check(site, i+1, j)) uf.union(num*i+j+1, num*(i+1)+j+1);        //down
                if (check(site, i, j+1)) uf.union(num*i+j+1, num*i+j+2);            //right  
                if (check(site, i, j-1)) uf.union(num*i+j+1, num*i+j);              //left
                if (check(site, i-1, j)) uf.union(num*i+j+1, num*(i-1)+j+1);        //up
                if (uf.connected(0, num*num+1)){ 
                    System.out.printf(""%d,%d\n"",i+1,j+1);
                    break;
                }
            }
            if (!(uf.connected(0, num*num+1))) System.out.printf(""-1\n"");
        }
    }
}
